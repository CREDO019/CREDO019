package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceUtil;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public abstract class DataChunk extends Chunk {
    private static final int READ_GRANULARITY = 16384;
    private byte[] data;
    private volatile boolean loadCanceled;

    protected abstract void consume(byte[] bArr, int r2) throws IOException;

    public DataChunk(DataSource dataSource, DataSpec dataSpec, int r14, Format format, int r16, Object obj, byte[] bArr) {
        super(dataSource, dataSpec, r14, format, r16, obj, C1856C.TIME_UNSET, C1856C.TIME_UNSET);
        DataChunk dataChunk;
        byte[] bArr2;
        if (bArr == null) {
            bArr2 = Util.EMPTY_BYTE_ARRAY;
            dataChunk = this;
        } else {
            dataChunk = this;
            bArr2 = bArr;
        }
        dataChunk.data = bArr2;
    }

    public byte[] getDataHolder() {
        return this.data;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void load() throws IOException {
        try {
            this.dataSource.open(this.dataSpec);
            int r0 = 0;
            int r1 = 0;
            while (r0 != -1 && !this.loadCanceled) {
                maybeExpandData(r1);
                r0 = this.dataSource.read(this.data, r1, 16384);
                if (r0 != -1) {
                    r1 += r0;
                }
            }
            if (!this.loadCanceled) {
                consume(this.data, r1);
            }
        } finally {
            DataSourceUtil.closeQuietly(this.dataSource);
        }
    }

    private void maybeExpandData(int r3) {
        byte[] bArr = this.data;
        if (bArr.length < r3 + 16384) {
            this.data = Arrays.copyOf(bArr, bArr.length + 16384);
        }
    }
}
