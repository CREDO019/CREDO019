package com.google.android.exoplayer2;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Pair;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class Timeline implements Bundleable {
    private static final int FIELD_PERIODS = 1;
    private static final int FIELD_SHUFFLED_WINDOW_INDICES = 2;
    private static final int FIELD_WINDOWS = 0;
    public static final Timeline EMPTY = new Timeline() { // from class: com.google.android.exoplayer2.Timeline.1
        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Window getWindow(int r1, Window window, long j) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Period getPeriod(int r1, Period period, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int r1) {
            throw new IndexOutOfBoundsException();
        }
    };
    public static final Bundleable.Creator<Timeline> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.Timeline$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            Timeline fromBundle;
            fromBundle = Timeline.fromBundle(bundle);
            return fromBundle;
        }
    };

    public abstract int getIndexOfPeriod(Object obj);

    public abstract Period getPeriod(int r1, Period period, boolean z);

    public abstract int getPeriodCount();

    public abstract Object getUidOfPeriod(int r1);

    public abstract Window getWindow(int r1, Window window, long j);

    public abstract int getWindowCount();

    /* loaded from: classes2.dex */
    public static final class Window implements Bundleable {
        private static final int FIELD_DEFAULT_POSITION_US = 9;
        private static final int FIELD_DURATION_US = 10;
        private static final int FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = 4;
        private static final int FIELD_FIRST_PERIOD_INDEX = 11;
        private static final int FIELD_IS_DYNAMIC = 6;
        private static final int FIELD_IS_PLACEHOLDER = 8;
        private static final int FIELD_IS_SEEKABLE = 5;
        private static final int FIELD_LAST_PERIOD_INDEX = 12;
        private static final int FIELD_LIVE_CONFIGURATION = 7;
        private static final int FIELD_MEDIA_ITEM = 1;
        private static final int FIELD_POSITION_IN_FIRST_PERIOD_US = 13;
        private static final int FIELD_PRESENTATION_START_TIME_MS = 2;
        private static final int FIELD_WINDOW_START_TIME_MS = 3;
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        @Deprecated
        public boolean isLive;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public MediaItem.LiveConfiguration liveConfiguration;
        public Object manifest;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        @Deprecated
        public Object tag;
        public long windowStartTimeMs;
        public static final Object SINGLE_WINDOW_UID = new Object();
        private static final Object FAKE_WINDOW_UID = new Object();
        private static final MediaItem EMPTY_MEDIA_ITEM = new MediaItem.Builder().setMediaId("com.google.android.exoplayer2.Timeline").setUri(Uri.EMPTY).build();
        public static final Bundleable.Creator<Window> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.Timeline$Window$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.Bundleable.Creator
            public final Bundleable fromBundle(Bundle bundle) {
                Timeline.Window fromBundle;
                fromBundle = Timeline.Window.fromBundle(bundle);
                return fromBundle;
            }
        };
        public Object uid = SINGLE_WINDOW_UID;
        public MediaItem mediaItem = EMPTY_MEDIA_ITEM;

        public Window set(Object obj, MediaItem mediaItem, Object obj2, long j, long j2, long j3, boolean z, boolean z2, MediaItem.LiveConfiguration liveConfiguration, long j4, long j5, int r22, int r23, long j6) {
            this.uid = obj;
            this.mediaItem = mediaItem != null ? mediaItem : EMPTY_MEDIA_ITEM;
            this.tag = (mediaItem == null || mediaItem.localConfiguration == null) ? null : mediaItem.localConfiguration.tag;
            this.manifest = obj2;
            this.presentationStartTimeMs = j;
            this.windowStartTimeMs = j2;
            this.elapsedRealtimeEpochOffsetMs = j3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.isLive = liveConfiguration != null;
            this.liveConfiguration = liveConfiguration;
            this.defaultPositionUs = j4;
            this.durationUs = j5;
            this.firstPeriodIndex = r22;
            this.lastPeriodIndex = r23;
            this.positionInFirstPeriodUs = j6;
            this.isPlaceholder = false;
            return this;
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return Util.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public boolean isLive() {
            Assertions.checkState(this.isLive == (this.liveConfiguration != null));
            return this.liveConfiguration != null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !getClass().equals(obj.getClass())) {
                return false;
            }
            Window window = (Window) obj;
            return Util.areEqual(this.uid, window.uid) && Util.areEqual(this.mediaItem, window.mediaItem) && Util.areEqual(this.manifest, window.manifest) && Util.areEqual(this.liveConfiguration, window.liveConfiguration) && this.presentationStartTimeMs == window.presentationStartTimeMs && this.windowStartTimeMs == window.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == window.elapsedRealtimeEpochOffsetMs && this.isSeekable == window.isSeekable && this.isDynamic == window.isDynamic && this.isPlaceholder == window.isPlaceholder && this.defaultPositionUs == window.defaultPositionUs && this.durationUs == window.durationUs && this.firstPeriodIndex == window.firstPeriodIndex && this.lastPeriodIndex == window.lastPeriodIndex && this.positionInFirstPeriodUs == window.positionInFirstPeriodUs;
        }

        public int hashCode() {
            int hashCode = (((JfifUtil.MARKER_EOI + this.uid.hashCode()) * 31) + this.mediaItem.hashCode()) * 31;
            Object obj = this.manifest;
            int hashCode2 = (hashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            int hashCode3 = liveConfiguration != null ? liveConfiguration.hashCode() : 0;
            long j = this.presentationStartTimeMs;
            long j2 = this.windowStartTimeMs;
            long j3 = this.elapsedRealtimeEpochOffsetMs;
            long j4 = this.defaultPositionUs;
            long j5 = this.durationUs;
            long j6 = this.positionInFirstPeriodUs;
            return ((((((((((((((((((((((hashCode2 + hashCode3) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31) + ((int) (j6 ^ (j6 >>> 32)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Bundle toBundle(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putBundle(keyForField(1), (z ? MediaItem.EMPTY : this.mediaItem).toBundle());
            bundle.putLong(keyForField(2), this.presentationStartTimeMs);
            bundle.putLong(keyForField(3), this.windowStartTimeMs);
            bundle.putLong(keyForField(4), this.elapsedRealtimeEpochOffsetMs);
            bundle.putBoolean(keyForField(5), this.isSeekable);
            bundle.putBoolean(keyForField(6), this.isDynamic);
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            if (liveConfiguration != null) {
                bundle.putBundle(keyForField(7), liveConfiguration.toBundle());
            }
            bundle.putBoolean(keyForField(8), this.isPlaceholder);
            bundle.putLong(keyForField(9), this.defaultPositionUs);
            bundle.putLong(keyForField(10), this.durationUs);
            bundle.putInt(keyForField(11), this.firstPeriodIndex);
            bundle.putInt(keyForField(12), this.lastPeriodIndex);
            bundle.putLong(keyForField(13), this.positionInFirstPeriodUs);
            return bundle;
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            return toBundle(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Window fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(keyForField(1));
            MediaItem fromBundle = bundle2 != null ? MediaItem.CREATOR.fromBundle(bundle2) : null;
            long j = bundle.getLong(keyForField(2), C1856C.TIME_UNSET);
            long j2 = bundle.getLong(keyForField(3), C1856C.TIME_UNSET);
            long j3 = bundle.getLong(keyForField(4), C1856C.TIME_UNSET);
            boolean z = bundle.getBoolean(keyForField(5), false);
            boolean z2 = bundle.getBoolean(keyForField(6), false);
            Bundle bundle3 = bundle.getBundle(keyForField(7));
            MediaItem.LiveConfiguration fromBundle2 = bundle3 != null ? MediaItem.LiveConfiguration.CREATOR.fromBundle(bundle3) : null;
            boolean z3 = bundle.getBoolean(keyForField(8), false);
            long j4 = bundle.getLong(keyForField(9), 0L);
            long j5 = bundle.getLong(keyForField(10), C1856C.TIME_UNSET);
            int r20 = bundle.getInt(keyForField(11), 0);
            int r21 = bundle.getInt(keyForField(12), 0);
            long j6 = bundle.getLong(keyForField(13), 0L);
            Window window = new Window();
            window.set(FAKE_WINDOW_UID, fromBundle, null, j, j2, j3, z, z2, fromBundle2, j4, j5, r20, r21, j6);
            window.isPlaceholder = z3;
            return window;
        }

        private static String keyForField(int r1) {
            return Integer.toString(r1, 36);
        }
    }

    /* loaded from: classes2.dex */
    public static final class Period implements Bundleable {
        public static final Bundleable.Creator<Period> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.Timeline$Period$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.Bundleable.Creator
            public final Bundleable fromBundle(Bundle bundle) {
                Timeline.Period fromBundle;
                fromBundle = Timeline.Period.fromBundle(bundle);
                return fromBundle;
            }
        };
        private static final int FIELD_AD_PLAYBACK_STATE = 4;
        private static final int FIELD_DURATION_US = 1;
        private static final int FIELD_PLACEHOLDER = 3;
        private static final int FIELD_POSITION_IN_WINDOW_US = 2;
        private static final int FIELD_WINDOW_INDEX = 0;
        private AdPlaybackState adPlaybackState = AdPlaybackState.NONE;
        public long durationUs;

        /* renamed from: id */
        public Object f218id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        public Period set(Object obj, Object obj2, int r13, long j, long j2) {
            return set(obj, obj2, r13, j, j2, AdPlaybackState.NONE, false);
        }

        public Period set(Object obj, Object obj2, int r3, long j, long j2, AdPlaybackState adPlaybackState, boolean z) {
            this.f218id = obj;
            this.uid = obj2;
            this.windowIndex = r3;
            this.durationUs = j;
            this.positionInWindowUs = j2;
            this.adPlaybackState = adPlaybackState;
            this.isPlaceholder = z;
            return this;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public Object getAdsId() {
            return this.adPlaybackState.adsId;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public long getAdGroupTimeUs(int r3) {
            return this.adPlaybackState.getAdGroup(r3).timeUs;
        }

        public int getFirstAdIndexToPlay(int r2) {
            return this.adPlaybackState.getAdGroup(r2).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int r2, int r3) {
            return this.adPlaybackState.getAdGroup(r2).getNextAdIndexToPlay(r3);
        }

        public boolean hasPlayedAdGroup(int r2) {
            return !this.adPlaybackState.getAdGroup(r2).hasUnplayedAds();
        }

        public int getAdGroupIndexForPositionUs(long j) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j, this.durationUs);
        }

        public int getAdGroupIndexAfterPositionUs(long j) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j, this.durationUs);
        }

        public int getAdCountInAdGroup(int r2) {
            return this.adPlaybackState.getAdGroup(r2).count;
        }

        public long getAdDurationUs(int r3, int r4) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(r3);
            return adGroup.count != -1 ? adGroup.durationsUs[r4] : C1856C.TIME_UNSET;
        }

        public int getAdState(int r3, int r4) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(r3);
            if (adGroup.count != -1) {
                return adGroup.states[r4];
            }
            return 0;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public boolean isServerSideInsertedAdGroup(int r2) {
            return this.adPlaybackState.getAdGroup(r2).isServerSideInserted;
        }

        public long getContentResumeOffsetUs(int r3) {
            return this.adPlaybackState.getAdGroup(r3).contentResumeOffsetUs;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !getClass().equals(obj.getClass())) {
                return false;
            }
            Period period = (Period) obj;
            return Util.areEqual(this.f218id, period.f218id) && Util.areEqual(this.uid, period.uid) && this.windowIndex == period.windowIndex && this.durationUs == period.durationUs && this.positionInWindowUs == period.positionInWindowUs && this.isPlaceholder == period.isPlaceholder && Util.areEqual(this.adPlaybackState, period.adPlaybackState);
        }

        public int hashCode() {
            Object obj = this.f218id;
            int hashCode = (JfifUtil.MARKER_EOI + (obj == null ? 0 : obj.hashCode())) * 31;
            Object obj2 = this.uid;
            int hashCode2 = obj2 != null ? obj2.hashCode() : 0;
            long j = this.durationUs;
            long j2 = this.positionInWindowUs;
            return ((((((((((hashCode + hashCode2) * 31) + this.windowIndex) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + this.adPlaybackState.hashCode();
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(keyForField(0), this.windowIndex);
            bundle.putLong(keyForField(1), this.durationUs);
            bundle.putLong(keyForField(2), this.positionInWindowUs);
            bundle.putBoolean(keyForField(3), this.isPlaceholder);
            bundle.putBundle(keyForField(4), this.adPlaybackState.toBundle());
            return bundle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Period fromBundle(Bundle bundle) {
            AdPlaybackState adPlaybackState;
            int r5 = bundle.getInt(keyForField(0), 0);
            long j = bundle.getLong(keyForField(1), C1856C.TIME_UNSET);
            long j2 = bundle.getLong(keyForField(2), 0L);
            boolean z = bundle.getBoolean(keyForField(3));
            Bundle bundle2 = bundle.getBundle(keyForField(4));
            if (bundle2 != null) {
                adPlaybackState = AdPlaybackState.CREATOR.fromBundle(bundle2);
            } else {
                adPlaybackState = AdPlaybackState.NONE;
            }
            AdPlaybackState adPlaybackState2 = adPlaybackState;
            Period period = new Period();
            period.set(null, null, r5, j, j2, adPlaybackState2, z);
            return period;
        }

        private static String keyForField(int r1) {
            return Integer.toString(r1, 36);
        }
    }

    public final boolean isEmpty() {
        return getWindowCount() == 0;
    }

    public int getNextWindowIndex(int r3, int r4, boolean z) {
        if (r4 == 0) {
            if (r3 == getLastWindowIndex(z)) {
                return -1;
            }
            return r3 + 1;
        } else if (r4 != 1) {
            if (r4 == 2) {
                return r3 == getLastWindowIndex(z) ? getFirstWindowIndex(z) : r3 + 1;
            }
            throw new IllegalStateException();
        } else {
            return r3;
        }
    }

    public int getPreviousWindowIndex(int r3, int r4, boolean z) {
        if (r4 == 0) {
            if (r3 == getFirstWindowIndex(z)) {
                return -1;
            }
            return r3 - 1;
        } else if (r4 != 1) {
            if (r4 == 2) {
                return r3 == getFirstWindowIndex(z) ? getLastWindowIndex(z) : r3 - 1;
            }
            throw new IllegalStateException();
        } else {
            return r3;
        }
    }

    public int getLastWindowIndex(boolean z) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public int getFirstWindowIndex(boolean z) {
        return isEmpty() ? -1 : 0;
    }

    public final Window getWindow(int r3, Window window) {
        return getWindow(r3, window, 0L);
    }

    public final int getNextPeriodIndex(int r2, Period period, Window window, int r5, boolean z) {
        int r3 = getPeriod(r2, period).windowIndex;
        if (getWindow(r3, window).lastPeriodIndex == r2) {
            int nextWindowIndex = getNextWindowIndex(r3, r5, z);
            if (nextWindowIndex == -1) {
                return -1;
            }
            return getWindow(nextWindowIndex, window).firstPeriodIndex;
        }
        return r2 + 1;
    }

    public final boolean isLastPeriod(int r1, Period period, Window window, int r4, boolean z) {
        return getNextPeriodIndex(r1, period, window, r4, z) == -1;
    }

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int r3, long j) {
        return getPeriodPositionUs(window, period, r3, j);
    }

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int r3, long j, long j2) {
        return getPeriodPositionUs(window, period, r3, j, j2);
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int r11, long j) {
        return (Pair) Assertions.checkNotNull(getPeriodPositionUs(window, period, r11, j, 0L));
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int r7, long j, long j2) {
        Assertions.checkIndex(r7, 0, getWindowCount());
        getWindow(r7, window, j2);
        if (j == C1856C.TIME_UNSET) {
            j = window.getDefaultPositionUs();
            if (j == C1856C.TIME_UNSET) {
                return null;
            }
        }
        int r72 = window.firstPeriodIndex;
        getPeriod(r72, period);
        while (r72 < window.lastPeriodIndex && period.positionInWindowUs != j) {
            int r0 = r72 + 1;
            if (getPeriod(r0, period).positionInWindowUs > j) {
                break;
            }
            r72 = r0;
        }
        getPeriod(r72, period, true);
        long j3 = j - period.positionInWindowUs;
        if (period.durationUs != C1856C.TIME_UNSET) {
            j3 = Math.min(j3, period.durationUs - 1);
        }
        return Pair.create(Assertions.checkNotNull(period.uid), Long.valueOf(Math.max(0L, j3)));
    }

    public Period getPeriodByUid(Object obj, Period period) {
        return getPeriod(getIndexOfPeriod(obj), period, true);
    }

    public final Period getPeriod(int r2, Period period) {
        return getPeriod(r2, period, false);
    }

    public boolean equals(Object obj) {
        int lastWindowIndex;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Timeline) {
            Timeline timeline = (Timeline) obj;
            if (timeline.getWindowCount() == getWindowCount() && timeline.getPeriodCount() == getPeriodCount()) {
                Window window = new Window();
                Period period = new Period();
                Window window2 = new Window();
                Period period2 = new Period();
                for (int r6 = 0; r6 < getWindowCount(); r6++) {
                    if (!getWindow(r6, window).equals(timeline.getWindow(r6, window2))) {
                        return false;
                    }
                }
                for (int r1 = 0; r1 < getPeriodCount(); r1++) {
                    if (!getPeriod(r1, period, true).equals(timeline.getPeriod(r1, period2, true))) {
                        return false;
                    }
                }
                int firstWindowIndex = getFirstWindowIndex(true);
                if (firstWindowIndex == timeline.getFirstWindowIndex(true) && (lastWindowIndex = getLastWindowIndex(true)) == timeline.getLastWindowIndex(true)) {
                    while (firstWindowIndex != lastWindowIndex) {
                        int nextWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
                        if (nextWindowIndex != timeline.getNextWindowIndex(firstWindowIndex, 0, true)) {
                            return false;
                        }
                        firstWindowIndex = nextWindowIndex;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Window window = new Window();
        Period period = new Period();
        int windowCount = JfifUtil.MARKER_EOI + getWindowCount();
        for (int r4 = 0; r4 < getWindowCount(); r4++) {
            windowCount = (windowCount * 31) + getWindow(r4, window).hashCode();
        }
        int periodCount = (windowCount * 31) + getPeriodCount();
        for (int r0 = 0; r0 < getPeriodCount(); r0++) {
            periodCount = (periodCount * 31) + getPeriod(r0, period, true).hashCode();
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        while (firstWindowIndex != -1) {
            periodCount = (periodCount * 31) + firstWindowIndex;
            firstWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
        }
        return periodCount;
    }

    public final Bundle toBundle(boolean z) {
        ArrayList arrayList = new ArrayList();
        int windowCount = getWindowCount();
        Window window = new Window();
        for (int r4 = 0; r4 < windowCount; r4++) {
            arrayList.add(getWindow(r4, window, 0L).toBundle(z));
        }
        ArrayList arrayList2 = new ArrayList();
        int periodCount = getPeriodCount();
        Period period = new Period();
        for (int r5 = 0; r5 < periodCount; r5++) {
            arrayList2.add(getPeriod(r5, period, false).toBundle());
        }
        int[] r2 = new int[windowCount];
        if (windowCount > 0) {
            r2[0] = getFirstWindowIndex(true);
        }
        for (int r52 = 1; r52 < windowCount; r52++) {
            r2[r52] = getNextWindowIndex(r2[r52 - 1], 0, true);
        }
        Bundle bundle = new Bundle();
        BundleUtil.putBinder(bundle, keyForField(0), new BundleListRetriever(arrayList));
        BundleUtil.putBinder(bundle, keyForField(1), new BundleListRetriever(arrayList2));
        bundle.putIntArray(keyForField(2), r2);
        return bundle;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public final Bundle toBundle() {
        return toBundle(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Timeline fromBundle(Bundle bundle) {
        ImmutableList fromBundleListRetriever = fromBundleListRetriever(Window.CREATOR, BundleUtil.getBinder(bundle, keyForField(0)));
        ImmutableList fromBundleListRetriever2 = fromBundleListRetriever(Period.CREATOR, BundleUtil.getBinder(bundle, keyForField(1)));
        int[] intArray = bundle.getIntArray(keyForField(2));
        if (intArray == null) {
            intArray = generateUnshuffledIndices(fromBundleListRetriever.size());
        }
        return new RemotableTimeline(fromBundleListRetriever, fromBundleListRetriever2, intArray);
    }

    private static <T extends Bundleable> ImmutableList<T> fromBundleListRetriever(Bundleable.Creator<T> creator, IBinder iBinder) {
        if (iBinder == null) {
            return ImmutableList.m409of();
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Bundle> list = BundleListRetriever.getList(iBinder);
        for (int r1 = 0; r1 < list.size(); r1++) {
            builder.add((ImmutableList.Builder) creator.fromBundle(list.get(r1)));
        }
        return builder.build();
    }

    private static String keyForField(int r1) {
        return Integer.toString(r1, 36);
    }

    private static int[] generateUnshuffledIndices(int r2) {
        int[] r0 = new int[r2];
        for (int r1 = 0; r1 < r2; r1++) {
            r0[r1] = r1;
        }
        return r0;
    }

    /* loaded from: classes2.dex */
    public static final class RemotableTimeline extends Timeline {
        private final ImmutableList<Period> periods;
        private final int[] shuffledWindowIndices;
        private final int[] windowIndicesInShuffled;
        private final ImmutableList<Window> windows;

        public RemotableTimeline(ImmutableList<Window> immutableList, ImmutableList<Period> immutableList2, int[] r6) {
            Assertions.checkArgument(immutableList.size() == r6.length);
            this.windows = immutableList;
            this.periods = immutableList2;
            this.shuffledWindowIndices = r6;
            this.windowIndicesInShuffled = new int[r6.length];
            for (int r2 = 0; r2 < r6.length; r2++) {
                this.windowIndicesInShuffled[r6[r2]] = r2;
            }
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.windows.size();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Window getWindow(int r23, Window window, long j) {
            Window window2 = this.windows.get(r23);
            window.set(window2.uid, window2.mediaItem, window2.manifest, window2.presentationStartTimeMs, window2.windowStartTimeMs, window2.elapsedRealtimeEpochOffsetMs, window2.isSeekable, window2.isDynamic, window2.liveConfiguration, window2.defaultPositionUs, window2.durationUs, window2.firstPeriodIndex, window2.lastPeriodIndex, window2.positionInFirstPeriodUs);
            window.isPlaceholder = window2.isPlaceholder;
            return window;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getNextWindowIndex(int r3, int r4, boolean z) {
            if (r4 == 1) {
                return r3;
            }
            if (r3 != getLastWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[r3] + 1] : r3 + 1;
            } else if (r4 == 2) {
                return getFirstWindowIndex(z);
            } else {
                return -1;
            }
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPreviousWindowIndex(int r3, int r4, boolean z) {
            if (r4 == 1) {
                return r3;
            }
            if (r3 != getFirstWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[r3] - 1] : r3 - 1;
            } else if (r4 == 2) {
                return getLastWindowIndex(z);
            } else {
                return -1;
            }
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getLastWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            if (z) {
                return this.shuffledWindowIndices[getWindowCount() - 1];
            }
            return getWindowCount() - 1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getFirstWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            if (z) {
                return this.shuffledWindowIndices[0];
            }
            return 0;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.periods.size();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Period getPeriod(int r11, Period period, boolean z) {
            Period period2 = this.periods.get(r11);
            period.set(period2.f218id, period2.uid, period2.windowIndex, period2.durationUs, period2.positionInWindowUs, period2.adPlaybackState, period2.isPlaceholder);
            return period;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Object getUidOfPeriod(int r1) {
            throw new UnsupportedOperationException();
        }
    }
}
