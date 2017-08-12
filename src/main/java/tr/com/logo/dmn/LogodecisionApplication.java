package tr.com.logo.dmn;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableInputImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableOutputImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableRuleImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;


@SpringBootApplication
public class LogodecisionApplication {

	public static void main(String[] args) throws  Exception {
        SpringApplication.run(LogodecisionApplication.class, args);

//        // configure and build the DMN engine
//        DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
//
//        File dmnFile = new File("src/main/resources/dugun.dmn");
//        InputStream dmnStream = new FileInputStream(dmnFile);
//
//
//        // parse a decision
//        DmnDecision decision = dmnEngine.parseDecision("dugun", dmnStream);
//        DmnDecisionTableImpl decisionTable = null;
//        if(decision instanceof DmnDecisionTableImpl){
//            decisionTable = (DmnDecisionTableImpl) decision;
//            out.println(decisionTable.toString());
//        }else{
//            out.println("Decision Table cinsinde değil..");
//        }
//
//        out.println("Inputs are:");
//        for (DmnDecisionTableInputImpl input : decisionTable.getInputs()) {
//            out.println(input.toString());
//        }
//
//        out.println("Outputs are:");
//        for (DmnDecisionTableOutputImpl output : decisionTable.getOutputs()) {
//            out.println(output.toString());
//        }
//
//        out.println("Rules are:");
//        for (DmnDecisionTableRuleImpl rules : decisionTable.getRules()) {
//            out.println(rules.toString());
//        }
//
//        Map<String, Object> data = new HashMap<String, Object>();
//
//        data.put("input1", 145);
//        data.put("input2", "Akraba");
//
//        // evaluate a decision
//        DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, data);
//        out.println(result.toString());
    }
}
