package expo.modules.updates.p021db;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseHolder.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lexpo/modules/updates/db/DatabaseHolder;", "", "mDatabase", "Lexpo/modules/updates/db/UpdatesDatabase;", "(Lexpo/modules/updates/db/UpdatesDatabase;)V", "database", "getDatabase", "()Lexpo/modules/updates/db/UpdatesDatabase;", "isInUse", "", "releaseDatabase", "", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.DatabaseHolder */
/* loaded from: classes5.dex */
public final class DatabaseHolder {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DatabaseHolder";
    private boolean isInUse;
    private final UpdatesDatabase mDatabase;

    public DatabaseHolder(UpdatesDatabase mDatabase) {
        Intrinsics.checkNotNullParameter(mDatabase, "mDatabase");
        this.mDatabase = mDatabase;
    }

    public final synchronized UpdatesDatabase getDatabase() {
        while (this.isInUse) {
            try {
                wait();
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while waiting for database", e);
            }
        }
        this.isInUse = true;
        return this.mDatabase;
    }

    public final synchronized void releaseDatabase() {
        this.isInUse = false;
        notify();
    }

    /* compiled from: DatabaseHolder.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/db/DatabaseHolder$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.updates.db.DatabaseHolder$Companion */
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
