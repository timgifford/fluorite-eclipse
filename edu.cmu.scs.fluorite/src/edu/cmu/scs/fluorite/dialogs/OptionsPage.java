package edu.cmu.scs.fluorite.dialogs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import edu.cmu.scs.fluorite.model.EventRecorder;
import edu.cmu.scs.fluorite.plugin.Activator;
import edu.cmu.scs.fluorite.preferences.Initializer;

public class OptionsPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Button mEnableEventLogger;
	private Button mShowConsole;
	private Button mWriteToConsole;
	private Button mLogFileWhenOpened;

	private Button mCombineCommands;
	private Text mCombineThreshold;

	private Button mLogInsertedText;
	private Button mLogDeletedText;

	private Button mLogTopBottomLines;
	private Button mLogMouseWheel;

	public OptionsPage() {
	}

	public OptionsPage(String title) {
		super(title);
	}

	public OptionsPage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getShell(), Activator.PLUGIN_ID + ".overallHelp");
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		mEnableEventLogger = new Button(comp, SWT.CHECK);
		mEnableEventLogger
				.setText("Enable EventLogger plugin (restart needed)");
		mEnableEventLogger.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_EnableEventLogger));

		mShowConsole = new Button(comp, SWT.CHECK);
		mShowConsole.setText("Show console");
		mShowConsole
				.setToolTipText("If set, make the console visible during command log.");
		mShowConsole.setSelection(Activator.getDefault().getPreferenceStore()
				.getBoolean(Initializer.Pref_ShowConsole));

		mWriteToConsole = new Button(comp, SWT.CHECK);
		mWriteToConsole.setText("Write to console");
		mWriteToConsole
				.setToolTipText("If set, write normal execution trace statements to the console during command log.");
		mWriteToConsole.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_WriteToConsole));

		createCollectFileText(comp);
		
		mCombineCommands = new Button(comp, SWT.CHECK);
		mCombineCommands.setText("Combine multiple commands of same type");
		mCombineCommands.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_CombineCommands));

		Composite compCombine = new Composite(comp, SWT.NONE);
		compCombine.setLayout(new GridLayout(2, false));
		compCombine.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(compCombine, SWT.NONE);
		label.setText("Command combine time threshold (in milliseconds)");

		mCombineThreshold = new Text(compCombine, SWT.BORDER);
		mCombineThreshold.setText(Integer.toString(Activator.getDefault()
				.getPreferenceStore()
				.getInt(Initializer.Pref_CombineTimeThreshold)));

		mLogInsertedText = new Button(comp, SWT.CHECK);
		mLogInsertedText.setText("Log Inserted Text");
		mLogInsertedText.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_LogInsertedText));

		mLogDeletedText = new Button(comp, SWT.CHECK);
		mLogDeletedText.setText("Log Deleted Text");
		mLogDeletedText.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_LogDeletedText));

		mLogTopBottomLines = new Button(comp, SWT.CHECK);
		mLogTopBottomLines
				.setText("Log top / bottom line numbers shown on the screen");
		mLogTopBottomLines.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_LogTopBottomLines));

		mLogMouseWheel = new Button(comp, SWT.CHECK);
		mLogMouseWheel.setText("Log mouse wheels");
		mLogMouseWheel.setSelection(Activator.getDefault().getPreferenceStore()
				.getBoolean(Initializer.Pref_LogMouseWheel));

		return comp;
	}

	private void createCollectFileText(Composite comp) {
		mLogFileWhenOpened = new Button(comp, SWT.CHECK);
		mLogFileWhenOpened.setText("Log file contents");
		mLogFileWhenOpened
				.setToolTipText("If set, logs file contents when the file is opened.");
		mLogFileWhenOpened.setSelection(Activator.getDefault()
				.getPreferenceStore()
				.getBoolean(Initializer.Pref_LogFileContents));
	}

	public void init(IWorkbench workbench) {
		// nothing to do
	}

	@Override
	public boolean performOk() {
		IPreferenceStore preferenceStore = Activator
				.getDefault()
				.getPreferenceStore();
		preferenceStore
				.setValue(Initializer.Pref_EnableEventLogger,
						mEnableEventLogger.getSelection());
		preferenceStore
				.setValue(Initializer.Pref_ShowConsole,
						mShowConsole.getSelection());
	
		preferenceStore.setValue(Initializer.Pref_LogFileContents, mLogFileWhenOpened.getSelection());
		
		preferenceStore
				.setValue(Initializer.Pref_WriteToConsole,
						mWriteToConsole.getSelection());

		preferenceStore
				.setValue(Initializer.Pref_CombineCommands,
						mCombineCommands.getSelection());
		preferenceStore
				.setValue(Initializer.Pref_CombineTimeThreshold,
						Integer.parseInt(mCombineThreshold.getText()));
		EventRecorder.getInstance().setCombineCommands(
				mCombineCommands.getSelection());
		EventRecorder.getInstance().setCombineTimeThreshold(
				Integer.parseInt(mCombineThreshold.getText()));

		preferenceStore
				.setValue(Initializer.Pref_LogInsertedText,
						mLogInsertedText.getSelection());
		preferenceStore
				.setValue(Initializer.Pref_LogDeletedText,
						mLogDeletedText.getSelection());

		preferenceStore
				.setValue(Initializer.Pref_LogTopBottomLines,
						mLogTopBottomLines.getSelection());

		preferenceStore
				.setValue(Initializer.Pref_LogMouseWheel,
						mLogMouseWheel.getSelection());

		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		mEnableEventLogger.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_EnableEventLogger));
		mShowConsole.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_ShowConsole));
		
		mLogFileWhenOpened.setSelection(preferenceStore.getDefaultBoolean(Initializer.Pref_LogFileContents));
		
		mWriteToConsole.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_WriteToConsole));

		mCombineCommands.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_CombineCommands));
		mCombineThreshold.setText(Integer.toString(preferenceStore
				.getDefaultInt(Initializer.Pref_CombineTimeThreshold)));

		mLogInsertedText.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_LogInsertedText));
		mLogDeletedText.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_LogDeletedText));

		mLogTopBottomLines.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_LogTopBottomLines));
		mLogMouseWheel.setSelection(preferenceStore
				.getDefaultBoolean(Initializer.Pref_LogMouseWheel));

		super.performDefaults();
	}

}
