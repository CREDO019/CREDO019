package expo.modules.kotlin.modules;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import expo.modules.kotlin.views.ViewManagerDefinition;
import expo.modules.kotlin.views.ViewManagerDefinitionBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: ModuleDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011J\u001c\u0010'\u001a\u00020\u00162\u000e\b\u0004\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160)H\u0086\bø\u0001\u0001J\u001c\u0010*\u001a\u00020\u00162\u000e\b\u0004\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160)H\u0086\bø\u0001\u0001J\u001c\u0010+\u001a\u00020\u00162\u000e\b\u0004\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160)H\u0086\bø\u0001\u0001J(\u0010,\u001a\u00020\u00162\u001a\b\u0004\u0010(\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00160\u0013H\u0086\bø\u0001\u0001J\u001c\u0010/\u001a\u00020\u00162\u000e\b\u0004\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160)H\u0086\bø\u0001\u0001J\u001c\u00100\u001a\u00020\u00162\u000e\b\u0004\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160)H\u0086\bø\u0001\u0001J\"\u00101\u001a\u00020\u00162\u0014\b\u0004\u0010(\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u001602H\u0086\bø\u0001\u0001J7\u00104\u001a\u00020\u00162'\u0010(\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0013¢\u0006\u0002\b\u0018ø\u0001\u0000¢\u0006\u0002\u0010\u001dJC\u00105\u001a\u00020\u0016\"\b\b\u0000\u00106*\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u0002H6092\u001d\u0010(\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H60:\u0012\u0004\u0012\u00020\u001602¢\u0006\u0002\b\u0018H\u0086\bø\u0001\u0001J%\u0010;\u001a\u00020\u00162\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u001602¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0001J\u0006\u0010=\u001a\u00020>R(\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000RL\u0010\u0012\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010\u0013¢\u0006\u0002\b\u00188\u0000@\u0000X\u0081\u000eø\u0001\u0000¢\u0006\u0016\n\u0002\u0010\u001e\u0012\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010\u001f\u001a\u0004\u0018\u00010 8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\n\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006?"}, m183d2 = {"Lexpo/modules/kotlin/modules/ModuleDefinitionBuilder;", "Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "module", "Lexpo/modules/kotlin/modules/Module;", "(Lexpo/modules/kotlin/modules/Module;)V", "eventListeners", "", "Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/EventListener;", "getEventListeners$annotations", "()V", "getEventListeners", "()Ljava/util/Map;", "getModule$annotations", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "name", "", "registerContracts", "Lkotlin/Function2;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "getRegisterContracts$annotations", "getRegisterContracts", "()Lkotlin/jvm/functions/Function2;", "setRegisterContracts", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "viewManagerDefinition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getViewManagerDefinition$annotations", "getViewManagerDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "setViewManagerDefinition", "(Lexpo/modules/kotlin/views/ViewManagerDefinition;)V", "Name", "OnActivityDestroys", TtmlNode.TAG_BODY, "Lkotlin/Function0;", "OnActivityEntersBackground", "OnActivityEntersForeground", "OnActivityResult", "Landroid/app/Activity;", "Lexpo/modules/kotlin/events/OnActivityResultPayload;", "OnCreate", "OnDestroy", "OnNewIntent", "Lkotlin/Function1;", "Landroid/content/Intent;", "RegisterActivityContracts", "View", "T", "Landroid/view/View;", "viewType", "Lkotlin/reflect/KClass;", "Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "ViewManager", "Lexpo/modules/kotlin/views/ViewManagerDefinitionBuilder;", "buildModule", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
@DefinitionMarker
/* loaded from: classes4.dex */
public final class ModuleDefinitionBuilder extends ObjectDefinitionBuilder {
    private final Map<EventName, EventListener> eventListeners;
    private final Module module;
    private String name;
    private Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> registerContracts;
    private ViewManagerDefinition viewManagerDefinition;

    public ModuleDefinitionBuilder() {
        this(null, 1, null);
    }

    public static /* synthetic */ void getEventListeners$annotations() {
    }

    public static /* synthetic */ void getModule$annotations() {
    }

    public static /* synthetic */ void getRegisterContracts$annotations() {
    }

    public static /* synthetic */ void getViewManagerDefinition$annotations() {
    }

    public ModuleDefinitionBuilder(Module module) {
        this.module = module;
        this.eventListeners = new LinkedHashMap();
    }

    public /* synthetic */ ModuleDefinitionBuilder(Module module, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? null : module);
    }

    public final Module getModule() {
        return this.module;
    }

    public final ViewManagerDefinition getViewManagerDefinition() {
        return this.viewManagerDefinition;
    }

    public final void setViewManagerDefinition(ViewManagerDefinition viewManagerDefinition) {
        this.viewManagerDefinition = viewManagerDefinition;
    }

    public final Map<EventName, EventListener> getEventListeners() {
        return this.eventListeners;
    }

    public final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> getRegisterContracts() {
        return this.registerContracts;
    }

    public final void setRegisterContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.registerContracts = function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final expo.modules.kotlin.modules.ModuleDefinitionData buildModule() {
        /*
            r7 = this;
            java.lang.String r0 = r7.name
            r1 = 0
            if (r0 != 0) goto L16
            expo.modules.kotlin.modules.Module r0 = r7.module
            if (r0 != 0) goto Lb
        L9:
            r2 = r1
            goto L17
        Lb:
            java.lang.Class r0 = r0.getClass()
            if (r0 != 0) goto L12
            goto L9
        L12:
            java.lang.String r0 = r0.getSimpleName()
        L16:
            r2 = r0
        L17:
            expo.modules.kotlin.modules.ModuleDefinitionData r0 = new expo.modules.kotlin.modules.ModuleDefinitionData
            if (r2 == 0) goto L2a
            expo.modules.kotlin.objects.ObjectDefinitionData r3 = r7.buildObject()
            expo.modules.kotlin.views.ViewManagerDefinition r4 = r7.viewManagerDefinition
            java.util.Map<expo.modules.kotlin.events.EventName, expo.modules.kotlin.events.EventListener> r5 = r7.eventListeners
            kotlin.jvm.functions.Function2<? super expo.modules.kotlin.activityresult.AppContextActivityResultCaller, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6 = r7.registerContracts
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            return r0
        L2a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Required value was null."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.modules.ModuleDefinitionBuilder.buildModule():expo.modules.kotlin.modules.ModuleDefinitionData");
    }

    public final void Name(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @Deprecated(message = "Use a `View` component instead.")
    public final void ViewManager(Function1<? super ViewManagerDefinitionBuilder, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (!(getViewManagerDefinition() == null)) {
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        }
        ViewManagerDefinitionBuilder viewManagerDefinitionBuilder = new ViewManagerDefinitionBuilder();
        body.invoke(viewManagerDefinitionBuilder);
        setViewManagerDefinition(viewManagerDefinitionBuilder.build());
    }

    public final <T extends View> void View(KClass<T> viewType, Function1<? super ViewDefinitionBuilder<T>, Unit> body) {
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        Intrinsics.checkNotNullParameter(body, "body");
        if (!(getViewManagerDefinition() == null)) {
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        }
        ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(viewType);
        body.invoke(viewDefinitionBuilder);
        setViewManagerDefinition(viewDefinitionBuilder.build());
    }

    public final void OnCreate(final Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Functions<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnCreate$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void RegisterActivityContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.registerContracts = body;
    }

    public final void OnDestroy(final Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Functions<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnDestroy$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityEntersForeground(final Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_ENTERS_FOREGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_FOREGROUND, new Functions<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityEntersForeground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityEntersBackground(final Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_ENTERS_BACKGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_BACKGROUND, new Functions<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityEntersBackground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnActivityDestroys(final Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ACTIVITY_DESTROYS, new BasicEventListener(EventName.ACTIVITY_DESTROYS, new Functions<Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityDestroys$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                body.invoke();
            }
        }));
    }

    public final void OnNewIntent(final Function1<? super Intent, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ON_NEW_INTENT, new EventListenerWithPayload(EventName.ON_NEW_INTENT, new Function1<Intent, Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnNewIntent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Intent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                body.invoke(it);
            }
        }));
    }

    public final void OnActivityResult(final Function2<? super Activity, ? super OnActivityResultPayload, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new Function2<Activity, OnActivityResultPayload, Unit>() { // from class: expo.modules.kotlin.modules.ModuleDefinitionBuilder$OnActivityResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
                invoke2(activity, onActivityResultPayload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Activity sender, OnActivityResultPayload payload) {
                Intrinsics.checkNotNullParameter(sender, "sender");
                Intrinsics.checkNotNullParameter(payload, "payload");
                body.invoke(sender, payload);
            }
        }));
    }
}
