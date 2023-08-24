package cl.json.social;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import cl.json.ShareFile;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.imagepicker.MediaTypes;

/* loaded from: classes.dex */
public class InstagramShare extends SingleShareIntent {
    private static final String PACKAGE = "com.instagram.android";
    private static final String PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=com.instagram.android";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cl.json.social.ShareIntent
    public String getDefaultWebLink() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cl.json.social.ShareIntent
    public String getPackage() {
        return PACKAGE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cl.json.social.ShareIntent
    public String getPlayStoreLink() {
        return PLAY_STORE_LINK;
    }

    public InstagramShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.social.SingleShareIntent, cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException {
        super.open(readableMap);
        if (!ShareIntent.hasValidKey(ImagesContract.URL, readableMap)) {
            Log.e("RNShare", "No url provided");
            return;
        }
        String string = readableMap.getString(ImagesContract.URL);
        if (Boolean.valueOf(string.startsWith("instagram://")).booleanValue()) {
            openInstagramUrlScheme(string);
        } else if (!ShareIntent.hasValidKey(SessionDescription.ATTR_TYPE, readableMap)) {
            Log.e("RNShare", "No type provided");
        } else if (Boolean.valueOf(readableMap.getString(SessionDescription.ATTR_TYPE).startsWith("image")).booleanValue()) {
            openInstagramIntentChooserForImage(string, this.chooserTitle);
        } else {
            super.openIntentChooser();
        }
    }

    protected void openInstagramUrlScheme(String str) {
        Uri parse = Uri.parse(str);
        getIntent().setAction("android.intent.action.VIEW");
        getIntent().setData(parse);
        super.openIntentChooser();
    }

    protected void openInstagramIntentChooserForImage(String str, String str2) {
        Uri uri = new ShareFile(str, MimeTypes.IMAGE_JPEG, "image", Boolean.valueOf(ShareIntent.hasValidKey("useInternalStorage", this.options) && this.options.getBoolean("useInternalStorage")), this.reactContext).getURI();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(MediaTypes.ImageAllMimeType);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setPackage(PACKAGE);
        Intent intent2 = new Intent("com.instagram.share.ADD_TO_STORY");
        intent2.setDataAndType(uri, "jpg");
        intent2.addFlags(1);
        intent2.setPackage(PACKAGE);
        Intent createChooser = Intent.createChooser(intent, str2);
        createChooser.addFlags(268435456);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{intent2});
        this.reactContext.getCurrentActivity().grantUriPermission(PACKAGE, uri, 1);
        this.reactContext.startActivity(createChooser);
    }
}
