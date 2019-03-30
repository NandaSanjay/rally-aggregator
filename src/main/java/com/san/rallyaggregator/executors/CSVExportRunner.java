/**
 * 
 */
package com.san.rallyaggregator.executors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import com.san.rallyaggregator.model.RallyItem;

/**
 * @author nandasan
 *
 */
public class CSVExportRunner implements Callable<File> {

	private static final String ELEMENT_SEPARATOR = ",";
	private static final CharSequence RECORD_HEADER = "FormattedID,Name,Requirement,Severity,State,FixedIn,SubmittedBy,Owner,DisplayColor\n";
	protected static final CharSequence NEW_LINE_CHAR = "\n";
	private String filePath;
	private List<RallyItem> items;
	
	public CSVExportRunner(String filePath, List<RallyItem> items) {
		super();
		this.filePath = filePath;
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public File call() throws Exception {
		// TODO Auto-generated method stub
		File exportFile = new File(filePath);
		if (!exportFile.isFile())
		{
			throw new Exception("Invalid file path supplied, Unable to create the file");
		}
		try (FileWriter fw = new FileWriter(exportFile))
		{
			fw.append(RECORD_HEADER);
			items.stream().forEach(new Consumer<RallyItem>() {

				@Override
				public void accept(RallyItem t) {
					try {
						fw.append(getformattedRecord(t));
						fw.append(NEW_LINE_CHAR);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		return exportFile;
	}

	/**
	 * @param t
	 * @return
	 */
	protected CharSequence getformattedRecord(RallyItem t) {
		return new StringBuffer().append(t.getId()).append(ELEMENT_SEPARATOR)
				.append(t.getName()).append(ELEMENT_SEPARATOR)
				.append(t.getRequirement()).append(ELEMENT_SEPARATOR)
				.append(t.getSeverity()).append(ELEMENT_SEPARATOR)
				.append(t.getState()).append(ELEMENT_SEPARATOR)
				.append(t.getFixedIn()).append(ELEMENT_SEPARATOR)
				.append(t.getSubmitter()).append(ELEMENT_SEPARATOR)
				.append(t.getOwner()).append(ELEMENT_SEPARATOR)
				.append(t.getColor()).append(ELEMENT_SEPARATOR).toString();
	}
}
