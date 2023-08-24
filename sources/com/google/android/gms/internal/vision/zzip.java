package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzip implements zzia {
    private final int flags;
    private final String info;
    private final Object[] zzyv;
    private final zzic zzyy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzip(zzic zzicVar, String str, Object[] objArr) {
        this.zzyy = zzicVar;
        this.info = str;
        this.zzyv = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int r4 = charAt & 8191;
        int r0 = 13;
        int r1 = 1;
        while (true) {
            int r2 = r1 + 1;
            char charAt2 = str.charAt(r1);
            if (charAt2 < 55296) {
                this.flags = r4 | (charAt2 << r0);
                return;
            }
            r4 |= (charAt2 & 8191) << r0;
            r0 += 13;
            r1 = r2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzhp() {
        return this.info;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zzhq() {
        return this.zzyv;
    }

    @Override // com.google.android.gms.internal.vision.zzia
    public final zzic zzhk() {
        return this.zzyy;
    }

    @Override // com.google.android.gms.internal.vision.zzia
    public final int zzhi() {
        return (this.flags & 1) == 1 ? zzgs.zzf.zzwt : zzgs.zzf.zzwu;
    }

    @Override // com.google.android.gms.internal.vision.zzia
    public final boolean zzhj() {
        return (this.flags & 2) == 2;
    }
}
