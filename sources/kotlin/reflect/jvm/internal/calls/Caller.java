package kotlin.reflect.jvm.internal.calls;

import androidx.core.app.NotificationCompat;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Caller.kt */
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u0000*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\u00020\u0003J\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H&¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00142\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0016¢\u0006\u0002\u0010\u0015R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, m183d2 = {"Lkotlin/reflect/jvm/internal/calls/Caller;", "M", "Ljava/lang/reflect/Member;", "", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", NotificationCompat.CATEGORY_CALL, "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "kotlin-reflection"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface Caller<M extends Member> {
    Object call(Object[] objArr);

    /* renamed from: getMember */
    M mo2998getMember();

    List<Type> getParameterTypes();

    Type getReturnType();

    /* compiled from: Caller.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static <M extends Member> void checkArguments(Caller<? extends M> caller, Object[] args) {
            Intrinsics.checkNotNullParameter(caller, "this");
            Intrinsics.checkNotNullParameter(args, "args");
            if (CallerKt.getArity(caller) == args.length) {
                return;
            }
            throw new IllegalArgumentException("Callable expects " + CallerKt.getArity(caller) + " arguments, but " + args.length + " were provided.");
        }
    }
}
