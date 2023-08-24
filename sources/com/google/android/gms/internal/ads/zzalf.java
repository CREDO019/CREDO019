package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzalf extends zzaka {
    private final Object zza;
    private final zzakf zzb;

    public zzalf(int r1, String str, zzakf zzakfVar, zzake zzakeVar) {
        super(r1, str, zzakeVar);
        this.zza = new Object();
        this.zzb = zzakfVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzaka
    public final zzakg zzh(zzajw zzajwVar) {
        String str;
        String str2;
        try {
            byte[] bArr = zzajwVar.zzb;
            Map map = zzajwVar.zzc;
            String str3 = "ISO-8859-1";
            if (map != null && (str2 = (String) map.get("Content-Type")) != null) {
                String[] split = str2.split(";", 0);
                int r6 = 1;
                while (true) {
                    if (r6 >= split.length) {
                        break;
                    }
                    String[] split2 = split[r6].trim().split("=", 0);
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str3 = split2[1];
                        break;
                    }
                    r6++;
                }
            }
            str = new String(bArr, str3);
        } catch (UnsupportedEncodingException unused) {
            str = new String(zzajwVar.zzb);
        }
        return zzakg.zzb(str, zzakx.zzb(zzajwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzaka
    /* renamed from: zzz */
    public void zzo(String str) {
        zzakf zzakfVar;
        synchronized (this.zza) {
            zzakfVar = this.zzb;
        }
        zzakfVar.zza(str);
    }
}
