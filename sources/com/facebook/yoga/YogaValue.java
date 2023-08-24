package com.facebook.yoga;

import com.google.android.gms.ads.AdError;

/* loaded from: classes.dex */
public class YogaValue {
    public final YogaUnit unit;
    public final float value;
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.value = f;
        this.unit = yogaUnit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public YogaValue(float f, int r2) {
        this(f, YogaUnit.fromInt(r2));
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            YogaUnit yogaUnit = this.unit;
            if (yogaUnit == yogaValue.unit) {
                return yogaUnit == YogaUnit.UNDEFINED || this.unit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    /* renamed from: com.facebook.yoga.YogaValue$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C17461 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit;

        static {
            int[] r0 = new int[YogaUnit.values().length];
            $SwitchMap$com$facebook$yoga$YogaUnit = r0;
            try {
                r0[YogaUnit.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String toString() {
        int r0 = C17461.$SwitchMap$com$facebook$yoga$YogaUnit[this.unit.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 == 4) {
                        return "auto";
                    }
                    throw new IllegalStateException();
                }
                return this.value + "%";
            }
            return Float.toString(this.value);
        }
        return AdError.UNDEFINED_DOMAIN;
    }

    public static YogaValue parse(String str) {
        if (str == null) {
            return null;
        }
        if (AdError.UNDEFINED_DOMAIN.equals(str)) {
            return UNDEFINED;
        }
        if ("auto".equals(str)) {
            return AUTO;
        }
        if (str.endsWith("%")) {
            return new YogaValue(Float.parseFloat(str.substring(0, str.length() - 1)), YogaUnit.PERCENT);
        }
        return new YogaValue(Float.parseFloat(str), YogaUnit.POINT);
    }
}
