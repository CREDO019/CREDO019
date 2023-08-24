package com.amplitude.analytics.connector;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: EventBridge.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00032\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bj\u0004\u0018\u0001`\tH&¨\u0006\n"}, m183d2 = {"Lcom/amplitude/analytics/connector/EventBridge;", "", "logEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amplitude/analytics/connector/AnalyticsEvent;", "setEventReceiver", "receiver", "Lkotlin/Function1;", "Lcom/amplitude/analytics/connector/AnalyticsEventReceiver;", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public interface EventBridge {
    void logEvent(AnalyticsEvent analyticsEvent);

    void setEventReceiver(Function1<? super AnalyticsEvent, Unit> function1);
}
