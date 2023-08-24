package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancementBuilder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt */
/* loaded from: classes5.dex */
public final class predefinedEnhancementInfo {
    private static final Map<String, PredefinedFunctionEnhancementInfo> PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    private static final typeQualifiers NULLABLE = new typeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);
    private static final typeQualifiers NOT_PLATFORM = new typeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);
    private static final typeQualifiers NOT_NULLABLE = new typeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);

    static {
        final SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        final String javaLang = signatureBuildingComponents.javaLang("Object");
        final String javaFunction = signatureBuildingComponents.javaFunction("Predicate");
        final String javaFunction2 = signatureBuildingComponents.javaFunction("Function");
        final String javaFunction3 = signatureBuildingComponents.javaFunction("Consumer");
        final String javaFunction4 = signatureBuildingComponents.javaFunction("BiFunction");
        final String javaFunction5 = signatureBuildingComponents.javaFunction("BiConsumer");
        final String javaFunction6 = signatureBuildingComponents.javaFunction("UnaryOperator");
        final String javaUtil = signatureBuildingComponents.javaUtil("stream/Stream");
        final String javaUtil2 = signatureBuildingComponents.javaUtil("Optional");
        SignatureEnhancementBuilder signatureEnhancementBuilder = new SignatureEnhancementBuilder();
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Iterator")).function("forEachRemaining", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction3;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers, typequalifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaLang("Iterable")).function("spliterator", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String javaUtil3 = SignatureBuildingComponents.this.javaUtil("Spliterator");
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(javaUtil3, typequalifiers, typequalifiers2);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Collection"));
        classEnhancementBuilder.function("removeIf", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers, typequalifiers2);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        classEnhancementBuilder.function("stream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaUtil;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str, typequalifiers, typequalifiers2);
            }
        });
        classEnhancementBuilder.function("parallelStream", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$3$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaUtil;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str, typequalifiers, typequalifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("List")).function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$4$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction6;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers, typequalifiers2);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder2 = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaUtil("Map"));
        classEnhancementBuilder2.function("forEach", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction5;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers, typequalifiers2, typequalifiers3);
            }
        });
        classEnhancementBuilder2.function("putIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
                String str3 = javaLang;
                typequalifiers3 = predefinedEnhancementInfo.NULLABLE;
                function.returns(str3, typequalifiers3);
            }
        });
        classEnhancementBuilder2.function("replace", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
                String str3 = javaLang;
                typequalifiers3 = predefinedEnhancementInfo.NULLABLE;
                function.returns(str3, typequalifiers3);
            }
        });
        classEnhancementBuilder2.function("replace", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
                String str3 = javaLang;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str3, typequalifiers3);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        classEnhancementBuilder2.function("replaceAll", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                typeQualifiers typequalifiers4;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction4;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers4 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers, typequalifiers2, typequalifiers3, typequalifiers4);
            }
        });
        classEnhancementBuilder2.function("compute", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$6
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                typeQualifiers typequalifiers4;
                typeQualifiers typequalifiers5;
                typeQualifiers typequalifiers6;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaFunction4;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers4 = predefinedEnhancementInfo.NULLABLE;
                typequalifiers5 = predefinedEnhancementInfo.NULLABLE;
                function.parameter(str2, typequalifiers2, typequalifiers3, typequalifiers4, typequalifiers5);
                String str3 = javaLang;
                typequalifiers6 = predefinedEnhancementInfo.NULLABLE;
                function.returns(str3, typequalifiers6);
            }
        });
        classEnhancementBuilder2.function("computeIfAbsent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$7
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                typeQualifiers typequalifiers4;
                typeQualifiers typequalifiers5;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaFunction2;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers4 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2, typequalifiers3, typequalifiers4);
                String str3 = javaLang;
                typequalifiers5 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str3, typequalifiers5);
            }
        });
        classEnhancementBuilder2.function("computeIfPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$8
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                typeQualifiers typequalifiers4;
                typeQualifiers typequalifiers5;
                typeQualifiers typequalifiers6;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaFunction4;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers4 = predefinedEnhancementInfo.NOT_NULLABLE;
                typequalifiers5 = predefinedEnhancementInfo.NULLABLE;
                function.parameter(str2, typequalifiers2, typequalifiers3, typequalifiers4, typequalifiers5);
                String str3 = javaLang;
                typequalifiers6 = predefinedEnhancementInfo.NULLABLE;
                function.returns(str3, typequalifiers6);
            }
        });
        classEnhancementBuilder2.function("merge", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$5$9
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                typeQualifiers typequalifiers4;
                typeQualifiers typequalifiers5;
                typeQualifiers typequalifiers6;
                typeQualifiers typequalifiers7;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_NULLABLE;
                function.parameter(str2, typequalifiers2);
                String str3 = javaFunction4;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers4 = predefinedEnhancementInfo.NOT_NULLABLE;
                typequalifiers5 = predefinedEnhancementInfo.NOT_NULLABLE;
                typequalifiers6 = predefinedEnhancementInfo.NULLABLE;
                function.parameter(str3, typequalifiers3, typequalifiers4, typequalifiers5, typequalifiers6);
                String str4 = javaLang;
                typequalifiers7 = predefinedEnhancementInfo.NULLABLE;
                function.returns(str4, typequalifiers7);
            }
        });
        SignatureEnhancementBuilder.ClassEnhancementBuilder classEnhancementBuilder3 = new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaUtil2);
        classEnhancementBuilder3.function("empty", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaUtil2;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_NULLABLE;
                function.returns(str, typequalifiers, typequalifiers2);
            }
        });
        classEnhancementBuilder3.function("of", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_NULLABLE;
                function.parameter(str, typequalifiers);
                String str2 = javaUtil2;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_NULLABLE;
                function.returns(str2, typequalifiers2, typequalifiers3);
            }
        });
        classEnhancementBuilder3.function("ofNullable", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NULLABLE;
                function.parameter(str, typequalifiers);
                String str2 = javaUtil2;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers3 = predefinedEnhancementInfo.NOT_NULLABLE;
                function.returns(str2, typequalifiers2, typequalifiers3);
            }
        });
        classEnhancementBuilder3.function("get", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_NULLABLE;
                function.returns(str, typequalifiers);
            }
        });
        classEnhancementBuilder3.function("ifPresent", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$6$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaFunction3;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                typequalifiers2 = predefinedEnhancementInfo.NOT_NULLABLE;
                function.parameter(str, typequalifiers, typequalifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaLang("ref/Reference")).function("get", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$7$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NULLABLE;
                function.returns(str, typequalifiers);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction).function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$8$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaFunction("BiPredicate")).function("test", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$9$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
                function.returns(JvmPrimitiveType.BOOLEAN);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction3).function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$10$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction5).function("accept", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$11$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction2).function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$12$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str2, typequalifiers2);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, javaFunction4).function("apply", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$13$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                typeQualifiers typequalifiers2;
                typeQualifiers typequalifiers3;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str, typequalifiers);
                String str2 = javaLang;
                typequalifiers2 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.parameter(str2, typequalifiers2);
                String str3 = javaLang;
                typequalifiers3 = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str3, typequalifiers3);
            }
        });
        new SignatureEnhancementBuilder.ClassEnhancementBuilder(signatureEnhancementBuilder, signatureBuildingComponents.javaFunction("Supplier")).function("get", new Function1<SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.PredefinedEnhancementInfoKt$PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE$1$1$14$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder functionEnhancementBuilder) {
                invoke2(functionEnhancementBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(SignatureEnhancementBuilder.ClassEnhancementBuilder.FunctionEnhancementBuilder function) {
                typeQualifiers typequalifiers;
                Intrinsics.checkNotNullParameter(function, "$this$function");
                String str = javaLang;
                typequalifiers = predefinedEnhancementInfo.NOT_PLATFORM;
                function.returns(str, typequalifiers);
            }
        });
        PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatureEnhancementBuilder.build();
    }

    public static final Map<String, PredefinedFunctionEnhancementInfo> getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}
