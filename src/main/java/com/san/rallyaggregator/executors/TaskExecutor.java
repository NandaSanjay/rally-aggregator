package com.san.rallyaggregator.executors;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.san.rallyaggregator.model.RallyItem;

public class TaskExecutor {
	
	private static final int EXECUTOR_POOL_SIZE = 3;
	private ExecutorService executor= Executors.newFixedThreadPool(EXECUTOR_POOL_SIZE);
	
	public List<RallyItem> submitParserTask(Callable task) throws InterruptedException, ExecutionException {
		
		Future<List<RallyItem>> future= executor.submit(task);	
		while(!future.isDone())
		{
			System.out.println("Waiting for CSVparser to complete executor");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return future.get();
		
	}	
	
	public File submitExporterTask(Callable task) throws InterruptedException, ExecutionException {
		
		Future<File> future= executor.submit(task);	
		while(!future.isDone())
		{
			System.out.println("Waiting for CSVparser to complete executor");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return future.get();
		
	}	
}
