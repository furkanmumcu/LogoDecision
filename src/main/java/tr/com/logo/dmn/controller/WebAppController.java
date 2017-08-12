package tr.com.logo.dmn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.logo.dmn.model.Tenant;
import tr.com.logo.dmn.model.User;

/**
 * Created by umitvardar on 29.04.2016.
 */
@RestController(value = "/api/v1")
public class WebAppController implements IWebAppContoller{
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @Override
    public User login(String uname, String passwd) {
        return null;
    }

    @Override
    public User createOrUpdateUser(User user) {
        return null;
    }

    @Override
    public Tenant createOrUpdateTenant(Tenant tenant) {
        return null;
    }
}
