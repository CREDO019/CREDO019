package fr.bamlab.rnimageresizer;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes5.dex */
public class ImageResizerModule extends ReactContextBaseJavaModule {
    private Context context;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ImageResizerAndroid";
    }

    public ImageResizerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
    }

    /* JADX WARN: Type inference failed for: r14v0, types: [fr.bamlab.rnimageresizer.ImageResizerModule$1] */
    @ReactMethod
    public void createResizedImage(final String str, final int r17, final int r18, final String str2, final int r20, final int r21, final String str3, final boolean z, final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: fr.bamlab.rnimageresizer.ImageResizerModule.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                try {
                    ImageResizerModule.this.createResizedImageWithExceptions(str, r17, r18, str2, r20, r21, str3, z, readableMap, callback, callback2);
                } catch (IOException e) {
                    callback2.invoke(e.getMessage());
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createResizedImageWithExceptions(String str, int r15, int r16, String str2, int r18, int r19, String str3, boolean z, ReadableMap readableMap, Callback callback, Callback callback2) throws IOException {
        Bitmap.CompressFormat valueOf = Bitmap.CompressFormat.valueOf(str2);
        Uri parse = Uri.parse(str);
        Bitmap createResizedImage = ImageResizer.createResizedImage(this.context, parse, r15, r16, r18, r19, readableMap.getString("mode"), readableMap.getBoolean("onlyScaleDown"));
        if (createResizedImage == null) {
            throw new IOException("The image failed to be resized; invalid Bitmap result.");
        }
        File cacheDir = this.context.getCacheDir();
        if (str3 != null) {
            cacheDir = new File(str3);
        }
        File saveImage = ImageResizer.saveImage(createResizedImage, cacheDir, UUID.randomUUID().toString(), valueOf, r18);
        if (saveImage.isFile()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString(RNFetchBlobConst.RNFB_RESPONSE_PATH, saveImage.getAbsolutePath());
            createMap.putString("uri", Uri.fromFile(saveImage).toString());
            createMap.putString("name", saveImage.getName());
            createMap.putDouble("size", saveImage.length());
            createMap.putDouble("width", createResizedImage.getWidth());
            createMap.putDouble("height", createResizedImage.getHeight());
            if (z) {
                try {
                    ImageResizer.copyExif(this.context, parse, saveImage.getAbsolutePath());
                } catch (Exception e) {
                    Log.e("ImageResizer::createResizedImageWithExceptions", "EXIF copy failed", e);
                }
            }
            callback.invoke(createMap);
        } else {
            callback2.invoke("Error getting resized image path");
        }
        createResizedImage.recycle();
    }
}
