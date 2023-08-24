package expo.modules.kotlin.functions;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ReadableArrayIterator;
import expo.modules.kotlin.ReadableArrayIteratorKt;
import expo.modules.kotlin.exception.ArgumentCastException;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.InvalidArgsNumberException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.AnyType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: AnyFunction.kt */
@Metadata(m184d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H ¢\u0006\u0002\b\u0017J\u001f\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0004¢\u0006\u0002\u0010\u001bJ'\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0004¢\u0006\u0002\u0010\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eR\u0014\u0010\b\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0084\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006 "}, m183d2 = {"Lexpo/modules/kotlin/functions/AnyFunction;", "", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "argsCount", "", "getArgsCount$expo_modules_core_release", "()I", "getDesiredArgsTypes", "()[Lexpo/modules/kotlin/types/AnyType;", "[Lexpo/modules/kotlin/types/AnyType;", "getName", "()Ljava/lang/String;", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "attachToJSObject$expo_modules_core_release", "convertArgs", "args", "Lcom/facebook/react/bridge/ReadableArray;", "(Lcom/facebook/react/bridge/ReadableArray;)[Ljava/lang/Object;", "([Ljava/lang/Object;)[Ljava/lang/Object;", "getCppRequiredTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class AnyFunction {
    private final AnyType[] desiredArgsTypes;
    private final String name;

    public abstract void attachToJSObject$expo_modules_core_release(AppContext appContext, JavaScriptModuleObject javaScriptModuleObject);

    public AnyFunction(String name, AnyType[] desiredArgsTypes) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        this.name = name;
        this.desiredArgsTypes = desiredArgsTypes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AnyType[] getDesiredArgsTypes() {
        return this.desiredArgsTypes;
    }

    public final int getArgsCount$expo_modules_core_release() {
        return this.desiredArgsTypes.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object[] convertArgs(ReadableArray args) throws CodedException {
        Intrinsics.checkNotNullParameter(args, "args");
        if (this.desiredArgsTypes.length != args.size()) {
            throw new InvalidArgsNumberException(args.size(), this.desiredArgsTypes.length);
        }
        int length = this.desiredArgsTypes.length;
        Object[] objArr = new Object[length];
        for (int r1 = 0; r1 < length; r1++) {
            objArr[r1] = null;
        }
        ReadableArrayIterator it = ReadableArrayIteratorKt.iterator(args);
        for (IndexedValue indexedValue : ArraysKt.withIndex(this.desiredArgsTypes)) {
            int component1 = indexedValue.component1();
            AnyType anyType = (AnyType) indexedValue.component2();
            Dynamic next = it.next();
            try {
                try {
                    objArr[component1] = anyType.convert(next);
                    Unit unit = Unit.INSTANCE;
                    Unit unit2 = Unit.INSTANCE;
                } catch (expo.modules.core.errors.CodedException e) {
                    String code = e.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    CodedException codedException = new CodedException(code, e.getMessage(), e.getCause());
                    KType kType = anyType.getKType();
                    ReadableType type = next.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "type");
                    throw new ArgumentCastException(kType, component1, type, codedException);
                } catch (CodedException e2) {
                    KType kType2 = anyType.getKType();
                    ReadableType type2 = next.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "type");
                    throw new ArgumentCastException(kType2, component1, type2, e2);
                } catch (Throwable th) {
                    KType kType3 = anyType.getKType();
                    ReadableType type3 = next.getType();
                    Intrinsics.checkNotNullExpressionValue(type3, "type");
                    throw new ArgumentCastException(kType3, component1, type3, new UnexpectedException(th));
                }
            } finally {
                next.recycle();
            }
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object[] convertArgs(Object[] args) throws CodedException {
        Intrinsics.checkNotNullParameter(args, "args");
        AnyType[] anyTypeArr = this.desiredArgsTypes;
        if (anyTypeArr.length != args.length) {
            throw new InvalidArgsNumberException(args.length, this.desiredArgsTypes.length);
        }
        int length = anyTypeArr.length;
        Object[] objArr = new Object[length];
        for (int r1 = 0; r1 < length; r1++) {
            objArr[r1] = null;
        }
        Iterator it = ArrayIteratorKt.iterator(args);
        for (IndexedValue indexedValue : ArraysKt.withIndex(this.desiredArgsTypes)) {
            int component1 = indexedValue.component1();
            AnyType anyType = (AnyType) indexedValue.component2();
            try {
                objArr[component1] = anyType.convert(it.next());
                Unit unit = Unit.INSTANCE;
            } catch (expo.modules.core.errors.CodedException e) {
                String code = e.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "e.code");
                throw new ArgumentCastException(anyType.getKType(), component1, ReadableType.String, new CodedException(code, e.getMessage(), e.getCause()));
            } catch (CodedException e2) {
                throw new ArgumentCastException(anyType.getKType(), component1, ReadableType.String, e2);
            } catch (Throwable th) {
                throw new ArgumentCastException(anyType.getKType(), component1, ReadableType.String, new UnexpectedException(th));
            }
        }
        return objArr;
    }

    public final List<ExpectedType> getCppRequiredTypes() {
        AnyType[] anyTypeArr = this.desiredArgsTypes;
        ArrayList arrayList = new ArrayList(anyTypeArr.length);
        int length = anyTypeArr.length;
        int r3 = 0;
        while (r3 < length) {
            AnyType anyType = anyTypeArr[r3];
            r3++;
            arrayList.add(anyType.getCppRequiredTypes());
        }
        return arrayList;
    }
}
