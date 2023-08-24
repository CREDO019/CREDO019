package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* loaded from: classes.dex */
class CircleShapeParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.m1371of("nm", "p", "s", "hd", "d");

    private CircleShapeParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CircleShape parse(JsonReader jsonReader, LottieComposition lottieComposition, int r12) throws IOException {
        boolean z = r12 == 3;
        String str = null;
        AnimatableValue<PointF, PointF> animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        boolean z2 = false;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName == 1) {
                animatableValue = AnimatablePathValueParser.parseSplitPath(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
            } else if (selectName == 3) {
                z2 = jsonReader.nextBoolean();
            } else if (selectName == 4) {
                z = jsonReader.nextInt() == 3;
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        return new CircleShape(str, animatableValue, animatablePointValue, z, z2);
    }
}
