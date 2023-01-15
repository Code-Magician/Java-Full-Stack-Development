package Codeforces;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;





class GetURI{
	private String handle;
	
	
	public GetURI(String _handle) {
		handle = _handle;
	}
	
	public URI UserInfo() throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.info?handles=" + handle);
	}
	
	public URI UserRatings() throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.rating?handle=" + handle);
	}
	
	public URI UserBlogEntries() throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.blogEntries?handle=" + handle);
	}
	
	public URI UserStatus() throws URISyntaxException
	{
		return new URI("https://codeforces.com/api/user.status?handle=" + handle);
	}
}





public class ApiFetcher {
	private GetURI getURI;
	private String handle;
	
	public ApiFetcher()
	{
		
	}
	
	public void fetchResult(URI url,String handle) throws IOException, InterruptedException
	{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(url)
				.GET()
				.build();
		
		HttpClient client = HttpClient.newBuilder().build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
//		if(response.sta
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
}

























