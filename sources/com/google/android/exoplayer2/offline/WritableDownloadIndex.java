package com.google.android.exoplayer2.offline;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface WritableDownloadIndex extends DownloadIndex {
    void putDownload(Download download) throws IOException;

    void removeDownload(String str) throws IOException;

    void setDownloadingStatesToQueued() throws IOException;

    void setStatesToRemoving() throws IOException;

    void setStopReason(int r1) throws IOException;

    void setStopReason(String str, int r2) throws IOException;
}
