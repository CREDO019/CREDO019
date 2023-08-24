package expo.modules.kotlin.views;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.modules.DefinitionMarker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewGroupDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Js\u0010/\u001a\u00020\r\"\n\b\u0000\u00100\u0018\u0001*\u00020\u0005\"\n\b\u0001\u00101\u0018\u0001*\u00020\t2M\b\b\u00102\u001aG\u0012\u0013\u0012\u0011H0¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H1¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0004H\u0086\bø\u0001\u0000J=\u00103\u001a\u00020\r\"\n\b\u0000\u00100\u0018\u0001*\u00020\u00052#\b\b\u00102\u001a\u001d\u0012\u0013\u0012\u0011H0¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u000b0\u001dH\u0086\bø\u0001\u0000J`\u00105\u001a\u00020\r\"\n\b\u0000\u00100\u0018\u0001*\u00020\u0005\"\n\b\u0001\u00101\u0018\u0001*\u00020\t2:\b\b\u00102\u001a4\u0012\u0013\u0012\u0011H0¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u0001H10\u0015H\u0086\bø\u0001\u0000J^\u00106\u001a\u00020\r\"\n\b\u0000\u00100\u0018\u0001*\u00020\u0005\"\n\b\u0001\u00101\u0018\u0001*\u00020\t28\b\b\u00102\u001a2\u0012\u0013\u0012\u0011H0¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H1¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\r0\u0015H\u0086\bø\u0001\u0000JR\u00107\u001a\u00020\r\"\n\b\u0000\u00100\u0018\u0001*\u00020\u000528\b\b\u00102\u001a2\u0012\u0013\u0012\u0011H0¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0015H\u0086\bø\u0001\u0000J\u0006\u00108\u001a\u000209Rq\u0010\u0003\u001aO\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0004j\u0004\u0018\u0001`\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R^\u0010\u0014\u001a<\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0015j\u0004\u0018\u0001`\u00168\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0002\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bRG\u0010\u001c\u001a%\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\\\u0010$\u001a:\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\r\u0018\u00010\u0015j\u0004\u0018\u0001`&8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\u0002\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010\u001bR\\\u0010*\u001a:\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0015j\u0004\u0018\u0001`+8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b,\u0010\u0002\u001a\u0004\b-\u0010\u0019\"\u0004\b.\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006:"}, m183d2 = {"Lexpo/modules/kotlin/views/ViewGroupDefinitionBuilder;", "", "()V", "addViewAction", "Lkotlin/Function3;", "Landroid/view/ViewGroup;", "Lkotlin/ParameterName;", "name", "parent", "Landroid/view/View;", "child", "", "index", "", "Lexpo/modules/kotlin/views/AddViewAction;", "getAddViewAction$annotations", "getAddViewAction", "()Lkotlin/jvm/functions/Function3;", "setAddViewAction", "(Lkotlin/jvm/functions/Function3;)V", "getChildAtAction", "Lkotlin/Function2;", "Lexpo/modules/kotlin/views/GetChildAtAction;", "getGetChildAtAction$annotations", "getGetChildAtAction", "()Lkotlin/jvm/functions/Function2;", "setGetChildAtAction", "(Lkotlin/jvm/functions/Function2;)V", "getChildCountAction", "Lkotlin/Function1;", "Lexpo/modules/kotlin/views/GetChildCountAction;", "getGetChildCountAction$annotations", "getGetChildCountAction", "()Lkotlin/jvm/functions/Function1;", "setGetChildCountAction", "(Lkotlin/jvm/functions/Function1;)V", "removeViewAction", "childToRemove", "Lexpo/modules/kotlin/views/RemoveViewAction;", "getRemoveViewAction$annotations", "getRemoveViewAction", "setRemoveViewAction", "removeViewAtAction", "Lexpo/modules/kotlin/views/RemoveViewAtAction;", "getRemoveViewAtAction$annotations", "getRemoveViewAtAction", "setRemoveViewAtAction", "AddChildView", "ParentViewType", "ChildViewType", TtmlNode.TAG_BODY, "GetChildCount", "view", "GetChildViewAt", "RemoveChildView", "RemoveChildViewAt", "build", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
@DefinitionMarker
/* loaded from: classes4.dex */
public final class ViewGroupDefinitionBuilder {
    private Function3<? super ViewGroup, ? super View, ? super Integer, Unit> addViewAction;
    private Function2<? super ViewGroup, ? super Integer, ? extends View> getChildAtAction;
    private Function1<? super ViewGroup, Integer> getChildCountAction;
    private Function2<? super ViewGroup, ? super View, Unit> removeViewAction;
    private Function2<? super ViewGroup, ? super Integer, Unit> removeViewAtAction;

    public static /* synthetic */ void getAddViewAction$annotations() {
    }

    public static /* synthetic */ void getGetChildAtAction$annotations() {
    }

    public static /* synthetic */ void getGetChildCountAction$annotations() {
    }

    public static /* synthetic */ void getRemoveViewAction$annotations() {
    }

    public static /* synthetic */ void getRemoveViewAtAction$annotations() {
    }

    public final Function3<ViewGroup, View, Integer, Unit> getAddViewAction() {
        return this.addViewAction;
    }

    public final void setAddViewAction(Function3<? super ViewGroup, ? super View, ? super Integer, Unit> function3) {
        this.addViewAction = function3;
    }

    public final Function2<ViewGroup, Integer, View> getGetChildAtAction() {
        return this.getChildAtAction;
    }

    public final void setGetChildAtAction(Function2<? super ViewGroup, ? super Integer, ? extends View> function2) {
        this.getChildAtAction = function2;
    }

    public final Function1<ViewGroup, Integer> getGetChildCountAction() {
        return this.getChildCountAction;
    }

    public final void setGetChildCountAction(Function1<? super ViewGroup, Integer> function1) {
        this.getChildCountAction = function1;
    }

    public final Function2<ViewGroup, View, Unit> getRemoveViewAction() {
        return this.removeViewAction;
    }

    public final void setRemoveViewAction(Function2<? super ViewGroup, ? super View, Unit> function2) {
        this.removeViewAction = function2;
    }

    public final Function2<ViewGroup, Integer, Unit> getRemoveViewAtAction() {
        return this.removeViewAtAction;
    }

    public final void setRemoveViewAtAction(Function2<? super ViewGroup, ? super Integer, Unit> function2) {
        this.removeViewAtAction = function2;
    }

    public final ViewGroupDefinition build() {
        return new ViewGroupDefinition(this.addViewAction, this.getChildAtAction, this.getChildCountAction, this.removeViewAction, this.removeViewAtAction);
    }

    public final /* synthetic */ <ParentViewType extends ViewGroup, ChildViewType extends View> void AddChildView(final Function3<? super ParentViewType, ? super ChildViewType, ? super Integer, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setAddViewAction(new Function3<ViewGroup, View, Integer, Unit>() { // from class: expo.modules.kotlin.views.ViewGroupDefinitionBuilder$AddChildView$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup viewGroup, View view, Integer num) {
                invoke(viewGroup, view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ViewGroup parent, View child, int r6) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(child, "child");
                Function3<ParentViewType, ChildViewType, Integer, Unit> function3 = body;
                Intrinsics.reifiedOperationMarker(1, "ParentViewType");
                Intrinsics.reifiedOperationMarker(1, "ChildViewType");
                function3.invoke(parent, child, Integer.valueOf(r6));
            }
        });
    }

    public final /* synthetic */ <ParentViewType extends ViewGroup> void GetChildCount(final Function1<? super ParentViewType, Integer> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setGetChildCountAction(new Function1<ViewGroup, Integer>() { // from class: expo.modules.kotlin.views.ViewGroupDefinitionBuilder$GetChildCount$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(ViewGroup view) {
                Intrinsics.checkNotNullParameter(view, "view");
                Function1<ParentViewType, Integer> function1 = body;
                Intrinsics.reifiedOperationMarker(1, "ParentViewType");
                return function1.invoke(view);
            }
        });
    }

    public final /* synthetic */ <ParentViewType extends ViewGroup, ChildViewType extends View> void GetChildViewAt(final Function2<? super ParentViewType, ? super Integer, ? extends ChildViewType> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setGetChildAtAction(new Function2<ViewGroup, Integer, ChildViewType>() { // from class: expo.modules.kotlin.views.ViewGroupDefinitionBuilder$GetChildViewAt$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(ViewGroup viewGroup, Integer num) {
                return invoke(viewGroup, num.intValue());
            }

            /* JADX WARN: Incorrect return type in method signature: (Landroid/view/ViewGroup;I)TChildViewType; */
            public final View invoke(ViewGroup view, int r5) {
                Intrinsics.checkNotNullParameter(view, "view");
                Function2<ParentViewType, Integer, ChildViewType> function2 = body;
                Intrinsics.reifiedOperationMarker(1, "ParentViewType");
                return (View) function2.invoke(view, Integer.valueOf(r5));
            }
        });
    }

    public final /* synthetic */ <ParentViewType extends ViewGroup> void RemoveChildViewAt(final Function2<? super ParentViewType, ? super Integer, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setRemoveViewAtAction(new Function2<ViewGroup, Integer, Unit>() { // from class: expo.modules.kotlin.views.ViewGroupDefinitionBuilder$RemoveChildViewAt$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup viewGroup, Integer num) {
                invoke(viewGroup, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(ViewGroup view, int r5) {
                Intrinsics.checkNotNullParameter(view, "view");
                Function2<ParentViewType, Integer, Unit> function2 = body;
                Intrinsics.reifiedOperationMarker(1, "ParentViewType");
                function2.invoke(view, Integer.valueOf(r5));
            }
        });
    }

    public final /* synthetic */ <ParentViewType extends ViewGroup, ChildViewType extends View> void RemoveChildView(final Function2<? super ParentViewType, ? super ChildViewType, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.needClassReification();
        setRemoveViewAction(new Function2<ViewGroup, View, Unit>() { // from class: expo.modules.kotlin.views.ViewGroupDefinitionBuilder$RemoveChildView$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup viewGroup, View view) {
                invoke2(viewGroup, view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup view, View child) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(child, "child");
                Function2<ParentViewType, ChildViewType, Unit> function2 = body;
                Intrinsics.reifiedOperationMarker(1, "ParentViewType");
                Intrinsics.reifiedOperationMarker(1, "ChildViewType");
                function2.invoke(view, child);
            }
        });
    }
}
