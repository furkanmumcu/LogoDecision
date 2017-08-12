package tr.com.logo.dmn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.logo.dmn.model.DecisionTableModel;
import tr.com.logo.dmn.repository.DecisionTableModelRepository;

import java.util.List;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
@Service
public class DecisionTableService {

    @Autowired
    private DecisionTableModelRepository decisionTableModelRepository;

    public List<DecisionTableModel> findAllDecisionTables(){
        return (List<DecisionTableModel>)(decisionTableModelRepository.findAll());
    }

    public DecisionTableModel findDecisionTable(String name, int version){
        return decisionTableModelRepository.findByCaptionAndVersion(name,version);
    }

    public DecisionTableModel saveDecisitonTable(String caption, Integer version, byte[] content){
        DecisionTableModel decisionTableModel = new DecisionTableModel();
        decisionTableModel.setCaption(caption);
        decisionTableModel.setVersion(version);
        decisionTableModel.setDecisionModel(content);
        return decisionTableModelRepository.save(decisionTableModel);
    }
}
