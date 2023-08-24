package com.polidea.multiplatformbleadapter.errors;

import com.canhub.cropper.CropImage;
import com.google.firebase.messaging.ServiceStarter;

/* loaded from: classes3.dex */
public enum BleErrorCode {
    UnknownError(0),
    BluetoothManagerDestroyed(1),
    OperationCancelled(2),
    OperationTimedOut(3),
    OperationStartFailed(4),
    InvalidIdentifiers(5),
    BluetoothUnsupported(100),
    BluetoothUnauthorized(101),
    BluetoothPoweredOff(102),
    BluetoothInUnknownState(103),
    BluetoothResetting(104),
    BluetoothStateChangeFailed(105),
    DeviceConnectionFailed(200),
    DeviceDisconnected(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE),
    DeviceRSSIReadFailed(202),
    DeviceAlreadyConnected(CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE),
    DeviceNotFound(CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE),
    DeviceNotConnected(205),
    DeviceMTUChangeFailed(206),
    ServicesDiscoveryFailed(300),
    IncludedServicesDiscoveryFailed(301),
    ServiceNotFound(302),
    ServicesNotDiscovered(303),
    CharacteristicsDiscoveryFailed(400),
    CharacteristicWriteFailed(401),
    CharacteristicReadFailed(402),
    CharacteristicNotifyChangeFailed(403),
    CharacteristicNotFound(404),
    CharacteristicsNotDiscovered(405),
    CharacteristicInvalidDataFormat(406),
    DescriptorsDiscoveryFailed(ServiceStarter.ERROR_UNKNOWN),
    DescriptorWriteFailed(501),
    DescriptorReadFailed(502),
    DescriptorNotFound(503),
    DescriptorsNotDiscovered(504),
    DescriptorInvalidDataFormat(505),
    DescriptorWriteNotAllowed(506),
    ScanStartFailed(600),
    LocationServicesDisabled(601);
    
    public final int code;

    BleErrorCode(int r3) {
        this.code = r3;
    }
}
