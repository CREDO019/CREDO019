package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzai extends zzn<zzy> {
    private final zzah zzef;

    public zzai(Context context, zzah zzahVar) {
        super(context, "TextNativeHandle", "ocr");
        this.zzef = zzahVar;
        zzp();
    }

    public final zzac[] zza(Bitmap bitmap, zzp zzpVar, zzae zzaeVar) {
        if (isOperational()) {
            try {
                return zzp().zza(ObjectWrapper.wrap(bitmap), zzpVar, zzaeVar);
            } catch (RemoteException e) {
                Log.e("TextNativeHandle", "Error calling native text recognizer", e);
                return new zzac[0];
            }
        }
        return new zzac[0];
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final void zzn() throws RemoteException {
        zzp().zzq();
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final /* synthetic */ zzy zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzaa zzzVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzzVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if (queryLocalInterface instanceof zzaa) {
                zzzVar = (zzaa) queryLocalInterface;
            } else {
                zzzVar = new zzz(instantiate);
            }
        }
        if (zzzVar == null) {
            return null;
        }
        return zzzVar.zza(ObjectWrapper.wrap(context), this.zzef);
    }
}
