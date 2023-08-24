package com.google.android.gms.internal.ads;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfkb {
    private final zzfki zza;
    private final WebView zzb;
    private final List zzc = new ArrayList();
    private final Map zzd = new HashMap();
    private final String zze = "";
    private final String zzf;
    private final zzfkc zzg;

    private zzfkb(zzfki zzfkiVar, WebView webView, String str, List list, String str2, String str3, zzfkc zzfkcVar) {
        this.zza = zzfkiVar;
        this.zzb = webView;
        this.zzg = zzfkcVar;
        this.zzf = str2;
    }

    public static zzfkb zzb(zzfki zzfkiVar, WebView webView, String str, String str2) {
        return new zzfkb(zzfkiVar, webView, null, null, str, "", zzfkc.HTML);
    }

    public static zzfkb zzc(zzfki zzfkiVar, WebView webView, String str, String str2) {
        return new zzfkb(zzfkiVar, webView, null, null, str, "", zzfkc.JAVASCRIPT);
    }

    public final WebView zza() {
        return this.zzb;
    }

    public final zzfkc zzd() {
        return this.zzg;
    }

    public final zzfki zze() {
        return this.zza;
    }

    public final String zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zze;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final Map zzi() {
        return Collections.unmodifiableMap(this.zzd);
    }
}
