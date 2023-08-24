package expo.modules.updates.p021db.entity;

import android.net.Uri;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.updates.p021db.enums.HashType;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: AssetEntity.kt */
@Metadata(m184d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u00020!8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\u001e\u0010)\u001a\u00020*8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u0010/\u001a\u0002008\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00101\"\u0004\b2\u00103R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u0010\u0010R\u001e\u00106\u001a\u0002008\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00101\"\u0004\b8\u00103R\u001c\u00109\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0017\"\u0004\b;\u0010\u0019R \u0010<\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000e\"\u0004\b>\u0010\u0010R \u0010?\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u000e\"\u0004\bA\u0010\u0010R \u0010B\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u000e\"\u0004\bD\u0010\u0010R\"\u0010E\u001a\u0004\u0018\u00010F8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010K\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR(\u0010L\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010M8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010R\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u000e\"\u0004\bT\u0010\u0010R\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010Z¨\u0006["}, m183d2 = {"Lexpo/modules/updates/db/entity/AssetEntity;", "", "key", "", SessionDescription.ATTR_TYPE, "(Ljava/lang/String;Ljava/lang/String;)V", "downloadTime", "Ljava/util/Date;", "getDownloadTime", "()Ljava/util/Date;", "setDownloadTime", "(Ljava/util/Date;)V", "embeddedAssetFilename", "getEmbeddedAssetFilename", "()Ljava/lang/String;", "setEmbeddedAssetFilename", "(Ljava/lang/String;)V", "expectedHash", "getExpectedHash", "setExpectedHash", "extraRequestHeaders", "Lorg/json/JSONObject;", "getExtraRequestHeaders", "()Lorg/json/JSONObject;", "setExtraRequestHeaders", "(Lorg/json/JSONObject;)V", "hash", "", "getHash", "()[B", "setHash", "([B)V", "hashType", "Lexpo/modules/updates/db/enums/HashType;", "getHashType", "()Lexpo/modules/updates/db/enums/HashType;", "setHashType", "(Lexpo/modules/updates/db/enums/HashType;)V", "headers", "getHeaders", "setHeaders", "id", "", "getId", "()J", "setId", "(J)V", "isLaunchAsset", "", "()Z", "setLaunchAsset", "(Z)V", "getKey", "setKey", "markedForDeletion", "getMarkedForDeletion", "setMarkedForDeletion", TtmlNode.TAG_METADATA, "getMetadata", "setMetadata", "relativePath", "getRelativePath", "setRelativePath", "resourcesFilename", "getResourcesFilename", "setResourcesFilename", "resourcesFolder", "getResourcesFolder", "setResourcesFolder", "scale", "", "getScale", "()Ljava/lang/Float;", "setScale", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "scales", "", "getScales", "()[Ljava/lang/Float;", "setScales", "([Ljava/lang/Float;)V", "[Ljava/lang/Float;", "getType", "setType", ImagesContract.URL, "Landroid/net/Uri;", "getUrl", "()Landroid/net/Uri;", "setUrl", "(Landroid/net/Uri;)V", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.entity.AssetEntity */
/* loaded from: classes5.dex */
public final class AssetEntity {
    private Date downloadTime;
    private String embeddedAssetFilename;
    private String expectedHash;
    private JSONObject extraRequestHeaders;
    private byte[] hash;
    private HashType hashType = HashType.SHA256;
    private JSONObject headers;

    /* renamed from: id */
    private long f1471id;
    private boolean isLaunchAsset;
    private String key;
    private boolean markedForDeletion;
    private JSONObject metadata;
    private String relativePath;
    private String resourcesFilename;
    private String resourcesFolder;
    private Float scale;
    private Float[] scales;
    private String type;
    private Uri url;

    public AssetEntity(String str, String str2) {
        this.key = str;
        this.type = str2;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getType() {
        return this.type;
    }

    public final void setKey(String str) {
        this.key = str;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final long getId() {
        return this.f1471id;
    }

    public final void setId(long j) {
        this.f1471id = j;
    }

    public final Uri getUrl() {
        return this.url;
    }

    public final void setUrl(Uri uri) {
        this.url = uri;
    }

    public final JSONObject getHeaders() {
        return this.headers;
    }

    public final void setHeaders(JSONObject jSONObject) {
        this.headers = jSONObject;
    }

    public final JSONObject getExtraRequestHeaders() {
        return this.extraRequestHeaders;
    }

    public final void setExtraRequestHeaders(JSONObject jSONObject) {
        this.extraRequestHeaders = jSONObject;
    }

    public final JSONObject getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(JSONObject jSONObject) {
        this.metadata = jSONObject;
    }

    public final Date getDownloadTime() {
        return this.downloadTime;
    }

    public final void setDownloadTime(Date date) {
        this.downloadTime = date;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final void setRelativePath(String str) {
        this.relativePath = str;
    }

    public final byte[] getHash() {
        return this.hash;
    }

    public final void setHash(byte[] bArr) {
        this.hash = bArr;
    }

    public final HashType getHashType() {
        return this.hashType;
    }

    public final void setHashType(HashType hashType) {
        Intrinsics.checkNotNullParameter(hashType, "<set-?>");
        this.hashType = hashType;
    }

    public final String getExpectedHash() {
        return this.expectedHash;
    }

    public final void setExpectedHash(String str) {
        this.expectedHash = str;
    }

    public final boolean getMarkedForDeletion() {
        return this.markedForDeletion;
    }

    public final void setMarkedForDeletion(boolean z) {
        this.markedForDeletion = z;
    }

    public final boolean isLaunchAsset() {
        return this.isLaunchAsset;
    }

    public final void setLaunchAsset(boolean z) {
        this.isLaunchAsset = z;
    }

    public final String getEmbeddedAssetFilename() {
        return this.embeddedAssetFilename;
    }

    public final void setEmbeddedAssetFilename(String str) {
        this.embeddedAssetFilename = str;
    }

    public final String getResourcesFilename() {
        return this.resourcesFilename;
    }

    public final void setResourcesFilename(String str) {
        this.resourcesFilename = str;
    }

    public final String getResourcesFolder() {
        return this.resourcesFolder;
    }

    public final void setResourcesFolder(String str) {
        this.resourcesFolder = str;
    }

    public final Float getScale() {
        return this.scale;
    }

    public final void setScale(Float f) {
        this.scale = f;
    }

    public final Float[] getScales() {
        return this.scales;
    }

    public final void setScales(Float[] fArr) {
        this.scales = fArr;
    }
}
