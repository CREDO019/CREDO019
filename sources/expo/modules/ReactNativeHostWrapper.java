package expo.modules;

import android.app.Application;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.google.android.exoplayer2.util.MimeTypes;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ReactNativeHostWrapper.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0014¨\u0006\u0013"}, m183d2 = {"Lexpo/modules/ReactNativeHostWrapper;", "Lexpo/modules/ReactNativeHostWrapperBase;", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "host", "Lcom/facebook/react/ReactNativeHost;", "(Landroid/app/Application;Lcom/facebook/react/ReactNativeHost;)V", "getDevSupportManagerFactory", "Lcom/facebook/react/devsupport/DevSupportManagerFactory;", "getReactPackageTurboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getRedBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getShouldRequireActivity", "", "getSurfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "getUIImplementationProvider", "Lcom/facebook/react/uimanager/UIImplementationProvider;", "expo_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ReactNativeHostWrapper extends ReactNativeHostWrapperBase {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativeHostWrapper(Application application, ReactNativeHost host) {
        super(application, host);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(host, "host");
    }

    @Override // com.facebook.react.ReactNativeHost
    protected DevSupportManagerFactory getDevSupportManagerFactory() {
        DevSupportManagerFactory devSupportManagerFactory = (DevSupportManagerFactory) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(getReactNativeHostHandlers$expo_release()), new Function1<ReactNativeHostHandler, Object>() { // from class: expo.modules.ReactNativeHostWrapper$getDevSupportManagerFactory$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(ReactNativeHostHandler reactNativeHostHandler) {
                return reactNativeHostHandler.getDevSupportManagerFactory();
            }
        }));
        return devSupportManagerFactory == null ? (DevSupportManagerFactory) invokeDelegateMethod$expo_release("getDevSupportManagerFactory") : devSupportManagerFactory;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return (ReactPackageTurboModuleManagerDelegate.Builder) invokeDelegateMethod$expo_release("getReactPackageTurboModuleManagerDelegateBuilder");
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getShouldRequireActivity() {
        return getHost().getShouldRequireActivity();
    }

    @Override // com.facebook.react.ReactNativeHost
    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        SurfaceDelegateFactory surfaceDelegateFactory = getHost().getSurfaceDelegateFactory();
        Intrinsics.checkNotNullExpressionValue(surfaceDelegateFactory, "host.surfaceDelegateFactory");
        return surfaceDelegateFactory;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected RedBoxHandler getRedBoxHandler() {
        return (RedBoxHandler) invokeDelegateMethod$expo_release("getRedBoxHandler");
    }

    @Override // com.facebook.react.ReactNativeHost
    protected UIImplementationProvider getUIImplementationProvider() {
        return (UIImplementationProvider) invokeDelegateMethod$expo_release("getUIImplementationProvider");
    }
}
