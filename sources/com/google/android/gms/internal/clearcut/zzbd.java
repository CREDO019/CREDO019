package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

/* loaded from: classes2.dex */
final class zzbd implements zzbf {
    private zzbd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbd(zzbc zzbcVar) {
        this();
    }

    @Override // com.google.android.gms.internal.clearcut.zzbf
    public final byte[] zzc(byte[] bArr, int r2, int r3) {
        return Arrays.copyOfRange(bArr, r2, r3 + r2);
    }
}
