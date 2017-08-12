package tr.com.logo.dmn.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.logo.dmn.model.DecisionTableModel;

import java.util.List;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
public interface DecisionTableModelRepository extends CrudRepository<DecisionTableModel,String>{

    DecisionTableModel findByCaptionAndVersion(String caption, int version);
}
