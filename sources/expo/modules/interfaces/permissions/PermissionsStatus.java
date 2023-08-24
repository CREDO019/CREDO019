package expo.modules.interfaces.permissions;

/* loaded from: classes4.dex */
public enum PermissionsStatus {
    GRANTED(PermissionsResponse.GRANTED_KEY),
    UNDETERMINED("undetermined"),
    DENIED("denied");
    
    private String status;

    PermissionsStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }
}
