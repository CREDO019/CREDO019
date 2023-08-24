package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcgr {
    public static Context zza(Context context) throws zzcgq {
        return zzc(context).getModuleContext();
    }

    public static Object zzb(Context context, String str, zzcgp zzcgpVar) throws zzcgq {
        try {
            return zzcgpVar.zza(zzc(context).instantiate(str));
        } catch (Exception e) {
            throw new zzcgq(e);
        }
    }

    private static DynamiteModule zzc(Context context) throws zzcgq {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
        } catch (Exception e) {
            throw new zzcgq(e);
        }
    }
}
