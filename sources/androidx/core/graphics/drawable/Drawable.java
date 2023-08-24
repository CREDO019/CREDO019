package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0003\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a2\u0010\b\u001a\u00020\t*\u00020\u00022\b\b\u0003\u0010\n\u001a\u00020\u00042\b\b\u0003\u0010\u000b\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u0004¨\u0006\u000e"}, m183d2 = {"toBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/drawable/Drawable;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "updateBounds", "", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.graphics.drawable.DrawableKt */
/* loaded from: classes.dex */
public final class Drawable {
    public static /* synthetic */ Bitmap toBitmap$default(android.graphics.drawable.Drawable drawable, int r1, int r2, Bitmap.Config config, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = drawable.getIntrinsicWidth();
        }
        if ((r4 & 2) != 0) {
            r2 = drawable.getIntrinsicHeight();
        }
        if ((r4 & 4) != 0) {
            config = null;
        }
        return toBitmap(drawable, r1, r2, config);
    }

    public static final Bitmap toBitmap(android.graphics.drawable.Drawable drawable, int r7, int r8, Bitmap.Config config) {
        Intrinsics.checkNotNullParameter(drawable, "<this>");
        if ((drawable instanceof BitmapDrawable) && (config == null || ((BitmapDrawable) drawable).getBitmap().getConfig() == config)) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (r7 == bitmapDrawable.getIntrinsicWidth() && r8 == bitmapDrawable.getIntrinsicHeight()) {
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                return bitmap;
            }
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), r7, r8, true);
            Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(bitmap, width, height, true)");
            return createScaledBitmap;
        }
        Rect bounds = drawable.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
        int r2 = bounds.left;
        int r3 = bounds.top;
        int r4 = bounds.right;
        int r0 = bounds.bottom;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap bitmap2 = Bitmap.createBitmap(r7, r8, config);
        drawable.setBounds(0, 0, r7, r8);
        drawable.draw(new Canvas(bitmap2));
        drawable.setBounds(r2, r3, r4, r0);
        Intrinsics.checkNotNullExpressionValue(bitmap2, "bitmap");
        return bitmap2;
    }

    public static /* synthetic */ void updateBounds$default(android.graphics.drawable.Drawable drawable, int r1, int r2, int r3, int r4, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            r1 = drawable.getBounds().left;
        }
        if ((r5 & 2) != 0) {
            r2 = drawable.getBounds().top;
        }
        if ((r5 & 4) != 0) {
            r3 = drawable.getBounds().right;
        }
        if ((r5 & 8) != 0) {
            r4 = drawable.getBounds().bottom;
        }
        updateBounds(drawable, r1, r2, r3, r4);
    }

    public static final void updateBounds(android.graphics.drawable.Drawable drawable, int r2, int r3, int r4, int r5) {
        Intrinsics.checkNotNullParameter(drawable, "<this>");
        drawable.setBounds(r2, r3, r4, r5);
    }
}
