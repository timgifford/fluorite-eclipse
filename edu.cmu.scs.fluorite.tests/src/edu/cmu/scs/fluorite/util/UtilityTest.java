package edu.cmu.scs.fluorite.util;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.junit.model.TestRoot;
import org.eclipse.jdt.internal.junit.model.TestRunSession;
import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
//import org.hamcrest.Matcher;
import org.junit.Test;

import edu.cmu.scs.fluorite.commands.ICommand;
import edu.cmu.scs.fluorite.commands.JUnitCommand;
public class UtilityTest {

	@Test
	public void shouldEncodeAttributesWhenWritingToXml(){
		Map<String, String> attrs = null;
		Map<String, String> data = null;
//		ITestElement featureTestSuite = new TestElement();
		String testRunName = "com.principal.sbd.caseinstall.cucumber.CucumberIntegrationTest";
		
		IJavaProject project = null;
		ITestRunSession cucumberSession = new TestRunSession(testRunName, project);
		TestRoot testRoot = new TestRoot(cucumberSession );
		
		ITestElement cucumberIntegrationTestSession = new TestCaseElementTestDouble(cucumberSession, "name with \"double quotes\""); //testRoot;
		
//	<Command __id="214" _type="JUnitCommand" elapsedTimeInSeconds="2724.30899977684" projectName="CaseInstallWeb"
//		             timestamp="4101437">
//		<TestSession succeeded="false" name="CucumberIntegrationTest (1)">
//        <TestSuite succeeded="false" name="com.principal.sbd.caseinstall.cucumber.CucumberIntegrationTest">
//            <TestSuite succeeded="false" name="Feature: Import Proposal">
//                <TestSuite succeeded="false" name="Scenario: Import new proposal when there are multiple versions">
//                    <TestCase succeeded="true"
//                              name="Scenario: Import new proposal when there are multiple versions.Given GPS contains proposals"/>
//                    
		
		ICommand junitCommmand = new JUnitCommand("CaseInstallWeb", 4101437, cucumberIntegrationTestSession);
		
		StringBuffer output = new StringBuffer();
		
		Utilities.persistCommand(output, attrs , data , junitCommmand );
		System.out.println(output);
		assertThat(output.toString(), containsString("&quot;double quotes&quot"));
	}
	
}
