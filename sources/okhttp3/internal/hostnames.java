package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Buffer;
import org.apache.logging.log4j.message.ParameterizedMessage;

@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002\u001a\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u0003¨\u0006\u000f"}, m183d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okhttp3.internal.HostnamesKt */
/* loaded from: classes5.dex */
public final class hostnames {
    public static final String toCanonicalHost(String toCanonicalHost) {
        InetAddress decodeIpv6;
        Intrinsics.checkNotNullParameter(toCanonicalHost, "$this$toCanonicalHost");
        if (StringsKt.contains$default((CharSequence) toCanonicalHost, (CharSequence) ParameterizedMessage.ERROR_MSG_SEPARATOR, false, 2, (Object) null)) {
            if (StringsKt.startsWith$default(toCanonicalHost, "[", false, 2, (Object) null) && StringsKt.endsWith$default(toCanonicalHost, "]", false, 2, (Object) null)) {
                decodeIpv6 = decodeIpv6(toCanonicalHost, 1, toCanonicalHost.length() - 1);
            } else {
                decodeIpv6 = decodeIpv6(toCanonicalHost, 0, toCanonicalHost.length());
            }
            if (decodeIpv6 != null) {
                byte[] address = decodeIpv6.getAddress();
                if (address.length == 16) {
                    Intrinsics.checkNotNullExpressionValue(address, "address");
                    return inet6AddressToAscii(address);
                } else if (address.length == 4) {
                    return decodeIpv6.getHostAddress();
                } else {
                    throw new AssertionError("Invalid IPv6 address: '" + toCanonicalHost + '\'');
                }
            }
            return null;
        }
        try {
            String str = IDN.toASCII(toCanonicalHost);
            Intrinsics.checkNotNullExpressionValue(str, "IDN.toASCII(host)");
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            if (!(lowerCase.length() == 0) && !containsInvalidHostnameAsciiCodes(lowerCase)) {
                return lowerCase;
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static final boolean containsInvalidHostnameAsciiCodes(String str) {
        int length = str.length();
        for (int r2 = 0; r2 < length; r2++) {
            char charAt = str.charAt(r2);
            if (Intrinsics.compare((int) charAt, 31) <= 0 || Intrinsics.compare((int) charAt, 127) >= 0 || StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", charAt, 0, false, 6, (Object) null) != -1) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
        if (r13 == 16) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0099, code lost:
        if (r14 != (-1)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
        r0 = r13 - r14;
        java.lang.System.arraycopy(r9, r14, r9, 16 - r0, r0);
        java.util.Arrays.fill(r9, r14, (16 - r13) + r14, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ae, code lost:
        return java.net.InetAddress.getByAddress(r9);
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.net.InetAddress decodeIpv6(java.lang.String r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 175
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.hostnames.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
    }

    private static final boolean decodeIpv4Suffix(String str, int r8, int r9, byte[] bArr, int r11) {
        int r0 = r11;
        while (r8 < r9) {
            if (r0 == bArr.length) {
                return false;
            }
            if (r0 != r11) {
                if (str.charAt(r8) != '.') {
                    return false;
                }
                r8++;
            }
            int r2 = r8;
            int r3 = 0;
            while (r2 < r9) {
                char charAt = str.charAt(r2);
                if (Intrinsics.compare((int) charAt, 48) < 0 || Intrinsics.compare((int) charAt, 57) > 0) {
                    break;
                } else if ((r3 == 0 && r8 != r2) || (r3 = ((r3 * 10) + charAt) - 48) > 255) {
                    return false;
                } else {
                    r2++;
                }
            }
            if (r2 - r8 == 0) {
                return false;
            }
            bArr[r0] = (byte) r3;
            r0++;
            r8 = r2;
        }
        return r0 == r11 + 4;
    }

    private static final String inet6AddressToAscii(byte[] bArr) {
        int r0 = 0;
        int r1 = -1;
        int r2 = 0;
        int r3 = 0;
        while (r2 < bArr.length) {
            int r4 = r2;
            while (r4 < 16 && bArr[r4] == 0 && bArr[r4 + 1] == 0) {
                r4 += 2;
            }
            int r5 = r4 - r2;
            if (r5 > r3 && r5 >= 4) {
                r1 = r2;
                r3 = r5;
            }
            r2 = r4 + 2;
        }
        Buffer buffer = new Buffer();
        while (r0 < bArr.length) {
            if (r0 == r1) {
                buffer.writeByte(58);
                r0 += r3;
                if (r0 == 16) {
                    buffer.writeByte(58);
                }
            } else {
                if (r0 > 0) {
                    buffer.writeByte(58);
                }
                buffer.writeHexadecimalUnsignedLong((Util.and(bArr[r0], 255) << 8) | Util.and(bArr[r0 + 1], 255));
                r0 += 2;
            }
        }
        return buffer.readUtf8();
    }
}
