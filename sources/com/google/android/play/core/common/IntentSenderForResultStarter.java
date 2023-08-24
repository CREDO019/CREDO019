package com.google.android.play.core.common;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

/* loaded from: classes3.dex */
public interface IntentSenderForResultStarter {
    void startIntentSenderForResult(IntentSender intentSender, int r2, Intent intent, int r4, int r5, int r6, Bundle bundle) throws IntentSender.SendIntentException;
}
