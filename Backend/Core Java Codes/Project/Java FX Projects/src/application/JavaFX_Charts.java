package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class JavaFX_Charts extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group rootGroup = new Group();
		Scene scene = new Scene(rootGroup, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chart Demonstration");
		
//		BuildPieChart(scene);
//		BuildLineChart(scene); // Area Chart Similar to Line Chart.
//		BuildBarChart(scene);
		BuildBubbleChart(scene);
		
		primaryStage.show();
	}
	
	
	private void BuildBubbleChart(Scene scene) {
		NumberAxis xAxis = new NumberAxis(0, 70, 5);
		NumberAxis yAxis = new NumberAxis(0, 24, 4);
		xAxis.setLabel("Age"); yAxis.setLabel("Hours");
		
		BubbleChart<Float, Float> bubbleChart = new BubbleChart(xAxis, yAxis);
		bubbleChart.setTitle("Number of Hours Spent by Different Age Group");
		
		XYChart.Series male = new XYChart.Series<>();  
        male.setName("Male");  
        male.getData().add(new XYChart.Data(10,2));  
        male.getData().add(new XYChart.Data(15,7));  
        male.getData().add(new XYChart.Data(25,4));  
        male.getData().add(new XYChart.Data(35,6));  
        male.getData().add(new XYChart.Data(45,10));  
        male.getData().add(new XYChart.Data(55,13));  
        male.getData().add(new XYChart.Data(65,15));  
          
        XYChart.Series female = new XYChart.Series<>();  
        female.setName("Female");  
        female.getData().add(new XYChart.Data(10,1));  
        female.getData().add(new XYChart.Data(15,2));  
        female.getData().add(new XYChart.Data(25,9));  
        female.getData().add(new XYChart.Data(35,12));  
        female.getData().add(new XYChart.Data(45,15));  
        female.getData().add(new XYChart.Data(55,4));  
        female.getData().add(new XYChart.Data(65,2));  
        
        bubbleChart.getData().add(male);  
        bubbleChart.getData().add(female); 
        
        Group root = (Group)scene.getRoot();
	    root.getChildren().add(bubbleChart);
	}
	
	private void BuildBarChart(Scene scene)
	{
		 String Euro = "Euro";  
		 String Pound = "British Pound";  
		 String A_Dollar = "Austrelian Dollar";  
		 String frenc= "Swis Franc";  
		
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis(0, 2, .05);
		xAxis.setLabel("Currency");   yAxis.setLabel("Dollar Price");
		
		BarChart<String, Float> barChart = new BarChart(xAxis, yAxis);
		barChart.setTitle("Currency Bar Chart");
		
		XYChart.Series<String, Float> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data(Euro,0.83));  
	    series.getData().add(new XYChart.Data(Pound,0.73));  
	    series.getData().add(new XYChart.Data(frenc,1.00));  
	    series.getData().add(new XYChart.Data(A_Dollar,1.32));  
	    
	    barChart.getData().addAll(series);
	    
	    Group root = (Group)scene.getRoot();
	    root.getChildren().add(barChart);
	}
	
	private void BuildLineChart(Scene scene) {
		NumberAxis xAxis = new NumberAxis(2008, 2018, 1);
		NumberAxis yAxis = new NumberAxis(10, 80, 5);
		
		LineChart lineChart = new LineChart(xAxis, yAxis);
		
		xAxis.setLabel("Years"); 	yAxis.setLabel("Prices");
		
		XYChart.Series series = new XYChart.Series();
		
		series.setName("Stock Analysis");  
        series.getData().add(new XYChart.Data(2009,25));  
        series.getData().add(new XYChart.Data(2010,15));  
        series.getData().add(new XYChart.Data(2011,68));  
        series.getData().add(new XYChart.Data(2012,60));  
        series.getData().add(new XYChart.Data(2013,35));  
        series.getData().add(new XYChart.Data(2014,55));  
        series.getData().add(new XYChart.Data(2015,45));  
        series.getData().add(new XYChart.Data(2016,67));  
        series.getData().add(new XYChart.Data(2017,78));  
        
        lineChart.getData().add(series);
        
        Group rootGroup = (Group)scene.getRoot();
	    rootGroup.getChildren().add(lineChart);
	}
	
	private void BuildPieChart(Scene scene)
	{
		PieChart pieChart = new PieChart();
		
		ObservableList<Data> list = FXCollections.observableArrayList();  
	    list.addAll(new PieChart.Data("JavaScript", 30.8),  
	            new PieChart.Data("Ruby", 11.8),  
	            new PieChart.Data("Java", 10.8),  
	            new PieChart.Data("Python", 11.6),  
	            new PieChart.Data("PHP", 7.2),  
	            new PieChart.Data("Objective-C", 10.7),  
	            new PieChart.Data("C", 5.2),  
	            new PieChart.Data("C++", 4.3),  
	            new PieChart.Data("Go",3.8),  
	            new PieChart.Data("CSS", 3.8)  
	    );  
		
	    pieChart.setData(list);
	    pieChart.setLegendSide(Side.RIGHT);
	    pieChart.setTitle("Programming Languages");
	    
	    Group rootGroup = (Group)scene.getRoot();
	    rootGroup.getChildren().add(pieChart);
	}
}
