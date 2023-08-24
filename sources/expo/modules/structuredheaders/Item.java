package expo.modules.structuredheaders;

import com.android.tools.r8.annotations.SynthesizedClass;

/* loaded from: classes3.dex */
public interface Item<T> extends ListElement<T>, Parametrizable<T> {
    @Override // expo.modules.structuredheaders.Parametrizable
    Item<T> withParams(Parameters parameters);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: expo.modules.structuredheaders.Item$-CC  reason: invalid class name */
    /* loaded from: classes3.dex */
    public final /* synthetic */ class CC<T> {
    }
}
