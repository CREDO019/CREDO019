package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaPeriodId;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
public final class ServerSideAdInsertionUtil {
    private ServerSideAdInsertionUtil() {
    }

    public static AdPlaybackState addAdGroupToAdPlaybackState(AdPlaybackState adPlaybackState, long j, long j2, long... jArr) {
        long mediaPeriodPositionUsForContent = getMediaPeriodPositionUsForContent(j, -1, adPlaybackState);
        int r2 = adPlaybackState.removedAdGroupCount;
        while (r2 < adPlaybackState.adGroupCount && adPlaybackState.getAdGroup(r2).timeUs != Long.MIN_VALUE && adPlaybackState.getAdGroup(r2).timeUs <= mediaPeriodPositionUsForContent) {
            r2++;
        }
        AdPlaybackState withContentResumeOffsetUs = adPlaybackState.withNewAdGroup(r2, mediaPeriodPositionUsForContent).withIsServerSideInserted(r2, true).withAdCount(r2, jArr.length).withAdDurationsUs(r2, jArr).withContentResumeOffsetUs(r2, j2);
        for (int r8 = 0; r8 < jArr.length && jArr[r8] == 0; r8++) {
            withContentResumeOffsetUs = withContentResumeOffsetUs.withSkippedAd(r2, r8);
        }
        return correctFollowingAdGroupTimes(withContentResumeOffsetUs, r2, Util.sum(jArr), j2);
    }

    public static long getStreamPositionUs(Player player, AdPlaybackState adPlaybackState) {
        Timeline currentTimeline = player.getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return C1856C.TIME_UNSET;
        }
        Timeline.Period period = currentTimeline.getPeriod(player.getCurrentPeriodIndex(), new Timeline.Period());
        if (Util.areEqual(period.getAdsId(), adPlaybackState.adsId)) {
            if (player.isPlayingAd()) {
                return getStreamPositionUsForAd(Util.msToUs(player.getCurrentPosition()), player.getCurrentAdGroupIndex(), player.getCurrentAdIndexInAdGroup(), adPlaybackState);
            }
            return getStreamPositionUsForContent(Util.msToUs(player.getCurrentPosition()) - period.getPositionInWindowUs(), -1, adPlaybackState);
        }
        return C1856C.TIME_UNSET;
    }

    public static long getStreamPositionUs(long j, MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        if (mediaPeriodId.isAd()) {
            return getStreamPositionUsForAd(j, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState);
        }
        return getStreamPositionUsForContent(j, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }

    public static long getMediaPeriodPositionUs(long j, MediaPeriodId mediaPeriodId, AdPlaybackState adPlaybackState) {
        if (mediaPeriodId.isAd()) {
            return getMediaPeriodPositionUsForAd(j, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, adPlaybackState);
        }
        return getMediaPeriodPositionUsForContent(j, mediaPeriodId.nextAdGroupIndex, adPlaybackState);
    }

    public static long getStreamPositionUsForAd(long j, int r9, int r10, AdPlaybackState adPlaybackState) {
        int r2;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(r9);
        long j2 = j + adGroup.timeUs;
        int r1 = adPlaybackState.removedAdGroupCount;
        while (true) {
            r2 = 0;
            if (r1 >= r9) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(r1);
            while (r2 < getAdCountInGroup(adPlaybackState, r1)) {
                j2 += adGroup2.durationsUs[r2];
                r2++;
            }
            j2 -= adGroup2.contentResumeOffsetUs;
            r1++;
        }
        if (r10 < getAdCountInGroup(adPlaybackState, r9)) {
            while (r2 < r10) {
                j2 += adGroup.durationsUs[r2];
                r2++;
            }
        }
        return j2;
    }

    public static long getMediaPeriodPositionUsForAd(long j, int r9, int r10, AdPlaybackState adPlaybackState) {
        int r2;
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(r9);
        long j2 = j - adGroup.timeUs;
        int r1 = adPlaybackState.removedAdGroupCount;
        while (true) {
            r2 = 0;
            if (r1 >= r9) {
                break;
            }
            AdPlaybackState.AdGroup adGroup2 = adPlaybackState.getAdGroup(r1);
            while (r2 < getAdCountInGroup(adPlaybackState, r1)) {
                j2 -= adGroup2.durationsUs[r2];
                r2++;
            }
            j2 += adGroup2.contentResumeOffsetUs;
            r1++;
        }
        if (r10 < getAdCountInGroup(adPlaybackState, r9)) {
            while (r2 < r10) {
                j2 -= adGroup.durationsUs[r2];
                r2++;
            }
        }
        return j2;
    }

    public static long getStreamPositionUsForContent(long j, int r12, AdPlaybackState adPlaybackState) {
        if (r12 == -1) {
            r12 = adPlaybackState.adGroupCount;
        }
        long j2 = 0;
        for (int r0 = adPlaybackState.removedAdGroupCount; r0 < r12; r0++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(r0);
            if (adGroup.timeUs == Long.MIN_VALUE || adGroup.timeUs > j) {
                break;
            }
            long j3 = adGroup.timeUs + j2;
            for (int r6 = 0; r6 < getAdCountInGroup(adPlaybackState, r0); r6++) {
                j2 += adGroup.durationsUs[r6];
            }
            j2 -= adGroup.contentResumeOffsetUs;
            if (adGroup.timeUs + adGroup.contentResumeOffsetUs > j) {
                return Math.max(j3, j + j2);
            }
        }
        return j + j2;
    }

    public static long getMediaPeriodPositionUsForContent(long j, int r11, AdPlaybackState adPlaybackState) {
        if (r11 == -1) {
            r11 = adPlaybackState.adGroupCount;
        }
        long j2 = 0;
        for (int r0 = adPlaybackState.removedAdGroupCount; r0 < r11; r0++) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(r0);
            if (adGroup.timeUs == Long.MIN_VALUE || adGroup.timeUs > j - j2) {
                break;
            }
            for (int r4 = 0; r4 < getAdCountInGroup(adPlaybackState, r0); r4++) {
                j2 += adGroup.durationsUs[r4];
            }
            j2 -= adGroup.contentResumeOffsetUs;
            long j3 = j - j2;
            if (adGroup.timeUs + adGroup.contentResumeOffsetUs > j3) {
                return Math.max(adGroup.timeUs, j3);
            }
        }
        return j - j2;
    }

    public static int getAdCountInGroup(AdPlaybackState adPlaybackState, int r2) {
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(r2);
        if (adGroup.count == -1) {
            return 0;
        }
        return adGroup.count;
    }

    private static AdPlaybackState correctFollowingAdGroupTimes(AdPlaybackState adPlaybackState, int r4, long j, long j2) {
        long j3 = (-j) + j2;
        while (true) {
            r4++;
            if (r4 >= adPlaybackState.adGroupCount) {
                return adPlaybackState;
            }
            long j4 = adPlaybackState.getAdGroup(r4).timeUs;
            if (j4 != Long.MIN_VALUE) {
                adPlaybackState = adPlaybackState.withAdGroupTimeUs(r4, j4 + j3);
            }
        }
    }
}
