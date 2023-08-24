package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.p026ws.WebSocketProtocol;

/* loaded from: classes.dex */
public class EventDispatcherImpl implements EventDispatcher, LifecycleEventListener {
    private static final Comparator<Event> EVENT_COMPARATOR = new Comparator<Event>() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl.1
        @Override // java.util.Comparator
        public int compare(Event event, Event event2) {
            if (event == null && event2 == null) {
                return 0;
            }
            if (event == null) {
                return -1;
            }
            if (event2 == null) {
                return 1;
            }
            int r5 = ((event.getTimestampMs() - event2.getTimestampMs()) > 0L ? 1 : ((event.getTimestampMs() - event2.getTimestampMs()) == 0L ? 0 : -1));
            if (r5 == 0) {
                return 0;
            }
            return r5 < 0 ? -1 : 1;
        }
    };
    private final ReactApplicationContext mReactContext;
    private volatile ReactEventEmitter mReactEventEmitter;
    private final Object mEventsStagingLock = new Object();
    private final Object mEventsToDispatchLock = new Object();
    private final LongSparseArray<Integer> mEventCookieToLastEventIdx = new LongSparseArray<>();
    private final Map<String, Short> mEventNameToEventId = MapBuilder.newHashMap();
    private final DispatchEventsRunnable mDispatchEventsRunnable = new DispatchEventsRunnable();
    private final ArrayList<Event> mEventStaging = new ArrayList<>();
    private final CopyOnWriteArrayList<EventDispatcherListener> mListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> mPostEventDispatchListeners = new CopyOnWriteArrayList<>();
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback = new ScheduleDispatchFrameCallback();
    private final AtomicInteger mHasDispatchScheduledCount = new AtomicInteger();
    private Event[] mEventsToDispatch = new Event[16];
    private int mEventsToDispatchSize = 0;
    private short mNextEventTypeId = 0;
    private volatile boolean mHasDispatchScheduled = false;

    private static long getEventCookie(int r5, short s, short s2) {
        return ((s & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | r5 | ((s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48);
    }

    public EventDispatcherImpl(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            Systrace.startAsyncFlow(0L, event.getEventName(), event.getUniqueID());
        }
        maybePostFrameCallbackFromNonUI();
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
        maybePostFrameCallbackFromNonUI();
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
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl.2
            @Override // java.lang.Runnable
            public void run() {
                EventDispatcherImpl.this.stopFrameCallback();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveStagedEventsToDispatchQueue() {
        synchronized (this.mEventsStagingLock) {
            synchronized (this.mEventsToDispatchLock) {
                for (int r2 = 0; r2 < this.mEventStaging.size(); r2++) {
                    Event event = this.mEventStaging.get(r2);
                    if (!event.canCoalesce()) {
                        addEventToEventsToDispatch(event);
                    } else {
                        long eventCookie = getEventCookie(event.getViewTag(), event.getEventName(), event.getCoalescingKey());
                        Integer num = this.mEventCookieToLastEventIdx.get(eventCookie);
                        Event event2 = null;
                        if (num == null) {
                            this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                        } else {
                            Event event3 = this.mEventsToDispatch[num.intValue()];
                            Event coalesce = event.coalesce(event3);
                            if (coalesce != event3) {
                                this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                                this.mEventsToDispatch[num.intValue()] = null;
                                event2 = event3;
                                event = coalesce;
                            } else {
                                event2 = event;
                                event = null;
                            }
                        }
                        if (event != null) {
                            addEventToEventsToDispatch(event);
                        }
                        if (event2 != null) {
                            event2.dispose();
                        }
                    }
                }
            }
            this.mEventStaging.clear();
        }
    }

    private long getEventCookie(int r4, String str, short s) {
        short s2;
        Short sh = this.mEventNameToEventId.get(str);
        if (sh != null) {
            s2 = sh.shortValue();
        } else {
            short s3 = this.mNextEventTypeId;
            this.mNextEventTypeId = (short) (s3 + 1);
            this.mEventNameToEventId.put(str, Short.valueOf(s3));
            s2 = s3;
        }
        return getEventCookie(r4, s2, s);
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
            Systrace.beginSection(0L, "ScheduleDispatchFrameCallback");
            try {
                EventDispatcherImpl.this.moveStagedEventsToDispatchQueue();
                if (!EventDispatcherImpl.this.mHasDispatchScheduled) {
                    EventDispatcherImpl.this.mHasDispatchScheduled = true;
                    Systrace.startAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcherImpl.this.mHasDispatchScheduledCount.get());
                    EventDispatcherImpl.this.mReactContext.runOnJSQueueThread(EventDispatcherImpl.this.mDispatchEventsRunnable);
                }
            } finally {
                Systrace.endSection(0L);
            }
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
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, EventDispatcherImpl.this.mCurrentFrameCallback);
        }

        public void maybePostFromNonUI() {
            if (this.mIsPosted) {
                return;
            }
            if (!EventDispatcherImpl.this.mReactContext.isOnUiQueueThread()) {
                EventDispatcherImpl.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl.ScheduleDispatchFrameCallback.1
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

    /* loaded from: classes.dex */
    private class DispatchEventsRunnable implements Runnable {
        private DispatchEventsRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Systrace.beginSection(0L, "DispatchEventsRunnable");
            try {
                Systrace.endAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcherImpl.this.mHasDispatchScheduledCount.getAndIncrement());
                EventDispatcherImpl.this.mHasDispatchScheduled = false;
                Assertions.assertNotNull(EventDispatcherImpl.this.mReactEventEmitter);
                synchronized (EventDispatcherImpl.this.mEventsToDispatchLock) {
                    if (EventDispatcherImpl.this.mEventsToDispatchSize > 0) {
                        if (EventDispatcherImpl.this.mEventsToDispatchSize > 1) {
                            Arrays.sort(EventDispatcherImpl.this.mEventsToDispatch, 0, EventDispatcherImpl.this.mEventsToDispatchSize, EventDispatcherImpl.EVENT_COMPARATOR);
                        }
                        for (int r3 = 0; r3 < EventDispatcherImpl.this.mEventsToDispatchSize; r3++) {
                            Event event = EventDispatcherImpl.this.mEventsToDispatch[r3];
                            if (event != null) {
                                Systrace.endAsyncFlow(0L, event.getEventName(), event.getUniqueID());
                                event.dispatchModern(EventDispatcherImpl.this.mReactEventEmitter);
                                event.dispose();
                            }
                        }
                        EventDispatcherImpl.this.clearEventsToDispatch();
                        EventDispatcherImpl.this.mEventCookieToLastEventIdx.clear();
                    }
                }
                Iterator it = EventDispatcherImpl.this.mPostEventDispatchListeners.iterator();
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    private void addEventToEventsToDispatch(Event event) {
        int r0 = this.mEventsToDispatchSize;
        Event[] eventArr = this.mEventsToDispatch;
        if (r0 == eventArr.length) {
            this.mEventsToDispatch = (Event[]) Arrays.copyOf(eventArr, eventArr.length * 2);
        }
        Event[] eventArr2 = this.mEventsToDispatch;
        int r1 = this.mEventsToDispatchSize;
        this.mEventsToDispatchSize = r1 + 1;
        eventArr2[r1] = event;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEventsToDispatch() {
        Arrays.fill(this.mEventsToDispatch, 0, this.mEventsToDispatchSize, (Object) null);
        this.mEventsToDispatchSize = 0;
    }
}
