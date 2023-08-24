package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzbe;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.internal.vision.zzp;
import com.google.android.gms.internal.vision.zzr;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzb extends zzn<zzh> {
    private final zzf zzcy;

    public zzb(Context context, zzf zzfVar) {
        super(context, "FaceNativeHandle", "face");
        zzbe.init(context);
        this.zzcy = zzfVar;
        zzp();
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzp zzpVar) {
        Landmark[] landmarkArr;
        FaceParcel[] faceParcelArr;
        Face[] faceArr;
        Contour[] contourArr;
        int r1 = 0;
        if (isOperational()) {
            try {
                FaceParcel[] zzc = zzp().zzc(ObjectWrapper.wrap(byteBuffer), zzpVar);
                Face[] faceArr2 = new Face[zzc.length];
                int r3 = 0;
                while (r3 < zzc.length) {
                    FaceParcel faceParcel = zzc[r3];
                    int r6 = faceParcel.f269id;
                    PointF pointF = new PointF(faceParcel.centerX, faceParcel.centerY);
                    float f = faceParcel.width;
                    float f2 = faceParcel.height;
                    float f3 = faceParcel.zzcz;
                    float f4 = faceParcel.zzda;
                    float f5 = faceParcel.zzdb;
                    LandmarkParcel[] landmarkParcelArr = faceParcel.zzdc;
                    if (landmarkParcelArr == null) {
                        faceParcelArr = zzc;
                        faceArr = faceArr2;
                        landmarkArr = new Landmark[r1];
                    } else {
                        landmarkArr = new Landmark[landmarkParcelArr.length];
                        int r14 = 0;
                        while (r14 < landmarkParcelArr.length) {
                            LandmarkParcel landmarkParcel = landmarkParcelArr[r14];
                            landmarkArr[r14] = new Landmark(new PointF(landmarkParcel.f270x, landmarkParcel.f271y), landmarkParcel.type);
                            r14++;
                            zzc = zzc;
                            faceArr2 = faceArr2;
                            landmarkParcelArr = landmarkParcelArr;
                        }
                        faceParcelArr = zzc;
                        faceArr = faceArr2;
                    }
                    zza[] zzaVarArr = faceParcel.zzdd;
                    if (zzaVarArr == null) {
                        contourArr = new Contour[0];
                    } else {
                        Contour[] contourArr2 = new Contour[zzaVarArr.length];
                        for (int r2 = 0; r2 < zzaVarArr.length; r2++) {
                            zza zzaVar = zzaVarArr[r2];
                            contourArr2[r2] = new Contour(zzaVar.zzcx, zzaVar.type);
                        }
                        contourArr = contourArr2;
                    }
                    faceArr[r3] = new Face(r6, pointF, f, f2, f3, f4, f5, landmarkArr, contourArr, faceParcel.zzce, faceParcel.zzcf, faceParcel.zzcg, faceParcel.zzch);
                    r3++;
                    zzc = faceParcelArr;
                    faceArr2 = faceArr;
                    r1 = 0;
                }
                return faceArr2;
            } catch (RemoteException e) {
                Log.e("FaceNativeHandle", "Could not call native face detector", e);
                return new Face[0];
            }
        }
        return new Face[0];
    }

    public final boolean zzd(int r4) {
        if (isOperational()) {
            try {
                return zzp().zzd(r4);
            } catch (RemoteException e) {
                Log.e("FaceNativeHandle", "Could not call native face detector", e);
                return false;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final void zzn() throws RemoteException {
        zzp().zzm();
    }

    @Override // com.google.android.gms.internal.vision.zzn
    protected final /* synthetic */ zzh zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzi asInterface;
        if (zzr.zza(context, "com.google.android.gms.vision.dynamite.face")) {
            asInterface = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            asInterface = zzl.asInterface(dynamiteModule.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        if (asInterface == null) {
            return null;
        }
        return asInterface.newFaceDetector(ObjectWrapper.wrap(context), this.zzcy);
    }
}
