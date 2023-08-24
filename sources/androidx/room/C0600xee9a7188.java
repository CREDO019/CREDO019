package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.p007db.SupportSQLiteDatabase;

/* compiled from: D8$$SyntheticClass */
/* renamed from: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda13 */
/* loaded from: classes.dex */
public final /* synthetic */ class C0600xee9a7188 implements Function {
    public static final /* synthetic */ C0600xee9a7188 INSTANCE = new C0600xee9a7188();

    private /* synthetic */ C0600xee9a7188() {
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).yieldIfContendedSafely());
    }
}
