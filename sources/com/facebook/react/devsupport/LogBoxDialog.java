package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import com.facebook.react.C1413R;

/* loaded from: classes.dex */
public class LogBoxDialog extends Dialog {
    public LogBoxDialog(Activity activity, View view) {
        super(activity, C1413R.C1420style.Theme_Catalyst_LogBox);
        requestWindowFeature(1);
        setContentView(view);
    }
}
