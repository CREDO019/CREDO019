package com.polidea.multiplatformbleadapter.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p042rx.Subscription;

/* loaded from: classes3.dex */
public class DisposableMap {
    private final Map<String, Subscription> subscriptions = new HashMap();

    public synchronized void replaceSubscription(String str, Subscription subscription) {
        Subscription put = this.subscriptions.put(str, subscription);
        if (put != null && !put.isUnsubscribed()) {
            put.unsubscribe();
        }
    }

    public synchronized boolean removeSubscription(String str) {
        Subscription remove = this.subscriptions.remove(str);
        if (remove == null) {
            return false;
        }
        if (!remove.isUnsubscribed()) {
            remove.unsubscribe();
        }
        return true;
    }

    public synchronized void removeAllSubscriptions() {
        Iterator<Map.Entry<String, Subscription>> it = this.subscriptions.entrySet().iterator();
        while (it.hasNext()) {
            Subscription value = it.next().getValue();
            it.remove();
            if (!value.isUnsubscribed()) {
                value.unsubscribe();
            }
        }
    }
}
