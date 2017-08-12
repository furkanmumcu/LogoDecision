package tr.com.logo.dmn.controller.dto;

import java.util.List;

/**
 * Created by Samet Sevim on 26.5.2016.
 */
public class DecisionTableDescription {

    private String decisionTableName;
    private int version;
    private List<DecisionItemVariable> variables;

    public DecisionTableDescription() {
    }

    public DecisionTableDescription(String decisionTableName, int version, List<DecisionItemVariable> variables) {
        this.decisionTableName = decisionTableName;
        this.version = version;
        this.variables = variables;
    }

    public String getDecisionTableName() {
        return decisionTableName;
    }

    public void setDecisionTableName(String decisionTableName) {
        this.decisionTableName = decisionTableName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<DecisionItemVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<DecisionItemVariable> variables) {
        this.variables = variables;
    }
}
