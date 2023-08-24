package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeTaskQueue;

@Metadata(m184d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m183d2 = {"Lkotlinx/coroutines/scheduling/GlobalQueue;", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "Lkotlinx/coroutines/scheduling/Task;", "()V", "kotlinx-coroutines-core"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlinx.coroutines.scheduling.GlobalQueue */
/* loaded from: classes5.dex */
public final class Tasks extends LockFreeTaskQueue<Task> {
    public Tasks() {
        super(false);
    }
}
