package com.RNFetchBlob;

/* loaded from: classes.dex */
public class RNFetchBlobProgressConfig {
    private int count;
    private boolean enable;
    private int interval;
    private long lastTick = 0;
    private int tick = 0;
    private ReportType type;

    /* loaded from: classes.dex */
    enum ReportType {
        Upload,
        Download
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobProgressConfig(boolean z, int r4, int r5, ReportType reportType) {
        this.count = -1;
        this.interval = -1;
        this.enable = false;
        ReportType reportType2 = ReportType.Download;
        this.enable = z;
        this.interval = r4;
        this.type = reportType;
        this.count = r5;
    }

    public boolean shouldReport(float f) {
        int r0 = this.count;
        boolean z = false;
        boolean z2 = r0 <= 0 || f <= 0.0f || Math.floor((double) (f * ((float) r0))) > ((double) this.tick);
        if (System.currentTimeMillis() - this.lastTick > this.interval && this.enable && z2) {
            z = true;
        }
        if (z) {
            this.tick++;
            this.lastTick = System.currentTimeMillis();
        }
        return z;
    }
}
