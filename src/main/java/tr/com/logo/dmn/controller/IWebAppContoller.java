package tr.com.logo.dmn.controller;

import tr.com.logo.dmn.model.Tenant;
import tr.com.logo.dmn.model.User;

/**
 * Created by umitvardar on 29.04.2016.
 */
public interface IWebAppContoller {
    User login(String uname, String passwd);
    User createOrUpdateUser(User user);
    Tenant createOrUpdateTenant(Tenant tenant);

}
