package com.google.android.exoplayer2.trackselection;

import android.util.Pair;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/* loaded from: classes2.dex */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;

    protected abstract Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] r2, int[] r3, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException;

    /* loaded from: classes2.dex */
    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface RendererSupport {
        }

        MappedTrackInfo(String[] strArr, int[] r2, TrackGroupArray[] trackGroupArrayArr, int[] r4, int[][][] r5, TrackGroupArray trackGroupArray) {
            this.rendererNames = strArr;
            this.rendererTrackTypes = r2;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = r5;
            this.rendererMixedMimeTypeAdaptiveSupports = r4;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = r2.length;
        }

        public int getRendererCount() {
            return this.rendererCount;
        }

        public String getRendererName(int r2) {
            return this.rendererNames[r2];
        }

        public int getRendererType(int r2) {
            return this.rendererTrackTypes[r2];
        }

        public TrackGroupArray getTrackGroups(int r2) {
            return this.rendererTrackGroups[r2];
        }

        public int getRendererSupport(int r11) {
            int[][] r112;
            int r3 = 0;
            for (int[] r4 : this.rendererFormatSupports[r11]) {
                for (int r7 : r4) {
                    int formatSupport = RendererCapabilities.CC.getFormatSupport(r7);
                    int r8 = 2;
                    if (formatSupport == 0 || formatSupport == 1 || formatSupport == 2) {
                        r8 = 1;
                    } else if (formatSupport != 3) {
                        if (formatSupport == 4) {
                            return 3;
                        }
                        throw new IllegalStateException();
                    }
                    r3 = Math.max(r3, r8);
                }
            }
            return r3;
        }

        public int getTypeSupport(int r4) {
            int r1 = 0;
            for (int r0 = 0; r0 < this.rendererCount; r0++) {
                if (this.rendererTrackTypes[r0] == r4) {
                    r1 = Math.max(r1, getRendererSupport(r0));
                }
            }
            return r1;
        }

        public int getCapabilities(int r2, int r3, int r4) {
            return this.rendererFormatSupports[r2][r3][r4];
        }

        public int getTrackSupport(int r1, int r2, int r3) {
            return RendererCapabilities.CC.getFormatSupport(getCapabilities(r1, r2, r3));
        }

        public int getAdaptiveSupport(int r7, int r8, boolean z) {
            int r0 = this.rendererTrackGroups[r7].get(r8).length;
            int[] r1 = new int[r0];
            int r3 = 0;
            for (int r2 = 0; r2 < r0; r2++) {
                int trackSupport = getTrackSupport(r7, r8, r2);
                if (trackSupport == 4 || (z && trackSupport == 3)) {
                    r1[r3] = r2;
                    r3++;
                }
            }
            return getAdaptiveSupport(r7, r8, Arrays.copyOf(r1, r3));
        }

        public int getAdaptiveSupport(int r8, int r9, int[] r10) {
            int r0 = 0;
            String str = null;
            boolean z = false;
            int r2 = 0;
            int r3 = 16;
            while (r0 < r10.length) {
                String str2 = this.rendererTrackGroups[r8].get(r9).getFormat(r10[r0]).sampleMimeType;
                int r6 = r2 + 1;
                if (r2 == 0) {
                    str = str2;
                } else {
                    z |= !Util.areEqual(str, str2);
                }
                r3 = Math.min(r3, RendererCapabilities.CC.getAdaptiveSupport(this.rendererFormatSupports[r8][r9][r0]));
                r0++;
                r2 = r6;
            }
            return z ? Math.min(r3, this.rendererMixedMimeTypeAdaptiveSupports[r8]) : r3;
        }

        public TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException {
        int[] formatSupport;
        int[] r2 = new int[rendererCapabilitiesArr.length + 1];
        int length = rendererCapabilitiesArr.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length];
        int[][][] r6 = new int[rendererCapabilitiesArr.length + 1][];
        for (int r8 = 0; r8 < length; r8++) {
            trackGroupArr[r8] = new TrackGroup[trackGroupArray.length];
            r6[r8] = new int[trackGroupArray.length];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int r82 = 0; r82 < trackGroupArray.length; r82++) {
            TrackGroup trackGroup = trackGroupArray.get(r82);
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup, r2, trackGroup.type == 5);
            if (findRenderer == rendererCapabilitiesArr.length) {
                formatSupport = new int[trackGroup.length];
            } else {
                formatSupport = getFormatSupport(rendererCapabilitiesArr[findRenderer], trackGroup);
            }
            int r12 = r2[findRenderer];
            trackGroupArr[findRenderer][r12] = trackGroup;
            r6[findRenderer][r12] = formatSupport;
            r2[findRenderer] = r2[findRenderer] + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        String[] strArr = new String[rendererCapabilitiesArr.length];
        int[] r9 = new int[rendererCapabilitiesArr.length];
        for (int r7 = 0; r7 < rendererCapabilitiesArr.length; r7++) {
            int r1 = r2[r7];
            trackGroupArrayArr[r7] = new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[r7], r1));
            r6[r7] = (int[][]) Util.nullSafeArrayCopy(r6[r7], r1);
            strArr[r7] = rendererCapabilitiesArr[r7].getName();
            r9[r7] = rendererCapabilitiesArr[r7].getTrackType();
        }
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(strArr, r9, trackGroupArrayArr, mixedMimeTypeAdaptationSupports, r6, new TrackGroupArray((TrackGroup[]) Util.nullSafeArrayCopy(trackGroupArr[rendererCapabilitiesArr.length], r2[rendererCapabilitiesArr.length])));
        Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks = selectTracks(mappedTrackInfo, r6, mixedMimeTypeAdaptationSupports, mediaPeriodId, timeline);
        return new TrackSelectorResult((RendererConfiguration[]) selectTracks.first, (ExoTrackSelection[]) selectTracks.second, TrackSelectionUtil.buildTracks(mappedTrackInfo, (TrackSelection[]) selectTracks.second), mappedTrackInfo);
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup, int[] r12, boolean z) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int r4 = 0;
        boolean z2 = true;
        for (int r3 = 0; r3 < rendererCapabilitiesArr.length; r3++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[r3];
            int r8 = 0;
            for (int r7 = 0; r7 < trackGroup.length; r7++) {
                r8 = Math.max(r8, RendererCapabilities.CC.getFormatSupport(rendererCapabilities.supportsFormat(trackGroup.getFormat(r7))));
            }
            boolean z3 = r12[r3] == 0;
            if (r8 > r4 || (r8 == r4 && z && !z2 && z3)) {
                length = r3;
                z2 = z3;
                r4 = r8;
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] r0 = new int[trackGroup.length];
        for (int r1 = 0; r1 < trackGroup.length; r1++) {
            r0[r1] = rendererCapabilities.supportsFormat(trackGroup.getFormat(r1));
        }
        return r0;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int[] r1 = new int[length];
        for (int r2 = 0; r2 < length; r2++) {
            r1[r2] = rendererCapabilitiesArr[r2].supportsMixedMimeTypeAdaptation();
        }
        return r1;
    }
}
