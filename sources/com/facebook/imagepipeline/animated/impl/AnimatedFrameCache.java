package com.facebook.imagepipeline.animated.impl;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedFrameCache {
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final CacheKey mImageCacheKey;
    private final LinkedHashSet<CacheKey> mFreeItemsPool = new LinkedHashSet<>();
    private final CountingMemoryCache.EntryStateObserver<CacheKey> mEntryStateObserver = new CountingMemoryCache.EntryStateObserver<CacheKey>() { // from class: com.facebook.imagepipeline.animated.impl.AnimatedFrameCache.1
        @Override // com.facebook.imagepipeline.cache.CountingMemoryCache.EntryStateObserver
        public void onExclusivityChanged(CacheKey key, boolean isExclusive) {
            AnimatedFrameCache.this.onReusabilityChange(key, isExclusive);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FrameKey implements CacheKey {
        private final int mFrameIndex;
        private final CacheKey mImageCacheKey;

        @Override // com.facebook.cache.common.CacheKey
        @Nullable
        public String getUriString() {
            return null;
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean isResourceIdForDebugging() {
            return false;
        }

        public FrameKey(CacheKey imageCacheKey, int frameIndex) {
            this.mImageCacheKey = imageCacheKey;
            this.mFrameIndex = frameIndex;
        }

        @Override // com.facebook.cache.common.CacheKey
        public String toString() {
            return Objects.toStringHelper(this).add("imageCacheKey", this.mImageCacheKey).add("frameIndex", this.mFrameIndex).toString();
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean equals(@Nullable Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof FrameKey) {
                FrameKey frameKey = (FrameKey) o;
                return this.mFrameIndex == frameKey.mFrameIndex && this.mImageCacheKey.equals(frameKey.mImageCacheKey);
            }
            return false;
        }

        @Override // com.facebook.cache.common.CacheKey
        public int hashCode() {
            return (this.mImageCacheKey.hashCode() * 1013) + this.mFrameIndex;
        }

        @Override // com.facebook.cache.common.CacheKey
        public boolean containsUri(Uri uri) {
            return this.mImageCacheKey.containsUri(uri);
        }
    }

    public AnimatedFrameCache(CacheKey imageCacheKey, final CountingMemoryCache<CacheKey, CloseableImage> backingCache) {
        this.mImageCacheKey = imageCacheKey;
        this.mBackingCache = backingCache;
    }

    public synchronized void onReusabilityChange(CacheKey key, boolean isReusable) {
        if (isReusable) {
            this.mFreeItemsPool.add(key);
        } else {
            this.mFreeItemsPool.remove(key);
        }
    }

    @Nullable
    public CloseableReference<CloseableImage> cache(int frameIndex, CloseableReference<CloseableImage> imageRef) {
        return this.mBackingCache.cache(keyFor(frameIndex), imageRef, this.mEntryStateObserver);
    }

    @Nullable
    public CloseableReference<CloseableImage> get(int frameIndex) {
        return this.mBackingCache.get(keyFor(frameIndex));
    }

    public boolean contains(int frameIndex) {
        return this.mBackingCache.contains((CountingMemoryCache<CacheKey, CloseableImage>) keyFor(frameIndex));
    }

    @Nullable
    public CloseableReference<CloseableImage> getForReuse() {
        CloseableReference<CloseableImage> reuse;
        do {
            CacheKey popFirstFreeItemKey = popFirstFreeItemKey();
            if (popFirstFreeItemKey == null) {
                return null;
            }
            reuse = this.mBackingCache.reuse(popFirstFreeItemKey);
        } while (reuse == null);
        return reuse;
    }

    @Nullable
    private synchronized CacheKey popFirstFreeItemKey() {
        CacheKey cacheKey;
        cacheKey = null;
        Iterator<CacheKey> it = this.mFreeItemsPool.iterator();
        if (it.hasNext()) {
            cacheKey = it.next();
            it.remove();
        }
        return cacheKey;
    }

    private FrameKey keyFor(int frameIndex) {
        return new FrameKey(this.mImageCacheKey, frameIndex);
    }
}
