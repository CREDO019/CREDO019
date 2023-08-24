package expo.modules.core.interfaces.services;

import android.view.View;
import expo.modules.core.interfaces.ActivityEventListener;
import expo.modules.core.interfaces.LifecycleEventListener;

/* loaded from: classes4.dex */
public interface UIManager {

    /* loaded from: classes4.dex */
    public interface GroupUIBlock {
        void execute(ViewHolder viewHolder);
    }

    /* loaded from: classes4.dex */
    public interface UIBlock<T> {
        void reject(Throwable th);

        void resolve(T t);
    }

    /* loaded from: classes4.dex */
    public interface ViewHolder {
        View get(Object obj);
    }

    @Deprecated
    <T> void addUIBlock(int r1, UIBlock<T> uIBlock, Class<T> cls);

    @Deprecated
    void addUIBlock(GroupUIBlock groupUIBlock);

    void registerActivityEventListener(ActivityEventListener activityEventListener);

    void registerLifecycleEventListener(LifecycleEventListener lifecycleEventListener);

    @Deprecated
    View resolveView(int r1);

    void runOnClientCodeQueueThread(Runnable runnable);

    void runOnNativeModulesQueueThread(Runnable runnable);

    void runOnUiQueueThread(Runnable runnable);

    void unregisterActivityEventListener(ActivityEventListener activityEventListener);

    void unregisterLifecycleEventListener(LifecycleEventListener lifecycleEventListener);
}
