package expo.modules.kotlin.types;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: Either.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u000b\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\u001d\u0010\u000b\u001a\u00028\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u000b\u001a\u00028\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0007¢\u0006\u0004\b\u0010\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00122\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0007¢\u0006\u0002\b\u0013J\u001b\u0010\u0011\u001a\u00020\u00122\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0007¢\u0006\u0002\b\u0014J\u000b\u0010\u0015\u001a\u00028\u0001¢\u0006\u0002\u0010\tR\u001c\u0010\u0004\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/kotlin/types/Either;", "FirstType", "", "SecondType", "value", "(Ljava/lang/Object;)V", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "first", "get", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KClass;", "getFirstType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getSecondType", "is", "", "isFirstType", "isSecondType", "second", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class Either<FirstType, SecondType> {
    private final Object value;

    public static /* synthetic */ void getValue$annotations() {
    }

    public Either(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
    }

    public final Object getValue() {
        return this.value;
    }

    public final boolean isFirstType(KClass<FirstType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type.isInstance(this.value);
    }

    public final boolean isSecondType(KClass<SecondType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return type.isInstance(this.value);
    }

    public final FirstType getFirstType(KClass<FirstType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (FirstType) this.value;
    }

    public final SecondType getSecondType(KClass<SecondType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (SecondType) this.value;
    }

    public final FirstType first() {
        return (FirstType) this.value;
    }

    public final SecondType second() {
        return (SecondType) this.value;
    }
}
