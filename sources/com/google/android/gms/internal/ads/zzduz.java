package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzduz extends FrameLayout {
    private final com.google.android.gms.ads.internal.util.zzas zza;

    public zzduz(Context context, View view, com.google.android.gms.ads.internal.util.zzas zzasVar) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(view);
        this.zza = zzasVar;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.zza.zzm(motionEvent);
        return false;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < getChildCount(); r2++) {
            View childAt = getChildAt(r2);
            if (childAt != null && (childAt instanceof zzcmn)) {
                arrayList.add((zzcmn) childAt);
            }
        }
        super.removeAllViews();
        int size = arrayList.size();
        for (int r1 = 0; r1 < size; r1++) {
            ((zzcmn) arrayList.get(r1)).destroy();
        }
    }
}
