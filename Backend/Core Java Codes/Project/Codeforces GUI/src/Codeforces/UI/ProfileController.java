package Codeforces.UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import javafx.scene.chart.PieChart;
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

	@FXML private PieChart languagesUsedPieChart;
	
	
	private FetchResult fetchResult;
	
	private UserInfo userInfo;
	private ArrayList<UserStatus> submissionList;
	private ArrayList<UserRatings> contestList;
	private ArrayList<UserBlogEntries> blogsList;
	
	private HashMap<String, Integer> languagesUsedMap; 
	private HashMap<String, Integer> verdictsMap; 
	private HashMap<Integer, Integer> problemRatingMap;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fetchResult = new FetchResult();
		
		userInfo = fetchResult.getUserInfo().getResult().get(0);
		submissionList = fetchResult.getUserStatus().getResult();
		contestList = fetchResult.getUserRatings().getResult();
		blogsList = fetchResult.getUserBlogEntries().getResult();
		
		
		languagesUsedMap = new HashMap<>();
		verdictsMap = new HashMap<>();
		
		
		
		Image img = new Image(userInfo.getTitlePhoto());
		userImage.setImage(img);
		
		name.setText("Name : " + userInfo.getFirstName() + " " + userInfo.getLastName());
		username.setText("Username : " + userInfo.getHandle());
		rank.setText("Rank : " + userInfo.getRank() + "(max : " + userInfo.getMaxRank() + " )");
		rating.setText("Rating : " + userInfo.getRating().toString() + "(max : " + userInfo.getMaxRating() + " )");
		lastOnline.setText("Last Visit : " + userInfo.getLastOnlineTimeSeconds());
		registered.setText("Registered : " + userInfo.getRegistrationTimeSeconds());
		friendOf.setText("Friends of : " + userInfo.getFriendOfCount());
		address.setText("Address : " + userInfo.getOrganization() + " " + userInfo.getCity() + " " + userInfo.getCountry());
		email.setText("Email : " + userInfo.getEmail());
		totalSubmission.setText("Submissions : " + submissionList.size());
		totalBlogs.setText("Blogs : " + blogsList.size());
		totalContests.setText("Contests : " + contestList.size());
		
		
		
//		CountLanguagesUsed();
//		MakeLanguagesUsedPieChart();
//		MakeVerdictsPieChart();
		
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
				languagesUsedMap.put(key, 0);
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
				verdictsMap.put(key, 0);
			}
		}
	}
	
	private void CountProblemRating()
	{
		for(UserStatus x : submissionList)
		{
			Integer key = x.getProblem().getPoints().intValue();
			
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
		
		languagesUsedPieChart.setData(list);
		languagesUsedPieChart.setLegendSide(Side.RIGHT);
		languagesUsedPieChart.setTitle("Verdicts");
	}
}
