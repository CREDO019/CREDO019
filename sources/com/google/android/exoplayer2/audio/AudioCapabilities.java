package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.net.Uri;
import android.provider.Settings;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class AudioCapabilities {
    private static final int DEFAULT_MAX_CHANNEL_COUNT = 8;
    private static final int DEFAULT_SAMPLE_RATE_HZ = 48000;
    private static final String EXTERNAL_SURROUND_SOUND_KEY = "external_surround_sound_enabled";
    private final int maxChannelCount;
    private final int[] supportedEncodings;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 8);
    private static final AudioCapabilities EXTERNAL_SURROUND_SOUND_CAPABILITIES = new AudioCapabilities(new int[]{2, 5, 6}, 8);
    private static final ImmutableMap<Integer, Integer> ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = new ImmutableMap.Builder().put(5, 6).put(17, 6).put(7, 6).put(18, 6).put(6, 8).put(8, 8).put(14, 8).buildOrThrow();

    public static AudioCapabilities getCapabilities(Context context) {
        return getCapabilities(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AudioCapabilities getCapabilities(Context context, Intent intent) {
        if (deviceMaySetExternalSurroundSoundGlobalSetting() && Settings.Global.getInt(context.getContentResolver(), EXTERNAL_SURROUND_SOUND_KEY, 0) == 1) {
            return EXTERNAL_SURROUND_SOUND_CAPABILITIES;
        }
        if (Util.SDK_INT >= 29 && (Util.isTv(context) || Util.isAutomotive(context))) {
            return new AudioCapabilities(Api29.getDirectPlaybackSupportedEncodings(), 8);
        }
        if (intent == null || intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting()) {
            return Settings.Global.getUriFor(EXTERNAL_SURROUND_SOUND_KEY);
        }
        return null;
    }

    public AudioCapabilities(int[] r2, int r3) {
        if (r2 != null) {
            int[] copyOf = Arrays.copyOf(r2, r2.length);
            this.supportedEncodings = copyOf;
            Arrays.sort(copyOf);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = r3;
    }

    public boolean supportsEncoding(int r2) {
        return Arrays.binarySearch(this.supportedEncodings, r2) >= 0;
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public boolean isPassthroughPlaybackSupported(Format format) {
        return getEncodingAndChannelConfigForPassthrough(format) != null;
    }

    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format format) {
        int maxSupportedChannelCountForPassthrough;
        int encoding = MimeTypes.getEncoding((String) Assertions.checkNotNull(format.sampleMimeType), format.codecs);
        if (ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(encoding))) {
            if (encoding == 18 && !supportsEncoding(18)) {
                encoding = 6;
            } else if (encoding == 8 && !supportsEncoding(8)) {
                encoding = 7;
            }
            if (supportsEncoding(encoding)) {
                if (format.channelCount == -1 || encoding == 18) {
                    maxSupportedChannelCountForPassthrough = getMaxSupportedChannelCountForPassthrough(encoding, format.sampleRate != -1 ? format.sampleRate : 48000);
                } else {
                    maxSupportedChannelCountForPassthrough = format.channelCount;
                    if (maxSupportedChannelCountForPassthrough > this.maxChannelCount) {
                        return null;
                    }
                }
                int channelConfigForPassthrough = getChannelConfigForPassthrough(maxSupportedChannelCountForPassthrough);
                if (channelConfigForPassthrough == 0) {
                    return null;
                }
                return Pair.create(Integer.valueOf(encoding), Integer.valueOf(channelConfigForPassthrough));
            }
            return null;
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AudioCapabilities) {
            AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
            return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
        }
        return false;
    }

    public int hashCode() {
        return this.maxChannelCount + (Arrays.hashCode(this.supportedEncodings) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }

    private static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        return Util.SDK_INT >= 17 && ("Amazon".equals(Util.MANUFACTURER) || "Xiaomi".equals(Util.MANUFACTURER));
    }

    private static int getMaxSupportedChannelCountForPassthrough(int r2, int r3) {
        if (Util.SDK_INT >= 29) {
            return Api29.getMaxSupportedChannelCountForPassthrough(r2, r3);
        }
        return ((Integer) Assertions.checkNotNull(ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.getOrDefault(Integer.valueOf(r2), 0))).intValue();
    }

    private static int getChannelConfigForPassthrough(int r2) {
        if (Util.SDK_INT <= 28) {
            if (r2 == 7) {
                r2 = 8;
            } else if (r2 == 3 || r2 == 4 || r2 == 5) {
                r2 = 6;
            }
        }
        if (Util.SDK_INT <= 26 && "fugu".equals(Util.DEVICE) && r2 == 1) {
            r2 = 2;
        }
        return Util.getAudioTrackChannelConfig(r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Api29 {
        private static final android.media.AudioAttributes DEFAULT_AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(0).build();

        private Api29() {
        }

        public static int[] getDirectPlaybackSupportedEncodings() {
            ImmutableList.Builder builder = ImmutableList.builder();
            UnmodifiableIterator it = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.keySet().iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(intValue).setSampleRate(48000).build(), DEFAULT_AUDIO_ATTRIBUTES)) {
                    builder.add((ImmutableList.Builder) Integer.valueOf(intValue));
                }
            }
            builder.add((ImmutableList.Builder) 2);
            return Ints.toArray(builder.build());
        }

        public static int getMaxSupportedChannelCountForPassthrough(int r3, int r4) {
            for (int r0 = 8; r0 > 0; r0--) {
                if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(r3).setSampleRate(r4).setChannelMask(Util.getAudioTrackChannelConfig(r0)).build(), DEFAULT_AUDIO_ATTRIBUTES)) {
                    return r0;
                }
            }
            return 0;
        }
    }
}
