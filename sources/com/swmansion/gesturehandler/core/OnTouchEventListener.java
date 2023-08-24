package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: OnTouchEventListener.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ5\u0010\n\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u00020\u0003\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u0004H&¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/OnTouchEventListener;", "", "onHandlerUpdate", "", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "handler", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Landroid/view/MotionEvent;)V", "onStateChange", "newState", "", "oldState", "(Lcom/swmansion/gesturehandler/core/GestureHandler;II)V", "onTouchEvent", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)V", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes3.dex */
public interface OnTouchEventListener {
    <T extends GestureHandler<T>> void onHandlerUpdate(T t, MotionEvent motionEvent);

    <T extends GestureHandler<T>> void onStateChange(T t, int r2, int r3);

    <T extends GestureHandler<T>> void onTouchEvent(T t);
}
