package com.facebook.react.views.text;

import android.content.Context;
import android.text.Spannable;
import com.facebook.react.C1413R;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.IViewManagerWithChildren;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga.YogaMeasureMode;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = ReactTextViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactTextViewManager extends ReactTextAnchorViewManager<ReactTextView, ReactTextShadowNode> implements IViewManagerWithChildren {
    public static final String REACT_CLASS = "RCTText";
    private static final short TX_STATE_KEY_ATTRIBUTED_STRING = 0;
    private static final short TX_STATE_KEY_HASH = 2;
    private static final short TX_STATE_KEY_MOST_RECENT_EVENT_COUNT = 3;
    private static final short TX_STATE_KEY_PARAGRAPH_ATTRIBUTES = 1;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public ReactTextViewManager() {
        this(null);
    }

    public ReactTextViewManager(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.mReactTextViewManagerCallback = reactTextViewManagerCallback;
        setupViewRecycling();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public ReactTextView prepareToRecycleView(ThemedReactContext themedReactContext, ReactTextView reactTextView) {
        super.prepareToRecycleView(themedReactContext, (ThemedReactContext) reactTextView);
        reactTextView.recycleView();
        setSelectionColor(reactTextView, null);
        return reactTextView;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactTextView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactTextView reactTextView, Object obj) {
        ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
        Spannable text = reactTextUpdate.getText();
        if (reactTextUpdate.containsImages()) {
            TextInlineImageSpan.possiblyUpdateInlineImageSpans(text, reactTextView);
        }
        reactTextView.setText(reactTextUpdate);
        ReactClickableSpan[] reactClickableSpanArr = (ReactClickableSpan[]) text.getSpans(0, reactTextUpdate.getText().length(), ReactClickableSpan.class);
        if (reactClickableSpanArr.length > 0) {
            reactTextView.setTag(C1413R.C1417id.accessibility_links, new ReactAccessibilityDelegate.AccessibilityLinks(reactClickableSpanArr, text));
            ReactAccessibilityDelegate.resetDelegate(reactTextView, reactTextView.isFocusable(), reactTextView.getImportantForAccessibility());
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode(this.mReactTextViewManagerCallback);
    }

    public ReactTextShadowNode createShadowNodeInstance(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return new ReactTextShadowNode(reactTextViewManagerCallback);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactTextView reactTextView) {
        super.onAfterUpdateTransaction((ReactTextViewManager) reactTextView);
        reactTextView.updateView();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateState(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        ReadableMapBuffer stateDataMapBuffer;
        if (ReactFeatureFlags.mapBufferSerializationEnabled && (stateDataMapBuffer = stateWrapper.getStateDataMapBuffer()) != null) {
            return getReactTextUpdate(reactTextView, reactStylesDiffMap, stateDataMapBuffer);
        }
        ReadableNativeMap stateData = stateWrapper.getStateData();
        if (stateData == null) {
            return null;
        }
        ReadableNativeMap map = stateData.getMap("attributedString");
        ReadableNativeMap map2 = stateData.getMap("paragraphAttributes");
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(reactTextView.getContext(), map, this.mReactTextViewManagerCallback);
        reactTextView.setSpanned(orCreateSpannableForText);
        return new ReactTextUpdate(orCreateSpannableForText, stateData.hasKey("mostRecentEventCount") ? stateData.getInt("mostRecentEventCount") : -1, false, TextAttributeProps.getTextAlignment(reactStylesDiffMap, TextLayoutManager.isRTL(map)), TextAttributeProps.getTextBreakStrategy(map2.getString(ViewProps.TEXT_BREAK_STRATEGY)), TextAttributeProps.getJustificationMode(reactStylesDiffMap));
    }

    private Object getReactTextUpdate(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, MapBuffer mapBuffer) {
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(0);
        MapBuffer mapBuffer3 = mapBuffer.getMapBuffer(1);
        Spannable orCreateSpannableForText = TextLayoutManagerMapBuffer.getOrCreateSpannableForText(reactTextView.getContext(), mapBuffer2, this.mReactTextViewManagerCallback);
        reactTextView.setSpanned(orCreateSpannableForText);
        return new ReactTextUpdate(orCreateSpannableForText, -1, false, TextAttributeProps.getTextAlignment(reactStylesDiffMap, TextLayoutManagerMapBuffer.isRTL(mapBuffer2)), TextAttributeProps.getTextBreakStrategy(mapBuffer3.getString(2)), TextAttributeProps.getJustificationMode(reactStylesDiffMap));
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.m1260of("topTextLayout", MapBuilder.m1261of("registrationName", "onTextLayout"), "topInlineViewLayout", MapBuilder.m1261of("registrationName", "onInlineViewLayout")));
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return TextLayoutManager.measureText(context, readableMap, readableMap2, f, yogaMeasureMode, f2, yogaMeasureMode2, this.mReactTextViewManagerCallback, fArr);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, MapBuffer mapBuffer3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return TextLayoutManagerMapBuffer.measureText(context, mapBuffer, mapBuffer2, f, yogaMeasureMode, f2, yogaMeasureMode2, this.mReactTextViewManagerCallback, fArr);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void setPadding(ReactTextView reactTextView, int r2, int r3, int r4, int r5) {
        reactTextView.setPadding(r2, r3, r4, r5);
    }
}
