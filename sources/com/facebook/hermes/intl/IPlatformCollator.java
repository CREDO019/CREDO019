package com.facebook.hermes.intl;

/* loaded from: classes.dex */
public interface IPlatformCollator {
    int compare(String str, String str2);

    IPlatformCollator configure(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String[] getAvailableLocales();

    Sensitivity getSensitivity();

    IPlatformCollator setCaseFirstAttribute(CaseFirst caseFirst);

    IPlatformCollator setIgnorePunctuation(boolean z);

    IPlatformCollator setNumericAttribute(boolean z);

    IPlatformCollator setSensitivity(Sensitivity sensitivity);

    /* loaded from: classes.dex */
    public enum Sensitivity {
        BASE,
        ACCENT,
        CASE,
        VARIANT,
        LOCALE;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12581.f129x17be7a55[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 != 4) {
                            if (r0 == 5) {
                                return "";
                            }
                            throw new IllegalArgumentException();
                        }
                        return Constants.SENSITIVITY_VARIANT;
                    }
                    return Constants.SENSITIVITY_CASE;
                }
                return Constants.SENSITIVITY_ACCENT;
            }
            return "base";
        }
    }

    /* loaded from: classes.dex */
    public enum Usage {
        SORT,
        SEARCH;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12581.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage[ordinal()];
            if (r0 != 1) {
                if (r0 == 2) {
                    return "search";
                }
                throw new IllegalArgumentException();
            }
            return Constants.SORT;
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformCollator$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C12581 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity */
        static final /* synthetic */ int[] f129x17be7a55;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage;

        static {
            int[] r0 = new int[CaseFirst.values().length];
            $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst = r0;
            try {
                r0[CaseFirst.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst[CaseFirst.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst[CaseFirst.FALSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] r3 = new int[Usage.values().length];
            $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage = r3;
            try {
                r3[Usage.SORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage[Usage.SEARCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] r32 = new int[Sensitivity.values().length];
            f129x17be7a55 = r32;
            try {
                r32[Sensitivity.BASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f129x17be7a55[Sensitivity.ACCENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f129x17be7a55[Sensitivity.CASE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f129x17be7a55[Sensitivity.VARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f129x17be7a55[Sensitivity.LOCALE.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum CaseFirst {
        UPPER,
        LOWER,
        FALSE;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12581.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return Constants.CASEFIRST_FALSE;
                    }
                    throw new IllegalArgumentException();
                }
                return Constants.CASEFIRST_LOWER;
            }
            return Constants.CASEFIRST_UPPER;
        }
    }
}
