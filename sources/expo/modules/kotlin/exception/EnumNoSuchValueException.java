package expo.modules.kotlin.exception;

import kotlin.Metadata;

/* compiled from: CodedException.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/exception/EnumNoSuchValueException;", "Lexpo/modules/kotlin/exception/CodedException;", "enumType", "Lkotlin/reflect/KClass;", "", "enumConstants", "", "value", "", "(Lkotlin/reflect/KClass;[Ljava/lang/Enum;Ljava/lang/Object;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EnumNoSuchValueException extends CodedException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public EnumNoSuchValueException(kotlin.reflect.KClass<java.lang.Enum<?>> r11, java.lang.Enum<?>[] r12, java.lang.Object r13) {
        /*
            r10 = this;
            java.lang.String r0 = "enumType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "enumConstants"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r11 = r11.getSimpleName()
            java.lang.String r0 = ", "
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            expo.modules.kotlin.exception.EnumNoSuchValueException$1 r0 = new kotlin.jvm.functions.Function1<java.lang.Enum<?>, java.lang.CharSequence>() { // from class: expo.modules.kotlin.exception.EnumNoSuchValueException.1
                static {
                    /*
                        expo.modules.kotlin.exception.EnumNoSuchValueException$1 r0 = new expo.modules.kotlin.exception.EnumNoSuchValueException$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:expo.modules.kotlin.exception.EnumNoSuchValueException$1) expo.modules.kotlin.exception.EnumNoSuchValueException.1.INSTANCE expo.modules.kotlin.exception.EnumNoSuchValueException$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.EnumNoSuchValueException.C44621.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.EnumNoSuchValueException.C44621.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(java.lang.Enum<?> r3) {
                    /*
                        r2 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                        java.lang.String r3 = r3.name()
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        r0.<init>()
                        java.lang.String r1 = "'"
                        r0.append(r1)
                        r0.append(r3)
                        r0.append(r1)
                        java.lang.String r3 = r0.toString()
                        java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.EnumNoSuchValueException.C44621.invoke(java.lang.Enum):java.lang.CharSequence");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.CharSequence invoke(java.lang.Enum<?> r1) {
                    /*
                        r0 = this;
                        java.lang.Enum r1 = (java.lang.Enum) r1
                        java.lang.CharSequence r1 = r0.invoke(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.EnumNoSuchValueException.C44621.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r7 = r0
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r8 = 30
            r9 = 0
            r1 = r12
            java.lang.String r12 = kotlin.collections.ArraysKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "'"
            r0.append(r1)
            r0.append(r13)
            java.lang.String r13 = "' is not present in "
            r0.append(r13)
            r0.append(r11)
            java.lang.String r11 = " enum, it must be one of: "
            r0.append(r11)
            r0.append(r12)
            java.lang.String r11 = r0.toString()
            r12 = 0
            r13 = 2
            r10.<init>(r11, r12, r13, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.EnumNoSuchValueException.<init>(kotlin.reflect.KClass, java.lang.Enum[], java.lang.Object):void");
    }
}
