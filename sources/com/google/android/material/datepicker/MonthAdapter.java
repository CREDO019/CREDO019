package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.C2151R;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class MonthAdapter extends BaseAdapter {
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final Month month;

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MonthAdapter(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.month = month;
        this.dateSelector = dateSelector;
        this.calendarConstraints = calendarConstraints;
    }

    @Override // android.widget.Adapter
    public Long getItem(int r3) {
        if (r3 < this.month.daysFromStartOfWeekToFirstOfMonth() || r3 > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(r3)));
    }

    @Override // android.widget.Adapter
    public long getItemId(int r3) {
        return r3 / this.month.daysInWeek;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.month.daysInMonth + firstPositionInMonth();
    }

    @Override // android.widget.Adapter
    public TextView getView(int r6, View view, ViewGroup viewGroup) {
        initializeStyles(viewGroup.getContext());
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(C2151R.layout.mtrl_calendar_day, viewGroup, false);
        }
        int firstPositionInMonth = r6 - firstPositionInMonth();
        if (firstPositionInMonth < 0 || firstPositionInMonth >= this.month.daysInMonth) {
            textView.setVisibility(8);
            textView.setEnabled(false);
        } else {
            int r7 = firstPositionInMonth + 1;
            textView.setTag(this.month);
            textView.setText(String.valueOf(r7));
            long day = this.month.getDay(r7);
            if (this.month.year == Month.today().year) {
                textView.setContentDescription(DateStrings.getMonthDayOfWeekDay(day));
            } else {
                textView.setContentDescription(DateStrings.getYearMonthDayOfWeekDay(day));
            }
            textView.setVisibility(0);
            textView.setEnabled(true);
        }
        Long item = getItem(r6);
        if (item == null) {
            return textView;
        }
        if (this.calendarConstraints.getDateValidator().isValid(item.longValue())) {
            textView.setEnabled(true);
            Iterator<Long> it = this.dateSelector.getSelectedDays().iterator();
            while (it.hasNext()) {
                if (UtcDates.canonicalYearMonthDay(item.longValue()) == UtcDates.canonicalYearMonthDay(it.next().longValue())) {
                    this.calendarStyle.selectedDay.styleItem(textView);
                    return textView;
                }
            }
            if (UtcDates.getTodayCalendar().getTimeInMillis() == item.longValue()) {
                this.calendarStyle.todayDay.styleItem(textView);
                return textView;
            }
            this.calendarStyle.day.styleItem(textView);
            return textView;
        }
        textView.setEnabled(false);
        this.calendarStyle.invalidDay.styleItem(textView);
        return textView;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    int positionToDay(int r2) {
        return (r2 - this.month.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int dayToPosition(int r2) {
        return firstPositionInMonth() + (r2 - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean withinMonth(int r2) {
        return r2 >= firstPositionInMonth() && r2 <= lastPositionInMonth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFirstInRow(int r2) {
        return r2 % this.month.daysInWeek == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLastInRow(int r3) {
        return (r3 + 1) % this.month.daysInWeek == 0;
    }
}
