package com.onesignal.outcomes.data;

import androidx.core.app.NotificationCompat;
import com.onesignal.OSLogger;
import com.onesignal.OSOutcomeEvent;
import com.onesignal.OneSignalApiResponseHandler;
import com.onesignal.influence.domain.OSInfluenceType;
import com.onesignal.outcomes.OSOutcomeConstants;
import com.onesignal.outcomes.domain.OSOutcomeEventParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OSOutcomeEventsV1Repository.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J(\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J(\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J(\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0018"}, m183d2 = {"Lcom/onesignal/outcomes/data/OSOutcomeEventsV1Repository;", "Lcom/onesignal/outcomes/data/OSOutcomeEventsRepository;", "logger", "Lcom/onesignal/OSLogger;", "outcomeEventsCache", "Lcom/onesignal/outcomes/data/OSOutcomeEventsCache;", "outcomeEventsService", "Lcom/onesignal/outcomes/data/OutcomeEventsService;", "(Lcom/onesignal/OSLogger;Lcom/onesignal/outcomes/data/OSOutcomeEventsCache;Lcom/onesignal/outcomes/data/OutcomeEventsService;)V", "requestMeasureDirectOutcomeEvent", "", "appId", "", "deviceType", "", NotificationCompat.CATEGORY_EVENT, "Lcom/onesignal/OSOutcomeEvent;", "responseHandler", "Lcom/onesignal/OneSignalApiResponseHandler;", "requestMeasureIndirectOutcomeEvent", "requestMeasureOutcomeEvent", "eventParams", "Lcom/onesignal/outcomes/domain/OSOutcomeEventParams;", "requestMeasureUnattributedOutcomeEvent", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class OSOutcomeEventsV1Repository extends OSOutcomeEventsRepository {

    @Metadata(m185bv = {1, 0, 3}, m182k = 3, m181mv = {1, 4, 2})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[OSInfluenceType.values().length];
            $EnumSwitchMapping$0 = r0;
            r0[OSInfluenceType.DIRECT.ordinal()] = 1;
            r0[OSInfluenceType.INDIRECT.ordinal()] = 2;
            r0[OSInfluenceType.UNATTRIBUTED.ordinal()] = 3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OSOutcomeEventsV1Repository(OSLogger logger, OSOutcomeEventsCache outcomeEventsCache, OutcomeEventsService outcomeEventsService) {
        super(logger, outcomeEventsCache, outcomeEventsService);
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(outcomeEventsCache, "outcomeEventsCache");
        Intrinsics.checkNotNullParameter(outcomeEventsService, "outcomeEventsService");
    }

    @Override // com.onesignal.outcomes.data.OSOutcomeEventsRepository, com.onesignal.outcomes.domain.OSOutcomeEventsRepository
    public void requestMeasureOutcomeEvent(String appId, int r4, OSOutcomeEventParams eventParams, OneSignalApiResponseHandler responseHandler) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(eventParams, "eventParams");
        Intrinsics.checkNotNullParameter(responseHandler, "responseHandler");
        OSOutcomeEvent event = OSOutcomeEvent.fromOutcomeEventParamsV2toOutcomeEventV1(eventParams);
        Intrinsics.checkNotNullExpressionValue(event, "event");
        OSInfluenceType session = event.getSession();
        if (session == null) {
            return;
        }
        int r0 = WhenMappings.$EnumSwitchMapping$0[session.ordinal()];
        if (r0 == 1) {
            requestMeasureDirectOutcomeEvent(appId, r4, event, responseHandler);
        } else if (r0 == 2) {
            requestMeasureIndirectOutcomeEvent(appId, r4, event, responseHandler);
        } else if (r0 != 3) {
        } else {
            requestMeasureUnattributedOutcomeEvent(appId, r4, event, responseHandler);
        }
    }

    private final void requestMeasureDirectOutcomeEvent(String str, int r3, OSOutcomeEvent oSOutcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jsonObject = oSOutcomeEvent.toJSONObjectForMeasure().put(OSOutcomeConstants.APP_ID, str).put(OSOutcomeConstants.DEVICE_TYPE, r3).put("direct", true);
            OutcomeEventsService outcomeEventsService = getOutcomeEventsService();
            Intrinsics.checkNotNullExpressionValue(jsonObject, "jsonObject");
            outcomeEventsService.sendOutcomeEvent(jsonObject, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            getLogger().error("Generating direct outcome:JSON Failed.", e);
        }
    }

    private final void requestMeasureIndirectOutcomeEvent(String str, int r3, OSOutcomeEvent oSOutcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jsonObject = oSOutcomeEvent.toJSONObjectForMeasure().put(OSOutcomeConstants.APP_ID, str).put(OSOutcomeConstants.DEVICE_TYPE, r3).put("direct", false);
            OutcomeEventsService outcomeEventsService = getOutcomeEventsService();
            Intrinsics.checkNotNullExpressionValue(jsonObject, "jsonObject");
            outcomeEventsService.sendOutcomeEvent(jsonObject, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            getLogger().error("Generating indirect outcome:JSON Failed.", e);
        }
    }

    private final void requestMeasureUnattributedOutcomeEvent(String str, int r3, OSOutcomeEvent oSOutcomeEvent, OneSignalApiResponseHandler oneSignalApiResponseHandler) {
        try {
            JSONObject jsonObject = oSOutcomeEvent.toJSONObjectForMeasure().put(OSOutcomeConstants.APP_ID, str).put(OSOutcomeConstants.DEVICE_TYPE, r3);
            OutcomeEventsService outcomeEventsService = getOutcomeEventsService();
            Intrinsics.checkNotNullExpressionValue(jsonObject, "jsonObject");
            outcomeEventsService.sendOutcomeEvent(jsonObject, oneSignalApiResponseHandler);
        } catch (JSONException e) {
            getLogger().error("Generating unattributed outcome:JSON Failed.", e);
        }
    }
}
