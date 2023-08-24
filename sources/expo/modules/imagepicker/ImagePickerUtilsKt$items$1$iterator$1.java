package expo.modules.imagepicker;

import android.content.ClipData;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMarkers;

/* compiled from: ImagePickerUtils.kt */
@Metadata(m184d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\t\u0010\r\u001a\u00020\u0002H\u0096\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0006\"\u0004\b\t\u0010\n¨\u0006\u000e"}, m183d2 = {"expo/modules/imagepicker/ImagePickerUtilsKt$items$1$iterator$1", "", "Landroid/content/ClipData$Item;", NewHtcHomeBadger.COUNT, "", "getCount", "()I", "index", "getIndex", "setIndex", "(I)V", "hasNext", "", "next", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImagePickerUtilsKt$items$1$iterator$1 implements Iterator<ClipData.Item>, KMarkers {
    final /* synthetic */ ClipData $this_items;
    private final int count;
    private int index;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImagePickerUtilsKt$items$1$iterator$1(ClipData clipData) {
        this.$this_items = clipData;
        this.count = clipData.getItemCount();
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int r1) {
        this.index = r1;
    }

    public final int getCount() {
        return this.count;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.count;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public ClipData.Item next() {
        ClipData clipData = this.$this_items;
        int r1 = this.index;
        this.index = r1 + 1;
        ClipData.Item itemAt = clipData.getItemAt(r1);
        Intrinsics.checkNotNullExpressionValue(itemAt, "getItemAt(index++)");
        return itemAt;
    }
}
