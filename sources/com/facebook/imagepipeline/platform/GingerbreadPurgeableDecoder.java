package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;
    @Nullable
    private final WebpBitmapFactory mWebpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, bytesRef.get().size(), null, options);
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int length, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(bytesRef, length, endsWithEOI(bytesRef, length) ? null : EOI, options);
    }

    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix) throws IOException {
        OutputStream outputStream;
        LimitedInputStream limitedInputStream;
        PooledByteBufferInputStream pooledByteBufferInputStream = null;
        OutputStream outputStream2 = null;
        MemoryFile memoryFile = new MemoryFile(null, (suffix == null ? 0 : suffix.length) + inputLength);
        memoryFile.allowPurging(false);
        try {
            PooledByteBufferInputStream pooledByteBufferInputStream2 = new PooledByteBufferInputStream(bytesRef.get());
            try {
                limitedInputStream = new LimitedInputStream(pooledByteBufferInputStream2, inputLength);
            } catch (Throwable th) {
                th = th;
                outputStream = null;
                limitedInputStream = null;
            }
            try {
                outputStream2 = memoryFile.getOutputStream();
                ByteStreams.copy(limitedInputStream, outputStream2);
                if (suffix != null) {
                    memoryFile.writeBytes(suffix, 0, inputLength, suffix.length);
                }
                CloseableReference.closeSafely(bytesRef);
                Closeables.closeQuietly(pooledByteBufferInputStream2);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.close(outputStream2, true);
                return memoryFile;
            } catch (Throwable th2) {
                th = th2;
                outputStream = outputStream2;
                pooledByteBufferInputStream = pooledByteBufferInputStream2;
                CloseableReference.closeSafely(bytesRef);
                Closeables.closeQuietly(pooledByteBufferInputStream);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.close(outputStream, true);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            limitedInputStream = null;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) Preconditions.checkNotNull(getFileDescriptorMethod().invoke(memoryFile, new Object[0]));
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef, int inputLength, @Nullable byte[] suffix, BitmapFactory.Options options) {
        MemoryFile memoryFile = null;
        try {
            try {
                MemoryFile copyToMemoryFile = copyToMemoryFile(bytesRef, inputLength, suffix);
                try {
                    FileDescriptor memoryFileDescriptor = getMemoryFileDescriptor(copyToMemoryFile);
                    WebpBitmapFactory webpBitmapFactory = this.mWebpBitmapFactory;
                    if (webpBitmapFactory != null) {
                        Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(webpBitmapFactory.decodeFileDescriptor(memoryFileDescriptor, null, options), "BitmapFactory returned null");
                        if (copyToMemoryFile != null) {
                            copyToMemoryFile.close();
                        }
                        return bitmap;
                    }
                    throw new IllegalStateException("WebpBitmapFactory is null");
                } catch (IOException e) {
                    e = e;
                    memoryFile = copyToMemoryFile;
                    throw Throwables.propagate(e);
                } catch (Throwable th) {
                    th = th;
                    memoryFile = copyToMemoryFile;
                    if (memoryFile != null) {
                        memoryFile.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }
}
