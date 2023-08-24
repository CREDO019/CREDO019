package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.google.android.exoplayer2.PlaybackException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeo extends zzep {
    private final AssetManager zza;
    private Uri zzb;
    private InputStream zzc;
    private long zzd;
    private boolean zze;

    public zzeo(Context context) {
        super(false);
        this.zza = context.getAssets();
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r9, int r10) throws zzen {
        if (r10 == 0) {
            return 0;
        }
        long j = this.zzd;
        if (j != 0) {
            if (j != -1) {
                try {
                    r10 = (int) Math.min(j, r10);
                } catch (IOException e) {
                    throw new zzen(e, 2000);
                }
            }
            InputStream inputStream = this.zzc;
            int r1 = zzel.zza;
            int read = inputStream.read(bArr, r9, r10);
            if (read == -1) {
                return -1;
            }
            long j2 = this.zzd;
            if (j2 != -1) {
                this.zzd = j2 - read;
            }
            zzg(read);
            return read;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws zzen {
        try {
            Uri uri = zzfaVar.zza;
            this.zzb = uri;
            String path = uri.getPath();
            Objects.requireNonNull(path);
            if (path.startsWith("/android_asset/")) {
                path = path.substring(15);
            } else if (path.startsWith("/")) {
                path = path.substring(1);
            }
            zzi(zzfaVar);
            InputStream open = this.zza.open(path, 1);
            this.zzc = open;
            if (open.skip(zzfaVar.zzf) < zzfaVar.zzf) {
                throw new zzen(null, 2008);
            }
            long j = zzfaVar.zzg;
            if (j != -1) {
                this.zzd = j;
            } else {
                long available = this.zzc.available();
                this.zzd = available;
                if (available == 2147483647L) {
                    this.zzd = -1L;
                }
            }
            this.zze = true;
            zzj(zzfaVar);
            return this.zzd;
        } catch (zzen e) {
            throw e;
        } catch (IOException e2) {
            throw new zzen(e2, true != (e2 instanceof FileNotFoundException) ? 2000 : PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() throws zzen {
        this.zzb = null;
        try {
            try {
                InputStream inputStream = this.zzc;
                if (inputStream != null) {
                    inputStream.close();
                }
                this.zzc = null;
                if (this.zze) {
                    this.zze = false;
                    zzh();
                }
            } catch (IOException e) {
                throw new zzen(e, 2000);
            }
        } catch (Throwable th) {
            this.zzc = null;
            if (this.zze) {
                this.zze = false;
                zzh();
            }
            throw th;
        }
    }
}
