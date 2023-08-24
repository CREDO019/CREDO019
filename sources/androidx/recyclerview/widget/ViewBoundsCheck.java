package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
class ViewBoundsCheck {
    static final int CVE_PVE_POS = 12;
    static final int CVE_PVS_POS = 8;
    static final int CVS_PVE_POS = 4;
    static final int CVS_PVS_POS = 0;

    /* renamed from: EQ */
    static final int f53EQ = 2;
    static final int FLAG_CVE_EQ_PVE = 8192;
    static final int FLAG_CVE_EQ_PVS = 512;
    static final int FLAG_CVE_GT_PVE = 4096;
    static final int FLAG_CVE_GT_PVS = 256;
    static final int FLAG_CVE_LT_PVE = 16384;
    static final int FLAG_CVE_LT_PVS = 1024;
    static final int FLAG_CVS_EQ_PVE = 32;
    static final int FLAG_CVS_EQ_PVS = 2;
    static final int FLAG_CVS_GT_PVE = 16;
    static final int FLAG_CVS_GT_PVS = 1;
    static final int FLAG_CVS_LT_PVE = 64;
    static final int FLAG_CVS_LT_PVS = 4;

    /* renamed from: GT */
    static final int f54GT = 1;

    /* renamed from: LT */
    static final int f55LT = 4;
    static final int MASK = 7;
    BoundFlags mBoundFlags = new BoundFlags();
    final Callback mCallback;

    /* loaded from: classes.dex */
    interface Callback {
        View getChildAt(int r1);

        int getChildEnd(View view);

        int getChildStart(View view);

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ViewBounds {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewBoundsCheck(Callback callback) {
        this.mCallback = callback;
    }

    /* loaded from: classes.dex */
    static class BoundFlags {
        int mBoundFlags = 0;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        int compare(int r1, int r2) {
            if (r1 > r2) {
                return 1;
            }
            return r1 == r2 ? 2 : 4;
        }

        BoundFlags() {
        }

        void setBounds(int r1, int r2, int r3, int r4) {
            this.mRvStart = r1;
            this.mRvEnd = r2;
            this.mChildStart = r3;
            this.mChildEnd = r4;
        }

        void addFlags(int r2) {
            this.mBoundFlags = r2 | this.mBoundFlags;
        }

        void resetFlags() {
            this.mBoundFlags = 0;
        }

        boolean boundsMatch() {
            int r0 = this.mBoundFlags;
            if ((r0 & 7) == 0 || (r0 & (compare(this.mChildStart, this.mRvStart) << 0)) != 0) {
                int r02 = this.mBoundFlags;
                if ((r02 & 112) == 0 || (r02 & (compare(this.mChildStart, this.mRvEnd) << 4)) != 0) {
                    int r03 = this.mBoundFlags;
                    if ((r03 & 1792) == 0 || (r03 & (compare(this.mChildEnd, this.mRvStart) << 8)) != 0) {
                        int r04 = this.mBoundFlags;
                        return (r04 & 28672) == 0 || (r04 & (compare(this.mChildEnd, this.mRvEnd) << 12)) != 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View findOneViewWithinBoundFlags(int r9, int r10, int r11, int r12) {
        int parentStart = this.mCallback.getParentStart();
        int parentEnd = this.mCallback.getParentEnd();
        int r2 = r10 > r9 ? 1 : -1;
        View view = null;
        while (r9 != r10) {
            View childAt = this.mCallback.getChildAt(r9);
            this.mBoundFlags.setBounds(parentStart, parentEnd, this.mCallback.getChildStart(childAt), this.mCallback.getChildEnd(childAt));
            if (r11 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(r11);
                if (this.mBoundFlags.boundsMatch()) {
                    return childAt;
                }
            }
            if (r12 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(r12);
                if (this.mBoundFlags.boundsMatch()) {
                    view = childAt;
                }
            }
            r9 += r2;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isViewWithinBoundFlags(View view, int r7) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(view), this.mCallback.getChildEnd(view));
        if (r7 != 0) {
            this.mBoundFlags.resetFlags();
            this.mBoundFlags.addFlags(r7);
            return this.mBoundFlags.boundsMatch();
        }
        return false;
    }
}
