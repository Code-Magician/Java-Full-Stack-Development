package Codeforces.ReturnObjects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import Codeforces.Helper.Helper;





class Member{
	@SerializedName("handle")			@Expose private String handle;
	@SerializedName("name")				@Expose private String name;
	
	
	@Override 
	public String toString()
	{
		return 
				handle + "\n" +
				name + "\n";
	}


	public String getHandle() {
		return handle;
	}


	public String getName() {
		return (name== null)? Helper.defaultString:name;
	}
}





class Party {
	enum Type{
		CONTESTANT, PRACTICE, VIRTUAL, MANAGER, OUT_OF_COMPETITION
	}
	
	
//	@SerializedName("contestId")			@Expose private Integer contestId;
//	@SerializedName("teamId")				@Expose private Integer teamId;
//	@SerializedName("teamName")				@Expose private String teamName;
//	@SerializedName("ghost")				@Expose private Boolean ghost;
//	@SerializedName("room")					@Expose private Integer room;
	@SerializedName("members")				@Expose private ArrayList<Member> members;
	@SerializedName("participantType")		@Expose private Type participantType;
	@SerializedName("startTimeSeconds")		@Expose private Long startTimeSeconds;
	
	
	
	@Override
	public String toString()
	{
		return 
//				contestId + "\n" + 
				members.toString() + "\n" + 
				participantType + "\n" + 
//				teamId + "\n" + 
//				teamName + "\n" + 
//				ghost + "\n" + 
//				room + "\n" + 
				startTimeSeconds + "\n"; 
	}
	
	
	
	public ArrayList<Member> getMembers() {
		return members;
	}


	public Type getParticipantType() {
		return participantType;
	}
	

	public String getStartTimeSeconds() {
		return (startTimeSeconds == null)
				? Helper.defaultString
				: Helper.GetDateTimeFromUnixFormat(startTimeSeconds);
	}
}





class Problem {
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





public class UserStatus {
	enum Verdict {
		FAILED, 
		OK, 
		PARTIAL, 
		COMPILATION_ERROR, 
		RUNTIME_ERROR, 
		WRONG_ANSWER, 
		PRESENTATION_ERROR, 
		TIME_LIMIT_EXCEEDED, 
		MEMORY_LIMIT_EXCEEDED, 
		IDLENESS_LIMIT_EXCEEDED, 
		SECURITY_VIOLATED, CRASHED, 
		INPUT_PREPARATION_CRASHED, 
		CHALLENGED, SKIPPED, 
		TESTING, REJECTED
	}
	
	
//	@SerializedName("contestId")			@Expose private Integer contestId;
//	@SerializedName("points")				@Expose private Float points;
	@SerializedName("id")					@Expose private Integer id;
	@SerializedName("creationTimeSeconds")	@Expose private Long creationTimeSeconds;
	@SerializedName("relativeTimeSeconds")	@Expose private Long relativeTimeSeconds;
	@SerializedName("problem")				@Expose private Problem problem;
	@SerializedName("author")				@Expose private Party author;
	@SerializedName("programmingLanguage")	@Expose private String programmingLanguage;
	@SerializedName("passedTestCount")		@Expose private Integer passedTestCount;
	@SerializedName("timeConsumedMillis")	@Expose private Integer timeConsumedMillis;
	@SerializedName("memoryConsumedBytes")	@Expose private Integer memoryConsumedBytes;
	@SerializedName("verdict")				@Expose private Verdict verdict;




	@Override
	public String toString() {
		return 
				id + "\n" + 
//				contestId + "\n" + 
				creationTimeSeconds + "\n" + 
				relativeTimeSeconds + "\n" + 
				"Problem : " + problem.toString() + "\n" + 
				"Author : " +author.toString() + "\n" + 
				programmingLanguage + "\n" + 
				passedTestCount + "\n" + 
				timeConsumedMillis + "\n" + 
				memoryConsumedBytes + "\n" + 
//				points + "\n" + 
				verdict.name() + "\n";
	}
	
	
	
	public Integer getId() {
		return id;
	}


	public String getCreationTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(creationTimeSeconds);
	}



	public String getRelativeTimeSeconds() {
		return Helper.GetDateTimeFromUnixFormat(relativeTimeSeconds);
	}



	public Problem getProblem() {
		return problem;
	}



	public Party getAuthor() {
		return author;
	}



	public String getProgrammingLanguage() {
		return programmingLanguage;
	}



	public Integer getPassedTestCount() {
		return passedTestCount;
	}



	public Integer getTimeConsumedMillis() {
		return timeConsumedMillis;
	}



	public Integer getMemoryConsumedBytes() {
		return memoryConsumedBytes;
	}


	public String getVerdict() {
		return (verdict == null)?Helper.defaultString:verdict.name();
	}
}


















