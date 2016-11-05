package com.sri.split.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sri.split.Splitter;

public class MultipleSplitter {
	
private static FileOutputStream fop;

public static void main(String[] args) throws IOException {
		
		String inputSplitData = readFileContentIntoString();
		
		SplitData splitData  =  convertJSONStringToObject(inputSplitData);
		
		int totalMoney = 0;
		
		for(Integer expenditure: splitData.getExpenditures()) 
			totalMoney += expenditure;
		
			
		List<Integer> debitOrCreditMoneyList = Splitter.calculateDebitCreditForEachPerson(splitData.getNumberOfPeople(), splitData.getExpenditures(), totalMoney);
				
		
		StringBuilder response = Splitter.splitAmongPersons( splitData.getNumberOfPeople(), splitData.getNames(), debitOrCreditMoneyList);
		
		
		File outputFile = new File("/Users/RAMS/Documents/workspace/Workspace/src/com/sri/split/output.txt");
		fop = new FileOutputStream(outputFile);

		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}

		// get the content in bytes
		byte[] contentInBytes = response.toString().getBytes();

		fop.write(contentInBytes);
		System.out.println("Split among people is done and the output file is generated: /Users/RAMS/Documents/workspace/Workspace/src/com/sri/split/output.txt");
		
		
	}

	private static SplitData convertJSONStringToObject( String inputSplitData ) throws IOException,
			JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		SplitData splitData = mapper.readValue(inputSplitData, SplitData.class);
		return splitData;
	}
	
	

	private static String readFileContentIntoString() throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		File file = new File("/Users/RAMS/Documents/workspace/Workspace/src/com/sri/split/input.txt");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String inputSplitData = new String(data, "UTF-8");
		return inputSplitData;
	}


	
	
	
	
	
	
	
}
