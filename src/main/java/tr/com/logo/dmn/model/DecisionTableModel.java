package tr.com.logo.dmn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Holds DecisionTableModel and Tenant's infos
 */

/**
 * Created by umitvardar on 29.04.2016.
 */
@Document
public class DecisionTableModel {
    @Id
    private String id;
    private String tenantId;
    private byte[] decisionModel;
    private int version;
    private Date timeStamp;
    private String caption;


    public DecisionTableModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public byte[] getDecisionModel() {
        return decisionModel;
    }

    public void setDecisionModel(byte[] decisionModel) {
        this.decisionModel = decisionModel;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecisionTableModel that = (DecisionTableModel) o;

        if (version != that.version) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tenantId != null ? !tenantId.equals(that.tenantId) : that.tenantId != null) return false;
        if (decisionModel != null ? !decisionModel.equals(that.decisionModel) : that.decisionModel != null)
            return false;
        return timeStamp != null ? timeStamp.equals(that.timeStamp) : that.timeStamp == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (decisionModel != null ? decisionModel.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DecisionTableModel{" +
                "id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", decisionModel='" + decisionModel + '\'' +
                ", version=" + version +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
