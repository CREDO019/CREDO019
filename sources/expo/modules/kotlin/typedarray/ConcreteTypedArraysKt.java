package expo.modules.kotlin.typedarray;

import kotlin.Metadata;

/* compiled from: ConcreteTypedArrays.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\b¨\u0006\u0005"}, m183d2 = {"checkIfInRange", "", "Lexpo/modules/kotlin/typedarray/TypedArray;", "index", "", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ConcreteTypedArraysKt {
    private static final void checkIfInRange(TypedArray typedArray, int r1) {
        if (r1 < 0 || r1 >= typedArray.getLength()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
