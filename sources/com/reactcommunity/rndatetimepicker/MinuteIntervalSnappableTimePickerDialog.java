package com.reactcommunity.rndatetimepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import com.amplitude.api.DeviceInfo;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class MinuteIntervalSnappableTimePickerDialog extends TimePickerDialog {
    private Handler handler;
    private Context mContext;
    private RNTimePickerDisplay mDisplay;
    private TimePicker mTimePicker;
    private int mTimePickerInterval;
    private final TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private Runnable runnable;

    public MinuteIntervalSnappableTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int r9, int r10, int r11, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, onTimeSetListener, r9, r10, z);
        this.handler = new Handler();
        this.mTimePickerInterval = r11;
        this.mTimeSetListener = onTimeSetListener;
        this.mDisplay = rNTimePickerDisplay;
        this.mContext = context;
    }

    public MinuteIntervalSnappableTimePickerDialog(Context context, int r9, TimePickerDialog.OnTimeSetListener onTimeSetListener, int r11, int r12, int r13, boolean z, RNTimePickerDisplay rNTimePickerDisplay) {
        super(context, r9, onTimeSetListener, r11, r12, z);
        this.handler = new Handler();
        this.mTimePickerInterval = r13;
        this.mTimeSetListener = onTimeSetListener;
        this.mDisplay = rNTimePickerDisplay;
        this.mContext = context;
    }

    public static boolean isValidMinuteInterval(int r2) {
        return r2 >= 1 && r2 <= 30 && 60 % r2 == 0;
    }

    private boolean timePickerHasCustomMinuteInterval() {
        return this.mTimePickerInterval != 1;
    }

    private boolean isSpinner() {
        return this.mDisplay == RNTimePickerDisplay.SPINNER;
    }

    private int getRealMinutes(int r2) {
        return isSpinner() ? r2 * this.mTimePickerInterval : r2;
    }

    private int getRealMinutes() {
        return getRealMinutes(this.mTimePicker.getCurrentMinute().intValue());
    }

    private int snapRealMinutesToInterval(int r3) {
        int round = Math.round(r3 / this.mTimePickerInterval);
        int r0 = this.mTimePickerInterval;
        int r32 = round * r0;
        return r32 == 60 ? r32 - r0 : r32;
    }

    private void assertNotSpinner(String str) {
        if (isSpinner()) {
            throw new RuntimeException(str);
        }
    }

    private boolean minutesNeedCorrection(int r2) {
        assertNotSpinner("minutesNeedCorrection is not intended to be used with spinner, spinner won't allow picking invalid values");
        return timePickerHasCustomMinuteInterval() && r2 != snapRealMinutesToInterval(r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pickerIsInTextInputMode() {
        View findViewById = findViewById(this.mContext.getResources().getIdentifier("input_mode", "id", DeviceInfo.OS_NAME));
        return findViewById != null && findViewById.hasFocus();
    }

    private void correctEnteredMinutes(final TimePicker timePicker, final int r3, final int r4) {
        assertNotSpinner("spinner never needs to be corrected because wrong values are not offered to user (both in scrolling and textInput mode)!");
        Runnable runnable = new Runnable() { // from class: com.reactcommunity.rndatetimepicker.MinuteIntervalSnappableTimePickerDialog.1
            @Override // java.lang.Runnable
            public void run() {
                if (MinuteIntervalSnappableTimePickerDialog.this.pickerIsInTextInputMode()) {
                    if (r4 > 5) {
                        fixTime();
                        moveCursorToEnd();
                        return;
                    }
                    return;
                }
                fixTime();
            }

            private void fixTime() {
                if (Build.VERSION.SDK_INT >= 23) {
                    timePicker.setHour(r3);
                    timePicker.setMinute(r4);
                    return;
                }
                timePicker.setCurrentHour(Integer.valueOf(r3));
                timePicker.setCurrentMinute(0);
                timePicker.setCurrentMinute(Integer.valueOf(r4));
            }

            private void moveCursorToEnd() {
                View findFocus = timePicker.findFocus();
                if (findFocus instanceof EditText) {
                    EditText editText = (EditText) findFocus;
                    editText.setSelection(editText.getText().length());
                    return;
                }
                Log.e("RN-datetimepicker", "could not set selection on time picker, this is a known issue on some Huawei devices");
            }
        };
        this.runnable = runnable;
        this.handler.postDelayed(runnable, 500L);
    }

    @Override // android.app.TimePickerDialog, android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int r5, int r6) {
        int realMinutes = getRealMinutes(r6);
        this.handler.removeCallbacks(this.runnable);
        if (!isSpinner() && minutesNeedCorrection(realMinutes)) {
            correctEnteredMinutes(timePicker, r5, snapRealMinutesToInterval(realMinutes));
        } else {
            super.onTimeChanged(timePicker, r5, r6);
        }
    }

    @Override // android.app.TimePickerDialog, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int r5) {
        boolean z = timePickerHasCustomMinuteInterval() || isSpinner();
        TimePicker timePicker = this.mTimePicker;
        if (timePicker != null && r5 == -1 && z) {
            timePicker.clearFocus();
            int intValue = this.mTimePicker.getCurrentHour().intValue();
            int realMinutes = getRealMinutes();
            if (timePickerHasCustomMinuteInterval()) {
                realMinutes = snapRealMinutesToInterval(realMinutes);
            }
            TimePickerDialog.OnTimeSetListener onTimeSetListener = this.mTimeSetListener;
            if (onTimeSetListener != null) {
                onTimeSetListener.onTimeSet(this.mTimePicker, intValue, realMinutes);
                return;
            }
            return;
        }
        super.onClick(dialogInterface, r5);
    }

    @Override // android.app.TimePickerDialog
    public void updateTime(int r2, int r3) {
        if (timePickerHasCustomMinuteInterval()) {
            if (isSpinner()) {
                super.updateTime(r2, snapRealMinutesToInterval(getRealMinutes()) / this.mTimePickerInterval);
                return;
            } else {
                super.updateTime(r2, snapRealMinutesToInterval(r3));
                return;
            }
        }
        super.updateTime(r2, r3);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mTimePicker = (TimePicker) findViewById(this.mContext.getResources().getIdentifier("timePicker", "id", DeviceInfo.OS_NAME));
        if (timePickerHasCustomMinuteInterval()) {
            setupPickerDialog();
        }
    }

    private void setupPickerDialog() {
        TimePicker timePicker = this.mTimePicker;
        if (timePicker == null) {
            Log.e("RN-datetimepicker", "time picker was null");
            return;
        }
        int intValue = timePicker.getCurrentMinute().intValue();
        if (isSpinner()) {
            setSpinnerDisplayedValues();
            this.mTimePicker.setCurrentMinute(Integer.valueOf(snapRealMinutesToInterval(intValue) / this.mTimePickerInterval));
            return;
        }
        this.mTimePicker.setCurrentMinute(Integer.valueOf(snapRealMinutesToInterval(intValue)));
    }

    private void setSpinnerDisplayedValues() {
        NumberPicker numberPicker = (NumberPicker) findViewById(this.mContext.getResources().getIdentifier("minute", "id", DeviceInfo.OS_NAME));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue((60 / this.mTimePickerInterval) - 1);
        ArrayList arrayList = new ArrayList(60 / this.mTimePickerInterval);
        int r5 = 0;
        while (r5 < 60) {
            arrayList.add(String.format("%02d", Integer.valueOf(r5)));
            r5 += this.mTimePickerInterval;
        }
        numberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.handler.removeCallbacks(this.runnable);
        super.onDetachedFromWindow();
    }
}
