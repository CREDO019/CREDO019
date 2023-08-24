package expo.modules.barcodescanner;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerProviderInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BarCodeScannerViewFinder.kt */
@Metadata(m184d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\b\u0000\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u001f\u0010\u001f\u001a\u0010\u0012\f\u0012\n \"*\u0004\u0018\u0001H!H!0 \"\u0006\b\u0000\u0010!\u0018\u0001H\u0082\bJ\u0018\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0016J \u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0016J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0014H\u0016J \u0010,\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H\u0016J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0014H\u0016J\u001a\u0010.\u001a\u00020\u001e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010/\u001a\u00020%H\u0002J\u0010\u00100\u001a\u00020\u001e2\b\u00101\u001a\u0004\u0018\u000102J\u000e\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\u0007J\b\u00105\u001a\u00020\u001eH\u0002J\b\u00106\u001a\u00020\u001eH\u0002J\b\u00107\u001a\u00020\u001eH\u0002J\b\u00108\u001a\u00020\u001eH\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006:²\u0006\n\u0010;\u001a\u00020<X\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerViewFinder;", "Landroid/view/TextureView;", "Landroid/view/TextureView$SurfaceTextureListener;", "Landroid/hardware/Camera$PreviewCallback;", "context", "Landroid/content/Context;", "cameraType", "", "barCodeScannerView", "Lexpo/modules/barcodescanner/BarCodeScannerView;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;ILexpo/modules/barcodescanner/BarCodeScannerView;Lexpo/modules/core/ModuleRegistryDelegate;)V", "barCodeScanner", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerInterface;", "camera", "Landroid/hardware/Camera;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "finderSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "isChanging", "", "isStarting", "isStopping", "ratio", "", "getRatio", "()D", "initBarCodeScanner", "", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onPreviewFrame", "data", "", "innerCamera", "onSurfaceTextureAvailable", "surface", "width", "height", "onSurfaceTextureDestroyed", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "scanForBarcodes", "mImageData", "setBarCodeScannerSettings", "settings", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerSettings;", "setCameraType", SessionDescription.ATTR_TYPE, "startCamera", "startPreview", "stopCamera", "stopPreview", "Companion", "expo-barcode-scanner_release", "barCodeScannerProvider", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerProviderInterface;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerViewFinder extends TextureView implements TextureView.SurfaceTextureListener, Camera.PreviewCallback {
    public static final Companion Companion = new Companion(null);
    private static volatile boolean barCodeScannerTaskLock;
    private BarCodeScannerInterface barCodeScanner;
    private BarCodeScannerView barCodeScannerView;
    private Camera camera;
    private int cameraType;
    private final CoroutineScope coroutineScope;
    private SurfaceTexture finderSurfaceTexture;
    private volatile boolean isChanging;
    private volatile boolean isStarting;
    private volatile boolean isStopping;
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int r2, int r3) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarCodeScannerViewFinder(Context context, int r3, BarCodeScannerView barCodeScannerView, ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barCodeScannerView, "barCodeScannerView");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.cameraType = r3;
        this.barCodeScannerView = barCodeScannerView;
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        setSurfaceTextureListener(this);
        initBarCodeScanner();
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.barcodescanner.BarCodeScannerViewFinder$moduleRegistry$$inlined$getFromModuleRegistry$1
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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int r2, int r3) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.finderSurfaceTexture = surface;
        startCamera();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.finderSurfaceTexture = null;
        stopCamera();
        try {
            CoroutineScopeKt.cancel(this.coroutineScope, new ModuleDestroyedException("View destroyed, scope canceled"));
            return true;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            Log.w("ScannerViewFinder", message, e);
            return true;
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        this.finderSurfaceTexture = surface;
    }

    public final double getRatio() {
        return ExpoBarCodeScanner.Companion.getInstance().getPreviewWidth(this.cameraType) / ExpoBarCodeScanner.Companion.getInstance().getPreviewHeight(this.cameraType);
    }

    public final void setCameraType(final int r3) {
        if (this.cameraType == r3) {
            return;
        }
        new Thread(new Runnable() { // from class: expo.modules.barcodescanner.BarCodeScannerViewFinder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarCodeScannerViewFinder.m1626setCameraType$lambda0(BarCodeScannerViewFinder.this, r3);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCameraType$lambda-0  reason: not valid java name */
    public static final void m1626setCameraType$lambda0(BarCodeScannerViewFinder this$0, int r2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isChanging = true;
        this$0.stopPreview();
        this$0.cameraType = r2;
        this$0.startPreview();
        this$0.isChanging = false;
    }

    private final void startPreview() {
        if (this.finderSurfaceTexture != null) {
            startCamera();
        }
    }

    private final void stopPreview() {
        if (this.camera != null) {
            stopCamera();
        }
    }

    private final synchronized void startCamera() {
        if (!this.isStarting && !this.isStopping) {
            this.isStarting = true;
            try {
                Camera acquireCameraInstance = ExpoBarCodeScanner.Companion.getInstance().acquireCameraInstance(this.cameraType);
                this.camera = acquireCameraInstance;
                if (acquireCameraInstance != null) {
                    Camera.Parameters parameters = acquireCameraInstance.getParameters();
                    List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                    if (supportedFocusModes != null && supportedFocusModes.contains("continuous-picture")) {
                        parameters.setFocusMode("continuous-picture");
                    }
                    ExpoBarCodeScanner companion = ExpoBarCodeScanner.Companion.getInstance();
                    List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
                    Intrinsics.checkNotNullExpressionValue(supportedPictureSizes, "temporaryParameters.supportedPictureSizes");
                    Camera.Size bestSize = companion.getBestSize(supportedPictureSizes, Integer.MAX_VALUE, Integer.MAX_VALUE);
                    if (parameters != null) {
                        parameters.setPictureSize(bestSize.width, bestSize.height);
                    }
                    acquireCameraInstance.setParameters(parameters);
                    acquireCameraInstance.setPreviewTexture(this.finderSurfaceTexture);
                    acquireCameraInstance.startPreview();
                    acquireCameraInstance.setPreviewCallback(this);
                    this.barCodeScannerView.layoutViewFinder();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
                stopCamera();
            }
            this.isStarting = false;
        }
    }

    private final synchronized void stopCamera() {
        if (!this.isStopping) {
            this.isStopping = true;
            try {
                Camera camera = this.camera;
                if (camera != null) {
                    camera.stopPreview();
                    camera.setPreviewCallback(null);
                    ExpoBarCodeScanner.Companion.getInstance().releaseCameraInstance();
                }
                this.camera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.isStopping = false;
        }
    }

    /* renamed from: initBarCodeScanner$lambda-3  reason: not valid java name */
    private static final BarCodeScannerProviderInterface m1625initBarCodeScanner$lambda3(Lazy<? extends BarCodeScannerProviderInterface> lazy) {
        BarCodeScannerProviderInterface value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "initBarCodeScanner$lambda-3(...)");
        return value;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] data, Camera innerCamera) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(innerCamera, "innerCamera");
        if (barCodeScannerTaskLock) {
            return;
        }
        barCodeScannerTaskLock = true;
        scanForBarcodes(innerCamera, data);
    }

    public final void setBarCodeScannerSettings(BarCodeScannerSettings barCodeScannerSettings) {
        BarCodeScannerInterface barCodeScannerInterface = this.barCodeScanner;
        if (barCodeScannerInterface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("barCodeScanner");
            barCodeScannerInterface = null;
        }
        barCodeScannerInterface.setSettings(barCodeScannerSettings);
    }

    private final void scanForBarcodes(Camera camera, byte[] bArr) {
        if (CoroutineScopeKt.isActive(this.coroutineScope)) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new BarCodeScannerViewFinder$scanForBarcodes$1(this, camera, bArr, null), 3, null);
        } else {
            barCodeScannerTaskLock = false;
        }
    }

    /* compiled from: BarCodeScannerViewFinder.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerViewFinder$Companion;", "", "()V", "barCodeScannerTaskLock", "", "getBarCodeScannerTaskLock", "()Z", "setBarCodeScannerTaskLock", "(Z)V", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getBarCodeScannerTaskLock() {
            return BarCodeScannerViewFinder.barCodeScannerTaskLock;
        }

        public final void setBarCodeScannerTaskLock(boolean z) {
            BarCodeScannerViewFinder.barCodeScannerTaskLock = z;
        }
    }

    private final void initBarCodeScanner() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        BarCodeScannerInterface createBarCodeDetectorWithContext = m1625initBarCodeScanner$lambda3(LazyKt.lazy(new Functions<BarCodeScannerProviderInterface>() { // from class: expo.modules.barcodescanner.BarCodeScannerViewFinder$initBarCodeScanner$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.barcodescanner.BarCodeScannerProviderInterface, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final BarCodeScannerProviderInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(BarCodeScannerProviderInterface.class);
            }
        })).createBarCodeDetectorWithContext(getContext());
        Intrinsics.checkNotNullExpressionValue(createBarCodeDetectorWithContext, "barCodeScannerProvider.c…ectorWithContext(context)");
        this.barCodeScanner = createBarCodeDetectorWithContext;
    }
}
