package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class BaseDataSource implements DataSource {
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners = new ArrayList<>(1);

    @Override // com.google.android.exoplayer2.upstream.DataSource, com.google.android.exoplayer2.upstream.HttpDataSource
    public /* synthetic */ Map getResponseHeaders() {
        Map emptyMap;
        emptyMap = Collections.emptyMap();
        return emptyMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDataSource(boolean z) {
        this.isNetwork = z;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public final void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        if (this.listeners.contains(transferListener)) {
            return;
        }
        this.listeners.add(transferListener);
        this.listenerCount++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void transferInitializing(DataSpec dataSpec) {
        for (int r0 = 0; r0 < this.listenerCount; r0++) {
            this.listeners.get(r0).onTransferInitializing(this, dataSpec, this.isNetwork);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void transferStarted(DataSpec dataSpec) {
        this.dataSpec = dataSpec;
        for (int r0 = 0; r0 < this.listenerCount; r0++) {
            this.listeners.get(r0).onTransferStart(this, dataSpec, this.isNetwork);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void bytesTransferred(int r5) {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int r1 = 0; r1 < this.listenerCount; r1++) {
            this.listeners.get(r1).onBytesTransferred(this, dataSpec, this.isNetwork, r5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void transferEnded() {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int r1 = 0; r1 < this.listenerCount; r1++) {
            this.listeners.get(r1).onTransferEnd(this, dataSpec, this.isNetwork);
        }
        this.dataSpec = null;
    }
}
