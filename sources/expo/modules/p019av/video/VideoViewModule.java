package expo.modules.p019av.video;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.core.arguments.MapArguments;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import expo.modules.p019av.ViewUtils;
import expo.modules.p019av.video.scalablevideoview.ScalableType;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: VideoViewModule.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/av/video/VideoViewModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.video.VideoViewModule */
/* loaded from: classes4.dex */
public final class VideoViewModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(this);
        moduleDefinitionBuilder.Name("ExpoVideoView");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VideoViewWrapper.class);
        if (!(moduleDefinitionBuilder.getViewManagerDefinition() == null)) {
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        }
        ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass);
        final VideoViewModule$definition$1$1$1 videoViewModule$definition$1$1$1 = new Function1<VideoViewWrapper, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$1$1$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VideoViewWrapper videoViewWrapper) {
                invoke2(videoViewWrapper);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(VideoViewWrapper view) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getVideoViewInstance().onDropViewInstance();
            }
        };
        viewDefinitionBuilder.setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$lambda-2$lambda-0$$inlined$OnViewDestroys$1
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
                Function1.this.invoke((VideoViewWrapper) it);
            }
        });
        viewDefinitionBuilder.Events("onStatusUpdate", "onLoadStart", "onLoad", "onError", "onReadyForDisplay", "onFullscreenUpdate");
        viewDefinitionBuilder.getProps().put("status", new ConcreteViewProp("status", AnyTypeKt.toAnyType(Reflection.typeOf(ReadableMap.class)), new Function2<VideoViewWrapper, ReadableMap, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$1$1$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VideoViewWrapper videoViewWrapper, ReadableMap readableMap) {
                invoke2(videoViewWrapper, readableMap);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(VideoViewWrapper view, ReadableMap status) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(status, "status");
                view.getVideoViewInstance().setStatus(new MapArguments(status.toHashMap()), null);
            }
        }));
        viewDefinitionBuilder.getProps().put("useNativeControls", new ConcreteViewProp("useNativeControls", AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), new Function2<VideoViewWrapper, Boolean, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$1$1$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VideoViewWrapper videoViewWrapper, Boolean bool) {
                invoke(videoViewWrapper, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(VideoViewWrapper view, boolean z) {
                Intrinsics.checkNotNullParameter(view, "view");
                view.getVideoViewInstance().setUseNativeControls(z);
            }
        }));
        viewDefinitionBuilder.getProps().put("source", new ConcreteViewProp("source", AnyTypeKt.toAnyType(Reflection.typeOf(ReadableMap.class)), new Function2<VideoViewWrapper, ReadableMap, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$1$1$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VideoViewWrapper videoViewWrapper, ReadableMap readableMap) {
                invoke2(videoViewWrapper, readableMap);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(VideoViewWrapper view, ReadableMap source) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(source, "source");
                view.getVideoViewInstance().setSource(new MapArguments(source.toHashMap()));
            }
        }));
        viewDefinitionBuilder.getProps().put(ViewProps.RESIZE_MODE, new ConcreteViewProp(ViewProps.RESIZE_MODE, AnyTypeKt.toAnyType(Reflection.typeOf(String.class)), new Function2<VideoViewWrapper, String, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$1$1$5
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(VideoViewWrapper videoViewWrapper, String str) {
                invoke2(videoViewWrapper, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(VideoViewWrapper view, String resizeModeOrdinalString) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(resizeModeOrdinalString, "resizeModeOrdinalString");
                view.getVideoViewInstance().setResizeMode(ScalableType.values()[Integer.parseInt(resizeModeOrdinalString)]);
            }
        }));
        moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
        moduleDefinitionBuilder.Constants(TuplesKt.m176to("ScaleNone", String.valueOf(ScalableType.LEFT_TOP.ordinal())), TuplesKt.m176to("ScaleToFill", String.valueOf(ScalableType.FIT_XY.ordinal())), TuplesKt.m176to("ScaleAspectFit", String.valueOf(ScalableType.FIT_CENTER.ordinal())), TuplesKt.m176to("ScaleAspectFill", String.valueOf(ScalableType.CENTER_CROP.ordinal())));
        ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent("setFullscreen", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.av.video.VideoViewModule$definition$lambda-2$$inlined$AsyncFunction$1
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = args[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    Object obj2 = args[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                    boolean booleanValue = ((Boolean) obj2).booleanValue();
                    ViewUtils.INSTANCE.tryRunWithVideoView(VideoViewModule.this.getAppContext().getLegacyModuleRegistry(), ((Integer) obj).intValue(), new VideoViewModule$definition$1$2$1(booleanValue, promise), promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent("setFullscreen", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE)), AnyTypeKt.toAnyType(Reflection.typeOf(Boolean.TYPE)), AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.av.video.VideoViewModule$definition$lambda-2$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                    Object obj2 = it[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                    Object obj3 = it[2];
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    Promise promise = (Promise) obj3;
                    boolean booleanValue = ((Boolean) obj2).booleanValue();
                    ViewUtils.INSTANCE.tryRunWithVideoView(VideoViewModule.this.getAppContext().getLegacyModuleRegistry(), ((Integer) obj).intValue(), new VideoViewModule$definition$1$2$1(booleanValue, promise), promise);
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("setFullscreen", asyncFunctionComponent);
        return moduleDefinitionBuilder.buildModule();
    }
}
