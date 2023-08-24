package kotlin;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m184d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\bH\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\b¨\u0006\t"}, m183d2 = {"floorDiv", "", "", "other", "", "", "mod", "", "", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 49, m178xs = "kotlin/NumbersKt")
/* renamed from: kotlin.NumbersKt__FloorDivModKt */
/* loaded from: classes5.dex */
public class FloorDivMod extends BigIntegers {
    private static final int floorDiv(byte b, byte b2) {
        int r0 = b / b2;
        return ((b ^ b2) >= 0 || b2 * r0 == b) ? r0 : r0 - 1;
    }

    private static final byte mod(byte b, byte b2) {
        int r2 = b % b2;
        return (byte) (r2 + (b2 & (((r2 ^ b2) & ((-r2) | r2)) >> 31)));
    }

    private static final int floorDiv(byte b, short s) {
        int r0 = b / s;
        return ((b ^ s) >= 0 || s * r0 == b) ? r0 : r0 - 1;
    }

    private static final short mod(byte b, short s) {
        int r2 = b % s;
        return (short) (r2 + (s & (((r2 ^ s) & ((-r2) | r2)) >> 31)));
    }

    private static final int floorDiv(byte b, int r3) {
        int r0 = b / r3;
        return ((b ^ r3) >= 0 || r3 * r0 == b) ? r0 : r0 - 1;
    }

    private static final int mod(byte b, int r3) {
        int r2 = b % r3;
        return r2 + (r3 & (((r2 ^ r3) & ((-r2) | r2)) >> 31));
    }

    private static final long floorDiv(byte b, long j) {
        long j2 = b;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final long mod(byte b, long j) {
        long j2 = b % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final int floorDiv(short s, byte b) {
        int r0 = s / b;
        return ((s ^ b) >= 0 || b * r0 == s) ? r0 : r0 - 1;
    }

    private static final byte mod(short s, byte b) {
        int r2 = s % b;
        return (byte) (r2 + (b & (((r2 ^ b) & ((-r2) | r2)) >> 31)));
    }

    private static final int floorDiv(short s, short s2) {
        int r0 = s / s2;
        return ((s ^ s2) >= 0 || s2 * r0 == s) ? r0 : r0 - 1;
    }

    private static final short mod(short s, short s2) {
        int r2 = s % s2;
        return (short) (r2 + (s2 & (((r2 ^ s2) & ((-r2) | r2)) >> 31)));
    }

    private static final int floorDiv(short s, int r3) {
        int r0 = s / r3;
        return ((s ^ r3) >= 0 || r3 * r0 == s) ? r0 : r0 - 1;
    }

    private static final int mod(short s, int r3) {
        int r2 = s % r3;
        return r2 + (r3 & (((r2 ^ r3) & ((-r2) | r2)) >> 31));
    }

    private static final long floorDiv(short s, long j) {
        long j2 = s;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final long mod(short s, long j) {
        long j2 = s % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final int floorDiv(int r2, byte b) {
        int r0 = r2 / b;
        return ((r2 ^ b) >= 0 || b * r0 == r2) ? r0 : r0 - 1;
    }

    private static final byte mod(int r2, byte b) {
        int r22 = r2 % b;
        return (byte) (r22 + (b & (((r22 ^ b) & ((-r22) | r22)) >> 31)));
    }

    private static final int floorDiv(int r2, short s) {
        int r0 = r2 / s;
        return ((r2 ^ s) >= 0 || s * r0 == r2) ? r0 : r0 - 1;
    }

    private static final short mod(int r2, short s) {
        int r22 = r2 % s;
        return (short) (r22 + (s & (((r22 ^ s) & ((-r22) | r22)) >> 31)));
    }

    private static final int floorDiv(int r2, int r3) {
        int r0 = r2 / r3;
        return ((r2 ^ r3) >= 0 || r3 * r0 == r2) ? r0 : r0 - 1;
    }

    private static final int mod(int r2, int r3) {
        int r22 = r2 % r3;
        return r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31));
    }

    private static final long floorDiv(int r8, long j) {
        long j2 = r8;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final long mod(int r6, long j) {
        long j2 = r6 % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final long floorDiv(long j, byte b) {
        long j2 = b;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final byte mod(long j, byte b) {
        long j2;
        long j3 = j % b;
        return (byte) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final long floorDiv(long j, short s) {
        long j2 = s;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final short mod(long j, short s) {
        long j2;
        long j3 = j % s;
        return (short) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final long floorDiv(long j, int r10) {
        long j2 = r10;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final int mod(long j, int r8) {
        long j2 = r8;
        long j3 = j % j2;
        return (int) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final long floorDiv(long j, long j2) {
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63));
    }

    private static final float mod(float f, float f2) {
        float f3 = f % f2;
        if (f3 == 0.0f) {
            return f3;
        }
        return !(Math.signum(f3) == Math.signum(f2)) ? f3 + f2 : f3;
    }

    private static final double mod(float f, double d) {
        double d2 = f % d;
        if (d2 == 0.0d) {
            return d2;
        }
        return !(Math.signum(d2) == Math.signum(d)) ? d2 + d : d2;
    }

    private static final double mod(double d, float f) {
        double d2 = f;
        double d3 = d % d2;
        if (d3 == 0.0d) {
            return d3;
        }
        return !(Math.signum(d3) == Math.signum(d2)) ? d3 + d2 : d3;
    }

    private static final double mod(double d, double d2) {
        double d3 = d % d2;
        if (d3 == 0.0d) {
            return d3;
        }
        return !(Math.signum(d3) == Math.signum(d2)) ? d3 + d2 : d3;
    }
}
