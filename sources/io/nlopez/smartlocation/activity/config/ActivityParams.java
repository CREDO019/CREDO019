package io.nlopez.smartlocation.activity.config;

/* loaded from: classes5.dex */
public class ActivityParams {
    public static final ActivityParams NORMAL = new Builder().setInterval(500).build();
    private long interval;

    ActivityParams(long j) {
        this.interval = j;
    }

    public long getInterval() {
        return this.interval;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ActivityParams) && this.interval == ((ActivityParams) obj).interval;
    }

    public int hashCode() {
        long j = this.interval;
        return (int) (j ^ (j >>> 32));
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        private long interval;

        public Builder setInterval(long j) {
            this.interval = j;
            return this;
        }

        public ActivityParams build() {
            return new ActivityParams(this.interval);
        }
    }
}
