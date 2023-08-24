package com.google.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class AdRequest {
    public static final String LOGTAG = "Ads";
    public static final String TEST_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final String VERSION = "0.0.0";

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public enum ErrorCode {
        INVALID_REQUEST("Invalid Ad request."),
        NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory."),
        NETWORK_ERROR("A network error occurred."),
        INTERNAL_ERROR("There was an internal error.");
        
        private final String zzb;

        ErrorCode(String str) {
            this.zzb = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.zzb;
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }

    private AdRequest() {
    }
}
