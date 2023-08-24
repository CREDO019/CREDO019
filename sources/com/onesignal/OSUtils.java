package com.onesignal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.location.LocationListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.location.LocationCallback;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSUtils {
    private static final int HMS_AVAILABLE_SUCCESSFUL = 0;
    private static final String HMS_CORE_SERVICES_PACKAGE = "com.huawei.hwid";
    public static int MAX_NETWORK_REQUEST_ATTEMPT_COUNT = 3;
    static final int[] NO_RETRY_NETWROK_REQUEST_STATUS_CODES = {401, 402, 403, 404, 410};
    public static final int UNINITIALIZABLE_STATUS = -999;

    private static boolean opaqueHasClass(Class<?> cls) {
        return true;
    }

    /* loaded from: classes3.dex */
    public enum SchemaType {
        DATA("data"),
        HTTPS("https"),
        HTTP("http");
        
        private final String text;

        SchemaType(String str) {
            this.text = str;
        }

        public static SchemaType fromString(String str) {
            SchemaType[] values;
            for (SchemaType schemaType : values()) {
                if (schemaType.text.equalsIgnoreCase(str)) {
                    return schemaType;
                }
            }
            return null;
        }
    }

    public static boolean shouldRetryNetworkRequest(int r5) {
        for (int r4 : NO_RETRY_NETWROK_REQUEST_STATUS_CODES) {
            if (r5 == r4) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int initializationChecker(Context context, String str) {
        Integer checkForGooglePushLibrary;
        int deviceType = getDeviceType();
        try {
            UUID.fromString(str);
            if ("b2f7f966-d8cc-11e4-bed1-df8f05be55ba".equals(str) || "5eb5a37e-b458-11e3-ac11-000c2940e62c".equals(str)) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal Example AppID detected, please update to your app's id found on OneSignal.com");
            }
            int r4 = 1;
            if (deviceType == 1 && (checkForGooglePushLibrary = checkForGooglePushLibrary()) != null) {
                r4 = checkForGooglePushLibrary.intValue();
            }
            Integer checkAndroidSupportLibrary = checkAndroidSupportLibrary(context);
            return checkAndroidSupportLibrary != null ? checkAndroidSupportLibrary.intValue() : r4;
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "OneSignal AppId format is invalid.\nExample: 'b2f7f966-d8cc-11e4-bed1-df8f05be55ba'\n", th);
            return UNINITIALIZABLE_STATUS;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasFCMLibrary() {
        try {
            return opaqueHasClass(FirebaseMessaging.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasGMSLocationLibrary() {
        try {
            return opaqueHasClass(LocationListener.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    private static boolean hasHMSAvailabilityLibrary() {
        try {
            return opaqueHasClass(HuaweiApiAvailability.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    private static boolean hasHMSPushKitLibrary() {
        try {
            return opaqueHasClass(HmsInstanceId.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    private static boolean hasHMSAGConnectLibrary() {
        try {
            return opaqueHasClass(AGConnectServicesConfig.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasHMSLocationLibrary() {
        try {
            return opaqueHasClass(LocationCallback.class);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasAllHMSLibrariesForPushKit() {
        return hasHMSAGConnectLibrary() && hasHMSPushKitLibrary();
    }

    Integer checkForGooglePushLibrary() {
        if (hasFCMLibrary()) {
            return null;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The Firebase FCM library is missing! Please make sure to include it in your project.");
        return -4;
    }

    private static boolean hasWakefulBroadcastReceiver() {
        return true;
    }

    private static boolean hasNotificationManagerCompat() {
        return true;
    }

    private static boolean hasJobIntentService() {
        return true;
    }

    private Integer checkAndroidSupportLibrary(Context context) {
        boolean hasWakefulBroadcastReceiver = hasWakefulBroadcastReceiver();
        boolean hasNotificationManagerCompat = hasNotificationManagerCompat();
        if (!hasWakefulBroadcastReceiver && !hasNotificationManagerCompat) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "Could not find the Android Support Library. Please make sure it has been correctly added to your project.");
            return -3;
        } else if (!hasWakefulBroadcastReceiver || !hasNotificationManagerCompat) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
            return -5;
        } else if (Build.VERSION.SDK_INT < 26 || getTargetSdkVersion(context) < 26 || hasJobIntentService()) {
            return null;
        } else {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "The included Android Support Library is to old or incomplete. Please update to the 26.0.0 revision or newer.");
            return -5;
        }
    }

    private static boolean packageInstalledAndEnabled(String str) {
        try {
            return OneSignal.appContext.getPackageManager().getPackageInfo(str, 128).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGMSInstalledAndEnabled() {
        return packageInstalledAndEnabled("com.google.android.gms");
    }

    private static boolean isHMSCoreInstalledAndEnabled() {
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(OneSignal.appContext) == 0;
    }

    private static boolean isHMSCoreInstalledAndEnabledFallback() {
        return packageInstalledAndEnabled(HMS_CORE_SERVICES_PACKAGE);
    }

    private boolean supportsADM() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private boolean supportsHMS() {
        if (hasHMSAvailabilityLibrary() && hasAllHMSLibrariesForPushKit()) {
            return isHMSCoreInstalledAndEnabled();
        }
        return false;
    }

    private boolean supportsGooglePush() {
        if (hasFCMLibrary()) {
            return isGMSInstalledAndEnabled();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDeviceType() {
        if (supportsADM()) {
            return 2;
        }
        if (supportsGooglePush()) {
            return 1;
        }
        if (supportsHMS()) {
            return 13;
        }
        return (!isGMSInstalledAndEnabled() && isHMSCoreInstalledAndEnabledFallback()) ? 13 : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAndroidDeviceType() {
        return new OSUtils().getDeviceType() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFireOSDeviceType() {
        return new OSUtils().getDeviceType() == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHuaweiDeviceType() {
        return new OSUtils().getDeviceType() == 13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer getNetType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) OneSignal.appContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type == 1 || type == 9) {
                return 0;
            }
            return 1;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCarrierName() {
        try {
            String networkOperatorName = ((TelephonyManager) OneSignal.appContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkOperatorName();
            if ("".equals(networkOperatorName)) {
                return null;
            }
            return networkOperatorName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static Bundle getManifestMetaBundle(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Manifest application info not found", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getManifestMetaBoolean(Context context, String str) {
        Bundle manifestMetaBundle = getManifestMetaBundle(context);
        if (manifestMetaBundle != null) {
            return manifestMetaBundle.getBoolean(str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getManifestMeta(Context context, String str) {
        Bundle manifestMetaBundle = getManifestMetaBundle(context);
        if (manifestMetaBundle != null) {
            return manifestMetaBundle.getString(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getResourceString(Context context, String str, String str2) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "string", context.getPackageName());
        return identifier != 0 ? resources.getString(identifier) : str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidEmail(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isStringNotEmpty(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean areNotificationsEnabled(Context context) {
        try {
            return NotificationManagerCompat.from(OneSignal.appContext).areNotificationsEnabled();
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRunningOnMainThread() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void runOnMainUIThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void runOnMainThreadDelayed(Runnable runnable, int r4) {
        new Handler(Looper.getMainLooper()).postDelayed(runnable, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getTargetSdkVersion(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 15;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidResourceName(String str) {
        return (str == null || str.matches("^[0-9]")) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri getSoundUri(Context context, String str) {
        int identifier;
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        if (isValidResourceName(str) && (identifier = resources.getIdentifier(str, "raw", packageName)) != 0) {
            return Uri.parse("android.resource://" + packageName + "/" + identifier);
        }
        int identifier2 = resources.getIdentifier("onesignal_default_sound", "raw", packageName);
        if (identifier2 != 0) {
            return Uri.parse("android.resource://" + packageName + "/" + identifier2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] parseVibrationPattern(JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            Object opt = jSONObject.opt("vib_pt");
            if (opt instanceof String) {
                jSONArray = new JSONArray((String) opt);
            } else {
                jSONArray = (JSONArray) opt;
            }
            long[] jArr = new long[jSONArray.length()];
            for (int r1 = 0; r1 < jSONArray.length(); r1++) {
                jArr[r1] = jSONArray.optLong(r1);
            }
            return jArr;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sleep(int r2) {
        try {
            Thread.sleep(r2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void openURLInBrowser(String str) {
        openURLInBrowser(Uri.parse(str.trim()));
    }

    private static void openURLInBrowser(Uri uri) {
        OneSignal.appContext.startActivity(openURLInBrowserIntent(uri));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent openURLInBrowserIntent(Uri uri) {
        Intent makeMainSelectorActivity;
        SchemaType fromString = uri.getScheme() != null ? SchemaType.fromString(uri.getScheme()) : null;
        if (fromString == null) {
            fromString = SchemaType.HTTP;
            if (!uri.toString().contains("://")) {
                uri = Uri.parse("http://" + uri.toString());
            }
        }
        if (C35791.$SwitchMap$com$onesignal$OSUtils$SchemaType[fromString.ordinal()] == 1) {
            makeMainSelectorActivity = Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_BROWSER");
            makeMainSelectorActivity.setData(uri);
        } else {
            makeMainSelectorActivity = new Intent("android.intent.action.VIEW", uri);
        }
        makeMainSelectorActivity.addFlags(268435456);
        return makeMainSelectorActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.onesignal.OSUtils$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C35791 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$OSUtils$SchemaType;

        static {
            int[] r0 = new int[SchemaType.values().length];
            $SwitchMap$com$onesignal$OSUtils$SchemaType = r0;
            try {
                r0[SchemaType.DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$onesignal$OSUtils$SchemaType[SchemaType.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$onesignal$OSUtils$SchemaType[SchemaType.HTTP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Set<T> newConcurrentSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<String> newStringSetFromJSONArray(JSONArray jSONArray) throws JSONException {
        HashSet hashSet = new HashSet();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            hashSet.add(jSONArray.getString(r1));
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasConfigChangeFlag(Activity activity, int r3) {
        try {
            return (activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).configChanges & r3) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    static Collection<String> extractStringsFromCollection(Collection<Object> collection) {
        ArrayList arrayList = new ArrayList();
        if (collection == null) {
            return arrayList;
        }
        for (Object obj : collection) {
            if (obj instanceof String) {
                arrayList.add((String) obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle jsonStringToBundle(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle = new Bundle();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
            return bundle;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldLogMissingAppIdError(String str) {
        if (str != null) {
            return false;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "OneSignal was not initialized, ensure to always initialize OneSignal from the onCreate of your Application class.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getRandomDelay(int r1, int r2) {
        return new Random().nextInt((r2 + 1) - r1) + r1;
    }

    static Throwable getRootCauseThrowable(Throwable th) {
        while (th.getCause() != null && th.getCause() != th) {
            th = th.getCause();
        }
        return th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRootCauseMessage(Throwable th) {
        return getRootCauseThrowable(th).getMessage();
    }
}
