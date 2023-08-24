package expo.modules.adapters.react.apploader;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HeadlessAppLoaderNotifier.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0006R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/adapters/react/apploader/HeadlessAppLoaderNotifier;", "", "()V", "listeners", "", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/adapters/react/apploader/HeadlessAppLoaderListener;", "getListeners", "()Ljava/util/Set;", "notifyAppDestroyed", "", "appScopeKey", "", "notifyAppLoaded", "registerListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class HeadlessAppLoaderNotifier {
    public static final HeadlessAppLoaderNotifier INSTANCE = new HeadlessAppLoaderNotifier();
    private static final Set<WeakReference<HeadlessAppLoaderListener>> listeners = new LinkedHashSet();

    private HeadlessAppLoaderNotifier() {
    }

    public final Set<WeakReference<HeadlessAppLoaderListener>> getListeners() {
        return listeners;
    }

    public final void registerListener(HeadlessAppLoaderListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listeners.add(new WeakReference<>(listener));
    }

    public final void notifyAppLoaded(String str) {
        if (str != null) {
            Iterator<T> it = listeners.iterator();
            while (it.hasNext()) {
                HeadlessAppLoaderListener headlessAppLoaderListener = (HeadlessAppLoaderListener) ((WeakReference) it.next()).get();
                if (headlessAppLoaderListener != null) {
                    headlessAppLoaderListener.appLoaded(str);
                }
            }
        }
    }

    public final void notifyAppDestroyed(String str) {
        if (str != null) {
            Iterator<T> it = listeners.iterator();
            while (it.hasNext()) {
                HeadlessAppLoaderListener headlessAppLoaderListener = (HeadlessAppLoaderListener) ((WeakReference) it.next()).get();
                if (headlessAppLoaderListener != null) {
                    headlessAppLoaderListener.appDestroyed(str);
                }
            }
        }
    }
}
