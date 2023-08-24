package expo.modules.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.interfaces.constants.ConstantsInterface;
import expo.modules.interfaces.font.FontManagerInterface;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FontLoaderModule.kt */
@Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u0001H\u0014H\u00140\u0013\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0082\bJ\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0017H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018²\u0006\f\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u008a\u0084\u0002²\u0006\f\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u008a\u0084\u0002"}, m183d2 = {"Lexpo/modules/font/FontLoaderModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isScoped", "", "()Z", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "getName", "", "loadAsync", "", "fontFamilyName", "localUri", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "expo-font_release", "fontManager", "Lexpo/modules/interfaces/font/FontManagerInterface;", "constantsModule", "Lexpo/modules/interfaces/constants/ConstantsInterface;"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FontLoaderModule extends ExportedModule {
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoFontLoader";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FontLoaderModule(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.moduleRegistryDelegate = new ModuleRegistryDelegate();
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.font.FontLoaderModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @ExpoMethod
    public final void loadAsync(String fontFamilyName, String localUri, Promise promise) {
        Typeface createFromFile;
        Intrinsics.checkNotNullParameter(fontFamilyName, "fontFamilyName");
        Intrinsics.checkNotNullParameter(localUri, "localUri");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            String str = isScoped() ? "ExpoFont-" : "";
            if (StringsKt.startsWith$default(localUri, "asset://", false, 2, (Object) null)) {
                AssetManager assets = getContext().getAssets();
                String substring = localUri.substring(9);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                createFromFile = Typeface.createFromAsset(assets, substring);
                Intrinsics.checkNotNullExpressionValue(createFromFile, "{\n        Typeface.creat…th + 1)\n        )\n      }");
            } else {
                createFromFile = Typeface.createFromFile(new File(Uri.parse(localUri).getPath()));
                Intrinsics.checkNotNullExpressionValue(createFromFile, "{\n        Typeface.creat…(localUri).path))\n      }");
            }
            final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
            Lazy lazy = LazyKt.lazy(new Functions<FontManagerInterface>() { // from class: expo.modules.font.FontLoaderModule$loadAsync$$inlined$moduleRegistry$1
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.font.FontManagerInterface, java.lang.Object] */
                @Override // kotlin.jvm.functions.Functions
                public final FontManagerInterface invoke() {
                    ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                    Intrinsics.checkNotNull(moduleRegistry);
                    return moduleRegistry.getModule(FontManagerInterface.class);
                }
            });
            if (m1638loadAsync$lambda0(lazy) == null) {
                promise.reject("E_NO_FONT_MANAGER", "There is no FontManager in module registry. Are you sure all the dependencies of expo-font are installed and linked?");
                return;
            }
            FontManagerInterface m1638loadAsync$lambda0 = m1638loadAsync$lambda0(lazy);
            Intrinsics.checkNotNull(m1638loadAsync$lambda0);
            m1638loadAsync$lambda0.setTypeface(str + fontFamilyName, 0, createFromFile);
            promise.resolve(null);
        } catch (Exception e) {
            String message = e.getMessage();
            promise.reject("E_UNEXPECTED", "Font.loadAsync unexpected exception: " + message, e);
        }
    }

    /* renamed from: loadAsync$lambda-0  reason: not valid java name */
    private static final FontManagerInterface m1638loadAsync$lambda0(Lazy<? extends FontManagerInterface> lazy) {
        return lazy.getValue();
    }

    /* renamed from: _get_isScoped_$lambda-1  reason: not valid java name */
    private static final ConstantsInterface m1637_get_isScoped_$lambda1(Lazy<? extends ConstantsInterface> lazy) {
        return lazy.getValue();
    }

    private final boolean isScoped() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Lazy lazy = LazyKt.lazy(new Functions<ConstantsInterface>() { // from class: expo.modules.font.FontLoaderModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.interfaces.constants.ConstantsInterface, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final ConstantsInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ConstantsInterface.class);
            }
        });
        if (m1637_get_isScoped_$lambda1(lazy) != null) {
            ConstantsInterface m1637_get_isScoped_$lambda1 = m1637_get_isScoped_$lambda1(lazy);
            Intrinsics.checkNotNull(m1637_get_isScoped_$lambda1);
            if (Intrinsics.areEqual("expo", m1637_get_isScoped_$lambda1.getAppOwnership())) {
                return true;
            }
        }
        return false;
    }
}
