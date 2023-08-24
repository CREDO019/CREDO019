package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzhc extends IOException {
    private zzic zzxh;

    public zzhc(String str) {
        super(str);
        this.zzxh = null;
    }

    public final zzhc zzg(zzic zzicVar) {
        this.zzxh = zzicVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgm() {
        return new zzhc("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgn() {
        return new zzhc("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgo() {
        return new zzhc("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgp() {
        return new zzhc("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgq() {
        return new zzhc("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhb zzgr() {
        return new zzhb("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgs() {
        return new zzhc("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhc zzgt() {
        return new zzhc("Protocol message had invalid UTF-8.");
    }
}
