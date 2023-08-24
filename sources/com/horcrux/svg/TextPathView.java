package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.horcrux.svg.TextProperties;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class TextPathView extends TextView {
    private String mHref;
    private TextProperties.TextPathMethod mMethod;
    private TextProperties.TextPathMidLine mMidLine;
    private TextProperties.TextPathSide mSide;
    private TextProperties.TextPathSpacing mSpacing;
    @Nullable
    private SVGLength mStartOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public void popGlyphContext() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView
    public void pushGlyphContext() {
    }

    public TextPathView(ReactContext reactContext) {
        super(reactContext);
        this.mMethod = TextProperties.TextPathMethod.align;
        this.mSpacing = TextProperties.TextPathSpacing.exact;
    }

    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    public void setStartOffset(Dynamic dynamic) {
        this.mStartOffset = SVGLength.from(dynamic);
        invalidate();
    }

    public void setStartOffset(String str) {
        this.mStartOffset = SVGLength.from(str);
        invalidate();
    }

    public void setStartOffset(Double d) {
        this.mStartOffset = SVGLength.from(d);
        invalidate();
    }

    @Override // com.horcrux.svg.TextView
    public void setMethod(@Nullable String str) {
        this.mMethod = TextProperties.TextPathMethod.valueOf(str);
        invalidate();
    }

    public void setSpacing(@Nullable String str) {
        this.mSpacing = TextProperties.TextPathSpacing.valueOf(str);
        invalidate();
    }

    public void setSide(@Nullable String str) {
        this.mSide = TextProperties.TextPathSide.valueOf(str);
        invalidate();
    }

    public void setSharp(@Nullable String str) {
        this.mMidLine = TextProperties.TextPathMidLine.valueOf(str);
        invalidate();
    }

    TextProperties.TextPathMethod getMethod() {
        return this.mMethod;
    }

    TextProperties.TextPathSpacing getSpacing() {
        return this.mSpacing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextProperties.TextPathSide getSide() {
        return this.mSide;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextProperties.TextPathMidLine getMidLine() {
        return this.mMidLine;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SVGLength getStartOffset() {
        return this.mStartOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        drawGroup(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path getTextPath(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate instanceof RenderableView) {
            return ((RenderableView) definedTemplate).getPath(canvas, paint);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        return getGroupPath(canvas, paint);
    }
}
