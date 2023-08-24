package expo.modules.core;

import android.content.Context;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.SingletonModule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes4.dex */
public class ModuleRegistryProvider {
    private List<Package> mPackages;

    public ModuleRegistryProvider(List<Package> list) {
        this.mPackages = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Package> getPackages() {
        return this.mPackages;
    }

    public ModuleRegistry get(Context context) {
        return new ModuleRegistry(createInternalModules(context), createExportedModules(context), createViewManagers(context), createSingletonModules(context));
    }

    public Collection<InternalModule> createInternalModules(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Package r2 : getPackages()) {
            arrayList.addAll(r2.createInternalModules(context));
        }
        return arrayList;
    }

    public Collection<ExportedModule> createExportedModules(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Package r2 : getPackages()) {
            arrayList.addAll(r2.createExportedModules(context));
        }
        return arrayList;
    }

    public Collection<ViewManager> createViewManagers(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Package r2 : getPackages()) {
            arrayList.addAll(r2.createViewManagers(context));
        }
        return arrayList;
    }

    public Collection<SingletonModule> createSingletonModules(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Package r2 : getPackages()) {
            arrayList.addAll(r2.createSingletonModules(context));
        }
        return arrayList;
    }
}
