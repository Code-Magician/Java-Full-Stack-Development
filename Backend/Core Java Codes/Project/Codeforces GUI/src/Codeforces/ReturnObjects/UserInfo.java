package Codeforces.ReturnObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Codeforces.Helper.Helper;





public class UserInfo {
//	@SerializedName("handle")					@Expose private String handle;
//	@SerializedName("titlePhoto")				@Expose	private String titlePhoto;
//	@SerializedName("vkId")						@Expose	private String vkId;
//	@SerializedName("openId")					@Expose	private String openId;
	@SerializedName("email")					@Expose	private String email;
	@SerializedName("firstName")				@Expose	private String firstName;
	@SerializedName("lastName")					@Expose	private String lastName;
	@SerializedName("country")					@Expose	private String country;
	@SerializedName("city")						@Expose	private String city;
	@SerializedName("organization")				@Expose	private String organization;
	@SerializedName("contribution")				@Expose	private Integer contribution;
	@SerializedName("rank")						@Expose	private String rank;
	@SerializedName("rating")					@Expose	private Integer rating;
	@SerializedName("maxRank")					@Expose	private String maxRank;
	@SerializedName("maxRating")				@Expose	private Integer maxRating;
	@SerializedName("lastOnlineTimeSeconds")	@Expose	private Long lastOnlineTimeSeconds;
	@SerializedName("registrationTimeSeconds")	@Expose	private Long registrationTimeSeconds;
	@SerializedName("friendOfCount")			@Expose	private Integer friendOfCount;
	@SerializedName("avatar")					@Expose	private String avatar;
	
	
	@Override
	public String toString()
	{
		return 
//				handle + '\n' +
				email + '\n' +
				firstName + '\n' +
				lastName + '\n' +
				country + '\n' +
				city + '\n' +
				organization + '\n' +
				contribution + '\n' +
				rank + '\n' +
				rating + '\n' +
				maxRank + '\n' +
				maxRating + '\n' +
				friendOfCount + '\n' +
				avatar;
	}
	
	

	public String getEmail() {
		return (email == null)? Helper.defaultString: email;
	}
	
	
	public String getFirstName() {
		return (firstName == null)? Helper.defaultString: firstName;
	}


	public String getLastName() {
		return (lastName == null)? Helper.defaultString: lastName;
	}


	public String getCountry() {
		return (country == null)? Helper.defaultString: country;
	}


	public String getCity() {
		return (city == null)? Helper.defaultString: city;
	}


	public String getOrganization() {
		return (organization == null)? Helper.defaultString: organization;
	}


	public Integer getContribution() {
		return contribution;
	}


	public String getRank() {
		return rank;
	}


	public Integer getRating() {
		return rating;
	}

	public String getMaxRank() {
		return maxRank;
	}


	public Integer getMaxRating() {
		return maxRating;
	}


	public String getLastOnlineTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(lastOnlineTimeSeconds);
	}


	public String getRegistrationTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(registrationTimeSeconds);
	}


	public Integer getFriendOfCount() {
		return friendOfCount;
	}

	public String getAvatar() {
		return avatar;
	}
}
