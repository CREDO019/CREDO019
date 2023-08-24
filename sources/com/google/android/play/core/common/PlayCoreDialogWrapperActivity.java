package com.google.android.play.core.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.google.android.play.core.internal.C2532bq;

/* loaded from: classes3.dex */
public class PlayCoreDialogWrapperActivity extends Activity {

    /* renamed from: a */
    private ResultReceiver f788a;

    /* renamed from: a */
    public static void m824a(Context context) {
        C2532bq.m750a(context.getPackageManager(), new ComponentName(context.getPackageName(), "com.google.android.play.core.common.PlayCoreDialogWrapperActivity"), 1);
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int r1, int r2, Intent intent) {
        ResultReceiver resultReceiver;
        Bundle bundle;
        int r3;
        super.onActivityResult(r1, r2, intent);
        if (r1 == 0 && (resultReceiver = this.f788a) != null) {
            if (r2 == -1) {
                bundle = new Bundle();
                r3 = 1;
            } else if (r2 == 0) {
                bundle = new Bundle();
                r3 = 2;
            }
            resultReceiver.send(r3, bundle);
        }
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f788a = (ResultReceiver) bundle.getParcelable("result_receiver");
            return;
        }
        this.f788a = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
        try {
            startIntentSenderForResult(((PendingIntent) getIntent().getExtras().get("confirmation_intent")).getIntentSender(), 0, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException unused) {
            ResultReceiver resultReceiver = this.f788a;
            if (resultReceiver != null) {
                resultReceiver.send(3, new Bundle());
            }
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("result_receiver", this.f788a);
    }
}
