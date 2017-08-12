package tr.com.logo.dmn.ui.component;

import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableInputImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableOutputImpl;
import sun.util.logging.PlatformLogger;
import tr.com.logo.dmn.controller.dto.DecisionItemVariable;
import tr.com.logo.dmn.model.DecisionTableModel;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.logging.Logger;

/**
 * Furkan Mumcu 5.30.2016
 */

public class DecisionTableEngineWindow extends PopupWindow{

    private static final Logger log= Logger.getLogger( DecisionTableEngineWindow.class.getName());


    public DecisionTableEngineWindow(DecisionTableModel decisionTableModel){
        setCaption("Make Decisions for " + decisionTableModel.getCaption() + " Table");
        //
        setModal(true);
        setResizable(false);
        setWidth("900px");
        center();
        //
        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);
        HorizontalLayout container = new HorizontalLayout();
        container.setWidth("100%");

        FormLayout inputLayout = new FormLayout();
        inputLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        FormLayout outputLayout = new FormLayout();
        outputLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        VerticalLayout buttonLayout = new VerticalLayout();

        //
        inputLayout.setWidth("100%");
        outputLayout.setWidth("100%");
        Label l = new Label("<b>Inputs:</b>", ContentMode.HTML);
        Label l2 = new Label("<b>Outputs:</b>", ContentMode.HTML);
        inputLayout.addComponent(l);
        outputLayout.addComponent(l2);
        container.addComponent(inputLayout);
        container.addComponent(buttonLayout);
        container.addComponent(outputLayout);
        mainLayout.addComponent(container);
        mainLayout.setMargin(new MarginInfo(false,true,false,true));

        //System.out.println(decisionTableModel.toString());
        int inputCount = 0;
        int outputCount = 0;

        List <Field> fields = new ArrayList<>();
        List <Field> outputFields = new ArrayList<>();
        DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
        ByteArrayInputStream bais = new ByteArrayInputStream(decisionTableModel.getDecisionModel());
        List<DecisionItemVariable> inputVariables = new ArrayList<>();
        List<DecisionItemVariable> outputVariables = new ArrayList<>();
        List<DmnDecision> decisions = dmnEngine.parseDecisions(bais);
        for(DmnDecision decision : decisions){ // gereksizmis zaten bi decision var
            System.out.println("lol");
            if(decision instanceof DmnDecisionTableImpl) {
                DmnDecisionTableImpl decisionTable = (DmnDecisionTableImpl) decision;
                for (DmnDecisionTableInputImpl input : decisionTable.getInputs()) {
                    inputCount++;
                    DecisionItemVariable decisionItemVariable = new DecisionItemVariable();
                    decisionItemVariable.setName(input.getName());
                    decisionItemVariable.setId(input.getId());
                    decisionItemVariable.setType(input.getExpression().getTypeDefinition().getTypeName());
                    decisionItemVariable.setValue("");
                    inputVariables.add(decisionItemVariable);


                    if(decisionItemVariable.getType().equals("string")) {
                        TextField t = new TextField(input.name);
                        t.setInputPrompt("string");
                        inputLayout.addComponent(t);
                        fields.add(t);
                        t.setId(decisionItemVariable.getId());
                    }
                    else if (decisionItemVariable.getType().equals("boolean")){
                        CheckBox checkBox = new CheckBox(input.name);
                        inputLayout.addComponent(checkBox);
                        fields.add(checkBox);
                        checkBox.setId(decisionItemVariable.getId());
                        System.out.println(decisionItemVariable.getId());
                        System.out.println(checkBox.getId());

                    }

                    else if (decisionItemVariable.getType().equals("integer")){
                        TextField t = new TextField(input.name);
                        t.addValidator(new IntegerRangeValidator("it must be integer", 1,1000000));
                        t.setInputPrompt("integer");
                        inputLayout.addComponent(t);
                        t.setImmediate(true);
                        t.setNullRepresentation("");
                        t.setNullSettingAllowed(true);
                        fields.add(t);
                        t.setId(decisionItemVariable.getId());
                    }

                    else if (decisionItemVariable.getType().equals("double")){
                        TextField t = new TextField(input.name);
                        t.addValidator(new DoubleRangeValidator("it must be double", 1.0,1000000.0));
                        t.setInputPrompt("double");
                        inputLayout.addComponent(t);
                        t.setImmediate(true);
                        t.setNullRepresentation("");
                        t.setNullSettingAllowed(true);
                        fields.add(t);
                        t.setId(decisionItemVariable.getId());
                    }

                    else if (decisionItemVariable.getType().equals("date")){
                        DateField dateField = new DateField(input.name);
                        inputLayout.addComponent(dateField);
                        fields.add(dateField);
                        dateField.setId(decisionItemVariable.getId());
                    }

                }
            } //////////////////////////

            if(decision instanceof DmnDecisionTableImpl) {
                DmnDecisionTableImpl decisionTable = (DmnDecisionTableImpl) decision;
                for (DmnDecisionTableOutputImpl output : decisionTable.getOutputs()) {
                    outputCount++;
                    DecisionItemVariable decisionItemVariable = new DecisionItemVariable();
                    decisionItemVariable.setName(output.getName());
                    decisionItemVariable.setId(output.getId());
                    decisionItemVariable.setType(output.getTypeDefinition().getTypeName());
                    decisionItemVariable.setValue("");
                    outputVariables.add(decisionItemVariable);


                    if(decisionItemVariable.getType().equals("string")) {
                        TextField t = new TextField(output.getName());
                        t.setId(decisionItemVariable.getId());
                        outputLayout.addComponent(t);
                        outputFields.add(t);
                    }

                    else if (decisionItemVariable.getType().equals("boolean")){
                        TextField ll = new TextField(output.getName());
                        ll.setId(decisionItemVariable.getId());
                        outputLayout.addComponent(ll);
                        outputFields.add(ll);
                    }

                    else if (decisionItemVariable.getType().equals("integer")){
                        TextField t = new TextField(output.getName());
                        t.addValidator(new IntegerRangeValidator("it must be integer", 1,1000000));
                        t.setId(decisionItemVariable.getId());
                        outputLayout.addComponent(t);
                        t.setImmediate(true);
                        t.setNullRepresentation("");
                        t.setNullSettingAllowed(true);
                        outputFields.add(t);
                    }

                    else if (decisionItemVariable.getType().equals("double")){
                        TextField t = new TextField(output.getName());
                        t.setId(decisionItemVariable.getId());
                        t.addValidator(new DoubleRangeValidator("it must be double", 1.0,1000000.0));
                        outputLayout.addComponent(t);
                        t.setImmediate(true);
                        t.setNullRepresentation("");
                        t.setNullSettingAllowed(true);
                        outputFields.add(t);
                    }

                    else if (decisionItemVariable.getType().equals("date")){
                        DateField dateField = new DateField(output.getName());
                        dateField.setId(decisionItemVariable.getId());
                        outputLayout.addComponent(dateField);
                        outputFields.add(dateField);
                    }
                }
            }
        }

        for(int c = 0; c< outputCount; c++){
            outputFields.get(c).setReadOnly(true);
        }

        Button saveButton = new Button("Run");
        Button cancelButton = new Button("Clear");
        saveButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        container.setWidth("100%");
        buttonLayout.addComponent(saveButton);
        container.setComponentAlignment(buttonLayout,Alignment.MIDDLE_CENTER);

        buttonLayout.addComponent(cancelButton);
        buttonLayout.addStyleName("spacing");
        buttonLayout.setWidth("40%");
        buttonLayout.setSpacing(true);
        saveButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);

        saveButton.addStyleName("save2");
        cancelButton.addStyleName("cancel2");
        cancelButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);

        buttonLayout.setComponentAlignment(saveButton,Alignment.MIDDLE_CENTER);
        buttonLayout.setComponentAlignment(cancelButton,Alignment.MIDDLE_CENTER);
        saveButton.setIcon(FontAwesome.PLAY);

        Map<String, Object> data = new HashMap<String, Object>();
        int finalInputCount = inputCount;
        int finalOutputCount = outputCount;

        cancelButton.addClickListener(e->{
            try {
                for (int c = 0; c < finalOutputCount; c++) {
                    outputFields.get(c).setReadOnly(false);
                }

                for (int c = 0; c < finalOutputCount; c++) {
                    outputFields.get(c).setValue("");
                }

                for (int c = 0; c < finalInputCount; c++) {
                    fields.get(c).setValue("");
                }

                for (int c = 0; c < finalOutputCount; c++) {
                    outputFields.get(c).setReadOnly(true);
                }
            }
            catch (Exception exception){
                System.out.println(exception.toString());
            }

        });

        saveButton.addClickListener(e->{
            for(DmnDecision decision : decisions) {
                try {
                    for (int j = 0; j < finalInputCount; j++) {

                        if (decision instanceof DmnDecisionTableImpl) {

                            data.put("input" + (j+1), fields.get(j).getValue());

                            if (data.size() == finalInputCount) {

                                DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, data);
                                Map<String, Object> treeMap = new TreeMap<String, Object>(result.get(0).getEntryMap());

                                for(int c = 0; c< finalOutputCount; c++){
                                    outputFields.get(c).setReadOnly(false);
                                }

                                int k=0;
                                for (Map.Entry<String, Object> entry : treeMap.entrySet()) {

                                    outputFields.get(k).setValue(String.valueOf(entry.getValue()));
                                    k++;

                                    System.out.println("Key : " + entry.getKey()
                                            + " Value : " + entry.getValue());
                                }

                                for(int c = 0; c< finalOutputCount; c++){
                                    outputFields.get(c).setReadOnly(true);
                                }
                            }
                        }
                    }
                }

                catch (Exception exception){
                    System.out.println(exception.toString());
                    Notification notif = new Notification("Warning", "No result was found with given intputs",
                            Notification.TYPE_WARNING_MESSAGE);
                    notif.show(Page.getCurrent());
                }
            }
        });

    }
}

