package com.fasterxml.jackson.databind.jsonFormatVisitors;

import androidx.autofill.HintConstants;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.onesignal.influence.OSInfluenceConstants;

/* loaded from: classes2.dex */
public enum JsonValueFormat {
    COLOR("color"),
    DATE("date"),
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE(HintConstants.AUTOFILL_HINT_PHONE),
    REGEX("regex"),
    STYLE(TtmlNode.TAG_STYLE),
    TIME(OSInfluenceConstants.TIME),
    URI("uri"),
    UTC_MILLISEC("utc-millisec");
    
    private final String _desc;

    JsonValueFormat(String str) {
        this._desc = str;
    }

    @Override // java.lang.Enum
    @JsonValue
    public String toString() {
        return this._desc;
    }
}
