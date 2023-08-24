package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Log;
import java.util.List;

/* loaded from: classes2.dex */
public interface ExoTrackSelection extends TrackSelection {

    /* renamed from: com.google.android.exoplayer2.trackselection.ExoTrackSelection$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static void $default$onDiscontinuity(ExoTrackSelection _this) {
        }

        public static void $default$onPlayWhenReadyChanged(ExoTrackSelection _this, boolean z) {
        }

        public static void $default$onRebuffer(ExoTrackSelection _this) {
        }

        public static boolean $default$shouldCancelChunkLoad(ExoTrackSelection _this, long j, Chunk chunk, List list) {
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public interface Factory {
        ExoTrackSelection[] createTrackSelections(Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline);
    }

    boolean blacklist(int r1, long j);

    void disable();

    void enable();

    int evaluateQueueSize(long j, List<? extends MediaChunk> list);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    boolean isBlacklisted(int r1, long j);

    void onDiscontinuity();

    void onPlayWhenReadyChanged(boolean z);

    void onPlaybackSpeed(float f);

    void onRebuffer();

    boolean shouldCancelChunkLoad(long j, Chunk chunk, List<? extends MediaChunk> list);

    void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr);

    /* loaded from: classes2.dex */
    public static final class Definition {
        private static final String TAG = "ETSDefinition";
        public final TrackGroup group;
        public final int[] tracks;
        public final int type;

        public Definition(TrackGroup trackGroup, int... r3) {
            this(trackGroup, r3, 0);
        }

        public Definition(TrackGroup trackGroup, int[] r5, int r6) {
            if (r5.length == 0) {
                Log.m1135e(TAG, "Empty tracks are not allowed", new IllegalArgumentException());
            }
            this.group = trackGroup;
            this.tracks = r5;
            this.type = r6;
        }
    }
}
