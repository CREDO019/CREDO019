package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* loaded from: classes3.dex */
public class PolyUtil {
    private static final double DEFAULT_TOLERANCE = 0.1d;

    private PolyUtil() {
    }

    private static double tanLatGC(double d, double d2, double d3, double d4) {
        return ((Math.tan(d) * Math.sin(d3 - d4)) + (Math.tan(d2) * Math.sin(d4))) / Math.sin(d3);
    }

    private static double mercatorLatRhumb(double d, double d2, double d3, double d4) {
        return ((MathUtil.mercator(d) * (d3 - d4)) + (MathUtil.mercator(d2) * d4)) / d3;
    }

    private static boolean intersects(double d, double d2, double d3, double d4, double d5, boolean z) {
        if ((d5 < 0.0d || d5 < d3) && ((d5 >= 0.0d || d5 >= d3) && d4 > -1.5707963267948966d && d > -1.5707963267948966d && d2 > -1.5707963267948966d && d < 1.5707963267948966d && d2 < 1.5707963267948966d && d3 > -3.141592653589793d)) {
            double d6 = (((d3 - d5) * d) + (d2 * d5)) / d3;
            if (d < 0.0d || d2 < 0.0d || d4 >= d6) {
                if ((d > 0.0d || d2 > 0.0d || d4 < d6) && d4 < 1.5707963267948966d) {
                    if (z) {
                        if (Math.tan(d4) < tanLatGC(d, d2, d3, d5)) {
                            return false;
                        }
                    } else if (MathUtil.mercator(d4) < mercatorLatRhumb(d, d2, d3, d5)) {
                        return false;
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean containsLocation(LatLng latLng, List<LatLng> list, boolean z) {
        return containsLocation(latLng.latitude, latLng.longitude, list, z);
    }

    public static boolean containsLocation(double d, double d2, List<LatLng> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        LatLng latLng = list.get(size - 1);
        double radians3 = Math.toRadians(latLng.latitude);
        double radians4 = Math.toRadians(latLng.longitude);
        double d3 = radians3;
        int r18 = 0;
        for (LatLng latLng2 : list) {
            double wrap = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
            if (radians == d3 && wrap == 0.0d) {
                return true;
            }
            double radians5 = Math.toRadians(latLng2.latitude);
            double radians6 = Math.toRadians(latLng2.longitude);
            if (intersects(d3, radians5, MathUtil.wrap(radians6 - radians4, -3.141592653589793d, 3.141592653589793d), radians, wrap, z)) {
                r18++;
            }
            d3 = radians5;
            radians4 = radians6;
        }
        return (r18 & 1) != 0;
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, true, z, d);
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnEdge(latLng, list, z, DEFAULT_TOLERANCE);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, false, z, d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnPath(latLng, list, z, DEFAULT_TOLERANCE);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d) {
        List<LatLng> list2;
        int r1;
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double d2 = d / 6371009.0d;
        double hav = MathUtil.hav(d2);
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        if (z) {
            r1 = size - 1;
            list2 = list;
        } else {
            list2 = list;
            r1 = 0;
        }
        LatLng latLng2 = list2.get(r1);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        if (z2) {
            double d3 = radians3;
            double d4 = radians4;
            for (LatLng latLng3 : list) {
                double radians5 = Math.toRadians(latLng3.latitude);
                double radians6 = Math.toRadians(latLng3.longitude);
                if (isOnSegmentGC(d3, d4, radians5, radians6, radians, radians2, hav)) {
                    return true;
                }
                d4 = radians6;
                d3 = radians5;
            }
            return false;
        }
        double d5 = radians - d2;
        double d6 = radians + d2;
        double mercator = MathUtil.mercator(radians3);
        double mercator2 = MathUtil.mercator(radians);
        double[] dArr = new double[3];
        Iterator<LatLng> it = list.iterator();
        while (it.hasNext()) {
            LatLng next = it.next();
            double d7 = d6;
            double radians7 = Math.toRadians(next.latitude);
            double mercator3 = MathUtil.mercator(radians7);
            Iterator<LatLng> it2 = it;
            double radians8 = Math.toRadians(next.longitude);
            if (Math.max(radians3, radians7) >= d5 && Math.min(radians3, radians7) <= d7) {
                double wrap = MathUtil.wrap(radians8 - radians4, -3.141592653589793d, 3.141592653589793d);
                double wrap2 = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
                dArr[0] = wrap2;
                dArr[1] = wrap2 + 6.283185307179586d;
                dArr[2] = wrap2 - 6.283185307179586d;
                for (int r12 = 0; r12 < 3; r12++) {
                    double d8 = dArr[r12];
                    double d9 = mercator3 - mercator;
                    double d10 = (wrap * wrap) + (d9 * d9);
                    double clamp = d10 > 0.0d ? MathUtil.clamp(((d8 * wrap) + ((mercator2 - mercator) * d9)) / d10, 0.0d, 1.0d) : 0.0d;
                    if (MathUtil.havDistance(radians, MathUtil.inverseMercator(mercator + (clamp * d9)), d8 - (clamp * wrap)) < hav) {
                        return true;
                    }
                }
                continue;
            }
            radians3 = radians7;
            radians4 = radians8;
            d6 = d7;
            mercator = mercator3;
            it = it2;
        }
        return false;
    }

    private static double sinDeltaBearing(double d, double d2, double d3, double d4, double d5, double d6) {
        double sin = Math.sin(d);
        double cos = Math.cos(d3);
        double cos2 = Math.cos(d5);
        double d7 = d6 - d2;
        double d8 = d4 - d2;
        double sin2 = Math.sin(d7) * cos2;
        double sin3 = Math.sin(d8) * cos;
        double d9 = sin * 2.0d;
        double sin4 = Math.sin(d5 - d) + (cos2 * d9 * MathUtil.hav(d7));
        double sin5 = Math.sin(d3 - d) + (d9 * cos * MathUtil.hav(d8));
        double d10 = ((sin2 * sin2) + (sin4 * sin4)) * ((sin3 * sin3) + (sin5 * sin5));
        if (d10 <= 0.0d) {
            return 1.0d;
        }
        return ((sin2 * sin5) - (sin4 * sin3)) / Math.sqrt(d10);
    }

    private static boolean isOnSegmentGC(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double havDistance = MathUtil.havDistance(d, d5, d2 - d6);
        if (havDistance <= d7) {
            return true;
        }
        double havDistance2 = MathUtil.havDistance(d3, d5, d4 - d6);
        if (havDistance2 <= d7) {
            return true;
        }
        double havFromSin = MathUtil.havFromSin(MathUtil.sinFromHav(havDistance) * sinDeltaBearing(d, d2, d3, d4, d5, d6));
        if (havFromSin > d7) {
            return false;
        }
        double havDistance3 = MathUtil.havDistance(d, d3, d2 - d4);
        double d8 = ((1.0d - (havDistance3 * 2.0d)) * havFromSin) + havDistance3;
        if (havDistance > d8 || havDistance2 > d8) {
            return false;
        }
        if (havDistance3 < 0.74d) {
            return true;
        }
        double d9 = 1.0d - (2.0d * havFromSin);
        return MathUtil.sinSumFromHav((havDistance - havFromSin) / d9, (havDistance2 - havFromSin) / d9) > 0.0d;
    }

    public static List<LatLng> simplify(List<LatLng> list, double d) {
        int size = list.size();
        if (size >= 1) {
            double d2 = 0.0d;
            if (d <= 0.0d) {
                throw new IllegalArgumentException("Tolerance must be greater than zero");
            }
            boolean isClosedPolygon = isClosedPolygon(list);
            LatLng latLng = null;
            if (isClosedPolygon) {
                latLng = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(new LatLng(latLng.latitude + 1.0E-11d, latLng.longitude + 1.0E-11d));
            }
            Stack stack = new Stack();
            double[] dArr = new double[size];
            int r9 = 0;
            dArr[0] = 1.0d;
            int r12 = size - 1;
            dArr[r12] = 1.0d;
            if (size > 2) {
                stack.push(new int[]{0, r12});
                int r1 = 0;
                while (stack.size() > 0) {
                    int[] r11 = (int[]) stack.pop();
                    double d3 = d2;
                    for (int r122 = r11[0] + 1; r122 < r11[1]; r122++) {
                        double distanceToLine = distanceToLine(list.get(r122), list.get(r11[0]), list.get(r11[1]));
                        if (distanceToLine > d3) {
                            d3 = distanceToLine;
                            r1 = r122;
                        }
                    }
                    if (d3 > d) {
                        dArr[r1] = d3;
                        stack.push(new int[]{r11[0], r1});
                        stack.push(new int[]{r1, r11[1]});
                    }
                    d2 = 0.0d;
                }
            }
            if (isClosedPolygon) {
                list.remove(list.size() - 1);
                list.add(latLng);
            }
            ArrayList arrayList = new ArrayList();
            for (LatLng latLng2 : list) {
                if (dArr[r9] != 0.0d) {
                    arrayList.add(latLng2);
                }
                r9++;
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Polyline must have at least 1 point");
    }

    public static boolean isClosedPolygon(List<LatLng> list) {
        return list.get(0).equals(list.get(list.size() - 1));
    }

    public static double distanceToLine(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng2.equals(latLng3)) {
            return SphericalUtil.computeDistanceBetween(latLng3, latLng);
        }
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        double radians5 = Math.toRadians(latLng3.latitude) - radians3;
        double radians6 = Math.toRadians(latLng3.longitude) - radians4;
        double d = (((radians - radians3) * radians5) + ((radians2 - radians4) * radians6)) / ((radians5 * radians5) + (radians6 * radians6));
        if (d <= 0.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng2);
        }
        if (d >= 1.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng3);
        }
        return SphericalUtil.computeDistanceBetween(new LatLng(latLng.latitude - latLng2.latitude, latLng.longitude - latLng2.longitude), new LatLng((latLng3.latitude - latLng2.latitude) * d, d * (latLng3.longitude - latLng2.longitude)));
    }

    public static List<LatLng> decode(String str) {
        int r9;
        int r8;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        while (r3 < length) {
            int r7 = 1;
            int r82 = 0;
            while (true) {
                r9 = r3 + 1;
                int charAt = (str.charAt(r3) - '?') - 1;
                r7 += charAt << r82;
                r82 += 5;
                if (charAt < 31) {
                    break;
                }
                r3 = r9;
            }
            int r32 = ((r7 & 1) != 0 ? ~(r7 >> 1) : r7 >> 1) + r4;
            int r42 = 1;
            int r72 = 0;
            while (true) {
                r8 = r9 + 1;
                int charAt2 = (str.charAt(r9) - '?') - 1;
                r42 += charAt2 << r72;
                r72 += 5;
                if (charAt2 < 31) {
                    break;
                }
                r9 = r8;
            }
            int r6 = r42 & 1;
            int r43 = r42 >> 1;
            if (r6 != 0) {
                r43 = ~r43;
            }
            r5 += r43;
            arrayList.add(new LatLng(r32 * 1.0E-5d, r5 * 1.0E-5d));
            r4 = r32;
            r3 = r8;
        }
        return arrayList;
    }

    public static String encode(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        long j = 0;
        long j2 = 0;
        for (LatLng latLng : list) {
            long round = Math.round(latLng.latitude * 100000.0d);
            long round2 = Math.round(latLng.longitude * 100000.0d);
            encode(round - j, stringBuffer);
            encode(round2 - j2, stringBuffer);
            j = round;
            j2 = round2;
        }
        return stringBuffer.toString();
    }

    private static void encode(long j, StringBuffer stringBuffer) {
        int r3 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        long j2 = j << 1;
        if (r3 < 0) {
            j2 = ~j2;
        }
        while (j2 >= 32) {
            stringBuffer.append(Character.toChars((int) ((32 | (31 & j2)) + 63)));
            j2 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j2 + 63)));
    }
}
