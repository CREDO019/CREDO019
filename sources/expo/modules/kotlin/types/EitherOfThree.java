package expo.modules.kotlin.types;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: Either.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007J\u001d\u0010\b\u001a\u00028\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\nH\u0007¢\u0006\u0002\b\u000fJ\u000b\u0010\u0010\u001a\u00028\u0002¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/kotlin/types/EitherOfThree;", "FirstType", "", "SecondType", "ThirdType", "Lexpo/modules/kotlin/types/Either;", "value", "(Ljava/lang/Object;)V", "get", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KClass;", "getThirdType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "is", "", "isThirdType", "third", "()Ljava/lang/Object;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class EitherOfThree<FirstType, SecondType, ThirdType> extends Either<FirstType, SecondType> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherOfThree(Object value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public final boolean isThirdType(KClass<ThirdType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type.isInstance(getValue());
    }

    public final ThirdType getThirdType(KClass<ThirdType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (ThirdType) getValue();
    }

    public final ThirdType third() {
        return (ThirdType) getValue();
    }
}
