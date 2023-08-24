package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.facebook.imagepipeline.common.RotationOptions;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzq {
    public static Bitmap zzb(Bitmap bitmap, zzp zzpVar) {
        int r0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (zzpVar.rotation != 0) {
            Matrix matrix = new Matrix();
            int r02 = zzpVar.rotation;
            if (r02 == 0) {
                r0 = 0;
            } else if (r02 == 1) {
                r0 = 90;
            } else if (r02 == 2) {
                r0 = RotationOptions.ROTATE_180;
            } else if (r02 != 3) {
                throw new IllegalArgumentException("Unsupported rotation degree.");
            } else {
                r0 = 270;
            }
            matrix.postRotate(r0);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        if (zzpVar.rotation == 1 || zzpVar.rotation == 3) {
            zzpVar.width = height;
            zzpVar.height = width;
        }
        return bitmap;
    }
}
