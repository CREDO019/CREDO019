package expo.modules.barcodescanner;

import android.content.Context;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.ExpoProp;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannerViewManager.kt */
@Metadata(m184d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001b\u001cB\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\"\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00022\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010\u0016H\u0007J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerViewManager;", "Lexpo/modules/core/ViewManager;", "Lexpo/modules/barcodescanner/BarCodeScannerView;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Lexpo/modules/core/ModuleRegistryDelegate;)V", "createViewInstance", "context", "Landroid/content/Context;", "getExportedEventNames", "", "", "getName", "getViewManagerType", "Lexpo/modules/core/ViewManager$ViewManagerType;", "onCreate", "", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "setBarCodeTypes", "view", "barCodeTypes", "Ljava/util/ArrayList;", "", "setType", SessionDescription.ATTR_TYPE, "", "Companion", "Events", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerViewManager extends ViewManager<BarCodeScannerView> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ExpoBarCodeScannerView";
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    public BarCodeScannerViewManager() {
        this(null, 1, null);
    }

    @Override // expo.modules.core.ViewManager
    public String getName() {
        return TAG;
    }

    public /* synthetic */ BarCodeScannerViewManager(ModuleRegistryDelegate moduleRegistryDelegate, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    public BarCodeScannerViewManager(ModuleRegistryDelegate moduleRegistryDelegate) {
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
    }

    /* compiled from: BarCodeScannerViewManager.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerViewManager$Events;", "", "mName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "EVENT_ON_BAR_CODE_SCANNED", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public enum Events {
        EVENT_ON_BAR_CODE_SCANNED("onBarCodeScanned");
        
        private final String mName;

        Events(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    @Override // expo.modules.core.ViewManager, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @Override // expo.modules.core.ViewManager
    public BarCodeScannerView createViewInstance(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BarCodeScannerView(context, this.moduleRegistryDelegate);
    }

    @Override // expo.modules.core.ViewManager
    public ViewManager.ViewManagerType getViewManagerType() {
        return ViewManager.ViewManagerType.GROUP;
    }

    @Override // expo.modules.core.ViewManager
    public List<String> getExportedEventNames() {
        Events[] values = Events.values();
        ArrayList arrayList = new ArrayList(values.length);
        int length = values.length;
        int r3 = 0;
        while (r3 < length) {
            Events events = values[r3];
            r3++;
            arrayList.add(events.toString());
        }
        return arrayList;
    }

    @ExpoProp(name = SessionDescription.ATTR_TYPE)
    public final void setType(BarCodeScannerView view, int r3) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setCameraType(r3);
    }

    @ExpoProp(name = "barCodeTypes")
    public final void setBarCodeTypes(BarCodeScannerView view, ArrayList<Double> arrayList) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (arrayList != null) {
            BarCodeScannerSettings barCodeScannerSettings = new BarCodeScannerSettings();
            barCodeScannerSettings.putTypes(arrayList);
            view.setBarCodeScannerSettings(barCodeScannerSettings);
        }
    }

    /* compiled from: BarCodeScannerViewManager.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannerViewManager$Companion;", "", "()V", "TAG", "", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
