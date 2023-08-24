package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CircleView extends RenderableView {
    private SVGLength mCx;
    private SVGLength mCy;

    /* renamed from: mR */
    private SVGLength f1244mR;

    public CircleView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setCx(Dynamic dynamic) {
        this.mCx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setCx(String str) {
        this.mCx = SVGLength.from(str);
        invalidate();
    }

    public void setCx(Double d) {
        this.mCx = SVGLength.from(d);
        invalidate();
    }

    public void setCy(Dynamic dynamic) {
        this.mCy = SVGLength.from(dynamic);
        invalidate();
    }

    public void setCy(String str) {
        this.mCy = SVGLength.from(str);
        invalidate();
    }

    public void setCy(Double d) {
        this.mCy = SVGLength.from(d);
        invalidate();
    }

    public void setR(Dynamic dynamic) {
        this.f1244mR = SVGLength.from(dynamic);
        invalidate();
    }

    public void setR(String str) {
        this.f1244mR = SVGLength.from(str);
        invalidate();
    }

    public void setR(Double d) {
        this.f1244mR = SVGLength.from(d);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnOther = relativeOnOther(this.f1244mR);
        path.addCircle((float) relativeOnWidth, (float) relativeOnHeight, (float) relativeOnOther, Path.Direction.CW);
        this.elements = new ArrayList<>();
        double d = relativeOnHeight - relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, d)}));
        double d2 = relativeOnWidth + relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d), new Point(d2, relativeOnHeight)}));
        double d3 = relativeOnHeight + relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d2, relativeOnHeight), new Point(relativeOnWidth, d3)}));
        double d4 = relativeOnWidth - relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d3), new Point(d4, relativeOnHeight)}));
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d4, relativeOnHeight), new Point(relativeOnWidth, d)}));
        return path;
    }
}