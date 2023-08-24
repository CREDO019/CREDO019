package expo.modules.p019av;

import android.content.Context;
import expo.modules.core.BasePackage;
import expo.modules.core.ExportedModule;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.p019av.player.datasource.SharedCookiesDataSourceFactoryProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: expo.modules.av.AVPackage */
/* loaded from: classes4.dex */
public class AVPackage extends BasePackage {
    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<InternalModule> createInternalModules(Context context) {
        return Arrays.asList(new AVManager(context), new SharedCookiesDataSourceFactoryProvider());
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ExportedModule> createExportedModules(Context context) {
        return Arrays.asList(new AVModule(context));
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ViewManager> createViewManagers(Context context) {
        return Collections.emptyList();
    }
}
