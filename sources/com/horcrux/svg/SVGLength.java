package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class SVGLength {
    final UnitType unit;
    final double value;

    /* loaded from: classes3.dex */
    public enum UnitType {
        UNKNOWN,
        NUMBER,
        PERCENTAGE,
        EMS,
        EXS,
        PX,
        CM,
        MM,
        IN,
        PT,
        PC
    }

    private SVGLength() {
        this.value = 0.0d;
        this.unit = UnitType.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SVGLength(double d) {
        this.value = d;
        this.unit = UnitType.NUMBER;
    }

    private SVGLength(String str) {
        String trim = str.trim();
        int length = trim.length();
        int r1 = length - 1;
        if (length == 0 || trim.equals("normal")) {
            this.unit = UnitType.UNKNOWN;
            this.value = 0.0d;
        } else if (trim.codePointAt(r1) == 37) {
            this.unit = UnitType.PERCENTAGE;
            this.value = Double.valueOf(trim.substring(0, r1)).doubleValue();
        } else {
            int r12 = length - 2;
            if (r12 > 0) {
                String substring = trim.substring(r12);
                substring.hashCode();
                char c = 65535;
                switch (substring.hashCode()) {
                    case 3178:
                        if (substring.equals("cm")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 3240:
                        if (substring.equals("em")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3251:
                        if (substring.equals("ex")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 3365:
                        if (substring.equals("in")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 3488:
                        if (substring.equals("mm")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 3571:
                        if (substring.equals("pc")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 3588:
                        if (substring.equals("pt")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 3592:
                        if (substring.equals("px")) {
                            c = 7;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.unit = UnitType.CM;
                        length = r12;
                        break;
                    case 1:
                        this.unit = UnitType.EMS;
                        length = r12;
                        break;
                    case 2:
                        this.unit = UnitType.EXS;
                        length = r12;
                        break;
                    case 3:
                        this.unit = UnitType.IN;
                        length = r12;
                        break;
                    case 4:
                        this.unit = UnitType.MM;
                        length = r12;
                        break;
                    case 5:
                        this.unit = UnitType.PC;
                        length = r12;
                        break;
                    case 6:
                        this.unit = UnitType.PT;
                        length = r12;
                        break;
                    case 7:
                        this.unit = UnitType.NUMBER;
                        length = r12;
                        break;
                    default:
                        this.unit = UnitType.NUMBER;
                        break;
                }
                this.value = Double.valueOf(trim.substring(0, length)).doubleValue();
                return;
            }
            this.unit = UnitType.NUMBER;
            this.value = Double.valueOf(trim).doubleValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.SVGLength$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C34001 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] r0 = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = r0;
            try {
                r0[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SVGLength from(Dynamic dynamic) {
        int r0 = C34001.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (r0 != 1) {
            if (r0 == 2) {
                return new SVGLength(dynamic.asString());
            }
            return new SVGLength();
        }
        return new SVGLength(dynamic.asDouble());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SVGLength from(String str) {
        return str != null ? new SVGLength(str) : new SVGLength();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SVGLength from(Double d) {
        return d != null ? new SVGLength(d.doubleValue()) : new SVGLength();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(Dynamic dynamic) {
        int r0 = C34001.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                return null;
            }
            return dynamic.asString();
        }
        return String.valueOf(dynamic.asDouble());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<SVGLength> arrayFrom(Dynamic dynamic) {
        int r0 = C34001.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (r0 == 1) {
            ArrayList<SVGLength> arrayList = new ArrayList<>(1);
            arrayList.add(new SVGLength(dynamic.asDouble()));
            return arrayList;
        } else if (r0 == 2) {
            ArrayList<SVGLength> arrayList2 = new ArrayList<>(1);
            arrayList2.add(new SVGLength(dynamic.asString()));
            return arrayList2;
        } else if (r0 != 3) {
            return null;
        } else {
            ReadableArray asArray = dynamic.asArray();
            int size = asArray.size();
            ArrayList<SVGLength> arrayList3 = new ArrayList<>(size);
            for (int r2 = 0; r2 < size; r2++) {
                arrayList3.add(from(asArray.getDynamic(r2)));
            }
            return arrayList3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<SVGLength> arrayFrom(ReadableArray readableArray) {
        int size = readableArray.size();
        ArrayList<SVGLength> arrayList = new ArrayList<>(size);
        for (int r2 = 0; r2 < size; r2++) {
            arrayList.add(from(readableArray.getDynamic(r2)));
        }
        return arrayList;
    }
}
