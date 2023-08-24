package com.google.android.gms.measurement.internal;

import com.amplitude.api.AmplitudeClient;
import com.google.firebase.messaging.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzhf {
    public static final String[] zza = {"ad_activeview", "ad_click", "ad_exposure", "ad_query", "ad_reward", "adunit_exposure", "app_background", "app_clear_data", "app_exception", "app_remove", "app_store_refund", "app_store_subscription_cancel", "app_store_subscription_convert", "app_store_subscription_renew", "app_upgrade", "app_update", "ga_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", AmplitudeClient.START_SESSION_EVENT, "session_start_with_rollout", "user_engagement", "ad_impression", "screen_view", "ga_extra_parameter", "firebase_campaign"};
    public static final String[] zzb = {"ad_impression"};
    public static final String[] zzc = {"_aa", "_ac", "_xa", "_aq", "_ar", "_xu", "_ab", "_cd", "_ae", "_ui", "app_store_refund", "app_store_subscription_cancel", "app_store_subscription_convert", "app_store_subscription_renew", "_ug", "_au", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, "_err", "_f", "_v", "_iap", Constants.ScionAnalytics.EVENT_NOTIFICATION_DISMISS, Constants.ScionAnalytics.EVENT_NOTIFICATION_FOREGROUND, Constants.ScionAnalytics.EVENT_NOTIFICATION_OPEN, Constants.ScionAnalytics.EVENT_NOTIFICATION_RECEIVE, "_ou", "_s", "_ssr", "_e", "_ai", "_vs", "_ep", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN};
    public static final String[] zzd = {"purchase", "refund", "add_payment_info", "add_shipping_info", "add_to_cart", "add_to_wishlist", "begin_checkout", "remove_from_cart", "select_item", "select_promotion", "view_cart", "view_item", "view_item_list", "view_promotion", "ecommerce_purchase", "purchase_refund", "set_checkout_option", "checkout_progress", "select_content", "view_search_results"};

    public static String zza(String str) {
        return zzit.zzb(str, zzc, zza);
    }

    public static String zzb(String str) {
        return zzit.zzb(str, zza, zzc);
    }
}
