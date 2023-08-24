package expo.modules.speech;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import com.amplitude.api.Constants;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.UIManager;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpeechModule.kt */
@Metadata(m184d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u00018B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001dH\u0002J\u0010\u0010'\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u001f\u0010(\u001a\u0010\u0012\f\u0012\n +*\u0004\u0018\u0001H*H*0)\"\u0006\b\u0000\u0010*\u0018\u0001H\u0082\bJ\u0010\u0010,\u001a\u00020!2\u0006\u0010(\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020!H\u0016J\b\u0010/\u001a\u00020!H\u0016J\b\u00100\u001a\u00020!H\u0016J6\u00101\u001a\u00020!2\u0006\u0010&\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u001d2\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u000204\u0018\u00010\u001c2\u0006\u0010\"\u001a\u00020#H\u0007J \u00105\u001a\u00020!2\u0006\u0010&\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019¨\u00069"}, m183d2 = {"Lexpo/modules/speech/SpeechModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "_textToSpeech", "Landroid/speech/tts/TextToSpeech;", "_ttsReady", "", "delayedUtterances", "Ljava/util/Queue;", "Lexpo/modules/speech/SpeechModule$Utterance;", "isTextToSpeechReady", "()Z", "textToSpeech", "getTextToSpeech", "()Landroid/speech/tts/TextToSpeech;", "textToSpeech$delegate", "Lkotlin/Lazy;", "uiManager", "Lexpo/modules/core/interfaces/services/UIManager;", "getUiManager", "()Lexpo/modules/core/interfaces/services/UIManager;", "uiManager$delegate", "getConstants", "", "", "", "getName", "getVoices", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "idToMap", "Landroid/os/Bundle;", "id", "isSpeaking", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "onHostDestroy", "onHostPause", "onHostResume", "speak", "text", "options", "", "speakOut", "Lexpo/modules/speech/SpeechOptions;", "stop", "Utterance", "expo-speech_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SpeechModule extends ExportedModule implements LifecycleEventListener {
    private TextToSpeech _textToSpeech;
    private boolean _ttsReady;
    private final Queue<Utterance> delayedUtterances;
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final Lazy textToSpeech$delegate;
    private final Lazy uiManager$delegate;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentSpeech";
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
    }

    public /* synthetic */ SpeechModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpeechModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        final ModuleRegistryDelegate moduleRegistryDelegate2 = this.moduleRegistryDelegate;
        this.uiManager$delegate = LazyKt.lazy(new Functions<UIManager>() { // from class: expo.modules.speech.SpeechModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.core.interfaces.services.UIManager, java.lang.Object] */
            @Override // kotlin.jvm.functions.Functions
            public final UIManager invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(UIManager.class);
            }
        });
        this.delayedUtterances = new ArrayDeque();
        this.textToSpeech$delegate = LazyKt.lazy(new SpeechModule$textToSpeech$2(context, this));
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.speech.SpeechModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    private final UIManager getUiManager() {
        Object value = this.uiManager$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-uiManager>(...)");
        return (UIManager) value;
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Integer> getConstants() {
        return MapsKt.mapOf(TuplesKt.m176to("maxSpeechInputLength", Integer.valueOf(TextToSpeech.getMaxSpeechInputLength())));
    }

    @ExpoMethod
    public final void isSpeaking(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        promise.resolve(Boolean.valueOf(getTextToSpeech().isSpeaking()));
    }

    @ExpoMethod
    public final void getVoices(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        List emptyList = CollectionsKt.emptyList();
        try {
            Set<Voice> voices = getTextToSpeech().getVoices();
            Intrinsics.checkNotNullExpressionValue(voices, "textToSpeech.voices");
            emptyList = CollectionsKt.toList(voices);
        } catch (Exception unused) {
        }
        List<Voice> list = emptyList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Voice voice : list) {
            String str = voice.getQuality() > 300 ? "Enhanced" : "Default";
            Bundle bundle = new Bundle();
            bundle.putString("identifier", voice.getName());
            bundle.putString("name", voice.getName());
            bundle.putString("quality", str);
            LanguageUtils languageUtils = LanguageUtils.INSTANCE;
            Locale locale = voice.getLocale();
            Intrinsics.checkNotNullExpressionValue(locale, "it.locale");
            bundle.putString(Constants.AMP_TRACKING_OPTION_LANGUAGE, languageUtils.getISOCode(locale));
            arrayList.add(bundle);
        }
        promise.resolve(arrayList);
    }

    @ExpoMethod
    public final void stop(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        getTextToSpeech().stop();
        promise.resolve(null);
    }

    @ExpoMethod
    public final void speak(String id, String text, Map<String, ? extends Object> map, Promise promise) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(promise, "promise");
        SpeechOptions optionsFromMap = SpeechOptions.Companion.optionsFromMap(map, promise);
        if (optionsFromMap == null) {
            return;
        }
        if (text.length() > TextToSpeech.getMaxSpeechInputLength()) {
            int maxSpeechInputLength = TextToSpeech.getMaxSpeechInputLength();
            promise.reject("ERR_SPEECH_INPUT_LENGTH", "Speech input text is too long! Limit of input length is: " + maxSpeechInputLength);
            return;
        }
        if (isTextToSpeechReady()) {
            speakOut(id, text, optionsFromMap);
        } else {
            this.delayedUtterances.add(new Utterance(id, text, optionsFromMap));
            getTextToSpeech();
        }
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void speakOut(String str, String str2, SpeechOptions speechOptions) {
        Locale locale;
        Object obj;
        Float pitch = speechOptions.getPitch();
        if (pitch != null) {
            getTextToSpeech().setPitch(pitch.floatValue());
        }
        Float rate = speechOptions.getRate();
        if (rate != null) {
            getTextToSpeech().setSpeechRate(rate.floatValue());
        }
        TextToSpeech textToSpeech = getTextToSpeech();
        String language = speechOptions.getLanguage();
        if (language == null) {
            locale = null;
        } else {
            locale = new Locale(language);
            int isLanguageAvailable = getTextToSpeech().isLanguageAvailable(locale);
            if (isLanguageAvailable == -1 || isLanguageAvailable == -2) {
                locale = Locale.getDefault();
            }
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        textToSpeech.setLanguage(locale);
        String voice = speechOptions.getVoice();
        if (voice != null) {
            Set<Voice> voices = getTextToSpeech().getVoices();
            Intrinsics.checkNotNullExpressionValue(voices, "textToSpeech.voices");
            Iterator<T> it = voices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((Voice) obj).getName(), voice)) {
                    break;
                }
            }
            Voice voice2 = (Voice) obj;
            if (voice2 != null) {
                getTextToSpeech().setVoice(voice2);
            }
        }
        getTextToSpeech().speak(str2, 1, null, str);
    }

    private final boolean isTextToSpeechReady() {
        return this._ttsReady;
    }

    private final TextToSpeech getTextToSpeech() {
        return (TextToSpeech) this.textToSpeech$delegate.getValue();
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
        getUiManager().registerLifecycleEventListener(this);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
        getTextToSpeech().shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle idToMap(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SpeechModule.kt */
    @Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/speech/SpeechModule$Utterance;", "", "id", "", "text", "options", "Lexpo/modules/speech/SpeechOptions;", "(Ljava/lang/String;Ljava/lang/String;Lexpo/modules/speech/SpeechOptions;)V", "getId", "()Ljava/lang/String;", "getOptions", "()Lexpo/modules/speech/SpeechOptions;", "getText", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-speech_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Utterance {

        /* renamed from: id */
        private final String f1463id;
        private final SpeechOptions options;
        private final String text;

        public static /* synthetic */ Utterance copy$default(Utterance utterance, String str, String str2, SpeechOptions speechOptions, int r4, Object obj) {
            if ((r4 & 1) != 0) {
                str = utterance.f1463id;
            }
            if ((r4 & 2) != 0) {
                str2 = utterance.text;
            }
            if ((r4 & 4) != 0) {
                speechOptions = utterance.options;
            }
            return utterance.copy(str, str2, speechOptions);
        }

        public final String component1() {
            return this.f1463id;
        }

        public final String component2() {
            return this.text;
        }

        public final SpeechOptions component3() {
            return this.options;
        }

        public final Utterance copy(String id, String text, SpeechOptions options) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            return new Utterance(id, text, options);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Utterance) {
                Utterance utterance = (Utterance) obj;
                return Intrinsics.areEqual(this.f1463id, utterance.f1463id) && Intrinsics.areEqual(this.text, utterance.text) && Intrinsics.areEqual(this.options, utterance.options);
            }
            return false;
        }

        public int hashCode() {
            return (((this.f1463id.hashCode() * 31) + this.text.hashCode()) * 31) + this.options.hashCode();
        }

        public String toString() {
            String str = this.f1463id;
            String str2 = this.text;
            SpeechOptions speechOptions = this.options;
            return "Utterance(id=" + str + ", text=" + str2 + ", options=" + speechOptions + ")";
        }

        public Utterance(String id, String text, SpeechOptions options) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(options, "options");
            this.f1463id = id;
            this.text = text;
            this.options = options;
        }

        public final String getId() {
            return this.f1463id;
        }

        public final String getText() {
            return this.text;
        }

        public final SpeechOptions getOptions() {
            return this.options;
        }
    }
}
