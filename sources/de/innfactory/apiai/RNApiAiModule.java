package de.innfactory.apiai;

import ai.api.AIConfiguration;
import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.RequestExtras;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.android.SessionIdStorage;
import ai.api.model.AIContext;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Entity;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class RNApiAiModule extends ReactContextBaseJavaModule implements AIListener {
    private static final String LANG_CHINESE_CHINA = "LANG_CHINESE_CHINA";
    private static final String LANG_CHINESE_HONGKONG = "LANG_CHINESE_HONGKONG";
    private static final String LANG_CHINESE_TAIWAN = "LANG_CHINESE_TAIWAN";
    private static final String LANG_DUTCH = "LANG_DUTCH";
    private static final String LANG_ENGLISH = "LANG_ENGLISH";
    private static final String LANG_ENGLISH_GB = "LANG_ENGLISH_GB";
    private static final String LANG_ENGLISH_US = "LANG_ENGLISH_US";
    private static final String LANG_FRENCH = "LANG_FRENCH";
    private static final String LANG_GERMAN = "LANG_GERMAN";
    private static final String LANG_ITALIAN = "LANG_ITALIAN";
    private static final String LANG_JAPANESE = "LANG_JAPANESE";
    private static final String LANG_KOREAN = "LANG_KOREAN";
    private static final String LANG_PORTUGUESE = "LANG_PORTUGUESE";
    private static final String LANG_PORTUGUESE_BRAZIL = "LANG_PORTUGUESE_BRAZIL";
    private static final String LANG_RUSSIAN = "LANG_RUSSIAN";
    private static final String LANG_SPANISH = "LANG_SPANISH";
    private static final String LANG_UKRAINIAN = "LANG_UKRAINIAN";
    private static final String TAG = "ApiAi";
    private String accessToken;
    private AIDataService aiDataService;
    private AIService aiService;
    private AIConfiguration config;
    private List<AIContext> contexts;
    private List<Entity> entities;
    private String languageTag;
    private Callback onAudioLevelCallback;
    private Callback onErrorCallback;
    private Callback onListeningCanceledCallback;
    private Callback onListeningFinishedCallback;
    private Callback onListeningStartedCallback;
    private Callback onResultCallback;
    private List<AIContext> permantentContexts;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    public RNApiAiModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.config = new AIConfiguration("", AIConfiguration.SupportedLanguages.DEFAULT, AIConfiguration.RecognitionEngine.System);
    }

    @ReactMethod
    public void setConfiguration(String str, String str2) {
        this.accessToken = str;
        this.languageTag = str2;
        this.config = new ai.api.android.AIConfiguration(str, AIConfiguration.SupportedLanguages.fromLanguageTag(str2), AIConfiguration.RecognitionEngine.System);
    }

    @ReactMethod
    public void getLanguage(Promise promise) {
        promise.resolve(this.languageTag);
    }

    @ReactMethod
    public void setContextsAsJson(String str) {
        this.contexts = (List) new Gson().fromJson(str, new TypeToken<List<AIContext>>() { // from class: de.innfactory.apiai.RNApiAiModule.1
        }.getType());
    }

    @ReactMethod
    public void setPermanentContextsAsJson(String str) {
        this.permantentContexts = (List) new Gson().fromJson(str, new TypeToken<List<AIContext>>() { // from class: de.innfactory.apiai.RNApiAiModule.2
        }.getType());
    }

    @ReactMethod
    public void setEntitiesAsJson(String str) throws AIServiceException {
        this.entities = (List) new Gson().fromJson(str, new TypeToken<List<Entity>>() { // from class: de.innfactory.apiai.RNApiAiModule.3
        }.getType());
    }

    @ReactMethod
    public void startListeningNative(Callback callback, Callback callback2) {
        this.onResultCallback = callback;
        this.onErrorCallback = callback2;
        getCurrentActivity().runOnUiThread(new Runnable() { // from class: de.innfactory.apiai.RNApiAiModule.4
            @Override // java.lang.Runnable
            public void run() {
                RNApiAiModule rNApiAiModule = RNApiAiModule.this;
                rNApiAiModule.aiService = AIService.getService(rNApiAiModule.getReactApplicationContext(), RNApiAiModule.this.config);
                RNApiAiModule.this.aiService.setListener(RNApiAiModule.this);
                if (RNApiAiModule.this.contexts == null && RNApiAiModule.this.permantentContexts == null && RNApiAiModule.this.entities == null) {
                    RNApiAiModule.this.aiService.startListening();
                    return;
                }
                RNApiAiModule rNApiAiModule2 = RNApiAiModule.this;
                RNApiAiModule.this.aiService.startListening(new RequestExtras(rNApiAiModule2.mergeContexts(rNApiAiModule2.contexts, RNApiAiModule.this.permantentContexts), RNApiAiModule.this.entities));
                RNApiAiModule.this.contexts = null;
                RNApiAiModule.this.entities = null;
            }
        });
    }

    @ReactMethod
    public void stopListening() {
        getCurrentActivity().runOnUiThread(new Runnable() { // from class: de.innfactory.apiai.RNApiAiModule.5
            @Override // java.lang.Runnable
            public void run() {
                if (RNApiAiModule.this.aiService != null) {
                    RNApiAiModule.this.aiService.stopListening();
                }
            }
        });
    }

    @ReactMethod
    public void cancel() {
        getCurrentActivity().runOnUiThread(new Runnable() { // from class: de.innfactory.apiai.RNApiAiModule.6
            @Override // java.lang.Runnable
            public void run() {
                if (RNApiAiModule.this.aiService != null) {
                    RNApiAiModule.this.aiService.cancel();
                }
            }
        });
    }

    @Override // ai.api.AIListener
    public void onResult(AIResponse aIResponse) {
        if (this.onResultCallback != null) {
            try {
                this.onResultCallback.invoke(new Gson().toJson(aIResponse));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override // ai.api.AIListener
    public void onError(AIError aIError) {
        if (this.onErrorCallback != null) {
            try {
                this.onErrorCallback.invoke(new Gson().toJson(aIError));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @ReactMethod
    public void onListeningStarted(Callback callback) {
        this.onListeningStartedCallback = callback;
    }

    @Override // ai.api.AIListener
    public void onListeningStarted() {
        Callback callback = this.onListeningStartedCallback;
        if (callback != null) {
            try {
                callback.invoke(new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @ReactMethod
    public void onListeningCanceled(Callback callback) {
        this.onListeningCanceledCallback = callback;
    }

    @Override // ai.api.AIListener
    public void onListeningCanceled() {
        Callback callback = this.onListeningCanceledCallback;
        if (callback != null) {
            try {
                callback.invoke(new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @ReactMethod
    public void onListeningFinished(Callback callback) {
        this.onListeningFinishedCallback = callback;
    }

    @Override // ai.api.AIListener
    public void onListeningFinished() {
        Callback callback = this.onListeningFinishedCallback;
        if (callback != null) {
            try {
                callback.invoke(new Object[0]);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @ReactMethod
    public void onAudioLevel(Callback callback) {
        this.onAudioLevelCallback = callback;
    }

    @Override // ai.api.AIListener
    public void onAudioLevel(float f) {
        Callback callback = this.onAudioLevelCallback;
        if (callback != null) {
            try {
                callback.invoke(Float.valueOf(f));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [de.innfactory.apiai.RNApiAiModule$7] */
    @ReactMethod
    public void requestQueryNative(String str, Callback callback, Callback callback2) {
        this.onResultCallback = callback;
        this.onErrorCallback = callback2;
        if (this.aiDataService == null) {
            this.aiDataService = new AIDataService(getReactApplicationContext(), this.config);
        }
        final AIRequest aIRequest = new AIRequest();
        aIRequest.setQuery(str);
        new AsyncTask<AIRequest, Void, AIResponse>() { // from class: de.innfactory.apiai.RNApiAiModule.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public AIResponse doInBackground(AIRequest... aIRequestArr) {
                AIRequest aIRequest2 = aIRequestArr[0];
                try {
                    if (RNApiAiModule.this.contexts == null && RNApiAiModule.this.permantentContexts == null && RNApiAiModule.this.entities == null) {
                        return RNApiAiModule.this.aiDataService.request(aIRequest);
                    }
                    RNApiAiModule rNApiAiModule = RNApiAiModule.this;
                    AIResponse request = RNApiAiModule.this.aiDataService.request(aIRequest, new RequestExtras(rNApiAiModule.mergeContexts(rNApiAiModule.contexts, RNApiAiModule.this.permantentContexts), RNApiAiModule.this.entities));
                    RNApiAiModule.this.contexts = null;
                    RNApiAiModule.this.entities = null;
                    return request;
                } catch (AIServiceException e) {
                    try {
                        RNApiAiModule.this.onErrorCallback.invoke(new Gson().toJson(e));
                    } catch (Exception unused) {
                        Log.e(RNApiAiModule.TAG, e.getMessage(), e);
                    }
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(AIResponse aIResponse) {
                if (aIResponse != null) {
                    RNApiAiModule.this.onResult(aIResponse);
                }
            }
        }.execute(aIRequest);
    }

    @ReactMethod
    public void getAccessToken(Promise promise) {
        promise.resolve(this.accessToken);
    }

    @ReactMethod
    public void getSessionId(Promise promise) {
        promise.resolve(SessionIdStorage.getSessionId(getReactApplicationContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<AIContext> mergeContexts(List<AIContext> list, List<AIContext> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        list.addAll(list2);
        return list;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put(LANG_CHINESE_CHINA, "zh-CN");
        hashMap.put(LANG_CHINESE_HONGKONG, "zh-HK");
        hashMap.put(LANG_CHINESE_TAIWAN, "zh-TW");
        hashMap.put(LANG_DUTCH, "nl");
        hashMap.put(LANG_ENGLISH, "en");
        hashMap.put(LANG_ENGLISH_GB, "en-GB");
        hashMap.put(LANG_ENGLISH_US, "en-US");
        hashMap.put(LANG_FRENCH, "fr");
        hashMap.put(LANG_GERMAN, "de");
        hashMap.put(LANG_ITALIAN, "it");
        hashMap.put(LANG_JAPANESE, "ja");
        hashMap.put(LANG_KOREAN, "ko");
        hashMap.put(LANG_PORTUGUESE, "pt");
        hashMap.put(LANG_PORTUGUESE_BRAZIL, "pt-BR");
        hashMap.put(LANG_RUSSIAN, "ru");
        hashMap.put(LANG_SPANISH, "es");
        hashMap.put(LANG_UKRAINIAN, "uk");
        return hashMap;
    }
}
