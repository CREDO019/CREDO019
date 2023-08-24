package com.google.android.gms.internal.vision;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzav implements zzau {
    private static zzav zzfr;
    @Nullable
    private final Context zze;
    @Nullable
    private final ContentObserver zzfl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzav zze(Context context) {
        zzav zzavVar;
        synchronized (zzav.class) {
            if (zzfr == null) {
                zzfr = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzav(context) : new zzav();
            }
            zzavVar = zzfr;
        }
        return zzavVar;
    }

    private zzav(Context context) {
        this.zze = context;
        zzax zzaxVar = new zzax(this, null);
        this.zzfl = zzaxVar;
        context.getContentResolver().registerContentObserver(zzal.CONTENT_URI, true, zzaxVar);
    }

    private zzav() {
        this.zze = null;
        this.zzfl = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.vision.zzau
    /* renamed from: zzc */
    public final String zzb(final String str) {
        if (this.zze == null) {
            return null;
        }
        try {
            return (String) zzat.zza(new zzaw(this, str) { // from class: com.google.android.gms.internal.vision.zzay
                private final zzav zzfs;
                private final String zzft;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zzfs = this;
                    this.zzft = str;
                }

                @Override // com.google.android.gms.internal.vision.zzaw
                public final Object zzt() {
                    return this.zzfs.zzd(this.zzft);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zzaa() {
        Context context;
        synchronized (zzav.class) {
            zzav zzavVar = zzfr;
            if (zzavVar != null && (context = zzavVar.zze) != null && zzavVar.zzfl != null) {
                context.getContentResolver().unregisterContentObserver(zzfr.zzfl);
            }
            zzfr = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String zzd(String str) {
        return zzal.zza(this.zze.getContentResolver(), str, (String) null);
    }
}
