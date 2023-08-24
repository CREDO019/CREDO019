package com.google.android.gms.common.internal.safeparcel;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.core.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public class SafeParcelWriter {
    private SafeParcelWriter() {
    }

    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int r1) {
        zzb(parcel, r1);
    }

    public static void writeBigDecimal(Parcel parcel, int r1, BigDecimal bigDecimal, boolean z) {
        if (bigDecimal == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
        parcel.writeInt(bigDecimal.scale());
        zzb(parcel, zza);
    }

    public static void writeBigDecimalArray(Parcel parcel, int r3, BigDecimal[] bigDecimalArr, boolean z) {
        if (bigDecimalArr == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int length = bigDecimalArr.length;
        parcel.writeInt(length);
        for (int r0 = 0; r0 < length; r0++) {
            parcel.writeByteArray(bigDecimalArr[r0].unscaledValue().toByteArray());
            parcel.writeInt(bigDecimalArr[r0].scale());
        }
        zzb(parcel, zza);
    }

    public static void writeBigInteger(Parcel parcel, int r1, BigInteger bigInteger, boolean z) {
        if (bigInteger == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeByteArray(bigInteger.toByteArray());
        zzb(parcel, zza);
    }

    public static void writeBigIntegerArray(Parcel parcel, int r3, BigInteger[] bigIntegerArr, boolean z) {
        if (bigIntegerArr == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        parcel.writeInt(bigIntegerArr.length);
        for (BigInteger bigInteger : bigIntegerArr) {
            parcel.writeByteArray(bigInteger.toByteArray());
        }
        zzb(parcel, zza);
    }

    public static void writeBoolean(Parcel parcel, int r2, boolean z) {
        zzc(parcel, r2, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBooleanArray(Parcel parcel, int r1, boolean[] zArr, boolean z) {
        if (zArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeBooleanArray(zArr);
        zzb(parcel, zza);
    }

    public static void writeBooleanList(Parcel parcel, int r3, List<Boolean> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = list.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(list.get(r0).booleanValue() ? 1 : 0);
        }
        zzb(parcel, zza);
    }

    public static void writeBooleanObject(Parcel parcel, int r1, Boolean bool, boolean z) {
        if (bool != null) {
            zzc(parcel, r1, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            zzc(parcel, r1, 0);
        }
    }

    public static void writeBundle(Parcel parcel, int r1, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeBundle(bundle);
        zzb(parcel, zza);
    }

    public static void writeByte(Parcel parcel, int r2, byte b) {
        zzc(parcel, r2, 4);
        parcel.writeInt(b);
    }

    public static void writeByteArray(Parcel parcel, int r1, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeByteArray(bArr);
        zzb(parcel, zza);
    }

    public static void writeByteArrayArray(Parcel parcel, int r3, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            parcel.writeByteArray(bArr2);
        }
        zzb(parcel, zza);
    }

    public static void writeByteArraySparseArray(Parcel parcel, int r3, SparseArray<byte[]> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseArray.keyAt(r0));
            parcel.writeByteArray(sparseArray.valueAt(r0));
        }
        zzb(parcel, zza);
    }

    public static void writeChar(Parcel parcel, int r2, char c) {
        zzc(parcel, r2, 4);
        parcel.writeInt(c);
    }

    public static void writeCharArray(Parcel parcel, int r1, char[] cArr, boolean z) {
        if (cArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeCharArray(cArr);
        zzb(parcel, zza);
    }

    public static void writeDouble(Parcel parcel, int r2, double d) {
        zzc(parcel, r2, 8);
        parcel.writeDouble(d);
    }

    public static void writeDoubleArray(Parcel parcel, int r1, double[] dArr, boolean z) {
        if (dArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeDoubleArray(dArr);
        zzb(parcel, zza);
    }

    public static void writeDoubleList(Parcel parcel, int r4, List<Double> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = list.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeDouble(list.get(r0).doubleValue());
        }
        zzb(parcel, zza);
    }

    public static void writeDoubleObject(Parcel parcel, int r1, Double d, boolean z) {
        if (d != null) {
            zzc(parcel, r1, 8);
            parcel.writeDouble(d.doubleValue());
        } else if (z) {
            zzc(parcel, r1, 0);
        }
    }

    public static void writeDoubleSparseArray(Parcel parcel, int r4, SparseArray<Double> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseArray.keyAt(r0));
            parcel.writeDouble(sparseArray.valueAt(r0).doubleValue());
        }
        zzb(parcel, zza);
    }

    public static void writeFloat(Parcel parcel, int r2, float f) {
        zzc(parcel, r2, 4);
        parcel.writeFloat(f);
    }

    public static void writeFloatArray(Parcel parcel, int r1, float[] fArr, boolean z) {
        if (fArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeFloatArray(fArr);
        zzb(parcel, zza);
    }

    public static void writeFloatList(Parcel parcel, int r3, List<Float> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = list.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeFloat(list.get(r0).floatValue());
        }
        zzb(parcel, zza);
    }

    public static void writeFloatObject(Parcel parcel, int r1, Float f, boolean z) {
        if (f != null) {
            zzc(parcel, r1, 4);
            parcel.writeFloat(f.floatValue());
        } else if (z) {
            zzc(parcel, r1, 0);
        }
    }

    public static void writeFloatSparseArray(Parcel parcel, int r3, SparseArray<Float> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseArray.keyAt(r0));
            parcel.writeFloat(sparseArray.valueAt(r0).floatValue());
        }
        zzb(parcel, zza);
    }

    public static void writeIBinder(Parcel parcel, int r1, IBinder iBinder, boolean z) {
        if (iBinder == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeStrongBinder(iBinder);
        zzb(parcel, zza);
    }

    public static void writeIBinderArray(Parcel parcel, int r1, IBinder[] iBinderArr, boolean z) {
        if (iBinderArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeBinderArray(iBinderArr);
        zzb(parcel, zza);
    }

    public static void writeIBinderList(Parcel parcel, int r1, List<IBinder> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeBinderList(list);
        zzb(parcel, zza);
    }

    public static void writeIBinderSparseArray(Parcel parcel, int r3, SparseArray<IBinder> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseArray.keyAt(r0));
            parcel.writeStrongBinder(sparseArray.valueAt(r0));
        }
        zzb(parcel, zza);
    }

    public static void writeInt(Parcel parcel, int r2, int r3) {
        zzc(parcel, r2, 4);
        parcel.writeInt(r3);
    }

    public static void writeIntArray(Parcel parcel, int r1, int[] r2, boolean z) {
        if (r2 == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeIntArray(r2);
        zzb(parcel, zza);
    }

    public static void writeIntegerList(Parcel parcel, int r3, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = list.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(list.get(r0).intValue());
        }
        zzb(parcel, zza);
    }

    public static void writeIntegerObject(Parcel parcel, int r1, Integer num, boolean z) {
        if (num != null) {
            zzc(parcel, r1, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            zzc(parcel, r1, 0);
        }
    }

    public static void writeList(Parcel parcel, int r1, List list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeList(list);
        zzb(parcel, zza);
    }

    public static void writeLong(Parcel parcel, int r2, long j) {
        zzc(parcel, r2, 8);
        parcel.writeLong(j);
    }

    public static void writeLongArray(Parcel parcel, int r1, long[] jArr, boolean z) {
        if (jArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeLongArray(jArr);
        zzb(parcel, zza);
    }

    public static void writeLongList(Parcel parcel, int r4, List<Long> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = list.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeLong(list.get(r0).longValue());
        }
        zzb(parcel, zza);
    }

    public static void writeLongObject(Parcel parcel, int r1, Long l, boolean z) {
        if (l != null) {
            zzc(parcel, r1, 8);
            parcel.writeLong(l.longValue());
        } else if (z) {
            zzc(parcel, r1, 0);
        }
    }

    public static void writeParcel(Parcel parcel, int r2, Parcel parcel2, boolean z) {
        if (parcel2 == null) {
            if (z) {
                zzc(parcel, r2, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r2);
        parcel.appendFrom(parcel2, 0, parcel2.dataSize());
        zzb(parcel, zza);
    }

    public static void writeParcelArray(Parcel parcel, int r5, Parcel[] parcelArr, boolean z) {
        if (parcelArr == null) {
            if (z) {
                zzc(parcel, r5, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r5);
        parcel.writeInt(parcelArr.length);
        for (Parcel parcel2 : parcelArr) {
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }

    public static void writeParcelList(Parcel parcel, int r5, List<Parcel> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r5, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r5);
        int size = list.size();
        parcel.writeInt(size);
        for (int r1 = 0; r1 < size; r1++) {
            Parcel parcel2 = list.get(r1);
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }

    public static void writeParcelSparseArray(Parcel parcel, int r5, SparseArray<Parcel> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r5, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r5);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r1 = 0; r1 < size; r1++) {
            parcel.writeInt(sparseArray.keyAt(r1));
            Parcel valueAt = sparseArray.valueAt(r1);
            if (valueAt != null) {
                parcel.writeInt(valueAt.dataSize());
                parcel.appendFrom(valueAt, 0, valueAt.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }

    public static void writeParcelable(Parcel parcel, int r1, Parcelable parcelable, int r3, boolean z) {
        if (parcelable == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcelable.writeToParcel(parcel, r3);
        zzb(parcel, zza);
    }

    public static void writePendingIntent(Parcel parcel, int r1, PendingIntent pendingIntent, boolean z) {
        if (pendingIntent == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        PendingIntent.writePendingIntentOrNullToParcel(pendingIntent, parcel);
        zzb(parcel, zza);
    }

    public static void writeShort(Parcel parcel, int r2, short s) {
        zzc(parcel, r2, 4);
        parcel.writeInt(s);
    }

    public static void writeSparseBooleanArray(Parcel parcel, int r1, SparseBooleanArray sparseBooleanArray, boolean z) {
        if (sparseBooleanArray == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeSparseBooleanArray(sparseBooleanArray);
        zzb(parcel, zza);
    }

    public static void writeSparseIntArray(Parcel parcel, int r3, SparseIntArray sparseIntArray, boolean z) {
        if (sparseIntArray == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseIntArray.keyAt(r0));
            parcel.writeInt(sparseIntArray.valueAt(r0));
        }
        zzb(parcel, zza);
    }

    public static void writeSparseLongArray(Parcel parcel, int r4, SparseLongArray sparseLongArray, boolean z) {
        if (sparseLongArray == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = sparseLongArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseLongArray.keyAt(r0));
            parcel.writeLong(sparseLongArray.valueAt(r0));
        }
        zzb(parcel, zza);
    }

    public static void writeString(Parcel parcel, int r1, String str, boolean z) {
        if (str == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeString(str);
        zzb(parcel, zza);
    }

    public static void writeStringArray(Parcel parcel, int r1, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeStringArray(strArr);
        zzb(parcel, zza);
    }

    public static void writeStringList(Parcel parcel, int r1, List<String> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r1, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r1);
        parcel.writeStringList(list);
        zzb(parcel, zza);
    }

    public static void writeStringSparseArray(Parcel parcel, int r3, SparseArray<String> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r3, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r3);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            parcel.writeInt(sparseArray.keyAt(r0));
            parcel.writeString(sparseArray.valueAt(r0));
        }
        zzb(parcel, zza);
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel parcel, int r4, T[] tArr, int r6, boolean z) {
        if (tArr == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        parcel.writeInt(tArr.length);
        for (T t : tArr) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, t, r6);
            }
        }
        zzb(parcel, zza);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int r4, List<T> list, boolean z) {
        if (list == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = list.size();
        parcel.writeInt(size);
        for (int r1 = 0; r1 < size; r1++) {
            T t = list.get(r1);
            if (t == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, t, 0);
            }
        }
        zzb(parcel, zza);
    }

    public static <T extends Parcelable> void writeTypedSparseArray(Parcel parcel, int r4, SparseArray<T> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                zzc(parcel, r4, 0);
                return;
            }
            return;
        }
        int zza = zza(parcel, r4);
        int size = sparseArray.size();
        parcel.writeInt(size);
        for (int r1 = 0; r1 < size; r1++) {
            parcel.writeInt(sparseArray.keyAt(r1));
            T valueAt = sparseArray.valueAt(r1);
            if (valueAt == null) {
                parcel.writeInt(0);
            } else {
                zzd(parcel, valueAt, 0);
            }
        }
        zzb(parcel, zza);
    }

    private static int zza(Parcel parcel, int r2) {
        parcel.writeInt(r2 | SupportMenu.CATEGORY_MASK);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int r3) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(r3 - 4);
        parcel.writeInt(dataPosition - r3);
        parcel.setDataPosition(dataPosition);
    }

    private static void zzc(Parcel parcel, int r1, int r2) {
        parcel.writeInt(r1 | (r2 << 16));
    }

    private static void zzd(Parcel parcel, Parcelable parcelable, int r4) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, r4);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
}
