package cl.json;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import cl.json.social.EmailShare;
import cl.json.social.FacebookPagesManagerShare;
import cl.json.social.FacebookShare;
import cl.json.social.FacebookStoriesShare;
import cl.json.social.GenericShare;
import cl.json.social.GooglePlusShare;
import cl.json.social.InstagramShare;
import cl.json.social.InstagramStoriesShare;
import cl.json.social.LinkedinShare;
import cl.json.social.MessengerShare;
import cl.json.social.PinterestShare;
import cl.json.social.SMSShare;
import cl.json.social.ShareIntent;
import cl.json.social.SnapChatShare;
import cl.json.social.TargetChosenReceiver;
import cl.json.social.TelegramShare;
import cl.json.social.TwitterShare;
import cl.json.social.ViberShare;
import cl.json.social.WhatsAppBusinessShare;
import cl.json.social.WhatsAppShare;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class RNShareModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final int SHARE_REQUEST_CODE = 16845;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNShare";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public void onActivityResult(int r2, int r3, Intent intent) {
        if (r2 == 16845) {
            if (r3 == 0) {
                TargetChosenReceiver.sendCallback(true, false, "CANCELED");
            } else if (r3 == -1) {
                TargetChosenReceiver.sendCallback(true, true);
            }
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int r2, int r3, Intent intent) {
        onActivityResult(r2, r3, intent);
    }

    /* loaded from: classes.dex */
    private enum SHARES {
        facebook,
        facebookstories,
        generic,
        pagesmanager,
        twitter,
        whatsapp,
        whatsappbusiness,
        instagram,
        instagramstories,
        googleplus,
        email,
        pinterest,
        messenger,
        snapchat,
        sms,
        linkedin,
        telegram,
        viber;

        public static ShareIntent getShareClass(String str, ReactApplicationContext reactApplicationContext) {
            switch (C08971.$SwitchMap$cl$json$RNShareModule$SHARES[valueOf(str).ordinal()]) {
                case 1:
                    return new GenericShare(reactApplicationContext);
                case 2:
                    return new FacebookShare(reactApplicationContext);
                case 3:
                    return new FacebookStoriesShare(reactApplicationContext);
                case 4:
                    return new FacebookPagesManagerShare(reactApplicationContext);
                case 5:
                    return new TwitterShare(reactApplicationContext);
                case 6:
                    return new WhatsAppShare(reactApplicationContext);
                case 7:
                    return new WhatsAppBusinessShare(reactApplicationContext);
                case 8:
                    return new InstagramShare(reactApplicationContext);
                case 9:
                    return new InstagramStoriesShare(reactApplicationContext);
                case 10:
                    return new GooglePlusShare(reactApplicationContext);
                case 11:
                    return new EmailShare(reactApplicationContext);
                case 12:
                    return new PinterestShare(reactApplicationContext);
                case 13:
                    return new SMSShare(reactApplicationContext);
                case 14:
                    return new SnapChatShare(reactApplicationContext);
                case 15:
                    return new MessengerShare(reactApplicationContext);
                case 16:
                    return new LinkedinShare(reactApplicationContext);
                case 17:
                    return new TelegramShare(reactApplicationContext);
                case 18:
                    return new ViberShare(reactApplicationContext);
                default:
                    return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: cl.json.RNShareModule$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C08971 {
        static final /* synthetic */ int[] $SwitchMap$cl$json$RNShareModule$SHARES;

        static {
            int[] r0 = new int[SHARES.values().length];
            $SwitchMap$cl$json$RNShareModule$SHARES = r0;
            try {
                r0[SHARES.generic.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.facebook.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.facebookstories.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.pagesmanager.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.twitter.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.whatsapp.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.whatsappbusiness.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.instagram.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.instagramstories.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.googleplus.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.email.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.pinterest.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.sms.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.snapchat.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.messenger.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.linkedin.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.telegram.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$cl$json$RNShareModule$SHARES[SHARES.viber.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public RNShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        SHARES[] values;
        HashMap hashMap = new HashMap();
        for (SHARES shares : SHARES.values()) {
            hashMap.put(shares.toString().toUpperCase(Locale.ROOT), shares.toString());
        }
        return hashMap;
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Callback callback, Callback callback2) {
        TargetChosenReceiver.registerCallbacks(callback2, callback);
        try {
            new GenericShare(this.reactContext).open(readableMap);
        } catch (ActivityNotFoundException e) {
            PrintStream printStream = System.out;
            printStream.println("ERROR " + e.getMessage());
            e.printStackTrace(System.out);
            TargetChosenReceiver.sendCallback(false, "not_available");
        } catch (Exception e2) {
            PrintStream printStream2 = System.out;
            printStream2.println("ERROR " + e2.getMessage());
            e2.printStackTrace(System.out);
            TargetChosenReceiver.sendCallback(false, e2.getMessage());
        }
    }

    @ReactMethod
    public void shareSingle(ReadableMap readableMap, Callback callback, Callback callback2) {
        System.out.println("SHARE SINGLE METHOD");
        TargetChosenReceiver.registerCallbacks(callback2, callback);
        if (ShareIntent.hasValidKey(NotificationCompat.CATEGORY_SOCIAL, readableMap)) {
            try {
                ShareIntent shareClass = SHARES.getShareClass(readableMap.getString(NotificationCompat.CATEGORY_SOCIAL), this.reactContext);
                if (shareClass != null && (shareClass instanceof ShareIntent)) {
                    shareClass.open(readableMap);
                    return;
                }
                throw new ActivityNotFoundException("Invalid share activity");
            } catch (ActivityNotFoundException e) {
                PrintStream printStream = System.out;
                printStream.println("ERROR " + e.getMessage());
                e.printStackTrace(System.out);
                TargetChosenReceiver.sendCallback(false, e.getMessage());
                return;
            } catch (Exception e2) {
                PrintStream printStream2 = System.out;
                printStream2.println("ERROR " + e2.getMessage());
                e2.printStackTrace(System.out);
                TargetChosenReceiver.sendCallback(false, e2.getMessage());
                return;
            }
        }
        TargetChosenReceiver.sendCallback(false, "key 'social' missing in options");
    }

    @ReactMethod
    public void isPackageInstalled(String str, Callback callback, Callback callback2) {
        try {
            callback2.invoke(Boolean.valueOf(ShareIntent.isPackageInstalled(str, this.reactContext)));
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("Error: " + e.getMessage());
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void isBase64File(String str, Callback callback, Callback callback2) {
        try {
            String scheme = Uri.parse(str).getScheme();
            if (scheme == null || !scheme.equals("data")) {
                callback2.invoke(false);
            } else {
                callback2.invoke(true);
            }
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("ERROR " + e.getMessage());
            e.printStackTrace(System.out);
            callback.invoke(e.getMessage());
        }
    }
}
