package Codeforces.API;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import Codeforces.ReturnObjects.CodeforcesResponse;
import Codeforces.ReturnObjects.UserBlogEntries;
import Codeforces.ReturnObjects.UserInfo;
import Codeforces.ReturnObjects.UserRatings;
import Codeforces.ReturnObjects.UserStatus;


class GetURI{
	public static URI UserInfo(String handle) throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.info?handles=" + handle);
	}
	
	public static URI UserRatings(String handle) throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.rating?handle=" + handle);
	}
	
	public static URI UserBlogEntries(String handle) throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.blogEntries?handle=" + handle);
	}
	
	public static URI UserStatus(String handle) throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.status?handle=" + handle);
	}
}





public class ApiFetcher {
	private String handle;
	private CodeforcesResponse responseObj;
	private Gson gson;
	
	public ApiFetcher(String handle)
	{
		this.handle = handle;
		
		setResponse(new CodeforcesResponse<>());
		gson = new Gson();
	}
	
	public <T> boolean fetchResult(URI url, Type type, Class<T> input) 
	{
		try {
			HttpRequest request = HttpRequest.newBuilder()
				.uri(url)
				.GET()
				.build();
		
			HttpClient client = HttpClient.newBuilder().build();
		
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println(response.body());
			
			setResponse(new CodeforcesResponse<T>());
			setResponse(gson.fromJson(response.body(), type));
			
			if(response.statusCode() == 200)
				return true;
			else return false;
		}catch (IOException e) {
			System.out.println("Fetch1 : " + e.getMessage());
		}catch (InterruptedException e) {
			System.out.println("Fetch2 : " + e.getMessage());
		}catch (Exception e) {
			System.out.println("Fetch3 : " + e.getMessage());
		}
		
		return true;
	}
	
	
	
	
	public CodeforcesResponse<UserInfo> getUserInfo() 
	{
		Type userInfoType = new TypeToken<CodeforcesResponse<UserInfo>>(){}.getType();
		
		try {
			if(!fetchResult(GetURI.UserInfo(handle), userInfoType, UserInfo.class))
				System.out.println(responseObj.getComment());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fetched User Info");
		return responseObj;
	}
	
	
	public CodeforcesResponse<UserRatings> getUserRatings() 
	{
		Type userRatingType = new TypeToken<CodeforcesResponse<UserRatings>>(){}.getType();
		
		try {
			if(!fetchResult(GetURI.UserRatings(handle), userRatingType, UserRatings.class))
				System.out.println(responseObj.getComment());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fetched User Ratings");
		
		return responseObj;
	}
	
	
	public CodeforcesResponse<UserBlogEntries> getUserBlogEntries() 
	{
		Type userBlogEntriesType = new TypeToken<CodeforcesResponse<UserBlogEntries>>(){}.getType();

		try {
			if(!fetchResult(GetURI.UserBlogEntries(handle), userBlogEntriesType, UserBlogEntries.class))
				System.out.println(responseObj.getComment());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fetched User BlogEntries");
		
		return responseObj;
	}
	
	
	public CodeforcesResponse<UserStatus> getUserStatus() 
	{
		Type userStatusType = new TypeToken<CodeforcesResponse<UserStatus>>(){}.getType();
		
		try {
			if(!fetchResult(GetURI.UserStatus(handle), userStatusType, UserStatus.class))
				System.out.println(responseObj.getComment());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Fetched User Status");
		
		return responseObj;
	}
	
	
	public void setHandle(String handle) {
		this.handle = handle;
	}

	public<T> CodeforcesResponse<T> getResponse() {
		return responseObj;
	}

	public<T> void setResponse(CodeforcesResponse<T> responseObj) {
		this.responseObj = responseObj;
	}
}

























