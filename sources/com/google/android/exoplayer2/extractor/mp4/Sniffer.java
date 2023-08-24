package com.google.android.exoplayer2.extractor.mp4;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

/* loaded from: classes2.dex */
final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, false, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        boolean z3;
        long length = extractorInput.getLength();
        long j = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        long j2 = -1;
        int r7 = (length > (-1L) ? 1 : (length == (-1L) ? 0 : -1));
        if (r7 != 0 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j = length;
        }
        int r4 = (int) j;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z4 = false;
        int r9 = 0;
        boolean z5 = false;
        while (r9 < r4) {
            parsableByteArray.reset(8);
            if (!extractorInput.peekFully(parsableByteArray.getData(), z4 ? 1 : 0, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int r11 = 16;
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.getData(), 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j2) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + 8;
                    }
                }
                r11 = 8;
            }
            long j3 = r11;
            if (readUnsignedInt < j3) {
                return z4;
            }
            r9 += r11;
            if (readInt == 1836019574) {
                r4 += (int) readUnsignedInt;
                if (r7 != 0 && r4 > length) {
                    r4 = (int) length;
                }
                j2 = -1;
            } else if (readInt == 1836019558 || readInt == 1836475768) {
                z3 = true;
                break;
            } else {
                long j4 = length;
                if ((r9 + readUnsignedInt) - j3 >= r4) {
                    break;
                }
                int r1 = (int) (readUnsignedInt - j3);
                r9 += r1;
                if (readInt == 1718909296) {
                    if (r1 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(r1);
                    extractorInput.peekFully(parsableByteArray.getData(), 0, r1);
                    int r12 = r1 / 4;
                    int r2 = 0;
                    while (true) {
                        if (r2 >= r12) {
                            break;
                        }
                        if (r2 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt(), z2)) {
                            z5 = true;
                            break;
                        }
                        r2++;
                    }
                    if (!z5) {
                        return false;
                    }
                } else if (r1 != 0) {
                    extractorInput.advancePeekPosition(r1);
                }
                length = j4;
                j2 = -1;
                z4 = false;
            }
        }
        z3 = false;
        return z5 && z == z3;
    }

    private static boolean isCompatibleBrand(int r5, boolean z) {
        if ((r5 >>> 8) == 3368816) {
            return true;
        }
        if (r5 == 1751476579 && z) {
            return true;
        }
        for (int r4 : COMPATIBLE_BRANDS) {
            if (r4 == r5) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
