package expo.modules.core.interfaces.services;

import android.os.Bundle;

/* loaded from: classes4.dex */
public interface EventEmitter {

    /* loaded from: classes4.dex */
    public static abstract class BaseEvent implements Event {
        @Override // expo.modules.core.interfaces.services.EventEmitter.Event
        public boolean canCoalesce() {
            return true;
        }

        @Override // expo.modules.core.interfaces.services.EventEmitter.Event
        public short getCoalescingKey() {
            return (short) 0;
        }
    }

    /* loaded from: classes4.dex */
    public interface Event {
        boolean canCoalesce();

        short getCoalescingKey();

        Bundle getEventBody();

        String getEventName();
    }

    void emit(int r1, Event event);

    void emit(int r1, String str, Bundle bundle);

    void emit(String str, Bundle bundle);
}
