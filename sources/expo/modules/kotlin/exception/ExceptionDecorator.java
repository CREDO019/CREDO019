package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, m183d2 = {"exceptionDecorator", "T", "decoratorBlock", "Lkotlin/Function1;", "Lexpo/modules/kotlin/exception/CodedException;", "Lkotlin/ParameterName;", "name", "e", "", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.kotlin.exception.ExceptionDecoratorKt */
/* loaded from: classes4.dex */
public final class ExceptionDecorator {
    public static final <T> T exceptionDecorator(Function1<? super CodedException, ? extends Throwable> decoratorBlock, Functions<? extends T> block) throws CodedException {
        Intrinsics.checkNotNullParameter(decoratorBlock, "decoratorBlock");
        Intrinsics.checkNotNullParameter(block, "block");
        try {
            return block.invoke();
        } catch (expo.modules.core.errors.CodedException e) {
            String code = e.getCode();
            Intrinsics.checkNotNullExpressionValue(code, "e.code");
            throw decoratorBlock.invoke(new CodedException(code, e.getMessage(), e.getCause()));
        } catch (CodedException e2) {
            throw decoratorBlock.invoke(e2);
        } catch (Throwable th) {
            throw decoratorBlock.invoke(new UnexpectedException(th));
        }
    }
}
