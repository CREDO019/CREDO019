package com.zaguiini.RNPureJwt;

import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class RNPureJwtModule extends ReactContextBaseJavaModule {
    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNPureJwt";
    }

    public RNPureJwtModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String toBase64(String str) {
        return Base64.encodeToString(str.getBytes(Charset.forName("UTF-8")), 0);
    }

    private String base64toString(String str) {
        return new String(Base64.decode(str, 0));
    }

    private void getResponse(String str, Promise promise) {
        ObjectMapper objectMapper = new ObjectMapper();
        WritableMap createMap = Arguments.createMap();
        String[] split = str.split(Pattern.quote("."));
        try {
            createMap.putMap("headers", Arguments.makeNativeMap((Map) objectMapper.readValue(base64toString(split[0]), new TypeReference<Map<String, Object>>() { // from class: com.zaguiini.RNPureJwt.RNPureJwtModule.1
            })));
        } catch (IOException unused) {
            promise.reject("7", "Invalid header");
        }
        try {
            createMap.putMap("payload", Arguments.makeNativeMap((Map) objectMapper.readValue(base64toString(split[1]), new TypeReference<Map<String, Object>>() { // from class: com.zaguiini.RNPureJwt.RNPureJwtModule.2
            })));
        } catch (IOException unused2) {
            promise.reject("8", "Invalid payload");
        }
        promise.resolve(createMap);
    }

    private void getResponse(Jwt jwt, Promise promise) {
        ObjectMapper objectMapper = new ObjectMapper();
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("headers", Arguments.makeNativeMap((Map) objectMapper.convertValue(jwt.getHeader(), DefaultClaims.class)));
        createMap.putMap("payload", Arguments.makeNativeMap((Map) objectMapper.convertValue(jwt.getBody(), DefaultClaims.class)));
        promise.resolve(createMap);
    }

    @ReactMethod
    public void decode(String str, String str2, ReadableMap readableMap, Promise promise) {
        JwtParser signingKey = Jwts.parser().setSigningKey(toBase64(str2));
        Boolean bool = false;
        for (Map.Entry<String, Object> entry : readableMap.toHashMap().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            if (key.equals("skipValidation")) {
                bool = Boolean.valueOf(((Boolean) value).booleanValue());
            }
        }
        try {
            getResponse(signingKey.parse(str), promise);
        } catch (ExpiredJwtException unused) {
            if (bool.booleanValue()) {
                getResponse(str, promise);
            } else {
                promise.reject(ExifInterface.GPS_MEASUREMENT_3D, "The JWT is expired.");
            }
        } catch (MalformedJwtException unused2) {
            if (bool.booleanValue()) {
                getResponse(str, promise);
            } else {
                promise.reject("2", "The JWT is invalid.");
            }
        } catch (SignatureException unused3) {
            if (bool.booleanValue()) {
                getResponse(str, promise);
            } else {
                promise.reject("6", "Invalid signature.");
            }
        } catch (Exception e) {
            promise.reject(SessionDescription.SUPPORTED_SDP_VERSION, e);
        }
    }

    @ReactMethod
    public void sign(ReadableMap readableMap, String str, ReadableMap readableMap2, Promise promise) {
        String string = readableMap2.hasKey("alg") ? readableMap2.getString("alg") : "HS256";
        JwtBuilder headerParam = Jwts.builder().signWith(SignatureAlgorithm.forName(string), toBase64(str)).setHeaderParam("alg", string).setHeaderParam(Header.TYPE, Header.JWT_TYPE);
        for (Map.Entry<String, Object> entry : readableMap.toHashMap().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            char c = 65535;
            switch (key.hashCode()) {
                case 96668:
                    if (key.equals("alg")) {
                        c = 0;
                        break;
                    }
                    break;
                case 96944:
                    if (key.equals(Claims.AUDIENCE)) {
                        c = 1;
                        break;
                    }
                    break;
                case 100893:
                    if (key.equals(Claims.EXPIRATION)) {
                        c = 2;
                        break;
                    }
                    break;
                case 104028:
                    if (key.equals(Claims.ISSUED_AT)) {
                        c = 3;
                        break;
                    }
                    break;
                case 104585:
                    if (key.equals(Claims.ISSUER)) {
                        c = 4;
                        break;
                    }
                    break;
                case 105567:
                    if (key.equals(Claims.f1486ID)) {
                        c = 5;
                        break;
                    }
                    break;
                case 108850:
                    if (key.equals(Claims.NOT_BEFORE)) {
                        c = 6;
                        break;
                    }
                    break;
                case 114240:
                    if (key.equals(Claims.SUBJECT)) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    break;
                case 1:
                    headerParam.setAudience(value.toString());
                    break;
                case 2:
                    headerParam.setExpiration(new Date(Double.valueOf(((Double) value).doubleValue()).longValue()));
                    break;
                case 3:
                    headerParam.setIssuedAt(new Date(Double.valueOf(((Double) value).doubleValue()).longValue()));
                    break;
                case 4:
                    headerParam.setIssuer(value.toString());
                    break;
                case 5:
                    headerParam.setId(value.toString());
                    break;
                case 6:
                    headerParam.setNotBefore(new Date(Double.valueOf(((Double) value).doubleValue()).longValue()));
                    break;
                case 7:
                    headerParam.setSubject(value.toString());
                    break;
                default:
                    headerParam.claim(key, value);
                    break;
            }
        }
        promise.resolve(headerParam.compact());
    }
}
