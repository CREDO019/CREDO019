package com.facebook.common.util;

import javax.annotation.Nullable;

/* loaded from: classes.dex */
public enum TriState {
    YES,
    NO,
    UNSET;

    public boolean isSet() {
        return this != UNSET;
    }

    public static TriState valueOf(boolean bool) {
        return bool ? YES : NO;
    }

    public static TriState valueOf(@Nullable Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    /* renamed from: com.facebook.common.util.TriState$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C11891 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState;

        static {
            int[] r0 = new int[TriState.values().length];
            $SwitchMap$com$facebook$common$util$TriState = r0;
            try {
                r0[TriState.YES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.NO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean asBoolean() {
        int r0 = C11891.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    throw new IllegalStateException("No boolean equivalent for UNSET");
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return false;
        }
        return true;
    }

    public boolean asBoolean(boolean defaultValue) {
        int r0 = C11891.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    return defaultValue;
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public Boolean asBooleanObject() {
        int r0 = C11891.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    return null;
                }
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public int getDbValue() {
        int r0 = C11891.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        int r1 = 1;
        if (r0 != 1) {
            r1 = 2;
            if (r0 != 2) {
                return 3;
            }
        }
        return r1;
    }

    public static TriState fromDbValue(int value) {
        if (value != 1) {
            if (value == 2) {
                return NO;
            }
            return UNSET;
        }
        return YES;
    }
}
