package tr.com.logo.dmn;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by umitvardar on 12.05.2016.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/tenant", glue = "tr.com.logo.dmn.glue.tenant")
public class DecisionTenantTest {
}
