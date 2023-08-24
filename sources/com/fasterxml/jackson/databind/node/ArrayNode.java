package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ArrayNode extends ContainerNode<ArrayNode> {
    private final List<JsonNode> _children;

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode get(String str) {
        return null;
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new ArrayList();
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, int r2) {
        super(jsonNodeFactory);
        this._children = new ArrayList(r2);
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, List<JsonNode> list) {
        super(jsonNodeFactory);
        this._children = list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    protected JsonNode _at(JsonPointer jsonPointer) {
        return get(jsonPointer.getMatchingIndex());
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ArrayNode deepCopy() {
        ArrayNode arrayNode = new ArrayNode(this._nodeFactory);
        for (JsonNode jsonNode : this._children) {
            arrayNode._children.add(jsonNode.deepCopy());
        }
        return arrayNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable.Base
    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public int size() {
        return this._children.size();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode get(int r2) {
        if (r2 < 0 || r2 >= this._children.size()) {
            return null;
        }
        return this._children.get(r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode path(int r2) {
        if (r2 >= 0 && r2 < this._children.size()) {
            return this._children.get(r2);
        }
        return MissingNode.getInstance();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Comparator<JsonNode> comparator, JsonNode jsonNode) {
        if (jsonNode instanceof ArrayNode) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            int size = this._children.size();
            if (arrayNode.size() != size) {
                return false;
            }
            List<JsonNode> list = this._children;
            List<JsonNode> list2 = arrayNode._children;
            for (int r3 = 0; r3 < size; r3++) {
                if (!list.get(r3).equals(comparator, list2.get(r3))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<JsonNode> list = this._children;
        int size = list.size();
        jsonGenerator.writeStartArray(size);
        for (int r2 = 0; r2 < size; r2++) {
            JsonNode jsonNode = list.get(r2);
            if (jsonNode instanceof BaseJsonNode) {
                ((BaseJsonNode) jsonNode).serialize(jsonGenerator, serializerProvider);
            } else {
                jsonNode.serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForArray(this, jsonGenerator);
        Iterator<JsonNode> it = this._children.iterator();
        while (it.hasNext()) {
            ((BaseJsonNode) it.next()).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(this, jsonGenerator);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode findValue(String str) {
        for (JsonNode jsonNode : this._children) {
            JsonNode findValue = jsonNode.findValue(str);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findValues(str, list);
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findValuesAsText(str, list);
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ObjectNode findParent(String str) {
        for (JsonNode jsonNode : this._children) {
            JsonNode findParent = jsonNode.findParent(str);
            if (findParent != null) {
                return (ObjectNode) findParent;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (JsonNode jsonNode : this._children) {
            list = jsonNode.findParents(str, list);
        }
        return list;
    }

    public JsonNode set(int r3, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        if (r3 < 0 || r3 >= this._children.size()) {
            throw new IndexOutOfBoundsException("Illegal index " + r3 + ", array size " + size());
        }
        return this._children.set(r3, jsonNode);
    }

    public ArrayNode add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
        return this;
    }

    public ArrayNode addAll(ArrayNode arrayNode) {
        this._children.addAll(arrayNode._children);
        return this;
    }

    public ArrayNode addAll(Collection<? extends JsonNode> collection) {
        this._children.addAll(collection);
        return this;
    }

    public ArrayNode insert(int r1, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _insert(r1, jsonNode);
        return this;
    }

    public JsonNode remove(int r2) {
        if (r2 < 0 || r2 >= this._children.size()) {
            return null;
        }
        return this._children.remove(r2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.node.ContainerNode
    public ArrayNode removeAll() {
        this._children.clear();
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    public ObjectNode addObject() {
        ObjectNode objectNode = objectNode();
        _add(objectNode);
        return objectNode;
    }

    public ArrayNode addPOJO(Object obj) {
        if (obj == null) {
            addNull();
        } else {
            _add(pojoNode(obj));
        }
        return this;
    }

    public ArrayNode addRawValue(RawValue rawValue) {
        if (rawValue == null) {
            addNull();
        } else {
            _add(rawValueNode(rawValue));
        }
        return this;
    }

    public ArrayNode addNull() {
        _add(nullNode());
        return this;
    }

    public ArrayNode add(int r1) {
        _add(numberNode(r1));
        return this;
    }

    public ArrayNode add(Integer num) {
        if (num == null) {
            return addNull();
        }
        return _add(numberNode(num.intValue()));
    }

    public ArrayNode add(long j) {
        return _add(numberNode(j));
    }

    public ArrayNode add(Long l) {
        if (l == null) {
            return addNull();
        }
        return _add(numberNode(l.longValue()));
    }

    public ArrayNode add(float f) {
        return _add(numberNode(f));
    }

    public ArrayNode add(Float f) {
        if (f == null) {
            return addNull();
        }
        return _add(numberNode(f.floatValue()));
    }

    public ArrayNode add(double d) {
        return _add(numberNode(d));
    }

    public ArrayNode add(Double d) {
        if (d == null) {
            return addNull();
        }
        return _add(numberNode(d.doubleValue()));
    }

    public ArrayNode add(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return addNull();
        }
        return _add(numberNode(bigDecimal));
    }

    public ArrayNode add(String str) {
        if (str == null) {
            return addNull();
        }
        return _add(textNode(str));
    }

    public ArrayNode add(boolean z) {
        return _add(booleanNode(z));
    }

    public ArrayNode add(Boolean bool) {
        if (bool == null) {
            return addNull();
        }
        return _add(booleanNode(bool.booleanValue()));
    }

    public ArrayNode add(byte[] bArr) {
        if (bArr == null) {
            return addNull();
        }
        return _add(binaryNode(bArr));
    }

    public ArrayNode insertArray(int r2) {
        ArrayNode arrayNode = arrayNode();
        _insert(r2, arrayNode);
        return arrayNode;
    }

    public ObjectNode insertObject(int r2) {
        ObjectNode objectNode = objectNode();
        _insert(r2, objectNode);
        return objectNode;
    }

    public ArrayNode insertPOJO(int r1, Object obj) {
        if (obj == null) {
            return insertNull(r1);
        }
        return _insert(r1, pojoNode(obj));
    }

    public ArrayNode insertNull(int r2) {
        _insert(r2, nullNode());
        return this;
    }

    public ArrayNode insert(int r1, int r2) {
        _insert(r1, numberNode(r2));
        return this;
    }

    public ArrayNode insert(int r1, Integer num) {
        if (num == null) {
            insertNull(r1);
        } else {
            _insert(r1, numberNode(num.intValue()));
        }
        return this;
    }

    public ArrayNode insert(int r1, long j) {
        return _insert(r1, numberNode(j));
    }

    public ArrayNode insert(int r3, Long l) {
        if (l == null) {
            return insertNull(r3);
        }
        return _insert(r3, numberNode(l.longValue()));
    }

    public ArrayNode insert(int r1, float f) {
        return _insert(r1, numberNode(f));
    }

    public ArrayNode insert(int r1, Float f) {
        if (f == null) {
            return insertNull(r1);
        }
        return _insert(r1, numberNode(f.floatValue()));
    }

    public ArrayNode insert(int r1, double d) {
        return _insert(r1, numberNode(d));
    }

    public ArrayNode insert(int r3, Double d) {
        if (d == null) {
            return insertNull(r3);
        }
        return _insert(r3, numberNode(d.doubleValue()));
    }

    public ArrayNode insert(int r1, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return insertNull(r1);
        }
        return _insert(r1, numberNode(bigDecimal));
    }

    public ArrayNode insert(int r1, String str) {
        if (str == null) {
            return insertNull(r1);
        }
        return _insert(r1, textNode(str));
    }

    public ArrayNode insert(int r1, boolean z) {
        return _insert(r1, booleanNode(z));
    }

    public ArrayNode insert(int r1, Boolean bool) {
        if (bool == null) {
            return insertNull(r1);
        }
        return _insert(r1, booleanNode(bool.booleanValue()));
    }

    public ArrayNode insert(int r1, byte[] bArr) {
        if (bArr == null) {
            return insertNull(r1);
        }
        return _insert(r1, binaryNode(bArr));
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ArrayNode)) {
            return this._children.equals(((ArrayNode) obj)._children);
        }
        return false;
    }

    protected boolean _childrenEqual(ArrayNode arrayNode) {
        return this._children.equals(arrayNode._children);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode
    public int hashCode() {
        return this._children.hashCode();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String toString() {
        StringBuilder sb = new StringBuilder((size() << 4) + 16);
        sb.append('[');
        int size = this._children.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (r2 > 0) {
                sb.append(',');
            }
            sb.append(this._children.get(r2).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    protected ArrayNode _add(JsonNode jsonNode) {
        this._children.add(jsonNode);
        return this;
    }

    protected ArrayNode _insert(int r2, JsonNode jsonNode) {
        if (r2 < 0) {
            this._children.add(0, jsonNode);
        } else if (r2 >= this._children.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(r2, jsonNode);
        }
        return this;
    }
}
