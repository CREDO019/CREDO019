package expo.modules.kotlin.activityaware;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* compiled from: AppCompatActivityAware.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/kotlin/activityaware/AppCompatActivityAware;", "", "addOnActivityAvailableListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lexpo/modules/kotlin/activityaware/OnActivityAvailableListener;", "removeOnActivityAvailableListener", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface AppCompatActivityAware {
    void addOnActivityAvailableListener(OnActivityAvailableListener onActivityAvailableListener);

    void removeOnActivityAvailableListener(OnActivityAvailableListener onActivityAvailableListener);
}
