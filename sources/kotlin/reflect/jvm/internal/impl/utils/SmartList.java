package kotlin.reflect.jvm.internal.impl.utils;

import com.onesignal.NotificationBundleProcessor;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private Object myElem;
    private int mySize;

    private static /* synthetic */ void $$$reportNull$$$0(int r10) {
        String str = (r10 == 2 || r10 == 3 || r10 == 5 || r10 == 6 || r10 == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(r10 == 2 || r10 == 3 || r10 == 5 || r10 == 6 || r10 == 7) ? 2 : 3];
        switch (r10) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY;
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (r10 == 2 || r10 == 3) {
            objArr[1] = "iterator";
        } else if (r10 == 5 || r10 == 6 || r10 == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
        }
        switch (r10) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (r10 != 2 && r10 != 3 && r10 != 5 && r10 != 6 && r10 != 7) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r4) {
        int r0;
        if (r4 >= 0 && r4 < (r0 = this.mySize)) {
            if (r0 == 1) {
                return (E) this.myElem;
            }
            return (E) ((Object[]) this.myElem)[r4];
        }
        throw new IndexOutOfBoundsException("Index: " + r4 + ", Size: " + this.mySize);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        int r0 = this.mySize;
        if (r0 == 0) {
            this.myElem = e;
        } else if (r0 == 1) {
            this.myElem = new Object[]{this.myElem, e};
        } else {
            Object[] objArr = (Object[]) this.myElem;
            int length = objArr.length;
            if (r0 >= length) {
                int r6 = ((length * 3) / 2) + 1;
                int r02 = r0 + 1;
                if (r6 < r02) {
                    r6 = r02;
                }
                Object[] objArr2 = new Object[r6];
                this.myElem = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
                objArr = objArr2;
            }
            objArr[this.mySize] = e;
        }
        this.mySize++;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int r6, E e) {
        int r0;
        if (r6 < 0 || r6 > (r0 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + r6 + ", Size: " + this.mySize);
        }
        if (r0 == 0) {
            this.myElem = e;
        } else if (r0 == 1 && r6 == 0) {
            this.myElem = new Object[]{e, this.myElem};
        } else {
            Object[] objArr = new Object[r0 + 1];
            if (r0 == 1) {
                objArr[0] = this.myElem;
            } else {
                Object[] objArr2 = (Object[]) this.myElem;
                System.arraycopy(objArr2, 0, objArr, 0, r6);
                System.arraycopy(objArr2, r6, objArr, r6 + 1, this.mySize - r6);
            }
            objArr[r6] = e;
            this.myElem = objArr;
        }
        this.mySize++;
        this.modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int r3, E e) {
        int r0;
        if (r3 < 0 || r3 >= (r0 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Size: " + this.mySize);
        } else if (r0 == 1) {
            E e2 = (E) this.myElem;
            this.myElem = e;
            return e2;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            E e3 = (E) objArr[r3];
            objArr[r3] = e;
            return e3;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int r7) {
        int r0;
        E e;
        if (r7 < 0 || r7 >= (r0 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + r7 + ", Size: " + this.mySize);
        }
        if (r0 == 1) {
            e = (E) this.myElem;
            this.myElem = null;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            Object obj = objArr[r7];
            if (r0 == 2) {
                this.myElem = objArr[1 - r7];
            } else {
                int r02 = (r0 - r7) - 1;
                if (r02 > 0) {
                    System.arraycopy(objArr, r7 + 1, objArr, r7, r02);
                }
                objArr[this.mySize - 1] = null;
            }
            e = (E) obj;
        }
        this.mySize--;
        this.modCount++;
        return e;
    }

    /* loaded from: classes5.dex */
    private static class EmptyIterator<T> implements Iterator<T> {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        private EmptyIterator() {
        }

        public static <T> EmptyIterator<T> getInstance() {
            return INSTANCE;
        }

        @Override // java.util.Iterator
        public T next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        int r0 = this.mySize;
        if (r0 == 0) {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(2);
            }
            return emptyIterator;
        } else if (r0 == 1) {
            return new SingletonIterator();
        } else {
            Iterator<E> it = super.iterator();
            if (it == null) {
                $$$reportNull$$$0(3);
            }
            return it;
        }
    }

    /* loaded from: classes5.dex */
    private static abstract class SingletonIteratorBase<T> implements Iterator<T> {
        private boolean myVisited;

        protected abstract void checkCoModification();

        protected abstract T getElement();

        private SingletonIteratorBase() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (this.myVisited) {
                throw new NoSuchElementException();
            }
            this.myVisited = true;
            checkCoModification();
            return getElement();
        }
    }

    /* loaded from: classes5.dex */
    private class SingletonIterator extends SingletonIteratorBase<E> {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = SmartList.this.modCount;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected E getElement() {
            return (E) SmartList.this.myElem;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected void checkCoModification() {
            if (SmartList.this.modCount == this.myInitialModCount) {
                return;
            }
            throw new ConcurrentModificationException("ModCount: " + SmartList.this.modCount + "; expected: " + this.myInitialModCount);
        }

        @Override // java.util.Iterator
        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        if (tArr == 0) {
            $$$reportNull$$$0(4);
        }
        int length = tArr.length;
        int r1 = this.mySize;
        if (r1 == 1) {
            if (length != 0) {
                tArr[0] = this.myElem;
            } else {
                T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
                tArr2[0] = this.myElem;
                if (tArr2 == 0) {
                    $$$reportNull$$$0(5);
                }
                return tArr2;
            }
        } else if (length < r1) {
            T[] tArr3 = (T[]) Arrays.copyOf((Object[]) this.myElem, r1, tArr.getClass());
            if (tArr3 == null) {
                $$$reportNull$$$0(6);
            }
            return tArr3;
        } else if (r1 != 0) {
            System.arraycopy(this.myElem, 0, tArr, 0, r1);
        }
        int r12 = this.mySize;
        if (length > r12) {
            tArr[r12] = 0;
        }
        if (tArr == 0) {
            $$$reportNull$$$0(7);
        }
        return tArr;
    }
}
