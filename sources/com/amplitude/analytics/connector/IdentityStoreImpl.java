package com.amplitude.analytics.connector;

import com.amplitude.analytics.connector.IdentityStore;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityStore.kt */
@Metadata(m184d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u000e\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0013\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m183d2 = {"Lcom/amplitude/analytics/connector/IdentityStoreImpl;", "Lcom/amplitude/analytics/connector/IdentityStore;", "()V", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, "Lcom/amplitude/analytics/connector/Identity;", "identityLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "listeners", "", "Lkotlin/Function1;", "", "Lcom/amplitude/analytics/connector/IdentityListener;", "listenersLock", "", "addIdentityListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "editIdentity", "Lcom/amplitude/analytics/connector/IdentityStore$Editor;", "getIdentity", "removeIdentityListener", "setIdentity", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class IdentityStoreImpl implements IdentityStore {
    private final ReentrantReadWriteLock identityLock = new ReentrantReadWriteLock(true);
    private Identity identity = new Identity(null, null, null, 7, null);
    private final Object listenersLock = new Object();
    private final Set<Function1<Identity, Unit>> listeners = new LinkedHashSet();

    @Override // com.amplitude.analytics.connector.IdentityStore
    public IdentityStore.Editor editIdentity() {
        final Identity identity = getIdentity();
        return new IdentityStore.Editor(this) { // from class: com.amplitude.analytics.connector.IdentityStoreImpl$editIdentity$1
            private String deviceId;
            final /* synthetic */ IdentityStoreImpl this$0;
            private String userId;
            private Map<String, ? extends Object> userProperties;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.this$0 = this;
                this.userId = Identity.this.getUserId();
                this.deviceId = Identity.this.getDeviceId();
                this.userProperties = Identity.this.getUserProperties();
            }

            @Override // com.amplitude.analytics.connector.IdentityStore.Editor
            public IdentityStore.Editor setUserId(String str) {
                this.userId = str;
                return this;
            }

            @Override // com.amplitude.analytics.connector.IdentityStore.Editor
            public IdentityStore.Editor setDeviceId(String str) {
                this.deviceId = str;
                return this;
            }

            @Override // com.amplitude.analytics.connector.IdentityStore.Editor
            public IdentityStore.Editor setUserProperties(Map<String, ? extends Object> userProperties) {
                Intrinsics.checkNotNullParameter(userProperties, "userProperties");
                this.userProperties = userProperties;
                return this;
            }

            @Override // com.amplitude.analytics.connector.IdentityStore.Editor
            public IdentityStore.Editor updateUserProperties(Map<String, ? extends Map<String, ? extends Object>> actions) {
                Intrinsics.checkNotNullParameter(actions, "actions");
                Map<String, ? extends Object> mutableMap = MapsKt.toMutableMap(this.userProperties);
                for (Map.Entry<String, ? extends Map<String, ? extends Object>> entry : actions.entrySet()) {
                    String key = entry.getKey();
                    Map<String, ? extends Object> value = entry.getValue();
                    int hashCode = key.hashCode();
                    if (hashCode != 1186238) {
                        if (hashCode != 146417720) {
                            if (hashCode == 1142092165 && key.equals("$unset")) {
                                for (Map.Entry<String, ? extends Object> entry2 : value.entrySet()) {
                                    mutableMap.remove(entry2.getKey());
                                }
                            }
                        } else if (key.equals("$clearAll")) {
                            mutableMap.clear();
                        }
                    } else if (key.equals("$set")) {
                        mutableMap.putAll(value);
                    }
                }
                this.userProperties = mutableMap;
                return this;
            }

            @Override // com.amplitude.analytics.connector.IdentityStore.Editor
            public void commit() {
                this.this$0.setIdentity(new Identity(this.userId, this.deviceId, this.userProperties));
            }
        };
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.amplitude.analytics.connector.IdentityStore
    public void setIdentity(Identity identity) {
        Set<Function1> set;
        Intrinsics.checkNotNullParameter(identity, "identity");
        Identity identity2 = getIdentity();
        ReentrantReadWriteLock reentrantReadWriteLock = this.identityLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int r4 = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int r5 = 0; r5 < readHoldCount; r5++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            this.identity = identity;
            Unit unit = Unit.INSTANCE;
            while (r4 < readHoldCount) {
                readLock.lock();
                r4++;
            }
            writeLock.unlock();
            if (Intrinsics.areEqual(identity, identity2)) {
                return;
            }
            synchronized (this.listenersLock) {
                set = CollectionsKt.toSet(this.listeners);
            }
            for (Function1 function1 : set) {
                function1.invoke(identity);
            }
        } catch (Throwable th) {
            while (r4 < readHoldCount) {
                readLock.lock();
                r4++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    @Override // com.amplitude.analytics.connector.IdentityStore
    public Identity getIdentity() {
        ReentrantReadWriteLock.ReadLock readLock = this.identityLock.readLock();
        readLock.lock();
        try {
            return this.identity;
        } finally {
            readLock.unlock();
        }
    }

    @Override // com.amplitude.analytics.connector.IdentityStore
    public void addIdentityListener(Function1<? super Identity, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.listenersLock) {
            this.listeners.add(listener);
        }
    }

    @Override // com.amplitude.analytics.connector.IdentityStore
    public void removeIdentityListener(Function1<? super Identity, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.listenersLock) {
            this.listeners.remove(listener);
        }
    }
}
