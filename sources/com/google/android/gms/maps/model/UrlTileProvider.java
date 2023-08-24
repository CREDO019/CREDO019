package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class UrlTileProvider implements TileProvider {
    private final int zza;
    private final int zzb;

    public UrlTileProvider(int r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int r6, int r7, int r8) {
        URL tileUrl = getTileUrl(r6, r7, r8);
        if (tileUrl == null) {
            return NO_TILE;
        }
        try {
            com.google.android.gms.internal.maps.zzf.zzb(4352);
            int r82 = this.zza;
            int r0 = this.zzb;
            InputStream inputStream = tileUrl.openConnection().getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Preconditions.checkNotNull(inputStream, "from must not be null.");
            Preconditions.checkNotNull(byteArrayOutputStream, "to must not be null.");
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    Tile tile = new Tile(r82, r0, byteArrayOutputStream.toByteArray());
                    com.google.android.gms.internal.maps.zzf.zza();
                    return tile;
                }
            }
        } catch (IOException unused) {
            com.google.android.gms.internal.maps.zzf.zza();
            return null;
        } catch (Throwable th) {
            com.google.android.gms.internal.maps.zzf.zza();
            throw th;
        }
    }

    public abstract URL getTileUrl(int r1, int r2, int r3);
}
