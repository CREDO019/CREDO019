package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Comparator;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes5.dex */
public class CompareToBuilder implements Builder<Integer> {
    private int comparison = 0;

    public static int reflectionCompare(Object obj, Object obj2) {
        return reflectionCompare(obj, obj2, false, null, new String[0]);
    }

    public static int reflectionCompare(Object obj, Object obj2, boolean z) {
        return reflectionCompare(obj, obj2, z, null, new String[0]);
    }

    public static int reflectionCompare(Object obj, Object obj2, Collection<String> collection) {
        return reflectionCompare(obj, obj2, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static int reflectionCompare(Object obj, Object obj2, String... strArr) {
        return reflectionCompare(obj, obj2, false, null, strArr);
    }

    public static int reflectionCompare(Object obj, Object obj2, boolean z, Class<?> cls, String... strArr) {
        if (obj == obj2) {
            return 0;
        }
        if (obj == null || obj2 == null) {
            throw null;
        }
        Class<?> cls2 = obj.getClass();
        if (!cls2.isInstance(obj2)) {
            throw new ClassCastException();
        }
        CompareToBuilder compareToBuilder = new CompareToBuilder();
        reflectionAppend(obj, obj2, cls2, compareToBuilder, z, strArr);
        while (cls2.getSuperclass() != null && cls2 != cls) {
            cls2 = cls2.getSuperclass();
            reflectionAppend(obj, obj2, cls2, compareToBuilder, z, strArr);
        }
        return compareToBuilder.toComparison();
    }

    private static void reflectionAppend(Object obj, Object obj2, Class<?> cls, CompareToBuilder compareToBuilder, boolean z, String[] strArr) {
        Field[] declaredFields = cls.getDeclaredFields();
        AccessibleObject.setAccessible(declaredFields, true);
        for (int r0 = 0; r0 < declaredFields.length && compareToBuilder.comparison == 0; r0++) {
            Field field = declaredFields[r0];
            if (!ArrayUtils.contains(strArr, field.getName()) && !field.getName().contains("$") && ((z || !Modifier.isTransient(field.getModifiers())) && !Modifier.isStatic(field.getModifiers()))) {
                try {
                    compareToBuilder.append(field.get(obj), field.get(obj2));
                } catch (IllegalAccessException unused) {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            }
        }
    }

    public CompareToBuilder appendSuper(int r2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = r2;
        return this;
    }

    public CompareToBuilder append(Object obj, Object obj2) {
        return append(obj, obj2, (Comparator<?>) null);
    }

    public CompareToBuilder append(Object obj, Object obj2, Comparator<?> comparator) {
        if (this.comparison == 0 && obj != obj2) {
            if (obj == null) {
                this.comparison = -1;
                return this;
            } else if (obj2 == null) {
                this.comparison = 1;
                return this;
            } else {
                if (obj.getClass().isArray()) {
                    appendArray(obj, obj2, comparator);
                } else if (comparator == null) {
                    this.comparison = ((Comparable) obj).compareTo(obj2);
                } else {
                    this.comparison = comparator.compare(obj, obj2);
                }
                return this;
            }
        }
        return this;
    }

    private void appendArray(Object obj, Object obj2, Comparator<?> comparator) {
        if (obj instanceof long[]) {
            append((long[]) obj, (long[]) obj2);
        } else if (obj instanceof int[]) {
            append((int[]) obj, (int[]) obj2);
        } else if (obj instanceof short[]) {
            append((short[]) obj, (short[]) obj2);
        } else if (obj instanceof char[]) {
            append((char[]) obj, (char[]) obj2);
        } else if (obj instanceof byte[]) {
            append((byte[]) obj, (byte[]) obj2);
        } else if (obj instanceof double[]) {
            append((double[]) obj, (double[]) obj2);
        } else if (obj instanceof float[]) {
            append((float[]) obj, (float[]) obj2);
        } else if (obj instanceof boolean[]) {
            append((boolean[]) obj, (boolean[]) obj2);
        } else {
            append((Object[]) obj, (Object[]) obj2, comparator);
        }
    }

    public CompareToBuilder append(long j, long j2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Long.compare(j, j2);
        return this;
    }

    public CompareToBuilder append(int r2, int r3) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Integer.compare(r2, r3);
        return this;
    }

    public CompareToBuilder append(short s, short s2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Short.compare(s, s2);
        return this;
    }

    public CompareToBuilder append(char c, char c2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Character.compare(c, c2);
        return this;
    }

    public CompareToBuilder append(byte b, byte b2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Byte.compare(b, b2);
        return this;
    }

    public CompareToBuilder append(double d, double d2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Double.compare(d, d2);
        return this;
    }

    public CompareToBuilder append(float f, float f2) {
        if (this.comparison != 0) {
            return this;
        }
        this.comparison = Float.compare(f, f2);
        return this;
    }

    public CompareToBuilder append(boolean z, boolean z2) {
        if (this.comparison == 0 && z != z2) {
            if (z) {
                this.comparison = 1;
            } else {
                this.comparison = -1;
            }
            return this;
        }
        return this;
    }

    public CompareToBuilder append(Object[] objArr, Object[] objArr2) {
        return append(objArr, objArr2, (Comparator<?>) null);
    }

    public CompareToBuilder append(Object[] objArr, Object[] objArr2, Comparator<?> comparator) {
        if (this.comparison == 0 && objArr != objArr2) {
            if (objArr == null) {
                this.comparison = -1;
                return this;
            } else if (objArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (objArr.length != objArr2.length) {
                this.comparison = objArr.length >= objArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < objArr.length && this.comparison == 0; r0++) {
                    append(objArr[r0], objArr2[r0], comparator);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(long[] jArr, long[] jArr2) {
        if (this.comparison == 0 && jArr != jArr2) {
            if (jArr == null) {
                this.comparison = -1;
                return this;
            } else if (jArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (jArr.length != jArr2.length) {
                this.comparison = jArr.length >= jArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < jArr.length && this.comparison == 0; r0++) {
                    append(jArr[r0], jArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(int[] r5, int[] r6) {
        if (this.comparison == 0 && r5 != r6) {
            if (r5 == null) {
                this.comparison = -1;
                return this;
            } else if (r6 == null) {
                this.comparison = 1;
                return this;
            } else if (r5.length != r6.length) {
                this.comparison = r5.length >= r6.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < r5.length && this.comparison == 0; r0++) {
                    append(r5[r0], r6[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(short[] sArr, short[] sArr2) {
        if (this.comparison == 0 && sArr != sArr2) {
            if (sArr == null) {
                this.comparison = -1;
                return this;
            } else if (sArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (sArr.length != sArr2.length) {
                this.comparison = sArr.length >= sArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < sArr.length && this.comparison == 0; r0++) {
                    append(sArr[r0], sArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(char[] cArr, char[] cArr2) {
        if (this.comparison == 0 && cArr != cArr2) {
            if (cArr == null) {
                this.comparison = -1;
                return this;
            } else if (cArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (cArr.length != cArr2.length) {
                this.comparison = cArr.length >= cArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < cArr.length && this.comparison == 0; r0++) {
                    append(cArr[r0], cArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(byte[] bArr, byte[] bArr2) {
        if (this.comparison == 0 && bArr != bArr2) {
            if (bArr == null) {
                this.comparison = -1;
                return this;
            } else if (bArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (bArr.length != bArr2.length) {
                this.comparison = bArr.length >= bArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < bArr.length && this.comparison == 0; r0++) {
                    append(bArr[r0], bArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(double[] dArr, double[] dArr2) {
        if (this.comparison == 0 && dArr != dArr2) {
            if (dArr == null) {
                this.comparison = -1;
                return this;
            } else if (dArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (dArr.length != dArr2.length) {
                this.comparison = dArr.length >= dArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < dArr.length && this.comparison == 0; r0++) {
                    append(dArr[r0], dArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(float[] fArr, float[] fArr2) {
        if (this.comparison == 0 && fArr != fArr2) {
            if (fArr == null) {
                this.comparison = -1;
                return this;
            } else if (fArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (fArr.length != fArr2.length) {
                this.comparison = fArr.length >= fArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < fArr.length && this.comparison == 0; r0++) {
                    append(fArr[r0], fArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public CompareToBuilder append(boolean[] zArr, boolean[] zArr2) {
        if (this.comparison == 0 && zArr != zArr2) {
            if (zArr == null) {
                this.comparison = -1;
                return this;
            } else if (zArr2 == null) {
                this.comparison = 1;
                return this;
            } else if (zArr.length != zArr2.length) {
                this.comparison = zArr.length >= zArr2.length ? 1 : -1;
                return this;
            } else {
                for (int r0 = 0; r0 < zArr.length && this.comparison == 0; r0++) {
                    append(zArr[r0], zArr2[r0]);
                }
                return this;
            }
        }
        return this;
    }

    public int toComparison() {
        return this.comparison;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.lang3.builder.Builder
    public Integer build() {
        return Integer.valueOf(toComparison());
    }
}
