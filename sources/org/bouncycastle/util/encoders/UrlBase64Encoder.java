package org.bouncycastle.util.encoders;

import org.apache.commons.fileupload.MultipartStream;

/* loaded from: classes4.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = MultipartStream.DASH;
        this.encodingTable[this.encodingTable.length - 1] = 95;
        this.padding = (byte) 46;
        initialiseDecodingTable();
    }
}
