package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzh extends zzn<zzg> {
    private final zzf zzbl;

    public zzh(Context context, zzf zzfVar) {
        super(context, "BarcodeNativeHandle", "barcode");
        this.zzbl = zzfVar;
        zzp();
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzp zzpVar) {
        if (isOperational()) {
            try {
                return zzp().zza(ObjectWrapper.wrap(byteBuffer), zzpVar);
            } catch (RemoteException e) {
                Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
                return new Barcode[0];
            }
        }
        return new Barcode[0];
    }

    public final Barcode[] zza(Bitmap bitmap, zzp zzpVar) {
        if (isOperational()) {
            try {
                return zzp().zzb(ObjectWrapper.wrap(bitmap), zzpVar);
            } catch (RemoteException e) {
                Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
                return new Barcode[0];
            }
        }
        return new Barcode[0];
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final void zzn() throws RemoteException {
        if (isOperational()) {
            zzp().zzm();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final /* synthetic */ zzg zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzi zzkVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (instantiate == null) {
            zzkVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            if (queryLocalInterface instanceof zzi) {
                zzkVar = (zzi) queryLocalInterface;
            } else {
                zzkVar = new zzk(instantiate);
            }
        }
        if (zzkVar == null) {
            return null;
        }
        return zzkVar.zza(ObjectWrapper.wrap(context), this.zzbl);
    }
}
