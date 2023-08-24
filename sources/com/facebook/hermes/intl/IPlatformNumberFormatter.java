package com.facebook.hermes.intl;

import android.icu.text.MeasureFormat;
import java.text.AttributedCharacterIterator;

/* loaded from: classes.dex */
public interface IPlatformNumberFormatter {

    /* loaded from: classes.dex */
    public enum RoundingType {
        SIGNIFICANT_DIGITS,
        FRACTION_DIGITS,
        COMPACT_ROUNDING
    }

    IPlatformNumberFormatter configure(ILocaleObject<?> iLocaleObject, String str, Style style, CurrencySign currencySign, Notation notation, CompactDisplay compactDisplay) throws JSRangeErrorException;

    String fieldToString(AttributedCharacterIterator.Attribute attribute, double d);

    String format(double d) throws JSRangeErrorException;

    AttributedCharacterIterator formatToParts(double d) throws JSRangeErrorException;

    String[] getAvailableLocales();

    String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    IPlatformNumberFormatter setCurrency(String str, CurrencyDisplay currencyDisplay) throws JSRangeErrorException;

    IPlatformNumberFormatter setFractionDigits(RoundingType roundingType, int r2, int r3);

    IPlatformNumberFormatter setGrouping(boolean z);

    IPlatformNumberFormatter setMinIntergerDigits(int r1);

    IPlatformNumberFormatter setSignDisplay(SignDisplay signDisplay);

    IPlatformNumberFormatter setSignificantDigits(RoundingType roundingType, int r2, int r3) throws JSRangeErrorException;

    IPlatformNumberFormatter setUnits(String str, UnitDisplay unitDisplay) throws JSRangeErrorException;

    /* loaded from: classes.dex */
    public enum Style {
        DECIMAL,
        PERCENT,
        CURRENCY,
        UNIT;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f146x5b95aa76[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "unit";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "currency";
                }
                return "percent";
            }
            return "decimal";
        }

        public int getInitialNumberFormatStyle(Notation notation, CurrencySign currencySign) throws JSRangeErrorException {
            int r0 = C12601.f146x5b95aa76[ordinal()];
            if (r0 != 2) {
                if (r0 != 3) {
                    return (notation == Notation.SCIENTIFIC || notation == Notation.ENGINEERING) ? 3 : 0;
                } else if (currencySign == CurrencySign.ACCOUNTING) {
                    return 7;
                } else {
                    if (currencySign == CurrencySign.STANDARD) {
                        return 1;
                    }
                    throw new JSRangeErrorException("Unrecognized formatting style requested.");
                }
            }
            return 2;
        }
    }

    /* loaded from: classes.dex */
    public enum Notation {
        STANDARD,
        SCIENTIFIC,
        ENGINEERING,
        COMPACT;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f144xb4c7dd1d[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "compact";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "engineering";
                }
                return "scientific";
            }
            return Constants.COLLATION_STANDARD;
        }
    }

    /* loaded from: classes.dex */
    public enum CompactDisplay {
        SHORT,
        LONG;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f141xca0e38fa[ordinal()];
            if (r0 != 1) {
                if (r0 == 2) {
                    return "long";
                }
                throw new IllegalArgumentException();
            }
            return "short";
        }
    }

    /* loaded from: classes.dex */
    public enum SignDisplay {
        AUTO,
        ALWAYS,
        NEVER,
        EXCEPTZERO;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f145xa920794a[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "exceptZero";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "never";
                }
                return "always";
            }
            return "auto";
        }
    }

    /* loaded from: classes.dex */
    public enum UnitDisplay {
        SHORT,
        NARROW,
        LONG;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f147x3028ffa3[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "long";
                    }
                    throw new IllegalArgumentException();
                }
                return "narrow";
            }
            return "short";
        }

        public MeasureFormat.FormatWidth getFormatWidth() {
            int r0 = C12601.f147x3028ffa3[ordinal()];
            if (r0 != 2) {
                if (r0 == 3) {
                    return MeasureFormat.FormatWidth.WIDE;
                }
                return MeasureFormat.FormatWidth.SHORT;
            }
            return MeasureFormat.FormatWidth.NARROW;
        }
    }

    /* loaded from: classes.dex */
    public enum CurrencyDisplay {
        SYMBOL,
        NARROWSYMBOL,
        CODE,
        NAME;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f142x4080d5f6[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "name";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "code";
                }
                return "narrowSymbol";
            }
            return "symbol";
        }

        public int getNameStyle() {
            return C12601.f142x4080d5f6[ordinal()] != 4 ? 0 : 1;
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformNumberFormatter$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C12601 {

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CompactDisplay */
        static final /* synthetic */ int[] f141xca0e38fa;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay */
        static final /* synthetic */ int[] f142x4080d5f6;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencySign */
        static final /* synthetic */ int[] f143x1c724d09;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation */
        static final /* synthetic */ int[] f144xb4c7dd1d;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay */
        static final /* synthetic */ int[] f145xa920794a;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style */
        static final /* synthetic */ int[] f146x5b95aa76;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay */
        static final /* synthetic */ int[] f147x3028ffa3;

        static {
            int[] r0 = new int[CurrencySign.values().length];
            f143x1c724d09 = r0;
            try {
                r0[CurrencySign.ACCOUNTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f143x1c724d09[CurrencySign.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] r2 = new int[CurrencyDisplay.values().length];
            f142x4080d5f6 = r2;
            try {
                r2[CurrencyDisplay.SYMBOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f142x4080d5f6[CurrencyDisplay.NARROWSYMBOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f142x4080d5f6[CurrencyDisplay.CODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f142x4080d5f6[CurrencyDisplay.NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            int[] r4 = new int[UnitDisplay.values().length];
            f147x3028ffa3 = r4;
            try {
                r4[UnitDisplay.SHORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f147x3028ffa3[UnitDisplay.NARROW.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f147x3028ffa3[UnitDisplay.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            int[] r42 = new int[SignDisplay.values().length];
            f145xa920794a = r42;
            try {
                r42[SignDisplay.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f145xa920794a[SignDisplay.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f145xa920794a[SignDisplay.NEVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f145xa920794a[SignDisplay.EXCEPTZERO.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            int[] r43 = new int[CompactDisplay.values().length];
            f141xca0e38fa = r43;
            try {
                r43[CompactDisplay.SHORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f141xca0e38fa[CompactDisplay.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            int[] r44 = new int[Notation.values().length];
            f144xb4c7dd1d = r44;
            try {
                r44[Notation.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f144xb4c7dd1d[Notation.SCIENTIFIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f144xb4c7dd1d[Notation.ENGINEERING.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f144xb4c7dd1d[Notation.COMPACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            int[] r45 = new int[Style.values().length];
            f146x5b95aa76 = r45;
            try {
                r45[Style.DECIMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f146x5b95aa76[Style.PERCENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f146x5b95aa76[Style.CURRENCY.ordinal()] = 3;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f146x5b95aa76[Style.UNIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum CurrencySign {
        STANDARD,
        ACCOUNTING;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12601.f143x1c724d09[ordinal()];
            if (r0 != 1) {
                if (r0 == 2) {
                    return Constants.COLLATION_STANDARD;
                }
                throw new IllegalArgumentException();
            }
            return "accounting";
        }
    }
}
