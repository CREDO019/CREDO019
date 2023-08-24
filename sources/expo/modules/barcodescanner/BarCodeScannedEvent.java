package expo.modules.barcodescanner;

import android.os.Bundle;
import androidx.core.util.Pools;
import com.facebook.react.uimanager.events.TouchesHelper;
import expo.modules.barcodescanner.BarCodeScannerViewManager;
import expo.modules.barcodescanner.utils.BarCodeScannerResultSerializer;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannedEvent.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannedEvent;", "Lexpo/modules/core/interfaces/services/EventEmitter$BaseEvent;", "viewTag", "", "barCode", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "density", "", "(ILexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;F)V", "getCoalescingKey", "", "getEventBody", "Landroid/os/Bundle;", "getEventName", "", "Companion", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannedEvent extends EventEmitter.BaseEvent {
    public static final Companion Companion = new Companion(null);
    private static final Pools.SynchronizedPool<BarCodeScannedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private BarCodeScannerResult barCode;
    private float density;
    private int viewTag;

    public BarCodeScannedEvent(int r2, BarCodeScannerResult barCode, float f) {
        Intrinsics.checkNotNullParameter(barCode, "barCode");
        this.viewTag = r2;
        this.barCode = barCode;
        this.density = f;
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter.BaseEvent, expo.modules.core.interfaces.services.EventEmitter.Event
    public short getCoalescingKey() {
        return (short) (this.barCode.getValue().hashCode() % 32767);
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter.Event
    public String getEventName() {
        return BarCodeScannerViewManager.Events.EVENT_ON_BAR_CODE_SCANNED.toString();
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter.Event
    public Bundle getEventBody() {
        Bundle bundle = BarCodeScannerResultSerializer.INSTANCE.toBundle(this.barCode, this.density);
        bundle.putInt(TouchesHelper.TARGET_KEY, this.viewTag);
        return bundle;
    }

    /* compiled from: BarCodeScannedEvent.kt */
    @Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lexpo/modules/barcodescanner/BarCodeScannedEvent$Companion;", "", "()V", "EVENTS_POOL", "Landroidx/core/util/Pools$SynchronizedPool;", "Lexpo/modules/barcodescanner/BarCodeScannedEvent;", "obtain", "viewTag", "", "barCode", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "density", "", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarCodeScannedEvent obtain(int r2, BarCodeScannerResult barCode, float f) {
            Intrinsics.checkNotNullParameter(barCode, "barCode");
            BarCodeScannedEvent barCodeScannedEvent = (BarCodeScannedEvent) BarCodeScannedEvent.EVENTS_POOL.acquire();
            return barCodeScannedEvent == null ? new BarCodeScannedEvent(r2, barCode, f) : barCodeScannedEvent;
        }
    }
}
