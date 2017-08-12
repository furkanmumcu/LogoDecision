package tr.com.logo.dmn.ui.component;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.util.StringUtils;
import org.vaadin.easyuploads.UploadField;
import tr.com.logo.dmn.Registry;
import tr.com.logo.dmn.ui.VaadinUI;

import java.io.ByteArrayInputStream;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
public class DecisionTableCreatorWindow extends PopupWindow{

    public DecisionTableCreatorWindow(){

        setCaption("Decision Table Creator");
        FormLayout formLayout = new FormLayout();
        formLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        TextField captionField = new TextField("Caption");
        TextField versionField = new TextField("Version");
        UploadField upload = new UploadField();
        upload.setButtonCaption("DecisionTable");
        upload.setFieldType(UploadField.FieldType.BYTE_ARRAY);
        formLayout.addComponents(captionField,versionField,upload);

        VerticalLayout contentLayout = new VerticalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");
        cancelButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        saveButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        cancelButton.addStyleName("cancel");
        saveButton.addStyleName("save");
        buttonLayout.setWidth(100,Unit.PERCENTAGE);
        buttonLayout.addComponents(cancelButton,saveButton);
        buttonLayout.setComponentAlignment(cancelButton,Alignment.BOTTOM_LEFT);
        buttonLayout.setComponentAlignment(saveButton,Alignment.BOTTOM_RIGHT);

        setResizable(false);
        contentLayout.addComponents(formLayout,buttonLayout);
        contentLayout.setMargin(new MarginInfo(false,true,false,true));
        setContent(contentLayout);
        setWidth(350,Unit.PIXELS);
        setHeight(300,Unit.PIXELS);
        center();

        cancelButton.addClickListener(e->{
            DecisionTableCreatorWindow.this.close();
        });

        saveButton.addClickListener(e->{
            if(!StringUtils.isEmpty(captionField.getValue()) && !StringUtils.isEmpty(versionField.getValue())){
                try{
                    upload.getContentAsStream();
                    upload.getValue();
                    Registry.getDecisionTableService().saveDecisitonTable(captionField.getValue(),
                            Integer.parseInt(versionField.getValue()),
                            (byte[])upload.getValue());
                    Notification.show("New decision table " + captionField.getValue() + " successfully created");
                    DecisionTableCreatorWindow.this.close();
                }
                catch (Exception exception){
                    System.out.println(exception.toString());

                }

            }
        });

    }
}
