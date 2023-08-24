package expo.modules.kotlin.events;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: EventListener.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/events/EventListener;", "", "eventName", "Lexpo/modules/kotlin/events/EventName;", "(Lexpo/modules/kotlin/events/EventName;)V", "getEventName", "()Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/BasicEventListener;", "Lexpo/modules/kotlin/events/EventListenerWithPayload;", "Lexpo/modules/kotlin/events/EventListenerWithSenderAndPayload;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class EventListener {
    private final EventName eventName;

    public /* synthetic */ EventListener(EventName eventName, DefaultConstructorMarker defaultConstructorMarker) {
        this(eventName);
    }

    private EventListener(EventName eventName) {
        this.eventName = eventName;
    }

    public final EventName getEventName() {
        return this.eventName;
    }
}
