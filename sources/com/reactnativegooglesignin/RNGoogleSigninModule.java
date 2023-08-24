package com.reactnativegooglesignin;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = RNGoogleSigninModule.MODULE_NAME)
/* loaded from: classes3.dex */
public class RNGoogleSigninModule extends ReactContextBaseJavaModule {
    public static final String ERROR_USER_RECOVERABLE_AUTH = "ERROR_USER_RECOVERABLE_AUTH";
    public static final String MODULE_NAME = "RNGoogleSignin";
    public static final String PLAY_SERVICES_NOT_AVAILABLE = "PLAY_SERVICES_NOT_AVAILABLE";
    public static final int RC_SIGN_IN = 9001;
    public static final int REQUEST_CODE_RECOVER_AUTH = 53294;
    private static final String SHOULD_RECOVER = "SHOULD_RECOVER";
    private GoogleSignInClient _apiClient;
    private PendingAuthRecovery pendingAuthRecovery;
    private PromiseWrapper promiseWrapper;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public PromiseWrapper getPromiseWrapper() {
        return this.promiseWrapper;
    }

    public RNGoogleSigninModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.promiseWrapper = new PromiseWrapper();
        reactApplicationContext.addActivityEventListener(new RNGoogleSigninActivityEventListener());
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("BUTTON_SIZE_ICON", 2);
        hashMap.put("BUTTON_SIZE_STANDARD", 0);
        hashMap.put("BUTTON_SIZE_WIDE", 1);
        hashMap.put("BUTTON_COLOR_AUTO", 2);
        hashMap.put("BUTTON_COLOR_LIGHT", 1);
        hashMap.put("BUTTON_COLOR_DARK", 0);
        hashMap.put("SIGN_IN_CANCELLED", String.valueOf((int) GoogleSignInStatusCodes.SIGN_IN_CANCELLED));
        hashMap.put("SIGN_IN_REQUIRED", String.valueOf(4));
        hashMap.put("IN_PROGRESS", PromiseWrapper.ASYNC_OP_IN_PROGRESS);
        hashMap.put(PLAY_SERVICES_NOT_AVAILABLE, PLAY_SERVICES_NOT_AVAILABLE);
        return hashMap;
    }

    @ReactMethod
    public void playServicesAvailable(boolean z, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            Log.w(MODULE_NAME, "could not determine playServicesAvailable, activity is null");
            promise.reject(MODULE_NAME, "activity is null");
            return;
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(currentActivity);
        if (isGooglePlayServicesAvailable != 0) {
            if (z && googleApiAvailability.isUserResolvableError(isGooglePlayServicesAvailable)) {
                googleApiAvailability.getErrorDialog(currentActivity, isGooglePlayServicesAvailable, 2404).show();
            }
            promise.reject(PLAY_SERVICES_NOT_AVAILABLE, "Play services not available");
            return;
        }
        promise.resolve(true);
    }

    @ReactMethod
    public void configure(ReadableMap readableMap, Promise promise) {
        this._apiClient = GoogleSignIn.getClient(getReactApplicationContext(), C4050Utils.getSignInOptions(C4050Utils.createScopesArray(readableMap.hasKey("scopes") ? readableMap.getArray("scopes") : Arguments.createArray()), readableMap.hasKey("webClientId") ? readableMap.getString("webClientId") : null, readableMap.hasKey("offlineAccess") && readableMap.getBoolean("offlineAccess"), readableMap.hasKey("forceCodeForRefreshToken") && readableMap.getBoolean("forceCodeForRefreshToken"), readableMap.hasKey("accountName") ? readableMap.getString("accountName") : null, readableMap.hasKey("hostedDomain") ? readableMap.getString("hostedDomain") : null));
        promise.resolve(null);
    }

    @ReactMethod
    public void signInSilently(Promise promise) {
        if (this._apiClient == null) {
            rejectWithNullClientError(promise);
            return;
        }
        this.promiseWrapper.setPromiseWithInProgressCheck(promise, "signInSilently");
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.reactnativegooglesignin.RNGoogleSigninModule.1
            @Override // java.lang.Runnable
            public void run() {
                Task<GoogleSignInAccount> silentSignIn = RNGoogleSigninModule.this._apiClient.silentSignIn();
                if (silentSignIn.isSuccessful()) {
                    RNGoogleSigninModule.this.handleSignInTaskResult(silentSignIn);
                } else {
                    silentSignIn.addOnCompleteListener(new OnCompleteListener() { // from class: com.reactnativegooglesignin.RNGoogleSigninModule.1.1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task task) {
                            RNGoogleSigninModule.this.handleSignInTaskResult(task);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSignInTaskResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount result = task.getResult(ApiException.class);
            if (result == null) {
                this.promiseWrapper.reject(MODULE_NAME, "GoogleSignInAccount instance was null");
            } else {
                this.promiseWrapper.resolve(C4050Utils.getUserProperties(result));
            }
        } catch (ApiException e) {
            int statusCode = e.getStatusCode();
            this.promiseWrapper.reject(String.valueOf(statusCode), GoogleSignInStatusCodes.getStatusCodeString(statusCode));
        }
    }

    @ReactMethod
    public void signIn(ReadableMap readableMap, Promise promise) {
        if (this._apiClient == null) {
            rejectWithNullClientError(promise);
            return;
        }
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(MODULE_NAME, "activity is null");
            return;
        }
        this.promiseWrapper.setPromiseWithInProgressCheck(promise, "signIn");
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.reactnativegooglesignin.RNGoogleSigninModule.2
            @Override // java.lang.Runnable
            public void run() {
                currentActivity.startActivityForResult(RNGoogleSigninModule.this._apiClient.getSignInIntent(), RNGoogleSigninModule.RC_SIGN_IN);
            }
        });
    }

    /* loaded from: classes3.dex */
    private class RNGoogleSigninActivityEventListener extends BaseActivityEventListener {
        private RNGoogleSigninActivityEventListener() {
        }

        @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
        public void onActivityResult(Activity activity, int r2, int r3, Intent intent) {
            if (r2 == 9001) {
                RNGoogleSigninModule.this.handleSignInTaskResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
            } else if (r2 == 53294) {
                if (r3 == -1) {
                    RNGoogleSigninModule.this.rerunFailedAuthTokenTask();
                } else {
                    RNGoogleSigninModule.this.promiseWrapper.reject(RNGoogleSigninModule.MODULE_NAME, "Failed authentication recovery attempt, probably user-rejected.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rerunFailedAuthTokenTask() {
        WritableMap userProperties = this.pendingAuthRecovery.getUserProperties();
        if (userProperties != null) {
            new AccessTokenRetrievalTask(this).execute(userProperties, null);
        } else {
            this.promiseWrapper.reject(MODULE_NAME, "rerunFailedAuthTokenTask: recovery failed");
        }
    }

    @ReactMethod
    public void signOut(final Promise promise) {
        GoogleSignInClient googleSignInClient = this._apiClient;
        if (googleSignInClient == null) {
            rejectWithNullClientError(promise);
        } else {
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.reactnativegooglesignin.RNGoogleSigninModule.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    RNGoogleSigninModule.this.handleSignOutOrRevokeAccessTask(task, promise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSignOutOrRevokeAccessTask(Task<Void> task, Promise promise) {
        if (task.isSuccessful()) {
            promise.resolve(null);
            return;
        }
        int exceptionCode = C4050Utils.getExceptionCode(task);
        promise.reject(String.valueOf(exceptionCode), GoogleSignInStatusCodes.getStatusCodeString(exceptionCode));
    }

    @ReactMethod
    public void revokeAccess(final Promise promise) {
        GoogleSignInClient googleSignInClient = this._apiClient;
        if (googleSignInClient == null) {
            rejectWithNullClientError(promise);
        } else {
            googleSignInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.reactnativegooglesignin.RNGoogleSigninModule.4
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    RNGoogleSigninModule.this.handleSignOutOrRevokeAccessTask(task, promise);
                }
            });
        }
    }

    @ReactMethod
    public void isSignedIn(Promise promise) {
        promise.resolve(Boolean.valueOf(GoogleSignIn.getLastSignedInAccount(getReactApplicationContext()) != null));
    }

    @ReactMethod
    public void getCurrentUser(Promise promise) {
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(getReactApplicationContext());
        promise.resolve(lastSignedInAccount == null ? null : C4050Utils.getUserProperties(lastSignedInAccount));
    }

    @ReactMethod
    public void clearCachedAccessToken(String str, Promise promise) {
        this.promiseWrapper.setPromiseWithInProgressCheck(promise, "clearCachedAccessToken");
        new TokenClearingTask(this).execute(str);
    }

    @ReactMethod
    public void getTokens(Promise promise) {
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(getReactApplicationContext());
        if (lastSignedInAccount == null) {
            promise.reject(MODULE_NAME, "getTokens requires a user to be signed in");
            return;
        }
        this.promiseWrapper.setPromiseWithInProgressCheck(promise, "getTokens");
        startTokenRetrievalTaskWithRecovery(lastSignedInAccount);
    }

    private void startTokenRetrievalTaskWithRecovery(GoogleSignInAccount googleSignInAccount) {
        WritableMap userProperties = C4050Utils.getUserProperties(googleSignInAccount);
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean(SHOULD_RECOVER, true);
        new AccessTokenRetrievalTask(this).execute(userProperties, createMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class AccessTokenRetrievalTask extends AsyncTask<WritableMap, Void, Void> {
        private WeakReference<RNGoogleSigninModule> weakModuleRef;

        AccessTokenRetrievalTask(RNGoogleSigninModule rNGoogleSigninModule) {
            this.weakModuleRef = new WeakReference<>(rNGoogleSigninModule);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(WritableMap... writableMapArr) {
            WritableMap writableMap = writableMapArr[0];
            RNGoogleSigninModule rNGoogleSigninModule = this.weakModuleRef.get();
            if (rNGoogleSigninModule == null) {
                return null;
            }
            try {
                insertAccessTokenIntoUserProperties(rNGoogleSigninModule, writableMap);
                rNGoogleSigninModule.getPromiseWrapper().resolve(writableMap);
            } catch (Exception e) {
                handleException(rNGoogleSigninModule, e, writableMap, writableMapArr.length >= 2 ? writableMapArr[1] : null);
            }
            return null;
        }

        private void insertAccessTokenIntoUserProperties(RNGoogleSigninModule rNGoogleSigninModule, WritableMap writableMap) throws IOException, GoogleAuthException {
            writableMap.putString("accessToken", GoogleAuthUtil.getToken(rNGoogleSigninModule.getReactApplicationContext(), new Account(writableMap.getMap("user").getString("email"), "com.google"), C4050Utils.scopesToString(writableMap.getArray("scopes"))));
        }

        private void handleException(RNGoogleSigninModule rNGoogleSigninModule, Exception exc, WritableMap writableMap, WritableMap writableMap2) {
            if (!(exc instanceof UserRecoverableAuthException)) {
                rNGoogleSigninModule.promiseWrapper.reject(RNGoogleSigninModule.MODULE_NAME, exc);
                return;
            }
            if (!(writableMap2 != null && writableMap2.hasKey(RNGoogleSigninModule.SHOULD_RECOVER) && writableMap2.getBoolean(RNGoogleSigninModule.SHOULD_RECOVER))) {
                rNGoogleSigninModule.promiseWrapper.reject(RNGoogleSigninModule.ERROR_USER_RECOVERABLE_AUTH, exc);
            } else {
                attemptRecovery(rNGoogleSigninModule, exc, writableMap);
            }
        }

        private void attemptRecovery(RNGoogleSigninModule rNGoogleSigninModule, Exception exc, WritableMap writableMap) {
            Activity currentActivity = rNGoogleSigninModule.getCurrentActivity();
            if (currentActivity == null) {
                rNGoogleSigninModule.pendingAuthRecovery = null;
                PromiseWrapper promiseWrapper = rNGoogleSigninModule.promiseWrapper;
                promiseWrapper.reject(RNGoogleSigninModule.MODULE_NAME, "Cannot attempt recovery auth because app is not in foreground. " + exc.getLocalizedMessage());
                return;
            }
            rNGoogleSigninModule.pendingAuthRecovery = new PendingAuthRecovery(writableMap);
            currentActivity.startActivityForResult(((UserRecoverableAuthException) exc).getIntent(), RNGoogleSigninModule.REQUEST_CODE_RECOVER_AUTH);
        }
    }

    /* loaded from: classes3.dex */
    private static class TokenClearingTask extends AsyncTask<String, Void, Void> {
        private WeakReference<RNGoogleSigninModule> weakModuleRef;

        TokenClearingTask(RNGoogleSigninModule rNGoogleSigninModule) {
            this.weakModuleRef = new WeakReference<>(rNGoogleSigninModule);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            RNGoogleSigninModule rNGoogleSigninModule = this.weakModuleRef.get();
            if (rNGoogleSigninModule == null) {
                return null;
            }
            try {
                GoogleAuthUtil.clearToken(rNGoogleSigninModule.getReactApplicationContext(), strArr[0]);
                rNGoogleSigninModule.getPromiseWrapper().resolve(null);
            } catch (Exception e) {
                rNGoogleSigninModule.promiseWrapper.reject(RNGoogleSigninModule.MODULE_NAME, e);
            }
            return null;
        }
    }

    private void rejectWithNullClientError(Promise promise) {
        promise.reject(MODULE_NAME, "apiClient is null - call configure first");
    }
}
