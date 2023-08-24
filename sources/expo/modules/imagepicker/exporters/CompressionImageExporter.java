package expo.modules.imagepicker.exporters;

import android.graphics.Bitmap;
import expo.modules.imagepicker.FailedToWriteFileException;
import expo.modules.kotlin.providers.AppContextProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import kotlinx.coroutines.Interruptible;

/* compiled from: CompressionImageExporter.kt */
@Metadata(m184d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J)\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/imagepicker/exporters/CompressionImageExporter;", "Lexpo/modules/imagepicker/exporters/ImageExporter;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "quality", "", "(Lexpo/modules/kotlin/providers/AppContextProvider;D)V", "compressQuality", "", "exportAsync", "Lexpo/modules/imagepicker/exporters/ImageExportResult;", "source", "Landroid/net/Uri;", "output", "Ljava/io/File;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/net/Uri;Ljava/io/File;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBitmap", "Landroid/graphics/Bitmap;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeImage", "", "bitmap", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "(Landroid/graphics/Bitmap;Ljava/io/File;Landroid/graphics/Bitmap$CompressFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CompressionImageExporter implements ImageExporter {
    private final AppContextProvider appContextProvider;
    private final int compressQuality;

    public CompressionImageExporter(AppContextProvider appContextProvider, double d) {
        Intrinsics.checkNotNullParameter(appContextProvider, "appContextProvider");
        this.appContextProvider = appContextProvider;
        this.compressQuality = (int) (d * 100);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c0  */
    @Override // expo.modules.imagepicker.exporters.ImageExporter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object exportAsync(android.net.Uri r11, java.io.File r12, android.content.ContentResolver r13, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.exporters.ImageExportResult> r14) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.CompressionImageExporter.exportAsync(android.net.Uri, java.io.File, android.content.ContentResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readBitmap(final android.net.Uri r5, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1
            if (r0 == 0) goto L14
            r0 = r6
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1 r0 = (expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1 r0 = new expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$2 r6 = new expo.modules.imagepicker.exporters.CompressionImageExporter$readBitmap$2
            r6.<init>()
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Functions) r6
            r0.label = r3
            r5 = 0
            java.lang.Object r6 = kotlinx.coroutines.Interruptible.runInterruptible$default(r5, r6, r0, r3, r5)
            if (r6 != r1) goto L46
            return r1
        L46:
            java.lang.String r5 = "private suspend fun read…oFile(), cause)\n    }\n  }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.exporters.CompressionImageExporter.readBitmap(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeImage(final Bitmap bitmap, final File file, final Bitmap.CompressFormat compressFormat, Continuation<? super Boolean> continuation) {
        return Interruptible.runInterruptible$default(null, new Functions<Boolean>() { // from class: expo.modules.imagepicker.exporters.CompressionImageExporter$writeImage$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Boolean invoke() {
                int r3;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    Bitmap bitmap2 = bitmap;
                    Bitmap.CompressFormat compressFormat2 = compressFormat;
                    r3 = this.compressQuality;
                    boolean compress = bitmap2.compress(compressFormat2, r3, fileOutputStream);
                    Closeable.closeFinally(fileOutputStream, null);
                    return Boolean.valueOf(compress);
                } catch (FileNotFoundException e) {
                    throw new FailedToWriteFileException(file, e);
                }
            }
        }, continuation, 1, null);
    }
}
