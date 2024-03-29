package expo.modules.kotlin.types.net;

import android.net.Uri;
import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.types.DynamicAwareTypeConverters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UriTypeConverter.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/kotlin/types/net/UriTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Landroid/net/Uri;", "isOptional", "", "(Z)V", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class UriTypeConverter extends DynamicAwareTypeConverters<Uri> {
    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    public UriTypeConverter(boolean z) {
        super(z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Uri convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Uri parse = Uri.parse(value.asString());
        Intrinsics.checkNotNullExpressionValue(parse, "parse(stringUri)");
        return parse;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Uri convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Uri parse = Uri.parse((String) value);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(stringUri)");
        return parse;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(CppType.STRING);
    }
}
