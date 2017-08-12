package tr.com.logo.dmn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.logo.dmn.service.DecisionTableService;

import javax.annotation.PostConstruct;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
@Component
public class Registry {
    private static Registry instance;

    @Autowired
    private DecisionTableService decisionTableService;

    @PostConstruct
    private void init() {
        instance = this;
    }

    public static Registry get() {
        return instance;
    }

    public static DecisionTableService getDecisionTableService() {
        return instance.decisionTableService;
    }

}

