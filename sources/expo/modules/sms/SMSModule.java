package expo.modules.sms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import androidx.core.p005os.EnvironmentCompat;
import androidx.webkit.internal.AssetHelper;
import com.facebook.react.bridge.BaseJavaModule;
import com.onesignal.OneSignalDbContract;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.UIManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.fileupload.FileUploadBase;

/* compiled from: SMSModule.kt */
@Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J,\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0018\u0010\u000f\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0007J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\tH\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J@\u0010\u001c\u001a\u00020\u00142\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0018\u0010 \u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u00102\u0006\u0010\u0015\u001a\u00020\u000bH\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m183d2 = {"Lexpo/modules/sms/SMSModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "smsPackage", "", "(Landroid/content/Context;Ljava/lang/String;)V", "mModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "mPendingPromise", "Lexpo/modules/core/Promise;", "mSMSComposerOpened", "", "getAttachment", FileUploadBase.ATTACHMENT, "", "key", "getName", "isAvailableAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "onCreate", "moduleRegistry", "onDestroy", "onHostDestroy", "onHostPause", "onHostResume", "sendSMSAsync", "addresses", "Ljava/util/ArrayList;", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "options", "", "expo-sms_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SMSModule extends ExportedModule implements LifecycleEventListener {
    private ModuleRegistry mModuleRegistry;
    private Promise mPendingPromise;
    private boolean mSMSComposerOpened;
    private final String smsPackage;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoSMS";
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SMSModule(Context context, String str) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.smsPackage = str;
    }

    public /* synthetic */ SMSModule(Context context, String str, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? null : str);
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.mModuleRegistry = moduleRegistry;
        if (moduleRegistry == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry = null;
        }
        UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager == null) {
            return;
        }
        uIManager.registerLifecycleEventListener(this);
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onDestroy() {
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        if (moduleRegistry == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry = null;
        }
        UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager == null) {
            return;
        }
        uIManager.unregisterLifecycleEventListener(this);
    }

    private final String getAttachment(Map<String, String> map, String str) {
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    @ExpoMethod
    public final void sendSMSAsync(ArrayList<String> addresses, String message, Map<String, ? extends Object> map, Promise promise) {
        Intent intent;
        Intrinsics.checkNotNullParameter(addresses, "addresses");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (this.mPendingPromise != null) {
            promise.reject("E_SMS_SENDING_IN_PROGRESS", "Different SMS sending in progress. Await the old request and then try again.");
            return;
        }
        ModuleRegistry moduleRegistry = null;
        Object obj = map == null ? null : map.get("attachments");
        List list = obj instanceof List ? (List) obj : null;
        if (list != null && (list.isEmpty() ^ true)) {
            intent = new Intent("android.intent.action.SEND");
            intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
            intent.putExtra("address", CollectionsKt.joinToString$default(addresses, ";", null, null, 0, null, null, 62, null));
            Object obj2 = list.get(0);
            Map<String, String> map2 = obj2 instanceof Map ? (Map) obj2 : null;
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(getAttachment(map2, "uri")));
            intent.setType(getAttachment(map2, "mimeType"));
            intent.addFlags(1);
        } else {
            intent = new Intent("android.intent.action.SENDTO");
            String joinToString$default = CollectionsKt.joinToString$default(addresses, ";", null, null, 0, null, null, 62, null);
            intent.setData(Uri.parse("smsto:" + joinToString$default));
        }
        String str = this.smsPackage;
        if (str == null) {
            str = Telephony.Sms.getDefaultSmsPackage(getContext());
        }
        if (str != null) {
            intent.setPackage(str);
            intent.putExtra("exit_on_sent", true);
            intent.putExtra("compose_mode", true);
            intent.putExtra("sms_body", message);
            this.mPendingPromise = promise;
            ModuleRegistry moduleRegistry2 = this.mModuleRegistry;
            if (moduleRegistry2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            } else {
                moduleRegistry = moduleRegistry2;
            }
            ((ActivityProvider) moduleRegistry.getModule(ActivityProvider.class)).getCurrentActivity().startActivity(intent);
            this.mSMSComposerOpened = true;
            return;
        }
        promise.reject("E_SMS_NO_SMS_APP", "No messaging application available");
    }

    @ExpoMethod
    public final void isAvailableAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.resolve(Boolean.valueOf(getContext().getPackageManager().hasSystemFeature("android.hardware.telephony")));
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        Promise promise = this.mPendingPromise;
        if (this.mSMSComposerOpened && promise != null) {
            Bundle bundle = new Bundle();
            bundle.putString("result", EnvironmentCompat.MEDIA_UNKNOWN);
            promise.resolve(bundle);
            this.mPendingPromise = null;
        }
        this.mSMSComposerOpened = false;
    }
}
