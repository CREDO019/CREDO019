package com.facebook.react;

import android.view.KeyEvent;
import android.view.View;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import java.util.Map;

/* loaded from: classes.dex */
public class ReactAndroidHWInputDeviceHelper {
    private static final Map<Integer, String> KEY_EVENTS_ACTIONS = MapBuilder.builder().put(23, "select").put(66, "select").put(62, "select").put(85, "playPause").put(89, "rewind").put(90, "fastForward").put(86, "stop").put(87, "next").put(88, "previous").put(19, "up").put(22, "right").put(20, "down").put(21, "left").put(165, "info").put(82, "menu").build();
    private int mLastFocusedViewId = -1;
    private final ReactRootView mReactRootView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactAndroidHWInputDeviceHelper(ReactRootView reactRootView) {
        this.mReactRootView = reactRootView;
    }

    public void handleKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if (action == 1 || action == 0) {
            Map<Integer, String> map = KEY_EVENTS_ACTIONS;
            if (map.containsKey(Integer.valueOf(keyCode))) {
                dispatchEvent(map.get(Integer.valueOf(keyCode)), this.mLastFocusedViewId, action);
            }
        }
    }

    public void onFocusChanged(View view) {
        if (this.mLastFocusedViewId == view.getId()) {
            return;
        }
        int r0 = this.mLastFocusedViewId;
        if (r0 != -1) {
            dispatchEvent("blur", r0);
        }
        this.mLastFocusedViewId = view.getId();
        dispatchEvent("focus", view.getId());
    }

    public void clearFocus() {
        int r0 = this.mLastFocusedViewId;
        if (r0 != -1) {
            dispatchEvent("blur", r0);
        }
        this.mLastFocusedViewId = -1;
    }

    private void dispatchEvent(String str, int r3) {
        dispatchEvent(str, r3, -1);
    }

    private void dispatchEvent(String str, int r4, int r5) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("eventType", str);
        writableNativeMap.putInt("eventKeyAction", r5);
        if (r4 != -1) {
            writableNativeMap.putInt("tag", r4);
        }
        this.mReactRootView.sendEvent("onHWKeyEvent", writableNativeMap);
    }
}
