package expo.modules.easclient;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EASClientID.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/easclient/EASClientID;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "uuid", "Ljava/util/UUID;", "getUuid", "()Ljava/util/UUID;", "uuid$delegate", "Lkotlin/Lazy;", "expo-eas-client_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EASClientID {
    private final Context context;
    private final Lazy uuid$delegate;

    public EASClientID(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.uuid$delegate = LazyKt.lazy(new Functions<UUID>() { // from class: expo.modules.easclient.EASClientID$uuid$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final UUID invoke() {
                Context context2;
                context2 = EASClientID.this.context;
                SharedPreferences sharedPreferences = context2.getSharedPreferences("dev.expo.EASSharedPreferences", 0);
                String string = sharedPreferences.getString("eas-client-id", null);
                if (string == null) {
                    string = UUID.randomUUID().toString();
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("eas-client-id", string);
                    edit.apply();
                }
                return UUID.fromString(string);
            }
        });
    }

    public final UUID getUuid() {
        Object value = this.uuid$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-uuid>(...)");
        return (UUID) value;
    }
}
