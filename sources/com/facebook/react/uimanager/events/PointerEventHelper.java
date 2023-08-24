package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.C1413R;
import com.facebook.react.common.ReactConstants;

/* loaded from: classes.dex */
public class PointerEventHelper {
    public static final String POINTER_CANCEL = "topPointerCancel";
    public static final String POINTER_DOWN = "topPointerDown";
    public static final String POINTER_ENTER = "topPointerEnter";
    public static final String POINTER_LEAVE = "topPointerLeave";
    public static final String POINTER_MOVE = "topPointerMove";
    public static final String POINTER_TYPE_MOUSE = "mouse";
    public static final String POINTER_TYPE_PEN = "pen";
    public static final String POINTER_TYPE_TOUCH = "touch";
    public static final String POINTER_TYPE_UNKNOWN = "";
    public static final String POINTER_UP = "topPointerUp";
    private static final int X_FLAG_SUPPORTS_HOVER = 16777216;

    /* loaded from: classes.dex */
    public enum EVENT {
        CANCEL,
        CANCEL_CAPTURE,
        DOWN,
        DOWN_CAPTURE,
        ENTER,
        ENTER_CAPTURE,
        LEAVE,
        LEAVE_CAPTURE,
        MOVE,
        MOVE_CAPTURE,
        UP,
        UP_CAPTURE
    }

    public static String getW3CPointerType(int r1) {
        return r1 != 1 ? r1 != 2 ? r1 != 3 ? "" : POINTER_TYPE_MOUSE : POINTER_TYPE_PEN : POINTER_TYPE_TOUCH;
    }

    /* renamed from: com.facebook.react.uimanager.events.PointerEventHelper$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C16941 {

        /* renamed from: $SwitchMap$com$facebook$react$uimanager$events$PointerEventHelper$EVENT */
        static final /* synthetic */ int[] f171x82302621;

        static {
            int[] r0 = new int[EVENT.values().length];
            f171x82302621 = r0;
            try {
                r0[EVENT.LEAVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f171x82302621[EVENT.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f171x82302621[EVENT.MOVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f171x82302621[EVENT.ENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f171x82302621[EVENT.CANCEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f171x82302621[EVENT.UP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f171x82302621[EVENT.DOWN_CAPTURE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f171x82302621[EVENT.UP_CAPTURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f171x82302621[EVENT.CANCEL_CAPTURE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f171x82302621[EVENT.ENTER_CAPTURE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f171x82302621[EVENT.LEAVE_CAPTURE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f171x82302621[EVENT.MOVE_CAPTURE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public static String getDispatchableEventName(EVENT event) {
        switch (C16941.f171x82302621[event.ordinal()]) {
            case 1:
                return POINTER_LEAVE;
            case 2:
                return POINTER_DOWN;
            case 3:
                return POINTER_MOVE;
            case 4:
                return POINTER_ENTER;
            case 5:
                return POINTER_CANCEL;
            case 6:
                return POINTER_UP;
            default:
                FLog.m1328e(ReactConstants.TAG, "No dispatchable event name for type: " + event);
                return null;
        }
    }

    public static boolean isListening(View view, EVENT event) {
        if (view == null) {
            return false;
        }
        Object obj = null;
        switch (C16941.f171x82302621[event.ordinal()]) {
            case 1:
                obj = view.getTag(C1413R.C1417id.pointer_leave);
                break;
            case 2:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            case 3:
                obj = view.getTag(C1413R.C1417id.pointer_move);
                break;
            case 4:
                obj = view.getTag(C1413R.C1417id.pointer_enter);
                break;
            case 10:
                obj = view.getTag(C1413R.C1417id.pointer_enter_capture);
                break;
            case 11:
                obj = view.getTag(C1413R.C1417id.pointer_leave_capture);
                break;
            case 12:
                obj = view.getTag(C1413R.C1417id.pointer_move_capture);
                break;
        }
        if (obj != null && (obj instanceof Boolean)) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static int getEventCategory(String str) {
        if (str == null) {
            return 2;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1786514288:
                if (str.equals(POINTER_ENTER)) {
                    c = 0;
                    break;
                }
                break;
            case -1780335505:
                if (str.equals(POINTER_LEAVE)) {
                    c = 1;
                    break;
                }
                break;
            case -1304584214:
                if (str.equals(POINTER_DOWN)) {
                    c = 2;
                    break;
                }
                break;
            case -1304316135:
                if (str.equals(POINTER_MOVE)) {
                    c = 3;
                    break;
                }
                break;
            case -1065042973:
                if (str.equals(POINTER_UP)) {
                    c = 4;
                    break;
                }
                break;
            case 383186882:
                if (str.equals(POINTER_CANCEL)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 3:
                return 4;
            case 2:
            case 4:
            case 5:
                return 3;
            default:
                return 2;
        }
    }

    public static boolean supportsHover(MotionEvent motionEvent) {
        if ((motionEvent.getFlags() & 16777216) != 0) {
            return true;
        }
        String w3CPointerType = getW3CPointerType(motionEvent.getToolType(motionEvent.getActionIndex()));
        if (w3CPointerType.equals(POINTER_TYPE_MOUSE) || w3CPointerType.equals(POINTER_TYPE_PEN)) {
            return true;
        }
        w3CPointerType.equals(POINTER_TYPE_TOUCH);
        return false;
    }
}
