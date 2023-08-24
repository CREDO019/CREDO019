package androidx.room;

import androidx.sqlite.p007db.SupportSQLiteOpenHelper;

/* loaded from: classes.dex */
interface DelegatingOpenHelper {
    SupportSQLiteOpenHelper getDelegate();
}
