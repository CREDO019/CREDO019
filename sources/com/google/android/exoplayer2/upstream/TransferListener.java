package com.google.android.exoplayer2.upstream;

/* loaded from: classes2.dex */
public interface TransferListener {
    void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int r4);

    void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z);

    void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z);

    void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z);
}
