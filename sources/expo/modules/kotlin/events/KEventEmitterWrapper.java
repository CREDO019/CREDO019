package expo.modules.kotlin.events;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.types.JSTypeConverter;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KModuleEventEmitterWrapper.kt */
@Metadata(m184d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001dB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J!\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J1\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0002\u0010\u0019J1\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00140\u00142\u000e\u0010\u0015\u001a\n \u0012*\u0004\u0018\u00010\u001a0\u001aH\u0096\u0001J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u001bH\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u001cH\u0016J)\u0010\f\u001a\u00020\r2\u000e\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00140\u00142\u000e\u0010\u0015\u001a\n \u0012*\u0004\u0018\u00010\u001a0\u001aH\u0096\u0001R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/kotlin/events/KEventEmitterWrapper;", "Lexpo/modules/kotlin/events/EventEmitter;", "Lexpo/modules/core/interfaces/services/EventEmitter;", "legacyEventEmitter", "reactContextHolder", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lexpo/modules/core/interfaces/services/EventEmitter;Ljava/lang/ref/WeakReference;)V", "deviceEventEmitter", "Lcom/facebook/react/modules/core/DeviceEventManagerModule$RCTDeviceEventEmitter;", "getDeviceEventEmitter", "()Lcom/facebook/react/modules/core/DeviceEventManagerModule$RCTDeviceEventEmitter;", "emit", "", "viewId", "", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/core/interfaces/services/EventEmitter$Event;", "kotlin.jvm.PlatformType", "eventName", "", "eventBody", "Lcom/facebook/react/bridge/WritableMap;", "coalescingKey", "", "(ILjava/lang/String;Lcom/facebook/react/bridge/WritableMap;Ljava/lang/Short;)V", "Landroid/os/Bundle;", "Lexpo/modules/kotlin/records/Record;", "", "UIEvent", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class KEventEmitterWrapper implements EventEmitter, expo.modules.core.interfaces.services.EventEmitter {
    private final expo.modules.core.interfaces.services.EventEmitter legacyEventEmitter;
    private final WeakReference<ReactApplicationContext> reactContextHolder;

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(int r2, EventEmitter.Event event) {
        this.legacyEventEmitter.emit(r2, event);
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(int r2, String str, Bundle bundle) {
        this.legacyEventEmitter.emit(r2, str, bundle);
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(String str, Bundle bundle) {
        this.legacyEventEmitter.emit(str, bundle);
    }

    public KEventEmitterWrapper(expo.modules.core.interfaces.services.EventEmitter legacyEventEmitter, WeakReference<ReactApplicationContext> reactContextHolder) {
        Intrinsics.checkNotNullParameter(legacyEventEmitter, "legacyEventEmitter");
        Intrinsics.checkNotNullParameter(reactContextHolder, "reactContextHolder");
        this.legacyEventEmitter = legacyEventEmitter;
        this.reactContextHolder = reactContextHolder;
    }

    private final DeviceEventManagerModule.RCTDeviceEventEmitter getDeviceEventEmitter() {
        ReactApplicationContext reactApplicationContext = this.reactContextHolder.get();
        if (reactApplicationContext == null) {
            return null;
        }
        return (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    @Override // expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter = getDeviceEventEmitter();
        if (deviceEventEmitter == null) {
            return;
        }
        deviceEventEmitter.emit(eventName, writableMap);
    }

    @Override // expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, Record record) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter = getDeviceEventEmitter();
        if (deviceEventEmitter == null) {
            return;
        }
        deviceEventEmitter.emit(eventName, JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, record, null, 2, null));
    }

    @Override // expo.modules.kotlin.events.EventEmitter
    public void emit(String eventName, Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        DeviceEventManagerModule.RCTDeviceEventEmitter deviceEventEmitter = getDeviceEventEmitter();
        if (deviceEventEmitter == null) {
            return;
        }
        deviceEventEmitter.emit(eventName, JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, map, null, 2, null));
    }

    @Override // expo.modules.kotlin.events.EventEmitter
    public void emit(int r3, String eventName, WritableMap writableMap, Short sh) {
        EventDispatcher eventDispatcherForReactTag;
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        ReactApplicationContext reactApplicationContext = this.reactContextHolder.get();
        if (reactApplicationContext == null || (eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactApplicationContext, r3)) == null) {
            return;
        }
        eventDispatcherForReactTag.dispatchEvent(new UIEvent(r3, eventName, writableMap, sh));
    }

    /* compiled from: KModuleEventEmitterWrapper.kt */
    @Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0014J\b\u0010\u0010\u001a\u00020\u0005H\u0016R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/events/KEventEmitterWrapper$UIEvent;", "Lcom/facebook/react/uimanager/events/Event;", "viewId", "", "eventName", "", "eventBody", "Lcom/facebook/react/bridge/WritableMap;", "coalescingKey", "", "(ILjava/lang/String;Lcom/facebook/react/bridge/WritableMap;Ljava/lang/Short;)V", "Ljava/lang/Short;", "canCoalesce", "", "getCoalescingKey", "getEventData", "getEventName", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private static final class UIEvent extends Event<UIEvent> {
        private final Short coalescingKey;
        private final WritableMap eventBody;
        private final String eventName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UIEvent(int r2, String eventName, WritableMap writableMap, Short sh) {
            super(r2);
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            this.eventName = eventName;
            this.eventBody = writableMap;
            this.coalescingKey = sh;
        }

        @Override // com.facebook.react.uimanager.events.Event
        public String getEventName() {
            return KModuleEventEmitterWrapperKt.normalizeEventName(this.eventName);
        }

        @Override // com.facebook.react.uimanager.events.Event
        public boolean canCoalesce() {
            return this.coalescingKey != null;
        }

        @Override // com.facebook.react.uimanager.events.Event
        public short getCoalescingKey() {
            Short sh = this.coalescingKey;
            if (sh == null) {
                return (short) 0;
            }
            return sh.shortValue();
        }

        @Override // com.facebook.react.uimanager.events.Event
        protected WritableMap getEventData() {
            WritableMap writableMap = this.eventBody;
            if (writableMap == null) {
                WritableMap createMap = Arguments.createMap();
                Intrinsics.checkNotNullExpressionValue(createMap, "createMap()");
                return createMap;
            }
            return writableMap;
        }
    }
}
