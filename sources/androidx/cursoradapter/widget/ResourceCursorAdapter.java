package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    @Deprecated
    public ResourceCursorAdapter(Context context, int r2, Cursor cursor) {
        super(context, cursor);
        this.mDropDownLayout = r2;
        this.mLayout = r2;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Deprecated
    public ResourceCursorAdapter(Context context, int r2, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.mDropDownLayout = r2;
        this.mLayout = r2;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int r2, Cursor cursor, int r4) {
        super(context, cursor, r4);
        this.mDropDownLayout = r2;
        this.mLayout = r2;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mLayout, viewGroup, false);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mDropDownLayout, viewGroup, false);
    }

    public void setViewResource(int r1) {
        this.mLayout = r1;
    }

    public void setDropDownViewResource(int r1) {
        this.mDropDownLayout = r1;
    }
}
