package expo.modules.structuredheaders;

import com.android.tools.r8.annotations.SynthesizedClass;

/* loaded from: classes3.dex */
public interface NumberItem<T> extends Item<T>, LongSupplier {
    int getDivisor();

    @Override // expo.modules.structuredheaders.Item, expo.modules.structuredheaders.Parametrizable
    NumberItem<T> withParams(Parameters parameters);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: expo.modules.structuredheaders.NumberItem$-CC  reason: invalid class name */
    /* loaded from: classes3.dex */
    public final /* synthetic */ class CC<T> {
    }
}
