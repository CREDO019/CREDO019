package expo.modules.speech;

import com.facebook.hermes.intl.Constants;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LanguageUtils.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0006J\u0018\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J\u0018\u0010\u0013\u001a\n \u0011*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0002R'\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\b¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/speech/LanguageUtils;", "", "()V", "countryISOCodes", "", "", "Ljava/util/Locale;", "getCountryISOCodes", "()Ljava/util/Map;", "countryISOCodes$delegate", "Lkotlin/Lazy;", "languageISOCodes", "getLanguageISOCodes", "languageISOCodes$delegate", "getISOCode", Constants.LOCALE, "transformCountryISO", "kotlin.jvm.PlatformType", "code", "transformLanguageISO", "expo-speech_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class LanguageUtils {
    public static final LanguageUtils INSTANCE = new LanguageUtils();
    private static final Lazy countryISOCodes$delegate = LazyKt.lazy(new Functions<Map<String, ? extends Locale>>() { // from class: expo.modules.speech.LanguageUtils$countryISOCodes$2
        @Override // kotlin.jvm.functions.Functions
        public final Map<String, ? extends Locale> invoke() {
            String[] iSOCountries = Locale.getISOCountries();
            Intrinsics.checkNotNullExpressionValue(iSOCountries, "getISOCountries()");
            String[] strArr = iSOCountries;
            ArrayList arrayList = new ArrayList(strArr.length);
            int length = strArr.length;
            int r3 = 0;
            while (r3 < length) {
                String str = strArr[r3];
                r3++;
                Locale locale = new Locale("", str);
                String iSO3Country = locale.getISO3Country();
                Intrinsics.checkNotNullExpressionValue(iSO3Country, "locale.getISO3Country()");
                String upperCase = iSO3Country.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
                arrayList.add(TuplesKt.m176to(upperCase, locale));
            }
            return MapsKt.toMap(arrayList);
        }
    });
    private static final Lazy languageISOCodes$delegate = LazyKt.lazy(new Functions<Map<String, ? extends Locale>>() { // from class: expo.modules.speech.LanguageUtils$languageISOCodes$2
        @Override // kotlin.jvm.functions.Functions
        public final Map<String, ? extends Locale> invoke() {
            String[] iSOLanguages = Locale.getISOLanguages();
            Intrinsics.checkNotNullExpressionValue(iSOLanguages, "getISOLanguages()");
            String[] strArr = iSOLanguages;
            ArrayList arrayList = new ArrayList(strArr.length);
            int length = strArr.length;
            int r3 = 0;
            while (r3 < length) {
                String str = strArr[r3];
                r3++;
                Locale locale = new Locale(str);
                arrayList.add(TuplesKt.m176to(locale.getISO3Language(), locale));
            }
            return MapsKt.toMap(arrayList);
        }
    });

    private LanguageUtils() {
    }

    private final Map<String, Locale> getCountryISOCodes() {
        return (Map) countryISOCodes$delegate.getValue();
    }

    private final Map<String, Locale> getLanguageISOCodes() {
        return (Map) languageISOCodes$delegate.getValue();
    }

    private final String transformCountryISO(String str) {
        Locale locale = getCountryISOCodes().get(str);
        Intrinsics.checkNotNull(locale);
        return locale.getCountry();
    }

    private final String transformLanguageISO(String str) {
        Locale locale = getLanguageISOCodes().get(str);
        Intrinsics.checkNotNull(locale);
        return locale.getLanguage();
    }

    public final String getISOCode(Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        String iSO3Language = locale.getISO3Language();
        Intrinsics.checkNotNullExpressionValue(iSO3Language, "locale.getISO3Language()");
        String language = transformLanguageISO(iSO3Language);
        String country = locale.getISO3Country();
        if (!Intrinsics.areEqual(country, "")) {
            Intrinsics.checkNotNullExpressionValue(country, "country");
            language = language + "-" + transformCountryISO(country);
        }
        Intrinsics.checkNotNullExpressionValue(language, "language");
        return language;
    }
}
