package com.swmansion.gesturehandler.core;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.core.app.NotificationCompat;
import com.onesignal.NotificationBundleProcessor;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GestureHandlerOrchestrator.kt */
@Metadata(m184d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 L2\u00020\u0001:\u0001LB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002JS\u0010'\u001a\u00020\n2\u0012\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\f2\u0006\u0010)\u001a\u00020\n2'\u0010*\u001a#\u0012\u0019\u0012\u0017\u0012\u0002\b\u0003\u0018\u00010\r¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110+H\u0082\b¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000201H\u0002J \u00104\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\u0010\u00108\u001a\u00020\u001f2\u0006\u00103\u001a\u000201H\u0002J \u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00032\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\u0014\u0010:\u001a\u00020\u00112\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\u0010\u0010;\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010<\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0010\u0010=\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u0014\u0010>\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\"\u0010?\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nJ\u000e\u0010B\u001a\u00020\u00112\u0006\u00103\u001a\u000201J\u001c\u0010C\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\"\u001a\u00020#H\u0002J \u0010D\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\b\u0010E\u001a\u00020\u001fH\u0002J\u0018\u0010F\u001a\u0002012\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u00103\u001a\u000201J\u0018\u0010G\u001a\u00020H2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010I\u001a\u00020HJ \u0010J\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\nH\u0002J\u0014\u0010K\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\rH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "", "wrapperView", "Landroid/view/ViewGroup;", "handlerRegistry", "Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;", "viewConfigHelper", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "(Landroid/view/ViewGroup;Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;)V", "activationIndex", "", "awaitingHandlers", "", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "[Lcom/swmansion/gesturehandler/core/GestureHandler;", "awaitingHandlersCount", "finishedHandlersCleanupScheduled", "", "gestureHandlers", "gestureHandlersCount", "handlersToCancel", "handlingChangeSemaphore", "isHandlingTouch", "minimumAlphaForTraversal", "", "getMinimumAlphaForTraversal", "()F", "setMinimumAlphaForTraversal", "(F)V", "preparedHandlers", "addAwaitingHandler", "", "handler", "canReceiveEvents", "view", "Landroid/view/View;", "cancelAll", "cleanupAwaitingHandlers", "cleanupFinishedHandlers", "compactHandlersIf", "handlers", NewHtcHomeBadger.COUNT, "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "([Lcom/swmansion/gesturehandler/core/GestureHandler;ILkotlin/jvm/functions/Function1;)I", "deliverEventToGestureHandler", "sourceEvent", "Landroid/view/MotionEvent;", "deliverEventToGestureHandlers", NotificationCompat.CATEGORY_EVENT, "extractAncestorHandlers", "coords", "", "pointerId", "extractGestureHandlers", "viewGroup", "hasOtherHandlerToWaitFor", "isClipping", "isViewAttachedUnderWrapper", "isViewOverflowingParent", "makeActive", "onHandlerStateChange", "newState", "prevState", "onTouchEvent", "recordHandlerIfNotPresent", "recordViewHandlersForPointer", "scheduleFinishedHandlersCleanup", "transformEventToViewCoords", "transformPointToViewCoords", "Landroid/graphics/PointF;", "point", "traverseWithPointerEvents", "tryActivate", "Companion", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes3.dex */
public final class GestureHandlerOrchestrator {
    private static final float DEFAULT_MIN_ALPHA_FOR_TRAVERSAL = 0.0f;
    private static final int SIMULTANEOUS_GESTURE_HANDLER_LIMIT = 20;
    private int activationIndex;
    private final GestureHandler<?>[] awaitingHandlers;
    private int awaitingHandlersCount;
    private boolean finishedHandlersCleanupScheduled;
    private final GestureHandler<?>[] gestureHandlers;
    private int gestureHandlersCount;
    private final GestureHandlerRegistry handlerRegistry;
    private final GestureHandler<?>[] handlersToCancel;
    private int handlingChangeSemaphore;
    private boolean isHandlingTouch;
    private float minimumAlphaForTraversal;
    private final GestureHandler<?>[] preparedHandlers;
    private final ViewConfigurationHelper viewConfigHelper;
    private final ViewGroup wrapperView;
    public static final Companion Companion = new Companion(null);
    private static final PointF tempPoint = new PointF();
    private static final float[] matrixTransformCoords = new float[2];
    private static final Matrix inverseMatrix = new Matrix();
    private static final float[] tempCoords = new float[2];
    private static final Comparator<GestureHandler<?>> handlersComparator = new Comparator() { // from class: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int m1532handlersComparator$lambda12;
            m1532handlersComparator$lambda12 = GestureHandlerOrchestrator.m1532handlersComparator$lambda12((GestureHandler) obj, (GestureHandler) obj2);
            return m1532handlersComparator$lambda12;
        }
    };

    /* compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[PointerEventsConfig.values().length];
            r0[PointerEventsConfig.NONE.ordinal()] = 1;
            r0[PointerEventsConfig.BOX_ONLY.ordinal()] = 2;
            r0[PointerEventsConfig.BOX_NONE.ordinal()] = 3;
            r0[PointerEventsConfig.AUTO.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public GestureHandlerOrchestrator(ViewGroup wrapperView, GestureHandlerRegistry handlerRegistry, ViewConfigurationHelper viewConfigHelper) {
        Intrinsics.checkNotNullParameter(wrapperView, "wrapperView");
        Intrinsics.checkNotNullParameter(handlerRegistry, "handlerRegistry");
        Intrinsics.checkNotNullParameter(viewConfigHelper, "viewConfigHelper");
        this.wrapperView = wrapperView;
        this.handlerRegistry = handlerRegistry;
        this.viewConfigHelper = viewConfigHelper;
        this.gestureHandlers = new GestureHandler[20];
        this.awaitingHandlers = new GestureHandler[20];
        this.preparedHandlers = new GestureHandler[20];
        this.handlersToCancel = new GestureHandler[20];
    }

    public final float getMinimumAlphaForTraversal() {
        return this.minimumAlphaForTraversal;
    }

    public final void setMinimumAlphaForTraversal(float f) {
        this.minimumAlphaForTraversal = f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r1 != 5) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 1
            r3.isHandlingTouch = r0
            int r1 = r4.getActionMasked()
            if (r1 == 0) goto L19
            r2 = 3
            if (r1 == r2) goto L15
            r2 = 5
            if (r1 == r2) goto L19
            goto L1c
        L15:
            r3.cancelAll()
            goto L1c
        L19:
            r3.extractGestureHandlers(r4)
        L1c:
            r3.deliverEventToGestureHandlers(r4)
            r4 = 0
            r3.isHandlingTouch = r4
            boolean r4 = r3.finishedHandlersCleanupScheduled
            if (r4 == 0) goto L2d
            int r4 = r3.handlingChangeSemaphore
            if (r4 != 0) goto L2d
            r3.cleanupFinishedHandlers()
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private final void scheduleFinishedHandlersCleanup() {
        if (this.isHandlingTouch || this.handlingChangeSemaphore != 0) {
            this.finishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private final int compactHandlersIf(GestureHandler<?>[] gestureHandlerArr, int r6, Function1<? super GestureHandler<?>, Boolean> function1) {
        int r1 = 0;
        for (int r0 = 0; r0 < r6; r0++) {
            if (function1.invoke(gestureHandlerArr[r0]).booleanValue()) {
                gestureHandlerArr[r1] = gestureHandlerArr[r0];
                r1++;
            }
        }
        return r1;
    }

    private final void cleanupFinishedHandlers() {
        boolean z = false;
        for (int r0 = this.gestureHandlersCount - 1; -1 < r0; r0--) {
            GestureHandler<?> gestureHandler = this.gestureHandlers[r0];
            Intrinsics.checkNotNull(gestureHandler);
            if (Companion.isFinished(gestureHandler.getState()) && !gestureHandler.isAwaiting()) {
                this.gestureHandlers[r0] = null;
                gestureHandler.reset();
                gestureHandler.setActive(false);
                gestureHandler.setAwaiting(false);
                gestureHandler.setActivationIndex(Integer.MAX_VALUE);
                z = true;
            }
        }
        if (z) {
            GestureHandler<?>[] gestureHandlerArr = this.gestureHandlers;
            int r3 = this.gestureHandlersCount;
            int r5 = 0;
            for (int r4 = 0; r4 < r3; r4++) {
                if (gestureHandlerArr[r4] != null) {
                    gestureHandlerArr[r5] = gestureHandlerArr[r4];
                    r5++;
                }
            }
            this.gestureHandlersCount = r5;
        }
        this.finishedHandlersCleanupScheduled = false;
    }

    private final boolean hasOtherHandlerToWaitFor(GestureHandler<?> gestureHandler) {
        int r0 = this.gestureHandlersCount;
        for (int r2 = 0; r2 < r0; r2++) {
            GestureHandler<?> gestureHandler2 = this.gestureHandlers[r2];
            Intrinsics.checkNotNull(gestureHandler2);
            Companion companion = Companion;
            if (!companion.isFinished(gestureHandler2.getState()) && companion.shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private final void tryActivate(GestureHandler<?> gestureHandler) {
        if (hasOtherHandlerToWaitFor(gestureHandler)) {
            addAwaitingHandler(gestureHandler);
            return;
        }
        makeActive(gestureHandler);
        gestureHandler.setAwaiting(false);
    }

    private final void cleanupAwaitingHandlers() {
        GestureHandler<?>[] gestureHandlerArr = this.awaitingHandlers;
        int r1 = this.awaitingHandlersCount;
        int r3 = 0;
        for (int r2 = 0; r2 < r1; r2++) {
            GestureHandler<?> gestureHandler = gestureHandlerArr[r2];
            Intrinsics.checkNotNull(gestureHandler);
            if (gestureHandler.isAwaiting()) {
                gestureHandlerArr[r3] = gestureHandlerArr[r2];
                r3++;
            }
        }
        this.awaitingHandlersCount = r3;
    }

    public final void onHandlerStateChange(GestureHandler<?> handler, int r11, int r12) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.handlingChangeSemaphore++;
        if (Companion.isFinished(r11)) {
            int r0 = this.awaitingHandlersCount;
            for (int r6 = 0; r6 < r0; r6++) {
                GestureHandler<?> gestureHandler = this.awaitingHandlers[r6];
                Companion companion = Companion;
                Intrinsics.checkNotNull(gestureHandler);
                if (companion.shouldHandlerWaitForOther(gestureHandler, handler)) {
                    if (r11 == 5) {
                        gestureHandler.cancel();
                        if (gestureHandler.getState() == 5) {
                            gestureHandler.dispatchStateChange(3, 2);
                        }
                        gestureHandler.setAwaiting(false);
                    } else {
                        tryActivate(gestureHandler);
                    }
                }
            }
            cleanupAwaitingHandlers();
        }
        if (r11 == 4) {
            tryActivate(handler);
        } else if (r12 == 4 || r12 == 5) {
            if (handler.isActive()) {
                handler.dispatchStateChange(r11, r12);
            } else if (r12 == 4 && (r11 == 3 || r11 == 1)) {
                handler.dispatchStateChange(r11, 2);
            }
        } else if (r12 != 0 || r11 != 3) {
            handler.dispatchStateChange(r11, r12);
        }
        this.handlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    private final void makeActive(GestureHandler<?> gestureHandler) {
        int state = gestureHandler.getState();
        gestureHandler.setAwaiting(false);
        gestureHandler.setActive(true);
        gestureHandler.setShouldResetProgress(true);
        int r3 = this.activationIndex;
        this.activationIndex = r3 + 1;
        gestureHandler.setActivationIndex(r3);
        int r32 = this.gestureHandlersCount;
        int r5 = 0;
        for (int r4 = 0; r4 < r32; r4++) {
            GestureHandler<?> gestureHandler2 = this.gestureHandlers[r4];
            Intrinsics.checkNotNull(gestureHandler2);
            if (Companion.shouldHandlerBeCancelledBy(gestureHandler2, gestureHandler)) {
                this.handlersToCancel[r5] = gestureHandler2;
                r5++;
            }
        }
        for (int r52 = r5 - 1; -1 < r52; r52--) {
            GestureHandler<?> gestureHandler3 = this.handlersToCancel[r52];
            Intrinsics.checkNotNull(gestureHandler3);
            gestureHandler3.cancel();
        }
        for (int r42 = this.awaitingHandlersCount - 1; -1 < r42; r42--) {
            GestureHandler<?> gestureHandler4 = this.awaitingHandlers[r42];
            Intrinsics.checkNotNull(gestureHandler4);
            if (Companion.shouldHandlerBeCancelledBy(gestureHandler4, gestureHandler)) {
                gestureHandler4.cancel();
                gestureHandler4.setAwaiting(false);
            }
        }
        cleanupAwaitingHandlers();
        gestureHandler.dispatchStateChange(4, 2);
        if (state != 4) {
            gestureHandler.dispatchStateChange(5, 4);
            if (state != 5) {
                gestureHandler.dispatchStateChange(0, 5);
            }
        }
    }

    private final void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        int r0 = this.gestureHandlersCount;
        ArraysKt.copyInto(this.gestureHandlers, this.preparedHandlers, 0, 0, r0);
        ArraysKt.sortWith(this.preparedHandlers, handlersComparator, 0, r0);
        for (int r3 = 0; r3 < r0; r3++) {
            GestureHandler<?> gestureHandler = this.preparedHandlers[r3];
            Intrinsics.checkNotNull(gestureHandler);
            deliverEventToGestureHandler(gestureHandler, motionEvent);
        }
    }

    private final void cancelAll() {
        int r0 = this.awaitingHandlersCount;
        while (true) {
            r0--;
            if (-1 >= r0) {
                break;
            }
            GestureHandler<?> gestureHandler = this.awaitingHandlers[r0];
            Intrinsics.checkNotNull(gestureHandler);
            gestureHandler.cancel();
        }
        int r02 = this.gestureHandlersCount;
        for (int r2 = 0; r2 < r02; r2++) {
            this.preparedHandlers[r2] = this.gestureHandlers[r2];
        }
        for (int r03 = r02 - 1; -1 < r03; r03--) {
            GestureHandler<?> gestureHandler2 = this.preparedHandlers[r03];
            Intrinsics.checkNotNull(gestureHandler2);
            gestureHandler2.cancel();
        }
    }

    private final void deliverEventToGestureHandler(GestureHandler<?> gestureHandler, MotionEvent motionEvent) {
        if (!isViewAttachedUnderWrapper(gestureHandler.getView())) {
            gestureHandler.cancel();
        } else if (gestureHandler.wantEvents()) {
            int actionMasked = motionEvent.getActionMasked();
            View view = gestureHandler.getView();
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(sourceEvent)");
            MotionEvent transformEventToViewCoords = transformEventToViewCoords(view, obtain);
            if (gestureHandler.getNeedsPointerData() && gestureHandler.getState() != 0) {
                gestureHandler.updatePointerData(transformEventToViewCoords);
            }
            if (!gestureHandler.isAwaiting() || actionMasked != 2) {
                boolean z = gestureHandler.getState() == 0;
                gestureHandler.handle(transformEventToViewCoords, motionEvent);
                if (gestureHandler.isActive()) {
                    if (gestureHandler.getShouldResetProgress()) {
                        gestureHandler.setShouldResetProgress(false);
                        gestureHandler.resetProgress();
                    }
                    gestureHandler.dispatchHandlerUpdate(transformEventToViewCoords);
                }
                if (gestureHandler.getNeedsPointerData() && z) {
                    gestureHandler.updatePointerData(transformEventToViewCoords);
                }
                if (actionMasked == 1 || actionMasked == 6) {
                    gestureHandler.stopTrackingPointer(transformEventToViewCoords.getPointerId(transformEventToViewCoords.getActionIndex()));
                }
            }
            transformEventToViewCoords.recycle();
        }
    }

    private final boolean isViewAttachedUnderWrapper(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.wrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.wrapperView) {
            parent = parent.getParent();
        }
        return parent == this.wrapperView;
    }

    public final MotionEvent transformEventToViewCoords(View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (view == null) {
            return event;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
            transformEventToViewCoords(viewGroup, event);
        }
        if (viewGroup != null) {
            event.setLocation((event.getX() + viewGroup.getScrollX()) - view.getLeft(), (event.getY() + viewGroup.getScrollY()) - view.getTop());
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            event.transform(matrix2);
        }
        return event;
    }

    public final PointF transformPointToViewCoords(View view, PointF point) {
        Intrinsics.checkNotNullParameter(point, "point");
        if (view == null) {
            return point;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
            transformPointToViewCoords(viewGroup, point);
        }
        if (viewGroup != null) {
            point.x += viewGroup.getScrollX() - view.getLeft();
            point.y += viewGroup.getScrollY() - view.getTop();
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            float[] fArr = tempCoords;
            fArr[0] = point.x;
            fArr[1] = point.y;
            matrix2.mapPoints(fArr);
            point.x = fArr[0];
            point.y = fArr[1];
        }
        return point;
    }

    private final void addAwaitingHandler(GestureHandler<?> gestureHandler) {
        int r0 = this.awaitingHandlersCount;
        for (int r2 = 0; r2 < r0; r2++) {
            if (this.awaitingHandlers[r2] == gestureHandler) {
                return;
            }
        }
        int r02 = this.awaitingHandlersCount;
        GestureHandler<?>[] gestureHandlerArr = this.awaitingHandlers;
        if (!(r02 < gestureHandlerArr.length)) {
            throw new IllegalStateException("Too many recognizers".toString());
        }
        this.awaitingHandlersCount = r02 + 1;
        gestureHandlerArr[r02] = gestureHandler;
        gestureHandler.setAwaiting(true);
        int r03 = this.activationIndex;
        this.activationIndex = r03 + 1;
        gestureHandler.setActivationIndex(r03);
    }

    private final void recordHandlerIfNotPresent(GestureHandler<?> gestureHandler, View view) {
        int r0 = this.gestureHandlersCount;
        for (int r2 = 0; r2 < r0; r2++) {
            if (this.gestureHandlers[r2] == gestureHandler) {
                return;
            }
        }
        int r02 = this.gestureHandlersCount;
        GestureHandler<?>[] gestureHandlerArr = this.gestureHandlers;
        if (!(r02 < gestureHandlerArr.length)) {
            throw new IllegalStateException("Too many recognizers".toString());
        }
        this.gestureHandlersCount = r02 + 1;
        gestureHandlerArr[r02] = gestureHandler;
        gestureHandler.setActive(false);
        gestureHandler.setAwaiting(false);
        gestureHandler.setActivationIndex(Integer.MAX_VALUE);
        gestureHandler.prepare(view, this);
    }

    private final boolean isViewOverflowingParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return false;
        }
        Matrix matrix = view.getMatrix();
        float[] fArr = matrixTransformCoords;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        matrix.mapPoints(fArr);
        float left = fArr[0] + view.getLeft();
        float top = fArr[1] + view.getTop();
        return left < 0.0f || left + ((float) view.getWidth()) > ((float) viewGroup.getWidth()) || top < 0.0f || top + ((float) view.getHeight()) > ((float) viewGroup.getHeight());
    }

    private final boolean extractAncestorHandlers(View view, float[] fArr, int r13) {
        boolean z = false;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                ArrayList<GestureHandler<?>> handlersForView = this.handlerRegistry.getHandlersForView((View) parent);
                if (handlersForView != null) {
                    synchronized (handlersForView) {
                        Iterator<GestureHandler<?>> it = handlersForView.iterator();
                        while (it.hasNext()) {
                            GestureHandler<?> handler = it.next();
                            if (handler.isEnabled() && handler.isWithinBounds(view, fArr[0], fArr[1])) {
                                Intrinsics.checkNotNullExpressionValue(handler, "handler");
                                recordHandlerIfNotPresent(handler, viewGroup);
                                handler.startTrackingPointer(r13);
                                z = true;
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                } else {
                    continue;
                }
            }
        }
        return z;
    }

    private final boolean recordViewHandlersForPointer(View view, float[] fArr, int r11) {
        boolean z;
        ArrayList<GestureHandler<?>> handlersForView = this.handlerRegistry.getHandlersForView(view);
        boolean z2 = false;
        if (handlersForView != null) {
            synchronized (handlersForView) {
                Iterator<GestureHandler<?>> it = handlersForView.iterator();
                z = false;
                while (it.hasNext()) {
                    GestureHandler<?> handler = it.next();
                    if (handler.isEnabled() && handler.isWithinBounds(view, fArr[0], fArr[1])) {
                        Intrinsics.checkNotNullExpressionValue(handler, "handler");
                        recordHandlerIfNotPresent(handler, view);
                        handler.startTrackingPointer(r11);
                        z = true;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        } else {
            z = false;
        }
        float width = view.getWidth();
        float f = fArr[0];
        if (0.0f <= f && f <= width) {
            float height = view.getHeight();
            float f2 = fArr[1];
            if (0.0f <= f2 && f2 <= height) {
                z2 = true;
            }
            if (z2 && isViewOverflowingParent(view) && extractAncestorHandlers(view, fArr, r11)) {
                return true;
            }
        }
        return z;
    }

    private final void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float[] fArr = tempCoords;
        fArr[0] = motionEvent.getX(actionIndex);
        fArr[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.wrapperView, fArr, pointerId);
        extractGestureHandlers(this.wrapperView, fArr, pointerId);
    }

    private final boolean extractGestureHandlers(ViewGroup viewGroup, float[] fArr, int r15) {
        for (int childCount = viewGroup.getChildCount() - 1; -1 < childCount; childCount--) {
            View childInDrawingOrderAtIndex = this.viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = tempPoint;
                Companion companion = Companion;
                companion.transformPointToChildViewCoords(fArr[0], fArr[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                float f = fArr[0];
                float f2 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                boolean traverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || companion.isTransformedTouchPointInView(fArr[0], fArr[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, fArr, r15) : false;
                fArr[0] = f;
                fArr[1] = f2;
                if (traverseWithPointerEvents) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean traverseWithPointerEvents(View view, float[] fArr, int r7) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[this.viewConfigHelper.getPointerEventsConfigForView(view).ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    boolean extractGestureHandlers = view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, fArr, r7) : false;
                    if (recordViewHandlersForPointer(view, fArr, r7) || extractGestureHandlers || Companion.shouldHandlerlessViewBecomeTouchTarget(view, fArr)) {
                        return true;
                    }
                } else if (view instanceof ViewGroup) {
                    boolean extractGestureHandlers2 = extractGestureHandlers((ViewGroup) view, fArr, r7);
                    if (extractGestureHandlers2) {
                        recordViewHandlersForPointer(view, fArr, r7);
                        return extractGestureHandlers2;
                    }
                    return extractGestureHandlers2;
                } else if (view instanceof EditText) {
                    return recordViewHandlersForPointer(view, fArr, r7);
                }
            } else if (recordViewHandlersForPointer(view, fArr, r7) || Companion.shouldHandlerlessViewBecomeTouchTarget(view, fArr)) {
                return true;
            }
        }
        return false;
    }

    private final boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.minimumAlphaForTraversal;
    }

    private final boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.viewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    /* compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(m184d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J \u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J \u0010\u001c\u001a\u00020\u00122\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J \u0010\u001f\u001a\u00020\u00122\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\rH\u0002J0\u0010#\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator$Companion;", "", "()V", "DEFAULT_MIN_ALPHA_FOR_TRAVERSAL", "", "SIMULTANEOUS_GESTURE_HANDLER_LIMIT", "", "handlersComparator", "Ljava/util/Comparator;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "inverseMatrix", "Landroid/graphics/Matrix;", "matrixTransformCoords", "", "tempCoords", "tempPoint", "Landroid/graphics/PointF;", "canRunSimultaneously", "", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "b", "isFinished", "state", "isTransformedTouchPointInView", "x", "y", "child", "Landroid/view/View;", "shouldHandlerBeCancelledBy", "handler", "other", "shouldHandlerWaitForOther", "shouldHandlerlessViewBecomeTouchTarget", "view", "coords", "transformPointToChildViewCoords", "", "parent", "Landroid/view/ViewGroup;", "outLocalPoint", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isFinished(int r3) {
            return r3 == 3 || r3 == 1 || r3 == 5;
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
            return (!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(fArr[0], fArr[1], view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void transformPointToChildViewCoords(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
            float scrollX = (f + viewGroup.getScrollX()) - view.getLeft();
            float scrollY = (f2 + viewGroup.getScrollY()) - view.getTop();
            Matrix matrix = view.getMatrix();
            if (!matrix.isIdentity()) {
                float[] fArr = GestureHandlerOrchestrator.matrixTransformCoords;
                fArr[0] = scrollX;
                fArr[1] = scrollY;
                matrix.invert(GestureHandlerOrchestrator.inverseMatrix);
                GestureHandlerOrchestrator.inverseMatrix.mapPoints(fArr);
                float f3 = fArr[0];
                scrollY = fArr[1];
                scrollX = f3;
            }
            pointF.set(scrollX, scrollY);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTransformedTouchPointInView(float f, float f2, View view) {
            if (0.0f <= f && f <= ((float) view.getWidth())) {
                if (0.0f <= f2 && f2 <= ((float) view.getHeight())) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerWaitForOther(GestureHandler<?> gestureHandler, GestureHandler<?> gestureHandler2) {
            return gestureHandler != gestureHandler2 && (gestureHandler.shouldWaitForHandlerFailure(gestureHandler2) || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler));
        }

        private final boolean canRunSimultaneously(GestureHandler<?> gestureHandler, GestureHandler<?> gestureHandler2) {
            return gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerBeCancelledBy(GestureHandler<?> gestureHandler, GestureHandler<?> gestureHandler2) {
            if (gestureHandler.hasCommonPointers(gestureHandler2) && !canRunSimultaneously(gestureHandler, gestureHandler2)) {
                if (gestureHandler == gestureHandler2 || !(gestureHandler.isAwaiting() || gestureHandler.getState() == 4)) {
                    return true;
                }
                return gestureHandler.shouldBeCancelledBy(gestureHandler2);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handlersComparator$lambda-12  reason: not valid java name */
    public static final int m1532handlersComparator$lambda12(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if ((gestureHandler.isActive() && gestureHandler2.isActive()) || (gestureHandler.isAwaiting() && gestureHandler2.isAwaiting())) {
            return Integer.signum(gestureHandler2.getActivationIndex() - gestureHandler.getActivationIndex());
        }
        if (!gestureHandler.isActive()) {
            if (gestureHandler2.isActive()) {
                return 1;
            }
            if (!gestureHandler.isAwaiting()) {
                return gestureHandler2.isAwaiting() ? 1 : 0;
            }
        }
        return -1;
    }
}
