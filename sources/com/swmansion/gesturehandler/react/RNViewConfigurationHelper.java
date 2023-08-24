package com.swmansion.gesturehandler.react;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.gesturehandler.core.PointerEventsConfig;
import com.swmansion.gesturehandler.core.ViewConfigurationHelper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNViewConfigurationHelper.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, m183d2 = {"Lcom/swmansion/gesturehandler/react/RNViewConfigurationHelper;", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "()V", "getChildInDrawingOrderAtIndex", "Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "index", "", "getPointerEventsConfigForView", "Lcom/swmansion/gesturehandler/core/PointerEventsConfig;", "view", "isViewClippingChildren", "", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes3.dex */
public final class RNViewConfigurationHelper implements ViewConfigurationHelper {

    /* compiled from: RNViewConfigurationHelper.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[PointerEvents.values().length];
            r0[PointerEvents.BOX_ONLY.ordinal()] = 1;
            r0[PointerEvents.BOX_NONE.ordinal()] = 2;
            r0[PointerEvents.NONE.ordinal()] = 3;
            r0[PointerEvents.AUTO.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Override // com.swmansion.gesturehandler.core.ViewConfigurationHelper
    public PointerEventsConfig getPointerEventsConfigForView(View view) {
        PointerEvents pointerEvents;
        Intrinsics.checkNotNullParameter(view, "view");
        if (view instanceof ReactPointerEventsView) {
            pointerEvents = ((ReactPointerEventsView) view).getPointerEvents();
            Intrinsics.checkNotNullExpressionValue(pointerEvents, "{\n        (view as React…ew).pointerEvents\n      }");
        } else {
            pointerEvents = PointerEvents.AUTO;
        }
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                return PointerEventsConfig.BOX_NONE;
            }
            if (pointerEvents == PointerEvents.BOX_ONLY) {
                return PointerEventsConfig.NONE;
            }
        }
        int r3 = WhenMappings.$EnumSwitchMapping$0[pointerEvents.ordinal()];
        if (r3 != 1) {
            if (r3 != 2) {
                if (r3 != 3) {
                    if (r3 == 4) {
                        return PointerEventsConfig.AUTO;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                return PointerEventsConfig.NONE;
            }
            return PointerEventsConfig.BOX_NONE;
        }
        return PointerEventsConfig.BOX_ONLY;
    }

    @Override // com.swmansion.gesturehandler.core.ViewConfigurationHelper
    public View getChildInDrawingOrderAtIndex(ViewGroup parent, int r3) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (parent instanceof ReactViewGroup) {
            View childAt = parent.getChildAt(((ReactViewGroup) parent).getZIndexMappedChildIndex(r3));
            Intrinsics.checkNotNullExpressionValue(childAt, "{\n      parent.getChildA…dChildIndex(index))\n    }");
            return childAt;
        }
        View childAt2 = parent.getChildAt(r3);
        Intrinsics.checkNotNullExpressionValue(childAt2, "parent.getChildAt(index)");
        return childAt2;
    }

    @Override // com.swmansion.gesturehandler.core.ViewConfigurationHelper
    public boolean isViewClippingChildren(ViewGroup view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getClipChildren()) {
            return true;
        }
        if (view instanceof ReactViewGroup) {
            return Intrinsics.areEqual("hidden", ((ReactViewGroup) view).getOverflow());
        }
        return false;
    }
}
