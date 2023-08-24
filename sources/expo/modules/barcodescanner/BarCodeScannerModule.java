package expo.modules.barcodescanner;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.barcodescanner.utils.BarCodeScannerResultSerializer;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import expo.modules.interfaces.imageloader.ImageLoaderInterface;
import expo.modules.interfaces.permissions.Permissions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: BarCodeScannerModule.kt */
@Metadata(m184d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00100\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u001f\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u0001H\u001aH\u001a0\u0019\"\u0006\b\u0000\u0010\u001a\u0018\u0001H\u0082\bJ\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J(\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00112\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006%²\u0006\n\u0010&\u001a\u00020'X\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "barCodeScannerProvider", "Lexpo/modules/barcodescanner/BarCodeScannerProvider;", "permissions", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissions", "()Lexpo/modules/interfaces/permissions/Permissions;", "permissions$delegate", "Lkotlin/Lazy;", "getConstants", "", "", "", "getName", "getPermissionsAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "requestPermissionsAsync", "scanFromURLAsync", ImagesContract.URL, "barCodeTypes", "", "", "Companion", "expo-barcode-scanner_release", "imageLoader", "Lexpo/modules/interfaces/imageloader/ImageLoaderInterface;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerModule extends ExportedModule {
    public static final Companion Companion = new Companion(null);
    private static final String ERROR_TAG = "E_BARCODE_SCANNER";
    private static final String TAG = "ExpoBarCodeScannerModule";
    private final BarCodeScannerProvider barCodeScannerProvider;
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final Lazy permissions$delegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return TAG;
    }

    public /* synthetic */ BarCodeScannerModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarCodeScannerModule(Context context, final ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.barCodeScannerProvider = new BarCodeScannerProvider();
        this.permissions$delegate = LazyKt.lazy(new Functions<Permissions>() { // from class: expo.modules.barcodescanner.BarCodeScannerModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.interfaces.permissions.Permissions] */
            @Override // kotlin.jvm.functions.Functions
            public final Permissions invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(Permissions.class);
            }
        });
    }

    private final Permissions getPermissions() {
        Object value = this.permissions$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-permissions>(...)");
        return (Permissions) value;
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.barcodescanner.BarCodeScannerModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = expo.modules.core.ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Map<String, Integer>> getConstants() {
        return MapsKt.mapOf(TuplesKt.m176to("BarCodeType", MapsKt.mapOf(TuplesKt.m176to("aztec", 4096), TuplesKt.m176to("ean13", 32), TuplesKt.m176to("ean8", 64), TuplesKt.m176to("qr", 256), TuplesKt.m176to("pdf417", 2048), TuplesKt.m176to("upc_e", 1024), TuplesKt.m176to("datamatrix", 16), TuplesKt.m176to("code39", 2), TuplesKt.m176to("code93", 4), TuplesKt.m176to("itf14", 128), TuplesKt.m176to("codabar", 8), TuplesKt.m176to("code128", 1), TuplesKt.m176to("upc_a", 512))), TuplesKt.m176to("Type", MapsKt.mapOf(TuplesKt.m176to("front", 1), TuplesKt.m176to("back", 2))));
    }

    @ExpoMethod
    public final void requestPermissionsAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        getPermissions().askForPermissionsWithPromise(promise, "android.permission.CAMERA");
    }

    @ExpoMethod
    public final void getPermissionsAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        getPermissions().getPermissionsWithPromise(promise, "android.permission.CAMERA");
    }

    @ExpoMethod
    public final void scanFromURLAsync(final String url, List<Double> list, final Promise promise) {
        IntRange indices;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final ArrayList arrayList = null;
        if (list != null && (indices = CollectionsKt.getIndices(list)) != null) {
            IntRange intRange = indices;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            Iterator<Integer> it = intRange.iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf((int) list.get(((IntIterator) it).nextInt()).doubleValue()));
            }
            arrayList = arrayList2;
        }
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        m1623scanFromURLAsync$lambda1(LazyKt.lazy(new Functions<ImageLoaderInterface>() { // from class: expo.modules.barcodescanner.BarCodeScannerModule$scanFromURLAsync$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.imageloader.ImageLoaderInterface, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final ImageLoaderInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ImageLoaderInterface.class);
            }
        })).loadImageForManipulationFromURL(url, new ImageLoaderInterface.ResultListener() { // from class: expo.modules.barcodescanner.BarCodeScannerModule$scanFromURLAsync$1
            @Override // expo.modules.interfaces.imageloader.ImageLoaderInterface.ResultListener
            public void onSuccess(Bitmap bitmap) {
                BarCodeScannerProvider barCodeScannerProvider;
                Context context;
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                barCodeScannerProvider = BarCodeScannerModule.this.barCodeScannerProvider;
                context = BarCodeScannerModule.this.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                BarCodeScannerInterface createBarCodeDetectorWithContext = barCodeScannerProvider.createBarCodeDetectorWithContext(context);
                BarCodeScannerSettings barCodeScannerSettings = new BarCodeScannerSettings();
                barCodeScannerSettings.putTypes(arrayList);
                createBarCodeDetectorWithContext.setSettings(barCodeScannerSettings);
                List<BarCodeScannerResult> scanMultiple = createBarCodeDetectorWithContext.scanMultiple(bitmap);
                Intrinsics.checkNotNullExpressionValue(scanMultiple, "scanner.scanMultiple(bitmap)");
                List<Integer> list2 = arrayList;
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : scanMultiple) {
                    if (list2.contains(Integer.valueOf(((BarCodeScannerResult) obj).getType()))) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList<BarCodeScannerResult> arrayList4 = arrayList3;
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                for (BarCodeScannerResult it2 : arrayList4) {
                    BarCodeScannerResultSerializer barCodeScannerResultSerializer = BarCodeScannerResultSerializer.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    arrayList5.add(barCodeScannerResultSerializer.toBundle(it2, 1.0f));
                }
                promise.resolve(arrayList5);
            }

            @Override // expo.modules.interfaces.imageloader.ImageLoaderInterface.ResultListener
            public void onFailure(Throwable th) {
                Promise promise2 = promise;
                String str = url;
                promise2.reject("E_BARCODE_SCANNER_IMAGE_RETRIEVAL_ERROR", "Could not get the image from given url: '" + str + "'", th);
            }
        });
    }

    /* renamed from: scanFromURLAsync$lambda-1  reason: not valid java name */
    private static final ImageLoaderInterface m1623scanFromURLAsync$lambda1(Lazy<? extends ImageLoaderInterface> lazy) {
        ImageLoaderInterface value = lazy.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "scanFromURLAsync$lambda-1(...)");
        return value;
    }

    /* compiled from: BarCodeScannerModule.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerModule$Companion;", "", "()V", "ERROR_TAG", "", "TAG", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
