package org.bouncycastle.asn1.x509;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

/* loaded from: classes5.dex */
public class KeyPurposeId extends ASN1Object {
    public static final KeyPurposeId anyExtendedKeyUsage;
    private static final ASN1ObjectIdentifier id_kp;
    public static final KeyPurposeId id_kp_OCSPSigning;
    public static final KeyPurposeId id_kp_capwapAC;
    public static final KeyPurposeId id_kp_capwapWTP;
    public static final KeyPurposeId id_kp_clientAuth;
    public static final KeyPurposeId id_kp_codeSigning;
    public static final KeyPurposeId id_kp_dvcs;
    public static final KeyPurposeId id_kp_eapOverLAN;
    public static final KeyPurposeId id_kp_eapOverPPP;
    public static final KeyPurposeId id_kp_emailProtection;
    public static final KeyPurposeId id_kp_ipsecEndSystem;
    public static final KeyPurposeId id_kp_ipsecIKE;
    public static final KeyPurposeId id_kp_ipsecTunnel;
    public static final KeyPurposeId id_kp_ipsecUser;
    public static final KeyPurposeId id_kp_macAddress;
    public static final KeyPurposeId id_kp_msSGC;
    public static final KeyPurposeId id_kp_nsSGC;
    public static final KeyPurposeId id_kp_sbgpCertAAServerAuth;
    public static final KeyPurposeId id_kp_scvpClient;
    public static final KeyPurposeId id_kp_scvpServer;
    public static final KeyPurposeId id_kp_scvp_responder;
    public static final KeyPurposeId id_kp_serverAuth;
    public static final KeyPurposeId id_kp_smartcardlogon;
    public static final KeyPurposeId id_kp_timeStamping;

    /* renamed from: id */
    private ASN1ObjectIdentifier f1655id;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");
        id_kp = aSN1ObjectIdentifier;
        anyExtendedKeyUsage = new KeyPurposeId(Extension.extendedKeyUsage.branch(SessionDescription.SUPPORTED_SDP_VERSION));
        id_kp_serverAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE));
        id_kp_clientAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch("2"));
        id_kp_codeSigning = new KeyPurposeId(aSN1ObjectIdentifier.branch(ExifInterface.GPS_MEASUREMENT_3D));
        id_kp_emailProtection = new KeyPurposeId(aSN1ObjectIdentifier.branch("4"));
        id_kp_ipsecEndSystem = new KeyPurposeId(aSN1ObjectIdentifier.branch("5"));
        id_kp_ipsecTunnel = new KeyPurposeId(aSN1ObjectIdentifier.branch("6"));
        id_kp_ipsecUser = new KeyPurposeId(aSN1ObjectIdentifier.branch("7"));
        id_kp_timeStamping = new KeyPurposeId(aSN1ObjectIdentifier.branch("8"));
        id_kp_OCSPSigning = new KeyPurposeId(aSN1ObjectIdentifier.branch("9"));
        id_kp_dvcs = new KeyPurposeId(aSN1ObjectIdentifier.branch("10"));
        id_kp_sbgpCertAAServerAuth = new KeyPurposeId(aSN1ObjectIdentifier.branch("11"));
        id_kp_scvp_responder = new KeyPurposeId(aSN1ObjectIdentifier.branch("12"));
        id_kp_eapOverPPP = new KeyPurposeId(aSN1ObjectIdentifier.branch("13"));
        id_kp_eapOverLAN = new KeyPurposeId(aSN1ObjectIdentifier.branch("14"));
        id_kp_scvpServer = new KeyPurposeId(aSN1ObjectIdentifier.branch("15"));
        id_kp_scvpClient = new KeyPurposeId(aSN1ObjectIdentifier.branch("16"));
        id_kp_ipsecIKE = new KeyPurposeId(aSN1ObjectIdentifier.branch("17"));
        id_kp_capwapAC = new KeyPurposeId(aSN1ObjectIdentifier.branch("18"));
        id_kp_capwapWTP = new KeyPurposeId(aSN1ObjectIdentifier.branch("19"));
        id_kp_smartcardlogon = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.20.2.2"));
        id_kp_macAddress = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.1.1.1.22"));
        id_kp_msSGC = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.10.3.3"));
        id_kp_nsSGC = new KeyPurposeId(new ASN1ObjectIdentifier("2.16.840.1.113730.4.1"));
    }

    private KeyPurposeId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f1655id = aSN1ObjectIdentifier;
    }

    public static KeyPurposeId getInstance(Object obj) {
        if (obj instanceof KeyPurposeId) {
            return (KeyPurposeId) obj;
        }
        if (obj != null) {
            return new KeyPurposeId(ASN1ObjectIdentifier.getInstance(obj));
        }
        return null;
    }

    public String getId() {
        return this.f1655id.getId();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f1655id;
    }

    public ASN1ObjectIdentifier toOID() {
        return this.f1655id;
    }

    public String toString() {
        return this.f1655id.toString();
    }
}