package tr.com.logo.dmn.controller;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableInputImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.logo.dmn.controller.dto.DecisionEvaluationResult;
import tr.com.logo.dmn.controller.dto.DecisionItemVariable;
import tr.com.logo.dmn.controller.dto.DecisionTableDescription;
import tr.com.logo.dmn.model.DecisionTableModel;
import tr.com.logo.dmn.service.DecisionTableService;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by umitvardar-Samet Sevim on 29.04.2016.
 */
@RestController
@RequestMapping(value = "/dmn/v1", produces = {"application/json"})
public class DecisionModelController {

    @Autowired
    private DecisionTableService decisionTableService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    private String testConfiguration(HttpRequest httpRequest){
        return "Configuration works";
    }

    @RequestMapping(path= "/decisionTable", method= RequestMethod.POST)
    private ResponseEntity evaluateDecisionTable(@RequestBody DecisionTableDescription decisionTableDescription, HttpRequest httpRequest){
        try{
            DecisionTableModel decisionTableModel = decisionTableService.findDecisionTable(decisionTableDescription.getDecisionTableName(), decisionTableDescription.getVersion());
            DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
            ByteArrayInputStream bais = new ByteArrayInputStream(decisionTableModel.getDecisionModel());
            for(DmnDecision decision : dmnEngine.parseDecisions(bais)){
                Map<String, Object> data = new HashMap<String, Object>();
//                data.put("Season","Spring");
//                data.put("How many guests",5);
                for(DecisionItemVariable variable : decisionTableDescription.getVariables()){
                    data.put(variable.getId(),variable.getValue());
                }
                DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, data);
                return ResponseEntity.ok(buildDmnEvaluation(result));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No decision found");

        }
        catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/decisionTable/{caption}/{version}", method = RequestMethod.GET)
    private ResponseEntity getDecisionTable(@PathVariable String caption, @PathVariable String version, HttpRequest httpRequest){
        try{
            decisionTableService.findDecisionTable(caption,Integer.parseInt(version));
            return ResponseEntity.ok(createDTO(decisionTableService.findDecisionTable(caption,Integer.parseInt(version))));
        }
        catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/decisionTable", method = RequestMethod.GET)
    private ResponseEntity getDecisionTables(HttpRequest httpRequest){
        try{
            List<DecisionTableModel> list = decisionTableService.findAllDecisionTables();
            List<DecisionTableDescription> dtoList = new ArrayList<>();
            for(DecisionTableModel decisionTableModel : list){
                dtoList.add(createDTO(decisionTableModel));
            }
            return ResponseEntity.ok(dtoList);
        }
        catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    private DecisionTableDescription createDTO(DecisionTableModel decisionTableModel){

        DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();

        DecisionTableDescription decisionTableDescription = new DecisionTableDescription();
        ByteArrayInputStream bais = new ByteArrayInputStream(decisionTableModel.getDecisionModel());
        decisionTableDescription.setVersion(decisionTableModel.getVersion());
        decisionTableDescription.setDecisionTableName(decisionTableModel.getCaption());
        List<DecisionItemVariable> variables = new ArrayList<>();
        List<DmnDecision> decisions = dmnEngine.parseDecisions(bais);
        for(DmnDecision decision : decisions){
            if(decision instanceof DmnDecisionTableImpl) {
                DmnDecisionTableImpl decisionTable = (DmnDecisionTableImpl) decision;
                for (DmnDecisionTableInputImpl input : decisionTable.getInputs()) {
                    DecisionItemVariable decisionItemVariable = new DecisionItemVariable();
                    decisionItemVariable.setName(input.getName());
                    decisionItemVariable.setId(input.getId());
                    decisionItemVariable.setType(input.getExpression().getTypeDefinition().getTypeName());
                    decisionItemVariable.setValue("");
                    variables.add(decisionItemVariable);
                }
            }
        }
        decisionTableDescription.setVariables(variables);
        return decisionTableDescription;
    }

    public List<DecisionEvaluationResult> buildDmnEvaluation(DmnDecisionTableResult dmnDecisionRuleResult){
        List<DecisionEvaluationResult> results  = new ArrayList<>();
        for(Map<String,Object> resultItem : dmnDecisionRuleResult.getResultList()){
            DecisionEvaluationResult decisionEvaluationResult = new DecisionEvaluationResult();
            List<DecisionItemVariable> resultItems  = new ArrayList<>();
            for(Map.Entry<String,Object> entry : resultItem.entrySet()){
                resultItems.add(new DecisionItemVariable(null,entry.getKey(),null,entry.getValue()));
            }
            decisionEvaluationResult.setResults(resultItems);
            results.add(decisionEvaluationResult);
        }
        return results;
    }

}
