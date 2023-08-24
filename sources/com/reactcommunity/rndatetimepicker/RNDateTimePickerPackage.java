package com.reactcommunity.rndatetimepicker;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class RNDateTimePickerPackage extends TurboReactPackage {
    @Override // com.facebook.react.TurboReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (str.equals(DatePickerModule.NAME)) {
            return new DatePickerModule(reactApplicationContext);
        }
        if (str.equals(TimePickerModule.NAME)) {
            return new TimePickerModule(reactApplicationContext);
        }
        return null;
    }

    @Override // com.facebook.react.TurboReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() { // from class: com.reactcommunity.rndatetimepicker.RNDateTimePickerPackage$$ExternalSyntheticLambda0
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return RNDateTimePickerPackage.lambda$getReactModuleInfoProvider$0();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getReactModuleInfoProvider$0() {
        HashMap hashMap = new HashMap();
        hashMap.put(DatePickerModule.NAME, new ReactModuleInfo(DatePickerModule.NAME, DatePickerModule.NAME, false, false, false, false, false));
        hashMap.put(TimePickerModule.NAME, new ReactModuleInfo(TimePickerModule.NAME, TimePickerModule.NAME, false, false, false, false, false));
        return hashMap;
    }
}
