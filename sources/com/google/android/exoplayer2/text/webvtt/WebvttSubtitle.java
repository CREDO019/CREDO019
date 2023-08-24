package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
final class WebvttSubtitle implements Subtitle {
    private final List<WebvttCueInfo> cueInfos;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCueInfo> list) {
        this.cueInfos = Collections.unmodifiableList(new ArrayList(list));
        this.cueTimesUs = new long[list.size() * 2];
        for (int r0 = 0; r0 < list.size(); r0++) {
            WebvttCueInfo webvttCueInfo = list.get(r0);
            int r2 = r0 * 2;
            this.cueTimesUs[r2] = webvttCueInfo.startTimeUs;
            this.cueTimesUs[r2 + 1] = webvttCueInfo.endTimeUs;
        }
        long[] jArr = this.cueTimesUs;
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = copyOf;
        Arrays.sort(copyOf);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        int binarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j, false, false);
        if (binarySearchCeil < this.sortedCueTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int r4) {
        Assertions.checkArgument(r4 >= 0);
        Assertions.checkArgument(r4 < this.sortedCueTimesUs.length);
        return this.sortedCueTimesUs[r4];
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int r3 = 0; r3 < this.cueInfos.size(); r3++) {
            long[] jArr = this.cueTimesUs;
            int r5 = r3 * 2;
            if (jArr[r5] <= j && j < jArr[r5 + 1]) {
                WebvttCueInfo webvttCueInfo = this.cueInfos.get(r3);
                if (webvttCueInfo.cue.line == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(webvttCueInfo.cue);
                }
            }
        }
        Collections.sort(arrayList2, new Comparator() { // from class: com.google.android.exoplayer2.text.webvtt.WebvttSubtitle$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((WebvttCueInfo) obj).startTimeUs, ((WebvttCueInfo) obj2).startTimeUs);
                return compare;
            }
        });
        for (int r2 = 0; r2 < arrayList2.size(); r2++) {
            arrayList.add(((WebvttCueInfo) arrayList2.get(r2)).cue.buildUpon().setLine((-1) - r2, 1).build());
        }
        return arrayList;
    }
}
