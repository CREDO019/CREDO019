package expo.modules.camera;

import android.os.Bundle;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nHÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003JK\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\b\b\u0002\u0010\u000b\u001a\u00020\tHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R,\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u0019¨\u0006("}, m183d2 = {"Lexpo/modules/camera/BarCodeScannedEvent;", "Lexpo/modules/kotlin/records/Record;", TouchesHelper.TARGET_KEY, "", "data", "", SessionDescription.ATTR_TYPE, "cornerPoints", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "boundingBox", "(ILjava/lang/String;ILjava/util/ArrayList;Landroid/os/Bundle;)V", "getBoundingBox$annotations", "()V", "getBoundingBox", "()Landroid/os/Bundle;", "getCornerPoints$annotations", "getCornerPoints", "()Ljava/util/ArrayList;", "getData$annotations", "getData", "()Ljava/lang/String;", "getTarget$annotations", "getTarget", "()I", "getType$annotations", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.camera.BarCodeScannedEvent */
/* loaded from: classes4.dex */
public final class Events implements Record {
    private final Bundle boundingBox;
    private final ArrayList<Bundle> cornerPoints;
    private final String data;
    private final int target;
    private final int type;

    public static /* synthetic */ Events copy$default(Events events, int r4, String str, int r6, ArrayList arrayList, Bundle bundle, int r9, Object obj) {
        if ((r9 & 1) != 0) {
            r4 = events.target;
        }
        if ((r9 & 2) != 0) {
            str = events.data;
        }
        String str2 = str;
        if ((r9 & 4) != 0) {
            r6 = events.type;
        }
        int r0 = r6;
        ArrayList<Bundle> arrayList2 = arrayList;
        if ((r9 & 8) != 0) {
            arrayList2 = events.cornerPoints;
        }
        ArrayList arrayList3 = arrayList2;
        if ((r9 & 16) != 0) {
            bundle = events.boundingBox;
        }
        return events.copy(r4, str2, r0, arrayList3, bundle);
    }

    @Field
    public static /* synthetic */ void getBoundingBox$annotations() {
    }

    @Field
    public static /* synthetic */ void getCornerPoints$annotations() {
    }

    @Field
    public static /* synthetic */ void getData$annotations() {
    }

    @Field
    public static /* synthetic */ void getTarget$annotations() {
    }

    @Field
    public static /* synthetic */ void getType$annotations() {
    }

    public final int component1() {
        return this.target;
    }

    public final String component2() {
        return this.data;
    }

    public final int component3() {
        return this.type;
    }

    public final ArrayList<Bundle> component4() {
        return this.cornerPoints;
    }

    public final Bundle component5() {
        return this.boundingBox;
    }

    public final Events copy(int r8, String data, int r10, ArrayList<Bundle> cornerPoints, Bundle boundingBox) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(cornerPoints, "cornerPoints");
        Intrinsics.checkNotNullParameter(boundingBox, "boundingBox");
        return new Events(r8, data, r10, cornerPoints, boundingBox);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Events) {
            Events events = (Events) obj;
            return this.target == events.target && Intrinsics.areEqual(this.data, events.data) && this.type == events.type && Intrinsics.areEqual(this.cornerPoints, events.cornerPoints) && Intrinsics.areEqual(this.boundingBox, events.boundingBox);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.target * 31) + this.data.hashCode()) * 31) + this.type) * 31) + this.cornerPoints.hashCode()) * 31) + this.boundingBox.hashCode();
    }

    public String toString() {
        int r0 = this.target;
        String str = this.data;
        int r2 = this.type;
        ArrayList<Bundle> arrayList = this.cornerPoints;
        Bundle bundle = this.boundingBox;
        return "BarCodeScannedEvent(target=" + r0 + ", data=" + str + ", type=" + r2 + ", cornerPoints=" + arrayList + ", boundingBox=" + bundle + ")";
    }

    public Events(int r2, String data, int r4, ArrayList<Bundle> cornerPoints, Bundle boundingBox) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(cornerPoints, "cornerPoints");
        Intrinsics.checkNotNullParameter(boundingBox, "boundingBox");
        this.target = r2;
        this.data = data;
        this.type = r4;
        this.cornerPoints = cornerPoints;
        this.boundingBox = boundingBox;
    }

    public final int getTarget() {
        return this.target;
    }

    public final String getData() {
        return this.data;
    }

    public final int getType() {
        return this.type;
    }

    public final ArrayList<Bundle> getCornerPoints() {
        return this.cornerPoints;
    }

    public final Bundle getBoundingBox() {
        return this.boundingBox;
    }
}
