package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ForwardingRequestListener implements RequestListener {
    private static final String TAG = "ForwardingRequestListener";
    private final List<RequestListener> mRequestListeners;

    public ForwardingRequestListener(Set<RequestListener> requestListeners) {
        this.mRequestListeners = new ArrayList(requestListeners.size());
        for (RequestListener requestListener : requestListeners) {
            if (requestListener != null) {
                this.mRequestListeners.add(requestListener);
            }
        }
    }

    public ForwardingRequestListener(RequestListener... requestListeners) {
        this.mRequestListeners = new ArrayList(requestListeners.length);
        for (RequestListener requestListener : requestListeners) {
            if (requestListener != null) {
                this.mRequestListeners.add(requestListener);
            }
        }
    }

    public void addRequestListener(RequestListener requestListener) {
        this.mRequestListeners.add(requestListener);
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestStart(request, callerContext, requestId, isPrefetch);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerStart(String requestId, String producerName) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerStart(requestId, producerName);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithSuccess(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithSuccess(requestId, producerName, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable t, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithFailure(requestId, producerName, t, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithCancellation(String requestId, String producerName, @Nullable Map<String, String> extraMap) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerFinishWithCancellation(requestId, producerName, extraMap);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerEvent(String requestId, String producerName, String producerEventName) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onProducerEvent(requestId, producerName, producerEventName);
            } catch (Exception e) {
                onException("InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public void onUltimateProducerReached(String requestId, String producerName, boolean successful) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onUltimateProducerReached(requestId, producerName, successful);
            } catch (Exception e) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestSuccess(request, requestId, isPrefetch);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestFailure(request, requestId, throwable, isPrefetch);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestFailure", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String requestId) {
        int size = this.mRequestListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                this.mRequestListeners.get(r1).onRequestCancellation(requestId);
            } catch (Exception e) {
                onException("InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public boolean requiresExtraMap(String id) {
        int size = this.mRequestListeners.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (this.mRequestListeners.get(r2).requiresExtraMap(id)) {
                return true;
            }
        }
        return false;
    }

    private void onException(String message, Throwable t) {
        FLog.m1327e(TAG, message, t);
    }
}
