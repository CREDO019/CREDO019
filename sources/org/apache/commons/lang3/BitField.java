package org.apache.commons.lang3;

/* loaded from: classes5.dex */
public class BitField {
    private final int _mask;
    private final int _shift_count;

    public BitField(int r1) {
        this._mask = r1;
        this._shift_count = r1 == 0 ? 0 : Integer.numberOfTrailingZeros(r1);
    }

    public int getValue(int r2) {
        return getRawValue(r2) >> this._shift_count;
    }

    public short getShortValue(short s) {
        return (short) getValue(s);
    }

    public int getRawValue(int r2) {
        return r2 & this._mask;
    }

    public short getShortRawValue(short s) {
        return (short) getRawValue(s);
    }

    public boolean isSet(int r2) {
        return (r2 & this._mask) != 0;
    }

    public boolean isAllSet(int r2) {
        int r0 = this._mask;
        return (r2 & r0) == r0;
    }

    public int setValue(int r3, int r4) {
        int r0 = this._mask;
        return (r3 & (~r0)) | ((r4 << this._shift_count) & r0);
    }

    public short setShortValue(short s, short s2) {
        return (short) setValue(s, s2);
    }

    public int clear(int r2) {
        return r2 & (~this._mask);
    }

    public short clearShort(short s) {
        return (short) clear(s);
    }

    public byte clearByte(byte b) {
        return (byte) clear(b);
    }

    public int set(int r2) {
        return r2 | this._mask;
    }

    public short setShort(short s) {
        return (short) set(s);
    }

    public byte setByte(byte b) {
        return (byte) set(b);
    }

    public int setBoolean(int r1, boolean z) {
        return z ? set(r1) : clear(r1);
    }

    public short setShortBoolean(short s, boolean z) {
        return z ? setShort(s) : clearShort(s);
    }

    public byte setByteBoolean(byte b, boolean z) {
        return z ? setByte(b) : clearByte(b);
    }
}
