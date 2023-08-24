package expo.modules.interfaces.barcodescanner;

/* loaded from: classes4.dex */
public enum BarCodeScannerSettingsKey {
    TYPES("barCodeTypes");
    
    private final String mName;

    BarCodeScannerSettingsKey(String str) {
        this.mName = str;
    }

    public static BarCodeScannerSettingsKey fromStringName(String str) {
        BarCodeScannerSettingsKey[] values;
        for (BarCodeScannerSettingsKey barCodeScannerSettingsKey : values()) {
            if (barCodeScannerSettingsKey.getName().equalsIgnoreCase(str)) {
                return barCodeScannerSettingsKey;
            }
        }
        return null;
    }

    public String getName() {
        return this.mName;
    }
}
