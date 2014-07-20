package edu.cmu.scs.fluorite.util;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestElementContainer;
import org.eclipse.jdt.junit.model.ITestRunSession;

public class TestCaseElementTestDouble implements ITestElement, ITestCaseElement {

	private ITestRunSession testRunSession;
	private String testMethodName;
	
	public TestCaseElementTestDouble(ITestRunSession testRunSession){
		this(testRunSession, "Test Method Name");
	}

	public TestCaseElementTestDouble(ITestRunSession testRunSession, String testMethodName) {
		this.testRunSession = testRunSession;
		this.testMethodName = testMethodName;
	}

	@Override
	public double getElapsedTimeInSeconds() {
		return 0;
	}

	@Override
	public FailureTrace getFailureTrace() {
		return null;
	}

	@Override
	public ITestElementContainer getParentContainer() {
		return null;
	}

	@Override
	public ProgressState getProgressState() {
		return null;
	}

	@Override
	public Result getTestResult(boolean arg0) {
		return null;
	}

	@Override
	public ITestRunSession getTestRunSession() {
		return testRunSession;
	}

	
	
	@Override
	public String getTestClassName() {
		return "Test Class Name";
	}

	@Override
	public String getTestMethodName() {
		return testMethodName;
	}

}
