package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.ExoMediaDrm;
import java.util.UUID;

/* loaded from: classes2.dex */
public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID r1, ExoMediaDrm.KeyRequest keyRequest) throws MediaDrmCallbackException;

    byte[] executeProvisionRequest(UUID r1, ExoMediaDrm.ProvisionRequest provisionRequest) throws MediaDrmCallbackException;
}
