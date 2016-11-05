package com.sri.split;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Splitter {
	
	

	public static void main(String[]args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//FileInputStream stream = new FileInputStream("input.txt");
		
		BufferedReader fileReader = new BufferedReader(new FileReader("/Users/RAMS/Documents/workspace/Workspace/src/com/sri/split/input.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = fileReader.readLine();
		    System.out.println("line:"+line);
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = fileReader.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println("output"+everything);
		    
		    try {
		    	ArrayList<String> al = new ArrayList<String>();
		    	
				JSONObject jsonObject = new JSONObject(everything);
				
				System.out.println("jsonObject : "+jsonObject.toString());
				
				JSONArray arr = jsonObject.getJSONArray("names");
				for (int i = 0; i < args.length; i++) {
					
					
					String sai      =      (String)jsonObject.get("sai");
					String siva     =      (String)jsonObject.get("siva");
					String sri      =      (String)jsonObject.get("sri");
					String praveen  =      (String)jsonObject.get("praveen");
					
					
					al.add("praveen");
					al.add("sai");
					al.add("siva");
					al.add("sri");
					
					
				
				}
				
				//JSONArray arr1 = jsonObject.getJSONArray("price");
				//for (int j = 0; j < args.length; j++) {
				
				//}
	           
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
		    fileReader.close();
		}
		
		
		int numOfPeople = readNumOfPeople(br);
		
		List<String> names = readPersonsNames(br, numOfPeople);
		
		List<Integer> moneySpentList = new ArrayList<>(); //TODO-change this to map with name as key and money as value or change this to type with name and money as members
		int totalMoney = readMoneySpentByEachPerson(br, names, moneySpentList);
		
		List<Integer> debitOrCreditMoneyList = calculateDebitCreditForEachPerson(numOfPeople, moneySpentList, totalMoney);
				
		
		StringBuilder response = splitAmongPersons(numOfPeople, names, debitOrCreditMoneyList);
		
		System.out.println("===========Final split result============");
		System.out.println(response);
		
	}



	public static StringBuilder splitAmongPersons(int numOfPeople, List<String> names,
			List<Integer> debitOrCreditMoneyList) {
		StringBuilder response = new StringBuilder();
		
		int index = 0;
		for(String name: names) {
			int currentPersonDCAmount = debitOrCreditMoneyList.get(index);
			if(currentPersonDCAmount > 0) {
				for(int i=0; i<numOfPeople; i++) {
					if(i == index) continue;
					int otherPersonDCAmount = debitOrCreditMoneyList.get(i);
					if(otherPersonDCAmount < 0) {
						int outstanding = currentPersonDCAmount + otherPersonDCAmount;
						
						if(outstanding < 0) {
							response.append(name + " gets " + currentPersonDCAmount + " from " + names.get(i) + "\n");
							debitOrCreditMoneyList.set(index, 0);
							debitOrCreditMoneyList.set(i, outstanding);
							break;
						} else {
							response.append(name + " gets " + modOfInteger(otherPersonDCAmount) + " from " + names.get(i) + "\n");
							debitOrCreditMoneyList.set(index, outstanding);
							currentPersonDCAmount = outstanding;
							debitOrCreditMoneyList.set(i, 0);
						}
					}
				}
			}
			index++;
		}
		return response;
	}



	public static List<Integer> calculateDebitCreditForEachPerson(int numOfPeople,
			List<Integer> moneySpentList, int totalMoney) {
		int eachPersonShare = totalMoney/numOfPeople;
		List<Integer> debitOrCreditMoneyList = new ArrayList<>();
		for(Integer personSpentMoney: moneySpentList)		
			debitOrCreditMoneyList.add(personSpentMoney-eachPersonShare);
		return debitOrCreditMoneyList;
	}



	public static int readMoneySpentByEachPerson(BufferedReader br, List<String> names, List<Integer> moneySpentList)
			throws IOException {
		
		int totalMoney = 0;
		
		for (String personName : names) {
			System.out.println("Enter money spent for: " + personName);
			int money = Integer.parseInt(br.readLine());
			moneySpentList.add(money);
			totalMoney += money;
		}
		return totalMoney;
	}



	public static List<String> readPersonsNames(BufferedReader br, int numOfPeople)
			throws IOException {
		List<String> names = new ArrayList<>(numOfPeople);
		
		for (int i = 0; i < numOfPeople; i++) {			
			System.out.println("Enter person" + (i+1) + "name: ");				
			names.add(br.readLine());		
		}
		return names;
	}



	public static int readNumOfPeople(BufferedReader br) throws IOException {
		System.out.println("How many pepoles in room?");
		int numOfPeople = Integer.parseInt(br.readLine());
		return numOfPeople;
	}
	
	
	
	public static Integer modOfInteger(Integer number) {
		if(number < 0)
			number *= -1;
		return number;
	}
	
	
}

/*
 * Put this whole project in GitHub
 * Pull this file into Dell eclipse
 */
