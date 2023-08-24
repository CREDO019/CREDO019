package expo.modules.kotlin.types;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.reflect.KType;

/* compiled from: TypeConverterProvider.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/kotlin/types/TypeConverterProvider;", "", "obtainTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KType;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface TypeConverterProvider {
    TypeConverter<?> obtainTypeConverter(KType kType);
}
