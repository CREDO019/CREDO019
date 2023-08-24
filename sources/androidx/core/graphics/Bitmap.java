package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a)\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\bø\u0001\u0000\u001a\u0015\u0010\u0011\u001a\u00020\b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\n\u001a\u0015\u0010\u0011\u001a\u00020\b*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014H\u0086\n\u001a\u001d\u0010\u0015\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0086\n\u001a'\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0086\b\u001a'\u0010\u001a\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u0003H\u0086\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, m183d2 = {"createBitmap", "Landroid/graphics/Bitmap;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "hasAlpha", "", "colorSpace", "Landroid/graphics/ColorSpace;", "applyCanvas", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "contains", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "get", "x", "y", "scale", "filter", "set", "color", "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.graphics.BitmapKt */
/* loaded from: classes.dex */
public final class Bitmap {
    public static final android.graphics.Bitmap applyCanvas(android.graphics.Bitmap bitmap, Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        block.invoke(new Canvas(bitmap));
        return bitmap;
    }

    public static final int get(android.graphics.Bitmap bitmap, int r2, int r3) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        return bitmap.getPixel(r2, r3);
    }

    public static final void set(android.graphics.Bitmap bitmap, int r2, int r3, int r4) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        bitmap.setPixel(r2, r3, r4);
    }

    public static /* synthetic */ android.graphics.Bitmap scale$default(android.graphics.Bitmap bitmap, int r1, int r2, boolean z, int r4, Object obj) {
        if ((r4 & 4) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        android.graphics.Bitmap createScaledBitmap = android.graphics.Bitmap.createScaledBitmap(bitmap, r1, r2, z);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(this, width, height, filter)");
        return createScaledBitmap;
    }

    public static final android.graphics.Bitmap scale(android.graphics.Bitmap bitmap, int r2, int r3, boolean z) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        android.graphics.Bitmap createScaledBitmap = android.graphics.Bitmap.createScaledBitmap(bitmap, r2, r3, z);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(this, width, height, filter)");
        return createScaledBitmap;
    }

    public static /* synthetic */ android.graphics.Bitmap createBitmap$default(int r0, int r1, Bitmap.Config config, int r3, Object obj) {
        if ((r3 & 4) != 0) {
            config = Bitmap.Config.ARGB_8888;
        }
        Intrinsics.checkNotNullParameter(config, "config");
        android.graphics.Bitmap createBitmap = android.graphics.Bitmap.createBitmap(r0, r1, config);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, height, config)");
        return createBitmap;
    }

    public static final android.graphics.Bitmap createBitmap(int r1, int r2, Bitmap.Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        android.graphics.Bitmap createBitmap = android.graphics.Bitmap.createBitmap(r1, r2, config);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, height, config)");
        return createBitmap;
    }

    public static /* synthetic */ android.graphics.Bitmap createBitmap$default(int r0, int r1, Bitmap.Config config, boolean z, ColorSpace colorSpace, int r5, Object obj) {
        if ((r5 & 4) != 0) {
            config = Bitmap.Config.ARGB_8888;
        }
        if ((r5 & 8) != 0) {
            z = true;
        }
        if ((r5 & 16) != 0) {
            colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            Intrinsics.checkNotNullExpressionValue(colorSpace, "get(ColorSpace.Named.SRGB)");
        }
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        android.graphics.Bitmap createBitmap = android.graphics.Bitmap.createBitmap(r0, r1, config, z, colorSpace);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, height, config, hasAlpha, colorSpace)");
        return createBitmap;
    }

    public static final android.graphics.Bitmap createBitmap(int r1, int r2, Bitmap.Config config, boolean z, ColorSpace colorSpace) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        android.graphics.Bitmap createBitmap = android.graphics.Bitmap.createBitmap(r1, r2, config, z, colorSpace);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, height, config, hasAlpha, colorSpace)");
        return createBitmap;
    }

    public static final boolean contains(android.graphics.Bitmap bitmap, Point p) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return p.x >= 0 && p.x < bitmap.getWidth() && p.y >= 0 && p.y < bitmap.getHeight();
    }

    public static final boolean contains(android.graphics.Bitmap bitmap, PointF p) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return p.x >= 0.0f && p.x < ((float) bitmap.getWidth()) && p.y >= 0.0f && p.y < ((float) bitmap.getHeight());
    }
}
