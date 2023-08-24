package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes2.dex */
public final class AdPlaybackState implements Bundleable {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    private static final int FIELD_AD_GROUPS = 1;
    private static final int FIELD_AD_RESUME_POSITION_US = 2;
    private static final int FIELD_CONTENT_DURATION_US = 3;
    private static final int FIELD_REMOVED_AD_GROUP_COUNT = 4;
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;
    public static final AdPlaybackState NONE = new AdPlaybackState(null, new AdGroup[0], 0, C1856C.TIME_UNSET, 0);
    private static final AdGroup REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
    public static final Bundleable.Creator<AdPlaybackState> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.source.ads.AdPlaybackState$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            AdPlaybackState fromBundle;
            fromBundle = AdPlaybackState.fromBundle(bundle);
            return fromBundle;
        }
    };

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AdState {
    }

    /* loaded from: classes2.dex */
    public static final class AdGroup implements Bundleable {
        public static final Bundleable.Creator<AdGroup> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.source.ads.AdPlaybackState$AdGroup$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.Bundleable.Creator
            public final Bundleable fromBundle(Bundle bundle) {
                AdPlaybackState.AdGroup fromBundle;
                fromBundle = AdPlaybackState.AdGroup.fromBundle(bundle);
                return fromBundle;
            }
        };
        private static final int FIELD_CONTENT_RESUME_OFFSET_US = 5;
        private static final int FIELD_COUNT = 1;
        private static final int FIELD_DURATIONS_US = 4;
        private static final int FIELD_IS_SERVER_SIDE_INSERTED = 6;
        private static final int FIELD_STATES = 3;
        private static final int FIELD_TIME_US = 0;
        private static final int FIELD_URIS = 2;
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final boolean isServerSideInserted;
        public final int[] states;
        public final long timeUs;
        public final Uri[] uris;

        public AdGroup(long j) {
            this(j, -1, new int[0], new Uri[0], new long[0], 0L, false);
        }

        private AdGroup(long j, int r5, int[] r6, Uri[] uriArr, long[] jArr, long j2, boolean z) {
            Assertions.checkArgument(r6.length == uriArr.length);
            this.timeUs = j;
            this.count = r5;
            this.states = r6;
            this.uris = uriArr;
            this.durationsUs = jArr;
            this.contentResumeOffsetUs = j2;
            this.isServerSideInserted = z;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int r4) {
            int r42 = r4 + 1;
            while (true) {
                int[] r1 = this.states;
                if (r42 >= r1.length || this.isServerSideInserted || r1[r42] == 0 || r1[r42] == 1) {
                    break;
                }
                r42++;
            }
            return r42;
        }

        public boolean shouldPlayAdGroup() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public boolean hasUnplayedAds() {
            if (this.count == -1) {
                return true;
            }
            for (int r2 = 0; r2 < this.count; r2++) {
                int[] r3 = this.states;
                if (r3[r2] == 0 || r3[r2] == 1) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AdGroup adGroup = (AdGroup) obj;
            return this.timeUs == adGroup.timeUs && this.count == adGroup.count && Arrays.equals(this.uris, adGroup.uris) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs) && this.contentResumeOffsetUs == adGroup.contentResumeOffsetUs && this.isServerSideInserted == adGroup.isServerSideInserted;
        }

        public int hashCode() {
            long j = this.timeUs;
            long j2 = this.contentResumeOffsetUs;
            return (((((((((((this.count * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Arrays.hashCode(this.uris)) * 31) + Arrays.hashCode(this.states)) * 31) + Arrays.hashCode(this.durationsUs)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.isServerSideInserted ? 1 : 0);
        }

        public AdGroup withTimeUs(long j) {
            return new AdGroup(j, this.count, this.states, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdCount(int r12) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, r12);
            long[] copyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, r12);
            return new AdGroup(this.timeUs, r12, copyStatesWithSpaceForAdCount, (Uri[]) Arrays.copyOf(this.uris, r12), copyDurationsUsWithSpaceForAdCount, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdUri(Uri uri, int r14) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, r14 + 1);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            Uri[] uriArr = (Uri[]) Arrays.copyOf(this.uris, copyStatesWithSpaceForAdCount.length);
            uriArr[r14] = uri;
            copyStatesWithSpaceForAdCount[r14] = 1;
            return new AdGroup(this.timeUs, this.count, copyStatesWithSpaceForAdCount, uriArr, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdState(int r18, int r19) {
            int r3 = this.count;
            boolean z = false;
            Assertions.checkArgument(r3 == -1 || r19 < r3);
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, r19 + 1);
            Assertions.checkArgument((copyStatesWithSpaceForAdCount[r19] == 0 || copyStatesWithSpaceForAdCount[r19] == 1 || copyStatesWithSpaceForAdCount[r19] == r18) ? true : true);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            Uri[] uriArr = this.uris;
            if (uriArr.length != copyStatesWithSpaceForAdCount.length) {
                uriArr = (Uri[]) Arrays.copyOf(uriArr, copyStatesWithSpaceForAdCount.length);
            }
            copyStatesWithSpaceForAdCount[r19] = r18;
            return new AdGroup(this.timeUs, this.count, copyStatesWithSpaceForAdCount, uriArr, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdDurationsUs(long[] jArr) {
            int length = jArr.length;
            Uri[] uriArr = this.uris;
            if (length < uriArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, uriArr.length);
            } else if (this.count != -1 && jArr.length > uriArr.length) {
                jArr = Arrays.copyOf(jArr, uriArr.length);
            }
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, jArr, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withContentResumeOffsetUs(long j) {
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, this.durationsUs, j, this.isServerSideInserted);
        }

        public AdGroup withIsServerSideInserted(boolean z) {
            return new AdGroup(this.timeUs, this.count, this.states, this.uris, this.durationsUs, this.contentResumeOffsetUs, z);
        }

        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(this.timeUs, 0, new int[0], new Uri[0], new long[0], this.contentResumeOffsetUs, this.isServerSideInserted);
            }
            int[] r0 = this.states;
            int length = r0.length;
            int[] copyOf = Arrays.copyOf(r0, length);
            for (int r1 = 0; r1 < length; r1++) {
                if (copyOf[r1] == 1 || copyOf[r1] == 0) {
                    copyOf[r1] = 2;
                }
            }
            return new AdGroup(this.timeUs, length, copyOf, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAllAdsReset() {
            if (this.count == -1) {
                return this;
            }
            int[] r0 = this.states;
            int length = r0.length;
            int[] copyOf = Arrays.copyOf(r0, length);
            for (int r1 = 0; r1 < length; r1++) {
                if (copyOf[r1] == 3 || copyOf[r1] == 2 || copyOf[r1] == 4) {
                    copyOf[r1] = this.uris[r1] == null ? 0 : 1;
                }
            }
            return new AdGroup(this.timeUs, length, copyOf, this.uris, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] r2, int r3) {
            int length = r2.length;
            int max = Math.max(r3, length);
            int[] copyOf = Arrays.copyOf(r2, max);
            Arrays.fill(copyOf, length, max, 0);
            return copyOf;
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int r4) {
            int length = jArr.length;
            int max = Math.max(r4, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, (long) C1856C.TIME_UNSET);
            return copyOf;
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong(keyForField(0), this.timeUs);
            bundle.putInt(keyForField(1), this.count);
            bundle.putParcelableArrayList(keyForField(2), new ArrayList<>(Arrays.asList(this.uris)));
            bundle.putIntArray(keyForField(3), this.states);
            bundle.putLongArray(keyForField(4), this.durationsUs);
            bundle.putLong(keyForField(5), this.contentResumeOffsetUs);
            bundle.putBoolean(keyForField(6), this.isServerSideInserted);
            return bundle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AdGroup fromBundle(Bundle bundle) {
            long j = bundle.getLong(keyForField(0));
            int r5 = bundle.getInt(keyForField(1), -1);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(2));
            int[] intArray = bundle.getIntArray(keyForField(3));
            long[] longArray = bundle.getLongArray(keyForField(4));
            long j2 = bundle.getLong(keyForField(5));
            boolean z = bundle.getBoolean(keyForField(6));
            if (intArray == null) {
                intArray = new int[0];
            }
            return new AdGroup(j, r5, intArray, parcelableArrayList == null ? new Uri[0] : (Uri[]) parcelableArrayList.toArray(new Uri[0]), longArray == null ? new long[0] : longArray, j2, z);
        }

        private static String keyForField(int r1) {
            return Integer.toString(r1, 36);
        }
    }

    public AdPlaybackState(Object obj, long... jArr) {
        this(obj, createEmptyAdGroups(jArr), 0L, C1856C.TIME_UNSET, 0);
    }

    private AdPlaybackState(Object obj, AdGroup[] adGroupArr, long j, long j2, int r7) {
        this.adsId = obj;
        this.adResumePositionUs = j;
        this.contentDurationUs = j2;
        this.adGroupCount = adGroupArr.length + r7;
        this.adGroups = adGroupArr;
        this.removedAdGroupCount = r7;
    }

    public AdGroup getAdGroup(int r3) {
        int r0 = this.removedAdGroupCount;
        if (r3 < r0) {
            return REMOVED_AD_GROUP;
        }
        return this.adGroups[r3 - r0];
    }

    public int getAdGroupIndexForPositionUs(long j, long j2) {
        int r0 = this.adGroupCount - 1;
        while (r0 >= 0 && isPositionBeforeAdGroup(j, j2, r0)) {
            r0--;
        }
        if (r0 < 0 || !getAdGroup(r0).hasUnplayedAds()) {
            return -1;
        }
        return r0;
    }

    public int getAdGroupIndexAfterPositionUs(long j, long j2) {
        if (j != Long.MIN_VALUE) {
            if (j2 == C1856C.TIME_UNSET || j < j2) {
                int r9 = this.removedAdGroupCount;
                while (r9 < this.adGroupCount && ((getAdGroup(r9).timeUs != Long.MIN_VALUE && getAdGroup(r9).timeUs <= j) || !getAdGroup(r9).shouldPlayAdGroup())) {
                    r9++;
                }
                if (r9 < this.adGroupCount) {
                    return r9;
                }
                return -1;
            }
            return -1;
        }
        return -1;
    }

    public boolean isAdInErrorState(int r4, int r5) {
        if (r4 >= this.adGroupCount) {
            return false;
        }
        AdGroup adGroup = getAdGroup(r4);
        return adGroup.count != -1 && r5 < adGroup.count && adGroup.states[r5] == 4;
    }

    public AdPlaybackState withAdGroupTimeUs(int r10, long j) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = this.adGroups[r102].withTimeUs(j);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withNewAdGroup(int r10, long j) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup adGroup = new AdGroup(j);
        AdGroup[] adGroupArr = (AdGroup[]) Util.nullSafeArrayAppend(this.adGroups, adGroup);
        System.arraycopy(adGroupArr, r102, adGroupArr, r102 + 1, this.adGroups.length - r102);
        adGroupArr[r102] = adGroup;
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdCount(int r10, int r11) {
        Assertions.checkArgument(r11 > 0);
        int r102 = r10 - this.removedAdGroupCount;
        if (this.adGroups[r102].count == r11) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = this.adGroups[r102].withAdCount(r11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdUri(int r10, int r11, Uri uri) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAdUri(uri, r11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withPlayedAd(int r10, int r11) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAdState(3, r11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAd(int r10, int r11) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAdState(2, r11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdLoadError(int r10, int r11) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAdState(4, r11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAdGroup(int r10) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAllAdsSkipped();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        Assertions.checkState(this.removedAdGroupCount == 0);
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        for (int r1 = 0; r1 < this.adGroupCount; r1++) {
            adGroupArr2[r1] = adGroupArr2[r1].withAdDurationsUs(jArr[r1]);
        }
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdDurationsUs(int r10, long... jArr) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAdDurationsUs(jArr);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdResumePositionUs(long j) {
        return this.adResumePositionUs == j ? this : new AdPlaybackState(this.adsId, this.adGroups, j, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withContentDurationUs(long j) {
        return this.contentDurationUs == j ? this : new AdPlaybackState(this.adsId, this.adGroups, this.adResumePositionUs, j, this.removedAdGroupCount);
    }

    public AdPlaybackState withRemovedAdGroupCount(int r11) {
        int r0 = this.removedAdGroupCount;
        if (r0 == r11) {
            return this;
        }
        Assertions.checkArgument(r11 > r0);
        int r02 = this.adGroupCount - r11;
        AdGroup[] adGroupArr = new AdGroup[r02];
        System.arraycopy(this.adGroups, r11 - this.removedAdGroupCount, adGroupArr, 0, r02);
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, r11);
    }

    public AdPlaybackState withContentResumeOffsetUs(int r10, long j) {
        int r102 = r10 - this.removedAdGroupCount;
        if (this.adGroups[r102].contentResumeOffsetUs == j) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withContentResumeOffsetUs(j);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withIsServerSideInserted(int r10, boolean z) {
        int r102 = r10 - this.removedAdGroupCount;
        if (this.adGroups[r102].isServerSideInserted == z) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withIsServerSideInserted(z);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withResetAdGroup(int r10) {
        int r102 = r10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[r102] = adGroupArr2[r102].withAllAdsReset();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public static AdPlaybackState fromAdPlaybackState(Object obj, AdPlaybackState adPlaybackState) {
        int r1 = adPlaybackState.adGroupCount - adPlaybackState.removedAdGroupCount;
        AdGroup[] adGroupArr = new AdGroup[r1];
        for (int r2 = 0; r2 < r1; r2++) {
            AdGroup adGroup = adPlaybackState.adGroups[r2];
            adGroupArr[r2] = new AdGroup(adGroup.timeUs, adGroup.count, Arrays.copyOf(adGroup.states, adGroup.states.length), (Uri[]) Arrays.copyOf(adGroup.uris, adGroup.uris.length), Arrays.copyOf(adGroup.durationsUs, adGroup.durationsUs.length), adGroup.contentResumeOffsetUs, adGroup.isServerSideInserted);
        }
        return new AdPlaybackState(obj, adGroupArr, adPlaybackState.adResumePositionUs, adPlaybackState.contentDurationUs, adPlaybackState.removedAdGroupCount);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
        return Util.areEqual(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && this.removedAdGroupCount == adPlaybackState.removedAdGroupCount && Arrays.equals(this.adGroups, adPlaybackState.adGroups);
    }

    public int hashCode() {
        int r0 = this.adGroupCount * 31;
        Object obj = this.adsId;
        return ((((((((r0 + (obj == null ? 0 : obj.hashCode())) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31) + Arrays.hashCode(this.adGroups);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdPlaybackState(adsId=");
        sb.append(this.adsId);
        sb.append(", adResumePositionUs=");
        sb.append(this.adResumePositionUs);
        sb.append(", adGroups=[");
        for (int r2 = 0; r2 < this.adGroups.length; r2++) {
            sb.append("adGroup(timeUs=");
            sb.append(this.adGroups[r2].timeUs);
            sb.append(", ads=[");
            for (int r3 = 0; r3 < this.adGroups[r2].states.length; r3++) {
                sb.append("ad(state=");
                int r5 = this.adGroups[r2].states[r3];
                if (r5 == 0) {
                    sb.append('_');
                } else if (r5 == 1) {
                    sb.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
                } else if (r5 == 2) {
                    sb.append('S');
                } else if (r5 == 3) {
                    sb.append('P');
                } else if (r5 == 4) {
                    sb.append('!');
                } else {
                    sb.append('?');
                }
                sb.append(", durationUs=");
                sb.append(this.adGroups[r2].durationsUs[r3]);
                sb.append(')');
                if (r3 < this.adGroups[r2].states.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("])");
            if (r2 < this.adGroups.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("])");
        return sb.toString();
    }

    private boolean isPositionBeforeAdGroup(long j, long j2, int r11) {
        if (j == Long.MIN_VALUE) {
            return false;
        }
        long j3 = getAdGroup(r11).timeUs;
        return j3 == Long.MIN_VALUE ? j2 == C1856C.TIME_UNSET || j < j2 : j < j3;
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (AdGroup adGroup : this.adGroups) {
            arrayList.add(adGroup.toBundle());
        }
        bundle.putParcelableArrayList(keyForField(1), arrayList);
        bundle.putLong(keyForField(2), this.adResumePositionUs);
        bundle.putLong(keyForField(3), this.contentDurationUs);
        bundle.putInt(keyForField(4), this.removedAdGroupCount);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AdPlaybackState fromBundle(Bundle bundle) {
        AdGroup[] adGroupArr;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(1));
        if (parcelableArrayList == null) {
            adGroupArr = new AdGroup[0];
        } else {
            AdGroup[] adGroupArr2 = new AdGroup[parcelableArrayList.size()];
            for (int r1 = 0; r1 < parcelableArrayList.size(); r1++) {
                adGroupArr2[r1] = AdGroup.CREATOR.fromBundle((Bundle) parcelableArrayList.get(r1));
            }
            adGroupArr = adGroupArr2;
        }
        return new AdPlaybackState(null, adGroupArr, bundle.getLong(keyForField(2), 0L), bundle.getLong(keyForField(3), C1856C.TIME_UNSET), bundle.getInt(keyForField(4)));
    }

    private static String keyForField(int r1) {
        return Integer.toString(r1, 36);
    }

    private static AdGroup[] createEmptyAdGroups(long[] jArr) {
        int length = jArr.length;
        AdGroup[] adGroupArr = new AdGroup[length];
        for (int r2 = 0; r2 < length; r2++) {
            adGroupArr[r2] = new AdGroup(jArr[r2]);
        }
        return adGroupArr;
    }
}
