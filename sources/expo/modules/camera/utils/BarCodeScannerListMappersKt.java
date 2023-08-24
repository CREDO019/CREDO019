package expo.modules.camera.utils;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: BarCodeScannerListMappers.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a9\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0086\bø\u0001\u0000\u001a9\u0010\t\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, m183d2 = {"mapX", "", "", "", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "it", "mapY", "expo-camera_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerListMappersKt {
    public static final void mapY(List<Integer> list, Function1<? super Integer, Integer> block) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        IntProgression step = RangesKt.step(RangesKt.until(0, list.size()), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
            return;
        }
        while (true) {
            int r3 = first + step2;
            list.set(first, block.invoke(Integer.valueOf(first)));
            if (first == last) {
                return;
            }
            first = r3;
        }
    }

    public static final void mapX(List<Integer> list, Function1<? super Integer, Integer> block) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        IntProgression step = RangesKt.step(RangesKt.until(1, list.size()), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
            return;
        }
        while (true) {
            int r3 = first + step2;
            list.set(first, block.invoke(Integer.valueOf(first)));
            if (first == last) {
                return;
            }
            first = r3;
        }
    }
}
