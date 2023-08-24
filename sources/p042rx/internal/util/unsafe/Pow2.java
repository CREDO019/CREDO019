package p042rx.internal.util.unsafe;

/* renamed from: rx.internal.util.unsafe.Pow2 */
/* loaded from: classes6.dex */
public final class Pow2 {
    public static boolean isPowerOfTwo(int r1) {
        return (r1 & (r1 + (-1))) == 0;
    }

    private Pow2() {
        throw new IllegalStateException("No instances!");
    }

    public static int roundToPowerOfTwo(int r1) {
        return 1 << (32 - Integer.numberOfLeadingZeros(r1 - 1));
    }
}
