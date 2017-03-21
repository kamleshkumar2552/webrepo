package com.ola.auto;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.Iterator;
import java.util.Set;
//package com.olahtek.controller;
import java.io.*;
import java.util.Arrays;
import java.util.regex.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

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
import org.omg.CORBA.PUBLIC_MEMBER;

import com.csvreader.CsvReader;


public class ola_usecase1 {

 public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InterruptedException {
  CloseableHttpClient httpClient;
 try {
   

	   JSONParser parser = new JSONParser();
	  
	   String getrandomemailid=getRandomemailid();
	   
	   JSONObject fileobj = new JSONObject();
		 fileobj.put("lname", "gopal");
		 fileobj.put("formType", "Userform");
		 fileobj.put("email", getrandomemailid);
		 fileobj.put("name", "Arun");
		 fileobj.put("password", "Testgr1@gmail");
		 
		 FileWriter out=new FileWriter("/home/kamalesh/automation/Olah_csv/UseC1/00_Name_In.json");
		 out.write(fileobj.toString());
		 out.flush();
		 out.close();
	  
	 
	  
 
   FileReader jsonObj2 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/00_Name_In.json");
  
   JSONObject jsonObj_2 = (JSONObject) parser.parse(jsonObj2);
   String link2 = "http://192.168.1.241:8080/service/Register/";
  
   String user_id = getuserid(jsonObj_2, link2);
  System.out.println("user id ::" + user_id);
   
    CsvReader products = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.1.csv");
	  products.readHeaders();
	 while (products.readRecord())
			   {
			    String SL_No = products.get("SL_No");
		 	    String Testcase_Id = products.get("TestCase_ID");
	     	    String Inputfile = products.get("Input_file");
			   String Outputfile = products.get("Output_file");
			   String Url = products.get("URL");
			    System.out.println(SL_No + "  : " + Testcase_Id + " : " + Inputfile + " : " + Outputfile + " : " + Url);
			    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
			    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
			  
			    String link1 = Url;
			    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
			    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
			    String Outfile = jsonObj_3.toString();
			    String user_id1 = connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
			   }
			   
			      
	                 
			   
				           
		FileReader jsonObj5 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/09_Insert_Emg_Hardcode_In.json");
		   JSONObject jsonObj_5 = (JSONObject) parser.parse(jsonObj5);
		   jsonObj_5.put("user_id", user_id);
		   String link5 = "http://192.168.1.241:8080/service/GoalEmergencyFund1/";
		 
		   String goal_id = getgoalid(jsonObj_5, link5);
		   products.close();
			          
				
			  CsvReader olah = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.2.csv");
			   olah.readHeaders();
		   while (olah.readRecord()) 
			   {
			    String SL_No = olah.get("SL_No");
			    String Testcase_Id = olah.get("TestCase_ID");
			    String Inputfile = olah.get("Input_file");
			    String Outputfile = olah.get("Output_file");
			    String Url = olah.get("URL");
			    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
			
			    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
			      
			    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
			   
			    String link1 = Url;
			    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
			    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
			    String Outfile = jsonObj_3.toString();
			   
			    String abc = usecase1(jsonObj_1, link1, Outfile, Testcase_Id, user_id, goal_id);
			
			   }
			
			   olah.close();
			   
			   
			   CsvReader olah1 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.3.csv");
			   olah1.readHeaders();
		   while (olah1.readRecord()) 
			   {
			    String SL_No = olah1.get("SL_No");
			    String Testcase_Id = olah1.get("TestCase_ID");
			    String Inputfile = olah1.get("Input_file");
			    String Outputfile = olah1.get("Output_file");
			    String Url = olah1.get("URL");
			    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
			
			    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
			      
			    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
			   
			    String link1 = Url;
			    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
			    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
			    String Outfile = jsonObj_3.toString();
			   
			    String abc = usecase11(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
			
			   }
			
			   olah1.close();
   
			   
			   FileReader jsonObj6 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/14_Mari_Insert_Hardcode_In.json");
			   JSONObject jsonObj_6 = (JSONObject) parser.parse(jsonObj6);
			   jsonObj_6.put("user_id", user_id);
			   String link6 = "http://192.168.1.241:8080/service/Goalmarriage/";
			   
			   String goal_id_m = getgoalid_m(jsonObj_6, link6);
			
           CsvReader olah2 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.4.csv");
           olah2.readHeaders();
		   while (olah2.readRecord())
		   {
		    String SL_No = olah2.get("SL_No");
		    String Testcase_Id = olah2.get("TestCase_ID");
		    String Inputfile = olah2.get("Input_file");
		    String Outputfile = olah2.get("Output_file");
		    String Url = olah2.get("URL");
		    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
		
		    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
		    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
		    String link1 = Url;
		  //  System.out.println(" Usecase1 : ");
		    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
		    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
		    String Outfile = jsonObj_3.toString();
		    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
		   }
		   olah2.close();
   
   
   
   

 
 
   FileReader jsonObj7 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/19_Kid1_Insert_Hardcode_In.json");
   JSONObject jsonObj_7 = (JSONObject) parser.parse(jsonObj7);
   jsonObj_7.put("user_id", user_id);
   String link7 = "http://192.168.1.241:8080/service/Kidgoal/";
   String kidid = getkidid(jsonObj_7, link7);
 

   CsvReader olah3 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.5.csv");
   olah3.readHeaders();
   while (olah3.readRecord())
   {
    String SL_No = olah3.get("SL_No");
    String Testcase_Id = olah3.get("TestCase_ID");
    String Inputfile = olah3.get("Input_file");
    String Outputfile = olah3.get("Output_file");
    String Url = olah3.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
  //  System.out.println(" Usecase1 : ");
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah3.close();

   
   
   
//getting profile name
   JSONObject jsonObj_8 =new  JSONObject();
   jsonObj_8.put("user_id", user_id);
   String link8 = "http://192.168.1.241:8080/service/UserProfile/";
   String profile_name = getincomeprofilename(jsonObj_8, link8);
   System.out.println("The profilename is returned :" + profile_name);
   
  
   
   
   CsvReader olah4 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.6.csv");
   olah4.readHeaders();
   while (olah4.readRecord()) 
   {
    String SL_No = olah4.get("SL_No");
    String Testcase_Id = olah4.get("TestCase_ID");
    String Inputfile = olah4.get("Input_file");
    String Outputfile = olah4.get("Output_file");
    String Url = olah4.get("URL");
    System.out.println("coming here::" +  SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    jsonObj_1.put("profile_name", profile_name);
   
    String link1 = Url;
   
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah4.close();
   
   
   
   CsvReader olah5 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.7.csv");
   olah5.readHeaders();
   while (olah5.readRecord())
   {
    String SL_No = olah5.get("SL_No");
    String Testcase_Id = olah5.get("TestCase_ID");
    String Inputfile = olah5.get("Input_file");
    String Outputfile = olah5.get("Output_file");
    String Url = olah5.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah5.close();
   
   
   FileReader jsonObj9 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/26_House_Insert_Hardcode_In.json");
   JSONObject jsonObj_9 = (JSONObject) parser.parse(jsonObj9);
   jsonObj_9.put("user_id", user_id);
   String link9 = "http://192.168.1.241:8080/service/Goalhouse/";
   String houseid = gethouseid(jsonObj_9, link9);
 
   
   CsvReader olah6 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.8.csv");
   olah6.readHeaders();
   while (olah6.readRecord())
   {
    String SL_No = olah6.get("SL_No");
    String Testcase_Id = olah6.get("TestCase_ID");
    String Inputfile = olah6.get("Input_file");
    String Outputfile = olah6.get("Output_file");
    String Url = olah6.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah6.close();
   
   
   
   FileReader jsonObj10 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/32_Coll1_Insert_Hardcode_In.json");
   JSONObject jsonObj_10 = (JSONObject) parser.parse(jsonObj10);
   jsonObj_10.put("user_id", user_id);
   String link10 = "http://192.168.1.241:8080/service/GoalCollegeEducation/";
   String collageid = getcollageid(jsonObj_10, link10);
   
   
   
   CsvReader olah7 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.9.csv");
   olah7.readHeaders();
   while (olah7.readRecord())
   {
    String SL_No = olah7.get("SL_No");
    String Testcase_Id = olah7.get("TestCase_ID");
    String Inputfile = olah7.get("Input_file");
    String Outputfile = olah7.get("Output_file");
    String Url = olah7.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah7.close();
   
   
   FileReader jsonObj11 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/35_Car_Insert_Hardcode_In.json");
   JSONObject jsonObj_11 = (JSONObject) parser.parse(jsonObj11);
   jsonObj_11.put("user_id", user_id);
   String link11 = "http://192.168.1.241:8080/service/Goalcar/";
   String carid = getcarid(jsonObj_11, link11);
   
   
   CsvReader olah8 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.10.csv");
   olah8.readHeaders();
   while (olah8.readRecord())
   {
    String SL_No = olah8.get("SL_No");
    String Testcase_Id = olah8.get("TestCase_ID");
    String Inputfile = olah8.get("Input_file");
    String Outputfile = olah8.get("Output_file");
    String Url = olah8.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah8.close();
   
   
   FileReader jsonObj12 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/38_Cust_Insert_Hardcode_In.json");
   JSONObject jsonObj_12 = (JSONObject) parser.parse(jsonObj12);
   jsonObj_12.put("user_id", user_id);
   String link12 = "http://192.168.1.241:8080/service/Goalcustomized/";
   String customizedgoalid = getcustomizedgoalid(jsonObj_12, link12);
   
   
   
   CsvReader olah9 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.11.csv");
   olah9.readHeaders();
   while (olah9.readRecord())
   {
    String SL_No = olah9.get("SL_No");
    String Testcase_Id = olah9.get("TestCase_ID");
    String Inputfile = olah9.get("Input_file");
    String Outputfile = olah9.get("Output_file");
    String Url = olah9.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah9.close();
   
   
   FileReader jsonObj13 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/42_Ret_Insert_Hardcode_In.json");
   JSONObject jsonObj_13 = (JSONObject) parser.parse(jsonObj13);
   jsonObj_13.put("user_id", user_id);
   String link13 = "http://192.168.1.241:8080/service/retirementGoal/";
   String retireid = getretireid(jsonObj_13, link13);
   
   
   CsvReader olah10 = new CsvReader("/home/kamalesh/automation/Olah_csv/olahtest-1.12.csv");
   olah10.readHeaders();
   while (olah10.readRecord())
   {
    String SL_No = olah10.get("SL_No");
    String Testcase_Id = olah10.get("TestCase_ID");
    String Inputfile = olah10.get("Input_file");
    String Outputfile = olah10.get("Output_file");
    String Url = olah10.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
 
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/UseC1/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah10.close();
   
   
   
   
   
   /*
   //case7 ###
    Second CSV file : after getting the goal id 
  CsvReader olah2 = new CsvReader("/home/kamalesh/automation/Olah_csv/marriagegoal.csv");
   olah2.readHeaders();
   while (olah2.readRecord()) 
   {
    String SL_No = olah2.get("SL_No");
    String Testcase_Id = olah2.get("TestCase_ID");
    String Inputfile = olah2.get("Input_file");
    String Outputfile = olah2.get("Output_file");
    String Url = olah2.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);
    
   
    
    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    String link1 = Url;
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    String abc = usecase1(jsonObj_1, link1, Outfile, Testcase_Id, user_id, goal_id_m);
   }
   olah2.close();
   
   

   
   //case8####
   CsvReader olah3 = new CsvReader("/home/kamalesh/automation/Olah_csv/kid1.csv");
   olah3.readHeaders();
   while (olah3.readRecord()) 
   {
    String SL_No = olah3.get("SL_No");
    String Testcase_Id = olah3.get("TestCase_ID");
    String Inputfile = olah3.get("Input_file");
    String Outputfile = olah3.get("Output_file");
    String Url = olah3.get("URL");
    System.out.println(SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
    String link1 = Url;
   
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah3.close();
   
   
   

   
      
   //update   graph
   CsvReader olah4 = new CsvReader("/home/kamalesh/automation/Olah_csv/graph_existing_inc.csv");
   olah4.readHeaders();
   while (olah4.readRecord()) 
   {
    String SL_No = olah4.get("SL_No");
    String Testcase_Id = olah4.get("TestCase_ID");
    String Inputfile = olah4.get("Input_file");
    String Outputfile = olah4.get("Output_file");
    String Url = olah4.get("URL");
    System.out.println("coming here::" +  SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
    jsonObj_1.put("profile_name", profile_name);
   
    String link1 = Url;
   
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah4.close();
   
   
   
   //create graph
   CsvReader olah6 = new CsvReader("/home/kamalesh/automation/Olah_csv/graph_new_create_inc.csv");
   olah6.readHeaders();
   while (olah6.readRecord()) 
   {
    String SL_No = olah6.get("SL_No");
    System.out.println("@@@@@" + SL_No);
    String Testcase_Id = olah6.get("TestCase_ID");
    String Inputfile = olah6.get("Input_file");
    String Outputfile = olah6.get("Output_file");
    String Url = olah6.get("URL");
    System.out.println("coming here::" +  SL_No + ":" + Testcase_Id + ":" + Inputfile + ":" + Outputfile + ":" + Url);

    FileReader jsonObj1 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Inputfile));
    JSONObject jsonObj_1 = (JSONObject) parser.parse(jsonObj1);
   
  
   
    String link1 = Url;
   
    FileReader jsonObj3 = new FileReader(new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/" + Outputfile));
    JSONObject jsonObj_3 = (JSONObject) parser.parse(jsonObj3);
    String Outfile = jsonObj_3.toString();
    
    connection(jsonObj_1, link1, Outfile, Testcase_Id, user_id);
   }
   olah6.close();
   
  

 
   */
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
 
  } catch (ParseException e) {
   
   e.printStackTrace();
  }

 }

 
 public static String getRandomemailid()
 {

	 Random random = new Random();
	 int number = random.nextInt(1000);

	 String randoms = "March" + number + "gmail.com";
	 return randoms;

 }
 
 
 public static boolean compareArrays(String[] out1, String[] resp1)
 {
	     
	 
		    Arrays.sort(out1);
		    Arrays.sort(resp1);
		  
		  
		    
		    return Arrays.equals(out1, resp1);
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
			   System.out.println("data is:: " + Data);
			   System.out.println("Rest URL : " + link1 + " Payload: " + Data);
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
			    System.out.println("json string is :: " +  jsonString);
			    JSONParser parser = new JSONParser();
			    restResponse = (JSONObject) parser.parse(jsonString);
			   
			    
			    restResponse.remove("finid");
			    restResponse.remove("goal_id");
			    restResponse.remove("GoalIds");
			    restResponse.remove("GoalStatus");
			    restResponse.remove("Goals");
			    restResponse.remove("income_profiles");
			    restResponse.remove("incomeProfile");
			    restResponse.remove("cost");
			    restResponse.remove("costArray");
			    restResponse.remove("user_id");
			    restResponse.remove("finplans");
			    restResponse.remove("houseGoalPresence");
			    
			   // restResponse.remove("userPlot");
			

			    String restResponse1=restResponse.toString();
				System.out.println("restResponse1:: " + restResponse1);   
			    
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
   System.out.println("Data is here::" + Data);
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
    usrid=(String)restResponse.get("userid");
    System.out.println("restResponse1 of user id::" + restResponse);
    restResponse.remove("userid");
    
    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/00_Name_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);

    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC1 : PASSED");
    else {
     System.out.println("Test case TC1: FAILED");
     System.out.println("Username already exists");
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
   //System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
   // System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/09_Insert_Emg_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
    //gid = restResponse.toString().substring(64, 75);
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC15 : PASSED");
    else {
     System.out.println("Test case TC15: FAILED");
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
 
 public static String gethouseid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
   // System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/26_House_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
    //gid = restResponse.toString().substring(64, 75);
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC27 : PASSED");
    else {
     System.out.println("Test case TC27: FAILED");
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
 
 
 public static String getcollageid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
   // System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/32_Coll1_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
  
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC33 : PASSED");
    else {
     System.out.println("Test case TC33: FAILED");
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
 
 
 public static String getcarid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
    System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/35_Car_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
  
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC36 : PASSED");
    else {
     System.out.println("Test case TC36: FAILED");
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
 
 public static String getcustomizedgoalid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
    System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/38_Cust_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
  
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC39 : PASSED");
    else {
     System.out.println("Test case TC39: FAILED");
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
 
 
 
 public static String getretireid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
    System.out.println("Rest response: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/42_Ret_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    
  
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC39 : PASSED");
    else {
     System.out.println("Test case TC39: FAILED");
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
 
 
 
 
 public static String getkidid(JSONObject jsonObj_5, String link5)
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
  

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/19_Kid1_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    
    
    System.out.println("rest response of goalid::"  + restResponse.toString());
    System.out.println("json object4 also::"  + jsonObj_4);
    
 
    gid = (String) restResponse.get("goal_id");
    System.out.println("gid::" + gid);
    restResponse.remove("goal_id");
    
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC22 : PASSED");
    else {
     System.out.println("Test case TC22: FAILED");
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
 
 
 public static String getincomeprofilename(JSONObject jsonObj_7, String link7) 
 {
  JSONObject restResponse = new JSONObject();
  JSONParser parser1 = new JSONParser();
  CloseableHttpClient httpClient;
  CloseableHttpResponse httpResponse;
  BufferedReader br;
  httpClient = null;
  JSONArray profilenamearray = new JSONArray();
  String profile=null;
  HttpServletRequest request;
  HttpSession session;
  try {
   httpClient = HttpClients.createDefault();
   String restURL = link7;
   HttpPost postRequest = new HttpPost(restURL);
   String Data = jsonObj_7.toString();
   System.out.println("Rest URL : " + link7 + " Payload: " + Data);
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
     System.out.println("restResponse of profile::" + restResponse);
    profilenamearray=  (JSONArray) restResponse.get("incomeProfiles");
    System.out.println("profilenamearray::@" + profilenamearray);
    
    String changearray=profilenamearray.toString();
    System.out.println("changearray::@" + changearray);
   profile=changearray.substring(20,57);
  }
   
   
   
  } catch (Exception e) {
   e.printStackTrace();
   return "";
  }
return profile;
 }
 
 
 
 
 
 
 public static String getplanname(JSONObject jsonObj_7, String link7) 
 {
  JSONObject restResponse = new JSONObject();
  JSONParser parser1 = new JSONParser();
  CloseableHttpClient httpClient;
  CloseableHttpResponse httpResponse;
  BufferedReader br;
  httpClient = null;
  JSONArray profilenamearray = new JSONArray();
  String profile=null;
  HttpServletRequest request;
  HttpSession session;
  try {
   httpClient = HttpClients.createDefault();
   String restURL = link7;
   HttpPost postRequest = new HttpPost(restURL);
   String Data = jsonObj_7.toString();
   System.out.println("Rest URL of link plan: " + link7 + " Payload: " + Data);
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
     System.out.println("restResponse of profile::" + restResponse);
    /*profilenamearray=  (JSONArray) restResponse.get("incomeProfiles");
    
   String changearray=profilenamearray.toString();
   
   profile=changearray.substring(20,53);*/
    
  
   
   }
   
   
   
  } catch (Exception e) {
   e.printStackTrace();
   return "";
  }
return null;
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

 public static String getgoalid_m(JSONObject jsonObj_5, String link5) 
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
   System.out.println("Rest URL: " + link5 + " Payload: " + Data);
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
    System.out.println("jsonstring" +  jsonString);
    JSONParser parser = new JSONParser();
    restResponse = (JSONObject) parser.parse(jsonString);
     System.out.println("Rest response csv3: " + restResponse);

    FileReader jsonObj4 = new FileReader("/home/kamalesh/automation/Olah_csv/UseC1/14_Mari_Insert_Hardcode_Out.json");
    JSONObject jsonObj_4 = (JSONObject) parser1.parse(jsonObj4);
    System.out.println("restresponse of marraige id::" + restResponse);
   // gid = restResponse.toString().substring(12, 20);
    gid = (String) restResponse.get("goal_id");
    System.out.println(" marraige id we got now::" + gid);
    restResponse.remove("goal_id");
    if (restResponse.equals(jsonObj_4))
     System.out.println("Test case TC16 : PASSED");
    else {
     System.out.println("Test case TC16: FAILED");
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
			
			System.out.println("Rest URL: " + link1 + " Payload: " + Data);
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

	    System.out.println("coming to 11");
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
			
			System.out.println("Rest URL: " + link1 + " Payload: " + Data);
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
				   System.out.println("response of 1.3::" + restResponse1);
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
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 public static void Result(String out1)
 {
  try {
   File file21 = new File("/home/kamalesh/automation/Olah_csv/Input_Output_files/Seg_1-200_Out.json");
   file21.createNewFile();
   FileWriter fw21 = new FileWriter("/home/kamalesh/automation/Olah_csv/Input_Output_files/Seg_1-200_Out.json");
   fw21.write(out1);

   if (out1.indexOf("success") != -1)
    System.out.println("PASS");
   else {
    System.out.println("FAIL");
    System.exit(0);
   }
   fw21.close();

  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}