package com.swmansion.gesturehandler.core;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;

/* compiled from: ViewConfigurationHelper.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005H&¨\u0006\r"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "", "getChildInDrawingOrderAtIndex", "Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "index", "", "getPointerEventsConfigForView", "Lcom/swmansion/gesturehandler/core/PointerEventsConfig;", "view", "isViewClippingChildren", "", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes3.dex */
public interface ViewConfigurationHelper {
    View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int r2);

    PointerEventsConfig getPointerEventsConfigForView(View view);

    boolean isViewClippingChildren(ViewGroup viewGroup);
}
