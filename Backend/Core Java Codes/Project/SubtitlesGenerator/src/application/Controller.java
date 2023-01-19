package application;

import java.io.IOException;
import java.lang.foreign.ValueLayout.OfAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import java.util.concurrent.Flow.Subscriber;

import com.google.gson.Gson;

import javafx.fxml.Initializable;

public class Controller implements Initializable{
	private final String apiUrl = "https://api.assemblyai.com/v2/transcript";
	private final String audioUrl = "https://bit.ly/3yxKEIY";
	private final String apiKey = "c7dc6b7ef5c64db2bde2237bc3761dc8";
	
	private ReturnedText result;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fetch();
	}
	
	
	public void Fetch()
	{
		result = new ReturnedText();
		result.setAudio_url(audioUrl);
		
		Gson gson = new Gson();
		String jsonPostString = gson.toJson(result);
		
		
		try {
			HttpRequest request = HttpRequest.newBuilder()
									.uri(new URI(apiUrl))
									.header("Authorization", apiKey)
									.POST(BodyPublishers.ofString(jsonPostString))
									.build();
			
			HttpClient client = HttpClient.newBuilder().build();
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			
			if(response.statusCode() != 200)
			{
				System.out.println(response.body());
				return;
			}
			
			result = gson.fromJson(response.body(), ReturnedText.class);
			
			
			
//			request = HttpRequest.newBuilder()
//						.uri(new URI())
//			
			while(result.getStatus() != "completed")
			{
				
			}
			
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
