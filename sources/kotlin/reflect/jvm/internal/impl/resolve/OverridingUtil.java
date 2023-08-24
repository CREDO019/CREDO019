package kotlin.reflect.jvm.internal.impl.resolve;

import com.onesignal.NotificationBundleProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerImpl;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* loaded from: classes5.dex */
public class OverridingUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final OverridingUtil DEFAULT;
    private static final KotlinTypeChecker.TypeConstructorEquality DEFAULT_TYPE_CONSTRUCTOR_EQUALITY;
    private static final List<ExternalOverridabilityCondition> EXTERNAL_CONDITIONS = CollectionsKt.toList(ServiceLoader.load(ExternalOverridabilityCondition.class, ExternalOverridabilityCondition.class.getClassLoader()));
    private final KotlinTypeChecker.TypeConstructorEquality equalityAxioms;
    private final KotlinTypeRefiner kotlinTypeRefiner;

    /* JADX WARN: Removed duplicated region for block: B:105:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x024f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0058 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0163 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r24) {
        /*
            Method dump skipped, instructions count: 1316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.$$$reportNull$$$0(int):void");
    }

    static {
        KotlinTypeChecker.TypeConstructorEquality typeConstructorEquality = new KotlinTypeChecker.TypeConstructorEquality() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.1
            private static /* synthetic */ void $$$reportNull$$$0(int r3) {
                Object[] objArr = new Object[3];
                if (r3 != 1) {
                    objArr[0] = NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY;
                } else {
                    objArr[0] = "b";
                }
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
                objArr[2] = "equals";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality
            public boolean equals(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
                if (typeConstructor == null) {
                    $$$reportNull$$$0(0);
                }
                if (typeConstructor2 == null) {
                    $$$reportNull$$$0(1);
                }
                return typeConstructor.equals(typeConstructor2);
            }
        };
        DEFAULT_TYPE_CONSTRUCTOR_EQUALITY = typeConstructorEquality;
        DEFAULT = new OverridingUtil(typeConstructorEquality, KotlinTypeRefiner.Default.INSTANCE);
    }

    public static OverridingUtil createWithTypeRefiner(KotlinTypeRefiner kotlinTypeRefiner) {
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(1);
        }
        return new OverridingUtil(DEFAULT_TYPE_CONSTRUCTOR_EQUALITY, kotlinTypeRefiner);
    }

    public static OverridingUtil create(KotlinTypeRefiner kotlinTypeRefiner, KotlinTypeChecker.TypeConstructorEquality typeConstructorEquality) {
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(2);
        }
        if (typeConstructorEquality == null) {
            $$$reportNull$$$0(3);
        }
        return new OverridingUtil(typeConstructorEquality, kotlinTypeRefiner);
    }

    private OverridingUtil(KotlinTypeChecker.TypeConstructorEquality typeConstructorEquality, KotlinTypeRefiner kotlinTypeRefiner) {
        if (typeConstructorEquality == null) {
            $$$reportNull$$$0(4);
        }
        if (kotlinTypeRefiner == null) {
            $$$reportNull$$$0(5);
        }
        this.equalityAxioms = typeConstructorEquality;
        this.kotlinTypeRefiner = kotlinTypeRefiner;
    }

    public static <D extends CallableDescriptor> Set<D> filterOutOverridden(Set<D> set) {
        if (set == null) {
            $$$reportNull$$$0(6);
        }
        return filterOverrides(set, !set.isEmpty() && kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtils.isTypeRefinementEnabled(kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtils.getModule(set.iterator().next())), null, new Function2<D, D, Tuples<CallableDescriptor, CallableDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.2
            /* JADX WARN: Incorrect types in method signature: (TD;TD;)Lkotlin/Pair<Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;>; */
            @Override // kotlin.jvm.functions.Function2
            public Tuples invoke(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
                return new Tuples(callableDescriptor, callableDescriptor2);
            }
        });
    }

    public static <D> Set<D> filterOverrides(Set<D> set, boolean z, Functions<?> functions, Function2<? super D, ? super D, Tuples<CallableDescriptor, CallableDescriptor>> function2) {
        if (set == null) {
            $$$reportNull$$$0(7);
        }
        if (function2 == null) {
            $$$reportNull$$$0(8);
        }
        if (set.size() <= 1) {
            if (set == null) {
                $$$reportNull$$$0(9);
            }
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : set) {
            if (functions != null) {
                functions.invoke();
            }
            Iterator it = linkedHashSet.iterator();
            while (true) {
                if (it.hasNext()) {
                    Tuples<CallableDescriptor, CallableDescriptor> invoke = function2.invoke(obj, (Object) it.next());
                    CallableDescriptor component1 = invoke.component1();
                    CallableDescriptor component2 = invoke.component2();
                    if (overrides(component1, component2, z, true)) {
                        it.remove();
                    } else if (overrides(component2, component1, z, true)) {
                        break;
                    }
                } else {
                    linkedHashSet.add(obj);
                    break;
                }
            }
        }
        return linkedHashSet;
    }

    public static <D extends CallableDescriptor> boolean overrides(D d, D d2, boolean z, boolean z2) {
        if (d == null) {
            $$$reportNull$$$0(11);
        }
        if (d2 == null) {
            $$$reportNull$$$0(12);
        }
        if (d.equals(d2) || !DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(d.getOriginal(), d2.getOriginal(), z, z2)) {
            CallableDescriptor original = d2.getOriginal();
            for (CallableDescriptor callableDescriptor : DescriptorUtils.getAllOverriddenDescriptors(d)) {
                if (DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(original, callableDescriptor, z, z2)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static Set<CallableMemberDescriptor> getOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor) {
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(13);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectOverriddenDeclarations(callableMemberDescriptor, linkedHashSet);
        return linkedHashSet;
    }

    private static void collectOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor, Set<CallableMemberDescriptor> set) {
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(15);
        }
        if (set == null) {
            $$$reportNull$$$0(16);
        }
        if (callableMemberDescriptor.getKind().isReal()) {
            set.add(callableMemberDescriptor);
        } else if (callableMemberDescriptor.getOverriddenDescriptors().isEmpty()) {
            throw new IllegalStateException("No overridden descriptors found for (fake override) " + callableMemberDescriptor);
        } else {
            for (CallableMemberDescriptor callableMemberDescriptor2 : callableMemberDescriptor.getOverriddenDescriptors()) {
                collectOverriddenDeclarations(callableMemberDescriptor2, set);
            }
        }
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(17);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(18);
        }
        OverrideCompatibilityInfo isOverridableBy = isOverridableBy(callableDescriptor, callableDescriptor2, classDescriptor, false);
        if (isOverridableBy == null) {
            $$$reportNull$$$0(19);
        }
        return isOverridableBy;
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, ClassDescriptor classDescriptor, boolean z) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(20);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(21);
        }
        OverrideCompatibilityInfo isOverridableByWithoutExternalConditions = isOverridableByWithoutExternalConditions(callableDescriptor, callableDescriptor2, z);
        boolean z2 = isOverridableByWithoutExternalConditions.getResult() == OverrideCompatibilityInfo.Result.OVERRIDABLE;
        for (ExternalOverridabilityCondition externalOverridabilityCondition : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition.getContract() != ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY && (!z2 || externalOverridabilityCondition.getContract() != ExternalOverridabilityCondition.Contract.SUCCESS_ONLY)) {
                int r3 = C48658.f1524x42e2f52f[externalOverridabilityCondition.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor).ordinal()];
                if (r3 == 1) {
                    z2 = true;
                } else if (r3 == 2) {
                    OverrideCompatibilityInfo conflict = OverrideCompatibilityInfo.conflict("External condition failed");
                    if (conflict == null) {
                        $$$reportNull$$$0(22);
                    }
                    return conflict;
                } else if (r3 == 3) {
                    OverrideCompatibilityInfo incompatible = OverrideCompatibilityInfo.incompatible("External condition");
                    if (incompatible == null) {
                        $$$reportNull$$$0(23);
                    }
                    return incompatible;
                }
            }
        }
        if (!z2) {
            if (isOverridableByWithoutExternalConditions == null) {
                $$$reportNull$$$0(24);
            }
            return isOverridableByWithoutExternalConditions;
        }
        for (ExternalOverridabilityCondition externalOverridabilityCondition2 : EXTERNAL_CONDITIONS) {
            if (externalOverridabilityCondition2.getContract() == ExternalOverridabilityCondition.Contract.CONFLICTS_ONLY) {
                int r1 = C48658.f1524x42e2f52f[externalOverridabilityCondition2.isOverridable(callableDescriptor, callableDescriptor2, classDescriptor).ordinal()];
                if (r1 == 1) {
                    throw new IllegalStateException("Contract violation in " + externalOverridabilityCondition2.getClass().getName() + " condition. It's not supposed to end with success");
                } else if (r1 == 2) {
                    OverrideCompatibilityInfo conflict2 = OverrideCompatibilityInfo.conflict("External condition failed");
                    if (conflict2 == null) {
                        $$$reportNull$$$0(25);
                    }
                    return conflict2;
                } else if (r1 == 3) {
                    OverrideCompatibilityInfo incompatible2 = OverrideCompatibilityInfo.incompatible("External condition");
                    if (incompatible2 == null) {
                        $$$reportNull$$$0(26);
                    }
                    return incompatible2;
                }
            }
        }
        OverrideCompatibilityInfo success = OverrideCompatibilityInfo.success();
        if (success == null) {
            $$$reportNull$$$0(27);
        }
        return success;
    }

    public OverrideCompatibilityInfo isOverridableByWithoutExternalConditions(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(28);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(29);
        }
        OverrideCompatibilityInfo basicOverridabilityProblem = getBasicOverridabilityProblem(callableDescriptor, callableDescriptor2);
        if (basicOverridabilityProblem != null) {
            if (basicOverridabilityProblem == null) {
                $$$reportNull$$$0(30);
            }
            return basicOverridabilityProblem;
        }
        List<KotlinType> compiledValueParameters = compiledValueParameters(callableDescriptor);
        List<KotlinType> compiledValueParameters2 = compiledValueParameters(callableDescriptor2);
        List<TypeParameterDescriptor> typeParameters = callableDescriptor.getTypeParameters();
        List<TypeParameterDescriptor> typeParameters2 = callableDescriptor2.getTypeParameters();
        int r6 = 0;
        if (typeParameters.size() != typeParameters2.size()) {
            while (r6 < compiledValueParameters.size()) {
                if (!KotlinTypeChecker.DEFAULT.equalTypes(compiledValueParameters.get(r6), compiledValueParameters2.get(r6))) {
                    OverrideCompatibilityInfo incompatible = OverrideCompatibilityInfo.incompatible("Type parameter number mismatch");
                    if (incompatible == null) {
                        $$$reportNull$$$0(31);
                    }
                    return incompatible;
                }
                r6++;
            }
            OverrideCompatibilityInfo conflict = OverrideCompatibilityInfo.conflict("Type parameter number mismatch");
            if (conflict == null) {
                $$$reportNull$$$0(32);
            }
            return conflict;
        }
        Tuples<NewKotlinTypeCheckerImpl, TypeCheckerState> createTypeChecker = createTypeChecker(typeParameters, typeParameters2);
        for (int r5 = 0; r5 < typeParameters.size(); r5++) {
            if (!areTypeParametersEquivalent(typeParameters.get(r5), typeParameters2.get(r5), createTypeChecker)) {
                OverrideCompatibilityInfo incompatible2 = OverrideCompatibilityInfo.incompatible("Type parameter bounds mismatch");
                if (incompatible2 == null) {
                    $$$reportNull$$$0(33);
                }
                return incompatible2;
            }
        }
        for (int r2 = 0; r2 < compiledValueParameters.size(); r2++) {
            if (!areTypesEquivalent(compiledValueParameters.get(r2), compiledValueParameters2.get(r2), createTypeChecker)) {
                OverrideCompatibilityInfo incompatible3 = OverrideCompatibilityInfo.incompatible("Value parameter type mismatch");
                if (incompatible3 == null) {
                    $$$reportNull$$$0(34);
                }
                return incompatible3;
            }
        }
        if ((callableDescriptor instanceof FunctionDescriptor) && (callableDescriptor2 instanceof FunctionDescriptor) && ((FunctionDescriptor) callableDescriptor).isSuspend() != ((FunctionDescriptor) callableDescriptor2).isSuspend()) {
            OverrideCompatibilityInfo conflict2 = OverrideCompatibilityInfo.conflict("Incompatible suspendability");
            if (conflict2 == null) {
                $$$reportNull$$$0(35);
            }
            return conflict2;
        }
        if (z) {
            KotlinType returnType = callableDescriptor.getReturnType();
            KotlinType returnType2 = callableDescriptor2.getReturnType();
            if (returnType != null && returnType2 != null) {
                if (KotlinTypeKt.isError(returnType2) && KotlinTypeKt.isError(returnType)) {
                    r6 = 1;
                }
                if (r6 == 0 && !createTypeChecker.getFirst().isSubtypeOf(createTypeChecker.getSecond(), returnType2.unwrap(), returnType.unwrap())) {
                    OverrideCompatibilityInfo conflict3 = OverrideCompatibilityInfo.conflict("Return type mismatch");
                    if (conflict3 == null) {
                        $$$reportNull$$$0(36);
                    }
                    return conflict3;
                }
            }
        }
        OverrideCompatibilityInfo success = OverrideCompatibilityInfo.success();
        if (success == null) {
            $$$reportNull$$$0(37);
        }
        return success;
    }

    public static OverrideCompatibilityInfo getBasicOverridabilityProblem(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        boolean z;
        if (callableDescriptor == null) {
            $$$reportNull$$$0(38);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(39);
        }
        boolean z2 = callableDescriptor instanceof FunctionDescriptor;
        if ((!z2 || (callableDescriptor2 instanceof FunctionDescriptor)) && (!((z = callableDescriptor instanceof PropertyDescriptor)) || (callableDescriptor2 instanceof PropertyDescriptor))) {
            if (!z2 && !z) {
                throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + callableDescriptor);
            } else if (!callableDescriptor.getName().equals(callableDescriptor2.getName())) {
                return OverrideCompatibilityInfo.incompatible("Name mismatch");
            } else {
                OverrideCompatibilityInfo checkReceiverAndParameterCount = checkReceiverAndParameterCount(callableDescriptor, callableDescriptor2);
                if (checkReceiverAndParameterCount != null) {
                    return checkReceiverAndParameterCount;
                }
                return null;
            }
        }
        return OverrideCompatibilityInfo.incompatible("Member kind mismatch");
    }

    private Tuples<NewKotlinTypeCheckerImpl, TypeCheckerState> createTypeChecker(List<TypeParameterDescriptor> list, List<TypeParameterDescriptor> list2) {
        if (list == null) {
            $$$reportNull$$$0(40);
        }
        if (list2 == null) {
            $$$reportNull$$$0(41);
        }
        return new Tuples<>(new NewKotlinTypeCheckerImpl(this.kotlinTypeRefiner, KotlinTypePreparator.Default.INSTANCE), createTypeCheckerState(list, list2));
    }

    private TypeCheckerState createTypeCheckerState(List<TypeParameterDescriptor> list, List<TypeParameterDescriptor> list2) {
        if (list == null) {
            $$$reportNull$$$0(42);
        }
        if (list2 == null) {
            $$$reportNull$$$0(43);
        }
        if (list.isEmpty()) {
            TypeCheckerState newTypeCheckerState = new OverridingUtilTypeSystemContext(null, this.equalityAxioms, this.kotlinTypeRefiner).newTypeCheckerState(true, true);
            if (newTypeCheckerState == null) {
                $$$reportNull$$$0(44);
            }
            return newTypeCheckerState;
        }
        HashMap hashMap = new HashMap();
        for (int r2 = 0; r2 < list.size(); r2++) {
            hashMap.put(list.get(r2).getTypeConstructor(), list2.get(r2).getTypeConstructor());
        }
        TypeCheckerState newTypeCheckerState2 = new OverridingUtilTypeSystemContext(hashMap, this.equalityAxioms, this.kotlinTypeRefiner).newTypeCheckerState(true, true);
        if (newTypeCheckerState2 == null) {
            $$$reportNull$$$0(45);
        }
        return newTypeCheckerState2;
    }

    private static OverrideCompatibilityInfo checkReceiverAndParameterCount(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        if ((callableDescriptor.getExtensionReceiverParameter() == null) != (callableDescriptor2.getExtensionReceiverParameter() == null)) {
            return OverrideCompatibilityInfo.incompatible("Receiver presence mismatch");
        }
        if (callableDescriptor.getValueParameters().size() != callableDescriptor2.getValueParameters().size()) {
            return OverrideCompatibilityInfo.incompatible("Value parameter number mismatch");
        }
        return null;
    }

    private boolean areTypesEquivalent(KotlinType kotlinType, KotlinType kotlinType2, Tuples<NewKotlinTypeCheckerImpl, TypeCheckerState> tuples) {
        if (kotlinType == null) {
            $$$reportNull$$$0(46);
        }
        if (kotlinType2 == null) {
            $$$reportNull$$$0(47);
        }
        if (tuples == null) {
            $$$reportNull$$$0(48);
        }
        if (KotlinTypeKt.isError(kotlinType) && KotlinTypeKt.isError(kotlinType2)) {
            return true;
        }
        return tuples.getFirst().equalTypes(tuples.getSecond(), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r1.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean areTypeParametersEquivalent(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r5, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r6, kotlin.Tuples<kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeCheckerImpl, kotlin.reflect.jvm.internal.impl.types.TypeCheckerState> r7) {
        /*
            r4 = this;
            if (r5 != 0) goto L7
            r0 = 49
            $$$reportNull$$$0(r0)
        L7:
            if (r6 != 0) goto Le
            r0 = 50
            $$$reportNull$$$0(r0)
        Le:
            if (r7 != 0) goto L15
            r0 = 51
            $$$reportNull$$$0(r0)
        L15:
            java.util.List r5 = r5.getUpperBounds()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List r6 = r6.getUpperBounds()
            r0.<init>(r6)
            int r6 = r5.size()
            int r1 = r0.size()
            r2 = 0
            if (r6 == r1) goto L2e
            return r2
        L2e:
            java.util.Iterator r5 = r5.iterator()
        L32:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L59
            java.lang.Object r6 = r5.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r6 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r6
            java.util.ListIterator r1 = r0.listIterator()
        L42:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L58
            java.lang.Object r3 = r1.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r3
            boolean r3 = r4.areTypesEquivalent(r6, r3, r7)
            if (r3 == 0) goto L42
            r1.remove()
            goto L32
        L58:
            return r2
        L59:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.areTypeParametersEquivalent(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, kotlin.Pair):boolean");
    }

    private static List<KotlinType> compiledValueParameters(CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        ArrayList arrayList = new ArrayList();
        if (extensionReceiverParameter != null) {
            arrayList.add(extensionReceiverParameter.getType());
        }
        for (ValueParameterDescriptor valueParameterDescriptor : callableDescriptor.getValueParameters()) {
            arrayList.add(valueParameterDescriptor.getType());
        }
        return arrayList;
    }

    public void generateOverridesInFunctionGroup(Name name, Collection<? extends CallableMemberDescriptor> collection, Collection<? extends CallableMemberDescriptor> collection2, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        if (name == null) {
            $$$reportNull$$$0(52);
        }
        if (collection == null) {
            $$$reportNull$$$0(53);
        }
        if (collection2 == null) {
            $$$reportNull$$$0(54);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(55);
        }
        if (overridingStrategy == null) {
            $$$reportNull$$$0(56);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        for (CallableMemberDescriptor callableMemberDescriptor : collection2) {
            linkedHashSet.removeAll(extractAndBindOverridesForMember(callableMemberDescriptor, collection, classDescriptor, overridingStrategy));
        }
        createAndBindFakeOverrides(classDescriptor, linkedHashSet, overridingStrategy);
    }

    public static boolean isVisibleForOverride(MemberDescriptor memberDescriptor, MemberDescriptor memberDescriptor2) {
        if (memberDescriptor == null) {
            $$$reportNull$$$0(57);
        }
        if (memberDescriptor2 == null) {
            $$$reportNull$$$0(58);
        }
        return !DescriptorVisibilities.isPrivate(memberDescriptor2.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(memberDescriptor2, memberDescriptor);
    }

    private Collection<CallableMemberDescriptor> extractAndBindOverridesForMember(CallableMemberDescriptor callableMemberDescriptor, Collection<? extends CallableMemberDescriptor> collection, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(59);
        }
        if (collection == null) {
            $$$reportNull$$$0(60);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(61);
        }
        if (overridingStrategy == null) {
            $$$reportNull$$$0(62);
        }
        ArrayList arrayList = new ArrayList(collection.size());
        SmartSet create = SmartSet.create();
        for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
            OverrideCompatibilityInfo.Result result = isOverridableBy(callableMemberDescriptor2, callableMemberDescriptor, classDescriptor).getResult();
            boolean isVisibleForOverride = isVisibleForOverride(callableMemberDescriptor, callableMemberDescriptor2);
            int r3 = C48658.f1525xb0ae9f3b[result.ordinal()];
            if (r3 == 1) {
                if (isVisibleForOverride) {
                    create.add(callableMemberDescriptor2);
                }
                arrayList.add(callableMemberDescriptor2);
            } else if (r3 == 2) {
                if (isVisibleForOverride) {
                    overridingStrategy.overrideConflict(callableMemberDescriptor2, callableMemberDescriptor);
                }
                arrayList.add(callableMemberDescriptor2);
            }
        }
        overridingStrategy.setOverriddenDescriptors(callableMemberDescriptor, create);
        return arrayList;
    }

    private static boolean allHasSameContainingDeclaration(Collection<CallableMemberDescriptor> collection) {
        if (collection == null) {
            $$$reportNull$$$0(63);
        }
        if (collection.size() < 2) {
            return true;
        }
        final DeclarationDescriptor containingDeclaration = collection.iterator().next().getContainingDeclaration();
        return CollectionsKt.all(collection, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.3
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(callableMemberDescriptor.getContainingDeclaration() == DeclarationDescriptor.this);
            }
        });
    }

    private static void createAndBindFakeOverrides(ClassDescriptor classDescriptor, Collection<CallableMemberDescriptor> collection, OverridingStrategy overridingStrategy) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(64);
        }
        if (collection == null) {
            $$$reportNull$$$0(65);
        }
        if (overridingStrategy == null) {
            $$$reportNull$$$0(66);
        }
        if (allHasSameContainingDeclaration(collection)) {
            for (CallableMemberDescriptor callableMemberDescriptor : collection) {
                createAndBindFakeOverride(Collections.singleton(callableMemberDescriptor), classDescriptor, overridingStrategy);
            }
            return;
        }
        LinkedList linkedList = new LinkedList(collection);
        while (!linkedList.isEmpty()) {
            createAndBindFakeOverride(extractMembersOverridableInBothWays(VisibilityUtil.findMemberWithMaxVisibility(linkedList), linkedList, overridingStrategy), classDescriptor, overridingStrategy);
        }
    }

    public static boolean isMoreSpecific(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(67);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(68);
        }
        KotlinType returnType = callableDescriptor.getReturnType();
        KotlinType returnType2 = callableDescriptor2.getReturnType();
        if (isVisibilityMoreSpecific(callableDescriptor, callableDescriptor2)) {
            Tuples<NewKotlinTypeCheckerImpl, TypeCheckerState> createTypeChecker = DEFAULT.createTypeChecker(callableDescriptor.getTypeParameters(), callableDescriptor2.getTypeParameters());
            if (callableDescriptor instanceof FunctionDescriptor) {
                return isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2, createTypeChecker);
            }
            if (callableDescriptor instanceof PropertyDescriptor) {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) callableDescriptor;
                PropertyDescriptor propertyDescriptor2 = (PropertyDescriptor) callableDescriptor2;
                if (isAccessorMoreSpecific(propertyDescriptor.getSetter(), propertyDescriptor2.getSetter())) {
                    if (propertyDescriptor.isVar() && propertyDescriptor2.isVar()) {
                        return createTypeChecker.getFirst().equalTypes(createTypeChecker.getSecond(), returnType.unwrap(), returnType2.unwrap());
                    }
                    return (propertyDescriptor.isVar() || !propertyDescriptor2.isVar()) && isReturnTypeMoreSpecific(callableDescriptor, returnType, callableDescriptor2, returnType2, createTypeChecker);
                }
                return false;
            }
            throw new IllegalArgumentException("Unexpected callable: " + callableDescriptor.getClass());
        }
        return false;
    }

    private static boolean isVisibilityMoreSpecific(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(69);
        }
        if (declarationDescriptorWithVisibility2 == null) {
            $$$reportNull$$$0(70);
        }
        Integer compare = DescriptorVisibilities.compare(declarationDescriptorWithVisibility.getVisibility(), declarationDescriptorWithVisibility2.getVisibility());
        return compare == null || compare.intValue() >= 0;
    }

    private static boolean isAccessorMoreSpecific(PropertyAccessorDescriptor propertyAccessorDescriptor, PropertyAccessorDescriptor propertyAccessorDescriptor2) {
        if (propertyAccessorDescriptor == null || propertyAccessorDescriptor2 == null) {
            return true;
        }
        return isVisibilityMoreSpecific(propertyAccessorDescriptor, propertyAccessorDescriptor2);
    }

    private static boolean isMoreSpecificThenAllOf(CallableDescriptor callableDescriptor, Collection<CallableDescriptor> collection) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(71);
        }
        if (collection == null) {
            $$$reportNull$$$0(72);
        }
        for (CallableDescriptor callableDescriptor2 : collection) {
            if (!isMoreSpecific(callableDescriptor, callableDescriptor2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReturnTypeMoreSpecific(CallableDescriptor callableDescriptor, KotlinType kotlinType, CallableDescriptor callableDescriptor2, KotlinType kotlinType2, Tuples<NewKotlinTypeCheckerImpl, TypeCheckerState> tuples) {
        if (callableDescriptor == null) {
            $$$reportNull$$$0(73);
        }
        if (kotlinType == null) {
            $$$reportNull$$$0(74);
        }
        if (callableDescriptor2 == null) {
            $$$reportNull$$$0(75);
        }
        if (kotlinType2 == null) {
            $$$reportNull$$$0(76);
        }
        if (tuples == null) {
            $$$reportNull$$$0(77);
        }
        return tuples.getFirst().isSubtypeOf(tuples.getSecond(), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <H> H selectMostSpecificMember(Collection<H> collection, Function1<H, CallableDescriptor> function1) {
        if (collection == null) {
            $$$reportNull$$$0(78);
        }
        if (function1 == 0) {
            $$$reportNull$$$0(79);
        }
        if (collection.size() == 1) {
            H h = (H) CollectionsKt.first(collection);
            if (h == null) {
                $$$reportNull$$$0(80);
            }
            return h;
        }
        ArrayList arrayList = new ArrayList(2);
        List map = CollectionsKt.map(collection, function1);
        H h2 = (H) CollectionsKt.first(collection);
        CallableDescriptor callableDescriptor = (CallableDescriptor) function1.invoke(h2);
        for (H h3 : collection) {
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) function1.invoke(h3);
            if (isMoreSpecificThenAllOf(callableDescriptor2, map)) {
                arrayList.add(h3);
            }
            if (isMoreSpecific(callableDescriptor2, callableDescriptor) && !isMoreSpecific(callableDescriptor, callableDescriptor2)) {
                h2 = h3;
            }
        }
        if (arrayList.isEmpty()) {
            if (h2 == null) {
                $$$reportNull$$$0(81);
            }
            return h2;
        } else if (arrayList.size() == 1) {
            H h4 = (H) CollectionsKt.first((Iterable<? extends Object>) arrayList);
            if (h4 == null) {
                $$$reportNull$$$0(82);
            }
            return h4;
        } else {
            H h5 = null;
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (!FlexibleTypesKt.isFlexible(((CallableDescriptor) function1.invoke(next)).getReturnType())) {
                    h5 = next;
                    break;
                }
            }
            if (h5 != null) {
                if (h5 == null) {
                    $$$reportNull$$$0(83);
                }
                return h5;
            }
            H h6 = (H) CollectionsKt.first((Iterable<? extends Object>) arrayList);
            if (h6 == null) {
                $$$reportNull$$$0(84);
            }
            return h6;
        }
    }

    private static void createAndBindFakeOverride(Collection<CallableMemberDescriptor> collection, ClassDescriptor classDescriptor, OverridingStrategy overridingStrategy) {
        if (collection == null) {
            $$$reportNull$$$0(85);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(86);
        }
        if (overridingStrategy == null) {
            $$$reportNull$$$0(87);
        }
        Collection<CallableMemberDescriptor> filterVisibleFakeOverrides = filterVisibleFakeOverrides(classDescriptor, collection);
        boolean isEmpty = filterVisibleFakeOverrides.isEmpty();
        if (!isEmpty) {
            collection = filterVisibleFakeOverrides;
        }
        CallableMemberDescriptor copy = ((CallableMemberDescriptor) selectMostSpecificMember(collection, new Function1<CallableMemberDescriptor, CallableDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.4
            @Override // kotlin.jvm.functions.Function1
            public CallableMemberDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return callableMemberDescriptor;
            }
        })).copy(classDescriptor, determineModalityForFakeOverride(collection, classDescriptor), isEmpty ? DescriptorVisibilities.INVISIBLE_FAKE : DescriptorVisibilities.INHERITED, CallableMemberDescriptor.Kind.FAKE_OVERRIDE, false);
        overridingStrategy.setOverriddenDescriptors(copy, collection);
        overridingStrategy.addFakeOverride(copy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil$8 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C48658 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$descriptors$Modality;

        /* renamed from: $SwitchMap$org$jetbrains$kotlin$resolve$ExternalOverridabilityCondition$Result */
        static final /* synthetic */ int[] f1524x42e2f52f;

        /* renamed from: $SwitchMap$org$jetbrains$kotlin$resolve$OverridingUtil$OverrideCompatibilityInfo$Result */
        static final /* synthetic */ int[] f1525xb0ae9f3b;

        static {
            int[] r0 = new int[Modality.values().length];
            $SwitchMap$org$jetbrains$kotlin$descriptors$Modality = r0;
            try {
                r0[Modality.FINAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$descriptors$Modality[Modality.SEALED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$descriptors$Modality[Modality.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$descriptors$Modality[Modality.ABSTRACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] r4 = new int[OverrideCompatibilityInfo.Result.values().length];
            f1525xb0ae9f3b = r4;
            try {
                r4[OverrideCompatibilityInfo.Result.OVERRIDABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1525xb0ae9f3b[OverrideCompatibilityInfo.Result.CONFLICT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1525xb0ae9f3b[OverrideCompatibilityInfo.Result.INCOMPATIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            int[] r42 = new int[ExternalOverridabilityCondition.Result.values().length];
            f1524x42e2f52f = r42;
            try {
                r42[ExternalOverridabilityCondition.Result.OVERRIDABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1524x42e2f52f[ExternalOverridabilityCondition.Result.CONFLICT.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1524x42e2f52f[ExternalOverridabilityCondition.Result.INCOMPATIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1524x42e2f52f[ExternalOverridabilityCondition.Result.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private static Modality determineModalityForFakeOverride(Collection<CallableMemberDescriptor> collection, ClassDescriptor classDescriptor) {
        if (collection == null) {
            $$$reportNull$$$0(88);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(89);
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (CallableMemberDescriptor callableMemberDescriptor : collection) {
            int r6 = C48658.$SwitchMap$org$jetbrains$kotlin$descriptors$Modality[callableMemberDescriptor.getModality().ordinal()];
            if (r6 == 1) {
                Modality modality = Modality.FINAL;
                if (modality == null) {
                    $$$reportNull$$$0(90);
                }
                return modality;
            } else if (r6 == 2) {
                throw new IllegalStateException("Member cannot have SEALED modality: " + callableMemberDescriptor);
            } else if (r6 == 3) {
                z2 = true;
            } else if (r6 == 4) {
                z3 = true;
            }
        }
        if (classDescriptor.isExpect() && classDescriptor.getModality() != Modality.ABSTRACT && classDescriptor.getModality() != Modality.SEALED) {
            z = true;
        }
        if (z2 && !z3) {
            Modality modality2 = Modality.OPEN;
            if (modality2 == null) {
                $$$reportNull$$$0(91);
            }
            return modality2;
        } else if (!z2 && z3) {
            Modality modality3 = z ? classDescriptor.getModality() : Modality.ABSTRACT;
            if (modality3 == null) {
                $$$reportNull$$$0(92);
            }
            return modality3;
        } else {
            HashSet hashSet = new HashSet();
            for (CallableMemberDescriptor callableMemberDescriptor2 : collection) {
                hashSet.addAll(getOverriddenDeclarations(callableMemberDescriptor2));
            }
            return getMinimalModality(filterOutOverridden(hashSet), z, classDescriptor.getModality());
        }
    }

    private static Modality getMinimalModality(Collection<CallableMemberDescriptor> collection, boolean z, Modality modality) {
        if (collection == null) {
            $$$reportNull$$$0(93);
        }
        if (modality == null) {
            $$$reportNull$$$0(94);
        }
        Modality modality2 = Modality.ABSTRACT;
        for (CallableMemberDescriptor callableMemberDescriptor : collection) {
            Modality modality3 = (z && callableMemberDescriptor.getModality() == Modality.ABSTRACT) ? modality : callableMemberDescriptor.getModality();
            if (modality3.compareTo(modality2) < 0) {
                modality2 = modality3;
            }
        }
        if (modality2 == null) {
            $$$reportNull$$$0(95);
        }
        return modality2;
    }

    private static Collection<CallableMemberDescriptor> filterVisibleFakeOverrides(final ClassDescriptor classDescriptor, Collection<CallableMemberDescriptor> collection) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(96);
        }
        if (collection == null) {
            $$$reportNull$$$0(97);
        }
        List filter = CollectionsKt.filter(collection, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.5
            @Override // kotlin.jvm.functions.Function1
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(!DescriptorVisibilities.isPrivate(callableMemberDescriptor.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(callableMemberDescriptor, ClassDescriptor.this));
            }
        });
        if (filter == null) {
            $$$reportNull$$$0(98);
        }
        return filter;
    }

    public static <H> Collection<H> extractMembersOverridableInBothWays(H h, Collection<H> collection, Function1<H, CallableDescriptor> function1, Function1<H, Unit> function12) {
        if (h == null) {
            $$$reportNull$$$0(99);
        }
        if (collection == null) {
            $$$reportNull$$$0(100);
        }
        if (function1 == null) {
            $$$reportNull$$$0(101);
        }
        if (function12 == null) {
            $$$reportNull$$$0(102);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(h);
        CallableDescriptor invoke = function1.invoke(h);
        Iterator<H> it = collection.iterator();
        while (it.hasNext()) {
            H next = it.next();
            CallableDescriptor invoke2 = function1.invoke(next);
            if (h == next) {
                it.remove();
            } else {
                OverrideCompatibilityInfo.Result bothWaysOverridability = getBothWaysOverridability(invoke, invoke2);
                if (bothWaysOverridability == OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                    arrayList.add(next);
                    it.remove();
                } else if (bothWaysOverridability == OverrideCompatibilityInfo.Result.CONFLICT) {
                    function12.invoke(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public static OverrideCompatibilityInfo.Result getBothWaysOverridability(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        OverridingUtil overridingUtil = DEFAULT;
        OverrideCompatibilityInfo.Result result = overridingUtil.isOverridableBy(callableDescriptor2, callableDescriptor, null).getResult();
        OverrideCompatibilityInfo.Result result2 = overridingUtil.isOverridableBy(callableDescriptor, callableDescriptor2, null).getResult();
        return (result == OverrideCompatibilityInfo.Result.OVERRIDABLE && result2 == OverrideCompatibilityInfo.Result.OVERRIDABLE) ? OverrideCompatibilityInfo.Result.OVERRIDABLE : (result == OverrideCompatibilityInfo.Result.CONFLICT || result2 == OverrideCompatibilityInfo.Result.CONFLICT) ? OverrideCompatibilityInfo.Result.CONFLICT : OverrideCompatibilityInfo.Result.INCOMPATIBLE;
    }

    private static Collection<CallableMemberDescriptor> extractMembersOverridableInBothWays(final CallableMemberDescriptor callableMemberDescriptor, Queue<CallableMemberDescriptor> queue, final OverridingStrategy overridingStrategy) {
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(104);
        }
        if (queue == null) {
            $$$reportNull$$$0(105);
        }
        if (overridingStrategy == null) {
            $$$reportNull$$$0(106);
        }
        return extractMembersOverridableInBothWays(callableMemberDescriptor, queue, new Function1<CallableMemberDescriptor, CallableDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.6
            @Override // kotlin.jvm.functions.Function1
            public CallableDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor2) {
                return callableMemberDescriptor2;
            }
        }, new Function1<CallableMemberDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.7
            @Override // kotlin.jvm.functions.Function1
            public Unit invoke(CallableMemberDescriptor callableMemberDescriptor2) {
                OverridingStrategy.this.inheritanceConflict(callableMemberDescriptor, callableMemberDescriptor2);
                return Unit.INSTANCE;
            }
        });
    }

    public static void resolveUnknownVisibilityForMember(CallableMemberDescriptor callableMemberDescriptor, Function1<CallableMemberDescriptor, Unit> function1) {
        DescriptorVisibility descriptorVisibility;
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(107);
        }
        for (CallableMemberDescriptor callableMemberDescriptor2 : callableMemberDescriptor.getOverriddenDescriptors()) {
            if (callableMemberDescriptor2.getVisibility() == DescriptorVisibilities.INHERITED) {
                resolveUnknownVisibilityForMember(callableMemberDescriptor2, function1);
            }
        }
        if (callableMemberDescriptor.getVisibility() != DescriptorVisibilities.INHERITED) {
            return;
        }
        DescriptorVisibility computeVisibilityToInherit = computeVisibilityToInherit(callableMemberDescriptor);
        if (computeVisibilityToInherit == null) {
            if (function1 != null) {
                function1.invoke(callableMemberDescriptor);
            }
            descriptorVisibility = DescriptorVisibilities.PUBLIC;
        } else {
            descriptorVisibility = computeVisibilityToInherit;
        }
        if (callableMemberDescriptor instanceof PropertyDescriptorImpl) {
            ((PropertyDescriptorImpl) callableMemberDescriptor).setVisibility(descriptorVisibility);
            for (PropertyAccessorDescriptor propertyAccessorDescriptor : ((PropertyDescriptor) callableMemberDescriptor).getAccessors()) {
                resolveUnknownVisibilityForMember(propertyAccessorDescriptor, computeVisibilityToInherit == null ? null : function1);
            }
        } else if (callableMemberDescriptor instanceof FunctionDescriptorImpl) {
            ((FunctionDescriptorImpl) callableMemberDescriptor).setVisibility(descriptorVisibility);
        } else {
            PropertyAccessorDescriptorImpl propertyAccessorDescriptorImpl = (PropertyAccessorDescriptorImpl) callableMemberDescriptor;
            propertyAccessorDescriptorImpl.setVisibility(descriptorVisibility);
            if (descriptorVisibility != propertyAccessorDescriptorImpl.getCorrespondingProperty().getVisibility()) {
                propertyAccessorDescriptorImpl.setDefault(false);
            }
        }
    }

    private static DescriptorVisibility computeVisibilityToInherit(CallableMemberDescriptor callableMemberDescriptor) {
        if (callableMemberDescriptor == null) {
            $$$reportNull$$$0(108);
        }
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        DescriptorVisibility findMaxVisibility = findMaxVisibility(overriddenDescriptors);
        if (findMaxVisibility == null) {
            return null;
        }
        if (callableMemberDescriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            for (CallableMemberDescriptor callableMemberDescriptor2 : overriddenDescriptors) {
                if (callableMemberDescriptor2.getModality() != Modality.ABSTRACT && !callableMemberDescriptor2.getVisibility().equals(findMaxVisibility)) {
                    return null;
                }
            }
            return findMaxVisibility;
        }
        return findMaxVisibility.normalize();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility findMaxVisibility(java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> r5) {
        /*
            if (r5 != 0) goto L7
            r0 = 109(0x6d, float:1.53E-43)
            $$$reportNull$$$0(r0)
        L7:
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L10
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r5 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.DEFAULT_VISIBILITY
            return r5
        L10:
            java.util.Iterator r0 = r5.iterator()
            r1 = 0
        L15:
            r2 = r1
        L16:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L38
            java.lang.Object r3 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r3
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r3 = r3.getVisibility()
            if (r2 != 0) goto L2a
        L28:
            r2 = r3
            goto L16
        L2a:
            java.lang.Integer r4 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.compare(r3, r2)
            if (r4 != 0) goto L31
            goto L15
        L31:
            int r4 = r4.intValue()
            if (r4 <= 0) goto L16
            goto L28
        L38:
            if (r2 != 0) goto L3b
            return r1
        L3b:
            java.util.Iterator r5 = r5.iterator()
        L3f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L5c
            java.lang.Object r0 = r5.next()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r0 = r0.getVisibility()
            java.lang.Integer r0 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.compare(r2, r0)
            if (r0 == 0) goto L5b
            int r0 = r0.intValue()
            if (r0 >= 0) goto L3f
        L5b:
            return r1
        L5c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.findMaxVisibility(java.util.Collection):kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility");
    }

    /* loaded from: classes5.dex */
    public static class OverrideCompatibilityInfo {
        private static final OverrideCompatibilityInfo SUCCESS = new OverrideCompatibilityInfo(Result.OVERRIDABLE, "SUCCESS");
        private final String debugMessage;
        private final Result overridable;

        /* loaded from: classes5.dex */
        public enum Result {
            OVERRIDABLE,
            INCOMPATIBLE,
            CONFLICT
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x005a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static /* synthetic */ void $$$reportNull$$$0(int r10) {
            /*
                r0 = 4
                r1 = 3
                r2 = 2
                r3 = 1
                if (r10 == r3) goto Lf
                if (r10 == r2) goto Lf
                if (r10 == r1) goto Lf
                if (r10 == r0) goto Lf
                java.lang.String r4 = "@NotNull method %s.%s must not return null"
                goto L11
            Lf:
                java.lang.String r4 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            L11:
                if (r10 == r3) goto L1b
                if (r10 == r2) goto L1b
                if (r10 == r1) goto L1b
                if (r10 == r0) goto L1b
                r5 = 2
                goto L1c
            L1b:
                r5 = 3
            L1c:
                java.lang.Object[] r5 = new java.lang.Object[r5]
                java.lang.String r6 = "success"
                java.lang.String r7 = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo"
                r8 = 0
                if (r10 == r3) goto L31
                if (r10 == r2) goto L31
                if (r10 == r1) goto L2e
                if (r10 == r0) goto L31
                r5[r8] = r7
                goto L35
            L2e:
                r5[r8] = r6
                goto L35
            L31:
                java.lang.String r9 = "debugMessage"
                r5[r8] = r9
            L35:
                switch(r10) {
                    case 1: goto L45;
                    case 2: goto L45;
                    case 3: goto L45;
                    case 4: goto L45;
                    case 5: goto L40;
                    case 6: goto L3b;
                    default: goto L38;
                }
            L38:
                r5[r3] = r6
                goto L47
            L3b:
                java.lang.String r6 = "getDebugMessage"
                r5[r3] = r6
                goto L47
            L40:
                java.lang.String r6 = "getResult"
                r5[r3] = r6
                goto L47
            L45:
                r5[r3] = r7
            L47:
                if (r10 == r3) goto L5a
                if (r10 == r2) goto L55
                if (r10 == r1) goto L50
                if (r10 == r0) goto L50
                goto L5e
            L50:
                java.lang.String r6 = "<init>"
                r5[r2] = r6
                goto L5e
            L55:
                java.lang.String r6 = "conflict"
                r5[r2] = r6
                goto L5e
            L5a:
                java.lang.String r6 = "incompatible"
                r5[r2] = r6
            L5e:
                java.lang.String r4 = java.lang.String.format(r4, r5)
                if (r10 == r3) goto L70
                if (r10 == r2) goto L70
                if (r10 == r1) goto L70
                if (r10 == r0) goto L70
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                r10.<init>(r4)
                goto L75
            L70:
                java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
                r10.<init>(r4)
            L75:
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.$$$reportNull$$$0(int):void");
        }

        public static OverrideCompatibilityInfo success() {
            OverrideCompatibilityInfo overrideCompatibilityInfo = SUCCESS;
            if (overrideCompatibilityInfo == null) {
                $$$reportNull$$$0(0);
            }
            return overrideCompatibilityInfo;
        }

        public static OverrideCompatibilityInfo incompatible(String str) {
            if (str == null) {
                $$$reportNull$$$0(1);
            }
            return new OverrideCompatibilityInfo(Result.INCOMPATIBLE, str);
        }

        public static OverrideCompatibilityInfo conflict(String str) {
            if (str == null) {
                $$$reportNull$$$0(2);
            }
            return new OverrideCompatibilityInfo(Result.CONFLICT, str);
        }

        public OverrideCompatibilityInfo(Result result, String str) {
            if (result == null) {
                $$$reportNull$$$0(3);
            }
            if (str == null) {
                $$$reportNull$$$0(4);
            }
            this.overridable = result;
            this.debugMessage = str;
        }

        public Result getResult() {
            Result result = this.overridable;
            if (result == null) {
                $$$reportNull$$$0(5);
            }
            return result;
        }
    }
}
