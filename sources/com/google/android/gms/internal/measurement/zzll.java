package com.google.android.gms.internal.measurement;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzll {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzlj zzljVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzljVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzb(StringBuilder sb, int r7, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, r7, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, r7, str, entry);
            }
        } else {
            sb.append('\n');
            int r0 = 0;
            for (int r1 = 0; r1 < r7; r1++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzmj.zza(zzjb.zzm((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzjb) {
                sb.append(": \"");
                sb.append(zzmj.zza((zzjb) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzkc) {
                sb.append(" {");
                zzd((zzkc) obj, sb, r7 + 2);
                sb.append("\n");
                while (r0 < r7) {
                    sb.append(' ');
                    r0++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int r8 = r7 + 2;
                zzb(sb, r8, "key", entry2.getKey());
                zzb(sb, r8, "value", entry2.getValue());
                sb.append("\n");
                while (r0 < r7) {
                    sb.append(' ');
                    r0++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zzd(zzlj zzljVar, StringBuilder sb, int r14) {
        Method[] declaredMethods;
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzljVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String concat = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 4)));
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, r14, zzc(concat), zzkc.zzbK(method2, zzljVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String concat2 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 3)));
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, r14, zzc(concat2), zzkc.zzbK(method3, zzljVar, new Object[0]));
                }
            }
            if (((Method) hashMap2.get("set".concat(String.valueOf(substring)))) != null && (!substring.endsWith("Bytes") || !hashMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                String concat3 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1)));
                Method method4 = (Method) hashMap.get("get".concat(String.valueOf(substring)));
                Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                if (method4 != null) {
                    Object zzbK = zzkc.zzbK(method4, zzljVar, new Object[0]);
                    if (method5 == null) {
                        if (zzbK instanceof Boolean) {
                            if (((Boolean) zzbK).booleanValue()) {
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                        } else if (zzbK instanceof Integer) {
                            if (((Integer) zzbK).intValue() != 0) {
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                        } else if (zzbK instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzbK).floatValue()) != 0) {
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                        } else if (zzbK instanceof Double) {
                            if (Double.doubleToRawLongBits(((Double) zzbK).doubleValue()) != 0) {
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                        } else {
                            if (zzbK instanceof String) {
                                equals = zzbK.equals("");
                            } else if (zzbK instanceof zzjb) {
                                equals = zzbK.equals(zzjb.zzb);
                            } else if (zzbK instanceof zzlj) {
                                if (zzbK != ((zzlj) zzbK).zzbR()) {
                                    zzb(sb, r14, zzc(concat3), zzbK);
                                }
                            } else {
                                if ((zzbK instanceof Enum) && ((Enum) zzbK).ordinal() == 0) {
                                }
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                            if (!equals) {
                                zzb(sb, r14, zzc(concat3), zzbK);
                            }
                        }
                    } else if (((Boolean) zzkc.zzbK(method5, zzljVar, new Object[0])).booleanValue()) {
                        zzb(sb, r14, zzc(concat3), zzbK);
                    }
                }
            }
        }
        if (!(zzljVar instanceof zzjz)) {
            zzmm zzmmVar = ((zzkc) zzljVar).zzc;
            if (zzmmVar != null) {
                zzmmVar.zzg(sb, r14);
                return;
            }
            return;
        }
        zzjt zzjtVar = ((zzjz) zzljVar).zza;
        throw null;
    }
}
