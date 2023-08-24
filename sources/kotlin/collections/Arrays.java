package kotlin.collections;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m184d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, m183d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 49, m178xs = "kotlin/collections/ArraysKt")
/* renamed from: kotlin.collections.ArraysKt__ArraysKt */
/* loaded from: classes5.dex */
public class Arrays extends ArraysJVM {
    public static final <T> List<T> flatten(T[][] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        T[][] tArr2 = tArr;
        int r4 = 0;
        for (T[] tArr3 : tArr2) {
            r4 += tArr3.length;
        }
        ArrayList arrayList = new ArrayList(r4);
        int length = tArr2.length;
        for (int r2 = 0; r2 < length; r2++) {
            CollectionsKt.addAll(arrayList, tArr[r2]);
        }
        return arrayList;
    }

    public static final <T, R> Tuples<List<T>, List<R>> unzip(Tuples<? extends T, ? extends R>[] tuplesArr) {
        Intrinsics.checkNotNullParameter(tuplesArr, "<this>");
        ArrayList arrayList = new ArrayList(tuplesArr.length);
        ArrayList arrayList2 = new ArrayList(tuplesArr.length);
        for (Tuples<? extends T, ? extends R> tuples : tuplesArr) {
            arrayList.add(tuples.getFirst());
            arrayList2.add(tuples.getSecond());
        }
        return TuplesKt.m176to(arrayList, arrayList2);
    }

    private static final boolean isNullOrEmpty(Object[] objArr) {
        if (objArr != null) {
            if (!(objArr.length == 0)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    private static final Object ifEmpty(Object[] objArr, Functions defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return objArr.length == 0 ? defaultValue.invoke() : objArr;
    }

    public static final <T> boolean contentDeepEquals(T[] tArr, T[] tArr2) {
        if (tArr == tArr2) {
            return true;
        }
        if (tArr == null || tArr2 == null || tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int r3 = 0; r3 < length; r3++) {
            T t = tArr[r3];
            T t2 = tArr2[r3];
            if (t != t2) {
                if (t == null || t2 == null) {
                    return false;
                }
                if ((t instanceof Object[]) && (t2 instanceof Object[])) {
                    if (!ArraysKt.contentDeepEquals((Object[]) t, (Object[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof byte[]) && (t2 instanceof byte[])) {
                    if (!java.util.Arrays.equals((byte[]) t, (byte[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof short[]) && (t2 instanceof short[])) {
                    if (!java.util.Arrays.equals((short[]) t, (short[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof int[]) && (t2 instanceof int[])) {
                    if (!java.util.Arrays.equals((int[]) t, (int[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof long[]) && (t2 instanceof long[])) {
                    if (!java.util.Arrays.equals((long[]) t, (long[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof float[]) && (t2 instanceof float[])) {
                    if (!java.util.Arrays.equals((float[]) t, (float[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof double[]) && (t2 instanceof double[])) {
                    if (!java.util.Arrays.equals((double[]) t, (double[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof char[]) && (t2 instanceof char[])) {
                    if (!java.util.Arrays.equals((char[]) t, (char[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof boolean[]) && (t2 instanceof boolean[])) {
                    if (!java.util.Arrays.equals((boolean[]) t, (boolean[]) t2)) {
                        return false;
                    }
                } else if ((t instanceof UByteArray) && (t2 instanceof UByteArray)) {
                    if (!UArraysKt.m2291contentEqualskV0jMPg(((UByteArray) t).m1833unboximpl(), ((UByteArray) t2).m1833unboximpl())) {
                        return false;
                    }
                } else if ((t instanceof UShortArray) && (t2 instanceof UShortArray)) {
                    if (!UArraysKt.m2288contentEqualsFGO6Aew(((UShortArray) t).m2093unboximpl(), ((UShortArray) t2).m2093unboximpl())) {
                        return false;
                    }
                } else if ((t instanceof UIntArray) && (t2 instanceof UIntArray)) {
                    if (!UArraysKt.m2289contentEqualsKJPZfPQ(((UIntArray) t).m1911unboximpl(), ((UIntArray) t2).m1911unboximpl())) {
                        return false;
                    }
                } else if ((t instanceof ULongArray) && (t2 instanceof ULongArray)) {
                    if (!UArraysKt.m2293contentEqualslec5QzE(((ULongArray) t).m1989unboximpl(), ((ULongArray) t2).m1989unboximpl())) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(t, t2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final <T> String contentDeepToString(T[] tArr) {
        if (tArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder((RangesKt.coerceAtMost(tArr.length, 429496729) * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(tArr, sb, new ArrayList());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] tArr, StringBuilder sb, List<Object[]> list) {
        if (list.contains(tArr)) {
            sb.append("[...]");
            return;
        }
        list.add(tArr);
        sb.append('[');
        int length = tArr.length;
        for (int r0 = 0; r0 < length; r0++) {
            if (r0 != 0) {
                sb.append(", ");
            }
            T t = tArr[r0];
            if (t == null) {
                sb.append("null");
            } else if (t instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt((Object[]) t, sb, list);
            } else if (t instanceof byte[]) {
                String arrays = java.util.Arrays.toString((byte[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
                sb.append(arrays);
            } else if (t instanceof short[]) {
                String arrays2 = java.util.Arrays.toString((short[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays2, "toString(this)");
                sb.append(arrays2);
            } else if (t instanceof int[]) {
                String arrays3 = java.util.Arrays.toString((int[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays3, "toString(this)");
                sb.append(arrays3);
            } else if (t instanceof long[]) {
                String arrays4 = java.util.Arrays.toString((long[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays4, "toString(this)");
                sb.append(arrays4);
            } else if (t instanceof float[]) {
                String arrays5 = java.util.Arrays.toString((float[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays5, "toString(this)");
                sb.append(arrays5);
            } else if (t instanceof double[]) {
                String arrays6 = java.util.Arrays.toString((double[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays6, "toString(this)");
                sb.append(arrays6);
            } else if (t instanceof char[]) {
                String arrays7 = java.util.Arrays.toString((char[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays7, "toString(this)");
                sb.append(arrays7);
            } else if (t instanceof boolean[]) {
                String arrays8 = java.util.Arrays.toString((boolean[]) t);
                Intrinsics.checkNotNullExpressionValue(arrays8, "toString(this)");
                sb.append(arrays8);
            } else if (t instanceof UByteArray) {
                UByteArray uByteArray = (UByteArray) t;
                sb.append(UArraysKt.m2305contentToString2csIQuQ(uByteArray != null ? uByteArray.m1833unboximpl() : null));
            } else if (t instanceof UShortArray) {
                UShortArray uShortArray = (UShortArray) t;
                sb.append(UArraysKt.m2309contentToStringd6D3K8(uShortArray != null ? uShortArray.m2093unboximpl() : null));
            } else if (t instanceof UIntArray) {
                UIntArray uIntArray = (UIntArray) t;
                sb.append(UArraysKt.m2308contentToStringXUkPCBk(uIntArray != null ? uIntArray.m1911unboximpl() : null));
            } else if (t instanceof ULongArray) {
                ULongArray uLongArray = (ULongArray) t;
                sb.append(UArraysKt.m2311contentToStringuLth9ew(uLongArray != null ? uLongArray.m1989unboximpl() : null));
            } else {
                sb.append(t.toString());
            }
        }
        sb.append(']');
        list.remove(CollectionsKt.getLastIndex(list));
    }
}