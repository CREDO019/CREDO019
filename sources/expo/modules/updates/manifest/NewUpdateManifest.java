package expo.modules.updates.manifest;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.manifests.core.NewManifest;
import expo.modules.structuredheaders.BooleanItem;
import expo.modules.structuredheaders.ListElement;
import expo.modules.structuredheaders.NumberItem;
import expo.modules.structuredheaders.Parser;
import expo.modules.structuredheaders.StringItem;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.loader.EmbeddedLoader;
import expo.modules.updates.manifest.NewUpdateManifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NewUpdateManifest.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 02\u00020\u0001:\u00010B_\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0012R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R'\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020 X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001d\u0010$\u001a\u0004\u0018\u00010\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0019\u001a\u0004\b%\u0010&R\u001d\u0010(\u001a\u0004\u0018\u00010\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0019\u001a\u0004\b)\u0010&R\u001b\u0010+\u001a\u00020,8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u0019\u001a\u0004\b-\u0010.¨\u00061"}, m183d2 = {"Lexpo/modules/updates/manifest/NewUpdateManifest;", "Lexpo/modules/updates/manifest/UpdateManifest;", "manifest", "Lexpo/modules/manifests/core/NewManifest;", "mId", "Ljava/util/UUID;", "mScopeKey", "", "mCommitTime", "Ljava/util/Date;", "mRuntimeVersion", "mLaunchAsset", "Lorg/json/JSONObject;", "mAssets", "Lorg/json/JSONArray;", "mExtensions", "mServerDefinedHeaders", "mManifestFilters", "(Lexpo/modules/manifests/core/NewManifest;Ljava/util/UUID;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONArray;Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)V", "assetEntityList", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getAssetEntityList", "()Ljava/util/List;", "assetEntityList$delegate", "Lkotlin/Lazy;", "assetHeaders", "", "getAssetHeaders", "()Ljava/util/Map;", "assetHeaders$delegate", "isDevelopmentMode", "", "()Z", "getManifest", "()Lexpo/modules/manifests/core/NewManifest;", "manifestFilters", "getManifestFilters", "()Lorg/json/JSONObject;", "manifestFilters$delegate", "serverDefinedHeaders", "getServerDefinedHeaders", "serverDefinedHeaders$delegate", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getUpdateEntity", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "updateEntity$delegate", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class NewUpdateManifest implements UpdateManifest {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdateManifest";
    private final Lazy assetEntityList$delegate;
    private final Lazy assetHeaders$delegate;
    private final boolean isDevelopmentMode;
    private final JSONArray mAssets;
    private final Date mCommitTime;
    private final JSONObject mExtensions;
    private final UUID mId;
    private final JSONObject mLaunchAsset;
    private final String mManifestFilters;
    private final String mRuntimeVersion;
    private final String mScopeKey;
    private final String mServerDefinedHeaders;
    private final NewManifest manifest;
    private final Lazy manifestFilters$delegate;
    private final Lazy serverDefinedHeaders$delegate;
    private final Lazy updateEntity$delegate;

    public /* synthetic */ NewUpdateManifest(NewManifest newManifest, UUID r2, String str, Date date, String str2, JSONObject jSONObject, JSONArray jSONArray, JSONObject jSONObject2, String str3, String str4, DefaultConstructorMarker defaultConstructorMarker) {
        this(newManifest, r2, str, date, str2, jSONObject, jSONArray, jSONObject2, str3, str4);
    }

    private NewUpdateManifest(NewManifest newManifest, UUID r2, String str, Date date, String str2, JSONObject jSONObject, JSONArray jSONArray, JSONObject jSONObject2, String str3, String str4) {
        this.manifest = newManifest;
        this.mId = r2;
        this.mScopeKey = str;
        this.mCommitTime = date;
        this.mRuntimeVersion = str2;
        this.mLaunchAsset = jSONObject;
        this.mAssets = jSONArray;
        this.mExtensions = jSONObject2;
        this.mServerDefinedHeaders = str3;
        this.mManifestFilters = str4;
        this.serverDefinedHeaders$delegate = LazyKt.lazy(new Functions<JSONObject>() { // from class: expo.modules.updates.manifest.NewUpdateManifest$serverDefinedHeaders$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final JSONObject invoke() {
                String str5;
                String str6;
                str5 = NewUpdateManifest.this.mServerDefinedHeaders;
                if (str5 == null) {
                    return null;
                }
                NewUpdateManifest.Companion companion = NewUpdateManifest.Companion;
                str6 = NewUpdateManifest.this.mServerDefinedHeaders;
                return companion.headerDictionaryToJSONObject$expo_updates_release(str6);
            }
        });
        this.manifestFilters$delegate = LazyKt.lazy(new Functions<JSONObject>() { // from class: expo.modules.updates.manifest.NewUpdateManifest$manifestFilters$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final JSONObject invoke() {
                String str5;
                String str6;
                str5 = NewUpdateManifest.this.mManifestFilters;
                if (str5 == null) {
                    return null;
                }
                NewUpdateManifest.Companion companion = NewUpdateManifest.Companion;
                str6 = NewUpdateManifest.this.mManifestFilters;
                return companion.headerDictionaryToJSONObject$expo_updates_release(str6);
            }
        });
        this.updateEntity$delegate = LazyKt.lazy(new Functions<UpdateEntity>() { // from class: expo.modules.updates.manifest.NewUpdateManifest$updateEntity$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final UpdateEntity invoke() {
                UUID r1;
                Date date2;
                String str5;
                String str6;
                r1 = NewUpdateManifest.this.mId;
                date2 = NewUpdateManifest.this.mCommitTime;
                str5 = NewUpdateManifest.this.mRuntimeVersion;
                str6 = NewUpdateManifest.this.mScopeKey;
                UpdateEntity updateEntity = new UpdateEntity(r1, date2, str5, str6);
                updateEntity.setManifest(NewUpdateManifest.this.getManifest().getRawJson());
                return updateEntity;
            }
        });
        this.assetHeaders$delegate = LazyKt.lazy(new Functions<Map<String, ? extends JSONObject>>() { // from class: expo.modules.updates.manifest.NewUpdateManifest$assetHeaders$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final Map<String, ? extends JSONObject> invoke() {
                JSONObject jSONObject3;
                JSONObject jSONObject4;
                JSONObject jSONObject5;
                jSONObject3 = NewUpdateManifest.this.mExtensions;
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                LinkedHashMap linkedHashMap = null;
                if (jSONObject3.has("assetRequestHeaders")) {
                    KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                        String string = jSONObject3.getString("assetRequestHeaders");
                        Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                        jSONObject4 = (JSONObject) string;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        jSONObject4 = (JSONObject) Double.valueOf(jSONObject3.getDouble("assetRequestHeaders"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        jSONObject4 = (JSONObject) Integer.valueOf(jSONObject3.getInt("assetRequestHeaders"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        jSONObject4 = (JSONObject) Long.valueOf(jSONObject3.getLong("assetRequestHeaders"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        jSONObject4 = (JSONObject) Boolean.valueOf(jSONObject3.getBoolean("assetRequestHeaders"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        JSONArray jSONArray2 = jSONObject3.getJSONArray("assetRequestHeaders");
                        Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONObject");
                        jSONObject4 = (JSONObject) jSONArray2;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        jSONObject4 = jSONObject3.getJSONObject("assetRequestHeaders");
                        Objects.requireNonNull(jSONObject4, "null cannot be cast to non-null type org.json.JSONObject");
                    } else {
                        Object obj = jSONObject3.get("assetRequestHeaders");
                        Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                        jSONObject4 = (JSONObject) obj;
                    }
                } else {
                    jSONObject4 = null;
                }
                if (jSONObject4 != null) {
                    Iterator<String> keys = jSONObject4.keys();
                    Intrinsics.checkNotNullExpressionValue(keys, "it.keys()");
                    Sequence asSequence = SequencesKt.asSequence(keys);
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    for (Object obj2 : asSequence) {
                        LinkedHashMap linkedHashMap3 = linkedHashMap2;
                        String str5 = (String) obj2;
                        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(JSONObject.class);
                        if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                            String string2 = jSONObject4.getString(str5);
                            Objects.requireNonNull(string2, "null cannot be cast to non-null type org.json.JSONObject");
                            jSONObject5 = (JSONObject) string2;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                            jSONObject5 = (JSONObject) Double.valueOf(jSONObject4.getDouble(str5));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                            jSONObject5 = (JSONObject) Integer.valueOf(jSONObject4.getInt(str5));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                            jSONObject5 = (JSONObject) Long.valueOf(jSONObject4.getLong(str5));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                            jSONObject5 = (JSONObject) Boolean.valueOf(jSONObject4.getBoolean(str5));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                            JSONArray jSONArray3 = jSONObject4.getJSONArray(str5);
                            Objects.requireNonNull(jSONArray3, "null cannot be cast to non-null type org.json.JSONObject");
                            jSONObject5 = (JSONObject) jSONArray3;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                            jSONObject5 = jSONObject4.getJSONObject(str5);
                            Objects.requireNonNull(jSONObject5, "null cannot be cast to non-null type org.json.JSONObject");
                        } else {
                            Object obj3 = jSONObject4.get(str5);
                            Objects.requireNonNull(obj3, "null cannot be cast to non-null type org.json.JSONObject");
                            jSONObject5 = (JSONObject) obj3;
                        }
                        linkedHashMap3.put(obj2, jSONObject5);
                    }
                    linkedHashMap = linkedHashMap2;
                }
                return linkedHashMap == null ? MapsKt.emptyMap() : linkedHashMap;
            }
        });
        this.assetEntityList$delegate = LazyKt.lazy(new Functions<List<AssetEntity>>() { // from class: expo.modules.updates.manifest.NewUpdateManifest$assetEntityList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final List<AssetEntity> invoke() {
                JSONArray jSONArray2;
                JSONArray jSONArray3;
                JSONArray jSONArray4;
                JSONArray jSONArray5;
                Map assetHeaders;
                String str5;
                String str6;
                JSONObject jSONObject3;
                JSONObject jSONObject4;
                String str7;
                JSONObject jSONObject5;
                Map assetHeaders2;
                JSONObject jSONObject6;
                JSONObject jSONObject7;
                String str8;
                ArrayList arrayList = new ArrayList();
                try {
                    jSONObject3 = NewUpdateManifest.this.mLaunchAsset;
                    String string = jSONObject3.getString("key");
                    jSONObject4 = NewUpdateManifest.this.mLaunchAsset;
                    if (jSONObject4.has("fileExtension")) {
                        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
                        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                            str7 = jSONObject4.getString("fileExtension");
                            if (str7 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                            str7 = (String) Double.valueOf(jSONObject4.getDouble("fileExtension"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                            str7 = (String) Integer.valueOf(jSONObject4.getInt("fileExtension"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                            str7 = (String) Long.valueOf(jSONObject4.getLong("fileExtension"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                            str7 = (String) Boolean.valueOf(jSONObject4.getBoolean("fileExtension"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                            JSONArray jSONArray6 = jSONObject4.getJSONArray("fileExtension");
                            if (jSONArray6 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str7 = (String) jSONArray6;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                            JSONObject jSONObject8 = jSONObject4.getJSONObject("fileExtension");
                            if (jSONObject8 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str7 = (String) jSONObject8;
                        } else {
                            Object obj = jSONObject4.get("fileExtension");
                            if (obj == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str7 = (String) obj;
                        }
                    } else {
                        str7 = null;
                    }
                    AssetEntity assetEntity = new AssetEntity(string, str7);
                    NewUpdateManifest newUpdateManifest = NewUpdateManifest.this;
                    jSONObject5 = newUpdateManifest.mLaunchAsset;
                    assetEntity.setUrl(Uri.parse(jSONObject5.getString(ImagesContract.URL)));
                    assetHeaders2 = newUpdateManifest.getAssetHeaders();
                    jSONObject6 = newUpdateManifest.mLaunchAsset;
                    assetEntity.setExtraRequestHeaders((JSONObject) assetHeaders2.get(jSONObject6.getString("key")));
                    assetEntity.setLaunchAsset(true);
                    assetEntity.setEmbeddedAssetFilename(EmbeddedLoader.BUNDLE_FILENAME);
                    jSONObject7 = newUpdateManifest.mLaunchAsset;
                    if (jSONObject7.has("hash")) {
                        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
                        if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                            str8 = jSONObject7.getString("hash");
                            if (str8 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                            str8 = (String) Double.valueOf(jSONObject7.getDouble("hash"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                            str8 = (String) Integer.valueOf(jSONObject7.getInt("hash"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                            str8 = (String) Long.valueOf(jSONObject7.getLong("hash"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                            str8 = (String) Boolean.valueOf(jSONObject7.getBoolean("hash"));
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                            JSONArray jSONArray7 = jSONObject7.getJSONArray("hash");
                            if (jSONArray7 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str8 = (String) jSONArray7;
                        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                            JSONObject jSONObject9 = jSONObject7.getJSONObject("hash");
                            if (jSONObject9 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str8 = (String) jSONObject9;
                        } else {
                            Object obj2 = jSONObject7.get("hash");
                            if (obj2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                            }
                            str8 = (String) obj2;
                        }
                    } else {
                        str8 = null;
                    }
                    assetEntity.setExpectedHash(str8);
                    arrayList.add(assetEntity);
                } catch (JSONException e) {
                    Log.e(NewUpdateManifest.TAG, "Could not read launch asset from manifest", e);
                }
                jSONArray2 = NewUpdateManifest.this.mAssets;
                if (jSONArray2 != null) {
                    jSONArray3 = NewUpdateManifest.this.mAssets;
                    if (jSONArray3.length() > 0) {
                        int r7 = 0;
                        jSONArray4 = NewUpdateManifest.this.mAssets;
                        int length = jSONArray4.length();
                        while (r7 < length) {
                            int r9 = r7 + 1;
                            try {
                                jSONArray5 = NewUpdateManifest.this.mAssets;
                                JSONObject assetObject = jSONArray5.getJSONObject(r7);
                                AssetEntity assetEntity2 = new AssetEntity(assetObject.getString("key"), assetObject.getString("fileExtension"));
                                NewUpdateManifest newUpdateManifest2 = NewUpdateManifest.this;
                                assetEntity2.setUrl(Uri.parse(assetObject.getString(ImagesContract.URL)));
                                assetHeaders = newUpdateManifest2.getAssetHeaders();
                                assetEntity2.setExtraRequestHeaders((JSONObject) assetHeaders.get(assetObject.getString("key")));
                                Intrinsics.checkNotNullExpressionValue(assetObject, "assetObject");
                                if (assetObject.has("embeddedAssetFilename")) {
                                    KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                                    if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                                        str5 = assetObject.getString("embeddedAssetFilename");
                                        if (str5 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                                        str5 = (String) Double.valueOf(assetObject.getDouble("embeddedAssetFilename"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                                        str5 = (String) Integer.valueOf(assetObject.getInt("embeddedAssetFilename"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                                        str5 = (String) Long.valueOf(assetObject.getLong("embeddedAssetFilename"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                                        str5 = (String) Boolean.valueOf(assetObject.getBoolean("embeddedAssetFilename"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                                        JSONArray jSONArray8 = assetObject.getJSONArray("embeddedAssetFilename");
                                        if (jSONArray8 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str5 = (String) jSONArray8;
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                        JSONObject jSONObject10 = assetObject.getJSONObject("embeddedAssetFilename");
                                        if (jSONObject10 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str5 = (String) jSONObject10;
                                    } else {
                                        Object obj3 = assetObject.get("embeddedAssetFilename");
                                        if (obj3 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str5 = (String) obj3;
                                    }
                                } else {
                                    str5 = null;
                                }
                                assetEntity2.setEmbeddedAssetFilename(str5);
                                if (assetObject.has("hash")) {
                                    KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(String.class);
                                    if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                                        str6 = assetObject.getString("hash");
                                        if (str6 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                                        str6 = (String) Double.valueOf(assetObject.getDouble("hash"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                                        str6 = (String) Integer.valueOf(assetObject.getInt("hash"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                                        str6 = (String) Long.valueOf(assetObject.getLong("hash"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                                        str6 = (String) Boolean.valueOf(assetObject.getBoolean("hash"));
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                                        JSONArray jSONArray9 = assetObject.getJSONArray("hash");
                                        if (jSONArray9 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str6 = (String) jSONArray9;
                                    } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                        JSONObject jSONObject11 = assetObject.getJSONObject("hash");
                                        if (jSONObject11 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str6 = (String) jSONObject11;
                                    } else {
                                        Object obj4 = assetObject.get("hash");
                                        if (obj4 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                        }
                                        str6 = (String) obj4;
                                    }
                                } else {
                                    str6 = null;
                                }
                                assetEntity2.setExpectedHash(str6);
                                arrayList.add(assetEntity2);
                            } catch (JSONException e2) {
                                Log.e(NewUpdateManifest.TAG, "Could not read asset from manifest", e2);
                            }
                            r7 = r9;
                        }
                    }
                }
                return arrayList;
            }
        });
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public NewManifest getManifest() {
        return this.manifest;
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public JSONObject getServerDefinedHeaders() {
        return (JSONObject) this.serverDefinedHeaders$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public JSONObject getManifestFilters() {
        return (JSONObject) this.manifestFilters$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public UpdateEntity getUpdateEntity() {
        return (UpdateEntity) this.updateEntity$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, JSONObject> getAssetHeaders() {
        return (Map) this.assetHeaders$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public List<AssetEntity> getAssetEntityList() {
        return (List) this.assetEntityList$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public boolean isDevelopmentMode() {
        return this.isDevelopmentMode;
    }

    /* compiled from: NewUpdateManifest.kt */
    @Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0019\u0010\u0010\u001a\u0004\u0018\u00010\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u0012R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m183d2 = {"Lexpo/modules/updates/manifest/NewUpdateManifest$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "fromNewManifest", "Lexpo/modules/updates/manifest/NewUpdateManifest;", "manifest", "Lexpo/modules/manifests/core/NewManifest;", "manifestHeaderData", "Lexpo/modules/updates/manifest/ManifestHeaderData;", "extensions", "Lorg/json/JSONObject;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "headerDictionaryToJSONObject", "headerDictionary", "headerDictionaryToJSONObject$expo_updates_release", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NewUpdateManifest fromNewManifest(NewManifest manifest, ManifestHeaderData manifestHeaderData, JSONObject jSONObject, UpdatesConfiguration configuration) throws JSONException {
            Date date;
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            Intrinsics.checkNotNullParameter(manifestHeaderData, "manifestHeaderData");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            UUID id = UUID.fromString(manifest.getID());
            String runtimeVersion = manifest.getRuntimeVersion();
            JSONObject launchAsset = manifest.getLaunchAsset();
            JSONArray assets = manifest.getAssets();
            try {
                date = UpdatesUtils.INSTANCE.parseDateString(manifest.getCreatedAt());
            } catch (ParseException e) {
                Log.e(NewUpdateManifest.TAG, "Could not parse manifest createdAt string; falling back to current time", e);
                date = new Date();
            }
            Intrinsics.checkNotNullExpressionValue(id, "id");
            String scopeKey = configuration.getScopeKey();
            Intrinsics.checkNotNull(scopeKey);
            return new NewUpdateManifest(manifest, id, scopeKey, date, runtimeVersion, launchAsset, assets, jSONObject, manifestHeaderData.getServerDefinedHeaders(), manifestHeaderData.getManifestFilters(), null);
        }

        public final JSONObject headerDictionaryToJSONObject$expo_updates_release(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                Map<String, ListElement<? extends Object>> map = new Parser(str).parseDictionary().get();
                for (String str2 : map.keySet()) {
                    ListElement<? extends Object> listElement = map.get(str2);
                    Intrinsics.checkNotNull(listElement);
                    ListElement<? extends Object> listElement2 = listElement;
                    if ((listElement2 instanceof StringItem) || (listElement2 instanceof BooleanItem) || (listElement2 instanceof NumberItem)) {
                        jSONObject.put(str2, listElement2.get());
                    }
                }
                return jSONObject;
            } catch (expo.modules.structuredheaders.ParseException e) {
                Log.e(NewUpdateManifest.TAG, "Failed to parse manifest header content", e);
                return null;
            } catch (JSONException e2) {
                Log.e(NewUpdateManifest.TAG, "Failed to parse manifest header content", e2);
                return null;
            }
        }
    }
}
