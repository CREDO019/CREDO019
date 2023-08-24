package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ArrayDeque.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0013\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016J\u001e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004H\u0002J\u001d\u0010'\u001a\u00020\u00142\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000b\u0010*\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010,\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010-\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0096\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u00100\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\u0016\u00102\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0004H\u0083\b¢\u0006\u0002\u0010.J\u0011\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0083\bJM\u00103\u001a\u00020\u00172>\u00104\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H\u0000¢\u0006\u0002\b8J\b\u00109\u001a\u00020\u0014H\u0016J\u000b\u0010:\u001a\u00028\u0000¢\u0006\u0002\u0010+J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\r\u0010<\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0010\u0010=\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0016\u0010@\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0016¢\u0006\u0002\u0010.J\u000b\u0010B\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010C\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u000b\u0010D\u001a\u00028\u0000¢\u0006\u0002\u0010+J\r\u0010E\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010+J\u0016\u0010F\u001a\u00020\u00142\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001e\u0010G\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010HJ\u0017\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0000¢\u0006\u0004\bJ\u0010KJ)\u0010I\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0000¢\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010KJ'\u0010O\u001a\b\u0012\u0004\u0012\u0002HL0\u000b\"\u0004\b\u0001\u0010L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HL0\u000bH\u0016¢\u0006\u0002\u0010NR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006Q"}, m183d2 = {"Lkotlin/collections/ArrayDeque;", ExifInterface.LONGITUDE_EAST, "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", TtmlNode.TAG_HEAD, "<set-?>", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    private static final int defaultMinCapacity = 10;
    private static final int maxArraySize = 2147483639;
    private Object[] elementData;
    private int head;
    private int size;
    public static final Companion Companion = new Companion(null);
    private static final Object[] emptyElementData = new Object[0];

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    public ArrayDeque(int r4) {
        Object[] objArr;
        if (r4 == 0) {
            objArr = emptyElementData;
        } else if (r4 > 0) {
            objArr = new Object[r4];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + r4);
        }
        this.elementData = objArr;
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    public ArrayDeque(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] array = elements.toArray(new Object[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        this.elementData = array;
        this.size = array.length;
        if (array.length == 0) {
            this.elementData = emptyElementData;
        }
    }

    private final void ensureCapacity(int r3) {
        if (r3 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (r3 <= objArr.length) {
            return;
        }
        if (objArr == emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(r3, 10)];
        } else {
            copyElements(Companion.newCapacity$kotlin_stdlib(objArr.length, r3));
        }
    }

    private final void copyElements(int r5) {
        Object[] objArr = new Object[r5];
        Object[] objArr2 = this.elementData;
        ArraysKt.copyInto(objArr2, objArr, 0, this.head, objArr2.length);
        Object[] objArr3 = this.elementData;
        int length = objArr3.length;
        int r2 = this.head;
        ArraysKt.copyInto(objArr3, objArr, length - r2, 0, r2);
        this.head = 0;
        this.elementData = objArr;
    }

    private final E internalGet(int r2) {
        return (E) this.elementData[r2];
    }

    private final int positiveMod(int r3) {
        Object[] objArr = this.elementData;
        return r3 >= objArr.length ? r3 - objArr.length : r3;
    }

    private final int negativeMod(int r2) {
        return r2 < 0 ? r2 + this.elementData.length : r2;
    }

    private final int internalIndex(int r2) {
        return positiveMod(this.head + r2);
    }

    private final int incremented(int r2) {
        if (r2 == ArraysKt.getLastIndex(this.elementData)) {
            return 0;
        }
        return r2 + 1;
    }

    private final int decremented(int r1) {
        return r1 == 0 ? ArraysKt.getLastIndex(this.elementData) : r1 - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[this.head];
    }

    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[this.head];
    }

    public final E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.elementData[positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    public final E lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.elementData[positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    public final void addFirst(E e) {
        ensureCapacity(size() + 1);
        int decremented = decremented(this.head);
        this.head = decremented;
        this.elementData[decremented] = e;
        this.size = size() + 1;
    }

    public final void addLast(E e) {
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(this.head + size())] = e;
        this.size = size() + 1;
    }

    public final E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int r1 = this.head;
        E e = (E) objArr[r1];
        objArr[r1] = null;
        this.head = incremented(r1);
        this.size = size() - 1;
        return e;
    }

    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int positiveMod = positiveMod(this.head + CollectionsKt.getLastIndex(this));
        Object[] objArr = this.elementData;
        E e = (E) objArr[positiveMod];
        objArr[positiveMod] = null;
        this.size = size() - 1;
        return e;
    }

    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int r8, E e) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r8, size());
        if (r8 == size()) {
            addLast(e);
        } else if (r8 == 0) {
            addFirst(e);
        } else {
            ensureCapacity(size() + 1);
            int positiveMod = positiveMod(this.head + r8);
            if (r8 < ((size() + 1) >> 1)) {
                int decremented = decremented(positiveMod);
                int decremented2 = decremented(this.head);
                int r2 = this.head;
                if (decremented >= r2) {
                    Object[] objArr = this.elementData;
                    objArr[decremented2] = objArr[r2];
                    ArraysKt.copyInto(objArr, objArr, r2, r2 + 1, decremented + 1);
                } else {
                    Object[] objArr2 = this.elementData;
                    ArraysKt.copyInto(objArr2, objArr2, r2 - 1, r2, objArr2.length);
                    Object[] objArr3 = this.elementData;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    ArraysKt.copyInto(objArr3, objArr3, 0, 1, decremented + 1);
                }
                this.elementData[decremented] = e;
                this.head = decremented2;
            } else {
                int positiveMod2 = positiveMod(this.head + size());
                if (positiveMod < positiveMod2) {
                    Object[] objArr4 = this.elementData;
                    ArraysKt.copyInto(objArr4, objArr4, positiveMod + 1, positiveMod, positiveMod2);
                } else {
                    Object[] objArr5 = this.elementData;
                    ArraysKt.copyInto(objArr5, objArr5, 1, 0, positiveMod2);
                    Object[] objArr6 = this.elementData;
                    objArr6[0] = objArr6[objArr6.length - 1];
                    ArraysKt.copyInto(objArr6, objArr6, positiveMod + 1, positiveMod, objArr6.length - 1);
                }
                this.elementData[positiveMod] = e;
            }
            this.size = size() + 1;
        }
    }

    private final void copyCollectionElements(int r5, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.elementData.length;
        while (r5 < length && it.hasNext()) {
            this.elementData[r5] = it.next();
            r5++;
        }
        int r1 = this.head;
        for (int r52 = 0; r52 < r1 && it.hasNext(); r52++) {
            this.elementData[r52] = it.next();
        }
        this.size = size() + collection.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(size() + elements.size());
        copyCollectionElements(positiveMod(this.head + size()), elements);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int r9, Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r9, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (r9 == size()) {
            return addAll(elements);
        }
        ensureCapacity(size() + elements.size());
        int positiveMod = positiveMod(this.head + size());
        int positiveMod2 = positiveMod(this.head + r9);
        int size = elements.size();
        if (r9 < ((size() + 1) >> 1)) {
            int r92 = this.head;
            int r0 = r92 - size;
            if (positiveMod2 < r92) {
                Object[] objArr = this.elementData;
                ArraysKt.copyInto(objArr, objArr, r0, r92, objArr.length);
                if (size >= positiveMod2) {
                    Object[] objArr2 = this.elementData;
                    ArraysKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, positiveMod2);
                } else {
                    Object[] objArr3 = this.elementData;
                    ArraysKt.copyInto(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.elementData;
                    ArraysKt.copyInto(objArr4, objArr4, 0, size, positiveMod2);
                }
            } else if (r0 >= 0) {
                Object[] objArr5 = this.elementData;
                ArraysKt.copyInto(objArr5, objArr5, r0, r92, positiveMod2);
            } else {
                Object[] objArr6 = this.elementData;
                r0 += objArr6.length;
                int r6 = positiveMod2 - r92;
                int length = objArr6.length - r0;
                if (length >= r6) {
                    ArraysKt.copyInto(objArr6, objArr6, r0, r92, positiveMod2);
                } else {
                    ArraysKt.copyInto(objArr6, objArr6, r0, r92, r92 + length);
                    Object[] objArr7 = this.elementData;
                    ArraysKt.copyInto(objArr7, objArr7, 0, this.head + length, positiveMod2);
                }
            }
            this.head = r0;
            copyCollectionElements(negativeMod(positiveMod2 - size), elements);
        } else {
            int r93 = positiveMod2 + size;
            if (positiveMod2 < positiveMod) {
                int r3 = size + positiveMod;
                Object[] objArr8 = this.elementData;
                if (r3 <= objArr8.length) {
                    ArraysKt.copyInto(objArr8, objArr8, r93, positiveMod2, positiveMod);
                } else if (r93 >= objArr8.length) {
                    ArraysKt.copyInto(objArr8, objArr8, r93 - objArr8.length, positiveMod2, positiveMod);
                } else {
                    int length2 = positiveMod - (r3 - objArr8.length);
                    ArraysKt.copyInto(objArr8, objArr8, 0, length2, positiveMod);
                    Object[] objArr9 = this.elementData;
                    ArraysKt.copyInto(objArr9, objArr9, r93, positiveMod2, length2);
                }
            } else {
                Object[] objArr10 = this.elementData;
                ArraysKt.copyInto(objArr10, objArr10, size, 0, positiveMod);
                Object[] objArr11 = this.elementData;
                if (r93 >= objArr11.length) {
                    ArraysKt.copyInto(objArr11, objArr11, r93 - objArr11.length, positiveMod2, objArr11.length);
                } else {
                    ArraysKt.copyInto(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.elementData;
                    ArraysKt.copyInto(objArr12, objArr12, r93, positiveMod2, objArr12.length - size);
                }
            }
            copyCollectionElements(positiveMod2, elements);
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r3) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, size());
        return (E) this.elementData[positiveMod(this.head + r3)];
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int r3, E e) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, size());
        int positiveMod = positiveMod(this.head + r3);
        Object[] objArr = this.elementData;
        E e2 = (E) objArr[positiveMod];
        objArr[positiveMod] = e;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int r5;
        int positiveMod = positiveMod(this.head + size());
        int r1 = this.head;
        if (r1 < positiveMod) {
            while (r1 < positiveMod) {
                if (Intrinsics.areEqual(obj, this.elementData[r1])) {
                    r5 = this.head;
                } else {
                    r1++;
                }
            }
            return -1;
        } else if (r1 < positiveMod) {
            return -1;
        } else {
            int length = this.elementData.length;
            while (true) {
                if (r1 >= length) {
                    for (int r12 = 0; r12 < positiveMod; r12++) {
                        if (Intrinsics.areEqual(obj, this.elementData[r12])) {
                            r1 = r12 + this.elementData.length;
                            r5 = this.head;
                        }
                    }
                    return -1;
                } else if (Intrinsics.areEqual(obj, this.elementData[r1])) {
                    r5 = this.head;
                    break;
                } else {
                    r1++;
                }
            }
        }
        return r1 - r5;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int lastIndex;
        int r5;
        int positiveMod = positiveMod(this.head + size());
        int r1 = this.head;
        if (r1 < positiveMod) {
            lastIndex = positiveMod - 1;
            if (r1 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                    if (lastIndex != r1) {
                        lastIndex--;
                    }
                }
                r5 = this.head;
                return lastIndex - r5;
            }
            return -1;
        }
        if (r1 > positiveMod) {
            int r0 = positiveMod - 1;
            while (true) {
                if (-1 < r0) {
                    if (Intrinsics.areEqual(obj, this.elementData[r0])) {
                        lastIndex = r0 + this.elementData.length;
                        r5 = this.head;
                        break;
                    }
                    r0--;
                } else {
                    lastIndex = ArraysKt.getLastIndex(this.elementData);
                    int r12 = this.head;
                    if (r12 <= lastIndex) {
                        while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                            if (lastIndex != r12) {
                                lastIndex--;
                            }
                        }
                        r5 = this.head;
                    }
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int r9) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r9, size());
        ArrayDeque<E> arrayDeque = this;
        if (r9 == CollectionsKt.getLastIndex(arrayDeque)) {
            return removeLast();
        }
        if (r9 == 0) {
            return removeFirst();
        }
        int positiveMod = positiveMod(this.head + r9);
        E e = (E) this.elementData[positiveMod];
        if (r9 < (size() >> 1)) {
            int r92 = this.head;
            if (positiveMod >= r92) {
                Object[] objArr = this.elementData;
                ArraysKt.copyInto(objArr, objArr, r92 + 1, r92, positiveMod);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt.copyInto(objArr2, objArr2, 1, 0, positiveMod);
                Object[] objArr3 = this.elementData;
                objArr3[0] = objArr3[objArr3.length - 1];
                int r0 = this.head;
                ArraysKt.copyInto(objArr3, objArr3, r0 + 1, r0, objArr3.length - 1);
            }
            Object[] objArr4 = this.elementData;
            int r02 = this.head;
            objArr4[r02] = null;
            this.head = incremented(r02);
        } else {
            int positiveMod2 = positiveMod(this.head + CollectionsKt.getLastIndex(arrayDeque));
            if (positiveMod <= positiveMod2) {
                Object[] objArr5 = this.elementData;
                ArraysKt.copyInto(objArr5, objArr5, positiveMod, positiveMod + 1, positiveMod2 + 1);
            } else {
                Object[] objArr6 = this.elementData;
                ArraysKt.copyInto(objArr6, objArr6, positiveMod, positiveMod + 1, objArr6.length);
                Object[] objArr7 = this.elementData;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt.copyInto(objArr7, objArr7, 0, 1, positiveMod2 + 1);
            }
            this.elementData[positiveMod2] = null;
        }
        this.size = size() - 1;
        return e;
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> function1) {
        int positiveMod;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod2 = positiveMod(this.head + size());
                int r3 = this.head;
                if (r3 < positiveMod2) {
                    positiveMod = r3;
                    while (r3 < positiveMod2) {
                        Object obj = this.elementData[r3];
                        if (function1.invoke(obj).booleanValue()) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        r3++;
                    }
                    ArraysKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    int r6 = r3;
                    boolean z2 = false;
                    while (r3 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[r3];
                        objArr[r3] = null;
                        if (function1.invoke(obj2).booleanValue()) {
                            this.elementData[r6] = obj2;
                            r6++;
                        } else {
                            z2 = true;
                        }
                        r3++;
                    }
                    positiveMod = positiveMod(r6);
                    for (int r1 = 0; r1 < positiveMod2; r1++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[r1];
                        objArr2[r1] = null;
                        if (function1.invoke(obj3).booleanValue()) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int positiveMod = positiveMod(this.head + size());
        int r1 = this.head;
        if (r1 < positiveMod) {
            ArraysKt.fill(this.elementData, (Object) null, r1, positiveMod);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt.fill(objArr, (Object) null, this.head, objArr.length);
            ArraysKt.fill(this.elementData, (Object) null, 0, positiveMod);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < size()) {
            array = (T[]) ArraysKt.arrayOfNulls(array, size());
        }
        int positiveMod = positiveMod(this.head + size());
        int r3 = this.head;
        if (r3 < positiveMod) {
            ArraysKt.copyInto$default(this.elementData, array, 0, r3, positiveMod, 2, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt.copyInto(objArr, array, 0, this.head, objArr.length);
            Object[] objArr2 = this.elementData;
            ArraysKt.copyInto(objArr2, array, objArr2.length - this.head, 0, positiveMod);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public final <T> T[] testToArray$kotlin_stdlib(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) toArray(array);
    }

    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    /* compiled from: ArrayDeque.kt */
    @Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int newCapacity$kotlin_stdlib(int r3, int r4) {
            int r32 = r3 + (r3 >> 1);
            if (r32 - r4 < 0) {
                r32 = r4;
            }
            if (r32 - ArrayDeque.maxArraySize > 0) {
                if (r4 > ArrayDeque.maxArraySize) {
                    return Integer.MAX_VALUE;
                }
                return ArrayDeque.maxArraySize;
            }
            return r32;
        }

        private Companion() {
        }
    }

    public final void internalStructure$kotlin_stdlib(Function2<? super Integer, ? super Object[], Unit> structure) {
        int r1;
        Intrinsics.checkNotNullParameter(structure, "structure");
        structure.invoke(Integer.valueOf((isEmpty() || (r1 = this.head) < positiveMod(this.head + size())) ? this.head : r1 - this.elementData.length), toArray());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> elements) {
        int positiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod2 = positiveMod(this.head + size());
                int r3 = this.head;
                if (r3 < positiveMod2) {
                    positiveMod = r3;
                    while (r3 < positiveMod2) {
                        Object obj = this.elementData[r3];
                        if (!elements.contains(obj)) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        r3++;
                    }
                    ArraysKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    int r6 = r3;
                    boolean z2 = false;
                    while (r3 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[r3];
                        objArr[r3] = null;
                        if (!elements.contains(obj2)) {
                            this.elementData[r6] = obj2;
                            r6++;
                        } else {
                            z2 = true;
                        }
                        r3++;
                    }
                    positiveMod = positiveMod(r6);
                    for (int r1 = 0; r1 < positiveMod2; r1++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[r1];
                        objArr2[r1] = null;
                        if (!elements.contains(obj3)) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> elements) {
        int positiveMod;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int positiveMod2 = positiveMod(this.head + size());
                int r3 = this.head;
                if (r3 < positiveMod2) {
                    positiveMod = r3;
                    while (r3 < positiveMod2) {
                        Object obj = this.elementData[r3];
                        if (elements.contains(obj)) {
                            this.elementData[positiveMod] = obj;
                            positiveMod++;
                        } else {
                            z = true;
                        }
                        r3++;
                    }
                    ArraysKt.fill(this.elementData, (Object) null, positiveMod, positiveMod2);
                } else {
                    int length = this.elementData.length;
                    int r6 = r3;
                    boolean z2 = false;
                    while (r3 < length) {
                        Object[] objArr = this.elementData;
                        Object obj2 = objArr[r3];
                        objArr[r3] = null;
                        if (elements.contains(obj2)) {
                            this.elementData[r6] = obj2;
                            r6++;
                        } else {
                            z2 = true;
                        }
                        r3++;
                    }
                    positiveMod = positiveMod(r6);
                    for (int r1 = 0; r1 < positiveMod2; r1++) {
                        Object[] objArr2 = this.elementData;
                        Object obj3 = objArr2[r1];
                        objArr2[r1] = null;
                        if (elements.contains(obj3)) {
                            this.elementData[positiveMod] = obj3;
                            positiveMod = incremented(positiveMod);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.size = negativeMod(positiveMod - this.head);
                }
            }
        }
        return z;
    }
}