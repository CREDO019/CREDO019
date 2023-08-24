package expo.modules.adapters.react.services;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import expo.modules.adapters.react.views.ViewManagerAdapterUtils;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.EventEmitter;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class EventEmitterModule implements EventEmitter, InternalModule {
    private ReactContext mReactContext;

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    public EventEmitterModule(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(String str, Bundle bundle) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, Arguments.fromBundle(bundle));
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(int r2, EventEmitter.Event event) {
        UIManagerHelper.getEventDispatcherForReactTag(this.mReactContext, r2).dispatchEvent(getReactEventFromEvent(r2, event));
    }

    @Override // expo.modules.core.interfaces.services.EventEmitter
    public void emit(final int r9, final String str, final Bundle bundle) {
        UIManagerHelper.getEventDispatcherForReactTag(this.mReactContext, r9).dispatchEvent(new Event(r9) { // from class: expo.modules.adapters.react.services.EventEmitterModule.1
            @Override // com.facebook.react.uimanager.events.Event
            public boolean canCoalesce() {
                return false;
            }

            @Override // com.facebook.react.uimanager.events.Event
            public short getCoalescingKey() {
                return (short) 0;
            }

            @Override // com.facebook.react.uimanager.events.Event
            public String getEventName() {
                return ViewManagerAdapterUtils.normalizeEventName(str);
            }

            @Override // com.facebook.react.uimanager.events.Event
            public void dispatch(RCTEventEmitter rCTEventEmitter) {
                int r0 = r9;
                String eventName = getEventName();
                Bundle bundle2 = bundle;
                rCTEventEmitter.receiveEvent(r0, eventName, bundle2 != null ? Arguments.fromBundle(bundle2) : null);
            }
        });
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class> getExportedInterfaces() {
        return Collections.singletonList(EventEmitter.class);
    }

    private static Event getReactEventFromEvent(final int r1, final EventEmitter.Event event) {
        return new Event(r1) { // from class: expo.modules.adapters.react.services.EventEmitterModule.2
            @Override // com.facebook.react.uimanager.events.Event
            public String getEventName() {
                return ViewManagerAdapterUtils.normalizeEventName(event.getEventName());
            }

            @Override // com.facebook.react.uimanager.events.Event
            public void dispatch(RCTEventEmitter rCTEventEmitter) {
                rCTEventEmitter.receiveEvent(r1, getEventName(), Arguments.fromBundle(event.getEventBody()));
            }

            @Override // com.facebook.react.uimanager.events.Event
            public boolean canCoalesce() {
                return event.canCoalesce();
            }

            @Override // com.facebook.react.uimanager.events.Event
            public short getCoalescingKey() {
                return event.getCoalescingKey();
            }
        };
    }
}
