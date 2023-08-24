package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* loaded from: classes5.dex */
public class TransientReceiver extends AbstractReceiverValue {
    private static /* synthetic */ void $$$reportNull$$$0(int r4) {
        Object[] objArr = new Object[3];
        if (r4 != 2) {
            objArr[0] = SessionDescription.ATTR_TYPE;
        } else {
            objArr[0] = "newType";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/TransientReceiver";
        if (r4 != 2) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "replaceType";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public TransientReceiver(KotlinType kotlinType) {
        this(kotlinType, null);
        if (kotlinType == null) {
            $$$reportNull$$$0(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private TransientReceiver(KotlinType kotlinType, ReceiverValue receiverValue) {
        super(kotlinType, receiverValue);
        if (kotlinType == null) {
            $$$reportNull$$$0(1);
        }
    }

    public String toString() {
        return "{Transient} : " + getType();
    }
}
