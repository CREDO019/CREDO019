package expo.modules.camera;

import android.os.Bundle;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Events.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/camera/PictureSavedEvent;", "Lexpo/modules/kotlin/records/Record;", "id", "", "data", "Landroid/os/Bundle;", "(ILandroid/os/Bundle;)V", "getData$annotations", "()V", "getData", "()Landroid/os/Bundle;", "getId$annotations", "getId", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PictureSavedEvent implements Record {
    private final Bundle data;

    /* renamed from: id */
    private final int f1426id;

    public static /* synthetic */ PictureSavedEvent copy$default(PictureSavedEvent pictureSavedEvent, int r1, Bundle bundle, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            r1 = pictureSavedEvent.f1426id;
        }
        if ((r3 & 2) != 0) {
            bundle = pictureSavedEvent.data;
        }
        return pictureSavedEvent.copy(r1, bundle);
    }

    @Field
    public static /* synthetic */ void getData$annotations() {
    }

    @Field
    public static /* synthetic */ void getId$annotations() {
    }

    public final int component1() {
        return this.f1426id;
    }

    public final Bundle component2() {
        return this.data;
    }

    public final PictureSavedEvent copy(int r2, Bundle data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new PictureSavedEvent(r2, data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PictureSavedEvent) {
            PictureSavedEvent pictureSavedEvent = (PictureSavedEvent) obj;
            return this.f1426id == pictureSavedEvent.f1426id && Intrinsics.areEqual(this.data, pictureSavedEvent.data);
        }
        return false;
    }

    public int hashCode() {
        return (this.f1426id * 31) + this.data.hashCode();
    }

    public String toString() {
        int r0 = this.f1426id;
        Bundle bundle = this.data;
        return "PictureSavedEvent(id=" + r0 + ", data=" + bundle + ")";
    }

    public PictureSavedEvent(int r2, Bundle data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.f1426id = r2;
        this.data = data;
    }

    public final int getId() {
        return this.f1426id;
    }

    public final Bundle getData() {
        return this.data;
    }
}
