package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzegj implements zzbyu {
    private static zzfke zzf(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -382745961) {
            if (str.equals("htmlDisplay")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 112202875) {
            if (hashCode == 714893483 && str.equals("nativeDisplay")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("video")) {
                c = 2;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    return null;
                }
                return zzfke.VIDEO;
            }
            return zzfke.NATIVE_DISPLAY;
        }
        return zzfke.HTML_DISPLAY;
    }

    private static zzfkg zzg(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1104128070) {
            if (str.equals("beginToRender")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1318088141) {
            if (hashCode == 1988248512 && str.equals("onePixel")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("definedByJavascript")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c == 2) {
                    return zzfkg.ONE_PIXEL;
                }
                return zzfkg.UNSPECIFIED;
            }
            return zzfkg.DEFINED_BY_JAVASCRIPT;
        }
        return zzfkg.BEGIN_TO_RENDER;
    }

    private static zzfkh zzh(String str) {
        if ("native".equals(str)) {
            return zzfkh.NATIVE;
        }
        if ("javascript".equals(str)) {
            return zzfkh.JAVASCRIPT;
        }
        return zzfkh.NONE;
    }

    @Override // com.google.android.gms.internal.ads.zzbyu
    public final IObjectWrapper zza(String str, WebView webView, String str2, String str3, String str4, zzbyw zzbywVar, zzbyv zzbyvVar, String str5) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
            zzfki zza = zzfki.zza("Google", str);
            zzfkh zzh = zzh("javascript");
            zzfke zzf = zzf(zzbyvVar.toString());
            if (zzh == zzfkh.NONE) {
                com.google.android.gms.ads.internal.util.zze.zzj("Omid html session error; Unable to parse impression owner: javascript");
                return null;
            } else if (zzf == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Omid html session error; Unable to parse creative type: ".concat(String.valueOf(String.valueOf(zzbyvVar))));
                return null;
            } else {
                zzfkh zzh2 = zzh(str4);
                if (zzf != zzfke.VIDEO || zzh2 != zzfkh.NONE) {
                    return ObjectWrapper.wrap(zzfjz.zza(zzfka.zza(zzf, zzg(zzbywVar.toString()), zzh, zzh2, true), zzfkb.zzb(zza, webView, str5, "")));
                }
                com.google.android.gms.ads.internal.util.zze.zzj("Omid html session error; Video events owner unknown for video creative: ".concat(String.valueOf(str4)));
                return null;
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbyu
    public final IObjectWrapper zzb(String str, WebView webView, String str2, String str3, String str4, String str5, zzbyw zzbywVar, zzbyv zzbyvVar, String str6) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
            zzfki zza = zzfki.zza(str5, str);
            zzfkh zzh = zzh("javascript");
            zzfkh zzh2 = zzh(str4);
            zzfke zzf = zzf(zzbyvVar.toString());
            if (zzh == zzfkh.NONE) {
                com.google.android.gms.ads.internal.util.zze.zzj("Omid js session error; Unable to parse impression owner: javascript");
                return null;
            } else if (zzf == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("Omid js session error; Unable to parse creative type: ".concat(String.valueOf(String.valueOf(zzbyvVar))));
                return null;
            } else if (zzf != zzfke.VIDEO || zzh2 != zzfkh.NONE) {
                return ObjectWrapper.wrap(zzfjz.zza(zzfka.zza(zzf, zzg(zzbywVar.toString()), zzh, zzh2, true), zzfkb.zzc(zza, webView, str6, "")));
            } else {
                com.google.android.gms.ads.internal.util.zze.zzj("Omid js session error; Video events owner unknown for video creative: ".concat(String.valueOf(str4)));
                return null;
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbyu
    public final void zzc(IObjectWrapper iObjectWrapper, View view) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (unwrap instanceof zzfjz) {
                ((zzfjz) unwrap).zzd(view);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbyu
    public final void zzd(IObjectWrapper iObjectWrapper) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() && zzfjx.zzb()) {
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (unwrap instanceof zzfjz) {
                ((zzfjz) unwrap).zze();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbyu
    public final boolean zze(Context context) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue()) {
            com.google.android.gms.ads.internal.util.zze.zzj("Omid flag is disabled");
            return false;
        } else if (zzfjx.zzb()) {
            return true;
        } else {
            zzfjx.zza(context);
            return zzfjx.zzb();
        }
    }
}
