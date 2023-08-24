package expo.modules;

import expo.modules.adapters.react.ReactAdapterPackage;
import expo.modules.application.ApplicationPackage;
import expo.modules.barcodescanner.BarCodeScannerPackage;
import expo.modules.camera.CameraViewModule;
import expo.modules.constants.ConstantsPackage;
import expo.modules.core.BasePackage;
import expo.modules.core.interfaces.Package;
import expo.modules.documentpicker.DocumentPickerPackage;
import expo.modules.easclient.EASClientModule;
import expo.modules.filesystem.FileSystemPackage;
import expo.modules.font.FontLoaderPackage;
import expo.modules.imageloader.ImageLoaderPackage;
import expo.modules.imagepicker.ImagePickerModule;
import expo.modules.inapppurchases.InAppPurchasesPackage;
import expo.modules.keepawake.KeepAwakePackage;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.modules.Module;
import expo.modules.location.LocationPackage;
import expo.modules.p019av.AVPackage;
import expo.modules.p019av.video.VideoViewModule;
import expo.modules.screenorientation.ScreenOrientationPackage;
import expo.modules.securestore.SecureStorePackage;
import expo.modules.sensors.SensorsPackage;
import expo.modules.sms.SMSPackage;
import expo.modules.speech.SpeechPackage;
import expo.modules.splashscreen.SplashScreenPackage;
import expo.modules.updates.UpdatesPackage;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class ExpoModulesPackageList implements ModulesProvider {

    /* loaded from: classes4.dex */
    private static class LazyHolder {
        static final List<Package> packagesList = Arrays.asList(new ReactAdapterPackage(), new ApplicationPackage(), new AVPackage(), new BarCodeScannerPackage(), new ConstantsPackage(), new BasePackage(), new DocumentPickerPackage(), new FileSystemPackage(), new FontLoaderPackage(), new ImageLoaderPackage(), new InAppPurchasesPackage(), new KeepAwakePackage(), new LocationPackage(), new ScreenOrientationPackage(), new SecureStorePackage(), new SensorsPackage(), new SMSPackage(), new SpeechPackage(), new SplashScreenPackage(), new UpdatesPackage());
        static final List<Class<? extends Module>> modulesList = Arrays.asList(VideoViewModule.class, CameraViewModule.class, EASClientModule.class, ImagePickerModule.class);

        private LazyHolder() {
        }
    }

    public static List<Package> getPackageList() {
        return LazyHolder.packagesList;
    }

    @Override // expo.modules.kotlin.ModulesProvider
    public List<Class<? extends Module>> getModulesList() {
        return LazyHolder.modulesList;
    }
}
