package com.google.android.exoplayer2.source;

import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class TrackGroup implements Bundleable {
    public static final Bundleable.Creator<TrackGroup> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.source.TrackGroup$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return TrackGroup.lambda$static$0(bundle);
        }
    };
    private static final int FIELD_FORMATS = 0;
    private static final int FIELD_ID = 1;
    private static final String TAG = "TrackGroup";
    private final Format[] formats;
    private int hashCode;

    /* renamed from: id */
    public final String f238id;
    public final int length;
    public final int type;

    private static int normalizeRoleFlags(int r0) {
        return r0 | 16384;
    }

    public TrackGroup(Format... formatArr) {
        this("", formatArr);
    }

    public TrackGroup(String str, Format... formatArr) {
        Assertions.checkArgument(formatArr.length > 0);
        this.f238id = str;
        this.formats = formatArr;
        this.length = formatArr.length;
        int trackType = MimeTypes.getTrackType(formatArr[0].sampleMimeType);
        this.type = trackType == -1 ? MimeTypes.getTrackType(formatArr[0].containerMimeType) : trackType;
        verifyCorrectness();
    }

    public TrackGroup copyWithId(String str) {
        return new TrackGroup(str, this.formats);
    }

    public Format getFormat(int r2) {
        return this.formats[r2];
    }

    public int indexOf(Format format) {
        int r0 = 0;
        while (true) {
            Format[] formatArr = this.formats;
            if (r0 >= formatArr.length) {
                return -1;
            }
            if (format == formatArr[r0]) {
                return r0;
            }
            r0++;
        }
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((527 + this.f238id.hashCode()) * 31) + Arrays.hashCode(this.formats);
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
        TrackGroup trackGroup = (TrackGroup) obj;
        return this.f238id.equals(trackGroup.f238id) && Arrays.equals(this.formats, trackGroup.formats);
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(keyForField(0), BundleableUtil.toBundleArrayList(Lists.newArrayList(this.formats)));
        bundle.putString(keyForField(1), this.f238id);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TrackGroup lambda$static$0(Bundle bundle) {
        ImmutableList fromBundleList;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(keyForField(0));
        if (parcelableArrayList == null) {
            fromBundleList = ImmutableList.m409of();
        } else {
            fromBundleList = BundleableUtil.fromBundleList(Format.CREATOR, parcelableArrayList);
        }
        return new TrackGroup(bundle.getString(keyForField(1), ""), (Format[]) fromBundleList.toArray(new Format[0]));
    }

    private static String keyForField(int r1) {
        return Integer.toString(r1, 36);
    }

    private void verifyCorrectness() {
        String normalizeLanguage = normalizeLanguage(this.formats[0].language);
        int normalizeRoleFlags = normalizeRoleFlags(this.formats[0].roleFlags);
        int r3 = 1;
        while (true) {
            Format[] formatArr = this.formats;
            if (r3 >= formatArr.length) {
                return;
            }
            if (!normalizeLanguage.equals(normalizeLanguage(formatArr[r3].language))) {
                logErrorMessage("languages", this.formats[0].language, this.formats[r3].language, r3);
                return;
            } else if (normalizeRoleFlags != normalizeRoleFlags(this.formats[r3].roleFlags)) {
                logErrorMessage("role flags", Integer.toBinaryString(this.formats[0].roleFlags), Integer.toBinaryString(this.formats[r3].roleFlags), r3);
                return;
            } else {
                r3++;
            }
        }
    }

    private static String normalizeLanguage(String str) {
        return (str == null || str.equals(C1856C.LANGUAGE_UNDETERMINED)) ? "" : str;
    }

    private static void logErrorMessage(String str, String str2, String str3, int r6) {
        Log.m1135e(TAG, "", new IllegalStateException("Different " + str + " combined in one TrackGroup: '" + str2 + "' (track 0) and '" + str3 + "' (track " + r6 + ")"));
    }
}
