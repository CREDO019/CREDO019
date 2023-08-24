package expo.modules.barcodescanner;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: BarCodeScannerView.kt */
@Metadata(m184d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002*\u0001\u000f\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J(\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\u001f\u0010\u001d\u001a\u0010\u0012\f\u0012\n  *\u0004\u0018\u0001H\u001fH\u001f0\u001e\"\u0006\b\u0000\u0010\u001f\u0018\u0001H\u0082\bJ\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u0018H\u0014J0\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0014J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020'2\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0010\u0010,\u001a\u00020\u00182\b\u0010-\u001a\u0004\u0018\u00010.J\u000e\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00020\bJ\u0010\u00101\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u00062²\u0006\n\u00103\u001a\u000204X\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerView;", "Landroid/view/ViewGroup;", "viewContext", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "actualDeviceOrientation", "", "displayDensity", "", "getDisplayDensity", "()F", "leftPadding", "orientationListener", "expo/modules/barcodescanner/BarCodeScannerView$orientationListener$1", "Lexpo/modules/barcodescanner/BarCodeScannerView$orientationListener$1;", "topPadding", SessionDescription.ATTR_TYPE, "viewFinder", "Lexpo/modules/barcodescanner/BarCodeScannerViewFinder;", "getDeviceOrientation", "context", "layoutViewFinder", "", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onBarCodeScanned", "barCode", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "onDetachedFromWindow", ViewProps.ON_LAYOUT, "changed", "", "onViewAdded", "child", "Landroid/view/View;", "setActualDeviceOrientation", "setBarCodeScannerSettings", "settings", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerSettings;", "setCameraType", "cameraType", "transformBarCodeScannerResultToViewCoordinates", "expo-barcode-scanner_release", "emitter", "Lexpo/modules/core/interfaces/services/EventEmitter;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerView extends ViewGroup {
    private int actualDeviceOrientation;
    private int leftPadding;
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final BarCodeScannerView$orientationListener$1 orientationListener;
    private int topPadding;
    private int type;
    private final Context viewContext;
    private BarCodeScannerViewFinder viewFinder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v1, types: [expo.modules.barcodescanner.BarCodeScannerView$orientationListener$1] */
    public BarCodeScannerView(final Context viewContext, ModuleRegistryDelegate moduleRegistryDelegate) {
        super(viewContext);
        Intrinsics.checkNotNullParameter(viewContext, "viewContext");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.viewContext = viewContext;
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        ?? r3 = new OrientationEventListener(viewContext) { // from class: expo.modules.barcodescanner.BarCodeScannerView$orientationListener$1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int r2) {
                Context context;
                boolean actualDeviceOrientation;
                BarCodeScannerView barCodeScannerView = BarCodeScannerView.this;
                context = barCodeScannerView.viewContext;
                actualDeviceOrientation = barCodeScannerView.setActualDeviceOrientation(context);
                if (actualDeviceOrientation) {
                    BarCodeScannerView.this.layoutViewFinder();
                }
            }
        };
        if (r3.canDetectOrientation()) {
            r3.enable();
        } else {
            r3.disable();
        }
        this.orientationListener = r3;
        this.actualDeviceOrientation = -1;
        ExpoBarCodeScanner.Companion.createInstance(getDeviceOrientation(viewContext));
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.barcodescanner.BarCodeScannerView$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        disable();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        layoutViewFinder(r2, r3, r4, r5);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        Intrinsics.checkNotNullParameter(child, "child");
        BarCodeScannerViewFinder barCodeScannerViewFinder = this.viewFinder;
        BarCodeScannerViewFinder barCodeScannerViewFinder2 = null;
        if (barCodeScannerViewFinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            barCodeScannerViewFinder = null;
        }
        if (Intrinsics.areEqual(barCodeScannerViewFinder, child)) {
            return;
        }
        BarCodeScannerViewFinder barCodeScannerViewFinder3 = this.viewFinder;
        if (barCodeScannerViewFinder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            barCodeScannerViewFinder3 = null;
        }
        removeView(barCodeScannerViewFinder3);
        BarCodeScannerViewFinder barCodeScannerViewFinder4 = this.viewFinder;
        if (barCodeScannerViewFinder4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
        } else {
            barCodeScannerViewFinder2 = barCodeScannerViewFinder4;
        }
        addView(barCodeScannerViewFinder2, 0);
    }

    /* renamed from: onBarCodeScanned$lambda-1  reason: not valid java name */
    private static final EventEmitter m1624onBarCodeScanned$lambda1(Lazy<? extends EventEmitter> lazy) {
        EventEmitter value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "onBarCodeScanned$lambda-1(...)");
        return value;
    }

    private final float getDisplayDensity() {
        return getResources().getDisplayMetrics().density;
    }

    private final void transformBarCodeScannerResultToViewCoordinates(BarCodeScannerResult barCodeScannerResult) {
        List<Integer> cornerPoints = barCodeScannerResult.getCornerPoints();
        int width = getWidth() - (this.leftPadding * 2);
        int height = getHeight() - (this.topPadding * 2);
        if (this.type == 1 && getDeviceOrientation(this.viewContext) % 2 == 0) {
            Intrinsics.checkNotNullExpressionValue(cornerPoints, "cornerPoints");
            IntProgression step = RangesKt.step(RangesKt.until(1, cornerPoints.size()), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    int r10 = first + step2;
                    int referenceImageHeight = barCodeScannerResult.getReferenceImageHeight();
                    Integer num = cornerPoints.get(first);
                    Intrinsics.checkNotNullExpressionValue(num, "cornerPoints[it]");
                    cornerPoints.set(first, Integer.valueOf(referenceImageHeight - num.intValue()));
                    if (first == last) {
                        break;
                    }
                    first = r10;
                }
            }
        }
        if (this.type == 1 && getDeviceOrientation(this.viewContext) % 2 != 0) {
            Intrinsics.checkNotNullExpressionValue(cornerPoints, "cornerPoints");
            IntProgression step3 = RangesKt.step(RangesKt.until(0, cornerPoints.size()), 2);
            int first2 = step3.getFirst();
            int last2 = step3.getLast();
            int step4 = step3.getStep();
            if ((step4 > 0 && first2 <= last2) || (step4 < 0 && last2 <= first2)) {
                while (true) {
                    int r11 = first2 + step4;
                    int referenceImageWidth = barCodeScannerResult.getReferenceImageWidth();
                    Integer num2 = cornerPoints.get(first2);
                    Intrinsics.checkNotNullExpressionValue(num2, "cornerPoints[it]");
                    cornerPoints.set(first2, Integer.valueOf(referenceImageWidth - num2.intValue()));
                    if (first2 == last2) {
                        break;
                    }
                    first2 = r11;
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(cornerPoints, "cornerPoints");
        IntProgression step5 = RangesKt.step(RangesKt.until(0, cornerPoints.size()), 2);
        int first3 = step5.getFirst();
        int last3 = step5.getLast();
        int step6 = step5.getStep();
        if ((step6 > 0 && first3 <= last3) || (step6 < 0 && last3 <= first3)) {
            while (true) {
                int r8 = first3 + step6;
                cornerPoints.set(first3, Integer.valueOf(MathKt.roundToInt(((cornerPoints.get(first3).intValue() * width) / barCodeScannerResult.getReferenceImageWidth()) + this.leftPadding)));
                if (first3 == last3) {
                    break;
                }
                first3 = r8;
            }
        }
        IntProgression step7 = RangesKt.step(RangesKt.until(1, cornerPoints.size()), 2);
        int first4 = step7.getFirst();
        int last4 = step7.getLast();
        int step8 = step7.getStep();
        if ((step8 > 0 && first4 <= last4) || (step8 < 0 && last4 <= first4)) {
            while (true) {
                int r5 = first4 + step8;
                cornerPoints.set(first4, Integer.valueOf(MathKt.roundToInt(((cornerPoints.get(first4).intValue() * height) / barCodeScannerResult.getReferenceImageHeight()) + this.topPadding)));
                if (first4 == last4) {
                    break;
                }
                first4 = r5;
            }
        }
        barCodeScannerResult.setReferenceImageHeight(getHeight());
        barCodeScannerResult.setReferenceImageWidth(getWidth());
        barCodeScannerResult.setCornerPoints(cornerPoints);
    }

    public final void setCameraType(int r4) {
        this.type = r4;
        BarCodeScannerViewFinder barCodeScannerViewFinder = this.viewFinder;
        if (barCodeScannerViewFinder == null) {
            BarCodeScannerViewFinder barCodeScannerViewFinder2 = new BarCodeScannerViewFinder(this.viewContext, r4, this, this.moduleRegistryDelegate);
            this.viewFinder = barCodeScannerViewFinder2;
            addView(barCodeScannerViewFinder2);
            return;
        }
        if (barCodeScannerViewFinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            barCodeScannerViewFinder = null;
        }
        barCodeScannerViewFinder.setCameraType(r4);
        ExpoBarCodeScanner.Companion.getInstance().adjustPreviewLayout(r4);
    }

    public final void setBarCodeScannerSettings(BarCodeScannerSettings barCodeScannerSettings) {
        BarCodeScannerViewFinder barCodeScannerViewFinder = this.viewFinder;
        if (barCodeScannerViewFinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            barCodeScannerViewFinder = null;
        }
        barCodeScannerViewFinder.setBarCodeScannerSettings(barCodeScannerSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean setActualDeviceOrientation(Context context) {
        int deviceOrientation = getDeviceOrientation(context);
        if (this.actualDeviceOrientation != deviceOrientation) {
            this.actualDeviceOrientation = deviceOrientation;
            ExpoBarCodeScanner.Companion.getInstance().setActualDeviceOrientation(this.actualDeviceOrientation);
            return true;
        }
        return false;
    }

    private final int getDeviceOrientation(Context context) {
        Object systemService = context.getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return ((WindowManager) systemService).getDefaultDisplay().getRotation();
    }

    public final void layoutViewFinder() {
        layoutViewFinder(getLeft(), getTop(), getRight(), getBottom());
    }

    private final void layoutViewFinder(int r12, int r13, int r14, int r15) {
        int r5;
        int r0;
        BarCodeScannerViewFinder barCodeScannerViewFinder = this.viewFinder;
        if (barCodeScannerViewFinder == null) {
            return;
        }
        float f = r14 - r12;
        float f2 = r15 - r13;
        BarCodeScannerViewFinder barCodeScannerViewFinder2 = null;
        if (barCodeScannerViewFinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            barCodeScannerViewFinder = null;
        }
        double ratio = barCodeScannerViewFinder.getRatio();
        double d = f2 * ratio;
        double d2 = f;
        if (d < d2) {
            r0 = (int) d;
            r5 = (int) f2;
        } else {
            r5 = (int) (d2 / ratio);
            r0 = (int) f;
        }
        float f3 = 2;
        int r1 = (int) ((f - r0) / f3);
        int r2 = (int) ((f2 - r5) / f3);
        this.leftPadding = r1;
        this.topPadding = r2;
        BarCodeScannerViewFinder barCodeScannerViewFinder3 = this.viewFinder;
        if (barCodeScannerViewFinder3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
        } else {
            barCodeScannerViewFinder2 = barCodeScannerViewFinder3;
        }
        barCodeScannerViewFinder2.layout(r1, r2, r0 + r1, r5 + r2);
        postInvalidate(r12, r13, r14, r15);
    }

    public final void onBarCodeScanned(BarCodeScannerResult barCode) {
        Intrinsics.checkNotNullParameter(barCode, "barCode");
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Lazy lazy = LazyKt.lazy(new Functions<EventEmitter>() { // from class: expo.modules.barcodescanner.BarCodeScannerView$onBarCodeScanned$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.services.EventEmitter] */
            @Override // kotlin.jvm.functions.Functions
            public final EventEmitter invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(EventEmitter.class);
            }
        });
        transformBarCodeScannerResultToViewCoordinates(barCode);
        m1624onBarCodeScanned$lambda1(lazy).emit(getId(), BarCodeScannedEvent.Companion.obtain(getId(), barCode, getDisplayDensity()));
    }
}
