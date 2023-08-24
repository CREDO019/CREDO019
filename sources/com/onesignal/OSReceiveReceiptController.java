package com.onesignal;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSReceiveReceiptController {
    private static final String OS_NOTIFICATION_ID = "os_notification_id";
    private static OSReceiveReceiptController sInstance;
    private int minDelay = 0;
    private int maxDelay = 25;
    private final OSRemoteParamController remoteParamController = OneSignal.getRemoteParamController();

    private OSReceiveReceiptController() {
    }

    public static synchronized OSReceiveReceiptController getInstance() {
        OSReceiveReceiptController oSReceiveReceiptController;
        synchronized (OSReceiveReceiptController.class) {
            if (sInstance == null) {
                sInstance = new OSReceiveReceiptController();
            }
            oSReceiveReceiptController = sInstance;
        }
        return oSReceiveReceiptController;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void beginEnqueueingWork(Context context, String str) {
        if (!this.remoteParamController.isReceiveReceiptEnabled()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "sendReceiveReceipt disabled");
            return;
        }
        int randomDelay = OSUtils.getRandomDelay(this.minDelay, this.maxDelay);
        Data build = new Data.Builder().putString(OS_NOTIFICATION_ID, str).build();
        Constraints buildConstraints = buildConstraints();
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OSReceiveReceiptController enqueueing send receive receipt work with notificationId: " + str + " and delay: " + randomDelay + " seconds");
        WorkManager workManager = WorkManager.getInstance(context);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_receive_receipt");
        workManager.enqueueUniqueWork(sb.toString(), ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(ReceiveReceiptWorker.class).setConstraints(buildConstraints).setInitialDelay((long) randomDelay, TimeUnit.SECONDS).setInputData(build).build());
    }

    Constraints buildConstraints() {
        return new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
    }

    /* loaded from: classes3.dex */
    public static class ReceiveReceiptWorker extends Worker {
        public ReceiveReceiptWorker(Context context, WorkerParameters workerParameters) {
            super(context, workerParameters);
        }

        @Override // androidx.work.Worker
        public ListenableWorker.Result doWork() {
            sendReceiveReceipt(getInputData().getString(OSReceiveReceiptController.OS_NOTIFICATION_ID));
            return ListenableWorker.Result.success();
        }

        void sendReceiveReceipt(final String str) {
            String savedAppId = (OneSignal.appId == null || OneSignal.appId.isEmpty()) ? OneSignal.getSavedAppId() : OneSignal.appId;
            String userId = OneSignal.getUserId();
            Integer num = null;
            OSReceiveReceiptRepository oSReceiveReceiptRepository = new OSReceiveReceiptRepository();
            try {
                num = Integer.valueOf(new OSUtils().getDeviceType());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            Integer num2 = num;
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "ReceiveReceiptWorker: Device Type is: " + num2);
            oSReceiveReceiptRepository.sendReceiveReceipt(savedAppId, userId, num2, str, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OSReceiveReceiptController.ReceiveReceiptWorker.1
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str2) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
                    OneSignal.Log(log_level2, "Receive receipt sent for notificationID: " + str);
                }

                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onFailure(int r3, String str2, Throwable th) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level2, "Receive receipt failed with statusCode: " + r3 + " response: " + str2);
                }
            });
        }
    }
}
