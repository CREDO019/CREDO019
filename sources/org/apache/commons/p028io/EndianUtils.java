package org.apache.commons.p028io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* renamed from: org.apache.commons.io.EndianUtils */
/* loaded from: classes5.dex */
public class EndianUtils {
    public static int swapInteger(int r2) {
        return (((r2 >> 0) & 255) << 24) + (((r2 >> 8) & 255) << 16) + (((r2 >> 16) & 255) << 8) + (((r2 >> 24) & 255) << 0);
    }

    public static long swapLong(long j) {
        return (((j >> 0) & 255) << 56) + (((j >> 8) & 255) << 48) + (((j >> 16) & 255) << 40) + (((j >> 24) & 255) << 32) + (((j >> 32) & 255) << 24) + (((j >> 40) & 255) << 16) + (((j >> 48) & 255) << 8) + (((j >> 56) & 255) << 0);
    }

    public static short swapShort(short s) {
        return (short) ((((s >> 0) & 255) << 8) + (((s >> 8) & 255) << 0));
    }

    public static float swapFloat(float f) {
        return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(f)));
    }

    public static double swapDouble(double d) {
        return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(d)));
    }

    public static void writeSwappedShort(byte[] bArr, int r3, short s) {
        bArr[r3 + 0] = (byte) ((s >> 0) & 255);
        bArr[r3 + 1] = (byte) ((s >> 8) & 255);
    }

    public static short readSwappedShort(byte[] bArr, int r2) {
        return (short) (((bArr[r2 + 0] & 255) << 0) + ((bArr[r2 + 1] & 255) << 8));
    }

    public static int readSwappedUnsignedShort(byte[] bArr, int r2) {
        return ((bArr[r2 + 0] & 255) << 0) + ((bArr[r2 + 1] & 255) << 8);
    }

    public static void writeSwappedInteger(byte[] bArr, int r3, int r4) {
        bArr[r3 + 0] = (byte) ((r4 >> 0) & 255);
        bArr[r3 + 1] = (byte) ((r4 >> 8) & 255);
        bArr[r3 + 2] = (byte) ((r4 >> 16) & 255);
        bArr[r3 + 3] = (byte) ((r4 >> 24) & 255);
    }

    public static int readSwappedInteger(byte[] bArr, int r3) {
        return ((bArr[r3 + 0] & 255) << 0) + ((bArr[r3 + 1] & 255) << 8) + ((bArr[r3 + 2] & 255) << 16) + ((bArr[r3 + 3] & 255) << 24);
    }

    public static long readSwappedUnsignedInteger(byte[] bArr, int r5) {
        return ((bArr[r5 + 3] & 255) << 24) + ((((bArr[r5 + 0] & 255) << 0) + ((bArr[r5 + 1] & 255) << 8) + ((bArr[r5 + 2] & 255) << 16)) & BodyPartID.bodyIdMax);
    }

    public static void writeSwappedLong(byte[] bArr, int r6, long j) {
        bArr[r6 + 0] = (byte) ((j >> 0) & 255);
        bArr[r6 + 1] = (byte) ((j >> 8) & 255);
        bArr[r6 + 2] = (byte) ((j >> 16) & 255);
        bArr[r6 + 3] = (byte) ((j >> 24) & 255);
        bArr[r6 + 4] = (byte) ((j >> 32) & 255);
        bArr[r6 + 5] = (byte) ((j >> 40) & 255);
        bArr[r6 + 6] = (byte) ((j >> 48) & 255);
        bArr[r6 + 7] = (byte) ((j >> 56) & 255);
    }

    public static long readSwappedLong(byte[] bArr, int r5) {
        return (readSwappedInteger(bArr, r5 + 4) << 32) + (readSwappedInteger(bArr, r5) & BodyPartID.bodyIdMax);
    }

    public static void writeSwappedFloat(byte[] bArr, int r1, float f) {
        writeSwappedInteger(bArr, r1, Float.floatToIntBits(f));
    }

    public static float readSwappedFloat(byte[] bArr, int r1) {
        return Float.intBitsToFloat(readSwappedInteger(bArr, r1));
    }

    public static void writeSwappedDouble(byte[] bArr, int r1, double d) {
        writeSwappedLong(bArr, r1, Double.doubleToLongBits(d));
    }

    public static double readSwappedDouble(byte[] bArr, int r1) {
        return Double.longBitsToDouble(readSwappedLong(bArr, r1));
    }

    public static void writeSwappedShort(OutputStream outputStream, short s) throws IOException {
        outputStream.write((byte) ((s >> 0) & 255));
        outputStream.write((byte) ((s >> 8) & 255));
    }

    public static short readSwappedShort(InputStream inputStream) throws IOException {
        return (short) (((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8));
    }

    public static int readSwappedUnsignedShort(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8);
    }

    public static void writeSwappedInteger(OutputStream outputStream, int r2) throws IOException {
        outputStream.write((byte) ((r2 >> 0) & 255));
        outputStream.write((byte) ((r2 >> 8) & 255));
        outputStream.write((byte) ((r2 >> 16) & 255));
        outputStream.write((byte) ((r2 >> 24) & 255));
    }

    public static int readSwappedInteger(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8) + ((read(inputStream) & 255) << 16) + ((read(inputStream) & 255) << 24);
    }

    public static long readSwappedUnsignedInteger(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 24) + ((((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8) + ((read(inputStream) & 255) << 16)) & BodyPartID.bodyIdMax);
    }

    public static void writeSwappedLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((j >> 0) & 255));
        outputStream.write((byte) ((j >> 8) & 255));
        outputStream.write((byte) ((j >> 16) & 255));
        outputStream.write((byte) ((j >> 24) & 255));
        outputStream.write((byte) ((j >> 32) & 255));
        outputStream.write((byte) ((j >> 40) & 255));
        outputStream.write((byte) ((j >> 48) & 255));
        outputStream.write((byte) ((j >> 56) & 255));
    }

    public static long readSwappedLong(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8];
        for (int r3 = 0; r3 < 8; r3++) {
            bArr[r3] = (byte) read(inputStream);
        }
        return readSwappedLong(bArr, 0);
    }

    public static void writeSwappedFloat(OutputStream outputStream, float f) throws IOException {
        writeSwappedInteger(outputStream, Float.floatToIntBits(f));
    }

    public static float readSwappedFloat(InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(readSwappedInteger(inputStream));
    }

    public static void writeSwappedDouble(OutputStream outputStream, double d) throws IOException {
        writeSwappedLong(outputStream, Double.doubleToLongBits(d));
    }

    public static double readSwappedDouble(InputStream inputStream) throws IOException {
        return Double.longBitsToDouble(readSwappedLong(inputStream));
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (-1 != read) {
            return read;
        }
        throw new EOFException("Unexpected EOF reached");
    }
}
