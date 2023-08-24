package expo.modules.updates.selectionpolicy;

import expo.modules.updates.UpdatesConfiguration;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectionPolicyFactory.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¨\u0006\t"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/SelectionPolicyFactory;", "", "()V", "createFilterAwarePolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", "runtimeVersions", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SelectionPolicyFactory {
    public static final SelectionPolicyFactory INSTANCE = new SelectionPolicyFactory();

    private SelectionPolicyFactory() {
    }

    public final SelectionPolicy createFilterAwarePolicy(List<String> runtimeVersions) {
        Intrinsics.checkNotNullParameter(runtimeVersions, "runtimeVersions");
        return new SelectionPolicy(new LauncherSelectionPolicyFilterAware(runtimeVersions), new LoaderSelectionPolicyFilterAware(), new ReaperSelectionPolicyFilterAware());
    }

    @JvmStatic
    public static final SelectionPolicy createFilterAwarePolicy(String runtimeVersion) {
        Intrinsics.checkNotNullParameter(runtimeVersion, "runtimeVersion");
        return new SelectionPolicy(new LauncherSelectionPolicyFilterAware(runtimeVersion), new LoaderSelectionPolicyFilterAware(), new ReaperSelectionPolicyFilterAware());
    }
}
