package com.google.android.gms.internal.clearcut;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
abstract class zzfg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int position = byteBuffer.position();
        int r2 = 0;
        while (r2 < length) {
            try {
                char charAt = charSequence.charAt(r2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + r2, (byte) charAt);
                r2++;
            } catch (IndexOutOfBoundsException unused) {
                char charAt2 = charSequence.charAt(r2);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1));
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (r2 == length) {
            byteBuffer.position(position + r2);
            return;
        }
        position += r2;
        while (r2 < length) {
            char charAt3 = charSequence.charAt(r2);
            if (charAt3 < 128) {
                byteBuffer.put(position, (byte) charAt3);
            } else if (charAt3 < 2048) {
                int r5 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> 6) | 192));
                    byteBuffer.put(r5, (byte) ((charAt3 & '?') | 128));
                    position = r5;
                } catch (IndexOutOfBoundsException unused2) {
                    position = r5;
                    char charAt22 = charSequence.charAt(r2);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(charAt22);
                    sb2.append(" at index ");
                    sb2.append(byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1));
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else if (charAt3 >= 55296 && 57343 >= charAt3) {
                int r52 = r2 + 1;
                if (r52 != length) {
                    try {
                        char charAt4 = charSequence.charAt(r52);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            int r4 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                int r1 = r4 + 1;
                                byteBuffer.put(r4, (byte) (((codePoint >>> 12) & 63) | 128));
                                int r42 = r1 + 1;
                                byteBuffer.put(r1, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(r42, (byte) ((codePoint & 63) | 128));
                                position = r42;
                                r2 = r52;
                            } catch (IndexOutOfBoundsException unused3) {
                                position = r4;
                                r2 = r52;
                                char charAt222 = charSequence.charAt(r2);
                                StringBuilder sb22 = new StringBuilder(37);
                                sb22.append("Failed writing ");
                                sb22.append(charAt222);
                                sb22.append(" at index ");
                                sb22.append(byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1));
                                throw new ArrayIndexOutOfBoundsException(sb22.toString());
                            }
                        } else {
                            r2 = r52;
                        }
                    } catch (IndexOutOfBoundsException unused4) {
                    }
                }
                throw new zzfi(r2, length);
            } else {
                int r53 = position + 1;
                byteBuffer.put(position, (byte) ((charAt3 >>> '\f') | 224));
                position = r53 + 1;
                byteBuffer.put(r53, (byte) (((charAt3 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt3 & '?') | 128));
            }
            r2++;
            position++;
        }
        byteBuffer.position(position);
    }

    abstract int zzb(int r1, byte[] bArr, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zze(byte[] bArr, int r3, int r4) {
        return zzb(0, bArr, r3, r4) == 0;
    }
}
