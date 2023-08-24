package com.google.android.cameraview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import com.canhub.cropper.CropImageOptions;
import com.google.android.cameraview.CameraViewImpl;
import com.google.android.cameraview.PreviewImpl;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

/* loaded from: classes2.dex */
class Camera2 extends CameraViewImpl implements MediaRecorder.OnInfoListener, MediaRecorder.OnErrorListener {
    private static final SparseIntArray INTERNAL_FACINGS;
    private static final int MAX_PREVIEW_HEIGHT = 1080;
    private static final int MAX_PREVIEW_WIDTH = 1920;
    private static final String TAG = "Camera2";
    private AspectRatio mAspectRatio;
    private boolean mAutoFocus;
    Set<String> mAvailableCameras;
    CameraDevice mCamera;
    private CameraCharacteristics mCameraCharacteristics;
    private final CameraDevice.StateCallback mCameraDeviceCallback;
    private String mCameraId;
    private final CameraManager mCameraManager;
    PictureCaptureCallback mCaptureCallback;
    CameraCaptureSession mCaptureSession;
    private int mDisplayOrientation;
    private int mFacing;
    private int mFlash;
    private float mFocusDepth;
    private int mImageFormat;
    private Rect mInitialCropRegion;
    private AspectRatio mInitialRatio;
    private boolean mIsRecording;
    private boolean mIsScanning;
    private MediaRecorder mMediaRecorder;
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener;
    private Size mPictureSize;
    private final SizeMap mPictureSizes;
    CaptureRequest.Builder mPreviewRequestBuilder;
    private final SizeMap mPreviewSizes;
    private Surface mPreviewSurface;
    private ImageReader mScanImageReader;
    private final CameraCaptureSession.StateCallback mSessionCallback;
    private ImageReader mStillImageReader;
    private String mVideoPath;
    private int mWhiteBalance;
    private float mZoom;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        INTERNAL_FACINGS = sparseIntArray;
        sparseIntArray.put(0, 1);
        sparseIntArray.put(1, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Camera2(CameraViewImpl.Callback callback, PreviewImpl previewImpl, Context context) {
        super(callback, previewImpl);
        this.mCameraDeviceCallback = new CameraDevice.StateCallback() { // from class: com.google.android.cameraview.Camera2.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(CameraDevice cameraDevice) {
                Camera2.this.mCamera = cameraDevice;
                Camera2.this.mCallback.onCameraOpened();
                Camera2.this.startCaptureSession();
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onClosed(CameraDevice cameraDevice) {
                Camera2.this.mCallback.onCameraClosed();
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(CameraDevice cameraDevice) {
                Camera2.this.mCamera = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(CameraDevice cameraDevice, int r4) {
                Log.e(Camera2.TAG, "onError: " + cameraDevice.getId() + " (" + r4 + ")");
                Camera2.this.mCamera = null;
            }
        };
        this.mSessionCallback = new CameraCaptureSession.StateCallback() { // from class: com.google.android.cameraview.Camera2.2
            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                if (Camera2.this.mCamera == null) {
                    return;
                }
                Camera2.this.mCaptureSession = cameraCaptureSession;
                Camera2 camera2 = Camera2.this;
                camera2.mInitialCropRegion = (Rect) camera2.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION);
                Camera2.this.updateAutoFocus();
                Camera2.this.updateFlash();
                Camera2.this.updateFocusDepth();
                Camera2.this.updateWhiteBalance();
                Camera2.this.updateZoom();
                try {
                    Camera2.this.mCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), Camera2.this.mCaptureCallback, null);
                } catch (CameraAccessException e) {
                    Log.e(Camera2.TAG, "Failed to start camera preview because it couldn't access camera", e);
                } catch (IllegalStateException e2) {
                    Log.e(Camera2.TAG, "Failed to start camera preview.", e2);
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                Log.e(Camera2.TAG, "Failed to configure capture session.");
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onClosed(CameraCaptureSession cameraCaptureSession) {
                if (Camera2.this.mCaptureSession == null || !Camera2.this.mCaptureSession.equals(cameraCaptureSession)) {
                    return;
                }
                Camera2.this.mCaptureSession = null;
            }
        };
        this.mCaptureCallback = new PictureCaptureCallback() { // from class: com.google.android.cameraview.Camera2.3
            @Override // com.google.android.cameraview.Camera2.PictureCaptureCallback
            public void onPrecaptureRequired() {
                Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                setState(3);
                try {
                    Camera2.this.mCaptureSession.capture(Camera2.this.mPreviewRequestBuilder.build(), this, null);
                    Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
                } catch (CameraAccessException e) {
                    Log.e(Camera2.TAG, "Failed to run precapture sequence.", e);
                }
            }

            @Override // com.google.android.cameraview.Camera2.PictureCaptureCallback
            public void onReady() {
                Camera2.this.captureStillPicture();
            }
        };
        this.mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() { // from class: com.google.android.cameraview.Camera2.4
            @Override // android.media.ImageReader.OnImageAvailableListener
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                try {
                    Image.Plane[] planes = acquireNextImage.getPlanes();
                    if (planes.length > 0) {
                        ByteBuffer buffer = planes[0].getBuffer();
                        byte[] bArr = new byte[buffer.remaining()];
                        buffer.get(bArr);
                        if (acquireNextImage.getFormat() != 256) {
                            Camera2.this.mCallback.onFramePreview(bArr, acquireNextImage.getWidth(), acquireNextImage.getHeight(), Camera2.this.mDisplayOrientation);
                        } else {
                            Camera2.this.mCallback.onPictureTaken(bArr);
                        }
                        acquireNextImage.close();
                    }
                    if (acquireNextImage != null) {
                        acquireNextImage.close();
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (acquireNextImage != null) {
                            try {
                                acquireNextImage.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
        };
        this.mAvailableCameras = new HashSet();
        this.mPreviewSizes = new SizeMap();
        this.mPictureSizes = new SizeMap();
        this.mAspectRatio = Constants.DEFAULT_ASPECT_RATIO;
        CameraManager cameraManager = (CameraManager) context.getSystemService("camera");
        this.mCameraManager = cameraManager;
        cameraManager.registerAvailabilityCallback(new CameraManager.AvailabilityCallback() { // from class: com.google.android.cameraview.Camera2.5
            @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
            public void onCameraAvailable(String str) {
                super.onCameraAvailable(str);
                Camera2.this.mAvailableCameras.add(str);
            }

            @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
            public void onCameraUnavailable(String str) {
                super.onCameraUnavailable(str);
                Camera2.this.mAvailableCameras.remove(str);
            }
        }, (Handler) null);
        this.mImageFormat = this.mIsScanning ? 35 : 256;
        this.mPreview.setCallback(new PreviewImpl.Callback() { // from class: com.google.android.cameraview.Camera2.6
            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceChanged() {
                Camera2.this.startCaptureSession();
            }

            @Override // com.google.android.cameraview.PreviewImpl.Callback
            public void onSurfaceDestroyed() {
                Camera2.this.stop();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean start() {
        if (!chooseCameraIdByFacing()) {
            this.mAspectRatio = this.mInitialRatio;
            return false;
        }
        collectCameraInfo();
        setAspectRatio(this.mInitialRatio);
        this.mInitialRatio = null;
        prepareStillImageReader();
        prepareScanImageReader();
        startOpeningCamera();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stop() {
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCaptureSession = null;
        }
        CameraDevice cameraDevice = this.mCamera;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.mCamera = null;
        }
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
            this.mStillImageReader = null;
        }
        ImageReader imageReader2 = this.mScanImageReader;
        if (imageReader2 != null) {
            imageReader2.close();
            this.mScanImageReader = null;
        }
        MediaRecorder mediaRecorder = this.mMediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            this.mMediaRecorder.reset();
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
            if (this.mIsRecording) {
                this.mCallback.onVideoRecorded(this.mVideoPath);
                this.mIsRecording = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean isCameraOpened() {
        return this.mCamera != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFacing(int r2) {
        if (this.mFacing == r2) {
            return;
        }
        this.mFacing = r2;
        if (isCameraOpened()) {
            stop();
            start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getFacing() {
        return this.mFacing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getCameraId() {
        return Integer.parseInt(this.mCameraId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public Set<AspectRatio> getSupportedAspectRatios() {
        return this.mPreviewSizes.ratios();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public SortedSet<Size> getAvailablePictureSizes(AspectRatio aspectRatio) {
        return this.mPictureSizes.sizes(aspectRatio);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPictureSize(Size size) {
        if (size == null) {
            return;
        }
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            this.mCaptureSession.close();
            this.mCaptureSession = null;
        }
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        this.mPictureSize = size;
        prepareStillImageReader();
        startCaptureSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public Size getPictureSize() {
        return this.mPictureSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean setAspectRatio(AspectRatio aspectRatio) {
        if (aspectRatio != null && this.mPreviewSizes.isEmpty()) {
            this.mInitialRatio = aspectRatio;
            return false;
        } else if (aspectRatio == null || aspectRatio.equals(this.mAspectRatio) || !this.mPreviewSizes.ratios().contains(aspectRatio)) {
            return false;
        } else {
            this.mAspectRatio = aspectRatio;
            this.mPictureSize = this.mPictureSizes.sizes(aspectRatio).last();
            prepareStillImageReader();
            prepareScanImageReader();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.close();
                this.mCaptureSession = null;
                startCaptureSession();
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public AspectRatio getAspectRatio() {
        return this.mAspectRatio;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setAutoFocus(boolean z) {
        if (this.mAutoFocus == z) {
            return;
        }
        this.mAutoFocus = z;
        if (this.mPreviewRequestBuilder != null) {
            updateAutoFocus();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                } catch (CameraAccessException unused) {
                    this.mAutoFocus = !this.mAutoFocus;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getAutoFocus() {
        return this.mAutoFocus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFlash(int r5) {
        int r0 = this.mFlash;
        if (r0 == r5) {
            return;
        }
        this.mFlash = r5;
        if (this.mPreviewRequestBuilder != null) {
            updateFlash();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                } catch (CameraAccessException unused) {
                    this.mFlash = r0;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public int getFlash() {
        return this.mFlash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void takePicture() {
        if (this.mAutoFocus) {
            lockFocus();
        } else {
            captureStillPicture();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean record(String str, int r4, int r5, boolean z, CamcorderProfile camcorderProfile) {
        if (!this.mIsRecording) {
            setUpMediaRecorder(str, r4, r5, z, camcorderProfile);
            try {
                this.mMediaRecorder.prepare();
                CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.close();
                    this.mCaptureSession = null;
                }
                Size chooseOptimalSize = chooseOptimalSize();
                this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
                Surface previewSurface = getPreviewSurface();
                Surface surface = this.mMediaRecorder.getSurface();
                CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(3);
                this.mPreviewRequestBuilder = createCaptureRequest;
                createCaptureRequest.addTarget(previewSurface);
                this.mPreviewRequestBuilder.addTarget(surface);
                this.mCamera.createCaptureSession(Arrays.asList(previewSurface, surface), this.mSessionCallback, null);
                this.mMediaRecorder.start();
                this.mIsRecording = true;
                return true;
            } catch (CameraAccessException | IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void stopRecording() {
        if (this.mIsRecording) {
            stopMediaRecorder();
            CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.close();
                this.mCaptureSession = null;
            }
            startCaptureSession();
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setFocusDepth(float f) {
        float f2 = this.mFocusDepth;
        if (f2 == f) {
            return;
        }
        this.mFocusDepth = f;
        if (this.mCaptureSession != null) {
            updateFocusDepth();
            try {
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            } catch (CameraAccessException unused) {
                this.mFocusDepth = f2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public float getFocusDepth() {
        return this.mFocusDepth;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setZoom(float f) {
        float f2 = this.mZoom;
        if (f2 == f) {
            return;
        }
        this.mZoom = f;
        if (this.mCaptureSession != null) {
            updateZoom();
            try {
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            } catch (CameraAccessException unused) {
                this.mZoom = f2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public float getZoom() {
        return this.mZoom;
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setWhiteBalance(int r5) {
        int r0 = this.mWhiteBalance;
        if (r0 == r5) {
            return;
        }
        this.mWhiteBalance = r5;
        if (this.mCaptureSession != null) {
            updateWhiteBalance();
            try {
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            } catch (CameraAccessException unused) {
                this.mWhiteBalance = r0;
            }
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public int getWhiteBalance() {
        return this.mWhiteBalance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setScanning(boolean z) {
        if (this.mIsScanning == z) {
            return;
        }
        this.mIsScanning = z;
        if (!z) {
            this.mImageFormat = 256;
        } else {
            this.mImageFormat = 35;
        }
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCaptureSession = null;
        }
        startCaptureSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public boolean getScanning() {
        return this.mIsScanning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.CameraViewImpl
    public void setDisplayOrientation(int r2) {
        this.mDisplayOrientation = r2;
        this.mPreview.setDisplayOrientation(this.mDisplayOrientation);
    }

    private boolean chooseCameraIdByFacing() {
        try {
            int r0 = INTERNAL_FACINGS.get(this.mFacing);
            String[] cameraIdList = this.mCameraManager.getCameraIdList();
            if (cameraIdList.length == 0) {
                throw new RuntimeException("No camera available.");
            }
            for (String str : cameraIdList) {
                CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(str);
                Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                if (num != null && num.intValue() != 2) {
                    Integer num2 = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if (num2 == null) {
                        throw new NullPointerException("Unexpected state: LENS_FACING null");
                    }
                    if (num2.intValue() == r0) {
                        this.mCameraId = str;
                        this.mCameraCharacteristics = cameraCharacteristics;
                        return true;
                    }
                }
            }
            String str2 = cameraIdList[0];
            this.mCameraId = str2;
            CameraCharacteristics cameraCharacteristics2 = this.mCameraManager.getCameraCharacteristics(str2);
            this.mCameraCharacteristics = cameraCharacteristics2;
            Integer num3 = (Integer) cameraCharacteristics2.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            if (num3 != null && num3.intValue() != 2) {
                Integer num4 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (num4 == null) {
                    throw new NullPointerException("Unexpected state: LENS_FACING null");
                }
                int size = INTERNAL_FACINGS.size();
                for (int r2 = 0; r2 < size; r2++) {
                    SparseIntArray sparseIntArray = INTERNAL_FACINGS;
                    if (sparseIntArray.valueAt(r2) == num4.intValue()) {
                        this.mFacing = sparseIntArray.keyAt(r2);
                        return true;
                    }
                }
                this.mFacing = 0;
                return true;
            }
            return false;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to get a list of camera devices", e);
        }
    }

    private void collectCameraInfo() {
        android.util.Size[] outputSizes;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            throw new IllegalStateException("Failed to get configuration map: " + this.mCameraId);
        }
        this.mPreviewSizes.clear();
        for (android.util.Size size : streamConfigurationMap.getOutputSizes(this.mPreview.getOutputClass())) {
            int width = size.getWidth();
            int height = size.getHeight();
            if (width <= MAX_PREVIEW_WIDTH && height <= MAX_PREVIEW_HEIGHT) {
                this.mPreviewSizes.add(new Size(width, height));
            }
        }
        this.mPictureSizes.clear();
        collectPictureSizes(this.mPictureSizes, streamConfigurationMap);
        if (this.mPictureSize == null) {
            this.mPictureSize = this.mPictureSizes.sizes(this.mAspectRatio).last();
        }
        for (AspectRatio aspectRatio : this.mPreviewSizes.ratios()) {
            if (!this.mPictureSizes.ratios().contains(aspectRatio)) {
                this.mPreviewSizes.remove(aspectRatio);
            }
        }
        if (this.mPreviewSizes.ratios().contains(this.mAspectRatio)) {
            return;
        }
        this.mAspectRatio = this.mPreviewSizes.ratios().iterator().next();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void collectPictureSizes(SizeMap sizeMap, StreamConfigurationMap streamConfigurationMap) {
        android.util.Size[] outputSizes;
        for (android.util.Size size : streamConfigurationMap.getOutputSizes(this.mImageFormat)) {
            this.mPictureSizes.add(new Size(size.getWidth(), size.getHeight()));
        }
    }

    private void prepareStillImageReader() {
        ImageReader imageReader = this.mStillImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        ImageReader newInstance = ImageReader.newInstance(this.mPictureSize.getWidth(), this.mPictureSize.getHeight(), 256, 1);
        this.mStillImageReader = newInstance;
        newInstance.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
    }

    private void prepareScanImageReader() {
        ImageReader imageReader = this.mScanImageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        Size last = this.mPreviewSizes.sizes(this.mAspectRatio).last();
        ImageReader newInstance = ImageReader.newInstance(last.getWidth(), last.getHeight(), 35, 1);
        this.mScanImageReader = newInstance;
        newInstance.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
    }

    private void startOpeningCamera() {
        try {
            this.mCameraManager.openCamera(this.mCameraId, this.mCameraDeviceCallback, (Handler) null);
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to open camera: " + this.mCameraId, e);
        }
    }

    void startCaptureSession() {
        if (!isCameraOpened() || !this.mPreview.isReady() || this.mStillImageReader == null || this.mScanImageReader == null) {
            return;
        }
        Size chooseOptimalSize = chooseOptimalSize();
        this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
        Surface previewSurface = getPreviewSurface();
        try {
            CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(1);
            this.mPreviewRequestBuilder = createCaptureRequest;
            createCaptureRequest.addTarget(previewSurface);
            if (this.mIsScanning) {
                this.mPreviewRequestBuilder.addTarget(this.mScanImageReader.getSurface());
            }
            this.mCamera.createCaptureSession(Arrays.asList(previewSurface, this.mStillImageReader.getSurface(), this.mScanImageReader.getSurface()), this.mSessionCallback, null);
        } catch (CameraAccessException unused) {
            this.mCallback.onMountError();
        }
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void resumePreview() {
        startCaptureSession();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void pausePreview() {
        try {
            this.mCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public Surface getPreviewSurface() {
        Surface surface = this.mPreviewSurface;
        return surface != null ? surface : this.mPreview.getSurface();
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            this.mPreviewSurface = new Surface(surfaceTexture);
        } else {
            this.mPreviewSurface = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.cameraview.Camera2.7
            @Override // java.lang.Runnable
            public void run() {
                if (Camera2.this.mCaptureSession != null) {
                    Camera2.this.mCaptureSession.close();
                    Camera2.this.mCaptureSession = null;
                }
                Camera2.this.startCaptureSession();
            }
        });
    }

    @Override // com.google.android.cameraview.CameraViewImpl
    public Size getPreviewSize() {
        return new Size(this.mPreview.getWidth(), this.mPreview.getHeight());
    }

    private Size chooseOptimalSize() {
        int width = this.mPreview.getWidth();
        int height = this.mPreview.getHeight();
        if (width < height) {
            height = width;
            width = height;
        }
        SortedSet<Size> sizes = this.mPreviewSizes.sizes(this.mAspectRatio);
        for (Size size : sizes) {
            if (size.getWidth() >= width && size.getHeight() >= height) {
                return size;
            }
        }
        return sizes.last();
    }

    void updateAutoFocus() {
        if (this.mAutoFocus) {
            int[] r0 = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (r0 == null || r0.length == 0 || (r0.length == 1 && r0[0] == 0)) {
                this.mAutoFocus = false;
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
                return;
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
            return;
        }
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
    }

    void updateFlash() {
        int r0 = this.mFlash;
        if (r0 == 0) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (r0 == 1) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 3);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (r0 == 2) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 2);
        } else if (r0 == 3) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        } else if (r0 != 4) {
        } else {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 4);
            this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
        }
    }

    void updateFocusDepth() {
        if (this.mAutoFocus) {
            return;
        }
        Float f = (Float) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        Objects.requireNonNull(f, "Unexpected state: LENS_INFO_MINIMUM_FOCUS_DISTANCE null");
        this.mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(this.mFocusDepth * f.floatValue()));
    }

    void updateZoom() {
        float floatValue = (this.mZoom * (((Float) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() - 1.0f)) + 1.0f;
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (rect != null) {
            int width = rect.width();
            int height = rect.height();
            int r3 = (width - ((int) (width / floatValue))) / 2;
            int r4 = (height - ((int) (height / floatValue))) / 2;
            Rect rect2 = new Rect(rect.left + r3, rect.top + r4, rect.right - r3, rect.bottom - r4);
            if (floatValue != 1.0f) {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, rect2);
            } else {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.mInitialCropRegion);
            }
        }
    }

    void updateWhiteBalance() {
        int r0 = this.mWhiteBalance;
        if (r0 == 0) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 1);
        } else if (r0 == 1) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 6);
        } else if (r0 == 2) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 5);
        } else if (r0 == 3) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 8);
        } else if (r0 == 4) {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 3);
        } else if (r0 != 5) {
        } else {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 2);
        }
    }

    private void lockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        try {
            this.mCaptureCallback.setState(1);
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to lock focus.", e);
        }
    }

    void captureStillPicture() {
        try {
            CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(2);
            if (this.mIsScanning) {
                this.mImageFormat = 256;
                createCaptureRequest.removeTarget(this.mScanImageReader.getSurface());
            }
            createCaptureRequest.addTarget(this.mStillImageReader.getSurface());
            createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_MODE));
            int r2 = this.mFlash;
            if (r2 == 0) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                createCaptureRequest.set(CaptureRequest.FLASH_MODE, 0);
            } else if (r2 == 1) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 3);
            } else if (r2 == 2) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                createCaptureRequest.set(CaptureRequest.FLASH_MODE, 2);
            } else if (r2 == 3) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
            } else if (r2 == 4) {
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
            }
            createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(getOutputRotation()));
            createCaptureRequest.set(CaptureRequest.SCALER_CROP_REGION, this.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION));
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.capture(createCaptureRequest.build(), new CameraCaptureSession.CaptureCallback() { // from class: com.google.android.cameraview.Camera2.8
                @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                    Camera2.this.unlockFocus();
                }
            }, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Cannot capture a still picture.", e);
        }
    }

    private int getOutputRotation() {
        return ((((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue() + (this.mDisplayOrientation * (this.mFacing != 1 ? -1 : 1))) + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360;
    }

    private void setUpMediaRecorder(String str, int r4, int r5, boolean z, CamcorderProfile camcorderProfile) {
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mMediaRecorder = mediaRecorder;
        mediaRecorder.setVideoSource(2);
        if (z) {
            this.mMediaRecorder.setAudioSource(1);
        }
        this.mMediaRecorder.setOutputFile(str);
        this.mVideoPath = str;
        if (CamcorderProfile.hasProfile(getCameraId(), camcorderProfile.quality)) {
            setCamcorderProfile(camcorderProfile, z);
        } else {
            setCamcorderProfile(CamcorderProfile.get(getCameraId(), 1), z);
        }
        this.mMediaRecorder.setOrientationHint(getOutputRotation());
        if (r4 != -1) {
            this.mMediaRecorder.setMaxDuration(r4);
        }
        if (r5 != -1) {
            this.mMediaRecorder.setMaxFileSize(r5);
        }
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
    }

    private void setCamcorderProfile(CamcorderProfile camcorderProfile, boolean z) {
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        this.mMediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
        this.mMediaRecorder.setVideoSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
        this.mMediaRecorder.setVideoEncodingBitRate(camcorderProfile.videoBitRate);
        this.mMediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
        if (z) {
            this.mMediaRecorder.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
            this.mMediaRecorder.setAudioChannels(camcorderProfile.audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(camcorderProfile.audioCodec);
        }
    }

    private void stopMediaRecorder() {
        this.mIsRecording = false;
        try {
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.abortCaptures();
            this.mMediaRecorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mMediaRecorder.reset();
        this.mMediaRecorder.release();
        this.mMediaRecorder = null;
        if (this.mVideoPath == null || !new File(this.mVideoPath).exists()) {
            this.mCallback.onVideoRecorded(null);
            return;
        }
        this.mCallback.onVideoRecorded(this.mVideoPath);
        this.mVideoPath = null;
    }

    void unlockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
            updateAutoFocus();
            updateFlash();
            if (this.mIsScanning) {
                this.mImageFormat = 35;
                startCaptureSession();
            } else {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, null);
                this.mCaptureCallback.setState(0);
            }
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to restart camera preview.", e);
        }
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int r2, int r3) {
        if (r2 == 800 || r2 == 801) {
            stopRecording();
        }
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int r2, int r3) {
        stopRecording();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class PictureCaptureCallback extends CameraCaptureSession.CaptureCallback {
        static final int STATE_CAPTURING = 5;
        static final int STATE_LOCKED = 2;
        static final int STATE_LOCKING = 1;
        static final int STATE_PRECAPTURE = 3;
        static final int STATE_PREVIEW = 0;
        static final int STATE_WAITING = 4;
        private int mState;

        public abstract void onPrecaptureRequired();

        public abstract void onReady();

        PictureCaptureCallback() {
        }

        void setState(int r1) {
            this.mState = r1;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            process(captureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            process(totalCaptureResult);
        }

        private void process(CaptureResult captureResult) {
            int r0 = this.mState;
            if (r0 == 1) {
                Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num == null) {
                    return;
                }
                if (num.intValue() == 4 || num.intValue() == 5) {
                    Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    if (num2 == null || num2.intValue() == 2) {
                        setState(5);
                        onReady();
                        return;
                    }
                    setState(2);
                    onPrecaptureRequired();
                }
            } else if (r0 == 3) {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num3 == null || num3.intValue() == 5 || num3.intValue() == 4 || num3.intValue() == 2) {
                    setState(4);
                }
            } else if (r0 != 4) {
            } else {
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num4 == null || num4.intValue() != 5) {
                    setState(5);
                    onReady();
                }
            }
        }
    }
}
