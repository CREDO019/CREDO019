package com.facebook.react.views.textinput;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.autofill.HintConstants;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewDefaults;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.text.TextAttributeProps;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.TextTransform;
import com.facebook.yoga.YogaConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.reactnativecommunity.picker.PickerBlurEvent;
import com.reactnativecommunity.picker.PickerFocusEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import kotlinx.coroutines.Debug;

@ReactModule(name = ReactTextInputManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode> {
    private static final int AUTOCAPITALIZE_FLAGS = 28672;
    private static final int BLUR_TEXT_INPUT = 2;
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    private static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    private static final String KEYBOARD_TYPE_URI = "url";
    private static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    private static final int PASSWORD_VISIBILITY_FLAG = 16;
    public static final String REACT_CLASS = "AndroidTextInput";
    private static final int SET_MOST_RECENT_EVENT_COUNT = 3;
    private static final int SET_TEXT_AND_SELECTION = 4;
    public static final String TAG = "ReactTextInputManager";
    private static final int UNSET = -1;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final Map<String, String> REACT_PROPS_AUTOFILL_HINTS_MAP = new HashMap<String, String>() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.1
        {
            put("birthdate-day", HintConstants.AUTOFILL_HINT_BIRTH_DATE_DAY);
            put("birthdate-full", HintConstants.AUTOFILL_HINT_BIRTH_DATE_FULL);
            put("birthdate-month", HintConstants.AUTOFILL_HINT_BIRTH_DATE_MONTH);
            put("birthdate-year", HintConstants.AUTOFILL_HINT_BIRTH_DATE_YEAR);
            put("cc-csc", HintConstants.AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE);
            put("cc-exp", HintConstants.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE);
            put("cc-exp-day", HintConstants.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY);
            put("cc-exp-month", HintConstants.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH);
            put("cc-exp-year", HintConstants.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR);
            put("cc-number", HintConstants.AUTOFILL_HINT_CREDIT_CARD_NUMBER);
            put("email", HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS);
            put(HintConstants.AUTOFILL_HINT_GENDER, HintConstants.AUTOFILL_HINT_GENDER);
            put("name", HintConstants.AUTOFILL_HINT_PERSON_NAME);
            put("name-family", HintConstants.AUTOFILL_HINT_PERSON_NAME_FAMILY);
            put("name-given", HintConstants.AUTOFILL_HINT_PERSON_NAME_GIVEN);
            put("name-middle", HintConstants.AUTOFILL_HINT_PERSON_NAME_MIDDLE);
            put("name-middle-initial", HintConstants.AUTOFILL_HINT_PERSON_NAME_MIDDLE_INITIAL);
            put("name-prefix", HintConstants.AUTOFILL_HINT_PERSON_NAME_PREFIX);
            put("name-suffix", HintConstants.AUTOFILL_HINT_PERSON_NAME_SUFFIX);
            put(HintConstants.AUTOFILL_HINT_PASSWORD, HintConstants.AUTOFILL_HINT_PASSWORD);
            put("password-new", HintConstants.AUTOFILL_HINT_NEW_PASSWORD);
            put("postal-address", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS);
            put("postal-address-country", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_COUNTRY);
            put("postal-address-extended", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_EXTENDED_ADDRESS);
            put("postal-address-extended-postal-code", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_EXTENDED_POSTAL_CODE);
            put("postal-address-locality", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_LOCALITY);
            put("postal-address-region", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_REGION);
            put("postal-code", HintConstants.AUTOFILL_HINT_POSTAL_CODE);
            put("street-address", HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS_STREET_ADDRESS);
            put("sms-otp", HintConstants.AUTOFILL_HINT_SMS_OTP);
            put("tel", HintConstants.AUTOFILL_HINT_PHONE_NUMBER);
            put("tel-country-code", HintConstants.AUTOFILL_HINT_PHONE_COUNTRY_CODE);
            put("tel-national", HintConstants.AUTOFILL_HINT_PHONE_NATIONAL);
            put("tel-device", HintConstants.AUTOFILL_HINT_PHONE_NUMBER_DEVICE);
            put(HintConstants.AUTOFILL_HINT_USERNAME, HintConstants.AUTOFILL_HINT_USERNAME);
            put("username-new", HintConstants.AUTOFILL_HINT_NEW_USERNAME);
        }
    };
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private static final String[] DRAWABLE_FIELDS = {"mCursorDrawable", "mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter"};
    private static final String[] DRAWABLE_RESOURCES = {"mCursorDrawableRes", "mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes"};

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactEditText createViewInstance(ThemedReactContext themedReactContext) {
        ReactEditText reactEditText = new ReactEditText(themedReactContext);
        reactEditText.setInputType(reactEditText.getInputType() & (-131073));
        reactEditText.setReturnKeyType("done");
        return reactEditText;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactBaseTextShadowNode createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }

    public ReactBaseTextShadowNode createShadowNodeInstance(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return new ReactTextInputShadowNode(reactTextViewManagerCallback);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        Map<String, Object> exportedCustomBubblingEventTypeConstants = super.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants == null) {
            exportedCustomBubblingEventTypeConstants = new HashMap<>();
        }
        exportedCustomBubblingEventTypeConstants.putAll(MapBuilder.builder().put("topSubmitEditing", MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).put("topEndEditing", MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).put(ReactTextInputEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onTextInput", "captured", "onTextInputCapture"))).put(PickerFocusEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onFocus", "captured", "onFocusCapture"))).put(PickerBlurEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onBlur", "captured", "onBlurCapture"))).put(ReactTextInputKeyPressEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onKeyPress", "captured", "onKeyPressCapture"))).build());
        return exportedCustomBubblingEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.builder().put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.m1261of("registrationName", "onScroll")).build());
        return exportedCustomDirectEventTypeConstants;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1260of("focusTextInput", 1, "blurTextInput", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactEditText reactEditText, int r3, ReadableArray readableArray) {
        if (r3 == 1) {
            receiveCommand(reactEditText, "focus", readableArray);
        } else if (r3 == 2) {
            receiveCommand(reactEditText, "blur", readableArray);
        } else if (r3 != 4) {
        } else {
            receiveCommand(reactEditText, "setTextAndSelection", readableArray);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactEditText reactEditText, String str, ReadableArray readableArray) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1699362314:
                if (str.equals("blurTextInput")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3027047:
                if (str.equals("blur")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 97604824:
                if (str.equals("focus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1427010500:
                if (str.equals("setTextAndSelection")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1690703013:
                if (str.equals("focusTextInput")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                reactEditText.clearFocusFromJS();
                return;
            case 2:
            case 4:
                reactEditText.requestFocusFromJS();
                return;
            case 3:
                int r8 = readableArray.getInt(0);
                if (r8 == -1) {
                    return;
                }
                int r0 = readableArray.getInt(2);
                int r1 = readableArray.getInt(3);
                if (r1 == -1) {
                    r1 = r0;
                }
                if (!readableArray.isNull(1)) {
                    reactEditText.maybeSetTextFromJS(getReactTextUpdate(readableArray.getString(1), r8, r0, r1));
                }
                reactEditText.maybeSetSelection(r8, r0, r1);
                return;
            default:
                return;
        }
    }

    private ReactTextUpdate getReactTextUpdate(String str, int r16, int r17, int r18) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) TextTransform.apply(str, TextTransform.UNSET));
        return new ReactTextUpdate(spannableStringBuilder, r16, false, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, r17, r18);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactEditText reactEditText, Object obj) {
        if (obj instanceof ReactTextUpdate) {
            ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
            int paddingLeft = (int) reactTextUpdate.getPaddingLeft();
            int paddingTop = (int) reactTextUpdate.getPaddingTop();
            int paddingRight = (int) reactTextUpdate.getPaddingRight();
            int paddingBottom = (int) reactTextUpdate.getPaddingBottom();
            if (paddingLeft != -1 || paddingTop != -1 || paddingRight != -1 || paddingBottom != -1) {
                if (paddingLeft == -1) {
                    paddingLeft = reactEditText.getPaddingLeft();
                }
                if (paddingTop == -1) {
                    paddingTop = reactEditText.getPaddingTop();
                }
                if (paddingRight == -1) {
                    paddingRight = reactEditText.getPaddingRight();
                }
                if (paddingBottom == -1) {
                    paddingBottom = reactEditText.getPaddingBottom();
                }
                reactEditText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
            if (reactTextUpdate.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.getText(), reactEditText);
            }
            boolean z = reactEditText.getSelectionStart() == reactEditText.getSelectionEnd();
            int selectionStart = reactTextUpdate.getSelectionStart();
            int selectionEnd = reactTextUpdate.getSelectionEnd();
            if ((selectionStart == -1 || selectionEnd == -1) && z) {
                selectionStart = reactTextUpdate.getText().length() - ((reactEditText.getText() != null ? reactEditText.getText().length() : 0) - reactEditText.getSelectionStart());
                selectionEnd = selectionStart;
            }
            reactEditText.maybeSetTextFromState(reactTextUpdate);
            reactEditText.maybeSetSelection(reactTextUpdate.getJsEventCounter(), selectionStart, selectionEnd);
        }
    }

    @ReactProp(defaultFloat = ViewDefaults.FONT_SIZE_SP, name = "fontSize")
    public void setFontSize(ReactEditText reactEditText, float f) {
        reactEditText.setFontSize(f);
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText reactEditText, String str) {
        reactEditText.setFontFamily(str);
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.MAX_FONT_SIZE_MULTIPLIER)
    public void setMaxFontSizeMultiplier(ReactEditText reactEditText, float f) {
        reactEditText.setMaxFontSizeMultiplier(f);
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText reactEditText, String str) {
        reactEditText.setFontWeight(str);
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText reactEditText, String str) {
        reactEditText.setFontStyle(str);
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.INCLUDE_FONT_PADDING)
    public void setIncludeFontPadding(ReactEditText reactEditText, boolean z) {
        reactEditText.setIncludeFontPadding(z);
    }

    @ReactProp(name = "importantForAutofill")
    public void setImportantForAutofill(ReactEditText reactEditText, String str) {
        int r3;
        if ("no".equals(str)) {
            r3 = 2;
        } else if ("noExcludeDescendants".equals(str)) {
            r3 = 8;
        } else if ("yes".equals(str)) {
            r3 = 1;
        } else {
            r3 = "yesExcludeDescendants".equals(str) ? 4 : 0;
        }
        setImportantForAutofill(reactEditText, r3);
    }

    private void setImportantForAutofill(ReactEditText reactEditText, int r4) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        reactEditText.setImportantForAutofill(r4);
    }

    private void setAutofillHints(ReactEditText reactEditText, String... strArr) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        reactEditText.setAutofillHints(strArr);
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setSelectionWatcher(new ReactSelectionWatcher(reactEditText));
        } else {
            reactEditText.setSelectionWatcher(null);
        }
    }

    @ReactProp(name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText reactEditText, Boolean bool) {
        reactEditText.setBlurOnSubmit(bool);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setContentSizeWatcher(new ReactContentSizeWatcher(reactEditText));
        } else {
            reactEditText.setContentSizeWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setScrollWatcher(new ReactScrollWatcher(reactEditText));
        } else {
            reactEditText.setScrollWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onKeyPress")
    public void setOnKeyPress(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnKeyPress(z);
    }

    @ReactProp(defaultFloat = 0.0f, name = ViewProps.LETTER_SPACING)
    public void setLetterSpacing(ReactEditText reactEditText, float f) {
        reactEditText.setLetterSpacingPt(f);
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ALLOW_FONT_SCALING)
    public void setAllowFontScaling(ReactEditText reactEditText, boolean z) {
        reactEditText.setAllowFontScaling(z);
    }

    @ReactProp(name = ReactTextInputShadowNode.PROP_PLACEHOLDER)
    public void setPlaceholder(ReactEditText reactEditText, String str) {
        reactEditText.setHint(str);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(reactEditText.getContext()));
        } else {
            reactEditText.setHintTextColor(num.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactEditText.getContext()));
        } else {
            reactEditText.setHighlightColor(num.intValue());
        }
        setCursorColor(reactEditText, num);
    }

    @ReactProp(customType = "Color", name = "cursorColor")
    public void setCursorColor(ReactEditText reactEditText, Integer num) {
        int r3;
        if (num == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Drawable textCursorDrawable = reactEditText.getTextCursorDrawable();
            if (textCursorDrawable != null) {
                textCursorDrawable.setColorFilter(new BlendModeColorFilter(num.intValue(), BlendMode.SRC_IN));
                reactEditText.setTextCursorDrawable(textCursorDrawable);
            }
        } else if (Build.VERSION.SDK_INT == 28) {
        } else {
            int r1 = 0;
            while (true) {
                String[] strArr = DRAWABLE_RESOURCES;
                if (r1 >= strArr.length) {
                    return;
                }
                try {
                    Field declaredField = TextView.class.getDeclaredField(strArr[r1]);
                    declaredField.setAccessible(true);
                    r3 = declaredField.getInt(reactEditText);
                } catch (IllegalAccessException | NoSuchFieldException unused) {
                }
                if (r3 == 0) {
                    return;
                }
                Drawable mutate = ContextCompat.getDrawable(reactEditText.getContext(), r3).mutate();
                mutate.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(reactEditText);
                Field declaredField3 = obj.getClass().getDeclaredField(DRAWABLE_FIELDS[r1]);
                declaredField3.setAccessible(true);
                if (strArr[r1] == "mCursorDrawableRes") {
                    declaredField3.set(obj, new Drawable[]{mutate, mutate});
                } else {
                    declaredField3.set(obj, mutate);
                }
                r1++;
            }
        }
    }

    private static boolean shouldHideCursorForEmailTextInput() {
        return Build.VERSION.SDK_INT == 29 && Build.MANUFACTURER.toLowerCase(Locale.ROOT).contains("xiaomi");
    }

    @ReactProp(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText reactEditText, boolean z) {
        if (reactEditText.getStagedInputType() == 32 && shouldHideCursorForEmailTextInput()) {
            return;
        }
        reactEditText.setCursorVisible(!z);
    }

    @ReactProp(defaultBoolean = false, name = "contextMenuHidden")
    public void setContextMenuHidden(ReactEditText reactEditText, final boolean z) {
        reactEditText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return z;
            }
        });
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setSelectAllOnFocus(z);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            ColorStateList defaultTextColor = DefaultStyleValuesUtil.getDefaultTextColor(reactEditText.getContext());
            if (defaultTextColor != null) {
                reactEditText.setTextColor(defaultTextColor);
                return;
            }
            Context context = reactEditText.getContext();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not get default text color from View Context: ");
            sb.append(context != null ? context.getClass().getCanonicalName() : "null");
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException(sb.toString()));
            return;
        }
        reactEditText.setTextColor(num.intValue());
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText reactEditText, Integer num) {
        Drawable background = reactEditText.getBackground();
        if (background.getConstantState() != null) {
            try {
                background = background.mutate();
            } catch (NullPointerException e) {
                FLog.m1327e(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", e);
            }
        }
        if (num == null) {
            background.clearColorFilter();
        } else if (Build.VERSION.SDK_INT == 21) {
            int borderColor = reactEditText.getBorderColor(3);
            setBorderColor(reactEditText, 4, num);
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            setBorderColor(reactEditText, 4, Integer.valueOf(borderColor));
        } else {
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText reactEditText, String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                reactEditText.setJustificationMode(1);
            }
            reactEditText.setGravityHorizontal(3);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            reactEditText.setJustificationMode(0);
        }
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityHorizontal(0);
        } else if ("left".equals(str)) {
            reactEditText.setGravityHorizontal(3);
        } else if ("right".equals(str)) {
            reactEditText.setGravityHorizontal(5);
        } else if (TtmlNode.CENTER.equals(str)) {
            reactEditText.setGravityHorizontal(1);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
        }
    }

    @ReactProp(name = ViewProps.TEXT_ALIGN_VERTICAL)
    public void setTextAlignVertical(ReactEditText reactEditText, String str) {
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityVertical(0);
        } else if (ViewProps.TOP.equals(str)) {
            reactEditText.setGravityVertical(48);
        } else if (ViewProps.BOTTOM.equals(str)) {
            reactEditText.setGravityVertical(80);
        } else if (TtmlNode.CENTER.equals(str)) {
            reactEditText.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + str);
        }
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText reactEditText, String str) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(reactEditText.getContext(), str), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText reactEditText, int r2) {
        reactEditText.setCompoundDrawablePadding(r2);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText reactEditText, boolean z) {
        reactEditText.setEnabled(z);
    }

    @ReactProp(defaultInt = 1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumLines(ReactEditText reactEditText, int r2) {
        reactEditText.setLines(r2);
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(ReactEditText reactEditText, Integer num) {
        InputFilter[] filters = reactEditText.getFilters();
        InputFilter[] inputFilterArr = EMPTY_FILTERS;
        if (num == null) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int r2 = 0; r2 < filters.length; r2++) {
                    if (!(filters[r2] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[r2]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    inputFilterArr = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int r1 = 0; r1 < filters.length; r1++) {
                if (filters[r1] instanceof InputFilter.LengthFilter) {
                    filters[r1] = new InputFilter.LengthFilter(num.intValue());
                    z = true;
                }
            }
            if (!z) {
                InputFilter[] inputFilterArr2 = new InputFilter[filters.length + 1];
                System.arraycopy(filters, 0, inputFilterArr2, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(num.intValue());
                filters = inputFilterArr2;
            }
            inputFilterArr = filters;
        } else {
            inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(num.intValue())};
        }
        reactEditText.setFilters(inputFilterArr);
    }

    @ReactProp(name = "autoComplete")
    public void setTextContentType(ReactEditText reactEditText, String str) {
        if (str == null) {
            setImportantForAutofill(reactEditText, 2);
        } else if (Debug.DEBUG_PROPERTY_VALUE_OFF.equals(str)) {
            setImportantForAutofill(reactEditText, 2);
        } else {
            Map<String, String> map = REACT_PROPS_AUTOFILL_HINTS_MAP;
            if (map.containsKey(str)) {
                setAutofillHints(reactEditText, map.get(str));
                return;
            }
            throw new JSApplicationIllegalArgumentException("Invalid autoComplete: " + str);
        }
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText reactEditText, Boolean bool) {
        int r3;
        if (bool != null) {
            r3 = bool.booleanValue() ? 32768 : 524288;
        } else {
            r3 = 0;
        }
        updateStagedInputTypeFlag(reactEditText, 557056, r3);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText reactEditText, boolean z) {
        updateStagedInputTypeFlag(reactEditText, z ? 0 : 131072, z ? 131072 : 0);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText reactEditText, boolean z) {
        updateStagedInputTypeFlag(reactEditText, 144, z ? 128 : 0);
        checkPasswordType(reactEditText);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText reactEditText, Dynamic dynamic) {
        int r2 = 16384;
        if (dynamic.getType() == ReadableType.Number) {
            r2 = dynamic.asInt();
        } else if (dynamic.getType() == ReadableType.String) {
            String asString = dynamic.asString();
            if (asString.equals("none")) {
                r2 = 0;
            } else if (asString.equals("characters")) {
                r2 = 4096;
            } else if (asString.equals("words")) {
                r2 = 8192;
            } else {
                asString.equals("sentences");
            }
        }
        updateStagedInputTypeFlag(reactEditText, AUTOCAPITALIZE_FLAGS, r2);
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(ReactEditText reactEditText, String str) {
        int r3;
        if ("numeric".equalsIgnoreCase(str)) {
            r3 = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_NUMBER_PAD.equalsIgnoreCase(str)) {
            r3 = 2;
        } else if (KEYBOARD_TYPE_DECIMAL_PAD.equalsIgnoreCase(str)) {
            r3 = 8194;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(str)) {
            r3 = 33;
            if (shouldHideCursorForEmailTextInput()) {
                reactEditText.setCursorVisible(false);
            }
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(str)) {
            r3 = 3;
        } else if (KEYBOARD_TYPE_VISIBLE_PASSWORD.equalsIgnoreCase(str)) {
            r3 = 144;
        } else {
            r3 = "url".equalsIgnoreCase(str) ? 16 : 1;
        }
        updateStagedInputTypeFlag(reactEditText, 15, r3);
        checkPasswordType(reactEditText);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText reactEditText, String str) {
        reactEditText.setReturnKeyType(str);
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText reactEditText, boolean z) {
        reactEditText.setDisableFullscreenUI(z);
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText reactEditText, String str) {
        reactEditText.setImeActionLabel(str, IME_ACTION_ID);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_RADIUS, ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(ReactEditText reactEditText, int r3, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (r3 == 0) {
            reactEditText.setBorderRadius(f);
        } else {
            reactEditText.setBorderRadius(f, r3 - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText reactEditText, String str) {
        reactEditText.setBorderStyle(str);
    }

    @ReactProp(defaultBoolean = true, name = "showSoftInputOnFocus")
    public void showKeyboardOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setShowSoftInputOnFocus(z);
    }

    @ReactProp(defaultBoolean = false, name = "autoFocus")
    public void setAutoFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setAutoFocus(z);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH})
    public void setBorderWidth(ReactEditText reactEditText, int r3, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactEditText.setBorderWidth(SPACING_TYPES[r3], f);
    }

    @ReactPropGroup(customType = "Color", names = {ViewProps.BORDER_COLOR, ViewProps.BORDER_LEFT_COLOR, ViewProps.BORDER_RIGHT_COLOR, ViewProps.BORDER_TOP_COLOR, ViewProps.BORDER_BOTTOM_COLOR})
    public void setBorderColor(ReactEditText reactEditText, int r5, Integer num) {
        reactEditText.setBorderColor(SPACING_TYPES[r5], num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK, num != null ? num.intValue() >>> 24 : Float.NaN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactEditText reactEditText) {
        super.onAfterUpdateTransaction((ReactTextInputManager) reactEditText);
        reactEditText.maybeUpdateTypeface();
        reactEditText.commitStagedInputType();
    }

    private static void checkPasswordType(ReactEditText reactEditText) {
        if ((reactEditText.getStagedInputType() & INPUT_TYPE_KEYBOARD_NUMBERED) == 0 || (reactEditText.getStagedInputType() & 128) == 0) {
            return;
        }
        updateStagedInputTypeFlag(reactEditText, 128, 16);
    }

    private static void updateStagedInputTypeFlag(ReactEditText reactEditText, int r2, int r3) {
        reactEditText.setStagedInputType(((~r2) & reactEditText.getStagedInputType()) | r3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EventDispatcher getEventDispatcher(ReactContext reactContext, ReactEditText reactEditText) {
        return UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactEditText.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ReactTextInputTextWatcher implements TextWatcher {
        private final ReactEditText mEditText;
        private final EventDispatcher mEventDispatcher;
        private String mPreviousText = null;
        private final int mSurfaceId;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        public ReactTextInputTextWatcher(ReactContext reactContext, ReactEditText reactEditText) {
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mEditText = reactEditText;
            this.mSurfaceId = UIManagerHelper.getSurfaceId(reactContext);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int r2, int r3, int r4) {
            this.mPreviousText = charSequence.toString();
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int r11, int r12, int r13) {
            if (this.mEditText.mDisableTextDiffing) {
                return;
            }
            if (r13 == 0 && r12 == 0) {
                return;
            }
            Assertions.assertNotNull(this.mPreviousText);
            String substring = charSequence.toString().substring(r11, r11 + r13);
            int r8 = r11 + r12;
            String substring2 = this.mPreviousText.substring(r11, r8);
            if (r13 == r12 && substring.equals(substring2)) {
                return;
            }
            FabricViewStateManager fabricViewStateManager = this.mEditText.getFabricViewStateManager();
            if (fabricViewStateManager.hasStateWrapper()) {
                fabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.ReactTextInputTextWatcher.1
                    @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                    public WritableMap getStateUpdate() {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putInt("mostRecentEventCount", ReactTextInputTextWatcher.this.mEditText.incrementAndGetEventCounter());
                        writableNativeMap.putInt("opaqueCacheId", ReactTextInputTextWatcher.this.mEditText.getId());
                        return writableNativeMap;
                    }
                });
            }
            this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mSurfaceId, this.mEditText.getId(), charSequence.toString(), this.mEditText.incrementAndGetEventCounter()));
            this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mSurfaceId, this.mEditText.getId(), substring, substring2, r11, r8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(final ThemedReactContext themedReactContext, final ReactEditText reactEditText) {
        reactEditText.setEventDispatcher(getEventDispatcher(themedReactContext, reactEditText));
        reactEditText.addTextChangedListener(new ReactTextInputTextWatcher(themedReactContext, reactEditText));
        reactEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                int surfaceId = themedReactContext.getSurfaceId();
                EventDispatcher eventDispatcher = ReactTextInputManager.getEventDispatcher(themedReactContext, reactEditText);
                if (z) {
                    eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(surfaceId, reactEditText.getId()));
                    return;
                }
                eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(surfaceId, reactEditText.getId()));
                eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(surfaceId, reactEditText.getId(), reactEditText.getText().toString()));
            }
        });
        reactEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.facebook.react.views.textinput.ReactTextInputManager.4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int r8, KeyEvent keyEvent) {
                if ((r8 & 255) != 0 || r8 == 0) {
                    boolean blurOnSubmit = reactEditText.getBlurOnSubmit();
                    boolean isMultiline = reactEditText.isMultiline();
                    ReactTextInputManager.getEventDispatcher(themedReactContext, reactEditText).dispatchEvent(new ReactTextInputSubmitEditingEvent(themedReactContext.getSurfaceId(), reactEditText.getId(), reactEditText.getText().toString()));
                    if (blurOnSubmit) {
                        reactEditText.clearFocus();
                    }
                    return blurOnSubmit || !isMultiline || r8 == 5 || r8 == 7;
                }
                return true;
            }
        });
    }

    /* loaded from: classes.dex */
    private static class ReactContentSizeWatcher implements ContentSizeWatcher {
        private final ReactEditText mEditText;
        private final EventDispatcher mEventDispatcher;
        private final int mSurfaceId;
        private int mPreviousContentWidth = 0;
        private int mPreviousContentHeight = 0;

        public ReactContentSizeWatcher(ReactEditText reactEditText) {
            this.mEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId(reactContext);
        }

        @Override // com.facebook.react.views.textinput.ContentSizeWatcher
        public void onLayout() {
            if (this.mEventDispatcher == null) {
                return;
            }
            int width = this.mEditText.getWidth();
            int height = this.mEditText.getHeight();
            if (this.mEditText.getLayout() != null) {
                width = this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth() + this.mEditText.getCompoundPaddingRight();
                height = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingBottom();
            }
            if (width == this.mPreviousContentWidth && height == this.mPreviousContentHeight) {
                return;
            }
            this.mPreviousContentHeight = height;
            this.mPreviousContentWidth = width;
            this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mSurfaceId, this.mEditText.getId(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height)));
        }
    }

    /* loaded from: classes.dex */
    private static class ReactSelectionWatcher implements SelectionWatcher {
        private final EventDispatcher mEventDispatcher;
        private int mPreviousSelectionEnd;
        private int mPreviousSelectionStart;
        private final ReactEditText mReactEditText;
        private final int mSurfaceId;

        public ReactSelectionWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId(reactContext);
        }

        @Override // com.facebook.react.views.textinput.SelectionWatcher
        public void onSelectionChanged(int r5, int r6) {
            int min = Math.min(r5, r6);
            int max = Math.max(r5, r6);
            if (this.mPreviousSelectionStart == min && this.mPreviousSelectionEnd == max) {
                return;
            }
            this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mSurfaceId, this.mReactEditText.getId(), min, max));
            this.mPreviousSelectionStart = min;
            this.mPreviousSelectionEnd = max;
        }
    }

    /* loaded from: classes.dex */
    private static class ReactScrollWatcher implements ScrollWatcher {
        private final EventDispatcher mEventDispatcher;
        private int mPreviousHoriz;
        private int mPreviousVert;
        private final ReactEditText mReactEditText;
        private final int mSurfaceId;

        public ReactScrollWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId(reactContext);
        }

        @Override // com.facebook.react.views.textinput.ScrollWatcher
        public void onScrollChanged(int r12, int r13, int r14, int r15) {
            if (this.mPreviousHoriz == r12 && this.mPreviousVert == r13) {
                return;
            }
            this.mEventDispatcher.dispatchEvent(ScrollEvent.obtain(this.mSurfaceId, this.mReactEditText.getId(), ScrollEventType.SCROLL, r12, r13, 0.0f, 0.0f, 0, 0, this.mReactEditText.getWidth(), this.mReactEditText.getHeight()));
            this.mPreviousHoriz = r12;
            this.mPreviousVert = r13;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedViewConstants() {
        return MapBuilder.m1261of("AutoCapitalizationType", MapBuilder.m1258of("none", 0, "characters", 4096, "words", 8192, "sentences", 16384));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void setPadding(ReactEditText reactEditText, int r2, int r3, int r4, int r5) {
        reactEditText.setPadding(r2, r3, r4, r5);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateState(ReactEditText reactEditText, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        FabricViewStateManager fabricViewStateManager = reactEditText.getFabricViewStateManager();
        if (!fabricViewStateManager.hasStateWrapper()) {
            reactEditText.setPadding(0, 0, 0, 0);
        }
        fabricViewStateManager.setStateWrapper(stateWrapper);
        ReadableNativeMap stateData = stateWrapper.getStateData();
        if (stateData != null && stateData.hasKey("attributedString")) {
            ReadableNativeMap map = stateData.getMap("attributedString");
            ReadableNativeMap map2 = stateData.getMap("paragraphAttributes");
            if (map == null || map2 == null) {
                throw new IllegalArgumentException("Invalid TextInput State was received as a parameters");
            }
            return ReactTextUpdate.buildReactTextUpdateFromState(TextLayoutManager.getOrCreateSpannableForText(reactEditText.getContext(), map, this.mReactTextViewManagerCallback), stateData.getInt("mostRecentEventCount"), TextAttributeProps.getTextAlignment(reactStylesDiffMap, TextLayoutManager.isRTL(map)), TextAttributeProps.getTextBreakStrategy(map2.getString(ViewProps.TEXT_BREAK_STRATEGY)), TextAttributeProps.getJustificationMode(reactStylesDiffMap), map.getArray("fragments").toArrayList().size() > 1);
        }
        return null;
    }
}
