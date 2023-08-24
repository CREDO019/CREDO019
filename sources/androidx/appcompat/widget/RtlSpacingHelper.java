package androidx.appcompat.widget;

/* loaded from: classes.dex */
class RtlSpacingHelper {
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRtl = false;
    private boolean mIsRelative = false;

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public void setRelative(int r3, int r4) {
        this.mStart = r3;
        this.mEnd = r4;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (r4 != Integer.MIN_VALUE) {
                this.mLeft = r4;
            }
            if (r3 != Integer.MIN_VALUE) {
                this.mRight = r3;
                return;
            }
            return;
        }
        if (r3 != Integer.MIN_VALUE) {
            this.mLeft = r3;
        }
        if (r4 != Integer.MIN_VALUE) {
            this.mRight = r4;
        }
    }

    public void setAbsolute(int r2, int r3) {
        this.mIsRelative = false;
        if (r2 != Integer.MIN_VALUE) {
            this.mExplicitLeft = r2;
            this.mLeft = r2;
        }
        if (r3 != Integer.MIN_VALUE) {
            this.mExplicitRight = r3;
            this.mRight = r3;
        }
    }

    public void setDirection(boolean z) {
        if (z == this.mIsRtl) {
            return;
        }
        this.mIsRtl = z;
        if (!this.mIsRelative) {
            this.mLeft = this.mExplicitLeft;
            this.mRight = this.mExplicitRight;
        } else if (z) {
            int r2 = this.mEnd;
            if (r2 == Integer.MIN_VALUE) {
                r2 = this.mExplicitLeft;
            }
            this.mLeft = r2;
            int r22 = this.mStart;
            if (r22 == Integer.MIN_VALUE) {
                r22 = this.mExplicitRight;
            }
            this.mRight = r22;
        } else {
            int r23 = this.mStart;
            if (r23 == Integer.MIN_VALUE) {
                r23 = this.mExplicitLeft;
            }
            this.mLeft = r23;
            int r24 = this.mEnd;
            if (r24 == Integer.MIN_VALUE) {
                r24 = this.mExplicitRight;
            }
            this.mRight = r24;
        }
    }
}
