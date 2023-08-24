package com.airbnb.android.react.maps;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class ViewChangesTracker {
    private static ViewChangesTracker instance;
    private final LinkedList<AirMapMarker> markers = new LinkedList<>();
    private boolean hasScheduledFrame = false;
    private final long fps = 40;
    private final LinkedList<AirMapMarker> markersToRemove = new LinkedList<>();
    private final Handler handler = new Handler(Looper.myLooper());
    private final Runnable updateRunnable = new Runnable() { // from class: com.airbnb.android.react.maps.ViewChangesTracker.1
        @Override // java.lang.Runnable
        public void run() {
            ViewChangesTracker.this.hasScheduledFrame = false;
            ViewChangesTracker.this.update();
            if (ViewChangesTracker.this.markers.size() > 0) {
                ViewChangesTracker.this.handler.postDelayed(ViewChangesTracker.this.updateRunnable, 40L);
            }
        }
    };

    private ViewChangesTracker() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewChangesTracker getInstance() {
        if (instance == null) {
            synchronized (ViewChangesTracker.class) {
                instance = new ViewChangesTracker();
            }
        }
        return instance;
    }

    public void addMarker(AirMapMarker airMapMarker) {
        this.markers.add(airMapMarker);
        if (this.hasScheduledFrame) {
            return;
        }
        this.hasScheduledFrame = true;
        this.handler.postDelayed(this.updateRunnable, 40L);
    }

    public void removeMarker(AirMapMarker airMapMarker) {
        this.markers.remove(airMapMarker);
    }

    public boolean containsMarker(AirMapMarker airMapMarker) {
        return this.markers.contains(airMapMarker);
    }

    public void update() {
        Iterator<AirMapMarker> it = this.markers.iterator();
        while (it.hasNext()) {
            AirMapMarker next = it.next();
            if (!next.updateCustomForTracking()) {
                this.markersToRemove.add(next);
            }
        }
        if (this.markersToRemove.size() > 0) {
            this.markers.removeAll(this.markersToRemove);
            this.markersToRemove.clear();
        }
    }
}
