package cl.json.social;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import cl.json.ShareFile;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.exoplayer2.util.MimeTypes;
import expo.modules.imagepicker.MediaTypes;

/* loaded from: classes.dex */
public class InstagramStoriesShare extends SingleShareIntent {
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

    public InstagramStoriesShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        setIntent(new Intent("com.instagram.share.ADD_TO_STORY"));
    }

    @Override // cl.json.social.SingleShareIntent, cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException {
        super.open(readableMap);
        shareStory(readableMap);
        openIntentChooser(readableMap);
    }

    private void shareStory(ReadableMap readableMap) {
        String str;
        String string;
        String str2;
        if (!hasValidKey("backgroundImage", readableMap) && !hasValidKey("backgroundVideo", readableMap) && !hasValidKey("stickerImage", readableMap)) {
            throw new IllegalArgumentException("Invalid background or sticker assets provided.");
        }
        Activity currentActivity = this.reactContext.getCurrentActivity();
        boolean z = false;
        if (currentActivity == null) {
            TargetChosenReceiver.sendCallback(false, "Something went wrong");
            return;
        }
        this.intent.putExtra("bottom_background_color", "#906df4");
        this.intent.putExtra("top_background_color", "#837DF4");
        if (hasValidKey("attributionURL", readableMap)) {
            this.intent.putExtra("content_url", readableMap.getString("attributionURL"));
        }
        if (hasValidKey("backgroundTopColor", readableMap)) {
            this.intent.putExtra("top_background_color", readableMap.getString("backgroundTopColor"));
        }
        if (hasValidKey("backgroundBottomColor", readableMap)) {
            this.intent.putExtra("bottom_background_color", readableMap.getString("backgroundBottomColor"));
        }
        boolean z2 = false;
        if (hasValidKey("useInternalStorage", readableMap)) {
            z2 = Boolean.valueOf(readableMap.getBoolean("useInternalStorage"));
        }
        Boolean valueOf = Boolean.valueOf((hasValidKey("backgroundImage", readableMap) || hasValidKey("backgroundVideo", readableMap)) ? true : true);
        if (valueOf.booleanValue()) {
            if (hasValidKey("backgroundImage", readableMap)) {
                str = readableMap.getString("backgroundImage");
            } else if (hasValidKey("backgroundVideo", readableMap)) {
                string = readableMap.getString("backgroundVideo");
                str2 = MediaTypes.VideoAllMimeType;
                ShareFile shareFile = new ShareFile(string, str2, AppStateModule.APP_STATE_BACKGROUND, z2, this.reactContext);
                this.intent.setDataAndType(shareFile.getURI(), shareFile.getType());
                this.intent.setFlags(1);
            } else {
                str = "";
            }
            str2 = MimeTypes.IMAGE_JPEG;
            string = str;
            ShareFile shareFile2 = new ShareFile(string, str2, AppStateModule.APP_STATE_BACKGROUND, z2, this.reactContext);
            this.intent.setDataAndType(shareFile2.getURI(), shareFile2.getType());
            this.intent.setFlags(1);
        }
        if (hasValidKey("stickerImage", readableMap)) {
            ShareFile shareFile3 = new ShareFile(readableMap.getString("stickerImage"), "image/png", "sticker", z2, this.reactContext);
            if (!valueOf.booleanValue()) {
                this.intent.setType(MediaTypes.ImageAllMimeType);
            }
            this.intent.putExtra("interactive_asset_uri", shareFile3.getURI());
            currentActivity.grantUriPermission(PACKAGE, shareFile3.getURI(), 1);
        }
    }
}
