package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzblk extends RelativeLayout {
    private static final float[] zza = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private AnimationDrawable zzb;

    public zzblk(Context context, zzblj zzbljVar, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzbljVar);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zza, null, null));
        shapeDrawable.getPaint().setColor(zzbljVar.zzd());
        setLayoutParams(layoutParams);
        setBackground(shapeDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzbljVar.zzg())) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzbljVar.zzg());
            textView.setTextColor(zzbljVar.zze());
            textView.setTextSize(zzbljVar.zzf());
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            int zzw = zzcgg.zzw(context, 4);
            com.google.android.gms.ads.internal.client.zzaw.zzb();
            textView.setPadding(zzw, 0, zzcgg.zzw(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzblm> zzi = zzbljVar.zzi();
        if (zzi == null || zzi.size() <= 1) {
            if (zzi.size() == 1) {
                try {
                    imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(((zzblm) zzi.get(0)).zzf()));
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.util.zze.zzh("Error while getting drawable.", e);
                }
            }
        } else {
            this.zzb = new AnimationDrawable();
            for (zzblm zzblmVar : zzi) {
                try {
                    this.zzb.addFrame((Drawable) ObjectWrapper.unwrap(zzblmVar.zzf()), zzbljVar.zzb());
                } catch (Exception e2) {
                    com.google.android.gms.ads.internal.util.zze.zzh("Error while getting drawable.", e2);
                }
            }
            imageView.setBackground(this.zzb);
        }
        addView(imageView);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzb;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
