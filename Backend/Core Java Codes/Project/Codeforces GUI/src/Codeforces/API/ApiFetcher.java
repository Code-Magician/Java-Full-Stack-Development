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
	private CodeforcesResponse<Object> responseObj;
	private Gson gson;
	
	public ApiFetcher(String handle)
	{
		this.handle = handle;
		
		setResponse(new CodeforcesResponse<>());
		gson = new Gson();
	}
	
	public boolean fetchResult(URI url, Type type) throws IOException, InterruptedException
	{
		try {
			HttpRequest request = HttpRequest.newBuilder()
				.uri(url)
				.GET()
				.build();
		
			HttpClient client = HttpClient.newBuilder().build();
		
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			setResponse(new CodeforcesResponse<>());
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
	
	
	
	
	public void getUserInfo() throws IOException, InterruptedException, URISyntaxException
	{
		Type userInfoType = new TypeToken<CodeforcesResponse<UserInfo>>(){}.getType();
		
		while(!fetchResult(GetURI.UserInfo(handle), userInfoType))
			Thread.sleep(2500);
			
	}
	
	
	public void getUserRatings() throws IOException, InterruptedException, URISyntaxException
	{
		Type userRatingType = new TypeToken<CodeforcesResponse<UserRatings>>(){}.getType();
		
		while(!fetchResult(GetURI.UserRatings(handle), userRatingType))
			Thread.sleep(2500);
	}
	
	
	public void getUserBlogEntries() throws IOException, InterruptedException, URISyntaxException
	{
		Type userBlogEntriesType = new TypeToken<CodeforcesResponse<UserBlogEntries>>(){}.getType();

		while(!fetchResult(GetURI.UserBlogEntries(handle), userBlogEntriesType))
			Thread.sleep(2500);
	}
	
	
	public void getUserStatus() throws IOException, InterruptedException, URISyntaxException
	{
		Type userStatusType = new TypeToken<CodeforcesResponse<UserStatus>>(){}.getType();
		
		while(!fetchResult(GetURI.UserStatus(handle), userStatusType))
			Thread.sleep(2500);
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public CodeforcesResponse<Object> getResponse() {
		return responseObj;
	}

	public void setResponse(CodeforcesResponse<Object> responseObj) {
		this.responseObj = responseObj;
	}
}

























