package com.onesignal;

import android.content.Context;
import com.google.android.gms.iid.InstanceID;
import com.onesignal.OneSignal;
import com.onesignal.PushRegistrator;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class PushRegistratorAbstractGoogle implements PushRegistrator {
    private static int REGISTRATION_RETRY_BACKOFF_MS = 10000;
    private static int REGISTRATION_RETRY_COUNT = 5;
    private boolean firedCallback;
    private Thread registerThread;
    private PushRegistrator.RegisteredHandler registeredHandler;

    abstract String getProviderName();

    abstract String getToken(String str) throws Throwable;

    @Override // com.onesignal.PushRegistrator
    public void registerForPush(Context context, String str, PushRegistrator.RegisteredHandler registeredHandler) {
        this.registeredHandler = registeredHandler;
        if (isValidProjectNumber(str, registeredHandler)) {
            internalRegisterForPush(str);
        }
    }

    private void internalRegisterForPush(String str) {
        try {
            if (OSUtils.isGMSInstalledAndEnabled()) {
                registerInBackground(str);
            } else {
                GooglePlayServicesUpgradePrompt.showUpdateGPSDialog();
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
                this.registeredHandler.complete(null, -7);
            }
        } catch (Throwable th) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.Log(log_level, "Could not register with " + getProviderName() + " due to an issue with your AndroidManifest.xml or with 'Google Play services'.", th);
            this.registeredHandler.complete(null, -8);
        }
    }

    private synchronized void registerInBackground(final String str) {
        Thread thread = this.registerThread;
        if (thread == null || !thread.isAlive()) {
            Thread thread2 = new Thread(new Runnable() { // from class: com.onesignal.PushRegistratorAbstractGoogle.1
                @Override // java.lang.Runnable
                public void run() {
                    int r0 = 0;
                    while (r0 < PushRegistratorAbstractGoogle.REGISTRATION_RETRY_COUNT && !PushRegistratorAbstractGoogle.this.attemptRegistration(str, r0)) {
                        r0++;
                        OSUtils.sleep(PushRegistratorAbstractGoogle.REGISTRATION_RETRY_BACKOFF_MS * r0);
                    }
                }
            });
            this.registerThread = thread2;
            thread2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean attemptRegistration(String str, int r10) {
        try {
            String token = getToken(str);
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
            OneSignal.Log(log_level, "Device registered, push token = " + token);
            this.registeredHandler.complete(token, 1);
            return true;
        } catch (IOException e) {
            int pushStatusFromThrowable = pushStatusFromThrowable(e);
            String rootCauseMessage = OSUtils.getRootCauseMessage(e);
            if (InstanceID.ERROR_SERVICE_NOT_AVAILABLE.equals(rootCauseMessage) || "AUTHENTICATION_FAILED".equals(rootCauseMessage)) {
                Exception exc = new Exception(e);
                if (r10 >= REGISTRATION_RETRY_COUNT - 1) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level2, "Retry count of " + REGISTRATION_RETRY_COUNT + " exceed! Could not get a " + getProviderName() + " Token.", exc);
                } else {
                    OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.INFO;
                    OneSignal.Log(log_level3, "'Google Play services' returned " + rootCauseMessage + " error. Current retry count: " + r10, exc);
                    if (r10 == 2) {
                        this.registeredHandler.complete(null, pushStatusFromThrowable);
                        this.firedCallback = true;
                        return true;
                    }
                }
                return false;
            }
            Exception exc2 = new Exception(e);
            OneSignal.LOG_LEVEL log_level4 = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.Log(log_level4, "Error Getting " + getProviderName() + " Token", exc2);
            if (!this.firedCallback) {
                this.registeredHandler.complete(null, pushStatusFromThrowable);
            }
            return true;
        } catch (Throwable th) {
            Exception exc3 = new Exception(th);
            int pushStatusFromThrowable2 = pushStatusFromThrowable(th);
            OneSignal.LOG_LEVEL log_level5 = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.Log(log_level5, "Unknown error getting " + getProviderName() + " Token", exc3);
            this.registeredHandler.complete(null, pushStatusFromThrowable2);
            return true;
        }
    }

    private static int pushStatusFromThrowable(Throwable th) {
        String rootCauseMessage = OSUtils.getRootCauseMessage(th);
        if (th instanceof IOException) {
            if (InstanceID.ERROR_SERVICE_NOT_AVAILABLE.equals(rootCauseMessage)) {
                return -9;
            }
            return "AUTHENTICATION_FAILED".equals(rootCauseMessage) ? -29 : -11;
        }
        return -12;
    }

    private boolean isValidProjectNumber(String str, PushRegistrator.RegisteredHandler registeredHandler) {
        boolean z;
        try {
            Float.parseFloat(str);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            return true;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Missing Google Project number!\nPlease enter a Google Project number / Sender ID on under App Settings > Android > Configuration on the OneSignal dashboard.");
        registeredHandler.complete(null, -6);
        return false;
    }
}
