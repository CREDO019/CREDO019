package com.facebook.hermes.intl;

/* compiled from: LocaleIdentifier.java */
/* loaded from: classes.dex */
class LocaleIdTokenizer {
    private CharSequence mLocaleIdBuffer;
    int mCurrentSubtagStart = 0;
    int mCurrentSubtagEnd = -1;

    private static boolean isSubtagSeparator(char c) {
        return c == '-';
    }

    /* compiled from: LocaleIdentifier.java */
    /* loaded from: classes.dex */
    public class LocaleIdSubtagIterationFailed extends Exception {
        public LocaleIdSubtagIterationFailed() {
        }
    }

    /* compiled from: LocaleIdentifier.java */
    /* loaded from: classes.dex */
    public class LocaleIdSubtag {
        private CharSequence mLocaleIdBuffer;
        private int mSubtagEnd;
        private int mSubtagStart;

        LocaleIdSubtag(CharSequence charSequence, int r3, int r4) {
            this.mLocaleIdBuffer = charSequence;
            this.mSubtagStart = r3;
            this.mSubtagEnd = r4;
        }

        public void reset() {
            this.mLocaleIdBuffer = "";
            this.mSubtagStart = 0;
            this.mSubtagEnd = 0;
        }

        public String toString() {
            return this.mLocaleIdBuffer.subSequence(this.mSubtagStart, this.mSubtagEnd + 1).toString();
        }

        public String toLowerString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int r1 = this.mSubtagStart; r1 <= this.mSubtagEnd; r1++) {
                stringBuffer.append(Character.toLowerCase(this.mLocaleIdBuffer.charAt(r1)));
            }
            return stringBuffer.toString();
        }

        public String toUpperString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int r1 = this.mSubtagStart; r1 <= this.mSubtagEnd; r1++) {
                stringBuffer.append(Character.toUpperCase(this.mLocaleIdBuffer.charAt(r1)));
            }
            return stringBuffer.toString();
        }

        public String toTitleString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int r1 = this.mSubtagStart; r1 <= this.mSubtagEnd; r1++) {
                if (r1 == this.mSubtagStart) {
                    stringBuffer.append(Character.toUpperCase(this.mLocaleIdBuffer.charAt(r1)));
                } else {
                    stringBuffer.append(Character.toLowerCase(this.mLocaleIdBuffer.charAt(r1)));
                }
            }
            return stringBuffer.toString();
        }

        public boolean isUnicodeLanguageSubtag() {
            return IntlTextUtils.isUnicodeLanguageSubtag(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isExtensionSingleton() {
            return IntlTextUtils.isExtensionSingleton(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeScriptSubtag() {
            return IntlTextUtils.isUnicodeScriptSubtag(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeRegionSubtag() {
            return IntlTextUtils.isUnicodeRegionSubtag(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeVariantSubtag() {
            return IntlTextUtils.isUnicodeVariantSubtag(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeExtensionAttribute() {
            return IntlTextUtils.isUnicodeExtensionAttribute(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeExtensionKey() {
            return IntlTextUtils.isUnicodeExtensionKey(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isUnicodeExtensionKeyTypeItem() {
            return IntlTextUtils.isUnicodeExtensionKeyTypeItem(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isTranformedExtensionTKey() {
            return IntlTextUtils.isTranformedExtensionTKey(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isTranformedExtensionTValueItem() {
            return IntlTextUtils.isTranformedExtensionTValueItem(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isPrivateUseExtension() {
            return IntlTextUtils.isPrivateUseExtension(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }

        public boolean isOtherExtension() {
            return IntlTextUtils.isOtherExtension(this.mLocaleIdBuffer, this.mSubtagStart, this.mSubtagEnd);
        }
    }

    public LocaleIdTokenizer(CharSequence charSequence) {
        this.mLocaleIdBuffer = charSequence;
    }

    public boolean hasMoreSubtags() {
        return this.mLocaleIdBuffer.length() > 0 && this.mCurrentSubtagEnd < this.mLocaleIdBuffer.length() - 1;
    }

    public LocaleIdSubtag nextSubtag() throws LocaleIdSubtagIterationFailed {
        if (!hasMoreSubtags()) {
            throw new LocaleIdSubtagIterationFailed();
        }
        int r0 = this.mCurrentSubtagEnd;
        if (r0 >= this.mCurrentSubtagStart) {
            if (!isSubtagSeparator(this.mLocaleIdBuffer.charAt(r0 + 1))) {
                throw new LocaleIdSubtagIterationFailed();
            }
            if (this.mCurrentSubtagEnd + 2 == this.mLocaleIdBuffer.length()) {
                throw new LocaleIdSubtagIterationFailed();
            }
            this.mCurrentSubtagStart = this.mCurrentSubtagEnd + 2;
        }
        this.mCurrentSubtagEnd = this.mCurrentSubtagStart;
        while (this.mCurrentSubtagEnd < this.mLocaleIdBuffer.length() && !isSubtagSeparator(this.mLocaleIdBuffer.charAt(this.mCurrentSubtagEnd))) {
            this.mCurrentSubtagEnd++;
        }
        int r02 = this.mCurrentSubtagEnd;
        int r1 = this.mCurrentSubtagStart;
        if (r02 > r1) {
            int r03 = r02 - 1;
            this.mCurrentSubtagEnd = r03;
            return new LocaleIdSubtag(this.mLocaleIdBuffer, r1, r03);
        }
        throw new LocaleIdSubtagIterationFailed();
    }
}
