package expo.modules.interfaces.permissions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionsResponse.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/interfaces/permissions/PermissionsResponse;", "", "status", "Lexpo/modules/interfaces/permissions/PermissionsStatus;", PermissionsResponse.CAN_ASK_AGAIN_KEY, "", "(Lexpo/modules/interfaces/permissions/PermissionsStatus;Z)V", "getCanAskAgain", "()Z", "getStatus", "()Lexpo/modules/interfaces/permissions/PermissionsStatus;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PermissionsResponse {
    public static final String CAN_ASK_AGAIN_KEY = "canAskAgain";
    public static final Companion Companion = new Companion(null);
    public static final String EXPIRES_KEY = "expires";
    public static final String GRANTED_KEY = "granted";
    public static final String PERMISSION_EXPIRES_NEVER = "never";
    public static final String SCOPE_ALWAYS = "always";
    public static final String SCOPE_IN_USE = "whenInUse";
    public static final String SCOPE_KEY = "scope";
    public static final String SCOPE_NONE = "none";
    public static final String STATUS_KEY = "status";
    private final boolean canAskAgain;
    private final PermissionsStatus status;

    public static /* synthetic */ PermissionsResponse copy$default(PermissionsResponse permissionsResponse, PermissionsStatus permissionsStatus, boolean z, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            permissionsStatus = permissionsResponse.status;
        }
        if ((r3 & 2) != 0) {
            z = permissionsResponse.canAskAgain;
        }
        return permissionsResponse.copy(permissionsStatus, z);
    }

    public final PermissionsStatus component1() {
        return this.status;
    }

    public final boolean component2() {
        return this.canAskAgain;
    }

    public final PermissionsResponse copy(PermissionsStatus status, boolean z) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new PermissionsResponse(status, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PermissionsResponse) {
            PermissionsResponse permissionsResponse = (PermissionsResponse) obj;
            return this.status == permissionsResponse.status && this.canAskAgain == permissionsResponse.canAskAgain;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.status.hashCode() * 31;
        boolean z = this.canAskAgain;
        int r1 = z;
        if (z != 0) {
            r1 = 1;
        }
        return hashCode + r1;
    }

    public String toString() {
        PermissionsStatus permissionsStatus = this.status;
        boolean z = this.canAskAgain;
        return "PermissionsResponse(status=" + permissionsStatus + ", canAskAgain=" + z + ")";
    }

    public PermissionsResponse(PermissionsStatus status, boolean z) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        this.canAskAgain = z;
    }

    public /* synthetic */ PermissionsResponse(PermissionsStatus permissionsStatus, boolean z, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(permissionsStatus, (r3 & 2) != 0 ? true : z);
    }

    public final PermissionsStatus getStatus() {
        return this.status;
    }

    public final boolean getCanAskAgain() {
        return this.canAskAgain;
    }

    /* compiled from: PermissionsResponse.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lexpo/modules/interfaces/permissions/PermissionsResponse$Companion;", "", "()V", "CAN_ASK_AGAIN_KEY", "", "EXPIRES_KEY", "GRANTED_KEY", "PERMISSION_EXPIRES_NEVER", "SCOPE_ALWAYS", "SCOPE_IN_USE", "SCOPE_KEY", "SCOPE_NONE", "STATUS_KEY", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
