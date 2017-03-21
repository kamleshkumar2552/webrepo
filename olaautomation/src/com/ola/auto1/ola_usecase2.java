package com.ola.auto1;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Set;
//package com.olahtek.controller;
import java.io.*;
import java.util.Arrays;
import java.util.regex.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.csvreader.CsvReader;


public class ola_usecase2 {

 public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
  CloseableHttpClient httpClient;
 try {
  //getting user id
	 
	 
	 
	 String getrandomemailid=getRandomemailid();
	   
	   JSONObject fileobj = new JSONObject();
		 fileobj.put("lname", "gopal");
		 fileobj.put("formType", "Userform");
		 fileobj.put("email", getrandomemailid);
		 fileobj.put("name", "Arun");
		 fileobj.put("password", "Testgr1@gmail");
		 
		 FileWriter out=new FileWriter("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/00_Name_In.json");
		 out.write(fileobj.toString());
		 out.flush();
		 out.close();
	  
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
   JSONParser parser = new JSONParser();
   FileReader jsonObj2 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/00_Name_In.json");
   JSONObject jsonObj_2 = (JSONObject) parser.parse(jsonObj2);
   String link2 = "http://192.168.1.241:8080/service/Register/";
   String user_id = getuserid(jsonObj_2, link2);
   System.out.println("user id we are getting" + user_id);
 
   
   CsvReader products = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.csv");
   products.readHeaders();
  while (products.readRecord())
   {
    String SL_No = products.get("SL_No");
    String Testcase_Id = products.get("TestCase_ID");
    String Inputfile = products.get("Input_file");
    String Outputfile = products.get("Output_file");
    String Url = products.get("URL");
    System.out.println(SL_No + "  : " + Testcase_Id + " : " + Inputfile + " : " + Outputfile + " : " + Url);
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String user_id1 = connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   
 
   FileReader jsonObj5 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/09_Insert_Emg_Hardcode_In.json");
   JSONObject jsonObj_5 = (JSONObject) parser.parse(jsonObj5);
   jsonObj_5.put("user_id", user_id);
   String link5 = "http://192.168.1.241:8080/service/GoalEmergencyFund1/";
   String goal_id = getgoalid(jsonObj_5, link5);
   products.close();
  
   
 CsvReader olah = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.csv");
   olah.readHeaders();
   while (olah.readRecord()) 
   {
    String SL_No = olah.get("SL_No");
    String Testcase_Id = olah.get("TestCase_ID");
    String Inputfile = olah.get("Input_file");
    String Outputfile = olah.get("Output_file");
    String Url = olah.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
      
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
   
    String abc = usecase1(jsonObj_1, link1, Outfile, Testcase_Id, user_id, goal_id);

   }

   olah.close();
   
    
   CsvReader olah1 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.1.csv");
   olah1.readHeaders();
   while (olah1.readRecord()) 
   {
    String SL_No = olah1.get("SL_No");
    String Testcase_Id = olah1.get("TestCase_ID");
    String Inputfile = olah1.get("Input_file");
    String Outputfile = olah1.get("Output_file");
    String Url = olah1.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
      
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
   
    String abc = usecase11(jsonObj_1, link1, Outfile, Testcase_Id, user_id);

   }

  olah1.close();
  
  
 
  
  FileReader jsonObj6 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/16_Coll1_Insert_Hardcode_In.json");
   JSONObject jsonObj_6 = (JSONObject) parser.parse(jsonObj6);
   jsonObj_6.put("user_id", user_id);
   String link6 = "http://192.168.1.241:8080/service/GoalCollegeEducation/";
  
   String collegegoalid = getcollegegoadid(jsonObj_6, link6);

   
 
   
    
   CsvReader olah2 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.2.csv");
   olah2.readHeaders();
   while (olah2.readRecord()) 
   {
    String SL_No = olah2.get("SL_No");
    String Testcase_Id = olah2.get("TestCase_ID");
    String Inputfile = olah2.get("Input_file");
    String Outputfile = olah2.get("Output_file");
    String Url = olah2.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah2.close();
   
   
   
 
   FileReader jsonObj7 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/24_Coll2_Insert_Hardcode_In.json");
   JSONObject jsonObj_7 = (JSONObject) parser.parse(jsonObj7);
   jsonObj_7.put("user_id", user_id);
   String link7 = "http://192.168.1.241:8080/service/GoalCollegeEducation/";
 
   String collegegoalid2 = getcollegegoadid2(jsonObj_7, link7);
   
   
    
   
   
   
   CsvReader olah3 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.3.csv");
   olah3.readHeaders();
   while (olah3.readRecord()) 
   {
    String SL_No = olah3.get("SL_No");
    String Testcase_Id = olah3.get("TestCase_ID");
    String Inputfile = olah3.get("Input_file");
    String Outputfile = olah3.get("Output_file");
    String Url = olah3.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah3.close();
  
   
   
   
   CsvReader olah4 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.4.csv");
   olah4.readHeaders();
   while (olah4.readRecord()) 
   {
    String SL_No = olah4.get("SL_No");
    String Testcase_Id = olah4.get("TestCase_ID");
    String Inputfile = olah4.get("Input_file");
    String Outputfile = olah4.get("Output_file");
    String Url = olah4.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase121(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah4.close();
   
   
   CsvReader olah5 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.5.csv");
   olah5.readHeaders();
   while (olah5.readRecord()) 
   {
    String SL_No = olah5.get("SL_No");
    String Testcase_Id = olah5.get("TestCase_ID");
    String Inputfile = olah5.get("Input_file");
    String Outputfile = olah5.get("Output_file");
    String Url = olah5.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah5.close();
   
   
   
   
   

   FileReader jsonObj8 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/30_Repl_House_Insert_Hardcode_In.json");
   JSONObject jsonObj_8 = (JSONObject) parser.parse(jsonObj8);
   jsonObj_8.put("user_id", user_id);
   String link8 = "http://192.168.1.241:8080/service/Goalhouse/";
 
   String replacehousegoalid = replacehousegoalid(jsonObj_8, link8);
   
   
   
   
   CsvReader olah6 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.6.csv");
   olah6.readHeaders();
   while (olah6.readRecord()) 
   {
    String SL_No = olah6.get("SL_No");
    String Testcase_Id = olah6.get("TestCase_ID");
    String Inputfile = olah6.get("Input_file");
    String Outputfile = olah6.get("Output_file");
    String Url = olah6.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah6.close();
   
   
   
   FileReader jsonObj9 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/33_Car_Insert_Hardcode_In.json");
   JSONObject jsonObj_9 = (JSONObject) parser.parse(jsonObj9);
   jsonObj_9.put("user_id", user_id);
   String link9 = "http://192.168.1.241:8080/service/Goalcar/";
 
   String cargoalid = cargoalid(jsonObj_9, link9);
   
   
   CsvReader olah7 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.7.csv");
   olah7.readHeaders();
   while (olah7.readRecord()) 
   {
    String SL_No = olah7.get("SL_No");
    String Testcase_Id = olah7.get("TestCase_ID");
    String Inputfile = olah7.get("Input_file");
    String Outputfile = olah7.get("Output_file");
    String Url = olah7.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah7.close();
   
   
   FileReader jsonObj10 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/36_Cust_Insert_Hardcode_In.json");
   JSONObject jsonObj_10 = (JSONObject) parser.parse(jsonObj10);
   jsonObj_10.put("user_id", user_id);
   String link10 = "http://192.168.1.241:8080/service/Goalcustomized/";
 
   String customizedgoalid = customizedgoalid(jsonObj_10, link10);
   
   
   
   
   
   CsvReader olah8 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.8.csv");
   olah8.readHeaders();
   while (olah8.readRecord()) 
   {
    String SL_No = olah8.get("SL_No");
    String Testcase_Id = olah8.get("TestCase_ID");
    String Inputfile = olah8.get("Input_file");
    String Outputfile = olah8.get("Output_file");
    String Url = olah8.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah8.close();
   
   
   FileReader jsonObj11 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/40_Ret_Insert_Hardcode_In.json");
   JSONObject jsonObj_11 = (JSONObject) parser.parse(jsonObj11);
   jsonObj_11.put("user_id", user_id);
   String link11 = "http://192.168.1.241:8080/service/retirementGoal/";
 
   String retirgoalid = retirgoalid(jsonObj_11, link11);
   
     
   
   
   CsvReader olah9 = new CsvReader("/home/kamalesh/automation/Olah_csv/usecase2.1.9.csv");
   olah9.readHeaders();
   while (olah9.readRecord()) 
   {
    String SL_No = olah9.get("SL_No");
    String Testcase_Id = olah9.get("TestCase_ID");
    String Inputfile = olah9.get("Input_file");
    String Outputfile = olah9.get("Output_file");
    String Url = olah9.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase12(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah9.close();
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  
  } catch (ParseException e) {
   
   e.printStackTrace();
  }

 }

 
 
 
 
 
 
 
 
 
 
 
 
 
 public static String getuserid(JSONObject jsonObj_1, String link1) 
 {
	
  JSONObject restResponse = new JSONObject();
  JSONParser parser1 = new JSONParser();
  CloseableHttpClient httpClient;
  CloseableHttpResponse httpResponse;
  BufferedReader br;
  httpClient = null;
  String usrid = null;
  HttpServletRequest request;
  HttpSession session;
  try {
   httpClient = HttpClients.createDefault();
   String restURL = link1;
   HttpPost postRequest = new HttpPost(restURL);
   String Data = jsonObj_1.toString();
   StringEntity input = new StringEntity(Data);
   input.setContentType("application/json");
   postRequest.setEntity(input);
   httpResponse = httpClient.execute(postRequest);
   if (httpResponse != null) {
    if (httpResponse.getStatusLine().getStatusCode() != 200) 
    {
     System.out.println("Problem in fetching credentials from BitlaMongo");
    }
    String output = null;
    String jsonString = null;
    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
    while ((output = br.readLine()) != null) {
     jsonString = output;
    }
    br.close();
    JSONParser parser = new JSONParser();
    restResponse = (JSONObject) parser.parse(jsonString);
    System.out.println("rest response of case1" + restResponse);
    usrid=(String)restResponse.get("userid");
    restResponse.remove("userid");
    
    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/00_Name_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);

    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC1 : PASSED");
    else {
     System.out.println("Test case TC1: FAILED");
     System.out.println("Username already exists");
     System.exit(0);
    }
    
    /* 
     int len = jsonString.length();
     if (len == 42) {
     usrid = jsonString.substring(30,40);
    }
    */
    
    
    
    return usrid;

   } else {
    restResponse.put("status", "fail");
    return null;
   }
  } catch (Exception e) {
   e.printStackTrace();
   return null;
  }
 }

 public static String connection(JSONObject jsonObj_1, String link1, String Outputfile, String Testcase_Id, String user_id) 
 {
	        
	
			  JSONObject restResponse = new JSONObject();
			  CloseableHttpClient httpClient;
			  CloseableHttpResponse httpResponse;
			  BufferedReader br;
			  httpClient = null;
			  HttpServletRequest request;
			  String usrid = null;
			  try {
			   httpClient = HttpClients.createDefault();
			   String restURL = link1;
			   
			   HttpPost postRequest = new HttpPost(restURL);
			   jsonObj_1.put("user_id", user_id);
			  
			   String Data = jsonObj_1.toString();
			  
			   StringEntity input = new StringEntity(Data);
			   input.setContentType("application/json");
			   postRequest.setEntity(input);
			   httpResponse = httpClient.execute(postRequest);
			   if (httpResponse != null) {
					    if (httpResponse.getStatusLine().getStatusCode() != 200) 
					    {
					     System.out.println("Problem in fetching credentials from BitlaMongo");
					    }
			    String output = null;
			    String jsonString = null;
			    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
			   
			    while ((output = br.readLine()) != null)
			    {
			     jsonString = output;
			    
			    }
			    br.close();
			   
			    JSONParser parser = new JSONParser();
			    restResponse = (JSONObject) parser.parse(jsonString);
			   
			    
			    restResponse.remove("finid");
			    restResponse.remove("goal_id");
			    restResponse.remove("GoalIds");
			    restResponse.remove("GoalStatus");
			    restResponse.remove("Goals");
			    restResponse.remove("income_profiles");
			    restResponse.remove("incomeProfile");
			    restResponse.remove("costArray");
			    restResponse.remove("cost");
			 //   restResponse.remove("userPlot");
			

			    String restResponse1=restResponse.toString();
			   
			    
				  String[] out = new String[Outputfile.length()];  
				  for(int i = 0; i < Outputfile.length(); i++)
				  {
					  out[i] = String. valueOf(Outputfile. charAt(i));
				  }
				  
				  
				  String[] resp = new String[restResponse1.length()];  
				  for(int i = 0; i < restResponse1.length(); i++)
				  {
					  resp[i] = String. valueOf(restResponse1. charAt(i));
				  }
				  
				  boolean checkarray= compareArrays(out, resp);
				  
				
			
				  if (checkarray==true)
				     {
					  System.out.println("Test case" + Testcase_Id + ": PASSED");
				     // continue;
				     } 
				  else 
				     {
				
				      System.out.println("Test case" + Testcase_Id + ": FAILED");
				      System.exit(0);
				     }
				    return usrid;
			  
			   } else {
			    restResponse.put("status", "fail");
			    return "";
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			   return "";
			  }

    }
 
 
 
 

 public static boolean compareArrays(String[] out1, String[] resp1)
 { 
		    Arrays.sort(out1);
		    Arrays.sort(resp1);	    
		    return Arrays.equals(out1, resp1);
}
 
 public static String getgoalid(JSONObject jsonObj_5, String link5)
 { 
	
  JSONObject restResponse = new JSONObject();
  JSONParser parser1 = new JSONParser();
  CloseableHttpClient httpClient;
  CloseableHttpResponse httpResponse;
  BufferedReader br;
  httpClient = null;
  String gid = null;
  HttpServletRequest request;
  HttpSession session;
  try {
   httpClient = HttpClients.createDefault();
   String restURL = link5;
   HttpPost postRequest = new HttpPost(restURL);
   String Data = jsonObj_5.toString();
  
   StringEntity input = new StringEntity(Data);
   input.setContentType("application/json");
   postRequest.setEntity(input);
   httpResponse = httpClient.execute(postRequest);
   if (httpResponse != null) {
    if (httpResponse.getStatusLine().getStatusCode() != 200) {
     System.out.println("Problem in fetching credentials from BitlaMongo");
    }
    String output = null;
    String jsonString = null;
    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
    while ((output = br.readLine()) != null) {
     jsonString = output;
    }
    br.close();

    JSONParser parser = new JSONParser();
    restResponse = (JSONObject) parser.parse(jsonString);
  
    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/09_Insert_Emg_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    //gid = restResponse.toString().substring(64, 75);
    gid = (String) restResponse.get("goal_id");
   
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC10 : PASSED");
    else {
     System.out.println("Test case TC10: FAILED");
     System.exit(0);
    }
    return gid;

   } else {
    restResponse.put("status", "fail");
    return "";
   }
  } catch (Exception e) {
   e.printStackTrace();
   return "";
  }
 }
 
 

	public static String usecase1(JSONObject jsonObj_1, String link1, String Outputfile, String Testcase_Id,
			String user_id, String goal_id) 
	{

	    
		
		JSONObject restResponse = new JSONObject();
		CloseableHttpClient httpClient;
		CloseableHttpResponse httpResponse;
		BufferedReader br;
		httpClient = null;
		HttpServletRequest request;
		String usrid = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = link1;
			HttpPost postRequest = new HttpPost(restURL);
		    jsonObj_1.put("user_id", user_id);
			jsonObj_1.put("goal_id", goal_id);
		   //jsonObj_1.put("plan_name", goal_id);
			
			
			
			String Data = jsonObj_1.toString();
			System.out.println("Data here::" + Data);
			
			StringEntity input = new StringEntity(Data);
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			
			if (httpResponse != null) 
			{
				
				if (httpResponse.getStatusLine().getStatusCode() != 200) 
				{
					
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				
				String jsonString = null;
			
				br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				
				// session = request.getSession(false);
				String output = br.readLine();
				while (output  != null) 
				{
					
					jsonString = output;
					
					 output = br.readLine();
				}
				
				br.close();
				JSONParser parser = new JSONParser();
				restResponse = (JSONObject) parser.parse(jsonString);
				
				restResponse.remove("finid");
				restResponse.remove("goal_id");
				restResponse.remove("GoalIds");
				restResponse.remove("GoalStatus");
				restResponse.remove("Goals");
				restResponse.remove("incomeProfile");
				restResponse.remove("income_profiles");
				//restResponse.remove("userPlot");
	
				
			    String restResponse1=restResponse.toString();
			    System.out.println("rest restonse here11::" + restResponse1);
				   
				  String[] out = new String[Outputfile.length()];  
				  for(int i = 0; i < Outputfile.length(); i++)
				  {
					  out[i] = String. valueOf(Outputfile. charAt(i));
				  }
			  String[] resp = new String[restResponse1.length()];  
				  for(int i = 0; i < restResponse1.length(); i++)
				  {
					  resp[i] = String. valueOf(restResponse1. charAt(i));
				  }
				  
			  boolean checkarray= compareArrays(out, resp);
					  if (checkarray==true)
				     {
					  System.out.println("Test case" + Testcase_Id + ": PASSED");
				     // continue;
				     } 
				  else 
				     {
				
				      System.out.println("Test case" + Testcase_Id + ": FAILED");
				      System.exit(0);
				     }
			
				  
				    return usrid;
			    
			
			   } else {
			    restResponse.put("status", "fail");
			    return "";
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			   return "";
			  }

	}
 
	
	
	public static String usecase11(JSONObject jsonObj_1, String link1, String Outputfile, String Testcase_Id,
			String user_id) 
	{

	
		JSONObject restResponse = new JSONObject();
		CloseableHttpClient httpClient;
		CloseableHttpResponse httpResponse;
		BufferedReader br;
		httpClient = null;
		HttpServletRequest request;
		String usrid = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = link1;
			HttpPost postRequest = new HttpPost(restURL);
		    jsonObj_1.put("user_id", user_id);
		
		  
			
			
			
			String Data = jsonObj_1.toString();
			System.out.println("data of 2.2::" + Data);
			
			StringEntity input = new StringEntity(Data);
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			
			if (httpResponse != null) 
			{
				
				if (httpResponse.getStatusLine().getStatusCode() != 200) 
				{
					
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				
				String jsonString = null;
			
				br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				
				// session = request.getSession(false);
				String output = br.readLine();
				while (output  != null) 
				{
					
					jsonString = output;
					
					 output = br.readLine();
				}
				
				br.close();
				JSONParser parser = new JSONParser();
				restResponse = (JSONObject) parser.parse(jsonString);
				
				restResponse.remove("finid");
				restResponse.remove("goal_id");
				restResponse.remove("GoalIds");
				restResponse.remove("GoalStatus");
				restResponse.remove("Goals");
				restResponse.remove("incomeProfile");
				restResponse.remove("income_profiles");
				//restResponse.remove("userPlot");
	
				
			    String restResponse1=restResponse.toString();
			    System.out.println("rest restonse here11::" + restResponse1);
				   
				  String[] out = new String[Outputfile.length()];  
				  for(int i = 0; i < Outputfile.length(); i++)
				  {
					  out[i] = String. valueOf(Outputfile. charAt(i));
				  }
			  String[] resp = new String[restResponse1.length()];  
				  for(int i = 0; i < restResponse1.length(); i++)
				  {
					  resp[i] = String. valueOf(restResponse1. charAt(i));
				  }
				  
			  boolean checkarray= compareArrays(out, resp);
					  if (checkarray==true)
				     {
					  System.out.println("Test case" + Testcase_Id + ": PASSED");
				     // continue;
				     } 
				  else 
				     {
				
				      System.out.println("Test case" + Testcase_Id + ": FAILED");
				      System.exit(0);
				     }
			
				  
				    return usrid;
			    
			
			   } else {
			    restResponse.put("status", "fail");
			    return "";
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			   return "";
			  }

	}
	
	
	
	
	public static String usecase12(JSONObject jsonObj_1, String link1, String Outputfile, String Testcase_Id,
			String user_id) 
	{

	
		JSONObject restResponse = new JSONObject();
		CloseableHttpClient httpClient;
		CloseableHttpResponse httpResponse;
		BufferedReader br;
		httpClient = null;
		HttpServletRequest request;
		String usrid = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = link1;
			HttpPost postRequest = new HttpPost(restURL);
		    jsonObj_1.put("user_id", user_id);
		
		  
			
			
			
			String Data = jsonObj_1.toString();
			System.out.println("data of 2.4" + Data);
			
			StringEntity input = new StringEntity(Data);
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			
			if (httpResponse != null) 
			{
				
				if (httpResponse.getStatusLine().getStatusCode() != 200) 
				{
					
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				
				String jsonString = null;
			
				br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				
				// session = request.getSession(false);
				String output = br.readLine();
				while (output  != null) 
				{
					
					jsonString = output;
					
					 output = br.readLine();
				}
				
				br.close();
				JSONParser parser = new JSONParser();
				restResponse = (JSONObject) parser.parse(jsonString);
				
				restResponse.remove("finid");
				restResponse.remove("goal_id");
				restResponse.remove("GoalIds");
				restResponse.remove("GoalStatus");
				restResponse.remove("Goals");
				restResponse.remove("incomeProfile");
				restResponse.remove("income_profiles");
				restResponse.remove("user_id");
				restResponse.remove("finplans");
	
				
			    String restResponse1=restResponse.toString();
				   
				  String[] out = new String[Outputfile.length()];  
				  for(int i = 0; i < Outputfile.length(); i++)
				  {
					  out[i] = String. valueOf(Outputfile. charAt(i));
				  }
			  String[] resp = new String[restResponse1.length()];  
				  for(int i = 0; i < restResponse1.length(); i++)
				  {
					  resp[i] = String. valueOf(restResponse1. charAt(i));
				  }
				  
			  boolean checkarray= compareArrays(out, resp);
					  if (checkarray==true)
				     {
					  System.out.println("Test case" + Testcase_Id + ": PASSED");
				     // continue;
				     } 
				  else 
				     {
				
				      System.out.println("Test case" + Testcase_Id + ": FAILED");
				      System.exit(0);
				     }
			
				  
				    return usrid;
			    
			
			   } else {
			    restResponse.put("status", "fail");
			    return "";
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			   return "";
			  }

	}
	
	
	
	public static String usecase121(JSONObject jsonObj_1, String link1, String Outputfile, String Testcase_Id,
			String user_id) 
	{

	
		JSONObject restResponse = new JSONObject();
		CloseableHttpClient httpClient;
		CloseableHttpResponse httpResponse;
		BufferedReader br;
		httpClient = null;
		HttpServletRequest request;
		String usrid = null;
		try {
			httpClient = HttpClients.createDefault();
			String restURL = link1;
			HttpPost postRequest = new HttpPost(restURL);
		    jsonObj_1.put("user_id", user_id);
		
		  
			
			
			
			String Data = jsonObj_1.toString();
			System.out.println("data of 2.4" + Data);
			
			StringEntity input = new StringEntity(Data);
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			httpResponse = httpClient.execute(postRequest);
			
			if (httpResponse != null) 
			{
				
				if (httpResponse.getStatusLine().getStatusCode() != 200) 
				{
					
					System.out.println("Problem in fetching credentials from BitlaMongo");
				}
				
				String jsonString = null;
			
				br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
				
				// session = request.getSession(false);
				String output = br.readLine();
				while (output  != null) 
				{
					
					jsonString = output;
					
					 output = br.readLine();
				}
				
				br.close();
				JSONParser parser = new JSONParser();
				restResponse = (JSONObject) parser.parse(jsonString);
				
				restResponse.remove("finid");
				restResponse.remove("goal_id");
				restResponse.remove("GoalIds");
				restResponse.remove("GoalStatus");
				restResponse.remove("Goals");
				restResponse.remove("incomeProfile");
				restResponse.remove("income_profiles");
				
	
				
			    String restResponse1=restResponse.toString();
				   System.out.println("response of tc29:: " + restResponse);
				  String[] out = new String[Outputfile.length()];  
				  for(int i = 0; i < Outputfile.length(); i++)
				  {
					  out[i] = String. valueOf(Outputfile. charAt(i));
				  }
			  String[] resp = new String[restResponse1.length()];  
				  for(int i = 0; i < restResponse1.length(); i++)
				  {
					  resp[i] = String. valueOf(restResponse1. charAt(i));
				  }
				  
			  boolean checkarray= compareArrays(out, resp);
					  if (checkarray==true)
				     {
					  System.out.println("Test case" + Testcase_Id + ": PASSED");
				     // continue;
				     } 
				  else 
				     {
				
				      System.out.println("Test case" + Testcase_Id + ": FAILED");
				      System.exit(0);
				     }
			
				  
				    return usrid;
			    
			
			   } else {
			    restResponse.put("status", "fail");
			    return "";
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			   return "";
			  }

	}
 
 
	public static String getcollegegoadid(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
       JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/16_Coll1_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC23 : PASSED");
	    else {
	     System.out.println("Test case TC23: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	 
	
	
	public static String getcollegegoadid2(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
      JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/24_Coll2_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC26 : PASSED");
	    else {
	     System.out.println("Test case TC26: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	
	
	
	
	
	public static String replacehousegoalid(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
     JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/30_Repl_House_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC32 : PASSED");
	    else {
	     System.out.println("Test case TC32: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	 
 
	public static String cargoalid(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
    JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/33_Car_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC34 : PASSED");
	    else {
	     System.out.println("Test case TC34: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	 
	public static String customizedgoalid(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
   JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/36_Cust_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC40 : PASSED");
	    else {
	     System.out.println("Test case TC40: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	 
	
	
	public static String retirgoalid(JSONObject jsonObj_5, String link5) 
	 {
	  JSONObject restResponse = new JSONObject();
	  JSONParser parser1 = new JSONParser();
	  CloseableHttpClient httpClient;
	  CloseableHttpResponse httpResponse;
	  BufferedReader br;
	  httpClient = null;
	  String gid = null;
	  HttpServletRequest request;
	  HttpSession session;
	  try {
	   httpClient = HttpClients.createDefault();
	   String restURL = link5;
	   HttpPost postRequest = new HttpPost(restURL);
	   String Data = jsonObj_5.toString();
	  
	   StringEntity input = new StringEntity(Data);
	   input.setContentType("application/json");
	   postRequest.setEntity(input);
	   httpResponse = httpClient.execute(postRequest);
	   if (httpResponse != null) {
	    if (httpResponse.getStatusLine().getStatusCode() != 200) {
	     System.out.println("Problem in fetching credentials from BitlaMongo");
	    }
	    String output = null;
	    String jsonString = null;
	    br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
	    while ((output = br.readLine()) != null) {
	     jsonString = output;
	    }
	    br.close();
  JSONParser parser = new JSONParser();
	    restResponse = (JSONObject) parser.parse(jsonString);
	    

	    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC2_02Mar/40_Ret_Insert_Hardcode_Out.json");
	    
	    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
	   
	   System.out.println("response of hardcode::" + restResponse);
	
	    gid = (String) restResponse.get("goal_id");
	    System.out.println("gid of hard code:: " + gid);
	    restResponse.remove("goal_id");
	    
	    if (restResponse.equals(jsonObj_4))
	     System.out.println("Test case TC40 : PASSED");
	    else {
	     System.out.println("Test case TC40: FAILED");
	     System.exit(0);
	    }
	    return gid;

	   } else {
	    restResponse.put("status", "fail");
	    return "";
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   return "";
	  }
	 }
	 
	public static String getRandomemailid()
	 {

		 Random random = new Random();
		 int number = random.nextInt(1000);

		 String randoms = "March" + number + "gmail.com";
		 return randoms;

	 }
 
 
 
 
 
}