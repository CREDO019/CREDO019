package com.facebook.systrace;

/* loaded from: classes.dex */
public final class SystraceMessage {
    private static final Builder NOOP_BUILDER = new NoopBuilder();

    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract Builder arg(String str, double d);

        public abstract Builder arg(String str, int r2);

        public abstract Builder arg(String str, long j);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    /* loaded from: classes.dex */
    private interface Flusher {
        void flush(StringBuilder sb);
    }

    public static Builder beginSection(long j, String str) {
        return NOOP_BUILDER;
    }

    public static Builder endSection(long j) {
        return NOOP_BUILDER;
    }

    /* loaded from: classes.dex */
    private static class NoopBuilder extends Builder {
        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, double d) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, int r2) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, long j) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public Builder arg(String str, Object obj) {
            return this;
        }

        @Override // com.facebook.systrace.SystraceMessage.Builder
        public void flush() {
        }

        private NoopBuilder() {
        }
    }
}
