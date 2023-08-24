package expo.modules.documentpicker;

import com.facebook.react.bridge.BaseJavaModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.Promise;
import expo.modules.imagepicker.MediaTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentPickerOptions.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ(\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/documentpicker/DocumentPickerOptions;", "", "copyToCacheDirectory", "", "types", "", "", "(Z[Ljava/lang/String;)V", "getCopyToCacheDirectory", "()Z", "getTypes", "()[Ljava/lang/String;", "[Ljava/lang/String;", "component1", "component2", "copy", "(Z[Ljava/lang/String;)Lexpo/modules/documentpicker/DocumentPickerOptions;", "equals", "other", "hashCode", "", "toString", "Companion", "expo-document-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DocumentPickerOptions {
    public static final Companion Companion = new Companion(null);
    private final boolean copyToCacheDirectory;
    private final String[] types;

    public static /* synthetic */ DocumentPickerOptions copy$default(DocumentPickerOptions documentPickerOptions, boolean z, String[] strArr, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            z = documentPickerOptions.copyToCacheDirectory;
        }
        if ((r3 & 2) != 0) {
            strArr = documentPickerOptions.types;
        }
        return documentPickerOptions.copy(z, strArr);
    }

    public final boolean component1() {
        return this.copyToCacheDirectory;
    }

    public final String[] component2() {
        return this.types;
    }

    public final DocumentPickerOptions copy(boolean z, String[] types) {
        Intrinsics.checkNotNullParameter(types, "types");
        return new DocumentPickerOptions(z, types);
    }

    public String toString() {
        boolean z = this.copyToCacheDirectory;
        String arrays = Arrays.toString(this.types);
        return "DocumentPickerOptions(copyToCacheDirectory=" + z + ", types=" + arrays + ")";
    }

    /* compiled from: DocumentPickerOptions.kt */
    @Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/documentpicker/DocumentPickerOptions$Companion;", "", "()V", "optionsFromMap", "Lexpo/modules/documentpicker/DocumentPickerOptions;", "options", "", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "expo-document-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DocumentPickerOptions optionsFromMap(Map<String, ? extends Object> options, Promise promise) {
            boolean booleanValue;
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(promise, "promise");
            if (options.containsKey(SessionDescription.ATTR_TYPE) && options.get(SessionDescription.ATTR_TYPE) == null) {
                promise.reject("ERR_INVALID_OPTION", "type must be a list of strings");
                return null;
            }
            ArrayList arrayListOf = CollectionsKt.arrayListOf(MediaTypes.AllMimeType);
            Object obj = options.get(SessionDescription.ATTR_TYPE);
            if (obj != null) {
                arrayListOf = (ArrayList) obj;
                if (arrayListOf.isEmpty() || !(arrayListOf.get(0) instanceof String)) {
                    promise.reject("ERR_INVALID_OPTION", "type must be a list of strings");
                    return null;
                }
            }
            Object obj2 = options.get("copyToCacheDirectory");
            if (obj2 == null) {
                booleanValue = true;
            } else if (obj2 instanceof Boolean) {
                booleanValue = ((Boolean) obj2).booleanValue();
            } else {
                promise.reject("ERR_INVALID_OPTION", "copyToCacheDirectory must be a boolean");
                return null;
            }
            Object[] array = arrayListOf.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            return new DocumentPickerOptions(booleanValue, (String[]) array);
        }
    }

    public DocumentPickerOptions(boolean z, String[] types) {
        Intrinsics.checkNotNullParameter(types, "types");
        this.copyToCacheDirectory = z;
        this.types = types;
    }

    public final boolean getCopyToCacheDirectory() {
        return this.copyToCacheDirectory;
    }

    public final String[] getTypes() {
        return this.types;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(getClass(), obj == null ? null : obj.getClass())) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type expo.modules.documentpicker.DocumentPickerOptions");
            DocumentPickerOptions documentPickerOptions = (DocumentPickerOptions) obj;
            return this.copyToCacheDirectory == documentPickerOptions.copyToCacheDirectory && Arrays.equals(this.types, documentPickerOptions.types);
        }
        return false;
    }

    public int hashCode() {
        return (DocumentPickerOptions$$ExternalSyntheticBackport0.m223m(this.copyToCacheDirectory) * 31) + Arrays.hashCode(this.types);
    }
}
