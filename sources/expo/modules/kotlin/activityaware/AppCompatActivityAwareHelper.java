package expo.modules.kotlin.activityaware;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppCompatActivityAwareHelper.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0005J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/kotlin/activityaware/AppCompatActivityAwareHelper;", "Lexpo/modules/kotlin/activityaware/AppCompatActivityAware;", "()V", "activityReference", "Ljava/lang/ref/WeakReference;", "Landroidx/appcompat/app/AppCompatActivity;", "getActivityReference", "()Ljava/lang/ref/WeakReference;", "setActivityReference", "(Ljava/lang/ref/WeakReference;)V", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lexpo/modules/kotlin/activityaware/OnActivityAvailableListener;", "getListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "addOnActivityAvailableListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "dispatchOnActivityAvailable", "activity", "removeOnActivityAvailableListener", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AppCompatActivityAwareHelper implements AppCompatActivityAware {
    private final CopyOnWriteArrayList<OnActivityAvailableListener> listeners = new CopyOnWriteArrayList<>();
    private WeakReference<AppCompatActivity> activityReference = new WeakReference<>(null);

    public final CopyOnWriteArrayList<OnActivityAvailableListener> getListeners() {
        return this.listeners;
    }

    public final WeakReference<AppCompatActivity> getActivityReference() {
        return this.activityReference;
    }

    public final void setActivityReference(WeakReference<AppCompatActivity> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        this.activityReference = weakReference;
    }

    public final void dispatchOnActivityAvailable(final AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activityReference = new WeakReference<>(activity);
        activity.runOnUiThread(new Runnable() { // from class: expo.modules.kotlin.activityaware.AppCompatActivityAwareHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppCompatActivityAwareHelper.m1662dispatchOnActivityAvailable$lambda0(AppCompatActivityAwareHelper.this, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dispatchOnActivityAvailable$lambda-0  reason: not valid java name */
    public static final void m1662dispatchOnActivityAvailable$lambda0(AppCompatActivityAwareHelper this$0, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Iterator<OnActivityAvailableListener> it = this$0.listeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityAvailable(activity);
        }
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void addOnActivityAvailableListener(final OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
        final AppCompatActivity appCompatActivity = this.activityReference.get();
        if (appCompatActivity == null) {
            return;
        }
        appCompatActivity.runOnUiThread(new Runnable() { // from class: expo.modules.kotlin.activityaware.AppCompatActivityAwareHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AppCompatActivityAwareHelper.m1661addOnActivityAvailableListener$lambda2$lambda1(OnActivityAvailableListener.this, appCompatActivity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addOnActivityAvailableListener$lambda-2$lambda-1  reason: not valid java name */
    public static final void m1661addOnActivityAvailableListener$lambda2$lambda1(OnActivityAvailableListener listener, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        listener.onActivityAvailable(activity);
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void removeOnActivityAvailableListener(OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }
}
