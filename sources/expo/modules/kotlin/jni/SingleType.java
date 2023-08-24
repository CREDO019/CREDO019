package expo.modules.kotlin.jni;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpectedType.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/jni/SingleType;", "", "expectedCppType", "Lexpo/modules/kotlin/jni/CppType;", "parameterTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "(Lexpo/modules/kotlin/jni/CppType;[Lexpo/modules/kotlin/jni/ExpectedType;)V", "getExpectedCppType$expo_modules_core_release", "()Lexpo/modules/kotlin/jni/CppType;", "getParameterTypes", "()[Lexpo/modules/kotlin/jni/ExpectedType;", "[Lexpo/modules/kotlin/jni/ExpectedType;", "getCppType", "", "getFirstParameterType", "getSecondParameterType", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SingleType {
    private final CppType expectedCppType;
    private final ExpectedType[] parameterTypes;

    public SingleType(CppType expectedCppType, ExpectedType[] expectedTypeArr) {
        Intrinsics.checkNotNullParameter(expectedCppType, "expectedCppType");
        this.expectedCppType = expectedCppType;
        this.parameterTypes = expectedTypeArr;
    }

    public /* synthetic */ SingleType(CppType cppType, ExpectedType[] expectedTypeArr, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(cppType, (r3 & 2) != 0 ? null : expectedTypeArr);
    }

    public final CppType getExpectedCppType$expo_modules_core_release() {
        return this.expectedCppType;
    }

    public final ExpectedType[] getParameterTypes() {
        return this.parameterTypes;
    }

    public final int getCppType() {
        return this.expectedCppType.getValue();
    }

    public final ExpectedType getFirstParameterType() {
        ExpectedType[] expectedTypeArr = this.parameterTypes;
        if (expectedTypeArr == null) {
            return null;
        }
        return expectedTypeArr[0];
    }

    public final ExpectedType getSecondParameterType() {
        ExpectedType[] expectedTypeArr = this.parameterTypes;
        if (expectedTypeArr == null) {
            return null;
        }
        return expectedTypeArr[1];
    }
}
