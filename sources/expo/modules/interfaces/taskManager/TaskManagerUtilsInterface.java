package expo.modules.interfaces.taskManager;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.content.Context;
import android.os.PersistableBundle;
import java.util.List;

/* loaded from: classes4.dex */
public interface TaskManagerUtilsInterface {
    void cancelScheduledJob(Context context, int r2);

    void cancelTaskIntent(Context context, String str, String str2);

    PendingIntent createTaskIntent(Context context, TaskInterface taskInterface);

    List<PersistableBundle> extractDataFromJobParams(JobParameters jobParameters);

    void scheduleJob(Context context, TaskInterface taskInterface, List<PersistableBundle> list);
}
