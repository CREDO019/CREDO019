package io.invertase.googlemobileads;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import androidx.core.p005os.EnvironmentCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.onesignal.OneSignalDbContract;
import io.invertase.googlemobileads.common.ReactNativeEventEmitter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsCommon {
    static AdSize getAdSizeForAdaptiveBanner(String str, ReactViewGroup reactViewGroup) {
        try {
            Activity currentActivity = ((ReactContext) reactViewGroup.getContext()).getCurrentActivity();
            Objects.requireNonNull(currentActivity);
            Activity activity = currentActivity;
            Display defaultDisplay = currentActivity.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            int r0 = (int) (displayMetrics.widthPixels / displayMetrics.density);
            if ("INLINE_ADAPTIVE_BANNER".equals(str)) {
                return AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(reactViewGroup.getContext(), r0);
            }
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(reactViewGroup.getContext(), r0);
        } catch (Exception unused) {
            return AdSize.BANNER;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AdSize getAdSize(String str, ReactViewGroup reactViewGroup) {
        if (str.matches("ADAPTIVE_BANNER|ANCHORED_ADAPTIVE_BANNER|INLINE_ADAPTIVE_BANNER")) {
            return getAdSizeForAdaptiveBanner(str, reactViewGroup);
        }
        return stringToAdSize(str);
    }

    static AdSize stringToAdSize(String str) {
        Matcher matcher = Pattern.compile("([0-9]+)x([0-9]+)").matcher(str);
        if (matcher.find()) {
            return new AdSize(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        String upperCase = str.toUpperCase();
        char c = 65535;
        switch (upperCase.hashCode()) {
            case -1966536496:
                if (upperCase.equals("LARGE_BANNER")) {
                    c = 2;
                    break;
                }
                break;
            case -1008851236:
                if (upperCase.equals("FULL_BANNER")) {
                    c = 4;
                    break;
                }
                break;
            case -96588539:
                if (upperCase.equals("MEDIUM_RECTANGLE")) {
                    c = 3;
                    break;
                }
                break;
            case -14796567:
                if (upperCase.equals("WIDE_SKYSCRAPER")) {
                    c = 1;
                    break;
                }
                break;
            case 66994602:
                if (upperCase.equals("FLUID")) {
                    c = 0;
                    break;
                }
                break;
            case 446888797:
                if (upperCase.equals("LEADERBOARD")) {
                    c = 5;
                    break;
                }
                break;
            case 1951953708:
                if (upperCase.equals("BANNER")) {
                    c = 7;
                    break;
                }
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c == 5) {
                                return AdSize.LEADERBOARD;
                            }
                            return AdSize.BANNER;
                        }
                        return AdSize.FULL_BANNER;
                    }
                    return AdSize.MEDIUM_RECTANGLE;
                }
                return AdSize.LARGE_BANNER;
            }
            return AdSize.WIDE_SKYSCRAPER;
        }
        return AdSize.FLUID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap errorCodeToMap(int r4) {
        WritableMap createMap = Arguments.createMap();
        if (r4 == 0) {
            createMap.putString("code", "error-code-internal-error");
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "Something happened internally; for instance, an invalid response was received from the ad server.");
        } else if (r4 == 1) {
            createMap.putString("code", "error-code-invalid-request");
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "The ad request was invalid; for instance, the ad unit ID was incorrect.");
        } else if (r4 == 2) {
            createMap.putString("code", "error-code-network-error");
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "The ad request was unsuccessful due to network connectivity.");
        } else if (r4 == 3) {
            createMap.putString("code", "error-code-no-fill");
            createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "The ad request was successful, but no ad was returned due to lack of ad inventory.");
        }
        return createMap;
    }

    public static AdManagerAdRequest buildAdRequest(ReadableMap readableMap) {
        AdManagerAdRequest.Builder builder = new AdManagerAdRequest.Builder();
        Bundle bundle = new Bundle();
        if (readableMap.hasKey("requestNonPersonalizedAdsOnly") && readableMap.getBoolean("requestNonPersonalizedAdsOnly")) {
            bundle.putString("npa", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        if (readableMap.hasKey("networkExtras")) {
            for (Map.Entry<String, Object> entry : readableMap.getMap("networkExtras").toHashMap().entrySet()) {
                bundle.putString(entry.getKey(), (String) entry.getValue());
            }
        }
        builder.addNetworkExtrasBundle(AdMobAdapter.class, bundle);
        if (readableMap.hasKey("keywords")) {
            ReadableArray array = readableMap.getArray("keywords");
            Objects.requireNonNull(array);
            Iterator<Object> it = array.toArrayList().iterator();
            while (it.hasNext()) {
                builder.addKeyword((String) it.next());
            }
        }
        if (readableMap.hasKey("contentUrl")) {
            String string = readableMap.getString("contentUrl");
            Objects.requireNonNull(string);
            builder.setContentUrl(string);
        }
        if (readableMap.hasKey("requestAgent")) {
            String string2 = readableMap.getString("requestAgent");
            Objects.requireNonNull(string2);
            builder.setRequestAgent(string2);
        }
        if (readableMap.hasKey("customTargeting")) {
            for (Map.Entry<String, Object> entry2 : readableMap.getMap("customTargeting").toHashMap().entrySet()) {
                builder.addCustomTargeting(entry2.getKey(), (String) entry2.getValue());
            }
        }
        if (readableMap.hasKey("publisherProvidedId")) {
            String string3 = readableMap.getString("publisherProvidedId");
            Objects.requireNonNull(string3);
            builder.setPublisherProvidedId(string3);
        }
        return builder.build();
    }

    public static void sendAdEvent(String str, int r4, String str2, String str3, @Nullable WritableMap writableMap) {
        ReactNativeEventEmitter sharedInstance = ReactNativeEventEmitter.getSharedInstance();
        WritableMap createMap = Arguments.createMap();
        createMap.putString(SessionDescription.ATTR_TYPE, str2);
        if (writableMap != null) {
            createMap.putMap("error", writableMap);
        }
        sharedInstance.sendEvent(new ReactNativeGoogleMobileAdsEvent(str, r4, str3, createMap));
    }

    public static void sendAdEvent(String str, int r4, String str2, String str3, @Nullable WritableMap writableMap, @Nullable WritableMap writableMap2) {
        ReactNativeEventEmitter sharedInstance = ReactNativeEventEmitter.getSharedInstance();
        WritableMap createMap = Arguments.createMap();
        createMap.putString(SessionDescription.ATTR_TYPE, str2);
        if (writableMap != null) {
            createMap.putMap("error", writableMap);
        }
        if (writableMap2 != null) {
            createMap.putMap("data", writableMap2);
        }
        sharedInstance.sendEvent(new ReactNativeGoogleMobileAdsEvent(str, r4, str3, createMap));
    }

    public static String[] getCodeAndMessageFromAdError(AdError adError) {
        String str;
        String message = adError.getMessage();
        int code = adError.getCode();
        if (code == 0) {
            str = "internal-error";
        } else if (code == 1) {
            str = "invalid-request";
        } else if (code == 2) {
            str = "network-error";
        } else if (code != 3) {
            switch (code) {
                case 8:
                    str = "app-id-missing";
                    break;
                case 9:
                    str = "mediation-no-fill";
                    break;
                case 10:
                    str = "request-id-mismatch";
                    break;
                case 11:
                    str = "invalid-ad-string";
                    break;
                default:
                    str = EnvironmentCompat.MEDIA_UNKNOWN;
                    break;
            }
        } else {
            str = "no-fill";
        }
        return new String[]{str, message};
    }

    public static boolean isAdManagerUnit(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("/");
    }
}
