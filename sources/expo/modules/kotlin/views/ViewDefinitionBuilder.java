package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.modules.DefinitionMarker;
import expo.modules.kotlin.types.AnyTypeKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KClasses;

/* compiled from: ViewDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010&\u001a\u00020\u000b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00180(H\u0007¢\u0006\u0004\b)\u0010*J\u001f\u0010&\u001a\u00020\u000b2\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180(\"\u00020\u0018¢\u0006\u0002\u0010*J%\u0010+\u001a\u00020\u000b2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b.H\u0086\bø\u0001\u0000J=\u0010/\u001a\u00020\u000b\"\n\b\u0001\u00100\u0018\u0001*\u00020\u00022#\b\b\u0010,\u001a\u001d\u0012\u0013\u0012\u0011H0¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u000b0\nH\u0086\bø\u0001\u0000J=\u00104\u001a\u00020\u000b\"\n\b\u0001\u00100\u0018\u0001*\u00020\u00022#\b\b\u0010,\u001a\u001d\u0012\u0013\u0012\u0011H0¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u000b0\nH\u0086\bø\u0001\u0000Jb\u00105\u001a\u00020\u000b\"\n\b\u0001\u00100\u0018\u0001*\u00020\u0002\"\u0006\b\u0002\u00106\u0018\u00012\u0006\u00102\u001a\u00020\u001828\b\b\u0010,\u001a2\u0012\u0013\u0012\u0011H0¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0013\u0012\u0011H6¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u000b07H\u0086\bø\u0001\u0000J\u0006\u00109\u001a\u00020:J\u001a\u0010;\u001a\u0014\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020\u000207H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R2\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\r\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R0\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\r\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR&\u0010\u001f\u001a\u0004\u0018\u00010 8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\r\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006>"}, m183d2 = {"Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "T", "Landroid/view/View;", "", "viewType", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "onViewDestroys", "Lkotlin/Function1;", "", "getOnViewDestroys$annotations", "()V", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "setOnViewDestroys", "(Lkotlin/jvm/functions/Function1;)V", "onViewDidUpdateProps", "getOnViewDidUpdateProps$annotations", "getOnViewDidUpdateProps", "setOnViewDidUpdateProps", "props", "", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps$annotations", "getProps", "()Ljava/util/Map;", "setProps", "(Ljava/util/Map;)V", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$annotations", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "setViewGroupDefinition", "(Lexpo/modules/kotlin/views/ViewGroupDefinition;)V", "Events", "callbacks", "", "EventsWithArray", "([Ljava/lang/String;)V", "GroupView", TtmlNode.TAG_BODY, "Lexpo/modules/kotlin/views/ViewGroupDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "OnViewDestroys", "ViewType", "Lkotlin/ParameterName;", "name", "view", "OnViewDidUpdateProps", "Prop", "PropType", "Lkotlin/Function2;", "prop", "build", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "createViewFactory", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
@DefinitionMarker
/* loaded from: classes4.dex */
public final class ViewDefinitionBuilder<T extends View> {
    private CallbacksDefinition callbacksDefinition;
    private Function1<? super View, Unit> onViewDestroys;
    private Function1<? super View, Unit> onViewDidUpdateProps;
    private Map<String, AnyViewProp> props;
    private ViewGroupDefinition viewGroupDefinition;
    private final KClass<T> viewType;

    public static /* synthetic */ void getOnViewDestroys$annotations() {
    }

    public static /* synthetic */ void getOnViewDidUpdateProps$annotations() {
    }

    public static /* synthetic */ void getProps$annotations() {
    }

    public static /* synthetic */ void getViewGroupDefinition$annotations() {
    }

    public ViewDefinitionBuilder(KClass<T> viewType) {
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        this.viewType = viewType;
        this.props = new LinkedHashMap();
    }

    public final Map<String, AnyViewProp> getProps() {
        return this.props;
    }

    public final void setProps(Map<String, AnyViewProp> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.props = map;
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final void setOnViewDestroys(Function1<? super View, Unit> function1) {
        this.onViewDestroys = function1;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    public final void setOnViewDidUpdateProps(Function1<? super View, Unit> function1) {
        this.onViewDidUpdateProps = function1;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final void setViewGroupDefinition(ViewGroupDefinition viewGroupDefinition) {
        this.viewGroupDefinition = viewGroupDefinition;
    }

    public final ViewManagerDefinition build() {
        return new ViewManagerDefinition(createViewFactory(), JvmClassMapping.getJavaClass((KClass) this.viewType), this.props, this.onViewDestroys, this.callbacksDefinition, this.viewGroupDefinition, this.onViewDidUpdateProps);
    }

    public final /* synthetic */ <ViewType extends View> void OnViewDestroys(final Function1<? super ViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDestroys$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                Function1<ViewType, Unit> function1 = body;
                Intrinsics.reifiedOperationMarker(1, "ViewType");
                function1.invoke(it);
            }
        });
    }

    public final /* synthetic */ <ViewType extends View> void OnViewDidUpdateProps(final Function1<? super ViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setOnViewDidUpdateProps(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$OnViewDidUpdateProps$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                Function1<ViewType, Unit> function1 = body;
                Intrinsics.reifiedOperationMarker(1, "ViewType");
                function1.invoke(it);
            }
        });
    }

    public final /* synthetic */ <ViewType extends View, PropType> void Prop(String name, Function2<? super ViewType, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Map<String, AnyViewProp> props = getProps();
        Intrinsics.reifiedOperationMarker(6, "PropType");
        props.put(name, new ConcreteViewProp(name, AnyTypeKt.toAnyType(null), body));
    }

    public final void Events(String... callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(callbacks);
    }

    public final void EventsWithArray(String[] callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(callbacks);
    }

    public final void GroupView(Function1<? super ViewGroupDefinitionBuilder, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (!(getViewGroupDefinition() == null)) {
            throw new IllegalArgumentException("The viewManager definition may have exported only one groupView definition.".toString());
        }
        ViewGroupDefinitionBuilder viewGroupDefinitionBuilder = new ViewGroupDefinitionBuilder();
        body.invoke(viewGroupDefinitionBuilder);
        setViewGroupDefinition(viewGroupDefinitionBuilder.build());
    }

    private final Function2<Context, AppContext, View> createViewFactory() {
        return (Function2) new Function2<Context, AppContext, T>(this) { // from class: expo.modules.kotlin.views.ViewDefinitionBuilder$createViewFactory$1
            final /* synthetic */ ViewDefinitionBuilder<T> this$0;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.this$0 = this;
            }

            /* JADX WARN: Incorrect return type in method signature: (Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)TT; */
            @Override // kotlin.jvm.functions.Function2
            public final View invoke(Context context, AppContext appContext) {
                KClass kClass;
                KClass kClass2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(appContext, "appContext");
                kClass = ((ViewDefinitionBuilder) this.this$0).viewType;
                KFunction primaryConstructor = KClasses.getPrimaryConstructor(kClass);
                ViewDefinitionBuilder<T> viewDefinitionBuilder = this.this$0;
                if (primaryConstructor == null) {
                    kClass2 = ((ViewDefinitionBuilder) viewDefinitionBuilder).viewType;
                    throw new IllegalArgumentException((kClass2 + " doesn't have a primary constructor").toString());
                }
                List<KParameter> parameters = primaryConstructor.getParameters();
                if (parameters.isEmpty()) {
                    throw new IllegalStateException("Android view has to have a constructor with at least one argument.");
                }
                if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Context.class), ((KParameter) CollectionsKt.first((List<? extends Object>) parameters)).getType().getClassifier())) {
                    throw new IllegalStateException("The type of the first constructor argument has to be `android.content.Context`.");
                }
                if (parameters.size() == 1) {
                    return (View) primaryConstructor.call(context);
                }
                if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(AppContext.class), parameters.get(1).getType().getClassifier())) {
                    throw new IllegalStateException("The type of the second constructor argument has to be `expo.modules.kotlin.AppContext`.");
                }
                if (parameters.size() != 2) {
                    throw new IllegalStateException("Android view has more constructor arguments than expected.");
                }
                return (View) primaryConstructor.call(context, appContext);
            }
        };
    }
}
