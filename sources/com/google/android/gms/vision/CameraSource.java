package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.Frame;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    private int facing;
    private int rotation;
    private Context zze;
    private final Object zzf;
    private Camera zzg;
    private Size zzh;
    private float zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private String zzm;
    private SurfaceTexture zzn;
    private boolean zzo;
    private Thread zzp;
    private zzb zzq;
    private Map<byte[], ByteBuffer> zzr;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public interface ShutterCallback {
        void onShutter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public class zza implements Camera.PreviewCallback {
        private zza() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzq.zza(bArr, camera);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static class zzc implements Camera.ShutterCallback {
        private ShutterCallback zzaa;

        private zzc() {
        }

        @Override // android.hardware.Camera.ShutterCallback
        public final void onShutter() {
            ShutterCallback shutterCallback = this.zzaa;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    class zzd implements Camera.PictureCallback {
        private PictureCallback zzab;

        private zzd() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zzab;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzf) {
                if (CameraSource.this.zzg != null) {
                    CameraSource.this.zzg.startPreview();
                }
            }
        }
    }

    public void release() {
        synchronized (this.zzf) {
            stop();
            this.zzq.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class zze {
        private Size zzac;
        private Size zzad;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zzac = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzad = new Size(size2.width, size2.height);
            }
        }

        public final Size zzb() {
            return this.zzac;
        }

        @Nullable
        public final Size zzc() {
            return this.zzad;
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            this.zzg = zza();
            SurfaceTexture surfaceTexture = new SurfaceTexture(100);
            this.zzn = surfaceTexture;
            this.zzg.setPreviewTexture(surfaceTexture);
            this.zzo = true;
            this.zzg.startPreview();
            Thread thread = new Thread(this.zzq);
            this.zzp = thread;
            thread.setName("gms.vision.CameraSource");
            this.zzq.setActive(true);
            this.zzp.start();
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public class zzb implements Runnable {
        private Detector<?> zzt;
        private long zzx;
        private ByteBuffer zzz;
        private long zzv = SystemClock.elapsedRealtime();
        private final Object lock = new Object();
        private boolean zzw = true;
        private int zzy = 0;

        zzb(Detector<?> detector) {
            this.zzt = detector;
        }

        final void release() {
            this.zzt.release();
            this.zzt = null;
        }

        final void setActive(boolean z) {
            synchronized (this.lock) {
                this.zzw = z;
                this.lock.notifyAll();
            }
        }

        final void zza(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                ByteBuffer byteBuffer = this.zzz;
                if (byteBuffer != null) {
                    camera.addCallbackBuffer(byteBuffer.array());
                    this.zzz = null;
                }
                if (!CameraSource.this.zzr.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zzx = SystemClock.elapsedRealtime() - this.zzv;
                this.zzy++;
                this.zzz = (ByteBuffer) CameraSource.this.zzr.get(bArr);
                this.lock.notifyAll();
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            Frame build;
            ByteBuffer byteBuffer;
            while (true) {
                synchronized (this.lock) {
                    while (true) {
                        z = this.zzw;
                        if (!z || this.zzz != null) {
                            break;
                        }
                        try {
                            this.lock.wait();
                        } catch (InterruptedException e) {
                            Log.d("CameraSource", "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (!z) {
                        return;
                    }
                    build = new Frame.Builder().setImageData(this.zzz, CameraSource.this.zzh.getWidth(), CameraSource.this.zzh.getHeight(), 17).setId(this.zzy).setTimestampMillis(this.zzx).setRotation(CameraSource.this.rotation).build();
                    byteBuffer = this.zzz;
                    this.zzz = null;
                }
                try {
                    this.zzt.receiveFrame(build);
                } catch (Exception e2) {
                    Log.e("CameraSource", "Exception thrown from receiver.", e2);
                } finally {
                    CameraSource.this.zzg.addCallbackBuffer(byteBuffer.array());
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private final Detector<?> zzt;
        private CameraSource zzu;

        public Builder(Context context, Detector<?> detector) {
            CameraSource cameraSource = new CameraSource();
            this.zzu = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            }
            this.zzt = detector;
            cameraSource.zze = context;
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                StringBuilder sb = new StringBuilder(28);
                sb.append("Invalid fps: ");
                sb.append(f);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzu.zzi = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int r4, int r5) {
            if (r4 <= 0 || r4 > 1000000 || r5 <= 0 || r5 > 1000000) {
                StringBuilder sb = new StringBuilder(45);
                sb.append("Invalid preview size: ");
                sb.append(r4);
                sb.append("x");
                sb.append(r5);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzu.zzj = r4;
            this.zzu.zzk = r5;
            return this;
        }

        public Builder setFacing(int r4) {
            if (r4 != 0 && r4 != 1) {
                StringBuilder sb = new StringBuilder(27);
                sb.append("Invalid camera: ");
                sb.append(r4);
                throw new IllegalArgumentException(sb.toString());
            }
            this.zzu.facing = r4;
            return this;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.zzu.zzl = z;
            return this;
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", str));
                str = null;
            }
            this.zzu.zzm = str;
            return this;
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzu;
            cameraSource.getClass();
            cameraSource.zzq = new zzb(this.zzt);
            return this.zzu;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                return this;
            }
            Camera zza2 = zza();
            this.zzg = zza2;
            zza2.setPreviewDisplay(surfaceHolder);
            this.zzg.startPreview();
            this.zzp = new Thread(this.zzq);
            this.zzq.setActive(true);
            this.zzp.start();
            this.zzo = false;
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzf) {
            this.zzq.setActive(false);
            Thread thread = this.zzp;
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException unused) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzp = null;
            }
            Camera camera = this.zzg;
            if (camera != null) {
                camera.stopPreview();
                this.zzg.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzo) {
                        this.zzg.setPreviewTexture(null);
                    } else {
                        this.zzg.setPreviewDisplay(null);
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e("CameraSource", sb.toString());
                }
                this.zzg.release();
                this.zzg = null;
            }
            this.zzr.clear();
        }
    }

    public Size getPreviewSize() {
        return this.zzh;
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzf) {
            if (this.zzg != null) {
                zzc zzcVar = new zzc();
                zzcVar.zzaa = shutterCallback;
                zzd zzdVar = new zzd();
                zzdVar.zzab = pictureCallback;
                this.zzg.takePicture(zzcVar, null, null, zzdVar);
            }
        }
    }

    private CameraSource() {
        this.zzf = new Object();
        this.facing = 0;
        this.zzi = 30.0f;
        this.zzj = 1024;
        this.zzk = 768;
        this.zzl = false;
        this.zzr = new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.hardware.Camera zza() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 575
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zza():android.hardware.Camera");
    }

    private final byte[] zza(Size size) {
        byte[] bArr = new byte[((int) Math.ceil(((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)) / 8.0d)) + 1];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzr.put(bArr, wrap);
        return bArr;
    }
}
