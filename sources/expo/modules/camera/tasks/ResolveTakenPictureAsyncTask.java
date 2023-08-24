package expo.modules.camera.tasks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.bridge.BaseJavaModule;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import expo.modules.camera.CameraViewHelper;
import expo.modules.camera.Options;
import expo.modules.camera.utils.FileSystemUtils;
import expo.modules.kotlin.Promise;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.p023io.Closeable;

/* compiled from: ResolveTakenPictureAsyncTask.kt */
@Metadata(m184d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0016H\u0002J'\u0010\u001a\u001a\u0004\u0018\u00010\u00032\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u001c\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u0002J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u0014J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006'"}, m183d2 = {"Lexpo/modules/camera/tasks/ResolveTakenPictureAsyncTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "Landroid/os/Bundle;", "imageData", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "options", "Lexpo/modules/camera/PictureOptions;", "directory", "Ljava/io/File;", "pictureSavedDelegate", "Lexpo/modules/camera/tasks/PictureSavedDelegate;", "([BLexpo/modules/kotlin/Promise;Lexpo/modules/camera/PictureOptions;Ljava/io/File;Lexpo/modules/camera/tasks/PictureSavedDelegate;)V", "quality", "", "getQuality", "()I", "decodeAndRotateBitmap", "Landroid/graphics/Bitmap;", "angle", "Landroid/graphics/BitmapFactory$Options;", "decodeBitmap", "orientation", "bitmapOptions", "doInBackground", OutcomeEventsTable.COLUMN_NAME_PARAMS, "", "([Ljava/lang/Void;)Landroid/os/Bundle;", "getImageRotation", "handleSkipProcessing", "onPostExecute", "", "response", "writeStreamToFile", "", "inputStream", "Ljava/io/ByteArrayOutputStream;", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, Bundle> {
    private final File directory;
    private byte[] imageData;
    private Options options;
    private PictureSavedDelegate pictureSavedDelegate;
    private Promise promise;

    private final int getImageRotation(int r2) {
        if (r2 != 3) {
            if (r2 != 6) {
                return r2 != 8 ? 0 : 270;
            }
            return 90;
        }
        return RotationOptions.ROTATE_180;
    }

    public ResolveTakenPictureAsyncTask(byte[] imageData, Promise promise, Options options, File directory, PictureSavedDelegate pictureSavedDelegate) {
        Intrinsics.checkNotNullParameter(imageData, "imageData");
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(pictureSavedDelegate, "pictureSavedDelegate");
        this.imageData = imageData;
        this.promise = promise;
        this.options = options;
        this.directory = directory;
        this.pictureSavedDelegate = pictureSavedDelegate;
    }

    private final int getQuality() {
        return (int) (this.options.getQuality() * 100);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, android.graphics.Bitmap] */
    @Override // android.os.AsyncTask
    public Bundle doInBackground(Void... params) {
        Intrinsics.checkNotNullParameter(params, "params");
        if (this.options.getSkipProcessing()) {
            return handleSkipProcessing();
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.imageData);
            Bundle bundle = new Bundle();
            ExifInterface exifInterface = new ExifInterface(byteArrayInputStream);
            Map<String, Object> additionalExif = this.options.getAdditionalExif();
            if (additionalExif != null) {
                CameraViewHelper.setExifData(exifInterface, additionalExif);
            }
            int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            OutOfMemoryError outOfMemoryError = null;
            while (options.inSampleSize <= this.options.getMaxDownsampling()) {
                try {
                    objectRef.element = decodeBitmap(this.imageData, attributeInt, options);
                    break;
                } catch (OutOfMemoryError e) {
                    options.inSampleSize *= 2;
                    outOfMemoryError = e;
                }
            }
            if (objectRef.element == 0) {
                this.promise.reject("ERR_CAMERA_OUT_OF_MEMORY", "Cannot allocate enough space to process the taken picture.", outOfMemoryError);
                Closeable.closeFinally(byteArrayInputStream, null);
                return null;
            }
            if (this.options.getExif()) {
                bundle.putBundle("exif", CameraViewHelper.getExifData(exifInterface));
            }
            bundle.putInt("width", ((Bitmap) objectRef.element).getWidth());
            bundle.putInt("height", ((Bitmap) objectRef.element).getHeight());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                ((Bitmap) objectRef.element).compress(Bitmap.CompressFormat.JPEG, getQuality(), byteArrayOutputStream2);
                String writeStreamToFile = writeStreamToFile(byteArrayOutputStream2);
                if (this.options.getExif()) {
                    Intrinsics.checkNotNull(writeStreamToFile);
                    CameraViewHelper.addExifData(new ExifInterface(writeStreamToFile), exifInterface);
                }
                String uri = Uri.fromFile(new File(writeStreamToFile)).toString();
                Intrinsics.checkNotNullExpressionValue(uri, "fromFile(imageFile).toString()");
                bundle.putString("uri", uri);
                if (this.options.getBase64()) {
                    bundle.putString(RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2));
                }
                Unit unit = Unit.INSTANCE;
                Closeable.closeFinally(byteArrayOutputStream, null);
                Closeable.closeFinally(byteArrayInputStream, null);
                return bundle;
            } finally {
            }
        } catch (Exception e2) {
            if (e2 instanceof Resources.NotFoundException) {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "Documents directory of the app could not be found.", e2);
            } else if (e2 instanceof IOException) {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "An unknown I/O exception has occurred.", e2);
            } else if (e2 instanceof IllegalArgumentException) {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "An incompatible parameter has been passed in. ", e2);
            } else {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "An unknown exception has occurred.", e2);
            }
            e2.printStackTrace();
            return null;
        }
    }

    private final Bundle handleSkipProcessing() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            byteArrayOutputStream2.write(this.imageData);
            String writeStreamToFile = writeStreamToFile(byteArrayOutputStream2);
            String uri = Uri.fromFile(new File(writeStreamToFile)).toString();
            Intrinsics.checkNotNullExpressionValue(uri, "fromFile(imageFile).toString()");
            Intrinsics.checkNotNull(writeStreamToFile);
            ExifInterface exifInterface = new ExifInterface(writeStreamToFile);
            Bundle bundle = new Bundle();
            bundle.putString("uri", uri);
            bundle.putInt("width", exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, -1));
            bundle.putInt("height", exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, -1));
            if (this.options.getExif()) {
                bundle.putBundle("exif", CameraViewHelper.getExifData(exifInterface));
            }
            if (this.options.getBase64()) {
                bundle.putString(RNFetchBlobConst.RNFB_RESPONSE_BASE64, Base64.encodeToString(this.imageData, 2));
            }
            Closeable.closeFinally(byteArrayOutputStream, null);
            return bundle;
        } catch (Exception e) {
            if (e instanceof IOException) {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "An unknown I/O exception has occurred.", e);
            } else {
                this.promise.reject("E_TAKING_PICTURE_FAILED", "An unknown exception has occurred.", e);
            }
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Bundle bundle) {
        super.onPostExecute((ResolveTakenPictureAsyncTask) bundle);
        if (bundle != null) {
            if (this.options.getFastMode()) {
                Bundle bundle2 = new Bundle();
                Integer id = this.options.getId();
                if (id == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                bundle2.putInt("id", id.intValue());
                bundle2.putBundle("data", bundle);
                this.pictureSavedDelegate.onPictureSaved(bundle2);
                return;
            }
            this.promise.resolve(bundle);
        }
    }

    private final String writeStreamToFile(ByteArrayOutputStream byteArrayOutputStream) throws Exception {
        try {
            String generateOutputPath = FileSystemUtils.INSTANCE.generateOutputPath(this.directory, "Camera", ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(generateOutputPath);
            byteArrayOutputStream.writeTo(fileOutputStream);
            Unit unit = Unit.INSTANCE;
            Closeable.closeFinally(fileOutputStream, null);
            return generateOutputPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final Bitmap decodeBitmap(byte[] bArr, int r3, BitmapFactory.Options options) {
        if (r3 != 0) {
            return decodeAndRotateBitmap(bArr, getImageRotation(r3), options);
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Intrinsics.checkNotNullExpressionValue(decodeByteArray, "{\n      BitmapFactory.de…ize, bitmapOptions)\n    }");
        return decodeByteArray;
    }

    private final Bitmap decodeAndRotateBitmap(byte[] bArr, int r11, BitmapFactory.Options options) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Matrix matrix = new Matrix();
        matrix.postRotate(r11);
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(source, 0, …rce.height, matrix, true)");
        return createBitmap;
    }
}
