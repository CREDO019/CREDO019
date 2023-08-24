package expo.modules.kotlin;

import android.util.Log;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ExpoModulesHelper.kt */
@Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m183d2 = {"Lexpo/modules/kotlin/ExpoModulesHelper;", "", "()V", "Companion", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ExpoModulesHelper {
    public static final Companion Companion = new Companion(null);
    private static final Lazy<ModulesProvider> modulesProvider$delegate = LazyKt.lazy(new Functions<ModulesProvider>() { // from class: expo.modules.kotlin.ExpoModulesHelper$Companion$modulesProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Functions
        public final ModulesProvider invoke() {
            try {
                Object newInstance = Class.forName("expo.modules.ExpoModulesPackageList").newInstance();
                if (newInstance != null) {
                    return (ModulesProvider) newInstance;
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.ModulesProvider");
            } catch (Exception e) {
                Log.e("ExpoModulesHelper", "Couldn't get expo modules list.", e);
                return null;
            }
        }
    });

    /* compiled from: ExpoModulesHelper.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/ExpoModulesHelper$Companion;", "", "()V", "modulesProvider", "Lexpo/modules/kotlin/ModulesProvider;", "getModulesProvider$annotations", "getModulesProvider", "()Lexpo/modules/kotlin/ModulesProvider;", "modulesProvider$delegate", "Lkotlin/Lazy;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getModulesProvider$annotations() {
        }

        private Companion() {
        }

        public final ModulesProvider getModulesProvider() {
            return (ModulesProvider) ExpoModulesHelper.modulesProvider$delegate.getValue();
        }
    }
}
