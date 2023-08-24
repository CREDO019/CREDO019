package com.google.android.exoplayer2.source.rtsp;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.rtsp.RtspMessageUtil;
import com.google.android.exoplayer2.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class RtspAuthenticationInfo {
    private static final String ALGORITHM = "MD5";
    public static final int BASIC = 1;
    private static final String BASIC_AUTHORIZATION_HEADER_FORMAT = "Basic %s";
    public static final int DIGEST = 2;
    private static final String DIGEST_AUTHORIZATION_HEADER_FORMAT = "Digest username=\"%s\", realm=\"%s\", nonce=\"%s\", uri=\"%s\", response=\"%s\"";
    private static final String DIGEST_AUTHORIZATION_HEADER_FORMAT_WITH_OPAQUE = "Digest username=\"%s\", realm=\"%s\", nonce=\"%s\", uri=\"%s\", response=\"%s\", opaque=\"%s\"";
    public final int authenticationMechanism;
    public final String nonce;
    public final String opaque;
    public final String realm;

    public RtspAuthenticationInfo(int r1, String str, String str2, String str3) {
        this.authenticationMechanism = r1;
        this.realm = str;
        this.nonce = str2;
        this.opaque = str3;
    }

    public String getAuthorizationHeaderValue(RtspMessageUtil.RtspAuthUserInfo rtspAuthUserInfo, Uri uri, int r5) throws ParserException {
        int r0 = this.authenticationMechanism;
        if (r0 != 1) {
            if (r0 == 2) {
                return getDigestAuthorizationHeaderValue(rtspAuthUserInfo, uri, r5);
            }
            throw ParserException.createForManifestWithUnsupportedFeature(null, new UnsupportedOperationException());
        }
        return getBasicAuthorizationHeaderValue(rtspAuthUserInfo);
    }

    private String getBasicAuthorizationHeaderValue(RtspMessageUtil.RtspAuthUserInfo rtspAuthUserInfo) {
        return Util.formatInvariant(BASIC_AUTHORIZATION_HEADER_FORMAT, Base64.encodeToString(RtspMessageUtil.getStringBytes(rtspAuthUserInfo.username + ParameterizedMessage.ERROR_MSG_SEPARATOR + rtspAuthUserInfo.password), 0));
    }

    private String getDigestAuthorizationHeaderValue(RtspMessageUtil.RtspAuthUserInfo rtspAuthUserInfo, Uri uri, int r11) throws ParserException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String methodString = RtspMessageUtil.toMethodString(r11);
            String hexString = Util.toHexString(messageDigest.digest(RtspMessageUtil.getStringBytes(rtspAuthUserInfo.username + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.realm + ParameterizedMessage.ERROR_MSG_SEPARATOR + rtspAuthUserInfo.password)));
            StringBuilder sb = new StringBuilder();
            sb.append(methodString);
            sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
            sb.append(uri);
            String hexString2 = Util.toHexString(messageDigest.digest(RtspMessageUtil.getStringBytes(sb.toString())));
            String hexString3 = Util.toHexString(messageDigest.digest(RtspMessageUtil.getStringBytes(hexString + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.nonce + ParameterizedMessage.ERROR_MSG_SEPARATOR + hexString2)));
            return this.opaque.isEmpty() ? Util.formatInvariant(DIGEST_AUTHORIZATION_HEADER_FORMAT, rtspAuthUserInfo.username, this.realm, this.nonce, uri, hexString3) : Util.formatInvariant(DIGEST_AUTHORIZATION_HEADER_FORMAT_WITH_OPAQUE, rtspAuthUserInfo.username, this.realm, this.nonce, uri, hexString3, this.opaque);
        } catch (NoSuchAlgorithmException e) {
            throw ParserException.createForManifestWithUnsupportedFeature(null, e);
        }
    }
}
