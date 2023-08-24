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
public class FacebookStoriesShare extends SingleShareIntent {
    private static final String PACKAGE = "com.facebook.katana";
    private static final String PLAY_STORE_LINK = "market://details?id=com.facebook.katana";

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

    public FacebookStoriesShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        setIntent(new Intent("com.facebook.stories.ADD_TO_STORY"));
    }

    @Override // cl.json.social.SingleShareIntent, cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException, IllegalArgumentException {
        super.open(readableMap);
        shareStory(readableMap);
        openIntentChooser(readableMap);
    }

    private void shareStory(ReadableMap readableMap) {
        String string;
        if (!hasValidKey("appId", readableMap)) {
            throw new IllegalArgumentException("appId was not provided.");
        }
        if (!hasValidKey("backgroundImage", readableMap) && !hasValidKey("backgroundVideo", readableMap) && !hasValidKey("stickerImage", readableMap)) {
            throw new IllegalArgumentException("Invalid background or sticker assets provided.");
        }
        Activity currentActivity = this.reactContext.getCurrentActivity();
        boolean z = false;
        if (currentActivity == null) {
            TargetChosenReceiver.sendCallback(false, "Something went wrong");
            return;
        }
        this.intent.putExtra("com.facebook.platform.extra.APPLICATION_ID", readableMap.getString("appId"));
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
                string = readableMap.getString("backgroundImage");
            } else {
                string = hasValidKey("backgroundVideo", readableMap) ? readableMap.getString("backgroundVideo") : "";
            }
            ShareFile shareFile = new ShareFile(string, MimeTypes.IMAGE_JPEG, AppStateModule.APP_STATE_BACKGROUND, z2, this.reactContext);
            this.intent.setDataAndType(shareFile.getURI(), shareFile.getType());
            this.intent.setFlags(1);
        }
        if (hasValidKey("stickerImage", readableMap)) {
            ShareFile shareFile2 = new ShareFile(readableMap.getString("stickerImage"), "image/png", "sticker", z2, this.reactContext);
            if (!valueOf.booleanValue()) {
                this.intent.setType(MediaTypes.ImageAllMimeType);
            }
            this.intent.putExtra("interactive_asset_uri", shareFile2.getURI());
            currentActivity.grantUriPermission(PACKAGE, shareFile2.getURI(), 1);
        }
    }
}
