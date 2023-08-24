package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

/* loaded from: classes.dex */
public abstract class BitmapTransformation implements Transformation<Bitmap> {
    protected abstract Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int r3, int r4);

    @Override // com.bumptech.glide.load.Transformation
    public final Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int r5, int r6) {
        if (!Util.isValidDimensions(r5, r6)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + r5 + " or height: " + r6 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Bitmap bitmap = resource.get();
        if (r5 == Integer.MIN_VALUE) {
            r5 = bitmap.getWidth();
        }
        if (r6 == Integer.MIN_VALUE) {
            r6 = bitmap.getHeight();
        }
        Bitmap transform = transform(bitmapPool, bitmap, r5, r6);
        return bitmap.equals(transform) ? resource : BitmapResource.obtain(transform, bitmapPool);
    }
}
