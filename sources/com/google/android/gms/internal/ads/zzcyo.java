package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.react.uimanager.ViewProps;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyo extends FrameLayout implements ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnGlobalLayoutListener {
    private final Context zza;
    private View zzb;

    private zzcyo(Context context) {
        super(context);
        this.zza = context;
    }

    public static zzcyo zza(Context context, View view, zzfcs zzfcsVar) {
        Resources resources;
        DisplayMetrics displayMetrics;
        zzcyo zzcyoVar = new zzcyo(context);
        if (!zzfcsVar.zzv.isEmpty() && (resources = zzcyoVar.zza.getResources()) != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            zzfct zzfctVar = (zzfct) zzfcsVar.zzv.get(0);
            zzcyoVar.setLayoutParams(new FrameLayout.LayoutParams((int) (zzfctVar.zza * displayMetrics.density), (int) (zzfctVar.zzb * displayMetrics.density)));
        }
        zzcyoVar.zzb = view;
        zzcyoVar.addView(view);
        com.google.android.gms.ads.internal.zzt.zzy();
        zzchn.zzb(zzcyoVar, zzcyoVar);
        com.google.android.gms.ads.internal.zzt.zzy();
        zzchn.zza(zzcyoVar, zzcyoVar);
        JSONObject jSONObject = zzfcsVar.zzaj;
        RelativeLayout relativeLayout = new RelativeLayout(zzcyoVar.zza);
        JSONObject optJSONObject = jSONObject.optJSONObject("header");
        if (optJSONObject != null) {
            zzcyoVar.zzc(optJSONObject, relativeLayout, 10);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("footer");
        if (optJSONObject2 != null) {
            zzcyoVar.zzc(optJSONObject2, relativeLayout, 12);
        }
        zzcyoVar.addView(relativeLayout);
        return zzcyoVar;
    }

    private final int zzb(double d) {
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        return zzcgg.zzw(this.zza, (int) d);
    }

    private final void zzc(JSONObject jSONObject, RelativeLayout relativeLayout, int r9) {
        TextView textView = new TextView(this.zza);
        textView.setTextColor(-1);
        textView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setGravity(17);
        textView.setText(jSONObject.optString("text", ""));
        textView.setTextSize((float) jSONObject.optDouble("text_size", 11.0d));
        int zzb = zzb(jSONObject.optDouble(ViewProps.PADDING, 0.0d));
        textView.setPadding(0, zzb, 0, zzb);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, zzb(jSONObject.optDouble("height", 15.0d)));
        layoutParams.addRule(r9);
        relativeLayout.addView(textView, layoutParams);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int[] r0 = new int[2];
        getLocationInWindow(r0);
        this.zzb.setY(-r0[1]);
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        int[] r0 = new int[2];
        getLocationInWindow(r0);
        this.zzb.setY(-r0[1]);
    }
}
