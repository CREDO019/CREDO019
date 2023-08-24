package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgo {
    private final AudioManager zza;
    private final zzgm zzb;
    private zzgn zzc;
    private int zzd;
    private float zze = 1.0f;

    public zzgo(Context context, Handler handler, zzgn zzgnVar) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
        Objects.requireNonNull(audioManager);
        this.zza = audioManager;
        this.zzc = zzgnVar;
        this.zzb = new zzgm(this, handler);
        this.zzd = 0;
    }

    private final void zze() {
        if (this.zzd == 0) {
            return;
        }
        if (zzel.zza < 26) {
            this.zza.abandonAudioFocus(this.zzb);
        }
        zzg(0);
    }

    private final void zzf(int r4) {
        int zzah;
        zzgn zzgnVar = this.zzc;
        if (zzgnVar != null) {
            zzin zzinVar = (zzin) zzgnVar;
            boolean zzq = zzinVar.zza.zzq();
            zzir zzirVar = zzinVar.zza;
            zzah = zzir.zzah(zzq, r4);
            zzirVar.zzau(zzq, r4, zzah);
        }
    }

    private final void zzg(int r2) {
        if (this.zzd == r2) {
            return;
        }
        this.zzd = r2;
        float f = r2 == 3 ? 0.2f : 1.0f;
        if (this.zze == f) {
            return;
        }
        this.zze = f;
        zzgn zzgnVar = this.zzc;
        if (zzgnVar != null) {
            ((zzin) zzgnVar).zza.zzar();
        }
    }

    public final float zza() {
        return this.zze;
    }

    public final int zzb(boolean z, int r2) {
        zze();
        return z ? 1 : -1;
    }

    public final void zzd() {
        this.zzc = null;
        zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzc(zzgo zzgoVar, int r3) {
        if (r3 == -3 || r3 == -2) {
            if (r3 == -2) {
                zzgoVar.zzf(0);
                zzgoVar.zzg(2);
                return;
            }
            zzgoVar.zzg(3);
        } else if (r3 == -1) {
            zzgoVar.zzf(-1);
            zzgoVar.zze();
        } else if (r3 == 1) {
            zzgoVar.zzg(1);
            zzgoVar.zzf(1);
        } else {
            Log.w("AudioFocusManager", "Unknown focus change type: " + r3);
        }
    }
}
