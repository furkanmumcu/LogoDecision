package tr.com.logo.dmn.model;

/**
 * Tenant Entity to make the system multi-tenant
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by umitvardar on 29.04.2016.
 */
@Document
public class Tenant {
    @Id
    private String id;
    private String title;
    private String officialName;

    public Tenant(String title, String officialName) {
        this.title = title;
        this.officialName = officialName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tenant tenant = (Tenant) o;

        if (id != null ? !id.equals(tenant.id) : tenant.id != null) return false;
        if (title != null ? !title.equals(tenant.title) : tenant.title != null) return false;
        return officialName != null ? officialName.equals(tenant.officialName) : tenant.officialName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (officialName != null ? officialName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", officialName='" + officialName + '\'' +
                '}';
    }
}
