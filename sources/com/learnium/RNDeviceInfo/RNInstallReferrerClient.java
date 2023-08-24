package com.learnium.RNDeviceInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes3.dex */
public class RNInstallReferrerClient {
    private static Class<?> InstallReferrerClientClazz = null;
    private static Class<?> InstallReferrerStateListenerClazz = null;
    private static final int R_RESPONSE_FEATURE_NOT_SUPPORTED = 2;
    private static final int R_RESPONSE_OK = 0;
    private static final int R_RESPONSE_SERVICE_UNAVAILABLE = 1;
    private static Class<?> ReferrerDetailsClazz;
    private Object installReferrerStateListener;
    private Object mReferrerClient;
    private final SharedPreferences sharedPreferences;

    static {
        try {
            InstallReferrerClientClazz = Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            InstallReferrerStateListenerClazz = Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
            ReferrerDetailsClazz = Class.forName("com.android.installreferrer.api.ReferrerDetails");
        } catch (Exception unused) {
            System.err.println("RNInstallReferrerClient exception. 'installreferrer' APIs are unavailable.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNInstallReferrerClient(Context context) {
        this.sharedPreferences = context.getSharedPreferences("react-native-device-info", 0);
        Class<?> cls = InstallReferrerClientClazz;
        if (cls == null || InstallReferrerStateListenerClazz == null || ReferrerDetailsClazz == null) {
            return;
        }
        try {
            Object invoke = cls.getMethod("newBuilder", Context.class).invoke(null, context);
            this.mReferrerClient = invoke.getClass().getMethod("build", new Class[0]).invoke(invoke, new Object[0]);
            this.installReferrerStateListener = Proxy.newProxyInstance(InstallReferrerStateListenerClazz.getClassLoader(), new Class[]{InstallReferrerStateListenerClazz}, new InstallReferrerStateListenerProxy());
            InstallReferrerClientClazz.getMethod("startConnection", InstallReferrerStateListenerClazz).invoke(this.mReferrerClient, this.installReferrerStateListener);
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    /* loaded from: classes3.dex */
    private class InstallReferrerStateListenerProxy implements InvocationHandler {
        private InstallReferrerStateListenerProxy() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            try {
                if (name.equals("onInstallReferrerSetupFinished") && objArr != null && (objArr[0] instanceof Integer)) {
                    onInstallReferrerSetupFinished(((Integer) objArr[0]).intValue());
                    return null;
                } else if (name.equals("onInstallReferrerServiceDisconnected")) {
                    onInstallReferrerServiceDisconnected();
                    return null;
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
            }
        }

        public void onInstallReferrerSetupFinished(int r5) {
            if (r5 != 0) {
                if (r5 == 1) {
                    Log.d("InstallReferrerState", "SERVICE_UNAVAILABLE");
                    return;
                } else if (r5 != 2) {
                    return;
                } else {
                    Log.d("InstallReferrerState", "FEATURE_NOT_SUPPORTED");
                    return;
                }
            }
            try {
                Log.d("InstallReferrerState", "OK");
                Object invoke = RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("getInstallReferrer", new Class[0]).invoke(RNInstallReferrerClient.this.mReferrerClient, new Object[0]);
                SharedPreferences.Editor edit = RNInstallReferrerClient.this.sharedPreferences.edit();
                edit.putString("installReferrer", (String) RNInstallReferrerClient.ReferrerDetailsClazz.getMethod("getInstallReferrer", new Class[0]).invoke(invoke, new Object[0]));
                edit.apply();
                RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("endConnection", new Class[0]).invoke(RNInstallReferrerClient.this.mReferrerClient, new Object[0]);
            } catch (Exception e) {
                PrintStream printStream = System.err;
                printStream.println("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: " + e.getMessage());
                e.printStackTrace(System.err);
            }
        }

        public void onInstallReferrerServiceDisconnected() {
            Log.d("RNInstallReferrerClient", "InstallReferrerService disconnected");
        }
    }
}
