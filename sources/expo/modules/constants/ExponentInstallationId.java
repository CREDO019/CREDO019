package expo.modules.constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;

/* compiled from: ExponentInstallationId.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/constants/ExponentInstallationId;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSharedPreferences", "Landroid/content/SharedPreferences;", "nonBackedUpUuidFile", "Ljava/io/File;", "getNonBackedUpUuidFile", "()Ljava/io/File;", "uuid", "", "getOrCreateUUID", "getUUID", "Companion", "expo-constants_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ExponentInstallationId {
    public static final Companion Companion = new Companion(null);
    public static final String LEGACY_UUID_KEY = "uuid";
    public static final String UUID_FILE_NAME = "expo_installation_uuid.txt";
    private final Context context;
    private final SharedPreferences mSharedPreferences;
    private String uuid;

    public ExponentInstallationId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("host.exp.exponent.SharedPreferences", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        this.mSharedPreferences = sharedPreferences;
    }

    public final String getUUID() {
        String str;
        String str2 = this.uuid;
        if (str2 != null) {
            return str2;
        }
        File nonBackedUpUuidFile = getNonBackedUpUuidFile();
        boolean z = true;
        try {
            FileReader fileReader = new FileReader(nonBackedUpUuidFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.uuid = UUID.fromString(bufferedReader.readLine()).toString();
            Unit unit = Unit.INSTANCE;
            Closeable.closeFinally(bufferedReader, null);
            Unit unit2 = Unit.INSTANCE;
            Closeable.closeFinally(fileReader, null);
        } catch (Exception e) {
            if (!(e instanceof IOException ? true : e instanceof IllegalArgumentException)) {
                throw e;
            }
        }
        String str3 = this.uuid;
        if (str3 != null) {
            return str3;
        }
        String string = this.mSharedPreferences.getString("uuid", null);
        if (string != null) {
            this.uuid = string;
            try {
                FileWriter fileWriter = new FileWriter(nonBackedUpUuidFile);
                fileWriter.write(string);
                Unit unit3 = Unit.INSTANCE;
                Closeable.closeFinally(fileWriter, null);
            } catch (IOException e2) {
                str = ExponentInstallationIdKt.TAG;
                Log.e(str, "Error while migrating UUID from legacy storage. " + e2);
                z = false;
            }
            if (z) {
                this.mSharedPreferences.edit().remove("uuid").apply();
            }
        }
        return this.uuid;
    }

    public final String getOrCreateUUID() {
        String str;
        String str2 = getUUID();
        if (str2 != null) {
            return str2;
        }
        this.uuid = UUID.randomUUID().toString();
        try {
            FileWriter fileWriter = new FileWriter(getNonBackedUpUuidFile());
            fileWriter.write(this.uuid);
            Unit unit = Unit.INSTANCE;
            Closeable.closeFinally(fileWriter, null);
        } catch (IOException e) {
            str = ExponentInstallationIdKt.TAG;
            Log.e(str, "Error while writing new UUID. " + e);
        }
        String str3 = this.uuid;
        Intrinsics.checkNotNull(str3);
        return str3;
    }

    private final File getNonBackedUpUuidFile() {
        return new File(this.context.getNoBackupFilesDir(), UUID_FILE_NAME);
    }

    /* compiled from: ExponentInstallationId.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/constants/ExponentInstallationId$Companion;", "", "()V", "LEGACY_UUID_KEY", "", "UUID_FILE_NAME", "expo-constants_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
