package expo.modules.kotlin.modules;

import expo.modules.kotlin.ConcatIterator;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.objects.ObjectDefinitionData;
import expo.modules.kotlin.objects.PropertyComponent;
import expo.modules.kotlin.views.ViewManagerDefinition;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleDefinitionData.kt */
@Metadata(m184d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012+\b\u0002\u0010\f\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r¢\u0006\u0002\b\u0011ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00140\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R%\u0010\u0017\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001d\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020*0\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0016R9\u0010\f\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r¢\u0006\u0002\b\u0011ø\u0001\u0000¢\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u001d\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002000\t¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b2\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m183d2 = {"Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "", "name", "", "objectDefinition", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "viewManagerDefinition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "eventListeners", "", "Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/EventListener;", "registerContracts", "Lkotlin/Function2;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lexpo/modules/kotlin/objects/ObjectDefinitionData;Lexpo/modules/kotlin/views/ViewManagerDefinition;Ljava/util/Map;Lkotlin/jvm/functions/Function2;)V", "asyncFunctions", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "getAsyncFunctions", "()Ljava/util/Map;", "constantsProvider", "Lkotlin/Function0;", "getConstantsProvider", "()Lkotlin/jvm/functions/Function0;", "getEventListeners", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "getEventsDefinition", "()Lexpo/modules/kotlin/events/EventsDefinition;", "functions", "Lexpo/modules/kotlin/ConcatIterator;", "Lexpo/modules/kotlin/functions/AnyFunction;", "getFunctions", "()Lexpo/modules/kotlin/ConcatIterator;", "getName", "()Ljava/lang/String;", "getObjectDefinition", "()Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "properties", "Lexpo/modules/kotlin/objects/PropertyComponent;", "getProperties", "getRegisterContracts", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getSyncFunctions", "getViewManagerDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ModuleDefinitionData {
    private final Map<String, BaseAsyncFunctionComponent> asyncFunctions;
    private final Functions<Map<String, Object>> constantsProvider;
    private final Map<EventName, EventListener> eventListeners;
    private final EventsDefinition eventsDefinition;
    private final String name;
    private final ObjectDefinitionData objectDefinition;
    private final Map<String, PropertyComponent> properties;
    private final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> registerContracts;
    private final Map<String, SyncFunctionComponent> syncFunctions;
    private final ViewManagerDefinition viewManagerDefinition;

    /* JADX WARN: Multi-variable type inference failed */
    public ModuleDefinitionData(String name, ObjectDefinitionData objectDefinition, ViewManagerDefinition viewManagerDefinition, Map<EventName, ? extends EventListener> eventListeners, Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(objectDefinition, "objectDefinition");
        Intrinsics.checkNotNullParameter(eventListeners, "eventListeners");
        this.name = name;
        this.objectDefinition = objectDefinition;
        this.viewManagerDefinition = viewManagerDefinition;
        this.eventListeners = eventListeners;
        this.registerContracts = function2;
        this.constantsProvider = objectDefinition.getConstantsProvider();
        this.syncFunctions = objectDefinition.getSyncFunctions();
        this.asyncFunctions = objectDefinition.getAsyncFunctions();
        this.eventsDefinition = objectDefinition.getEventsDefinition();
        this.properties = objectDefinition.getProperties();
    }

    public final String getName() {
        return this.name;
    }

    public final ObjectDefinitionData getObjectDefinition() {
        return this.objectDefinition;
    }

    public final ViewManagerDefinition getViewManagerDefinition() {
        return this.viewManagerDefinition;
    }

    public /* synthetic */ ModuleDefinitionData(String str, ObjectDefinitionData objectDefinitionData, ViewManagerDefinition viewManagerDefinition, Map map, Function2 function2, int r13, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, objectDefinitionData, (r13 & 4) != 0 ? null : viewManagerDefinition, (r13 & 8) != 0 ? MapsKt.emptyMap() : map, (r13 & 16) != 0 ? null : function2);
    }

    public final Map<EventName, EventListener> getEventListeners() {
        return this.eventListeners;
    }

    public final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> getRegisterContracts() {
        return this.registerContracts;
    }

    public final Functions<Map<String, Object>> getConstantsProvider() {
        return this.constantsProvider;
    }

    public final Map<String, SyncFunctionComponent> getSyncFunctions() {
        return this.syncFunctions;
    }

    public final Map<String, BaseAsyncFunctionComponent> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final EventsDefinition getEventsDefinition() {
        return this.eventsDefinition;
    }

    public final Map<String, PropertyComponent> getProperties() {
        return this.properties;
    }

    public final ConcatIterator<AnyFunction> getFunctions() {
        return new ConcatIterator<>(this.syncFunctions.values().iterator(), this.asyncFunctions.values().iterator());
    }
}
