package com.reactcommunity.rndatetimepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TimePicker;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class RNDismissableTimePickerDialog extends MinuteIntervalSnappableTimePickerDialog {
    @Override // com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog, android.app.TimePickerDialog, android.content.DialogInterface.OnClickListener
    public /* bridge */ /* synthetic */ void onClick(DialogInterface dialogInterface, int r2) {
        super.onClick(dialogInterface, r2);
    }

    @Override // com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog, android.app.Dialog, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog, android.app.TimePickerDialog, android.widget.TimePicker.OnTimeChangedListener
    public /* bridge */ /* synthetic */ void onTimeChanged(TimePicker timePicker, int r2, int r3) {
        super.onTimeChanged(timePicker, r2, r3);
    }

    @Override // com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog, android.app.TimePickerDialog
    public /* bridge */ /* synthetic */ void updateTime(int r1, int r2) {
        super.updateTime(r1, r2);
    }

    public RNDismissableTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int r9, int r10, int r11, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, onTimeSetListener, r9, r10, r11, z, rNTimePickerDisplay);
        fixSpinner(context, r9, r10, z, rNTimePickerDisplay);
    }

    public RNDismissableTimePickerDialog(Context context, int r8, TimePickerDialog.OnTimeSetListener onTimeSetListener, int r10, int r11, int r12, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, r8, onTimeSetListener, r10, r11, r12, z, rNTimePickerDisplay);
        fixSpinner(context, r10, r11, z, rNTimePickerDisplay);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        if (Build.VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }

    private void fixSpinner(Context context, int r17, int r18, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        if (Build.VERSION.SDK_INT == 24 && rNTimePickerDisplay == RNTimePickerDisplay.SPINNER) {
            try {
                context.obtainStyledAttributes(null, (int[]) Class.forName("com.android.internal.R$styleable").getField("TimePicker").get(null), 16843933, 0).recycle();
                TimePicker timePicker = (TimePicker) ReflectionHelper.findField(TimePickerDialog.class, TimePicker.class, "mTimePicker").get(this);
                Field findField = ReflectionHelper.findField(TimePicker.class, Class.forName("android.widget.TimePicker$TimePickerDelegate"), "mDelegate");
                Object obj = findField.get(timePicker);
                Class<?> cls = Class.forName("android.widget.TimePickerSpinnerDelegate");
                if (obj.getClass() != cls) {
                    findField.set(timePicker, null);
                    timePicker.removeAllViews();
                    Constructor<?> constructor = cls.getConstructor(TimePicker.class, Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE);
                    constructor.setAccessible(true);
                    findField.set(timePicker, constructor.newInstance(timePicker, context, null, 16843933, 0));
                    timePicker.setIs24HourView(Boolean.valueOf(z));
                    timePicker.setCurrentHour(Integer.valueOf(r17));
                    timePicker.setCurrentMinute(Integer.valueOf(r18));
                    timePicker.setOnTimeChangedListener(this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
