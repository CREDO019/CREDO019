package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SimpleCursorAdapter extends ResourceCursorAdapter {
    private CursorToStringConverter mCursorToStringConverter;
    protected int[] mFrom;
    String[] mOriginalFrom;
    private int mStringConversionColumn;
    protected int[] mTo;
    private ViewBinder mViewBinder;

    /* loaded from: classes.dex */
    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    /* loaded from: classes.dex */
    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int r3);
    }

    @Deprecated
    public SimpleCursorAdapter(Context context, int r2, Cursor cursor, String[] strArr, int[] r5) {
        super(context, r2, cursor);
        this.mStringConversionColumn = -1;
        this.mTo = r5;
        this.mOriginalFrom = strArr;
        findColumns(cursor, strArr);
    }

    public SimpleCursorAdapter(Context context, int r2, Cursor cursor, String[] strArr, int[] r5, int r6) {
        super(context, r2, cursor, r6);
        this.mStringConversionColumn = -1;
        this.mTo = r5;
        this.mOriginalFrom = strArr;
        findColumns(cursor, strArr);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ViewBinder viewBinder = this.mViewBinder;
        int[] r0 = this.mTo;
        int length = r0.length;
        int[] r2 = this.mFrom;
        for (int r4 = 0; r4 < length; r4++) {
            View findViewById = view.findViewById(r0[r4]);
            if (findViewById != null) {
                if (viewBinder != null ? viewBinder.setViewValue(findViewById, cursor, r2[r4]) : false) {
                    continue;
                } else {
                    String string = cursor.getString(r2[r4]);
                    if (string == null) {
                        string = "";
                    }
                    if (findViewById instanceof TextView) {
                        setViewText((TextView) findViewById, string);
                    } else if (findViewById instanceof ImageView) {
                        setViewImage((ImageView) findViewById, string);
                    } else {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                    }
                }
            }
        }
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public void setViewImage(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void setViewText(TextView textView, String str) {
        textView.setText(str);
    }

    public int getStringConversionColumn() {
        return this.mStringConversionColumn;
    }

    public void setStringConversionColumn(int r1) {
        this.mStringConversionColumn = r1;
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.mCursorToStringConverter;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursorToStringConverter) {
        this.mCursorToStringConverter = cursorToStringConverter;
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public CharSequence convertToString(Cursor cursor) {
        CursorToStringConverter cursorToStringConverter = this.mCursorToStringConverter;
        if (cursorToStringConverter != null) {
            return cursorToStringConverter.convertToString(cursor);
        }
        int r0 = this.mStringConversionColumn;
        if (r0 > -1) {
            return cursor.getString(r0);
        }
        return super.convertToString(cursor);
    }

    private void findColumns(Cursor cursor, String[] strArr) {
        if (cursor != null) {
            int length = strArr.length;
            int[] r1 = this.mFrom;
            if (r1 == null || r1.length != length) {
                this.mFrom = new int[length];
            }
            for (int r12 = 0; r12 < length; r12++) {
                this.mFrom[r12] = cursor.getColumnIndexOrThrow(strArr[r12]);
            }
            return;
        }
        this.mFrom = null;
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public Cursor swapCursor(Cursor cursor) {
        findColumns(cursor, this.mOriginalFrom);
        return super.swapCursor(cursor);
    }

    public void changeCursorAndColumns(Cursor cursor, String[] strArr, int[] r3) {
        this.mOriginalFrom = strArr;
        this.mTo = r3;
        findColumns(cursor, strArr);
        super.changeCursor(cursor);
    }
}
