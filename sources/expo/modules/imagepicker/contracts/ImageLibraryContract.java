package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import expo.modules.imagepicker.ImagePickerUtils;
import expo.modules.imagepicker.contracts.ContractsUtils;
import expo.modules.kotlin.activityresult.AppContextActivityResultContract;
import expo.modules.kotlin.providers.AppContextProvider;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageLibraryContract.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\"\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/imagepicker/contracts/ImageLibraryContract;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "Lexpo/modules/imagepicker/contracts/ImageLibraryContractOptions;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ImageLibraryContract implements AppContextActivityResultContract<ImageLibraryContractOptions, ContractsUtils> {
    private final AppContextProvider appContextProvider;

    public ImageLibraryContract(AppContextProvider appContextProvider) {
        Intrinsics.checkNotNullParameter(appContextProvider, "appContextProvider");
        this.appContextProvider = appContextProvider;
    }

    public final ContentResolver getContentResolver() {
        Context reactContext = this.appContextProvider.getAppContext().getReactContext();
        if (reactContext == null) {
            throw new IllegalArgumentException("React Application Context is null".toString());
        }
        ContentResolver contentResolver = reactContext.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "requireNotNull(appContex…ll\"\n    }.contentResolver");
        return contentResolver;
    }

    @Override // expo.modules.kotlin.activityresult.AppContextActivityResultContract
    public Intent createIntent(Context context, ImageLibraryContractOptions input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent type = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(input.getOptions().getMediaTypes().toMimeType());
        if (input.getOptions().getAllowsMultipleSelection()) {
            type.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        Intrinsics.checkNotNullExpressionValue(type, "Intent(Intent.ACTION_GET…, true)\n        }\n      }");
        return type;
    }

    @Override // expo.modules.kotlin.activityresult.AppContextActivityResultContract
    public ContractsUtils parseResult(ImageLibraryContractOptions input, int r3, Intent intent) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (r3 == 0) {
            return new ContractsUtils.Cancelled();
        }
        if (!input.getOptions().getAllowsMultipleSelection()) {
            if (intent != null) {
                Uri data = intent.getData();
                if (data == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                Intrinsics.checkNotNullExpressionValue(data, "requireNotNull(requireNotNull(intent).data)");
                return new ContractsUtils.Success(CollectionsKt.listOf(TuplesKt.m176to(ImagePickerUtils.toMediaType(data, getContentResolver()), data)));
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        } else if (intent != null) {
            List<Uri> allDataUris = ImagePickerUtils.getAllDataUris(intent);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allDataUris, 10));
            for (Uri uri : allDataUris) {
                arrayList.add(TuplesKt.m176to(ImagePickerUtils.toMediaType(uri, getContentResolver()), uri));
            }
            return new ContractsUtils.Success(arrayList);
        } else {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
    }
}
