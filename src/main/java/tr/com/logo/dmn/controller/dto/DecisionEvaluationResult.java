package tr.com.logo.dmn.controller.dto;

import java.util.List;

/**
 * Created by User on 26.5.2016.
 */
public class DecisionEvaluationResult {

    private List<DecisionItemVariable> results;

    public DecisionEvaluationResult() {
    }

    public DecisionEvaluationResult(List<DecisionItemVariable> results) {

        this.results = results;
    }

    public List<DecisionItemVariable> getResults() {
        return results;
    }

    public void setResults(List<DecisionItemVariable> results) {
        this.results = results;
    }
}
