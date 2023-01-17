package Codeforces.ReturnObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Codeforces.Helper.Helper;

public class UserRatings {
//	@SerializedName("handle")					@Expose private String 	handle;
	@SerializedName("contestId")				@Expose private Integer contestId;
	@SerializedName("contestName")				@Expose private String 	contestName;
	@SerializedName("rank")						@Expose private Integer rank;
	@SerializedName("ratingUpdateTimeSeconds")	@Expose private Long ratingUpdateTimeSeconds;
	@SerializedName("oldRating")				@Expose private Integer oldRating;
	@SerializedName("newRating")				@Expose private Integer newRating;	
	
	

	@Override
	public String toString()
	{
		return 
				contestId + "\n" + 
				contestName + "\n" + 
//				handle + "\n" + 
				rank + "\n" + 
				ratingUpdateTimeSeconds + "\n" + 
				oldRating + "\n" + 
				newRating;
 	}
	
	
	public Integer getContestId() {
		return contestId;
	}


	public String getContestName() {
		return contestName;
	}


	public Integer getRank() {
		return rank;
	}


	public String getRatingUpdateTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(ratingUpdateTimeSeconds);
	}


	public Integer getOldRating() {
		return oldRating;
	}


	public Integer getNewRating() {
		return newRating;
	}

}
