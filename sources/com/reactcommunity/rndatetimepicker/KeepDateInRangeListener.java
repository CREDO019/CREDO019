package com.reactcommunity.rndatetimepicker;

import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class KeepDateInRangeListener implements DatePicker.OnDateChangedListener {
    private final Bundle args;

    public KeepDateInRangeListener(Bundle bundle) {
        this.args = bundle;
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker datePicker, int r2, int r3, int r4) {
        fixPotentialMaxDateBug(datePicker, r2, r3, r4);
        fixPotentialMinDateBug(datePicker, r2, r3, r4);
    }

    private void fixPotentialMaxDateBug(DatePicker datePicker, int r3, int r4, int r5) {
        if (isDateAfterMaxDate(this.args, r3, r4, r5)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.args.getLong(RNConstants.ARG_MAXDATE));
            datePicker.updateDate(calendar.get(1), calendar.get(2), calendar.get(5));
        }
    }

    private void fixPotentialMinDateBug(DatePicker datePicker, int r3, int r4, int r5) {
        if (isDateBeforeMinDate(this.args, r3, r4, r5)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.args.getLong(RNConstants.ARG_MINDATE));
            datePicker.updateDate(calendar.get(1), calendar.get(2), calendar.get(5));
        }
    }

    public static boolean isDateAfterMaxDate(Bundle bundle, int r6, int r7, int r8) {
        if (bundle.containsKey(RNConstants.ARG_MAXDATE)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(bundle.getLong(RNConstants.ARG_MAXDATE));
            return r6 > calendar.get(1) || (r6 == calendar.get(1) && r7 > calendar.get(2)) || (r6 == calendar.get(1) && r7 == calendar.get(2) && r8 > calendar.get(5));
        }
        return false;
    }

    public static boolean isDateBeforeMinDate(Bundle bundle, int r6, int r7, int r8) {
        if (bundle.containsKey(RNConstants.ARG_MINDATE)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(bundle.getLong(RNConstants.ARG_MINDATE));
            return r6 < calendar.get(1) || (r6 == calendar.get(1) && r7 < calendar.get(2)) || (r6 == calendar.get(1) && r7 == calendar.get(2) && r8 < calendar.get(5));
        }
        return false;
    }
}
