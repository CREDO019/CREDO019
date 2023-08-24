package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import androidx.exifinterface.media.ExifInterface;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.internal.progressionUtil;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.p028io.IOUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: ClassMapperLite.kt */
/* loaded from: classes5.dex */
public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();

    /* renamed from: kotlin  reason: collision with root package name */
    private static final String f2597kotlin = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);
    private static final Map<String, String> map;

    private ClassMapperLite() {
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listOf = CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", ExifInterface.LATITUDE_SOUTH, "Int", "I", "Float", "F", "Long", "J", "Double", "D"});
        int progressionLastElement = progressionUtil.getProgressionLastElement(0, listOf.size() - 1, 2);
        if (progressionLastElement >= 0) {
            int r4 = 0;
            while (true) {
                int r5 = r4 + 2;
                StringBuilder sb = new StringBuilder();
                String str = f2597kotlin;
                sb.append(str);
                sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                sb.append((String) listOf.get(r4));
                int r9 = r4 + 1;
                linkedHashMap.put(sb.toString(), listOf.get(r9));
                linkedHashMap.put(str + IOUtils.DIR_SEPARATOR_UNIX + ((String) listOf.get(r4)) + "Array", Intrinsics.stringPlus("[", listOf.get(r9)));
                if (r4 == progressionLastElement) {
                    break;
                }
                r4 = r5;
            }
        }
        linkedHashMap.put(Intrinsics.stringPlus(f2597kotlin, "/Unit"), ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        m3009map$lambda0$add(linkedHashMap, "Any", "java/lang/Object");
        m3009map$lambda0$add(linkedHashMap, "Nothing", "java/lang/Void");
        m3009map$lambda0$add(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            m3009map$lambda0$add(linkedHashMap, str2, Intrinsics.stringPlus("java/lang/", str2));
        }
        for (String str3 : CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"})) {
            m3009map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("collections/", str3), Intrinsics.stringPlus("java/util/", str3));
            m3009map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("collections/Mutable", str3), Intrinsics.stringPlus("java/util/", str3));
        }
        m3009map$lambda0$add(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        m3009map$lambda0$add(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        m3009map$lambda0$add(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        m3009map$lambda0$add(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int r2 = 0; r2 < 23; r2++) {
            String stringPlus = Intrinsics.stringPlus("Function", Integer.valueOf(r2));
            StringBuilder sb2 = new StringBuilder();
            String str4 = f2597kotlin;
            sb2.append(str4);
            sb2.append("/jvm/functions/Function");
            sb2.append(r2);
            m3009map$lambda0$add(linkedHashMap, stringPlus, sb2.toString());
            m3009map$lambda0$add(linkedHashMap, Intrinsics.stringPlus("reflect/KFunction", Integer.valueOf(r2)), Intrinsics.stringPlus(str4, "/reflect/KFunction"));
        }
        for (String str5 : CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"})) {
            m3009map$lambda0$add(linkedHashMap, Intrinsics.stringPlus(str5, ".Companion"), f2597kotlin + "/jvm/internal/" + str5 + "CompanionObject");
        }
        map = linkedHashMap;
    }

    /* renamed from: map$lambda-0$add  reason: not valid java name */
    private static final void m3009map$lambda0$add(Map<String, String> map2, String str, String str2) {
        map2.put(f2597kotlin + IOUtils.DIR_SEPARATOR_UNIX + str, Matrix.MATRIX_TYPE_RANDOM_LT + str2 + ';');
    }

    @JvmStatic
    public static final String mapClass(String classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        String str = map.get(classId);
        if (str == null) {
            return Matrix.MATRIX_TYPE_RANDOM_LT + StringsKt.replace$default(classId, '.', '$', false, 4, (Object) null) + ';';
        }
        return str;
    }
}
