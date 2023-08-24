package expo.modules.p019av.player.datasource;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: expo.modules.av.player.datasource.SharedCookiesDataSourceFactoryProvider */
/* loaded from: classes4.dex */
public class SharedCookiesDataSourceFactoryProvider implements InternalModule, DataSourceFactoryProvider {
    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class> getExportedInterfaces() {
        return Collections.singletonList(DataSourceFactoryProvider.class);
    }

    @Override // expo.modules.p019av.player.datasource.DataSourceFactoryProvider
    public DataSource.Factory createFactory(Context context, ModuleRegistry moduleRegistry, String str, Map<String, Object> map, TransferListener transferListener) {
        return new SharedCookiesDataSourceFactory(context, moduleRegistry, str, map, transferListener);
    }
}
