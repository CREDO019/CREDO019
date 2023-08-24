package org.apache.commons.p028io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.p028io.input.ObservableInputStream;

/* renamed from: org.apache.commons.io.input.MessageDigestCalculatingInputStream */
/* loaded from: classes5.dex */
public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    /* renamed from: org.apache.commons.io.input.MessageDigestCalculatingInputStream$MessageDigestMaintainingObserver */
    /* loaded from: classes5.dex */
    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {

        /* renamed from: md */
        private final MessageDigest f1565md;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.f1565md = messageDigest;
        }

        @Override // org.apache.commons.p028io.input.ObservableInputStream.Observer
        void data(int r2) throws IOException {
            this.f1565md.update((byte) r2);
        }

        @Override // org.apache.commons.p028io.input.ObservableInputStream.Observer
        void data(byte[] bArr, int r3, int r4) throws IOException {
            this.f1565md.update(bArr, r3, r4);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream);
        this.messageDigest = messageDigest;
        add(new MessageDigestMaintainingObserver(messageDigest));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, String str) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(str));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
