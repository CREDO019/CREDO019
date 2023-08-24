package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.modules.DefinitionMarker;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewManagerDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010*\u001a\u00020\b2\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100,\"\u00020\u0010¢\u0006\u0002\u0010-J%\u0010.\u001a\u00020\b2\u0017\u0010/\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b1H\u0086\bø\u0001\u0000J=\u00102\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u00072#\b\b\u0010/\u001a\u001d\u0012\u0013\u0012\u0011H3¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\b0\u0006H\u0086\bø\u0001\u0000Jb\u00107\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u0007\"\u0006\b\u0001\u00108\u0018\u00012\u0006\u00105\u001a\u00020\u001028\b\b\u0010/\u001a2\u0012\u0013\u0012\u0011H3¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0013\u0012\u0011H8¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b09H\u0086\bø\u0001\u0000J¨\u0001\u0010;\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u0007\"\u0006\b\u0001\u00108\u0018\u0001\"\u0006\b\u0002\u0010<\u0018\u00012*\u0010=\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H<0>0,\"\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H<0>2M\b\b\u0010/\u001aG\u0012\u0013\u0012\u0011H3¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0013\u0012\u0011H<¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011H8¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0?H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010AJ\u0088\u0001\u0010;\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u0007\"\u0006\b\u0001\u00108\u0018\u00012\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100,\"\u00020\u00102M\b\b\u0010/\u001aG\u0012\u0013\u0012\u0011H3¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H8¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0?H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010CJ\u008a\u0001\u0010;\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u0007\"\u0006\b\u0001\u00108\u0018\u00012\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100,\"\u00020\u00102M\b\b\u0010/\u001aG\u0012\u0013\u0012\u0011H3¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0013\u0012\u00110D¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011H8¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020\b0?H\u0087\bø\u0001\u0000¢\u0006\u0004\bE\u0010CJ.\u0010F\u001a\u00020\b\"\n\b\u0000\u00103\u0018\u0001*\u00020\u00072\u0014\b\b\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H30\u0006H\u0086\bø\u0001\u0000J\u0006\u0010G\u001a\u00020HR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR0\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R2\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\rR&\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u0002\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R.\u0010#\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010$8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u0002\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006I"}, m183d2 = {"Lexpo/modules/kotlin/views/ViewManagerDefinitionBuilder;", "", "()V", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "onViewDestroys", "Lkotlin/Function1;", "Landroid/view/View;", "", "getOnViewDestroys$annotations", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "setOnViewDestroys", "(Lkotlin/jvm/functions/Function1;)V", "props", "", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps$annotations", "getProps", "()Ljava/util/Map;", "setProps", "(Ljava/util/Map;)V", "viewFactory", "Landroid/content/Context;", "getViewFactory$annotations", "getViewFactory", "setViewFactory", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$annotations", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "setViewGroupDefinition", "(Lexpo/modules/kotlin/views/ViewGroupDefinition;)V", "viewType", "Ljava/lang/Class;", "getViewType$annotations", "getViewType", "()Ljava/lang/Class;", "setViewType", "(Ljava/lang/Class;)V", "Events", "callbacks", "", "([Ljava/lang/String;)V", "GroupView", TtmlNode.TAG_BODY, "Lexpo/modules/kotlin/views/ViewGroupDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "OnViewDestroys", "ViewType", "Lkotlin/ParameterName;", "name", "view", "Prop", "PropType", "Lkotlin/Function2;", "prop", "PropGroup", "CustomValue", "propInfo", "Lkotlin/Pair;", "Lkotlin/Function3;", "value", "([Lkotlin/Pair;Lkotlin/jvm/functions/Function3;)V", "names", "([Ljava/lang/String;Lkotlin/jvm/functions/Function3;)V", "", "PropGroupIndexed", "View", "build", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
@DefinitionMarker
/* loaded from: classes4.dex */
public final class ViewManagerDefinitionBuilder {
    private CallbacksDefinition callbacksDefinition;
    private Function1<? super View, Unit> onViewDestroys;
    private Map<String, AnyViewProp> props = new LinkedHashMap();
    private Function1<? super Context, ? extends View> viewFactory;
    private ViewGroupDefinition viewGroupDefinition;
    private Class<? extends View> viewType;

    public static /* synthetic */ void getOnViewDestroys$annotations() {
    }

    public static /* synthetic */ void getProps$annotations() {
    }

    public static /* synthetic */ void getViewFactory$annotations() {
    }

    public static /* synthetic */ void getViewGroupDefinition$annotations() {
    }

    public static /* synthetic */ void getViewType$annotations() {
    }

    public final Function1<Context, View> getViewFactory() {
        return this.viewFactory;
    }

    public final void setViewFactory(Function1<? super Context, ? extends View> function1) {
        this.viewFactory = function1;
    }

    public final Class<? extends View> getViewType() {
        return this.viewType;
    }

    public final void setViewType(Class<? extends View> cls) {
        this.viewType = cls;
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

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final void setViewGroupDefinition(ViewGroupDefinition viewGroupDefinition) {
        this.viewGroupDefinition = viewGroupDefinition;
    }

    public final ViewManagerDefinition build() {
        Function2<Context, AppContext, View> function2 = new Function2<Context, AppContext, View>() { // from class: expo.modules.kotlin.views.ViewManagerDefinitionBuilder$build$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final View invoke(Context context, AppContext noName_1) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                Function1<Context, View> viewFactory = ViewManagerDefinitionBuilder.this.getViewFactory();
                if (viewFactory != null) {
                    return viewFactory.invoke(context);
                }
                throw new IllegalArgumentException("Required value was null.".toString());
            }
        };
        Class<? extends View> cls = this.viewType;
        if (cls != null) {
            return new ViewManagerDefinition(function2, cls, this.props, this.onViewDestroys, this.callbacksDefinition, this.viewGroupDefinition, null, 64, null);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final /* synthetic */ <ViewType extends View> void View(Function1<? super Context, ? extends ViewType> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "ViewType");
        setViewType(View.class);
        setViewFactory(body);
    }

    public final /* synthetic */ <ViewType extends View> void OnViewDestroys(final Function1<? super ViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setOnViewDestroys(new Function1<View, Unit>() { // from class: expo.modules.kotlin.views.ViewManagerDefinitionBuilder$OnViewDestroys$1
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

    public final /* synthetic */ <ViewType extends View, PropType> void PropGroup(String[] names, final Function3<? super ViewType, ? super String, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(names, "names");
        Intrinsics.checkNotNullParameter(body, "body");
        int length = names.length;
        int r1 = 0;
        while (r1 < length) {
            final String str = names[r1];
            r1++;
            Map<String, AnyViewProp> props = getProps();
            Intrinsics.reifiedOperationMarker(6, "PropType");
            AnyType anyType = AnyTypeKt.toAnyType(null);
            Intrinsics.needClassReification();
            props.put(str, new ConcreteViewProp(str, anyType, new Function2<ViewType, PropType, Unit>() { // from class: expo.modules.kotlin.views.ViewManagerDefinitionBuilder$PropGroup$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
                    invoke((View) obj, obj2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect types in method signature: (TViewType;TPropType;)V */
                public final void invoke(View view, Object obj) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    body.invoke(view, str, obj);
                }
            }));
        }
    }

    public final /* synthetic */ <ViewType extends View, PropType, CustomValue> void PropGroup(Tuples<String, ? extends CustomValue>[] propInfo, final Function3<? super ViewType, ? super CustomValue, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(propInfo, "propInfo");
        Intrinsics.checkNotNullParameter(body, "body");
        int length = propInfo.length;
        int r1 = 0;
        while (r1 < length) {
            Tuples<String, ? extends CustomValue> tuples = propInfo[r1];
            r1++;
            String component1 = tuples.component1();
            final CustomValue component2 = tuples.component2();
            Map<String, AnyViewProp> props = getProps();
            Intrinsics.reifiedOperationMarker(6, "PropType");
            AnyType anyType = AnyTypeKt.toAnyType(null);
            Intrinsics.needClassReification();
            props.put(component1, new ConcreteViewProp(component1, anyType, new Function2<ViewType, PropType, Unit>() { // from class: expo.modules.kotlin.views.ViewManagerDefinitionBuilder$PropGroup$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
                    invoke((View) obj, obj2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect types in method signature: (TViewType;TPropType;)V */
                public final void invoke(View view, Object obj) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    body.invoke(view, component2, obj);
                }
            }));
        }
    }

    public final void Events(String... callbacks) {
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

    public final /* synthetic */ <ViewType extends View, PropType> void PropGroupIndexed(String[] names, final Function3<? super ViewType, ? super Integer, ? super PropType, Unit> body) {
        Intrinsics.checkNotNullParameter(names, "names");
        Intrinsics.checkNotNullParameter(body, "body");
        int length = names.length;
        int r1 = 0;
        final int r2 = 0;
        while (r1 < length) {
            String str = names[r1];
            r1++;
            Map<String, AnyViewProp> props = getProps();
            Intrinsics.reifiedOperationMarker(6, "PropType");
            AnyType anyType = AnyTypeKt.toAnyType(null);
            Intrinsics.needClassReification();
            props.put(str, new ConcreteViewProp(str, anyType, new Function2<ViewType, PropType, Unit>() { // from class: expo.modules.kotlin.views.ViewManagerDefinitionBuilder$PropGroup$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
                    invoke((View) obj, obj2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect types in method signature: (TViewType;TPropType;)V */
                public final void invoke(View view, Object obj) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    body.invoke(view, Integer.valueOf(r2), obj);
                }
            }));
            r2++;
        }
    }
}
