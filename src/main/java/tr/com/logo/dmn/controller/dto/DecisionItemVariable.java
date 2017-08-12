package tr.com.logo.dmn.controller.dto;

/**
 * Created by Samet Sevim on 26.5.2016.
 */
public class DecisionItemVariable {

    private String name;
    private String id;
    private String type;
    private Object value;

    public DecisionItemVariable() {
    }


    public DecisionItemVariable(String name, String id, String type, Object value) {

        this.name = name;
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
