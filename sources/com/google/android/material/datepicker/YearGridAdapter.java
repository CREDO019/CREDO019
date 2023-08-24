package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.C2151R;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final MaterialCalendar<?> materialCalendar;

    /* loaded from: classes3.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public YearGridAdapter(MaterialCalendar<?> materialCalendar) {
        this.materialCalendar = materialCalendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int r4) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(C2151R.layout.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int r9) {
        int yearForPosition = getYearForPosition(r9);
        String string = viewHolder.textView.getContext().getString(C2151R.string.mtrl_picker_navigate_to_year_description);
        viewHolder.textView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(yearForPosition)));
        viewHolder.textView.setContentDescription(String.format(string, Integer.valueOf(yearForPosition)));
        CalendarStyle calendarStyle = this.materialCalendar.getCalendarStyle();
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        CalendarItemStyle calendarItemStyle = todayCalendar.get(1) == yearForPosition ? calendarStyle.todayYear : calendarStyle.year;
        for (Long l : this.materialCalendar.getDateSelector().getSelectedDays()) {
            todayCalendar.setTimeInMillis(l.longValue());
            if (todayCalendar.get(1) == yearForPosition) {
                calendarItemStyle = calendarStyle.selectedYear;
            }
        }
        calendarItemStyle.styleItem(viewHolder.textView);
        viewHolder.textView.setOnClickListener(createYearClickListener(yearForPosition));
    }

    private View.OnClickListener createYearClickListener(final int r2) {
        return new View.OnClickListener() { // from class: com.google.android.material.datepicker.YearGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YearGridAdapter.this.materialCalendar.setCurrentMonth(Month.create(r2, YearGridAdapter.this.materialCalendar.getCurrentMonth().month));
                YearGridAdapter.this.materialCalendar.setSelector(MaterialCalendar.CalendarSelector.DAY);
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.materialCalendar.getCalendarConstraints().getYearSpan();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPositionForYear(int r2) {
        return r2 - this.materialCalendar.getCalendarConstraints().getStart().year;
    }

    int getYearForPosition(int r2) {
        return this.materialCalendar.getCalendarConstraints().getStart().year + r2;
    }
}