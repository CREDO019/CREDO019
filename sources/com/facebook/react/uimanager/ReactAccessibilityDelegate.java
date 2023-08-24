package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.C1413R;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ReactAccessibilityDelegate extends ExploreByTouchHelper {
    private static final int SEND_EVENT = 1;
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_DISABLED = "disabled";
    private static final String STATE_SELECTED = "selected";
    private static final String TAG = "ReactAccessibilityDelegate";
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    public static final String TOP_ACCESSIBILITY_ACTION_EVENT = "topAccessibilityAction";
    public static final HashMap<String, Integer> sActionIdMap;
    private static int sCounter = 1056964608;
    private final HashMap<Integer, String> mAccessibilityActionsMap;
    View mAccessibilityLabelledBy;
    private final AccessibilityLinks mAccessibilityLinks;
    private Handler mHandler;
    private final View mView;

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected boolean onPerformActionForVirtualView(int r1, int r2, Bundle bundle) {
        return false;
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        sActionIdMap = hashMap;
        hashMap.put("activate", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()));
        hashMap.put("longpress", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId()));
        hashMap.put("increment", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId()));
        hashMap.put("decrement", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()));
    }

    private void scheduleAccessibilityEventSender(View view) {
        if (this.mHandler.hasMessages(1, view)) {
            this.mHandler.removeMessages(1, view);
        }
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, view), 200L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.uimanager.ReactAccessibilityDelegate$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C16743 {

        /* renamed from: $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole */
        static final /* synthetic */ int[] f166x27e8253f;

        static {
            int[] r0 = new int[AccessibilityRole.values().length];
            f166x27e8253f = r0;
            try {
                r0[AccessibilityRole.BUTTON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TOGGLEBUTTON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f166x27e8253f[AccessibilityRole.SEARCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f166x27e8253f[AccessibilityRole.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f166x27e8253f[AccessibilityRole.IMAGEBUTTON.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f166x27e8253f[AccessibilityRole.KEYBOARDKEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TEXT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f166x27e8253f[AccessibilityRole.ADJUSTABLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f166x27e8253f[AccessibilityRole.CHECKBOX.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f166x27e8253f[AccessibilityRole.RADIO.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f166x27e8253f[AccessibilityRole.SPINBUTTON.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f166x27e8253f[AccessibilityRole.SWITCH.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f166x27e8253f[AccessibilityRole.LIST.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f166x27e8253f[AccessibilityRole.GRID.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f166x27e8253f[AccessibilityRole.NONE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f166x27e8253f[AccessibilityRole.LINK.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f166x27e8253f[AccessibilityRole.SUMMARY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f166x27e8253f[AccessibilityRole.HEADER.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f166x27e8253f[AccessibilityRole.ALERT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f166x27e8253f[AccessibilityRole.COMBOBOX.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f166x27e8253f[AccessibilityRole.MENU.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f166x27e8253f[AccessibilityRole.MENUBAR.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f166x27e8253f[AccessibilityRole.MENUITEM.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f166x27e8253f[AccessibilityRole.PROGRESSBAR.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f166x27e8253f[AccessibilityRole.RADIOGROUP.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f166x27e8253f[AccessibilityRole.SCROLLBAR.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TAB.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TABLIST.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TIMER.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f166x27e8253f[AccessibilityRole.TOOLBAR.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum AccessibilityRole {
        NONE,
        BUTTON,
        TOGGLEBUTTON,
        LINK,
        SEARCH,
        IMAGE,
        IMAGEBUTTON,
        KEYBOARDKEY,
        TEXT,
        ADJUSTABLE,
        SUMMARY,
        HEADER,
        ALERT,
        CHECKBOX,
        COMBOBOX,
        MENU,
        MENUBAR,
        MENUITEM,
        PROGRESSBAR,
        RADIO,
        RADIOGROUP,
        SCROLLBAR,
        SPINBUTTON,
        SWITCH,
        TAB,
        TABLIST,
        TIMER,
        LIST,
        GRID,
        TOOLBAR;

        public static String getValue(AccessibilityRole accessibilityRole) {
            switch (C16743.f166x27e8253f[accessibilityRole.ordinal()]) {
                case 1:
                    return "android.widget.Button";
                case 2:
                    return "android.widget.ToggleButton";
                case 3:
                    return "android.widget.EditText";
                case 4:
                    return "android.widget.ImageView";
                case 5:
                    return "android.widget.ImageButon";
                case 6:
                    return "android.inputmethodservice.Keyboard$Key";
                case 7:
                    return "android.widget.TextView";
                case 8:
                    return "android.widget.SeekBar";
                case 9:
                    return "android.widget.CheckBox";
                case 10:
                    return "android.widget.RadioButton";
                case 11:
                    return "android.widget.SpinButton";
                case 12:
                    return "android.widget.Switch";
                case 13:
                    return "android.widget.AbsListView";
                case 14:
                    return "android.widget.GridView";
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                    return "android.view.View";
                default:
                    throw new IllegalArgumentException("Invalid accessibility role value: " + accessibilityRole);
            }
        }

        public static AccessibilityRole fromValue(String str) {
            AccessibilityRole[] values;
            for (AccessibilityRole accessibilityRole : values()) {
                if (accessibilityRole.name().equalsIgnoreCase(str)) {
                    return accessibilityRole;
                }
            }
            throw new IllegalArgumentException("Invalid accessibility role value: " + str);
        }
    }

    public ReactAccessibilityDelegate(View view, boolean z, int r4) {
        super(view);
        this.mView = view;
        this.mAccessibilityActionsMap = new HashMap<>();
        this.mHandler = new Handler() { // from class: com.facebook.react.uimanager.ReactAccessibilityDelegate.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                ((View) message.obj).sendAccessibilityEvent(4);
            }
        };
        view.setFocusable(z);
        ViewCompat.setImportantForAccessibility(view, r4);
        this.mAccessibilityLinks = (AccessibilityLinks) view.getTag(C1413R.C1417id.accessibility_links);
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(C1413R.C1417id.accessibility_role);
        if (accessibilityRole != null) {
            setRole(accessibilityNodeInfoCompat, accessibilityRole, view.getContext());
        }
        Object tag = view.getTag(C1413R.C1417id.labelled_by);
        if (tag != null) {
            View findView = ReactFindViewUtil.findView(view.getRootView(), (String) tag);
            this.mAccessibilityLabelledBy = findView;
            if (findView != null) {
                accessibilityNodeInfoCompat.setLabeledBy(findView);
            }
        }
        ReadableMap readableMap = (ReadableMap) view.getTag(C1413R.C1417id.accessibility_state);
        if (readableMap != null) {
            setState(accessibilityNodeInfoCompat, readableMap, view.getContext());
        }
        ReadableArray readableArray = (ReadableArray) view.getTag(C1413R.C1417id.accessibility_actions);
        ReadableMap readableMap2 = (ReadableMap) view.getTag(C1413R.C1417id.accessibility_collection_item);
        if (readableMap2 != null) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(readableMap2.getInt("rowIndex"), readableMap2.getInt("rowSpan"), readableMap2.getInt("columnIndex"), readableMap2.getInt("columnSpan"), readableMap2.getBoolean("heading")));
        }
        if (readableArray != null) {
            for (int r2 = 0; r2 < readableArray.size(); r2++) {
                ReadableMap map = readableArray.getMap(r2);
                if (!map.hasKey("name")) {
                    throw new IllegalArgumentException("Unknown accessibility action.");
                }
                int r5 = sCounter;
                String string = map.hasKey("label") ? map.getString("label") : null;
                HashMap<String, Integer> hashMap = sActionIdMap;
                if (hashMap.containsKey(map.getString("name"))) {
                    r5 = hashMap.get(map.getString("name")).intValue();
                } else {
                    sCounter++;
                }
                this.mAccessibilityActionsMap.put(Integer.valueOf(r5), map.getString("name"));
                accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(r5, string));
            }
        }
        ReadableMap readableMap3 = (ReadableMap) view.getTag(C1413R.C1417id.accessibility_value);
        if (readableMap3 != null && readableMap3.hasKey("min") && readableMap3.hasKey("now") && readableMap3.hasKey("max")) {
            Dynamic dynamic = readableMap3.getDynamic("min");
            Dynamic dynamic2 = readableMap3.getDynamic("now");
            Dynamic dynamic3 = readableMap3.getDynamic("max");
            if (dynamic != null && dynamic.getType() == ReadableType.Number && dynamic2 != null && dynamic2.getType() == ReadableType.Number && dynamic3 != null && dynamic3.getType() == ReadableType.Number) {
                int asInt = dynamic.asInt();
                int asInt2 = dynamic2.asInt();
                int asInt3 = dynamic3.asInt();
                if (asInt3 > asInt && asInt2 >= asInt && asInt3 >= asInt2) {
                    accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(0, asInt, asInt3, asInt2));
                }
            }
        }
        String str = (String) view.getTag(C1413R.C1417id.react_test_id);
        if (str != null) {
            accessibilityNodeInfoCompat.setViewIdResourceName(str);
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        ReadableMap readableMap = (ReadableMap) view.getTag(C1413R.C1417id.accessibility_value);
        if (readableMap != null && readableMap.hasKey("min") && readableMap.hasKey("now") && readableMap.hasKey("max")) {
            Dynamic dynamic = readableMap.getDynamic("min");
            Dynamic dynamic2 = readableMap.getDynamic("now");
            Dynamic dynamic3 = readableMap.getDynamic("max");
            if (dynamic == null || dynamic.getType() != ReadableType.Number || dynamic2 == null || dynamic2.getType() != ReadableType.Number || dynamic3 == null || dynamic3.getType() != ReadableType.Number) {
                return;
            }
            int asInt = dynamic.asInt();
            int asInt2 = dynamic2.asInt();
            int asInt3 = dynamic3.asInt();
            if (asInt3 <= asInt || asInt2 < asInt || asInt3 < asInt2) {
                return;
            }
            accessibilityEvent.setItemCount(asInt3 - asInt);
            accessibilityEvent.setCurrentItemIndex(asInt2);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View view, int r7, Bundle bundle) {
        if (this.mAccessibilityActionsMap.containsKey(Integer.valueOf(r7))) {
            final WritableMap createMap = Arguments.createMap();
            createMap.putString("actionName", this.mAccessibilityActionsMap.get(Integer.valueOf(r7)));
            ReactContext reactContext = (ReactContext) view.getContext();
            if (reactContext.hasActiveReactInstance()) {
                int id = view.getId();
                int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
                UIManager uIManager = UIManagerHelper.getUIManager(reactContext, id);
                if (uIManager != null) {
                    ((EventDispatcher) uIManager.getEventDispatcher()).dispatchEvent(new Event(surfaceId, id) { // from class: com.facebook.react.uimanager.ReactAccessibilityDelegate.2
                        @Override // com.facebook.react.uimanager.events.Event
                        public String getEventName() {
                            return ReactAccessibilityDelegate.TOP_ACCESSIBILITY_ACTION_EVENT;
                        }

                        @Override // com.facebook.react.uimanager.events.Event
                        protected WritableMap getEventData() {
                            return createMap;
                        }
                    });
                }
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter, no CatalystInstance"));
            }
            AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(C1413R.C1417id.accessibility_role);
            ReadableMap readableMap = (ReadableMap) view.getTag(C1413R.C1417id.accessibility_value);
            if (accessibilityRole == AccessibilityRole.ADJUSTABLE) {
                if (r7 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId() || r7 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()) {
                    if (readableMap != null && !readableMap.hasKey("text")) {
                        scheduleAccessibilityEventSender(view);
                    }
                    return super.performAccessibilityAction(view, r7, bundle);
                }
                return true;
            }
            return true;
        }
        return super.performAccessibilityAction(view, r7, bundle);
    }

    private static void setState(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ReadableMap readableMap, Context context) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            Dynamic dynamic = readableMap.getDynamic(nextKey);
            if (nextKey.equals(STATE_SELECTED) && dynamic.getType() == ReadableType.Boolean) {
                accessibilityNodeInfoCompat.setSelected(dynamic.asBoolean());
            } else if (nextKey.equals(STATE_DISABLED) && dynamic.getType() == ReadableType.Boolean) {
                accessibilityNodeInfoCompat.setEnabled(!dynamic.asBoolean());
            } else if (nextKey.equals(STATE_CHECKED) && dynamic.getType() == ReadableType.Boolean) {
                boolean asBoolean = dynamic.asBoolean();
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setChecked(asBoolean);
                if (accessibilityNodeInfoCompat.getClassName().equals(AccessibilityRole.getValue(AccessibilityRole.SWITCH))) {
                    accessibilityNodeInfoCompat.setText(context.getString(asBoolean ? C1413R.string.state_on_description : C1413R.string.state_off_description));
                }
            }
        }
    }

    public static void setRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityRole accessibilityRole, Context context) {
        if (accessibilityRole == null) {
            accessibilityRole = AccessibilityRole.NONE;
        }
        accessibilityNodeInfoCompat.setClassName(AccessibilityRole.getValue(accessibilityRole));
        if (accessibilityRole.equals(AccessibilityRole.LINK)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.link_description));
        } else if (accessibilityRole.equals(AccessibilityRole.IMAGE)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.image_description));
        } else if (accessibilityRole.equals(AccessibilityRole.IMAGEBUTTON)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.imagebutton_description));
            accessibilityNodeInfoCompat.setClickable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.BUTTON)) {
            accessibilityNodeInfoCompat.setClickable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.TOGGLEBUTTON)) {
            accessibilityNodeInfoCompat.setClickable(true);
            accessibilityNodeInfoCompat.setCheckable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.SUMMARY)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.summary_description));
        } else if (accessibilityRole.equals(AccessibilityRole.HEADER)) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, 0, 1, true));
        } else if (accessibilityRole.equals(AccessibilityRole.ALERT)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.alert_description));
        } else if (accessibilityRole.equals(AccessibilityRole.COMBOBOX)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.combobox_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENU)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.menu_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENUBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.menubar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENUITEM)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.menuitem_description));
        } else if (accessibilityRole.equals(AccessibilityRole.PROGRESSBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.progressbar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.RADIOGROUP)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.radiogroup_description));
        } else if (accessibilityRole.equals(AccessibilityRole.SCROLLBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.scrollbar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.SPINBUTTON)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.spinbutton_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TAB)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.rn_tab_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TABLIST)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.tablist_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TIMER)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.timer_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TOOLBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(C1413R.string.toolbar_description));
        }
    }

    public static void setDelegate(View view, boolean z, int r3) {
        if (ViewCompat.hasAccessibilityDelegate(view)) {
            return;
        }
        if (view.getTag(C1413R.C1417id.accessibility_role) == null && view.getTag(C1413R.C1417id.accessibility_state) == null && view.getTag(C1413R.C1417id.accessibility_actions) == null && view.getTag(C1413R.C1417id.react_test_id) == null && view.getTag(C1413R.C1417id.accessibility_collection_item) == null && view.getTag(C1413R.C1417id.accessibility_links) == null) {
            return;
        }
        ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, z, r3));
    }

    public static void resetDelegate(View view, boolean z, int r3) {
        ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, z, r3));
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected int getVirtualViewAt(float f, float f2) {
        Layout layout;
        AccessibilityLinks accessibilityLinks = this.mAccessibilityLinks;
        if (accessibilityLinks == null || accessibilityLinks.size() == 0) {
            return Integer.MIN_VALUE;
        }
        View view = this.mView;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if ((textView.getText() instanceof Spanned) && (layout = textView.getLayout()) != null) {
                float totalPaddingLeft = f - textView.getTotalPaddingLeft();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical((int) ((f2 - textView.getTotalPaddingTop()) + textView.getScrollY())), totalPaddingLeft + textView.getScrollX());
                ClickableSpan clickableSpan = (ClickableSpan) getFirstSpan(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
                if (clickableSpan == null) {
                    return Integer.MIN_VALUE;
                }
                Spanned spanned = (Spanned) textView.getText();
                AccessibilityLinks.AccessibleLink linkBySpanPos = this.mAccessibilityLinks.getLinkBySpanPos(spanned.getSpanStart(clickableSpan), spanned.getSpanEnd(clickableSpan));
                if (linkBySpanPos != null) {
                    return linkBySpanPos.f167id;
                }
                return Integer.MIN_VALUE;
            }
            return Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected void getVisibleVirtualViews(List<Integer> list) {
        if (this.mAccessibilityLinks == null) {
            return;
        }
        for (int r0 = 0; r0 < this.mAccessibilityLinks.size(); r0++) {
            list.add(Integer.valueOf(r0));
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    protected void onPopulateNodeForVirtualView(int r5, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityLinks accessibilityLinks = this.mAccessibilityLinks;
        if (accessibilityLinks == null) {
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 1, 1));
            return;
        }
        AccessibilityLinks.AccessibleLink linkById = accessibilityLinks.getLinkById(r5);
        if (linkById == null) {
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 1, 1));
            return;
        }
        accessibilityNodeInfoCompat.setContentDescription(linkById.description);
        accessibilityNodeInfoCompat.addAction(16);
        accessibilityNodeInfoCompat.setBoundsInParent(getBoundsInParent(linkById));
        accessibilityNodeInfoCompat.setRoleDescription(this.mView.getResources().getString(C1413R.string.link_description));
        accessibilityNodeInfoCompat.setClassName(AccessibilityRole.getValue(AccessibilityRole.BUTTON));
    }

    private Rect getBoundsInParent(AccessibilityLinks.AccessibleLink accessibleLink) {
        Paint paint;
        AbsoluteSizeSpan absoluteSizeSpan;
        View view = this.mView;
        if (!(view instanceof TextView)) {
            return new Rect(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }
        TextView textView = (TextView) view;
        Layout layout = textView.getLayout();
        if (layout == null) {
            return new Rect(0, 0, textView.getWidth(), textView.getHeight());
        }
        Rect rect = new Rect();
        double d = accessibleLink.end;
        double primaryHorizontal = layout.getPrimaryHorizontal(r4);
        new Paint().setTextSize(((AbsoluteSizeSpan) getFirstSpan(accessibleLink.start, accessibleLink.end, AbsoluteSizeSpan.class)) != null ? absoluteSizeSpan.getSize() : textView.getTextSize());
        int ceil = (int) Math.ceil(paint.measureText(accessibleLink.description));
        int lineForOffset = layout.getLineForOffset(r4);
        boolean z = lineForOffset != layout.getLineForOffset((int) d);
        layout.getLineBounds(lineForOffset, rect);
        int scrollY = textView.getScrollY() + textView.getTotalPaddingTop();
        rect.top += scrollY;
        rect.bottom += scrollY;
        rect.left = (int) (rect.left + ((primaryHorizontal + textView.getTotalPaddingLeft()) - textView.getScrollX()));
        if (z) {
            return new Rect(rect.left, rect.top, rect.right, rect.bottom);
        }
        return new Rect(rect.left, rect.top, rect.left + ceil, rect.bottom);
    }

    protected <T> T getFirstSpan(int r4, int r5, Class<T> cls) {
        View view = this.mView;
        if ((view instanceof TextView) && (((TextView) view).getText() instanceof Spanned)) {
            Object[] spans = ((Spanned) ((TextView) this.mView).getText()).getSpans(r4, r5, cls);
            if (spans.length > 0) {
                return (T) spans[0];
            }
            return null;
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static class AccessibilityLinks {
        private final List<AccessibleLink> mLinks;

        public AccessibilityLinks(ClickableSpan[] clickableSpanArr, Spannable spannable) {
            ArrayList arrayList = new ArrayList();
            for (int r1 = 0; r1 < clickableSpanArr.length; r1++) {
                ClickableSpan clickableSpan = clickableSpanArr[r1];
                int spanStart = spannable.getSpanStart(clickableSpan);
                int spanEnd = spannable.getSpanEnd(clickableSpan);
                if (spanStart != spanEnd && spanStart >= 0 && spanEnd >= 0 && spanStart <= spannable.length() && spanEnd <= spannable.length()) {
                    AccessibleLink accessibleLink = new AccessibleLink();
                    accessibleLink.description = spannable.subSequence(spanStart, spanEnd).toString();
                    accessibleLink.start = spanStart;
                    accessibleLink.end = spanEnd;
                    accessibleLink.f167id = (clickableSpanArr.length - 1) - r1;
                    arrayList.add(accessibleLink);
                }
            }
            this.mLinks = arrayList;
        }

        public AccessibleLink getLinkById(int r4) {
            for (AccessibleLink accessibleLink : this.mLinks) {
                if (accessibleLink.f167id == r4) {
                    return accessibleLink;
                }
            }
            return null;
        }

        public AccessibleLink getLinkBySpanPos(int r4, int r5) {
            for (AccessibleLink accessibleLink : this.mLinks) {
                if (accessibleLink.start == r4 && accessibleLink.end == r5) {
                    return accessibleLink;
                }
            }
            return null;
        }

        public int size() {
            return this.mLinks.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class AccessibleLink {
            public String description;
            public int end;

            /* renamed from: id */
            public int f167id;
            public int start;

            private AccessibleLink() {
            }
        }
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mAccessibilityLinks != null) {
            return super.getAccessibilityNodeProvider(view);
        }
        return null;
    }
}
