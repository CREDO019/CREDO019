package com.onesignal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.onesignal.AndroidSupportV4Compat;
import com.onesignal.OneSignal;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class LocationController {
    static final long BACKGROUND_UPDATE_TIME_MS = 570000;
    static final long FOREGROUND_UPDATE_TIME_MS = 270000;
    private static final long TIME_BACKGROUND_SEC = 600;
    private static final long TIME_FOREGROUND_SEC = 300;
    static Context classContext;
    static Thread fallbackFailThread;
    static Location lastLocation;
    private static boolean locationCoarse;
    private static LocationHandlerThread locationHandlerThread;
    static String requestPermission;
    private static final List<LocationPromptCompletionHandler> promptHandlers = new ArrayList();
    private static ConcurrentHashMap<PermissionType, LocationHandler> locationHandlers = new ConcurrentHashMap<>();
    static final Object syncLock = new Object() { // from class: com.onesignal.LocationController.1
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface LocationHandler {
        PermissionType getType();

        void onComplete(LocationPoint locationPoint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class LocationPromptCompletionHandler implements LocationHandler {
        /* JADX INFO: Access modifiers changed from: package-private */
        public void onAnswered(OneSignal.PromptActionResult promptActionResult) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum PermissionType {
        STARTUP,
        PROMPT_LOCATION,
        SYNC_SERVICE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocationHandlerThread getLocationHandlerThread() {
        if (locationHandlerThread == null) {
            synchronized (syncLock) {
                if (locationHandlerThread == null) {
                    locationHandlerThread = new LocationHandlerThread();
                }
            }
        }
        return locationHandlerThread;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class LocationPoint {
        Float accuracy;

        /* renamed from: bg */
        Boolean f1297bg;
        Double lat;
        Double log;
        Long timeStamp;
        Integer type;

        LocationPoint() {
        }

        public String toString() {
            return "LocationPoint{lat=" + this.lat + ", log=" + this.log + ", accuracy=" + this.accuracy + ", type=" + this.type + ", bg=" + this.f1297bg + ", timeStamp=" + this.timeStamp + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean scheduleUpdate(Context context) {
        if (!hasLocationPermission(context)) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "LocationController scheduleUpdate not possible, location permission not enabled");
            return false;
        } else if (!OneSignal.isLocationShared()) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "LocationController scheduleUpdate not possible, location shared not enabled");
            return false;
        } else {
            long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis() - getLastLocationTime();
            long j = (OneSignal.isInForeground() ? TIME_FOREGROUND_SEC : TIME_BACKGROUND_SEC) * 1000;
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, "LocationController scheduleUpdate lastTime: " + currentTimeMillis + " minTime: " + j);
            OSSyncService.getInstance().scheduleLocationUpdateTask(context, j - currentTimeMillis);
            return true;
        }
    }

    private static void setLastLocationTime(long j) {
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_LAST_LOCATION_TIME, j);
    }

    private static long getLastLocationTime() {
        return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_OS_LAST_LOCATION_TIME, -600000L);
    }

    private static boolean hasLocationPermission(Context context) {
        return AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private static void addPromptHandlerIfAvailable(LocationHandler locationHandler) {
        if (locationHandler instanceof LocationPromptCompletionHandler) {
            List<LocationPromptCompletionHandler> list = promptHandlers;
            synchronized (list) {
                list.add((LocationPromptCompletionHandler) locationHandler);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendAndClearPromptHandlers(boolean z, OneSignal.PromptActionResult promptActionResult) {
        if (!z) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "LocationController sendAndClearPromptHandlers from non prompt flow");
            return;
        }
        List<LocationPromptCompletionHandler> list = promptHandlers;
        synchronized (list) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "LocationController calling prompt handlers");
            for (LocationPromptCompletionHandler locationPromptCompletionHandler : list) {
                locationPromptCompletionHandler.onAnswered(promptActionResult);
            }
            promptHandlers.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void getLocation(Context context, boolean z, boolean z2, LocationHandler locationHandler) {
        int r4;
        addPromptHandlerIfAvailable(locationHandler);
        classContext = context;
        locationHandlers.put(locationHandler.getType(), locationHandler);
        if (!OneSignal.isLocationShared()) {
            sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.ERROR);
            fireFailedComplete();
            return;
        }
        int checkSelfPermission = AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        if (checkSelfPermission == -1) {
            r4 = AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
            locationCoarse = true;
        } else {
            r4 = -1;
        }
        int checkSelfPermission2 = Build.VERSION.SDK_INT >= 29 ? AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_BACKGROUND_LOCATION") : -1;
        if (Build.VERSION.SDK_INT < 23) {
            if (checkSelfPermission != 0 && r4 != 0) {
                sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.LOCATION_PERMISSIONS_MISSING_MANIFEST);
                locationHandler.onComplete(null);
                return;
            }
            sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.PERMISSION_GRANTED);
            startGetLocation();
        } else if (checkSelfPermission != 0) {
            try {
                List asList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                OneSignal.PromptActionResult promptActionResult = OneSignal.PromptActionResult.PERMISSION_DENIED;
                if (asList.contains("android.permission.ACCESS_FINE_LOCATION")) {
                    requestPermission = "android.permission.ACCESS_FINE_LOCATION";
                } else if (!asList.contains("android.permission.ACCESS_COARSE_LOCATION")) {
                    OneSignal.onesignalLog(OneSignal.LOG_LEVEL.INFO, "Location permissions not added on AndroidManifest file");
                    promptActionResult = OneSignal.PromptActionResult.LOCATION_PERMISSIONS_MISSING_MANIFEST;
                } else if (r4 != 0) {
                    requestPermission = "android.permission.ACCESS_COARSE_LOCATION";
                } else if (Build.VERSION.SDK_INT >= 29 && asList.contains("android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    requestPermission = "android.permission.ACCESS_BACKGROUND_LOCATION";
                }
                if (requestPermission != null && z) {
                    LocationPermissionController.INSTANCE.prompt(z2, requestPermission);
                } else if (r4 == 0) {
                    sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.PERMISSION_GRANTED);
                    startGetLocation();
                } else {
                    sendAndClearPromptHandlers(z, promptActionResult);
                    fireFailedComplete();
                }
            } catch (PackageManager.NameNotFoundException e) {
                sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.ERROR);
                e.printStackTrace();
            }
        } else if (Build.VERSION.SDK_INT >= 29 && checkSelfPermission2 != 0) {
            backgroundLocationPermissionLogic(context, z, z2);
        } else {
            sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.PERMISSION_GRANTED);
            startGetLocation();
        }
    }

    private static void backgroundLocationPermissionLogic(Context context, boolean z, boolean z2) {
        try {
            if (Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions).contains("android.permission.ACCESS_BACKGROUND_LOCATION")) {
                requestPermission = "android.permission.ACCESS_BACKGROUND_LOCATION";
            }
            if (requestPermission != null && z) {
                LocationPermissionController.INSTANCE.prompt(z2, requestPermission);
                return;
            }
            sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.PERMISSION_GRANTED);
            startGetLocation();
        } catch (PackageManager.NameNotFoundException e) {
            sendAndClearPromptHandlers(z, OneSignal.PromptActionResult.ERROR);
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startGetLocation() {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "LocationController startGetLocation with lastLocation: " + lastLocation);
        try {
            if (isGooglePlayServicesAvailable()) {
                GMSLocationController.startGetLocation();
            } else if (isHMSAvailable()) {
                HMSLocationController.startGetLocation();
            } else {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "LocationController startGetLocation not possible, no location dependency found");
                fireFailedComplete();
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Location permission exists but there was an error initializing: ", th);
            fireFailedComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void onFocusChange() {
        synchronized (syncLock) {
            if (isGooglePlayServicesAvailable()) {
                GMSLocationController.onFocusChange();
                return;
            }
            if (isHMSAvailable()) {
                HMSLocationController.onFocusChange();
            }
        }
    }

    static boolean isGooglePlayServicesAvailable() {
        return OSUtils.isAndroidDeviceType() && OSUtils.hasGMSLocationLibrary();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHMSAvailable() {
        return OSUtils.isHuaweiDeviceType() && OSUtils.hasHMSLocationLibrary();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireFailedComplete() {
        synchronized (syncLock) {
            if (isGooglePlayServicesAvailable()) {
                GMSLocationController.fireFailedComplete();
            } else if (isHMSAvailable()) {
                HMSLocationController.fireFailedComplete();
            }
        }
        fireComplete(null);
    }

    private static void fireComplete(LocationPoint locationPoint) {
        Thread thread;
        HashMap hashMap = new HashMap();
        synchronized (LocationController.class) {
            hashMap.putAll(locationHandlers);
            locationHandlers.clear();
            thread = fallbackFailThread;
        }
        for (PermissionType permissionType : hashMap.keySet()) {
            ((LocationHandler) hashMap.get(permissionType)).onComplete(locationPoint);
        }
        if (thread != null && !Thread.currentThread().equals(thread)) {
            thread.interrupt();
        }
        if (thread == fallbackFailThread) {
            synchronized (LocationController.class) {
                if (thread == fallbackFailThread) {
                    fallbackFailThread = null;
                }
            }
        }
        setLastLocationTime(OneSignal.getTime().getCurrentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void fireCompleteForLocation(Location location) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "LocationController fireCompleteForLocation with location: " + location);
        LocationPoint locationPoint = new LocationPoint();
        locationPoint.accuracy = Float.valueOf(location.getAccuracy());
        locationPoint.f1297bg = Boolean.valueOf(OneSignal.isInForeground() ^ true);
        locationPoint.type = Integer.valueOf(!locationCoarse ? 1 : 0);
        locationPoint.timeStamp = Long.valueOf(location.getTime());
        if (locationCoarse) {
            locationPoint.lat = Double.valueOf(new BigDecimal(location.getLatitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
            locationPoint.log = Double.valueOf(new BigDecimal(location.getLongitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
        } else {
            locationPoint.lat = Double.valueOf(location.getLatitude());
            locationPoint.log = Double.valueOf(location.getLongitude());
        }
        fireComplete(locationPoint);
        scheduleUpdate(classContext);
    }

    /* loaded from: classes3.dex */
    protected static class LocationHandlerThread extends HandlerThread {
        Handler mHandler;

        LocationHandlerThread() {
            super("OSH_LocationHandlerThread");
            start();
            this.mHandler = new Handler(getLooper());
        }
    }
}
