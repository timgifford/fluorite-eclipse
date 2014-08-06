package edu.cmu.scs.fluorite.commands;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import edu.cmu.scs.fluorite.commands.JUnitCommand.TestData;
import edu.cmu.scs.fluorite.commands.JUnitCommand.TestData.ElementType;
import edu.cmu.scs.fluorite.model.Events;
import edu.cmu.scs.fluorite.util.IFilter;
import edu.cmu.scs.fluorite.util.LogReader;

public class JUnitCommandReadWriteTest {
	
	private static final String FILE_PATH = "data/JUnitCommandReadWriteTest.xml";
	private LogReader reader;
	private Events events;
	private JUnitCommand cmd;

	@Before
	public void setup() {
		reader = new LogReader();
		events = reader.readFilter(FILE_PATH, new IFilter() {
			@Override
			public boolean filter(Element element) {
				return LogReader.isCommandTyped(element, "JUnitCommand");
			}
		});
		
		// the first command.
		cmd = (JUnitCommand) events.getCommands().get(0);
	}
	
	@Test
	public void eventsTimeStampIncludesDate(){
		String eventStartTime = getFormattedTimeStamp(events.getStartTimestamp());
		
		assertThat(eventStartTime, is("11/06/2013 01:42:08:567"));
	}
	
	@Test
	public void commandsTimestampIsDurationSinceEventTimeStamp(){
		long startTimestamp = events.getStartTimestamp();
		
		String dateFormatted = getFormattedTimeStamp(startTimestamp + cmd.getTimestamp());
		
		assertThat(dateFormatted, is("11/06/2013 01:43:00:959"));
	}

	@Test
	public void eventsContainsFourJUnitCommands(){
		assertThat(events.getCommands().size(), is(4));
		for (int i = 0; i < 4; ++i) {
			assertThat(events.getCommands().get(i), is(instanceOf(JUnitCommand.class)));
		}
	}
	
	@Test
	public void testCreateFrom() {
		TestData testData;
		TestData testData2;
		TestData testData3;
		
		testData = (TestData) cmd.getRootData();
		assertThat(testData.getType(), is(ElementType.TestSession));
		assertThat(testData.getName(), is("JUnitTestProject"));
		assertThat(testData.getSucceeded(), is(false));
		assertThat(testData.getChildren().size(), is(3));
		
		testData2 = testData.getChildren().get(0);
		assertThat(testData2.getType(), is(ElementType.TestSuite));
		assertThat(testData2.getName(), is("junit.test.packagename.one.TestClassOne"));
		assertThat(testData2.getSucceeded(), is(true));
		assertThat(testData2.getChildren().size(), is(2));
		
		testData3 = testData2.getChildren().get(0);
		assertThat(testData3.getType(), is(ElementType.TestCase));
		assertThat(testData3.getName(), is("testMethodOne"));
		assertThat(testData3.getSucceeded(), is(true));
		assertThat(testData3.getChildren(), is(nullValue()));
		
		testData3 = testData2.getChildren().get(1);
		assertThat(testData3.getType(), is(ElementType.TestCase));
		assertThat(testData3.getName(), is("testMethodTwo"));
		assertThat(testData3.getSucceeded(), is(true));
		assertThat(testData3.getChildren(), is(nullValue()));
		
		testData2 = testData.getChildren().get(1);
		assertThat(testData2.getType(), is(ElementType.TestSuite));
		assertThat(testData2.getName(), is("junit.test.packagename.two.TestClassTwo"));
		assertThat(testData2.getSucceeded(), is(false));
		assertThat(testData2.getChildren().size(), is(2));
		
		testData3 = testData2.getChildren().get(0);
		assertThat(testData3.getType(), is(ElementType.TestCase));
		assertThat(testData3.getName(), is("testMethodThree"));
		assertThat(testData3.getSucceeded(), is(true));
		assertThat(testData3.getChildren(), is(nullValue()));
		
		testData3 = testData2.getChildren().get(1);
		assertThat(testData3.getType(), is(ElementType.TestCase));
		assertThat(testData3.getName(), is("testMethodFour"));
		assertThat(testData3.getSucceeded(), is(false));
		assertThat(testData3.getChildren(), is(nullValue()));
		
		testData2 = testData.getChildren().get(2);
		assertThat(testData2.getType(), is(ElementType.TestSuite));
		assertThat(testData2.getName(), is("junit.test.packagename.two.TestClassThree"));
		assertThat(testData2.getSucceeded(), is(true));
		assertThat(testData2.getChildren().size(), is(1));
		
		testData3 = testData2.getChildren().get(0);
		assertThat(testData3.getType(), is(ElementType.TestCase));
		assertThat(testData3.getName(), is("testMethodFive"));
		assertThat(testData3.getSucceeded(), is(true));
		assertThat(testData3.getChildren(), is(nullValue()));
		
		// the third command
		cmd = (JUnitCommand) events.getCommands().get(2);
		assertThat(cmd.getProjectName(), is("JUnitTestProject"));
		assertThat(cmd.getElapsedTimeInSeconds(), is(0.0010001659393310547));
		assertThat(cmd.getRootData(), is(instanceOf(TestData.class)));
		
		testData = (TestData) cmd.getRootData();
		assertThat(testData.getType(), is(ElementType.TestSession));
		assertThat(testData.getName(), is("TestClassTwo.testMethodFour"));
		assertThat(testData.getSucceeded(), is(false));
		assertThat(testData.getChildren().size(), is(1));
		
		testData2 = testData.getChildren().get(0);
		assertThat(testData2.getType(), is(ElementType.TestCase));
		assertThat(testData2.getName(), is("testMethodFour"));
		assertThat(testData2.getSucceeded(), is(false));
		assertThat(testData2.getChildren(), is(nullValue()));
	}

	@Test
	public void assertFirstCommand() {
		assertThat(cmd.getProjectName(), is("JUnitTestProject"));
		assertThat(cmd.getElapsedTimeInSeconds(), is(0.003000020980834961));
		assertThat(cmd.getRootData(), is(instanceOf(TestData.class)));
	}

	public String getFormattedTimeStamp(long timestamp) {
		Date date = new Date(timestamp);
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		return formatter.format(date);
	}
}

