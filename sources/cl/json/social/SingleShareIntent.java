package cl.json.social;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import cl.json.RNShareModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.common.internal.ImagesContract;
import com.onesignal.OneSignalDbContract;

/* loaded from: classes.dex */
public abstract class SingleShareIntent extends ShareIntent {
    protected String appStoreURL;
    protected String playStoreURL;

    public SingleShareIntent(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.playStoreURL = null;
        this.appStoreURL = null;
    }

    @Override // cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException {
        String playStoreLink;
        System.out.println(getPackage());
        if (getPackage() != null || getDefaultWebLink() != null || getPlayStoreLink() != null) {
            if (isPackageInstalled(getPackage(), this.reactContext)) {
                System.out.println("INSTALLED");
                if (getComponentClass() != null) {
                    getIntent().setComponent(new ComponentName(getPackage(), getComponentClass()));
                } else {
                    getIntent().setPackage(getPackage());
                }
                super.open(readableMap);
                return;
            }
            System.out.println("NOT INSTALLED");
            if (getDefaultWebLink() != null) {
                playStoreLink = getDefaultWebLink().replace("{url}", urlEncode(readableMap.getString(ImagesContract.URL))).replace("{message}", urlEncode(readableMap.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE)));
            } else {
                playStoreLink = getPlayStoreLink() != null ? getPlayStoreLink() : "";
            }
            setIntent(new Intent(new Intent("android.intent.action.VIEW", Uri.parse(playStoreLink))));
        }
        super.open(readableMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cl.json.social.ShareIntent
    public void openIntentChooser() throws ActivityNotFoundException {
        openIntentChooser(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void openIntentChooser(ReadableMap readableMap) throws ActivityNotFoundException {
        if (this.options.hasKey("forceDialog") && this.options.getBoolean("forceDialog")) {
            Activity currentActivity = this.reactContext.getCurrentActivity();
            if (currentActivity == null) {
                TargetChosenReceiver.sendCallback(false, "Something went wrong");
                return;
            } else if (readableMap != null && !ShareIntent.hasValidKey(NotificationCompat.CATEGORY_SOCIAL, readableMap)) {
                throw new IllegalArgumentException("social is empty");
            } else {
                if (TargetChosenReceiver.isSupported()) {
                    Intent createChooser = Intent.createChooser(getIntent(), this.chooserTitle, TargetChosenReceiver.getSharingSenderIntent(this.reactContext));
                    createChooser.addFlags(1073741824);
                    currentActivity.startActivityForResult(createChooser, RNShareModule.SHARE_REQUEST_CODE);
                    return;
                }
                Intent createChooser2 = Intent.createChooser(getIntent(), this.chooserTitle);
                createChooser2.addFlags(1073741824);
                currentActivity.startActivityForResult(createChooser2, RNShareModule.SHARE_REQUEST_CODE);
                TargetChosenReceiver.sendCallback(true, true, "OK");
                return;
            }
        }
        getIntent().addFlags(268435456);
        this.reactContext.startActivity(getIntent());
        TargetChosenReceiver.sendCallback(true, true, getIntent().getPackage());
    }
}
