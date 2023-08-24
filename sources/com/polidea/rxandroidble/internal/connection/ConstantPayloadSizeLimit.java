package com.polidea.rxandroidble.internal.connection;

/* loaded from: classes3.dex */
class ConstantPayloadSizeLimit implements PayloadSizeLimitProvider {
    private final int limit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPayloadSizeLimit(int r1) {
        this.limit = r1;
    }

    @Override // com.polidea.rxandroidble.internal.connection.PayloadSizeLimitProvider
    public int getPayloadSizeLimit() {
        return this.limit;
    }
}
