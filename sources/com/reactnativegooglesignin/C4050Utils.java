package com.reactnativegooglesignin;

import android.net.Uri;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

/* renamed from: com.reactnativegooglesignin.Utils */
/* loaded from: classes3.dex */
public class C4050Utils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String scopesToString(ReadableArray readableArray) {
        StringBuilder sb = new StringBuilder("oauth2:");
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            sb.append(readableArray.getString(r1));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap getUserProperties(GoogleSignInAccount googleSignInAccount) {
        Uri photoUrl = googleSignInAccount.getPhotoUrl();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", googleSignInAccount.getId());
        createMap.putString("name", googleSignInAccount.getDisplayName());
        createMap.putString("givenName", googleSignInAccount.getGivenName());
        createMap.putString("familyName", googleSignInAccount.getFamilyName());
        createMap.putString("email", googleSignInAccount.getEmail());
        createMap.putString("photo", photoUrl != null ? photoUrl.toString() : null);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("user", createMap);
        createMap2.putString("idToken", googleSignInAccount.getIdToken());
        createMap2.putString("serverAuthCode", googleSignInAccount.getServerAuthCode());
        WritableArray createArray = Arguments.createArray();
        for (Scope scope : googleSignInAccount.getGrantedScopes()) {
            String scope2 = scope.toString();
            if (scope2.startsWith("http")) {
                createArray.pushString(scope2);
            }
        }
        createMap2.putArray("scopes", createArray);
        return createMap2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSignInOptions getSignInOptions(Scope[] scopeArr, String str, boolean z, boolean z2, String str2, String str3) {
        GoogleSignInOptions.Builder requestScopes = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(new Scope("email"), scopeArr);
        if (str != null && !str.isEmpty()) {
            requestScopes.requestIdToken(str);
            if (z) {
                requestScopes.requestServerAuthCode(str, z2);
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            requestScopes.setAccountName(str2);
        }
        if (str3 != null && !str3.isEmpty()) {
            requestScopes.setHostedDomain(str3);
        }
        return requestScopes.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Scope[] createScopesArray(ReadableArray readableArray) {
        int size = readableArray.size();
        Scope[] scopeArr = new Scope[size];
        for (int r2 = 0; r2 < size; r2++) {
            scopeArr[r2] = new Scope(readableArray.getString(r2));
        }
        return scopeArr;
    }

    public static int getExceptionCode(Task<Void> task) {
        Exception exception = task.getException();
        if (exception instanceof ApiException) {
            return ((ApiException) exception).getStatusCode();
        }
        return 8;
    }
}
