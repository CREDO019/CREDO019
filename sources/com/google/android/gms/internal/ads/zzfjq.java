package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjq {
    private final zzejx zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final Context zze;
    private final zzfdf zzf;
    private final zzfdg zzg;
    private final Clock zzh;
    private final zzapb zzi;

    public zzfjq(zzejx zzejxVar, zzcgt zzcgtVar, String str, String str2, Context context, zzfdf zzfdfVar, zzfdg zzfdgVar, Clock clock, zzapb zzapbVar) {
        this.zza = zzejxVar;
        this.zzb = zzcgtVar.zza;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = zzfdfVar;
        this.zzg = zzfdgVar;
        this.zzh = clock;
        this.zzi = zzapbVar;
    }

    public static final List zzf(int r3, int r4, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zzh((String) it.next(), "@gw_mpe@", "2." + r4));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzg(String str) {
        return TextUtils.isEmpty(str) ? "" : zzcgm.zzl() ? "fakeForAdDebugLog" : str;
    }

    private static String zzh(String str, String str2, String str3) {
        if (true == TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    public final List zzc(zzfde zzfdeVar, zzfcs zzfcsVar, List list) {
        return zzd(zzfdeVar, zzfcsVar, false, "", "", list);
    }

    public final List zzd(zzfde zzfdeVar, zzfcs zzfcsVar, boolean z, String str, String str2, List list) {
        ArrayList arrayList = new ArrayList();
        String str3 = true != z ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String zzh = zzh(zzh(zzh((String) it.next(), "@gw_adlocid@", zzfdeVar.zza.zza.zzf), "@gw_adnetrefresh@", str3), "@gw_sdkver@", this.zzb);
            if (zzfcsVar != null) {
                zzh = zzceu.zzc(zzh(zzh(zzh(zzh, "@gw_qdata@", zzfcsVar.zzz), "@gw_adnetid@", zzfcsVar.zzy), "@gw_allocid@", zzfcsVar.zzx), this.zze, zzfcsVar.zzX);
            }
            String zzh2 = zzh(zzh(zzh(zzh, "@gw_adnetstatus@", this.zza.zzf()), "@gw_seqnum@", this.zzc), "@gw_sessid@", this.zzd);
            boolean z2 = false;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcF)).booleanValue() && !TextUtils.isEmpty(str)) {
                z2 = true;
            }
            boolean z3 = !TextUtils.isEmpty(str2);
            if (!z2) {
                if (z3) {
                    z3 = true;
                } else {
                    arrayList.add(zzh2);
                }
            }
            if (this.zzi.zzf(Uri.parse(zzh2))) {
                Uri.Builder buildUpon = Uri.parse(zzh2).buildUpon();
                if (z2) {
                    buildUpon = buildUpon.appendQueryParameter("ms", str);
                }
                if (z3) {
                    buildUpon = buildUpon.appendQueryParameter("attok", str2);
                }
                zzh2 = buildUpon.build().toString();
            }
            arrayList.add(zzh2);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005f A[LOOP:0: B:13:0x0059->B:15:0x005f, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List zze(com.google.android.gms.internal.ads.zzfcs r10, java.util.List r11, com.google.android.gms.internal.ads.zzcbq r12) {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.google.android.gms.common.util.Clock r1 = r9.zzh
            long r1 = r1.currentTimeMillis()
            java.lang.String r3 = r12.zzc()     // Catch: android.os.RemoteException -> La8
            int r12 = r12.zzb()     // Catch: android.os.RemoteException -> La8
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch: android.os.RemoteException -> La8
            com.google.android.gms.internal.ads.zzbiq r4 = com.google.android.gms.internal.ads.zzbiy.zzcG
            com.google.android.gms.internal.ads.zzbiw r5 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r4 = r5.zzb(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L35
            com.google.android.gms.internal.ads.zzfdg r4 = r9.zzg
            if (r4 != 0) goto L32
            com.google.android.gms.internal.ads.zzfsb r4 = com.google.android.gms.internal.ads.zzfsb.zzc()
            goto L3b
        L32:
            com.google.android.gms.internal.ads.zzfdf r4 = r4.zza
            goto L37
        L35:
            com.google.android.gms.internal.ads.zzfdf r4 = r9.zzf
        L37:
            com.google.android.gms.internal.ads.zzfsb r4 = com.google.android.gms.internal.ads.zzfsb.zzd(r4)
        L3b:
            com.google.android.gms.internal.ads.zzfjo r5 = new com.google.android.gms.internal.ads.zzfru() { // from class: com.google.android.gms.internal.ads.zzfjo
                static {
                    /*
                        com.google.android.gms.internal.ads.zzfjo r0 = new com.google.android.gms.internal.ads.zzfjo
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:com.google.android.gms.internal.ads.zzfjo) com.google.android.gms.internal.ads.zzfjo.zza com.google.android.gms.internal.ads.zzfjo
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjo.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjo.<init>():void");
                }

                @Override // com.google.android.gms.internal.ads.zzfru
                public final java.lang.Object apply(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.google.android.gms.internal.ads.zzfdf r1 = (com.google.android.gms.internal.ads.zzfdf) r1
                        java.lang.String r1 = com.google.android.gms.internal.ads.zzfjq.zza(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjo.apply(java.lang.Object):java.lang.Object");
                }
            }
            com.google.android.gms.internal.ads.zzfsb r5 = r4.zza(r5)
            java.lang.String r6 = ""
            java.lang.Object r5 = r5.zzb(r6)
            java.lang.String r5 = (java.lang.String) r5
            com.google.android.gms.internal.ads.zzfjp r7 = new com.google.android.gms.internal.ads.zzfru() { // from class: com.google.android.gms.internal.ads.zzfjp
                static {
                    /*
                        com.google.android.gms.internal.ads.zzfjp r0 = new com.google.android.gms.internal.ads.zzfjp
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:com.google.android.gms.internal.ads.zzfjp) com.google.android.gms.internal.ads.zzfjp.zza com.google.android.gms.internal.ads.zzfjp
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjp.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjp.<init>():void");
                }

                @Override // com.google.android.gms.internal.ads.zzfru
                public final java.lang.Object apply(java.lang.Object r1) {
                    /*
                        r0 = this;
                        com.google.android.gms.internal.ads.zzfdf r1 = (com.google.android.gms.internal.ads.zzfdf) r1
                        java.lang.String r1 = com.google.android.gms.internal.ads.zzfjq.zzb(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjp.apply(java.lang.Object):java.lang.Object");
                }
            }
            com.google.android.gms.internal.ads.zzfsb r4 = r4.zza(r7)
            java.lang.Object r4 = r4.zzb(r6)
            java.lang.String r4 = (java.lang.String) r4
            java.util.Iterator r11 = r11.iterator()
        L59:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto La7
            java.lang.Object r6 = r11.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = android.net.Uri.encode(r5)
            java.lang.String r8 = "@gw_rwd_userid@"
            java.lang.String r6 = zzh(r6, r8, r7)
            java.lang.String r7 = android.net.Uri.encode(r4)
            java.lang.String r8 = "@gw_rwd_custom_data@"
            java.lang.String r6 = zzh(r6, r8, r7)
            java.lang.String r7 = java.lang.Long.toString(r1)
            java.lang.String r8 = "@gw_tmstmp@"
            java.lang.String r6 = zzh(r6, r8, r7)
            java.lang.String r7 = android.net.Uri.encode(r3)
            java.lang.String r8 = "@gw_rwd_itm@"
            java.lang.String r6 = zzh(r6, r8, r7)
            java.lang.String r7 = "@gw_rwd_amt@"
            java.lang.String r6 = zzh(r6, r7, r12)
            java.lang.String r7 = r9.zzb
            java.lang.String r8 = "@gw_sdkver@"
            java.lang.String r6 = zzh(r6, r8, r7)
            android.content.Context r7 = r9.zze
            boolean r8 = r10.zzX
            java.lang.String r6 = com.google.android.gms.internal.ads.zzceu.zzc(r6, r7, r8)
            r0.add(r6)
            goto L59
        La7:
            return r0
        La8:
            r10 = move-exception
            java.lang.String r11 = "Unable to determine award type and amount."
            com.google.android.gms.ads.internal.util.zze.zzh(r11, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfjq.zze(com.google.android.gms.internal.ads.zzfcs, java.util.List, com.google.android.gms.internal.ads.zzcbq):java.util.List");
    }
}
