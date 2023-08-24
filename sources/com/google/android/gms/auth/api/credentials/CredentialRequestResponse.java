package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.api.C2134Response;

/* compiled from: com.google.android.gms:play-services-auth@@19.2.0 */
/* loaded from: classes2.dex */
public class CredentialRequestResponse extends C2134Response<CredentialRequestResult> {
    public Credential getCredential() {
        return getResult().getCredential();
    }
}
