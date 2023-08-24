package com.google.android.play.core.missingsplits;

import android.content.Context;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public class MissingSplitsManagerFactory {

    /* renamed from: a */
    private static final AtomicReference<Boolean> f874a = new AtomicReference<>(null);

    public static MissingSplitsManager create(Context context) {
        return new C2583b(context, Runtime.getRuntime(), new C2582a(context, context.getPackageManager()), f874a);
    }
}
