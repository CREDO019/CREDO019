package com.google.android.gms.internal.vision;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzabu uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzka {
    public static final zzka zzabm;
    public static final zzka zzabn;
    public static final zzka zzabo;
    public static final zzka zzabp;
    public static final zzka zzabq;
    public static final zzka zzabr;
    public static final zzka zzabs;
    public static final zzka zzabt;
    public static final zzka zzabu;
    public static final zzka zzabv;
    public static final zzka zzabw;
    public static final zzka zzabx;
    public static final zzka zzaby;
    public static final zzka zzabz;
    public static final zzka zzaca;
    public static final zzka zzacb;
    public static final zzka zzacc;
    public static final zzka zzacd;
    private static final /* synthetic */ zzka[] zzacg;
    private final zzkd zzace;
    private final int zzacf;

    public static zzka[] values() {
        return (zzka[]) zzacg.clone();
    }

    private zzka(String str, int r2, zzkd zzkdVar, int r4) {
        this.zzace = zzkdVar;
        this.zzacf = r4;
    }

    public final zzkd zzip() {
        return this.zzace;
    }

    public final int zziq() {
        return this.zzacf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzka(String str, int r2, zzkd zzkdVar, int r4, zzjx zzjxVar) {
        this(str, r2, zzkdVar, r4);
    }

    static {
        zzka zzkaVar = new zzka("DOUBLE", 0, zzkd.DOUBLE, 1);
        zzabm = zzkaVar;
        zzka zzkaVar2 = new zzka("FLOAT", 1, zzkd.FLOAT, 5);
        zzabn = zzkaVar2;
        zzka zzkaVar3 = new zzka("INT64", 2, zzkd.LONG, 0);
        zzabo = zzkaVar3;
        zzka zzkaVar4 = new zzka("UINT64", 3, zzkd.LONG, 0);
        zzabp = zzkaVar4;
        zzka zzkaVar5 = new zzka("INT32", 4, zzkd.INT, 0);
        zzabq = zzkaVar5;
        zzka zzkaVar6 = new zzka("FIXED64", 5, zzkd.LONG, 1);
        zzabr = zzkaVar6;
        zzka zzkaVar7 = new zzka("FIXED32", 6, zzkd.INT, 5);
        zzabs = zzkaVar7;
        zzka zzkaVar8 = new zzka("BOOL", 7, zzkd.BOOLEAN, 0);
        zzabt = zzkaVar8;
        final zzkd zzkdVar = zzkd.STRING;
        zzka zzkaVar9 = new zzka("STRING", 8, zzkdVar, 2) { // from class: com.google.android.gms.internal.vision.zzjz
        };
        zzabu = zzkaVar9;
        final zzkd zzkdVar2 = zzkd.MESSAGE;
        zzka zzkaVar10 = new zzka("GROUP", 9, zzkdVar2, 3) { // from class: com.google.android.gms.internal.vision.zzkc
        };
        zzabv = zzkaVar10;
        final zzkd zzkdVar3 = zzkd.MESSAGE;
        zzka zzkaVar11 = new zzka("MESSAGE", 10, zzkdVar3, 2) { // from class: com.google.android.gms.internal.vision.zzkb
        };
        zzabw = zzkaVar11;
        final zzkd zzkdVar4 = zzkd.BYTE_STRING;
        zzka zzkaVar12 = new zzka("BYTES", 11, zzkdVar4, 2) { // from class: com.google.android.gms.internal.vision.zzke
        };
        zzabx = zzkaVar12;
        zzka zzkaVar13 = new zzka("UINT32", 12, zzkd.INT, 0);
        zzaby = zzkaVar13;
        zzka zzkaVar14 = new zzka("ENUM", 13, zzkd.ENUM, 0);
        zzabz = zzkaVar14;
        zzka zzkaVar15 = new zzka("SFIXED32", 14, zzkd.INT, 5);
        zzaca = zzkaVar15;
        zzka zzkaVar16 = new zzka("SFIXED64", 15, zzkd.LONG, 1);
        zzacb = zzkaVar16;
        zzka zzkaVar17 = new zzka("SINT32", 16, zzkd.INT, 0);
        zzacc = zzkaVar17;
        zzka zzkaVar18 = new zzka("SINT64", 17, zzkd.LONG, 0);
        zzacd = zzkaVar18;
        zzacg = new zzka[]{zzkaVar, zzkaVar2, zzkaVar3, zzkaVar4, zzkaVar5, zzkaVar6, zzkaVar7, zzkaVar8, zzkaVar9, zzkaVar10, zzkaVar11, zzkaVar12, zzkaVar13, zzkaVar14, zzkaVar15, zzkaVar16, zzkaVar17, zzkaVar18};
    }
}
