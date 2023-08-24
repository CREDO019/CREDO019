package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: SignatureBuildingComponents.kt */
/* loaded from: classes5.dex */
public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    public final String javaLang(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/lang/", name);
    }

    public final String javaUtil(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/util/", name);
    }

    public final String javaFunction(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.stringPlus("java/util/function/", name);
    }

    public final Set<String> inJavaLang(String name, String... signatures) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        return inClass(javaLang(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    public final Set<String> inJavaUtil(String name, String... signatures) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        return inClass(javaUtil(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    public final Set<String> inClass(String internalName, String... signatures) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int length = signatures.length;
        int r2 = 0;
        while (r2 < length) {
            String str = signatures[r2];
            r2++;
            linkedHashSet.add(internalName + '.' + str);
        }
        return linkedHashSet;
    }

    public final String signature(String internalName, String jvmDescriptor) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        Intrinsics.checkNotNullParameter(jvmDescriptor, "jvmDescriptor");
        return internalName + '.' + jvmDescriptor;
    }

    public final String jvmDescriptor(String name, List<String> parameters, String ret) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(ret, "ret");
        return name + '(' + CollectionsKt.joinToString$default(parameters, "", null, null, 0, null, new Function1<String, CharSequence>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents$jvmDescriptor$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                String escapeClassName;
                Intrinsics.checkNotNullParameter(it, "it");
                escapeClassName = SignatureBuildingComponents.INSTANCE.escapeClassName(it);
                return escapeClassName;
            }
        }, 30, null) + ')' + escapeClassName(ret);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String escapeClassName(String str) {
        if (str.length() > 1) {
            return Matrix.MATRIX_TYPE_RANDOM_LT + str + ';';
        }
        return str;
    }

    public final String[] constructors(String... signatures) {
        Intrinsics.checkNotNullParameter(signatures, "signatures");
        ArrayList arrayList = new ArrayList(signatures.length);
        int length = signatures.length;
        int r3 = 0;
        while (r3 < length) {
            String str = signatures[r3];
            r3++;
            arrayList.add("<init>(" + str + ")V");
        }
        Object[] array = arrayList.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[]) array;
    }
}
