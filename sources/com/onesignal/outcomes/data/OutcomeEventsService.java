package com.onesignal.outcomes.data;

import com.onesignal.OneSignalApiResponseHandler;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: OutcomeEventsService.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m183d2 = {"Lcom/onesignal/outcomes/data/OutcomeEventsService;", "", "sendOutcomeEvent", "", "jsonObject", "Lorg/json/JSONObject;", "responseHandler", "Lcom/onesignal/OneSignalApiResponseHandler;", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public interface OutcomeEventsService {
    void sendOutcomeEvent(JSONObject jSONObject, OneSignalApiResponseHandler oneSignalApiResponseHandler);
}
