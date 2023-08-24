package expo.modules.barcodescanner;

import android.content.Context;
import expo.modules.barcodescanner.scanners.GMVBarCodeScanner;
import expo.modules.barcodescanner.scanners.ZxingBarCodeScanner;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerProviderInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannerProvider.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\n0\tH\u0016¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerProvider;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerProviderInterface;", "()V", "createBarCodeDetectorWithContext", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerInterface;", "context", "Landroid/content/Context;", "getExportedInterfaces", "", "Ljava/lang/Class;", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerProvider implements InternalModule, BarCodeScannerProviderInterface {
    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<BarCodeScannerProviderInterface>> getExportedInterfaces() {
        return CollectionsKt.listOf(BarCodeScannerProviderInterface.class);
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerProviderInterface
    public BarCodeScannerInterface createBarCodeDetectorWithContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        GMVBarCodeScanner gMVBarCodeScanner = new GMVBarCodeScanner(context);
        if (!gMVBarCodeScanner.isAvailable()) {
            gMVBarCodeScanner = null;
        }
        if (gMVBarCodeScanner != null) {
            return gMVBarCodeScanner;
        }
        return new ZxingBarCodeScanner(context);
    }
}
