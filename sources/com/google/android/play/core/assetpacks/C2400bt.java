package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.InterfaceC2552cj;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bt */
/* loaded from: classes3.dex */
public final class C2400bt {

    /* renamed from: a */
    private static final C2494af f544a = new C2494af("ExtractChunkTaskHandler");

    /* renamed from: b */
    private final byte[] f545b = new byte[8192];

    /* renamed from: c */
    private final C2382bb f546c;

    /* renamed from: d */
    private final InterfaceC2552cj<InterfaceC2478w> f547d;

    /* renamed from: e */
    private final InterfaceC2552cj<C2376aw> f548e;

    /* renamed from: f */
    private final C2406bz f549f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2400bt(C2382bb c2382bb, InterfaceC2552cj<InterfaceC2478w> interfaceC2552cj, InterfaceC2552cj<C2376aw> interfaceC2552cj2, C2406bz c2406bz) {
        this.f546c = c2382bb;
        this.f547d = interfaceC2552cj;
        this.f548e = interfaceC2552cj2;
        this.f549f = c2406bz;
    }

    /* renamed from: b */
    private final File m952b(C2399bs c2399bs) {
        File m1002a = this.f546c.m1002a(c2399bs.f628k, c2399bs.f535a, c2399bs.f536b, c2399bs.f537c);
        if (!m1002a.exists()) {
            m1002a.mkdirs();
        }
        return m1002a;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:5|6|(1:8)(2:112|113)|9|(2:11|(12:13|(1:(1:(2:17|(2:82|83))(2:84|85))(2:86|(10:88|(7:21|(4:22|(2:26|(1:35)(4:30|(1:32)|33|34))|36|(1:38)(1:62))|40|41|(1:43)|44|(2:46|(1:48)(2:49|(1:51)(3:52|(2:54|(1:56)(2:58|59))(1:61)|57))))|63|64|(2:76|77)|66|67|68|69|(2:71|72)(1:73))(2:89|90)))(2:91|(4:93|(4:94|(1:96)|97|(1:100)(1:106))|103|(1:105))(2:107|108))|19|(0)|63|64|(0)|66|67|68|69|(0)(0))(2:109|110))|111|(0)|63|64|(0)|66|67|68|69|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x02f0, code lost:
        com.google.android.play.core.assetpacks.C2400bt.f544a.m804d("Could not close file for chunk %s of slice %s of pack %s.", java.lang.Integer.valueOf(r23.f539e), r23.f537c, r23.f628k);
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0294 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0180 A[Catch: all -> 0x032f, TryCatch #3 {IOException -> 0x033b, blocks: (B:6:0x002f, B:89:0x028b, B:9:0x0037, B:11:0x0040, B:13:0x0046, B:15:0x0054, B:19:0x005e, B:51:0x0180, B:52:0x0189, B:54:0x0193, B:56:0x0199, B:58:0x019f, B:60:0x01a5, B:62:0x01c9, B:63:0x01d5, B:64:0x01d9, B:65:0x01e0, B:67:0x01e6, B:69:0x01ec, B:71:0x01f2, B:72:0x0202, B:74:0x0208, B:76:0x020e, B:77:0x0221, B:79:0x0227, B:80:0x0236, B:82:0x023c, B:88:0x027d, B:85:0x0264, B:86:0x026b, B:87:0x026c, B:22:0x0078, B:23:0x0081, B:24:0x0082, B:25:0x009b, B:26:0x009c, B:28:0x00c3, B:29:0x00cf, B:30:0x00d8, B:31:0x00d9, B:33:0x00f7, B:34:0x0109, B:36:0x011e, B:37:0x0123, B:42:0x0132, B:44:0x013b, B:45:0x0153, B:46:0x015c, B:47:0x015d, B:48:0x017c), top: B:116:0x002f }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m953a(com.google.android.play.core.assetpacks.C2399bs r23) {
        /*
            Method dump skipped, instructions count: 885
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.C2400bt.m953a(com.google.android.play.core.assetpacks.bs):void");
    }
}
