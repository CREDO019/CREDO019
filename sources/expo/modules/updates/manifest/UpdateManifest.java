package expo.modules.updates.manifest;

import expo.modules.manifests.core.Manifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: UpdateManifest.kt */
@Metadata(m184d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/updates/manifest/UpdateManifest;", "", "assetEntityList", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getAssetEntityList", "()Ljava/util/List;", "isDevelopmentMode", "", "()Z", "manifest", "Lexpo/modules/manifests/core/Manifest;", "getManifest", "()Lexpo/modules/manifests/core/Manifest;", "manifestFilters", "Lorg/json/JSONObject;", "getManifestFilters", "()Lorg/json/JSONObject;", "serverDefinedHeaders", "getServerDefinedHeaders", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getUpdateEntity", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface UpdateManifest {
    List<AssetEntity> getAssetEntityList();

    Manifest getManifest();

    JSONObject getManifestFilters();

    JSONObject getServerDefinedHeaders();

    UpdateEntity getUpdateEntity();

    boolean isDevelopmentMode();
}
