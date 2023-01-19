package application;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


class Word{
	@SerializedName("text") 	@Expose 	private String text;
	@SerializedName("start") 	@Expose 	private Integer start;
	@SerializedName("end") 		@Expose 	private Integer end;
	
	
	public String getText() {
		return text;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public Integer getEnd() {
		return end;
	}
}

public class ReturnedText {
	@SerializedName("status") 		@Expose 	private String status;
	@SerializedName("id") 	  		@Expose 	private String id;
	@SerializedName("audio_url") 	@Expose 	private String audio_url;
	@SerializedName("text") 		@Expose 	private String text;
	@SerializedName("words") 		@Expose 	private ArrayList<Word> words;
	
	
	
	public String getAudio_url() {
		return audio_url;
	}
	
	public void setAudio_url(String audio_url) {
		this.audio_url = audio_url;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public ArrayList<Word> getWords() {
		return words;
	}
}
