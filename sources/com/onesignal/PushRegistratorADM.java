package com.onesignal;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import com.onesignal.OneSignal;
import com.onesignal.PushRegistrator;

/* loaded from: classes3.dex */
public class PushRegistratorADM implements PushRegistrator {
    private static boolean callbackSuccessful = false;
    private static PushRegistrator.RegisteredHandler registeredCallback;

    @Override // com.onesignal.PushRegistrator
    public void registerForPush(final Context context, String str, final PushRegistrator.RegisteredHandler registeredHandler) {
        registeredCallback = registeredHandler;
        new Thread(new Runnable() { // from class: com.onesignal.PushRegistratorADM.1
            @Override // java.lang.Runnable
            public void run() {
                ADM adm = new ADM(context);
                String registrationId = adm.getRegistrationId();
                if (registrationId == null) {
                    adm.startRegister();
                } else {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                    OneSignal.Log(log_level, "ADM Already registered with ID:" + registrationId);
                    registeredHandler.complete(registrationId, 1);
                }
                try {
                    Thread.sleep(30000L);
                } catch (InterruptedException unused) {
                }
                if (PushRegistratorADM.callbackSuccessful) {
                    return;
                }
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "com.onesignal.ADMMessageHandler timed out, please check that your have the receiver, service, and your package name matches(NOTE: Case Sensitive) per the OneSignal instructions.");
                PushRegistratorADM.fireCallback(null);
            }
        }).start();
    }

    public static void fireCallback(String str) {
        PushRegistrator.RegisteredHandler registeredHandler = registeredCallback;
        if (registeredHandler == null) {
            return;
        }
        callbackSuccessful = true;
        registeredHandler.complete(str, 1);
    }
}
