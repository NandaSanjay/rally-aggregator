package com.san.rallyaggregator.services;

import java.awt.ItemSelectable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.san.rallyaggregator.executors.CSVParserRunner;
import com.san.rallyaggregator.executors.TaskExecutor;
import com.san.rallyaggregator.model.RallyItem;

@Service("fileParser")
public class InputDataParser {
	
	TaskExecutor executor = new TaskExecutor();
	
	private static final String CSV_SEPARATOR = ",";

	public List<RallyItem> parseCSVData(File csvFile) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		if (csvFile == null || (csvFile != null && !csvFile.isFile())) {
			throw new IllegalArgumentException("Invalid file");
		}
		
		List<RallyItem> parsedData = executor.submitParserTask(new CSVParserRunner(csvFile));
	    parsedData.forEach(new Consumer<RallyItem>() {

			@Override
			public void accept(RallyItem item) {
				//System.out.println("Retrived Rally Items" + item.toString());
				
			}
		});
		return parsedData;		
	}

}
