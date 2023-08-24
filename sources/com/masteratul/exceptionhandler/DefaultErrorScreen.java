package com.masteratul.exceptionhandler;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;

/* loaded from: classes3.dex */
public class DefaultErrorScreen extends Activity {
    private static String TAG = "RN_ERROR_HANDLER";
    private Button quitButton;
    private Button relaunchButton;
    private Button showDetailsButton;
    private TextView stackTraceView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        try {
            str = getIntent().getExtras().getString("stack_trace_string");
        } catch (Exception e) {
            Log.e(TAG, String.format("Was not able to get StackTrace: %s", e.getMessage()));
            str = "StackTrace unavailable";
        }
        setContentView(C3468R.layout.default_error_screen);
        this.quitButton = (Button) findViewById(C3468R.C3472id.eh_quit_button);
        this.relaunchButton = (Button) findViewById(C3468R.C3472id.eh_restart_button);
        this.showDetailsButton = (Button) findViewById(C3468R.C3472id.eh_show_details_button);
        TextView textView = (TextView) findViewById(C3468R.C3472id.eh_stack_trace_text_view);
        this.stackTraceView = textView;
        textView.setText(str);
        this.showDetailsButton.setOnClickListener(new View.OnClickListener() { // from class: com.masteratul.exceptionhandler.DefaultErrorScreen.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DefaultErrorScreen.this.stackTraceView.getVisibility() == 0) {
                    DefaultErrorScreen.this.stackTraceView.setVisibility(8);
                    DefaultErrorScreen.this.showDetailsButton.setText("SHOW DETAILS");
                    return;
                }
                DefaultErrorScreen.this.stackTraceView.setVisibility(0);
                DefaultErrorScreen.this.showDetailsButton.setText("HIDE DETAILS");
            }
        });
        this.relaunchButton.setOnClickListener(new View.OnClickListener() { // from class: com.masteratul.exceptionhandler.DefaultErrorScreen.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DefaultErrorScreen.doRestart(DefaultErrorScreen.this.getApplicationContext());
            }
        });
        this.quitButton.setOnClickListener(new View.OnClickListener() { // from class: com.masteratul.exceptionhandler.DefaultErrorScreen.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    public static void doRestart(Context context) {
        try {
            if (context != null) {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
                    if (launchIntentForPackage != null) {
                        launchIntentForPackage.addFlags(67108864);
                        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 100, PendingIntent.getActivity(context, 654311, launchIntentForPackage, 268435456));
                        System.exit(0);
                        return;
                    }
                    throw new Exception("Was not able to restart application, mStartActivity null");
                }
                throw new Exception("Was not able to restart application, PM null");
            }
            throw new Exception("Was not able to restart application, Context null");
        } catch (Exception unused) {
            Log.e(TAG, "Was not able to restart application");
        }
    }
}
