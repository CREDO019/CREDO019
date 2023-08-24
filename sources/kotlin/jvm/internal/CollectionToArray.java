package kotlin.jvm.internal;

import com.onesignal.NotificationBundleProcessor;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;

/* compiled from: CollectionToArray.kt */
@Metadata(m184d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m183d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 2147483645;

    public static final Object[] toArray(Collection<?> collection, Object[] objArr) {
        Object[] objArr2;
        Intrinsics.checkNotNullParameter(collection, "collection");
        Objects.requireNonNull(objArr);
        int size = collection.size();
        int r2 = 0;
        if (size == 0) {
            if (objArr.length > 0) {
                objArr[0] = null;
                return objArr;
            }
            return objArr;
        }
        Iterator<?> it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length > 0) {
                objArr[0] = null;
                return objArr;
            }
            return objArr;
        }
        if (size <= objArr.length) {
            objArr2 = objArr;
        } else {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            Objects.requireNonNull(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr2 = (Object[]) newInstance;
        }
        while (true) {
            int r3 = r2 + 1;
            objArr2[r2] = it.next();
            if (r3 >= objArr2.length) {
                if (!it.hasNext()) {
                    return objArr2;
                }
                int r22 = ((r3 * 3) + 1) >>> 1;
                if (r22 <= r3) {
                    if (r3 >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                    r22 = MAX_SIZE;
                }
                objArr2 = Arrays.copyOf(objArr2, r22);
                Intrinsics.checkNotNullExpressionValue(objArr2, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                if (objArr2 == objArr) {
                    objArr[r3] = null;
                    return objArr;
                }
                Object[] copyOf = Arrays.copyOf(objArr2, r3);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(result, size)");
                return copyOf;
            }
            r2 = r3;
        }
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    private static final Object[] toArrayImpl(Collection<?> collection, Functions<Object[]> functions, Function1<? super Integer, Object[]> function1, Function2<? super Object[], ? super Integer, Object[]> function2) {
        int size = collection.size();
        if (size == 0) {
            return functions.invoke();
        }
        Iterator<?> it = collection.iterator();
        if (!it.hasNext()) {
            return functions.invoke();
        }
        int r4 = 0;
        ?? r3 = function1.invoke(Integer.valueOf(size));
        while (true) {
            int r0 = r4 + 1;
            r3[r4] = it.next();
            if (r0 >= r3.length) {
                if (!it.hasNext()) {
                    return r3;
                }
                int r42 = ((r0 * 3) + 1) >>> 1;
                if (r42 <= r0) {
                    if (r0 >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                    r42 = MAX_SIZE;
                }
                r3 = Arrays.copyOf((Object[]) r3, r42);
                Intrinsics.checkNotNullExpressionValue(r3, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                return function2.invoke(r3, Integer.valueOf(r0));
            }
            r4 = r0;
            r3 = r3;
        }
    }

    public static final Object[] toArray(Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator<?> it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArr = new Object[size];
                int r1 = 0;
                while (true) {
                    int r2 = r1 + 1;
                    objArr[r1] = it.next();
                    if (r2 >= objArr.length) {
                        if (!it.hasNext()) {
                            return objArr;
                        }
                        int r12 = ((r2 * 3) + 1) >>> 1;
                        if (r12 <= r2) {
                            if (r2 >= MAX_SIZE) {
                                throw new OutOfMemoryError();
                            }
                            r12 = MAX_SIZE;
                        }
                        objArr = Arrays.copyOf(objArr, r12);
                        Intrinsics.checkNotNullExpressionValue(objArr, "copyOf(result, newSize)");
                    } else if (!it.hasNext()) {
                        Object[] copyOf = Arrays.copyOf(objArr, r2);
                        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(result, size)");
                        return copyOf;
                    }
                    r1 = r2;
                }
            }
        }
        return EMPTY;
    }
}
