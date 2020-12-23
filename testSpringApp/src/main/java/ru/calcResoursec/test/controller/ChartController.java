package ru.calcResoursec.test.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.model.Purchase;
import ru.calcResoursec.test.model.User;
import ru.calcResoursec.test.repository.PurchaseRepository;
import ru.calcResoursec.test.repository.CheckRepository;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

@Controller
public class ChartController{
	
	
	@Autowired
	CheckRepository checkRepository;

	//при вводе /chart после локалхоста нас перекидывает на Chart
	@GetMapping("/chart")
	public String getMainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		
		getChart();
		return "chart";
	}

	@PostMapping("/chart")
	public String addPurchase(@AuthenticationPrincipal User user,
							  Map<String, Object> model) {

		getChart();
		
		return "chart";
	}

		public void getChart()  {

			
			  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			  
			  List<Check> checks = checkRepository.findAll();
			  
			  SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			  
			  for(Check check:checks) {
				  try {
					formatter.parse(check.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }

			  checks.sort(Comparator.comparing(o -> o.getDate()));
			  
			  for(Check check:checks) {
				  try {
					Date dateForm = formatter.parse(check.getDate());
					dataset.addValue(check.getSum(), "check", formatter.format(dateForm));
					System.out.println(formatter.format(dateForm));
				  } catch (ParseException e) {
						e.printStackTrace();
					}
			  }
			  
			  
			  JFreeChart lineChartObject = ChartFactory.createLineChart(
			     "Check use","Date",
			     "Summa Check",
			     dataset,PlotOrientation.VERTICAL,
			     true,true,false);

			  int width = 640;    
			  int height = 480;    

			  File lineChart = new File( "src\\main\\webapp\\lineChart.png" ); 
			  
			  try {
				ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
}