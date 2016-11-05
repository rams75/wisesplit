package com.sri.split.model;

import java.util.List;

public class SplitData {
	
	private Integer numberOfPeople;
	
	private List<String> names;
	
	private List<Integer> expenditures;
	
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public void setNames(List<String> names) {
		this.names = names;
	}
	
	public List<Integer> getExpenditures() {
		return expenditures;
	}
	
	public void setExpenditures(List<Integer> expenditures) {
		this.expenditures = expenditures;
	}
	
	@Override
	public String toString() {
		return "SplitData [numberOfPeople=" + numberOfPeople + ", names=" + names
				+ ", expenditures=" + expenditures + "]";
	}
	
	
}