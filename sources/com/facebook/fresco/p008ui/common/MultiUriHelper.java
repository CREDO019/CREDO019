package com.facebook.fresco.p008ui.common;

import android.net.Uri;
import com.facebook.common.internal.InterfaceC1181Fn;
import javax.annotation.Nullable;

/* renamed from: com.facebook.fresco.ui.common.MultiUriHelper */
/* loaded from: classes.dex */
public abstract class MultiUriHelper {
    @Nullable
    public static <T> Uri getMainUri(@Nullable T mainRequest, @Nullable T lowResRequest, @Nullable T[] firstAvailableRequest, InterfaceC1181Fn<T, Uri> requestToUri) {
        Uri apply;
        Uri apply2;
        if (mainRequest == null || (apply2 = requestToUri.apply(mainRequest)) == null) {
            if (firstAvailableRequest == null || firstAvailableRequest.length <= 0 || firstAvailableRequest[0] == null || (apply = requestToUri.apply(firstAvailableRequest[0])) == null) {
                if (lowResRequest != null) {
                    return requestToUri.apply(lowResRequest);
                }
                return null;
            }
            return apply;
        }
        return apply2;
    }
}
