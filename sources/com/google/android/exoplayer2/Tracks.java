package com.google.android.exoplayer2;

import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Booleans;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public final class Tracks implements Bundleable {
    private static final int FIELD_TRACK_GROUPS = 0;
    private final ImmutableList<Group> groups;
    public static final Tracks EMPTY = new Tracks(ImmutableList.m409of());
    public static final Bundleable.Creator<Tracks> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.Tracks$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return Tracks.lambda$static$0(bundle);
        }
    };

    /* loaded from: classes2.dex */
    public static final class Group implements Bundleable {
        public static final Bundleable.Creator<Group> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.Tracks$Group$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.Bundleable.Creator
            public final Bundleable fromBundle(Bundle bundle) {
                return Tracks.Group.lambda$static$0(bundle);
            }
        };
        private static final int FIELD_ADAPTIVE_SUPPORTED = 4;
        private static final int FIELD_TRACK_GROUP = 0;
        private static final int FIELD_TRACK_SELECTED = 3;
        private static final int FIELD_TRACK_SUPPORT = 1;
        private final boolean adaptiveSupported;
        public final int length;
        private final TrackGroup mediaTrackGroup;
        private final boolean[] trackSelected;
        private final int[] trackSupport;

        public Group(TrackGroup trackGroup, boolean z, int[] r7, boolean[] zArr) {
            int r0 = trackGroup.length;
            this.length = r0;
            boolean z2 = false;
            Assertions.checkArgument(r0 == r7.length && r0 == zArr.length);
            this.mediaTrackGroup = trackGroup;
            if (z && r0 > 1) {
                z2 = true;
            }
            this.adaptiveSupported = z2;
            this.trackSupport = (int[]) r7.clone();
            this.trackSelected = (boolean[]) zArr.clone();
        }

        public TrackGroup getMediaTrackGroup() {
            return this.mediaTrackGroup;
        }

        public Format getTrackFormat(int r2) {
            return this.mediaTrackGroup.getFormat(r2);
        }

        public int getTrackSupport(int r2) {
            return this.trackSupport[r2];
        }

        public boolean isTrackSupported(int r2) {
            return isTrackSupported(r2, false);
        }

        public boolean isTrackSupported(int r4, boolean z) {
            int[] r0 = this.trackSupport;
            return r0[r4] == 4 || (z && r0[r4] == 3);
        }

        public boolean isSelected() {
            return Booleans.contains(this.trackSelected, true);
        }

        public boolean isAdaptiveSupported() {
            return this.adaptiveSupported;
        }

        public boolean isSupported() {
            return isSupported(false);
        }

        public boolean isSupported(boolean z) {
            for (int r1 = 0; r1 < this.trackSupport.length; r1++) {
                if (isTrackSupported(r1, z)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTrackSelected(int r2) {
            return this.trackSelected[r2];
        }

        public int getType() {
            return this.mediaTrackGroup.type;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Group group = (Group) obj;
            return this.adaptiveSupported == group.adaptiveSupported && this.mediaTrackGroup.equals(group.mediaTrackGroup) && Arrays.equals(this.trackSupport, group.trackSupport) && Arrays.equals(this.trackSelected, group.trackSelected);
        }

        public int hashCode() {
            return (((((this.mediaTrackGroup.hashCode() * 31) + (this.adaptiveSupported ? 1 : 0)) * 31) + Arrays.hashCode(this.trackSupport)) * 31) + Arrays.hashCode(this.trackSelected);
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(keyForField(0), this.mediaTrackGroup.toBundle());
            bundle.putIntArray(keyForField(1), this.trackSupport);
            bundle.putBooleanArray(keyForField(3), this.trackSelected);
            bundle.putBoolean(keyForField(4), this.adaptiveSupported);
            return bundle;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Group lambda$static$0(Bundle bundle) {
            TrackGroup fromBundle = TrackGroup.CREATOR.fromBundle((Bundle) Assertions.checkNotNull(bundle.getBundle(keyForField(0))));
            return new Group(fromBundle, bundle.getBoolean(keyForField(4), false), (int[]) MoreObjects.firstNonNull(bundle.getIntArray(keyForField(1)), new int[fromBundle.length]), (boolean[]) MoreObjects.firstNonNull(bundle.getBooleanArray(keyForField(3)), new boolean[fromBundle.length]));
        }

        private static String keyForField(int r1) {
            return Integer.toString(r1, 36);
        }
    }

    public Tracks(List<Group> list) {
        this.groups = ImmutableList.copyOf((Collection) list);
    }

    public ImmutableList<Group> getGroups() {
        return this.groups;
    }

    public boolean isEmpty() {
        return this.groups.isEmpty();
    }

    public boolean containsType(int r4) {
        for (int r1 = 0; r1 < this.groups.size(); r1++) {
            if (this.groups.get(r1).getType() == r4) {
                return true;
            }
        }
        return false;
    }

    public boolean isTypeSupported(int r2) {
        return isTypeSupported(r2, false);
    }

    public boolean isTypeSupported(int r4, boolean z) {
        for (int r1 = 0; r1 < this.groups.size(); r1++) {
            if (this.groups.get(r1).getType() == r4 && this.groups.get(r1).isSupported(z)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int r2) {
        return isTypeSupportedOrEmpty(r2, false);
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int r2, boolean z) {
        return !containsType(r2) || isTypeSupported(r2, z);
    }

    public boolean isTypeSelected(int r5) {
        for (int r1 = 0; r1 < this.groups.size(); r1++) {
            Group group = this.groups.get(r1);
            if (group.isSelected() && group.getType() == r5) {
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
        return this.groups.equals(((Tracks) obj).groups);
    }

    public int hashCode() {
        return this.groups.hashCode();
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(keyForField(0), BundleableUtil.toBundleArrayList(this.groups));
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Tracks lambda$static$0(Bundle bundle) {
        ImmutableList fromBundleList;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(0));
        if (parcelableArrayList == null) {
            fromBundleList = ImmutableList.m409of();
        } else {
            fromBundleList = BundleableUtil.fromBundleList(Group.CREATOR, parcelableArrayList);
        }
        return new Tracks(fromBundleList);
    }

    private static String keyForField(int r1) {
        return Integer.toString(r1, 36);
    }
}
