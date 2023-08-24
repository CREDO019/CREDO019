package com.canhub.cropper;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageContractOptions.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¨\u0006\b"}, m183d2 = {"options", "Lcom/canhub/cropper/CropImageContractOptions;", "uri", "Landroid/net/Uri;", "builder", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "cropper_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropImageContractOptionsKt {
    public static /* synthetic */ CropImageContractOptions options$default(Uri uri, Function1 function1, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            uri = null;
        }
        if ((r2 & 2) != 0) {
            function1 = new Function1<CropImageContractOptions, Unit>() { // from class: com.canhub.cropper.CropImageContractOptionsKt$options$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(CropImageContractOptions cropImageContractOptions) {
                    Intrinsics.checkNotNullParameter(cropImageContractOptions, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CropImageContractOptions cropImageContractOptions) {
                    invoke2(cropImageContractOptions);
                    return Unit.INSTANCE;
                }
            };
        }
        return options(uri, function1);
    }

    public static final CropImageContractOptions options(Uri uri, Function1<? super CropImageContractOptions, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        CropImageContractOptions cropImageContractOptions = new CropImageContractOptions(uri, new CropImageOptions());
        builder.invoke(cropImageContractOptions);
        return cropImageContractOptions;
    }
}
