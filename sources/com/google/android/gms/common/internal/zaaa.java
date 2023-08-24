package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.C2127R;
import com.google.android.gms.common.util.DeviceProperties;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class zaaa extends Button {
    public zaaa(Context context, AttributeSet attributeSet) {
        super(context, null, 16842824);
    }

    private static final int zab(int r0, int r1, int r2, int r3) {
        if (r0 != 0) {
            if (r0 != 1) {
                if (r0 == 2) {
                    return r3;
                }
                throw new IllegalStateException("Unknown color scheme: " + r0);
            }
            return r2;
        }
        return r1;
    }

    public final void zaa(Resources resources, int r8, int r9) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int r0 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(r0);
        setMinWidth(r0);
        int zab = zab(r9, C2127R.C2129drawable.common_google_signin_btn_icon_dark, C2127R.C2129drawable.common_google_signin_btn_icon_light, C2127R.C2129drawable.common_google_signin_btn_icon_light);
        int zab2 = zab(r9, C2127R.C2129drawable.common_google_signin_btn_text_dark, C2127R.C2129drawable.common_google_signin_btn_text_light, C2127R.C2129drawable.common_google_signin_btn_text_light);
        if (r8 == 0 || r8 == 1) {
            zab = zab2;
        } else if (r8 != 2) {
            throw new IllegalStateException("Unknown button size: " + r8);
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zab));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(C2127R.C2128color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zab(r9, C2127R.C2128color.common_google_signin_btn_text_dark, C2127R.C2128color.common_google_signin_btn_text_light, C2127R.C2128color.common_google_signin_btn_text_light))));
        if (r8 == 0) {
            setText(resources.getString(C2127R.string.common_signin_button_text));
        } else if (r8 == 1) {
            setText(resources.getString(C2127R.string.common_signin_button_text_long));
        } else if (r8 == 2) {
            setText((CharSequence) null);
        } else {
            throw new IllegalStateException("Unknown button size: " + r8);
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }
}
