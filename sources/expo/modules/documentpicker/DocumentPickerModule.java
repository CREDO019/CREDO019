package expo.modules.documentpicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityEventListener;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.core.utilities.FileUtilities;
import expo.modules.imagepicker.MediaTypes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import org.apache.commons.p028io.FilenameUtils;
import org.apache.commons.p028io.IOUtils;

/* compiled from: DocumentPickerModule.kt */
@Metadata(m184d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J&\u0010\u001c\u001a\u00020\u001d2\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010 0\u001f2\u0006\u0010!\u001a\u00020\u0011H\u0007J\b\u0010\"\u001a\u00020\u0018H\u0016J\u001f\u0010#\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u0001H%H%0$\"\u0006\b\u0000\u0010%\u0018\u0001H\u0082\bJ*\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0010\u0010/\u001a\u00020\u001d2\u0006\u0010#\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.H\u0016R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m183d2 = {"Lexpo/modules/documentpicker/DocumentPickerModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/ActivityEventListener;", "mContext", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "mActivityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "getMActivityProvider", "()Lexpo/modules/core/interfaces/ActivityProvider;", "mActivityProvider$delegate", "Lkotlin/Lazy;", "mCopyToCacheDirectory", "", "mPromise", "Lexpo/modules/core/Promise;", "mUIManager", "Lexpo/modules/core/interfaces/services/UIManager;", "getMUIManager", "()Lexpo/modules/core/interfaces/services/UIManager;", "mUIManager$delegate", "copyDocumentToCacheDirectory", "", "documentUri", "Landroid/net/Uri;", "name", "getDocumentAsync", "", "options", "", "", BaseJavaModule.METHOD_TYPE_PROMISE, "getName", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "onNewIntent", "expo-document-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DocumentPickerModule extends ExportedModule implements ActivityEventListener {
    private final Lazy mActivityProvider$delegate;
    private boolean mCopyToCacheDirectory;
    private Promise mPromise;
    private final Lazy mUIManager$delegate;
    private final ModuleRegistryDelegate moduleRegistryDelegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoDocumentPicker";
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
    }

    public /* synthetic */ DocumentPickerModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DocumentPickerModule(Context mContext, final ModuleRegistryDelegate moduleRegistryDelegate) {
        super(mContext);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.mCopyToCacheDirectory = true;
        this.mActivityProvider$delegate = LazyKt.lazy(new Functions<ActivityProvider>() { // from class: expo.modules.documentpicker.DocumentPickerModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.ActivityProvider] */
            @Override // kotlin.jvm.functions.Functions
            public final ActivityProvider invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(ActivityProvider.class);
            }
        });
        this.mUIManager$delegate = LazyKt.lazy(new Functions<UIManager>() { // from class: expo.modules.documentpicker.DocumentPickerModule$special$$inlined$moduleRegistry$2
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.core.interfaces.services.UIManager, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final UIManager invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(UIManager.class);
            }
        });
    }

    private final ActivityProvider getMActivityProvider() {
        Object value = this.mActivityProvider$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mActivityProvider>(...)");
        return (ActivityProvider) value;
    }

    private final UIManager getMUIManager() {
        Object value = this.mUIManager$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mUIManager>(...)");
        return (UIManager) value;
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.documentpicker.DocumentPickerModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
        getMUIManager().registerActivityEventListener(this);
    }

    @ExpoMethod
    public final void getDocumentAsync(Map<String, ? extends Object> options, Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (this.mPromise != null) {
            promise.reject("E_DOCUMENT_PICKER", "Different document picking in progress. Await other document picking first.");
            return;
        }
        DocumentPickerOptions optionsFromMap = DocumentPickerOptions.Companion.optionsFromMap(options, promise);
        if (optionsFromMap == null) {
            return;
        }
        this.mPromise = promise;
        this.mCopyToCacheDirectory = optionsFromMap.getCopyToCacheDirectory();
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (optionsFromMap.getTypes().length > 1) {
            intent.putExtra("android.intent.extra.MIME_TYPES", optionsFromMap.getTypes());
            str = MediaTypes.AllMimeType;
        } else {
            str = optionsFromMap.getTypes()[0];
        }
        intent.setType(str);
        getMActivityProvider().getCurrentActivity().startActivityForResult(intent, 4137);
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onActivityResult(Activity activity, int r12, int r13, Intent intent) {
        Promise promise;
        Uri data;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (r12 == 4137 && (promise = this.mPromise) != null) {
            Intrinsics.checkNotNull(promise);
            DocumentDetails documentDetails = null;
            this.mPromise = null;
            if (r13 != -1) {
                Bundle bundle = new Bundle();
                bundle.putString(SessionDescription.ATTR_TYPE, "cancel");
                promise.resolve(bundle);
                return;
            }
            if (intent != null && (data = intent.getData()) != null) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                DocumentDetails read = new DocumentDetailsReader(context).read(data);
                if (!this.mCopyToCacheDirectory || read == null) {
                    documentDetails = read;
                } else {
                    String copyDocumentToCacheDirectory = copyDocumentToCacheDirectory(data, read.getName());
                    if (copyDocumentToCacheDirectory == null) {
                        promise.reject("E_DOCUMENT_PICKER", "Failed to copy to cache directory.");
                        return;
                    }
                    documentDetails = DocumentDetails.copy$default(read, null, copyDocumentToCacheDirectory, null, null, 13, null);
                }
            }
            if (documentDetails == null) {
                promise.reject("E_DOCUMENT_PICKER", "Failed to read the selected document.");
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(SessionDescription.ATTR_TYPE, "success");
            bundle2.putString("uri", documentDetails.getUri());
            bundle2.putString("name", documentDetails.getName());
            if (documentDetails.getMimeType() != null) {
                bundle2.putString("mimeType", documentDetails.getMimeType());
            }
            Integer size = documentDetails.getSize();
            if (size != null) {
                size.intValue();
                bundle2.putInt("size", documentDetails.getSize().intValue());
            }
            promise.resolve(bundle2);
        }
    }

    private final String copyDocumentToCacheDirectory(Uri uri, String str) {
        File file = new File(FileUtilities.generateOutputPath(getContext().getCacheDir(), "DocumentPicker", FilenameUtils.getExtension(str)));
        try {
            InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
            InputStream inputStream = openInputStream;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                IOUtils.copy(inputStream, fileOutputStream);
                Closeable.closeFinally(fileOutputStream, null);
                Closeable.closeFinally(openInputStream, null);
                return Uri.fromFile(file).toString();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
