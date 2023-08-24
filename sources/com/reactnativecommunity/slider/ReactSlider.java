package com.reactnativecommunity.slider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class ReactSlider extends AppCompatSeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private boolean isSliding;
    private List<String> mAccessibilityIncrements;
    private String mAccessibilityUnits;
    private int mLowerLimit;
    private double mMaxValue;
    private double mMinValue;
    private double mRealLowerLimit;
    private double mRealUpperLimit;
    private double mStep;
    private double mStepCalculated;
    private int mUpperLimit;
    private double mValue;

    public ReactSlider(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMinValue = 0.0d;
        this.mMaxValue = 0.0d;
        this.mValue = 0.0d;
        this.isSliding = false;
        this.mStep = 0.0d;
        this.mStepCalculated = 0.0d;
        this.mRealLowerLimit = -9.223372036854776E18d;
        this.mRealUpperLimit = 9.223372036854776E18d;
        super.setLayoutDirection(I18nUtil.getInstance().isRTL(context) ? 1 : 0);
        disableStateListAnimatorIfNeeded();
    }

    private void disableStateListAnimatorIfNeeded() {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 26) {
            return;
        }
        super.setStateListAnimator(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLowerLimit(double d) {
        this.mRealLowerLimit = d;
        updateLowerLimit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUpperLimit(double d) {
        this.mRealUpperLimit = d;
        updateUpperLimit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLowerLimit() {
        return this.mLowerLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getUpperLimit() {
        return this.mUpperLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSliding() {
        return this.isSliding;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void isSliding(boolean z) {
        this.isSliding = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityUnits(String str) {
        this.mAccessibilityUnits = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityIncrements(List<String> list) {
        this.mAccessibilityIncrements = list;
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        if (Build.VERSION.SDK_INT >= 21) {
            if (accessibilityEvent.getEventType() == 32768 || (accessibilityEvent.getEventType() == 4 && isAccessibilityFocused())) {
                setupAccessibility((int) this.mValue);
            }
        }
    }

    @Override // android.view.View
    public void announceForAccessibility(CharSequence charSequence) {
        Context context = getContext();
        final AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            final AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(16384);
            obtain.setClassName(getClass().getName());
            obtain.setPackageName(context.getPackageName());
            obtain.getText().add(charSequence);
            new Timer().schedule(new TimerTask() { // from class: com.reactnativecommunity.slider.ReactSlider.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    accessibilityManager.sendAccessibilityEvent(obtain);
                }
            }, 1000L);
        }
    }

    public void setupAccessibility(int r6) {
        List<String> list;
        if (this.mAccessibilityUnits == null || (list = this.mAccessibilityIncrements) == null || list.size() - 1 != ((int) this.mMaxValue)) {
            return;
        }
        String str = this.mAccessibilityIncrements.get(r6);
        int length = this.mAccessibilityUnits.length();
        String str2 = this.mAccessibilityUnits;
        if (str != null && Integer.parseInt(str) == 1) {
            str2 = str2.substring(0, length - 1);
        }
        announceForAccessibility(String.format("%s %s", str, str2));
    }

    public double toRealProgress(int r5) {
        if (r5 == getMax()) {
            return this.mMaxValue;
        }
        return (r5 * getStepValue()) + this.mMinValue;
    }

    private void updateAll() {
        if (this.mStep == 0.0d) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / DEFAULT_TOTAL_STEPS;
        }
        setMax(getTotalSteps());
        updateLowerLimit();
        updateUpperLimit();
        updateValue();
    }

    private void updateLowerLimit() {
        double max = Math.max(this.mRealLowerLimit, this.mMinValue);
        double d = this.mMinValue;
        this.mLowerLimit = (int) Math.round(((max - d) / (this.mMaxValue - d)) * getTotalSteps());
    }

    private void updateUpperLimit() {
        double min = Math.min(this.mRealUpperLimit, this.mMaxValue);
        double d = this.mMinValue;
        this.mUpperLimit = (int) Math.round(((min - d) / (this.mMaxValue - d)) * getTotalSteps());
    }

    private void updateValue() {
        double d = this.mValue;
        double d2 = this.mMinValue;
        setProgress((int) Math.round(((d - d2) / (this.mMaxValue - d2)) * getTotalSteps()));
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private double getStepValue() {
        double d = this.mStep;
        return d > 0.0d ? d : this.mStepCalculated;
    }

    private BitmapDrawable getBitmapDrawable(final String str) {
        try {
            return (BitmapDrawable) Executors.newSingleThreadExecutor().submit(new Callable<BitmapDrawable>() { // from class: com.reactnativecommunity.slider.ReactSlider.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public BitmapDrawable call() {
                    Bitmap decodeStream;
                    try {
                        if (!str.startsWith("http://") && !str.startsWith("https://") && !str.startsWith("file://") && !str.startsWith("asset://") && !str.startsWith("data:")) {
                            decodeStream = BitmapFactory.decodeResource(ReactSlider.this.getResources(), ReactSlider.this.getResources().getIdentifier(str, "drawable", ReactSlider.this.getContext().getPackageName()));
                            return new BitmapDrawable(ReactSlider.this.getResources(), decodeStream);
                        }
                        decodeStream = BitmapFactory.decodeStream(new URL(str).openStream());
                        return new BitmapDrawable(ReactSlider.this.getResources(), decodeStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setThumbImage(String str) {
        if (str != null) {
            setThumb(getBitmapDrawable(str));
            if (Build.VERSION.SDK_INT >= 21) {
                setSplitTrack(false);
                return;
            }
            return;
        }
        setThumb(getThumb());
    }
}
