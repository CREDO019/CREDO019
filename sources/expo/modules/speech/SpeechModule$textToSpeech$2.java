package expo.modules.speech;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.speech.SpeechModule;
import java.util.Queue;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpeechModule.kt */
@Metadata(m184d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m183d2 = {"<anonymous>", "Landroid/speech/tts/TextToSpeech;", "invoke"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SpeechModule$textToSpeech$2 extends Lambda implements Functions<TextToSpeech> {
    final /* synthetic */ Context $context;
    final /* synthetic */ SpeechModule this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpeechModule$textToSpeech$2(Context context, SpeechModule speechModule) {
        super(0);
        this.$context = context;
        this.this$0 = speechModule;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Functions
    public final TextToSpeech invoke() {
        Context context = this.$context;
        final SpeechModule speechModule = this.this$0;
        TextToSpeech textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() { // from class: expo.modules.speech.SpeechModule$textToSpeech$2$$ExternalSyntheticLambda0
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int r2) {
                SpeechModule$textToSpeech$2.m1700invoke$lambda1(SpeechModule.this, r2);
            }
        });
        this.this$0._textToSpeech = textToSpeech;
        return textToSpeech;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m1700invoke$lambda1(final SpeechModule this$0, int r4) {
        TextToSpeech textToSpeech;
        Queue<SpeechModule.Utterance> queue;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (r4 == 0) {
            synchronized (this$0) {
                this$0._ttsReady = true;
                textToSpeech = this$0._textToSpeech;
                Intrinsics.checkNotNull(textToSpeech);
                textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: expo.modules.speech.SpeechModule$textToSpeech$2$newTtsInstance$1$1$1
                    private final Lazy emitter$delegate;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        final ModuleRegistryDelegate moduleRegistryDelegate = SpeechModule.this.moduleRegistryDelegate;
                        this.emitter$delegate = LazyKt.lazy(new Functions<EventEmitter>() { // from class: expo.modules.speech.SpeechModule$textToSpeech$2$newTtsInstance$1$1$1$special$$inlined$moduleRegistry$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.core.interfaces.services.EventEmitter] */
                            @Override // kotlin.jvm.functions.Functions
                            public final EventEmitter invoke() {
                                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                                Intrinsics.checkNotNull(moduleRegistry);
                                return moduleRegistry.getModule(EventEmitter.class);
                            }
                        });
                    }

                    private final EventEmitter getEmitter() {
                        return (EventEmitter) this.emitter$delegate.getValue();
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onStart(String utteranceId) {
                        Bundle idToMap;
                        Intrinsics.checkNotNullParameter(utteranceId, "utteranceId");
                        EventEmitter emitter = getEmitter();
                        idToMap = SpeechModule.this.idToMap(utteranceId);
                        emitter.emit("Exponent.speakingStarted", idToMap);
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onRangeStart(String utteranceId, int r3, int r42, int r5) {
                        Intrinsics.checkNotNullParameter(utteranceId, "utteranceId");
                        Bundle bundle = new Bundle();
                        bundle.putString("id", utteranceId);
                        bundle.putInt("charIndex", r3);
                        bundle.putInt("charLength", r42 - r3);
                        getEmitter().emit("Exponent.speakingWillSayNextString", bundle);
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onDone(String utteranceId) {
                        Bundle idToMap;
                        Intrinsics.checkNotNullParameter(utteranceId, "utteranceId");
                        EventEmitter emitter = getEmitter();
                        idToMap = SpeechModule.this.idToMap(utteranceId);
                        emitter.emit("Exponent.speakingDone", idToMap);
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onStop(String utteranceId, boolean z) {
                        Bundle idToMap;
                        Intrinsics.checkNotNullParameter(utteranceId, "utteranceId");
                        EventEmitter emitter = getEmitter();
                        idToMap = SpeechModule.this.idToMap(utteranceId);
                        emitter.emit("Exponent.speakingStopped", idToMap);
                    }

                    @Override // android.speech.tts.UtteranceProgressListener
                    public void onError(String utteranceId) {
                        Bundle idToMap;
                        Intrinsics.checkNotNullParameter(utteranceId, "utteranceId");
                        EventEmitter emitter = getEmitter();
                        idToMap = SpeechModule.this.idToMap(utteranceId);
                        emitter.emit("Exponent.speakingError", idToMap);
                    }
                });
                queue = this$0.delayedUtterances;
                for (SpeechModule.Utterance utterance : queue) {
                    this$0.speakOut(utterance.component1(), utterance.component2(), utterance.component3());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }
}
