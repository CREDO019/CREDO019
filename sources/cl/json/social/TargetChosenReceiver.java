package cl.json.social;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;

/* loaded from: classes.dex */
public class TargetChosenReceiver extends BroadcastReceiver {
    private static final String EXTRA_RECEIVER_TOKEN = "receiver_token";
    private static final Object LOCK = new Object();
    private static Callback failureCallback;
    private static TargetChosenReceiver sLastRegisteredReceiver;
    private static String sTargetChosenReceiveAction;
    private static Callback successCallback;

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public static void registerCallbacks(Callback callback, Callback callback2) {
        successCallback = callback;
        failureCallback = callback2;
    }

    public static IntentSender getSharingSenderIntent(ReactContext reactContext) {
        synchronized (LOCK) {
            if (sTargetChosenReceiveAction == null) {
                sTargetChosenReceiveAction = reactContext.getPackageName() + "/" + TargetChosenReceiver.class.getName() + "_ACTION";
            }
            Context applicationContext = reactContext.getApplicationContext();
            TargetChosenReceiver targetChosenReceiver = sLastRegisteredReceiver;
            if (targetChosenReceiver != null) {
                applicationContext.unregisterReceiver(targetChosenReceiver);
            }
            TargetChosenReceiver targetChosenReceiver2 = new TargetChosenReceiver();
            sLastRegisteredReceiver = targetChosenReceiver2;
            applicationContext.registerReceiver(targetChosenReceiver2, new IntentFilter(sTargetChosenReceiveAction));
        }
        Intent intent = new Intent(sTargetChosenReceiveAction);
        intent.setPackage(reactContext.getPackageName());
        intent.setClass(reactContext.getApplicationContext(), TargetChosenReceiver.class);
        intent.putExtra(EXTRA_RECEIVER_TOKEN, sLastRegisteredReceiver.hashCode());
        return PendingIntent.getBroadcast(reactContext, 0, intent, Build.VERSION.SDK_INT >= 23 ? 1409286144 : 1342177280).getIntentSender();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        synchronized (LOCK) {
            if (sLastRegisteredReceiver != this) {
                return;
            }
            context.getApplicationContext().unregisterReceiver(sLastRegisteredReceiver);
            sLastRegisteredReceiver = null;
            if (intent.hasExtra(EXTRA_RECEIVER_TOKEN) && intent.getIntExtra(EXTRA_RECEIVER_TOKEN, 0) == hashCode()) {
                ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
                if (componentName != null) {
                    sendCallback(true, true, componentName.flattenToString());
                } else {
                    sendCallback(true, true, "OK");
                }
            }
        }
    }

    public static void sendCallback(boolean z, Object... objArr) {
        if (z) {
            Callback callback = successCallback;
            if (callback != null) {
                callback.invoke(objArr);
            }
        } else {
            Callback callback2 = failureCallback;
            if (callback2 != null) {
                callback2.invoke(objArr);
            }
        }
        successCallback = null;
        failureCallback = null;
    }
}
