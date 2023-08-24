package com.reactcommunity.rndatetimepicker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Calendar;

@ReactModule(name = DatePickerModule.NAME)
/* loaded from: classes3.dex */
public class DatePickerModule extends NativeModuleDatePickerSpec {
    public static final String NAME = "RNCDatePicker";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public DatePickerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* loaded from: classes3.dex */
    private class DatePickerDialogListener implements DatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener, DialogInterface.OnClickListener {
        private final Bundle mArgs;
        private final Promise mPromise;
        private boolean mPromiseResolved = false;

        public DatePickerDialogListener(Promise promise, Bundle bundle) {
            this.mPromise = promise;
            this.mArgs = bundle;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int r11, int r12, int r13) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", RNConstants.ACTION_DATE_SET);
            writableNativeMap.putInt("year", r11);
            writableNativeMap.putInt("month", r12);
            writableNativeMap.putInt("day", r13);
            if (KeepDateInRangeListener.isDateAfterMaxDate(this.mArgs, r11, r12, r13)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(this.mArgs.getLong(RNConstants.ARG_MAXDATE));
                writableNativeMap.putInt("year", calendar.get(1));
                writableNativeMap.putInt("month", calendar.get(2));
                writableNativeMap.putInt("day", calendar.get(5));
            }
            if (KeepDateInRangeListener.isDateBeforeMinDate(this.mArgs, r11, r12, r13)) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(this.mArgs.getLong(RNConstants.ARG_MINDATE));
                writableNativeMap.putInt("year", calendar2.get(1));
                writableNativeMap.putInt("month", calendar2.get(2));
                writableNativeMap.putInt("day", calendar2.get(5));
            }
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", RNConstants.ACTION_DISMISSED);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int r3) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", RNConstants.ACTION_NEUTRAL_BUTTON);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }

    @Override // com.reactcommunity.rndatetimepicker.NativeModuleDatePickerSpec
    @ReactMethod
    public void dismiss(Promise promise) {
        Common.dismissDialog((FragmentActivity) getCurrentActivity(), NAME, promise);
    }

    @Override // com.reactcommunity.rndatetimepicker.NativeModuleDatePickerSpec
    @ReactMethod
    public void open(final ReadableMap readableMap, final Promise promise) {
        FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
        if (fragmentActivity == null) {
            promise.reject(RNConstants.ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to an Activity");
            return;
        }
        final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.reactcommunity.rndatetimepicker.DatePickerModule.1
            @Override // java.lang.Runnable
            public void run() {
                RNDatePickerDialogFragment rNDatePickerDialogFragment = (RNDatePickerDialogFragment) supportFragmentManager.findFragmentByTag(DatePickerModule.NAME);
                if (rNDatePickerDialogFragment != null) {
                    rNDatePickerDialogFragment.update(DatePickerModule.this.createFragmentArguments(readableMap));
                    return;
                }
                RNDatePickerDialogFragment rNDatePickerDialogFragment2 = new RNDatePickerDialogFragment();
                rNDatePickerDialogFragment2.setArguments(DatePickerModule.this.createFragmentArguments(readableMap));
                DatePickerModule datePickerModule = DatePickerModule.this;
                DatePickerDialogListener datePickerDialogListener = new DatePickerDialogListener(promise, datePickerModule.createFragmentArguments(readableMap));
                rNDatePickerDialogFragment2.setOnDismissListener(datePickerDialogListener);
                rNDatePickerDialogFragment2.setOnDateSetListener(datePickerDialogListener);
                rNDatePickerDialogFragment2.setOnNeutralButtonActionListener(datePickerDialogListener);
                rNDatePickerDialogFragment2.show(supportFragmentManager, DatePickerModule.NAME);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundle = new Bundle();
        if (readableMap.hasKey("value") && !readableMap.isNull("value")) {
            bundle.putLong("value", (long) readableMap.getDouble("value"));
        }
        if (readableMap.hasKey(RNConstants.ARG_MINDATE) && !readableMap.isNull(RNConstants.ARG_MINDATE)) {
            bundle.putLong(RNConstants.ARG_MINDATE, (long) readableMap.getDouble(RNConstants.ARG_MINDATE));
        }
        if (readableMap.hasKey(RNConstants.ARG_MAXDATE) && !readableMap.isNull(RNConstants.ARG_MAXDATE)) {
            bundle.putLong(RNConstants.ARG_MAXDATE, (long) readableMap.getDouble(RNConstants.ARG_MAXDATE));
        }
        if (readableMap.hasKey("display") && !readableMap.isNull("display")) {
            bundle.putString("display", readableMap.getString("display"));
        }
        if (readableMap.hasKey(RNConstants.ARG_DIALOG_BUTTONS) && !readableMap.isNull(RNConstants.ARG_DIALOG_BUTTONS)) {
            bundle.putBundle(RNConstants.ARG_DIALOG_BUTTONS, Arguments.toBundle(readableMap.getMap(RNConstants.ARG_DIALOG_BUTTONS)));
        }
        if (readableMap.hasKey(RNConstants.ARG_TZOFFSET_MINS) && !readableMap.isNull(RNConstants.ARG_TZOFFSET_MINS)) {
            bundle.putLong(RNConstants.ARG_TZOFFSET_MINS, (long) readableMap.getDouble(RNConstants.ARG_TZOFFSET_MINS));
        }
        if (readableMap.hasKey("testID") && !readableMap.isNull("testID")) {
            bundle.putString("testID", readableMap.getString("testID"));
        }
        return bundle;
    }
}
