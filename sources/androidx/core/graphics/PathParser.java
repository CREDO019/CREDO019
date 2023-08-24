package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int r3, int r4) {
        if (r3 > r4) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (r3 < 0 || r3 > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int r42 = r4 - r3;
        int min = Math.min(r42, length - r3);
        float[] fArr2 = new float[r42];
        System.arraycopy(fArr, r3, fArr2, 0, min);
        return fArr2;
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData != null) {
            try {
                PathDataNode.nodesToPath(createNodesFromPathData, path);
                return path;
            } catch (RuntimeException e) {
                throw new RuntimeException("Error in parsing " + str, e);
            }
        }
        return null;
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int r3 = 1;
        int r4 = 0;
        while (r3 < str.length()) {
            int nextStart = nextStart(str, r3);
            String trim = str.substring(r4, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            r4 = nextStart;
            r3 = nextStart + 1;
        }
        if (r3 - r4 == 1 && r4 < str.length()) {
            addNode(arrayList, str.charAt(r4), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int r1 = 0; r1 < pathDataNodeArr.length; r1++) {
            pathDataNodeArr2[r1] = new PathDataNode(pathDataNodeArr[r1]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int r1 = 0; r1 < pathDataNodeArr.length; r1++) {
            if (pathDataNodeArr[r1].mType != pathDataNodeArr2[r1].mType || pathDataNodeArr[r1].mParams.length != pathDataNodeArr2[r1].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int r1 = 0; r1 < pathDataNodeArr2.length; r1++) {
            pathDataNodeArr[r1].mType = pathDataNodeArr2[r1].mType;
            for (int r2 = 0; r2 < pathDataNodeArr2[r1].mParams.length; r2++) {
                pathDataNodeArr[r1].mParams[r2] = pathDataNodeArr2[r1].mParams[r2];
            }
        }
    }

    private static int nextStart(String str, int r4) {
        while (r4 < str.length()) {
            char charAt = str.charAt(r4);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return r4;
            }
            r4++;
        }
        return r4;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int r4 = 1;
            int r5 = 0;
            while (r4 < length) {
                extract(str, r4, extractFloatResult);
                int r6 = extractFloatResult.mEndPosition;
                if (r4 < r6) {
                    fArr[r5] = Float.parseFloat(str.substring(r4, r6));
                    r5++;
                }
                r4 = extractFloatResult.mEndWithNegOrDot ? r6 : r6 + 1;
            }
            return copyOfRange(fArr, 0, r5);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003a A[LOOP:0: B:3:0x0007->B:24:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void extract(java.lang.String r8, int r9, androidx.core.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = 0
            r3 = 0
            r4 = 0
        L7:
            int r5 = r8.length()
            if (r1 >= r5) goto L3d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L35
            r6 = 69
            if (r5 == r6) goto L33
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L33
            switch(r5) {
                case 44: goto L35;
                case 45: goto L2a;
                case 46: goto L22;
                default: goto L21;
            }
        L21:
            goto L31
        L22:
            if (r3 != 0) goto L27
            r2 = 0
            r3 = 1
            goto L37
        L27:
            r10.mEndWithNegOrDot = r7
            goto L35
        L2a:
            if (r1 == r9) goto L31
            if (r2 != 0) goto L31
            r10.mEndWithNegOrDot = r7
            goto L35
        L31:
            r2 = 0
            goto L37
        L33:
            r2 = 1
            goto L37
        L35:
            r2 = 0
            r4 = 1
        L37:
            if (r4 == 0) goto L3a
            goto L3d
        L3a:
            int r1 = r1 + 1
            goto L7
        L3d:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.extract(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    public static boolean interpolatePathDataNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2, PathDataNode[] pathDataNodeArr3, float f) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr3 == null) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
        }
        if (pathDataNodeArr.length != pathDataNodeArr2.length || pathDataNodeArr2.length != pathDataNodeArr3.length) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
        }
        if (canMorph(pathDataNodeArr2, pathDataNodeArr3)) {
            for (int r1 = 0; r1 < pathDataNodeArr.length; r1++) {
                pathDataNodeArr[r1].interpolatePathDataNode(pathDataNodeArr2[r1], pathDataNodeArr3[r1], f);
            }
            return true;
        }
        return false;
    }

    /* loaded from: classes.dex */
    public static class PathDataNode {
        public float[] mParams;
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int r2 = 0; r2 < pathDataNodeArr.length; r2++) {
                addCommand(path, fArr, c, pathDataNodeArr[r2].mType, pathDataNodeArr[r2].mParams);
                c = pathDataNodeArr[r2].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            this.mType = pathDataNode.mType;
            int r0 = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (r0 >= fArr.length) {
                    return;
                }
                this.mParams[r0] = (fArr[r0] * (1.0f - f)) + (pathDataNode2.mParams[r0] * f);
                r0++;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int r19;
            int r24;
            float f;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            char c3 = c2;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[2];
            float f12 = fArr[3];
            float f13 = fArr[4];
            float f14 = fArr[5];
            switch (c3) {
                case 'A':
                case 'a':
                    r19 = 7;
                    break;
                case 'C':
                case 'c':
                    r19 = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    r19 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                default:
                    r19 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    r19 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f13, f14);
                    f9 = f13;
                    f11 = f9;
                    f10 = f14;
                    f12 = f10;
                    r19 = 2;
                    break;
            }
            float f15 = f9;
            float f16 = f10;
            float f17 = f13;
            float f18 = f14;
            int r7 = 0;
            char c4 = c;
            while (r7 < fArr2.length) {
                if (c3 != 'A') {
                    if (c3 == 'C') {
                        r24 = r7;
                        int r72 = r24 + 2;
                        int r8 = r24 + 3;
                        int r9 = r24 + 4;
                        int r11 = r24 + 5;
                        path.cubicTo(fArr2[r24 + 0], fArr2[r24 + 1], fArr2[r72], fArr2[r8], fArr2[r9], fArr2[r11]);
                        f15 = fArr2[r9];
                        float f19 = fArr2[r11];
                        float f20 = fArr2[r72];
                        float f21 = fArr2[r8];
                        f16 = f19;
                        f12 = f21;
                        f11 = f20;
                    } else if (c3 == 'H') {
                        r24 = r7;
                        int r73 = r24 + 0;
                        path.lineTo(fArr2[r73], f16);
                        f15 = fArr2[r73];
                    } else if (c3 == 'Q') {
                        r24 = r7;
                        int r74 = r24 + 0;
                        int r1 = r24 + 1;
                        int r3 = r24 + 2;
                        int r5 = r24 + 3;
                        path.quadTo(fArr2[r74], fArr2[r1], fArr2[r3], fArr2[r5]);
                        float f22 = fArr2[r74];
                        float f23 = fArr2[r1];
                        f15 = fArr2[r3];
                        f16 = fArr2[r5];
                        f11 = f22;
                        f12 = f23;
                    } else if (c3 == 'V') {
                        r24 = r7;
                        int r75 = r24 + 0;
                        path.lineTo(f15, fArr2[r75]);
                        f16 = fArr2[r75];
                    } else if (c3 != 'a') {
                        if (c3 != 'c') {
                            if (c3 == 'h') {
                                int r0 = r7 + 0;
                                path.rLineTo(fArr2[r0], 0.0f);
                                f15 += fArr2[r0];
                            } else if (c3 != 'q') {
                                if (c3 == 'v') {
                                    int r02 = r7 + 0;
                                    path.rLineTo(0.0f, fArr2[r02]);
                                    f4 = fArr2[r02];
                                } else if (c3 == 'L') {
                                    int r03 = r7 + 0;
                                    int r4 = r7 + 1;
                                    path.lineTo(fArr2[r03], fArr2[r4]);
                                    f15 = fArr2[r03];
                                    f16 = fArr2[r4];
                                } else if (c3 == 'M') {
                                    int r04 = r7 + 0;
                                    f15 = fArr2[r04];
                                    int r12 = r7 + 1;
                                    f16 = fArr2[r12];
                                    if (r7 > 0) {
                                        path.lineTo(fArr2[r04], fArr2[r12]);
                                    } else {
                                        path.moveTo(fArr2[r04], fArr2[r12]);
                                        r24 = r7;
                                        f18 = f16;
                                        f17 = f15;
                                    }
                                } else if (c3 == 'S') {
                                    if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                        f15 = (f15 * 2.0f) - f11;
                                        f16 = (f16 * 2.0f) - f12;
                                    }
                                    float f24 = f16;
                                    int r82 = r7 + 0;
                                    int r92 = r7 + 1;
                                    int r13 = r7 + 2;
                                    int r14 = r7 + 3;
                                    path.cubicTo(f15, f24, fArr2[r82], fArr2[r92], fArr2[r13], fArr2[r14]);
                                    f = fArr2[r82];
                                    f2 = fArr2[r92];
                                    f15 = fArr2[r13];
                                    f16 = fArr2[r14];
                                    f11 = f;
                                    f12 = f2;
                                } else if (c3 == 'T') {
                                    if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                        f15 = (f15 * 2.0f) - f11;
                                        f16 = (f16 * 2.0f) - f12;
                                    }
                                    int r05 = r7 + 0;
                                    int r2 = r7 + 1;
                                    path.quadTo(f15, f16, fArr2[r05], fArr2[r2]);
                                    float f25 = fArr2[r05];
                                    float f26 = fArr2[r2];
                                    r24 = r7;
                                    f12 = f16;
                                    f11 = f15;
                                    f15 = f25;
                                    f16 = f26;
                                } else if (c3 == 'l') {
                                    int r06 = r7 + 0;
                                    int r42 = r7 + 1;
                                    path.rLineTo(fArr2[r06], fArr2[r42]);
                                    f15 += fArr2[r06];
                                    f4 = fArr2[r42];
                                } else if (c3 == 'm') {
                                    int r07 = r7 + 0;
                                    f15 += fArr2[r07];
                                    int r15 = r7 + 1;
                                    f16 += fArr2[r15];
                                    if (r7 > 0) {
                                        path.rLineTo(fArr2[r07], fArr2[r15]);
                                    } else {
                                        path.rMoveTo(fArr2[r07], fArr2[r15]);
                                        r24 = r7;
                                        f18 = f16;
                                        f17 = f15;
                                    }
                                } else if (c3 == 's') {
                                    if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                        float f27 = f15 - f11;
                                        f5 = f16 - f12;
                                        f6 = f27;
                                    } else {
                                        f6 = 0.0f;
                                        f5 = 0.0f;
                                    }
                                    int r132 = r7 + 0;
                                    int r142 = r7 + 1;
                                    int r152 = r7 + 2;
                                    int r22 = r7 + 3;
                                    path.rCubicTo(f6, f5, fArr2[r132], fArr2[r142], fArr2[r152], fArr2[r22]);
                                    f = fArr2[r132] + f15;
                                    f2 = fArr2[r142] + f16;
                                    f15 += fArr2[r152];
                                    f3 = fArr2[r22];
                                } else if (c3 == 't') {
                                    if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                        f7 = f15 - f11;
                                        f8 = f16 - f12;
                                    } else {
                                        f8 = 0.0f;
                                        f7 = 0.0f;
                                    }
                                    int r16 = r7 + 0;
                                    int r32 = r7 + 1;
                                    path.rQuadTo(f7, f8, fArr2[r16], fArr2[r32]);
                                    float f28 = f7 + f15;
                                    float f29 = f8 + f16;
                                    f15 += fArr2[r16];
                                    f16 += fArr2[r32];
                                    f12 = f29;
                                    f11 = f28;
                                }
                                f16 += f4;
                            } else {
                                int r08 = r7 + 0;
                                int r23 = r7 + 1;
                                int r43 = r7 + 2;
                                int r6 = r7 + 3;
                                path.rQuadTo(fArr2[r08], fArr2[r23], fArr2[r43], fArr2[r6]);
                                f = fArr2[r08] + f15;
                                f2 = fArr2[r23] + f16;
                                f15 += fArr2[r43];
                                f3 = fArr2[r6];
                            }
                            r24 = r7;
                        } else {
                            int r133 = r7 + 2;
                            int r143 = r7 + 3;
                            int r153 = r7 + 4;
                            int r222 = r7 + 5;
                            path.rCubicTo(fArr2[r7 + 0], fArr2[r7 + 1], fArr2[r133], fArr2[r143], fArr2[r153], fArr2[r222]);
                            f = fArr2[r133] + f15;
                            f2 = fArr2[r143] + f16;
                            f15 += fArr2[r153];
                            f3 = fArr2[r222];
                        }
                        f16 += f3;
                        f11 = f;
                        f12 = f2;
                        r24 = r7;
                    } else {
                        int r134 = r7 + 5;
                        int r144 = r7 + 6;
                        r24 = r7;
                        drawArc(path, f15, f16, fArr2[r134] + f15, fArr2[r144] + f16, fArr2[r7 + 0], fArr2[r7 + 1], fArr2[r7 + 2], fArr2[r7 + 3] != 0.0f, fArr2[r7 + 4] != 0.0f);
                        f15 += fArr2[r134];
                        f16 += fArr2[r144];
                    }
                    r7 = r24 + r19;
                    c4 = c2;
                    c3 = c4;
                } else {
                    r24 = r7;
                    int r135 = r24 + 5;
                    int r145 = r24 + 6;
                    drawArc(path, f15, f16, fArr2[r135], fArr2[r145], fArr2[r24 + 0], fArr2[r24 + 1], fArr2[r24 + 2], fArr2[r24 + 3] != 0.0f, fArr2[r24 + 4] != 0.0f);
                    f15 = fArr2[r135];
                    f16 = fArr2[r145];
                }
                f12 = f16;
                f11 = f15;
                r7 = r24 + r19;
                c4 = c2;
                c3 = c4;
            }
            fArr[0] = f15;
            fArr[1] = f16;
            fArr[2] = f11;
            fArr[3] = f12;
            fArr[4] = f17;
            fArr[5] = f18;
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = f;
            double d4 = d3 * cos;
            double d5 = f2;
            double d6 = f5;
            double d7 = (d4 + (d5 * sin)) / d6;
            double d8 = f6;
            double d9 = (((-f) * sin) + (d5 * cos)) / d8;
            double d10 = f4;
            double d11 = ((f3 * cos) + (d10 * sin)) / d6;
            double d12 = (((-f3) * sin) + (d10 * cos)) / d8;
            double d13 = d7 - d11;
            double d14 = d9 - d12;
            double d15 = (d7 + d11) / 2.0d;
            double d16 = (d9 + d12) / 2.0d;
            double d17 = (d13 * d13) + (d14 * d14);
            if (d17 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d18 = (1.0d / d17) - 0.25d;
            if (d18 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d17);
                float sqrt = (float) (Math.sqrt(d17) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d18);
            double d19 = d13 * sqrt2;
            double d20 = sqrt2 * d14;
            if (z == z2) {
                d = d15 - d20;
                d2 = d16 + d19;
            } else {
                d = d15 + d20;
                d2 = d16 - d19;
            }
            double atan2 = Math.atan2(d9 - d2, d7 - d);
            double atan22 = Math.atan2(d12 - d2, d11 - d) - atan2;
            int r2 = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            if (z2 != (r2 >= 0)) {
                atan22 = r2 > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d21 = d * d6;
            double d22 = d2 * d8;
            arcToBezier(path, (d21 * cos) - (d22 * sin), (d21 * sin) + (d22 * cos), d6, d8, d3, d5, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = (d12 * sin2) - (d13 * cos2);
            double d15 = d11 * sin;
            double d16 = d4 * cos;
            double d17 = (sin2 * d15) + (cos2 * d16);
            double d18 = d9 / ceil;
            double d19 = d8;
            double d20 = d17;
            double d21 = d14;
            int r2 = 0;
            double d22 = d5;
            double d23 = d6;
            while (r2 < ceil) {
                double d24 = d19 + d18;
                double sin3 = Math.sin(d24);
                double cos3 = Math.cos(d24);
                double d25 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                double d26 = d2 + (d10 * sin * cos3) + (d16 * sin3);
                double d27 = (d12 * sin3) - (d13 * cos3);
                double d28 = (sin3 * d15) + (cos3 * d16);
                double d29 = d24 - d19;
                double tan = Math.tan(d29 / 2.0d);
                double sin4 = (Math.sin(d29) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                double d30 = d22 + (d21 * sin4);
                double d31 = cos;
                double d32 = sin;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) d30, (float) (d23 + (d20 * sin4)), (float) (d25 - (sin4 * d27)), (float) (d26 - (sin4 * d28)), (float) d25, (float) d26);
                r2++;
                d18 = d18;
                sin = d32;
                d22 = d25;
                d15 = d15;
                cos = d31;
                d19 = d24;
                d20 = d28;
                d21 = d27;
                ceil = ceil;
                d23 = d26;
                d10 = d3;
            }
        }
    }

    private PathParser() {
    }
}
