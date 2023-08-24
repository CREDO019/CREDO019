package expo.modules.kotlin.types;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: Either.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u0002*\b\b\u0003\u0010\u0005*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0002\u0010\bJ\u000b\u0010\t\u001a\u00028\u0003¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00028\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00030\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00030\rH\u0007¢\u0006\u0002\b\u0012¨\u0006\u0013"}, m183d2 = {"Lexpo/modules/kotlin/types/EitherOfFour;", "FirstType", "", "SecondType", "ThirdType", "FourthType", "Lexpo/modules/kotlin/types/EitherOfThree;", "value", "(Ljava/lang/Object;)V", "fourth", "()Ljava/lang/Object;", "get", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KClass;", "getFourthType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "is", "", "isFourthType", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EitherOfFour<FirstType, SecondType, ThirdType, FourthType> extends EitherOfThree<FirstType, SecondType, ThirdType> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherOfFour(Object value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public final boolean isFourthType(KClass<FourthType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type.isInstance(getValue());
    }

    public final FourthType getFourthType(KClass<FourthType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (FourthType) getValue();
    }

    public final FourthType fourth() {
        return (FourthType) getValue();
    }
}
