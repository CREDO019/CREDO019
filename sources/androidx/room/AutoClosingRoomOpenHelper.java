package androidx.room;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.arch.core.util.Function;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.room.util.SneakyThrow;
import androidx.sqlite.p007db.SupportSQLiteCompat;
import androidx.sqlite.p007db.SupportSQLiteDatabase;
import androidx.sqlite.p007db.SupportSQLiteOpenHelper;
import androidx.sqlite.p007db.SupportSQLiteQuery;
import androidx.sqlite.p007db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final AutoCloser mAutoCloser;
    private final AutoClosingSupportSQLiteDatabase mAutoClosingDb;
    private final SupportSQLiteOpenHelper mDelegateOpenHelper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper, AutoCloser autoCloser) {
        this.mDelegateOpenHelper = supportSQLiteOpenHelper;
        this.mAutoCloser = autoCloser;
        autoCloser.init(supportSQLiteOpenHelper);
        this.mAutoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mDelegateOpenHelper.getDatabaseName();
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.mDelegateOpenHelper.setWriteAheadLoggingEnabled(z);
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        this.mAutoClosingDb.pokeOpen();
        return this.mAutoClosingDb;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        this.mAutoClosingDb.pokeOpen();
        return this.mAutoClosingDb;
    }

    @Override // androidx.sqlite.p007db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.mAutoClosingDb.close();
        } catch (IOException e) {
            SneakyThrow.reThrow(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoCloser getAutoCloser() {
        return this.mAutoCloser;
    }

    SupportSQLiteDatabase getAutoClosingDb() {
        return this.mAutoClosingDb;
    }

    @Override // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegateOpenHelper;
    }

    /* loaded from: classes.dex */
    static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        private final AutoCloser mAutoCloser;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$pokeOpen$0(SupportSQLiteDatabase supportSQLiteDatabase) {
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public /* synthetic */ void execPerConnectionSQL(String str, Object[] objArr) {
            SupportSQLiteDatabase.CC.$default$execPerConnectionSQL(this, str, objArr);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public /* synthetic */ boolean isExecPerConnectionSQLSupported() {
            return SupportSQLiteDatabase.CC.$default$isExecPerConnectionSQLSupported(this);
        }

        AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser) {
            this.mAutoCloser = autoCloser;
        }

        void pokeOpen() {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda4
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$pokeOpen$0((SupportSQLiteDatabase) obj);
                }
            });
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public SupportSQLiteStatement compileStatement(String str) {
            return new AutoClosingSupportSqliteStatement(str, this.mAutoCloser);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void beginTransaction() {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void beginTransactionNonExclusive() {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListener(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void endTransaction() {
            if (this.mAutoCloser.getDelegateDatabase() == null) {
                throw new IllegalStateException("End transaction called but delegateDb is null");
            }
            try {
                this.mAutoCloser.getDelegateDatabase().endTransaction();
            } finally {
                this.mAutoCloser.decrementCountAndScheduleClose();
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setTransactionSuccessful() {
            SupportSQLiteDatabase delegateDatabase = this.mAutoCloser.getDelegateDatabase();
            if (delegateDatabase == null) {
                throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null");
            }
            delegateDatabase.setTransactionSuccessful();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean inTransaction() {
            if (this.mAutoCloser.getDelegateDatabase() == null) {
                return false;
            }
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda8
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(((SupportSQLiteDatabase) obj).inTransaction());
                }
            })).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean isDbLockedByCurrentThread() {
            if (this.mAutoCloser.getDelegateDatabase() == null) {
                return false;
            }
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda10
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDbLockedByCurrentThread());
                }
            })).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean yieldIfContendedSafely() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0600xee9a7188.INSTANCE)).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean yieldIfContendedSafely(long j) {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0600xee9a7188.INSTANCE)).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public int getVersion() {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda7
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((SupportSQLiteDatabase) obj).getVersion());
                }
            })).intValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setVersion(final int r3) {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda16
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setVersion$1(r3, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$setVersion$1(int r0, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.setVersion(r0);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public long getMaximumSize() {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda14
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((SupportSQLiteDatabase) obj).getMaximumSize());
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public long setMaximumSize(final long j) {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda17
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    Long valueOf;
                    valueOf = Long.valueOf(((SupportSQLiteDatabase) obj).setMaximumSize(j));
                    return valueOf;
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public long getPageSize() {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda15
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((SupportSQLiteDatabase) obj).getPageSize());
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setPageSize(final long j) {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda18
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setPageSize$3(j, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$setPageSize$3(long j, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.setPageSize(j);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public Cursor query(String str) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(str), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public Cursor query(String str, Object[] objArr) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(str, objArr), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery, cancellationSignal), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public long insert(final String str, final int r4, final ContentValues contentValues) throws SQLException {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda20
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    Long valueOf;
                    valueOf = Long.valueOf(((SupportSQLiteDatabase) obj).insert(str, r4, contentValues));
                    return valueOf;
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public int delete(final String str, final String str2, final Object[] objArr) {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda22
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(((SupportSQLiteDatabase) obj).delete(str, str2, objArr));
                    return valueOf;
                }
            })).intValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public int update(final String str, final int r10, final ContentValues contentValues, final String str2, final Object[] objArr) {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda21
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    Integer valueOf;
                    valueOf = Integer.valueOf(((SupportSQLiteDatabase) obj).update(str, r10, contentValues, str2, objArr));
                    return valueOf;
                }
            })).intValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void execSQL(final String str) throws SQLException {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda19
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$execSQL$7(str, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$execSQL$7(String str, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL(str);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void execSQL(final String str, final Object[] objArr) throws SQLException {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda23
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$execSQL$8(str, objArr, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$execSQL$8(String str, Object[] objArr, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL(str, objArr);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean isReadOnly() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda12
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(((SupportSQLiteDatabase) obj).isReadOnly());
                }
            })).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean isOpen() {
            SupportSQLiteDatabase delegateDatabase = this.mAutoCloser.getDelegateDatabase();
            if (delegateDatabase == null) {
                return false;
            }
            return delegateDatabase.isOpen();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean needUpgrade(final int r3) {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda0
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(((SupportSQLiteDatabase) obj).needUpgrade(r3));
                    return valueOf;
                }
            })).booleanValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public String getPath() {
            return (String) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda6
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return ((SupportSQLiteDatabase) obj).getPath();
                }
            });
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setLocale(final Locale locale) {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda1
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setLocale$10(locale, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$setLocale$10(Locale locale, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.setLocale(locale);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setMaxSqlCacheSize(final int r3) {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda11
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setMaxSqlCacheSize$11(r3, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$setMaxSqlCacheSize$11(int r0, SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.setMaxSqlCacheSize(r0);
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void setForeignKeyConstraintsEnabled(final boolean z) {
            this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda2
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$setForeignKeyConstraintsEnabled$12(z, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$setForeignKeyConstraintsEnabled$12(boolean z, SupportSQLiteDatabase supportSQLiteDatabase) {
            if (Build.VERSION.SDK_INT >= 16) {
                supportSQLiteDatabase.setForeignKeyConstraintsEnabled(z);
                return null;
            }
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean enableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public void disableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda3
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSQLiteDatabase.lambda$isWriteAheadLoggingEnabled$13((SupportSQLiteDatabase) obj);
                }
            })).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Boolean lambda$isWriteAheadLoggingEnabled$13(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (Build.VERSION.SDK_INT >= 16) {
                return Boolean.valueOf(supportSQLiteDatabase.isWriteAheadLoggingEnabled());
            }
            return false;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public List<Pair<String, String>> getAttachedDbs() {
            return (List) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda5
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return ((SupportSQLiteDatabase) obj).getAttachedDbs();
                }
            });
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteDatabase
        public boolean isDatabaseIntegrityOk() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$$ExternalSyntheticLambda9
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDatabaseIntegrityOk());
                }
            })).booleanValue();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.mAutoCloser.closeDatabaseIfOpen();
        }
    }

    /* loaded from: classes.dex */
    private static final class KeepAliveCursor implements Cursor {
        private final AutoCloser mAutoCloser;
        private final Cursor mDelegate;

        KeepAliveCursor(Cursor cursor, AutoCloser autoCloser) {
            this.mDelegate = cursor;
            this.mAutoCloser = autoCloser;
        }

        @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mDelegate.close();
            this.mAutoCloser.decrementCountAndScheduleClose();
        }

        @Override // android.database.Cursor
        public boolean isClosed() {
            return this.mDelegate.isClosed();
        }

        @Override // android.database.Cursor
        public int getCount() {
            return this.mDelegate.getCount();
        }

        @Override // android.database.Cursor
        public int getPosition() {
            return this.mDelegate.getPosition();
        }

        @Override // android.database.Cursor
        public boolean move(int r2) {
            return this.mDelegate.move(r2);
        }

        @Override // android.database.Cursor
        public boolean moveToPosition(int r2) {
            return this.mDelegate.moveToPosition(r2);
        }

        @Override // android.database.Cursor
        public boolean moveToFirst() {
            return this.mDelegate.moveToFirst();
        }

        @Override // android.database.Cursor
        public boolean moveToLast() {
            return this.mDelegate.moveToLast();
        }

        @Override // android.database.Cursor
        public boolean moveToNext() {
            return this.mDelegate.moveToNext();
        }

        @Override // android.database.Cursor
        public boolean moveToPrevious() {
            return this.mDelegate.moveToPrevious();
        }

        @Override // android.database.Cursor
        public boolean isFirst() {
            return this.mDelegate.isFirst();
        }

        @Override // android.database.Cursor
        public boolean isLast() {
            return this.mDelegate.isLast();
        }

        @Override // android.database.Cursor
        public boolean isBeforeFirst() {
            return this.mDelegate.isBeforeFirst();
        }

        @Override // android.database.Cursor
        public boolean isAfterLast() {
            return this.mDelegate.isAfterLast();
        }

        @Override // android.database.Cursor
        public int getColumnIndex(String str) {
            return this.mDelegate.getColumnIndex(str);
        }

        @Override // android.database.Cursor
        public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
            return this.mDelegate.getColumnIndexOrThrow(str);
        }

        @Override // android.database.Cursor
        public String getColumnName(int r2) {
            return this.mDelegate.getColumnName(r2);
        }

        @Override // android.database.Cursor
        public String[] getColumnNames() {
            return this.mDelegate.getColumnNames();
        }

        @Override // android.database.Cursor
        public int getColumnCount() {
            return this.mDelegate.getColumnCount();
        }

        @Override // android.database.Cursor
        public byte[] getBlob(int r2) {
            return this.mDelegate.getBlob(r2);
        }

        @Override // android.database.Cursor
        public String getString(int r2) {
            return this.mDelegate.getString(r2);
        }

        @Override // android.database.Cursor
        public void copyStringToBuffer(int r2, CharArrayBuffer charArrayBuffer) {
            this.mDelegate.copyStringToBuffer(r2, charArrayBuffer);
        }

        @Override // android.database.Cursor
        public short getShort(int r2) {
            return this.mDelegate.getShort(r2);
        }

        @Override // android.database.Cursor
        public int getInt(int r2) {
            return this.mDelegate.getInt(r2);
        }

        @Override // android.database.Cursor
        public long getLong(int r3) {
            return this.mDelegate.getLong(r3);
        }

        @Override // android.database.Cursor
        public float getFloat(int r2) {
            return this.mDelegate.getFloat(r2);
        }

        @Override // android.database.Cursor
        public double getDouble(int r3) {
            return this.mDelegate.getDouble(r3);
        }

        @Override // android.database.Cursor
        public int getType(int r2) {
            return this.mDelegate.getType(r2);
        }

        @Override // android.database.Cursor
        public boolean isNull(int r2) {
            return this.mDelegate.isNull(r2);
        }

        @Override // android.database.Cursor
        @Deprecated
        public void deactivate() {
            this.mDelegate.deactivate();
        }

        @Override // android.database.Cursor
        @Deprecated
        public boolean requery() {
            return this.mDelegate.requery();
        }

        @Override // android.database.Cursor
        public void registerContentObserver(ContentObserver contentObserver) {
            this.mDelegate.registerContentObserver(contentObserver);
        }

        @Override // android.database.Cursor
        public void unregisterContentObserver(ContentObserver contentObserver) {
            this.mDelegate.unregisterContentObserver(contentObserver);
        }

        @Override // android.database.Cursor
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDelegate.registerDataSetObserver(dataSetObserver);
        }

        @Override // android.database.Cursor
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDelegate.unregisterDataSetObserver(dataSetObserver);
        }

        @Override // android.database.Cursor
        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
            this.mDelegate.setNotificationUri(contentResolver, uri);
        }

        @Override // android.database.Cursor
        public void setNotificationUris(ContentResolver contentResolver, List<Uri> list) {
            SupportSQLiteCompat.Api29Impl.setNotificationUris(this.mDelegate, contentResolver, list);
        }

        @Override // android.database.Cursor
        public Uri getNotificationUri() {
            return SupportSQLiteCompat.Api19Impl.getNotificationUri(this.mDelegate);
        }

        @Override // android.database.Cursor
        public List<Uri> getNotificationUris() {
            return SupportSQLiteCompat.Api29Impl.getNotificationUris(this.mDelegate);
        }

        @Override // android.database.Cursor
        public boolean getWantsAllOnMoveCalls() {
            return this.mDelegate.getWantsAllOnMoveCalls();
        }

        @Override // android.database.Cursor
        public void setExtras(Bundle bundle) {
            SupportSQLiteCompat.Api23Impl.setExtras(this.mDelegate, bundle);
        }

        @Override // android.database.Cursor
        public Bundle getExtras() {
            return this.mDelegate.getExtras();
        }

        @Override // android.database.Cursor
        public Bundle respond(Bundle bundle) {
            return this.mDelegate.respond(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AutoClosingSupportSqliteStatement implements SupportSQLiteStatement {
        private final AutoCloser mAutoCloser;
        private final ArrayList<Object> mBinds = new ArrayList<>();
        private final String mSql;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        AutoClosingSupportSqliteStatement(String str, AutoCloser autoCloser) {
            this.mSql = str;
            this.mAutoCloser = autoCloser;
        }

        private <T> T executeSqliteStatementWithRefCount(final Function<SupportSQLiteStatement, T> function) {
            return (T) this.mAutoCloser.executeRefCountingFunction(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda0
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.this.m1394xfbbec096(function, (SupportSQLiteDatabase) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$executeSqliteStatementWithRefCount$0$androidx-room-AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement */
        public /* synthetic */ Object m1394xfbbec096(Function function, SupportSQLiteDatabase supportSQLiteDatabase) {
            SupportSQLiteStatement compileStatement = supportSQLiteDatabase.compileStatement(this.mSql);
            doBinds(compileStatement);
            return function.apply(compileStatement);
        }

        private void doBinds(SupportSQLiteStatement supportSQLiteStatement) {
            int r0 = 0;
            while (r0 < this.mBinds.size()) {
                int r1 = r0 + 1;
                Object obj = this.mBinds.get(r0);
                if (obj == null) {
                    supportSQLiteStatement.bindNull(r1);
                } else if (obj instanceof Long) {
                    supportSQLiteStatement.bindLong(r1, ((Long) obj).longValue());
                } else if (obj instanceof Double) {
                    supportSQLiteStatement.bindDouble(r1, ((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    supportSQLiteStatement.bindString(r1, (String) obj);
                } else if (obj instanceof byte[]) {
                    supportSQLiteStatement.bindBlob(r1, (byte[]) obj);
                }
                r0 = r1;
            }
        }

        private void saveBinds(int r4, Object obj) {
            int r42 = r4 - 1;
            if (r42 >= this.mBinds.size()) {
                for (int size = this.mBinds.size(); size <= r42; size++) {
                    this.mBinds.add(null);
                }
            }
            this.mBinds.set(r42, obj);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteStatement
        public void execute() {
            executeSqliteStatementWithRefCount(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda1
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement.lambda$execute$1((SupportSQLiteStatement) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$execute$1(SupportSQLiteStatement supportSQLiteStatement) {
            supportSQLiteStatement.execute();
            return null;
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteStatement
        public int executeUpdateDelete() {
            return ((Integer) executeSqliteStatementWithRefCount(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda2
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((SupportSQLiteStatement) obj).executeUpdateDelete());
                }
            })).intValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteStatement
        public long executeInsert() {
            return ((Long) executeSqliteStatementWithRefCount(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda4
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((SupportSQLiteStatement) obj).executeInsert());
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteStatement
        public long simpleQueryForLong() {
            return ((Long) executeSqliteStatementWithRefCount(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda5
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((SupportSQLiteStatement) obj).simpleQueryForLong());
                }
            })).longValue();
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteStatement
        public String simpleQueryForString() {
            return (String) executeSqliteStatementWithRefCount(new Function() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$$ExternalSyntheticLambda3
                @Override // androidx.arch.core.util.Function
                public final Object apply(Object obj) {
                    return ((SupportSQLiteStatement) obj).simpleQueryForString();
                }
            });
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void bindNull(int r2) {
            saveBinds(r2, null);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void bindLong(int r1, long j) {
            saveBinds(r1, Long.valueOf(j));
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void bindDouble(int r1, double d) {
            saveBinds(r1, Double.valueOf(d));
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void bindString(int r1, String str) {
            saveBinds(r1, str);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void bindBlob(int r1, byte[] bArr) {
            saveBinds(r1, bArr);
        }

        @Override // androidx.sqlite.p007db.SupportSQLiteProgram
        public void clearBindings() {
            this.mBinds.clear();
        }
    }
}