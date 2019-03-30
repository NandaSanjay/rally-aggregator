package com.san.rallyaggregator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.junit.Test;

import com.san.rallyaggregator.model.RallyItem;
import com.san.rallyaggregator.services.DefectAggregator;
import com.san.rallyaggregator.services.ExportedDatWriter;
import com.san.rallyaggregator.services.InputDataParser;
import com.san.rallyaggregator.utils.RallyCategory;

public class CSVParserRunnerTest {
	
	InputDataParser parser = new InputDataParser();
	DefectAggregator aggregator = new DefectAggregator();
	ExportedDatWriter writer = new ExportedDatWriter();

	@Test
	public void testPraseData() throws FileNotFoundException, IOException, InterruptedException, ExecutionException
	{
		URL url = getClass().getResource("export.csv");
		File exportedCSVData = new File(url.getPath());
		parser.parseCSVData(exportedCSVData);
		
		
	}
	
	@Test
	public void testGetFilteredItemByCategory() throws Exception
	{
		URL url = getClass().getResource("export.csv");
		File exportedCSVData = new File(url.getPath());
		List<RallyItem> parseCSVData = parser.parseCSVData(exportedCSVData);
		List<RallyItem> filteredItemsByCategory = aggregator.getFilteredItemsByCategory(RallyCategory.KEYWORD, "IPv6");
		System.out.println("Rally Deect list for Prasanna:");
		filteredItemsByCategory.forEach(new Consumer<RallyItem>() {

			@Override
			public void accept(RallyItem t) {
				System.out.println(t.toString());
				
			}
		});		
	}
	
	@Test
	public void testExportFilteredDataWriter() throws Exception
	{
		URL url = getClass().getResource("export.csv");
		File exportedCSVData = new File(url.getPath());
		List<RallyItem> parseCSVData = parser.parseCSVData(exportedCSVData);
		List<RallyItem> filteredItemsByCategory = aggregator.getFilteredItemsByCategory(RallyCategory.KEYWORD, "IPv6");
		System.out.println("Rally Defect list with keyword: IPv6");
		filteredItemsByCategory.forEach(new Consumer<RallyItem>() {

			@Override
			public void accept(RallyItem t) {
				System.out.println(t.toString());
				
			}
		});	
		URL outputUrl = getClass().getResource("output.csv");
		writer.exportAsCSVFile(outputUrl.getPath(), filteredItemsByCategory);
	}
}
