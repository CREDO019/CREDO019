package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions;", "", "()V", "FileSystemModuleNotFound", "MissingActivity", "MissingPermissions", "PermissionsModuleNotFound", "ReactContextLost", "SimulatorNotSupported", "ViewNotFound", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.kotlin.exception.Exceptions */
/* loaded from: classes4.dex */
public final class CommonExceptions {

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$ViewNotFound;", "Lexpo/modules/kotlin/exception/CodedException;", "viewType", "Lkotlin/reflect/KClass;", "viewTag", "", "(Lkotlin/reflect/KClass;I)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$ViewNotFound */
    /* loaded from: classes4.dex */
    public static final class ViewNotFound extends CodedException {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewNotFound(KClass<?> viewType, int r4) {
            super("Unable to find the " + viewType + " view with tag " + r4, null, 2, null);
            Intrinsics.checkNotNullParameter(viewType, "viewType");
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$ReactContextLost;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$ReactContextLost */
    /* loaded from: classes4.dex */
    public static final class ReactContextLost extends CodedException {
        public ReactContextLost() {
            super("The react context has been lost", null, 2, null);
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$PermissionsModuleNotFound;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$PermissionsModuleNotFound */
    /* loaded from: classes4.dex */
    public static final class PermissionsModuleNotFound extends CodedException {
        public PermissionsModuleNotFound() {
            super("Permissions module not found", null, 2, null);
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$SimulatorNotSupported;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$SimulatorNotSupported */
    /* loaded from: classes4.dex */
    public static final class SimulatorNotSupported extends CodedException {
        public SimulatorNotSupported() {
            super("This operation is not supported on the simulator", null, 2, null);
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$FileSystemModuleNotFound;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$FileSystemModuleNotFound */
    /* loaded from: classes4.dex */
    public static final class FileSystemModuleNotFound extends CodedException {
        public FileSystemModuleNotFound() {
            super("FileSystem module not found, make sure 'expo-file-system' is linked correctly", null, 2, null);
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$MissingPermissions;", "Lexpo/modules/kotlin/exception/CodedException;", "permissions", "", "", "([Ljava/lang/String;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$MissingPermissions */
    /* loaded from: classes4.dex */
    public static final class MissingPermissions extends CodedException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public MissingPermissions(java.lang.String... r11) {
            /*
                r10 = this;
                java.lang.String r0 = "permissions"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                java.lang.String r0 = ", "
                r2 = r0
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 62
                r9 = 0
                r1 = r11
                java.lang.String r11 = kotlin.collections.ArraysKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Missing permissions: "
                r0.append(r1)
                r0.append(r11)
                java.lang.String r11 = r0.toString()
                r0 = 0
                r1 = 2
                r10.<init>(r11, r0, r1, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.CommonExceptions.MissingPermissions.<init>(java.lang.String[]):void");
        }
    }

    /* compiled from: CommonExceptions.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m183d2 = {"Lexpo/modules/kotlin/exception/Exceptions$MissingActivity;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.exception.Exceptions$MissingActivity */
    /* loaded from: classes4.dex */
    public static final class MissingActivity extends CodedException {
        public MissingActivity() {
            super("The current activity is no longer available", null, 2, null);
        }
    }
}
