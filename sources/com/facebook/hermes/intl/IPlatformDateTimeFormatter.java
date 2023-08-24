package com.facebook.hermes.intl;

import java.text.AttributedCharacterIterator;

/* loaded from: classes.dex */
public interface IPlatformDateTimeFormatter {
    void configure(ILocaleObject<?> iLocaleObject, String str, String str2, FormatMatcher formatMatcher, WeekDay weekDay, Era era, Year year, Month month, Day day, Hour hour, Minute minute, Second second, TimeZoneName timeZoneName, HourCycle hourCycle, Object obj) throws JSRangeErrorException;

    String fieldToString(AttributedCharacterIterator.Attribute attribute, String str);

    String format(double d) throws JSRangeErrorException;

    AttributedCharacterIterator formatToParts(double d) throws JSRangeErrorException;

    String[] getAvailableLocales();

    String getDefaultCalendarName(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    HourCycle getDefaultHourCycle(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String getDefaultTimeZone(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    /* loaded from: classes.dex */
    public enum FormatMatcher {
        BESTFIT,
        BASIC;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f132xe3b8aa6e[ordinal()];
            if (r0 != 1) {
                if (r0 == 2) {
                    return "basic";
                }
                throw new IllegalArgumentException();
            }
            return Constants.LOCALEMATCHER_BESTFIT;
        }
    }

    /* loaded from: classes.dex */
    public enum HourCycle {
        H11,
        H12,
        H23,
        H24,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f134xa1956815[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 != 4) {
                            if (r0 == 5) {
                                return "";
                            }
                            throw new IllegalArgumentException();
                        }
                        return "h24";
                    }
                    return "h23";
                }
                return "h12";
            }
            return "h11";
        }
    }

    /* loaded from: classes.dex */
    public enum WeekDay {
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f139x64abae7b[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "narrow";
                }
                return "short";
            }
            return "long";
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f139x64abae7b[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "EEEEE";
                }
                return "EEE";
            }
            return "EEEE";
        }
    }

    /* loaded from: classes.dex */
    public enum Era {
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f131x7c20f247[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "narrow";
                }
                return "short";
            }
            return "long";
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f131x7c20f247[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r0 == 4) {
                            return "";
                        }
                        throw new IllegalArgumentException();
                    }
                    return "G5";
                }
                return "GGG";
            }
            return "GGGG";
        }
    }

    /* loaded from: classes.dex */
    public enum Year {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f140x8063daa[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "2-digit";
            }
            return Constants.COLLATION_OPTION_NUMERIC;
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f140x8063daa[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "yy";
            }
            return "yyyy";
        }
    }

    /* loaded from: classes.dex */
    public enum Month {
        NUMERIC,
        DIGIT2,
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            switch (C12591.f136xf81d1ab3[ordinal()]) {
                case 1:
                    return Constants.COLLATION_OPTION_NUMERIC;
                case 2:
                    return "2-digit";
                case 3:
                    return "long";
                case 4:
                    return "short";
                case 5:
                    return "narrow";
                case 6:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public String getSkeleonSymbol() {
            switch (C12591.f136xf81d1ab3[ordinal()]) {
                case 1:
                    return "M";
                case 2:
                    return "MM";
                case 3:
                    return "MMMM";
                case 4:
                    return "MMM";
                case 5:
                    return "MMMMM";
                case 6:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Day {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f130x7c20ec8f[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "2-digit";
            }
            return Constants.COLLATION_OPTION_NUMERIC;
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f130x7c20ec8f[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "dd";
            }
            return "d";
        }
    }

    /* loaded from: classes.dex */
    public enum Hour {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f133x7feab51[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "2-digit";
            }
            return Constants.COLLATION_OPTION_NUMERIC;
        }

        public String getSkeleonSymbol12() {
            int r0 = C12591.f133x7feab51[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "hh";
            }
            return "h";
        }

        public String getSkeleonSymbol24() {
            int r0 = C12591.f133x7feab51[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "kk";
            }
            return "k";
        }
    }

    /* loaded from: classes.dex */
    public enum Minute {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f135xb31b441[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "2-digit";
            }
            return Constants.COLLATION_OPTION_NUMERIC;
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f135xb31b441[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "mm";
            }
            return "m";
        }
    }

    /* loaded from: classes.dex */
    public enum Second {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f137x153152a1[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "2-digit";
            }
            return Constants.COLLATION_OPTION_NUMERIC;
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f137x153152a1[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "ss";
            }
            return "s";
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformDateTimeFormatter$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C12591 {

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day */
        static final /* synthetic */ int[] f130x7c20ec8f;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era */
        static final /* synthetic */ int[] f131x7c20f247;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$FormatMatcher */
        static final /* synthetic */ int[] f132xe3b8aa6e;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour */
        static final /* synthetic */ int[] f133x7feab51;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle */
        static final /* synthetic */ int[] f134xa1956815;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute */
        static final /* synthetic */ int[] f135xb31b441;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month */
        static final /* synthetic */ int[] f136xf81d1ab3;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second */
        static final /* synthetic */ int[] f137x153152a1;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName */
        static final /* synthetic */ int[] f138xb563eeb1;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay */
        static final /* synthetic */ int[] f139x64abae7b;

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year */
        static final /* synthetic */ int[] f140x8063daa;

        static {
            int[] r0 = new int[TimeZoneName.values().length];
            f138xb563eeb1 = r0;
            try {
                r0[TimeZoneName.LONG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f138xb563eeb1[TimeZoneName.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f138xb563eeb1[TimeZoneName.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] r3 = new int[Second.values().length];
            f137x153152a1 = r3;
            try {
                r3[Second.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f137x153152a1[Second.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f137x153152a1[Second.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] r32 = new int[Minute.values().length];
            f135xb31b441 = r32;
            try {
                r32[Minute.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f135xb31b441[Minute.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f135xb31b441[Minute.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            int[] r33 = new int[Hour.values().length];
            f133x7feab51 = r33;
            try {
                r33[Hour.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f133x7feab51[Hour.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f133x7feab51[Hour.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            int[] r34 = new int[Day.values().length];
            f130x7c20ec8f = r34;
            try {
                r34[Day.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f130x7c20ec8f[Day.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f130x7c20ec8f[Day.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            int[] r35 = new int[Month.values().length];
            f136xf81d1ab3 = r35;
            try {
                r35[Month.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f136xf81d1ab3[Month.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f136xf81d1ab3[Month.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f136xf81d1ab3[Month.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f136xf81d1ab3[Month.NARROW.ordinal()] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f136xf81d1ab3[Month.UNDEFINED.ordinal()] = 6;
            } catch (NoSuchFieldError unused21) {
            }
            int[] r5 = new int[Year.values().length];
            f140x8063daa = r5;
            try {
                r5[Year.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f140x8063daa[Year.DIGIT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f140x8063daa[Year.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused24) {
            }
            int[] r52 = new int[Era.values().length];
            f131x7c20f247 = r52;
            try {
                r52[Era.LONG.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f131x7c20f247[Era.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f131x7c20f247[Era.NARROW.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f131x7c20f247[Era.UNDEFINED.ordinal()] = 4;
            } catch (NoSuchFieldError unused28) {
            }
            int[] r53 = new int[WeekDay.values().length];
            f139x64abae7b = r53;
            try {
                r53[WeekDay.LONG.ordinal()] = 1;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f139x64abae7b[WeekDay.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f139x64abae7b[WeekDay.NARROW.ordinal()] = 3;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f139x64abae7b[WeekDay.UNDEFINED.ordinal()] = 4;
            } catch (NoSuchFieldError unused32) {
            }
            int[] r54 = new int[HourCycle.values().length];
            f134xa1956815 = r54;
            try {
                r54[HourCycle.H11.ordinal()] = 1;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f134xa1956815[HourCycle.H12.ordinal()] = 2;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                f134xa1956815[HourCycle.H23.ordinal()] = 3;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                f134xa1956815[HourCycle.H24.ordinal()] = 4;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                f134xa1956815[HourCycle.UNDEFINED.ordinal()] = 5;
            } catch (NoSuchFieldError unused37) {
            }
            int[] r2 = new int[FormatMatcher.values().length];
            f132xe3b8aa6e = r2;
            try {
                r2[FormatMatcher.BESTFIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                f132xe3b8aa6e[FormatMatcher.BASIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused39) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum TimeZoneName {
        LONG,
        SHORT,
        UNDEFINED;

        @Override // java.lang.Enum
        public String toString() {
            int r0 = C12591.f138xb563eeb1[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "short";
            }
            return "long";
        }

        public String getSkeleonSymbol() {
            int r0 = C12591.f138xb563eeb1[ordinal()];
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return "";
                    }
                    throw new IllegalArgumentException();
                }
                return "O";
            }
            return "VV";
        }
    }
}
