package expo.modules.location;

import android.content.Context;
import expo.modules.core.BasePackage;
import expo.modules.core.ExportedModule;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class LocationPackage extends BasePackage {
    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ExportedModule> createExportedModules(Context context) {
        return Collections.singletonList(new LocationModule(context));
    }
}
