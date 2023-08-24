package com.horcrux.svg;

import android.graphics.Matrix;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.TransformHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.horcrux.svg.VirtualView;
import java.lang.reflect.Array;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* compiled from: RenderableViewManager.java */
/* loaded from: classes3.dex */
class VirtualViewManager<V extends VirtualView> extends ViewGroupManager<VirtualView> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final double EPSILON = 1.0E-5d;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    protected final String mClassName;
    protected ViewManagerDelegate<V> mDelegate;
    protected final SVGClass svgClass;
    private static final MatrixDecompositionContext sMatrixDecompositionContext = new MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];
    private static final SparseArray<RenderableView> mTagToRenderableView = new SparseArray<>();
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: RenderableViewManager.java */
    /* loaded from: classes3.dex */
    public enum SVGClass {
        RNSVGGroup,
        RNSVGPath,
        RNSVGText,
        RNSVGTSpan,
        RNSVGTextPath,
        RNSVGImage,
        RNSVGCircle,
        RNSVGEllipse,
        RNSVGLine,
        RNSVGRect,
        RNSVGClipPath,
        RNSVGDefs,
        RNSVGUse,
        RNSVGSymbol,
        RNSVGLinearGradient,
        RNSVGRadialGradient,
        RNSVGPattern,
        RNSVGMask,
        RNSVGMarker,
        RNSVGForeignObject
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VirtualViewManager(SVGClass sVGClass) {
        this.svgClass = sVGClass;
        this.mClassName = sVGClass.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate getDelegate() {
        return this.mDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RenderableViewManager.java */
    /* loaded from: classes3.dex */
    public static class RenderableShadowNode extends LayoutShadowNode {
        @ReactPropGroup(names = {ViewProps.ALIGN_SELF, ViewProps.ALIGN_ITEMS, ViewProps.COLLAPSABLE, ViewProps.FLEX, ViewProps.FLEX_BASIS, ViewProps.FLEX_DIRECTION, ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.OVERFLOW, ViewProps.ALIGN_CONTENT, "display", ViewProps.POSITION, "right", ViewProps.TOP, ViewProps.BOTTOM, "left", "start", "end", "width", "height", ViewProps.MIN_WIDTH, ViewProps.MAX_WIDTH, ViewProps.MIN_HEIGHT, ViewProps.MAX_HEIGHT, ViewProps.MARGIN, ViewProps.MARGIN_VERTICAL, ViewProps.MARGIN_HORIZONTAL, ViewProps.MARGIN_LEFT, ViewProps.MARGIN_RIGHT, ViewProps.MARGIN_TOP, ViewProps.MARGIN_BOTTOM, ViewProps.MARGIN_START, ViewProps.MARGIN_END, ViewProps.PADDING, ViewProps.PADDING_VERTICAL, ViewProps.PADDING_HORIZONTAL, ViewProps.PADDING_LEFT, ViewProps.PADDING_RIGHT, ViewProps.PADDING_TOP, ViewProps.PADDING_BOTTOM, ViewProps.PADDING_START, ViewProps.PADDING_END, ViewProps.BORDER_WIDTH, ViewProps.BORDER_START_WIDTH, ViewProps.BORDER_END_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH})
        public void ignoreLayoutProps(int r1, Dynamic dynamic) {
        }

        RenderableShadowNode() {
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new RenderableShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return RenderableShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RenderableViewManager.java */
    /* loaded from: classes3.dex */
    public static class MatrixDecompositionContext extends MatrixMathHelper.MatrixDecompositionContext {
        final double[] perspective = new double[4];
        final double[] scale = new double[3];
        final double[] skew = new double[3];
        final double[] translation = new double[3];
        final double[] rotationDegrees = new double[3];

        MatrixDecompositionContext() {
        }
    }

    private static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < 1.0E-5d;
    }

    private static void decomposeMatrix() {
        MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        double[] dArr = matrixDecompositionContext.perspective;
        double[] dArr2 = matrixDecompositionContext.scale;
        double[] dArr3 = matrixDecompositionContext.skew;
        double[] dArr4 = matrixDecompositionContext.translation;
        double[] dArr5 = matrixDecompositionContext.rotationDegrees;
        if (isZero(sTransformDecompositionArray[15])) {
            return;
        }
        double[][] dArr6 = (double[][]) Array.newInstance(double.class, 4, 4);
        double[] dArr7 = new double[16];
        for (int r11 = 0; r11 < 4; r11++) {
            for (int r12 = 0; r12 < 4; r12++) {
                double[] dArr8 = sTransformDecompositionArray;
                int r18 = (r11 * 4) + r12;
                double d = dArr8[r18] / dArr8[15];
                dArr6[r11][r12] = d;
                if (r12 == 3) {
                    d = 0.0d;
                }
                dArr7[r18] = d;
            }
        }
        dArr7[15] = 1.0d;
        if (isZero(MatrixMathHelper.determinant(dArr7))) {
            return;
        }
        if (!isZero(dArr6[0][3]) || !isZero(dArr6[1][3]) || !isZero(dArr6[2][3])) {
            MatrixMathHelper.multiplyVectorByMatrix(new double[]{dArr6[0][3], dArr6[1][3], dArr6[2][3], dArr6[3][3]}, MatrixMathHelper.transpose(MatrixMathHelper.inverse(dArr7)), dArr);
        } else {
            dArr[2] = 0.0d;
            dArr[1] = 0.0d;
            dArr[0] = 0.0d;
            dArr[3] = 1.0d;
        }
        System.arraycopy(dArr6[3], 0, dArr4, 0, 3);
        double[][] dArr9 = (double[][]) Array.newInstance(double.class, 3, 3);
        for (int r2 = 0; r2 < 3; r2++) {
            dArr9[r2][0] = dArr6[r2][0];
            dArr9[r2][1] = dArr6[r2][1];
            dArr9[r2][2] = dArr6[r2][2];
        }
        dArr2[0] = MatrixMathHelper.v3Length(dArr9[0]);
        dArr9[0] = MatrixMathHelper.v3Normalize(dArr9[0], dArr2[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr2[1] = MatrixMathHelper.v3Length(dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Normalize(dArr9[1], dArr2[1]);
        dArr3[0] = dArr3[0] / dArr2[1];
        dArr3[1] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr3[1]);
        dArr3[2] = MatrixMathHelper.v3Dot(dArr9[1], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr3[2]);
        dArr2[2] = MatrixMathHelper.v3Length(dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Normalize(dArr9[2], dArr2[2]);
        dArr3[1] = dArr3[1] / dArr2[2];
        dArr3[2] = dArr3[2] / dArr2[2];
        if (MatrixMathHelper.v3Dot(dArr9[0], MatrixMathHelper.v3Cross(dArr9[1], dArr9[2])) < 0.0d) {
            for (int r22 = 0; r22 < 3; r22++) {
                dArr2[r22] = dArr2[r22] * (-1.0d);
                double[] dArr10 = dArr9[r22];
                dArr10[0] = dArr10[0] * (-1.0d);
                double[] dArr11 = dArr9[r22];
                dArr11[1] = dArr11[1] * (-1.0d);
                double[] dArr12 = dArr9[r22];
                dArr12[2] = dArr12[2] * (-1.0d);
            }
        }
        dArr5[0] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
        dArr5[1] = MatrixMathHelper.roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][1] * dArr9[2][1]) + (dArr9[2][2] * dArr9[2][2])))) * 57.29577951308232d);
        dArr5[2] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
    }

    private static void setTransformProperty(View view, ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        decomposeMatrix();
        MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) matrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) matrixDecompositionContext.translation[1]));
        view.setRotation((float) matrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) matrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) matrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) matrixDecompositionContext.scale[0]);
        view.setScaleY((float) matrixDecompositionContext.scale[1]);
        double[] dArr = matrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = (-1.0f) / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return this.mClassName;
    }

    @ReactProp(name = "mask")
    public void setMask(V v, String str) {
        v.setMask(str);
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(V v, String str) {
        v.setMarkerStart(str);
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(V v, String str) {
        v.setMarkerMid(str);
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(V v, String str) {
        v.setMarkerEnd(str);
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(V v, String str) {
        v.setClipPath(str);
    }

    @ReactProp(name = "clipRule")
    public void setClipRule(V v, int r2) {
        v.setClipRule(r2);
    }

    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(@Nonnull V v, float f) {
        v.setOpacity(f);
    }

    @ReactProp(name = "responsible")
    public void setResponsible(V v, boolean z) {
        v.setResponsible(z);
    }

    @ReactProp(name = ViewProps.POINTER_EVENTS)
    public void setPointerEvents(V v, @Nullable String str) {
        if (str == null) {
            v.setPointerEvents(PointerEvents.AUTO);
        } else {
            v.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "name")
    public void setName(V v, String str) {
        v.setName(str);
    }

    @ReactProp(name = "display")
    public void setDisplay(V v, String str) {
        v.setDisplay(str);
    }

    @ReactProp(name = "matrix")
    public void setMatrix(V v, Dynamic dynamic) {
        v.setMatrix(dynamic);
    }

    public void setMatrix(V v, @Nullable ReadableArray readableArray) {
        v.setMatrix(readableArray);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    public void setTransform(VirtualView virtualView, @Nullable ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(virtualView);
        } else {
            setTransformProperty(virtualView, readableArray);
        }
        Matrix matrix = virtualView.getMatrix();
        virtualView.mTransform = matrix;
        virtualView.mTransformInvertible = matrix.invert(virtualView.mInvTransform);
    }

    @ReactProp(name = ViewProps.TRANSFORM)
    public void setTransform(V v, Dynamic dynamic) {
        if (dynamic.getType() != ReadableType.Array) {
            return;
        }
        setTransform((VirtualView) v, dynamic.asArray());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateSvgView(V v) {
        SvgView svgView = v.getSvgView();
        if (svgView != null) {
            svgView.invalidate();
        }
        if (v instanceof TextView) {
            ((TextView) v).getTextContainer().clearChildCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull VirtualView virtualView) {
        super.addEventEmitters(themedReactContext, (ThemedReactContext) virtualView);
        virtualView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.horcrux.svg.VirtualViewManager.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (view instanceof VirtualView) {
                    VirtualViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                if (view instanceof VirtualView) {
                    VirtualViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(@Nonnull VirtualView virtualView) {
        super.onAfterUpdateTransaction((VirtualViewManager<V>) virtualView);
        invalidateSvgView(virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RenderableViewManager.java */
    /* renamed from: com.horcrux.svg.VirtualViewManager$2 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C34312 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass;

        static {
            int[] r0 = new int[SVGClass.values().length];
            $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass = r0;
            try {
                r0[SVGClass.RNSVGGroup.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGPath.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGCircle.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGEllipse.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGLine.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGRect.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGText.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGTSpan.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGTextPath.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGImage.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGClipPath.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGDefs.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGUse.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGSymbol.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGLinearGradient.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGRadialGradient.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGPattern.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGMask.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGMarker.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[SVGClass.RNSVGForeignObject.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public VirtualView createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        switch (C34312.$SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[this.svgClass.ordinal()]) {
            case 1:
                return new GroupView(themedReactContext);
            case 2:
                return new PathView(themedReactContext);
            case 3:
                return new CircleView(themedReactContext);
            case 4:
                return new EllipseView(themedReactContext);
            case 5:
                return new LineView(themedReactContext);
            case 6:
                return new RectView(themedReactContext);
            case 7:
                return new TextView(themedReactContext);
            case 8:
                return new TSpanView(themedReactContext);
            case 9:
                return new TextPathView(themedReactContext);
            case 10:
                return new ImageView(themedReactContext);
            case 11:
                return new ClipPathView(themedReactContext);
            case 12:
                return new DefsView(themedReactContext);
            case 13:
                return new UseView(themedReactContext);
            case 14:
                return new SymbolView(themedReactContext);
            case 15:
                return new LinearGradientView(themedReactContext);
            case 16:
                return new RadialGradientView(themedReactContext);
            case 17:
                return new PatternView(themedReactContext);
            case 18:
                return new MaskView(themedReactContext);
            case 19:
                return new MarkerView(themedReactContext);
            case 20:
                return new ForeignObjectView(themedReactContext);
            default:
                throw new IllegalStateException("Unexpected type " + this.svgClass.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setRenderableView(int r1, RenderableView renderableView) {
        mTagToRenderableView.put(r1, renderableView);
        SparseArray<Runnable> sparseArray = mTagToRunnable;
        Runnable runnable = sparseArray.get(r1);
        if (runnable != null) {
            runnable.run();
            sparseArray.delete(r1);
        }
    }

    static void runWhenViewIsAvailable(int r1, Runnable runnable) {
        mTagToRunnable.put(r1, runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static RenderableView getRenderableViewByTag(int r1) {
        return mTagToRenderableView.get(r1);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(@Nonnull VirtualView virtualView) {
        super.onDropViewInstance((VirtualViewManager<V>) virtualView);
        mTagToRenderableView.remove(virtualView.getId());
    }
}
