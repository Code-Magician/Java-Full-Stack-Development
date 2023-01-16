package Codeforces.ReturnObjects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Codeforces.Helper.Helper;

public class UserBlogEntries {
//	@SerializedName("originalLocale")				@Expose private String 	originalLocale;
//	@SerializedName("authorHandle")					@Expose private String 	authorHandle;
//	@SerializedName("locale")						@Expose private String 	locale;
	@SerializedName("id")							@Expose private Integer id;
	@SerializedName("creationTimeSeconds")			@Expose private Integer creationTimeSeconds;
	@SerializedName("title")						@Expose private String 	title;
	@SerializedName("content")						@Expose private String 	content;
	@SerializedName("modificationTimeSeconds")		@Expose private Long modificationTimeSeconds;
	@SerializedName("allowViewHistory")				@Expose private Boolean allowViewHistory;
	@SerializedName("tags")							@Expose private ArrayList<String> 	tags;
	@SerializedName("rating")						@Expose private Integer rating;
	
	


	@Override
	public String toString()
	{
		return 
				id + "\n" +
//				originalLocale + "\n" +
				creationTimeSeconds + "\n" +
//				authorHandle + "\n" +
				title + "\n" +
				content + "\n" +
//				locale + "\n" +
				modificationTimeSeconds + "\n" +
				allowViewHistory + "\n" +
				tags + "\n" +
				rating;
	}
	
	
	
	public Integer getId() {
		return id;
	}



	public Integer getCreationTimeSeconds() {
		return creationTimeSeconds;
	}



	public String getTitle() {
		return title;
	}



	public String getContent() {
		return (content == null)? Helper.defaultString: content;
	}



	public String getModificationTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(modificationTimeSeconds);
	}



	public Boolean getAllowViewHistory() {
		return allowViewHistory;
	}



	public ArrayList<String> getTags() {
		return tags;
	}



	public Integer getRating() {
		return rating;
	}

}
