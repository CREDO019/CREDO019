package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.text.CustomLetterSpacingSpan;
import com.facebook.react.views.text.CustomLineHeightSpan;
import com.facebook.react.views.text.CustomStyleSpan;
import com.facebook.react.views.text.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.ReactSpan;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.facebook.react.views.text.TextAttributes;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ReactEditText extends AppCompatEditText implements FabricViewStateManager.HasFabricViewStateManager {
    public static final boolean DEBUG_MODE = false;
    private static final int UNSET = -1;
    private static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    private final String TAG;
    private boolean mAutoFocus;
    private Boolean mBlurOnSubmit;
    protected boolean mContainsImages;
    private ContentSizeWatcher mContentSizeWatcher;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private boolean mDetectScrollMovement;
    private boolean mDidAttachToWindow;
    private boolean mDisableFullscreen;
    protected boolean mDisableTextDiffing;
    private EventDispatcher mEventDispatcher;
    private final FabricViewStateManager mFabricViewStateManager;
    private String mFontFamily;
    private int mFontStyle;
    private int mFontWeight;
    private final InputMethodManager mInputMethodManager;
    protected boolean mIsSettingTextFromCacheUpdate;
    protected boolean mIsSettingTextFromJS;
    protected boolean mIsSettingTextFromState;
    private InternalKeyListener mKeyListener;
    private ArrayList<TextWatcher> mListeners;
    protected int mNativeEventCount;
    private boolean mOnKeyPress;
    private ReactViewBackgroundManager mReactBackgroundManager;
    private String mReturnKeyType;
    private ScrollWatcher mScrollWatcher;
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    private TextAttributes mTextAttributes;
    private TextWatcherDelegator mTextWatcherDelegator;
    private boolean mTypefaceDirty;

    @Override // android.view.View
    public boolean isLayoutRequested() {
        return false;
    }

    public ReactEditText(Context context) {
        super(context);
        this.TAG = "ReactEditText";
        this.mIsSettingTextFromCacheUpdate = false;
        this.mDetectScrollMovement = false;
        this.mOnKeyPress = false;
        this.mTypefaceDirty = false;
        this.mFontFamily = null;
        this.mFontWeight = -1;
        this.mFontStyle = -1;
        this.mAutoFocus = false;
        this.mDidAttachToWindow = false;
        this.mFabricViewStateManager = new FabricViewStateManager();
        this.mDisableTextDiffing = false;
        this.mIsSettingTextFromState = false;
        setFocusableInTouchMode(false);
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mInputMethodManager = (InputMethodManager) Assertions.assertNotNull(context.getSystemService("input_method"));
        this.mDefaultGravityHorizontal = getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        this.mDefaultGravityVertical = getGravity() & 112;
        this.mNativeEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mBlurOnSubmit = null;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = getInputType();
        if (this.mKeyListener == null) {
            this.mKeyListener = new InternalKeyListener();
        }
        this.mScrollWatcher = null;
        this.mTextAttributes = new TextAttributes();
        applyTextAttributes();
        if (Build.VERSION.SDK_INT >= 26 && Build.VERSION.SDK_INT <= 27) {
            setLayerType(1, null);
        }
        ViewCompat.setAccessibilityDelegate(this, new ReactAccessibilityDelegate(this, isFocusable(), getImportantForAccessibility()) { // from class: com.facebook.react.views.textinput.ReactEditText.1
            @Override // com.facebook.react.uimanager.ReactAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view, int r3, Bundle bundle) {
                if (r3 == 16) {
                    int length = ReactEditText.this.getText().length();
                    if (length > 0) {
                        ReactEditText.this.setSelection(length);
                    }
                    return ReactEditText.this.requestFocusInternal();
                }
                return super.performAccessibilityAction(view, r3, bundle);
            }
        });
    }

    protected void finalize() {
        TextLayoutManager.deleteCachedSpannableForTag(getId());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        onContentSizeChange();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mDetectScrollMovement = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2 && this.mDetectScrollMovement) {
            if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.mDetectScrollMovement = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent keyEvent) {
        if (r2 == 66 && !isMultiline()) {
            hideSoftKeyboard();
            return true;
        }
        return super.onKeyUp(r2, keyEvent);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onScrollChanged(int r2, int r3, int r4, int r5) {
        super.onScrollChanged(r2, r3, r4, r5);
        ScrollWatcher scrollWatcher = this.mScrollWatcher;
        if (scrollWatcher != null) {
            scrollWatcher.onScrollChanged(r2, r3, r4, r5);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = UIManagerHelper.getReactContext(this);
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.mOnKeyPress) {
            onCreateInputConnection = new ReactEditTextInputConnectionWrapper(onCreateInputConnection, reactContext, this, this.mEventDispatcher);
        }
        if (isMultiline() && getBlurOnSubmit()) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    @Override // android.view.View
    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        hideSoftKeyboard();
    }

    @Override // android.view.View
    public boolean requestFocus(int r1, Rect rect) {
        return isFocused();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestFocusInternal() {
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(TsExtractor.TS_STREAM_TYPE_HDMV_DTS, null);
        if (getShowSoftInputOnFocus()) {
            showSoftKeyboard();
        }
        return requestFocus;
    }

    @Override // android.widget.TextView
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(textWatcher);
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setScrollWatcher(ScrollWatcher scrollWatcher) {
        this.mScrollWatcher = scrollWatcher;
    }

    public void maybeSetSelection(int r1, int r2, int r3) {
        if (!canUpdateWithEventCount(r1) || r2 == -1 || r3 == -1) {
            return;
        }
        setSelection(clampToTextLength(r2), clampToTextLength(r3));
    }

    private int clampToTextLength(int r3) {
        return Math.max(0, Math.min(r3, getText() == null ? 0 : getText().length()));
    }

    @Override // android.widget.EditText
    public void setSelection(int r1, int r2) {
        super.setSelection(r1, r2);
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int r2, int r3) {
        super.onSelectionChanged(r2, r3);
        if (this.mIsSettingTextFromCacheUpdate || this.mSelectionWatcher == null || !hasFocus()) {
            return;
        }
        this.mSelectionWatcher.onSelectionChanged(r2, r3);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int r2, Rect rect) {
        SelectionWatcher selectionWatcher;
        super.onFocusChanged(z, r2, rect);
        if (!z || (selectionWatcher = this.mSelectionWatcher) == null) {
            return;
        }
        selectionWatcher.onSelectionChanged(getSelectionStart(), getSelectionEnd());
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    public void setBlurOnSubmit(Boolean bool) {
        this.mBlurOnSubmit = bool;
    }

    public void setOnKeyPress(boolean z) {
        this.mOnKeyPress = z;
    }

    public boolean getBlurOnSubmit() {
        Boolean bool = this.mBlurOnSubmit;
        if (bool == null) {
            return !isMultiline();
        }
        return bool.booleanValue();
    }

    public void setDisableFullscreenUI(boolean z) {
        this.mDisableFullscreen = z;
        updateImeOptions();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    public void setReturnKeyType(String str) {
        this.mReturnKeyType = str;
        updateImeOptions();
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStagedInputType() {
        return this.mStagedInputType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedInputType(int r1) {
        this.mStagedInputType = r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commitStagedInputType() {
        if (getInputType() != this.mStagedInputType) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            setInputType(this.mStagedInputType);
            setSelection(selectionStart, selectionEnd);
        }
    }

    @Override // android.widget.TextView
    public void setInputType(int r2) {
        Typeface typeface = super.getTypeface();
        super.setInputType(r2);
        this.mStagedInputType = r2;
        super.setTypeface(typeface);
        if (isMultiline()) {
            setSingleLine(false);
        }
        if (this.mKeyListener == null) {
            this.mKeyListener = new InternalKeyListener();
        }
        this.mKeyListener.setInputType(r2);
        setKeyListener(this.mKeyListener);
    }

    public void setFontFamily(String str) {
        this.mFontFamily = str;
        this.mTypefaceDirty = true;
    }

    public void setFontWeight(String str) {
        int parseFontWeight = ReactTypefaceUtils.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            this.mTypefaceDirty = true;
        }
    }

    public void setFontStyle(String str) {
        int parseFontStyle = ReactTypefaceUtils.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            this.mTypefaceDirty = true;
        }
    }

    public void maybeUpdateTypeface() {
        if (this.mTypefaceDirty) {
            this.mTypefaceDirty = false;
            setTypeface(ReactTypefaceUtils.applyStyles(getTypeface(), this.mFontStyle, this.mFontWeight, this.mFontFamily, getContext().getAssets()));
        }
    }

    public void requestFocusFromJS() {
        requestFocusInternal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFocusFromJS() {
        clearFocus();
    }

    public int incrementAndGetEventCounter() {
        int r0 = this.mNativeEventCount + 1;
        this.mNativeEventCount = r0;
        return r0;
    }

    public void maybeSetTextFromJS(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromJS = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromJS = false;
    }

    public void maybeSetTextFromState(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromState = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromState = false;
    }

    public boolean canUpdateWithEventCount(int r2) {
        return r2 >= this.mNativeEventCount;
    }

    public void maybeSetText(ReactTextUpdate reactTextUpdate) {
        if (!(isSecureText() && TextUtils.equals(getText(), reactTextUpdate.getText())) && canUpdateWithEventCount(reactTextUpdate.getJsEventCounter())) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.getText());
            manageSpans(spannableStringBuilder, reactTextUpdate.mContainsMultipleFragments);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mDisableTextDiffing = true;
            if (reactTextUpdate.getText().length() == 0) {
                setText((CharSequence) null);
            } else {
                getText().replace(0, length(), spannableStringBuilder);
            }
            this.mDisableTextDiffing = false;
            if (Build.VERSION.SDK_INT >= 23 && getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
            updateCachedSpannable(false);
        }
    }

    private void manageSpans(SpannableStringBuilder spannableStringBuilder, boolean z) {
        Object[] spans;
        for (Object obj : getText().getSpans(0, length(), Object.class)) {
            int spanFlags = getText().getSpanFlags(obj);
            boolean z2 = (spanFlags & 33) == 33;
            if (obj instanceof ReactSpan) {
                getText().removeSpan(obj);
            }
            if (z2) {
                int spanStart = getText().getSpanStart(obj);
                int spanEnd = getText().getSpanEnd(obj);
                getText().removeSpan(obj);
                if (sameTextForSpan(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(obj, spanStart, spanEnd, spanFlags);
                }
            }
        }
        if (z) {
            return;
        }
        addSpansForMeasurement(getText());
    }

    private static boolean sameTextForSpan(Editable editable, SpannableStringBuilder spannableStringBuilder, int r5, int r6) {
        if (r5 > spannableStringBuilder.length() || r6 > spannableStringBuilder.length()) {
            return false;
        }
        while (r5 < r6) {
            if (editable.charAt(r5) != spannableStringBuilder.charAt(r5)) {
                return false;
            }
            r5++;
        }
        return true;
    }

    private void addSpansForMeasurement(Spannable spannable) {
        Object[] spans;
        if (this.mFabricViewStateManager.hasStateWrapper()) {
            boolean z = this.mDisableTextDiffing;
            this.mDisableTextDiffing = true;
            int length = spannable.length();
            int r5 = 0;
            for (Object obj : spannable.getSpans(0, length(), Object.class)) {
                int spanFlags = spannable.getSpanFlags(obj);
                if (((spanFlags & 18) == 18 || (spanFlags & 17) == 17) && (obj instanceof ReactSpan) && spannable.getSpanStart(obj) == 0 && spannable.getSpanEnd(obj) == length) {
                    spannable.removeSpan(obj);
                }
            }
            ArrayList<TextLayoutManager.SetSpanOperation> arrayList = new ArrayList();
            if (!Float.isNaN(this.mTextAttributes.getLetterSpacing())) {
                arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomLetterSpacingSpan(this.mTextAttributes.getLetterSpacing())));
            }
            arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new ReactAbsoluteSizeSpan(this.mTextAttributes.getEffectiveFontSize())));
            if (this.mFontStyle != -1 || this.mFontWeight != -1 || this.mFontFamily != null) {
                arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomStyleSpan(this.mFontStyle, this.mFontWeight, null, this.mFontFamily, UIManagerHelper.getReactContext(this).getAssets())));
            }
            if (!Float.isNaN(this.mTextAttributes.getEffectiveLineHeight())) {
                arrayList.add(new TextLayoutManager.SetSpanOperation(0, length, new CustomLineHeightSpan(this.mTextAttributes.getEffectiveLineHeight())));
            }
            for (TextLayoutManager.SetSpanOperation setSpanOperation : arrayList) {
                setSpanOperation.execute(spannable, r5);
                r5++;
            }
            this.mDisableTextDiffing = z;
        }
    }

    protected boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput(this, 0);
    }

    protected void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator();
        }
        return this.mTextWatcherDelegator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    private boolean isSecureText() {
        return (getInputType() & 144) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContentSizeChange() {
        ContentSizeWatcher contentSizeWatcher = this.mContentSizeWatcher;
        if (contentSizeWatcher != null) {
            contentSizeWatcher.onLayout();
        }
        setIntrinsicContentSize();
    }

    private void setIntrinsicContentSize() {
        ReactContext reactContext = UIManagerHelper.getReactContext(this);
        FabricViewStateManager fabricViewStateManager = this.mFabricViewStateManager;
        if (fabricViewStateManager == null || fabricViewStateManager.hasStateWrapper() || reactContext.isBridgeless()) {
            return;
        }
        ReactTextInputLocalData reactTextInputLocalData = new ReactTextInputLocalData(this);
        UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null) {
            uIManagerModule.setViewLocalData(getId(), reactTextInputLocalData);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGravityHorizontal(int r3) {
        if (r3 == 0) {
            r3 = this.mDefaultGravityHorizontal;
        }
        setGravity(r3 | (getGravity() & (-8) & (-8388616)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGravityVertical(int r2) {
        if (r2 == 0) {
            r2 = this.mDefaultGravityVertical;
        }
        setGravity(r2 | (getGravity() & (-113)));
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateImeOptions() {
        /*
            r9 = this;
            java.lang.String r0 = r9.mReturnKeyType
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 6
            if (r0 == 0) goto L70
            r0.hashCode()
            r7 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case -1273775369: goto L58;
                case -906336856: goto L4d;
                case 3304: goto L42;
                case 3089282: goto L37;
                case 3377907: goto L2c;
                case 3387192: goto L21;
                case 3526536: goto L16;
                default: goto L15;
            }
        L15:
            goto L62
        L16:
            java.lang.String r8 = "send"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L1f
            goto L62
        L1f:
            r7 = 6
            goto L62
        L21:
            java.lang.String r8 = "none"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L2a
            goto L62
        L2a:
            r7 = 5
            goto L62
        L2c:
            java.lang.String r8 = "next"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L35
            goto L62
        L35:
            r7 = 4
            goto L62
        L37:
            java.lang.String r8 = "done"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L40
            goto L62
        L40:
            r7 = 3
            goto L62
        L42:
            java.lang.String r8 = "go"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L4b
            goto L62
        L4b:
            r7 = 2
            goto L62
        L4d:
            java.lang.String r8 = "search"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L56
            goto L62
        L56:
            r7 = 1
            goto L62
        L58:
            java.lang.String r8 = "previous"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L61
            goto L62
        L61:
            r7 = 0
        L62:
            switch(r7) {
                case 0: goto L6e;
                case 1: goto L6c;
                case 2: goto L6a;
                case 3: goto L70;
                case 4: goto L71;
                case 5: goto L68;
                case 6: goto L66;
                default: goto L65;
            }
        L65:
            goto L70
        L66:
            r1 = 4
            goto L71
        L68:
            r1 = 1
            goto L71
        L6a:
            r1 = 2
            goto L71
        L6c:
            r1 = 3
            goto L71
        L6e:
            r1 = 7
            goto L71
        L70:
            r1 = 6
        L71:
            boolean r0 = r9.mDisableFullscreen
            if (r0 == 0) goto L7c
            r0 = 33554432(0x2000000, float:9.403955E-38)
            r0 = r0 | r1
            r9.setImeOptions(r0)
            goto L7f
        L7c:
            r9.setImeOptions(r1)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactEditText.updateImeOptions():void");
    }

    @Override // android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (textInlineImageSpan.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onDetachedFromWindow();
            }
        }
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onStartTemporaryDetach();
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        super.setTextIsSelectable(true);
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onAttachedToWindow();
            }
        }
        if (this.mAutoFocus && !this.mDidAttachToWindow) {
            requestFocusInternal();
        }
        this.mDidAttachToWindow = true;
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan textInlineImageSpan : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                textInlineImageSpan.onFinishTemporaryDetach();
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.mReactBackgroundManager.setBackgroundColor(r2);
    }

    public void setBorderWidth(int r2, float f) {
        this.mReactBackgroundManager.setBorderWidth(r2, f);
    }

    public void setBorderColor(int r2, float f, float f2) {
        this.mReactBackgroundManager.setBorderColor(r2, f, f2);
    }

    public int getBorderColor(int r2) {
        return this.mReactBackgroundManager.getBorderColor(r2);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderRadius(float f, int r3) {
        this.mReactBackgroundManager.setBorderRadius(f, r3);
    }

    public void setBorderStyle(String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setLetterSpacingPt(float f) {
        this.mTextAttributes.setLetterSpacing(f);
        applyTextAttributes();
    }

    public void setAllowFontScaling(boolean z) {
        if (this.mTextAttributes.getAllowFontScaling() != z) {
            this.mTextAttributes.setAllowFontScaling(z);
            applyTextAttributes();
        }
    }

    public void setFontSize(float f) {
        this.mTextAttributes.setFontSize(f);
        applyTextAttributes();
    }

    public void setMaxFontSizeMultiplier(float f) {
        if (f != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f);
            applyTextAttributes();
        }
    }

    public void setAutoFocus(boolean z) {
        this.mAutoFocus = z;
    }

    protected void applyTextAttributes() {
        setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
        float effectiveLetterSpacing = this.mTextAttributes.getEffectiveLetterSpacing();
        if (Float.isNaN(effectiveLetterSpacing)) {
            return;
        }
        setLetterSpacing(effectiveLetterSpacing);
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCachedSpannable(boolean z) {
        FabricViewStateManager fabricViewStateManager = this.mFabricViewStateManager;
        if (fabricViewStateManager == null || !fabricViewStateManager.hasStateWrapper() || getId() == -1) {
            return;
        }
        boolean z2 = true;
        if (z) {
            this.mIsSettingTextFromCacheUpdate = true;
            addSpansForMeasurement(getText());
            this.mIsSettingTextFromCacheUpdate = false;
        }
        Editable text = getText();
        z2 = (text == null || text.length() <= 0) ? false : false;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (z2) {
            try {
                spannableStringBuilder.append(text.subSequence(0, text.length()));
            } catch (IndexOutOfBoundsException e) {
                ReactSoftExceptionLogger.logSoftException(this.TAG, e);
            }
        }
        if (!z2) {
            if (getHint() != null && getHint().length() > 0) {
                spannableStringBuilder.append(getHint());
            } else {
                spannableStringBuilder.append((CharSequence) "I");
            }
            addSpansForMeasurement(spannableStringBuilder);
        }
        TextLayoutManager.setCachedSpannabledForTag(getId(), spannableStringBuilder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.mEventDispatcher = eventDispatcher;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TextWatcherDelegator implements TextWatcher {
        private TextWatcherDelegator() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int r4, int r5, int r6) {
            if (ReactEditText.this.mIsSettingTextFromCacheUpdate || ReactEditText.this.mIsSettingTextFromJS || ReactEditText.this.mListeners == null) {
                return;
            }
            Iterator it = ReactEditText.this.mListeners.iterator();
            while (it.hasNext()) {
                ((TextWatcher) it.next()).beforeTextChanged(charSequence, r4, r5, r6);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int r4, int r5, int r6) {
            if (!ReactEditText.this.mIsSettingTextFromCacheUpdate) {
                if (!ReactEditText.this.mIsSettingTextFromJS && ReactEditText.this.mListeners != null) {
                    Iterator it = ReactEditText.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((TextWatcher) it.next()).onTextChanged(charSequence, r4, r5, r6);
                    }
                }
                ReactEditText reactEditText = ReactEditText.this;
                reactEditText.updateCachedSpannable(!reactEditText.mIsSettingTextFromJS && !ReactEditText.this.mIsSettingTextFromState && r4 == 0 && r5 == 0);
            }
            ReactEditText.this.onContentSizeChange();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (ReactEditText.this.mIsSettingTextFromCacheUpdate || ReactEditText.this.mIsSettingTextFromJS || ReactEditText.this.mListeners == null) {
                return;
            }
            Iterator it = ReactEditText.this.mListeners.iterator();
            while (it.hasNext()) {
                ((TextWatcher) it.next()).afterTextChanged(editable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InternalKeyListener implements KeyListener {
        private int mInputType = 0;

        public void setInputType(int r1) {
            this.mInputType = r1;
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return this.mInputType;
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyDown(View view, Editable editable, int r4, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyDown(view, editable, r4, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyUp(View view, Editable editable, int r4, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyUp(view, editable, r4, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyOther(view, editable, keyEvent);
        }

        @Override // android.text.method.KeyListener
        public void clearMetaKeyState(View view, Editable editable, int r4) {
            ReactEditText.sKeyListener.clearMetaKeyState(view, editable, r4);
        }
    }
}
