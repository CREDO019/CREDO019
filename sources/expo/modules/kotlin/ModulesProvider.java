package expo.modules.kotlin;

import expo.modules.kotlin.modules.Module;
import java.util.List;
import kotlin.Metadata;

/* compiled from: ModulesProvider.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003H&¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/kotlin/ModulesProvider;", "", "getModulesList", "", "Ljava/lang/Class;", "Lexpo/modules/kotlin/modules/Module;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface ModulesProvider {
    List<Class<? extends Module>> getModulesList();
}
