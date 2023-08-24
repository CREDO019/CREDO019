package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class JavaOnlyArray implements ReadableArray, WritableArray {
    private final List mBackingList;

    public static JavaOnlyArray from(List list) {
        return new JavaOnlyArray(list);
    }

    /* renamed from: of */
    public static JavaOnlyArray m1264of(Object... objArr) {
        return new JavaOnlyArray(objArr);
    }

    public static JavaOnlyArray deepClone(ReadableArray readableArray) {
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        int size = readableArray.size();
        for (int r2 = 0; r2 < size; r2++) {
            switch (C14971.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(r2).ordinal()]) {
                case 1:
                    javaOnlyArray.pushNull();
                    break;
                case 2:
                    javaOnlyArray.pushBoolean(readableArray.getBoolean(r2));
                    break;
                case 3:
                    javaOnlyArray.pushDouble(readableArray.getDouble(r2));
                    break;
                case 4:
                    javaOnlyArray.pushString(readableArray.getString(r2));
                    break;
                case 5:
                    javaOnlyArray.pushMap(JavaOnlyMap.deepClone(readableArray.getMap(r2)));
                    break;
                case 6:
                    javaOnlyArray.pushArray(deepClone(readableArray.getArray(r2)));
                    break;
            }
        }
        return javaOnlyArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.bridge.JavaOnlyArray$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C14971 {
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

    private JavaOnlyArray(Object... objArr) {
        this.mBackingList = Arrays.asList(objArr);
    }

    private JavaOnlyArray(List list) {
        this.mBackingList = new ArrayList(list);
    }

    public JavaOnlyArray() {
        this.mBackingList = new ArrayList();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        return this.mBackingList.size();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int r2) {
        return this.mBackingList.get(r2) == null;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int r3) {
        return ((Number) this.mBackingList.get(r3)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int r2) {
        return ((Number) this.mBackingList.get(r2)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int r2) {
        return (String) this.mBackingList.get(r2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableArray getArray(int r2) {
        return (ReadableArray) this.mBackingList.get(r2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int r2) {
        return ((Boolean) this.mBackingList.get(r2)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableMap getMap(int r2) {
        return (ReadableMap) this.mBackingList.get(r2);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int r1) {
        return DynamicFromArray.create(this, r1);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int r2) {
        Object obj = this.mBackingList.get(r2);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer)) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        return null;
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushBoolean(boolean z) {
        this.mBackingList.add(Boolean.valueOf(z));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushDouble(double d) {
        this.mBackingList.add(Double.valueOf(d));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushInt(int r5) {
        this.mBackingList.add(new Double(r5));
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushString(String str) {
        this.mBackingList.add(str);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushArray(ReadableArray readableArray) {
        this.mBackingList.add(readableArray);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushMap(ReadableMap readableMap) {
        this.mBackingList.add(readableMap);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushNull() {
        this.mBackingList.add(null);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        return new ArrayList<>(this.mBackingList);
    }

    public String toString() {
        return this.mBackingList.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        List list = this.mBackingList;
        List list2 = ((JavaOnlyArray) obj).mBackingList;
        return list == null ? list2 == null : list.equals(list2);
    }

    public int hashCode() {
        List list = this.mBackingList;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }
}
