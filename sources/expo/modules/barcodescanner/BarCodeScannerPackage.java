package expo.modules.barcodescanner;

import android.content.Context;
import expo.modules.core.BasePackage;
import expo.modules.core.ExportedModule;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.InternalModule;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannerPackage.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerPackage;", "Lexpo/modules/core/BasePackage;", "()V", "createExportedModules", "", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "createInternalModules", "Lexpo/modules/core/interfaces/InternalModule;", "createViewManagers", "Lexpo/modules/core/ViewManager;", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerPackage extends BasePackage {
    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<InternalModule> createInternalModules(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new BarCodeScannerProvider());
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ExportedModule> createExportedModules(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new BarCodeScannerModule(context, null, 2, null));
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ViewManager<?>> createViewManagers(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new BarCodeScannerViewManager(null, 1, null));
    }
}
