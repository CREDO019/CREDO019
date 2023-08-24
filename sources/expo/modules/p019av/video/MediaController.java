package expo.modules.p019av.video;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import expo.modules.p019av.C4300R;
import expo.modules.p019av.player.PlayerDataControl;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

/* renamed from: expo.modules.av.video.MediaController */
/* loaded from: classes4.dex */
public class MediaController extends FrameLayout {
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private static final int sDefaultTimeout = 3000;
    private ViewGroup mAnchor;
    private Context mContext;
    private TextView mCurrentTime;
    private boolean mDragging;
    private TextView mEndTime;
    private ImageButton mFastForwardButton;
    private View.OnClickListener mFfwdListener;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    private boolean mFromXml;
    private ImageButton mFullscreenButton;
    private View.OnClickListener mFullscreenListener;
    private Handler mHandler;
    private boolean mListenersSet;
    private ImageButton mNextButton;
    private View.OnClickListener mNextListener;
    private ImageButton mPauseButton;
    private View.OnClickListener mPauseListener;
    private PlayerDataControl mPlayer;
    private ImageButton mPrevButton;
    private View.OnClickListener mPrevListener;
    private ProgressBar mProgress;
    private View.OnClickListener mRewListener;
    private ImageButton mRewindButton;
    private View mRoot;
    private SeekBar.OnSeekBarChangeListener mSeekListener;
    private boolean mShowing;
    private boolean mUseFastForward;

    public MediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new MessageHandler(this);
        this.mPauseListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.show(3000);
                MediaController.this.doPauseResume();
            }
        };
        this.mFullscreenListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.show(3000);
                MediaController.this.doToggleFullscreen();
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: expo.modules.av.video.MediaController.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MediaController.this.show(3600000);
                MediaController.this.mDragging = true;
                MediaController.this.mHandler.removeMessages(2);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int r4, boolean z) {
                if (MediaController.this.mPlayer != null && z) {
                    int duration = (int) ((MediaController.this.mPlayer.getDuration() * r4) / 1000);
                    MediaController.this.mPlayer.seekTo(duration);
                    if (MediaController.this.mCurrentTime != null) {
                        MediaController.this.mCurrentTime.setText(MediaController.this.stringForTime(duration));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                MediaController.this.mDragging = false;
                MediaController.this.setProgress();
                MediaController.this.updatePausePlay();
                MediaController.this.show(3000);
                MediaController.this.mHandler.sendEmptyMessage(2);
            }
        };
        this.mRewListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaController.this.mPlayer == null) {
                    return;
                }
                MediaController.this.mPlayer.seekTo(MediaController.this.mPlayer.getCurrentPosition() - 5000);
                MediaController.this.setProgress();
                MediaController.this.show(3000);
            }
        };
        this.mFfwdListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaController.this.mPlayer == null) {
                    return;
                }
                MediaController.this.mPlayer.seekTo(MediaController.this.mPlayer.getCurrentPosition() + 15000);
                MediaController.this.setProgress();
                MediaController.this.show(3000);
            }
        };
        this.mRoot = null;
        this.mContext = context;
        this.mUseFastForward = true;
        this.mFromXml = true;
    }

    public MediaController(Context context, boolean z) {
        super(context);
        this.mHandler = new MessageHandler(this);
        this.mPauseListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.show(3000);
                MediaController.this.doPauseResume();
            }
        };
        this.mFullscreenListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.show(3000);
                MediaController.this.doToggleFullscreen();
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: expo.modules.av.video.MediaController.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MediaController.this.show(3600000);
                MediaController.this.mDragging = true;
                MediaController.this.mHandler.removeMessages(2);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int r4, boolean z2) {
                if (MediaController.this.mPlayer != null && z2) {
                    int duration = (int) ((MediaController.this.mPlayer.getDuration() * r4) / 1000);
                    MediaController.this.mPlayer.seekTo(duration);
                    if (MediaController.this.mCurrentTime != null) {
                        MediaController.this.mCurrentTime.setText(MediaController.this.stringForTime(duration));
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                MediaController.this.mDragging = false;
                MediaController.this.setProgress();
                MediaController.this.updatePausePlay();
                MediaController.this.show(3000);
                MediaController.this.mHandler.sendEmptyMessage(2);
            }
        };
        this.mRewListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaController.this.mPlayer == null) {
                    return;
                }
                MediaController.this.mPlayer.seekTo(MediaController.this.mPlayer.getCurrentPosition() - 5000);
                MediaController.this.setProgress();
                MediaController.this.show(3000);
            }
        };
        this.mFfwdListener = new View.OnClickListener() { // from class: expo.modules.av.video.MediaController.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MediaController.this.mPlayer == null) {
                    return;
                }
                MediaController.this.mPlayer.seekTo(MediaController.this.mPlayer.getCurrentPosition() + 15000);
                MediaController.this.setProgress();
                MediaController.this.show(3000);
            }
        };
        this.mContext = context;
        this.mUseFastForward = z;
    }

    public MediaController(Context context) {
        this(context, true);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        View view = this.mRoot;
        if (view != null) {
            initControllerView(view);
        }
        super.onFinishInflate();
    }

    public void setMediaPlayer(PlayerDataControl playerDataControl) {
        this.mPlayer = playerDataControl;
        updateControls();
    }

    public void updateControls() {
        setProgress();
        updatePausePlay();
        updateFullScreen();
    }

    public void setAnchorView(ViewGroup viewGroup) {
        this.mAnchor = viewGroup;
        if (this.mRoot == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            removeAllViews();
            addView(makeControllerView(), layoutParams);
        }
    }

    protected View makeControllerView() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C4300R.layout.expo_media_controller, (ViewGroup) null);
        this.mRoot = inflate;
        initControllerView(inflate);
        return this.mRoot;
    }

    private void initControllerView(View view) {
        ImageButton imageButton = (ImageButton) view.findViewById(C4300R.C4305id.play_button);
        this.mPauseButton = imageButton;
        if (imageButton != null) {
            imageButton.requestFocus();
            this.mPauseButton.setOnClickListener(this.mPauseListener);
        }
        ImageButton imageButton2 = (ImageButton) view.findViewById(C4300R.C4305id.fullscreen_mode_button);
        this.mFullscreenButton = imageButton2;
        if (imageButton2 != null) {
            imageButton2.requestFocus();
            this.mFullscreenButton.setOnClickListener(this.mFullscreenListener);
        }
        ImageButton imageButton3 = (ImageButton) view.findViewById(C4300R.C4305id.fast_forward_button);
        this.mFastForwardButton = imageButton3;
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this.mFfwdListener);
            if (!this.mFromXml) {
                this.mFastForwardButton.setVisibility(this.mUseFastForward ? 0 : 8);
            }
        }
        ImageButton imageButton4 = (ImageButton) view.findViewById(C4300R.C4305id.rewind_button);
        this.mRewindButton = imageButton4;
        if (imageButton4 != null) {
            imageButton4.setOnClickListener(this.mRewListener);
            if (!this.mFromXml) {
                this.mRewindButton.setVisibility(this.mUseFastForward ? 0 : 8);
            }
        }
        ImageButton imageButton5 = (ImageButton) view.findViewById(C4300R.C4305id.skip_next_button);
        this.mNextButton = imageButton5;
        if (imageButton5 != null && !this.mFromXml && !this.mListenersSet) {
            imageButton5.setVisibility(8);
        }
        ImageButton imageButton6 = (ImageButton) view.findViewById(C4300R.C4305id.skip_previous_button);
        this.mPrevButton = imageButton6;
        if (imageButton6 != null && !this.mFromXml && !this.mListenersSet) {
            imageButton6.setVisibility(8);
        }
        ProgressBar progressBar = (ProgressBar) view.findViewById(C4300R.C4305id.seek_bar);
        this.mProgress = progressBar;
        if (progressBar != null) {
            if (progressBar instanceof SeekBar) {
                ((SeekBar) progressBar).setOnSeekBarChangeListener(this.mSeekListener);
            }
            this.mProgress.setMax(1000);
        }
        this.mEndTime = (TextView) view.findViewById(C4300R.C4305id.end_time_text);
        this.mCurrentTime = (TextView) view.findViewById(C4300R.C4305id.current_time_text);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        installPrevNextListeners();
    }

    public void show() {
        show(3000);
    }

    private void disableUnsupportedButtons() {
        PlayerDataControl playerDataControl = this.mPlayer;
        if (playerDataControl == null) {
            return;
        }
        try {
            if (this.mPauseButton != null && !playerDataControl.canPause()) {
                this.mPauseButton.setEnabled(false);
            }
            if (this.mRewindButton != null && !this.mPlayer.canSeekBackward()) {
                this.mRewindButton.setEnabled(false);
            }
            if (this.mFastForwardButton == null || this.mPlayer.canSeekForward()) {
                return;
            }
            this.mFastForwardButton.setEnabled(false);
        } catch (IncompatibleClassChangeError unused) {
        }
    }

    public void show(int r5) {
        if (!this.mShowing && this.mAnchor != null) {
            setProgress();
            ImageButton imageButton = this.mPauseButton;
            if (imageButton != null) {
                imageButton.requestFocus();
            }
            disableUnsupportedButtons();
            this.mAnchor.addView(this, new FrameLayout.LayoutParams(-1, -1));
            this.mShowing = true;
        }
        updateControls();
        this.mHandler.sendEmptyMessage(2);
        Message obtainMessage = this.mHandler.obtainMessage(1);
        if (r5 != 0) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendMessageDelayed(obtainMessage, r5);
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    public void hide() {
        ViewGroup viewGroup = this.mAnchor;
        if (viewGroup == null) {
            return;
        }
        try {
            viewGroup.removeView(this);
            this.mHandler.removeMessages(2);
        } catch (IllegalArgumentException unused) {
            Log.w("MediaController", "already removed");
        }
        this.mShowing = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String stringForTime(int r8) {
        int r82 = r8 / 1000;
        int r0 = r82 % 60;
        int r1 = (r82 / 60) % 60;
        int r83 = r82 / 3600;
        this.mFormatBuilder.setLength(0);
        return r83 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(r83), Integer.valueOf(r1), Integer.valueOf(r0)).toString() : this.mFormatter.format("%02d:%02d", Integer.valueOf(r1), Integer.valueOf(r0)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int setProgress() {
        PlayerDataControl playerDataControl = this.mPlayer;
        if (playerDataControl == null || this.mDragging) {
            return 0;
        }
        int currentPosition = playerDataControl.getCurrentPosition();
        int duration = this.mPlayer.getDuration();
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            if (duration > 0) {
                progressBar.setProgress((int) ((currentPosition * 1000) / duration));
            }
            this.mProgress.setSecondaryProgress(this.mPlayer.getBufferPercentage() * 10);
        }
        TextView textView = this.mEndTime;
        if (textView != null) {
            textView.setText(stringForTime(duration));
        }
        TextView textView2 = this.mCurrentTime;
        if (textView2 != null) {
            textView2.setText(stringForTime(currentPosition));
        }
        return currentPosition;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            show(3000);
            return true;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            show(3000);
            return false;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mPlayer != null && isEnabled()) {
            int keyCode = keyEvent.getKeyCode();
            boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
            if (keyCode != 79 && keyCode != 85 && keyCode != 62) {
                if (keyCode == 126) {
                    if (z && !this.mPlayer.isPlaying()) {
                        this.mPlayer.start();
                        updatePausePlay();
                        show(3000);
                    }
                    return true;
                } else if (keyCode == 86 || keyCode == 127) {
                    if (z && this.mPlayer.isPlaying()) {
                        this.mPlayer.pause();
                        updatePausePlay();
                        show(3000);
                    }
                    return true;
                } else if (keyCode == 25 || keyCode == 24 || keyCode == 164) {
                    return super.dispatchKeyEvent(keyEvent);
                } else {
                    if (keyCode != 4 && keyCode != 82) {
                        show(3000);
                        return super.dispatchKeyEvent(keyEvent);
                    }
                    if (z) {
                        hide();
                    }
                    return true;
                }
            } else if (z) {
                doPauseResume();
                show(3000);
                ImageButton imageButton = this.mPauseButton;
                if (imageButton != null) {
                    imageButton.requestFocus();
                }
            }
        }
        return true;
    }

    public void updatePausePlay() {
        PlayerDataControl playerDataControl;
        if (this.mRoot == null || this.mPauseButton == null || (playerDataControl = this.mPlayer) == null) {
            return;
        }
        if (playerDataControl.isPlaying()) {
            this.mPauseButton.setImageResource(C4300R.C4303drawable.exo_controls_pause);
        } else {
            this.mPauseButton.setImageResource(C4300R.C4303drawable.exo_controls_play);
        }
    }

    public void updateFullScreen() {
        PlayerDataControl playerDataControl;
        if (this.mRoot == null || this.mFullscreenButton == null || (playerDataControl = this.mPlayer) == null) {
            return;
        }
        if (playerDataControl.isFullscreen()) {
            this.mFullscreenButton.setImageResource(C4300R.C4303drawable.ic_fullscreen_exit_32dp);
        } else {
            this.mFullscreenButton.setImageResource(C4300R.C4303drawable.ic_fullscreen_32dp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPauseResume() {
        PlayerDataControl playerDataControl = this.mPlayer;
        if (playerDataControl == null) {
            return;
        }
        if (playerDataControl.isPlaying()) {
            this.mPlayer.pause();
        } else {
            this.mPlayer.start();
        }
        updatePausePlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doToggleFullscreen() {
        PlayerDataControl playerDataControl = this.mPlayer;
        if (playerDataControl == null) {
            return;
        }
        playerDataControl.toggleFullscreen();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        ImageButton imageButton = this.mPauseButton;
        if (imageButton != null) {
            imageButton.setEnabled(z);
        }
        ImageButton imageButton2 = this.mFastForwardButton;
        if (imageButton2 != null) {
            imageButton2.setEnabled(z);
        }
        ImageButton imageButton3 = this.mRewindButton;
        if (imageButton3 != null) {
            imageButton3.setEnabled(z);
        }
        ImageButton imageButton4 = this.mNextButton;
        boolean z2 = true;
        if (imageButton4 != null) {
            imageButton4.setEnabled(z && this.mNextListener != null);
        }
        ImageButton imageButton5 = this.mPrevButton;
        if (imageButton5 != null) {
            imageButton5.setEnabled((!z || this.mPrevListener == null) ? false : false);
        }
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setEnabled(z);
        }
        disableUnsupportedButtons();
        super.setEnabled(z);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MediaController.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MediaController.class.getName());
    }

    private void installPrevNextListeners() {
        ImageButton imageButton = this.mNextButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(this.mNextListener);
            this.mNextButton.setEnabled(this.mNextListener != null);
        }
        ImageButton imageButton2 = this.mPrevButton;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this.mPrevListener);
            this.mPrevButton.setEnabled(this.mPrevListener != null);
        }
    }

    public void setPrevNextListeners(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.mNextListener = onClickListener;
        this.mPrevListener = onClickListener2;
        this.mListenersSet = true;
        if (this.mRoot != null) {
            installPrevNextListeners();
            ImageButton imageButton = this.mNextButton;
            if (imageButton != null && !this.mFromXml) {
                imageButton.setVisibility(0);
            }
            ImageButton imageButton2 = this.mPrevButton;
            if (imageButton2 == null || this.mFromXml) {
                return;
            }
            imageButton2.setVisibility(0);
        }
    }

    /* renamed from: expo.modules.av.video.MediaController$MessageHandler */
    /* loaded from: classes4.dex */
    private static class MessageHandler extends Handler {
        private final WeakReference<MediaController> mView;

        MessageHandler(MediaController mediaController) {
            this.mView = new WeakReference<>(mediaController);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MediaController mediaController = this.mView.get();
            if (mediaController == null || mediaController.mPlayer == null) {
                return;
            }
            int r4 = message.what;
            if (r4 == 1) {
                mediaController.hide();
            } else if (r4 != 2) {
            } else {
                int progress = mediaController.setProgress();
                if (!mediaController.mDragging && mediaController.mShowing && mediaController.mPlayer.isPlaying()) {
                    sendMessageDelayed(obtainMessage(2), 1000 - (progress % 1000));
                }
            }
        }
    }
}
