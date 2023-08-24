package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_BACK_BUFFER_DURATION_MS = 0;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_CAMERA_MOTION_BUFFER_SIZE = 131072;
    public static final int DEFAULT_IMAGE_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_METADATA_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MIN_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_MUXED_BUFFER_SIZE = 144310272;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = false;
    public static final boolean DEFAULT_RETAIN_BACK_BUFFER_FROM_KEYFRAME = false;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    public static final int DEFAULT_TEXT_BUFFER_SIZE = 131072;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE = 131072000;
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isLoading;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private int targetBufferBytes;
    private final int targetBufferBytesOverwrite;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private DefaultAllocator allocator;
        private boolean buildCalled;
        private int minBufferMs = 50000;
        private int maxBufferMs = 50000;
        private int bufferForPlaybackMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
        private int bufferForPlaybackAfterRebufferMs = 5000;
        private int targetBufferBytes = -1;
        private boolean prioritizeTimeOverSizeThresholds = false;
        private int backBufferDurationMs = 0;
        private boolean retainBackBufferFromKeyframe = false;

        public Builder setAllocator(DefaultAllocator defaultAllocator) {
            Assertions.checkState(!this.buildCalled);
            this.allocator = defaultAllocator;
            return this;
        }

        public Builder setBufferDurationsMs(int r5, int r6, int r7, int r8) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(r7, 0, "bufferForPlaybackMs", SessionDescription.SUPPORTED_SDP_VERSION);
            DefaultLoadControl.assertGreaterOrEqual(r8, 0, "bufferForPlaybackAfterRebufferMs", SessionDescription.SUPPORTED_SDP_VERSION);
            DefaultLoadControl.assertGreaterOrEqual(r5, r7, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(r5, r8, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(r6, r5, "maxBufferMs", "minBufferMs");
            this.minBufferMs = r5;
            this.maxBufferMs = r6;
            this.bufferForPlaybackMs = r7;
            this.bufferForPlaybackAfterRebufferMs = r8;
            return this;
        }

        public Builder setTargetBufferBytes(int r2) {
            Assertions.checkState(!this.buildCalled);
            this.targetBufferBytes = r2;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = z;
            return this;
        }

        public Builder setBackBuffer(int r4, boolean z) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(r4, 0, "backBufferDurationMs", SessionDescription.SUPPORTED_SDP_VERSION);
            this.backBufferDurationMs = r4;
            this.retainBackBufferFromKeyframe = z;
            return this;
        }

        @Deprecated
        public DefaultLoadControl createDefaultLoadControl() {
            return build();
        }

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000, -1, false, 0, false);
    }

    protected DefaultLoadControl(DefaultAllocator defaultAllocator, int r7, int r8, int r9, int r10, int r11, boolean z, int r13, boolean z2) {
        assertGreaterOrEqual(r9, 0, "bufferForPlaybackMs", SessionDescription.SUPPORTED_SDP_VERSION);
        assertGreaterOrEqual(r10, 0, "bufferForPlaybackAfterRebufferMs", SessionDescription.SUPPORTED_SDP_VERSION);
        assertGreaterOrEqual(r7, r9, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(r7, r10, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(r8, r7, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(r13, 0, "backBufferDurationMs", SessionDescription.SUPPORTED_SDP_VERSION);
        this.allocator = defaultAllocator;
        this.minBufferUs = Util.msToUs(r7);
        this.maxBufferUs = Util.msToUs(r8);
        this.bufferForPlaybackUs = Util.msToUs(r9);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs(r10);
        this.targetBufferBytesOverwrite = r11;
        this.targetBufferBytes = r11 == -1 ? 13107200 : r11;
        this.prioritizeTimeOverSizeThresholds = z;
        this.backBufferDurationUs = Util.msToUs(r13);
        this.retainBackBufferFromKeyframe = z2;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        int r3 = this.targetBufferBytesOverwrite;
        if (r3 == -1) {
            r3 = calculateTargetBufferBytes(rendererArr, exoTrackSelectionArr);
        }
        this.targetBufferBytes = r3;
        this.allocator.setTargetBufferSize(r3);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public long getBackBufferDurationUs() {
        return this.backBufferDurationUs;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return this.retainBackBufferFromKeyframe;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldContinueLoading(long j, long j2, float f) {
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes;
        long j3 = this.minBufferUs;
        if (f > 1.0f) {
            j3 = Math.min(Util.getMediaDurationForPlayoutDuration(j3, f), this.maxBufferUs);
        }
        if (j2 < Math.max(j3, 500000L)) {
            if (!this.prioritizeTimeOverSizeThresholds && z2) {
                z = false;
            }
            this.isLoading = z;
            if (!z && j2 < 500000) {
                Log.m1132w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (j2 >= this.maxBufferUs || z2) {
            this.isLoading = false;
        }
        return this.isLoading;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldStartPlayback(long j, float f, boolean z, long j2) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j, f);
        long j3 = z ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        if (j2 != C1856C.TIME_UNSET) {
            j3 = Math.min(j2 / 2, j3);
        }
        return j3 <= 0 || playoutDurationForMediaDuration >= j3 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferBytes);
    }

    protected int calculateTargetBufferBytes(Renderer[] rendererArr, ExoTrackSelection[] exoTrackSelectionArr) {
        int r1 = 0;
        for (int r0 = 0; r0 < rendererArr.length; r0++) {
            if (exoTrackSelectionArr[r0] != null) {
                r1 += getDefaultBufferSize(rendererArr[r0].getTrackType());
            }
        }
        return Math.max(13107200, r1);
    }

    private void reset(boolean z) {
        int r0 = this.targetBufferBytesOverwrite;
        if (r0 == -1) {
            r0 = 13107200;
        }
        this.targetBufferBytes = r0;
        this.isLoading = false;
        if (z) {
            this.allocator.reset();
        }
    }

    private static int getDefaultBufferSize(int r1) {
        switch (r1) {
            case -2:
                return 0;
            case -1:
            default:
                throw new IllegalArgumentException();
            case 0:
                return DEFAULT_MUXED_BUFFER_SIZE;
            case 1:
                return 13107200;
            case 2:
                return DEFAULT_VIDEO_BUFFER_SIZE;
            case 3:
            case 4:
            case 5:
            case 6:
                return 131072;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertGreaterOrEqual(int r0, int r1, String str, String str2) {
        boolean z = r0 >= r1;
        Assertions.checkArgument(z, str + " cannot be less than " + str2);
    }
}
