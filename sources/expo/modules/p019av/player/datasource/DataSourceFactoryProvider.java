package expo.modules.p019av.player.datasource;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import expo.modules.core.ModuleRegistry;
import java.util.Map;

/* renamed from: expo.modules.av.player.datasource.DataSourceFactoryProvider */
/* loaded from: classes4.dex */
public interface DataSourceFactoryProvider {
    DataSource.Factory createFactory(Context context, ModuleRegistry moduleRegistry, String str, Map<String, Object> map, TransferListener transferListener);
}
