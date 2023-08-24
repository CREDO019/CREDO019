package expo.modules.imagepicker.contracts;

import android.net.Uri;
import expo.modules.imagepicker.MediaType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "", "()V", "Cancelled", "Success", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Cancelled;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.imagepicker.contracts.ImagePickerContractResult */
/* loaded from: classes4.dex */
public abstract class ContractsUtils {
    public /* synthetic */ ContractsUtils(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: ContractsUtils.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Cancelled;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "()V", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.imagepicker.contracts.ImagePickerContractResult$Cancelled */
    /* loaded from: classes4.dex */
    public static final class Cancelled extends ContractsUtils {
        public Cancelled() {
            super(null);
        }
    }

    private ContractsUtils() {
    }

    /* compiled from: ContractsUtils.kt */
    @Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\u0002\u0010\u0007R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "data", "", "Lkotlin/Pair;", "Lexpo/modules/imagepicker/MediaType;", "Landroid/net/Uri;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.imagepicker.contracts.ImagePickerContractResult$Success */
    /* loaded from: classes4.dex */
    public static final class Success extends ContractsUtils {
        private final List<Tuples<MediaType, Uri>> data;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Success(List<? extends Tuples<? extends MediaType, ? extends Uri>> data) {
            super(null);
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<Tuples<MediaType, Uri>> getData() {
            return this.data;
        }
    }
}
