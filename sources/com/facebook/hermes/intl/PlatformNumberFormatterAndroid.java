package com.facebook.hermes.intl;

import android.os.Build;
import com.facebook.hermes.intl.IPlatformNumberFormatter;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

/* loaded from: classes.dex */
public class PlatformNumberFormatterAndroid implements IPlatformNumberFormatter {
    private DecimalFormat mDecimalFormat;
    private Format mFinalFormat;
    private LocaleObjectAndroid mLocaleObject;
    private IPlatformNumberFormatter.Style mStyle;

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String fieldToString(AttributedCharacterIterator.Attribute attribute, double d) {
        return "literal";
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) {
        return "latn";
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setSignificantDigits(IPlatformNumberFormatter.RoundingType roundingType, int r2, int r3) {
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setUnits(String str, IPlatformNumberFormatter.UnitDisplay unitDisplay) {
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public /* bridge */ /* synthetic */ IPlatformNumberFormatter configure(ILocaleObject iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        return configure((ILocaleObject<?>) iLocaleObject, str, style, currencySign, notation, compactDisplay);
    }

    private void initialize(DecimalFormat decimalFormat, ILocaleObject<?> iLocaleObject, IPlatformNumberFormatter.Style style) {
        this.mDecimalFormat = decimalFormat;
        this.mFinalFormat = decimalFormat;
        this.mLocaleObject = (LocaleObjectAndroid) iLocaleObject;
        this.mStyle = style;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setCurrency(String str, IPlatformNumberFormatter.CurrencyDisplay currencyDisplay) throws JSRangeErrorException {
        if (this.mStyle == IPlatformNumberFormatter.Style.CURRENCY) {
            Currency currency = Currency.getInstance(str);
            this.mDecimalFormat.setCurrency(currency);
            int r4 = C12641.f150x4080d5f6[currencyDisplay.ordinal()];
            if (r4 != 1) {
                if (r4 != 2) {
                    str = currency.getSymbol(this.mLocaleObject.getLocale());
                }
            } else if (Build.VERSION.SDK_INT >= 19) {
                str = currency.getDisplayName(this.mLocaleObject.getLocale());
            } else {
                str = currency.getSymbol(this.mLocaleObject.getLocale());
            }
            DecimalFormatSymbols decimalFormatSymbols = this.mDecimalFormat.getDecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol(str);
            this.mDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            this.mDecimalFormat.getDecimalFormatSymbols().setCurrencySymbol(str);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.hermes.intl.PlatformNumberFormatterAndroid$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C12641 {

        /* renamed from: $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay */
        static final /* synthetic */ int[] f150x4080d5f6;

        static {
            int[] r0 = new int[IPlatformNumberFormatter.CurrencyDisplay.values().length];
            f150x4080d5f6 = r0;
            try {
                r0[IPlatformNumberFormatter.CurrencyDisplay.NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f150x4080d5f6[IPlatformNumberFormatter.CurrencyDisplay.CODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f150x4080d5f6[IPlatformNumberFormatter.CurrencyDisplay.SYMBOL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f150x4080d5f6[IPlatformNumberFormatter.CurrencyDisplay.NARROWSYMBOL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setGrouping(boolean z) {
        this.mDecimalFormat.setGroupingUsed(z);
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setMinIntergerDigits(int r2) {
        if (r2 != -1) {
            this.mDecimalFormat.setMinimumIntegerDigits(r2);
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setFractionDigits(IPlatformNumberFormatter.RoundingType roundingType, int r3, int r4) {
        if (roundingType == IPlatformNumberFormatter.RoundingType.FRACTION_DIGITS) {
            if (r3 >= 0) {
                this.mDecimalFormat.setMinimumFractionDigits(r3);
            }
            if (r4 >= 0) {
                this.mDecimalFormat.setMaximumFractionDigits(r4);
            }
        }
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid setSignDisplay(IPlatformNumberFormatter.SignDisplay signDisplay) {
        if (signDisplay == IPlatformNumberFormatter.SignDisplay.NEVER) {
            this.mDecimalFormat.setPositivePrefix("");
            this.mDecimalFormat.setPositiveSuffix("");
            this.mDecimalFormat.setNegativePrefix("");
            this.mDecimalFormat.setNegativeSuffix("");
        }
        return this;
    }

    public static int getCurrencyDigits(String str) throws JSRangeErrorException {
        try {
            return Currency.getInstance(str).getDefaultFractionDigits();
        } catch (IllegalArgumentException unused) {
            throw new JSRangeErrorException("Invalid currency code !");
        }
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String format(double d) {
        return this.mFinalFormat.format(Double.valueOf(d));
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public AttributedCharacterIterator formatToParts(double d) {
        return this.mFinalFormat.formatToCharacterIterator(Double.valueOf(d));
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public PlatformNumberFormatterAndroid configure(ILocaleObject<?> iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        java.text.NumberFormat numberFormat = java.text.NumberFormat.getInstance((Locale) iLocaleObject.getLocale());
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        initialize((DecimalFormat) numberFormat, iLocaleObject, style);
        return this;
    }

    @Override // com.facebook.hermes.intl.IPlatformNumberFormatter
    public String[] getAvailableLocales() {
        if (Build.VERSION.SDK_INT < 21) {
            return new String[]{"en"};
        }
        ArrayList arrayList = new ArrayList();
        for (Locale locale : java.text.NumberFormat.getAvailableLocales()) {
            arrayList.add(locale.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
