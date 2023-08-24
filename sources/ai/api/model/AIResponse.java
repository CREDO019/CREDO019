package ai.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

/* loaded from: classes.dex */
public class AIResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("id")

    /* renamed from: id */
    private String f1id;
    @SerializedName("lang")
    private String lang;
    @SerializedName("result")
    private Result result;
    @SerializedName("sessionId")
    private String sessionId;
    @SerializedName("status")
    private Status status;
    @SerializedName("timestamp")
    private Date timestamp;

    public String getId() {
        return this.f1id;
    }

    public void setId(String str) {
        this.f1id = str;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public boolean isError() {
        Status status = this.status;
        return (status == null || status.getCode() == null || this.status.getCode().intValue() < 400) ? false : true;
    }

    public String toString() {
        return String.format("AIResponse{id='%s', timestamp=%s, result=%s, status=%s, sessionId=%s}", this.f1id, this.timestamp, this.result, this.status, this.sessionId);
    }

    public void cleanup() {
        Result result = this.result;
        if (result != null) {
            result.trimParameters();
        }
    }
}
