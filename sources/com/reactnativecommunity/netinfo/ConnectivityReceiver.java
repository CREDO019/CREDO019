package com.reactnativecommunity.netinfo;

import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import com.amplitude.api.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bouncycastle.i18n.ErrorBundle;

/* loaded from: classes3.dex */
public abstract class ConnectivityReceiver {
    private final ConnectivityManager mConnectivityManager;
    private Boolean mIsInternetReachableOverride;
    private final ReactApplicationContext mReactContext;
    private final TelephonyManager mTelephonyManager;
    private final WifiManager mWifiManager;
    public boolean hasListener = false;
    @Nonnull
    private ConnectionType mConnectionType = ConnectionType.UNKNOWN;
    @Nullable
    private CellularGeneration mCellularGeneration = null;
    private boolean mIsInternetReachable = false;

    public abstract void register();

    public abstract void unregister();

    private static String getSubnet(InetAddress inetAddress) throws SocketException {
        int networkPrefixLength = (-1) << (32 - NetworkInterface.getByInetAddress(inetAddress).getInterfaceAddresses().get(1).getNetworkPrefixLength());
        return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf((networkPrefixLength >> 24) & 255), Integer.valueOf((networkPrefixLength >> 16) & 255), Integer.valueOf((networkPrefixLength >> 8) & 255), Integer.valueOf(networkPrefixLength & 255));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        this.mConnectivityManager = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) reactApplicationContext.getApplicationContext().getSystemService("wifi");
        this.mTelephonyManager = (TelephonyManager) reactApplicationContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
    }

    public void getCurrentState(@Nullable String str, Promise promise) {
        promise.resolve(createConnectivityEventMap(str));
    }

    public void setIsInternetReachableOverride(boolean z) {
        this.mIsInternetReachableOverride = Boolean.valueOf(z);
        updateConnectivity(this.mConnectionType, this.mCellularGeneration, this.mIsInternetReachable);
    }

    public void clearIsInternetReachableOverride() {
        this.mIsInternetReachableOverride = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactApplicationContext getReactContext() {
        return this.mReactContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityManager getConnectivityManager() {
        return this.mConnectivityManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateConnectivity(@Nonnull ConnectionType connectionType, @Nullable CellularGeneration cellularGeneration, boolean z) {
        Boolean bool = this.mIsInternetReachableOverride;
        if (bool != null) {
            z = bool.booleanValue();
        }
        boolean z2 = connectionType != this.mConnectionType;
        boolean z3 = cellularGeneration != this.mCellularGeneration;
        boolean z4 = z != this.mIsInternetReachable;
        if (z2 || z3 || z4) {
            this.mConnectionType = connectionType;
            this.mCellularGeneration = cellularGeneration;
            this.mIsInternetReachable = z;
            if (this.hasListener) {
                sendConnectivityChangedEvent();
            }
        }
    }

    protected void sendConnectivityChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("netInfo.networkStatusDidChange", createConnectivityEventMap(null));
    }

    protected WritableMap createConnectivityEventMap(@Nullable String str) {
        WritableMap createMap = Arguments.createMap();
        if (NetInfoUtils.isAccessWifiStatePermissionGranted(getReactContext())) {
            createMap.putBoolean("isWifiEnabled", this.mWifiManager.isWifiEnabled());
        }
        createMap.putString(SessionDescription.ATTR_TYPE, str != null ? str : this.mConnectionType.label);
        boolean z = false;
        boolean z2 = (this.mConnectionType.equals(ConnectionType.NONE) || this.mConnectionType.equals(ConnectionType.UNKNOWN)) ? false : true;
        createMap.putBoolean("isConnected", z2);
        if (this.mIsInternetReachable && (str == null || str.equals(this.mConnectionType.label))) {
            z = true;
        }
        createMap.putBoolean("isInternetReachable", z);
        if (str == null) {
            str = this.mConnectionType.label;
        }
        WritableMap createDetailsMap = createDetailsMap(str);
        if (z2) {
            createDetailsMap.putBoolean("isConnectionExpensive", getConnectivityManager() != null ? getConnectivityManager().isActiveNetworkMetered() : true);
        }
        createMap.putMap(ErrorBundle.DETAIL_ENTRY, createDetailsMap);
        return createMap;
    }

    private WritableMap createDetailsMap(@Nonnull String str) {
        WifiInfo connectionInfo;
        WritableMap createMap = Arguments.createMap();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1419358249:
                if (str.equals("ethernet")) {
                    c = 0;
                    break;
                }
                break;
            case -916596374:
                if (str.equals("cellular")) {
                    c = 1;
                    break;
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (networkInterfaces.hasMoreElements()) {
                        Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement = inetAddresses.nextElement();
                            if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                createMap.putString("ipAddress", nextElement.getHostAddress());
                                createMap.putString("subnet", getSubnet(nextElement));
                                return createMap;
                            }
                        }
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                break;
            case 1:
                CellularGeneration cellularGeneration = this.mCellularGeneration;
                if (cellularGeneration != null) {
                    createMap.putString("cellularGeneration", cellularGeneration.label);
                }
                String networkOperatorName = this.mTelephonyManager.getNetworkOperatorName();
                if (networkOperatorName != null) {
                    createMap.putString(Constants.AMP_TRACKING_OPTION_CARRIER, networkOperatorName);
                    break;
                }
                break;
            case 2:
                if (NetInfoUtils.isAccessWifiStatePermissionGranted(getReactContext()) && (connectionInfo = this.mWifiManager.getConnectionInfo()) != null) {
                    try {
                        String str2 = connectionInfo.getSSID();
                        if (str2 != null && !str2.contains("<unknown ssid>")) {
                            createMap.putString("ssid", str2.replace("\"", ""));
                        }
                    } catch (Exception unused) {
                    }
                    try {
                        String str3 = connectionInfo.getBSSID();
                        if (str3 != null) {
                            createMap.putString("bssid", str3);
                        }
                    } catch (Exception unused2) {
                    }
                    try {
                        createMap.putInt("strength", WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 100));
                    } catch (Exception unused3) {
                    }
                    try {
                        if (Build.VERSION.SDK_INT >= 21) {
                            createMap.putInt("frequency", connectionInfo.getFrequency());
                        }
                    } catch (Exception unused4) {
                    }
                    try {
                        byte[] byteArray = BigInteger.valueOf(connectionInfo.getIpAddress()).toByteArray();
                        NetInfoUtils.reverseByteArray(byteArray);
                        createMap.putString("ipAddress", InetAddress.getByAddress(byteArray).getHostAddress());
                    } catch (Exception unused5) {
                    }
                    try {
                        byte[] byteArray2 = BigInteger.valueOf(connectionInfo.getIpAddress()).toByteArray();
                        NetInfoUtils.reverseByteArray(byteArray2);
                        createMap.putString("subnet", getSubnet(InetAddress.getByAddress(byteArray2)));
                    } catch (Exception unused6) {
                    }
                    try {
                        createMap.putInt("linkSpeed", connectionInfo.getLinkSpeed());
                    } catch (Exception unused7) {
                    }
                    try {
                        if (Build.VERSION.SDK_INT >= 29) {
                            createMap.putInt("rxLinkSpeed", connectionInfo.getRxLinkSpeedMbps());
                        }
                    } catch (Exception unused8) {
                    }
                    try {
                        if (Build.VERSION.SDK_INT >= 29) {
                            createMap.putInt("txLinkSpeed", connectionInfo.getTxLinkSpeedMbps());
                            break;
                        }
                    } catch (Exception unused9) {
                        break;
                    }
                }
                break;
        }
        return createMap;
    }
}
