package com.canhub.cropper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageIntentChooser;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.databinding.CropImageActivityBinding;
import com.canhub.cropper.utils.GetUriForFile;
import com.facebook.imagepipeline.producers.DecodeProducer;
import expo.modules.imagepicker.MediaTypes;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CropImageActivity.kt */
@Metadata(m184d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 C2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002CDB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J*\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0012\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0016J\u0012\u0010-\u001a\u00020\u00142\b\u0010.\u001a\u0004\u0018\u00010\nH\u0014J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020!H\u0014J(\u00101\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH\u0016J\b\u00102\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020\u0014H\u0016J\b\u00104\u001a\u00020\u0014H\u0002J\u0010\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u001cH\u0016J\u0010\u0010:\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0016J*\u0010;\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010<\u001a\u00020\u0014H\u0016J\u001c\u0010=\u001a\u00020\u00142\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00140>H\u0016J\b\u0010?\u001a\u00020\u0014H\u0002J \u0010@\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%2\u0006\u0010A\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020\u001cH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\n0\n0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, m183d2 = {"Lcom/canhub/cropper/CropImageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "()V", "binding", "Lcom/canhub/cropper/databinding/CropImageActivityBinding;", "cropImageOptions", "Lcom/canhub/cropper/CropImageOptions;", "cropImageUri", "Landroid/net/Uri;", "cropImageView", "Lcom/canhub/cropper/CropImageView;", "latestTmpUri", "pickImageGallery", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "takePicture", "cropImage", "", "getResultIntent", "Landroid/content/Intent;", "uri", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", DecodeProducer.SAMPLE_SIZE, "", "getTmpFileUri", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onCropImageComplete", "view", "result", "Lcom/canhub/cropper/CropImageView$CropResult;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPickImageResult", "resultUri", "onSaveInstanceState", "outState", "onSetImageUriComplete", "onStart", "onStop", "openCamera", "openSource", "source", "Lcom/canhub/cropper/CropImageActivity$Source;", "rotateImage", "degrees", "setCropImageView", "setResult", "setResultCancel", "showImageSourceDialog", "Lkotlin/Function1;", "showIntentChooser", "updateMenuItemIconColor", "itemId", "color", "Companion", "Source", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public class CropImageActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {
    @Deprecated
    public static final String BUNDLE_KEY_TMP_URI = "bundle_key_tmp_uri";
    private static final Companion Companion = new Companion(null);
    private CropImageActivityBinding binding;
    private CropImageOptions cropImageOptions;
    private Uri cropImageUri;
    private CropImageView cropImageView;
    private Uri latestTmpUri;
    private final ActivityResultLauncher<String> pickImageGallery;
    private final ActivityResultLauncher<Uri> takePicture;

    /* compiled from: CropImageActivity.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m183d2 = {"Lcom/canhub/cropper/CropImageActivity$Source;", "", "(Ljava/lang/String;I)V", "CAMERA", "GALLERY", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum Source {
        CAMERA,
        GALLERY
    }

    /* compiled from: CropImageActivity.kt */
    @Metadata(m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Source.values().length];
            r0[Source.CAMERA.ordinal()] = 1;
            r0[Source.GALLERY.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public CropImageActivity() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback() { // from class: com.canhub.cropper.CropImageActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CropImageActivity.m1486pickImageGallery$lambda0(CropImageActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->\n            onPickImageResult(uri)\n        }");
        this.pickImageGallery = registerForActivityResult;
        ActivityResultLauncher<Uri> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback() { // from class: com.canhub.cropper.CropImageActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CropImageActivity.m1488takePicture$lambda1(CropImageActivity.this, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(ActivityResultContracts.TakePicture()) {\n        if (it) onPickImageResult(latestTmpUri) else onPickImageResult(null)\n    }");
        this.takePicture = registerForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: pickImageGallery$lambda-0  reason: not valid java name */
    public static final void m1486pickImageGallery$lambda0(CropImageActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onPickImageResult(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: takePicture$lambda-1  reason: not valid java name */
    public static final void m1488takePicture$lambda1(CropImageActivity this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.onPickImageResult(it.booleanValue() ? this$0.latestTmpUri : null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Uri parse;
        String string;
        super.onCreate(bundle);
        CropImageActivityBinding inflate = CropImageActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.binding = inflate;
        if (inflate != null) {
            setContentView(inflate.getRoot());
            CropImageActivityBinding cropImageActivityBinding = this.binding;
            if (cropImageActivityBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                throw null;
            }
            CropImageView cropImageView = cropImageActivityBinding.cropImageView;
            Intrinsics.checkNotNullExpressionValue(cropImageView, "binding.cropImageView");
            setCropImageView(cropImageView);
            Bundle bundleExtra = getIntent().getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE);
            this.cropImageUri = bundleExtra == null ? null : (Uri) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE);
            CropImageOptions cropImageOptions = bundleExtra == null ? null : (CropImageOptions) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
            if (cropImageOptions == null) {
                cropImageOptions = new CropImageOptions();
            }
            this.cropImageOptions = cropImageOptions;
            if (bundle == null) {
                Uri uri = this.cropImageUri;
                if (uri == null || Intrinsics.areEqual(uri, Uri.EMPTY)) {
                    CropImageOptions cropImageOptions2 = this.cropImageOptions;
                    if (cropImageOptions2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                        throw null;
                    } else if (cropImageOptions2.showIntentChooser) {
                        showIntentChooser();
                    } else {
                        CropImageOptions cropImageOptions3 = this.cropImageOptions;
                        if (cropImageOptions3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                            throw null;
                        }
                        if (cropImageOptions3.imageSourceIncludeGallery) {
                            CropImageOptions cropImageOptions4 = this.cropImageOptions;
                            if (cropImageOptions4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                                throw null;
                            } else if (cropImageOptions4.imageSourceIncludeCamera) {
                                showImageSourceDialog(new CropImageActivity$onCreate$1(this));
                            }
                        }
                        CropImageOptions cropImageOptions5 = this.cropImageOptions;
                        if (cropImageOptions5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                            throw null;
                        } else if (cropImageOptions5.imageSourceIncludeGallery) {
                            this.pickImageGallery.launch(MediaTypes.ImageAllMimeType);
                        } else {
                            CropImageOptions cropImageOptions6 = this.cropImageOptions;
                            if (cropImageOptions6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                                throw null;
                            } else if (cropImageOptions6.imageSourceIncludeCamera) {
                                openCamera();
                            } else {
                                finish();
                            }
                        }
                    }
                } else {
                    CropImageView cropImageView2 = this.cropImageView;
                    if (cropImageView2 != null) {
                        cropImageView2.setImageUriAsync(this.cropImageUri);
                    }
                }
            } else {
                String string2 = bundle.getString(BUNDLE_KEY_TMP_URI);
                if (string2 == null) {
                    parse = null;
                } else {
                    parse = Uri.parse(string2);
                    Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
                }
                this.latestTmpUri = parse;
            }
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar == null) {
                return;
            }
            CropImageOptions cropImageOptions7 = this.cropImageOptions;
            if (cropImageOptions7 != null) {
                if (cropImageOptions7.activityTitle.length() > 0) {
                    CropImageOptions cropImageOptions8 = this.cropImageOptions;
                    if (cropImageOptions8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                        throw null;
                    }
                    string = cropImageOptions8.activityTitle;
                } else {
                    string = getResources().getString(C1150R.string.crop_image_activity_title);
                }
                setTitle(string);
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        throw null;
    }

    private final void showIntentChooser() {
        CropImageIntentChooser cropImageIntentChooser = new CropImageIntentChooser(this, new CropImageIntentChooser.ResultCallback() { // from class: com.canhub.cropper.CropImageActivity$showIntentChooser$ciIntentChooser$1
            @Override // com.canhub.cropper.CropImageIntentChooser.ResultCallback
            public void onSuccess(Uri uri) {
                CropImageActivity.this.onPickImageResult(uri);
            }

            @Override // com.canhub.cropper.CropImageIntentChooser.ResultCallback
            public void onCancelled() {
                CropImageActivity.this.setResultCancel();
            }
        });
        CropImageOptions cropImageOptions = this.cropImageOptions;
        if (cropImageOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            throw null;
        }
        String str = cropImageOptions.intentChooserTitle;
        if (str != null) {
            if (!(!StringsKt.isBlank(str))) {
                str = null;
            }
            if (str != null) {
                cropImageIntentChooser.setIntentChooserTitle(str);
            }
        }
        List<String> list = cropImageOptions.intentChooserPriorityList;
        if (list != null) {
            if (!(!list.isEmpty())) {
                list = null;
            }
            if (list != null) {
                cropImageIntentChooser.setupPriorityAppsList(list);
            }
        }
        cropImageIntentChooser.showChooserIntent(cropImageOptions.imageSourceIncludeCamera, cropImageOptions.imageSourceIncludeGallery, cropImageOptions.imageSourceIncludeCamera ? getTmpFileUri() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openSource(Source source) {
        int r2 = WhenMappings.$EnumSwitchMapping$0[source.ordinal()];
        if (r2 == 1) {
            openCamera();
        } else if (r2 != 2) {
        } else {
            this.pickImageGallery.launch(MediaTypes.ImageAllMimeType);
        }
    }

    private final void openCamera() {
        Uri tmpFileUri = getTmpFileUri();
        this.latestTmpUri = tmpFileUri;
        this.takePicture.launch(tmpFileUri);
    }

    private final Uri getTmpFileUri() {
        File tmpFile = File.createTempFile("tmp_image_file", ".png", getCacheDir());
        tmpFile.createNewFile();
        tmpFile.deleteOnExit();
        Intrinsics.checkNotNullExpressionValue(tmpFile, "tmpFile");
        return GetUriForFile.getUriForFile(this, tmpFile);
    }

    public void showImageSourceDialog(final Function1<? super Source, Unit> openSource) {
        Intrinsics.checkNotNullParameter(openSource, "openSource");
        new AlertDialog.Builder(this).setCancelable(false).setTitle(C1150R.string.pick_image_chooser_title).setItems(new String[]{getString(C1150R.string.pick_image_camera), getString(C1150R.string.pick_image_gallery)}, new DialogInterface.OnClickListener() { // from class: com.canhub.cropper.CropImageActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r3) {
                CropImageActivity.m1487showImageSourceDialog$lambda10(Function1.this, dialogInterface, r3);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showImageSourceDialog$lambda-10  reason: not valid java name */
    public static final void m1487showImageSourceDialog$lambda10(Function1 openSource, DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(openSource, "$openSource");
        openSource.invoke(r2 == 0 ? Source.CAMERA : Source.GALLERY);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CropImageView cropImageView = this.cropImageView;
        if (cropImageView != null) {
            cropImageView.setOnSetImageUriCompleteListener(this);
        }
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 == null) {
            return;
        }
        cropImageView2.setOnCropImageCompleteListener(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        CropImageView cropImageView = this.cropImageView;
        if (cropImageView != null) {
            cropImageView.setOnSetImageUriCompleteListener(null);
        }
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 == null) {
            return;
        }
        cropImageView2.setOnCropImageCompleteListener(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY_TMP_URI, String.valueOf(this.latestTmpUri));
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e6  */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onCreateOptionsMenu(android.view.Menu r8) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageActivity.onCreateOptionsMenu(android.view.Menu):boolean");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == C1150R.C1154id.crop_image_menu_crop) {
            cropImage();
            return true;
        } else if (itemId == C1150R.C1154id.ic_rotate_left_24) {
            CropImageOptions cropImageOptions = this.cropImageOptions;
            if (cropImageOptions != null) {
                rotateImage(-cropImageOptions.rotationDegrees);
                return true;
            }
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            throw null;
        } else if (itemId == C1150R.C1154id.ic_rotate_right_24) {
            CropImageOptions cropImageOptions2 = this.cropImageOptions;
            if (cropImageOptions2 != null) {
                rotateImage(cropImageOptions2.rotationDegrees);
                return true;
            }
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            throw null;
        } else if (itemId == C1150R.C1154id.ic_flip_24_horizontally) {
            CropImageView cropImageView = this.cropImageView;
            if (cropImageView == null) {
                return true;
            }
            cropImageView.flipImageHorizontally();
            return true;
        } else if (itemId != C1150R.C1154id.ic_flip_24_vertically) {
            if (itemId == 16908332) {
                setResultCancel();
                return true;
            }
            return super.onOptionsItemSelected(item);
        } else {
            CropImageView cropImageView2 = this.cropImageView;
            if (cropImageView2 == null) {
                return true;
            }
            cropImageView2.flipImageVertically();
            return true;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPickImageResult(Uri uri) {
        if (uri == null) {
            setResultCancel();
            return;
        }
        this.cropImageUri = uri;
        CropImageView cropImageView = this.cropImageView;
        if (cropImageView == null) {
            return;
        }
        cropImageView.setImageUriAsync(uri);
    }

    @Override // com.canhub.cropper.CropImageView.OnSetImageUriCompleteListener
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception exc) {
        CropImageView cropImageView;
        CropImageView cropImageView2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (exc == null) {
            CropImageOptions cropImageOptions = this.cropImageOptions;
            if (cropImageOptions == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            if (cropImageOptions.initialCropWindowRectangle != null && (cropImageView2 = this.cropImageView) != null) {
                CropImageOptions cropImageOptions2 = this.cropImageOptions;
                if (cropImageOptions2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                    throw null;
                }
                cropImageView2.setCropRect(cropImageOptions2.initialCropWindowRectangle);
            }
            CropImageOptions cropImageOptions3 = this.cropImageOptions;
            if (cropImageOptions3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            if (cropImageOptions3.initialRotation > 0 && (cropImageView = this.cropImageView) != null) {
                CropImageOptions cropImageOptions4 = this.cropImageOptions;
                if (cropImageOptions4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                    throw null;
                }
                cropImageView.setRotatedDegrees(cropImageOptions4.initialRotation);
            }
            CropImageOptions cropImageOptions5 = this.cropImageOptions;
            if (cropImageOptions5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            } else if (cropImageOptions5.skipEditing) {
                cropImage();
                return;
            } else {
                return;
            }
        }
        setResult(null, exc, 1);
    }

    @Override // com.canhub.cropper.CropImageView.OnCropImageCompleteListener
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(result, "result");
        setResult(result.getUriContent(), result.getError(), result.getSampleSize());
    }

    public void cropImage() {
        CropImageOptions cropImageOptions = this.cropImageOptions;
        if (cropImageOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            throw null;
        } else if (cropImageOptions.noOutputImage) {
            setResult(null, null, 1);
        } else {
            CropImageView cropImageView = this.cropImageView;
            if (cropImageView == null) {
                return;
            }
            CropImageOptions cropImageOptions2 = this.cropImageOptions;
            if (cropImageOptions2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            Bitmap.CompressFormat compressFormat = cropImageOptions2.outputCompressFormat;
            CropImageOptions cropImageOptions3 = this.cropImageOptions;
            if (cropImageOptions3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            int r5 = cropImageOptions3.outputCompressQuality;
            CropImageOptions cropImageOptions4 = this.cropImageOptions;
            if (cropImageOptions4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            int r6 = cropImageOptions4.outputRequestWidth;
            CropImageOptions cropImageOptions5 = this.cropImageOptions;
            if (cropImageOptions5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            int r7 = cropImageOptions5.outputRequestHeight;
            CropImageOptions cropImageOptions6 = this.cropImageOptions;
            if (cropImageOptions6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
            CropImageView.RequestSizeOptions requestSizeOptions = cropImageOptions6.outputRequestSizeOptions;
            CropImageOptions cropImageOptions7 = this.cropImageOptions;
            if (cropImageOptions7 != null) {
                cropImageView.croppedImageAsync(compressFormat, r5, r6, r7, requestSizeOptions, cropImageOptions7.customOutputUri);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                throw null;
            }
        }
    }

    public void setCropImageView(CropImageView cropImageView) {
        Intrinsics.checkNotNullParameter(cropImageView, "cropImageView");
        this.cropImageView = cropImageView;
    }

    public void rotateImage(int r2) {
        CropImageView cropImageView = this.cropImageView;
        if (cropImageView == null) {
            return;
        }
        cropImageView.rotateImage(r2);
    }

    public void setResult(Uri uri, Exception exc, int r4) {
        setResult(exc == null ? -1 : CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, getResultIntent(uri, exc, r4));
        finish();
    }

    public void setResultCancel() {
        setResult(0);
        finish();
    }

    public Intent getResultIntent(Uri uri, Exception exc, int r13) {
        CropImageView cropImageView = this.cropImageView;
        Uri imageUri = cropImageView == null ? null : cropImageView.getImageUri();
        CropImageView cropImageView2 = this.cropImageView;
        float[] cropPoints = cropImageView2 == null ? null : cropImageView2.getCropPoints();
        CropImageView cropImageView3 = this.cropImageView;
        Rect cropRect = cropImageView3 == null ? null : cropImageView3.getCropRect();
        CropImageView cropImageView4 = this.cropImageView;
        int rotatedDegrees = cropImageView4 == null ? 0 : cropImageView4.getRotatedDegrees();
        CropImageView cropImageView5 = this.cropImageView;
        CropImage.ActivityResult activityResult = new CropImage.ActivityResult(imageUri, uri, exc, cropPoints, cropRect, rotatedDegrees, cropImageView5 == null ? null : cropImageView5.getWholeImageRect(), r13);
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, activityResult);
        return intent;
    }

    public void updateMenuItemIconColor(Menu menu, int r3, int r4) {
        Drawable icon;
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuItem findItem = menu.findItem(r3);
        if (findItem == null || (icon = findItem.getIcon()) == null) {
            return;
        }
        try {
            icon.mutate();
            icon.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(r4, BlendModeCompat.SRC_ATOP));
            findItem.setIcon(icon);
        } catch (Exception e) {
            Log.w("AIC", "Failed to update menu item color", e);
        }
    }

    /* compiled from: CropImageActivity.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lcom/canhub/cropper/CropImageActivity$Companion;", "", "()V", "BUNDLE_KEY_TMP_URI", "", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
