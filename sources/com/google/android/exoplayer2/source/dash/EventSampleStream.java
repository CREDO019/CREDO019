package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessageEncoder;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.dash.manifest.EventStream;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* loaded from: classes2.dex */
final class EventSampleStream implements SampleStream {
    private int currentIndex;
    private EventStream eventStream;
    private boolean eventStreamAppendable;
    private long[] eventTimesUs;
    private boolean isFormatSentDownstream;
    private final Format upstreamFormat;
    private final EventMessageEncoder eventMessageEncoder = new EventMessageEncoder();
    private long pendingSeekPositionUs = C1856C.TIME_UNSET;

    @Override // com.google.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
    }

    public EventSampleStream(EventStream eventStream, Format format, boolean z) {
        this.upstreamFormat = format;
        this.eventStream = eventStream;
        this.eventTimesUs = eventStream.presentationTimesUs;
        updateEventStream(eventStream, z);
    }

    public String eventStreamId() {
        return this.eventStream.m1157id();
    }

    public void updateEventStream(EventStream eventStream, boolean z) {
        int r0 = this.currentIndex;
        long j = r0 == 0 ? -9223372036854775807L : this.eventTimesUs[r0 - 1];
        this.eventStreamAppendable = z;
        this.eventStream = eventStream;
        long[] jArr = eventStream.presentationTimesUs;
        this.eventTimesUs = jArr;
        long j2 = this.pendingSeekPositionUs;
        if (j2 != C1856C.TIME_UNSET) {
            seekToUs(j2);
        } else if (j != C1856C.TIME_UNSET) {
            this.currentIndex = Util.binarySearchCeil(jArr, j, false, false);
        }
    }

    public void seekToUs(long j) {
        boolean z = true;
        int binarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, true, false);
        this.currentIndex = binarySearchCeil;
        if (!((this.eventStreamAppendable && binarySearchCeil == this.eventTimesUs.length) ? false : false)) {
            j = C1856C.TIME_UNSET;
        }
        this.pendingSeekPositionUs = j;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int r9) {
        int r0 = this.currentIndex;
        boolean z = r0 == this.eventTimesUs.length;
        if (z && !this.eventStreamAppendable) {
            decoderInputBuffer.setFlags(4);
            return -4;
        } else if ((r9 & 2) != 0 || !this.isFormatSentDownstream) {
            formatHolder.format = this.upstreamFormat;
            this.isFormatSentDownstream = true;
            return -5;
        } else if (z) {
            return -3;
        } else {
            if ((r9 & 1) == 0) {
                this.currentIndex = r0 + 1;
            }
            if ((r9 & 4) == 0) {
                byte[] encode = this.eventMessageEncoder.encode(this.eventStream.events[r0]);
                decoderInputBuffer.ensureSpaceForWrite(encode.length);
                decoderInputBuffer.data.put(encode);
            }
            decoderInputBuffer.timeUs = this.eventTimesUs[r0];
            decoderInputBuffer.setFlags(1);
            return -4;
        }
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        int max = Math.max(this.currentIndex, Util.binarySearchCeil(this.eventTimesUs, j, true, false));
        int r6 = max - this.currentIndex;
        this.currentIndex = max;
        return r6;
    }
}
