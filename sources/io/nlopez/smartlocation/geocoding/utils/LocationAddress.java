package io.nlopez.smartlocation.geocoding.utils;

import android.location.Address;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class LocationAddress implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: io.nlopez.smartlocation.geocoding.utils.LocationAddress.1
        @Override // android.os.Parcelable.Creator
        public LocationAddress createFromParcel(Parcel parcel) {
            return new LocationAddress(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LocationAddress[] newArray(int r1) {
            return new LocationAddress[r1];
        }
    };
    private Address address;
    private Location location;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LocationAddress(Address address) {
        this.address = address;
        Location location = new Location(LocationAddress.class.getCanonicalName());
        this.location = location;
        location.setLatitude(address.getLatitude());
        this.location.setLongitude(address.getLongitude());
    }

    public LocationAddress(Parcel parcel) {
        this.location = (Location) parcel.readParcelable(Location.class.getClassLoader());
        this.address = (Address) parcel.readParcelable(Address.class.getClassLoader());
    }

    public Location getLocation() {
        return this.location;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 <= this.address.getMaxAddressLineIndex(); r1++) {
            sb.append(this.address.getAddressLine(r1));
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r3) {
        parcel.writeParcelable(this.location, r3);
        parcel.writeParcelable(this.address, r3);
    }
}
