package tr.com.logo.dmn;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by umitvardar on 12.05.2016.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/rest", glue = "tr.com.logo.cucumber.glue.rest")
public class DecisionRestTest {
}
