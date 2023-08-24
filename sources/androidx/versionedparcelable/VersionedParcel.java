package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class VersionedParcel {
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_FLOAT = 8;
    private static final int TYPE_INTEGER = 7;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;
    protected final ArrayMap<String, Class> mParcelizerCache;
    protected final ArrayMap<String, Method> mReadCache;
    protected final ArrayMap<String, Method> mWriteCache;

    protected abstract void closeField();

    protected abstract VersionedParcel createSubParcel();

    public boolean isStream() {
        return false;
    }

    protected abstract boolean readBoolean();

    protected abstract Bundle readBundle();

    protected abstract byte[] readByteArray();

    protected abstract CharSequence readCharSequence();

    protected abstract double readDouble();

    protected abstract boolean readField(int r1);

    protected abstract float readFloat();

    protected abstract int readInt();

    protected abstract long readLong();

    protected abstract <T extends Parcelable> T readParcelable();

    protected abstract String readString();

    protected abstract IBinder readStrongBinder();

    protected abstract void setOutputField(int r1);

    public void setSerializationFlags(boolean z, boolean z2) {
    }

    protected abstract void writeBoolean(boolean z);

    protected abstract void writeBundle(Bundle bundle);

    protected abstract void writeByteArray(byte[] bArr);

    protected abstract void writeByteArray(byte[] bArr, int r2, int r3);

    protected abstract void writeCharSequence(CharSequence charSequence);

    protected abstract void writeDouble(double d);

    protected abstract void writeFloat(float f);

    protected abstract void writeInt(int r1);

    protected abstract void writeLong(long j);

    protected abstract void writeParcelable(Parcelable parcelable);

    protected abstract void writeString(String str);

    protected abstract void writeStrongBinder(IBinder iBinder);

    protected abstract void writeStrongInterface(IInterface iInterface);

    public VersionedParcel(ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        this.mReadCache = arrayMap;
        this.mWriteCache = arrayMap2;
        this.mParcelizerCache = arrayMap3;
    }

    public void writeStrongInterface(IInterface iInterface, int r2) {
        setOutputField(r2);
        writeStrongInterface(iInterface);
    }

    public void writeBundle(Bundle bundle, int r2) {
        setOutputField(r2);
        writeBundle(bundle);
    }

    public void writeBoolean(boolean z, int r2) {
        setOutputField(r2);
        writeBoolean(z);
    }

    public void writeByteArray(byte[] bArr, int r2) {
        setOutputField(r2);
        writeByteArray(bArr);
    }

    public void writeByteArray(byte[] bArr, int r2, int r3, int r4) {
        setOutputField(r4);
        writeByteArray(bArr, r2, r3);
    }

    public void writeCharSequence(CharSequence charSequence, int r2) {
        setOutputField(r2);
        writeCharSequence(charSequence);
    }

    public void writeInt(int r1, int r2) {
        setOutputField(r2);
        writeInt(r1);
    }

    public void writeLong(long j, int r3) {
        setOutputField(r3);
        writeLong(j);
    }

    public void writeFloat(float f, int r2) {
        setOutputField(r2);
        writeFloat(f);
    }

    public void writeDouble(double d, int r3) {
        setOutputField(r3);
        writeDouble(d);
    }

    public void writeString(String str, int r2) {
        setOutputField(r2);
        writeString(str);
    }

    public void writeStrongBinder(IBinder iBinder, int r2) {
        setOutputField(r2);
        writeStrongBinder(iBinder);
    }

    public void writeParcelable(Parcelable parcelable, int r2) {
        setOutputField(r2);
        writeParcelable(parcelable);
    }

    public boolean readBoolean(boolean z, int r2) {
        return !readField(r2) ? z : readBoolean();
    }

    public int readInt(int r1, int r2) {
        return !readField(r2) ? r1 : readInt();
    }

    public long readLong(long j, int r3) {
        return !readField(r3) ? j : readLong();
    }

    public float readFloat(float f, int r2) {
        return !readField(r2) ? f : readFloat();
    }

    public double readDouble(double d, int r3) {
        return !readField(r3) ? d : readDouble();
    }

    public String readString(String str, int r2) {
        return !readField(r2) ? str : readString();
    }

    public IBinder readStrongBinder(IBinder iBinder, int r2) {
        return !readField(r2) ? iBinder : readStrongBinder();
    }

    public byte[] readByteArray(byte[] bArr, int r2) {
        return !readField(r2) ? bArr : readByteArray();
    }

    public <T extends Parcelable> T readParcelable(T t, int r2) {
        return !readField(r2) ? t : (T) readParcelable();
    }

    public Bundle readBundle(Bundle bundle, int r2) {
        return !readField(r2) ? bundle : readBundle();
    }

    public void writeByte(byte b, int r2) {
        setOutputField(r2);
        writeInt(b);
    }

    public void writeSize(Size size, int r2) {
        setOutputField(r2);
        writeBoolean(size != null);
        if (size != null) {
            writeInt(size.getWidth());
            writeInt(size.getHeight());
        }
    }

    public void writeSizeF(SizeF sizeF, int r2) {
        setOutputField(r2);
        writeBoolean(sizeF != null);
        if (sizeF != null) {
            writeFloat(sizeF.getWidth());
            writeFloat(sizeF.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int r4) {
        setOutputField(r4);
        if (sparseBooleanArray == null) {
            writeInt(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        writeInt(size);
        for (int r0 = 0; r0 < size; r0++) {
            writeInt(sparseBooleanArray.keyAt(r0));
            writeBoolean(sparseBooleanArray.valueAt(r0));
        }
    }

    public void writeBooleanArray(boolean[] zArr, int r2) {
        setOutputField(r2);
        writeBooleanArray(zArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBooleanArray(boolean[] zArr) {
        if (zArr != null) {
            writeInt(zArr.length);
            for (boolean z : zArr) {
                writeInt(z ? 1 : 0);
            }
            return;
        }
        writeInt(-1);
    }

    public boolean[] readBooleanArray(boolean[] zArr, int r2) {
        return !readField(r2) ? zArr : readBooleanArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean[] readBooleanArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        boolean[] zArr = new boolean[readInt];
        for (int r3 = 0; r3 < readInt; r3++) {
            zArr[r3] = readInt() != 0;
        }
        return zArr;
    }

    public void writeCharArray(char[] cArr, int r4) {
        setOutputField(r4);
        if (cArr != null) {
            writeInt(cArr.length);
            for (char c : cArr) {
                writeInt(c);
            }
            return;
        }
        writeInt(-1);
    }

    public CharSequence readCharSequence(CharSequence charSequence, int r2) {
        return !readField(r2) ? charSequence : readCharSequence();
    }

    public char[] readCharArray(char[] cArr, int r4) {
        if (readField(r4)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            char[] cArr2 = new char[readInt];
            for (int r0 = 0; r0 < readInt; r0++) {
                cArr2[r0] = (char) readInt();
            }
            return cArr2;
        }
        return cArr;
    }

    public void writeIntArray(int[] r1, int r2) {
        setOutputField(r2);
        writeIntArray(r1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeIntArray(int[] r4) {
        if (r4 != null) {
            writeInt(r4.length);
            for (int r2 : r4) {
                writeInt(r2);
            }
            return;
        }
        writeInt(-1);
    }

    public int[] readIntArray(int[] r1, int r2) {
        return !readField(r2) ? r1 : readIntArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] readIntArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        int[] r1 = new int[readInt];
        for (int r2 = 0; r2 < readInt; r2++) {
            r1[r2] = readInt();
        }
        return r1;
    }

    public void writeLongArray(long[] jArr, int r2) {
        setOutputField(r2);
        writeLongArray(jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeLongArray(long[] jArr) {
        if (jArr != null) {
            writeInt(jArr.length);
            for (long j : jArr) {
                writeLong(j);
            }
            return;
        }
        writeInt(-1);
    }

    public long[] readLongArray(long[] jArr, int r2) {
        return !readField(r2) ? jArr : readLongArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] readLongArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        long[] jArr = new long[readInt];
        for (int r2 = 0; r2 < readInt; r2++) {
            jArr[r2] = readLong();
        }
        return jArr;
    }

    public void writeFloatArray(float[] fArr, int r2) {
        setOutputField(r2);
        writeFloatArray(fArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeFloatArray(float[] fArr) {
        if (fArr != null) {
            writeInt(fArr.length);
            for (float f : fArr) {
                writeFloat(f);
            }
            return;
        }
        writeInt(-1);
    }

    public float[] readFloatArray(float[] fArr, int r2) {
        return !readField(r2) ? fArr : readFloatArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float[] readFloatArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        float[] fArr = new float[readInt];
        for (int r2 = 0; r2 < readInt; r2++) {
            fArr[r2] = readFloat();
        }
        return fArr;
    }

    public void writeDoubleArray(double[] dArr, int r2) {
        setOutputField(r2);
        writeDoubleArray(dArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeDoubleArray(double[] dArr) {
        if (dArr != null) {
            writeInt(dArr.length);
            for (double d : dArr) {
                writeDouble(d);
            }
            return;
        }
        writeInt(-1);
    }

    public double[] readDoubleArray(double[] dArr, int r2) {
        return !readField(r2) ? dArr : readDoubleArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] readDoubleArray() {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        double[] dArr = new double[readInt];
        for (int r2 = 0; r2 < readInt; r2++) {
            dArr[r2] = readDouble();
        }
        return dArr;
    }

    public <T> void writeSet(Set<T> set, int r2) {
        writeCollection(set, r2);
    }

    public <T> void writeList(List<T> list, int r2) {
        writeCollection(list, r2);
    }

    public <K, V> void writeMap(Map<K, V> map, int r5) {
        setOutputField(r5);
        if (map == null) {
            writeInt(-1);
            return;
        }
        int size = map.size();
        writeInt(size);
        if (size == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        writeCollection(arrayList);
        writeCollection(arrayList2);
    }

    private <T> void writeCollection(Collection<T> collection, int r2) {
        setOutputField(r2);
        writeCollection(collection);
    }

    private <T> void writeCollection(Collection<T> collection) {
        if (collection == null) {
            writeInt(-1);
            return;
        }
        int size = collection.size();
        writeInt(size);
        if (size > 0) {
            int type = getType(collection.iterator().next());
            writeInt(type);
            switch (type) {
                case 1:
                    for (T t : collection) {
                        writeVersionedParcelable(t);
                    }
                    return;
                case 2:
                    for (T t2 : collection) {
                        writeParcelable(t2);
                    }
                    return;
                case 3:
                    for (T t3 : collection) {
                        writeSerializable(t3);
                    }
                    return;
                case 4:
                    for (T t4 : collection) {
                        writeString(t4);
                    }
                    return;
                case 5:
                    for (T t5 : collection) {
                        writeStrongBinder(t5);
                    }
                    return;
                case 6:
                default:
                    return;
                case 7:
                    for (T t6 : collection) {
                        writeInt(t6.intValue());
                    }
                    return;
                case 8:
                    for (T t7 : collection) {
                        writeFloat(t7.floatValue());
                    }
                    return;
            }
        }
    }

    public <T> void writeArray(T[] tArr, int r2) {
        setOutputField(r2);
        writeArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> void writeArray(T[] tArr) {
        if (tArr == null) {
            writeInt(-1);
            return;
        }
        int length = tArr.length;
        writeInt(length);
        if (length > 0) {
            int r1 = 0;
            int type = getType(tArr[0]);
            writeInt(type);
            if (type == 1) {
                while (r1 < length) {
                    writeVersionedParcelable((VersionedParcelable) tArr[r1]);
                    r1++;
                }
            } else if (type == 2) {
                while (r1 < length) {
                    writeParcelable((Parcelable) tArr[r1]);
                    r1++;
                }
            } else if (type == 3) {
                while (r1 < length) {
                    writeSerializable((Serializable) tArr[r1]);
                    r1++;
                }
            } else if (type == 4) {
                while (r1 < length) {
                    writeString((String) tArr[r1]);
                    r1++;
                }
            } else if (type != 5) {
            } else {
                while (r1 < length) {
                    writeStrongBinder((IBinder) tArr[r1]);
                    r1++;
                }
            }
        }
    }

    private <T> int getType(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        if (t instanceof Integer) {
            return 7;
        }
        if (t instanceof Float) {
            return 8;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable, int r2) {
        setOutputField(r2);
        writeVersionedParcelable(versionedParcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        writeVersionedParcelableCreator(versionedParcelable);
        VersionedParcel createSubParcel = createSubParcel();
        writeToParcel(versionedParcelable, createSubParcel);
        createSubParcel.closeField();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    public void writeSerializable(Serializable serializable, int r2) {
        setOutputField(r2);
        writeSerializable(serializable);
    }

    private void writeSerializable(Serializable serializable) {
        if (serializable == null) {
            writeString(null);
            return;
        }
        String name = serializable.getClass().getName();
        writeString(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            writeByteArray(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", e);
        }
    }

    public void writeException(Exception exc, int r5) {
        setOutputField(r5);
        if (exc == null) {
            writeNoException();
            return;
        }
        int r52 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            r52 = -9;
        } else if (exc instanceof SecurityException) {
            r52 = -1;
        } else if (exc instanceof BadParcelableException) {
            r52 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            r52 = -3;
        } else if (exc instanceof NullPointerException) {
            r52 = -4;
        } else if (exc instanceof IllegalStateException) {
            r52 = -5;
        } else if (exc instanceof NetworkOnMainThreadException) {
            r52 = -6;
        } else if (exc instanceof UnsupportedOperationException) {
            r52 = -7;
        }
        writeInt(r52);
        if (r52 == 0) {
            if (exc instanceof RuntimeException) {
                throw ((RuntimeException) exc);
            }
            throw new RuntimeException(exc);
        }
        writeString(exc.getMessage());
        if (r52 != -9) {
            return;
        }
        writeParcelable((Parcelable) exc);
    }

    protected void writeNoException() {
        writeInt(0);
    }

    public Exception readException(Exception exc, int r2) {
        int readExceptionCode;
        return (readField(r2) && (readExceptionCode = readExceptionCode()) != 0) ? readException(readExceptionCode, readString()) : exc;
    }

    private int readExceptionCode() {
        return readInt();
    }

    private Exception readException(int r1, String str) {
        return createException(r1, str);
    }

    protected static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    private Exception createException(int r4, String str) {
        switch (r4) {
            case -9:
                return (Exception) readParcelable();
            case SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION /* -8 */:
            default:
                return new RuntimeException("Unknown exception code: " + r4 + " msg " + str);
            case -7:
                return new UnsupportedOperationException(str);
            case -6:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
        }
    }

    public byte readByte(byte b, int r2) {
        return !readField(r2) ? b : (byte) (readInt() & 255);
    }

    public Size readSize(Size size, int r3) {
        if (readField(r3)) {
            if (readBoolean()) {
                return new Size(readInt(), readInt());
            }
            return null;
        }
        return size;
    }

    public SizeF readSizeF(SizeF sizeF, int r3) {
        if (readField(r3)) {
            if (readBoolean()) {
                return new SizeF(readFloat(), readFloat());
            }
            return null;
        }
        return sizeF;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int r5) {
        if (readField(r5)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(readInt);
            for (int r0 = 0; r0 < readInt; r0++) {
                sparseBooleanArray2.put(readInt(), readBoolean());
            }
            return sparseBooleanArray2;
        }
        return sparseBooleanArray;
    }

    public <T> Set<T> readSet(Set<T> set, int r2) {
        return !readField(r2) ? set : (Set) readCollection(new ArraySet());
    }

    public <T> List<T> readList(List<T> list, int r2) {
        return !readField(r2) ? list : (List) readCollection(new ArrayList());
    }

    private <T, S extends Collection<T>> S readCollection(S s) {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        if (readInt != 0) {
            int readInt2 = readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    s.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    s.add(readParcelable());
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    s.add(readSerializable());
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    s.add(readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    s.add(readStrongBinder());
                    readInt--;
                }
            }
        }
        return s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <K, V> Map<K, V> readMap(Map<K, V> map, int r7) {
        if (readField(r7)) {
            int readInt = readInt();
            if (readInt < 0) {
                return null;
            }
            ArrayMap arrayMap = new ArrayMap();
            if (readInt == 0) {
                return arrayMap;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            readCollection(arrayList);
            readCollection(arrayList2);
            for (int r2 = 0; r2 < readInt; r2++) {
                arrayMap.put(arrayList.get(r2), arrayList2.get(r2));
            }
            return arrayMap;
        }
        return map;
    }

    public <T> T[] readArray(T[] tArr, int r2) {
        return !readField(r2) ? tArr : (T[]) readArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T[] readArray(T[] tArr) {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readInt);
        if (readInt != 0) {
            int readInt2 = readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    arrayList.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    arrayList.add(readParcelable());
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    arrayList.add(readSerializable());
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    arrayList.add(readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    arrayList.add(readStrongBinder());
                    readInt--;
                }
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int r2) {
        return !readField(r2) ? t : (T) readVersionedParcelable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        return (T) readFromParcel(readString, createSubParcel());
    }

    protected Serializable readSerializable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(readByteArray())) { // from class: androidx.versionedparcelable.VersionedParcel.1
                @Override // java.io.ObjectInputStream
                protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    return cls != null ? cls : super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + readString + ")", e);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + readString + ")", e2);
        }
    }

    protected <T extends VersionedParcelable> T readFromParcel(String str, VersionedParcel versionedParcel) {
        try {
            return (T) getReadMethod(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    protected <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        try {
            getWriteMethod(t.getClass()).invoke(null, t, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    private Method getReadMethod(String str) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.mReadCache.get(str);
        if (method == null) {
            System.currentTimeMillis();
            Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
            this.mReadCache.put(str, declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    private Method getWriteMethod(Class cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.mWriteCache.get(cls.getName());
        if (method == null) {
            Class findParcelClass = findParcelClass(cls);
            System.currentTimeMillis();
            Method declaredMethod = findParcelClass.getDeclaredMethod("write", cls, VersionedParcel.class);
            this.mWriteCache.put(cls.getName(), declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    private Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        Class cls2 = this.mParcelizerCache.get(cls.getName());
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
            this.mParcelizerCache.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    /* loaded from: classes.dex */
    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }
}
