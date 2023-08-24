package com.google.android.exoplayer2.p012ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.p012ui.TrackSelectionView;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionOverride;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.exoplayer2.ui.TrackSelectionView */
/* loaded from: classes2.dex */
public class TrackSelectionView extends LinearLayout {
    private boolean allowAdaptiveSelections;
    private boolean allowMultipleOverrides;
    private final ComponentListener componentListener;
    private final CheckedTextView defaultView;
    private final CheckedTextView disableView;
    private final LayoutInflater inflater;
    private boolean isDisabled;
    private TrackSelectionListener listener;
    private final Map<TrackGroup, TrackSelectionOverride> overrides;
    private final int selectableItemBackgroundResourceId;
    private final List<Tracks.Group> trackGroups;
    private Comparator<TrackInfo> trackInfoComparator;
    private TrackNameProvider trackNameProvider;
    private CheckedTextView[][] trackViews;

    /* renamed from: com.google.android.exoplayer2.ui.TrackSelectionView$TrackSelectionListener */
    /* loaded from: classes2.dex */
    public interface TrackSelectionListener {
        void onTrackSelectionChanged(boolean z, Map<TrackGroup, TrackSelectionOverride> map);
    }

    public static Map<TrackGroup, TrackSelectionOverride> filterOverrides(Map<TrackGroup, TrackSelectionOverride> map, List<Tracks.Group> list, boolean z) {
        HashMap hashMap = new HashMap();
        for (int r1 = 0; r1 < list.size(); r1++) {
            TrackSelectionOverride trackSelectionOverride = map.get(list.get(r1).getMediaTrackGroup());
            if (trackSelectionOverride != null && (z || hashMap.isEmpty())) {
                hashMap.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            }
        }
        return hashMap;
    }

    public TrackSelectionView(Context context) {
        this(context, null);
    }

    public TrackSelectionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TrackSelectionView(Context context, AttributeSet attributeSet, int r8) {
        super(context, attributeSet, r8);
        setOrientation(1);
        setSaveFromParentEnabled(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843534});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        this.selectableItemBackgroundResourceId = resourceId;
        obtainStyledAttributes.recycle();
        LayoutInflater from = LayoutInflater.from(context);
        this.inflater = from;
        ComponentListener componentListener = new ComponentListener();
        this.componentListener = componentListener;
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.trackGroups = new ArrayList();
        this.overrides = new HashMap();
        CheckedTextView checkedTextView = (CheckedTextView) from.inflate(17367055, (ViewGroup) this, false);
        this.disableView = checkedTextView;
        checkedTextView.setBackgroundResource(resourceId);
        checkedTextView.setText(C2058R.string.exo_track_selection_none);
        checkedTextView.setEnabled(false);
        checkedTextView.setFocusable(true);
        checkedTextView.setOnClickListener(componentListener);
        checkedTextView.setVisibility(8);
        addView(checkedTextView);
        addView(from.inflate(C2058R.layout.exo_list_divider, (ViewGroup) this, false));
        CheckedTextView checkedTextView2 = (CheckedTextView) from.inflate(17367055, (ViewGroup) this, false);
        this.defaultView = checkedTextView2;
        checkedTextView2.setBackgroundResource(resourceId);
        checkedTextView2.setText(C2058R.string.exo_track_selection_auto);
        checkedTextView2.setEnabled(false);
        checkedTextView2.setFocusable(true);
        checkedTextView2.setOnClickListener(componentListener);
        addView(checkedTextView2);
    }

    public void setAllowAdaptiveSelections(boolean z) {
        if (this.allowAdaptiveSelections != z) {
            this.allowAdaptiveSelections = z;
            updateViews();
        }
    }

    public void setAllowMultipleOverrides(boolean z) {
        if (this.allowMultipleOverrides != z) {
            this.allowMultipleOverrides = z;
            if (!z && this.overrides.size() > 1) {
                Map<TrackGroup, TrackSelectionOverride> filterOverrides = filterOverrides(this.overrides, this.trackGroups, false);
                this.overrides.clear();
                this.overrides.putAll(filterOverrides);
            }
            updateViews();
        }
    }

    public void setShowDisableOption(boolean z) {
        this.disableView.setVisibility(z ? 0 : 8);
    }

    public void setTrackNameProvider(TrackNameProvider trackNameProvider) {
        this.trackNameProvider = (TrackNameProvider) Assertions.checkNotNull(trackNameProvider);
        updateViews();
    }

    public void init(List<Tracks.Group> list, boolean z, Map<TrackGroup, TrackSelectionOverride> map, final Comparator<Format> comparator, TrackSelectionListener trackSelectionListener) {
        this.isDisabled = z;
        this.trackInfoComparator = comparator == null ? null : new Comparator() { // from class: com.google.android.exoplayer2.ui.TrackSelectionView$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = comparator.compare(((TrackSelectionView.TrackInfo) obj).getFormat(), ((TrackSelectionView.TrackInfo) obj2).getFormat());
                return compare;
            }
        };
        this.listener = trackSelectionListener;
        this.trackGroups.clear();
        this.trackGroups.addAll(list);
        this.overrides.clear();
        this.overrides.putAll(filterOverrides(map, list, this.allowMultipleOverrides));
        updateViews();
    }

    public boolean getIsDisabled() {
        return this.isDisabled;
    }

    public Map<TrackGroup, TrackSelectionOverride> getOverrides() {
        return this.overrides;
    }

    private void updateViews() {
        for (int childCount = getChildCount() - 1; childCount >= 3; childCount--) {
            removeViewAt(childCount);
        }
        if (this.trackGroups.isEmpty()) {
            this.disableView.setEnabled(false);
            this.defaultView.setEnabled(false);
            return;
        }
        this.disableView.setEnabled(true);
        this.defaultView.setEnabled(true);
        this.trackViews = new CheckedTextView[this.trackGroups.size()];
        boolean shouldEnableMultiGroupSelection = shouldEnableMultiGroupSelection();
        for (int r3 = 0; r3 < this.trackGroups.size(); r3++) {
            Tracks.Group group = this.trackGroups.get(r3);
            boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(group);
            this.trackViews[r3] = new CheckedTextView[group.length];
            int r6 = group.length;
            TrackInfo[] trackInfoArr = new TrackInfo[r6];
            for (int r8 = 0; r8 < group.length; r8++) {
                trackInfoArr[r8] = new TrackInfo(group, r8);
            }
            Comparator<TrackInfo> comparator = this.trackInfoComparator;
            if (comparator != null) {
                Arrays.sort(trackInfoArr, comparator);
            }
            for (int r82 = 0; r82 < r6; r82++) {
                if (r82 == 0) {
                    addView(this.inflater.inflate(C2058R.layout.exo_list_divider, (ViewGroup) this, false));
                }
                CheckedTextView checkedTextView = (CheckedTextView) this.inflater.inflate((shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection) ? 17367056 : 17367055, (ViewGroup) this, false);
                checkedTextView.setBackgroundResource(this.selectableItemBackgroundResourceId);
                checkedTextView.setText(this.trackNameProvider.getTrackName(trackInfoArr[r82].getFormat()));
                checkedTextView.setTag(trackInfoArr[r82]);
                if (group.isTrackSupported(r82)) {
                    checkedTextView.setFocusable(true);
                    checkedTextView.setOnClickListener(this.componentListener);
                } else {
                    checkedTextView.setFocusable(false);
                    checkedTextView.setEnabled(false);
                }
                this.trackViews[r3][r82] = checkedTextView;
                addView(checkedTextView);
            }
        }
        updateViewStates();
    }

    private void updateViewStates() {
        this.disableView.setChecked(this.isDisabled);
        this.defaultView.setChecked(!this.isDisabled && this.overrides.size() == 0);
        for (int r0 = 0; r0 < this.trackViews.length; r0++) {
            TrackSelectionOverride trackSelectionOverride = this.overrides.get(this.trackGroups.get(r0).getMediaTrackGroup());
            int r3 = 0;
            while (true) {
                CheckedTextView[][] checkedTextViewArr = this.trackViews;
                if (r3 < checkedTextViewArr[r0].length) {
                    if (trackSelectionOverride != null) {
                        this.trackViews[r0][r3].setChecked(trackSelectionOverride.trackIndices.contains(Integer.valueOf(((TrackInfo) Assertions.checkNotNull(checkedTextViewArr[r0][r3].getTag())).trackIndex)));
                    } else {
                        checkedTextViewArr[r0][r3].setChecked(false);
                    }
                    r3++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClick(View view) {
        if (view == this.disableView) {
            onDisableViewClicked();
        } else if (view == this.defaultView) {
            onDefaultViewClicked();
        } else {
            onTrackViewClicked(view);
        }
        updateViewStates();
        TrackSelectionListener trackSelectionListener = this.listener;
        if (trackSelectionListener != null) {
            trackSelectionListener.onTrackSelectionChanged(getIsDisabled(), getOverrides());
        }
    }

    private void onDisableViewClicked() {
        this.isDisabled = true;
        this.overrides.clear();
    }

    private void onDefaultViewClicked() {
        this.isDisabled = false;
        this.overrides.clear();
    }

    private void onTrackViewClicked(View view) {
        boolean z = false;
        this.isDisabled = false;
        TrackInfo trackInfo = (TrackInfo) Assertions.checkNotNull(view.getTag());
        TrackGroup mediaTrackGroup = trackInfo.trackGroup.getMediaTrackGroup();
        int r3 = trackInfo.trackIndex;
        TrackSelectionOverride trackSelectionOverride = this.overrides.get(mediaTrackGroup);
        if (trackSelectionOverride == null) {
            if (!this.allowMultipleOverrides && this.overrides.size() > 0) {
                this.overrides.clear();
            }
            this.overrides.put(mediaTrackGroup, new TrackSelectionOverride(mediaTrackGroup, ImmutableList.m408of(Integer.valueOf(r3))));
            return;
        }
        ArrayList arrayList = new ArrayList(trackSelectionOverride.trackIndices);
        boolean isChecked = ((CheckedTextView) view).isChecked();
        boolean shouldEnableAdaptiveSelection = shouldEnableAdaptiveSelection(trackInfo.trackGroup);
        z = (shouldEnableAdaptiveSelection || shouldEnableMultiGroupSelection()) ? true : true;
        if (isChecked && z) {
            arrayList.remove(Integer.valueOf(r3));
            if (arrayList.isEmpty()) {
                this.overrides.remove(mediaTrackGroup);
            } else {
                this.overrides.put(mediaTrackGroup, new TrackSelectionOverride(mediaTrackGroup, arrayList));
            }
        } else if (isChecked) {
        } else {
            if (shouldEnableAdaptiveSelection) {
                arrayList.add(Integer.valueOf(r3));
                this.overrides.put(mediaTrackGroup, new TrackSelectionOverride(mediaTrackGroup, arrayList));
                return;
            }
            this.overrides.put(mediaTrackGroup, new TrackSelectionOverride(mediaTrackGroup, ImmutableList.m408of(Integer.valueOf(r3))));
        }
    }

    private boolean shouldEnableAdaptiveSelection(Tracks.Group group) {
        return this.allowAdaptiveSelections && group.isAdaptiveSupported();
    }

    private boolean shouldEnableMultiGroupSelection() {
        return this.allowMultipleOverrides && this.trackGroups.size() > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.ui.TrackSelectionView$ComponentListener */
    /* loaded from: classes2.dex */
    public class ComponentListener implements View.OnClickListener {
        private ComponentListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TrackSelectionView.this.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.ui.TrackSelectionView$TrackInfo */
    /* loaded from: classes2.dex */
    public static final class TrackInfo {
        public final Tracks.Group trackGroup;
        public final int trackIndex;

        public TrackInfo(Tracks.Group group, int r2) {
            this.trackGroup = group;
            this.trackIndex = r2;
        }

        public Format getFormat() {
            return this.trackGroup.getTrackFormat(this.trackIndex);
        }
    }
}