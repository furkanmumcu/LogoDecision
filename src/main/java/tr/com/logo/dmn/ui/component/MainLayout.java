package tr.com.logo.dmn.ui.component;

import com.sebworks.vaadstrap.Col;
import com.sebworks.vaadstrap.ColMod;
import com.sebworks.vaadstrap.Container;
import com.sebworks.vaadstrap.Row;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableInputImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableOutputImpl;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableRuleImpl;
import tr.com.logo.dmn.Registry;
import tr.com.logo.dmn.model.DecisionTableModel;
import tr.com.logo.dmn.service.DecisionTableService;
import tr.com.logo.dmn.ui.VaadinUI;

import java.io.ByteArrayInputStream;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
public class MainLayout extends Container{

    private Row headerRow,contentRow;
    private DecisionTableService decisionTableService;
    private DecisionTableCreatorWindow decisionTableCreatorWindow;
    public MainLayout(){

        decisionTableService = Registry.getDecisionTableService(); // cunku bu spring acisindan bisey ifade etmeyen bi class
        decisionTableCreatorWindow = new DecisionTableCreatorWindow();
        decisionTableCreatorWindow.addCloseListener(e->{
            initContentRow();
        });
        headerRow = addRow();
        contentRow = addRow();
        initHeaderRow();
        initContentRow();

    }

    private void initHeaderRow(){
        HorizontalLayout headerLayout = new HorizontalLayout();
        Button newDecisionTableButton = new Button("New Decision Table");
        newDecisionTableButton.addClickListener(e->{
            VaadinUI.getCurrent().addWindow(decisionTableCreatorWindow);
        });
        headerLayout.addStyleName("main-header");
        newDecisionTableButton.addStyleName("new-decision-model");
        newDecisionTableButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        headerLayout.addComponent(newDecisionTableButton);
        headerLayout.setComponentAlignment(newDecisionTableButton, Alignment.TOP_RIGHT);
        headerLayout.setWidth(100,Unit.PERCENTAGE);
        headerRow.setWidth(100,Unit.PERCENTAGE);
        headerRow.addComponent(headerLayout);
    }

    private void initContentRow(){
        contentRow.removeAllComponents();
        for(DecisionTableModel decisionTableModel : decisionTableService.findAllDecisionTables()){
            addDecisionModelLayout(decisionTableModel);
        }
    }

    private void addDecisionModelLayout(DecisionTableModel decisionTableModel){

        Col colTemp = contentRow.addCol(ColMod.SM_6,ColMod.MD_4,ColMod.LG_3);
        colTemp.addComponent(renderDecisionItem(decisionTableModel));
    }

    private HorizontalLayout renderDecisionItem(DecisionTableModel decisionTableModel){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setHeight(150,Unit.PIXELS);
        horizontalLayout.setWidth(100,Unit.PERCENTAGE);
        horizontalLayout.setStyleName("decision-table-item");
        Label iconLabel = new Label();
        iconLabel.setContentMode(ContentMode.HTML);
        iconLabel.setValue(FontAwesome.DIAMOND.getHtml());
        iconLabel.setHeight(150,Unit.PIXELS);
        iconLabel.setWidth(100,Unit.PERCENTAGE);
        iconLabel.addStyleName("decision-table-label");
        Label label = new Label(decisionTableModel.getCaption() + "-v." + decisionTableModel.getVersion());
        horizontalLayout.addComponents(iconLabel,label);
        horizontalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        horizontalLayout.setComponentAlignment(iconLabel,Alignment.MIDDLE_CENTER);
        horizontalLayout.addLayoutClickListener(e->{
            VaadinUI.getCurrent().addWindow(new DecisionTableEngineWindow(decisionTableModel));
            ////////////////////
            System.out.println(decisionTableModel.getId()); /** in order to see layout id    */
            ////////////////////
        });

        horizontalLayout.setId(decisionTableModel.getId());
        return horizontalLayout;
    }
}
