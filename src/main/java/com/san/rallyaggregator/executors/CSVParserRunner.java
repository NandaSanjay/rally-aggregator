package com.san.rallyaggregator.executors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.san.rallyaggregator.model.RallyItem;

public class CSVParserRunner implements Callable<List<RallyItem>> {
	
	private static final String ELEMENT_SEPARATOR = ",";
	private File csvFile;
	
	public CSVParserRunner(File csvFile) {
		super();
		this.csvFile = csvFile;
	}

	@Override
	public  List<RallyItem> call() throws Exception {
		
		List<RallyItem> processedData = new ArrayList<RallyItem>();
		
		try (BufferedReader br = new BufferedReader(new  FileReader(csvFile)))
		{
			br.readLine();
			String nextRecord = null;
			
			while((nextRecord = br.readLine()) != null)
			{
                String[] elements = nextRecord.split(ELEMENT_SEPARATOR);
                RallyItem item = new RallyItem();
                item.setId(elements[0]);
                item.setName(elements[1]);
                item.setRequirement(elements[2]);
                item.setSeverity(elements[3]);
                item.setState(elements[4]);
                item.setFixedIn(elements[5]);
                item.setSubmitter(elements[6]);
                item.setOwner(elements[7]);
                item.setColor(elements[8]);


                
                processedData.add(item);
			}
			return processedData;
		}
	}

}
