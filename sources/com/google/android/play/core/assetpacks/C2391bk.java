package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.play.core.internal.InterfaceC2552cj;
import com.google.android.play.core.tasks.Tasks;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bk */
/* loaded from: classes3.dex */
public final class C2391bk {

    /* renamed from: a */
    private final InterfaceC2552cj<InterfaceC2478w> f499a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2391bk(InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj) {
        this.f499a = interfaceC2552cj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final InputStream m969a(int r9, String str, String str2, int r12) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Tasks.await(this.f499a.m713a().mo825b(r9, str, str2, r12));
            if (parcelFileDescriptor == null || parcelFileDescriptor.getFileDescriptor() == null) {
                throw new C2402bv(String.format("Corrupted ParcelFileDescriptor, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(r9), str, str2, Integer.valueOf(r12)), r9);
            }
            return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
        } catch (InterruptedException e) {
            throw new C2402bv("Extractor was interrupted while waiting for chunk file.", e, r9);
        } catch (ExecutionException e2) {
            throw new C2402bv(String.format("Error opening chunk file, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(r9), str, str2, Integer.valueOf(r12)), e2, r9);
        }
    }
}
