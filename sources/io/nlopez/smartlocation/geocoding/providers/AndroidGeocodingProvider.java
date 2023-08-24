package io.nlopez.smartlocation.geocoding.providers;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import io.nlopez.smartlocation.OnGeocodingListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.geocoding.GeocodingProvider;
import io.nlopez.smartlocation.geocoding.utils.LocationAddress;
import io.nlopez.smartlocation.utils.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class AndroidGeocodingProvider implements GeocodingProvider {
    private static final String BROADCAST_DIRECT_GEOCODING_ACTION = AndroidGeocodingProvider.class.getCanonicalName() + ".DIRECT_GEOCODE_ACTION";
    private static final String BROADCAST_REVERSE_GEOCODING_ACTION = AndroidGeocodingProvider.class.getCanonicalName() + ".REVERSE_GEOCODE_ACTION";
    private static final String DIRECT_GEOCODING_ID = "direct";
    private static final String LOCALE_ID = "locale";
    private static final String LOCATION_ID = "location";
    private static final String NAME_ID = "name";
    private static final String RESULT_ID = "result";
    private static final String REVERSE_GEOCODING_ID = "reverse";
    private Context context;
    private BroadcastReceiver directReceiver;
    private HashMap<Location, Integer> fromLocationList;
    private HashMap<String, Integer> fromNameList;
    private OnGeocodingListener geocodingListener;
    private Locale locale;
    private Logger logger;
    private OnReverseGeocodingListener reverseGeocodingListener;
    private BroadcastReceiver reverseReceiver;

    public AndroidGeocodingProvider() {
        this(Locale.getDefault());
    }

    public AndroidGeocodingProvider(Locale locale) {
        this.directReceiver = new BroadcastReceiver() { // from class: io.nlopez.smartlocation.geocoding.providers.AndroidGeocodingProvider.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (AndroidGeocodingProvider.BROADCAST_DIRECT_GEOCODING_ACTION.equals(intent.getAction())) {
                    AndroidGeocodingProvider.this.logger.mo195d("sending new direct geocoding response", new Object[0]);
                    if (AndroidGeocodingProvider.this.geocodingListener != null) {
                        AndroidGeocodingProvider.this.geocodingListener.onLocationResolved(intent.getStringExtra("name"), intent.getParcelableArrayListExtra(AndroidGeocodingProvider.RESULT_ID));
                    }
                }
            }
        };
        this.reverseReceiver = new BroadcastReceiver() { // from class: io.nlopez.smartlocation.geocoding.providers.AndroidGeocodingProvider.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (AndroidGeocodingProvider.BROADCAST_REVERSE_GEOCODING_ACTION.equals(intent.getAction())) {
                    AndroidGeocodingProvider.this.logger.mo195d("sending new reverse geocoding response", new Object[0]);
                    if (AndroidGeocodingProvider.this.reverseGeocodingListener != null) {
                        AndroidGeocodingProvider.this.reverseGeocodingListener.onAddressResolved((Location) intent.getParcelableExtra("location"), (ArrayList) intent.getSerializableExtra(AndroidGeocodingProvider.RESULT_ID));
                    }
                }
            }
        };
        if (locale == null) {
            throw new RuntimeException("Locale is null");
        }
        this.locale = locale;
        this.fromNameList = new HashMap<>();
        this.fromLocationList = new HashMap<>();
        if (!Geocoder.isPresent()) {
            throw new RuntimeException("Android Geocoder not present. Please check if Geocoder.isPresent() before invoking the search");
        }
    }

    @Override // io.nlopez.smartlocation.geocoding.GeocodingProvider
    public void init(Context context, Logger logger) {
        this.logger = logger;
        this.context = context;
    }

    @Override // io.nlopez.smartlocation.geocoding.GeocodingProvider
    public void addName(String str, int r3) {
        this.fromNameList.put(str, Integer.valueOf(r3));
    }

    @Override // io.nlopez.smartlocation.geocoding.GeocodingProvider
    public void addLocation(Location location, int r3) {
        this.fromLocationList.put(location, Integer.valueOf(r3));
    }

    @Override // io.nlopez.smartlocation.geocoding.GeocodingProvider
    public void start(OnGeocodingListener onGeocodingListener, OnReverseGeocodingListener onReverseGeocodingListener) {
        this.geocodingListener = onGeocodingListener;
        this.reverseGeocodingListener = onReverseGeocodingListener;
        if (this.fromNameList.isEmpty() && this.fromLocationList.isEmpty()) {
            this.logger.mo187w("No direct geocoding or reverse geocoding points added", new Object[0]);
            return;
        }
        IntentFilter intentFilter = new IntentFilter(BROADCAST_DIRECT_GEOCODING_ACTION);
        IntentFilter intentFilter2 = new IntentFilter(BROADCAST_REVERSE_GEOCODING_ACTION);
        Intent intent = new Intent(this.context, AndroidGeocodingService.class);
        intent.putExtra("locale", this.locale);
        if (!this.fromNameList.isEmpty()) {
            this.context.registerReceiver(this.directReceiver, intentFilter);
            intent.putExtra("direct", this.fromNameList);
        }
        if (!this.fromLocationList.isEmpty()) {
            this.context.registerReceiver(this.reverseReceiver, intentFilter2);
            intent.putExtra(REVERSE_GEOCODING_ID, this.fromLocationList);
        }
        this.context.startService(intent);
        this.fromNameList.clear();
        this.fromLocationList.clear();
    }

    @Override // io.nlopez.smartlocation.geocoding.GeocodingProvider
    public void stop() {
        try {
            this.context.unregisterReceiver(this.directReceiver);
        } catch (IllegalArgumentException unused) {
            this.logger.mo195d("Silenced 'receiver not registered' stuff (calling stop more times than necessary did this)", new Object[0]);
        }
        try {
            this.context.unregisterReceiver(this.reverseReceiver);
        } catch (IllegalArgumentException unused2) {
            this.logger.mo195d("Silenced 'receiver not registered' stuff (calling stop more times than necessary did this)", new Object[0]);
        }
    }

    /* loaded from: classes5.dex */
    public static class AndroidGeocodingService extends IntentService {
        private Geocoder geocoder;

        public AndroidGeocodingService() {
            super(AndroidGeocodingService.class.getSimpleName());
        }

        @Override // android.app.IntentService
        protected void onHandleIntent(Intent intent) {
            Locale locale = (Locale) intent.getSerializableExtra("locale");
            if (locale == null) {
                this.geocoder = new Geocoder(this);
            } else {
                this.geocoder = new Geocoder(this, locale);
            }
            if (intent.hasExtra("direct")) {
                HashMap hashMap = (HashMap) intent.getSerializableExtra("direct");
                for (String str : hashMap.keySet()) {
                    sendDirectGeocodingBroadcast(str, addressFromName(str, ((Integer) hashMap.get(str)).intValue()));
                }
            }
            if (intent.hasExtra(AndroidGeocodingProvider.REVERSE_GEOCODING_ID)) {
                HashMap hashMap2 = (HashMap) intent.getSerializableExtra(AndroidGeocodingProvider.REVERSE_GEOCODING_ID);
                for (Location location : hashMap2.keySet()) {
                    sendReverseGeocodingBroadcast(location, addressFromLocation(location, ((Integer) hashMap2.get(location)).intValue()));
                }
            }
        }

        private void sendDirectGeocodingBroadcast(String str, ArrayList<LocationAddress> arrayList) {
            Intent intent = new Intent(AndroidGeocodingProvider.BROADCAST_DIRECT_GEOCODING_ACTION);
            intent.putExtra("name", str);
            intent.putExtra(AndroidGeocodingProvider.RESULT_ID, arrayList);
            sendBroadcast(intent);
        }

        private void sendReverseGeocodingBroadcast(Location location, ArrayList<Address> arrayList) {
            Intent intent = new Intent(AndroidGeocodingProvider.BROADCAST_REVERSE_GEOCODING_ACTION);
            intent.putExtra("location", location);
            intent.putExtra(AndroidGeocodingProvider.RESULT_ID, arrayList);
            sendBroadcast(intent);
        }

        private ArrayList<Address> addressFromLocation(Location location, int r9) {
            try {
                return new ArrayList<>(this.geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), r9));
            } catch (IOException unused) {
                return new ArrayList<>();
            }
        }

        private ArrayList<LocationAddress> addressFromName(String str, int r4) {
            try {
                List<Address> fromLocationName = this.geocoder.getFromLocationName(str, r4);
                ArrayList<LocationAddress> arrayList = new ArrayList<>();
                for (Address address : fromLocationName) {
                    arrayList.add(new LocationAddress(address));
                }
                return arrayList;
            } catch (IOException unused) {
                return new ArrayList<>();
            }
        }
    }
}
