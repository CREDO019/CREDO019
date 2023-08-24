package org.apache.logging.log4j.spi;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.HttpUrl;
import org.apache.logging.log4j.ThreadContext;

/* loaded from: classes5.dex */
public class DefaultThreadContextStack implements ThreadContextStack {
    private static final long serialVersionUID = 5050501;
    private static final ThreadLocal<MutableThreadContextStack> stack = new ThreadLocal<>();
    private final boolean useStack;

    public DefaultThreadContextStack(boolean z) {
        this.useStack = z;
    }

    private MutableThreadContextStack getNonNullStackCopy() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return (MutableThreadContextStack) (mutableThreadContextStack == null ? new MutableThreadContextStack() : mutableThreadContextStack.copy());
    }

    @Override // java.util.Collection
    public boolean add(String str) {
        if (this.useStack) {
            MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
            nonNullStackCopy.add(str);
            nonNullStackCopy.freeze();
            stack.set(nonNullStackCopy);
            return true;
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends String> collection) {
        if (!this.useStack || collection.isEmpty()) {
            return false;
        }
        MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
        nonNullStackCopy.addAll(collection);
        nonNullStackCopy.freeze();
        stack.set(nonNullStackCopy);
        return true;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public List<String> asList() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList();
        }
        return mutableThreadContextStack.asList();
    }

    @Override // java.util.Collection
    public void clear() {
        stack.remove();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return mutableThreadContextStack != null && mutableThreadContextStack.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return true;
        }
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return mutableThreadContextStack != null && mutableThreadContextStack.containsAll(collection);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public ThreadContextStack copy() {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || (mutableThreadContextStack = stack.get()) == null) {
            return new MutableThreadContextStack();
        }
        return mutableThreadContextStack.copy();
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ((!(obj instanceof DefaultThreadContextStack) || this.useStack == ((DefaultThreadContextStack) obj).useStack) && (obj instanceof ThreadContextStack)) {
            ThreadContextStack threadContextStack = (ThreadContextStack) obj;
            MutableThreadContextStack mutableThreadContextStack = stack.get();
            if (mutableThreadContextStack == null) {
                return threadContextStack == null;
            }
            return mutableThreadContextStack.equals(threadContextStack);
        }
        return false;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public int getDepth() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.getDepth();
    }

    @Override // java.util.Collection
    public int hashCode() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return 31 + (mutableThreadContextStack == null ? 0 : mutableThreadContextStack.hashCode());
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return mutableThreadContextStack == null || mutableThreadContextStack.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList().iterator();
        }
        return mutableThreadContextStack.iterator();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String peek() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null || mutableThreadContextStack.size() == 0) {
            return null;
        }
        return mutableThreadContextStack.peek();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String pop() {
        if (this.useStack) {
            ThreadLocal<MutableThreadContextStack> threadLocal = stack;
            MutableThreadContextStack mutableThreadContextStack = threadLocal.get();
            if (mutableThreadContextStack == null || mutableThreadContextStack.size() == 0) {
                throw new NoSuchElementException("The ThreadContext stack is empty");
            }
            MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
            String pop = mutableThreadContextStack2.pop();
            mutableThreadContextStack2.freeze();
            threadLocal.set(mutableThreadContextStack2);
            return pop;
        }
        return "";
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void push(String str) {
        if (this.useStack) {
            add(str);
        }
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        ThreadLocal<MutableThreadContextStack> threadLocal;
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || (mutableThreadContextStack = (threadLocal = stack).get()) == null || mutableThreadContextStack.size() == 0) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
        boolean remove = mutableThreadContextStack2.remove(obj);
        mutableThreadContextStack2.freeze();
        threadLocal.set(mutableThreadContextStack2);
        return remove;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        ThreadLocal<MutableThreadContextStack> threadLocal;
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || collection.isEmpty() || (mutableThreadContextStack = (threadLocal = stack).get()) == null || mutableThreadContextStack.isEmpty()) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
        boolean removeAll = mutableThreadContextStack2.removeAll(collection);
        mutableThreadContextStack2.freeze();
        threadLocal.set(mutableThreadContextStack2);
        return removeAll;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        ThreadLocal<MutableThreadContextStack> threadLocal;
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || collection.isEmpty() || (mutableThreadContextStack = (threadLocal = stack).get()) == null || mutableThreadContextStack.isEmpty()) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
        boolean retainAll = mutableThreadContextStack2.retainAll(collection);
        mutableThreadContextStack2.freeze();
        threadLocal.set(mutableThreadContextStack2);
        return retainAll;
    }

    @Override // java.util.Collection
    public int size() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.size();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return mutableThreadContextStack == null ? new String[0] : mutableThreadContextStack.toArray(new Object[mutableThreadContextStack.size()]);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        if (mutableThreadContextStack == null) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        return (T[]) mutableThreadContextStack.toArray(tArr);
    }

    public String toString() {
        MutableThreadContextStack mutableThreadContextStack = stack.get();
        return mutableThreadContextStack == null ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : mutableThreadContextStack.toString();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void trim(int r3) {
        if (r3 < 0) {
            throw new IllegalArgumentException("Maximum stack depth cannot be negative");
        }
        ThreadLocal<MutableThreadContextStack> threadLocal = stack;
        MutableThreadContextStack mutableThreadContextStack = threadLocal.get();
        if (mutableThreadContextStack == null) {
            return;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
        mutableThreadContextStack2.trim(r3);
        mutableThreadContextStack2.freeze();
        threadLocal.set(mutableThreadContextStack2);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public ThreadContext.ContextStack getImmutableStackOrNull() {
        return stack.get();
    }
}
