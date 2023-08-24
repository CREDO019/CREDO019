package com.amplitude.analytics.connector;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnalyticsConnector.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, m183d2 = {"Lcom/amplitude/analytics/connector/AnalyticsConnector;", "", "()V", "eventBridge", "Lcom/amplitude/analytics/connector/EventBridge;", "getEventBridge", "()Lcom/amplitude/analytics/connector/EventBridge;", "identityStore", "Lcom/amplitude/analytics/connector/IdentityStore;", "getIdentityStore", "()Lcom/amplitude/analytics/connector/IdentityStore;", "Companion", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class AnalyticsConnector {
    private final EventBridge eventBridge;
    private final IdentityStore identityStore;
    public static final Companion Companion = new Companion(null);
    private static final Object instancesLock = new Object();
    private static final Map<String, AnalyticsConnector> instances = new LinkedHashMap();

    public /* synthetic */ AnalyticsConnector(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final AnalyticsConnector getInstance(String str) {
        return Companion.getInstance(str);
    }

    private AnalyticsConnector() {
        this.identityStore = new IdentityStoreImpl();
        this.eventBridge = new EventBridgeImpl();
    }

    /* compiled from: AnalyticsConnector.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005H\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m183d2 = {"Lcom/amplitude/analytics/connector/AnalyticsConnector$Companion;", "", "()V", "instances", "", "", "Lcom/amplitude/analytics/connector/AnalyticsConnector;", "instancesLock", "getInstance", "instanceName", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AnalyticsConnector getInstance(String instanceName) {
            AnalyticsConnector analyticsConnector;
            Intrinsics.checkNotNullParameter(instanceName, "instanceName");
            synchronized (AnalyticsConnector.instancesLock) {
                Map map = AnalyticsConnector.instances;
                Object obj = map.get(instanceName);
                if (obj == null) {
                    obj = new AnalyticsConnector(null);
                    map.put(instanceName, obj);
                }
                analyticsConnector = (AnalyticsConnector) obj;
            }
            return analyticsConnector;
        }
    }

    public final IdentityStore getIdentityStore() {
        return this.identityStore;
    }

    public final EventBridge getEventBridge() {
        return this.eventBridge;
    }
}
