package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.p009io.NumberInput;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 262144;
    static final int MIN_SEGMENT_LEN = 1000;
    static final char[] NO_CHARS = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    public void releaseBuffers() {
        if (this._allocator == null) {
            resetWithEmpty();
        } else if (this._currentSegment != null) {
            resetWithEmpty();
            char[] cArr = this._currentSegment;
            this._currentSegment = null;
            this._allocator.releaseCharBuffer(2, cArr);
        }
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int r3, int r4) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = r3;
        this._inputLen = r4;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithCopy(char[] cArr, int r4, int r5) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(r5);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, r4, r5);
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    private char[] buf(int r3) {
        BufferRecycler bufferRecycler = this._allocator;
        if (bufferRecycler != null) {
            return bufferRecycler.allocCharBuffer(2, r3);
        }
        return new char[Math.max(r3, 1000)];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this._resultString;
        if (str != null) {
            return str.length();
        }
        return this._segmentSize + this._currentSize;
    }

    public int getTextOffset() {
        int r0 = this._inputStart;
        if (r0 >= 0) {
            return r0;
        }
        return 0;
    }

    public boolean hasTextAsCharacters() {
        return this._inputStart >= 0 || this._resultArray != null || this._resultString == null;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        String str = this._resultString;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this._resultArray = charArray;
            return charArray;
        } else if (!this._hasSegments) {
            char[] cArr2 = this._currentSegment;
            return cArr2 == null ? NO_CHARS : cArr2;
        } else {
            return contentsAsArray();
        }
    }

    public String contentsAsString() {
        if (this._resultString == null) {
            char[] cArr = this._resultArray;
            if (cArr != null) {
                this._resultString = new String(cArr);
            } else {
                int r0 = this._inputStart;
                if (r0 >= 0) {
                    int r2 = this._inputLen;
                    if (r2 < 1) {
                        this._resultString = "";
                        return "";
                    }
                    this._resultString = new String(this._inputBuffer, r0, r2);
                } else {
                    int r02 = this._segmentSize;
                    int r22 = this._currentSize;
                    if (r02 == 0) {
                        this._resultString = r22 != 0 ? new String(this._currentSegment, 0, r22) : "";
                    } else {
                        StringBuilder sb = new StringBuilder(r02 + r22);
                        ArrayList<char[]> arrayList = this._segments;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int r23 = 0; r23 < size; r23++) {
                                char[] cArr2 = this._segments.get(r23);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this._currentSegment, 0, this._currentSize);
                        this._resultString = sb.toString();
                    }
                }
            }
        }
        return this._resultString;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr == null) {
            char[] resultArray = resultArray();
            this._resultArray = resultArray;
            return resultArray;
        }
        return cArr;
    }

    public BigDecimal contentsAsDecimal() throws NumberFormatException {
        char[] cArr;
        char[] cArr2;
        char[] cArr3 = this._resultArray;
        if (cArr3 != null) {
            return NumberInput.parseBigDecimal(cArr3);
        }
        int r0 = this._inputStart;
        if (r0 >= 0 && (cArr2 = this._inputBuffer) != null) {
            return NumberInput.parseBigDecimal(cArr2, r0, this._inputLen);
        }
        if (this._segmentSize == 0 && (cArr = this._currentSegment) != null) {
            return NumberInput.parseBigDecimal(cArr, 0, this._currentSize);
        }
        return NumberInput.parseBigDecimal(contentsAsArray());
    }

    public double contentsAsDouble() throws NumberFormatException {
        return NumberInput.parseDouble(contentsAsString());
    }

    public int contentsToWriter(Writer writer) throws IOException {
        int r3;
        char[] cArr = this._resultArray;
        if (cArr != null) {
            writer.write(cArr);
            return this._resultArray.length;
        }
        String str = this._resultString;
        if (str != null) {
            writer.write(str);
            return this._resultString.length();
        }
        int r0 = this._inputStart;
        if (r0 >= 0) {
            int r1 = this._inputLen;
            if (r1 > 0) {
                writer.write(this._inputBuffer, r0, r1);
            }
            return r1;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size = arrayList.size();
            r3 = 0;
            for (int r2 = 0; r2 < size; r2++) {
                char[] cArr2 = this._segments.get(r2);
                int length = cArr2.length;
                writer.write(cArr2, 0, length);
                r3 += length;
            }
        } else {
            r3 = 0;
        }
        int r02 = this._currentSize;
        if (r02 > 0) {
            writer.write(this._currentSegment, 0, r02);
            return r3 + r02;
        }
        return r3;
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public void append(char c) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int r1 = this._currentSize;
        this._currentSize = r1 + 1;
        cArr[r1] = c;
    }

    public void append(char[] cArr, int r5, int r6) {
        if (this._inputStart >= 0) {
            unshare(r6);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr2 = this._currentSegment;
        int length = cArr2.length;
        int r2 = this._currentSize;
        int r1 = length - r2;
        if (r1 >= r6) {
            System.arraycopy(cArr, r5, cArr2, r2, r6);
            this._currentSize += r6;
            return;
        }
        if (r1 > 0) {
            System.arraycopy(cArr, r5, cArr2, r2, r1);
            r5 += r1;
            r6 -= r1;
        }
        do {
            expand(r6);
            int min = Math.min(this._currentSegment.length, r6);
            System.arraycopy(cArr, r5, this._currentSegment, 0, min);
            this._currentSize += min;
            r5 += min;
            r6 -= min;
        } while (r6 > 0);
    }

    public void append(String str, int r6, int r7) {
        if (this._inputStart >= 0) {
            unshare(r7);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int r2 = this._currentSize;
        int r1 = length - r2;
        if (r1 >= r7) {
            str.getChars(r6, r6 + r7, cArr, r2);
            this._currentSize += r7;
            return;
        }
        if (r1 > 0) {
            int r3 = r6 + r1;
            str.getChars(r6, r3, cArr, r2);
            r7 -= r1;
            r6 = r3;
        }
        while (true) {
            expand(r7);
            int min = Math.min(this._currentSegment.length, r7);
            int r12 = r6 + min;
            str.getChars(r6, r12, this._currentSegment, 0);
            this._currentSize += min;
            r7 -= min;
            if (r7 <= 0) {
                return;
            }
            r6 = r12;
        }
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = buf(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr == null) {
            char[] buf = buf(0);
            this._currentSegment = buf;
            return buf;
        }
        return cArr;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public void setCurrentLength(int r1) {
        this._currentSize = r1;
    }

    public String setCurrentAndReturn(int r4) {
        this._currentSize = r4;
        if (this._segmentSize > 0) {
            return contentsAsString();
        }
        String str = r4 == 0 ? "" : new String(this._currentSegment, 0, r4);
        this._resultString = str;
        return str;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        this._currentSize = 0;
        int r0 = length + (length >> 1);
        if (r0 < 1000) {
            r0 = 1000;
        } else if (r0 > 262144) {
            r0 = 262144;
        }
        char[] carr = carr(r0);
        this._currentSegment = carr;
        return carr;
    }

    public char[] expandCurrentSegment() {
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int r2 = (length >> 1) + length;
        if (r2 > 262144) {
            r2 = (length >> 2) + length;
        }
        char[] copyOf = Arrays.copyOf(cArr, r2);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public char[] expandCurrentSegment(int r3) {
        char[] cArr = this._currentSegment;
        if (cArr.length >= r3) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, r3);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public String toString() {
        return contentsAsString();
    }

    private void unshare(int r6) {
        int r0 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int r3 = this._inputStart;
        this._inputStart = -1;
        int r62 = r6 + r0;
        char[] cArr2 = this._currentSegment;
        if (cArr2 == null || r62 > cArr2.length) {
            this._currentSegment = buf(r62);
        }
        if (r0 > 0) {
            System.arraycopy(cArr, r3, this._currentSegment, 0, r0);
        }
        this._segmentSize = 0;
        this._currentSize = r0;
    }

    private void expand(int r3) {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        char[] cArr = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(cArr);
        this._segmentSize += cArr.length;
        this._currentSize = 0;
        int length = cArr.length;
        int r32 = length + (length >> 1);
        if (r32 < 1000) {
            r32 = 1000;
        } else if (r32 > 262144) {
            r32 = 262144;
        }
        this._currentSegment = carr(r32);
    }

    private char[] resultArray() {
        int r4;
        String str = this._resultString;
        if (str != null) {
            return str.toCharArray();
        }
        int r0 = this._inputStart;
        if (r0 >= 0) {
            int r2 = this._inputLen;
            if (r2 < 1) {
                return NO_CHARS;
            }
            if (r0 == 0) {
                return Arrays.copyOf(this._inputBuffer, r2);
            }
            return Arrays.copyOfRange(this._inputBuffer, r0, r2 + r0);
        }
        int size = size();
        if (size < 1) {
            return NO_CHARS;
        }
        char[] carr = carr(size);
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size2 = arrayList.size();
            r4 = 0;
            for (int r3 = 0; r3 < size2; r3++) {
                char[] cArr = this._segments.get(r3);
                int length = cArr.length;
                System.arraycopy(cArr, 0, carr, r4, length);
                r4 += length;
            }
        } else {
            r4 = 0;
        }
        System.arraycopy(this._currentSegment, 0, carr, r4, this._currentSize);
        return carr;
    }

    private char[] carr(int r1) {
        return new char[r1];
    }
}
