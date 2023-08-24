package io.invertase.googlemobileads;

import android.preference.PreferenceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import io.invertase.googlemobileads.common.ReactNativeModule;
import javax.annotation.Nonnull;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsConsentModule extends ReactNativeModule {
    private static final String TAG = "RNGoogleMobileAdsConsentModule";
    private ConsentInformation consentInformation;

    private String getConsentStatusString(int r2) {
        return r2 != 1 ? r2 != 2 ? r2 != 3 ? "UNKNOWN" : "OBTAINED" : "REQUIRED" : "NOT_REQUIRED";
    }

    public ReactNativeGoogleMobileAdsConsentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
        this.consentInformation = UserMessagingPlatform.getConsentInformation(reactApplicationContext);
    }

    @ReactMethod
    public void requestInfoUpdate(@Nonnull ReadableMap readableMap, final Promise promise) {
        try {
            ConsentRequestParameters.Builder builder = new ConsentRequestParameters.Builder();
            ConsentDebugSettings.Builder builder2 = new ConsentDebugSettings.Builder(getApplicationContext());
            if (readableMap.hasKey("testDeviceIdentifiers")) {
                ReadableArray array = readableMap.getArray("testDeviceIdentifiers");
                for (int r5 = 0; r5 < array.size(); r5++) {
                    builder2.addTestDeviceHashedId(array.getString(r5));
                }
            }
            if (readableMap.hasKey("debugGeography")) {
                builder2.setDebugGeography(readableMap.getInt("debugGeography"));
            }
            builder.setConsentDebugSettings(builder2.build());
            if (readableMap.hasKey("tagForUnderAgeOfConsent")) {
                builder.setTagForUnderAgeOfConsent(readableMap.getBoolean("tagForUnderAgeOfConsent"));
            }
            ConsentRequestParameters build = builder.build();
            if (getCurrentActivity() == null) {
                rejectPromiseWithCodeAndMessage(promise, "null-activity", "Attempted to request a consent info update but the current Activity was null.");
            } else {
                this.consentInformation.requestConsentInfoUpdate(getCurrentActivity(), build, new ConsentInformation.OnConsentInfoUpdateSuccessListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda2
                    @Override // com.google.android.ump.ConsentInformation.OnConsentInfoUpdateSuccessListener
                    public final void onConsentInfoUpdateSuccess() {
                        ReactNativeGoogleMobileAdsConsentModule.this.m204x40a06cc7(promise);
                    }
                }, new ConsentInformation.OnConsentInfoUpdateFailureListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda1
                    @Override // com.google.android.ump.ConsentInformation.OnConsentInfoUpdateFailureListener
                    public final void onConsentInfoUpdateFailure(FormError formError) {
                        ReactNativeGoogleMobileAdsConsentModule.rejectPromiseWithCodeAndMessage(Promise.this, "consent-update-failed", formError.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            rejectPromiseWithCodeAndMessage(promise, "consent-update-failed", e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$requestInfoUpdate$0$io-invertase-googlemobileads-ReactNativeGoogleMobileAdsConsentModule */
    public /* synthetic */ void m204x40a06cc7(Promise promise) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("status", getConsentStatusString(this.consentInformation.getConsentStatus()));
        createMap.putBoolean("isConsentFormAvailable", this.consentInformation.isConsentFormAvailable());
        promise.resolve(createMap);
    }

    @ReactMethod
    public void showForm(final Promise promise) {
        try {
            if (getCurrentActivity() == null) {
                rejectPromiseWithCodeAndMessage(promise, "null-activity", "Consent form attempted to show but the current Activity was null.");
            } else {
                getCurrentActivity().runOnUiThread(new Runnable() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReactNativeGoogleMobileAdsConsentModule.this.m201x40b8a80d(promise);
                    }
                });
            }
        } catch (Exception e) {
            rejectPromiseWithCodeAndMessage(promise, "consent-form-error", e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showForm$5$io-invertase-googlemobileads-ReactNativeGoogleMobileAdsConsentModule */
    public /* synthetic */ void m201x40b8a80d(final Promise promise) {
        UserMessagingPlatform.loadConsentForm(getReactApplicationContext(), new UserMessagingPlatform.OnConsentFormLoadSuccessListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda4
            @Override // com.google.android.ump.UserMessagingPlatform.OnConsentFormLoadSuccessListener
            public final void onConsentFormLoadSuccess(ConsentForm consentForm) {
                ReactNativeGoogleMobileAdsConsentModule.this.m202xcef7e6cf(promise, consentForm);
            }
        }, new UserMessagingPlatform.OnConsentFormLoadFailureListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda3
            @Override // com.google.android.ump.UserMessagingPlatform.OnConsentFormLoadFailureListener
            public final void onConsentFormLoadFailure(FormError formError) {
                ReactNativeGoogleMobileAdsConsentModule.rejectPromiseWithCodeAndMessage(Promise.this, "consent-form-error", formError.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showForm$3$io-invertase-googlemobileads-ReactNativeGoogleMobileAdsConsentModule */
    public /* synthetic */ void m202xcef7e6cf(final Promise promise, ConsentForm consentForm) {
        consentForm.show(getCurrentActivity(), new ConsentForm.OnConsentFormDismissedListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsConsentModule$$ExternalSyntheticLambda0
            @Override // com.google.android.ump.ConsentForm.OnConsentFormDismissedListener
            public final void onConsentFormDismissed(FormError formError) {
                ReactNativeGoogleMobileAdsConsentModule.this.m203x96178630(promise, formError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showForm$2$io-invertase-googlemobileads-ReactNativeGoogleMobileAdsConsentModule */
    public /* synthetic */ void m203x96178630(Promise promise, FormError formError) {
        if (formError != null) {
            rejectPromiseWithCodeAndMessage(promise, "consent-form-error", formError.getMessage());
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("status", getConsentStatusString(this.consentInformation.getConsentStatus()));
        promise.resolve(createMap);
    }

    @ReactMethod
    public void reset() {
        this.consentInformation.reset();
    }

    @ReactMethod
    public void getTCString(Promise promise) {
        try {
            promise.resolve(PreferenceManager.getDefaultSharedPreferences(getReactApplicationContext()).getString("IABTCF_TCString", null));
        } catch (Exception e) {
            rejectPromiseWithCodeAndMessage(promise, "consent-string-error", e.toString());
        }
    }
}
