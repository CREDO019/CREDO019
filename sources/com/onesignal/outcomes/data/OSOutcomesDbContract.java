package com.onesignal.outcomes.data;

import kotlin.Metadata;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, m183d2 = {"Lcom/onesignal/outcomes/data/CachedUniqueOutcomeTable;", "", "()V", "COLUMN_CHANNEL_INFLUENCE_ID", "", "COLUMN_CHANNEL_TYPE", "COLUMN_NAME_NAME", "COLUMN_NAME_NOTIFICATION_ID", "ID", "TABLE_NAME", "TABLE_NAME_V1", "TABLE_NAME_V2", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* renamed from: com.onesignal.outcomes.data.CachedUniqueOutcomeTable */
/* loaded from: classes3.dex */
public final class OSOutcomesDbContract {
    public static final String COLUMN_CHANNEL_INFLUENCE_ID = "channel_influence_id";
    public static final String COLUMN_CHANNEL_TYPE = "channel_type";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_NOTIFICATION_ID = "notification_id";

    /* renamed from: ID */
    public static final String f1309ID = "_id";
    public static final OSOutcomesDbContract INSTANCE = new OSOutcomesDbContract();
    public static final String TABLE_NAME = "cached_unique_outcome";
    public static final String TABLE_NAME_V1 = "cached_unique_outcome_notification";
    public static final String TABLE_NAME_V2 = "cached_unique_outcome";

    private OSOutcomesDbContract() {
    }
}
