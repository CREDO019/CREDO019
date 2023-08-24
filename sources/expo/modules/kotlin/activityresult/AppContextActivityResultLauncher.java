package expo.modules.kotlin.activityresult;

import androidx.activity.result.ActivityResultCallback;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuationJvm;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;

/* compiled from: AppContextActivityResultLauncher.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0019\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ#\u0010\n\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fH&¢\u0006\u0002\u0010\u0010R\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "I", "Ljava/io/Serializable;", "O", "", "()V", "contract", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "getContract", "()Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "launch", "input", "(Ljava/io/Serializable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "Landroidx/activity/result/ActivityResultCallback;", "(Ljava/io/Serializable;Landroidx/activity/result/ActivityResultCallback;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class AppContextActivityResultLauncher<I extends Serializable, O> {
    public abstract AppContextActivityResultContract<I, O> getContract();

    public abstract void launch(I r1, ActivityResultCallback<O> activityResultCallback);

    public final Object launch(I r4, Continuation<? super O> continuation) {
        SafeContinuationJvm safeContinuationJvm = new SafeContinuationJvm(IntrinsicsKt.intercepted(continuation));
        final SafeContinuationJvm safeContinuationJvm2 = safeContinuationJvm;
        launch((AppContextActivityResultLauncher<I, O>) r4, new ActivityResultCallback() { // from class: expo.modules.kotlin.activityresult.AppContextActivityResultLauncher$launch$2$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(O o) {
                Continuation<O> continuation2 = safeContinuationJvm2;
                Result.Companion companion = Result.Companion;
                continuation2.resumeWith(Result.m1749constructorimpl(o));
            }
        });
        Object orThrow = safeContinuationJvm.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }
}
