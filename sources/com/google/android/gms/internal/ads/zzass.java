package com.google.android.gms.internal.ads;

import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import com.amplitude.api.Constants;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzass implements Parcelable {
    public static final Parcelable.Creator<zzass> CREATOR = new zzasr();
    private int zzA;
    public final String zza;
    public final int zzb;
    public final String zzc;
    public final zzaxd zzd;
    public final String zze;
    public final String zzf;
    public final int zzg;
    public final List zzh;
    public final zzaur zzi;
    public final int zzj;
    public final int zzk;
    public final float zzl;
    public final int zzm;
    public final float zzn;
    public final int zzo;
    public final byte[] zzp;
    public final zzbaq zzq;
    public final int zzr;
    public final int zzs;
    public final int zzt;
    public final int zzu;
    public final int zzv;
    public final long zzw;
    public final int zzx;
    public final String zzy;
    public final int zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzass(Parcel parcel) {
        this.zza = parcel.readString();
        this.zze = parcel.readString();
        this.zzf = parcel.readString();
        this.zzc = parcel.readString();
        this.zzb = parcel.readInt();
        this.zzg = parcel.readInt();
        this.zzj = parcel.readInt();
        this.zzk = parcel.readInt();
        this.zzl = parcel.readFloat();
        this.zzm = parcel.readInt();
        this.zzn = parcel.readFloat();
        this.zzp = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.zzo = parcel.readInt();
        this.zzq = (zzbaq) parcel.readParcelable(zzbaq.class.getClassLoader());
        this.zzr = parcel.readInt();
        this.zzs = parcel.readInt();
        this.zzt = parcel.readInt();
        this.zzu = parcel.readInt();
        this.zzv = parcel.readInt();
        this.zzx = parcel.readInt();
        this.zzy = parcel.readString();
        this.zzz = parcel.readInt();
        this.zzw = parcel.readLong();
        int readInt = parcel.readInt();
        this.zzh = new ArrayList(readInt);
        for (int r1 = 0; r1 < readInt; r1++) {
            this.zzh.add(parcel.createByteArray());
        }
        this.zzi = (zzaur) parcel.readParcelable(zzaur.class.getClassLoader());
        this.zzd = (zzaxd) parcel.readParcelable(zzaxd.class.getClassLoader());
    }

    public static zzass zzg(String str, String str2, String str3, int r18, int r19, int r20, int r21, List list, zzaur zzaurVar, int r24, String str4) {
        return zzh(str, str2, null, -1, -1, r20, r21, -1, -1, -1, null, zzaurVar, 0, str4, null);
    }

    public static zzass zzh(String str, String str2, String str3, int r32, int r33, int r34, int r35, int r36, int r37, int r38, List list, zzaur zzaurVar, int r41, String str4, zzaxd zzaxdVar) {
        return new zzass(str, null, str2, null, -1, r33, -1, -1, -1.0f, -1, -1.0f, null, -1, null, r34, r35, r36, -1, -1, r41, str4, -1, Long.MAX_VALUE, list, zzaurVar, null);
    }

    public static zzass zzi(String str, String str2, String str3, int r32, List list, String str4, zzaur zzaurVar) {
        return new zzass(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, str4, -1, Long.MAX_VALUE, list, zzaurVar, null);
    }

    public static zzass zzj(String str, String str2, String str3, int r32, zzaur zzaurVar) {
        return new zzass(str, null, MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, zzaurVar, null);
    }

    public static zzass zzk(String str, String str2, String str3, int r32, int r33, String str4, int r35, zzaur zzaurVar, long j, List list) {
        return new zzass(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, r33, str4, -1, j, list, zzaurVar, null);
    }

    public static zzass zzl(String str, String str2, String str3, int r32, int r33, int r34, int r35, float f, List list, int r38, float f2, byte[] bArr, int r41, zzbaq zzbaqVar, zzaur zzaurVar) {
        return new zzass(str, null, str2, null, -1, r33, r34, r35, -1.0f, r38, f2, bArr, r41, zzbaqVar, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, list, zzaurVar, null);
    }

    private static void zzm(MediaFormat mediaFormat, String str, int r3) {
        if (r3 != -1) {
            mediaFormat.setInteger(str, r3);
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzass zzassVar = (zzass) obj;
            if (this.zzb == zzassVar.zzb && this.zzg == zzassVar.zzg && this.zzj == zzassVar.zzj && this.zzk == zzassVar.zzk && this.zzl == zzassVar.zzl && this.zzm == zzassVar.zzm && this.zzn == zzassVar.zzn && this.zzo == zzassVar.zzo && this.zzr == zzassVar.zzr && this.zzs == zzassVar.zzs && this.zzt == zzassVar.zzt && this.zzu == zzassVar.zzu && this.zzv == zzassVar.zzv && this.zzw == zzassVar.zzw && this.zzx == zzassVar.zzx && zzban.zzo(this.zza, zzassVar.zza) && zzban.zzo(this.zzy, zzassVar.zzy) && this.zzz == zzassVar.zzz && zzban.zzo(this.zze, zzassVar.zze) && zzban.zzo(this.zzf, zzassVar.zzf) && zzban.zzo(this.zzc, zzassVar.zzc) && zzban.zzo(this.zzi, zzassVar.zzi) && zzban.zzo(this.zzd, zzassVar.zzd) && zzban.zzo(this.zzq, zzassVar.zzq) && Arrays.equals(this.zzp, zzassVar.zzp) && this.zzh.size() == zzassVar.zzh.size()) {
                for (int r2 = 0; r2 < this.zzh.size(); r2++) {
                    if (!Arrays.equals((byte[]) this.zzh.get(r2), (byte[]) zzassVar.zzh.get(r2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zze;
        String str3 = this.zzf;
        int r3 = this.zzb;
        String str4 = this.zzy;
        int r5 = this.zzj;
        int r6 = this.zzk;
        float f = this.zzl;
        int r8 = this.zzr;
        int r9 = this.zzs;
        return "Format(" + str + ", " + str2 + ", " + str3 + ", " + r3 + ", " + str4 + ", [" + r5 + ", " + r6 + ", " + f + "], [" + r8 + ", " + r9 + "])";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zze);
        parcel.writeString(this.zzf);
        parcel.writeString(this.zzc);
        parcel.writeInt(this.zzb);
        parcel.writeInt(this.zzg);
        parcel.writeInt(this.zzj);
        parcel.writeInt(this.zzk);
        parcel.writeFloat(this.zzl);
        parcel.writeInt(this.zzm);
        parcel.writeFloat(this.zzn);
        parcel.writeInt(this.zzp != null ? 1 : 0);
        byte[] bArr = this.zzp;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.zzo);
        parcel.writeParcelable(this.zzq, r6);
        parcel.writeInt(this.zzr);
        parcel.writeInt(this.zzs);
        parcel.writeInt(this.zzt);
        parcel.writeInt(this.zzu);
        parcel.writeInt(this.zzv);
        parcel.writeInt(this.zzx);
        parcel.writeString(this.zzy);
        parcel.writeInt(this.zzz);
        parcel.writeLong(this.zzw);
        int size = this.zzh.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeByteArray((byte[]) this.zzh.get(r0));
        }
        parcel.writeParcelable(this.zzi, 0);
        parcel.writeParcelable(this.zzd, 0);
    }

    public final int zza() {
        int r2;
        int r0 = this.zzj;
        if (r0 == -1 || (r2 = this.zzk) == -1) {
            return -1;
        }
        return r0 * r2;
    }

    public final MediaFormat zzb() {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", this.zzf);
        String str = this.zzy;
        if (str != null) {
            mediaFormat.setString(Constants.AMP_TRACKING_OPTION_LANGUAGE, str);
        }
        zzm(mediaFormat, "max-input-size", this.zzg);
        zzm(mediaFormat, "width", this.zzj);
        zzm(mediaFormat, "height", this.zzk);
        float f = this.zzl;
        if (f != -1.0f) {
            mediaFormat.setFloat("frame-rate", f);
        }
        zzm(mediaFormat, "rotation-degrees", this.zzm);
        zzm(mediaFormat, "channel-count", this.zzr);
        zzm(mediaFormat, "sample-rate", this.zzs);
        zzm(mediaFormat, "encoder-delay", this.zzu);
        zzm(mediaFormat, "encoder-padding", this.zzv);
        for (int r1 = 0; r1 < this.zzh.size(); r1++) {
            mediaFormat.setByteBuffer("csd-" + r1, ByteBuffer.wrap((byte[]) this.zzh.get(r1)));
        }
        zzbaq zzbaqVar = this.zzq;
        if (zzbaqVar != null) {
            zzm(mediaFormat, "color-transfer", zzbaqVar.zzc);
            zzm(mediaFormat, "color-standard", zzbaqVar.zza);
            zzm(mediaFormat, "color-range", zzbaqVar.zzb);
            byte[] bArr = zzbaqVar.zzd;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
        return mediaFormat;
    }

    public final zzass zzc(zzaur zzaurVar) {
        return new zzass(this.zza, this.zze, this.zzf, this.zzc, this.zzb, this.zzg, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzp, this.zzo, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu, this.zzv, this.zzx, this.zzy, this.zzz, this.zzw, this.zzh, zzaurVar, this.zzd);
    }

    public final zzass zzd(int r31, int r32) {
        return new zzass(this.zza, this.zze, this.zzf, this.zzc, this.zzb, this.zzg, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzp, this.zzo, this.zzq, this.zzr, this.zzs, this.zzt, r31, r32, this.zzx, this.zzy, this.zzz, this.zzw, this.zzh, this.zzi, this.zzd);
    }

    public final zzass zze(int r32) {
        return new zzass(this.zza, this.zze, this.zzf, this.zzc, this.zzb, r32, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzp, this.zzo, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu, this.zzv, this.zzx, this.zzy, this.zzz, this.zzw, this.zzh, this.zzi, this.zzd);
    }

    public final zzass zzf(zzaxd zzaxdVar) {
        return new zzass(this.zza, this.zze, this.zzf, this.zzc, this.zzb, this.zzg, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzp, this.zzo, this.zzq, this.zzr, this.zzs, this.zzt, this.zzu, this.zzv, this.zzx, this.zzy, this.zzz, this.zzw, this.zzh, this.zzi, zzaxdVar);
    }

    public final int hashCode() {
        int r0 = this.zzA;
        if (r0 == 0) {
            String str = this.zza;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 527) * 31;
            String str2 = this.zze;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.zzf;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.zzc;
            int hashCode4 = (((((((((((hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.zzb) * 31) + this.zzj) * 31) + this.zzk) * 31) + this.zzr) * 31) + this.zzs) * 31;
            String str5 = this.zzy;
            int hashCode5 = (((hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.zzz) * 31;
            zzaur zzaurVar = this.zzi;
            int hashCode6 = (hashCode5 + (zzaurVar == null ? 0 : zzaurVar.hashCode())) * 31;
            zzaxd zzaxdVar = this.zzd;
            int hashCode7 = hashCode6 + (zzaxdVar != null ? zzaxdVar.hashCode() : 0);
            this.zzA = hashCode7;
            return hashCode7;
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzass(String str, String str2, String str3, String str4, int r8, int r9, int r10, int r11, float f, int r13, float f2, byte[] bArr, int r16, zzbaq zzbaqVar, int r18, int r19, int r20, int r21, int r22, int r23, String str5, int r25, long j, List list, zzaur zzaurVar, zzaxd zzaxdVar) {
        this.zza = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzc = str4;
        this.zzb = r8;
        this.zzg = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = f;
        this.zzm = r13;
        this.zzn = f2;
        this.zzp = bArr;
        this.zzo = r16;
        this.zzq = zzbaqVar;
        this.zzr = r18;
        this.zzs = r19;
        this.zzt = r20;
        this.zzu = r21;
        this.zzv = r22;
        this.zzx = r23;
        this.zzy = str5;
        this.zzz = r25;
        this.zzw = j;
        this.zzh = list == null ? Collections.emptyList() : list;
        this.zzi = zzaurVar;
        this.zzd = zzaxdVar;
    }
}
