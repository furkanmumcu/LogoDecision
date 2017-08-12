package tr.com.logo.dmn;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class EvaluationRemote {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
		selenium.setSpeed("2000");
	}

	@Test
	public void testEvaluationRemote() throws Exception {
		/*
		selenium.open("/");
		selenium.clickAt("//div[@id='ROOT-2521314']/div/div[2]/div[2]/div[11]/div/div/div[2]/div","");
		selenium.type("id=gwt-uid-4", "Pazartesi");
		selenium.type("id=gwt-uid-6", "Ogle");
		selenium.click("//div[@id='ROOT-2521314-overlays']/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div");
		*/

		/*
		selenium.open("/");
		selenium.clickAt("//div[@id='ROOT-2521314']/div/div[2]/div[2]/div[14]/div/div/div/div/span", "");
		selenium.type("id=gwt-uid-4", "Bilkent");
		selenium.type("id=gwt-uid-6", "3");
		selenium.type("id=gwt-uid-8", "cs");
		selenium.type("id=gwt-uid-10", "3.0");
		selenium.click("//div[@id='ROOT-2521314-overlays']/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div");
		*/

		/*
		selenium.open("/");
		selenium.clickAt("//div[@id='574d396a56b04c7285ec2923']/div/div[2]/div", "");
		selenium.type("id=input1", "Bilkent");
		selenium.type("id=InputClause_0hmkumv", "3");
		selenium.type("id=InputClause_0yl7c4i", "cs");
		selenium.type("id=InputClause_0dno65l", "3.0");
		selenium.click("//div[@id='ROOT-2521314-overlays']/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div");
		*/

		selenium.open("/");
		selenium.clickAt("//div[@id='574d396a56b04c7285ec2923']/div/div/div/span", "");
		selenium.type("id=input1", "Hacettepe");
		selenium.type("id=InputClause_0hmkumv", "3");
		selenium.type("id=InputClause_0yl7c4i", "3");
		selenium.type("id=InputClause_0dno65l", "3.0");
		selenium.click("//div[@id='ROOT-2521314-overlays']/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div");


	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
