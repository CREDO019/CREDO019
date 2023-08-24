package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes.dex */
public class ReactViewBackgroundDrawable extends Drawable {
    private static final int ALL_BITS_SET = -1;
    private static final int ALL_BITS_UNSET = 0;
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    private Spacing mBorderAlpha;
    private float[] mBorderCornerRadii;
    private Spacing mBorderRGB;
    private BorderStyle mBorderStyle;
    private Spacing mBorderWidth;
    private Path mCenterDrawPath;
    private final Context mContext;
    private PointF mInnerBottomLeftCorner;
    private PointF mInnerBottomRightCorner;
    private Path mInnerClipPathForBorderRadius;
    private RectF mInnerClipTempRectForBorderRadius;
    private PointF mInnerTopLeftCorner;
    private PointF mInnerTopRightCorner;
    private int mLayoutDirection;
    private Path mOuterClipPathForBorderRadius;
    private RectF mOuterClipTempRectForBorderRadius;
    private Path mPathForBorder;
    private Path mPathForBorderRadiusOutline;
    private RectF mTempRectForBorderRadiusOutline;
    private RectF mTempRectForCenterDrawPath;
    private final Path mPathForSingleBorder = new Path();
    private boolean mNeedUpdatePathForBorderRadius = false;
    private float mBorderRadius = Float.NaN;
    private final Paint mPaint = new Paint(1);
    private int mColor = 0;
    private int mAlpha = 255;

    /* loaded from: classes.dex */
    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    private static int colorFromAlphaAndRGBComponents(float f, float f2) {
        return ((((int) f) << 24) & (-16777216)) | (((int) f2) & ViewCompat.MEASURED_SIZE_MASK);
    }

    private static int fastBorderCompatibleColorOrZero(int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        int r0 = (r6 > 0 ? r10 : -1) & (r3 > 0 ? r7 : -1) & (r4 > 0 ? r8 : -1) & (r5 > 0 ? r9 : -1);
        if (r3 <= 0) {
            r7 = 0;
        }
        if (r4 <= 0) {
            r8 = 0;
        }
        int r32 = r7 | r8;
        if (r5 <= 0) {
            r9 = 0;
        }
        int r33 = r32 | r9;
        if (r6 <= 0) {
            r10 = 0;
        }
        if (r0 == (r33 | r10)) {
            return r0;
        }
        return 0;
    }

    public boolean onResolvedLayoutDirectionChanged(int r1) {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.views.view.ReactViewBackgroundDrawable$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C17291 {

        /* renamed from: $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle */
        static final /* synthetic */ int[] f175xe6ea8768;

        static {
            int[] r0 = new int[BorderStyle.values().length];
            f175xe6ea8768 = r0;
            try {
                r0[BorderStyle.SOLID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f175xe6ea8768[BorderStyle.DASHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f175xe6ea8768[BorderStyle.DOTTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        public static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
            int r7 = C17291.f175xe6ea8768[borderStyle.ordinal()];
            if (r7 == 2) {
                float f2 = f * 3.0f;
                return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
            } else if (r7 != 3) {
                return null;
            } else {
                return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
            }
        }
    }

    public ReactViewBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        updatePathEffect();
        if (!hasRoundedBorders()) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    public boolean hasRoundedBorders() {
        if (YogaConstants.isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) {
            float[] fArr = this.mBorderCornerRadii;
            if (fArr != null) {
                for (float f : fArr) {
                    if (!YogaConstants.isUndefined(f) && f > 0.0f) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (r2 != this.mAlpha) {
            this.mAlpha = r2;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if ((!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) || this.mBorderCornerRadii != null) {
            updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
            return;
        }
        outline.setRect(getBounds());
    }

    public void setBorderWidth(int r2, float f) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (FloatUtil.floatsEqual(this.mBorderWidth.getRaw(r2), f)) {
            return;
        }
        this.mBorderWidth.set(r2, f);
        if (r2 == 0 || r2 == 1 || r2 == 2 || r2 == 3 || r2 == 4 || r2 == 5 || r2 == 8) {
            this.mNeedUpdatePathForBorderRadius = true;
        }
        invalidateSelf();
    }

    public void setBorderColor(int r1, float f, float f2) {
        setBorderRGB(r1, f);
        setBorderAlpha(r1, f2);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    private void setBorderRGB(int r3, float f) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderRGB.getRaw(r3), f)) {
            return;
        }
        this.mBorderRGB.set(r3, f);
        invalidateSelf();
    }

    private void setBorderAlpha(int r3, float f) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(r3), f)) {
            return;
        }
        this.mBorderAlpha.set(r3, f);
        invalidateSelf();
    }

    public void setBorderStyle(String str) {
        BorderStyle valueOf = str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != valueOf) {
            this.mBorderStyle = valueOf;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float f) {
        if (FloatUtil.floatsEqual(this.mBorderRadius, f)) {
            return;
        }
        this.mBorderRadius = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public void setRadius(float f, int r4) {
        if (this.mBorderCornerRadii == null) {
            float[] fArr = new float[8];
            this.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (FloatUtil.floatsEqual(this.mBorderCornerRadii[r4], f)) {
            return;
        }
        this.mBorderCornerRadii[r4] = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public float getFullBorderRadius() {
        if (YogaConstants.isUndefined(this.mBorderRadius)) {
            return 0.0f;
        }
        return this.mBorderRadius;
    }

    public float getBorderRadius(BorderRadiusLocation borderRadiusLocation) {
        return getBorderRadiusOrDefaultTo(Float.NaN, borderRadiusLocation);
    }

    public float getBorderRadiusOrDefaultTo(float f, BorderRadiusLocation borderRadiusLocation) {
        float[] fArr = this.mBorderCornerRadii;
        if (fArr == null) {
            return f;
        }
        float f2 = fArr[borderRadiusLocation.ordinal()];
        return YogaConstants.isUndefined(f2) ? f : f2;
    }

    public void setColor(int r1) {
        this.mColor = r1;
        invalidateSelf();
    }

    public int getResolvedLayoutDirection() {
        return this.mLayoutDirection;
    }

    public boolean setResolvedLayoutDirection(int r2) {
        if (this.mLayoutDirection != r2) {
            this.mLayoutDirection = r2;
            return onResolvedLayoutDirectionChanged(r2);
        }
        return false;
    }

    public int getColor() {
        return this.mColor;
    }

    private void drawRoundedBackgroundWithBorders(Canvas canvas) {
        int r2;
        int r17;
        float f;
        float f2;
        float f3;
        float f4;
        updatePath();
        canvas.save();
        int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(multiplyColorAlpha) != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(this.mInnerClipPathForBorderRadius, this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int borderColor = getBorderColor(0);
        int borderColor2 = getBorderColor(1);
        int borderColor3 = getBorderColor(2);
        int borderColor4 = getBorderColor(3);
        if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.right > 0.0f) {
            float fullBorderWidth = getFullBorderWidth();
            int borderColor5 = getBorderColor(8);
            if (directionAwareBorderInsets.top != fullBorderWidth || directionAwareBorderInsets.bottom != fullBorderWidth || directionAwareBorderInsets.left != fullBorderWidth || directionAwareBorderInsets.right != fullBorderWidth || borderColor != borderColor5 || borderColor2 != borderColor5 || borderColor3 != borderColor5 || borderColor4 != borderColor5) {
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas.clipPath(this.mOuterClipPathForBorderRadius, Region.Op.INTERSECT);
                canvas.clipPath(this.mInnerClipPathForBorderRadius, Region.Op.DIFFERENCE);
                boolean z = getResolvedLayoutDirection() == 1;
                int borderColor6 = getBorderColor(4);
                int borderColor7 = getBorderColor(5);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                    if (isBorderColorDefined(4)) {
                        borderColor = borderColor6;
                    }
                    if (isBorderColorDefined(5)) {
                        borderColor3 = borderColor7;
                    }
                    r2 = z ? borderColor3 : borderColor;
                    if (!z) {
                        borderColor = borderColor3;
                    }
                    r17 = borderColor;
                } else {
                    int r7 = z ? borderColor7 : borderColor6;
                    if (!z) {
                        borderColor6 = borderColor7;
                    }
                    boolean isBorderColorDefined = isBorderColorDefined(4);
                    boolean isBorderColorDefined2 = isBorderColorDefined(5);
                    boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                    if (!z) {
                        isBorderColorDefined = isBorderColorDefined2;
                    }
                    if (z2) {
                        borderColor = r7;
                    }
                    if (isBorderColorDefined) {
                        r2 = borderColor;
                        r17 = borderColor6;
                    } else {
                        r2 = borderColor;
                        r17 = borderColor3;
                    }
                }
                float f5 = this.mOuterClipTempRectForBorderRadius.left;
                float f6 = this.mOuterClipTempRectForBorderRadius.right;
                float f7 = this.mOuterClipTempRectForBorderRadius.top;
                float f8 = this.mOuterClipTempRectForBorderRadius.bottom;
                if (directionAwareBorderInsets.left > 0.0f) {
                    f = f8;
                    f2 = f7;
                    f3 = f6;
                    f4 = f5;
                    drawQuadrilateral(canvas, r2, f5, f7, this.mInnerTopLeftCorner.x, this.mInnerTopLeftCorner.y, this.mInnerBottomLeftCorner.x, this.mInnerBottomLeftCorner.y, f5, f);
                } else {
                    f = f8;
                    f2 = f7;
                    f3 = f6;
                    f4 = f5;
                }
                if (directionAwareBorderInsets.top > 0.0f) {
                    drawQuadrilateral(canvas, borderColor2, f4, f2, this.mInnerTopLeftCorner.x, this.mInnerTopLeftCorner.y, this.mInnerTopRightCorner.x, this.mInnerTopRightCorner.y, f3, f2);
                }
                if (directionAwareBorderInsets.right > 0.0f) {
                    drawQuadrilateral(canvas, r17, f3, f2, this.mInnerTopRightCorner.x, this.mInnerTopRightCorner.y, this.mInnerBottomRightCorner.x, this.mInnerBottomRightCorner.y, f3, f);
                }
                if (directionAwareBorderInsets.bottom > 0.0f) {
                    drawQuadrilateral(canvas, borderColor4, f4, f, this.mInnerBottomLeftCorner.x, this.mInnerBottomLeftCorner.y, this.mInnerBottomRightCorner.x, this.mInnerBottomRightCorner.y, f3, f);
                }
            } else if (fullBorderWidth > 0.0f) {
                this.mPaint.setColor(ColorUtil.multiplyColorAlpha(borderColor5, this.mAlpha));
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(fullBorderWidth);
                canvas.drawPath(this.mCenterDrawPath, this.mPaint);
            }
        }
        canvas.restore();
    }

    private void updatePath() {
        float f;
        float f2;
        float max;
        float max2;
        float max3;
        float max4;
        float max5;
        float max6;
        float max7;
        float max8;
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(8);
            if (Color.alpha(borderColor) != 0 && Color.alpha(borderColor2) != 0 && Color.alpha(borderColor3) != 0 && Color.alpha(borderColor4) != 0 && Color.alpha(borderColor5) != 0) {
                this.mInnerClipTempRectForBorderRadius.top += directionAwareBorderInsets.top;
                this.mInnerClipTempRectForBorderRadius.bottom -= directionAwareBorderInsets.bottom;
                this.mInnerClipTempRectForBorderRadius.left += directionAwareBorderInsets.left;
                this.mInnerClipTempRectForBorderRadius.right -= directionAwareBorderInsets.right;
            }
            this.mTempRectForCenterDrawPath.top += directionAwareBorderInsets.top * 0.5f;
            this.mTempRectForCenterDrawPath.bottom -= directionAwareBorderInsets.bottom * 0.5f;
            this.mTempRectForCenterDrawPath.left += directionAwareBorderInsets.left * 0.5f;
            this.mTempRectForCenterDrawPath.right -= directionAwareBorderInsets.right * 0.5f;
            float fullBorderRadius = getFullBorderRadius();
            float borderRadiusOrDefaultTo = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_LEFT);
            float borderRadiusOrDefaultTo2 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_RIGHT);
            float borderRadiusOrDefaultTo3 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_LEFT);
            float borderRadiusOrDefaultTo4 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_RIGHT);
            boolean z = getResolvedLayoutDirection() == 1;
            float borderRadius = getBorderRadius(BorderRadiusLocation.TOP_START);
            float borderRadius2 = getBorderRadius(BorderRadiusLocation.TOP_END);
            float borderRadius3 = getBorderRadius(BorderRadiusLocation.BOTTOM_START);
            float borderRadius4 = getBorderRadius(BorderRadiusLocation.BOTTOM_END);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo = borderRadius;
                }
                if (!YogaConstants.isUndefined(borderRadius2)) {
                    borderRadiusOrDefaultTo2 = borderRadius2;
                }
                if (!YogaConstants.isUndefined(borderRadius3)) {
                    borderRadiusOrDefaultTo3 = borderRadius3;
                }
                if (!YogaConstants.isUndefined(borderRadius4)) {
                    borderRadiusOrDefaultTo4 = borderRadius4;
                }
                f = z ? borderRadiusOrDefaultTo2 : borderRadiusOrDefaultTo;
                if (!z) {
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                }
                f2 = z ? borderRadiusOrDefaultTo4 : borderRadiusOrDefaultTo3;
                if (z) {
                    borderRadiusOrDefaultTo4 = borderRadiusOrDefaultTo3;
                }
            } else {
                float f3 = z ? borderRadius2 : borderRadius;
                if (!z) {
                    borderRadius = borderRadius2;
                }
                float f4 = z ? borderRadius4 : borderRadius3;
                if (!z) {
                    borderRadius3 = borderRadius4;
                }
                if (!YogaConstants.isUndefined(f3)) {
                    borderRadiusOrDefaultTo = f3;
                }
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo2 = borderRadius;
                }
                if (!YogaConstants.isUndefined(f4)) {
                    borderRadiusOrDefaultTo3 = f4;
                }
                if (YogaConstants.isUndefined(borderRadius3)) {
                    f = borderRadiusOrDefaultTo;
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                    f2 = borderRadiusOrDefaultTo3;
                } else {
                    f = borderRadiusOrDefaultTo;
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                    f2 = borderRadiusOrDefaultTo3;
                    borderRadiusOrDefaultTo4 = borderRadius3;
                }
            }
            float f5 = f2;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{Math.max(f - directionAwareBorderInsets.left, 0.0f), Math.max(f - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.bottom, 0.0f), Math.max(f2 - directionAwareBorderInsets.left, 0.0f), Math.max(f2 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{f, f, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo4, borderRadiusOrDefaultTo4, f5, f5}, Path.Direction.CW);
            Spacing spacing = this.mBorderWidth;
            float f6 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            float f7 = f + f6;
            float f8 = borderRadiusOrDefaultTo + f6;
            float f9 = borderRadiusOrDefaultTo4 + f6;
            float f10 = f5 + f6;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f7, f7, f8, f8, f9, f9, f10, f10}, Path.Direction.CW);
            Path path = this.mCenterDrawPath;
            RectF rectF = this.mTempRectForCenterDrawPath;
            float[] fArr = new float[8];
            fArr[0] = Math.max(f - (directionAwareBorderInsets.left * 0.5f), directionAwareBorderInsets.left > 0.0f ? f / directionAwareBorderInsets.left : 0.0f);
            fArr[1] = Math.max(f - (directionAwareBorderInsets.top * 0.5f), directionAwareBorderInsets.top > 0.0f ? f / directionAwareBorderInsets.top : 0.0f);
            fArr[2] = Math.max(borderRadiusOrDefaultTo - (directionAwareBorderInsets.right * 0.5f), directionAwareBorderInsets.right > 0.0f ? borderRadiusOrDefaultTo / directionAwareBorderInsets.right : 0.0f);
            fArr[3] = Math.max(borderRadiusOrDefaultTo - (directionAwareBorderInsets.top * 0.5f), directionAwareBorderInsets.top > 0.0f ? borderRadiusOrDefaultTo / directionAwareBorderInsets.top : 0.0f);
            fArr[4] = Math.max(borderRadiusOrDefaultTo4 - (directionAwareBorderInsets.right * 0.5f), directionAwareBorderInsets.right > 0.0f ? borderRadiusOrDefaultTo4 / directionAwareBorderInsets.right : 0.0f);
            fArr[5] = Math.max(borderRadiusOrDefaultTo4 - (directionAwareBorderInsets.bottom * 0.5f), directionAwareBorderInsets.bottom > 0.0f ? borderRadiusOrDefaultTo4 / directionAwareBorderInsets.bottom : 0.0f);
            fArr[6] = Math.max(f5 - (directionAwareBorderInsets.left * 0.5f), directionAwareBorderInsets.left > 0.0f ? f5 / directionAwareBorderInsets.left : 0.0f);
            fArr[7] = Math.max(f5 - (directionAwareBorderInsets.bottom * 0.5f), directionAwareBorderInsets.bottom > 0.0f ? f5 / directionAwareBorderInsets.bottom : 0.0f);
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            this.mInnerTopLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerTopLeftCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.left + (max * 2.0f), this.mInnerClipTempRectForBorderRadius.top + (max2 * 2.0f), this.mOuterClipTempRectForBorderRadius.left, this.mOuterClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopLeftCorner);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            this.mInnerBottomLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerBottomLeftCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.bottom - (max8 * 2.0f), this.mInnerClipTempRectForBorderRadius.left + (max7 * 2.0f), this.mInnerClipTempRectForBorderRadius.bottom, this.mOuterClipTempRectForBorderRadius.left, this.mOuterClipTempRectForBorderRadius.bottom, this.mInnerClipTempRectForBorderRadius.left, this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomLeftCorner);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            this.mInnerTopRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerTopRightCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.right - (max3 * 2.0f), this.mInnerClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.top + (max4 * 2.0f), this.mOuterClipTempRectForBorderRadius.right, this.mOuterClipTempRectForBorderRadius.top, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopRightCorner);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            this.mInnerBottomRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerBottomRightCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine(this.mInnerClipTempRectForBorderRadius.right - (max5 * 2.0f), this.mInnerClipTempRectForBorderRadius.bottom - (max6 * 2.0f), this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.bottom, this.mOuterClipTempRectForBorderRadius.right, this.mOuterClipTempRectForBorderRadius.bottom, this.mInnerClipTempRectForBorderRadius.right, this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomRightCorner);
        }
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = d15 + (d16 * d13 * d13);
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = sqrt + d9;
        double d22 = (d13 * sqrt) + d14 + d10;
        if (Double.isNaN(d21) || Double.isNaN(d22)) {
            return;
        }
        pointF.x = (float) d21;
        pointF.y = (float) d22;
    }

    public float getBorderWidthOrDefaultTo(float f, int r3) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return f;
        }
        float raw = spacing.getRaw(r3);
        return YogaConstants.isUndefined(raw) ? f : raw;
    }

    private void updatePathEffect() {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, getFullBorderWidth()) : null);
    }

    private void updatePathEffect(int r2) {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, r2) : null);
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || YogaConstants.isUndefined(spacing.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    private void drawRectangularBackgroundWithBorders(Canvas canvas) {
        int r19;
        int r22;
        int r12;
        int r9;
        int r8;
        int r92;
        int r82;
        this.mPaint.setStyle(Paint.Style.FILL);
        int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(multiplyColorAlpha) != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            canvas.drawRect(getBounds(), this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int round = Math.round(directionAwareBorderInsets.left);
        int round2 = Math.round(directionAwareBorderInsets.top);
        int round3 = Math.round(directionAwareBorderInsets.right);
        int round4 = Math.round(directionAwareBorderInsets.bottom);
        if (round > 0 || round3 > 0 || round2 > 0 || round4 > 0) {
            Rect bounds = getBounds();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            boolean z = getResolvedLayoutDirection() == 1;
            int borderColor5 = getBorderColor(4);
            int borderColor6 = getBorderColor(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (isBorderColorDefined(4)) {
                    borderColor = borderColor5;
                }
                if (isBorderColorDefined(5)) {
                    borderColor3 = borderColor6;
                }
                int r5 = z ? borderColor3 : borderColor;
                if (!z) {
                    borderColor = borderColor3;
                }
                r22 = borderColor;
                r19 = r5;
            } else {
                int r83 = z ? borderColor6 : borderColor5;
                if (!z) {
                    borderColor5 = borderColor6;
                }
                boolean isBorderColorDefined = isBorderColorDefined(4);
                boolean isBorderColorDefined2 = isBorderColorDefined(5);
                boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                if (!z) {
                    isBorderColorDefined = isBorderColorDefined2;
                }
                if (z2) {
                    borderColor = r83;
                }
                r19 = borderColor;
                r22 = isBorderColorDefined ? borderColor5 : borderColor3;
            }
            int r93 = bounds.left;
            int r84 = bounds.top;
            int fastBorderCompatibleColorOrZero = fastBorderCompatibleColorOrZero(round, round2, round3, round4, r19, borderColor2, r22, borderColor4);
            if (fastBorderCompatibleColorOrZero != 0) {
                if (Color.alpha(fastBorderCompatibleColorOrZero) != 0) {
                    int r3 = bounds.right;
                    int r1 = bounds.bottom;
                    this.mPaint.setColor(fastBorderCompatibleColorOrZero);
                    this.mPaint.setStyle(Paint.Style.STROKE);
                    if (round > 0) {
                        this.mPathForSingleBorder.reset();
                        int round5 = Math.round(directionAwareBorderInsets.left);
                        updatePathEffect(round5);
                        this.mPaint.setStrokeWidth(round5);
                        float f = r93 + (round5 / 2);
                        this.mPathForSingleBorder.moveTo(f, r84);
                        this.mPathForSingleBorder.lineTo(f, r1);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (round2 > 0) {
                        this.mPathForSingleBorder.reset();
                        int round6 = Math.round(directionAwareBorderInsets.top);
                        updatePathEffect(round6);
                        this.mPaint.setStrokeWidth(round6);
                        float f2 = r84 + (round6 / 2);
                        this.mPathForSingleBorder.moveTo(r93, f2);
                        this.mPathForSingleBorder.lineTo(r3, f2);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (round3 > 0) {
                        this.mPathForSingleBorder.reset();
                        int round7 = Math.round(directionAwareBorderInsets.right);
                        updatePathEffect(round7);
                        this.mPaint.setStrokeWidth(round7);
                        float f3 = r3 - (round7 / 2);
                        this.mPathForSingleBorder.moveTo(f3, r84);
                        this.mPathForSingleBorder.lineTo(f3, r1);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (round4 > 0) {
                        this.mPathForSingleBorder.reset();
                        int round8 = Math.round(directionAwareBorderInsets.bottom);
                        updatePathEffect(round8);
                        this.mPaint.setStrokeWidth(round8);
                        float f4 = r1 - (round8 / 2);
                        this.mPathForSingleBorder.moveTo(r93, f4);
                        this.mPathForSingleBorder.lineTo(r3, f4);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                        return;
                    }
                    return;
                }
                return;
            }
            this.mPaint.setAntiAlias(false);
            int width = bounds.width();
            int height = bounds.height();
            if (round > 0) {
                float f5 = r93;
                float f6 = r93 + round;
                r12 = r84;
                drawQuadrilateral(canvas, r19, f5, r84, f6, r84 + round2, f6, r82 - round4, f5, r84 + height);
            } else {
                r12 = r84;
            }
            if (round2 > 0) {
                float f7 = r12;
                float f8 = r12 + round2;
                drawQuadrilateral(canvas, borderColor2, r93, f7, r93 + round, f8, r92 - round3, f8, r93 + width, f7);
            }
            if (round3 > 0) {
                int r94 = r93 + width;
                float f9 = r94;
                float f10 = r94 - round3;
                drawQuadrilateral(canvas, r22, f9, r12, f9, r12 + height, f10, r8 - round4, f10, r12 + round2);
            }
            if (round4 > 0) {
                int r85 = r12 + height;
                float f11 = r85;
                float f12 = r85 - round4;
                drawQuadrilateral(canvas, borderColor4, r93, f11, r93 + width, f11, r9 - round3, f12, r93 + round, f12);
            }
            this.mPaint.setAntiAlias(true);
        }
    }

    private void drawQuadrilateral(Canvas canvas, int r3, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (r3 == 0) {
            return;
        }
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPaint.setColor(r3);
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(f, f2);
        this.mPathForBorder.lineTo(f3, f4);
        this.mPathForBorder.lineTo(f5, f6);
        this.mPathForBorder.lineTo(f7, f8);
        this.mPathForBorder.lineTo(f, f2);
        canvas.drawPath(this.mPathForBorder, this.mPaint);
    }

    private int getBorderWidth(int r2) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return 0;
        }
        float f = spacing.get(r2);
        if (YogaConstants.isUndefined(f)) {
            return -1;
        }
        return Math.round(f);
    }

    private boolean isBorderColorDefined(int r4) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(r4) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        return (YogaConstants.isUndefined(f) || YogaConstants.isUndefined(spacing2 != null ? spacing2.get(r4) : Float.NaN)) ? false : true;
    }

    public int getBorderColor(int r3) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(r3) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return colorFromAlphaAndRGBComponents(spacing2 != null ? spacing2.get(r3) : 255.0f, f);
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (this.mBorderWidth != null) {
            boolean z = getResolvedLayoutDirection() == 1;
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo4 = raw;
                }
                if (!YogaConstants.isUndefined(raw2)) {
                    borderWidthOrDefaultTo5 = raw2;
                }
                float f = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
                borderWidthOrDefaultTo4 = f;
            } else {
                float f2 = z ? raw2 : raw;
                if (!z) {
                    raw = raw2;
                }
                if (!YogaConstants.isUndefined(f2)) {
                    borderWidthOrDefaultTo4 = f2;
                }
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo5 = raw;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }
}
