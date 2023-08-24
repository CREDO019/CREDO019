package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

/* loaded from: classes.dex */
public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public AppWidgetTarget(Context context, int r2, int r3, int r4, RemoteViews remoteViews, int... r6) {
        super(r2, r3);
        if (r6.length == 0) {
            throw new IllegalArgumentException("WidgetIds must have length > 0");
        }
        this.context = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.widgetIds = (int[]) Preconditions.checkNotNull(r6, "WidgetIds can not be null!");
        this.viewId = r4;
        this.componentName = null;
    }

    public AppWidgetTarget(Context context, int r9, RemoteViews remoteViews, int... r11) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, r9, remoteViews, r11);
    }

    public AppWidgetTarget(Context context, int r2, int r3, int r4, RemoteViews remoteViews, ComponentName componentName) {
        super(r2, r3);
        this.context = (Context) Preconditions.checkNotNull(context, "Context can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.componentName = (ComponentName) Preconditions.checkNotNull(componentName, "ComponentName can not be null!");
        this.viewId = r4;
        this.widgetIds = null;
    }

    public AppWidgetTarget(Context context, int r9, RemoteViews remoteViews, ComponentName componentName) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, r9, remoteViews, componentName);
    }

    private void update() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.context);
        ComponentName componentName = this.componentName;
        if (componentName != null) {
            appWidgetManager.updateAppWidget(componentName, this.remoteViews);
        } else {
            appWidgetManager.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
