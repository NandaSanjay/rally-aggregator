package com.san.rallyaggregator.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.san.rallyaggregator.model.RallyItem;
import com.san.rallyaggregator.repository.RallyItemsRepository;

@Service
public class RallyItemService {
	
	@Resource
	RallyItemsRepository repository;
	
	@Resource
	InputDataParser parser;
	
	public Integer parseAndSaveRallyItems(File file) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
		List<RallyItem> items = parser.parseCSVData(file);
		repository.saveAll(items);
		return items.size();
				
	}

}
