package expo.modules.camera;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.cameraview.AspectRatio;
import com.google.android.cameraview.Size;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.camera.CameraExceptions;
import expo.modules.camera.tasks.ResolveTakenPictureAsyncTask;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.core.utilities.EmulatorUtilities;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CommonExceptions;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KTypeProjection;
import kotlinx.coroutines.Debug;

/* compiled from: CameraViewModule.kt */
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/camera/CameraViewModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "cacheDirectory", "Ljava/io/File;", "getCacheDirectory", "()Ljava/io/File;", "permissionsManager", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissionsManager", "()Lexpo/modules/interfaces/permissions/Permissions;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "findView", "Lexpo/modules/camera/ExpoCameraView;", "viewTag", "", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class CameraViewModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionComponent asyncFunctionComponent6;
        AsyncFunctionComponent asyncFunctionComponent7;
        AsyncFunctionComponent asyncFunctionComponent8;
        AsyncFunctionComponent asyncFunctionComponent9;
        AsyncFunctionComponent asyncFunctionComponent10;
        AsyncFunctionComponent asyncFunctionComponent11;
        AsyncFunctionComponent asyncFunctionComponent12;
        AsyncFunctionComponent asyncFunctionComponent13;
        ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(this);
        moduleDefinitionBuilder.Name("ExponentCamera");
        moduleDefinitionBuilder.Constants(TuplesKt.m176to("Type", MapsKt.mapOf(TuplesKt.m176to("front", 1), TuplesKt.m176to("back", 0))), TuplesKt.m176to("FlashMode", MapsKt.mapOf(TuplesKt.m176to(Debug.DEBUG_PROPERTY_VALUE_OFF, 0), TuplesKt.m176to("on", 1), TuplesKt.m176to("auto", 3), TuplesKt.m176to("torch", 2))), TuplesKt.m176to("AutoFocus", MapsKt.mapOf(TuplesKt.m176to("on", true), TuplesKt.m176to(Debug.DEBUG_PROPERTY_VALUE_OFF, false))), TuplesKt.m176to(ExifInterface.TAG_WHITE_BALANCE, MapsKt.mapOf(TuplesKt.m176to("auto", 0), TuplesKt.m176to("cloudy", 1), TuplesKt.m176to("sunny", 2), TuplesKt.m176to("shadow", 3), TuplesKt.m176to("fluorescent", 4), TuplesKt.m176to("incandescent", 5))), TuplesKt.m176to("VideoQuality", MapsKt.mapOf(TuplesKt.m176to("2160p", 0), TuplesKt.m176to("1080p", 1), TuplesKt.m176to("720p", 2), TuplesKt.m176to("480p", 3), TuplesKt.m176to("4:3", 4))));
        ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Integer.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent("pausePreview", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$1
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    findView = CameraViewModule.this.findView(((Integer) promise).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().pausePreview();
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent("pausePreview", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().pausePreview();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("pausePreview", asyncFunctionComponent);
        asyncFunctionComponent.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Integer.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent2 = new AsyncFunctionWithPromiseComponent("resumePreview", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$3
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    findView = CameraViewModule.this.findView(((Integer) promise).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().resumePreview();
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent2 = new AsyncFunctionComponent("resumePreview", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().resumePreview();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("resumePreview", asyncFunctionComponent2);
        asyncFunctionComponent2.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent3 = new AsyncFunctionWithPromiseComponent("takePicture", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Options.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$5
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    ExpoCameraView findView;
                    File cacheDirectory;
                    File cacheDirectory2;
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = args[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.camera.PictureOptions");
                    Options options = (Options) obj;
                    Object obj2 = args[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj2).intValue());
                    if (!EmulatorUtilities.INSTANCE.isRunningOnEmulator()) {
                        if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                            cacheDirectory2 = CameraViewModule.this.getCacheDirectory();
                            findView.takePicture(options, promise, cacheDirectory2);
                            return;
                        }
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    byte[] generateSimulatorPhoto = CameraViewHelper.INSTANCE.generateSimulatorPhoto(findView.getWidth(), findView.getHeight());
                    cacheDirectory = CameraViewModule.this.getCacheDirectory();
                    new ResolveTakenPictureAsyncTask(generateSimulatorPhoto, promise, options, cacheDirectory, findView).execute(new Void[0]);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent3 = new AsyncFunctionComponent("takePicture", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Options.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    File cacheDirectory;
                    File cacheDirectory2;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.camera.PictureOptions");
                    Options options = (Options) obj;
                    Object obj2 = it[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                    Object obj3 = it[2];
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    Promise promise = (Promise) obj3;
                    findView = CameraViewModule.this.findView(((Integer) obj2).intValue());
                    if (!EmulatorUtilities.INSTANCE.isRunningOnEmulator()) {
                        if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                            cacheDirectory2 = CameraViewModule.this.getCacheDirectory();
                            findView.takePicture(options, promise, cacheDirectory2);
                            return Unit.INSTANCE;
                        }
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    byte[] generateSimulatorPhoto = CameraViewHelper.INSTANCE.generateSimulatorPhoto(findView.getWidth(), findView.getHeight());
                    cacheDirectory = CameraViewModule.this.getCacheDirectory();
                    return new ResolveTakenPictureAsyncTask(generateSimulatorPhoto, promise, options, cacheDirectory, findView).execute(new Void[0]);
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("takePicture", asyncFunctionComponent3);
        asyncFunctionComponent3.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent4 = new AsyncFunctionWithPromiseComponent("record", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(RecordingOptions.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$7
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Permissions permissionsManager;
                    ExpoCameraView findView;
                    File cacheDirectory;
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = args[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.camera.RecordingOptions");
                    RecordingOptions recordingOptions = (RecordingOptions) obj;
                    Object obj2 = args[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                    int intValue = ((Integer) obj2).intValue();
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    if (permissionsManager.hasGrantedPermissions("android.permission.RECORD_AUDIO")) {
                        findView = CameraViewModule.this.findView(intValue);
                        if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                            cacheDirectory = CameraViewModule.this.getCacheDirectory();
                            findView.record(recordingOptions, promise, cacheDirectory);
                            return;
                        }
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    throw new CommonExceptions.MissingPermissions("android.permission.RECORD_AUDIO");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent4 = new AsyncFunctionComponent("record", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(RecordingOptions.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$8
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    ExpoCameraView findView;
                    File cacheDirectory;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.camera.RecordingOptions");
                    RecordingOptions recordingOptions = (RecordingOptions) obj;
                    Object obj2 = it[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                    Object obj3 = it[2];
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    Promise promise = (Promise) obj3;
                    int intValue = ((Integer) obj2).intValue();
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    if (permissionsManager.hasGrantedPermissions("android.permission.RECORD_AUDIO")) {
                        findView = CameraViewModule.this.findView(intValue);
                        if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                            cacheDirectory = CameraViewModule.this.getCacheDirectory();
                            findView.record(recordingOptions, promise, cacheDirectory);
                            return Unit.INSTANCE;
                        }
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    throw new CommonExceptions.MissingPermissions("android.permission.RECORD_AUDIO");
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("record", asyncFunctionComponent4);
        asyncFunctionComponent4.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Integer.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent5 = new AsyncFunctionWithPromiseComponent("stopRecording", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$9
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    findView = CameraViewModule.this.findView(((Integer) promise).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().stopRecording();
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent5 = new AsyncFunctionComponent("stopRecording", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$10
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj).intValue());
                    if (findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        findView.getCameraView$expo_camera_release().stopRecording();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("stopRecording", asyncFunctionComponent5);
        asyncFunctionComponent5.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Integer.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent6 = new AsyncFunctionWithPromiseComponent("getSupportedRatios", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$11
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    findView = CameraViewModule.this.findView(((Integer) promise).intValue());
                    if (!findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    Set<AspectRatio> supportedAspectRatios = findView.getCameraView$expo_camera_release().getSupportedAspectRatios();
                    Intrinsics.checkNotNullExpressionValue(supportedAspectRatios, "view.cameraView.supportedAspectRatios");
                    Set<AspectRatio> set = supportedAspectRatios;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
                    for (AspectRatio aspectRatio : set) {
                        arrayList.add(aspectRatio.toString());
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent6 = new AsyncFunctionComponent("getSupportedRatios", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$12
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj).intValue());
                    if (!findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    Set<AspectRatio> supportedAspectRatios = findView.getCameraView$expo_camera_release().getSupportedAspectRatios();
                    Intrinsics.checkNotNullExpressionValue(supportedAspectRatios, "view.cameraView.supportedAspectRatios");
                    Set<AspectRatio> set = supportedAspectRatios;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
                    for (AspectRatio aspectRatio : set) {
                        arrayList.add(aspectRatio.toString());
                    }
                    return arrayList;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("getSupportedRatios", asyncFunctionComponent6);
        asyncFunctionComponent6.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Integer.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent7 = new AsyncFunctionWithPromiseComponent("getAvailablePictureSizes", new AnyType[]{AnyTypeKt.toAnyType(Reflection.nullableTypeOf(String.class))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$13
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) args[0];
                    findView = CameraViewModule.this.findView(((Integer) promise).intValue());
                    if (!findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    SortedSet<Size> sizes = findView.getCameraView$expo_camera_release().getAvailablePictureSizes(AspectRatio.parse(str));
                    Intrinsics.checkNotNullExpressionValue(sizes, "sizes");
                    SortedSet<Size> sortedSet = sizes;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(sortedSet, 10));
                    for (Size size : sortedSet) {
                        arrayList.add(size.toString());
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent7 = new AsyncFunctionComponent("getAvailablePictureSizes", new AnyType[]{AnyTypeKt.toAnyType(Reflection.nullableTypeOf(String.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$14
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    ExpoCameraView findView;
                    Intrinsics.checkNotNullParameter(it, "it");
                    String str = (String) it[0];
                    Object obj = it[1];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    findView = CameraViewModule.this.findView(((Integer) obj).intValue());
                    if (!findView.getCameraView$expo_camera_release().isCameraOpened()) {
                        throw new CameraExceptions.CameraIsNotRunning();
                    }
                    SortedSet<Size> sizes = findView.getCameraView$expo_camera_release().getAvailablePictureSizes(AspectRatio.parse(str));
                    Intrinsics.checkNotNullExpressionValue(sizes, "sizes");
                    SortedSet<Size> sortedSet = sizes;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(sortedSet, 10));
                    for (Size size : sortedSet) {
                        arrayList.add(size.toString());
                    }
                    return arrayList;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("getAvailablePictureSizes", asyncFunctionComponent7);
        asyncFunctionComponent7.runOnQueue(Queues.MAIN);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent8 = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$15
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.CAMERA");
                }
            });
        } else {
            asyncFunctionComponent8 = new AsyncFunctionComponent("requestPermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$16
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.CAMERA");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("requestPermissionsAsync", asyncFunctionComponent8);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent9 = new AsyncFunctionWithPromiseComponent("requestCameraPermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$17
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.CAMERA");
                }
            });
        } else {
            asyncFunctionComponent9 = new AsyncFunctionComponent("requestCameraPermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$18
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.CAMERA");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("requestCameraPermissionsAsync", asyncFunctionComponent9);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent10 = new AsyncFunctionWithPromiseComponent("requestMicrophonePermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$19
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.RECORD_AUDIO");
                }
            });
        } else {
            asyncFunctionComponent10 = new AsyncFunctionComponent("requestMicrophonePermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$20
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.askForPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.RECORD_AUDIO");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("requestMicrophonePermissionsAsync", asyncFunctionComponent10);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent11 = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$21
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.CAMERA");
                }
            });
        } else {
            asyncFunctionComponent11 = new AsyncFunctionComponent("getPermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$22
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.CAMERA");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("getPermissionsAsync", asyncFunctionComponent11);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent12 = new AsyncFunctionWithPromiseComponent("getCameraPermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$23
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.CAMERA");
                }
            });
        } else {
            asyncFunctionComponent12 = new AsyncFunctionComponent("getCameraPermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$24
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.CAMERA");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("getCameraPermissionsAsync", asyncFunctionComponent12);
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent13 = new AsyncFunctionWithPromiseComponent("getMicrophonePermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$25
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, promise, "android.permission.RECORD_AUDIO");
                }
            });
        } else {
            asyncFunctionComponent13 = new AsyncFunctionComponent("getMicrophonePermissionsAsync", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$$inlined$AsyncFunction$26
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Permissions permissionsManager;
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    permissionsManager = CameraViewModule.this.getPermissionsManager();
                    Permissions.CC.getPermissionsWithPermissionsManager(permissionsManager, (Promise) obj, "android.permission.RECORD_AUDIO");
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("getMicrophonePermissionsAsync", asyncFunctionComponent13);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoCameraView.class);
        if (!(moduleDefinitionBuilder.getViewManagerDefinition() == null)) {
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        }
        ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass);
        viewDefinitionBuilder.Events("onCameraReady", "onMountError", "onBarCodeScanned", "onFacesDetected", "onFaceDetectionError", "onPictureSaved");
        final Function1<ExpoCameraView, Unit> function1 = new Function1<ExpoCameraView, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView) {
                invoke2(expoCameraView);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ExpoCameraView view) {
                Object obj;
                Intrinsics.checkNotNullParameter(view, "view");
                try {
                    obj = CameraViewModule.this.getAppContext().getLegacyModuleRegistry().getModule(UIManager.class);
                } catch (Exception unused) {
                    obj = null;
                }
                UIManager uIManager = (UIManager) obj;
                if (uIManager != null) {
                    uIManager.unregisterLifecycleEventListener(view);
                }
                view.getCameraView$expo_camera_release().stop();
            }
        };
        viewDefinitionBuilder.setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$lambda-16$lambda-15$$inlined$OnViewDestroys$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1.this.invoke((ExpoCameraView) it);
            }
        });
        viewDefinitionBuilder.getProps().put(SessionDescription.ATTR_TYPE, new ConcreteViewProp(SessionDescription.ATTR_TYPE, AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), new Function2<ExpoCameraView, Integer, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Integer num) {
                invoke(expoCameraView, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, int r3) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setFacing(r3);
            }
        }));
        viewDefinitionBuilder.getProps().put("ratio", new ConcreteViewProp("ratio", AnyTypeKt.toAnyType(Reflection.nullableTypeOf(String.class)), new Function2<ExpoCameraView, String, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, String str) {
                invoke2(expoCameraView, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ExpoCameraView view, String str) {
                Intrinsics.checkNotNullParameter(view, "view");
                if (str == null) {
                    return;
                }
                view.getCameraView$expo_camera_release().setAspectRatio(AspectRatio.parse(str));
            }
        }));
        viewDefinitionBuilder.getProps().put("flashMode", new ConcreteViewProp("flashMode", AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), new Function2<ExpoCameraView, Integer, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Integer num) {
                invoke(expoCameraView, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, int r3) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setFlash(r3);
            }
        }));
        viewDefinitionBuilder.getProps().put("autoFocus", new ConcreteViewProp("autoFocus", AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), new Function2<ExpoCameraView, Boolean, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$5
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Boolean bool) {
                invoke(expoCameraView, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setAutoFocus(z);
            }
        }));
        viewDefinitionBuilder.getProps().put("focusDepth", new ConcreteViewProp("focusDepth", AnyTypeKt.toAnyType(Reflection.typeOf(Float.TYPE)), new Function2<ExpoCameraView, Float, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$6
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Float f) {
                invoke(expoCameraView, f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, float f) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setFocusDepth(f);
            }
        }));
        viewDefinitionBuilder.getProps().put("zoom", new ConcreteViewProp("zoom", AnyTypeKt.toAnyType(Reflection.typeOf(Float.TYPE)), new Function2<ExpoCameraView, Float, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$7
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Float f) {
                invoke(expoCameraView, f.floatValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, float f) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setZoom(f);
            }
        }));
        viewDefinitionBuilder.getProps().put("whiteBalance", new ConcreteViewProp("whiteBalance", AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), new Function2<ExpoCameraView, Integer, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$8
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Integer num) {
                invoke(expoCameraView, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, int r3) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setWhiteBalance(r3);
            }
        }));
        viewDefinitionBuilder.getProps().put("pictureSize", new ConcreteViewProp("pictureSize", AnyTypeKt.toAnyType(Reflection.nullableTypeOf(String.class)), new Function2<ExpoCameraView, String, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$9
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, String str) {
                invoke2(expoCameraView, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ExpoCameraView view, String str) {
                Intrinsics.checkNotNullParameter(view, "view");
                if (str == null) {
                    return;
                }
                view.getCameraView$expo_camera_release().setPictureSize(Size.parse(str));
            }
        }));
        viewDefinitionBuilder.getProps().put("barCodeScannerSettings", new ConcreteViewProp("barCodeScannerSettings", AnyTypeKt.toAnyType(Reflection.nullableTypeOf(Map.class, KTypeProjection.Companion.invariant(Reflection.typeOf(String.class)), KTypeProjection.Companion.invariant(Reflection.nullableTypeOf(Object.class)))), new Function2<ExpoCameraView, Map<String, ? extends Object>, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$10
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Map<String, ? extends Object> map) {
                invoke2(expoCameraView, map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ExpoCameraView view, Map<String, ? extends Object> map) {
                Intrinsics.checkNotNullParameter(view, "view");
                if (map == null) {
                    return;
                }
                view.setBarCodeScannerSettings(new BarCodeScannerSettings(map));
            }
        }));
        viewDefinitionBuilder.getProps().put("useCamera2Api", new ConcreteViewProp("useCamera2Api", AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), new Function2<ExpoCameraView, Boolean, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$11
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Boolean bool) {
                invoke(expoCameraView, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getCameraView$expo_camera_release().setUsingCamera2Api(z);
            }
        }));
        viewDefinitionBuilder.getProps().put("barCodeScannerEnabled", new ConcreteViewProp("barCodeScannerEnabled", AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), new Function2<ExpoCameraView, Boolean, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$12
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Boolean bool) {
                invoke(expoCameraView, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.setShouldScanBarCodes(z);
            }
        }));
        viewDefinitionBuilder.getProps().put("faceDetectorEnabled", new ConcreteViewProp("faceDetectorEnabled", AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), new Function2<ExpoCameraView, Boolean, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$13
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Boolean bool) {
                invoke(expoCameraView, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ExpoCameraView view, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.setShouldDetectFaces(z);
            }
        }));
        viewDefinitionBuilder.getProps().put("faceDetectorSettings", new ConcreteViewProp("faceDetectorSettings", AnyTypeKt.toAnyType(Reflection.nullableTypeOf(Map.class, KTypeProjection.Companion.invariant(Reflection.typeOf(String.class)), KTypeProjection.Companion.invariant(Reflection.typeOf(Object.class)))), new Function2<ExpoCameraView, Map<String, ? extends Object>, Unit>() { // from class: expo.modules.camera.CameraViewModule$definition$1$14$14
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ExpoCameraView expoCameraView, Map<String, ? extends Object> map) {
                invoke2(expoCameraView, map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ExpoCameraView view, Map<String, ? extends Object> map) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.setFaceDetectorSettings(map);
            }
        }));
        moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
        return moduleDefinitionBuilder.buildModule();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getCacheDirectory() {
        return getAppContext().getCacheDirectory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Permissions getPermissionsManager() {
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            return permissions;
        }
        throw new CommonExceptions.PermissionsModuleNotFound();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExpoCameraView findView(int r3) {
        ExpoCameraView expoCameraView = (ExpoCameraView) getAppContext().findView(r3);
        if (expoCameraView != null) {
            return expoCameraView;
        }
        throw new CommonExceptions.ViewNotFound(Reflection.getOrCreateKotlinClass(ExpoCameraView.class), r3);
    }
}
