package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.onesignal.NotificationBundleProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeDataParser implements ValueParser<ShapeData> {
    public static final ShapeDataParser INSTANCE = new ShapeDataParser();
    private static final JsonReader.Options NAMES = JsonReader.Options.m1371of("c", "v", "i", NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST);

    private ShapeDataParser() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.ValueParser
    public ShapeData parse(JsonReader jsonReader, float f) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.beginArray();
        }
        jsonReader.beginObject();
        List<PointF> list = null;
        List<PointF> list2 = null;
        List<PointF> list3 = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                z = jsonReader.nextBoolean();
            } else if (selectName == 1) {
                list = JsonUtils.jsonToPoints(jsonReader, f);
            } else if (selectName == 2) {
                list2 = JsonUtils.jsonToPoints(jsonReader, f);
            } else if (selectName == 3) {
                list3 = JsonUtils.jsonToPoints(jsonReader, f);
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (jsonReader.peek() == JsonReader.Token.END_ARRAY) {
            jsonReader.endArray();
        }
        if (list == null || list2 == null || list3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (list.isEmpty()) {
            return new ShapeData(new PointF(), false, Collections.emptyList());
        }
        int size = list.size();
        PointF pointF = list.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int r7 = 1; r7 < size; r7++) {
            PointF pointF2 = list.get(r7);
            int r9 = r7 - 1;
            arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(r9), list3.get(r9)), MiscUtils.addPoints(pointF2, list2.get(r7)), pointF2));
        }
        if (z) {
            PointF pointF3 = list.get(0);
            int r13 = size - 1;
            arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(r13), list3.get(r13)), MiscUtils.addPoints(pointF3, list2.get(0)), pointF3));
        }
        return new ShapeData(pointF, z, arrayList);
    }
}
