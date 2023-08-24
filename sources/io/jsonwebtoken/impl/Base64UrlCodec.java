package io.jsonwebtoken.impl;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes5.dex */
public class Base64UrlCodec extends AbstractTextCodec {
    @Override // io.jsonwebtoken.impl.TextCodec
    public String encode(byte[] bArr) {
        byte[] removePadding = removePadding(TextCodec.BASE64.encode(bArr).getBytes(US_ASCII));
        for (int r0 = 0; r0 < removePadding.length; r0++) {
            if (removePadding[r0] == 43) {
                removePadding[r0] = MultipartStream.DASH;
            } else if (removePadding[r0] == 47) {
                removePadding[r0] = 95;
            }
        }
        return new String(removePadding, US_ASCII);
    }

    protected byte[] removePadding(byte[] bArr) {
        int r2 = 0;
        for (int length = bArr.length - 1; length > 0 && bArr[length] == 61; length--) {
            r2++;
        }
        if (r2 > 0) {
            byte[] bArr2 = new byte[bArr.length - r2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length - r2);
            return bArr2;
        }
        return bArr;
    }

    @Override // io.jsonwebtoken.impl.TextCodec
    public byte[] decode(String str) {
        char[] ensurePadding = ensurePadding(str.toCharArray());
        for (int r0 = 0; r0 < ensurePadding.length; r0++) {
            if (ensurePadding[r0] == '-') {
                ensurePadding[r0] = '+';
            } else if (ensurePadding[r0] == '_') {
                ensurePadding[r0] = IOUtils.DIR_SEPARATOR_UNIX;
            }
        }
        return TextCodec.BASE64.decode(new String(ensurePadding));
    }

    protected char[] ensurePadding(char[] cArr) {
        int length = cArr.length % 4;
        int r0 = (length == 2 || length == 3) ? 4 - length : 0;
        if (r0 > 0) {
            char[] cArr2 = new char[cArr.length + r0];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            for (int r1 = 0; r1 < r0; r1++) {
                cArr2[cArr.length + r1] = '=';
            }
            return cArr2;
        }
        return cArr;
    }
}
