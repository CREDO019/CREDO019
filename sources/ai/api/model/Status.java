package ai.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Status implements Serializable {
    private static final Map<Integer, String> errorDescriptions = new HashMap();
    private static final Map<Integer, String> errorTypes = new HashMap();
    private static final long serialVersionUID = 1;
    @SerializedName("code")
    private Integer code;
    @SerializedName("errorDetails")
    private String errorDetails;
    @SerializedName("errorID")
    private String errorID;
    @SerializedName("errorType")
    private String errorType;

    public Status() {
        Map<Integer, String> map = errorDescriptions;
        map.put(400, "Some required parameter is missing or has wrong value. Details will be in the errorDetails field.");
        Map<Integer, String> map2 = errorTypes;
        map2.put(400, "bad_request");
        map.put(401, "Authorization failed. Please check your access keys.");
        map2.put(401, "unauthorized");
        map.put(404, "Uri is not found or some resource with provided id is not found.");
        map2.put(404, "not_found");
        map.put(405, "Attempting to use POST with a GET-only endpoint, or vice-versa.");
        map2.put(405, "not_allowed");
        map.put(406, "Uploaded files have some problems with it.");
        map2.put(406, "not_acceptable");
        map.put(409, "The request could not be completed due to a conflict with the current state of the resource. This code is only allowed in situations where it is expected that the user might be able to resolve the conflict and resubmit the request.");
        map2.put(409, "conflict");
    }

    public static Status fromResponseCode(int r3) {
        Status status = new Status();
        status.setCode(Integer.valueOf(r3));
        Map<Integer, String> map = errorTypes;
        if (map.containsKey(Integer.valueOf(r3))) {
            status.setErrorType(map.get(Integer.valueOf(r3)));
        }
        Map<Integer, String> map2 = errorDescriptions;
        if (map2.containsKey(Integer.valueOf(r3))) {
            status.setErrorDetails(map2.get(Integer.valueOf(r3)));
        }
        return status;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(String str) {
        this.errorType = str;
    }

    public String getErrorDetails() {
        Integer num = this.code;
        if (num != null) {
            Map<Integer, String> map = errorDescriptions;
            if (map.containsKey(num)) {
                return map.get(this.code);
            }
        }
        return this.errorDetails;
    }

    public void setErrorDetails(String str) {
        this.errorDetails = str;
    }

    public String getErrorID() {
        return this.errorID;
    }

    public void setErrorID(String str) {
        this.errorID = str;
    }

    public String toString() {
        return String.format("Status{code=%d, errorType='%s', errorDetails='%s'}", this.code, this.errorType, this.errorDetails);
    }
}
