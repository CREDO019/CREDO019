package expo.modules.speech;

import com.amplitude.api.Constants;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.Promise;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpeechOptions.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001d"}, m183d2 = {"Lexpo/modules/speech/SpeechOptions;", "", Constants.AMP_TRACKING_OPTION_LANGUAGE, "", "pitch", "", "rate", "voice", "(Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;)V", "getLanguage", "()Ljava/lang/String;", "getPitch", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getRate", "getVoice", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;)Lexpo/modules/speech/SpeechOptions;", "equals", "", "other", "hashCode", "", "toString", "Companion", "expo-speech_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SpeechOptions {
    public static final Companion Companion = new Companion(null);
    private final String language;
    private final Float pitch;
    private final Float rate;
    private final String voice;

    public static /* synthetic */ SpeechOptions copy$default(SpeechOptions speechOptions, String str, Float f, Float f2, String str2, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            str = speechOptions.language;
        }
        if ((r5 & 2) != 0) {
            f = speechOptions.pitch;
        }
        if ((r5 & 4) != 0) {
            f2 = speechOptions.rate;
        }
        if ((r5 & 8) != 0) {
            str2 = speechOptions.voice;
        }
        return speechOptions.copy(str, f, f2, str2);
    }

    public final String component1() {
        return this.language;
    }

    public final Float component2() {
        return this.pitch;
    }

    public final Float component3() {
        return this.rate;
    }

    public final String component4() {
        return this.voice;
    }

    public final SpeechOptions copy(String str, Float f, Float f2, String str2) {
        return new SpeechOptions(str, f, f2, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SpeechOptions) {
            SpeechOptions speechOptions = (SpeechOptions) obj;
            return Intrinsics.areEqual(this.language, speechOptions.language) && Intrinsics.areEqual((Object) this.pitch, (Object) speechOptions.pitch) && Intrinsics.areEqual((Object) this.rate, (Object) speechOptions.rate) && Intrinsics.areEqual(this.voice, speechOptions.voice);
        }
        return false;
    }

    public int hashCode() {
        String str = this.language;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Float f = this.pitch;
        int hashCode2 = (hashCode + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.rate;
        int hashCode3 = (hashCode2 + (f2 == null ? 0 : f2.hashCode())) * 31;
        String str2 = this.voice;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.language;
        Float f = this.pitch;
        Float f2 = this.rate;
        String str2 = this.voice;
        return "SpeechOptions(language=" + str + ", pitch=" + f + ", rate=" + f2 + ", voice=" + str2 + ")";
    }

    public SpeechOptions(String str, Float f, Float f2, String str2) {
        this.language = str;
        this.pitch = f;
        this.rate = f2;
        this.voice = str2;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final Float getPitch() {
        return this.pitch;
    }

    public final Float getRate() {
        return this.rate;
    }

    public final String getVoice() {
        return this.voice;
    }

    /* compiled from: SpeechOptions.kt */
    @Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/speech/SpeechOptions$Companion;", "", "()V", "optionsFromMap", "Lexpo/modules/speech/SpeechOptions;", "options", "", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "expo-speech_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SpeechOptions optionsFromMap(Map<String, ? extends Object> map, Promise promise) {
            String str;
            Float valueOf;
            Float valueOf2;
            Intrinsics.checkNotNullParameter(promise, "promise");
            String str2 = null;
            if (map == null) {
                return new SpeechOptions(null, null, null, null);
            }
            Object obj = map.get(Constants.AMP_TRACKING_OPTION_LANGUAGE);
            if (obj == null) {
                str = null;
            } else if (obj instanceof String) {
                str = (String) obj;
            } else {
                promise.reject("ERR_INVALID_OPTION", "Language must be a string");
                return null;
            }
            Object obj2 = map.get("pitch");
            if (obj2 == null) {
                valueOf = null;
            } else if (!(obj2 instanceof Number)) {
                promise.reject("ERR_INVALID_OPTION", "Pitch must be a number");
                return null;
            } else {
                valueOf = Float.valueOf(((Number) obj2).floatValue());
            }
            Object obj3 = map.get("rate");
            if (obj3 == null) {
                valueOf2 = null;
            } else if (!(obj3 instanceof Number)) {
                promise.reject("ERR_INVALID_OPTION", "Rate must be a number");
                return null;
            } else {
                valueOf2 = Float.valueOf(((Number) obj3).floatValue());
            }
            Object obj4 = map.get("voice");
            if (obj4 != null) {
                if (obj4 instanceof String) {
                    str2 = (String) obj4;
                } else {
                    promise.reject("ERR_INVALID_OPTION", "Voice name must be a string");
                    return null;
                }
            }
            return new SpeechOptions(str, valueOf, valueOf2, str2);
        }
    }
}
