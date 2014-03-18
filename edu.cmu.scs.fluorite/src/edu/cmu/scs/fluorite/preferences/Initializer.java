package edu.cmu.scs.fluorite.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import edu.cmu.scs.fluorite.plugin.Activator;

public class Initializer extends AbstractPreferenceInitializer {

	public static final String Pref_EnableEventLogger = "EventLogger_EnableEventLogger";
	public static final String Pref_ShowConsole = "EventLogger_ShowConsole";
	public static final String Pref_WriteToConsole = "EventLogger_WriteToConsole";

	public static final String Pref_CombineCommands = "EventLogger_CombineCommands";
	public static final String Pref_CombineTimeThreshold = "EventLogger_CombineTimeThreshold";

	public static final String Pref_LogInsertedText = "EventLogger_LogInsertedText";
	public static final String Pref_LogDeletedText = "EventLogger_LogDeletedText";

	public static final String Pref_LogTopBottomLines = "EventLogger_LogTopBottomLines";
	public static final String Pref_LogMouseWheel = "EventLogger_LogMouseWheel";

	public static final String Pref_FindForward = "EventLogger_FindForward";
	public static final String Pref_FindCaseSensitive = "EventLogger_FindCaseSensitive";
	public static final String Pref_FindWrapSearch = "EventLogger_FindWrapSearch";
	public static final String Pref_FindWholeWord = "EventLogger_FindWholeWord";
	public static final String Pref_FindRegExp = "EventLogger_FindRegExp";
	public static final String Pref_LogFileContents = "EventLogger_LogFileContents";

	public Initializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.setDefault(Pref_EnableEventLogger, true);
		preferenceStore.setDefault(Pref_ShowConsole, false);
		preferenceStore.setDefault(Pref_WriteToConsole, false);

		preferenceStore.setDefault(Pref_LogFileContents, true);
		
		preferenceStore.setDefault(Pref_CombineCommands, true);
		preferenceStore.setDefault(Pref_CombineTimeThreshold, 2000);

		preferenceStore.setDefault(Pref_LogInsertedText, true);
		preferenceStore.setDefault(Pref_LogDeletedText, true);

		preferenceStore.setDefault(Pref_LogTopBottomLines, false);
		preferenceStore.setDefault(Pref_LogMouseWheel, false);

		preferenceStore.setDefault(Pref_FindForward, true);
		preferenceStore.setDefault(Pref_FindCaseSensitive, false);
		preferenceStore.setDefault(Pref_FindWrapSearch, false);
		preferenceStore.setDefault(Pref_FindWholeWord, false);
		preferenceStore.setDefault(Pref_FindRegExp, false);
	}

}
