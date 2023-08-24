package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes.dex */
public class DeviceInfo {
    public static final String OS_NAME = "android";
    private static final String SETTING_ADVERTISING_ID = "advertising_id";
    private static final String SETTING_LIMIT_AD_TRACKING = "limit_ad_tracking";
    private static final String TAG = "com.amplitude.api.DeviceInfo";
    private CachedInfo cachedInfo;
    private Context context;
    private boolean locationListening;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CachedInfo {
        private String advertisingId;
        private String appSetId;
        private String brand;
        private String carrier;
        private String country;
        private boolean gpsEnabled;
        private String language;
        private boolean limitAdTrackingEnabled;
        private String manufacturer;
        private String model;
        private String osName;
        private String osVersion;
        private String versionName;

        private String getOsName() {
            return DeviceInfo.OS_NAME;
        }

        private CachedInfo() {
            this.advertisingId = getAdvertisingId();
            this.versionName = getVersionName();
            this.osName = getOsName();
            this.osVersion = getOsVersion();
            this.brand = getBrand();
            this.manufacturer = getManufacturer();
            this.model = getModel();
            this.carrier = getCarrier();
            this.country = getCountry();
            this.language = getLanguage();
            this.gpsEnabled = checkGPSEnabled();
            this.appSetId = getAppSetId();
        }

        private String getVersionName() {
            try {
                return DeviceInfo.this.context.getPackageManager().getPackageInfo(DeviceInfo.this.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException | Exception unused) {
                return null;
            }
        }

        private String getOsVersion() {
            return Build.VERSION.RELEASE;
        }

        private String getBrand() {
            return Build.BRAND;
        }

        private String getManufacturer() {
            return Build.MANUFACTURER;
        }

        private String getModel() {
            return Build.MODEL;
        }

        private String getCarrier() {
            try {
                return ((TelephonyManager) DeviceInfo.this.context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        private String getCountry() {
            String countryFromLocation = getCountryFromLocation();
            if (C1060Utils.isEmptyString(countryFromLocation)) {
                String countryFromNetwork = getCountryFromNetwork();
                return !C1060Utils.isEmptyString(countryFromNetwork) ? countryFromNetwork : getCountryFromLocale();
            }
            return countryFromLocation;
        }

        private String getCountryFromLocation() {
            Location mostRecentLocation;
            List<Address> fromLocation;
            if (DeviceInfo.this.isLocationListening() && (mostRecentLocation = DeviceInfo.this.getMostRecentLocation()) != null) {
                try {
                    if (Geocoder.isPresent() && (fromLocation = DeviceInfo.this.getGeocoder().getFromLocation(mostRecentLocation.getLatitude(), mostRecentLocation.getLongitude(), 1)) != null) {
                        for (Address address : fromLocation) {
                            if (address != null) {
                                return address.getCountryCode();
                            }
                        }
                    }
                } catch (IOException | IllegalArgumentException | IllegalStateException | NoSuchMethodError | NullPointerException | SecurityException unused) {
                }
            }
            return null;
        }

        private String getCountryFromNetwork() {
            String networkCountryIso;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
                if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null) {
                    return null;
                }
                return networkCountryIso.toUpperCase(Locale.US);
            } catch (Exception unused) {
                return null;
            }
        }

        private Locale getLocale() {
            Configuration configuration = Resources.getSystem().getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleList locales = configuration.getLocales();
                if (locales.isEmpty()) {
                    return Locale.getDefault();
                }
                return locales.get(0);
            }
            return configuration.locale;
        }

        private String getCountryFromLocale() {
            return getLocale().getCountry();
        }

        private String getLanguage() {
            return getLocale().getLanguage();
        }

        private String getAdvertisingId() {
            if ("Amazon".equals(getManufacturer())) {
                return getAndCacheAmazonAdvertisingId();
            }
            return getAndCacheGoogleAdvertisingId();
        }

        private String getAppSetId() {
            try {
                Object invoke = Class.forName("com.google.android.gms.appset.AppSet").getMethod("getClient", Context.class).invoke(null, DeviceInfo.this.context);
                Object invoke2 = Class.forName("com.google.android.gms.tasks.Tasks").getMethod("await", Class.forName("com.google.android.gms.tasks.Task")).invoke(null, invoke.getClass().getMethod("getAppSetIdInfo", new Class[0]).invoke(invoke, new Object[0]));
                this.appSetId = (String) invoke2.getClass().getMethod("getId", new Class[0]).invoke(invoke2, new Object[0]);
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services SDK not found for app set id!");
            } catch (InvocationTargetException unused2) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services not available for app set id");
            } catch (Exception e) {
                AmplitudeLog.getLogger().m1367e(DeviceInfo.TAG, "Encountered an error connecting to Google Play Services for app set id", e);
            }
            return this.appSetId;
        }

        private String getAndCacheAmazonAdvertisingId() {
            ContentResolver contentResolver = DeviceInfo.this.context.getContentResolver();
            this.limitAdTrackingEnabled = Settings.Secure.getInt(contentResolver, DeviceInfo.SETTING_LIMIT_AD_TRACKING, 0) == 1;
            String string = Settings.Secure.getString(contentResolver, DeviceInfo.SETTING_ADVERTISING_ID);
            this.advertisingId = string;
            return string;
        }

        private String getAndCacheGoogleAdvertisingId() {
            try {
                boolean z = true;
                Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, DeviceInfo.this.context);
                Boolean bool = (Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0]);
                if (bool == null || !bool.booleanValue()) {
                    z = false;
                }
                this.limitAdTrackingEnabled = z;
                this.advertisingId = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services SDK not found for advertising id!");
            } catch (InvocationTargetException unused2) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services not available for advertising id");
            } catch (Exception e) {
                AmplitudeLog.getLogger().m1367e(DeviceInfo.TAG, "Encountered an error connecting to Google Play Services for advertising id", e);
            }
            return this.advertisingId;
        }

        private boolean checkGPSEnabled() {
            try {
                Integer num = (Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", Context.class).invoke(null, DeviceInfo.this.context);
                if (num != null) {
                    return num.intValue() == 0;
                }
                return false;
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services Util not found!");
                return false;
            } catch (IllegalAccessException unused2) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (NoSuchMethodException unused3) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (InvocationTargetException unused4) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (Exception e) {
                AmplitudeLog logger = AmplitudeLog.getLogger();
                String str = DeviceInfo.TAG;
                logger.m1362w(str, "Error when checking for Google Play Services: " + e);
                return false;
            } catch (NoClassDefFoundError unused5) {
                AmplitudeLog.getLogger().m1362w(DeviceInfo.TAG, "Google Play Services Util not found!");
                return false;
            }
        }
    }

    public DeviceInfo(Context context, boolean z) {
        this.context = context;
        this.locationListening = z;
    }

    private CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public void prefetch() {
        getCachedInfo();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getVersionName() {
        return getCachedInfo().versionName;
    }

    public String getOsName() {
        return getCachedInfo().osName;
    }

    public String getOsVersion() {
        return getCachedInfo().osVersion;
    }

    public String getBrand() {
        return getCachedInfo().brand;
    }

    public String getManufacturer() {
        return getCachedInfo().manufacturer;
    }

    public String getModel() {
        return getCachedInfo().model;
    }

    public String getCarrier() {
        return getCachedInfo().carrier;
    }

    public String getCountry() {
        return getCachedInfo().country;
    }

    public String getLanguage() {
        return getCachedInfo().language;
    }

    public String getAdvertisingId() {
        return getCachedInfo().advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return getCachedInfo().limitAdTrackingEnabled;
    }

    public String getAppSetId() {
        return getCachedInfo().appSetId;
    }

    public boolean isGooglePlayServicesEnabled() {
        return getCachedInfo().gpsEnabled;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x005a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0033 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.location.Location getMostRecentLocation() {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to get most recent location"
            boolean r1 = r8.isLocationListening()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            android.content.Context r1 = r8.context
            boolean r1 = com.amplitude.api.C1060Utils.checkLocationPermissionAllowed(r1)
            if (r1 != 0) goto L13
            return r2
        L13:
            android.content.Context r1 = r8.context
            java.lang.String r3 = "location"
            java.lang.Object r1 = r1.getSystemService(r3)
            android.location.LocationManager r1 = (android.location.LocationManager) r1
            if (r1 != 0) goto L20
            return r2
        L20:
            r3 = 1
            java.util.List r3 = r1.getProviders(r3)     // Catch: java.lang.Throwable -> L26
            goto L27
        L26:
            r3 = r2
        L27:
            if (r3 != 0) goto L2a
            return r2
        L2a:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r3.iterator()
        L33:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L5e
            java.lang.Object r5 = r3.next()
            java.lang.String r5 = (java.lang.String) r5
            android.location.Location r5 = r1.getLastKnownLocation(r5)     // Catch: java.lang.Exception -> L44 java.lang.SecurityException -> L4e
            goto L58
        L44:
            com.amplitude.api.AmplitudeLog r5 = com.amplitude.api.AmplitudeLog.getLogger()
            java.lang.String r6 = com.amplitude.api.DeviceInfo.TAG
            r5.m1362w(r6, r0)
            goto L57
        L4e:
            com.amplitude.api.AmplitudeLog r5 = com.amplitude.api.AmplitudeLog.getLogger()
            java.lang.String r6 = com.amplitude.api.DeviceInfo.TAG
            r5.m1362w(r6, r0)
        L57:
            r5 = r2
        L58:
            if (r5 == 0) goto L33
            r4.add(r5)
            goto L33
        L5e:
            r0 = -1
            java.util.Iterator r3 = r4.iterator()
        L64:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L7e
            java.lang.Object r4 = r3.next()
            android.location.Location r4 = (android.location.Location) r4
            long r5 = r4.getTime()
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 <= 0) goto L64
            long r0 = r4.getTime()
            r2 = r4
            goto L64
        L7e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DeviceInfo.getMostRecentLocation():android.location.Location");
    }

    public boolean isLocationListening() {
        return this.locationListening;
    }

    public void setLocationListening(boolean z) {
        this.locationListening = z;
    }

    protected Geocoder getGeocoder() {
        return new Geocoder(this.context, Locale.ENGLISH);
    }
}
