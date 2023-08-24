package kotlin.jvm.internal;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028\u0000H$¢\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000bX\u0082\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r¨\u0006\u0019"}, m183d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", ViewProps.POSITION, "getPosition", "()I", "setPosition", "spreads", "", "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final int size;
    private final T[] spreads;

    private static /* synthetic */ void getSpreads$annotations() {
    }

    protected abstract int getSize(T t);

    public PrimitiveSpreadBuilder(int r1) {
        this.size = r1;
        this.spreads = (T[]) new Object[r1];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getPosition() {
        return this.position;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPosition(int r1) {
        this.position = r1;
    }

    public final void addSpread(T spreadArgument) {
        Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
        T[] tArr = this.spreads;
        int r1 = this.position;
        this.position = r1 + 1;
        tArr[r1] = spreadArgument;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int size() {
        int r0 = this.size - 1;
        int r2 = 0;
        if (r0 >= 0) {
            int r3 = 0;
            while (true) {
                T t = this.spreads[r3];
                r2 += t != null ? getSize(t) : 1;
                if (r3 == r0) {
                    break;
                }
                r3++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T toArray(T values, T result) {
        int r4;
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(result, "result");
        int r0 = this.size - 1;
        int r1 = 0;
        if (r0 >= 0) {
            int r2 = 0;
            int r3 = 0;
            r4 = 0;
            while (true) {
                T t = this.spreads[r2];
                if (t != null) {
                    if (r3 < r2) {
                        int r6 = r2 - r3;
                        System.arraycopy(values, r3, result, r4, r6);
                        r4 += r6;
                    }
                    int size = getSize(t);
                    System.arraycopy(t, 0, result, r4, size);
                    r4 += size;
                    r3 = r2 + 1;
                }
                if (r2 == r0) {
                    break;
                }
                r2++;
            }
            r1 = r3;
        } else {
            r4 = 0;
        }
        int r02 = this.size;
        if (r1 < r02) {
            System.arraycopy(values, r1, result, r4, r02 - r1);
        }
        return result;
    }
}
