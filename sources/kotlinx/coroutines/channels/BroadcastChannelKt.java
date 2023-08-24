package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: BroadcastChannel.kt */
@Metadata(m184d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, m183d2 = {"BroadcastChannel", "Lkotlinx/coroutines/channels/BroadcastChannel;", ExifInterface.LONGITUDE_EAST, "capacity", "", "kotlinx-coroutines-core"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class BroadcastChannelKt {
    public static final <E> BroadcastChannel<E> BroadcastChannel(int r1) {
        if (r1 != -2) {
            if (r1 != -1) {
                if (r1 != 0) {
                    if (r1 == Integer.MAX_VALUE) {
                        throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
                    }
                    return new ArrayBroadcastChannel(r1);
                }
                throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
            }
            return new ConflatedBroadcastChannel();
        }
        return new ArrayBroadcastChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
    }
}
