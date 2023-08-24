package com.google.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes3.dex */
final class DefaultDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat enUsFormat;
    private final DateFormat iso8601Format;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultDateTypeAdapter(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    DefaultDateTypeAdapter(int r2) {
        this(DateFormat.getDateInstance(r2, Locale.US), DateFormat.getDateInstance(r2));
    }

    public DefaultDateTypeAdapter(int r2, int r3) {
        this(DateFormat.getDateTimeInstance(r2, r3, Locale.US), DateFormat.getDateTimeInstance(r2, r3));
    }

    DefaultDateTypeAdapter(DateFormat dateFormat, DateFormat dateFormat2) {
        this.enUsFormat = dateFormat;
        this.localFormat = dateFormat2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.iso8601Format = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonPrimitive jsonPrimitive;
        synchronized (this.localFormat) {
            jsonPrimitive = new JsonPrimitive(this.enUsFormat.format(date));
        }
        return jsonPrimitive;
    }

    @Override // com.google.gson.JsonDeserializer
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (!(jsonElement instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Date deserializeToDate = deserializeToDate(jsonElement);
        if (type == Date.class) {
            return deserializeToDate;
        }
        if (type == Timestamp.class) {
            return new Timestamp(deserializeToDate.getTime());
        }
        if (type == java.sql.Date.class) {
            return new java.sql.Date(deserializeToDate.getTime());
        }
        throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
    }

    private Date deserializeToDate(JsonElement jsonElement) {
        Date parse;
        synchronized (this.localFormat) {
            try {
                try {
                    try {
                        parse = this.localFormat.parse(jsonElement.getAsString());
                    } catch (ParseException unused) {
                        return this.iso8601Format.parse(jsonElement.getAsString());
                    }
                } catch (ParseException e) {
                    throw new JsonSyntaxException(jsonElement.getAsString(), e);
                }
            } catch (ParseException unused2) {
                return this.enUsFormat.parse(jsonElement.getAsString());
            }
        }
        return parse;
    }

    public String toString() {
        return "DefaultDateTypeAdapter(" + this.localFormat.getClass().getSimpleName() + ')';
    }
}