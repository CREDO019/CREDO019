package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public interface EbmlProcessor {
    public static final int ELEMENT_TYPE_BINARY = 4;
    public static final int ELEMENT_TYPE_FLOAT = 5;
    public static final int ELEMENT_TYPE_MASTER = 1;
    public static final int ELEMENT_TYPE_STRING = 3;
    public static final int ELEMENT_TYPE_UNKNOWN = 0;
    public static final int ELEMENT_TYPE_UNSIGNED_INT = 2;

    @Target({java.lang.annotation.ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ElementType {
    }

    void binaryElement(int r1, int r2, ExtractorInput extractorInput) throws IOException;

    void endMasterElement(int r1) throws ParserException;

    void floatElement(int r1, double d) throws ParserException;

    int getElementType(int r1);

    void integerElement(int r1, long j) throws ParserException;

    boolean isLevel1Element(int r1);

    void startMasterElement(int r1, long j, long j2) throws ParserException;

    void stringElement(int r1, String str) throws ParserException;
}
