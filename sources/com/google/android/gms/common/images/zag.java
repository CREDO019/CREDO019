package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zag {
    final zad zaa;
    protected int zab;

    public zag(Uri uri, int r3) {
        this.zab = 0;
        this.zaa = new zad(uri);
        this.zab = r3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zab(Context context, zam zamVar, boolean z) {
        int r2 = this.zab;
        zaa(r2 != 0 ? context.getResources().getDrawable(r2) : null, z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zac(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), false, false, true);
    }
}
