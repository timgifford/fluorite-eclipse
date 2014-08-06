package edu.cmu.scs.fluorite.commands;

import org.eclipse.jface.preference.IPreferenceStore;

import edu.cmu.scs.fluorite.plugin.Activator;
import edu.cmu.scs.fluorite.preferences.Initializer;

public class PluginPreferences {

	private boolean logFileContents = false;

	public boolean isLogFileContentsEnabled() {
		return logFileContents;
	}

	public void setLogFileContents(boolean value) {
		this.logFileContents = value;
	}
	
	public static PluginPreferences createFromActivator(){
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		PluginPreferences pluginPreferences = new PluginPreferences();
		pluginPreferences.setLogFileContents(preferenceStore.getBoolean(Initializer.Pref_LogFileContents));
		return pluginPreferences;
	}

}
