package expo.modules.interfaces.permissions;

import expo.modules.core.Promise;

/* loaded from: classes4.dex */
public interface Permissions {
    void askForPermissions(PermissionsResponseListener permissionsResponseListener, String... strArr);

    void askForPermissionsWithPromise(Promise promise, String... strArr);

    void getPermissions(PermissionsResponseListener permissionsResponseListener, String... strArr);

    void getPermissionsWithPromise(Promise promise, String... strArr);

    boolean hasGrantedPermissions(String... strArr);

    boolean isPermissionPresentInManifest(String str);

    /* renamed from: expo.modules.interfaces.permissions.Permissions$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static void getPermissionsWithPermissionsManager(Permissions permissions, Promise promise, String... strArr) {
            if (permissions == null) {
                promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
            } else {
                permissions.getPermissionsWithPromise(promise, strArr);
            }
        }

        public static void getPermissionsWithPermissionsManager(Permissions permissions, final expo.modules.kotlin.Promise promise, String... strArr) {
            getPermissionsWithPermissionsManager(permissions, new Promise() { // from class: expo.modules.interfaces.permissions.Permissions.1
                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(String str, String str2) {
                    reject(str, str2, null);
                }

                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(String str, Throwable th) {
                    reject(str, th.getMessage(), th);
                }

                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(Throwable th) {
                    Promise.CC.$default$reject(this, th);
                }

                @Override // expo.modules.core.Promise
                public void resolve(Object obj) {
                    expo.modules.kotlin.Promise.this.resolve(obj);
                }

                @Override // expo.modules.core.Promise
                public void reject(String str, String str2, Throwable th) {
                    expo.modules.kotlin.Promise.this.reject(str, str2, th);
                }
            }, strArr);
        }

        public static void askForPermissionsWithPermissionsManager(Permissions permissions, Promise promise, String... strArr) {
            if (permissions == null) {
                promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
            } else {
                permissions.askForPermissionsWithPromise(promise, strArr);
            }
        }

        public static void askForPermissionsWithPermissionsManager(Permissions permissions, final expo.modules.kotlin.Promise promise, String... strArr) {
            askForPermissionsWithPermissionsManager(permissions, new Promise() { // from class: expo.modules.interfaces.permissions.Permissions.2
                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(String str, String str2) {
                    reject(str, str2, null);
                }

                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(String str, Throwable th) {
                    reject(str, th.getMessage(), th);
                }

                @Override // expo.modules.core.Promise
                public /* synthetic */ void reject(Throwable th) {
                    Promise.CC.$default$reject(this, th);
                }

                @Override // expo.modules.core.Promise
                public void resolve(Object obj) {
                    expo.modules.kotlin.Promise.this.resolve(obj);
                }

                @Override // expo.modules.core.Promise
                public void reject(String str, String str2, Throwable th) {
                    expo.modules.kotlin.Promise.this.reject(str, str2, th);
                }
            }, strArr);
        }
    }
}
