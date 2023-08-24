package com.google.android.play.core.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import com.google.android.play.core.splitcompat.C2608p;

/* renamed from: com.google.android.play.core.internal.by */
/* loaded from: classes3.dex */
public final class C2540by {

    /* renamed from: a */
    private static final C2494af f842a = new C2494af("PhoneskyVerificationUtils");

    /* renamed from: a */
    public static boolean m723a(Context context) {
        Signature[] signatureArr;
        try {
            signatureArr = context.getPackageManager().getPackageInfo("com.android.vending", 64).signatures;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (signatureArr == null || (r1 = signatureArr.length) == 0) {
            f842a.m804d("Phonesky package is not signed -- possibly self-built package. Could not verify.", new Object[0]);
            return false;
        }
        for (Signature signature : signatureArr) {
            String m575a = C2608p.m575a(signature.toByteArray());
            if ("8P1sW0EPJcslw7UzRsiXL64w-O50Ed-RBICtay1g24M".equals(m575a)) {
                return true;
            }
            if ((Build.TAGS.contains("dev-keys") || Build.TAGS.contains("test-keys")) && "GXWy8XF3vIml3_MfnmSmyuKBpT3B0dWbHRR_4cgq-gA".equals(m575a)) {
                return true;
            }
        }
        return false;
    }
}
