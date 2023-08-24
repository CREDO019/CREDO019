package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> implements ContextualDeserializer {
    protected final Boolean _unwrapSingle;

    protected abstract T handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    protected abstract PrimitiveArrayDeserializers<?> withResolved(Boolean bool);

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
        this._unwrapSingle = null;
    }

    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> primitiveArrayDeserializers, Boolean bool) {
        super(primitiveArrayDeserializers._valueClass);
        this._unwrapSingle = bool;
    }

    public static JsonDeserializer<?> forType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (cls == Long.TYPE) {
            return LongDeser.instance;
        }
        if (cls == Byte.TYPE) {
            return new ByteDeser();
        }
        if (cls == Short.TYPE) {
            return new ShortDeser();
        }
        if (cls == Float.TYPE) {
            return new FloatDeser();
        }
        if (cls == Double.TYPE) {
            return new DoubleDeser();
        }
        if (cls == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (cls == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Boolean findFormatFeature = findFormatFeature(deserializationContext, beanProperty, this._valueClass, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return findFormatFeature == this._unwrapSingle ? this : withResolved(findFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    protected T handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return handleSingleElementUnwrapped(jsonParser, deserializationContext);
        }
        return (T) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        private static final long serialVersionUID = 1;

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return this;
        }

        public CharDeser() {
            super(char[].class);
        }

        protected CharDeser(CharDeser charDeser, Boolean bool) {
            super(charDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String charSequence;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken == JsonToken.VALUE_STRING) {
                            charSequence = jsonParser.getText();
                        } else {
                            charSequence = ((CharSequence) deserializationContext.handleUnexpectedToken(Character.TYPE, jsonParser)).toString();
                        }
                        if (charSequence.length() != 1) {
                            deserializationContext.reportMappingException("Can not convert a JSON String of length %d into a char element of char array", Integer.valueOf(charSequence.length()));
                        }
                        sb.append(charSequence.charAt(0));
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public char[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        private static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        protected BooleanDeser(BooleanDeser booleanDeser, Boolean bool) {
            super(booleanDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new BooleanDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            boolean _parseBooleanPrimitive;
            int r5;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] resetAndStart = booleanBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = booleanBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r5 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseBooleanPrimitive;
                    r3 = r5;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r5;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, booleanBuilder.bufferedSize() + r3);
                }
            }
            return booleanBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public boolean[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        private static final long serialVersionUID = 1;

        public ByteDeser() {
            super(byte[].class);
        }

        protected ByteDeser(ByteDeser byteDeser, Boolean bool) {
            super(byteDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new ByteDeser(this, bool);
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x0072 A[Catch: Exception -> 0x008a, TRY_LEAVE, TryCatch #1 {Exception -> 0x008a, blocks: (B:21:0x0043, B:23:0x004b, B:25:0x004f, B:28:0x0054, B:33:0x006f, B:35:0x0072, B:31:0x005a, B:32:0x006b), top: B:49:0x0043 }] */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public byte[] deserialize(com.fasterxml.jackson.core.JsonParser r7, com.fasterxml.jackson.databind.DeserializationContext r8) throws java.io.IOException {
            /*
                r6 = this;
                com.fasterxml.jackson.core.JsonToken r0 = r7.getCurrentToken()
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
                if (r0 != r1) goto L11
                com.fasterxml.jackson.core.Base64Variant r8 = r8.getBase64Variant()
                byte[] r7 = r7.getBinaryValue(r8)
                return r7
            L11:
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT
                if (r0 != r1) goto L26
                java.lang.Object r0 = r7.getEmbeddedObject()
                if (r0 != 0) goto L1d
                r7 = 0
                return r7
            L1d:
                boolean r1 = r0 instanceof byte[]
                if (r1 == 0) goto L26
                byte[] r0 = (byte[]) r0
                byte[] r0 = (byte[]) r0
                return r0
            L26:
                boolean r0 = r7.isExpectedStartArrayToken()
                if (r0 != 0) goto L33
                java.lang.Object r7 = r6.handleNonArray(r7, r8)
                byte[] r7 = (byte[]) r7
                return r7
            L33:
                com.fasterxml.jackson.databind.util.ArrayBuilders r0 = r8.getArrayBuilders()
                com.fasterxml.jackson.databind.util.ArrayBuilders$ByteBuilder r0 = r0.getByteBuilder()
                java.lang.Object r1 = r0.resetAndStart()
                byte[] r1 = (byte[]) r1
                r2 = 0
                r3 = 0
            L43:
                com.fasterxml.jackson.core.JsonToken r4 = r7.nextToken()     // Catch: java.lang.Exception -> L8a
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.END_ARRAY     // Catch: java.lang.Exception -> L8a
                if (r4 == r5) goto L83
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch: java.lang.Exception -> L8a
                if (r4 == r5) goto L6b
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch: java.lang.Exception -> L8a
                if (r4 != r5) goto L54
                goto L6b
            L54:
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL     // Catch: java.lang.Exception -> L8a
                if (r4 != r5) goto L5a
                r4 = 0
                goto L6f
            L5a:
                java.lang.Class<?> r4 = r6._valueClass     // Catch: java.lang.Exception -> L8a
                java.lang.Class r4 = r4.getComponentType()     // Catch: java.lang.Exception -> L8a
                java.lang.Object r4 = r8.handleUnexpectedToken(r4, r7)     // Catch: java.lang.Exception -> L8a
                java.lang.Number r4 = (java.lang.Number) r4     // Catch: java.lang.Exception -> L8a
                byte r4 = r4.byteValue()     // Catch: java.lang.Exception -> L8a
                goto L6f
            L6b:
                byte r4 = r7.getByteValue()     // Catch: java.lang.Exception -> L8a
            L6f:
                int r5 = r1.length     // Catch: java.lang.Exception -> L8a
                if (r3 < r5) goto L7a
                java.lang.Object r5 = r0.appendCompletedChunk(r1, r3)     // Catch: java.lang.Exception -> L8a
                byte[] r5 = (byte[]) r5     // Catch: java.lang.Exception -> L8a
                r1 = r5
                r3 = 0
            L7a:
                int r5 = r3 + 1
                r1[r3] = r4     // Catch: java.lang.Exception -> L80
                r3 = r5
                goto L43
            L80:
                r7 = move-exception
                r3 = r5
                goto L8b
            L83:
                java.lang.Object r7 = r0.completeAndClearBuffer(r1, r3)
                byte[] r7 = (byte[]) r7
                return r7
            L8a:
                r7 = move-exception
            L8b:
                int r8 = r0.bufferedSize()
                int r8 = r8 + r3
                com.fasterxml.jackson.databind.JsonMappingException r7 = com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(r7, r1, r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.ByteDeser.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):byte[]");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public byte[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            byte byteValue;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                byteValue = jsonParser.getByteValue();
            } else if (currentToken == JsonToken.VALUE_NULL) {
                return null;
            } else {
                byteValue = ((Number) deserializationContext.handleUnexpectedToken(this._valueClass.getComponentType(), jsonParser)).byteValue();
            }
            return new byte[]{byteValue};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        private static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        protected ShortDeser(ShortDeser shortDeser, Boolean bool) {
            super(shortDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new ShortDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            short _parseShortPrimitive;
            int r5;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] resetAndStart = shortBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = shortBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r5 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseShortPrimitive;
                    r3 = r5;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r5;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, shortBuilder.bufferedSize() + r3);
                }
            }
            return shortBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public short[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();
        private static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        protected IntDeser(IntDeser intDeser, Boolean bool) {
            super(intDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new IntDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int _parseIntPrimitive;
            int r5;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] resetAndStart = intBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = intBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r5 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseIntPrimitive;
                    r3 = r5;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r5;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, intBuilder.bufferedSize() + r3);
                }
            }
            return intBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public int[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();
        private static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        protected LongDeser(LongDeser longDeser, Boolean bool) {
            super(longDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new LongDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            long _parseLongPrimitive;
            int r6;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] resetAndStart = longBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = longBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r6 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseLongPrimitive;
                    r3 = r6;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r6;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, longBuilder.bufferedSize() + r3);
                }
            }
            return longBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public long[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        private static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        protected FloatDeser(FloatDeser floatDeser, Boolean bool) {
            super(floatDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new FloatDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            float _parseFloatPrimitive;
            int r5;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] resetAndStart = floatBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = floatBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r5 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseFloatPrimitive;
                    r3 = r5;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r5;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, floatBuilder.bufferedSize() + r3);
                }
            }
            return floatBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public float[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        private static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        protected DoubleDeser(DoubleDeser doubleDeser, Boolean bool) {
            super(doubleDeser, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new DoubleDeser(this, bool);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            double _parseDoublePrimitive;
            int r6;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] resetAndStart = doubleBuilder.resetAndStart();
            int r3 = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                    if (r3 >= resetAndStart.length) {
                        resetAndStart = doubleBuilder.appendCompletedChunk(resetAndStart, r3);
                        r3 = 0;
                    }
                    r6 = r3 + 1;
                } catch (Exception e) {
                    e = e;
                }
                try {
                    resetAndStart[r3] = _parseDoublePrimitive;
                    r3 = r6;
                } catch (Exception e2) {
                    e = e2;
                    r3 = r6;
                    throw JsonMappingException.wrapWithPath(e, resetAndStart, doubleBuilder.bufferedSize() + r3);
                }
            }
            return doubleBuilder.completeAndClearBuffer(resetAndStart, r3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public double[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }
    }
}
