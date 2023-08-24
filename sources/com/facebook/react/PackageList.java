package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import cl.json.RNSharePackage;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.airbnb.android.react.lottie.LottiePackage;
import com.airbnb.android.react.maps.MapsPackage;
import com.amplitude.reactnative.AmplitudeReactNativePackage;
import com.bitgo.randombytes.RandomBytesPackage;
import com.facebook.flipper.reactnative.FlipperPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.geektime.rnonesignalandroid.ReactNativeOneSignalPackage;
import com.horcrux.svg.SvgPackage;
import com.learnium.RNDeviceInfo.C3452RNDeviceInfo;
import com.lugg.RNCConfig.RNCConfigPackage;
import com.masteratul.exceptionhandler.ReactNativeExceptionHandlerPackage;
import com.oblador.performance.PerformancePackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.polidea.reactnativeble.BlePackage;
import com.reactcommunity.rndatetimepicker.RNDateTimePickerPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.reactnativecommunity.picker.RNCPickerPackage;
import com.reactnativecommunity.slider.ReactSliderPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.reactnativegooglesignin.RNGoogleSigninPackage;
import com.reactnativelauncharguments.LaunchArgumentsPackage;
import com.reactnativepagerview.PagerViewPackage;
import com.reactnativerate.RNRatePackage;
import com.rnfs.RNFSPackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.wenkesj.voice.VoicePackage;
import com.zaguiini.RNPureJwt.RNPureJwtPackage;
import de.innfactory.apiai.RNApiAiPackage;
import expo.modules.ExpoModulesPackage;
import fr.bamlab.rnimageresizer.ImageResizerPackage;
import io.invertase.googlemobileads.ReactNativeGoogleMobileAdsPackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.linusu.RNGetRandomValuesPackage;
import org.reactnative.maskedview.RNCMaskedViewPackage;
import org.th317erd.react.DynamicFontsPackage;

/* loaded from: classes.dex */
public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, (MainPackageConfig) null);
    }

    public PackageList(Application application) {
        this(application, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        return reactNativeHost == null ? this.application : reactNativeHost.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(this.mConfig), new AmplitudeReactNativePackage(), new AsyncStoragePackage(), new RNDateTimePickerPackage(), new RNCMaskedViewPackage(), new NetInfoPackage(), new ReactSliderPackage(), new RNGoogleSigninPackage(), new RNCPickerPackage(), new VoicePackage(), new ExpoModulesPackage(), new LottiePackage(), new BackgroundTimerPackage(), new BlePackage(), new RNCConfigPackage(), new C3452RNDeviceInfo(), new RNApiAiPackage(), new DynamicFontsPackage(), new ReactNativeExceptionHandlerPackage(), new FlipperPackage(), new RNFSPackage(), new RNGestureHandlerPackage(), new RNGetRandomValuesPackage(), new ReactNativeGoogleMobileAdsPackage(), new ImageResizerPackage(), new MapsPackage(), new ReactNativeOneSignalPackage(), new PagerViewPackage(), new PerformancePackage(), new RandomBytesPackage(), new RNRatePackage(), new ReanimatedPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new RNSharePackage(), new SvgPackage(), new RNCWebViewPackage(), new RNFetchBlobPackage(), new LaunchArgumentsPackage(), new RNPureJwtPackage()));
    }
}
