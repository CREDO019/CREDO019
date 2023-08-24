package com.google.android.exoplayer2.p012ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p001v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.media.app.NotificationCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager */
/* loaded from: classes2.dex */
public class PlayerNotificationManager {
    private static final String ACTION_DISMISS = "com.google.android.exoplayer.dismiss";
    public static final String ACTION_FAST_FORWARD = "com.google.android.exoplayer.ffwd";
    public static final String ACTION_NEXT = "com.google.android.exoplayer.next";
    public static final String ACTION_PAUSE = "com.google.android.exoplayer.pause";
    public static final String ACTION_PLAY = "com.google.android.exoplayer.play";
    public static final String ACTION_PREVIOUS = "com.google.android.exoplayer.prev";
    public static final String ACTION_REWIND = "com.google.android.exoplayer.rewind";
    public static final String ACTION_STOP = "com.google.android.exoplayer.stop";
    public static final String EXTRA_INSTANCE_ID = "INSTANCE_ID";
    private static final int MSG_START_OR_UPDATE_NOTIFICATION = 0;
    private static final int MSG_UPDATE_NOTIFICATION_BITMAP = 1;
    private static int instanceIdCounter;
    private int badgeIconType;
    private NotificationCompat.Builder builder;
    private List<NotificationCompat.Action> builderActions;
    private final String channelId;
    private int color;
    private boolean colorized;
    private final Context context;
    private int currentNotificationTag;
    private final CustomActionReceiver customActionReceiver;
    private final Map<String, NotificationCompat.Action> customActions;
    private int defaults;
    private final PendingIntent dismissPendingIntent;
    private String groupKey;
    private final int instanceId;
    private final IntentFilter intentFilter;
    private boolean isNotificationStarted;
    private final Handler mainHandler;
    private final MediaDescriptionAdapter mediaDescriptionAdapter;
    private MediaSessionCompat.Token mediaSessionToken;
    private final NotificationBroadcastReceiver notificationBroadcastReceiver;
    private final int notificationId;
    private final NotificationListener notificationListener;
    private final NotificationManagerCompat notificationManager;
    private final Map<String, NotificationCompat.Action> playbackActions;
    private Player player;
    private final Player.Listener playerListener;
    private int priority;
    private int smallIconResourceId;
    private boolean useChronometer;
    private boolean useFastForwardAction;
    private boolean useFastForwardActionInCompactView;
    private boolean useNextAction;
    private boolean useNextActionInCompactView;
    private boolean usePlayPauseActions;
    private boolean usePreviousAction;
    private boolean usePreviousActionInCompactView;
    private boolean useRewindAction;
    private boolean useRewindActionInCompactView;
    private boolean useStopAction;
    private int visibility;

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$CustomActionReceiver */
    /* loaded from: classes2.dex */
    public interface CustomActionReceiver {
        Map<String, NotificationCompat.Action> createCustomActions(Context context, int r2);

        List<String> getCustomActions(Player player);

        void onCustomAction(Player player, String str, Intent intent);
    }

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$MediaDescriptionAdapter */
    /* loaded from: classes2.dex */
    public interface MediaDescriptionAdapter {

        /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$MediaDescriptionAdapter$-CC  reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static CharSequence $default$getCurrentSubText(MediaDescriptionAdapter _this, Player player) {
                return null;
            }
        }

        PendingIntent createCurrentContentIntent(Player player);

        CharSequence getCurrentContentText(Player player);

        CharSequence getCurrentContentTitle(Player player);

        Bitmap getCurrentLargeIcon(Player player, BitmapCallback bitmapCallback);

        CharSequence getCurrentSubText(Player player);
    }

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$NotificationListener */
    /* loaded from: classes2.dex */
    public interface NotificationListener {

        /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$NotificationListener$-CC  reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static void $default$onNotificationCancelled(NotificationListener _this, int r1, boolean z) {
            }

            public static void $default$onNotificationPosted(NotificationListener _this, int r1, Notification notification, boolean z) {
            }
        }

        void onNotificationCancelled(int r1, boolean z);

        void onNotificationPosted(int r1, Notification notification, boolean z);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$Priority */
    /* loaded from: classes2.dex */
    public @interface Priority {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$Visibility */
    /* loaded from: classes2.dex */
    public @interface Visibility {
    }

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$Builder */
    /* loaded from: classes2.dex */
    public static class Builder {
        protected int channelDescriptionResourceId;
        protected final String channelId;
        protected int channelImportance;
        protected int channelNameResourceId;
        protected final Context context;
        protected CustomActionReceiver customActionReceiver;
        protected int fastForwardActionIconResourceId;
        protected String groupKey;
        protected MediaDescriptionAdapter mediaDescriptionAdapter;
        protected int nextActionIconResourceId;
        protected final int notificationId;
        protected NotificationListener notificationListener;
        protected int pauseActionIconResourceId;
        protected int playActionIconResourceId;
        protected int previousActionIconResourceId;
        protected int rewindActionIconResourceId;
        protected int smallIconResourceId;
        protected int stopActionIconResourceId;

        @Deprecated
        public Builder(Context context, int r2, String str, MediaDescriptionAdapter mediaDescriptionAdapter) {
            this(context, r2, str);
            this.mediaDescriptionAdapter = mediaDescriptionAdapter;
        }

        public Builder(Context context, int r3, String str) {
            Assertions.checkArgument(r3 > 0);
            this.context = context;
            this.notificationId = r3;
            this.channelId = str;
            this.channelImportance = 2;
            this.mediaDescriptionAdapter = new DefaultMediaDescriptionAdapter(null);
            this.smallIconResourceId = C2058R.C2060drawable.exo_notification_small_icon;
            this.playActionIconResourceId = C2058R.C2060drawable.exo_notification_play;
            this.pauseActionIconResourceId = C2058R.C2060drawable.exo_notification_pause;
            this.stopActionIconResourceId = C2058R.C2060drawable.exo_notification_stop;
            this.rewindActionIconResourceId = C2058R.C2060drawable.exo_notification_rewind;
            this.fastForwardActionIconResourceId = C2058R.C2060drawable.exo_notification_fastforward;
            this.previousActionIconResourceId = C2058R.C2060drawable.exo_notification_previous;
            this.nextActionIconResourceId = C2058R.C2060drawable.exo_notification_next;
        }

        public Builder setChannelNameResourceId(int r1) {
            this.channelNameResourceId = r1;
            return this;
        }

        public Builder setChannelDescriptionResourceId(int r1) {
            this.channelDescriptionResourceId = r1;
            return this;
        }

        public Builder setChannelImportance(int r1) {
            this.channelImportance = r1;
            return this;
        }

        public Builder setNotificationListener(NotificationListener notificationListener) {
            this.notificationListener = notificationListener;
            return this;
        }

        public Builder setCustomActionReceiver(CustomActionReceiver customActionReceiver) {
            this.customActionReceiver = customActionReceiver;
            return this;
        }

        public Builder setSmallIconResourceId(int r1) {
            this.smallIconResourceId = r1;
            return this;
        }

        public Builder setPlayActionIconResourceId(int r1) {
            this.playActionIconResourceId = r1;
            return this;
        }

        public Builder setPauseActionIconResourceId(int r1) {
            this.pauseActionIconResourceId = r1;
            return this;
        }

        public Builder setStopActionIconResourceId(int r1) {
            this.stopActionIconResourceId = r1;
            return this;
        }

        public Builder setRewindActionIconResourceId(int r1) {
            this.rewindActionIconResourceId = r1;
            return this;
        }

        public Builder setFastForwardActionIconResourceId(int r1) {
            this.fastForwardActionIconResourceId = r1;
            return this;
        }

        public Builder setPreviousActionIconResourceId(int r1) {
            this.previousActionIconResourceId = r1;
            return this;
        }

        public Builder setNextActionIconResourceId(int r1) {
            this.nextActionIconResourceId = r1;
            return this;
        }

        public Builder setGroup(String str) {
            this.groupKey = str;
            return this;
        }

        public Builder setMediaDescriptionAdapter(MediaDescriptionAdapter mediaDescriptionAdapter) {
            this.mediaDescriptionAdapter = mediaDescriptionAdapter;
            return this;
        }

        public PlayerNotificationManager build() {
            int r1 = this.channelNameResourceId;
            if (r1 != 0) {
                NotificationUtil.createNotificationChannel(this.context, this.channelId, r1, this.channelDescriptionResourceId, this.channelImportance);
            }
            return new PlayerNotificationManager(this.context, this.channelId, this.notificationId, this.mediaDescriptionAdapter, this.notificationListener, this.customActionReceiver, this.smallIconResourceId, this.playActionIconResourceId, this.pauseActionIconResourceId, this.stopActionIconResourceId, this.rewindActionIconResourceId, this.fastForwardActionIconResourceId, this.previousActionIconResourceId, this.nextActionIconResourceId, this.groupKey);
        }
    }

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$BitmapCallback */
    /* loaded from: classes2.dex */
    public final class BitmapCallback {
        private final int notificationTag;

        private BitmapCallback(int r2) {
            this.notificationTag = r2;
        }

        public void onBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                PlayerNotificationManager.this.postUpdateNotificationBitmap(bitmap, this.notificationTag);
            }
        }
    }

    protected PlayerNotificationManager(Context context, String str, int r15, MediaDescriptionAdapter mediaDescriptionAdapter, NotificationListener notificationListener, CustomActionReceiver customActionReceiver, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, String str2) {
        Map<String, NotificationCompat.Action> emptyMap;
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.channelId = str;
        this.notificationId = r15;
        this.mediaDescriptionAdapter = mediaDescriptionAdapter;
        this.notificationListener = notificationListener;
        this.customActionReceiver = customActionReceiver;
        this.smallIconResourceId = r19;
        this.groupKey = str2;
        int r3 = instanceIdCounter;
        instanceIdCounter = r3 + 1;
        this.instanceId = r3;
        this.mainHandler = Util.createHandler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.exoplayer2.ui.PlayerNotificationManager$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleMessage;
                handleMessage = PlayerNotificationManager.this.handleMessage(message);
                return handleMessage;
            }
        });
        this.notificationManager = NotificationManagerCompat.from(applicationContext);
        this.playerListener = new PlayerListener();
        this.notificationBroadcastReceiver = new NotificationBroadcastReceiver();
        this.intentFilter = new IntentFilter();
        this.usePreviousAction = true;
        this.useNextAction = true;
        this.usePlayPauseActions = true;
        this.useRewindAction = true;
        this.useFastForwardAction = true;
        this.colorized = true;
        this.useChronometer = true;
        this.color = 0;
        this.defaults = 0;
        this.priority = -1;
        this.badgeIconType = 1;
        this.visibility = 1;
        Map<String, NotificationCompat.Action> createPlaybackActions = createPlaybackActions(applicationContext, r3, r20, r21, r22, r23, r24, r25, r26);
        this.playbackActions = createPlaybackActions;
        for (String str3 : createPlaybackActions.keySet()) {
            this.intentFilter.addAction(str3);
        }
        if (customActionReceiver != null) {
            emptyMap = customActionReceiver.createCustomActions(applicationContext, this.instanceId);
        } else {
            emptyMap = Collections.emptyMap();
        }
        this.customActions = emptyMap;
        for (String str4 : emptyMap.keySet()) {
            this.intentFilter.addAction(str4);
        }
        this.dismissPendingIntent = createBroadcastIntent(ACTION_DISMISS, applicationContext, this.instanceId);
        this.intentFilter.addAction(ACTION_DISMISS);
    }

    public final void setPlayer(Player player) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.playerListener);
            if (player == null) {
                stopNotification(false);
            }
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.playerListener);
            postStartOrUpdateNotification();
        }
    }

    public final void setUseNextAction(boolean z) {
        if (this.useNextAction != z) {
            this.useNextAction = z;
            invalidate();
        }
    }

    public final void setUsePreviousAction(boolean z) {
        if (this.usePreviousAction != z) {
            this.usePreviousAction = z;
            invalidate();
        }
    }

    public final void setUseNextActionInCompactView(boolean z) {
        if (this.useNextActionInCompactView != z) {
            this.useNextActionInCompactView = z;
            if (z) {
                this.useFastForwardActionInCompactView = false;
            }
            invalidate();
        }
    }

    public final void setUsePreviousActionInCompactView(boolean z) {
        if (this.usePreviousActionInCompactView != z) {
            this.usePreviousActionInCompactView = z;
            if (z) {
                this.useRewindActionInCompactView = false;
            }
            invalidate();
        }
    }

    public final void setUseFastForwardAction(boolean z) {
        if (this.useFastForwardAction != z) {
            this.useFastForwardAction = z;
            invalidate();
        }
    }

    public final void setUseRewindAction(boolean z) {
        if (this.useRewindAction != z) {
            this.useRewindAction = z;
            invalidate();
        }
    }

    public final void setUseFastForwardActionInCompactView(boolean z) {
        if (this.useFastForwardActionInCompactView != z) {
            this.useFastForwardActionInCompactView = z;
            if (z) {
                this.useNextActionInCompactView = false;
            }
            invalidate();
        }
    }

    public final void setUseRewindActionInCompactView(boolean z) {
        if (this.useRewindActionInCompactView != z) {
            this.useRewindActionInCompactView = z;
            if (z) {
                this.usePreviousActionInCompactView = false;
            }
            invalidate();
        }
    }

    public final void setUsePlayPauseActions(boolean z) {
        if (this.usePlayPauseActions != z) {
            this.usePlayPauseActions = z;
            invalidate();
        }
    }

    public final void setUseStopAction(boolean z) {
        if (this.useStopAction == z) {
            return;
        }
        this.useStopAction = z;
        invalidate();
    }

    public final void setMediaSessionToken(MediaSessionCompat.Token token) {
        if (Util.areEqual(this.mediaSessionToken, token)) {
            return;
        }
        this.mediaSessionToken = token;
        invalidate();
    }

    public final void setBadgeIconType(int r2) {
        if (this.badgeIconType == r2) {
            return;
        }
        if (r2 == 0 || r2 == 1 || r2 == 2) {
            this.badgeIconType = r2;
            invalidate();
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void setColorized(boolean z) {
        if (this.colorized != z) {
            this.colorized = z;
            invalidate();
        }
    }

    public final void setDefaults(int r2) {
        if (this.defaults != r2) {
            this.defaults = r2;
            invalidate();
        }
    }

    public final void setColor(int r2) {
        if (this.color != r2) {
            this.color = r2;
            invalidate();
        }
    }

    public final void setPriority(int r2) {
        if (this.priority == r2) {
            return;
        }
        if (r2 == -2 || r2 == -1 || r2 == 0 || r2 == 1 || r2 == 2) {
            this.priority = r2;
            invalidate();
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void setSmallIcon(int r2) {
        if (this.smallIconResourceId != r2) {
            this.smallIconResourceId = r2;
            invalidate();
        }
    }

    public final void setUseChronometer(boolean z) {
        if (this.useChronometer != z) {
            this.useChronometer = z;
            invalidate();
        }
    }

    public final void setVisibility(int r2) {
        if (this.visibility == r2) {
            return;
        }
        if (r2 == -1 || r2 == 0 || r2 == 1) {
            this.visibility = r2;
            invalidate();
            return;
        }
        throw new IllegalStateException();
    }

    public final void invalidate() {
        if (this.isNotificationStarted) {
            postStartOrUpdateNotification();
        }
    }

    private void startOrUpdateNotification(Player player, Bitmap bitmap) {
        boolean ongoing = getOngoing(player);
        NotificationCompat.Builder createNotification = createNotification(player, this.builder, ongoing, bitmap);
        this.builder = createNotification;
        boolean z = false;
        if (createNotification == null) {
            stopNotification(false);
            return;
        }
        Notification build = createNotification.build();
        this.notificationManager.notify(this.notificationId, build);
        if (!this.isNotificationStarted) {
            this.context.registerReceiver(this.notificationBroadcastReceiver, this.intentFilter);
        }
        NotificationListener notificationListener = this.notificationListener;
        if (notificationListener != null) {
            notificationListener.onNotificationPosted(this.notificationId, build, (ongoing || !this.isNotificationStarted) ? true : true);
        }
        this.isNotificationStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopNotification(boolean z) {
        if (this.isNotificationStarted) {
            this.isNotificationStarted = false;
            this.mainHandler.removeMessages(0);
            this.notificationManager.cancel(this.notificationId);
            this.context.unregisterReceiver(this.notificationBroadcastReceiver);
            NotificationListener notificationListener = this.notificationListener;
            if (notificationListener != null) {
                notificationListener.onNotificationCancelled(this.notificationId, z);
            }
        }
    }

    protected NotificationCompat.Builder createNotification(Player player, NotificationCompat.Builder builder, boolean z, Bitmap bitmap) {
        NotificationCompat.Action action;
        if (player.getPlaybackState() == 1 && player.getCurrentTimeline().isEmpty()) {
            this.builderActions = null;
            return null;
        }
        List<String> actions = getActions(player);
        ArrayList arrayList = new ArrayList(actions.size());
        for (int r5 = 0; r5 < actions.size(); r5++) {
            String str = actions.get(r5);
            if (this.playbackActions.containsKey(str)) {
                action = this.playbackActions.get(str);
            } else {
                action = this.customActions.get(str);
            }
            if (action != null) {
                arrayList.add(action);
            }
        }
        if (builder == null || !arrayList.equals(this.builderActions)) {
            builder = new NotificationCompat.Builder(this.context, this.channelId);
            this.builderActions = arrayList;
            for (int r52 = 0; r52 < arrayList.size(); r52++) {
                builder.addAction((NotificationCompat.Action) arrayList.get(r52));
            }
        }
        NotificationCompat.MediaStyle mediaStyle = new NotificationCompat.MediaStyle();
        MediaSessionCompat.Token token = this.mediaSessionToken;
        if (token != null) {
            mediaStyle.setMediaSession(token);
        }
        mediaStyle.setShowActionsInCompactView(getActionIndicesForCompactView(actions, player));
        mediaStyle.setShowCancelButton(!z);
        mediaStyle.setCancelButtonIntent(this.dismissPendingIntent);
        builder.setStyle(mediaStyle);
        builder.setDeleteIntent(this.dismissPendingIntent);
        builder.setBadgeIconType(this.badgeIconType).setOngoing(z).setColor(this.color).setColorized(this.colorized).setSmallIcon(this.smallIconResourceId).setVisibility(this.visibility).setPriority(this.priority).setDefaults(this.defaults);
        if (Util.SDK_INT >= 21 && this.useChronometer && player.isPlaying() && !player.isPlayingAd() && !player.isCurrentMediaItemDynamic() && player.getPlaybackParameters().speed == 1.0f) {
            builder.setWhen(System.currentTimeMillis() - player.getContentPosition()).setShowWhen(true).setUsesChronometer(true);
        } else {
            builder.setShowWhen(false).setUsesChronometer(false);
        }
        builder.setContentTitle(this.mediaDescriptionAdapter.getCurrentContentTitle(player));
        builder.setContentText(this.mediaDescriptionAdapter.getCurrentContentText(player));
        builder.setSubText(this.mediaDescriptionAdapter.getCurrentSubText(player));
        if (bitmap == null) {
            MediaDescriptionAdapter mediaDescriptionAdapter = this.mediaDescriptionAdapter;
            int r0 = this.currentNotificationTag + 1;
            this.currentNotificationTag = r0;
            bitmap = mediaDescriptionAdapter.getCurrentLargeIcon(player, new BitmapCallback(r0));
        }
        setLargeIcon(builder, bitmap);
        builder.setContentIntent(this.mediaDescriptionAdapter.createCurrentContentIntent(player));
        String str2 = this.groupKey;
        if (str2 != null) {
            builder.setGroup(str2);
        }
        builder.setOnlyAlertOnce(true);
        return builder;
    }

    protected List<String> getActions(Player player) {
        boolean isCommandAvailable = player.isCommandAvailable(7);
        boolean isCommandAvailable2 = player.isCommandAvailable(11);
        boolean isCommandAvailable3 = player.isCommandAvailable(12);
        boolean isCommandAvailable4 = player.isCommandAvailable(9);
        ArrayList arrayList = new ArrayList();
        if (this.usePreviousAction && isCommandAvailable) {
            arrayList.add(ACTION_PREVIOUS);
        }
        if (this.useRewindAction && isCommandAvailable2) {
            arrayList.add(ACTION_REWIND);
        }
        if (this.usePlayPauseActions) {
            if (shouldShowPauseButton(player)) {
                arrayList.add(ACTION_PAUSE);
            } else {
                arrayList.add(ACTION_PLAY);
            }
        }
        if (this.useFastForwardAction && isCommandAvailable3) {
            arrayList.add(ACTION_FAST_FORWARD);
        }
        if (this.useNextAction && isCommandAvailable4) {
            arrayList.add(ACTION_NEXT);
        }
        CustomActionReceiver customActionReceiver = this.customActionReceiver;
        if (customActionReceiver != null) {
            arrayList.addAll(customActionReceiver.getCustomActions(player));
        }
        if (this.useStopAction) {
            arrayList.add(ACTION_STOP);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int[] getActionIndicesForCompactView(java.util.List<java.lang.String> r7, com.google.android.exoplayer2.Player r8) {
        /*
            r6 = this;
            java.lang.String r0 = "com.google.android.exoplayer.pause"
            int r0 = r7.indexOf(r0)
            java.lang.String r1 = "com.google.android.exoplayer.play"
            int r1 = r7.indexOf(r1)
            boolean r2 = r6.usePreviousActionInCompactView
            r3 = -1
            if (r2 == 0) goto L18
            java.lang.String r2 = "com.google.android.exoplayer.prev"
            int r2 = r7.indexOf(r2)
            goto L24
        L18:
            boolean r2 = r6.useRewindActionInCompactView
            if (r2 == 0) goto L23
            java.lang.String r2 = "com.google.android.exoplayer.rewind"
            int r2 = r7.indexOf(r2)
            goto L24
        L23:
            r2 = -1
        L24:
            boolean r4 = r6.useNextActionInCompactView
            if (r4 == 0) goto L2f
            java.lang.String r4 = "com.google.android.exoplayer.next"
            int r7 = r7.indexOf(r4)
            goto L3b
        L2f:
            boolean r4 = r6.useFastForwardActionInCompactView
            if (r4 == 0) goto L3a
            java.lang.String r4 = "com.google.android.exoplayer.ffwd"
            int r7 = r7.indexOf(r4)
            goto L3b
        L3a:
            r7 = -1
        L3b:
            r4 = 3
            int[] r4 = new int[r4]
            r5 = 0
            if (r2 == r3) goto L44
            r4[r5] = r2
            r5 = 1
        L44:
            boolean r8 = r6.shouldShowPauseButton(r8)
            if (r0 == r3) goto L52
            if (r8 == 0) goto L52
            int r8 = r5 + 1
            r4[r5] = r0
        L50:
            r5 = r8
            goto L5b
        L52:
            if (r1 == r3) goto L5b
            if (r8 != 0) goto L5b
            int r8 = r5 + 1
            r4[r5] = r1
            goto L50
        L5b:
            if (r7 == r3) goto L62
            int r8 = r5 + 1
            r4[r5] = r7
            r5 = r8
        L62:
            int[] r7 = java.util.Arrays.copyOf(r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.p012ui.PlayerNotificationManager.getActionIndicesForCompactView(java.util.List, com.google.android.exoplayer2.Player):int[]");
    }

    protected boolean getOngoing(Player player) {
        int playbackState = player.getPlaybackState();
        return (playbackState == 2 || playbackState == 3) && player.getPlayWhenReady();
    }

    private boolean shouldShowPauseButton(Player player) {
        return (player.getPlaybackState() == 4 || player.getPlaybackState() == 1 || !player.getPlayWhenReady()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStartOrUpdateNotification() {
        if (this.mainHandler.hasMessages(0)) {
            return;
        }
        this.mainHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postUpdateNotificationBitmap(Bitmap bitmap, int r5) {
        this.mainHandler.obtainMessage(1, r5, -1, bitmap).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int r0 = message.what;
        if (r0 == 0) {
            Player player = this.player;
            if (player != null) {
                startOrUpdateNotification(player, null);
            }
        } else if (r0 != 1) {
            return false;
        } else {
            if (this.player != null && this.isNotificationStarted && this.currentNotificationTag == message.arg1) {
                startOrUpdateNotification(this.player, (Bitmap) message.obj);
            }
        }
        return true;
    }

    private static Map<String, NotificationCompat.Action> createPlaybackActions(Context context, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
        HashMap hashMap = new HashMap();
        hashMap.put(ACTION_PLAY, new NotificationCompat.Action(r7, context.getString(C2058R.string.exo_controls_play_description), createBroadcastIntent(ACTION_PLAY, context, r6)));
        hashMap.put(ACTION_PAUSE, new NotificationCompat.Action(r8, context.getString(C2058R.string.exo_controls_pause_description), createBroadcastIntent(ACTION_PAUSE, context, r6)));
        hashMap.put(ACTION_STOP, new NotificationCompat.Action(r9, context.getString(C2058R.string.exo_controls_stop_description), createBroadcastIntent(ACTION_STOP, context, r6)));
        hashMap.put(ACTION_REWIND, new NotificationCompat.Action(r10, context.getString(C2058R.string.exo_controls_rewind_description), createBroadcastIntent(ACTION_REWIND, context, r6)));
        hashMap.put(ACTION_FAST_FORWARD, new NotificationCompat.Action(r11, context.getString(C2058R.string.exo_controls_fastforward_description), createBroadcastIntent(ACTION_FAST_FORWARD, context, r6)));
        hashMap.put(ACTION_PREVIOUS, new NotificationCompat.Action(r12, context.getString(C2058R.string.exo_controls_previous_description), createBroadcastIntent(ACTION_PREVIOUS, context, r6)));
        hashMap.put(ACTION_NEXT, new NotificationCompat.Action(r13, context.getString(C2058R.string.exo_controls_next_description), createBroadcastIntent(ACTION_NEXT, context, r6)));
        return hashMap;
    }

    private static PendingIntent createBroadcastIntent(String str, Context context, int r4) {
        Intent intent = new Intent(str).setPackage(context.getPackageName());
        intent.putExtra(EXTRA_INSTANCE_ID, r4);
        return PendingIntent.getBroadcast(context, r4, intent, Util.SDK_INT >= 23 ? 201326592 : C1856C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    private static void setLargeIcon(NotificationCompat.Builder builder, Bitmap bitmap) {
        builder.setLargeIcon(bitmap);
    }

    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$PlayerListener */
    /* loaded from: classes2.dex */
    private class PlayerListener implements Player.Listener {
        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onAudioSessionIdChanged(int r1) {
            Player.Listener.CC.$default$onAudioSessionIdChanged(this, r1);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onCues(CueGroup cueGroup) {
            Player.Listener.CC.$default$onCues(this, cueGroup);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onCues(List list) {
            Player.Listener.CC.$default$onCues(this, list);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onDeviceVolumeChanged(int r1, boolean z) {
            Player.Listener.CC.$default$onDeviceVolumeChanged(this, r1, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onIsLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onIsLoadingChanged(this, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onIsPlayingChanged(boolean z) {
            Player.Listener.CC.$default$onIsPlayingChanged(this, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onLoadingChanged(this, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
            Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int r2) {
            Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, r2);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onMetadata(Metadata metadata) {
            Player.Listener.CC.$default$onMetadata(this, metadata);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int r2) {
            Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, r2);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlaybackStateChanged(int r1) {
            Player.Listener.CC.$default$onPlaybackStateChanged(this, r1);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int r1) {
            Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, r1);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerError(this, playbackException);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlayerStateChanged(boolean z, int r2) {
            Player.Listener.CC.$default$onPlayerStateChanged(this, z, r2);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(int r1) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, r1);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int r3) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, r3);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onRenderedFirstFrame() {
            Player.Listener.CC.$default$onRenderedFirstFrame(this);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onRepeatModeChanged(int r1) {
            Player.Listener.CC.$default$onRepeatModeChanged(this, r1);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onSeekBackIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onSeekProcessed() {
            Player.Listener.CC.$default$onSeekProcessed(this);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onSurfaceSizeChanged(int r1, int r2) {
            Player.Listener.CC.$default$onSurfaceSizeChanged(this, r1, r2);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onTimelineChanged(Timeline timeline, int r2) {
            Player.Listener.CC.$default$onTimelineChanged(this, timeline, r2);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onTracksChanged(Tracks tracks) {
            Player.Listener.CC.$default$onTracksChanged(this, tracks);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public /* synthetic */ void onVolumeChanged(float f) {
            Player.Listener.CC.$default$onVolumeChanged(this, f);
        }

        private PlayerListener() {
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 7, 0, 12, 11, 8, 9, 14)) {
                PlayerNotificationManager.this.postStartOrUpdateNotification();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$NotificationBroadcastReceiver */
    /* loaded from: classes2.dex */
    public class NotificationBroadcastReceiver extends BroadcastReceiver {
        private NotificationBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Player player = PlayerNotificationManager.this.player;
            if (player != null && PlayerNotificationManager.this.isNotificationStarted && intent.getIntExtra(PlayerNotificationManager.EXTRA_INSTANCE_ID, PlayerNotificationManager.this.instanceId) == PlayerNotificationManager.this.instanceId) {
                String action = intent.getAction();
                if (PlayerNotificationManager.ACTION_PLAY.equals(action)) {
                    if (player.getPlaybackState() == 1) {
                        player.prepare();
                    } else if (player.getPlaybackState() == 4) {
                        player.seekToDefaultPosition(player.getCurrentMediaItemIndex());
                    }
                    player.play();
                } else if (PlayerNotificationManager.ACTION_PAUSE.equals(action)) {
                    player.pause();
                } else if (PlayerNotificationManager.ACTION_PREVIOUS.equals(action)) {
                    player.seekToPrevious();
                } else if (PlayerNotificationManager.ACTION_REWIND.equals(action)) {
                    player.seekBack();
                } else if (PlayerNotificationManager.ACTION_FAST_FORWARD.equals(action)) {
                    player.seekForward();
                } else if (PlayerNotificationManager.ACTION_NEXT.equals(action)) {
                    player.seekToNext();
                } else if (PlayerNotificationManager.ACTION_STOP.equals(action)) {
                    player.stop(true);
                } else if (PlayerNotificationManager.ACTION_DISMISS.equals(action)) {
                    PlayerNotificationManager.this.stopNotification(true);
                } else if (action == null || PlayerNotificationManager.this.customActionReceiver == null || !PlayerNotificationManager.this.customActions.containsKey(action)) {
                } else {
                    PlayerNotificationManager.this.customActionReceiver.onCustomAction(player, action, intent);
                }
            }
        }
    }
}
