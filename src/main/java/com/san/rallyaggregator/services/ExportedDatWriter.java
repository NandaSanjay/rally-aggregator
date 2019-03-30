/**
 * 
 */
package com.san.rallyaggregator.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import com.san.rallyaggregator.executors.CSVExportRunner;
import com.san.rallyaggregator.executors.CSVParserRunner;
import com.san.rallyaggregator.executors.TaskExecutor;
import com.san.rallyaggregator.model.RallyItem;
import com.san.rallyaggregator.utils.StringUtils;

/**
 * @author nandasan
 *
 */
public class ExportedDatWriter {
	TaskExecutor executor = new TaskExecutor();
	
	public File exportAsCSVFile(String toFilePath, List<RallyItem> items) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		if (StringUtils.isNullOrEmpty(toFilePath) || items == null) {
			throw new IllegalArgumentException("Unable to export, Invalid filepath provided or the extracted data is empty");
		}
		
		File exportedfile = executor.submitExporterTask(new CSVExportRunner(toFilePath, items));
		return exportedfile;		
	}
}
