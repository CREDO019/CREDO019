package com.th3rdwave.safeareacontext;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Rect.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, m183d2 = {"Lcom/th3rdwave/safeareacontext/Rect;", "", "x", "", "y", "width", "height", "(FFFF)V", "getHeight", "()F", "getWidth", "getX", "getY", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class Rect {
    private final float height;
    private final float width;

    /* renamed from: x */
    private final float f1381x;

    /* renamed from: y */
    private final float f1382y;

    public static /* synthetic */ Rect copy$default(Rect rect, float f, float f2, float f3, float f4, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            f = rect.f1381x;
        }
        if ((r5 & 2) != 0) {
            f2 = rect.f1382y;
        }
        if ((r5 & 4) != 0) {
            f3 = rect.width;
        }
        if ((r5 & 8) != 0) {
            f4 = rect.height;
        }
        return rect.copy(f, f2, f3, f4);
    }

    public final float component1() {
        return this.f1381x;
    }

    public final float component2() {
        return this.f1382y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    public final Rect copy(float f, float f2, float f3, float f4) {
        return new Rect(f, f2, f3, f4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Rect) {
            Rect rect = (Rect) obj;
            return Intrinsics.areEqual((Object) Float.valueOf(this.f1381x), (Object) Float.valueOf(rect.f1381x)) && Intrinsics.areEqual((Object) Float.valueOf(this.f1382y), (Object) Float.valueOf(rect.f1382y)) && Intrinsics.areEqual((Object) Float.valueOf(this.width), (Object) Float.valueOf(rect.width)) && Intrinsics.areEqual((Object) Float.valueOf(this.height), (Object) Float.valueOf(rect.height));
        }
        return false;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.f1381x) * 31) + Float.floatToIntBits(this.f1382y)) * 31) + Float.floatToIntBits(this.width)) * 31) + Float.floatToIntBits(this.height);
    }

    public String toString() {
        return "Rect(x=" + this.f1381x + ", y=" + this.f1382y + ", width=" + this.width + ", height=" + this.height + ')';
    }

    public Rect(float f, float f2, float f3, float f4) {
        this.f1381x = f;
        this.f1382y = f2;
        this.width = f3;
        this.height = f4;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getX() {
        return this.f1381x;
    }

    public final float getY() {
        return this.f1382y;
    }
}
