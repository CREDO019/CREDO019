package com.google.android.play.core.splitinstall;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class SplitInstallRequest {

    /* renamed from: a */
    private final List<String> f932a;

    /* renamed from: b */
    private final List<Locale> f933b;

    /* loaded from: classes3.dex */
    public static class Builder {

        /* renamed from: a */
        private final List<String> f934a = new ArrayList();

        /* renamed from: b */
        private final List<Locale> f935b = new ArrayList();

        private Builder() {
        }

        /* synthetic */ Builder(byte[] bArr) {
        }

        public Builder addLanguage(Locale locale) {
            this.f935b.add(locale);
            return this;
        }

        public Builder addModule(String str) {
            this.f934a.add(str);
            return this;
        }

        public SplitInstallRequest build() {
            return new SplitInstallRequest(this);
        }
    }

    /* synthetic */ SplitInstallRequest(Builder builder) {
        this.f932a = new ArrayList(builder.f934a);
        this.f933b = new ArrayList(builder.f935b);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public List<Locale> getLanguages() {
        return this.f933b;
    }

    public List<String> getModuleNames() {
        return this.f932a;
    }

    public String toString() {
        return String.format("SplitInstallRequest{modulesNames=%s,languages=%s}", this.f932a, this.f933b);
    }
}
