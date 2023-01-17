package Codeforces.ReturnObjects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Codeforces.Helper.Helper;

public class Problem {
	enum Type {
		PROGRAMMING, QUESTION
	};
	
	
	@SerializedName("contestId")		@Expose private Integer contestId;
	@SerializedName("problemsetName")	@Expose private String problemsetName;
	@SerializedName("index")			@Expose private String index;
	@SerializedName("name")				@Expose private String name;
	@SerializedName("type")				@Expose private Type type;
	@SerializedName("points")			@Expose private Float points;
	@SerializedName("rating")			@Expose private Integer rating;
	@SerializedName("tags")				@Expose private ArrayList<String> tags;
	
	
	
	
	@Override
	public String toString()
	{
		return 
//				contestId + "\n" + 
				problemsetName + "\n" + 
				index + "\n" + 
				name + "\n" + 
				type + "\n" + 
				points + "\n" + 
				rating + "\n" + 
				tags + "\n";
	}
	
	
	
	public Integer getContestId()
	{
		return contestId;
	}
	
	
	public String getProblemsetName() {
		return (problemsetName == null)?Helper.defaultString:problemsetName;
	}


	public String getIndex() {
		return index;
	}



	public String getName() {
		return name;
	}



	public Type getType() {
		return type;
	}



	public Float getPoints() {
		return points;
	}



	public Integer getRating() {
		return rating;
	}



	public ArrayList<String> getTags() {
		return tags;
	}
}
