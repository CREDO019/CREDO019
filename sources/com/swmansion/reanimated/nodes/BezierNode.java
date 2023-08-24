package com.swmansion.reanimated.nodes;

import android.graphics.PointF;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.MapUtils;
import com.swmansion.reanimated.NodesManager;

/* loaded from: classes3.dex */
public class BezierNode extends Node {
    private final int mInputID;
    private final CubicBezierInterpolator mInterpolator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class CubicBezierInterpolator {

        /* renamed from: a */
        protected PointF f1355a;

        /* renamed from: b */
        protected PointF f1356b;

        /* renamed from: c */
        protected PointF f1357c;
        protected PointF end;
        protected PointF start;

        public CubicBezierInterpolator(PointF pointF, PointF pointF2) {
            this.f1355a = new PointF();
            this.f1356b = new PointF();
            this.f1357c = new PointF();
            this.start = pointF;
            this.end = pointF2;
        }

        public CubicBezierInterpolator(float f, float f2, float f3, float f4) {
            this(new PointF(f, f2), new PointF(f3, f4));
        }

        public float getInterpolation(float f) {
            return getBezierCoordinateY(getXForTime(f));
        }

        protected float getBezierCoordinateY(float f) {
            this.f1357c.y = this.start.y * 3.0f;
            this.f1356b.y = ((this.end.y - this.start.y) * 3.0f) - this.f1357c.y;
            this.f1355a.y = (1.0f - this.f1357c.y) - this.f1356b.y;
            return f * (this.f1357c.y + ((this.f1356b.y + (this.f1355a.y * f)) * f));
        }

        protected float getXForTime(float f) {
            float f2 = f;
            for (int r0 = 1; r0 < 14; r0++) {
                float bezierCoordinateX = getBezierCoordinateX(f2) - f;
                if (Math.abs(bezierCoordinateX) < 0.001d) {
                    break;
                }
                f2 -= bezierCoordinateX / getXDerivate(f2);
            }
            return f2;
        }

        private float getXDerivate(float f) {
            return this.f1357c.x + (f * ((this.f1356b.x * 2.0f) + (this.f1355a.x * 3.0f * f)));
        }

        private float getBezierCoordinateX(float f) {
            this.f1357c.x = this.start.x * 3.0f;
            this.f1356b.x = ((this.end.x - this.start.x) * 3.0f) - this.f1357c.x;
            this.f1355a.x = (1.0f - this.f1357c.x) - this.f1356b.x;
            return f * (this.f1357c.x + ((this.f1356b.x + (this.f1355a.x * f)) * f));
        }
    }

    public BezierNode(int r4, ReadableMap readableMap, NodesManager nodesManager) {
        super(r4, readableMap, nodesManager);
        this.mInputID = MapUtils.getInt(readableMap, "input", "Reanimated: Argument passed to bezier node is either of wrong type or is missing.");
        this.mInterpolator = new CubicBezierInterpolator((float) readableMap.getDouble("mX1"), (float) readableMap.getDouble("mY1"), (float) readableMap.getDouble("mX2"), (float) readableMap.getDouble("mY2"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public Double evaluate() {
        return Double.valueOf(this.mInterpolator.getInterpolation(((Double) this.mNodesManager.getNodeValue(this.mInputID)).floatValue()));
    }
}
