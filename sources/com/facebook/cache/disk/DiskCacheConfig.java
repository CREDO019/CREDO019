package com.facebook.cache.disk;

import android.content.Context;
import android.support.p001v4.media.session.PlaybackStateCompat;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import java.io.File;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    @Nullable
    private final Context mContext;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;

    protected DiskCacheConfig(Builder builder) {
        CacheErrorLogger cacheErrorLogger;
        CacheEventListener cacheEventListener;
        DiskTrimmableRegistry diskTrimmableRegistry;
        Context context = builder.mContext;
        this.mContext = context;
        Preconditions.checkState((builder.mBaseDirectoryPathSupplier == null && context == null) ? false : true, "Either a non-null context or a base directory path or supplier must be provided.");
        if (builder.mBaseDirectoryPathSupplier == null && context != null) {
            builder.mBaseDirectoryPathSupplier = new Supplier<File>() { // from class: com.facebook.cache.disk.DiskCacheConfig.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.common.internal.Supplier
                public File get() {
                    Preconditions.checkNotNull(DiskCacheConfig.this.mContext);
                    return DiskCacheConfig.this.mContext.getApplicationContext().getCacheDir();
                }
            };
        }
        this.mVersion = builder.mVersion;
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(builder.mBaseDirectoryName);
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(builder.mBaseDirectoryPathSupplier);
        this.mDefaultSizeLimit = builder.mMaxCacheSize;
        this.mLowDiskSpaceSizeLimit = builder.mMaxCacheSizeOnLowDiskSpace;
        this.mMinimumSizeLimit = builder.mMaxCacheSizeOnVeryLowDiskSpace;
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(builder.mEntryEvictionComparatorSupplier);
        if (builder.mCacheErrorLogger == null) {
            cacheErrorLogger = NoOpCacheErrorLogger.getInstance();
        } else {
            cacheErrorLogger = builder.mCacheErrorLogger;
        }
        this.mCacheErrorLogger = cacheErrorLogger;
        if (builder.mCacheEventListener == null) {
            cacheEventListener = NoOpCacheEventListener.getInstance();
        } else {
            cacheEventListener = builder.mCacheEventListener;
        }
        this.mCacheEventListener = cacheEventListener;
        if (builder.mDiskTrimmableRegistry == null) {
            diskTrimmableRegistry = NoOpDiskTrimmableRegistry.getInstance();
        } else {
            diskTrimmableRegistry = builder.mDiskTrimmableRegistry;
        }
        this.mDiskTrimmableRegistry = diskTrimmableRegistry;
        this.mIndexPopulateAtStartupEnabled = builder.mIndexPopulateAtStartupEnabled;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    @Nullable
    public Context getContext() {
        return this.mContext;
    }

    public boolean getIndexPopulateAtStartupEnabled() {
        return this.mIndexPopulateAtStartupEnabled;
    }

    public static Builder newBuilder(@Nullable Context context) {
        return new Builder(context);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String mBaseDirectoryName;
        @Nullable
        private Supplier<File> mBaseDirectoryPathSupplier;
        @Nullable
        private CacheErrorLogger mCacheErrorLogger;
        @Nullable
        private CacheEventListener mCacheEventListener;
        @Nullable
        private final Context mContext;
        @Nullable
        private DiskTrimmableRegistry mDiskTrimmableRegistry;
        private EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
        private boolean mIndexPopulateAtStartupEnabled;
        private long mMaxCacheSize;
        private long mMaxCacheSizeOnLowDiskSpace;
        private long mMaxCacheSizeOnVeryLowDiskSpace;
        private int mVersion;

        private Builder(@Nullable Context context) {
            this.mVersion = 1;
            this.mBaseDirectoryName = "image_cache";
            this.mMaxCacheSize = 41943040L;
            this.mMaxCacheSizeOnLowDiskSpace = 10485760L;
            this.mMaxCacheSizeOnVeryLowDiskSpace = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
            this.mContext = context;
        }

        public Builder setVersion(int version) {
            this.mVersion = version;
            return this;
        }

        public Builder setBaseDirectoryName(String baseDirectoryName) {
            this.mBaseDirectoryName = baseDirectoryName;
            return this;
        }

        public Builder setBaseDirectoryPath(final File baseDirectoryPath) {
            this.mBaseDirectoryPathSupplier = Suppliers.m1349of(baseDirectoryPath);
            return this;
        }

        public Builder setBaseDirectoryPathSupplier(Supplier<File> baseDirectoryPathSupplier) {
            this.mBaseDirectoryPathSupplier = baseDirectoryPathSupplier;
            return this;
        }

        public Builder setMaxCacheSize(long maxCacheSize) {
            this.mMaxCacheSize = maxCacheSize;
            return this;
        }

        public Builder setMaxCacheSizeOnLowDiskSpace(long maxCacheSizeOnLowDiskSpace) {
            this.mMaxCacheSizeOnLowDiskSpace = maxCacheSizeOnLowDiskSpace;
            return this;
        }

        public Builder setMaxCacheSizeOnVeryLowDiskSpace(long maxCacheSizeOnVeryLowDiskSpace) {
            this.mMaxCacheSizeOnVeryLowDiskSpace = maxCacheSizeOnVeryLowDiskSpace;
            return this;
        }

        public Builder setEntryEvictionComparatorSupplier(EntryEvictionComparatorSupplier supplier) {
            this.mEntryEvictionComparatorSupplier = supplier;
            return this;
        }

        public Builder setCacheErrorLogger(CacheErrorLogger cacheErrorLogger) {
            this.mCacheErrorLogger = cacheErrorLogger;
            return this;
        }

        public Builder setCacheEventListener(CacheEventListener cacheEventListener) {
            this.mCacheEventListener = cacheEventListener;
            return this;
        }

        public Builder setDiskTrimmableRegistry(DiskTrimmableRegistry diskTrimmableRegistry) {
            this.mDiskTrimmableRegistry = diskTrimmableRegistry;
            return this;
        }

        public Builder setIndexPopulateAtStartupEnabled(boolean indexEnabled) {
            this.mIndexPopulateAtStartupEnabled = indexEnabled;
            return this;
        }

        public DiskCacheConfig build() {
            return new DiskCacheConfig(this);
        }
    }
}
