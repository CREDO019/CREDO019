package org.bouncycastle.math.p039ec.tools;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.bouncycastle.asn1.p032x9.ECNamedCurveTable;
import org.bouncycastle.asn1.p032x9.X9ECParameters;
import org.bouncycastle.crypto.p033ec.CustomNamedCurves;
import org.bouncycastle.math.p039ec.ECAlgorithms;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;
import org.bouncycastle.util.Integers;

/* renamed from: org.bouncycastle.math.ec.tools.TraceOptimizer */
/* loaded from: classes5.dex */
public class TraceOptimizer {
    private static final BigInteger ONE = BigInteger.valueOf(1);

    /* renamed from: R */
    private static final SecureRandom f2379R = new SecureRandom();

    private static int calculateTrace(ECFieldElement eCFieldElement) {
        int fieldSize = eCFieldElement.getFieldSize();
        int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(fieldSize);
        int r4 = 1;
        ECFieldElement eCFieldElement2 = eCFieldElement;
        while (numberOfLeadingZeros > 0) {
            eCFieldElement2 = eCFieldElement2.squarePow(r4).add(eCFieldElement2);
            numberOfLeadingZeros--;
            r4 = fieldSize >>> numberOfLeadingZeros;
            if ((r4 & 1) != 0) {
                eCFieldElement2 = eCFieldElement2.square().add(eCFieldElement);
            }
        }
        if (eCFieldElement2.isZero()) {
            return 0;
        }
        if (eCFieldElement2.isOne()) {
            return 1;
        }
        throw new IllegalStateException("Internal error in trace calculation");
    }

    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }

    public static void implPrintNonZeroTraceBits(X9ECParameters x9ECParameters) {
        PrintStream printStream;
        StringBuilder sb;
        ECCurve curve = x9ECParameters.getCurve();
        int fieldSize = curve.getFieldSize();
        ArrayList arrayList = new ArrayList();
        for (int r3 = 0; r3 < fieldSize; r3++) {
            if ((r3 & 1) != 0 || r3 == 0) {
                if (calculateTrace(curve.fromBigInteger(ONE.shiftLeft(r3))) != 0) {
                    arrayList.add(Integers.valueOf(r3));
                    printStream = System.out;
                    sb = new StringBuilder();
                    sb.append(" ");
                    sb.append(r3);
                    printStream.print(sb.toString());
                }
            } else if (arrayList.contains(Integers.valueOf(r3 >>> 1))) {
                arrayList.add(Integers.valueOf(r3));
                printStream = System.out;
                sb = new StringBuilder();
                sb.append(" ");
                sb.append(r3);
                printStream.print(sb.toString());
            }
        }
        System.out.println();
        for (int r32 = 0; r32 < 1000; r32++) {
            BigInteger bigInteger = new BigInteger(fieldSize, f2379R);
            int calculateTrace = calculateTrace(curve.fromBigInteger(bigInteger));
            int r7 = 0;
            for (int r6 = 0; r6 < arrayList.size(); r6++) {
                if (bigInteger.testBit(((Integer) arrayList.get(r6)).intValue())) {
                    r7 ^= 1;
                }
            }
            if (calculateTrace != r7) {
                throw new IllegalStateException("Optimized-trace sanity check failed");
            }
        }
    }

    public static void main(String[] strArr) {
        TreeSet<String> treeSet = new TreeSet(enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(enumToList(CustomNamedCurves.getNames()));
        for (String str : treeSet) {
            X9ECParameters byName = CustomNamedCurves.getByName(str);
            if (byName == null) {
                byName = ECNamedCurveTable.getByName(str);
            }
            if (byName != null && ECAlgorithms.isF2mCurve(byName.getCurve())) {
                PrintStream printStream = System.out;
                printStream.print(str + ParameterizedMessage.ERROR_MSG_SEPARATOR);
                implPrintNonZeroTraceBits(byName);
            }
        }
    }

    public static void printNonZeroTraceBits(X9ECParameters x9ECParameters) {
        if (!ECAlgorithms.isF2mCurve(x9ECParameters.getCurve())) {
            throw new IllegalArgumentException("Trace only defined over characteristic-2 fields");
        }
        implPrintNonZeroTraceBits(x9ECParameters);
    }
}
