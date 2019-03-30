/**
 * 
 */
package com.san.rallyaggregator.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.*;
import com.san.rallyaggregator.model.RallyItem;

import com.san.rallyaggregator.repository.RallyItemsRepository;
import com.san.rallyaggregator.utils.RallyCategory;

/**
 * @author nandasan
 *
 */
@Service("aggregator")
public class DefectAggregator {
	
	@Resource
	RallyItemsRepository repository;

	public List<RallyItem> getFilteredItemsByCategory(RallyCategory category, String value) throws Exception
	{
		List<RallyItem> items = repository.findAll();
		switch (category) {
		case OWNER:
			return items.stream().filter(item -> item.getOwner().toLowerCase().contains(value.toLowerCase())).collect(toList());
		case SUBMITTER:
			return items.stream().filter(item -> item.getSubmitter().toLowerCase().contains(value.toLowerCase())).collect(toList());
		case SEVERITY:
			return items.stream().filter(item -> item.getSeverity().toLowerCase().contains(value.toLowerCase())).collect(toList());
		case STATE:
			return items.stream().filter(item -> item.getState().toLowerCase().contains(value.toLowerCase())).collect(toList());
		case TAG:
			return items.stream().filter(item -> item.getName().toLowerCase().contains(value.toLowerCase())).collect(toList());
		case KEYWORD:
			return items.stream()
					.filter(item -> (item.getName().toLowerCase().contains(value.toLowerCase())
					                 || item.getRequirement().toLowerCase().contains(value.toLowerCase())))
					.collect(toList());
		default:
			throw new Exception("Invalid category to filter");
		}
	}

}
