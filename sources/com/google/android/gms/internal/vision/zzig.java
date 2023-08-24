package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzig<T> implements zzir<T> {
    private static final int[] zzys = new int[0];
    private static final Unsafe zzyt = zzjp.zzil();
    private final int[] zzyu;
    private final Object[] zzyv;
    private final int zzyw;
    private final int zzyx;
    private final zzic zzyy;
    private final boolean zzyz;
    private final boolean zzza;
    private final boolean zzzb;
    private final boolean zzzc;
    private final int[] zzzd;
    private final int zzze;
    private final int zzzf;
    private final zzik zzzg;
    private final zzhm zzzh;
    private final zzjj<?, ?> zzzi;
    private final zzgf<?> zzzj;
    private final zzhv zzzk;

    private zzig(int[] r1, Object[] objArr, int r3, int r4, zzic zzicVar, boolean z, boolean z2, int[] r8, int r9, int r10, zzik zzikVar, zzhm zzhmVar, zzjj<?, ?> zzjjVar, zzgf<?> zzgfVar, zzhv zzhvVar) {
        this.zzyu = r1;
        this.zzyv = objArr;
        this.zzyw = r3;
        this.zzyx = r4;
        this.zzza = zzicVar instanceof zzgs;
        this.zzzb = z;
        this.zzyz = zzgfVar != null && zzgfVar.zze(zzicVar);
        this.zzzc = false;
        this.zzzd = r8;
        this.zzze = r9;
        this.zzzf = r10;
        this.zzzg = zzikVar;
        this.zzzh = zzhmVar;
        this.zzzi = zzjjVar;
        this.zzzj = zzgfVar;
        this.zzyy = zzicVar;
        this.zzzk = zzhvVar;
    }

    private static boolean zzbr(int r1) {
        return (r1 & 536870912) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzig<T> zza(Class<T> cls, zzia zziaVar, zzik zzikVar, zzhm zzhmVar, zzjj<?, ?> zzjjVar, zzgf<?> zzgfVar, zzhv zzhvVar) {
        int r10;
        int charAt;
        int charAt2;
        int r3;
        int r9;
        int r12;
        int r15;
        int[] r14;
        int r6;
        int r17;
        char charAt3;
        int r172;
        char charAt4;
        int r173;
        char charAt5;
        int r16;
        char charAt6;
        int r152;
        char charAt7;
        int r142;
        char charAt8;
        int r13;
        char charAt9;
        int r122;
        char charAt10;
        int r27;
        int r2;
        boolean z;
        int r153;
        zzip zzipVar;
        int r123;
        int objectFieldOffset;
        int r31;
        int r0;
        Class<?> cls2;
        String str;
        int r4;
        int r124;
        Field zza;
        int r29;
        char charAt11;
        int r19;
        Field zza2;
        Field zza3;
        int r33;
        char charAt12;
        int r26;
        char charAt13;
        int r262;
        char charAt14;
        int r125;
        char charAt15;
        char charAt16;
        if (zziaVar instanceof zzip) {
            zzip zzipVar2 = (zzip) zziaVar;
            int r32 = 0;
            boolean z2 = zzipVar2.zzhi() == zzgs.zzf.zzwu;
            String zzhp = zzipVar2.zzhp();
            int length = zzhp.length();
            int charAt17 = zzhp.charAt(0);
            if (charAt17 >= 55296) {
                int r5 = charAt17 & 8191;
                int r8 = 1;
                int r92 = 13;
                while (true) {
                    r10 = r8 + 1;
                    charAt16 = zzhp.charAt(r8);
                    if (charAt16 < 55296) {
                        break;
                    }
                    r5 |= (charAt16 & 8191) << r92;
                    r92 += 13;
                    r8 = r10;
                }
                charAt17 = r5 | (charAt16 << r92);
            } else {
                r10 = 1;
            }
            int r82 = r10 + 1;
            int charAt18 = zzhp.charAt(r10);
            if (charAt18 >= 55296) {
                int r93 = charAt18 & 8191;
                int r102 = 13;
                while (true) {
                    r125 = r82 + 1;
                    charAt15 = zzhp.charAt(r82);
                    if (charAt15 < 55296) {
                        break;
                    }
                    r93 |= (charAt15 & 8191) << r102;
                    r102 += 13;
                    r82 = r125;
                }
                charAt18 = r93 | (charAt15 << r102);
                r82 = r125;
            }
            if (charAt18 == 0) {
                r14 = zzys;
                r6 = 0;
                r9 = 0;
                charAt = 0;
                r12 = 0;
                charAt2 = 0;
                r15 = 0;
            } else {
                int r94 = r82 + 1;
                int charAt19 = zzhp.charAt(r82);
                if (charAt19 >= 55296) {
                    int r83 = charAt19 & 8191;
                    int r103 = 13;
                    while (true) {
                        r122 = r94 + 1;
                        charAt10 = zzhp.charAt(r94);
                        if (charAt10 < 55296) {
                            break;
                        }
                        r83 |= (charAt10 & 8191) << r103;
                        r103 += 13;
                        r94 = r122;
                    }
                    charAt19 = r83 | (charAt10 << r103);
                    r94 = r122;
                }
                int r104 = r94 + 1;
                int charAt20 = zzhp.charAt(r94);
                if (charAt20 >= 55296) {
                    int r95 = charAt20 & 8191;
                    int r126 = 13;
                    while (true) {
                        r13 = r104 + 1;
                        charAt9 = zzhp.charAt(r104);
                        if (charAt9 < 55296) {
                            break;
                        }
                        r95 |= (charAt9 & 8191) << r126;
                        r126 += 13;
                        r104 = r13;
                    }
                    charAt20 = r95 | (charAt9 << r126);
                    r104 = r13;
                }
                int r127 = r104 + 1;
                charAt = zzhp.charAt(r104);
                if (charAt >= 55296) {
                    int r105 = charAt & 8191;
                    int r132 = 13;
                    while (true) {
                        r142 = r127 + 1;
                        charAt8 = zzhp.charAt(r127);
                        if (charAt8 < 55296) {
                            break;
                        }
                        r105 |= (charAt8 & 8191) << r132;
                        r132 += 13;
                        r127 = r142;
                    }
                    charAt = r105 | (charAt8 << r132);
                    r127 = r142;
                }
                int r133 = r127 + 1;
                int charAt21 = zzhp.charAt(r127);
                if (charAt21 >= 55296) {
                    int r128 = charAt21 & 8191;
                    int r143 = 13;
                    while (true) {
                        r152 = r133 + 1;
                        charAt7 = zzhp.charAt(r133);
                        if (charAt7 < 55296) {
                            break;
                        }
                        r128 |= (charAt7 & 8191) << r143;
                        r143 += 13;
                        r133 = r152;
                    }
                    charAt21 = r128 | (charAt7 << r143);
                    r133 = r152;
                }
                int r144 = r133 + 1;
                charAt2 = zzhp.charAt(r133);
                if (charAt2 >= 55296) {
                    int r134 = charAt2 & 8191;
                    int r154 = 13;
                    while (true) {
                        r16 = r144 + 1;
                        charAt6 = zzhp.charAt(r144);
                        if (charAt6 < 55296) {
                            break;
                        }
                        r134 |= (charAt6 & 8191) << r154;
                        r154 += 13;
                        r144 = r16;
                    }
                    charAt2 = r134 | (charAt6 << r154);
                    r144 = r16;
                }
                int r155 = r144 + 1;
                int charAt22 = zzhp.charAt(r144);
                if (charAt22 >= 55296) {
                    int r145 = charAt22 & 8191;
                    int r162 = 13;
                    while (true) {
                        r173 = r155 + 1;
                        charAt5 = zzhp.charAt(r155);
                        if (charAt5 < 55296) {
                            break;
                        }
                        r145 |= (charAt5 & 8191) << r162;
                        r162 += 13;
                        r155 = r173;
                    }
                    charAt22 = r145 | (charAt5 << r162);
                    r155 = r173;
                }
                int r163 = r155 + 1;
                int charAt23 = zzhp.charAt(r155);
                if (charAt23 >= 55296) {
                    int r156 = charAt23 & 8191;
                    int r34 = r163;
                    int r164 = 13;
                    while (true) {
                        r172 = r34 + 1;
                        charAt4 = zzhp.charAt(r34);
                        if (charAt4 < 55296) {
                            break;
                        }
                        r156 |= (charAt4 & 8191) << r164;
                        r164 += 13;
                        r34 = r172;
                    }
                    charAt23 = r156 | (charAt4 << r164);
                    r3 = r172;
                } else {
                    r3 = r163;
                }
                int r165 = r3 + 1;
                int charAt24 = zzhp.charAt(r3);
                if (charAt24 >= 55296) {
                    int r35 = charAt24 & 8191;
                    int r62 = r165;
                    int r166 = 13;
                    while (true) {
                        r17 = r62 + 1;
                        charAt3 = zzhp.charAt(r62);
                        if (charAt3 < 55296) {
                            break;
                        }
                        r35 |= (charAt3 & 8191) << r166;
                        r166 += 13;
                        r62 = r17;
                    }
                    charAt24 = r35 | (charAt3 << r166);
                    r165 = r17;
                }
                int r157 = (charAt19 << 1) + charAt20;
                r9 = charAt21;
                r12 = r157;
                r15 = charAt24;
                r32 = charAt19;
                r82 = r165;
                int r352 = charAt22;
                r14 = new int[charAt24 + charAt22 + charAt23];
                r6 = r352;
            }
            Unsafe unsafe = zzyt;
            Object[] zzhq = zzipVar2.zzhq();
            Class<?> cls3 = zzipVar2.zzhk().getClass();
            int r18 = r82;
            int[] r84 = new int[charAt2 * 3];
            Object[] objArr = new Object[charAt2 << 1];
            int r20 = r15 + r6;
            int r22 = r15;
            int r63 = r18;
            int r23 = r20;
            int r182 = 0;
            int r21 = 0;
            while (r63 < length) {
                int r24 = r63 + 1;
                int charAt25 = zzhp.charAt(r63);
                int r25 = length;
                if (charAt25 >= 55296) {
                    int r64 = charAt25 & 8191;
                    int r28 = r24;
                    int r242 = 13;
                    while (true) {
                        r262 = r28 + 1;
                        charAt14 = zzhp.charAt(r28);
                        r27 = r15;
                        if (charAt14 < 55296) {
                            break;
                        }
                        r64 |= (charAt14 & 8191) << r242;
                        r242 += 13;
                        r28 = r262;
                        r15 = r27;
                    }
                    charAt25 = r64 | (charAt14 << r242);
                    r2 = r262;
                } else {
                    r27 = r15;
                    r2 = r24;
                }
                int r158 = r2 + 1;
                int charAt26 = zzhp.charAt(r2);
                if (charAt26 >= 55296) {
                    int r210 = charAt26 & 8191;
                    int r159 = r158;
                    int r243 = 13;
                    while (true) {
                        r26 = r159 + 1;
                        charAt13 = zzhp.charAt(r159);
                        z = z2;
                        if (charAt13 < 55296) {
                            break;
                        }
                        r210 |= (charAt13 & 8191) << r243;
                        r243 += 13;
                        r159 = r26;
                        z2 = z;
                    }
                    charAt26 = r210 | (charAt13 << r243);
                    r153 = r26;
                } else {
                    z = z2;
                    r153 = r158;
                }
                int r11 = charAt26 & 255;
                int r244 = r9;
                if ((charAt26 & 1024) != 0) {
                    r14[r182] = r21;
                    r182++;
                }
                int r30 = charAt;
                if (r11 >= 51) {
                    int r96 = r153 + 1;
                    int charAt27 = zzhp.charAt(r153);
                    char c = 55296;
                    if (charAt27 >= 55296) {
                        int r1510 = charAt27 & 8191;
                        int r322 = 13;
                        while (true) {
                            r33 = r96 + 1;
                            charAt12 = zzhp.charAt(r96);
                            if (charAt12 < c) {
                                break;
                            }
                            r1510 |= (charAt12 & 8191) << r322;
                            r322 += 13;
                            r96 = r33;
                            c = 55296;
                        }
                        charAt27 = r1510 | (charAt12 << r322);
                        r96 = r33;
                    }
                    int r106 = r11 - 51;
                    int r323 = r96;
                    if (r106 == 9 || r106 == 17) {
                        objArr[((r21 / 3) << 1) + 1] = zzhq[r12];
                        r12++;
                    } else if (r106 == 12 && (charAt17 & 1) == 1) {
                        objArr[((r21 / 3) << 1) + 1] = zzhq[r12];
                        r12++;
                    }
                    int r97 = charAt27 << 1;
                    Object obj = zzhq[r97];
                    if (obj instanceof Field) {
                        zza2 = (Field) obj;
                    } else {
                        zza2 = zza(cls3, (String) obj);
                        zzhq[r97] = zza2;
                    }
                    zzipVar = zzipVar2;
                    String str2 = zzhp;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zza2);
                    int r98 = r97 + 1;
                    Object obj2 = zzhq[r98];
                    if (obj2 instanceof Field) {
                        zza3 = (Field) obj2;
                    } else {
                        zza3 = zza(cls3, (String) obj2);
                        zzhq[r98] = zza3;
                    }
                    cls2 = cls3;
                    r31 = r12;
                    r153 = r323;
                    str = str2;
                    r124 = 0;
                    r4 = (int) unsafe.objectFieldOffset(zza3);
                    r0 = r32;
                } else {
                    zzipVar = zzipVar2;
                    String str3 = zzhp;
                    int r02 = r12 + 1;
                    Field zza4 = zza(cls3, (String) zzhq[r12]);
                    if (r11 == 9 || r11 == 17) {
                        r123 = 1;
                        objArr[((r21 / 3) << 1) + 1] = zza4.getType();
                    } else {
                        if (r11 == 27 || r11 == 49) {
                            r123 = 1;
                            r19 = r02 + 1;
                            objArr[((r21 / 3) << 1) + 1] = zzhq[r02];
                        } else if (r11 == 12 || r11 == 30 || r11 == 44) {
                            r123 = 1;
                            if ((charAt17 & 1) == 1) {
                                r19 = r02 + 1;
                                objArr[((r21 / 3) << 1) + 1] = zzhq[r02];
                            }
                        } else {
                            if (r11 == 50) {
                                int r107 = r22 + 1;
                                r14[r22] = r21;
                                int r129 = (r21 / 3) << 1;
                                int r222 = r02 + 1;
                                objArr[r129] = zzhq[r02];
                                if ((charAt26 & 2048) != 0) {
                                    r02 = r222 + 1;
                                    objArr[r129 + 1] = zzhq[r222];
                                    r22 = r107;
                                } else {
                                    r02 = r222;
                                    r123 = 1;
                                    r22 = r107;
                                }
                            }
                            r123 = 1;
                        }
                        r02 = r19;
                    }
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zza4);
                    if ((charAt17 & 1) != r123 || r11 > 17) {
                        r31 = r02;
                        r0 = r32;
                        cls2 = cls3;
                        str = str3;
                        r4 = 0;
                        r124 = 0;
                    } else {
                        int r99 = r153 + 1;
                        str = str3;
                        int charAt28 = str.charAt(r153);
                        if (charAt28 >= 55296) {
                            int r1210 = charAt28 & 8191;
                            int r167 = 13;
                            while (true) {
                                r29 = r99 + 1;
                                charAt11 = str.charAt(r99);
                                if (charAt11 < 55296) {
                                    break;
                                }
                                r1210 |= (charAt11 & 8191) << r167;
                                r167 += 13;
                                r99 = r29;
                            }
                            charAt28 = r1210 | (charAt11 << r167);
                            r99 = r29;
                        }
                        int r192 = (r32 << 1) + (charAt28 / 32);
                        Object obj3 = zzhq[r192];
                        r31 = r02;
                        if (obj3 instanceof Field) {
                            zza = (Field) obj3;
                        } else {
                            zza = zza(cls3, (String) obj3);
                            zzhq[r192] = zza;
                        }
                        r0 = r32;
                        cls2 = cls3;
                        r4 = (int) unsafe.objectFieldOffset(zza);
                        r124 = charAt28 % 32;
                        r153 = r99;
                    }
                    if (r11 >= 18 && r11 <= 49) {
                        r14[r23] = objectFieldOffset;
                        r23++;
                    }
                }
                int r910 = r21 + 1;
                r84[r21] = charAt25;
                int r65 = r910 + 1;
                r84[r910] = objectFieldOffset | ((charAt26 & 256) != 0 ? 268435456 : 0) | ((charAt26 & 512) != 0 ? 536870912 : 0) | (r11 << 20);
                r21 = r65 + 1;
                r84[r65] = (r124 << 20) | r4;
                r32 = r0;
                zzhp = str;
                r63 = r153;
                cls3 = cls2;
                r9 = r244;
                length = r25;
                r15 = r27;
                z2 = z;
                charAt = r30;
                r12 = r31;
                zzipVar2 = zzipVar;
            }
            return new zzig<>(r84, objArr, charAt, r9, zzipVar2.zzhk(), z2, false, r14, r15, r20, zzikVar, zzhmVar, zzjjVar, zzgfVar, zzhvVar);
        }
        ((zzjg) zziaVar).zzhi();
        int r03 = zzgs.zzf.zzwu;
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final T newInstance() {
        return (T) this.zzzg.newInstance(this.zzyy);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzjp.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzjp.zzo(r11, r6))) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.vision.zzit.zze(com.google.android.gms.internal.vision.zzjp.zzp(r10, r6), com.google.android.gms.internal.vision.zzjp.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.gms.internal.vision.zzit.zze(com.google.android.gms.internal.vision.zzjp.zzp(r10, r6), com.google.android.gms.internal.vision.zzjp.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzl(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzl(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.gms.internal.vision.zzit.zze(com.google.android.gms.internal.vision.zzjp.zzp(r10, r6), com.google.android.gms.internal.vision.zzjp.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.gms.internal.vision.zzit.zze(com.google.android.gms.internal.vision.zzjp.zzp(r10, r6), com.google.android.gms.internal.vision.zzjp.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.gms.internal.vision.zzit.zze(com.google.android.gms.internal.vision.zzjp.zzp(r10, r6), com.google.android.gms.internal.vision.zzjp.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzm(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzm(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzl(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzk(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzl(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.gms.internal.vision.zzjp.zzl(r10, r6) == com.google.android.gms.internal.vision.zzjp.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzjp.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzjp.zzn(r11, r6))) goto L85;
     */
    @Override // com.google.android.gms.internal.vision.zzir
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.equals(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final int hashCode(T t) {
        int r2;
        int zzab;
        int length = this.zzyu.length;
        int r22 = 0;
        for (int r1 = 0; r1 < length; r1 += 3) {
            int zzbp = zzbp(r1);
            int r4 = this.zzyu[r1];
            long j = 1048575 & zzbp;
            int r7 = 37;
            switch ((zzbp & 267386880) >>> 20) {
                case 0:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(Double.doubleToLongBits(zzjp.zzo(t, j)));
                    r22 = r2 + zzab;
                    break;
                case 1:
                    r2 = r22 * 53;
                    zzab = Float.floatToIntBits(zzjp.zzn(t, j));
                    r22 = r2 + zzab;
                    break;
                case 2:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(zzjp.zzl(t, j));
                    r22 = r2 + zzab;
                    break;
                case 3:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(zzjp.zzl(t, j));
                    r22 = r2 + zzab;
                    break;
                case 4:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 5:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(zzjp.zzl(t, j));
                    r22 = r2 + zzab;
                    break;
                case 6:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 7:
                    r2 = r22 * 53;
                    zzab = zzgt.zzm(zzjp.zzm(t, j));
                    r22 = r2 + zzab;
                    break;
                case 8:
                    r2 = r22 * 53;
                    zzab = ((String) zzjp.zzp(t, j)).hashCode();
                    r22 = r2 + zzab;
                    break;
                case 9:
                    Object zzp = zzjp.zzp(t, j);
                    if (zzp != null) {
                        r7 = zzp.hashCode();
                    }
                    r22 = (r22 * 53) + r7;
                    break;
                case 10:
                    r2 = r22 * 53;
                    zzab = zzjp.zzp(t, j).hashCode();
                    r22 = r2 + zzab;
                    break;
                case 11:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 12:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 13:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 14:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(zzjp.zzl(t, j));
                    r22 = r2 + zzab;
                    break;
                case 15:
                    r2 = r22 * 53;
                    zzab = zzjp.zzk(t, j);
                    r22 = r2 + zzab;
                    break;
                case 16:
                    r2 = r22 * 53;
                    zzab = zzgt.zzab(zzjp.zzl(t, j));
                    r22 = r2 + zzab;
                    break;
                case 17:
                    Object zzp2 = zzjp.zzp(t, j);
                    if (zzp2 != null) {
                        r7 = zzp2.hashCode();
                    }
                    r22 = (r22 * 53) + r7;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    r2 = r22 * 53;
                    zzab = zzjp.zzp(t, j).hashCode();
                    r22 = r2 + zzab;
                    break;
                case 50:
                    r2 = r22 * 53;
                    zzab = zzjp.zzp(t, j).hashCode();
                    r22 = r2 + zzab;
                    break;
                case 51:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(Double.doubleToLongBits(zzf(t, j)));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = Float.floatToIntBits(zzg(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(zzi(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(zzi(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(zzi(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzm(zzj(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = ((String) zzjp.zzp(t, j)).hashCode();
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzjp.zzp(t, j).hashCode();
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzjp.zzp(t, j).hashCode();
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(zzi(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzh(t, j);
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzgt.zzab(zzi(t, j));
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzig<T>) t, r4, r1)) {
                        r2 = r22 * 53;
                        zzab = zzjp.zzp(t, j).hashCode();
                        r22 = r2 + zzab;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (r22 * 53) + this.zzzi.zzv(t).hashCode();
        return this.zzyz ? (hashCode * 53) + this.zzzj.zze(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zzd(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int r0 = 0; r0 < this.zzyu.length; r0 += 3) {
            int zzbp = zzbp(r0);
            long j = 1048575 & zzbp;
            int r4 = this.zzyu[r0];
            switch ((zzbp & 267386880) >>> 20) {
                case 0:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza(t, j, zzjp.zzo(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzn(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza(t, j, zzjp.zzm(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza(t, j, zzjp.zzp(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t, t2, r0);
                    break;
                case 10:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza(t, j, zzjp.zzp(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zzb(t, j, zzjp.zzk(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zza((zzig<T>) t2, r0)) {
                        zzjp.zza((Object) t, j, zzjp.zzl(t2, j));
                        zzb((zzig<T>) t, r0);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t, t2, r0);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzzh.zza(t, t2, j);
                    break;
                case 50:
                    zzit.zza(this.zzzk, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza((zzig<T>) t2, r4, r0)) {
                        zzjp.zza(t, j, zzjp.zzp(t2, j));
                        zzb((zzig<T>) t, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t, t2, r0);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza((zzig<T>) t2, r4, r0)) {
                        zzjp.zza(t, j, zzjp.zzp(t2, j));
                        zzb((zzig<T>) t, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t, t2, r0);
                    break;
            }
        }
        if (this.zzzb) {
            return;
        }
        zzit.zza(this.zzzi, t, t2);
        if (this.zzyz) {
            zzit.zza(this.zzzj, t, t2);
        }
    }

    private final void zza(T t, T t2, int r6) {
        long zzbp = zzbp(r6) & 1048575;
        if (zza((zzig<T>) t2, r6)) {
            Object zzp = zzjp.zzp(t, zzbp);
            Object zzp2 = zzjp.zzp(t2, zzbp);
            if (zzp != null && zzp2 != null) {
                zzjp.zza(t, zzbp, zzgt.zzb(zzp, zzp2));
                zzb((zzig<T>) t, r6);
            } else if (zzp2 != null) {
                zzjp.zza(t, zzbp, zzp2);
                zzb((zzig<T>) t, r6);
            }
        }
    }

    private final void zzb(T t, T t2, int r7) {
        int zzbp = zzbp(r7);
        int r1 = this.zzyu[r7];
        long j = zzbp & 1048575;
        if (zza((zzig<T>) t2, r1, r7)) {
            Object zzp = zzjp.zzp(t, j);
            Object zzp2 = zzjp.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzjp.zza(t, j, zzgt.zzb(zzp, zzp2));
                zzb((zzig<T>) t, r1, r7);
            } else if (zzp2 != null) {
                zzjp.zza(t, j, zzp2);
                zzb((zzig<T>) t, r1, r7);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.vision.zzir
    public final int zzr(T t) {
        int r8;
        int r18;
        long j;
        int zzd;
        int zzb;
        int zzp;
        int zzv;
        int zzy;
        int zzba;
        int zzbc;
        int zzb2;
        int zzy2;
        int zzba2;
        int zzbc2;
        int r3 = 267386880;
        int r82 = 1;
        if (this.zzzb) {
            Unsafe unsafe = zzyt;
            int r12 = 0;
            int r13 = 0;
            while (r12 < this.zzyu.length) {
                int zzbp = zzbp(r12);
                int r15 = (zzbp & r3) >>> 20;
                int r32 = this.zzyu[r12];
                long j2 = zzbp & 1048575;
                int r14 = (r15 < zzgn.DOUBLE_LIST_PACKED.m1085id() || r15 > zzgn.SINT64_LIST_PACKED.m1085id()) ? 0 : this.zzyu[r12 + 2] & 1048575;
                switch (r15) {
                    case 0:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzb(r32, 0.0d);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 1:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzb(r32, 0.0f);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 2:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzd(r32, zzjp.zzl(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 3:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zze(r32, zzjp.zzl(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 4:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzl(r32, zzjp.zzk(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 5:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzg(r32, 0L);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 6:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzo(r32, 0);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 7:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzb(r32, true);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 8:
                        if (zza((zzig<T>) t, r12)) {
                            Object zzp2 = zzjp.zzp(t, j2);
                            if (zzp2 instanceof zzfh) {
                                zzb2 = zzga.zzc(r32, (zzfh) zzp2);
                                break;
                            } else {
                                zzb2 = zzga.zzb(r32, (String) zzp2);
                                break;
                            }
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 9:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzit.zzc(r32, zzjp.zzp(t, j2), zzbm(r12));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 10:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzc(r32, (zzfh) zzjp.zzp(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 11:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzm(r32, zzjp.zzk(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 12:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzq(r32, zzjp.zzk(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 13:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzp(r32, 0);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 14:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzh(r32, 0L);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 15:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzn(r32, zzjp.zzk(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 16:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzf(r32, zzjp.zzl(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 17:
                        if (zza((zzig<T>) t, r12)) {
                            zzb2 = zzga.zzc(r32, (zzic) zzjp.zzp(t, j2), zzbm(r12));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 18:
                        zzb2 = zzit.zzw(r32, zze(t, j2), false);
                        break;
                    case 19:
                        zzb2 = zzit.zzv(r32, zze(t, j2), false);
                        break;
                    case 20:
                        zzb2 = zzit.zzo(r32, zze(t, j2), false);
                        break;
                    case 21:
                        zzb2 = zzit.zzp(r32, zze(t, j2), false);
                        break;
                    case 22:
                        zzb2 = zzit.zzs(r32, zze(t, j2), false);
                        break;
                    case 23:
                        zzb2 = zzit.zzw(r32, zze(t, j2), false);
                        break;
                    case 24:
                        zzb2 = zzit.zzv(r32, zze(t, j2), false);
                        break;
                    case 25:
                        zzb2 = zzit.zzx(r32, zze(t, j2), false);
                        break;
                    case 26:
                        zzb2 = zzit.zzc(r32, zze(t, j2));
                        break;
                    case 27:
                        zzb2 = zzit.zzc(r32, zze(t, j2), zzbm(r12));
                        break;
                    case 28:
                        zzb2 = zzit.zzd(r32, zze(t, j2));
                        break;
                    case 29:
                        zzb2 = zzit.zzt(r32, zze(t, j2), false);
                        break;
                    case 30:
                        zzb2 = zzit.zzr(r32, zze(t, j2), false);
                        break;
                    case 31:
                        zzb2 = zzit.zzv(r32, zze(t, j2), false);
                        break;
                    case 32:
                        zzb2 = zzit.zzw(r32, zze(t, j2), false);
                        break;
                    case 33:
                        zzb2 = zzit.zzu(r32, zze(t, j2), false);
                        break;
                    case 34:
                        zzb2 = zzit.zzq(r32, zze(t, j2), false);
                        break;
                    case 35:
                        zzy2 = zzit.zzy((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 36:
                        zzy2 = zzit.zzx((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 37:
                        zzy2 = zzit.zzq((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 38:
                        zzy2 = zzit.zzr((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 39:
                        zzy2 = zzit.zzu((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 40:
                        zzy2 = zzit.zzy((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 41:
                        zzy2 = zzit.zzx((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 42:
                        zzy2 = zzit.zzz((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 43:
                        zzy2 = zzit.zzv((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 44:
                        zzy2 = zzit.zzt((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 45:
                        zzy2 = zzit.zzx((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 46:
                        zzy2 = zzit.zzy((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 47:
                        zzy2 = zzit.zzw((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 48:
                        zzy2 = zzit.zzs((List) unsafe.getObject(t, j2));
                        if (zzy2 > 0) {
                            if (this.zzzc) {
                                unsafe.putInt(t, r14, zzy2);
                            }
                            zzba2 = zzga.zzba(r32);
                            zzbc2 = zzga.zzbc(zzy2);
                            zzb2 = zzba2 + zzbc2 + zzy2;
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 49:
                        zzb2 = zzit.zzd(r32, zze(t, j2), zzbm(r12));
                        break;
                    case 50:
                        zzb2 = this.zzzk.zzb(r32, zzjp.zzp(t, j2), zzbn(r12));
                        break;
                    case 51:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzb(r32, 0.0d);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 52:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzb(r32, 0.0f);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 53:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzd(r32, zzi(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 54:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zze(r32, zzi(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 55:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzl(r32, zzh(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 56:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzg(r32, 0L);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 57:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzo(r32, 0);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 58:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzb(r32, true);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 59:
                        if (zza((zzig<T>) t, r32, r12)) {
                            Object zzp3 = zzjp.zzp(t, j2);
                            if (zzp3 instanceof zzfh) {
                                zzb2 = zzga.zzc(r32, (zzfh) zzp3);
                                break;
                            } else {
                                zzb2 = zzga.zzb(r32, (String) zzp3);
                                break;
                            }
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 60:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzit.zzc(r32, zzjp.zzp(t, j2), zzbm(r12));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 61:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzc(r32, (zzfh) zzjp.zzp(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 62:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzm(r32, zzh(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 63:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzq(r32, zzh(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 64:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzp(r32, 0);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 65:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzh(r32, 0L);
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 66:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzn(r32, zzh(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 67:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzf(r32, zzi(t, j2));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    case 68:
                        if (zza((zzig<T>) t, r32, r12)) {
                            zzb2 = zzga.zzc(r32, (zzic) zzjp.zzp(t, j2), zzbm(r12));
                            break;
                        } else {
                            continue;
                            r12 += 3;
                            r3 = 267386880;
                        }
                    default:
                        r12 += 3;
                        r3 = 267386880;
                }
                r13 += zzb2;
                r12 += 3;
                r3 = 267386880;
            }
            return r13 + zza(this.zzzi, t);
        }
        Unsafe unsafe2 = zzyt;
        int r33 = -1;
        int r5 = 0;
        int r6 = 0;
        int r122 = 0;
        while (r5 < this.zzyu.length) {
            int zzbp2 = zzbp(r5);
            int[] r142 = this.zzyu;
            int r152 = r142[r5];
            int r4 = (zzbp2 & 267386880) >>> 20;
            if (r4 <= 17) {
                int r11 = r142[r5 + 2];
                int r143 = r11 & 1048575;
                r18 = r82 << (r11 >>> 20);
                if (r143 != r33) {
                    r122 = unsafe2.getInt(t, r143);
                    r33 = r143;
                }
                r8 = r11;
            } else {
                r8 = (!this.zzzc || r4 < zzgn.DOUBLE_LIST_PACKED.m1085id() || r4 > zzgn.SINT64_LIST_PACKED.m1085id()) ? 0 : this.zzyu[r5 + 2] & 1048575;
                r18 = 0;
            }
            long j3 = zzbp2 & 1048575;
            switch (r4) {
                case 0:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        r6 += zzga.zzb(r152, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        r6 += zzga.zzb(r152, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        zzd = zzga.zzd(r152, unsafe2.getLong(t, j3));
                        r6 += zzd;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        zzd = zzga.zze(r152, unsafe2.getLong(t, j3));
                        r6 += zzd;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        zzd = zzga.zzl(r152, unsafe2.getInt(t, j3));
                        r6 += zzd;
                    }
                    break;
                case 5:
                    j = 0;
                    if ((r122 & r18) != 0) {
                        zzd = zzga.zzg(r152, 0L);
                        r6 += zzd;
                    }
                    break;
                case 6:
                    if ((r122 & r18) != 0) {
                        r6 += zzga.zzo(r152, 0);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 7:
                    if ((r122 & r18) != 0) {
                        r6 += zzga.zzb(r152, true);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 8:
                    if ((r122 & r18) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzfh) {
                            zzb = zzga.zzc(r152, (zzfh) object);
                        } else {
                            zzb = zzga.zzb(r152, (String) object);
                        }
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((r122 & r18) != 0) {
                        zzb = zzit.zzc(r152, unsafe2.getObject(t, j3), zzbm(r5));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzc(r152, (zzfh) unsafe2.getObject(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzm(r152, unsafe2.getInt(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzq(r152, unsafe2.getInt(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((r122 & r18) != 0) {
                        zzp = zzga.zzp(r152, 0);
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzh(r152, 0L);
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzn(r152, unsafe2.getInt(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzf(r152, unsafe2.getLong(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((r122 & r18) != 0) {
                        zzb = zzga.zzc(r152, (zzic) unsafe2.getObject(t, j3), zzbm(r5));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 18:
                    zzb = zzit.zzw(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzb;
                    j = 0;
                    break;
                case 19:
                    zzv = zzit.zzv(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 20:
                    zzv = zzit.zzo(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 21:
                    zzv = zzit.zzp(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 22:
                    zzv = zzit.zzs(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 23:
                    zzv = zzit.zzw(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 24:
                    zzv = zzit.zzv(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 25:
                    zzv = zzit.zzx(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 26:
                    zzb = zzit.zzc(r152, (List) unsafe2.getObject(t, j3));
                    r6 += zzb;
                    j = 0;
                    break;
                case 27:
                    zzb = zzit.zzc(r152, (List<?>) unsafe2.getObject(t, j3), zzbm(r5));
                    r6 += zzb;
                    j = 0;
                    break;
                case 28:
                    zzb = zzit.zzd(r152, (List) unsafe2.getObject(t, j3));
                    r6 += zzb;
                    j = 0;
                    break;
                case 29:
                    zzb = zzit.zzt(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzb;
                    j = 0;
                    break;
                case 30:
                    zzv = zzit.zzr(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 31:
                    zzv = zzit.zzv(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 32:
                    zzv = zzit.zzw(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 33:
                    zzv = zzit.zzu(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 34:
                    zzv = zzit.zzq(r152, (List) unsafe2.getObject(t, j3), false);
                    r6 += zzv;
                    j = 0;
                    break;
                case 35:
                    zzy = zzit.zzy((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 36:
                    zzy = zzit.zzx((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 37:
                    zzy = zzit.zzq((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 38:
                    zzy = zzit.zzr((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 39:
                    zzy = zzit.zzu((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 40:
                    zzy = zzit.zzy((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 41:
                    zzy = zzit.zzx((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 42:
                    zzy = zzit.zzz((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 43:
                    zzy = zzit.zzv((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 44:
                    zzy = zzit.zzt((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 45:
                    zzy = zzit.zzx((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 46:
                    zzy = zzit.zzy((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 47:
                    zzy = zzit.zzw((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 48:
                    zzy = zzit.zzs((List) unsafe2.getObject(t, j3));
                    if (zzy > 0) {
                        if (this.zzzc) {
                            unsafe2.putInt(t, r8, zzy);
                        }
                        zzba = zzga.zzba(r152);
                        zzbc = zzga.zzbc(zzy);
                        zzp = zzba + zzbc + zzy;
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 49:
                    zzb = zzit.zzd(r152, (List) unsafe2.getObject(t, j3), zzbm(r5));
                    r6 += zzb;
                    j = 0;
                    break;
                case 50:
                    zzb = this.zzzk.zzb(r152, unsafe2.getObject(t, j3), zzbn(r5));
                    r6 += zzb;
                    j = 0;
                    break;
                case 51:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzb(r152, 0.0d);
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzp = zzga.zzb(r152, 0.0f);
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzd(r152, zzi(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zze(r152, zzi(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzl(r152, zzh(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzg(r152, 0L);
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzp = zzga.zzo(r152, 0);
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzp = zzga.zzb(r152, true);
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza((zzig<T>) t, r152, r5)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzfh) {
                            zzb = zzga.zzc(r152, (zzfh) object2);
                        } else {
                            zzb = zzga.zzb(r152, (String) object2);
                        }
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzit.zzc(r152, unsafe2.getObject(t, j3), zzbm(r5));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzc(r152, (zzfh) unsafe2.getObject(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzm(r152, zzh(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzq(r152, zzh(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzp = zzga.zzp(r152, 0);
                        r6 += zzp;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzh(r152, 0L);
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzn(r152, zzh(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzf(r152, zzi(t, j3));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza((zzig<T>) t, r152, r5)) {
                        zzb = zzga.zzc(r152, (zzic) unsafe2.getObject(t, j3), zzbm(r5));
                        r6 += zzb;
                    }
                    j = 0;
                    break;
                default:
                    j = 0;
                    break;
            }
            r5 += 3;
            r82 = 1;
        }
        int r83 = 0;
        int zza = r6 + zza(this.zzzi, t);
        if (this.zzyz) {
            zzgi<?> zze = this.zzzj.zze(t);
            for (int r112 = 0; r112 < zze.zztb.zzhx(); r112++) {
                Map.Entry<?, Object> zzbu = zze.zztb.zzbu(r112);
                r83 += zzgi.zzc((zzgk) zzbu.getKey(), zzbu.getValue());
            }
            for (Map.Entry<?, Object> entry : zze.zztb.zzhy()) {
                r83 += zzgi.zzc((zzgk) entry.getKey(), entry.getValue());
            }
            return zza + r83;
        }
        return zza;
    }

    private static <UT, UB> int zza(zzjj<UT, UB> zzjjVar, T t) {
        return zzjjVar.zzr(zzjjVar.zzv(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzjp.zzp(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a2a  */
    @Override // com.google.android.gms.internal.vision.zzir
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzkg r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, com.google.android.gms.internal.vision.zzkg):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0496  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r19, com.google.android.gms.internal.vision.zzkg r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzkg):void");
    }

    private final <K, V> void zza(zzkg zzkgVar, int r3, Object obj, int r5) throws IOException {
        if (obj != null) {
            zzkgVar.zza(r3, this.zzzk.zzp(zzbn(r5)), this.zzzk.zzl(obj));
        }
    }

    private static <UT, UB> void zza(zzjj<UT, UB> zzjjVar, T t, zzkg zzkgVar) throws IOException {
        zzjjVar.zza((zzjj<UT, UB>) zzjjVar.zzv(t), zzkgVar);
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zza(T t, zzis zzisVar, zzgd zzgdVar) throws IOException {
        Objects.requireNonNull(zzgdVar);
        zzjj zzjjVar = this.zzzi;
        zzgf<?> zzgfVar = this.zzzj;
        zzgi<?> zzgiVar = null;
        Object obj = null;
        while (true) {
            try {
                int zzdu = zzisVar.zzdu();
                int zzbs = zzbs(zzdu);
                if (zzbs >= 0) {
                    int zzbp = zzbp(zzbs);
                    switch ((267386880 & zzbp) >>> 20) {
                        case 0:
                            zzjp.zza(t, zzbp & 1048575, zzisVar.readDouble());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 1:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.readFloat());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 2:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.zzdx());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 3:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.zzdw());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 4:
                            zzjp.zzb(t, zzbp & 1048575, zzisVar.zzdy());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 5:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.zzdz());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 6:
                            zzjp.zzb(t, zzbp & 1048575, zzisVar.zzea());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 7:
                            zzjp.zza(t, zzbp & 1048575, zzisVar.zzeb());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 8:
                            zza(t, zzbp, zzisVar);
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 9:
                            if (zza((zzig<T>) t, zzbs)) {
                                long j = zzbp & 1048575;
                                zzjp.zza(t, j, zzgt.zzb(zzjp.zzp(t, j), zzisVar.zza(zzbm(zzbs), zzgdVar)));
                                break;
                            } else {
                                zzjp.zza(t, zzbp & 1048575, zzisVar.zza(zzbm(zzbs), zzgdVar));
                                zzb((zzig<T>) t, zzbs);
                                continue;
                            }
                        case 10:
                            zzjp.zza(t, zzbp & 1048575, zzisVar.zzed());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 11:
                            zzjp.zzb(t, zzbp & 1048575, zzisVar.zzee());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 12:
                            int zzef = zzisVar.zzef();
                            zzgy zzbo = zzbo(zzbs);
                            if (zzbo != null && !zzbo.zzf(zzef)) {
                                obj = zzit.zza(zzdu, zzef, obj, zzjjVar);
                                break;
                            }
                            zzjp.zzb(t, zzbp & 1048575, zzef);
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 13:
                            zzjp.zzb(t, zzbp & 1048575, zzisVar.zzeg());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 14:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.zzeh());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 15:
                            zzjp.zzb(t, zzbp & 1048575, zzisVar.zzei());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 16:
                            zzjp.zza((Object) t, zzbp & 1048575, zzisVar.zzej());
                            zzb((zzig<T>) t, zzbs);
                            continue;
                        case 17:
                            if (zza((zzig<T>) t, zzbs)) {
                                long j2 = zzbp & 1048575;
                                zzjp.zza(t, j2, zzgt.zzb(zzjp.zzp(t, j2), zzisVar.zzc(zzbm(zzbs), zzgdVar)));
                                break;
                            } else {
                                zzjp.zza(t, zzbp & 1048575, zzisVar.zzc(zzbm(zzbs), zzgdVar));
                                zzb((zzig<T>) t, zzbs);
                                continue;
                            }
                        case 18:
                            zzisVar.zza(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 19:
                            zzisVar.zzb(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 20:
                            zzisVar.zzd(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 21:
                            zzisVar.zzc(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 22:
                            zzisVar.zze(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 23:
                            zzisVar.zzf(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 24:
                            zzisVar.zzg(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 25:
                            zzisVar.zzh(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 26:
                            if (zzbr(zzbp)) {
                                zzisVar.zzi(this.zzzh.zza(t, zzbp & 1048575));
                                break;
                            } else {
                                zzisVar.readStringList(this.zzzh.zza(t, zzbp & 1048575));
                                continue;
                            }
                        case 27:
                            zzisVar.zza(this.zzzh.zza(t, zzbp & 1048575), zzbm(zzbs), zzgdVar);
                            continue;
                        case 28:
                            zzisVar.zzj(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 29:
                            zzisVar.zzk(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 30:
                            List<Integer> zza = this.zzzh.zza(t, zzbp & 1048575);
                            zzisVar.zzl(zza);
                            obj = zzit.zza(zzdu, zza, zzbo(zzbs), obj, zzjjVar);
                            continue;
                        case 31:
                            zzisVar.zzm(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 32:
                            zzisVar.zzn(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 33:
                            zzisVar.zzo(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 34:
                            zzisVar.zzp(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 35:
                            zzisVar.zza(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 36:
                            zzisVar.zzb(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 37:
                            zzisVar.zzd(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 38:
                            zzisVar.zzc(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 39:
                            zzisVar.zze(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 40:
                            zzisVar.zzf(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 41:
                            zzisVar.zzg(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 42:
                            zzisVar.zzh(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 43:
                            zzisVar.zzk(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 44:
                            List<Integer> zza2 = this.zzzh.zza(t, zzbp & 1048575);
                            zzisVar.zzl(zza2);
                            obj = zzit.zza(zzdu, zza2, zzbo(zzbs), obj, zzjjVar);
                            continue;
                        case 45:
                            zzisVar.zzm(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 46:
                            zzisVar.zzn(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 47:
                            zzisVar.zzo(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 48:
                            zzisVar.zzp(this.zzzh.zza(t, zzbp & 1048575));
                            continue;
                        case 49:
                            zzisVar.zzb(this.zzzh.zza(t, zzbp & 1048575), zzbm(zzbs), zzgdVar);
                            continue;
                        case 50:
                            Object zzbn = zzbn(zzbs);
                            long zzbp2 = zzbp(zzbs) & 1048575;
                            Object zzp = zzjp.zzp(t, zzbp2);
                            if (zzp == null) {
                                zzp = this.zzzk.zzo(zzbn);
                                zzjp.zza(t, zzbp2, zzp);
                            } else if (this.zzzk.zzm(zzp)) {
                                Object zzo = this.zzzk.zzo(zzbn);
                                this.zzzk.zzc(zzo, zzp);
                                zzjp.zza(t, zzbp2, zzo);
                                zzp = zzo;
                            }
                            zzisVar.zza(this.zzzk.zzk(zzp), this.zzzk.zzp(zzbn), zzgdVar);
                            continue;
                        case 51:
                            zzjp.zza(t, zzbp & 1048575, Double.valueOf(zzisVar.readDouble()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 52:
                            zzjp.zza(t, zzbp & 1048575, Float.valueOf(zzisVar.readFloat()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 53:
                            zzjp.zza(t, zzbp & 1048575, Long.valueOf(zzisVar.zzdx()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 54:
                            zzjp.zza(t, zzbp & 1048575, Long.valueOf(zzisVar.zzdw()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 55:
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzisVar.zzdy()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 56:
                            zzjp.zza(t, zzbp & 1048575, Long.valueOf(zzisVar.zzdz()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 57:
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzisVar.zzea()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 58:
                            zzjp.zza(t, zzbp & 1048575, Boolean.valueOf(zzisVar.zzeb()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 59:
                            zza(t, zzbp, zzisVar);
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 60:
                            if (zza((zzig<T>) t, zzdu, zzbs)) {
                                long j3 = zzbp & 1048575;
                                zzjp.zza(t, j3, zzgt.zzb(zzjp.zzp(t, j3), zzisVar.zza(zzbm(zzbs), zzgdVar)));
                            } else {
                                zzjp.zza(t, zzbp & 1048575, zzisVar.zza(zzbm(zzbs), zzgdVar));
                                zzb((zzig<T>) t, zzbs);
                            }
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 61:
                            zzjp.zza(t, zzbp & 1048575, zzisVar.zzed());
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 62:
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzisVar.zzee()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 63:
                            int zzef2 = zzisVar.zzef();
                            zzgy zzbo2 = zzbo(zzbs);
                            if (zzbo2 != null && !zzbo2.zzf(zzef2)) {
                                obj = zzit.zza(zzdu, zzef2, obj, zzjjVar);
                                break;
                            }
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzef2));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 64:
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzisVar.zzeg()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 65:
                            zzjp.zza(t, zzbp & 1048575, Long.valueOf(zzisVar.zzeh()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 66:
                            zzjp.zza(t, zzbp & 1048575, Integer.valueOf(zzisVar.zzei()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 67:
                            zzjp.zza(t, zzbp & 1048575, Long.valueOf(zzisVar.zzej()));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        case 68:
                            zzjp.zza(t, zzbp & 1048575, zzisVar.zzc(zzbm(zzbs), zzgdVar));
                            zzb((zzig<T>) t, zzdu, zzbs);
                            continue;
                        default:
                            if (obj == null) {
                                try {
                                    obj = zzjjVar.zzif();
                                } catch (zzhb unused) {
                                    zzjjVar.zza(zzisVar);
                                    if (obj == null) {
                                        obj = zzjjVar.zzw(t);
                                    }
                                    if (!zzjjVar.zza((zzjj) obj, zzisVar)) {
                                        for (int r14 = this.zzze; r14 < this.zzzf; r14++) {
                                            obj = zza((Object) t, this.zzzd[r14], (int) obj, (zzjj<UT, int>) zzjjVar);
                                        }
                                        if (obj != null) {
                                            zzjjVar.zzg(t, obj);
                                            return;
                                        }
                                        return;
                                    }
                                    break;
                                }
                            }
                            if (!zzjjVar.zza((zzjj) obj, zzisVar)) {
                                for (int r142 = this.zzze; r142 < this.zzzf; r142++) {
                                    obj = zza((Object) t, this.zzzd[r142], (int) obj, (zzjj<UT, int>) zzjjVar);
                                }
                                if (obj != null) {
                                    zzjjVar.zzg(t, obj);
                                    return;
                                }
                                return;
                            }
                            continue;
                    }
                } else if (zzdu == Integer.MAX_VALUE) {
                    for (int r143 = this.zzze; r143 < this.zzzf; r143++) {
                        obj = zza((Object) t, this.zzzd[r143], (int) obj, (zzjj<UT, int>) zzjjVar);
                    }
                    if (obj != null) {
                        zzjjVar.zzg(t, obj);
                        return;
                    }
                    return;
                } else {
                    Object zza3 = !this.zzyz ? null : zzgfVar.zza(zzgdVar, this.zzyy, zzdu);
                    if (zza3 != null) {
                        if (zzgiVar == null) {
                            zzgiVar = zzgfVar.zzf(t);
                        }
                        zzgi<?> zzgiVar2 = zzgiVar;
                        obj = zzgfVar.zza(zzisVar, zza3, zzgdVar, zzgiVar2, obj, zzjjVar);
                        zzgiVar = zzgiVar2;
                    } else {
                        zzjjVar.zza(zzisVar);
                        if (obj == null) {
                            obj = zzjjVar.zzw(t);
                        }
                        if (!zzjjVar.zza((zzjj) obj, zzisVar)) {
                            for (int r144 = this.zzze; r144 < this.zzzf; r144++) {
                                obj = zza((Object) t, this.zzzd[r144], (int) obj, (zzjj<UT, int>) zzjjVar);
                            }
                            if (obj != null) {
                                zzjjVar.zzg(t, obj);
                                return;
                            }
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                for (int r15 = this.zzze; r15 < this.zzzf; r15++) {
                    obj = zza((Object) t, this.zzzd[r15], (int) obj, (zzjj<UT, int>) zzjjVar);
                }
                if (obj != null) {
                    zzjjVar.zzg(t, obj);
                }
                throw th;
            }
        }
    }

    private static zzjm zzs(Object obj) {
        zzgs zzgsVar = (zzgs) obj;
        zzjm zzjmVar = zzgsVar.zzwd;
        if (zzjmVar == zzjm.zzig()) {
            zzjm zzih = zzjm.zzih();
            zzgsVar.zzwd = zzih;
            return zzih;
        }
        return zzjmVar;
    }

    private static int zza(byte[] bArr, int r2, int r3, zzka zzkaVar, Class<?> cls, zzfb zzfbVar) throws IOException {
        switch (zzif.zzrr[zzkaVar.ordinal()]) {
            case 1:
                int zzb = zzez.zzb(bArr, r2, zzfbVar);
                zzfbVar.zzrq = Boolean.valueOf(zzfbVar.zzrp != 0);
                return zzb;
            case 2:
                return zzez.zze(bArr, r2, zzfbVar);
            case 3:
                zzfbVar.zzrq = Double.valueOf(zzez.zzc(bArr, r2));
                return r2 + 8;
            case 4:
            case 5:
                zzfbVar.zzrq = Integer.valueOf(zzez.zza(bArr, r2));
                return r2 + 4;
            case 6:
            case 7:
                zzfbVar.zzrq = Long.valueOf(zzez.zzb(bArr, r2));
                return r2 + 8;
            case 8:
                zzfbVar.zzrq = Float.valueOf(zzez.zzd(bArr, r2));
                return r2 + 4;
            case 9:
            case 10:
            case 11:
                int zza = zzez.zza(bArr, r2, zzfbVar);
                zzfbVar.zzrq = Integer.valueOf(zzfbVar.zzro);
                return zza;
            case 12:
            case 13:
                int zzb2 = zzez.zzb(bArr, r2, zzfbVar);
                zzfbVar.zzrq = Long.valueOf(zzfbVar.zzrp);
                return zzb2;
            case 14:
                return zzez.zza(zzin.zzho().zzf(cls), bArr, r2, r3, zzfbVar);
            case 15:
                int zza2 = zzez.zza(bArr, r2, zzfbVar);
                zzfbVar.zzrq = Integer.valueOf(zzft.zzau(zzfbVar.zzro));
                return zza2;
            case 16:
                int zzb3 = zzez.zzb(bArr, r2, zzfbVar);
                zzfbVar.zzrq = Long.valueOf(zzft.zzr(zzfbVar.zzrp));
                return zzb3;
            case 17:
                return zzez.zzd(bArr, r2, zzfbVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x01fb -> B:104:0x01fc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0133 -> B:55:0x0134). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x01ad -> B:84:0x01ae). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zza(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.vision.zzfb r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 918
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.zzfb):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <K, V> int zza(T t, byte[] bArr, int r10, int r11, int r12, long j, zzfb zzfbVar) throws IOException {
        Unsafe unsafe = zzyt;
        Object zzbn = zzbn(r12);
        Object object = unsafe.getObject(t, j);
        if (this.zzzk.zzm(object)) {
            Object zzo = this.zzzk.zzo(zzbn);
            this.zzzk.zzc(zzo, object);
            unsafe.putObject(t, j, zzo);
            object = zzo;
        }
        zzht<?, ?> zzp = this.zzzk.zzp(zzbn);
        Map<?, ?> zzk = this.zzzk.zzk(object);
        int zza = zzez.zza(bArr, r10, zzfbVar);
        int r13 = zzfbVar.zzro;
        if (r13 < 0 || r13 > r11 - zza) {
            throw zzhc.zzgm();
        }
        int r132 = r13 + zza;
        Object obj = (K) zzp.zzyn;
        Object obj2 = (V) zzp.zzgc;
        while (zza < r132) {
            int r1 = zza + 1;
            int r102 = bArr[zza];
            if (r102 < 0) {
                r1 = zzez.zza(r102, bArr, r1, zzfbVar);
                r102 = zzfbVar.zzro;
            }
            int r2 = r1;
            int r14 = r102 >>> 3;
            int r3 = r102 & 7;
            if (r14 == 1) {
                if (r3 == zzp.zzym.zziq()) {
                    zza = zza(bArr, r2, r11, zzp.zzym, (Class<?>) null, zzfbVar);
                    obj = (K) zzfbVar.zzrq;
                } else {
                    zza = zzez.zza(r102, bArr, r2, r11, zzfbVar);
                }
            } else {
                if (r14 == 2 && r3 == zzp.zzyo.zziq()) {
                    zza = zza(bArr, r2, r11, zzp.zzyo, zzp.zzgc.getClass(), zzfbVar);
                    obj2 = zzfbVar.zzrq;
                }
                zza = zzez.zza(r102, bArr, r2, r11, zzfbVar);
            }
        }
        if (zza != r132) {
            throw zzhc.zzgs();
        }
        zzk.put(obj, obj2);
        return r132;
    }

    private final int zza(T t, byte[] bArr, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long j, int r28, zzfb zzfbVar) throws IOException {
        int zzb;
        Unsafe unsafe = zzyt;
        long j2 = this.zzyu[r28 + 2] & 1048575;
        switch (r25) {
            case 51:
                if (r23 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzez.zzc(bArr, r19)));
                    zzb = r19 + 8;
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 52:
                if (r23 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzez.zzd(bArr, r19)));
                    zzb = r19 + 4;
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 53:
            case 54:
                if (r23 == 0) {
                    zzb = zzez.zzb(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, Long.valueOf(zzfbVar.zzrp));
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 55:
            case 62:
                if (r23 == 0) {
                    zzb = zzez.zza(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzfbVar.zzro));
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 56:
            case 65:
                if (r23 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzez.zzb(bArr, r19)));
                    zzb = r19 + 8;
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 57:
            case 64:
                if (r23 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzez.zza(bArr, r19)));
                    zzb = r19 + 4;
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 58:
                if (r23 == 0) {
                    zzb = zzez.zzb(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, Boolean.valueOf(zzfbVar.zzrp != 0));
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 59:
                if (r23 == 2) {
                    int zza = zzez.zza(bArr, r19, zzfbVar);
                    int r4 = zzfbVar.zzro;
                    if (r4 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((r24 & 536870912) != 0 && !zzjs.zzf(bArr, zza, zza + r4)) {
                        throw zzhc.zzgt();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, zza, r4, zzgt.UTF_8));
                        zza += r4;
                    }
                    unsafe.putInt(t, j2, r22);
                    return zza;
                }
                return r19;
            case 60:
                if (r23 == 2) {
                    int zza2 = zzez.zza(zzbm(r28), bArr, r19, r20, zzfbVar);
                    Object object = unsafe.getInt(t, j2) == r22 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, zzfbVar.zzrq);
                    } else {
                        unsafe.putObject(t, j, zzgt.zzb(object, zzfbVar.zzrq));
                    }
                    unsafe.putInt(t, j2, r22);
                    return zza2;
                }
                return r19;
            case 61:
                if (r23 == 2) {
                    zzb = zzez.zze(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, zzfbVar.zzrq);
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 63:
                if (r23 == 0) {
                    int zza3 = zzez.zza(bArr, r19, zzfbVar);
                    int r42 = zzfbVar.zzro;
                    zzgy zzbo = zzbo(r28);
                    if (zzbo == null || zzbo.zzf(r42)) {
                        unsafe.putObject(t, j, Integer.valueOf(r42));
                        zzb = zza3;
                        unsafe.putInt(t, j2, r22);
                        return zzb;
                    }
                    zzs(t).zzb(r21, Long.valueOf(r42));
                    return zza3;
                }
                return r19;
            case 66:
                if (r23 == 0) {
                    zzb = zzez.zza(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzft.zzau(zzfbVar.zzro)));
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 67:
                if (r23 == 0) {
                    zzb = zzez.zzb(bArr, r19, zzfbVar);
                    unsafe.putObject(t, j, Long.valueOf(zzft.zzr(zzfbVar.zzrp)));
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            case 68:
                if (r23 == 3) {
                    zzb = zzez.zza(zzbm(r28), bArr, r19, r20, (r21 & (-8)) | 4, zzfbVar);
                    Object object2 = unsafe.getInt(t, j2) == r22 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, zzfbVar.zzrq);
                    } else {
                        unsafe.putObject(t, j, zzgt.zzb(object2, zzfbVar.zzrq));
                    }
                    unsafe.putInt(t, j2, r22);
                    return zzb;
                }
                return r19;
            default:
                return r19;
        }
    }

    private final zzir zzbm(int r4) {
        int r42 = (r4 / 3) << 1;
        zzir zzirVar = (zzir) this.zzyv[r42];
        if (zzirVar != null) {
            return zzirVar;
        }
        zzir<T> zzf = zzin.zzho().zzf((Class) this.zzyv[r42 + 1]);
        this.zzyv[r42] = zzf;
        return zzf;
    }

    private final Object zzbn(int r2) {
        return this.zzyv[(r2 / 3) << 1];
    }

    private final zzgy zzbo(int r2) {
        return (zzgy) this.zzyv[((r2 / 3) << 1) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0695, code lost:
        if (r1 != 18) goto L81;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:213:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0683  */
    /* JADX WARN: Type inference failed for: r0v49, types: [com.google.android.gms.internal.vision.zzgo, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v50, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v51, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v52, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v53, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v54, types: [com.google.android.gms.internal.vision.zzff, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v55, types: [com.google.android.gms.internal.vision.zzgu, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v57, types: [com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzgz] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(T r33, byte[] r34, int r35, int r36, int r37, com.google.android.gms.internal.vision.zzfb r38) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1966
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzfb):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x022e, code lost:
        if (r0 == r15) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0230, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01e2, code lost:
        if (r0 == r15) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x020f, code lost:
        if (r0 == r15) goto L96;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    @Override // com.google.android.gms.internal.vision.zzir
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.vision.zzfb r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzig.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzfb):void");
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zzg(T t) {
        int r1;
        int r0 = this.zzze;
        while (true) {
            r1 = this.zzzf;
            if (r0 >= r1) {
                break;
            }
            long zzbp = zzbp(this.zzzd[r0]) & 1048575;
            Object zzp = zzjp.zzp(t, zzbp);
            if (zzp != null) {
                zzjp.zza(t, zzbp, this.zzzk.zzn(zzp));
            }
            r0++;
        }
        int length = this.zzzd.length;
        while (r1 < length) {
            this.zzzh.zzb(t, this.zzzd[r1]);
            r1++;
        }
        this.zzzi.zzg(t);
        if (this.zzyz) {
            this.zzzj.zzg(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int r10, UB ub, zzjj<UT, UB> zzjjVar) {
        zzgy zzbo;
        int r3 = this.zzyu[r10];
        Object zzp = zzjp.zzp(obj, zzbp(r10) & 1048575);
        return (zzp == null || (zzbo = zzbo(r10)) == null) ? ub : (UB) zza(r10, r3, this.zzzk.zzk(zzp), zzbo, (zzgy) ub, (zzjj<UT, zzgy>) zzjjVar);
    }

    private final <K, V, UT, UB> UB zza(int r5, int r6, Map<K, V> map, zzgy zzgyVar, UB ub, zzjj<UT, UB> zzjjVar) {
        zzht<?, ?> zzp = this.zzzk.zzp(zzbn(r5));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzgyVar.zzf(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzjjVar.zzif();
                }
                zzfp zzap = zzfh.zzap(zzhu.zza(zzp, next.getKey(), next.getValue()));
                try {
                    zzhu.zza(zzap.zzew(), zzp, next.getKey(), next.getValue());
                    zzjjVar.zza((zzjj<UT, UB>) ub, r6, zzap.zzev());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.vision.zzir] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.vision.zzir] */
    @Override // com.google.android.gms.internal.vision.zzir
    public final boolean zzt(T t) {
        int r8;
        int r1 = -1;
        int r2 = 0;
        int r3 = 0;
        while (true) {
            boolean z = true;
            if (r2 >= this.zzze) {
                return !this.zzyz || this.zzzj.zze(t).isInitialized();
            }
            int r4 = this.zzzd[r2];
            int r6 = this.zzyu[r4];
            int zzbp = zzbp(r4);
            if (this.zzzb) {
                r8 = 0;
            } else {
                int r82 = this.zzyu[r4 + 2];
                int r10 = r82 & 1048575;
                r8 = 1 << (r82 >>> 20);
                if (r10 != r1) {
                    r3 = zzyt.getInt(t, r10);
                    r1 = r10;
                }
            }
            if (((268435456 & zzbp) != 0) && !zza((zzig<T>) t, r4, r3, r8)) {
                return false;
            }
            int r102 = (267386880 & zzbp) >>> 20;
            if (r102 == 9 || r102 == 17) {
                if (zza((zzig<T>) t, r4, r3, r8) && !zza(t, zzbp, zzbm(r4))) {
                    return false;
                }
            } else {
                if (r102 != 27) {
                    if (r102 == 60 || r102 == 68) {
                        if (zza((zzig<T>) t, r6, r4) && !zza(t, zzbp, zzbm(r4))) {
                            return false;
                        }
                    } else if (r102 != 49) {
                        if (r102 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzl = this.zzzk.zzl(zzjp.zzp(t, zzbp & 1048575));
                            if (!zzl.isEmpty()) {
                                if (this.zzzk.zzp(zzbn(r4)).zzyo.zzip() == zzkd.MESSAGE) {
                                    zzir<T> zzirVar = 0;
                                    Iterator<?> it = zzl.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzirVar == null) {
                                            zzirVar = zzin.zzho().zzf(next.getClass());
                                        }
                                        boolean zzt = zzirVar.zzt(next);
                                        zzirVar = zzirVar;
                                        if (!zzt) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzjp.zzp(t, zzbp & 1048575);
                if (!list.isEmpty()) {
                    ?? zzbm = zzbm(r4);
                    int r7 = 0;
                    while (true) {
                        if (r7 >= list.size()) {
                            break;
                        } else if (!zzbm.zzt(list.get(r7))) {
                            z = false;
                            break;
                        } else {
                            r7++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            }
            r2++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int r3, zzir zzirVar) {
        return zzirVar.zzt(zzjp.zzp(obj, r3 & 1048575));
    }

    private static void zza(int r1, Object obj, zzkg zzkgVar) throws IOException {
        if (obj instanceof String) {
            zzkgVar.zza(r1, (String) obj);
        } else {
            zzkgVar.zza(r1, (zzfh) obj);
        }
    }

    private final void zza(Object obj, int r4, zzis zzisVar) throws IOException {
        if (zzbr(r4)) {
            zzjp.zza(obj, r4 & 1048575, zzisVar.zzec());
        } else if (this.zzza) {
            zzjp.zza(obj, r4 & 1048575, zzisVar.readString());
        } else {
            zzjp.zza(obj, r4 & 1048575, zzisVar.zzed());
        }
    }

    private final int zzbp(int r2) {
        return this.zzyu[r2 + 1];
    }

    private final int zzbq(int r2) {
        return this.zzyu[r2 + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzjp.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzjp.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzjp.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzjp.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzjp.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int r3) {
        return zza((zzig<T>) t, r3) == zza((zzig<T>) t2, r3);
    }

    private final boolean zza(T t, int r3, int r4, int r5) {
        if (this.zzzb) {
            return zza((zzig<T>) t, r3);
        }
        return (r4 & r5) != 0;
    }

    private final boolean zza(T t, int r8) {
        if (this.zzzb) {
            int zzbp = zzbp(r8);
            long j = zzbp & 1048575;
            switch ((zzbp & 267386880) >>> 20) {
                case 0:
                    return zzjp.zzo(t, j) != 0.0d;
                case 1:
                    return zzjp.zzn(t, j) != 0.0f;
                case 2:
                    return zzjp.zzl(t, j) != 0;
                case 3:
                    return zzjp.zzl(t, j) != 0;
                case 4:
                    return zzjp.zzk(t, j) != 0;
                case 5:
                    return zzjp.zzl(t, j) != 0;
                case 6:
                    return zzjp.zzk(t, j) != 0;
                case 7:
                    return zzjp.zzm(t, j);
                case 8:
                    Object zzp = zzjp.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    } else if (zzp instanceof zzfh) {
                        return !zzfh.zzrx.equals(zzp);
                    } else {
                        throw new IllegalArgumentException();
                    }
                case 9:
                    return zzjp.zzp(t, j) != null;
                case 10:
                    return !zzfh.zzrx.equals(zzjp.zzp(t, j));
                case 11:
                    return zzjp.zzk(t, j) != 0;
                case 12:
                    return zzjp.zzk(t, j) != 0;
                case 13:
                    return zzjp.zzk(t, j) != 0;
                case 14:
                    return zzjp.zzl(t, j) != 0;
                case 15:
                    return zzjp.zzk(t, j) != 0;
                case 16:
                    return zzjp.zzl(t, j) != 0;
                case 17:
                    return zzjp.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int zzbq = zzbq(r8);
        return (zzjp.zzk(t, (long) (zzbq & 1048575)) & (1 << (zzbq >>> 20))) != 0;
    }

    private final void zzb(T t, int r5) {
        if (this.zzzb) {
            return;
        }
        int zzbq = zzbq(r5);
        long j = zzbq & 1048575;
        zzjp.zzb(t, j, zzjp.zzk(t, j) | (1 << (zzbq >>> 20)));
    }

    private final boolean zza(T t, int r4, int r5) {
        return zzjp.zzk(t, (long) (zzbq(r5) & 1048575)) == r4;
    }

    private final void zzb(T t, int r4, int r5) {
        zzjp.zzb(t, zzbq(r5) & 1048575, r4);
    }

    private final int zzbs(int r2) {
        if (r2 < this.zzyw || r2 > this.zzyx) {
            return -1;
        }
        return zzu(r2, 0);
    }

    private final int zzt(int r2, int r3) {
        if (r2 < this.zzyw || r2 > this.zzyx) {
            return -1;
        }
        return zzu(r2, r3);
    }

    private final int zzu(int r5, int r6) {
        int length = (this.zzyu.length / 3) - 1;
        while (r6 <= length) {
            int r1 = (length + r6) >>> 1;
            int r2 = r1 * 3;
            int r3 = this.zzyu[r2];
            if (r5 == r3) {
                return r2;
            }
            if (r5 < r3) {
                length = r1 - 1;
            } else {
                r6 = r1 + 1;
            }
        }
        return -1;
    }
}
