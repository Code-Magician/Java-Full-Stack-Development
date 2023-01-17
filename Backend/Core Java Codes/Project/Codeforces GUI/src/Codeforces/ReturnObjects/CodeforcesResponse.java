package Codeforces.ReturnObjects;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeforcesResponse<E> {
	@SerializedName("status")	@Expose		private String status;
	@SerializedName("comment")	@Expose		private String comment;
	@SerializedName("result")	@Expose		private ArrayList<E> result;
	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public ArrayList<E> getResult()
	{
		return result;
	}
	
	public void setResult(ArrayList<E> result)
	{
		this.result = result;
	}
	
	
	@Override
	public String toString()
	{
		return 
				status + '\n' + 
				comment + '\n' + 
				result.get(0).toString() + '\n';
	}
}
