package expo.modules.p019av.video.scalablevideoview;

import android.graphics.Matrix;
import android.util.Size;

/* renamed from: expo.modules.av.video.scalablevideoview.ScaleManager */
/* loaded from: classes4.dex */
public class ScaleManager {
    private Size mVideoSize;
    private Size mViewSize;

    public ScaleManager(Size size, Size size2) {
        this.mViewSize = size;
        this.mVideoSize = size2;
    }

    public Matrix getScaleMatrix(ScalableType scalableType) {
        switch (C43251.$SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[scalableType.ordinal()]) {
            case 1:
                return getNoScale();
            case 2:
                return fitXY();
            case 3:
                return fitCenter();
            case 4:
                return fitStart();
            case 5:
                return fitEnd();
            case 6:
                return getOriginalScale(PivotPoint.LEFT_TOP);
            case 7:
                return getOriginalScale(PivotPoint.LEFT_CENTER);
            case 8:
                return getOriginalScale(PivotPoint.LEFT_BOTTOM);
            case 9:
                return getOriginalScale(PivotPoint.CENTER_TOP);
            case 10:
                return getOriginalScale(PivotPoint.CENTER);
            case 11:
                return getOriginalScale(PivotPoint.CENTER_BOTTOM);
            case 12:
                return getOriginalScale(PivotPoint.RIGHT_TOP);
            case 13:
                return getOriginalScale(PivotPoint.RIGHT_CENTER);
            case 14:
                return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
            case 15:
                return getCropScale(PivotPoint.LEFT_TOP);
            case 16:
                return getCropScale(PivotPoint.LEFT_CENTER);
            case 17:
                return getCropScale(PivotPoint.LEFT_BOTTOM);
            case 18:
                return getCropScale(PivotPoint.CENTER_TOP);
            case 19:
                return getCropScale(PivotPoint.CENTER);
            case 20:
                return getCropScale(PivotPoint.CENTER_BOTTOM);
            case 21:
                return getCropScale(PivotPoint.RIGHT_TOP);
            case 22:
                return getCropScale(PivotPoint.RIGHT_CENTER);
            case 23:
                return getCropScale(PivotPoint.RIGHT_BOTTOM);
            case 24:
                return startInside();
            case 25:
                return centerInside();
            case 26:
                return endInside();
            default:
                return null;
        }
    }

    private Matrix getMatrix(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2, f3, f4);
        return matrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: expo.modules.av.video.scalablevideoview.ScaleManager$1 */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class C43251 {
        static final /* synthetic */ int[] $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint;
        static final /* synthetic */ int[] $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType;

        static {
            int[] r0 = new int[PivotPoint.values().length];
            $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint = r0;
            try {
                r0[PivotPoint.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.LEFT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.LEFT_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.CENTER_TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.CENTER_BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.RIGHT_TOP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.RIGHT_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[PivotPoint.RIGHT_BOTTOM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] r9 = new int[ScalableType.values().length];
            $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType = r9;
            try {
                r9[ScalableType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.FIT_XY.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_BOTTOM.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_TOP.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_CENTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_BOTTOM.ordinal()] = 14;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_TOP_CROP.ordinal()] = 15;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_CENTER_CROP.ordinal()] = 16;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.LEFT_BOTTOM_CROP.ordinal()] = 17;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_TOP_CROP.ordinal()] = 18;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_CROP.ordinal()] = 19;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_BOTTOM_CROP.ordinal()] = 20;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_TOP_CROP.ordinal()] = 21;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_CENTER_CROP.ordinal()] = 22;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.RIGHT_BOTTOM_CROP.ordinal()] = 23;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.START_INSIDE.ordinal()] = 24;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.CENTER_INSIDE.ordinal()] = 25;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$expo$modules$av$video$scalablevideoview$ScalableType[ScalableType.END_INSIDE.ordinal()] = 26;
            } catch (NoSuchFieldError unused35) {
            }
        }
    }

    private Matrix getMatrix(float f, float f2, PivotPoint pivotPoint) {
        switch (C43251.$SwitchMap$expo$modules$av$video$scalablevideoview$PivotPoint[pivotPoint.ordinal()]) {
            case 1:
                return getMatrix(f, f2, 0.0f, 0.0f);
            case 2:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight() / 2.0f);
            case 3:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight());
            case 4:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, 0.0f);
            case 5:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight() / 2.0f);
            case 6:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight());
            case 7:
                return getMatrix(f, f2, this.mViewSize.getWidth(), 0.0f);
            case 8:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight() / 2.0f);
            case 9:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight());
            default:
                throw new IllegalArgumentException("Illegal PivotPoint");
        }
    }

    private Matrix getNoScale() {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), PivotPoint.LEFT_TOP);
    }

    private Matrix getFitScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float min = Math.min(width, height);
        return getMatrix(min / width, min / height, pivotPoint);
    }

    private Matrix fitXY() {
        return getMatrix(1.0f, 1.0f, PivotPoint.LEFT_TOP);
    }

    private Matrix fitStart() {
        return getFitScale(PivotPoint.LEFT_TOP);
    }

    private Matrix fitCenter() {
        return getFitScale(PivotPoint.CENTER);
    }

    private Matrix fitEnd() {
        return getFitScale(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix getOriginalScale(PivotPoint pivotPoint) {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), pivotPoint);
    }

    private Matrix getCropScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float max = Math.max(width, height);
        return getMatrix(max / width, max / height, pivotPoint);
    }

    private Matrix startInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.LEFT_TOP);
        }
        return fitStart();
    }

    private Matrix centerInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.CENTER);
        }
        return fitCenter();
    }

    private Matrix endInside() {
        if (this.mVideoSize.getHeight() <= this.mViewSize.getWidth() && this.mVideoSize.getHeight() <= this.mViewSize.getHeight()) {
            return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
        }
        return fitEnd();
    }
}
