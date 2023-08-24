package expo.modules.kotlin.jni;

import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.util.JSStackTrace;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.onesignal.OneSignalDbContract;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.types.JSTypeConverter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromiseImpl.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0082\bJ$\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u0016\u0010\u0004\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/kotlin/jni/PromiseImpl;", "Lexpo/modules/kotlin/Promise;", "resolveBlock", "Lexpo/modules/kotlin/jni/JavaCallback;", "rejectBlock", "(Lexpo/modules/kotlin/jni/JavaCallback;Lexpo/modules/kotlin/jni/JavaCallback;)V", "getRejectBlock$expo_modules_core_release", "()Lexpo/modules/kotlin/jni/JavaCallback;", "getResolveBlock$expo_modules_core_release", "wasResolve", "", "checkIfWasResolved", "", TtmlNode.TAG_BODY, "Lkotlin/Function0;", "reject", "code", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "cause", "", "resolve", "value", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PromiseImpl implements Promise {
    private final JavaCallback rejectBlock;
    private final JavaCallback resolveBlock;
    private boolean wasResolve;

    public PromiseImpl(JavaCallback resolveBlock, JavaCallback rejectBlock) {
        Intrinsics.checkNotNullParameter(resolveBlock, "resolveBlock");
        Intrinsics.checkNotNullParameter(rejectBlock, "rejectBlock");
        this.resolveBlock = resolveBlock;
        this.rejectBlock = rejectBlock;
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(CodedException codedException) {
        Promise.DefaultImpls.reject(this, codedException);
    }

    public final JavaCallback getResolveBlock$expo_modules_core_release() {
        return this.resolveBlock;
    }

    public final JavaCallback getRejectBlock$expo_modules_core_release() {
        return this.rejectBlock;
    }

    private final void checkIfWasResolved(Functions<Unit> functions) {
        if (this.wasResolve) {
            return;
        }
        functions.invoke();
        this.wasResolve = true;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(Object obj) {
        if (this.wasResolve) {
            return;
        }
        getResolveBlock$expo_modules_core_release().invoke(JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, obj, null, 2, null));
        this.wasResolve = true;
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(String code, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(code, "code");
        if (this.wasResolve) {
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("code", code);
        if (str != null) {
            writableNativeMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str);
        } else if (th != null) {
            writableNativeMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, th.getMessage());
        } else {
            writableNativeMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "Error not specified.");
        }
        writableNativeMap.putNull("userInfo");
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "cause.stackTrace");
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            for (int r0 = 0; r0 < stackTrace.length && r0 < 50; r0++) {
                StackTraceElement stackTraceElement = stackTrace[r0];
                WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString("class", stackTraceElement.getClassName());
                writableNativeMap2.putString("file", stackTraceElement.getFileName());
                writableNativeMap2.putInt("lineNumber", stackTraceElement.getLineNumber());
                writableNativeMap2.putString(JSStackTrace.METHOD_NAME_KEY, stackTraceElement.getMethodName());
                writableNativeArray.pushMap(writableNativeMap2);
            }
            writableNativeMap.putArray("nativeStackAndroid", writableNativeArray);
        } else {
            writableNativeMap.putArray("nativeStackAndroid", new WritableNativeArray());
        }
        getRejectBlock$expo_modules_core_release().invoke((Object) writableNativeMap);
        this.wasResolve = true;
    }
}
