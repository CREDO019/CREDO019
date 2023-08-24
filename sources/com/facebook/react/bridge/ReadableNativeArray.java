package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static int jniPassCounter;
    private Object[] mLocalArray;
    private ReadableType[] mLocalTypeArray;

    private native Object[] importArray();

    private native Object[] importTypeArray();

    static {
        ReactBridge.staticInit();
        jniPassCounter = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        Object[] objArr = this.mLocalArray;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            if (this.mLocalArray == null) {
                jniPassCounter++;
                this.mLocalArray = (Object[]) Assertions.assertNotNull(importArray());
            }
        }
        return this.mLocalArray;
    }

    private ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr = this.mLocalTypeArray;
        if (readableTypeArr != null) {
            return readableTypeArr;
        }
        synchronized (this) {
            if (this.mLocalTypeArray == null) {
                jniPassCounter++;
                Object[] objArr = (Object[]) Assertions.assertNotNull(importTypeArray());
                this.mLocalTypeArray = (ReadableType[]) Arrays.copyOf(objArr, objArr.length, ReadableType[].class);
            }
        }
        return this.mLocalTypeArray;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        return getLocalArray().length;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int r2) {
        return getLocalArray()[r2] == null;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int r2) {
        return ((Boolean) getLocalArray()[r2]).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int r3) {
        return ((Double) getLocalArray()[r3]).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int r2) {
        return ((Double) getLocalArray()[r2]).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int r2) {
        return (String) getLocalArray()[r2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeArray getArray(int r2) {
        return (ReadableNativeArray) getLocalArray()[r2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeMap getMap(int r2) {
        return (ReadableNativeMap) getLocalArray()[r2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int r2) {
        return getLocalTypeArray()[r2];
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int r1) {
        return DynamicFromArray.create(this, r1);
    }

    public int hashCode() {
        return getLocalArray().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ReadableNativeArray) {
            return Arrays.deepEquals(getLocalArray(), ((ReadableNativeArray) obj).getLocalArray());
        }
        return false;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int r1 = 0; r1 < size(); r1++) {
            switch (C15031.$SwitchMap$com$facebook$react$bridge$ReadableType[getType(r1).ordinal()]) {
                case 1:
                    arrayList.add(null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(getBoolean(r1)));
                    break;
                case 3:
                    arrayList.add(Double.valueOf(getDouble(r1)));
                    break;
                case 4:
                    arrayList.add(getString(r1));
                    break;
                case 5:
                    arrayList.add(getMap(r1).toHashMap());
                    break;
                case 6:
                    arrayList.add(getArray(r1).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + r1 + ".");
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.bridge.ReadableNativeArray$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C15031 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] r0 = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = r0;
            try {
                r0[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
