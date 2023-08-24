package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class PathParser {
    static ArrayList<PathElement> elements;

    /* renamed from: i */
    private static int f1259i;

    /* renamed from: l */
    private static int f1260l;
    private static Path mPath;
    private static boolean mPenDown;
    private static float mPenDownX;
    private static float mPenDownY;
    private static float mPenX;
    private static float mPenY;
    private static float mPivotX;
    private static float mPivotY;
    static float mScale;

    /* renamed from: s */
    private static String f1261s;

    private static boolean is_cmd(char c) {
        switch (c) {
            case 'A':
            case 'C':
            case 'H':
            case 'L':
            case 'M':
            case 'Q':
            case 'S':
            case 'T':
            case 'V':
            case 'Z':
            case 'a':
            case 'c':
            case 'h':
            case 'l':
            case 'm':
            case 'q':
            case 's':
            case 't':
            case 'v':
            case 'z':
                return true;
            default:
                return false;
        }
    }

    private static boolean is_number_start(char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+';
    }

    PathParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ae A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Path parse(java.lang.String r25) {
        /*
            Method dump skipped, instructions count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.parse(java.lang.String):android.graphics.Path");
    }

    private static void move(float f, float f2) {
        moveTo(f + mPenX, f2 + mPenY);
    }

    private static void moveTo(float f, float f2) {
        mPenX = f;
        mPivotX = f;
        mPenDownX = f;
        mPenY = f2;
        mPivotY = f2;
        mPenDownY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.moveTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(f, f2)}));
    }

    private static void line(float f, float f2) {
        lineTo(f + mPenX, f2 + mPenY);
    }

    private static void lineTo(float f, float f2) {
        setPenDown();
        mPenX = f;
        mPivotX = f;
        mPenY = f2;
        mPivotY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.lineTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(f, f2)}));
    }

    private static void curve(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = mPenX;
        float f8 = mPenY;
        curveTo(f + f7, f2 + f8, f3 + f7, f4 + f8, f5 + f7, f6 + f8);
    }

    private static void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        mPivotX = f3;
        mPivotY = f4;
        cubicTo(f, f2, f3, f4, f5, f6);
    }

    private static void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        setPenDown();
        mPenX = f5;
        mPenY = f6;
        Path path = mPath;
        float f7 = mScale;
        path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f6 * f7);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f, f2), new Point(f3, f4), new Point(f5, f6)}));
    }

    private static void smoothCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        smoothCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void smoothCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        cubicTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2, f3, f4);
    }

    private static void quadraticBezierCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        quadraticBezierCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        float f5 = f * 2.0f;
        float f6 = f2 * 2.0f;
        float f7 = (mPenX + f5) / 3.0f;
        float f8 = (mPenY + f6) / 3.0f;
        cubicTo(f7, f8, (f3 + f5) / 3.0f, (f4 + f6) / 3.0f, f3, f4);
    }

    private static void smoothQuadraticBezierCurve(float f, float f2) {
        smoothQuadraticBezierCurveTo(f + mPenX, f2 + mPenY);
    }

    private static void smoothQuadraticBezierCurveTo(float f, float f2) {
        quadraticBezierCurveTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2);
    }

    private static void arc(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        arcTo(f, f2, f3, z, z2, f4 + mPenX, f5 + mPenY);
    }

    private static void arcTo(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12 = mPenX;
        float f13 = mPenY;
        float abs = Math.abs(f2 == 0.0f ? f == 0.0f ? f5 - f13 : f : f2);
        float abs2 = Math.abs(f == 0.0f ? f4 - f12 : f);
        if (abs2 == 0.0f || abs == 0.0f || (f4 == f12 && f5 == f13)) {
            lineTo(f4, f5);
            return;
        }
        float radians = (float) Math.toRadians(f3);
        double d = radians;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        float f14 = f4 - f12;
        float f15 = f5 - f13;
        float f16 = ((cos * f14) / 2.0f) + ((sin * f15) / 2.0f);
        float f17 = -sin;
        float f18 = ((f17 * f14) / 2.0f) + ((cos * f15) / 2.0f);
        float f19 = abs2 * abs2;
        float f20 = abs * abs * f16 * f16;
        if ((((f19 * abs) * abs) - ((f19 * f18) * f18)) - f20 < 0.0f) {
            f9 = f17;
            float sqrt = (float) Math.sqrt(1.0f - (f8 / f6));
            abs2 *= sqrt;
            abs *= sqrt;
            f10 = f14 / 2.0f;
            f11 = f15 / 2.0f;
        } else {
            f9 = f17;
            float sqrt2 = (float) Math.sqrt(f8 / (f7 + f20));
            if (z == z2) {
                sqrt2 = -sqrt2;
            }
            float f21 = (((-sqrt2) * f18) * abs2) / abs;
            float f22 = ((sqrt2 * f16) * abs) / abs2;
            f10 = ((cos * f21) - (sin * f22)) + (f14 / 2.0f);
            f11 = (f15 / 2.0f) + (f21 * sin) + (f22 * cos);
        }
        float f23 = cos / abs2;
        float f24 = sin / abs2;
        float f25 = f9 / abs;
        float f26 = cos / abs;
        float f27 = -f10;
        float f28 = -f11;
        float f29 = abs;
        float f30 = abs2;
        float atan2 = (float) Math.atan2((f25 * f27) + (f26 * f28), (f27 * f23) + (f28 * f24));
        float f31 = f14 - f10;
        float f32 = f15 - f11;
        float atan22 = (float) Math.atan2((f25 * f31) + (f26 * f32), (f23 * f31) + (f24 * f32));
        float f33 = f10 + f12;
        float f34 = f11 + f13;
        float f35 = f14 + f12;
        float f36 = f15 + f13;
        setPenDown();
        mPivotX = f35;
        mPenX = f35;
        mPivotY = f36;
        mPenY = f36;
        if (f30 != f29 || radians != 0.0f) {
            arcToBezier(f33, f34, f30, f29, atan2, atan22, z2, radians);
            return;
        }
        float degrees = (float) Math.toDegrees(atan2);
        float abs3 = Math.abs((degrees - ((float) Math.toDegrees(atan22))) % 360.0f);
        if (!z ? abs3 > 180.0f : abs3 < 180.0f) {
            abs3 = 360.0f - abs3;
        }
        if (!z2) {
            abs3 = -abs3;
        }
        float f37 = mScale;
        mPath.arcTo(new RectF((f33 - f30) * f37, (f34 - f30) * f37, (f33 + f30) * f37, (f34 + f30) * f37), degrees, abs3);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f35, f36)}));
    }

    private static void close() {
        if (mPenDown) {
            mPenX = mPenDownX;
            mPenY = mPenDownY;
            mPenDown = false;
            mPath.close();
            elements.add(new PathElement(ElementType.kCGPathElementCloseSubpath, new Point[]{new Point(mPenX, mPenY)}));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068 A[LOOP:0: B:12:0x0066->B:13:0x0068, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void arcToBezier(float r24, float r25, float r26, float r27, float r28, float r29, boolean r30, float r31) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.arcToBezier(float, float, float, float, float, float, boolean, float):void");
    }

    private static void setPenDown() {
        if (mPenDown) {
            return;
        }
        mPenDownX = mPenX;
        mPenDownY = mPenY;
        mPenDown = true;
    }

    private static double round(double d) {
        double pow = Math.pow(10.0d, 4.0d);
        return Math.round(d * pow) / pow;
    }

    private static void skip_spaces() {
        while (true) {
            int r0 = f1259i;
            if (r0 >= f1260l || !Character.isWhitespace(f1261s.charAt(r0))) {
                return;
            }
            f1259i++;
        }
    }

    private static boolean is_absolute(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean parse_flag() {
        skip_spaces();
        char charAt = f1261s.charAt(f1259i);
        if (charAt == '0' || charAt == '1') {
            int r1 = f1259i + 1;
            f1259i = r1;
            if (r1 < f1260l && f1261s.charAt(r1) == ',') {
                f1259i++;
            }
            skip_spaces();
            return charAt == '1';
        }
        throw new Error(String.format("Unexpected flag '%c' (i=%d, s=%s)", Character.valueOf(charAt), Integer.valueOf(f1259i), f1261s));
    }

    private static float parse_list_number() {
        if (f1259i == f1260l) {
            throw new Error(String.format("Unexpected end (s=%s)", f1261s));
        }
        float parse_number = parse_number();
        skip_spaces();
        parse_list_separator();
        return parse_number;
    }

    private static float parse_number() {
        char charAt;
        skip_spaces();
        int r0 = f1259i;
        if (r0 == f1260l) {
            throw new Error(String.format("Unexpected end (s=%s)", f1261s));
        }
        char charAt2 = f1261s.charAt(r0);
        if (charAt2 == '-' || charAt2 == '+') {
            int r1 = f1259i + 1;
            f1259i = r1;
            charAt2 = f1261s.charAt(r1);
        }
        if (charAt2 >= '0' && charAt2 <= '9') {
            skip_digits();
            int r12 = f1259i;
            if (r12 < f1260l) {
                charAt2 = f1261s.charAt(r12);
            }
        } else if (charAt2 != '.') {
            throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt2), Integer.valueOf(f1259i), f1261s));
        }
        if (charAt2 == '.') {
            f1259i++;
            skip_digits();
            int r8 = f1259i;
            if (r8 < f1260l) {
                charAt2 = f1261s.charAt(r8);
            }
        }
        if (charAt2 == 'e' || charAt2 == 'E') {
            int r13 = f1259i;
            if (r13 + 1 < f1260l && (charAt = f1261s.charAt(r13 + 1)) != 'm' && charAt != 'x') {
                int r14 = f1259i + 1;
                f1259i = r14;
                char charAt3 = f1261s.charAt(r14);
                if (charAt3 == '+' || charAt3 == '-') {
                    f1259i++;
                    skip_digits();
                } else if (charAt3 >= '0' && charAt3 <= '9') {
                    skip_digits();
                } else {
                    throw new Error(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(charAt3), Integer.valueOf(f1259i), f1261s));
                }
            }
        }
        String substring = f1261s.substring(r0, f1259i);
        float parseFloat = Float.parseFloat(substring);
        if (Float.isInfinite(parseFloat) || Float.isNaN(parseFloat)) {
            throw new Error(String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", substring, Integer.valueOf(r0), Integer.valueOf(f1259i), f1261s));
        }
        return parseFloat;
    }

    private static void parse_list_separator() {
        int r0 = f1259i;
        if (r0 >= f1260l || f1261s.charAt(r0) != ',') {
            return;
        }
        f1259i++;
    }

    private static void skip_digits() {
        while (true) {
            int r0 = f1259i;
            if (r0 >= f1260l || !Character.isDigit(f1261s.charAt(r0))) {
                return;
            }
            f1259i++;
        }
    }
}
