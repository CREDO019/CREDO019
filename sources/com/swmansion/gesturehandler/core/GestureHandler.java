package com.swmansion.gesturehandler.core;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.swmansion.gesturehandler.core.GestureHandler;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: GestureHandler.kt */
@Metadata(m184d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 »\u0001*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u0006º\u0001»\u0001¼\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\\\u001a\u00020]J\u0010\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020\u001dH\u0016J\u0010\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020`H\u0002J\u0010\u0010b\u001a\u00020]2\u0006\u0010c\u001a\u00020NH\u0002J\u0010\u0010d\u001a\u00020]2\u0006\u0010c\u001a\u00020NH\u0002J*\u0010e\u001a\u00028\u00002\u0017\u0010f\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020]0g¢\u0006\u0002\bhH\u0084\bø\u0001\u0000¢\u0006\u0002\u0010iJ\u0006\u0010j\u001a\u00020]J\u0006\u0010k\u001a\u00020]J\b\u0010l\u001a\u00020]H\u0002J\b\u0010m\u001a\u0004\u0018\u00010\u000eJ\b\u0010n\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010o\u001a\n q*\u0004\u0018\u00010p0p2\u0006\u0010c\u001a\u00020NH\u0002J\u0010\u0010r\u001a\u00020]2\u0006\u0010a\u001a\u00020`H\u0016J\u0018\u0010s\u001a\u00020]2\u0006\u0010t\u001a\u00020\u00052\u0006\u0010u\u001a\u00020\u0005H\u0016J\u0010\u0010v\u001a\u00020]2\u0006\u0010a\u001a\u00020`H\u0002J\b\u0010w\u001a\u00020]H\u0016J\u0010\u0010x\u001a\u00020]2\u0006\u0010a\u001a\u00020`H\u0002J\u0010\u0010y\u001a\u00020]2\u0006\u0010a\u001a\u00020`H\u0002J\u0006\u0010z\u001a\u00020]J\b\u0010{\u001a\u00020]H\u0002J\u0006\u0010|\u001a\u00020]J\b\u0010}\u001a\u00020\u0005H\u0002J\u0016\u0010~\u001a\u0004\u0018\u00010\u007f2\n\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u0001H\u0002J\u0019\u0010\u0082\u0001\u001a\u00020]2\u0007\u0010\u0083\u0001\u001a\u00020`2\u0007\u0010\u0084\u0001\u001a\u00020`J\u0014\u0010\u0085\u0001\u001a\u00020\u001d2\u000b\u0010\u0086\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000J\"\u0010$\u001a\u00020\u001d2\b\u0010T\u001a\u0004\u0018\u00010S2\u0007\u0010\u0087\u0001\u001a\u00020%2\u0007\u0010\u0088\u0001\u001a\u00020%J\u0011\u0010\u0089\u0001\u001a\u00020]2\u0006\u0010t\u001a\u00020\u0005H\u0002J\u0011\u0010\u008a\u0001\u001a\u00020\u001d2\u0006\u0010a\u001a\u00020`H\u0002J\t\u0010\u008b\u0001\u001a\u00020]H\u0014J\u001a\u0010\u008c\u0001\u001a\u00020]2\u0006\u0010a\u001a\u00020`2\u0007\u0010\u0084\u0001\u001a\u00020`H\u0014J\t\u0010\u008d\u0001\u001a\u00020]H\u0014J\t\u0010\u008e\u0001\u001a\u00020]H\u0014J\u001a\u0010\u008f\u0001\u001a\u00020]2\u0006\u0010t\u001a\u00020\u00052\u0007\u0010\u0090\u0001\u001a\u00020\u0005H\u0014J\u001b\u0010\u0091\u0001\u001a\u00020]2\b\u0010T\u001a\u0004\u0018\u00010S2\b\u0010=\u001a\u0004\u0018\u00010>J\u0007\u0010\u0092\u0001\u001a\u00020]J\t\u0010\u0093\u0001\u001a\u00020]H\u0016J\t\u0010\u0094\u0001\u001a\u00020]H\u0016J\u000f\u0010\u0095\u0001\u001a\u00028\u0000H\u0004¢\u0006\u0003\u0010\u0096\u0001J\u0016\u0010\u0097\u0001\u001a\u00028\u00002\u0007\u0010\u0098\u0001\u001a\u00020\u001d¢\u0006\u0003\u0010\u0099\u0001J\u0016\u0010\u009a\u0001\u001a\u00028\u00002\u0007\u0010\u009b\u0001\u001a\u00020%¢\u0006\u0003\u0010\u009c\u0001JC\u0010\u009a\u0001\u001a\u00028\u00002\u0007\u0010\u009d\u0001\u001a\u00020%2\u0007\u0010\u009e\u0001\u001a\u00020%2\u0007\u0010\u009f\u0001\u001a\u00020%2\u0007\u0010 \u0001\u001a\u00020%2\u0007\u0010¡\u0001\u001a\u00020%2\u0007\u0010¢\u0001\u001a\u00020%¢\u0006\u0003\u0010£\u0001J\u0018\u0010¤\u0001\u001a\u00028\u00002\t\u0010¥\u0001\u001a\u0004\u0018\u00010\u001b¢\u0006\u0003\u0010¦\u0001J\u0015\u0010§\u0001\u001a\u00028\u00002\u0006\u00105\u001a\u00020\u001d¢\u0006\u0003\u0010\u0099\u0001J\u0016\u0010¨\u0001\u001a\u0006\u0012\u0002\b\u00030\u00002\t\u0010©\u0001\u001a\u0004\u0018\u00010<J\u0015\u0010ª\u0001\u001a\u00028\u00002\u0006\u0010?\u001a\u00020\u001d¢\u0006\u0003\u0010\u0099\u0001J\u0016\u0010«\u0001\u001a\u00020\u001d2\u000b\u0010¬\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0016\u0010\u00ad\u0001\u001a\u00020\u001d2\u000b\u0010¬\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0016\u0010®\u0001\u001a\u00020\u001d2\u000b\u0010¬\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0016J\u0014\u0010¯\u0001\u001a\u00020\u001d2\u000b\u0010¬\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0010\u0010°\u0001\u001a\u00020]2\u0007\u0010±\u0001\u001a\u00020\u0005J\u0010\u0010²\u0001\u001a\u00020]2\u0007\u0010±\u0001\u001a\u00020\u0005J\n\u0010³\u0001\u001a\u00030´\u0001H\u0016J\u0014\u0010µ\u0001\u001a\u00030¶\u00012\b\u0010·\u0001\u001a\u00030¶\u0001H\u0004J\u000f\u0010¸\u0001\u001a\u00020]2\u0006\u0010a\u001a\u00020`J\u0007\u0010¹\u0001\u001a\u00020\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001e\u0010#\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u001e\u0010$\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u001e\u0010&\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u000e\u0010+\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010-\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b.\u0010(R\u0011\u0010/\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b0\u0010(R\u0011\u00101\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b2\u0010(R\u0011\u00103\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b4\u0010(R\u000e\u00105\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00106\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001e\"\u0004\b8\u0010 R\u001e\u00109\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0007R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001e\"\u0004\bB\u0010 R\u001e\u0010C\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0007R\u001a\u0010E\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0007\"\u0004\bG\u0010\tR\u001e\u0010H\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0007R\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010L\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010N0MX\u0082\u0004¢\u0006\u0004\n\u0002\u0010OR\u001e\u0010P\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0007R\u000e\u0010R\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010T\u001a\u0004\u0018\u00010S2\b\u0010\r\u001a\u0004\u0018\u00010S@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u000e\u0010W\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010X\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bY\u0010(R\u001e\u0010Z\u001a\u00020%2\u0006\u0010\r\u001a\u00020%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b[\u0010(\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006½\u0001"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler;", "ConcreteGestureHandlerT", "", "()V", "actionType", "", "getActionType", "()I", "setActionType", "(I)V", "activationIndex", "getActivationIndex", "setActivationIndex", "<set-?>", "Lcom/facebook/react/bridge/WritableArray;", "allTouchesPayload", "getAllTouchesPayload", "()Lcom/facebook/react/bridge/WritableArray;", "changedTouchesPayload", "getChangedTouchesPayload", "", "eventCoalescingKey", "getEventCoalescingKey", "()S", "hitSlop", "", "interactionController", "Lcom/swmansion/gesturehandler/core/GestureHandlerInteractionController;", "isActive", "", "()Z", "setActive", "(Z)V", "isAwaiting", "setAwaiting", "isEnabled", "isWithinBounds", "", "lastAbsolutePositionX", "getLastAbsolutePositionX", "()F", "lastAbsolutePositionY", "getLastAbsolutePositionY", "lastEventOffsetX", "lastEventOffsetY", "lastPositionInWindowX", "getLastPositionInWindowX", "lastPositionInWindowY", "getLastPositionInWindowY", "lastRelativePositionX", "getLastRelativePositionX", "lastRelativePositionY", "getLastRelativePositionY", "manualActivation", "needsPointerData", "getNeedsPointerData", "setNeedsPointerData", "numberOfPointers", "getNumberOfPointers", "onTouchEventListener", "Lcom/swmansion/gesturehandler/core/OnTouchEventListener;", "orchestrator", "Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "shouldCancelWhenOutside", "shouldResetProgress", "getShouldResetProgress", "setShouldResetProgress", "state", "getState", "tag", "getTag", "setTag", "touchEventType", "getTouchEventType", "trackedPointerIDs", "", "trackedPointers", "", "Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "[Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "trackedPointersCount", "getTrackedPointersCount", "trackedPointersIDsCount", "Landroid/view/View;", "view", "getView", "()Landroid/view/View;", "windowOffset", "x", "getX", "y", "getY", "activate", "", "force", "adaptEvent", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "addChangedPointer", "pointerData", "addPointerToAll", "applySelf", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "begin", "cancel", "cancelPointers", "consumeAllTouchesPayload", "consumeChangedTouchesPayload", "createPointerData", "Lcom/facebook/react/bridge/WritableMap;", "kotlin.jvm.PlatformType", "dispatchHandlerUpdate", "dispatchStateChange", "newState", "prevState", "dispatchTouchDownEvent", "dispatchTouchEvent", "dispatchTouchMoveEvent", "dispatchTouchUpEvent", "end", "extractAllPointersData", "fail", "findNextLocalPointerId", "getWindow", "Landroid/view/Window;", "context", "Landroid/content/Context;", "handle", "transformedEvent", "sourceEvent", "hasCommonPointers", "other", "posX", "posY", "moveToState", "needAdapt", "onCancel", "onHandle", "onPrepare", "onReset", "onStateChange", "previousState", "prepare", "reset", "resetConfig", "resetProgress", "self", "()Lcom/swmansion/gesturehandler/core/GestureHandler;", "setEnabled", "enabled", "(Z)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setHitSlop", ViewProps.PADDING, "(F)Lcom/swmansion/gesturehandler/core/GestureHandler;", "leftPad", "topPad", "rightPad", "bottomPad", "width", "height", "(FFFFFF)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setInteractionController", "controller", "(Lcom/swmansion/gesturehandler/core/GestureHandlerInteractionController;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setManualActivation", "setOnTouchEventListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setShouldCancelWhenOutside", "shouldBeCancelledBy", "handler", "shouldRecognizeSimultaneously", "shouldRequireToWaitForFailure", "shouldWaitForHandlerFailure", "startTrackingPointer", "pointerId", "stopTrackingPointer", "toString", "", "transformPoint", "Landroid/graphics/PointF;", "point", "updatePointerData", "wantEvents", "AdaptEventException", "Companion", "PointerData", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes3.dex */
public class GestureHandler<ConcreteGestureHandlerT extends GestureHandler<ConcreteGestureHandlerT>> {
    public static final int ACTION_TYPE_JS_FUNCTION_NEW_API = 4;
    public static final int ACTION_TYPE_JS_FUNCTION_OLD_API = 3;
    public static final int ACTION_TYPE_NATIVE_ANIMATED_EVENT = 2;
    public static final int ACTION_TYPE_REANIMATED_WORKLET = 1;
    public static final Companion Companion = new Companion(null);
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static final int MAX_POINTERS_COUNT = 12;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static short nextEventCoalescingKey;
    private static MotionEvent.PointerCoords[] pointerCoords;
    private static MotionEvent.PointerProperties[] pointerProps;
    private int actionType;
    private int activationIndex;
    private WritableArray allTouchesPayload;
    private WritableArray changedTouchesPayload;
    private short eventCoalescingKey;
    private float[] hitSlop;
    private GestureHandlerInteractionController interactionController;
    private boolean isActive;
    private boolean isAwaiting;
    private boolean isEnabled;
    private boolean isWithinBounds;
    private float lastAbsolutePositionX;
    private float lastAbsolutePositionY;
    private float lastEventOffsetX;
    private float lastEventOffsetY;
    private boolean manualActivation;
    private boolean needsPointerData;
    private int numberOfPointers;
    private OnTouchEventListener onTouchEventListener;
    private GestureHandlerOrchestrator orchestrator;
    private boolean shouldCancelWhenOutside;
    private boolean shouldResetProgress;
    private int state;
    private int tag;
    private int touchEventType;
    private final int[] trackedPointerIDs = new int[12];
    private final PointerData[] trackedPointers;
    private int trackedPointersCount;
    private int trackedPointersIDsCount;
    private View view;
    private final int[] windowOffset;

    /* renamed from: x */
    private float f1349x;

    /* renamed from: y */
    private float f1350y;

    protected void onCancel() {
    }

    protected void onPrepare() {
    }

    protected void onReset() {
    }

    protected void onStateChange(int r1, int r2) {
    }

    public void resetProgress() {
    }

    public GestureHandler() {
        int[] r2 = new int[2];
        for (int r4 = 0; r4 < 2; r4++) {
            r2[r4] = 0;
        }
        this.windowOffset = r2;
        this.isEnabled = true;
        PointerData[] pointerDataArr = new PointerData[12];
        for (int r3 = 0; r3 < 12; r3++) {
            pointerDataArr[r3] = null;
        }
        this.trackedPointers = pointerDataArr;
    }

    public final int getTag() {
        return this.tag;
    }

    public final void setTag(int r1) {
        this.tag = r1;
    }

    public final View getView() {
        return this.view;
    }

    public final int getState() {
        return this.state;
    }

    public final float getX() {
        return this.f1349x;
    }

    public final float getY() {
        return this.f1350y;
    }

    public final boolean isWithinBounds() {
        return this.isWithinBounds;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final int getActionType() {
        return this.actionType;
    }

    public final void setActionType(int r1) {
        this.actionType = r1;
    }

    public final WritableArray getChangedTouchesPayload() {
        return this.changedTouchesPayload;
    }

    public final WritableArray getAllTouchesPayload() {
        return this.allTouchesPayload;
    }

    public final int getTouchEventType() {
        return this.touchEventType;
    }

    public final int getTrackedPointersCount() {
        return this.trackedPointersCount;
    }

    public final boolean getNeedsPointerData() {
        return this.needsPointerData;
    }

    public final void setNeedsPointerData(boolean z) {
        this.needsPointerData = z;
    }

    public final short getEventCoalescingKey() {
        return this.eventCoalescingKey;
    }

    public final float getLastAbsolutePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastAbsolutePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final int getNumberOfPointers() {
        return this.numberOfPointers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ConcreteGestureHandlerT self() {
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.swmansion.gesturehandler.core.GestureHandler, java.lang.Object, ConcreteGestureHandlerT extends com.swmansion.gesturehandler.core.GestureHandler<ConcreteGestureHandlerT>] */
    protected final ConcreteGestureHandlerT applySelf(Function1<? super ConcreteGestureHandlerT, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        GestureHandler self = self();
        block.invoke(self);
        return self;
    }

    public final int getActivationIndex() {
        return this.activationIndex;
    }

    public final void setActivationIndex(int r1) {
        this.activationIndex = r1;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public final boolean isAwaiting() {
        return this.isAwaiting;
    }

    public final void setAwaiting(boolean z) {
        this.isAwaiting = z;
    }

    public final boolean getShouldResetProgress() {
        return this.shouldResetProgress;
    }

    public final void setShouldResetProgress(boolean z) {
        this.shouldResetProgress = z;
    }

    public void dispatchStateChange(int r3, int r4) {
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(self(), r3, r4);
        }
    }

    public void dispatchHandlerUpdate(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onHandlerUpdate(self(), event);
        }
    }

    public void dispatchTouchEvent() {
        OnTouchEventListener onTouchEventListener;
        if (this.changedTouchesPayload == null || (onTouchEventListener = this.onTouchEventListener) == null) {
            return;
        }
        onTouchEventListener.onTouchEvent(self());
    }

    public void resetConfig() {
        this.needsPointerData = false;
        this.manualActivation = false;
        this.shouldCancelWhenOutside = false;
        this.isEnabled = true;
        this.hitSlop = null;
    }

    public final boolean hasCommonPointers(GestureHandler<?> other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int length = this.trackedPointerIDs.length;
        for (int r2 = 0; r2 < length; r2++) {
            if (this.trackedPointerIDs[r2] != -1 && other.trackedPointerIDs[r2] != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setEnabled$lambda-3$lambda-2  reason: not valid java name */
    public static final void m1531setEnabled$lambda3$lambda2(GestureHandler this_applySelf) {
        Intrinsics.checkNotNullParameter(this_applySelf, "$this_applySelf");
        this_applySelf.cancel();
    }

    public final ConcreteGestureHandlerT setHitSlop(float f) {
        return setHitSlop(f, f, f, f, Float.NaN, Float.NaN);
    }

    public final void prepare(View view, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (!(this.view == null && this.orchestrator == null)) {
            throw new IllegalStateException("Already prepared or hasn't been reset".toString());
        }
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.state = 0;
        this.view = view;
        this.orchestrator = gestureHandlerOrchestrator;
        Window window = getWindow(view != null ? view.getContext() : null);
        View decorView = window != null ? window.getDecorView() : null;
        if (decorView != null) {
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            this.windowOffset[0] = rect.left;
            this.windowOffset[1] = rect.top;
        } else {
            int[] r5 = this.windowOffset;
            r5[0] = 0;
            r5[1] = 0;
        }
        onPrepare();
    }

    private final Window getWindow(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return ((Activity) context).getWindow();
        }
        if (context instanceof ContextWrapper) {
            return getWindow(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private final int findNextLocalPointerId() {
        int[] r3;
        int r1 = 0;
        while (r1 < this.trackedPointersIDsCount) {
            int r2 = 0;
            while (true) {
                r3 = this.trackedPointerIDs;
                if (r2 >= r3.length || r3[r2] == r1) {
                    break;
                }
                r2++;
            }
            if (r2 == r3.length) {
                return r1;
            }
            r1++;
        }
        return r1;
    }

    public final void startTrackingPointer(int r4) {
        int[] r0 = this.trackedPointerIDs;
        if (r0[r4] == -1) {
            r0[r4] = findNextLocalPointerId();
            this.trackedPointersIDsCount++;
        }
    }

    public final void stopTrackingPointer(int r4) {
        int[] r0 = this.trackedPointerIDs;
        if (r0[r4] != -1) {
            r0[r4] = -1;
            this.trackedPointersIDsCount--;
        }
    }

    private final boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.trackedPointersIDsCount) {
            return true;
        }
        int length = this.trackedPointerIDs.length;
        for (int r2 = 0; r2 < length; r2++) {
            int r3 = this.trackedPointerIDs[r2];
            if (r3 != -1 && r3 != r2) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00b6 A[EDGE_INSN: B:84:0x00b6->B:44:0x00b6 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v12, types: [android.view.MotionEvent$PointerProperties[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.view.MotionEvent adaptEvent(android.view.MotionEvent r27) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.adaptEvent(android.view.MotionEvent):android.view.MotionEvent");
    }

    /* compiled from: GestureHandler.kt */
    @Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B%\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$AdaptEventException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "e", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Landroid/view/MotionEvent;Ljava/lang/IllegalArgumentException;)V", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public static final class AdaptEventException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AdaptEventException(GestureHandler<?> handler, MotionEvent event, IllegalArgumentException e) {
            super(StringsKt.trimIndent("\n    handler: " + Reflection.getOrCreateKotlinClass(handler.getClass()).getSimpleName() + "\n    state: " + handler.getState() + "\n    view: " + handler.getView() + "\n    orchestrator: " + ((GestureHandler) handler).orchestrator + "\n    isEnabled: " + handler.isEnabled() + "\n    isActive: " + handler.isActive() + "\n    isAwaiting: " + handler.isAwaiting() + "\n    trackedPointersCount: " + ((GestureHandler) handler).trackedPointersIDsCount + "\n    trackedPointers: " + ArraysKt.joinToString$default(((GestureHandler) handler).trackedPointerIDs, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\n    while handling event: " + event + "\n    "), e);
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(e, "e");
        }
    }

    public final void handle(MotionEvent transformedEvent, MotionEvent sourceEvent) {
        int r0;
        Intrinsics.checkNotNullParameter(transformedEvent, "transformedEvent");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (!this.isEnabled || (r0 = this.state) == 3 || r0 == 1 || r0 == 5 || this.trackedPointersIDsCount < 1) {
            return;
        }
        try {
            MotionEvent[] motionEventArr = {adaptEvent(transformedEvent), adaptEvent(sourceEvent)};
            MotionEvent motionEvent = motionEventArr[0];
            MotionEvent motionEvent2 = motionEventArr[1];
            this.f1349x = motionEvent.getX();
            this.f1350y = motionEvent.getY();
            this.numberOfPointers = motionEvent.getPointerCount();
            boolean isWithinBounds = isWithinBounds(this.view, this.f1349x, this.f1350y);
            this.isWithinBounds = isWithinBounds;
            if (this.shouldCancelWhenOutside && !isWithinBounds) {
                int r8 = this.state;
                if (r8 == 4) {
                    cancel();
                    return;
                } else if (r8 == 2) {
                    fail();
                    return;
                } else {
                    return;
                }
            }
            this.lastAbsolutePositionX = GestureUtils.INSTANCE.getLastPointerX(motionEvent, true);
            this.lastAbsolutePositionY = GestureUtils.INSTANCE.getLastPointerY(motionEvent, true);
            this.lastEventOffsetX = motionEvent.getRawX() - motionEvent.getX();
            this.lastEventOffsetY = motionEvent.getRawY() - motionEvent.getY();
            onHandle(motionEvent, motionEvent2);
            if (!Intrinsics.areEqual(motionEvent, transformedEvent)) {
                motionEvent.recycle();
            }
            if (Intrinsics.areEqual(motionEvent2, sourceEvent)) {
                return;
            }
            motionEvent2.recycle();
        } catch (AdaptEventException unused) {
            fail();
        }
    }

    private final void dispatchTouchDownEvent(MotionEvent motionEvent) {
        this.changedTouchesPayload = null;
        this.touchEventType = 1;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (motionEvent.getX(motionEvent.getActionIndex()) + (motionEvent.getRawX() - motionEvent.getX())) - this.windowOffset[0], (motionEvent.getY(motionEvent.getActionIndex()) + (motionEvent.getRawY() - motionEvent.getY())) - this.windowOffset[1]);
        this.trackedPointersCount++;
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        extractAllPointersData();
        dispatchTouchEvent();
    }

    private final void dispatchTouchUpEvent(MotionEvent motionEvent) {
        extractAllPointersData();
        this.changedTouchesPayload = null;
        this.touchEventType = 3;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (motionEvent.getX(motionEvent.getActionIndex()) + (motionEvent.getRawX() - motionEvent.getX())) - this.windowOffset[0], (motionEvent.getY(motionEvent.getActionIndex()) + (motionEvent.getRawY() - motionEvent.getY())) - this.windowOffset[1]);
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        this.trackedPointers[pointerId] = null;
        this.trackedPointersCount--;
        dispatchTouchEvent();
    }

    private final void dispatchTouchMoveEvent(MotionEvent motionEvent) {
        this.changedTouchesPayload = null;
        this.touchEventType = 2;
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        int pointerCount = motionEvent.getPointerCount();
        int r5 = 0;
        for (int r4 = 0; r4 < pointerCount; r4++) {
            PointerData pointerData = this.trackedPointers[motionEvent.getPointerId(r4)];
            if (pointerData != null) {
                if (pointerData.getX() == motionEvent.getX(r4)) {
                    if (pointerData.getY() == motionEvent.getY(r4)) {
                    }
                }
                pointerData.setX(motionEvent.getX(r4));
                pointerData.setY(motionEvent.getY(r4));
                pointerData.setAbsoluteX((motionEvent.getX(r4) + rawX) - this.windowOffset[0]);
                pointerData.setAbsoluteY((motionEvent.getY(r4) + rawY) - this.windowOffset[1]);
                addChangedPointer(pointerData);
                r5++;
            }
        }
        if (r5 > 0) {
            extractAllPointersData();
            dispatchTouchEvent();
        }
    }

    public final void updatePointerData(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 0 || event.getActionMasked() == 5) {
            dispatchTouchDownEvent(event);
            dispatchTouchMoveEvent(event);
        } else if (event.getActionMasked() == 1 || event.getActionMasked() == 6) {
            dispatchTouchMoveEvent(event);
            dispatchTouchUpEvent(event);
        } else if (event.getActionMasked() == 2) {
            dispatchTouchMoveEvent(event);
        }
    }

    private final void extractAllPointersData() {
        PointerData[] pointerDataArr;
        this.allTouchesPayload = null;
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addPointerToAll(pointerData);
            }
        }
    }

    private final void cancelPointers() {
        PointerData[] pointerDataArr;
        this.touchEventType = 4;
        this.changedTouchesPayload = null;
        extractAllPointersData();
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addChangedPointer(pointerData);
            }
        }
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        dispatchTouchEvent();
    }

    private final void addChangedPointer(PointerData pointerData) {
        if (this.changedTouchesPayload == null) {
            this.changedTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.changedTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final void addPointerToAll(PointerData pointerData) {
        if (this.allTouchesPayload == null) {
            this.allTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.allTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final WritableMap createPointerData(PointerData pointerData) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", pointerData.getPointerId());
        createMap.putDouble("x", PixelUtil.toDIPFromPixel(pointerData.getX()));
        createMap.putDouble("y", PixelUtil.toDIPFromPixel(pointerData.getY()));
        createMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteX()));
        createMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteY()));
        return createMap;
    }

    public final WritableArray consumeChangedTouchesPayload() {
        WritableArray writableArray = this.changedTouchesPayload;
        this.changedTouchesPayload = null;
        return writableArray;
    }

    public final WritableArray consumeAllTouchesPayload() {
        WritableArray writableArray = this.allTouchesPayload;
        this.allTouchesPayload = null;
        return writableArray;
    }

    private final void moveToState(int r4) {
        UiThreadUtil.assertOnUiThread();
        if (this.state == r4) {
            return;
        }
        if (this.trackedPointersCount > 0 && (r4 == 5 || r4 == 3 || r4 == 1)) {
            cancelPointers();
        }
        int r0 = this.state;
        this.state = r4;
        if (r4 == 4) {
            short s = nextEventCoalescingKey;
            nextEventCoalescingKey = (short) (s + 1);
            this.eventCoalescingKey = s;
        }
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        Intrinsics.checkNotNull(gestureHandlerOrchestrator);
        gestureHandlerOrchestrator.onHandlerStateChange(this, r4, r0);
        onStateChange(r4, r0);
    }

    public final boolean wantEvents() {
        int r0;
        return (!this.isEnabled || (r0 = this.state) == 1 || r0 == 3 || r0 == 5 || this.trackedPointersIDsCount <= 0) ? false : true;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, handler);
    }

    public final boolean shouldWaitForHandlerFailure(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, handler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler<?> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.interactionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, handler);
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler<?> handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, handler);
    }

    public final boolean isWithinBounds(View view, float f, float f2) {
        float f3;
        Intrinsics.checkNotNull(view);
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.hitSlop;
        if (fArr != null) {
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[2];
            float f7 = fArr[3];
            Companion companion = Companion;
            float f8 = companion.hitSlopSet(f4) ? 0.0f - f4 : 0.0f;
            r4 = companion.hitSlopSet(f5) ? 0.0f - f5 : 0.0f;
            if (companion.hitSlopSet(f6)) {
                width += f6;
            }
            if (companion.hitSlopSet(f7)) {
                height += f7;
            }
            float f9 = fArr[4];
            float f10 = fArr[5];
            if (companion.hitSlopSet(f9)) {
                if (!companion.hitSlopSet(f4)) {
                    f8 = width - f9;
                } else if (!companion.hitSlopSet(f6)) {
                    width = f9 + f8;
                }
            }
            if (companion.hitSlopSet(f10)) {
                if (!companion.hitSlopSet(f5)) {
                    r4 = height - f10;
                } else if (!companion.hitSlopSet(f7)) {
                    height = f10 + r4;
                }
            }
            f3 = r4;
            r4 = f8;
        } else {
            f3 = 0.0f;
        }
        if (r4 <= f && f <= width) {
            if (f3 <= f2 && f2 <= height) {
                return true;
            }
        }
        return false;
    }

    public final void cancel() {
        int r0 = this.state;
        if (r0 == 4 || r0 == 0 || r0 == 2) {
            onCancel();
            moveToState(3);
        }
    }

    public final void fail() {
        int r0 = this.state;
        if (r0 == 4 || r0 == 0 || r0 == 2) {
            moveToState(1);
        }
    }

    public final void activate() {
        activate(false);
    }

    public void activate(boolean z) {
        if (!this.manualActivation || z) {
            int r2 = this.state;
            if (r2 == 0 || r2 == 2) {
                moveToState(4);
            }
        }
    }

    public final void begin() {
        if (this.state == 0) {
            moveToState(2);
        }
    }

    public final void end() {
        int r0 = this.state;
        if (r0 == 2 || r0 == 4) {
            moveToState(5);
        }
    }

    protected void onHandle(MotionEvent event, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        moveToState(1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PointF transformPoint(PointF point) {
        PointF transformPointToViewCoords;
        Intrinsics.checkNotNullParameter(point, "point");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator == null || (transformPointToViewCoords = gestureHandlerOrchestrator.transformPointToViewCoords(this.view, point)) == null) {
            point.x = Float.NaN;
            point.y = Float.NaN;
            return point;
        }
        return transformPointToViewCoords;
    }

    public final void reset() {
        this.view = null;
        this.orchestrator = null;
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        this.touchEventType = 0;
        onReset();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final GestureHandler<?> setOnTouchEventListener(OnTouchEventListener onTouchEventListener) {
        this.onTouchEventListener = onTouchEventListener;
        return this;
    }

    public String toString() {
        String simpleName;
        View view = this.view;
        if (view == null) {
            simpleName = null;
        } else {
            Intrinsics.checkNotNull(view);
            simpleName = view.getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "@[" + this.tag + "]:" + simpleName;
    }

    public final float getLastRelativePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastRelativePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final float getLastPositionInWindowX() {
        return (this.lastAbsolutePositionX + this.lastEventOffsetX) - this.windowOffset[0];
    }

    public final float getLastPositionInWindowY() {
        return (this.lastAbsolutePositionY + this.lastEventOffsetY) - this.windowOffset[1];
    }

    /* compiled from: GestureHandler.kt */
    @Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0010\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eX\u0082.¢\u0006\u0004\n\u0002\u0010 R\u0018\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001eX\u0082.¢\u0006\u0004\n\u0002\u0010#¨\u0006-"}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$Companion;", "", "()V", "ACTION_TYPE_JS_FUNCTION_NEW_API", "", "ACTION_TYPE_JS_FUNCTION_OLD_API", "ACTION_TYPE_NATIVE_ANIMATED_EVENT", "ACTION_TYPE_REANIMATED_WORKLET", "DIRECTION_DOWN", "DIRECTION_LEFT", "DIRECTION_RIGHT", "DIRECTION_UP", "HIT_SLOP_BOTTOM_IDX", "HIT_SLOP_HEIGHT_IDX", "HIT_SLOP_LEFT_IDX", "HIT_SLOP_NONE", "", "HIT_SLOP_RIGHT_IDX", "HIT_SLOP_TOP_IDX", "HIT_SLOP_WIDTH_IDX", "MAX_POINTERS_COUNT", "STATE_ACTIVE", "STATE_BEGAN", "STATE_CANCELLED", "STATE_END", "STATE_FAILED", "STATE_UNDETERMINED", "nextEventCoalescingKey", "", "pointerCoords", "", "Landroid/view/MotionEvent$PointerCoords;", "[Landroid/view/MotionEvent$PointerCoords;", "pointerProps", "Landroid/view/MotionEvent$PointerProperties;", "[Landroid/view/MotionEvent$PointerProperties;", "hitSlopSet", "", "value", "initPointerProps", "", "size", "stateToString", "", "state", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String stateToString(int r2) {
            if (r2 != 0) {
                if (r2 != 1) {
                    if (r2 != 2) {
                        if (r2 != 3) {
                            if (r2 != 4) {
                                if (r2 != 5) {
                                    return null;
                                }
                                return "END";
                            }
                            return "ACTIVE";
                        }
                        return "CANCELLED";
                    }
                    return "BEGIN";
                }
                return "FAILED";
            }
            return "UNDETERMINED";
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initPointerProps(int r5) {
            if (GestureHandler.pointerProps == null) {
                GestureHandler.pointerProps = new MotionEvent.PointerProperties[12];
                GestureHandler.pointerCoords = new MotionEvent.PointerCoords[12];
            }
            while (r5 > 0) {
                MotionEvent.PointerProperties[] pointerPropertiesArr = GestureHandler.pointerProps;
                MotionEvent.PointerCoords[] pointerCoordsArr = null;
                if (pointerPropertiesArr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr = null;
                }
                int r3 = r5 - 1;
                if (pointerPropertiesArr[r3] != null) {
                    return;
                }
                MotionEvent.PointerProperties[] pointerPropertiesArr2 = GestureHandler.pointerProps;
                if (pointerPropertiesArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr2 = null;
                }
                pointerPropertiesArr2[r3] = new MotionEvent.PointerProperties();
                MotionEvent.PointerCoords[] pointerCoordsArr2 = GestureHandler.pointerCoords;
                if (pointerCoordsArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerCoords");
                } else {
                    pointerCoordsArr = pointerCoordsArr2;
                }
                pointerCoordsArr[r3] = new MotionEvent.PointerCoords();
                r5--;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hitSlopSet(float f) {
            return !Float.isNaN(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GestureHandler.kt */
    @Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\""}, m183d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "", "pointerId", "", "x", "", "y", "absoluteX", "absoluteY", "(IFFFF)V", "getAbsoluteX", "()F", "setAbsoluteX", "(F)V", "getAbsoluteY", "setAbsoluteY", "getPointerId", "()I", "getX", "setX", "getY", "setY", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-gesture-handler_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes3.dex */
    public static final class PointerData {
        private float absoluteX;
        private float absoluteY;
        private final int pointerId;

        /* renamed from: x */
        private float f1351x;

        /* renamed from: y */
        private float f1352y;

        public static /* synthetic */ PointerData copy$default(PointerData pointerData, int r4, float f, float f2, float f3, float f4, int r9, Object obj) {
            if ((r9 & 1) != 0) {
                r4 = pointerData.pointerId;
            }
            if ((r9 & 2) != 0) {
                f = pointerData.f1351x;
            }
            float f5 = f;
            if ((r9 & 4) != 0) {
                f2 = pointerData.f1352y;
            }
            float f6 = f2;
            if ((r9 & 8) != 0) {
                f3 = pointerData.absoluteX;
            }
            float f7 = f3;
            if ((r9 & 16) != 0) {
                f4 = pointerData.absoluteY;
            }
            return pointerData.copy(r4, f5, f6, f7, f4);
        }

        public final int component1() {
            return this.pointerId;
        }

        public final float component2() {
            return this.f1351x;
        }

        public final float component3() {
            return this.f1352y;
        }

        public final float component4() {
            return this.absoluteX;
        }

        public final float component5() {
            return this.absoluteY;
        }

        public final PointerData copy(int r8, float f, float f2, float f3, float f4) {
            return new PointerData(r8, f, f2, f3, f4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof PointerData) {
                PointerData pointerData = (PointerData) obj;
                return this.pointerId == pointerData.pointerId && Intrinsics.areEqual((Object) Float.valueOf(this.f1351x), (Object) Float.valueOf(pointerData.f1351x)) && Intrinsics.areEqual((Object) Float.valueOf(this.f1352y), (Object) Float.valueOf(pointerData.f1352y)) && Intrinsics.areEqual((Object) Float.valueOf(this.absoluteX), (Object) Float.valueOf(pointerData.absoluteX)) && Intrinsics.areEqual((Object) Float.valueOf(this.absoluteY), (Object) Float.valueOf(pointerData.absoluteY));
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.pointerId * 31) + Float.floatToIntBits(this.f1351x)) * 31) + Float.floatToIntBits(this.f1352y)) * 31) + Float.floatToIntBits(this.absoluteX)) * 31) + Float.floatToIntBits(this.absoluteY);
        }

        public String toString() {
            return "PointerData(pointerId=" + this.pointerId + ", x=" + this.f1351x + ", y=" + this.f1352y + ", absoluteX=" + this.absoluteX + ", absoluteY=" + this.absoluteY + ')';
        }

        public PointerData(int r1, float f, float f2, float f3, float f4) {
            this.pointerId = r1;
            this.f1351x = f;
            this.f1352y = f2;
            this.absoluteX = f3;
            this.absoluteY = f4;
        }

        public final int getPointerId() {
            return this.pointerId;
        }

        public final float getX() {
            return this.f1351x;
        }

        public final void setX(float f) {
            this.f1351x = f;
        }

        public final float getY() {
            return this.f1352y;
        }

        public final void setY(float f) {
            this.f1352y = f;
        }

        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        public final void setAbsoluteX(float f) {
            this.absoluteX = f;
        }

        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final void setAbsoluteY(float f) {
            this.absoluteY = f;
        }
    }

    public final ConcreteGestureHandlerT setShouldCancelWhenOutside(boolean z) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.shouldCancelWhenOutside = z;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setEnabled(boolean z) {
        final ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        if (concretegesturehandlert.view != null && concretegesturehandlert.isEnabled != z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.core.GestureHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GestureHandler.m1531setEnabled$lambda3$lambda2(GestureHandler.this);
                }
            });
        }
        concretegesturehandlert.isEnabled = z;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setManualActivation(boolean z) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.manualActivation = z;
        return concretegesturehandlert;
    }

    public final ConcreteGestureHandlerT setHitSlop(float f, float f2, float f3, float f4, float f5, float f6) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        if (concretegesturehandlert.hitSlop == null) {
            concretegesturehandlert.hitSlop = new float[6];
        }
        float[] fArr = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr);
        boolean z = false;
        fArr[0] = f;
        float[] fArr2 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr2);
        fArr2[1] = f2;
        float[] fArr3 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr3);
        fArr3[2] = f3;
        float[] fArr4 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr4);
        fArr4[3] = f4;
        float[] fArr5 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr5);
        fArr5[4] = f5;
        float[] fArr6 = concretegesturehandlert.hitSlop;
        Intrinsics.checkNotNull(fArr6);
        fArr6[5] = f6;
        Companion companion = Companion;
        if (!((companion.hitSlopSet(f5) && companion.hitSlopSet(f) && companion.hitSlopSet(f3)) ? false : true)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined".toString());
        }
        if (!(!companion.hitSlopSet(f5) || companion.hitSlopSet(f) || companion.hitSlopSet(f3))) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined".toString());
        }
        if (!((companion.hitSlopSet(f6) && companion.hitSlopSet(f4) && companion.hitSlopSet(f2)) ? false : true)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined".toString());
        }
        if ((!companion.hitSlopSet(f6) || companion.hitSlopSet(f4) || companion.hitSlopSet(f2)) ? true : true) {
            return concretegesturehandlert;
        }
        throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined".toString());
    }

    public final ConcreteGestureHandlerT setInteractionController(GestureHandlerInteractionController gestureHandlerInteractionController) {
        ConcreteGestureHandlerT concretegesturehandlert = (ConcreteGestureHandlerT) self();
        concretegesturehandlert.interactionController = gestureHandlerInteractionController;
        return concretegesturehandlert;
    }
}
