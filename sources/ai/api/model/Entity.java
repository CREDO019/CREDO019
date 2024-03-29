package ai.api.model;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Entity implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("entries")
    private List<EntityEntry> entries;
    @SerializedName(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND)
    private Boolean extend;
    @SerializedName("isEnum")
    private Boolean isEnum;
    @SerializedName("name")
    private String name;

    public Entity() {
    }

    public Entity(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<EntityEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<EntityEntry> list) {
        this.entries = list;
    }

    public void addEntry(EntityEntry entityEntry) {
        if (this.entries == null) {
            this.entries = new ArrayList();
        }
        this.entries.add(entityEntry);
    }

    public Boolean getExtend() {
        return this.extend;
    }

    public void setExtend(boolean z) {
        this.extend = Boolean.valueOf(z);
    }

    public Boolean isEnum() {
        return this.isEnum;
    }

    public void setIsEnum(Boolean bool) {
        this.isEnum = bool;
    }
}
