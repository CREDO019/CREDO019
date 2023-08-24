package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefg implements zzfyk {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzefh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzefg(zzefh zzefhVar, boolean z) {
        this.zzb = zzefhVar;
        this.zza = z;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        com.google.android.gms.ads.internal.util.zze.zzg("Failed to get signals bundle");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    @Override // com.google.android.gms.internal.ads.zzfyk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ void zzb(java.lang.Object r8) {
        /*
            r7 = this;
            android.os.Bundle r8 = (android.os.Bundle) r8
            com.google.android.gms.internal.ads.zzefh r0 = r7.zzb
            boolean r0 = r0.zzf()
            if (r0 == 0) goto Lb
            return
        Lb:
            java.lang.String r0 = "ad_types"
            java.lang.Object r0 = r8.get(r0)
            boolean r1 = r0 instanceof java.util.List
            if (r1 == 0) goto L18
            java.util.List r0 = (java.util.List) r0
            goto L22
        L18:
            boolean r1 = r0 instanceof java.lang.String[]
            if (r1 == 0) goto L48
            java.lang.String[] r0 = (java.lang.String[]) r0
            java.util.List r0 = java.util.Arrays.asList(r0)
        L22:
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            r1.<init>(r2)
            java.util.Iterator r0 = r0.iterator()
        L2f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L43
            java.lang.Object r2 = r0.next()
            boolean r3 = r2 instanceof java.lang.String
            if (r3 == 0) goto L2f
            java.lang.String r2 = (java.lang.String) r2
            r1.add(r2)
            goto L2f
        L43:
            java.util.List r0 = java.util.Collections.unmodifiableList(r1)
            goto L4c
        L48:
            java.util.List r0 = java.util.Collections.emptyList()
        L4c:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r0 = r0.iterator()
        L55:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Laf
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            int r2 = r1.hashCode()
            r3 = 3
            r5 = 2
            r6 = 1
            switch(r2) {
                case -1396342996: goto L8a;
                case -1052618729: goto L80;
                case -239580146: goto L76;
                case 604727084: goto L6c;
                default: goto L6b;
            }
        L6b:
            goto L94
        L6c:
            java.lang.String r2 = "interstitial"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L94
            r1 = 1
            goto L95
        L76:
            java.lang.String r2 = "rewarded"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L94
            r1 = 3
            goto L95
        L80:
            java.lang.String r2 = "native"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L94
            r1 = 2
            goto L95
        L8a:
            java.lang.String r2 = "banner"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L94
            r1 = 0
            goto L95
        L94:
            r1 = -1
        L95:
            if (r1 == 0) goto La9
            if (r1 == r6) goto La6
            if (r1 == r5) goto La3
            if (r1 == r3) goto La0
            com.google.android.gms.internal.ads.zzbfj r1 = com.google.android.gms.internal.ads.zzbfj.AD_FORMAT_TYPE_UNSPECIFIED
            goto Lab
        La0:
            com.google.android.gms.internal.ads.zzbfj r1 = com.google.android.gms.internal.ads.zzbfj.REWARD_BASED_VIDEO_AD
            goto Lab
        La3:
            com.google.android.gms.internal.ads.zzbfj r1 = com.google.android.gms.internal.ads.zzbfj.NATIVE_APP_INSTALL
            goto Lab
        La6:
            com.google.android.gms.internal.ads.zzbfj r1 = com.google.android.gms.internal.ads.zzbfj.INTERSTITIAL
            goto Lab
        La9:
            com.google.android.gms.internal.ads.zzbfj r1 = com.google.android.gms.internal.ads.zzbfj.BANNER
        Lab:
            r4.add(r1)
            goto L55
        Laf:
            com.google.android.gms.internal.ads.zzefh r0 = r7.zzb
            com.google.android.gms.internal.ads.zzbgy r6 = com.google.android.gms.internal.ads.zzefh.zzb(r0, r8)
            com.google.android.gms.internal.ads.zzefh r0 = r7.zzb
            com.google.android.gms.internal.ads.zzbgp r5 = com.google.android.gms.internal.ads.zzefh.zza(r0, r8)
            com.google.android.gms.internal.ads.zzefh r8 = r7.zzb
            com.google.android.gms.internal.ads.zzeev r8 = r8.zza
            boolean r3 = r7.zza
            com.google.android.gms.internal.ads.zzeff r0 = new com.google.android.gms.internal.ads.zzeff
            r1 = r0
            r2 = r7
            r1.<init>()
            r8.zza(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzefg.zzb(java.lang.Object):void");
    }
}
