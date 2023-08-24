package org.bouncycastle.crypto.util;

import androidx.core.view.MotionEventCompat;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AlphabetMapper;

/* loaded from: classes5.dex */
public class BasicAlphabetMapper implements AlphabetMapper {
    private Map<Integer, Character> charMap;
    private Map<Character, Integer> indexMap;

    public BasicAlphabetMapper(String str) {
        this(str.toCharArray());
    }

    public BasicAlphabetMapper(char[] cArr) {
        this.indexMap = new HashMap();
        this.charMap = new HashMap();
        for (int r0 = 0; r0 != cArr.length; r0++) {
            if (this.indexMap.containsKey(Character.valueOf(cArr[r0]))) {
                throw new IllegalArgumentException("duplicate key detected in alphabet: " + cArr[r0]);
            }
            this.indexMap.put(Character.valueOf(cArr[r0]), Integer.valueOf(r0));
            this.charMap.put(Integer.valueOf(r0), Character.valueOf(cArr[r0]));
        }
    }

    @Override // org.bouncycastle.crypto.AlphabetMapper
    public char[] convertToChars(byte[] bArr) {
        char[] cArr;
        int r1 = 0;
        if (this.charMap.size() <= 256) {
            cArr = new char[bArr.length];
            while (r1 != bArr.length) {
                cArr[r1] = this.charMap.get(Integer.valueOf(bArr[r1] & 255)).charValue();
                r1++;
            }
        } else if ((bArr.length & 1) != 0) {
            throw new IllegalArgumentException("two byte radix and input string odd length");
        } else {
            cArr = new char[bArr.length / 2];
            while (r1 != bArr.length) {
                cArr[r1 / 2] = this.charMap.get(Integer.valueOf(((bArr[r1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr[r1 + 1] & 255))).charValue();
                r1 += 2;
            }
        }
        return cArr;
    }

    @Override // org.bouncycastle.crypto.AlphabetMapper
    public byte[] convertToIndexes(char[] cArr) {
        byte[] bArr;
        int r1 = 0;
        if (this.indexMap.size() <= 256) {
            bArr = new byte[cArr.length];
            while (r1 != cArr.length) {
                bArr[r1] = this.indexMap.get(Character.valueOf(cArr[r1])).byteValue();
                r1++;
            }
        } else {
            bArr = new byte[cArr.length * 2];
            while (r1 != cArr.length) {
                int intValue = this.indexMap.get(Character.valueOf(cArr[r1])).intValue();
                int r3 = r1 * 2;
                bArr[r3] = (byte) ((intValue >> 8) & 255);
                bArr[r3 + 1] = (byte) (intValue & 255);
                r1++;
            }
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.AlphabetMapper
    public int getRadix() {
        return this.indexMap.size();
    }
}
