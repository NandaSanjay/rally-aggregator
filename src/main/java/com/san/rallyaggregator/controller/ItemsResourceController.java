package com.san.rallyaggregator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.san.rallyaggregator.model.RallyItem;
import com.san.rallyaggregator.services.DefectAggregator;
import com.san.rallyaggregator.services.InputDataParser;
import com.san.rallyaggregator.services.RallyItemService;
import com.san.rallyaggregator.utils.RallyCategory;

@RestController
public class ItemsResourceController implements ServletContextAware  {
	
	@Resource(name = "rallyItemService")
	RallyItemService service;
	
	ServletContext context;

	@Resource(name = "aggregator")
	private DefectAggregator aggregator;
	
	@PostMapping("/items")
	public Integer postResource(@RequestParam MultipartFile file) throws IllegalStateException, IOException, InterruptedException, ExecutionException {
		
		/**
		 * MultipartFile.transferTo() fails to copy to the relative path, hence obtaining the absolute path
		 * from ServeletContext.
		 * Alternate approach is to do a manual copy to the created file 
		 * 
		 * 	    FileOutputStream fos = new FileOutputStream(inputFile); 
         *	    fos.write(file.getBytes());
         *      fos.close();
		 */
		
		File inputFile = new File(context.getRealPath("/") + file.getOriginalFilename());
		inputFile.createNewFile();
		file.transferTo(inputFile);

		return service.parseAndSaveRallyItems(inputFile);

	
	}

	/**
	 * Inject the ServletContext through ServletContextAware
	 * Alternatively autowire the property.
	 */
	@Override
	public void setServletContext(ServletContext context)
			throws BeansException {
		this.context = context;
		
	}
	
	@GetMapping("items")
	public List<RallyItem> getFilteredRallyItems(@RequestParam(name="Filter-Category") RallyCategory category, @RequestParam(name = "Value") String value) {
		try {
			return aggregator.getFilteredItemsByCategory(category, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
