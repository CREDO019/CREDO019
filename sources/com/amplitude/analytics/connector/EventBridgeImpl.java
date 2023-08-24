package com.amplitude.analytics.connector;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventBridge.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0007H\u0016J$\u0010\u000e\u001a\u00020\n2\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0004\u0018\u0001`\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0004\u0018\u0001`\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m183d2 = {"Lcom/amplitude/analytics/connector/EventBridgeImpl;", "Lcom/amplitude/analytics/connector/EventBridge;", "()V", "lock", "", "queue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lcom/amplitude/analytics/connector/AnalyticsEvent;", "receiver", "Lkotlin/Function1;", "", "Lcom/amplitude/analytics/connector/AnalyticsEventReceiver;", "logEvent", NotificationCompat.CATEGORY_EVENT, "setEventReceiver", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class EventBridgeImpl implements EventBridge {
    private final Object lock = new Object();
    private final ArrayBlockingQueue<AnalyticsEvent> queue = new ArrayBlockingQueue<>(512);
    private Function1<? super AnalyticsEvent, Unit> receiver;

    @Override // com.amplitude.analytics.connector.EventBridge
    public void logEvent(AnalyticsEvent event) {
        Function1<? super AnalyticsEvent, Unit> function1;
        Intrinsics.checkNotNullParameter(event, "event");
        synchronized (this.lock) {
            if (this.receiver == null) {
                this.queue.offer(event);
            }
            function1 = this.receiver;
        }
        if (function1 == null) {
            return;
        }
        function1.invoke(event);
    }

    @Override // com.amplitude.analytics.connector.EventBridge
    public void setEventReceiver(Function1<? super AnalyticsEvent, Unit> function1) {
        ArrayList<AnalyticsEvent> arrayList;
        synchronized (this.lock) {
            this.receiver = function1;
            arrayList = new ArrayList();
            this.queue.drainTo(arrayList);
        }
        for (AnalyticsEvent analyticsEvent : arrayList) {
            if (function1 != null) {
                function1.invoke(analyticsEvent);
            }
        }
    }
}
