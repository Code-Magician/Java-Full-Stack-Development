package Codeforces.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import Codeforces.API.FetchResult;
import Codeforces.ReturnObjects.UserBlogEntries;
import Codeforces.ReturnObjects.UserInfo;
import Codeforces.ReturnObjects.UserRatings;
import Codeforces.ReturnObjects.UserStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController implements Initializable {
	@FXML private ImageView userImage;
	@FXML private Label name;
	@FXML private Label username;
	@FXML private Label rank;
	@FXML private Label rating;
	@FXML private Label lastOnline;
	@FXML private Label registered;
	@FXML private Label friendOf;
	@FXML private Label address;
	@FXML private Label email;
	@FXML private Label totalSubmission;
	@FXML private Label totalContests;
	@FXML private Label totalBlogs;
	@FXML private Label maxUps;
	@FXML private Label maxDowns;
	@FXML private Label maxRank;
	@FXML private Label minRank;

	@FXML private PieChart languagesUsedPieChart;
	@FXML private PieChart verdictsPieChart;
	@FXML private PieChart problemTagsPieChart;
	@FXML private BarChart<String, Integer> problemIndexBarChart;
	@FXML private NumberAxis problemIndexBarChartNumberAxis;
	@FXML private BarChart<String, Integer> problemRatingsBarChart;
	@FXML private NumberAxis problemRatingsBarChartNumberAxis;
	
	
	
	private FetchResult fetchResult;
	
	private UserInfo userInfo;
	private ArrayList<UserStatus> submissionList;
	private ArrayList<UserRatings> contestList;
	private ArrayList<UserBlogEntries> blogsList;
	
	
	private TreeMap<String, Integer> languagesUsedMap; 
	private TreeMap<String, Integer> verdictsMap; 
	private TreeMap<Integer, Integer> problemRatingMap;
	private TreeMap<String, Integer> problemTagsMap;
	private TreeMap<Character, Integer> problemIndexMap;
	int maxUp, maxDown, bestRank, worstRank;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fetchResult = new FetchResult();
		
		userInfo = fetchResult.getUserInfo().getResult().get(0);
		submissionList = fetchResult.getUserStatus().getResult();
		contestList = fetchResult.getUserRatings().getResult();
		blogsList = fetchResult.getUserBlogEntries().getResult();
		
		
		languagesUsedMap = new TreeMap<>();
		verdictsMap = new TreeMap<>();
		problemRatingMap = new TreeMap<>();
		problemTagsMap = new TreeMap<>();
		problemIndexMap = new TreeMap<>();
		maxUp = 0;	maxDown = 0;
		bestRank = Integer.MAX_VALUE;	worstRank = Integer.MIN_VALUE;
		
		
		CalculateDetails();
		
		
		Image img = new Image(userInfo.getTitlePhoto());
		userImage.setImage(img);
		userImage.setPreserveRatio(true);
		
		name.setText(("Name : " + userInfo.getFirstName() + " " + userInfo.getLastName().toUpperCase()));
		username.setText(("Username : " + userInfo.getHandle()).toUpperCase());
		rank.setText(("Rank : " + userInfo.getRank() + "( max : " + userInfo.getMaxRank() + " )").toUpperCase());
		rating.setText(("Rating : " + userInfo.getRating().toString() + "( max : " + userInfo.getMaxRating() + " )").toUpperCase());
		lastOnline.setText(("Last Visit : " + userInfo.getLastOnlineTimeSeconds()).toUpperCase());
		registered.setText(("Registered : " + userInfo.getRegistrationTimeSeconds()).toUpperCase());
		friendOf.setText(("Friends of : " + userInfo.getFriendOfCount()).toUpperCase());
		address.setText(("Address : " + userInfo.getOrganization() + " " + userInfo.getCity() + " " + userInfo.getCountry()).toUpperCase());
		email.setText(("Email : " + userInfo.getEmail()).toUpperCase());
		totalSubmission.setText(("Submissions : " + submissionList.size()).toUpperCase());
		totalBlogs.setText(("Blogs : " + blogsList.size()).toUpperCase());
		totalContests.setText(("Contests : " + contestList.size()).toUpperCase());
		maxUps.setText(("Max Up : " + maxUp).toUpperCase());
		maxDowns.setText(("Max Down : " + maxDown).toUpperCase());
		maxRank.setText(("Best Rank : " + bestRank).toUpperCase());
		minRank.setText(("Worst Rank : " + worstRank).toUpperCase());
		
		
		
//		CountLanguagesUsed();
		MakeLanguagesUsedPieChart();
		MakeVerdictsPieChart();
		MakeProblemTagsPieChart();
		MakeProblemIndexBarChart();
		MakeProblemRatingsBarChart();
	}	
	
	
	
	private void CalculateDetails()
	{
		for(UserRatings x:contestList)
		{
			Integer oldRating = x.getOldRating();
			Integer newRating = x.getNewRating();
			Integer rank = x.getRank();
			
			maxUp = Math.max(maxUp, newRating - oldRating);
			maxDown = Math.min(maxDown, newRating - oldRating);
			bestRank = Math.min(bestRank, rank);
			worstRank = Math.max(worstRank, rank);
		}
	}
	
	
	private void CountLanguagesUsed()
	{
		for(UserStatus x:submissionList)
		{
			String key = x.getProgrammingLanguage();
			
			if(languagesUsedMap.containsKey(key))
			{
				int temp = languagesUsedMap.get(key);
				temp ++;
				
				languagesUsedMap.put(key, temp);
			}
			else {
				languagesUsedMap.put(key, 1);
			}
		}
	}
	
	
	private void CountVerdicts()
	{
		for(UserStatus x:submissionList)
		{
			String key = x.getVerdict();
			
			if(verdictsMap.containsKey(key))
			{
				int temp = verdictsMap.get(key);
				temp ++;
				
				verdictsMap.put(key, temp);
			}
			else {
				verdictsMap.put(key, 1);
			}
		}
	}
	
	private void CountProblemRating()
	{
		for(UserStatus x : submissionList)
		{
			if(x.getVerdict() != "OK")	continue;
			
			Integer key = x.getProblem().getRating();
			if(key == null) continue;
			
			if(problemRatingMap.containsKey(key))
			{
				Integer value = problemRatingMap.get(key);
				value++;
				
				problemRatingMap.put(key, value);
			}
			else {
				problemRatingMap.put(key, 1);
			}
		}
	}
	
	
	private void CountProblemIndex()
	{
		for(UserStatus x:submissionList)
		{
			if(x.getVerdict() != "OK")	continue;
			
			String keyStr = x.getProblem().getIndex();
			Character key = keyStr.charAt(0);
			
			if(problemIndexMap.containsKey(key))
			{
				Integer value = problemIndexMap.get(key);
				value++;
				
				problemIndexMap.put(key, value);
			}
			else {
				problemIndexMap.put(key, 1);
			}
		}
	}
	
	
	private void CountProblemTags()
	{
		for(UserStatus x:submissionList)
		{
			if(x.getVerdict() != "OK")	continue;
			
			ArrayList<String> tags = x.getProblem().getTags();
			
			for(String tag:tags)
			{	
				if(problemTagsMap.containsKey(tag))
				{
					Integer value = problemTagsMap.get(tag);
					value++;
					
					problemTagsMap.put(tag, value);
				}
				else {
					problemTagsMap.put(tag, 1);
				}
			}
		}
	}
	
	
	
	private void MakeLanguagesUsedPieChart()
	{
		CountLanguagesUsed();
		
		ObservableList<Data> list = FXCollections.observableArrayList();  
		
		for(Map.Entry<String, Integer> x: languagesUsedMap.entrySet())
		{
			String key = x.getKey();
			Integer value = x.getValue();
			
			list.add(new PieChart.Data(key, value));
		}
		
		languagesUsedPieChart.setData(list);
		languagesUsedPieChart.setLegendSide(Side.RIGHT);
		languagesUsedPieChart.setTitle("Languages Used");
	}
	
	
	private void MakeVerdictsPieChart()
	{
		CountVerdicts();
		
		ObservableList<Data> list = FXCollections.observableArrayList();  
		
		for(Map.Entry<String, Integer> x: verdictsMap.entrySet())
		{
			String key = x.getKey();
			Integer value = x.getValue();
			
			list.add(new PieChart.Data(key, value));
		}
		
		verdictsPieChart.setData(list);
		verdictsPieChart.setLegendSide(Side.RIGHT);
		verdictsPieChart.setTitle("Verdicts");
	}
	
	
	private void MakeProblemTagsPieChart()
	{
		CountProblemTags();
		
		ObservableList<Data> list = FXCollections.observableArrayList();  
		
		for(Map.Entry<String, Integer> x: problemTagsMap.entrySet())
		{
			String key = x.getKey();
			Integer value = x.getValue();
			
			list.add(new PieChart.Data(key, value));
		}
		
		problemTagsPieChart.setData(list);
		problemTagsPieChart.setLegendSide(Side.RIGHT);
		problemTagsPieChart.setTitle("Problem Tags");
	}
	
	
	private void MakeProblemIndexBarChart()
	{
		CountProblemIndex();
		
		int maxSolvedInIndex = 0;
		for(Map.Entry<Character, Integer> x:problemIndexMap.entrySet())
		{
			maxSolvedInIndex = Math.max(maxSolvedInIndex, x.getValue());
		}
		maxSolvedInIndex += 20;
		
		problemIndexBarChartNumberAxis.setLowerBound(0);
		problemIndexBarChartNumberAxis.setUpperBound(maxSolvedInIndex);
		
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for(Map.Entry<Character, Integer> x:problemIndexMap.entrySet())
		{
			series.getData().add(new XYChart.Data<String, Integer>(x.getKey().toString(), x.getValue()));
		}
		
		problemIndexBarChart.getData().addAll(series);
	}
	
	private void MakeProblemRatingsBarChart()
	{
		CountProblemRating();
		
		int maxSolvedInIndex = 0;
		for(Map.Entry<Integer, Integer> x:problemRatingMap.entrySet())
		{
			maxSolvedInIndex = Math.max(maxSolvedInIndex, x.getValue());
		}
		maxSolvedInIndex += 20;
		
		problemRatingsBarChartNumberAxis.setLowerBound(0);
		problemRatingsBarChartNumberAxis.setUpperBound(maxSolvedInIndex);
		
		
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for(Map.Entry<Integer, Integer> x:problemRatingMap.entrySet())
		{
			series.getData().add(new XYChart.Data<String, Integer>(Integer.toString(x.getKey()), x.getValue()));
			System.out.println(x.getKey() + " : " + x.getValue());
		}
		
		problemRatingsBarChart.getData().addAll(series);
	}
}
