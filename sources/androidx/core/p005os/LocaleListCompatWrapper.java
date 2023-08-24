package androidx.core.p005os;

import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/* renamed from: androidx.core.os.LocaleListCompatWrapper */
/* loaded from: classes.dex */
final class LocaleListCompatWrapper implements LocaleListInterface {
    private final Locale[] mList;
    private final String mStringRepresentation;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale EN_LATN = LocaleListCompat.forLanguageTagCompat("en-Latn");

    @Override // androidx.core.p005os.LocaleListInterface
    public Object getLocaleList() {
        return null;
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public Locale get(int r3) {
        if (r3 >= 0) {
            Locale[] localeArr = this.mList;
            if (r3 < localeArr.length) {
                return localeArr[r3];
            }
        }
        return null;
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public int size() {
        return this.mList.length;
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public int indexOf(Locale locale) {
        int r0 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (r0 >= localeArr.length) {
                return -1;
            }
            if (localeArr[r0].equals(locale)) {
                return r0;
            }
            r0++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListCompatWrapper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListCompatWrapper) obj).mList;
        if (this.mList.length != localeArr.length) {
            return false;
        }
        int r1 = 0;
        while (true) {
            Locale[] localeArr2 = this.mList;
            if (r1 >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[r1].equals(localeArr[r1])) {
                return false;
            }
            r1++;
        }
    }

    public int hashCode() {
        int r0 = 1;
        int r1 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (r1 >= localeArr.length) {
                return r0;
            }
            r0 = (r0 * 31) + localeArr[r1].hashCode();
            r1++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int r1 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (r1 < localeArr.length) {
                sb.append(localeArr[r1]);
                if (r1 < this.mList.length - 1) {
                    sb.append(',');
                }
                r1++;
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleListCompatWrapper(Locale... localeArr) {
        if (localeArr.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int r3 = 0; r3 < localeArr.length; r3++) {
            Locale locale = localeArr[r3];
            if (locale == null) {
                throw new NullPointerException("list[" + r3 + "] is null");
            }
            if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                arrayList.add(locale2);
                toLanguageTag(sb, locale2);
                if (r3 < localeArr.length - 1) {
                    sb.append(',');
                }
                hashSet.add(locale2);
            }
        }
        this.mList = (Locale[]) arrayList.toArray(new Locale[arrayList.size()]);
        this.mStringRepresentation = sb.toString();
    }

    static void toLanguageTag(StringBuilder sb, Locale locale) {
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return;
        }
        sb.append('-');
        sb.append(locale.getCountry());
    }

    private static String getLikelyScript(Locale locale) {
        if (Build.VERSION.SDK_INT >= 21) {
            String script = locale.getScript();
            if (!script.isEmpty()) {
                return script;
            }
        }
        return "";
    }

    private static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    private static int matchScore(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
            return 0;
        }
        String likelyScript = getLikelyScript(locale);
        if (likelyScript.isEmpty()) {
            String country = locale.getCountry();
            return (country.isEmpty() || country.equals(locale2.getCountry())) ? 1 : 0;
        }
        return likelyScript.equals(getLikelyScript(locale2)) ? 1 : 0;
    }

    private int findFirstMatchIndex(Locale locale) {
        int r0 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (r0 >= localeArr.length) {
                return Integer.MAX_VALUE;
            }
            if (matchScore(locale, localeArr[r0]) > 0) {
                return r0;
            }
            r0++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r6 < Integer.MAX_VALUE) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int computeFirstMatchIndex(java.util.Collection<java.lang.String> r5, boolean r6) {
        /*
            r4 = this;
            java.util.Locale[] r0 = r4.mList
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L8
            return r2
        L8:
            int r0 = r0.length
            if (r0 != 0) goto Ld
            r5 = -1
            return r5
        Ld:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == 0) goto L1e
            java.util.Locale r6 = androidx.core.p005os.LocaleListCompatWrapper.EN_LATN
            int r6 = r4.findFirstMatchIndex(r6)
            if (r6 != 0) goto L1b
            return r2
        L1b:
            if (r6 >= r0) goto L1e
            goto L21
        L1e:
            r6 = 2147483647(0x7fffffff, float:NaN)
        L21:
            java.util.Iterator r5 = r5.iterator()
        L25:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L40
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Locale r1 = androidx.core.p005os.LocaleListCompat.forLanguageTagCompat(r1)
            int r1 = r4.findFirstMatchIndex(r1)
            if (r1 != 0) goto L3c
            return r2
        L3c:
            if (r1 >= r6) goto L25
            r6 = r1
            goto L25
        L40:
            if (r6 != r0) goto L43
            return r2
        L43:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.p005os.LocaleListCompatWrapper.computeFirstMatchIndex(java.util.Collection, boolean):int");
    }

    private Locale computeFirstMatch(Collection<String> collection, boolean z) {
        int computeFirstMatchIndex = computeFirstMatchIndex(collection, z);
        if (computeFirstMatchIndex == -1) {
            return null;
        }
        return this.mList[computeFirstMatchIndex];
    }

    @Override // androidx.core.p005os.LocaleListInterface
    public Locale getFirstMatch(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), false);
    }
}
