package com.google.android.play.core.internal;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* renamed from: com.google.android.play.core.internal.bx */
/* loaded from: classes3.dex */
public final class C2539bx {

    /* renamed from: a */
    private final Context f841a;

    public C2539bx(Context context) {
        this.f841a = context;
    }

    /* renamed from: a */
    private static String m724a(Locale locale) {
        String concat;
        String valueOf = String.valueOf(locale.getLanguage());
        if (locale.getCountry().isEmpty()) {
            concat = "";
        } else {
            String valueOf2 = String.valueOf(locale.getCountry());
            concat = valueOf2.length() != 0 ? "_".concat(valueOf2) : new String("_");
        }
        String valueOf3 = String.valueOf(concat);
        return valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf);
    }

    /* renamed from: a */
    public final List<String> m725a() {
        Configuration configuration = this.f841a.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = configuration.getLocales();
            ArrayList arrayList = new ArrayList(locales.size());
            for (int r2 = 0; r2 < locales.size(); r2++) {
                arrayList.add(m724a(locales.get(r2)));
            }
            return arrayList;
        }
        return Collections.singletonList(m724a(configuration.locale));
    }
}
