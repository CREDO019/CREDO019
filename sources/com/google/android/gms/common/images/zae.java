package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class zae extends zag {
    private final WeakReference zac;

    public zae(ImageView imageView, int r3) {
        super(Uri.EMPTY, r3);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference(imageView);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zae) {
            ImageView imageView = (ImageView) this.zac.get();
            ImageView imageView2 = (ImageView) ((zae) obj).zac.get();
            return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
        }
        return false;
    }

    public final int hashCode() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.images.zag
    public final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = (ImageView) this.zac.get();
        if (imageView != null) {
            if (z2 || z3 || !(imageView instanceof zal)) {
                boolean z4 = false;
                if (!z2 && !z) {
                    z4 = true;
                }
                if (z4) {
                    Drawable drawable2 = imageView.getDrawable();
                    if (drawable2 == null) {
                        drawable2 = null;
                    } else if (drawable2 instanceof zak) {
                        drawable2 = ((zak) drawable2).zaa();
                    }
                    drawable = new zak(drawable2, drawable);
                }
                imageView.setImageDrawable(drawable);
                if (imageView instanceof zal) {
                    zal zalVar = (zal) imageView;
                    throw null;
                } else if (drawable == null || !z4) {
                    return;
                } else {
                    ((zak) drawable).zab(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                    return;
                }
            }
            zal zalVar2 = (zal) imageView;
            throw null;
        }
    }

    public zae(ImageView imageView, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference(imageView);
    }
}
