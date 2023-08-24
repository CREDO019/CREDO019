package expo.modules.imagepicker;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePickerModule.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B'\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001b\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\bHÆ\u0003J/\u0010\u0010\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/imagepicker/PendingMediaPickingResult;", "", "data", "", "Lkotlin/Pair;", "Lexpo/modules/imagepicker/MediaType;", "Landroid/net/Uri;", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "(Ljava/util/List;Lexpo/modules/imagepicker/ImagePickerOptions;)V", "getData", "()Ljava/util/List;", "getOptions", "()Lexpo/modules/imagepicker/ImagePickerOptions;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PendingMediaPickingResult {
    private final List<Tuples<MediaType, Uri>> data;
    private final ImagePickerOptions options;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PendingMediaPickingResult copy$default(PendingMediaPickingResult pendingMediaPickingResult, List list, ImagePickerOptions imagePickerOptions, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            list = pendingMediaPickingResult.data;
        }
        if ((r3 & 2) != 0) {
            imagePickerOptions = pendingMediaPickingResult.options;
        }
        return pendingMediaPickingResult.copy(list, imagePickerOptions);
    }

    public final List<Tuples<MediaType, Uri>> component1() {
        return this.data;
    }

    public final ImagePickerOptions component2() {
        return this.options;
    }

    public final PendingMediaPickingResult copy(List<? extends Tuples<? extends MediaType, ? extends Uri>> data, ImagePickerOptions options) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(options, "options");
        return new PendingMediaPickingResult(data, options);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PendingMediaPickingResult) {
            PendingMediaPickingResult pendingMediaPickingResult = (PendingMediaPickingResult) obj;
            return Intrinsics.areEqual(this.data, pendingMediaPickingResult.data) && Intrinsics.areEqual(this.options, pendingMediaPickingResult.options);
        }
        return false;
    }

    public int hashCode() {
        return (this.data.hashCode() * 31) + this.options.hashCode();
    }

    public String toString() {
        List<Tuples<MediaType, Uri>> list = this.data;
        ImagePickerOptions imagePickerOptions = this.options;
        return "PendingMediaPickingResult(data=" + list + ", options=" + imagePickerOptions + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PendingMediaPickingResult(List<? extends Tuples<? extends MediaType, ? extends Uri>> data, ImagePickerOptions options) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(options, "options");
        this.data = data;
        this.options = options;
    }

    public final List<Tuples<MediaType, Uri>> getData() {
        return this.data;
    }

    public final ImagePickerOptions getOptions() {
        return this.options;
    }
}
