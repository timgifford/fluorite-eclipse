package edu.cmu.scs.fluorite.commands;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class FileOpenCommandTest {

	private PluginPreferences pluginPreferences;
	private FileOpenCommand command;

	@Before
	public void setup() {
		pluginPreferences = new PluginPreferences();
		command = new FileOpenCommand();
		command.setPluginPreferences(pluginPreferences);
	}

	@Test
	public void snapshotNotIncludedInMapWhenOptionDisabled(){
		pluginPreferences.setLogFileContents(false);
		
		Map<String, String> dataMap = command.getDataMap();
		
		assertFalse(dataMap.containsKey("snapshot"));
	}
	
	@Test
	public void snapshotIncludedInMapWhenOptionEnabled(){
		pluginPreferences.setLogFileContents(true);
		command.setSnapshot("");
		
		Map<String, String> dataMap = command.getDataMap();
		
		assertTrue(dataMap.containsKey("snapshot"));		
	}
}
