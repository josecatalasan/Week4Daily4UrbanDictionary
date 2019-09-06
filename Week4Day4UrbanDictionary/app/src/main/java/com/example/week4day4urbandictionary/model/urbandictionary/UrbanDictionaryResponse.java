package com.example.week4day4urbandictionary.model.urbandictionary;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UrbanDictionaryResponse{

	@SerializedName("list")
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"UrbanDictionaryResponse{" + 
			"list = '" + list + '\'' + 
			"}";
		}
}