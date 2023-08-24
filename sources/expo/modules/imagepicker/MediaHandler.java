package expo.modules.imagepicker;

import android.content.Context;
import expo.modules.kotlin.providers.AppContextProvider;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaHandler.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J5\u0010\u0016\u001a\u00020\u00172\u0018\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00100\u001a0\u00192\u0006\u0010\u0011\u001a\u00020\u0012H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/imagepicker/MediaHandler;", "", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "handleImage", "Lexpo/modules/imagepicker/ImagePickerAsset;", "sourceUri", "Landroid/net/Uri;", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "(Landroid/net/Uri;Lexpo/modules/imagepicker/ImagePickerOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleVideo", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readExtras", "Lexpo/modules/imagepicker/ImagePickerResponse;", "bareResult", "", "Lkotlin/Pair;", "Lexpo/modules/imagepicker/MediaType;", "readExtras$expo_image_picker_release", "(Ljava/util/List;Lexpo/modules/imagepicker/ImagePickerOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MediaHandler {
    private final AppContextProvider appContextProvider;

    /* compiled from: MediaHandler.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[MediaType.values().length];
            r0[MediaType.VIDEO.ordinal()] = 1;
            r0[MediaType.IMAGE.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public MediaHandler(AppContextProvider appContextProvider) {
        Intrinsics.checkNotNullParameter(appContextProvider, "appContextProvider");
        this.appContextProvider = appContextProvider;
    }

    private final Context getContext() {
        Context reactContext = this.appContextProvider.getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new IllegalArgumentException("React Application Context is null".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00df  */
    /* JADX WARN: Type inference failed for: r10v12, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r10v15, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00b5 -> B:26:0x00b8). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00d7 -> B:34:0x00d9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readExtras$expo_image_picker_release(java.util.List<? extends kotlin.Tuples<? extends expo.modules.imagepicker.MediaType, ? extends android.net.Uri>> r10, expo.modules.imagepicker.ImagePickerOptions r11, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerResponse> r12) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.readExtras$expo_image_picker_release(java.util.List, expo.modules.imagepicker.ImagePickerOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final File getCacheDirectory() {
        return this.appContextProvider.getAppContext().getCacheDirectory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object handleImage(android.net.Uri r25, expo.modules.imagepicker.ImagePickerOptions r26, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerAsset> r27) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.handleImage(android.net.Uri, expo.modules.imagepicker.ImagePickerOptions, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object handleVideo(android.net.Uri r19, kotlin.coroutines.Continuation<? super expo.modules.imagepicker.ImagePickerAsset> r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            boolean r3 = r2 instanceof expo.modules.imagepicker.MediaHandler$handleVideo$1
            if (r3 == 0) goto L1a
            r3 = r2
            expo.modules.imagepicker.MediaHandler$handleVideo$1 r3 = (expo.modules.imagepicker.MediaHandler$handleVideo$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L1a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L1f
        L1a:
            expo.modules.imagepicker.MediaHandler$handleVideo$1 r3 = new expo.modules.imagepicker.MediaHandler$handleVideo$1
            r3.<init>(r1, r2)
        L1f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L46
            if (r5 != r6) goto L3e
            java.lang.Object r0 = r3.L$2
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r4 = r3.L$1
            android.net.Uri r4 = (android.net.Uri) r4
            java.lang.Object r3 = r3.L$0
            expo.modules.imagepicker.MediaHandler r3 = (expo.modules.imagepicker.MediaHandler) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r4
            goto L70
        L3e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L46:
            kotlin.ResultKt.throwOnFailure(r2)
            java.io.File r2 = r18.getCacheDirectory()
            java.lang.String r5 = ".mp4"
            java.io.File r2 = expo.modules.imagepicker.ImagePickerUtils.createOutputFile(r2, r5)
            android.content.Context r5 = r18.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            java.lang.String r7 = "context.contentResolver"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
            r3.L$0 = r1
            r3.L$1 = r0
            r3.L$2 = r2
            r3.label = r6
            java.lang.Object r3 = expo.modules.imagepicker.ImagePickerUtils.copyFile(r0, r2, r5, r3)
            if (r3 != r4) goto L6f
            return r4
        L6f:
            r3 = r1
        L70:
            android.net.Uri r4 = android.net.Uri.fromFile(r2)
            java.lang.String r5 = "fromFile(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            android.media.MediaMetadataRetriever r5 = new android.media.MediaMetadataRetriever     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r5.<init>()     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            android.content.Context r3 = r3.getContext()     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r5.setDataSource(r3, r4)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            expo.modules.imagepicker.MediaType r8 = expo.modules.imagepicker.MediaType.VIDEO     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            java.lang.String r9 = r4.toString()     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r3 = 18
            int r10 = expo.modules.imagepicker.ImagePickerUtils.extractInt(r5, r3)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r3 = 19
            int r11 = expo.modules.imagepicker.ImagePickerUtils.extractInt(r5, r3)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r3 = 9
            int r3 = expo.modules.imagepicker.ImagePickerUtils.extractInt(r5, r3)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r4 = 24
            int r4 = expo.modules.imagepicker.ImagePickerUtils.extractInt(r5, r4)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            java.lang.String r7 = expo.modules.imagepicker.ImagePickerUtils.getMediaStoreAssetId(r0)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            expo.modules.imagepicker.ImagePickerAsset r0 = new expo.modules.imagepicker.ImagePickerAsset     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            java.lang.String r5 = "toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r5)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r12 = 0
            r13 = 0
            java.lang.Integer r14 = kotlin.coroutines.jvm.internal.boxing.boxInt(r3)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            java.lang.Integer r15 = kotlin.coroutines.jvm.internal.boxing.boxInt(r4)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            r16 = 96
            r17 = 0
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)     // Catch: expo.modules.imagepicker.FailedToExtractVideoMetadataException -> Lc1
            return r0
        Lc1:
            r0 = move-exception
            expo.modules.imagepicker.FailedToExtractVideoMetadataException r3 = new expo.modules.imagepicker.FailedToExtractVideoMetadataException
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r3.<init>(r2, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaHandler.handleVideo(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
