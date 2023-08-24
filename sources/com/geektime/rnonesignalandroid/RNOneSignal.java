package com.geektime.rnonesignalandroid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.onesignal.OSDeviceState;
import com.onesignal.OSEmailSubscriptionObserver;
import com.onesignal.OSEmailSubscriptionStateChanges;
import com.onesignal.OSInAppMessage;
import com.onesignal.OSInAppMessageAction;
import com.onesignal.OSInAppMessageLifecycleHandler;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OSOutcomeEvent;
import com.onesignal.OSPermissionObserver;
import com.onesignal.OSPermissionStateChanges;
import com.onesignal.OSSMSSubscriptionObserver;
import com.onesignal.OSSMSSubscriptionStateChanges;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RNOneSignal extends ReactContextBaseJavaModule implements OSPermissionObserver, OSSubscriptionObserver, OneSignal.OSNotificationOpenedHandler, OSEmailSubscriptionObserver, LifecycleEventListener, OneSignal.OSInAppMessageClickHandler, OSSMSSubscriptionObserver {
    public static final String HIDDEN_MESSAGE_KEY = "hidden";
    private boolean hasSetEmailSubscriptionObserver;
    private boolean hasSetInAppClickedHandler;
    private boolean hasSetPermissionObserver;
    private boolean hasSetSMSSubscriptionObserver;
    private boolean hasSetSubscriptionObserver;
    private OSInAppMessageAction inAppMessageActionResult;
    private ReactApplicationContext mReactApplicationContext;
    private ReactContext mReactContext;
    private HashMap<String, OSNotificationReceivedEvent> notificationReceivedEventCache;
    private boolean oneSignalInitDone;
    private Callback pendingGetTagsCallback;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "OneSignal";
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @ReactMethod
    public void removeListeners(int r1) {
    }

    private String appIdFromManifest(ReactApplicationContext reactApplicationContext) {
        try {
            PackageManager packageManager = reactApplicationContext.getPackageManager();
            String packageName = reactApplicationContext.getPackageName();
            reactApplicationContext.getPackageManager();
            return packageManager.getApplicationInfo(packageName, 128).metaData.getString("onesignal_app_id");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private void removeObservers() {
        removeEmailSubscriptionObserver();
        removePermissionObserver();
        removeSubscriptionObserver();
        removeSMSSubscriptionObserver();
    }

    private void removeHandlers() {
        OneSignal.setInAppMessageClickHandler(null);
        OneSignal.setNotificationOpenedHandler(null);
        OneSignal.setNotificationWillShowInForegroundHandler(null);
        OneSignal.setInAppMessageLifecycleHandler(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, Object obj) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject jsonFromErrorMessageString(String str) throws JSONException {
        return new JSONObject().put("error", str);
    }

    public RNOneSignal(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.hasSetInAppClickedHandler = false;
        this.hasSetSubscriptionObserver = false;
        this.hasSetEmailSubscriptionObserver = false;
        this.hasSetSMSSubscriptionObserver = false;
        this.hasSetPermissionObserver = false;
        this.mReactApplicationContext = reactApplicationContext;
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.notificationReceivedEventCache = new HashMap<>();
    }

    private void initOneSignal() {
        OneSignal.sdkType = "react";
        Context currentActivity = this.mReactApplicationContext.getCurrentActivity();
        if (this.oneSignalInitDone) {
            Log.e("OneSignal", "Already initialized the OneSignal React-Native SDK");
            return;
        }
        this.oneSignalInitDone = true;
        if (currentActivity == null) {
            currentActivity = this.mReactApplicationContext.getApplicationContext();
        }
        OneSignal.setInAppMessageClickHandler(this);
        OneSignal.initWithContext(currentActivity);
    }

    @ReactMethod
    public void setAppId(String str) {
        OneSignal.setAppId(str);
    }

    @Override // com.onesignal.OSPermissionObserver
    public void onOSPermissionChanged(OSPermissionStateChanges oSPermissionStateChanges) {
        Log.i("OneSignal", "sending permission change event");
        sendEvent("OneSignal-permissionChanged", RNUtils.jsonToWritableMap(oSPermissionStateChanges.toJSONObject()));
    }

    @Override // com.onesignal.OSSubscriptionObserver
    public void onOSSubscriptionChanged(OSSubscriptionStateChanges oSSubscriptionStateChanges) {
        Log.i("OneSignal", "sending subscription change event");
        sendEvent("OneSignal-subscriptionChanged", RNUtils.jsonToWritableMap(oSSubscriptionStateChanges.toJSONObject()));
    }

    @Override // com.onesignal.OSEmailSubscriptionObserver
    public void onOSEmailSubscriptionChanged(OSEmailSubscriptionStateChanges oSEmailSubscriptionStateChanges) {
        Log.i("OneSignal", "sending email subscription change event");
        sendEvent("OneSignal-emailSubscriptionChanged", RNUtils.jsonToWritableMap(oSEmailSubscriptionStateChanges.toJSONObject()));
    }

    @Override // com.onesignal.OSSMSSubscriptionObserver
    public void onSMSSubscriptionChanged(OSSMSSubscriptionStateChanges oSSMSSubscriptionStateChanges) {
        Log.i("OneSignal", "sending SMS subscription change event");
        sendEvent("OneSignal-smsSubscriptionChanged", RNUtils.jsonToWritableMap(oSSMSSubscriptionStateChanges.toJSONObject()));
    }

    @ReactMethod
    public void addPermissionObserver() {
        if (this.hasSetPermissionObserver) {
            return;
        }
        OneSignal.addPermissionObserver(this);
        this.hasSetPermissionObserver = true;
    }

    @ReactMethod
    public void removePermissionObserver() {
        if (this.hasSetPermissionObserver) {
            OneSignal.removePermissionObserver(this);
            this.hasSetPermissionObserver = false;
        }
    }

    @ReactMethod
    public void addSubscriptionObserver() {
        if (this.hasSetSubscriptionObserver) {
            return;
        }
        OneSignal.addSubscriptionObserver(this);
        this.hasSetSubscriptionObserver = true;
    }

    @ReactMethod
    public void removeSubscriptionObserver() {
        if (this.hasSetSubscriptionObserver) {
            OneSignal.removeSubscriptionObserver(this);
            this.hasSetSubscriptionObserver = false;
        }
    }

    @ReactMethod
    public void addEmailSubscriptionObserver() {
        if (this.hasSetEmailSubscriptionObserver) {
            return;
        }
        OneSignal.addEmailSubscriptionObserver(this);
        this.hasSetEmailSubscriptionObserver = true;
    }

    @ReactMethod
    public void removeEmailSubscriptionObserver() {
        if (this.hasSetEmailSubscriptionObserver) {
            OneSignal.removeEmailSubscriptionObserver(this);
            this.hasSetEmailSubscriptionObserver = false;
        }
    }

    @ReactMethod
    public void addSMSSubscriptionObserver() {
        if (this.hasSetSMSSubscriptionObserver) {
            return;
        }
        OneSignal.addSMSSubscriptionObserver(this);
        this.hasSetSMSSubscriptionObserver = true;
    }

    @ReactMethod
    public void removeSMSSubscriptionObserver() {
        if (this.hasSetSMSSubscriptionObserver) {
            OneSignal.removeSMSSubscriptionObserver(this);
            this.hasSetSMSSubscriptionObserver = false;
        }
    }

    @ReactMethod
    public void getDeviceState(Promise promise) {
        OSDeviceState deviceState = OneSignal.getDeviceState();
        if (deviceState == null) {
            Log.e("OneSignal", "getDeviceState: OSDeviceState is null");
            promise.reject("Null OSDeviceState", "OSDeviceState is null");
            return;
        }
        promise.resolve(RNUtils.jsonToWritableMap(deviceState.toJSONObject()));
    }

    @ReactMethod
    public void setLanguage(String str, Callback callback, Callback callback2) {
        OneSignal.setLanguage(str, new OneSignal.OSSetLanguageCompletionHandler(callback, callback2) { // from class: com.geektime.rnonesignalandroid.RNOneSignal.1
            final Callback[] callbackArr;
            final /* synthetic */ Callback val$failureCallback;
            final /* synthetic */ Callback val$successCallback;

            {
                this.val$successCallback = callback;
                this.val$failureCallback = callback2;
                this.callbackArr = new Callback[]{callback, callback2};
            }

            @Override // com.onesignal.OneSignal.OSSetLanguageCompletionHandler
            public void onSuccess(String str2) {
                Callback[] callbackArr = this.callbackArr;
                if (callbackArr[0] != null) {
                    if (str2 == null) {
                        str2 = "{'success' : 'true', 'message' : 'Successfully set language.'}";
                    }
                    callbackArr[0].invoke(str2);
                    Callback[] callbackArr2 = this.callbackArr;
                    callbackArr2[0] = null;
                    callbackArr2[1] = null;
                }
            }

            @Override // com.onesignal.OneSignal.OSSetLanguageCompletionHandler
            public void onFailure(OneSignal.OSLanguageError oSLanguageError) {
                try {
                    if (this.callbackArr[1] != null) {
                        String message = oSLanguageError.getMessage();
                        if (message == null) {
                            message = "Failed to set language.";
                        }
                        this.callbackArr[1].invoke(RNUtils.jsonToWritableMap(RNOneSignal.this.jsonFromErrorMessageString(message)));
                        Callback[] callbackArr = this.callbackArr;
                        callbackArr[0] = null;
                        callbackArr[1] = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void disablePush(boolean z) {
        OneSignal.disablePush(z);
    }

    @ReactMethod
    public void unsubscribeWhenNotificationsAreDisabled(boolean z) {
        OneSignal.unsubscribeWhenNotificationsAreDisabled(z);
    }

    @ReactMethod
    public void sendTag(String str, String str2) {
        OneSignal.sendTag(str, str2);
    }

    @ReactMethod
    public void sendTags(ReadableMap readableMap) {
        OneSignal.sendTags(RNUtils.readableMapToJson(readableMap));
    }

    @ReactMethod
    public void deleteTags(ReadableArray readableArray) {
        OneSignal.deleteTags(RNUtils.convertReableArrayIntoStringCollection(readableArray));
    }

    @ReactMethod
    public void getTags(Callback callback) {
        if (this.pendingGetTagsCallback == null) {
            this.pendingGetTagsCallback = callback;
        }
        OneSignal.getTags(new OneSignal.OSGetTagsHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.2
            @Override // com.onesignal.OneSignal.OSGetTagsHandler
            public void tagsAvailable(JSONObject jSONObject) {
                if (RNOneSignal.this.pendingGetTagsCallback != null) {
                    RNOneSignal.this.pendingGetTagsCallback.invoke(RNUtils.jsonToWritableMap(jSONObject));
                }
                RNOneSignal.this.pendingGetTagsCallback = null;
            }
        });
    }

    @ReactMethod
    public void setEmail(String str, String str2, Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.setEmail(str, str2, new OneSignal.EmailUpdateHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.3
            @Override // com.onesignal.OneSignal.EmailUpdateHandler
            public void onSuccess() {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(new Object[0]);
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.EmailUpdateHandler
            public void onFailure(OneSignal.EmailUpdateError emailUpdateError) {
                try {
                    Callback[] callbackArr2 = callbackArr;
                    if (callbackArr2[0] != null) {
                        callbackArr2[0].invoke(RNUtils.jsonToWritableMap(RNOneSignal.this.jsonFromErrorMessageString(emailUpdateError.getMessage())));
                        callbackArr[0] = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void logoutEmail(Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.logoutEmail(new OneSignal.EmailUpdateHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.4
            @Override // com.onesignal.OneSignal.EmailUpdateHandler
            public void onSuccess() {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(new Object[0]);
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.EmailUpdateHandler
            public void onFailure(OneSignal.EmailUpdateError emailUpdateError) {
                try {
                    Callback[] callbackArr2 = callbackArr;
                    if (callbackArr2[0] != null) {
                        callbackArr2[0].invoke(RNUtils.jsonToWritableMap(RNOneSignal.this.jsonFromErrorMessageString(emailUpdateError.getMessage())));
                        callbackArr[0] = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void setSMSNumber(String str, String str2, Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.setSMSNumber(str, str2, new OneSignal.OSSMSUpdateHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.5
            @Override // com.onesignal.OneSignal.OSSMSUpdateHandler
            public void onSuccess(JSONObject jSONObject) {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.OSSMSUpdateHandler
            public void onFailure(OneSignal.OSSMSUpdateError oSSMSUpdateError) {
                try {
                    Callback[] callbackArr2 = callbackArr;
                    if (callbackArr2[0] != null) {
                        callbackArr2[0].invoke(RNUtils.jsonToWritableMap(RNOneSignal.this.jsonFromErrorMessageString(oSSMSUpdateError.getMessage())));
                        callbackArr[0] = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void logoutSMSNumber(Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.logoutSMSNumber(new OneSignal.OSSMSUpdateHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.6
            @Override // com.onesignal.OneSignal.OSSMSUpdateHandler
            public void onSuccess(JSONObject jSONObject) {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.OSSMSUpdateHandler
            public void onFailure(OneSignal.OSSMSUpdateError oSSMSUpdateError) {
                try {
                    Callback[] callbackArr2 = callbackArr;
                    if (callbackArr2[0] != null) {
                        callbackArr2[0].invoke(RNUtils.jsonToWritableMap(RNOneSignal.this.jsonFromErrorMessageString(oSSMSUpdateError.getMessage())));
                        callbackArr[0] = null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void promptForPushNotificationsWithUserResponse(boolean z, Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.promptForPushNotifications(z, new OneSignal.PromptForPushNotificationPermissionResponseHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.7
            @Override // com.onesignal.OneSignal.PromptForPushNotificationPermissionResponseHandler
            public void response(boolean z2) {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(Boolean.valueOf(z2));
                    callbackArr[0] = null;
                }
            }
        });
    }

    @ReactMethod
    public void promptLocation() {
        OneSignal.promptLocation();
    }

    @ReactMethod
    public void setLogLevel(int r1, int r2) {
        OneSignal.setLogLevel(r1, r2);
    }

    @ReactMethod
    public void isLocationShared(Promise promise) {
        promise.resolve(Boolean.valueOf(OneSignal.isLocationShared()));
    }

    @ReactMethod
    public void setLocationShared(Boolean bool) {
        OneSignal.setLocationShared(bool.booleanValue());
    }

    @ReactMethod
    public void postNotification(String str, Callback callback, Callback callback2) {
        final Callback[] callbackArr = {callback, callback2};
        OneSignal.postNotification(str, new OneSignal.PostNotificationResponseHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.8
            @Override // com.onesignal.OneSignal.PostNotificationResponseHandler
            public void onSuccess(JSONObject jSONObject) {
                Log.i("OneSignal", "postNotification Success: " + jSONObject.toString());
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.PostNotificationResponseHandler
            public void onFailure(JSONObject jSONObject) {
                Log.e("OneSignal", "postNotification Failure: " + jSONObject.toString());
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[1] != null) {
                    callbackArr2[1].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[1] = null;
                }
            }
        });
    }

    @ReactMethod
    public void clearOneSignalNotifications() {
        OneSignal.clearOneSignalNotifications();
    }

    @ReactMethod
    public void removeNotification(int r1) {
        OneSignal.removeNotification(r1);
    }

    @ReactMethod
    public void removeGroupedNotifications(String str) {
        OneSignal.removeGroupedNotifications(str);
    }

    @ReactMethod
    public void requiresUserPrivacyConsent(Promise promise) {
        promise.resolve(Boolean.valueOf(OneSignal.requiresUserPrivacyConsent()));
    }

    @ReactMethod
    public void setRequiresUserPrivacyConsent(Boolean bool) {
        OneSignal.setRequiresUserPrivacyConsent(bool.booleanValue());
    }

    @ReactMethod
    public void provideUserConsent(Boolean bool) {
        OneSignal.provideUserConsent(bool.booleanValue());
    }

    @ReactMethod
    public void userProvidedPrivacyConsent(Promise promise) {
        promise.resolve(Boolean.valueOf(OneSignal.userProvidedPrivacyConsent()));
    }

    @ReactMethod
    public void setExternalUserId(final String str, String str2, Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.setExternalUserId(str, str2, new OneSignal.OSExternalUserIdUpdateCompletionHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.9
            @Override // com.onesignal.OneSignal.OSExternalUserIdUpdateCompletionHandler
            public void onSuccess(JSONObject jSONObject) {
                Log.i("OneSignal", "Completed setting external user id: " + str + "with results: " + jSONObject.toString());
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.OSExternalUserIdUpdateCompletionHandler
            public void onFailure(OneSignal.ExternalIdError externalIdError) {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(externalIdError.getMessage());
                    callbackArr[0] = null;
                }
            }
        });
    }

    @ReactMethod
    public void removeExternalUserId(Callback callback) {
        final Callback[] callbackArr = {callback};
        OneSignal.removeExternalUserId(new OneSignal.OSExternalUserIdUpdateCompletionHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.10
            @Override // com.onesignal.OneSignal.OSExternalUserIdUpdateCompletionHandler
            public void onSuccess(JSONObject jSONObject) {
                Log.i("OneSignal", "Completed removing external user id with results: " + jSONObject.toString());
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(RNUtils.jsonToWritableMap(jSONObject));
                    callbackArr[0] = null;
                }
            }

            @Override // com.onesignal.OneSignal.OSExternalUserIdUpdateCompletionHandler
            public void onFailure(OneSignal.ExternalIdError externalIdError) {
                Callback[] callbackArr2 = callbackArr;
                if (callbackArr2[0] != null) {
                    callbackArr2[0].invoke(externalIdError.getMessage());
                    callbackArr[0] = null;
                }
            }
        });
    }

    @ReactMethod
    public void setNotificationOpenedHandler() {
        OneSignal.setNotificationOpenedHandler(this);
    }

    @Override // com.onesignal.OneSignal.OSNotificationOpenedHandler
    public void notificationOpened(OSNotificationOpenedResult oSNotificationOpenedResult) {
        sendEvent("OneSignal-remoteNotificationOpened", RNUtils.jsonToWritableMap(oSNotificationOpenedResult.toJSONObject()));
    }

    @ReactMethod
    public void setNotificationWillShowInForegroundHandler() {
        OneSignal.setNotificationWillShowInForegroundHandler(new OneSignal.OSNotificationWillShowInForegroundHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.11
            @Override // com.onesignal.OneSignal.OSNotificationWillShowInForegroundHandler
            public void notificationWillShowInForeground(OSNotificationReceivedEvent oSNotificationReceivedEvent) {
                OSNotification notification = oSNotificationReceivedEvent.getNotification();
                RNOneSignal.this.notificationReceivedEventCache.put(notification.getNotificationId(), oSNotificationReceivedEvent);
                RNOneSignal.this.sendEvent("OneSignal-notificationWillShowInForeground", RNUtils.jsonToWritableMap(notification.toJSONObject()));
            }
        });
    }

    @ReactMethod
    public void completeNotificationEvent(String str, boolean z) {
        OSNotificationReceivedEvent oSNotificationReceivedEvent = this.notificationReceivedEventCache.get(str);
        if (oSNotificationReceivedEvent == null) {
            Log.e("OneSignal", "(java): could not find cached notification received event with id " + str);
            return;
        }
        if (z) {
            oSNotificationReceivedEvent.complete(oSNotificationReceivedEvent.getNotification());
        } else {
            oSNotificationReceivedEvent.complete(null);
        }
        this.notificationReceivedEventCache.remove(str);
    }

    @ReactMethod
    public void addTrigger(String str, Object obj) {
        OneSignal.addTrigger(str, obj);
    }

    @ReactMethod
    public void addTriggers(ReadableMap readableMap) {
        OneSignal.addTriggers(readableMap.toHashMap());
    }

    @ReactMethod
    public void removeTriggerForKey(String str) {
        OneSignal.removeTriggerForKey(str);
    }

    @ReactMethod
    public void removeTriggersForKeys(ReadableArray readableArray) {
        OneSignal.removeTriggersForKeys(RNUtils.convertReableArrayIntoStringCollection(readableArray));
    }

    @ReactMethod
    public void getTriggerValueForKey(String str, Promise promise) {
        Object triggerValueForKey = OneSignal.getTriggerValueForKey(str);
        if (triggerValueForKey == null) {
            Log.e("OneSignal", "getTriggerValueForKey: There was no value for the key: " + str);
            promise.reject("No Value", "There was no value for the key: " + str);
            return;
        }
        promise.resolve(triggerValueForKey);
    }

    @ReactMethod
    public void setInAppMessageClickHandler() {
        OneSignal.setInAppMessageClickHandler(new OneSignal.OSInAppMessageClickHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.12
            @Override // com.onesignal.OneSignal.OSInAppMessageClickHandler
            public void inAppMessageClicked(OSInAppMessageAction oSInAppMessageAction) {
                if (!RNOneSignal.this.hasSetInAppClickedHandler) {
                    RNOneSignal.this.inAppMessageActionResult = oSInAppMessageAction;
                } else {
                    RNOneSignal.this.sendEvent("OneSignal-inAppMessageClicked", RNUtils.jsonToWritableMap(oSInAppMessageAction.toJSONObject()));
                }
            }
        });
    }

    @ReactMethod
    public void initInAppMessageClickHandlerParams() {
        this.hasSetInAppClickedHandler = true;
        OSInAppMessageAction oSInAppMessageAction = this.inAppMessageActionResult;
        if (oSInAppMessageAction != null) {
            inAppMessageClicked(oSInAppMessageAction);
            this.inAppMessageActionResult = null;
        }
    }

    @Override // com.onesignal.OneSignal.OSInAppMessageClickHandler
    public void inAppMessageClicked(OSInAppMessageAction oSInAppMessageAction) {
        if (!this.hasSetInAppClickedHandler) {
            this.inAppMessageActionResult = oSInAppMessageAction;
        } else {
            sendEvent("OneSignal-inAppMessageClicked", RNUtils.jsonToWritableMap(oSInAppMessageAction.toJSONObject()));
        }
    }

    @ReactMethod
    public void setInAppMessageLifecycleHandler() {
        OneSignal.setInAppMessageLifecycleHandler(new OSInAppMessageLifecycleHandler() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.13
            @Override // com.onesignal.OSInAppMessageLifecycleHandler
            public void onWillDisplayInAppMessage(OSInAppMessage oSInAppMessage) {
                RNOneSignal.this.sendEvent("OneSignal-inAppMessageWillDisplay", RNUtils.jsonToWritableMap(oSInAppMessage.toJSONObject()));
            }

            @Override // com.onesignal.OSInAppMessageLifecycleHandler
            public void onDidDisplayInAppMessage(OSInAppMessage oSInAppMessage) {
                RNOneSignal.this.sendEvent("OneSignal-inAppMessageDidDisplay", RNUtils.jsonToWritableMap(oSInAppMessage.toJSONObject()));
            }

            @Override // com.onesignal.OSInAppMessageLifecycleHandler
            public void onWillDismissInAppMessage(OSInAppMessage oSInAppMessage) {
                RNOneSignal.this.sendEvent("OneSignal-inAppMessageWillDismiss", RNUtils.jsonToWritableMap(oSInAppMessage.toJSONObject()));
            }

            @Override // com.onesignal.OSInAppMessageLifecycleHandler
            public void onDidDismissInAppMessage(OSInAppMessage oSInAppMessage) {
                RNOneSignal.this.sendEvent("OneSignal-inAppMessageDidDismiss", RNUtils.jsonToWritableMap(oSInAppMessage.toJSONObject()));
            }
        });
    }

    @ReactMethod
    public void pauseInAppMessages(Boolean bool) {
        OneSignal.pauseInAppMessages(bool.booleanValue());
    }

    @ReactMethod
    public void sendOutcome(final String str, final Callback callback) {
        OneSignal.sendOutcome(str, new OneSignal.OutcomeCallback() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.14
            @Override // com.onesignal.OneSignal.OutcomeCallback
            public void onSuccess(OSOutcomeEvent oSOutcomeEvent) {
                if (oSOutcomeEvent != null) {
                    try {
                        callback.invoke(RNUtils.jsonToWritableMap(oSOutcomeEvent.toJSONObject()));
                        return;
                    } catch (JSONException e) {
                        Log.e("OneSignal", "sendOutcome with name: " + str + ", failed with message: " + e.getMessage());
                        return;
                    }
                }
                Log.e("OneSignal", "sendOutcome OSOutcomeEvent is null");
            }
        });
    }

    @ReactMethod
    public void sendUniqueOutcome(final String str, final Callback callback) {
        OneSignal.sendUniqueOutcome(str, new OneSignal.OutcomeCallback() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.15
            @Override // com.onesignal.OneSignal.OutcomeCallback
            public void onSuccess(OSOutcomeEvent oSOutcomeEvent) {
                if (oSOutcomeEvent != null) {
                    try {
                        callback.invoke(RNUtils.jsonToWritableMap(oSOutcomeEvent.toJSONObject()));
                        return;
                    } catch (JSONException e) {
                        Log.e("OneSignal", "sendUniqueOutcome with name: " + str + ", failed with message: " + e.getMessage());
                        return;
                    }
                }
                Log.e("OneSignal", "sendUniqueOutcome OSOutcomeEvent is null");
            }
        });
    }

    @ReactMethod
    public void sendOutcomeWithValue(final String str, final float f, final Callback callback) {
        OneSignal.sendOutcomeWithValue(str, f, new OneSignal.OutcomeCallback() { // from class: com.geektime.rnonesignalandroid.RNOneSignal.16
            @Override // com.onesignal.OneSignal.OutcomeCallback
            public void onSuccess(OSOutcomeEvent oSOutcomeEvent) {
                if (oSOutcomeEvent != null) {
                    try {
                        callback.invoke(RNUtils.jsonToWritableMap(oSOutcomeEvent.toJSONObject()));
                        return;
                    } catch (JSONException e) {
                        Log.e("OneSignal", "sendOutcomeWithValue with name: " + str + " and value: " + f + ", failed with message: " + e.getMessage());
                        return;
                    }
                }
                Log.e("OneSignal", "sendOutcomeWithValue OSOutcomeEvent is null");
            }
        });
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        removeHandlers();
        removeObservers();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        initOneSignal();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        removeHandlers();
        removeObservers();
    }
}
