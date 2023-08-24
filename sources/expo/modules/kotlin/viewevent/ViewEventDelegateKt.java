package expo.modules.kotlin.viewevent;

import android.view.View;
import androidx.core.app.NotificationCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;

/* compiled from: ViewEventDelegate.kt */
@Metadata(m184d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aQ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u000321\b\n\u0010\u0004\u001a+\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\n\u0012\u0004\u0012\u0002H\u0002\u0018\u0001`\nH\u0086\bø\u0001\u0000\u001an\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\u0001*\u00020\u00032I\b\u0002\u0010\u0004\u001aC\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0018\u0001`\nH\u0007¢\u0006\u0002\b\u000e*F\u0010\u000f\u001a\u0004\b\u0000\u0010\u0002\"\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00052\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, m183d2 = {"EventDispatcher", "Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", "T", "Landroid/view/View;", "coalescingKey", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_EVENT, "", "Lexpo/modules/kotlin/viewevent/CoalescingKey;", "", "", "", "MapEventDispatcher", "CoalescingKey", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ViewEventDelegateKt {
    public static /* synthetic */ ViewEventDelegate EventDispatcher$default(View view, Function1 function1, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            function1 = null;
        }
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.reifiedOperationMarker(6, "T");
        return new ViewEventDelegate(null, view, function1);
    }

    public static final /* synthetic */ <T> ViewEventDelegate<T> EventDispatcher(View view, Function1<? super T, Short> function1) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.reifiedOperationMarker(6, "T");
        return new ViewEventDelegate<>(null, view, function1);
    }

    public static /* synthetic */ ViewEventDelegate MapEventDispatcher$default(View view, Function1 function1, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            function1 = null;
        }
        return MapEventDispatcher(view, function1);
    }

    public static final ViewEventDelegate<Map<String, Object>> MapEventDispatcher(View view, Function1<? super Map<String, ? extends Object>, Short> function1) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return new ViewEventDelegate<>(Reflection.typeOf(Map.class, KTypeProjection.Companion.invariant(Reflection.typeOf(String.class)), KTypeProjection.Companion.invariant(Reflection.typeOf(Object.class))), view, function1);
    }
}
