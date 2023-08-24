package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableList;

/* compiled from: AbstractMutableList.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, m183d2 = {"Lkotlin/collections/AbstractMutableList;", ExifInterface.LONGITUDE_EAST, "", "Ljava/util/AbstractList;", "()V", "add", "", "index", "", "element", "(ILjava/lang/Object;)V", "removeAt", "(I)Ljava/lang/Object;", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public abstract class AbstractMutableList<E> extends java.util.AbstractList<E> implements List<E>, KMutableList {
    @Override // java.util.AbstractList, java.util.List
    public abstract void add(int r1, E e);

    public abstract int getSize();

    public abstract E removeAt(int r1);

    @Override // java.util.AbstractList, java.util.List
    public abstract E set(int r1, E e);

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int r1) {
        return removeAt(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
