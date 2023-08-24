package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.C0264R;
import androidx.core.provider.FontRequest;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FontResourcesParserCompat {
    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    /* loaded from: classes.dex */
    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FetchStrategy {
    }

    /* loaded from: classes.dex */
    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        private final FontRequest mRequest;
        private final int mStrategy;
        private final String mSystemFontFamilyName;
        private final int mTimeoutMs;

        public ProviderResourceEntry(FontRequest fontRequest, int r2, int r3, String str) {
            this.mRequest = fontRequest;
            this.mStrategy = r2;
            this.mTimeoutMs = r3;
            this.mSystemFontFamilyName = str;
        }

        public ProviderResourceEntry(FontRequest fontRequest, int r3, int r4) {
            this(fontRequest, r3, r4, null);
        }

        public FontRequest getRequest() {
            return this.mRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }

        public String getSystemFontFamilyName() {
            return this.mSystemFontFamilyName;
        }
    }

    /* loaded from: classes.dex */
    public static final class FontFileResourceEntry {
        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mTtcIndex;
        private String mVariationSettings;
        private int mWeight;

        public FontFileResourceEntry(String str, int r2, boolean z, String str2, int r5, int r6) {
            this.mFileName = str;
            this.mWeight = r2;
            this.mItalic = z;
            this.mVariationSettings = str2;
            this.mTtcIndex = r5;
            this.mResourceId = r6;
        }

        public String getFileName() {
            return this.mFileName;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getResourceId() {
            return this.mResourceId;
        }
    }

    /* loaded from: classes.dex */
    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] fontFileResourceEntryArr) {
            this.mEntries = fontFileResourceEntryArr;
        }

        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static FamilyResourceEntry parse(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return readFamilies(xmlPullParser, resources);
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return readFamily(xmlPullParser, resources);
        }
        skip(xmlPullParser);
        return null;
    }

    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0264R.styleable.FontFamily);
        String string = obtainAttributes.getString(C0264R.styleable.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(C0264R.styleable.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(C0264R.styleable.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(C0264R.styleable.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(C0264R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(C0264R.styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string4 = obtainAttributes.getString(C0264R.styleable.FontFamily_fontProviderSystemFontFamily);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                skip(xmlPullParser);
            }
            return new ProviderResourceEntry(new FontRequest(string, string2, string3, readCerts(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(readFont(xmlPullParser, resources));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[arrayList.size()]));
    }

    private static int getType(TypedArray typedArray, int r3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(r3);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(r3, typedValue);
        return typedValue.type;
    }

    public static List<List<byte[]>> readCerts(Resources resources, int r6) {
        if (r6 == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(r6);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (getType(obtainTypedArray, 0) == 1) {
                for (int r62 = 0; r62 < obtainTypedArray.length(); r62++) {
                    int resourceId = obtainTypedArray.getResourceId(r62, 0);
                    if (resourceId != 0) {
                        arrayList.add(toByteArrayList(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(toByteArrayList(resources.getStringArray(r6)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static List<byte[]> toByteArrayList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        int r0;
        int r02;
        int r03;
        int r2;
        int r04;
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0264R.styleable.FontFamilyFont);
        if (obtainAttributes.hasValue(C0264R.styleable.FontFamilyFont_fontWeight)) {
            r0 = C0264R.styleable.FontFamilyFont_fontWeight;
        } else {
            r0 = C0264R.styleable.FontFamilyFont_android_fontWeight;
        }
        int r4 = obtainAttributes.getInt(r0, 400);
        if (obtainAttributes.hasValue(C0264R.styleable.FontFamilyFont_fontStyle)) {
            r02 = C0264R.styleable.FontFamilyFont_fontStyle;
        } else {
            r02 = C0264R.styleable.FontFamilyFont_android_fontStyle;
        }
        boolean z = 1 == obtainAttributes.getInt(r02, 0);
        if (obtainAttributes.hasValue(C0264R.styleable.FontFamilyFont_ttcIndex)) {
            r03 = C0264R.styleable.FontFamilyFont_ttcIndex;
        } else {
            r03 = C0264R.styleable.FontFamilyFont_android_ttcIndex;
        }
        if (obtainAttributes.hasValue(C0264R.styleable.FontFamilyFont_fontVariationSettings)) {
            r2 = C0264R.styleable.FontFamilyFont_fontVariationSettings;
        } else {
            r2 = C0264R.styleable.FontFamilyFont_android_fontVariationSettings;
        }
        String string = obtainAttributes.getString(r2);
        int r7 = obtainAttributes.getInt(r03, 0);
        if (obtainAttributes.hasValue(C0264R.styleable.FontFamilyFont_font)) {
            r04 = C0264R.styleable.FontFamilyFont_font;
        } else {
            r04 = C0264R.styleable.FontFamilyFont_android_font;
        }
        int resourceId = obtainAttributes.getResourceId(r04, 0);
        String string2 = obtainAttributes.getString(r04);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        return new FontFileResourceEntry(string2, r4, z, string, r7, resourceId);
    }

    private static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int r0 = 1;
        while (r0 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                r0++;
            } else if (next == 3) {
                r0--;
            }
        }
    }

    private FontResourcesParserCompat() {
    }
}
