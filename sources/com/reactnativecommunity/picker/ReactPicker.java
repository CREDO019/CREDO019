package com.reactnativecommunity.picker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.UIManagerModule;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class ReactPicker extends AppCompatSpinner {
    private boolean mIsOpen;
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    private int mMode;
    private int mOldElementSize;
    @Nullable
    private OnFocusListener mOnFocusListener;
    @Nullable
    private OnSelectListener mOnSelectListener;
    @Nullable
    private Integer mPrimaryColor;
    @Nullable
    private Integer mStagedSelection;
    private final Runnable measureAndLayout;

    /* loaded from: classes3.dex */
    public interface OnFocusListener {
        void onPickerBlur();

        void onPickerFocus();
    }

    /* loaded from: classes3.dex */
    public interface OnSelectListener {
        void onItemSelected(int r1);
    }

    public ReactPicker(Context context) {
        super(context);
        this.mMode = 0;
        this.mOldElementSize = Integer.MIN_VALUE;
        this.mIsOpen = false;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.reactnativecommunity.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int r3, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(r3);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, int r4) {
        super(context, r4);
        this.mMode = 0;
        this.mOldElementSize = Integer.MIN_VALUE;
        this.mIsOpen = false;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.reactnativecommunity.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int r3, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(r3);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = r4;
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 0;
        this.mOldElementSize = Integer.MIN_VALUE;
        this.mIsOpen = false;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.reactnativecommunity.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int r3, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(r3);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.mMode = 0;
        this.mOldElementSize = Integer.MIN_VALUE;
        this.mIsOpen = false;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.reactnativecommunity.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int r32, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(r32);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int r3, int r4) {
        super(context, attributeSet, r3, r4);
        this.mMode = 0;
        this.mOldElementSize = Integer.MIN_VALUE;
        this.mIsOpen = false;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.reactnativecommunity.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int r32, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(r32);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = r4;
        handleRTL(context);
        setSpinnerBackground();
    }

    private void setSpinnerBackground() {
        setBackgroundResource(C3996R.C3999drawable.spinner_dropdown_background);
        setBackgroundColor(0);
    }

    private void handleRTL(Context context) {
        if (I18nUtil.getInstance().isRTL(context)) {
            setLayoutDirection(1);
            setTextDirection(4);
            return;
        }
        setLayoutDirection(0);
        setTextDirection(3);
    }

    @Override // android.widget.AbsSpinner, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    @Override // androidx.appcompat.widget.AppCompatSpinner, android.widget.Spinner, android.view.View
    public boolean performClick() {
        this.mIsOpen = true;
        OnFocusListener onFocusListener = this.mOnFocusListener;
        if (onFocusListener != null) {
            onFocusListener.onPickerFocus();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        if (this.mIsOpen && z) {
            this.mIsOpen = false;
            OnFocusListener onFocusListener = this.mOnFocusListener;
            if (onFocusListener != null) {
                onFocusListener.onPickerBlur();
            }
        }
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        super.onLayout(z, r2, r3, r4, r5);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatSpinner, android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int r3, int r4) {
        int applyDimension;
        super.onMeasure(r3, r4);
        int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition < 0 || getAdapter() == null || selectedItemPosition >= getAdapter().getCount()) {
            applyDimension = (int) TypedValue.applyDimension(1, 50.0f, Resources.getSystem().getDisplayMetrics());
        } else {
            View view = getAdapter().getView(selectedItemPosition, null, this);
            measureChild(view, View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            applyDimension = view.getMeasuredHeight();
        }
        if (applyDimension != this.mOldElementSize) {
            UIManagerModule uIManagerModule = (UIManagerModule) getReactContext().getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), new ReactPickerLocalData(applyDimension));
            }
            this.mOldElementSize = applyDimension;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        super.setFocusableInTouchMode(true);
        super.setFocusable(true);
        super.onDetachedFromWindow();
    }

    public void setOnSelectListener(@Nullable OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setOnFocusListener(@Nullable OnFocusListener onFocusListener) {
        this.mOnFocusListener = onFocusListener;
    }

    @Nullable
    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    @Nullable
    public OnFocusListener getOnFocusListener() {
        return this.mOnFocusListener;
    }

    public void setStagedSelection(int r1) {
        this.mStagedSelection = Integer.valueOf(r1);
    }

    public void updateStagedSelection() {
        Integer num = this.mStagedSelection;
        if (num != null) {
            setSelectionWithSuppressEvent(num.intValue());
            this.mStagedSelection = null;
        }
    }

    private void setSelectionWithSuppressEvent(int r2) {
        if (r2 != getSelectedItemPosition()) {
            setOnItemSelectedListener(null);
            setSelection(r2, false);
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    @Nullable
    public Integer getPrimaryColor() {
        return this.mPrimaryColor;
    }

    public void setPrimaryColor(@Nullable Integer num) {
        this.mPrimaryColor = num;
    }

    public void setDropdownIconColor(@Nullable int r3) {
        ((RippleDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(C3996R.C4000id.dropdown_icon)).setColorFilter(r3, PorterDuff.Mode.SRC_ATOP);
    }

    public void setDropdownIconRippleColor(@Nullable int r3) {
        ((RippleDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(C3996R.C4000id.dropdown_icon)).setColor(ColorStateList.valueOf(r3));
    }

    @Override // android.view.View
    public void setBackgroundColor(@Nullable int r3) {
        ((GradientDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(C3996R.C4000id.dropdown_background)).setColor(r3);
    }

    public int getMode() {
        return this.mMode;
    }

    private ReactContext getReactContext() {
        Context context = getContext();
        if (!(context instanceof ReactContext) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }
}
