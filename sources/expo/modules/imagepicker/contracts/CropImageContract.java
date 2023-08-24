package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.p005os.Bundle;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageActivity;
import com.canhub.cropper.CropImageOptions;
import expo.modules.imagepicker.ImagePickerUtils;
import expo.modules.imagepicker.MediaType;
import expo.modules.imagepicker.contracts.ContractsUtils;
import expo.modules.kotlin.activityresult.AppContextActivityResultContract;
import expo.modules.kotlin.providers.AppContextProvider;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* compiled from: CropImageContract.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\"\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/CropImageContract;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "Lexpo/modules/imagepicker/contracts/CropImageContractOptions;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CropImageContract implements AppContextActivityResultContract<CropImageContractOptions, ContractsUtils> {
    private final AppContextProvider appContextProvider;

    public CropImageContract(AppContextProvider appContextProvider) {
        Intrinsics.checkNotNullParameter(appContextProvider, "appContextProvider");
        this.appContextProvider = appContextProvider;
    }

    @Override // expo.modules.kotlin.activityresult.AppContextActivityResultContract
    public Intent createIntent(Context context, CropImageContractOptions input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent intent = new Intent(context, CropImageActivity.class);
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
        Bitmap.CompressFormat bitmapCompressFormat = ImagePickerUtils.toBitmapCompressFormat(ImagePickerUtils.getType(contentResolver, input.getSourceUri()));
        Uri fromFile = Uri.fromFile(ImagePickerUtils.createOutputFile(this.appContextProvider.getAppContext().getCacheDirectory(), ImagePickerUtils.toImageFileExtension(bitmapCompressFormat)));
        Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
        Tuples[] tuplesArr = new Tuples[2];
        tuplesArr[0] = TuplesKt.m176to(CropImage.CROP_IMAGE_EXTRA_SOURCE, input.getSourceUri());
        CropImageOptions cropImageOptions = new CropImageOptions();
        cropImageOptions.outputCompressFormat = bitmapCompressFormat;
        cropImageOptions.outputCompressQuality = (int) (input.getOptions().getQuality() * 100);
        cropImageOptions.customOutputUri = fromFile;
        Tuples<Integer, Integer> aspect = input.getOptions().getAspect();
        if (aspect != null) {
            int intValue = aspect.component1().intValue();
            int intValue2 = aspect.component2().intValue();
            cropImageOptions.aspectRatioX = intValue;
            cropImageOptions.aspectRatioY = intValue2;
            cropImageOptions.fixAspectRatio = true;
            cropImageOptions.initialCropWindowPaddingRatio = 0.0f;
        }
        cropImageOptions.validate();
        Unit unit = Unit.INSTANCE;
        tuplesArr[1] = TuplesKt.m176to(CropImage.CROP_IMAGE_EXTRA_OPTIONS, cropImageOptions);
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE, Bundle.bundleOf(tuplesArr));
        return intent;
    }

    @Override // expo.modules.kotlin.activityresult.AppContextActivityResultContract
    public ContractsUtils parseResult(CropImageContractOptions input, int r4, Intent intent) {
        Intrinsics.checkNotNullParameter(input, "input");
        CropImage.ActivityResult activityResult = intent == null ? null : (CropImage.ActivityResult) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_RESULT);
        if (r4 == 0 || activityResult == null) {
            return new ContractsUtils.Cancelled();
        }
        Uri uriContent = activityResult.getUriContent();
        if (uriContent == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Context reactContext = this.appContextProvider.getAppContext().getReactContext();
        if (reactContext != null) {
            BuildersKt__BuildersKt.runBlocking$default(null, new CropImageContract$parseResult$1(input, uriContent, reactContext.getContentResolver(), null), 1, null);
            return new ContractsUtils.Success(CollectionsKt.listOf(TuplesKt.m176to(MediaType.IMAGE, uriContent)));
        }
        throw new IllegalArgumentException("React Application Context is null".toString());
    }
}
