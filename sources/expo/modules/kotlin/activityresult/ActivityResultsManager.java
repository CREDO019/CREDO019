package expo.modules.kotlin.activityresult;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.kotlin.activityaware.AppCompatActivityAware;
import expo.modules.kotlin.activityaware.AppCompatActivityAwareHelper;
import expo.modules.kotlin.activityaware.OnActivityAvailableListener;
import expo.modules.kotlin.providers.CurrentActivityProvider;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: ActivityResultsManager.kt */
@Metadata(m184d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J(\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0019JU\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0\u001c\"\b\b\u0000\u0010\u001d*\u00020\u001f\"\u0004\b\u0001\u0010\u001e2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0#H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m183d2 = {"Lexpo/modules/kotlin/activityresult/ActivityResultsManager;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lexpo/modules/kotlin/activityaware/AppCompatActivityAware;", "currentActivityProvider", "Lexpo/modules/kotlin/providers/CurrentActivityProvider;", "(Lexpo/modules/kotlin/providers/CurrentActivityProvider;)V", "activityAwareHelper", "Lexpo/modules/kotlin/activityaware/AppCompatActivityAwareHelper;", "nextLocalRequestCode", "Ljava/util/concurrent/atomic/AtomicInteger;", "registry", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultRegistry;", "addOnActivityAvailableListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lexpo/modules/kotlin/activityaware/OnActivityAvailableListener;", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onHostDestroy", "Landroidx/appcompat/app/AppCompatActivity;", "onHostResume", "registerForActivityResult", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "I", "O", "Ljava/io/Serializable;", "contract", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "fallbackCallback", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;", "(Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeOnActivityAvailableListener", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ActivityResultsManager implements AppContextActivityResultCaller, AppCompatActivityAware {
    private final AppCompatActivityAwareHelper activityAwareHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final AppContextActivityResultRegistry registry;

    public ActivityResultsManager(CurrentActivityProvider currentActivityProvider) {
        Intrinsics.checkNotNullParameter(currentActivityProvider, "currentActivityProvider");
        this.registry = new AppContextActivityResultRegistry(currentActivityProvider);
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityAwareHelper = new AppCompatActivityAwareHelper();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C44561(null), 3, null);
    }

    /* compiled from: ActivityResultsManager.kt */
    @Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, m183d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    @DebugMetadata(m175c = "expo.modules.kotlin.activityresult.ActivityResultsManager$1", m174f = "ActivityResultsManager.kt", m173i = {0}, m172l = {102}, m171m = "invokeSuspend", m170n = {"$this$withActivityAvailable$iv"}, m169s = {"L$0"})
    /* renamed from: expo.modules.kotlin.activityresult.ActivityResultsManager$1 */
    /* loaded from: classes4.dex */
    static final class C44561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        C44561(Continuation<? super C44561> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C44561(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r5v1, types: [expo.modules.kotlin.activityresult.ActivityResultsManager$1$invokeSuspend$$inlined$withActivityAvailable$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                final ActivityResultsManager activityResultsManager = ActivityResultsManager.this;
                final ActivityResultsManager activityResultsManager2 = activityResultsManager;
                this.L$0 = activityResultsManager2;
                this.L$1 = activityResultsManager;
                this.label = 1;
                C44561 c44561 = this;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(c44561), 1);
                cancellableContinuationImpl.initCancellability();
                final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
                final ?? r5 = new OnActivityAvailableListener() { // from class: expo.modules.kotlin.activityresult.ActivityResultsManager$1$invokeSuspend$$inlined$withActivityAvailable$1
                    @Override // expo.modules.kotlin.activityaware.OnActivityAvailableListener
                    public void onActivityAvailable(AppCompatActivity activity) {
                        Object m1749constructorimpl;
                        AppContextActivityResultRegistry appContextActivityResultRegistry;
                        Intrinsics.checkNotNullParameter(activity, "activity");
                        expo.modules.kotlin.activityaware.AppCompatActivityAware.this.removeOnActivityAvailableListener(this);
                        CancellableContinuation cancellableContinuation = cancellableContinuationImpl2;
                        try {
                            Result.Companion companion = Result.Companion;
                            AppCompatActivityAware appCompatActivityAware = this;
                            appContextActivityResultRegistry = activityResultsManager.registry;
                            appContextActivityResultRegistry.restoreInstanceState(activity);
                            m1749constructorimpl = Result.m1749constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            m1749constructorimpl = Result.m1749constructorimpl(ResultKt.createFailure(th));
                        }
                        cancellableContinuation.resumeWith(m1749constructorimpl);
                    }
                };
                activityResultsManager2.addOnActivityAvailableListener((OnActivityAvailableListener) r5);
                cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: expo.modules.kotlin.activityresult.ActivityResultsManager$1$invokeSuspend$$inlined$withActivityAvailable$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        AppCompatActivityAware.this.removeOnActivityAvailableListener(r5);
                    }
                });
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbes.probeCoroutineSuspended(c44561);
                }
                if (result == obj2) {
                    return obj2;
                }
            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ActivityResultsManager activityResultsManager3 = (ActivityResultsManager) this.L$1;
                AppCompatActivityAware appCompatActivityAware = (AppCompatActivityAware) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void onActivityResult(Activity activity, int r3, int r4, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.registry.dispatchResult(r3, r4, intent);
    }

    public final void onHostResume(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activityAwareHelper.dispatchOnActivityAvailable(activity);
    }

    public final void onHostDestroy(AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.registry.persistInstanceState(activity);
    }

    /* JADX WARN: Type inference failed for: r9v0, types: [expo.modules.kotlin.activityresult.ActivityResultsManager$registerForActivityResult$$inlined$withActivityAvailable$1] */
    @Override // expo.modules.kotlin.activityresult.AppContextActivityResultCaller
    public <I extends Serializable, O> Object registerForActivityResult(final AppContextActivityResultContract<I, O> appContextActivityResultContract, final AppContextActivityResultFallbackCallback<I, O> appContextActivityResultFallbackCallback, Continuation<? super AppContextActivityResultLauncher<I, O>> continuation) {
        final ActivityResultsManager activityResultsManager = this;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        final ?? r9 = new OnActivityAvailableListener() { // from class: expo.modules.kotlin.activityresult.ActivityResultsManager$registerForActivityResult$$inlined$withActivityAvailable$1
            @Override // expo.modules.kotlin.activityaware.OnActivityAvailableListener
            public void onActivityAvailable(AppCompatActivity activity) {
                Object m1749constructorimpl;
                AppContextActivityResultRegistry appContextActivityResultRegistry;
                AtomicInteger atomicInteger;
                Intrinsics.checkNotNullParameter(activity, "activity");
                AppCompatActivityAware.this.removeOnActivityAvailableListener(this);
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl2;
                try {
                    Result.Companion companion = Result.Companion;
                    C4458x27195394 c4458x27195394 = this;
                    appContextActivityResultRegistry = this.registry;
                    atomicInteger = this.nextLocalRequestCode;
                    int andIncrement = atomicInteger.getAndIncrement();
                    m1749constructorimpl = Result.m1749constructorimpl(appContextActivityResultRegistry.register("AppContext_rq#" + andIncrement, activity, appContextActivityResultContract, appContextActivityResultFallbackCallback));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    m1749constructorimpl = Result.m1749constructorimpl(ResultKt.createFailure(th));
                }
                cancellableContinuation.resumeWith(m1749constructorimpl);
            }
        };
        activityResultsManager.addOnActivityAvailableListener((OnActivityAvailableListener) r9);
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: expo.modules.kotlin.activityresult.ActivityResultsManager$registerForActivityResult$$inlined$withActivityAvailable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                AppCompatActivityAware.this.removeOnActivityAvailableListener(r9);
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void addOnActivityAvailableListener(OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.activityAwareHelper.addOnActivityAvailableListener(listener);
    }

    @Override // expo.modules.kotlin.activityaware.AppCompatActivityAware
    public void removeOnActivityAvailableListener(OnActivityAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.activityAwareHelper.removeOnActivityAvailableListener(listener);
    }
}
