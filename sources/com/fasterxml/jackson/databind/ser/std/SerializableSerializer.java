package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
/* loaded from: classes2.dex */
public class SerializableSerializer extends StdSerializer<JsonSerializable> {
    public static final SerializableSerializer instance = new SerializableSerializer();
    private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference<>();

    protected SerializableSerializer() {
        super(JsonSerializable.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, JsonSerializable jsonSerializable) {
        if (jsonSerializable instanceof JsonSerializable.Base) {
            return ((JsonSerializable.Base) jsonSerializable).isEmpty(serializerProvider);
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonSerializable.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.fasterxml.jackson.databind.JsonNode getSchema(com.fasterxml.jackson.databind.SerializerProvider r7, java.lang.reflect.Type r8) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r6 = this;
            com.fasterxml.jackson.databind.node.ObjectNode r0 = r6.createObjectNode()
            r1 = 0
            if (r8 == 0) goto L42
            java.lang.Class r8 = com.fasterxml.jackson.databind.type.TypeFactory.rawClass(r8)
            java.lang.Class<com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema> r2 = com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema.class
            boolean r2 = r8.isAnnotationPresent(r2)
            if (r2 == 0) goto L42
            java.lang.Class<com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema> r2 = com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r8 = r8.getAnnotation(r2)
            com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema r8 = (com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema) r8
            java.lang.String r2 = r8.schemaType()
            java.lang.String r3 = r8.schemaObjectPropertiesDefinition()
            java.lang.String r4 = "##irrelevant"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L30
            java.lang.String r3 = r8.schemaObjectPropertiesDefinition()
            goto L31
        L30:
            r3 = r1
        L31:
            java.lang.String r5 = r8.schemaItemDefinition()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L3f
            java.lang.String r1 = r8.schemaItemDefinition()
        L3f:
            r8 = r1
            r1 = r3
            goto L45
        L42:
            java.lang.String r2 = "any"
            r8 = r1
        L45:
            java.lang.String r3 = "type"
            r0.put(r3, r2)
            r2 = 0
            if (r1 == 0) goto L62
            java.lang.String r3 = "properties"
            com.fasterxml.jackson.databind.ObjectMapper r4 = _getObjectMapper()     // Catch: java.io.IOException -> L5b
            com.fasterxml.jackson.databind.JsonNode r1 = r4.readTree(r1)     // Catch: java.io.IOException -> L5b
            r0.set(r3, r1)     // Catch: java.io.IOException -> L5b
            goto L62
        L5b:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r3 = "Failed to parse @JsonSerializableSchema.schemaObjectPropertiesDefinition value"
            r7.reportMappingProblem(r3, r1)
        L62:
            if (r8 == 0) goto L79
            java.lang.String r1 = "items"
            com.fasterxml.jackson.databind.ObjectMapper r3 = _getObjectMapper()     // Catch: java.io.IOException -> L72
            com.fasterxml.jackson.databind.JsonNode r8 = r3.readTree(r8)     // Catch: java.io.IOException -> L72
            r0.set(r1, r8)     // Catch: java.io.IOException -> L72
            goto L79
        L72:
            java.lang.Object[] r8 = new java.lang.Object[r2]
            java.lang.String r1 = "Failed to parse @JsonSerializableSchema.schemaItemDefinition value"
            r7.reportMappingProblem(r1, r8)
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.SerializableSerializer.getSchema(com.fasterxml.jackson.databind.SerializerProvider, java.lang.reflect.Type):com.fasterxml.jackson.databind.JsonNode");
    }

    private static final synchronized ObjectMapper _getObjectMapper() {
        ObjectMapper objectMapper;
        synchronized (SerializableSerializer.class) {
            AtomicReference<ObjectMapper> atomicReference = _mapperReference;
            objectMapper = atomicReference.get();
            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
                atomicReference.set(objectMapper);
            }
        }
        return objectMapper;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }
}
