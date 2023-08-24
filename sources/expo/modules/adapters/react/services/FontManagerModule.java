package expo.modules.adapters.react.services;

import android.graphics.Typeface;
import com.facebook.react.views.text.ReactFontManager;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.interfaces.font.FontManagerInterface;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class FontManagerModule implements FontManagerInterface, InternalModule {
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
        return Collections.singletonList(FontManagerInterface.class);
    }

    @Override // expo.modules.interfaces.font.FontManagerInterface
    public void setTypeface(String str, int r3, Typeface typeface) {
        ReactFontManager.getInstance().setTypeface(str, r3, typeface);
    }
}
