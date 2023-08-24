package com.reactnativecommunity.picker;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.reactnativecommunity.picker.ReactPicker;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class ReactPickerManager extends BaseViewManager<ReactPicker, ReactPickerShadowNode> {
    private static final int BLUR_PICKER = 2;
    private static final ReadableArray EMPTY_ARRAY = Arguments.createArray();
    private static final int FOCUS_PICKER = 1;

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactPicker reactPicker, Object obj) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(PickerItemSelectEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(PickerFocusEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onFocus", "captured", "onFocusCapture"))).put(PickerBlurEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onBlur", "captured", "onBlurCapture"))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1260of("focus", 1, "blur", 2);
    }

    @ReactProp(name = "items")
    public void setItems(ReactPicker reactPicker, @Nullable ReadableArray readableArray) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), readableArray);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setItems(readableArray);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker reactPicker, @Nullable Integer num) {
        reactPicker.setPrimaryColor(num);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter != null) {
            reactPickerAdapter.setPrimaryTextColor(num);
        }
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, @Nullable String str) {
        reactPicker.setPrompt(str);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker reactPicker, boolean z) {
        reactPicker.setEnabled(z);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker reactPicker, int r2) {
        reactPicker.setStagedSelection(r2);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(ReactPicker reactPicker, @Nullable int r2) {
        reactPicker.setBackgroundColor(r2);
    }

    @ReactProp(name = "dropdownIconColor")
    public void setDropdownIconColor(ReactPicker reactPicker, @Nullable int r2) {
        reactPicker.setDropdownIconColor(r2);
    }

    @ReactProp(name = "dropdownIconRippleColor")
    public void setDropdownIconRippleColor(ReactPicker reactPicker, @Nullable int r2) {
        reactPicker.setDropdownIconRippleColor(r2);
    }

    @ReactProp(defaultInt = 1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumberOfLines(ReactPicker reactPicker, int r5) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), EMPTY_ARRAY);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPickerAdapter2.setNumberOfLines(r5);
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setNumberOfLines(r5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactPicker reactPicker) {
        super.onAfterUpdateTransaction((ReactPickerManager) reactPicker);
        reactPicker.updateStagedSelection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactPicker reactPicker) {
        PickerEventEmitter pickerEventEmitter = new PickerEventEmitter(reactPicker, ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher());
        reactPicker.setOnSelectListener(pickerEventEmitter);
        reactPicker.setOnFocusListener(pickerEventEmitter);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactPicker reactPicker, int r2, ReadableArray readableArray) {
        if (r2 == 1) {
            reactPicker.performClick();
        } else if (r2 != 2) {
        } else {
            reactPicker.clearFocus();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactPicker reactPicker, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("blur")) {
            reactPicker.clearFocus();
        } else if (str.equals("focus")) {
            reactPicker.performClick();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactPickerShadowNode createShadowNodeInstance() {
        return new ReactPickerShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends ReactPickerShadowNode> getShadowNodeClass() {
        return ReactPickerShadowNode.class;
    }

    /* loaded from: classes3.dex */
    private static class ReactPickerAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        @Nullable
        private ReadableArray mItems;
        private int mNumberOfLines = 1;
        @Nullable
        private Integer mPrimaryTextColor;

        @Override // android.widget.Adapter
        public long getItemId(int r3) {
            return r3;
        }

        public ReactPickerAdapter(Context context, @Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
        }

        public void setItems(@Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return 0;
            }
            return readableArray.size();
        }

        @Override // android.widget.Adapter
        public ReadableMap getItem(int r2) {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return null;
            }
            return readableArray.getMap(r2);
        }

        @Override // android.widget.Adapter
        public View getView(int r2, View view, ViewGroup viewGroup) {
            return getView(r2, view, viewGroup, false);
        }

        @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int r2, View view, ViewGroup viewGroup) {
            return getView(r2, view, viewGroup, true);
        }

        private View getView(int r8, View view, ViewGroup viewGroup, boolean z) {
            Integer num;
            int r9;
            ReadableMap item = getItem(r8);
            ReadableMap map = item.hasKey(TtmlNode.TAG_STYLE) ? item.getMap(TtmlNode.TAG_STYLE) : null;
            if (view == null) {
                if (z) {
                    r9 = C3996R.layout.simple_spinner_dropdown_item;
                } else {
                    r9 = C3996R.layout.simple_spinner_item;
                }
                view = this.mInflater.inflate(r9, viewGroup, false);
            }
            boolean z2 = item.hasKey("enabled") ? item.getBoolean("enabled") : true;
            view.setEnabled(z2);
            view.setClickable(!z2);
            TextView textView = (TextView) view;
            textView.setText(item.getString("label"));
            textView.setMaxLines(this.mNumberOfLines);
            if (map != null) {
                if (map.hasKey("backgroundColor") && !map.isNull("backgroundColor")) {
                    view.setBackgroundColor(map.getInt("backgroundColor"));
                } else {
                    view.setBackgroundColor(0);
                }
                if (map.hasKey("color") && !map.isNull("color")) {
                    textView.setTextColor(map.getInt("color"));
                }
                if (map.hasKey("fontSize") && !map.isNull("fontSize")) {
                    textView.setTextSize((float) map.getDouble("fontSize"));
                }
                if (map.hasKey("fontFamily") && !map.isNull("fontFamily")) {
                    textView.setTypeface(Typeface.create(map.getString("fontFamily"), 0));
                }
            }
            if (!z && (num = this.mPrimaryTextColor) != null) {
                textView.setTextColor(num.intValue());
            } else if (item.hasKey("color") && !item.isNull("color")) {
                textView.setTextColor(item.getInt("color"));
            }
            if (item.hasKey("fontFamily") && !item.isNull("fontFamily")) {
                textView.setTypeface(Typeface.create(item.getString("fontFamily"), 0));
            }
            if (I18nUtil.getInstance().isRTL(view.getContext())) {
                view.setLayoutDirection(1);
                view.setTextDirection(4);
            } else {
                view.setLayoutDirection(0);
                view.setTextDirection(3);
            }
            return view;
        }

        public void setPrimaryTextColor(@Nullable Integer num) {
            this.mPrimaryTextColor = num;
            notifyDataSetChanged();
        }

        public void setNumberOfLines(int r1) {
            this.mNumberOfLines = r1;
            notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PickerEventEmitter implements ReactPicker.OnSelectListener, ReactPicker.OnFocusListener {
        private final EventDispatcher mEventDispatcher;
        private final ReactPicker mReactPicker;

        public PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
            this.mReactPicker = reactPicker;
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnSelectListener
        public void onItemSelected(int r4) {
            this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), r4));
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnFocusListener
        public void onPickerBlur() {
            this.mEventDispatcher.dispatchEvent(new PickerBlurEvent(this.mReactPicker.getId()));
        }

        @Override // com.reactnativecommunity.picker.ReactPicker.OnFocusListener
        public void onPickerFocus() {
            this.mEventDispatcher.dispatchEvent(new PickerFocusEvent(this.mReactPicker.getId()));
        }
    }
}
