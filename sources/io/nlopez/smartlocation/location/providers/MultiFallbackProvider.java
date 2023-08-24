package io.nlopez.smartlocation.location.providers;

import android.content.Context;
import android.location.Location;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.ServiceLocationProvider;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.utils.Logger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes5.dex */
public class MultiFallbackProvider implements LocationProvider {
    private Context context;
    private LocationProvider currentProvider;
    private OnLocationUpdatedListener locationListener;
    private LocationParams locationParams;
    private Logger logger;
    private Queue<LocationProvider> providers = new LinkedList();
    private boolean shouldStart;
    private boolean singleUpdate;

    MultiFallbackProvider() {
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void init(Context context, Logger logger) {
        this.context = context;
        this.logger = logger;
        LocationProvider currentProvider = getCurrentProvider();
        if (currentProvider != null) {
            currentProvider.init(context, logger);
        }
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void start(OnLocationUpdatedListener onLocationUpdatedListener, LocationParams locationParams, boolean z) {
        this.shouldStart = true;
        this.locationListener = onLocationUpdatedListener;
        this.locationParams = locationParams;
        this.singleUpdate = z;
        LocationProvider currentProvider = getCurrentProvider();
        if (currentProvider != null) {
            currentProvider.start(onLocationUpdatedListener, locationParams, z);
        }
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void stop() {
        LocationProvider currentProvider = getCurrentProvider();
        if (currentProvider != null) {
            currentProvider.stop();
        }
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public Location getLastLocation() {
        LocationProvider currentProvider = getCurrentProvider();
        if (currentProvider == null) {
            return null;
        }
        return currentProvider.getLastLocation();
    }

    boolean addProvider(LocationProvider locationProvider) {
        return this.providers.add(locationProvider);
    }

    Collection<LocationProvider> getProviders() {
        return this.providers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationProvider getCurrentProvider() {
        if (this.currentProvider == null && !this.providers.isEmpty()) {
            this.currentProvider = this.providers.poll();
        }
        return this.currentProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fallbackProvider() {
        if (this.providers.isEmpty()) {
            return;
        }
        this.currentProvider.stop();
        LocationProvider poll = this.providers.poll();
        this.currentProvider = poll;
        poll.init(this.context, this.logger);
        if (this.shouldStart) {
            this.currentProvider.start(this.locationListener, this.locationParams, this.singleUpdate);
        }
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        private MultiFallbackProvider builtProvider = new MultiFallbackProvider();

        public Builder withGooglePlayServicesProvider() {
            return withServiceProvider(new LocationGooglePlayServicesProvider());
        }

        public Builder withDefaultProvider() {
            return withProvider(new LocationManagerProvider());
        }

        public Builder withServiceProvider(ServiceLocationProvider serviceLocationProvider) {
            serviceLocationProvider.setServiceListener(new FallbackListenerWrapper(this.builtProvider, serviceLocationProvider));
            return withProvider(serviceLocationProvider);
        }

        public Builder withProvider(LocationProvider locationProvider) {
            this.builtProvider.addProvider(locationProvider);
            return this;
        }

        public MultiFallbackProvider build() {
            if (this.builtProvider.providers.isEmpty()) {
                withDefaultProvider();
            }
            return this.builtProvider;
        }
    }
}
