package Codeforces.API;

import Codeforces.ReturnObjects.CodeforcesResponse;
import Codeforces.ReturnObjects.*;




public final class FetchResult {
	private String handle;

	private ApiFetcher apiFetcher;
	
	private static CodeforcesResponse<UserInfo> userInfo;
	private static CodeforcesResponse<UserStatus> userStatus;
	private static CodeforcesResponse<UserRatings> userRatings;
	private static CodeforcesResponse<UserBlogEntries> userBlogEntries;
	
	
	public CodeforcesResponse<UserInfo> getUserInfo() {
		return userInfo;
	}


	public CodeforcesResponse<UserStatus> getUserStatus() {
		return userStatus;
	}


	public CodeforcesResponse<UserRatings> getUserRatings() {
		return userRatings;
	}


	public CodeforcesResponse<UserBlogEntries> getUserBlogEntries() {
		return userBlogEntries;
	}


	public void setHandle(String handle) {
		this.handle = handle;
		apiFetcher.setHandle(handle);
	}

	
	
	public FetchResult() {
		apiFetcher = new ApiFetcher(handle);
	}
	
	
	public void Fetch() 
	{
		userInfo = apiFetcher.getUserInfo();
		userStatus = apiFetcher.getUserStatus();
		userRatings = apiFetcher.getUserRatings();
		userBlogEntries = apiFetcher.getUserBlogEntries();
		
		System.out.println(userInfo.toString());
		System.out.println("Fetched");
	}
}
