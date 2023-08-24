package com.onesignal;

import androidx.browser.trusted.sharing.ShareTarget;
import com.onesignal.OSThrowable;
import java.io.IOException;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OneSignalRestClient {
    private static final String BASE_URL = "https://api.onesignal.com/";
    static final String CACHE_KEY_GET_TAGS = "CACHE_KEY_GET_TAGS";
    static final String CACHE_KEY_REMOTE_PARAMS = "CACHE_KEY_REMOTE_PARAMS";
    private static final int GET_TIMEOUT = 60000;
    private static final String OS_ACCEPT_HEADER = "application/vnd.onesignal.v1+json";
    private static final String OS_API_VERSION = "1";
    private static final int THREAD_ID = 10000;
    private static final int TIMEOUT = 120000;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class ResponseHandler {
        void onFailure(int r1, String str, Throwable th) {
        }

        void onSuccess(String str) {
        }
    }

    private static int getThreadTimeout(int r0) {
        return r0 + 5000;
    }

    OneSignalRestClient() {
    }

    public static void put(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.1
            @Override // java.lang.Runnable
            public void run() {
                OneSignalRestClient.makeRequest(str, "PUT", jSONObject, responseHandler, OneSignalRestClient.TIMEOUT, null);
            }
        }, "OS_REST_ASYNC_PUT").start();
    }

    public static void post(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.2
            @Override // java.lang.Runnable
            public void run() {
                OneSignalRestClient.makeRequest(str, ShareTarget.METHOD_POST, jSONObject, responseHandler, OneSignalRestClient.TIMEOUT, null);
            }
        }, "OS_REST_ASYNC_POST").start();
    }

    public static void get(final String str, final ResponseHandler responseHandler, final String str2) {
        new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.3
            @Override // java.lang.Runnable
            public void run() {
                OneSignalRestClient.makeRequest(str, null, null, responseHandler, OneSignalRestClient.GET_TIMEOUT, str2);
            }
        }, "OS_REST_ASYNC_GET").start();
    }

    public static void getSync(String str, ResponseHandler responseHandler, String str2) {
        makeRequest(str, null, null, responseHandler, GET_TIMEOUT, str2);
    }

    public static void putSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "PUT", jSONObject, responseHandler, TIMEOUT, null);
    }

    public static void postSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, ShareTarget.METHOD_POST, jSONObject, responseHandler, TIMEOUT, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void makeRequest(final String str, final String str2, final JSONObject jSONObject, final ResponseHandler responseHandler, final int r16, final String str3) {
        if (OSUtils.isRunningOnMainThread()) {
            throw new OSThrowable.OSMainThreadException("Method: " + str2 + " was called from the Main Thread!");
        } else if (str2 == null || !OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            final Thread[] threadArr = new Thread[1];
            Thread thread = new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.4
                @Override // java.lang.Runnable
                public void run() {
                    threadArr[0] = OneSignalRestClient.startHTTPConnection(str, str2, jSONObject, responseHandler, r16, str3);
                }
            }, "OS_HTTPConnection");
            thread.start();
            try {
                thread.join(getThreadTimeout(r16));
                if (thread.getState() != Thread.State.TERMINATED) {
                    thread.interrupt();
                }
                if (threadArr[0] != null) {
                    threadArr[0].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02d4, code lost:
        if (r8 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02d7, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Thread startHTTPConnection(java.lang.String r16, java.lang.String r17, org.json.JSONObject r18, com.onesignal.OneSignalRestClient.ResponseHandler r19, int r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 735
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OneSignalRestClient.startHTTPConnection(java.lang.String, java.lang.String, org.json.JSONObject, com.onesignal.OneSignalRestClient$ResponseHandler, int, java.lang.String):java.lang.Thread");
    }

    private static Thread callResponseHandlerOnSuccess(final ResponseHandler responseHandler, final String str) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.5
            @Override // java.lang.Runnable
            public void run() {
                ResponseHandler.this.onSuccess(str);
            }
        }, "OS_REST_SUCCESS_CALLBACK");
        thread.start();
        return thread;
    }

    private static Thread callResponseHandlerOnFailure(final ResponseHandler responseHandler, final int r3, final String str, final Throwable th) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() { // from class: com.onesignal.OneSignalRestClient.6
            @Override // java.lang.Runnable
            public void run() {
                ResponseHandler.this.onFailure(r3, str, th);
            }
        }, "OS_REST_FAILURE_CALLBACK");
        thread.start();
        return thread;
    }

    private static HttpURLConnection newHttpURLConnection(String str) throws IOException {
        return (HttpURLConnection) new URL(BASE_URL + str).openConnection();
    }
}
