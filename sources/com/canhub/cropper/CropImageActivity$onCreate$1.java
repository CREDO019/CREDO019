package com.canhub.cropper;

import com.canhub.cropper.CropImageActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageActivity.kt */
@Metadata(m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
/* synthetic */ class CropImageActivity$onCreate$1 extends FunctionReferenceImpl implements Function1<CropImageActivity.Source, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CropImageActivity$onCreate$1(CropImageActivity cropImageActivity) {
        super(1, cropImageActivity, CropImageActivity.class, "openSource", "openSource(Lcom/canhub/cropper/CropImageActivity$Source;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CropImageActivity.Source source) {
        invoke2(source);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(CropImageActivity.Source p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((CropImageActivity) this.receiver).openSource(p0);
    }
}
