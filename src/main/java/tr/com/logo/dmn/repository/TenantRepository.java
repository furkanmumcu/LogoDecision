package tr.com.logo.dmn.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.logo.dmn.model.Tenant;

/**
 * Created by Samet Sevim on 25.5.2016.
 */
public interface TenantRepository extends CrudRepository<Tenant,String>{
}
