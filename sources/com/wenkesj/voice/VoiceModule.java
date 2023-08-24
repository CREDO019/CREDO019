package com.wenkesj.voice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.onesignal.OneSignalDbContract;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class VoiceModule extends ReactContextBaseJavaModule implements RecognitionListener {
    private boolean isRecognizing;
    private String locale;
    final ReactApplicationContext reactContext;
    private SpeechRecognizer speech;

    public static String getErrorText(int r0) {
        switch (r0) {
            case 1:
                return "Network timeout";
            case 2:
                return "Network error";
            case 3:
                return "Audio recording error";
            case 4:
                return "error from server";
            case 5:
                return "Client side error";
            case 6:
                return "No speech input";
            case 7:
                return "No match";
            case 8:
                return "RecognitionService busy";
            case 9:
                return "Insufficient permissions";
            default:
                return "Didn't understand, please try again.";
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RCTVoice";
    }

    @Override // android.speech.RecognitionListener
    public void onEvent(int r1, Bundle bundle) {
    }

    public VoiceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.speech = null;
        this.isRecognizing = false;
        this.locale = null;
        this.reactContext = reactApplicationContext;
    }

    private String getLocale(String str) {
        return (str == null || str.equals("")) ? Locale.getDefault().toString() : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListening(ReadableMap readableMap) {
        SpeechRecognizer speechRecognizer = this.speech;
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
            this.speech = null;
        }
        if (readableMap.hasKey("RECOGNIZER_ENGINE")) {
            String string = readableMap.getString("RECOGNIZER_ENGINE");
            string.hashCode();
            if (string.equals("GOOGLE")) {
                this.speech = SpeechRecognizer.createSpeechRecognizer(this.reactContext, ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"));
            } else {
                this.speech = SpeechRecognizer.createSpeechRecognizer(this.reactContext);
            }
        } else {
            this.speech = SpeechRecognizer.createSpeechRecognizer(this.reactContext);
        }
        this.speech.setRecognitionListener(this);
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            nextKey.hashCode();
            char c = 65535;
            switch (nextKey.hashCode()) {
                case -799376495:
                    if (nextKey.equals("EXTRA_LANGUAGE_MODEL")) {
                        c = 0;
                        break;
                    }
                    break;
                case -530349781:
                    if (nextKey.equals("EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS")) {
                        c = 1;
                        break;
                    }
                    break;
                case -136377678:
                    if (nextKey.equals("EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1189324300:
                    if (nextKey.equals("EXTRA_MAX_RESULTS")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1326635678:
                    if (nextKey.equals("EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2061034665:
                    if (nextKey.equals("EXTRA_PARTIAL_RESULTS")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    String string2 = readableMap.getString(nextKey);
                    string2.hashCode();
                    if (!string2.equals("LANGUAGE_MODEL_FREE_FORM")) {
                        if (string2.equals("LANGUAGE_MODEL_WEB_SEARCH")) {
                            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
                            break;
                        } else {
                            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
                            break;
                        }
                    } else {
                        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
                        break;
                    }
                case 1:
                    intent.putExtra("android.speech.extras.SPEECH_INPUT_MINIMUM_LENGTH_MILLIS", Double.valueOf(readableMap.getDouble(nextKey)).intValue());
                    break;
                case 2:
                    intent.putExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS", Double.valueOf(readableMap.getDouble(nextKey)).intValue());
                    break;
                case 3:
                    intent.putExtra("android.speech.extra.MAX_RESULTS", Double.valueOf(readableMap.getDouble(nextKey)).intValue());
                    break;
                case 4:
                    intent.putExtra("android.speech.extras.SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS", Double.valueOf(readableMap.getDouble(nextKey)).intValue());
                    break;
                case 5:
                    intent.putExtra("android.speech.extra.PARTIAL_RESULTS", readableMap.getBoolean(nextKey));
                    break;
            }
        }
        intent.putExtra("android.speech.extra.LANGUAGE", getLocale(this.locale));
        this.speech.startListening(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSpeechWithPermissions(String str, final ReadableMap readableMap, final Callback callback) {
        this.locale = str;
        new Handler(this.reactContext.getMainLooper()).post(new Runnable() { // from class: com.wenkesj.voice.VoiceModule.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VoiceModule.this.startListening(readableMap);
                    VoiceModule.this.isRecognizing = true;
                    callback.invoke(false);
                } catch (Exception e) {
                    callback.invoke(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void startSpeech(final String str, final ReadableMap readableMap, final Callback callback) {
        if (!isPermissionGranted() && readableMap.getBoolean("REQUEST_PERMISSIONS_AUTO")) {
            String[] strArr = {"android.permission.RECORD_AUDIO"};
            if (getCurrentActivity() != null) {
                ((PermissionAwareActivity) getCurrentActivity()).requestPermissions(strArr, 1, new PermissionListener() { // from class: com.wenkesj.voice.VoiceModule.2
                    @Override // com.facebook.react.modules.core.PermissionListener
                    public boolean onRequestPermissionsResult(int r5, String[] strArr2, int[] r7) {
                        boolean z = true;
                        for (int r1 = 0; r1 < strArr2.length; r1++) {
                            z = z && (r7[r1] == 0);
                        }
                        VoiceModule.this.startSpeechWithPermissions(str, readableMap, callback);
                        return z;
                    }
                });
                return;
            }
            return;
        }
        startSpeechWithPermissions(str, readableMap, callback);
    }

    @ReactMethod
    public void stopSpeech(final Callback callback) {
        new Handler(this.reactContext.getMainLooper()).post(new Runnable() { // from class: com.wenkesj.voice.VoiceModule.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (VoiceModule.this.speech != null) {
                        VoiceModule.this.speech.stopListening();
                    }
                    VoiceModule.this.isRecognizing = false;
                    callback.invoke(false);
                } catch (Exception e) {
                    callback.invoke(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void cancelSpeech(final Callback callback) {
        new Handler(this.reactContext.getMainLooper()).post(new Runnable() { // from class: com.wenkesj.voice.VoiceModule.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (VoiceModule.this.speech != null) {
                        VoiceModule.this.speech.cancel();
                    }
                    VoiceModule.this.isRecognizing = false;
                    callback.invoke(false);
                } catch (Exception e) {
                    callback.invoke(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void destroySpeech(final Callback callback) {
        new Handler(this.reactContext.getMainLooper()).post(new Runnable() { // from class: com.wenkesj.voice.VoiceModule.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (VoiceModule.this.speech != null) {
                        VoiceModule.this.speech.destroy();
                    }
                    VoiceModule.this.speech = null;
                    VoiceModule.this.isRecognizing = false;
                    callback.invoke(false);
                } catch (Exception e) {
                    callback.invoke(e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void isSpeechAvailable(final Callback callback) {
        new Handler(this.reactContext.getMainLooper()).post(new Runnable() { // from class: com.wenkesj.voice.VoiceModule.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    callback.invoke(Boolean.valueOf(SpeechRecognizer.isRecognitionAvailable(this.reactContext)), false);
                } catch (Exception e) {
                    callback.invoke(false, e.getMessage());
                }
            }
        });
    }

    @ReactMethod
    public void getSpeechRecognitionServices(Promise promise) {
        List<ResolveInfo> queryIntentServices = this.reactContext.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 0);
        WritableArray createArray = Arguments.createArray();
        for (ResolveInfo resolveInfo : queryIntentServices) {
            createArray.pushString(resolveInfo.serviceInfo.packageName);
        }
        promise.resolve(createArray);
    }

    private boolean isPermissionGranted() {
        return getReactApplicationContext().checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    @ReactMethod
    public void isRecognizing(Callback callback) {
        callback.invoke(Boolean.valueOf(this.isRecognizing));
    }

    private void sendEvent(String str, @Nullable WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    @Override // android.speech.RecognitionListener
    public void onBeginningOfSpeech() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("error", false);
        sendEvent("onSpeechStart", createMap);
        Log.d("ASR", "onBeginningOfSpeech()");
    }

    @Override // android.speech.RecognitionListener
    public void onBufferReceived(byte[] bArr) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("error", false);
        sendEvent("onSpeechRecognized", createMap);
        Log.d("ASR", "onBufferReceived()");
    }

    @Override // android.speech.RecognitionListener
    public void onEndOfSpeech() {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("error", false);
        sendEvent("onSpeechEnd", createMap);
        Log.d("ASR", "onEndOfSpeech()");
        this.isRecognizing = false;
    }

    @Override // android.speech.RecognitionListener
    public void onError(int r4) {
        String format = String.format("%d/%s", Integer.valueOf(r4), getErrorText(r4));
        WritableMap createMap = Arguments.createMap();
        createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, format);
        createMap.putString("code", String.valueOf(r4));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("error", createMap);
        sendEvent("onSpeechError", createMap2);
        Log.d("ASR", "onError() - " + format);
    }

    @Override // android.speech.RecognitionListener
    public void onPartialResults(Bundle bundle) {
        WritableArray createArray = Arguments.createArray();
        Iterator<String> it = bundle.getStringArrayList("results_recognition").iterator();
        while (it.hasNext()) {
            createArray.pushString(it.next());
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putArray("value", createArray);
        sendEvent("onSpeechPartialResults", createMap);
        Log.d("ASR", "onPartialResults()");
    }

    @Override // android.speech.RecognitionListener
    public void onReadyForSpeech(Bundle bundle) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("error", false);
        sendEvent("onSpeechStart", createMap);
        Log.d("ASR", "onReadyForSpeech()");
    }

    @Override // android.speech.RecognitionListener
    public void onResults(Bundle bundle) {
        WritableArray createArray = Arguments.createArray();
        Iterator<String> it = bundle.getStringArrayList("results_recognition").iterator();
        while (it.hasNext()) {
            createArray.pushString(it.next());
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putArray("value", createArray);
        sendEvent("onSpeechResults", createMap);
        Log.d("ASR", "onResults()");
    }

    @Override // android.speech.RecognitionListener
    public void onRmsChanged(float f) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("value", f);
        sendEvent("onSpeechVolumeChanged", createMap);
    }
}
