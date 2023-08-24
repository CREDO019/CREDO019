package kotlin.sequences;

import com.onesignal.NotificationBundleProcessor;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Sequences.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", NewHtcHomeBadger.COUNT, "getCount", "()I", "drop", NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, "iterator", "", "take", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    private final int endIndex;
    private final Sequence<T> sequence;
    private final int startIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public SubSequence(Sequence<? extends T> sequence, int r4, int r5) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.startIndex = r4;
        this.endIndex = r5;
        if (!(r4 >= 0)) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + r4).toString());
        }
        if (!(r5 >= 0)) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + r5).toString());
        }
        if (r5 >= r4) {
            return;
        }
        throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + r5 + " < " + r4).toString());
    }

    private final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int r4) {
        return r4 >= getCount() ? SequencesKt.emptySequence() : new SubSequence(this.sequence, this.startIndex + r4, this.endIndex);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int r4) {
        if (r4 >= getCount()) {
            return this;
        }
        Sequence<T> sequence = this.sequence;
        int r2 = this.startIndex;
        return new SubSequence(sequence, r2, r4 + r2);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new SubSequence$iterator$1(this);
    }
}
