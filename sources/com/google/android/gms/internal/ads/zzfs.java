package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import com.onesignal.OSInAppMessageContentKt;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzfs extends zzev {
    public static final zzfsg zza = new zzfsg() { // from class: com.google.android.gms.internal.ads.zzfm
        @Override // com.google.android.gms.internal.ads.zzfsg
        public final boolean zza(Object obj) {
            String str = (String) obj;
            if (str == null) {
                return false;
            }
            String zza2 = zzfrm.zza(str);
            if (TextUtils.isEmpty(zza2)) {
                return false;
            }
            return ((zza2.contains("text") && !zza2.contains(MimeTypes.TEXT_VTT)) || zza2.contains(OSInAppMessageContentKt.HTML) || zza2.contains("xml")) ? false : true;
        }
    };

    @Override // 
    Map zze();
}
