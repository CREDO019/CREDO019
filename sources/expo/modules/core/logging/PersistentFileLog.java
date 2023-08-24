package expo.modules.core.logging;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import kotlin.p023io.FilesKt;

/* compiled from: PersistentFileLog.kt */
@Metadata(m184d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0001!B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J;\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032+\b\u0002\u0010\u000b\u001a%\u0012\u001b\u0012\u0019\u0018\u00010\rj\u0004\u0018\u0001`\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\fJ\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J1\u0010\u0014\u001a\u00020\t2)\u0010\u000b\u001a%\u0012\u001b\u0012\u0019\u0018\u00010\rj\u0004\u0018\u0001`\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\fJ\b\u0010\u0015\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\tH\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002JT\u0010\u0019\u001a\u00020\t2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u001b0\f2)\u0010\u000b\u001a%\u0012\u001b\u0012\u0019\u0018\u00010\rj\u0004\u0018\u0001`\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\fJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dJ\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dH\u0002J\u0016\u0010\u001f\u001a\u00020\t2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001dH\u0002R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m183d2 = {"Lexpo/modules/core/logging/PersistentFileLog;", "", "category", "", "context", "Landroid/content/Context;", "(Ljava/lang/String;Landroid/content/Context;)V", "filePath", "appendEntry", "", "entry", "completionHandler", "Lkotlin/Function1;", "Ljava/lang/Error;", "Lkotlin/Error;", "Lkotlin/ParameterName;", "name", "_", "appendTextToFile", "text", "clearEntries", "deleteFileSync", "ensureFileExists", "getFileSize", "", "purgeEntriesNotMatchingFilter", "filter", "", "readEntries", "", "readFileLinesSync", "writeFileLinesSync", "entries", "Companion", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PersistentFileLog {
    private static final String FILE_NAME_PREFIX = "dev.expo.modules.core.logging";
    private final String filePath;
    public static final Companion Companion = new Companion(null);
    private static final PersistentFileLogSerialDispatchQueue queue = new PersistentFileLogSerialDispatchQueue();

    public PersistentFileLog(String category, Context context) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(context, "context");
        String path = context.getFilesDir().getPath();
        this.filePath = path + "/dev.expo.modules.core.logging." + category;
    }

    public final List<String> readEntries() {
        if (0 == getFileSize()) {
            return CollectionsKt.emptyList();
        }
        return readFileLinesSync();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void appendEntry$default(PersistentFileLog persistentFileLog, String str, Function1 function1, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            function1 = new Function1<Error, Unit>() { // from class: expo.modules.core.logging.PersistentFileLog$appendEntry$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Error error) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Error error) {
                    invoke2(error);
                    return Unit.INSTANCE;
                }
            };
        }
        persistentFileLog.appendEntry(str, function1);
    }

    public final void appendEntry(final String entry, final Function1<? super Error, Unit> completionHandler) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(completionHandler, "completionHandler");
        queue.add(new Functions<Unit>() { // from class: expo.modules.core.logging.PersistentFileLog$appendEntry$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                long fileSize;
                String str;
                try {
                    PersistentFileLog.this.ensureFileExists();
                    fileSize = PersistentFileLog.this.getFileSize();
                    if (fileSize == 0) {
                        str = entry;
                    } else {
                        str = "\n" + entry;
                    }
                    PersistentFileLog.this.appendTextToFile(str);
                    completionHandler.invoke(null);
                } catch (Error e) {
                    completionHandler.invoke(e);
                }
            }
        });
    }

    public final void purgeEntriesNotMatchingFilter(final Function1<? super String, Boolean> filter, final Function1<? super Error, Unit> completionHandler) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Intrinsics.checkNotNullParameter(completionHandler, "completionHandler");
        queue.add(new Functions<Unit>() { // from class: expo.modules.core.logging.PersistentFileLog$purgeEntriesNotMatchingFilter$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                List readFileLinesSync;
                try {
                    PersistentFileLog.this.ensureFileExists();
                    readFileLinesSync = PersistentFileLog.this.readFileLinesSync();
                    Function1<String, Boolean> function1 = filter;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : readFileLinesSync) {
                        if (((Boolean) function1.invoke(obj)).booleanValue()) {
                            arrayList.add(obj);
                        }
                    }
                    PersistentFileLog.this.writeFileLinesSync(arrayList);
                    completionHandler.invoke(null);
                } catch (Throwable th) {
                    completionHandler.invoke(new Error(th));
                }
            }
        });
    }

    public final void clearEntries(final Function1<? super Error, Unit> completionHandler) {
        Intrinsics.checkNotNullParameter(completionHandler, "completionHandler");
        queue.add(new Functions<Unit>() { // from class: expo.modules.core.logging.PersistentFileLog$clearEntries$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                try {
                    PersistentFileLog.this.deleteFileSync();
                    completionHandler.invoke(null);
                } catch (Error e) {
                    completionHandler.invoke(e);
                }
            }
        });
    }

    public final void ensureFileExists() {
        File file = new File(this.filePath);
        if (file.exists() || file.createNewFile()) {
            return;
        }
        String str = this.filePath;
        throw new IOException("Unable to create file at path " + str);
    }

    public final long getFileSize() {
        File file = new File(this.filePath);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                long size = fileInputStream.getChannel().size();
                Closeable.closeFinally(fileInputStream, null);
                return size;
            } catch (IOException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    public final void appendTextToFile(String str) {
        File file = new File(this.filePath);
        Charset defaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(defaultCharset, "defaultCharset()");
        FilesKt.appendText(file, str, defaultCharset);
    }

    public final List<String> readFileLinesSync() {
        File file = new File(this.filePath);
        Charset defaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(defaultCharset, "defaultCharset()");
        ArrayList arrayList = new ArrayList();
        for (Object obj : FilesKt.readLines(file, defaultCharset)) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void writeFileLinesSync(List<String> list) {
        File file = new File(this.filePath);
        String joinToString$default = CollectionsKt.joinToString$default(list, "\n", null, null, 0, null, null, 62, null);
        Charset defaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(defaultCharset, "defaultCharset()");
        FilesKt.writeText(file, joinToString$default, defaultCharset);
    }

    public final void deleteFileSync() {
        File file = new File(this.filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /* compiled from: PersistentFileLog.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/core/logging/PersistentFileLog$Companion;", "", "()V", "FILE_NAME_PREFIX", "", "queue", "Lexpo/modules/core/logging/PersistentFileLogSerialDispatchQueue;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
