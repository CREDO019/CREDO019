package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.facebook.common.util.UriUtil;

/* loaded from: classes.dex */
public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final BitmapPool bitmapPool;
    private final ResourceDrawableDecoder drawableDecoder;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool) {
        this.drawableDecoder = resourceDrawableDecoder;
        this.bitmapPool = bitmapPool;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(Uri uri, Options options) {
        return UriUtil.QUALIFIED_RESOURCE_SCHEME.equals(uri.getScheme());
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(Uri uri, int r3, int r4, Options options) {
        Resource<Drawable> decode = this.drawableDecoder.decode(uri, r3, r4, options);
        if (decode == null) {
            return null;
        }
        return DrawableToBitmapConverter.convert(this.bitmapPool, decode.get(), r3, r4);
    }
}