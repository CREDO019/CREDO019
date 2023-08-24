package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.android.billingclient.api.BillingClient;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class TrackGooglePurchase {
    private static Class<?> IInAppBillingServiceClass = null;
    private static int iapEnabled = -99;
    private Context appContext;
    private Method getPurchasesMethod;
    private Method getSkuDetailsMethod;
    private Object mIInAppBillingService;
    private ServiceConnection mServiceConn;
    private boolean newAsExisting;
    private boolean isWaitingForPurchasesRequest = false;
    private ArrayList<String> purchaseTokens = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackGooglePurchase(Context context) {
        this.newAsExisting = true;
        this.appContext = context;
        try {
            JSONArray jSONArray = new JSONArray(OneSignalPrefs.getString(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "purchaseTokens", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
            for (int r7 = 0; r7 < jSONArray.length(); r7++) {
                this.purchaseTokens.add(jSONArray.get(r7).toString());
            }
            boolean z = jSONArray.length() == 0;
            this.newAsExisting = z;
            if (z) {
                this.newAsExisting = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        trackIAP();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean CanTrack(Context context) {
        if (iapEnabled == -99) {
            iapEnabled = context.checkCallingOrSelfPermission("com.android.vending.BILLING");
        }
        try {
            if (iapEnabled == 0) {
                IInAppBillingServiceClass = Class.forName("com.android.vending.billing.IInAppBillingService");
            }
            return iapEnabled == 0;
        } catch (Throwable unused) {
            iapEnabled = 0;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackIAP() {
        if (this.mServiceConn == null) {
            this.mServiceConn = new ServiceConnection() { // from class: com.onesignal.TrackGooglePurchase.1
                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    int unused = TrackGooglePurchase.iapEnabled = -99;
                    TrackGooglePurchase.this.mIInAppBillingService = null;
                }

                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        Method asInterfaceMethod = TrackGooglePurchase.getAsInterfaceMethod(Class.forName("com.android.vending.billing.IInAppBillingService$Stub"));
                        asInterfaceMethod.setAccessible(true);
                        TrackGooglePurchase.this.mIInAppBillingService = asInterfaceMethod.invoke(null, iBinder);
                        TrackGooglePurchase.this.QueryBoughtItems();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            };
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            this.appContext.bindService(intent, this.mServiceConn, 1);
        } else if (this.mIInAppBillingService != null) {
            QueryBoughtItems();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void QueryBoughtItems() {
        if (this.isWaitingForPurchasesRequest) {
            return;
        }
        new Thread(new Runnable() { // from class: com.onesignal.TrackGooglePurchase.2
            @Override // java.lang.Runnable
            public void run() {
                TrackGooglePurchase.this.isWaitingForPurchasesRequest = true;
                try {
                    if (TrackGooglePurchase.this.getPurchasesMethod == null) {
                        TrackGooglePurchase.this.getPurchasesMethod = TrackGooglePurchase.getGetPurchasesMethod(TrackGooglePurchase.IInAppBillingServiceClass);
                        TrackGooglePurchase.this.getPurchasesMethod.setAccessible(true);
                    }
                    Bundle bundle = (Bundle) TrackGooglePurchase.this.getPurchasesMethod.invoke(TrackGooglePurchase.this.mIInAppBillingService, 3, TrackGooglePurchase.this.appContext.getPackageName(), BillingClient.SkuType.INAPP, null);
                    if (bundle.getInt("RESPONSE_CODE") == 0) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                        for (int r5 = 0; r5 < stringArrayList2.size(); r5++) {
                            String str = stringArrayList.get(r5);
                            String string = new JSONObject(stringArrayList2.get(r5)).getString("purchaseToken");
                            if (!TrackGooglePurchase.this.purchaseTokens.contains(string) && !arrayList2.contains(string)) {
                                arrayList2.add(string);
                                arrayList.add(str);
                            }
                        }
                        if (arrayList.size() > 0) {
                            TrackGooglePurchase.this.sendPurchases(arrayList, arrayList2);
                        } else if (stringArrayList2.size() == 0) {
                            TrackGooglePurchase.this.newAsExisting = false;
                            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", false);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPurchases(ArrayList<String> arrayList, final ArrayList<String> arrayList2) {
        try {
            if (this.getSkuDetailsMethod == null) {
                Method getSkuDetailsMethod = getGetSkuDetailsMethod(IInAppBillingServiceClass);
                this.getSkuDetailsMethod = getSkuDetailsMethod;
                getSkuDetailsMethod.setAccessible(true);
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
            Bundle bundle2 = (Bundle) this.getSkuDetailsMethod.invoke(this.mIInAppBillingService, 3, this.appContext.getPackageName(), BillingClient.SkuType.INAPP, bundle);
            if (bundle2.getInt("RESPONSE_CODE") == 0) {
                ArrayList<String> stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                HashMap hashMap = new HashMap();
                Iterator<String> it = stringArrayList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject = new JSONObject(it.next());
                    String string = jSONObject.getString("productId");
                    BigDecimal divide = new BigDecimal(jSONObject.getString("price_amount_micros")).divide(new BigDecimal(1000000));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("sku", string);
                    jSONObject2.put("iso", jSONObject.getString("price_currency_code"));
                    jSONObject2.put("amount", divide.toString());
                    hashMap.put(string, jSONObject2);
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    String next = it2.next();
                    if (hashMap.containsKey(next)) {
                        jSONArray.put(hashMap.get(next));
                    }
                }
                if (jSONArray.length() > 0) {
                    OneSignal.sendPurchases(jSONArray, this.newAsExisting, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.TrackGooglePurchase.3
                        public void onFailure(int r1, JSONObject jSONObject3, Throwable th) {
                            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "HTTP sendPurchases failed to send.", th);
                            TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                        }

                        @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                        public void onSuccess(String str) {
                            TrackGooglePurchase.this.purchaseTokens.addAll(arrayList2);
                            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "purchaseTokens", TrackGooglePurchase.this.purchaseTokens.toString());
                            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_PLAYER_PURCHASES, "ExistingPurchases", true);
                            TrackGooglePurchase.this.newAsExisting = false;
                            TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                        }
                    });
                }
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed to track IAP purchases", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getAsInterfaceMethod(Class cls) {
        Method[] methods;
        for (Method method : cls.getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == IBinder.class) {
                return method;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getGetPurchasesMethod(Class cls) {
        Method[] methods;
        for (Method method : cls.getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == String.class) {
                return method;
            }
        }
        return null;
    }

    private static Method getGetSkuDetailsMethod(Class cls) {
        Method[] methods;
        for (Method method : cls.getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == Bundle.class && returnType == Bundle.class) {
                return method;
            }
        }
        return null;
    }
}
