package expo.modules.kotlin.objects;

import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectDefinitionData.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001Bg\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\u0002\u0010\u000eR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R%\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "", "constantsProvider", "Lkotlin/Function0;", "", "", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "asyncFunctions", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "properties", "Lexpo/modules/kotlin/objects/PropertyComponent;", "(Lkotlin/jvm/functions/Function0;Ljava/util/Map;Ljava/util/Map;Lexpo/modules/kotlin/events/EventsDefinition;Ljava/util/Map;)V", "getAsyncFunctions", "()Ljava/util/Map;", "getConstantsProvider", "()Lkotlin/jvm/functions/Function0;", "getEventsDefinition", "()Lexpo/modules/kotlin/events/EventsDefinition;", "getProperties", "getSyncFunctions", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ObjectDefinitionData {
    private final Map<String, BaseAsyncFunctionComponent> asyncFunctions;
    private final Functions<Map<String, Object>> constantsProvider;
    private final EventsDefinition eventsDefinition;
    private final Map<String, PropertyComponent> properties;
    private final Map<String, SyncFunctionComponent> syncFunctions;

    /* JADX WARN: Multi-variable type inference failed */
    public ObjectDefinitionData(Functions<? extends Map<String, ? extends Object>> constantsProvider, Map<String, SyncFunctionComponent> syncFunctions, Map<String, ? extends BaseAsyncFunctionComponent> asyncFunctions, EventsDefinition eventsDefinition, Map<String, PropertyComponent> properties) {
        Intrinsics.checkNotNullParameter(constantsProvider, "constantsProvider");
        Intrinsics.checkNotNullParameter(syncFunctions, "syncFunctions");
        Intrinsics.checkNotNullParameter(asyncFunctions, "asyncFunctions");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.constantsProvider = constantsProvider;
        this.syncFunctions = syncFunctions;
        this.asyncFunctions = asyncFunctions;
        this.eventsDefinition = eventsDefinition;
        this.properties = properties;
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
}
