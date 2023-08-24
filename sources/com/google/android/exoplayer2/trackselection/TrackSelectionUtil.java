package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class TrackSelectionUtil {

    /* loaded from: classes2.dex */
    public interface AdaptiveTrackSelectionFactory {
        ExoTrackSelection createAdaptiveTrackSelection(ExoTrackSelection.Definition definition);
    }

    private TrackSelectionUtil() {
    }

    public static ExoTrackSelection[] createTrackSelectionsForDefinitions(ExoTrackSelection.Definition[] definitionArr, AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory) {
        ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
        boolean z = false;
        for (int r2 = 0; r2 < definitionArr.length; r2++) {
            ExoTrackSelection.Definition definition = definitionArr[r2];
            if (definition != null) {
                if (definition.tracks.length > 1 && !z) {
                    exoTrackSelectionArr[r2] = adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
                    z = true;
                } else {
                    exoTrackSelectionArr[r2] = new FixedTrackSelection(definition.group, definition.tracks[0], definition.type);
                }
            }
        }
        return exoTrackSelectionArr;
    }

    public static DefaultTrackSelector.Parameters updateParametersWithOverride(DefaultTrackSelector.Parameters parameters, int r1, TrackGroupArray trackGroupArray, boolean z, DefaultTrackSelector.SelectionOverride selectionOverride) {
        DefaultTrackSelector.Parameters.Builder rendererDisabled = parameters.buildUpon().clearSelectionOverrides(r1).setRendererDisabled(r1, z);
        if (selectionOverride != null) {
            rendererDisabled.setSelectionOverride(r1, trackGroupArray, selectionOverride);
        }
        return rendererDisabled.build();
    }

    public static LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int r5 = 0;
        for (int r4 = 0; r4 < length; r4++) {
            if (exoTrackSelection.isBlacklisted(r4, elapsedRealtime)) {
                r5++;
            }
        }
        return new LoadErrorHandlingPolicy.FallbackOptions(1, 0, length, r5);
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelection[] trackSelectionArr) {
        List[] listArr = new List[trackSelectionArr.length];
        for (int r1 = 0; r1 < trackSelectionArr.length; r1++) {
            TrackSelection trackSelection = trackSelectionArr[r1];
            listArr[r1] = trackSelection != null ? ImmutableList.m408of(trackSelection) : ImmutableList.m409of();
        }
        return buildTracks(mappedTrackInfo, listArr);
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, List<? extends TrackSelection>[] listArr) {
        boolean z;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int r3 = 0; r3 < mappedTrackInfo.getRendererCount(); r3++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(r3);
            List<? extends TrackSelection> list = listArr[r3];
            for (int r6 = 0; r6 < trackGroups.length; r6++) {
                TrackGroup trackGroup = trackGroups.get(r6);
                boolean z2 = mappedTrackInfo.getAdaptiveSupport(r3, r6, false) != 0;
                int[] r10 = new int[trackGroup.length];
                boolean[] zArr = new boolean[trackGroup.length];
                for (int r12 = 0; r12 < trackGroup.length; r12++) {
                    r10[r12] = mappedTrackInfo.getTrackSupport(r3, r6, r12);
                    int r13 = 0;
                    while (true) {
                        if (r13 >= list.size()) {
                            z = false;
                            break;
                        }
                        TrackSelection trackSelection = list.get(r13);
                        if (trackSelection.getTrackGroup().equals(trackGroup) && trackSelection.indexOf(r12) != -1) {
                            z = true;
                            break;
                        }
                        r13++;
                    }
                    zArr[r12] = z;
                }
                builder.add((ImmutableList.Builder) new Tracks.Group(trackGroup, z2, r10, zArr));
            }
        }
        TrackGroupArray unmappedTrackGroups = mappedTrackInfo.getUnmappedTrackGroups();
        for (int r32 = 0; r32 < unmappedTrackGroups.length; r32++) {
            TrackGroup trackGroup2 = unmappedTrackGroups.get(r32);
            int[] r5 = new int[trackGroup2.length];
            Arrays.fill(r5, 0);
            builder.add((ImmutableList.Builder) new Tracks.Group(trackGroup2, false, r5, new boolean[trackGroup2.length]));
        }
        return new Tracks(builder.build());
    }
}
