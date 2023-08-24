package com.horcrux.svg;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
class RNSVGMarkerPosition {
    private static boolean auto_start_reverse_;
    private static int element_index_;
    private static Point in_slope_;
    private static Point origin_;
    private static Point out_slope_;
    private static ArrayList<RNSVGMarkerPosition> positions_;
    private static Point subpath_start_;
    double angle;
    Point origin;
    RNSVGMarkerType type;

    private static double rad2deg(double d) {
        return d * 57.29577951308232d;
    }

    private RNSVGMarkerPosition(RNSVGMarkerType rNSVGMarkerType, Point point, double d) {
        this.type = rNSVGMarkerType;
        this.origin = point;
        this.angle = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<RNSVGMarkerPosition> fromPath(ArrayList<PathElement> arrayList) {
        positions_ = new ArrayList<>();
        element_index_ = 0;
        origin_ = new Point(0.0d, 0.0d);
        subpath_start_ = new Point(0.0d, 0.0d);
        Iterator<PathElement> it = arrayList.iterator();
        while (it.hasNext()) {
            UpdateFromPathElement(it.next());
        }
        PathIsDone();
        return positions_;
    }

    private static void PathIsDone() {
        positions_.add(new RNSVGMarkerPosition(RNSVGMarkerType.kEndMarker, origin_, CurrentAngle(RNSVGMarkerType.kEndMarker)));
    }

    private static double BisectingAngle(double d, double d2) {
        if (Math.abs(d - d2) > 180.0d) {
            d += 360.0d;
        }
        return (d + d2) / 2.0d;
    }

    private static double SlopeAngleRadians(Point point) {
        return Math.atan2(point.f1267y, point.f1266x);
    }

    private static double CurrentAngle(RNSVGMarkerType rNSVGMarkerType) {
        double rad2deg = rad2deg(SlopeAngleRadians(in_slope_));
        double rad2deg2 = rad2deg(SlopeAngleRadians(out_slope_));
        int r5 = C33971.$SwitchMap$com$horcrux$svg$RNSVGMarkerType[rNSVGMarkerType.ordinal()];
        if (r5 == 1) {
            return auto_start_reverse_ ? rad2deg2 + 180.0d : rad2deg2;
        } else if (r5 != 2) {
            if (r5 != 3) {
                return 0.0d;
            }
            return rad2deg;
        } else {
            return BisectingAngle(rad2deg, rad2deg2);
        }
    }

    private static Point subtract(Point point, Point point2) {
        return new Point(point2.f1266x - point.f1266x, point2.f1267y - point.f1267y);
    }

    private static boolean isZero(Point point) {
        return point.f1266x == 0.0d && point.f1267y == 0.0d;
    }

    private static void ComputeQuadTangents(SegmentData segmentData, Point point, Point point2, Point point3) {
        segmentData.start_tangent = subtract(point2, point);
        segmentData.end_tangent = subtract(point3, point2);
        if (isZero(segmentData.start_tangent)) {
            segmentData.start_tangent = segmentData.end_tangent;
        } else if (isZero(segmentData.end_tangent)) {
            segmentData.end_tangent = segmentData.start_tangent;
        }
    }

    private static SegmentData ExtractPathElementFeatures(PathElement pathElement) {
        SegmentData segmentData = new SegmentData();
        Point[] pointArr = pathElement.points;
        int r6 = C33971.$SwitchMap$com$horcrux$svg$ElementType[pathElement.type.ordinal()];
        if (r6 == 1) {
            segmentData.position = pointArr[2];
            segmentData.start_tangent = subtract(pointArr[0], origin_);
            segmentData.end_tangent = subtract(pointArr[2], pointArr[1]);
            if (isZero(segmentData.start_tangent)) {
                ComputeQuadTangents(segmentData, pointArr[0], pointArr[1], pointArr[2]);
            } else if (isZero(segmentData.end_tangent)) {
                ComputeQuadTangents(segmentData, origin_, pointArr[0], pointArr[1]);
            }
        } else if (r6 == 2) {
            segmentData.position = pointArr[1];
            ComputeQuadTangents(segmentData, origin_, pointArr[0], pointArr[1]);
        } else if (r6 == 3 || r6 == 4) {
            segmentData.position = pointArr[0];
            segmentData.start_tangent = subtract(segmentData.position, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        } else if (r6 == 5) {
            segmentData.position = subpath_start_;
            segmentData.start_tangent = subtract(segmentData.position, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        }
        return segmentData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.RNSVGMarkerPosition$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C33971 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$ElementType;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RNSVGMarkerType;

        static {
            int[] r0 = new int[ElementType.values().length];
            $SwitchMap$com$horcrux$svg$ElementType = r0;
            try {
                r0[ElementType.kCGPathElementAddCurveToPoint.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementAddQuadCurveToPoint.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementMoveToPoint.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementAddLineToPoint.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementCloseSubpath.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] r3 = new int[RNSVGMarkerType.values().length];
            $SwitchMap$com$horcrux$svg$RNSVGMarkerType = r3;
            try {
                r3[RNSVGMarkerType.kStartMarker.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType[RNSVGMarkerType.kMidMarker.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType[RNSVGMarkerType.kEndMarker.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static void UpdateFromPathElement(PathElement pathElement) {
        SegmentData ExtractPathElementFeatures = ExtractPathElementFeatures(pathElement);
        out_slope_ = ExtractPathElementFeatures.start_tangent;
        int r1 = element_index_;
        if (r1 > 0) {
            RNSVGMarkerType rNSVGMarkerType = r1 == 1 ? RNSVGMarkerType.kStartMarker : RNSVGMarkerType.kMidMarker;
            positions_.add(new RNSVGMarkerPosition(rNSVGMarkerType, origin_, CurrentAngle(rNSVGMarkerType)));
        }
        in_slope_ = ExtractPathElementFeatures.end_tangent;
        origin_ = ExtractPathElementFeatures.position;
        if (pathElement.type == ElementType.kCGPathElementMoveToPoint) {
            subpath_start_ = pathElement.points[0];
        } else if (pathElement.type == ElementType.kCGPathElementCloseSubpath) {
            subpath_start_ = new Point(0.0d, 0.0d);
        }
        element_index_++;
    }
}
