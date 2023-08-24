package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ForwardingRequestListener2 implements RequestListener2 {
    private static final String TAG = "ForwardingRequestListener2";
    private final List<RequestListener2> mRequestListeners;

    public ForwardingRequestListener2(Set<RequestListener2> requestListeners) {
        this.mRequestListeners = new ArrayList(requestListeners.size());
        for (RequestListener2 requestListener2 : requestListeners) {
            if (requestListener2 != null) {
                this.mRequestListeners.add(requestListener2);
            }
        }
    }

    public ForwardingRequestListener2(RequestListener2... requestListeners) {
        this.mRequestListeners = new ArrayList(requestListeners.length);
        for (RequestListener2 requestListener2 : requestListeners) {
            if (requestListener2 != null) {
                this.mRequestListeners.add(requestListener2);
            }
        }
    }

    public void addRequestListener(RequestListener2 requestListener) {
        this.mRequestListeners.add(requestListener);
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestStart(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestStart(producerContext);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(ProducerContext producerContext, String producerName) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerStart(producerContext, producerName);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(ProducerContext producerContext, String producerName, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithSuccess(producerContext, producerName, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(ProducerContext producerContext, String producerName, Throwable t, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithFailure(producerContext, producerName, t, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(ProducerContext producerContext, String producerName, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithCancellation(producerContext, producerName, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(ProducerContext producerContext, String producerName, String producerEventName) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerEvent(producerContext, producerName, producerEventName);
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(ProducerContext producerContext, String producerName, boolean successful) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onUltimateProducerReached(producerContext, producerName, successful);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestSuccess(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestSuccess(producerContext);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestFailure(ProducerContext producerContext, Throwable throwable) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestFailure(producerContext, throwable);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestCancellation(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestCancellation(producerContext);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(ProducerContext producerContext, String producerName) {
        int size = this.mRequestListeners.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (this.mRequestListeners.get(r2).requiresExtraMap(producerContext, producerName)) {
                return true;
            }
        }
        return false;
    }

    private void onException(String message, Throwable t) {
        FLog.m1327e(TAG, message, t);
    }
}
