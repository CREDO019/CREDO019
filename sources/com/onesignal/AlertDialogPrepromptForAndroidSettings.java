package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.onesignal.AlertDialogPrepromptForAndroidSettings;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlertDialogPrepromptForAndroidSettings.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\r"}, m183d2 = {"Lcom/onesignal/AlertDialogPrepromptForAndroidSettings;", "", "()V", "show", "", "activity", "Landroid/app/Activity;", "titlePrefix", "", "previouslyDeniedPostfix", "callback", "Lcom/onesignal/AlertDialogPrepromptForAndroidSettings$Callback;", "Callback", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class AlertDialogPrepromptForAndroidSettings {
    public static final AlertDialogPrepromptForAndroidSettings INSTANCE = new AlertDialogPrepromptForAndroidSettings();

    /* compiled from: AlertDialogPrepromptForAndroidSettings.kt */
    @Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, m183d2 = {"Lcom/onesignal/AlertDialogPrepromptForAndroidSettings$Callback;", "", "onAccept", "", "onDecline", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
    /* loaded from: classes3.dex */
    public interface Callback {
        void onAccept();

        void onDecline();
    }

    private AlertDialogPrepromptForAndroidSettings() {
    }

    public final void show(Activity activity, String titlePrefix, String previouslyDeniedPostfix, final Callback callback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(titlePrefix, "titlePrefix");
        Intrinsics.checkNotNullParameter(previouslyDeniedPostfix, "previouslyDeniedPostfix");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String string = activity.getString(C3644R.string.permission_not_available_title);
        Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…sion_not_available_title)");
        String format = String.format(string, Arrays.copyOf(new Object[]{titlePrefix}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(this, *args)");
        String string2 = activity.getString(C3644R.string.permission_not_available_message);
        Intrinsics.checkNotNullExpressionValue(string2, "activity.getString(R.str…on_not_available_message)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{previouslyDeniedPostfix}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "java.lang.String.format(this, *args)");
        new AlertDialog.Builder(activity).setTitle(format).setMessage(format2).setPositiveButton(C3644R.string.permission_not_available_open_settings_option, new DialogInterface.OnClickListener() { // from class: com.onesignal.AlertDialogPrepromptForAndroidSettings$show$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r2) {
                AlertDialogPrepromptForAndroidSettings.Callback.this.onAccept();
            }
        }).setNegativeButton(17039369, new DialogInterface.OnClickListener() { // from class: com.onesignal.AlertDialogPrepromptForAndroidSettings$show$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int r2) {
                AlertDialogPrepromptForAndroidSettings.Callback.this.onDecline();
            }
        }).show();
    }
}
