package io.nlopez.smartlocation.p022rx;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import com.google.android.gms.location.DetectedActivity;
import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.OnGeocodingListener;
import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geocoding.utils.LocationAddress;
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence;
import java.util.List;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.functions.Action0;

/* renamed from: io.nlopez.smartlocation.rx.ObservableFactory */
/* loaded from: classes5.dex */
public class ObservableFactory {
    private ObservableFactory() {
        throw new AssertionError("This should not be instantiated");
    }

    public static Observable<Location> from(final SmartLocation.LocationControl locationControl) {
        return Observable.create(new Observable.OnSubscribe<Location>() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.2
            @Override // p042rx.functions.Action1
            public void call(final Subscriber<? super Location> subscriber) {
                SmartLocation.LocationControl.this.start(new OnLocationUpdatedListener() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.2.1
                    @Override // io.nlopez.smartlocation.OnLocationUpdatedListener
                    public void onLocationUpdated(Location location) {
                        subscriber.onNext(location);
                    }
                });
            }
        }).doOnUnsubscribe(new Action0() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.1
            @Override // p042rx.functions.Action0
            public void call() {
                SmartLocation.LocationControl.this.stop();
            }
        });
    }

    public static Observable<DetectedActivity> from(final SmartLocation.ActivityRecognitionControl activityRecognitionControl) {
        return Observable.create(new Observable.OnSubscribe<DetectedActivity>() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.4
            @Override // p042rx.functions.Action1
            public void call(final Subscriber<? super DetectedActivity> subscriber) {
                SmartLocation.ActivityRecognitionControl.this.start(new OnActivityUpdatedListener() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.4.1
                    @Override // io.nlopez.smartlocation.OnActivityUpdatedListener
                    public void onActivityUpdated(DetectedActivity detectedActivity) {
                        subscriber.onNext(detectedActivity);
                    }
                });
            }
        }).doOnUnsubscribe(new Action0() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.3
            @Override // p042rx.functions.Action0
            public void call() {
                SmartLocation.ActivityRecognitionControl.this.stop();
            }
        });
    }

    public static Observable<TransitionGeofence> from(final SmartLocation.GeofencingControl geofencingControl) {
        return Observable.create(new Observable.OnSubscribe<TransitionGeofence>() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.6
            @Override // p042rx.functions.Action1
            public void call(final Subscriber<? super TransitionGeofence> subscriber) {
                SmartLocation.GeofencingControl.this.start(new OnGeofencingTransitionListener() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.6.1
                    @Override // io.nlopez.smartlocation.OnGeofencingTransitionListener
                    public void onGeofenceTransition(TransitionGeofence transitionGeofence) {
                        subscriber.onNext(transitionGeofence);
                    }
                });
            }
        }).doOnUnsubscribe(new Action0() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.5
            @Override // p042rx.functions.Action0
            public void call() {
                SmartLocation.GeofencingControl.this.stop();
            }
        });
    }

    public static Observable<List<LocationAddress>> fromAddress(final Context context, final String str, final int r3) {
        return Observable.create(new Observable.OnSubscribe<List<LocationAddress>>() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.7
            @Override // p042rx.functions.Action1
            public void call(final Subscriber<? super List<LocationAddress>> subscriber) {
                SmartLocation.with(context).geocoding().add(str, r3).start(new OnGeocodingListener() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.7.1
                    @Override // io.nlopez.smartlocation.OnGeocodingListener
                    public void onLocationResolved(String str2, List<LocationAddress> list) {
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    public static Observable<List<Address>> fromLocation(final Context context, final Location location, final int r3) {
        return Observable.create(new Observable.OnSubscribe<List<Address>>() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.8
            @Override // p042rx.functions.Action1
            public void call(final Subscriber<? super List<Address>> subscriber) {
                SmartLocation.with(context).geocoding().add(location, r3).start(new OnReverseGeocodingListener() { // from class: io.nlopez.smartlocation.rx.ObservableFactory.8.1
                    @Override // io.nlopez.smartlocation.OnReverseGeocodingListener
                    public void onAddressResolved(Location location2, List<Address> list) {
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }
}
