package com.reactcommunity.rndatetimepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.DatePicker;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class RNDismissableDatePickerDialog extends DatePickerDialog {
    public RNDismissableDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener, int r9, int r10, int r11, RNDatePickerDisplay rNDatePickerDisplay) {
        super(context, onDateSetListener, r9, r10, r11);
        fixSpinner(context, r9, r10, r11, rNDatePickerDisplay);
    }

    public RNDismissableDatePickerDialog(Context context, int r8, DatePickerDialog.OnDateSetListener onDateSetListener, int r10, int r11, int r12, RNDatePickerDisplay rNDatePickerDisplay) {
        super(context, r8, onDateSetListener, r10, r11, r12);
        fixSpinner(context, r10, r11, r12, rNDatePickerDisplay);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        if (Build.VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }

    private void fixSpinner(Context context, int r18, int r19, int r20, RNDatePickerDisplay rNDatePickerDisplay) {
        if (Build.VERSION.SDK_INT == 24 && rNDatePickerDisplay == RNDatePickerDisplay.SPINNER) {
            try {
                context.obtainStyledAttributes(null, (int[]) Class.forName("com.android.internal.R$styleable").getField("DatePicker").get(null), 16843612, 0).recycle();
                DatePicker datePicker = (DatePicker) ReflectionHelper.findField(DatePickerDialog.class, DatePicker.class, "mDatePicker").get(this);
                Field findField = ReflectionHelper.findField(DatePicker.class, Class.forName("android.widget.DatePickerSpinnerDelegate"), "mDelegate");
                if (findField.get(datePicker).getClass() != Class.forName("android.widget.DatePickerSpinnerDelegate")) {
                    findField.set(datePicker, null);
                    datePicker.removeAllViews();
                    Method declaredMethod = DatePicker.class.getDeclaredMethod("createSpinnerUIDelegate", Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    findField.set(datePicker, declaredMethod.invoke(datePicker, context, null, 16843612, 0));
                    datePicker.setCalendarViewShown(false);
                    datePicker.init(r18, r19, r20, this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (rNDatePickerDisplay != RNDatePickerDisplay.SPINNER || getDatePicker() == null) {
            return;
        }
        getDatePicker().setCalendarViewShown(false);
    }
}
