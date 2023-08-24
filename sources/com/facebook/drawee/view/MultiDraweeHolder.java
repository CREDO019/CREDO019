package com.facebook.drawee.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MultiDraweeHolder<DH extends DraweeHierarchy> {
    boolean mIsAttached = false;
    ArrayList<DraweeHolder<DH>> mHolders = new ArrayList<>();

    public void onAttach() {
        if (this.mIsAttached) {
            return;
        }
        this.mIsAttached = true;
        for (int r0 = 0; r0 < this.mHolders.size(); r0++) {
            this.mHolders.get(r0).onAttach();
        }
    }

    public void onDetach() {
        if (this.mIsAttached) {
            this.mIsAttached = false;
            for (int r0 = 0; r0 < this.mHolders.size(); r0++) {
                this.mHolders.get(r0).onDetach();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        for (int r1 = 0; r1 < this.mHolders.size(); r1++) {
            if (this.mHolders.get(r1).onTouchEvent(event)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        if (this.mIsAttached) {
            for (int r0 = 0; r0 < this.mHolders.size(); r0++) {
                this.mHolders.get(r0).onDetach();
            }
        }
        this.mHolders.clear();
    }

    public void add(DraweeHolder<DH> holder) {
        add(this.mHolders.size(), holder);
    }

    public void add(int index, DraweeHolder<DH> holder) {
        Preconditions.checkNotNull(holder);
        Preconditions.checkElementIndex(index, this.mHolders.size() + 1);
        this.mHolders.add(index, holder);
        if (this.mIsAttached) {
            holder.onAttach();
        }
    }

    public void remove(int index) {
        DraweeHolder<DH> draweeHolder = this.mHolders.get(index);
        if (this.mIsAttached) {
            draweeHolder.onDetach();
        }
        this.mHolders.remove(index);
    }

    public DraweeHolder<DH> get(int index) {
        return this.mHolders.get(index);
    }

    public int size() {
        return this.mHolders.size();
    }

    public void draw(Canvas canvas) {
        for (int r0 = 0; r0 < this.mHolders.size(); r0++) {
            Drawable topLevelDrawable = get(r0).getTopLevelDrawable();
            if (topLevelDrawable != null) {
                topLevelDrawable.draw(canvas);
            }
        }
    }

    public boolean verifyDrawable(Drawable who) {
        for (int r1 = 0; r1 < this.mHolders.size(); r1++) {
            if (who == get(r1).getTopLevelDrawable()) {
                return true;
            }
        }
        return false;
    }
}
