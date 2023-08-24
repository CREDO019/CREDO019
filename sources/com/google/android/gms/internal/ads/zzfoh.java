package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfoh {
    final zzfok zza;
    final boolean zzb;

    private zzfoh(zzfok zzfokVar) {
        this.zza = zzfokVar;
        this.zzb = zzfokVar != null;
    }

    public static zzfoh zzb(Context context, String str, String str2) {
        zzfok zzfoiVar;
        try {
            try {
                try {
                    IBinder instantiate = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.gass.internal.clearcut.GassDynamiteClearcutLogger");
                    if (instantiate == null) {
                        zzfoiVar = null;
                    } else {
                        IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.gass.internal.clearcut.IGassClearcut");
                        zzfoiVar = queryLocalInterface instanceof zzfok ? (zzfok) queryLocalInterface : new zzfoi(instantiate);
                    }
                    zzfoiVar.zze(ObjectWrapper.wrap(context), str, null);
                    Log.i("GASS", "GassClearcutLogger Initialized.");
                    return new zzfoh(zzfoiVar);
                } catch (Exception e) {
                    throw new zzfnj(e);
                }
            } catch (Exception e2) {
                throw new zzfnj(e2);
            }
        } catch (RemoteException | zzfnj | NullPointerException | SecurityException unused) {
            Log.d("GASS", "Cannot dynamite load clearcut");
            return new zzfoh(new zzfol());
        }
    }

    public static zzfoh zzc() {
        zzfol zzfolVar = new zzfol();
        Log.d("GASS", "Clearcut logging disabled");
        return new zzfoh(zzfolVar);
    }

    public final zzfog zza(byte[] bArr) {
        return new zzfog(this, bArr, null);
    }
}
