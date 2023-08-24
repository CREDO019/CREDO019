package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.ads.MediaContent;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public class MediaView extends FrameLayout {
    private MediaContent zza;
    private ImageView.ScaleType zzb;

    public MediaView(Context context) {
        super(context);
    }

    public void setImageScaleType(ImageView.ScaleType scaleType) {
        this.zzb = scaleType;
    }

    public void setMediaContent(MediaContent mediaContent) {
        this.zza = mediaContent;
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaView(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
    }

    public MediaView(Context context, AttributeSet attributeSet, int r3, int r4) {
        super(context, attributeSet, r3, r4);
    }
}
