package cl.json.social;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.webkit.internal.AssetHelper;
import cl.json.RNShareModule;
import cl.json.ShareFile;
import cl.json.ShareFiles;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.internal.ImagesContract;
import com.onesignal.OneSignalDbContract;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ShareIntent {
    protected ShareFile backgroundAsset;
    protected String chooserTitle = "Share";
    protected ShareFile fileShare;
    protected Intent intent;
    protected ReadableMap options;
    protected final ReactApplicationContext reactContext;
    protected ShareFile stickerAsset;

    /* JADX INFO: Access modifiers changed from: protected */
    public String getComponentClass() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String getDefaultWebLink();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String getPackage();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String getPlayStoreLink();

    public ShareIntent(ReactApplicationContext reactApplicationContext) {
        this.reactContext = reactApplicationContext;
        setIntent(new Intent("android.intent.action.SEND"));
        getIntent().setType(AssetHelper.DEFAULT_MIME_TYPE);
    }

    public Intent excludeChooserIntent(Intent intent, ReadableMap readableMap) {
        ArrayList arrayList = new ArrayList();
        ArrayList<HashMap> arrayList2 = new ArrayList();
        Intent intent2 = new Intent(intent.getAction());
        intent2.setType(intent.getType());
        List<ResolveInfo> queryIntentActivities = this.reactContext.getPackageManager().queryIntentActivities(intent2, 0);
        if (!queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo != null && !readableMap.getArray("excludedActivityTypes").toString().contains(resolveInfo.activityInfo.packageName)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("packageName", resolveInfo.activityInfo.packageName);
                    hashMap.put("className", resolveInfo.activityInfo.name);
                    hashMap.put("simpleName", String.valueOf(resolveInfo.activityInfo.loadLabel(this.reactContext.getPackageManager())));
                    arrayList2.add(hashMap);
                }
            }
            if (!arrayList2.isEmpty()) {
                Collections.sort(arrayList2, new Comparator<HashMap<String, String>>() { // from class: cl.json.social.ShareIntent.1
                    @Override // java.util.Comparator
                    public int compare(HashMap<String, String> hashMap2, HashMap<String, String> hashMap3) {
                        return hashMap2.get("simpleName").compareTo(hashMap3.get("simpleName"));
                    }
                });
                for (HashMap hashMap2 : arrayList2) {
                    Intent intent3 = (Intent) intent.clone();
                    intent3.setPackage((String) hashMap2.get("packageName"));
                    intent3.setClassName((String) hashMap2.get("packageName"), (String) hashMap2.get("className"));
                    arrayList.add(intent3);
                }
                Intent createChooser = Intent.createChooser((Intent) arrayList.remove(arrayList.size() - 1), "share");
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
                return createChooser;
            }
        }
        return Intent.createChooser(intent, "Share");
    }

    public void open(ReadableMap readableMap) throws ActivityNotFoundException {
        this.options = readableMap;
        if (hasValidKey("isNewTask", readableMap) && readableMap.getBoolean("isNewTask")) {
            getIntent().addFlags(268468224);
        }
        if (hasValidKey("subject", readableMap)) {
            getIntent().putExtra("android.intent.extra.SUBJECT", readableMap.getString("subject"));
        }
        if (hasValidKey("email", readableMap)) {
            getIntent().putExtra("android.intent.extra.EMAIL", new String[]{readableMap.getString("email")});
        }
        if (hasValidKey("title", readableMap)) {
            this.chooserTitle = readableMap.getString("title");
        }
        String string = hasValidKey(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, readableMap) ? readableMap.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE) : "";
        String string2 = hasValidKey(NotificationCompat.CATEGORY_SOCIAL, readableMap) ? readableMap.getString(NotificationCompat.CATEGORY_SOCIAL) : "";
        if (string2.equals("sms")) {
            String string3 = readableMap.getString("recipient");
            if (!string3.isEmpty()) {
                getIntent().putExtra("address", string3);
            }
        }
        if (string2.equals("whatsapp") && readableMap.hasKey("whatsAppNumber")) {
            String string4 = readableMap.getString("whatsAppNumber");
            getIntent().putExtra("jid", string4 + "@s.whatsapp.net");
        }
        if (string2.equals("whatsappbusiness") && readableMap.hasKey("whatsAppNumber")) {
            String string5 = readableMap.getString("whatsAppNumber");
            getIntent().putExtra("jid", string5 + "@s.whatsapp.net");
        }
        if (hasValidKey("urls", readableMap)) {
            ShareFiles fileShares = getFileShares(readableMap);
            if (fileShares.isFile()) {
                ArrayList<Uri> arrayList = fileShares.getURI();
                getIntent().setAction("android.intent.action.SEND_MULTIPLE");
                getIntent().setType(fileShares.getType());
                getIntent().putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
                getIntent().addFlags(1);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                getIntent().putExtra("android.intent.extra.TEXT", string);
            } else if (!TextUtils.isEmpty(string)) {
                Intent intent = getIntent();
                intent.putExtra("android.intent.extra.TEXT", string + " " + readableMap.getArray("urls").getString(0));
            } else {
                getIntent().putExtra("android.intent.extra.TEXT", readableMap.getArray("urls").getString(0));
            }
        } else if (hasValidKey(ImagesContract.URL, readableMap)) {
            ShareFile fileShare = getFileShare(readableMap);
            this.fileShare = fileShare;
            if (fileShare.isFile()) {
                Uri uri = this.fileShare.getURI();
                getIntent().setType(this.fileShare.getType());
                getIntent().putExtra("android.intent.extra.STREAM", uri);
                getIntent().addFlags(1);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                getIntent().putExtra("android.intent.extra.TEXT", string);
            } else if (!TextUtils.isEmpty(string)) {
                Intent intent2 = getIntent();
                intent2.putExtra("android.intent.extra.TEXT", string + " " + readableMap.getString(ImagesContract.URL));
            } else {
                getIntent().putExtra("android.intent.extra.TEXT", readableMap.getString(ImagesContract.URL));
            }
        } else if (TextUtils.isEmpty(string)) {
        } else {
            getIntent().putExtra("android.intent.extra.TEXT", string);
        }
    }

    protected ShareFile getFileShare(ReadableMap readableMap) {
        String string = hasValidKey("filename", readableMap) ? readableMap.getString("filename") : null;
        boolean z = false;
        if (hasValidKey("useInternalStorage", readableMap)) {
            z = Boolean.valueOf(readableMap.getBoolean("useInternalStorage"));
        }
        Boolean bool = z;
        if (hasValidKey(SessionDescription.ATTR_TYPE, readableMap)) {
            return new ShareFile(readableMap.getString(ImagesContract.URL), readableMap.getString(SessionDescription.ATTR_TYPE), string, bool, this.reactContext);
        }
        return new ShareFile(readableMap.getString(ImagesContract.URL), string, bool, this.reactContext);
    }

    protected ShareFiles getFileShares(ReadableMap readableMap) {
        ArrayList arrayList = new ArrayList();
        if (hasValidKey("filenames", readableMap)) {
            ReadableArray array = readableMap.getArray("filenames");
            for (int r1 = 0; r1 < array.size(); r1++) {
                arrayList.add(array.getString(r1));
            }
        }
        boolean z = false;
        if (hasValidKey("useInternalStorage", readableMap)) {
            z = Boolean.valueOf(readableMap.getBoolean("useInternalStorage"));
        }
        Boolean bool = z;
        if (hasValidKey(SessionDescription.ATTR_TYPE, readableMap)) {
            return new ShareFiles(readableMap.getArray("urls"), arrayList, readableMap.getString(SessionDescription.ATTR_TYPE), bool, this.reactContext);
        }
        return new ShareFiles(readableMap.getArray("urls"), arrayList, bool, this.reactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("URLEncoder.encode() failed for " + str);
        }
    }

    protected Intent[] getIntentsToViewFile(Intent intent, Uri uri) {
        List<ResolveInfo> queryIntentActivities = this.reactContext.getPackageManager().queryIntentActivities(intent, 0);
        Intent[] intentArr = new Intent[queryIntentActivities.size()];
        for (int r1 = 0; r1 < queryIntentActivities.size(); r1++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(r1);
            String str = resolveInfo.activityInfo.packageName;
            Intent intent2 = new Intent();
            intent2.setComponent(new ComponentName(str, resolveInfo.activityInfo.name));
            intent2.setAction("android.intent.action.VIEW");
            intent2.setDataAndType(uri, intent.getType());
            intent2.addFlags(1);
            intentArr[r1] = new Intent(intent2);
        }
        return intentArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void openIntentChooser() throws ActivityNotFoundException {
        Intent createChooser;
        Activity currentActivity = this.reactContext.getCurrentActivity();
        if (currentActivity == null) {
            TargetChosenReceiver.sendCallback(false, "Something went wrong");
            return;
        }
        IntentSender intentSender = null;
        if (TargetChosenReceiver.isSupported()) {
            intentSender = TargetChosenReceiver.getSharingSenderIntent(this.reactContext);
            createChooser = Intent.createChooser(getIntent(), this.chooserTitle, intentSender);
        } else {
            createChooser = Intent.createChooser(getIntent(), this.chooserTitle);
        }
        createChooser.addFlags(1073741824);
        if (hasValidKey("showAppsToView", this.options) && hasValidKey(ImagesContract.URL, this.options)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setType(this.fileShare.getType());
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", getIntentsToViewFile(intent, this.fileShare.getURI()));
        }
        if (hasValidKey("excludedActivityTypes", this.options)) {
            if (Build.VERSION.SDK_INT >= 24) {
                createChooser.putExtra("android.intent.extra.EXCLUDE_COMPONENTS", getExcludedComponentArray(this.options.getArray("excludedActivityTypes")));
                currentActivity.startActivityForResult(createChooser, RNShareModule.SHARE_REQUEST_CODE);
            } else {
                currentActivity.startActivityForResult(excludeChooserIntent(getIntent(), this.options), RNShareModule.SHARE_REQUEST_CODE);
            }
        } else {
            currentActivity.startActivityForResult(createChooser, RNShareModule.SHARE_REQUEST_CODE);
        }
        if (intentSender == null) {
            TargetChosenReceiver.sendCallback(true, true, "OK");
        }
    }

    public static boolean isPackageInstalled(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent getIntent() {
        return this.intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public static boolean hasValidKey(String str, ReadableMap readableMap) {
        return (readableMap == null || !readableMap.hasKey(str) || readableMap.isNull(str)) ? false : true;
    }

    private ComponentName[] getExcludedComponentArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        Intent intent = new Intent(getIntent().getAction());
        intent.setType(getIntent().getType());
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryIntentActivities = this.reactContext.getPackageManager().queryIntentActivities(intent, 0);
        for (int r2 = 0; r2 < readableArray.size(); r2++) {
            String string = readableArray.getString(r2);
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(string)) {
                    arrayList.add(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                }
            }
        }
        return (ComponentName[]) arrayList.toArray(new ComponentName[0]);
    }
}
