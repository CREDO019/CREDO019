package com.google.android.exoplayer2.source.dash;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.source.chunk.BundledChunkExtractor;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Period;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.List;

/* loaded from: classes2.dex */
public final class DashUtil {
    public static DataSpec buildDataSpec(Representation representation, String str, RangedUri rangedUri, int r5) {
        return new DataSpec.Builder().setUri(rangedUri.resolveUri(str)).setPosition(rangedUri.start).setLength(rangedUri.length).setKey(resolveCacheKey(representation, rangedUri)).setFlags(r5).build();
    }

    public static DataSpec buildDataSpec(Representation representation, RangedUri rangedUri, int r4) {
        return buildDataSpec(representation, representation.baseUrls.get(0).url, rangedUri, r4);
    }

    public static DashManifest loadManifest(DataSource dataSource, Uri uri) throws IOException {
        return (DashManifest) ParsingLoadable.load(dataSource, new DashManifestParser(), uri, 4);
    }

    public static Format loadFormatWithDrmInitData(DataSource dataSource, Period period) throws IOException {
        int r0 = 2;
        Representation firstRepresentation = getFirstRepresentation(period, 2);
        if (firstRepresentation == null) {
            r0 = 1;
            firstRepresentation = getFirstRepresentation(period, 1);
            if (firstRepresentation == null) {
                return null;
            }
        }
        Format format = firstRepresentation.format;
        Format loadSampleFormat = loadSampleFormat(dataSource, r0, firstRepresentation);
        return loadSampleFormat == null ? format : loadSampleFormat.withManifestFormatInfo(format);
    }

    public static Format loadSampleFormat(DataSource dataSource, int r2, Representation representation, int r4) throws IOException {
        if (representation.getInitializationUri() == null) {
            return null;
        }
        ChunkExtractor newChunkExtractor = newChunkExtractor(r2, representation.format);
        try {
            loadInitializationData(newChunkExtractor, dataSource, representation, r4, false);
            newChunkExtractor.release();
            return ((Format[]) Assertions.checkStateNotNull(newChunkExtractor.getSampleFormats()))[0];
        } catch (Throwable th) {
            newChunkExtractor.release();
            throw th;
        }
    }

    public static Format loadSampleFormat(DataSource dataSource, int r2, Representation representation) throws IOException {
        return loadSampleFormat(dataSource, r2, representation, 0);
    }

    public static ChunkIndex loadChunkIndex(DataSource dataSource, int r2, Representation representation, int r4) throws IOException {
        if (representation.getInitializationUri() == null) {
            return null;
        }
        ChunkExtractor newChunkExtractor = newChunkExtractor(r2, representation.format);
        try {
            loadInitializationData(newChunkExtractor, dataSource, representation, r4, true);
            newChunkExtractor.release();
            return newChunkExtractor.getChunkIndex();
        } catch (Throwable th) {
            newChunkExtractor.release();
            throw th;
        }
    }

    public static ChunkIndex loadChunkIndex(DataSource dataSource, int r2, Representation representation) throws IOException {
        return loadChunkIndex(dataSource, r2, representation, 0);
    }

    private static void loadInitializationData(ChunkExtractor chunkExtractor, DataSource dataSource, Representation representation, int r5, boolean z) throws IOException {
        RangedUri rangedUri = (RangedUri) Assertions.checkNotNull(representation.getInitializationUri());
        if (z) {
            RangedUri indexUri = representation.getIndexUri();
            if (indexUri == null) {
                return;
            }
            RangedUri attemptMerge = rangedUri.attemptMerge(indexUri, representation.baseUrls.get(r5).url);
            if (attemptMerge == null) {
                loadInitializationData(dataSource, representation, r5, chunkExtractor, rangedUri);
                rangedUri = indexUri;
            } else {
                rangedUri = attemptMerge;
            }
        }
        loadInitializationData(dataSource, representation, r5, chunkExtractor, rangedUri);
    }

    public static void loadInitializationData(ChunkExtractor chunkExtractor, DataSource dataSource, Representation representation, boolean z) throws IOException {
        loadInitializationData(chunkExtractor, dataSource, representation, 0, z);
    }

    private static void loadInitializationData(DataSource dataSource, Representation representation, int r10, ChunkExtractor chunkExtractor, RangedUri rangedUri) throws IOException {
        new InitializationChunk(dataSource, buildDataSpec(representation, representation.baseUrls.get(r10).url, rangedUri, 0), representation.format, 0, null, chunkExtractor).load();
    }

    public static String resolveCacheKey(Representation representation, RangedUri rangedUri) {
        String cacheKey = representation.getCacheKey();
        return cacheKey != null ? cacheKey : rangedUri.resolveUri(representation.baseUrls.get(0).url).toString();
    }

    private static ChunkExtractor newChunkExtractor(int r2, Format format) {
        String str = format.containerMimeType;
        return new BundledChunkExtractor(str != null && (str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM)) ? new MatroskaExtractor() : new FragmentedMp4Extractor(), r2, format);
    }

    private static Representation getFirstRepresentation(Period period, int r3) {
        int adaptationSetIndex = period.getAdaptationSetIndex(r3);
        if (adaptationSetIndex == -1) {
            return null;
        }
        List<Representation> list = period.adaptationSets.get(adaptationSetIndex).representations;
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private DashUtil() {
    }
}