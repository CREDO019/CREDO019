package com.airbnb.lottie.animation;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.LocaleList;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LPaint extends Paint {
    @Override // android.graphics.Paint
    public void setTextLocales(LocaleList localeList) {
    }

    public LPaint() {
    }

    public LPaint(int r1) {
        super(r1);
    }

    public LPaint(PorterDuff.Mode mode) {
        setXfermode(new PorterDuffXfermode(mode));
    }

    public LPaint(int r1, PorterDuff.Mode mode) {
        super(r1);
        setXfermode(new PorterDuffXfermode(mode));
    }

    @Override // android.graphics.Paint
    public void setAlpha(int r5) {
        if (Build.VERSION.SDK_INT < 30) {
            setColor((MiscUtils.clamp(r5, 0, 255) << 24) | (getColor() & ViewCompat.MEASURED_SIZE_MASK));
            return;
        }
        super.setAlpha(MiscUtils.clamp(r5, 0, 255));
    }
}
