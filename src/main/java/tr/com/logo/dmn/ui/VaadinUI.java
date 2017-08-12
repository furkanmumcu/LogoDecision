package tr.com.logo.dmn.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import tr.com.logo.dmn.ui.component.MainLayout;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
@SpringUI
@Theme("logodecision")
@Widgetset("tr.com.logo.dmn.ui.LogoDecisionWidgetSet")
public class VaadinUI extends UI{
    @Override
    protected void init(VaadinRequest vaadinRequest) {
       setContent(new MainLayout());
    }
}
