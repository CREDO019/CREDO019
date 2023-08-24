package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    protected final int[] tracks;
    private final int type;

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void disable() {
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void enable() {
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public /* synthetic */ void onDiscontinuity() {
        ExoTrackSelection.CC.$default$onDiscontinuity(this);
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public /* synthetic */ void onPlayWhenReadyChanged(boolean z) {
        ExoTrackSelection.CC.$default$onPlayWhenReadyChanged(this, z);
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public void onPlaybackSpeed(float f) {
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public /* synthetic */ void onRebuffer() {
        ExoTrackSelection.CC.$default$onRebuffer(this);
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public /* synthetic */ boolean shouldCancelChunkLoad(long j, Chunk chunk, List list) {
        return ExoTrackSelection.CC.$default$shouldCancelChunkLoad(this, j, chunk, list);
    }

    public BaseTrackSelection(TrackGroup trackGroup, int... r3) {
        this(trackGroup, r3, 0);
    }

    public BaseTrackSelection(TrackGroup trackGroup, int[] r5, int r6) {
        int r1 = 0;
        Assertions.checkState(r5.length > 0);
        this.type = r6;
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        int length = r5.length;
        this.length = length;
        this.formats = new Format[length];
        for (int r62 = 0; r62 < r5.length; r62++) {
            this.formats[r62] = trackGroup.getFormat(r5[r62]);
        }
        Arrays.sort(this.formats, new Comparator() { // from class: com.google.android.exoplayer2.trackselection.BaseTrackSelection$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BaseTrackSelection.lambda$new$0((Format) obj, (Format) obj2);
            }
        });
        this.tracks = new int[this.length];
        while (true) {
            int r52 = this.length;
            if (r1 < r52) {
                this.tracks[r1] = trackGroup.indexOf(this.formats[r1]);
                r1++;
            } else {
                this.excludeUntilTimes = new long[r52];
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(Format format, Format format2) {
        return format2.bitrate - format.bitrate;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int getType() {
        return this.type;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int length() {
        return this.tracks.length;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final Format getFormat(int r2) {
        return this.formats[r2];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int getIndexInTrackGroup(int r2) {
        return this.tracks[r2];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int indexOf(Format format) {
        for (int r0 = 0; r0 < this.length; r0++) {
            if (this.formats[r0] == format) {
                return r0;
            }
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int indexOf(int r3) {
        for (int r0 = 0; r0 < this.length; r0++) {
            if (this.tracks[r0] == r3) {
                return r0;
            }
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        return list.size();
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public boolean blacklist(int r11, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean isBlacklisted = isBlacklisted(r11, elapsedRealtime);
        int r4 = 0;
        while (r4 < this.length && !isBlacklisted) {
            isBlacklisted = (r4 == r11 || isBlacklisted(r4, elapsedRealtime)) ? false : true;
            r4++;
        }
        if (isBlacklisted) {
            long[] jArr = this.excludeUntilTimes;
            jArr[r11] = Math.max(jArr[r11], Util.addWithOverflowDefault(elapsedRealtime, j, Long.MAX_VALUE));
            return true;
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.trackselection.ExoTrackSelection
    public boolean isBlacklisted(int r4, long j) {
        return this.excludeUntilTimes[r4] > j;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (System.identityHashCode(this.group) * 31) + Arrays.hashCode(this.tracks);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
        return this.group == baseTrackSelection.group && Arrays.equals(this.tracks, baseTrackSelection.tracks);
    }
}
