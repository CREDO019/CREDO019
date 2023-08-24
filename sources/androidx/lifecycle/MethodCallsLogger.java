package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MethodCallsLogger {
    private Map<String, Integer> mCalledMethods = new HashMap();

    public boolean approveCall(String str, int r6) {
        Integer num = this.mCalledMethods.get(str);
        int intValue = num != null ? num.intValue() : 0;
        boolean z = (intValue & r6) != 0;
        this.mCalledMethods.put(str, Integer.valueOf(r6 | intValue));
        return !z;
    }
}
