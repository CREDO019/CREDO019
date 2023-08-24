package com.facebook.react.uimanager.events;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class LockFreeEventDispatcherImpl implements EventDispatcher, LifecycleEventListener {
    private final ReactApplicationContext mReactContext;
    private volatile ReactEventEmitter mReactEventEmitter;
    private final boolean DEBUG_MODE = false;
    private final String TAG = "LockFreeEventDispatcherImpl";
    private final CopyOnWriteArrayList<EventDispatcherListener> mListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> mPostEventDispatchListeners = new CopyOnWriteArrayList<>();
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback = new ScheduleDispatchFrameCallback();

    public LockFreeEventDispatcherImpl(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        Assertions.assertNotNull(this.mReactEventEmitter);
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        event.dispatchModern(this.mReactEventEmitter);
        event.dispose();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
        maybePostFrameCallbackFromNonUI();
    }

    private void maybePostFrameCallbackFromNonUI() {
        if (this.mReactEventEmitter != null) {
            this.mCurrentFrameCallback.maybePostFromNonUI();
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.add(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.remove(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.add(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.remove(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.resume();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.LockFreeEventDispatcherImpl.1
            @Override // java.lang.Runnable
            public void run() {
                LockFreeEventDispatcherImpl.this.stopFrameCallback();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int r2, RCTEventEmitter rCTEventEmitter) {
        this.mReactEventEmitter.register(r2, rCTEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int r2, RCTModernEventEmitter rCTModernEventEmitter) {
        this.mReactEventEmitter.register(r2, rCTModernEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void unregisterEventEmitter(int r2) {
        this.mReactEventEmitter.unregister(r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ScheduleDispatchFrameCallback extends ChoreographerCompat.FrameCallback {
        private volatile boolean mIsPosted;
        private boolean mShouldStop;

        private ScheduleDispatchFrameCallback() {
            this.mIsPosted = false;
            this.mShouldStop = false;
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                post();
            }
            LockFreeEventDispatcherImpl.this.driveEventBeats();
        }

        public void resume() {
            this.mShouldStop = false;
            maybePost();
        }

        public void stop() {
            this.mShouldStop = true;
        }

        public void maybePost() {
            if (this.mIsPosted) {
                return;
            }
            this.mIsPosted = true;
            post();
        }

        private void post() {
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, LockFreeEventDispatcherImpl.this.mCurrentFrameCallback);
        }

        public void maybePostFromNonUI() {
            if (this.mIsPosted) {
                return;
            }
            if (!LockFreeEventDispatcherImpl.this.mReactContext.isOnUiQueueThread()) {
                LockFreeEventDispatcherImpl.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.LockFreeEventDispatcherImpl.ScheduleDispatchFrameCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScheduleDispatchFrameCallback.this.maybePost();
                    }
                });
            } else {
                maybePost();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void driveEventBeats() {
        Iterator<BatchEventDispatchedListener> it = this.mPostEventDispatchListeners.iterator();
        while (it.hasNext()) {
            it.next().onBatchEventDispatched();
        }
    }
}
