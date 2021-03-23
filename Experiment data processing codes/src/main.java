import org.apache.commons.io.FileUtils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;

import com.alibaba.fastjson.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class main {
	static HashSet<Integer> attackNum=new HashSet<Integer>();
	static HashSet<Integer> lowAttackNum= new HashSet<Integer> ();
	static HashSet<Integer> mediumAttackNum= new HashSet<Integer> ();	
	static HashSet<Integer> highAttackNum= new HashSet<Integer> ();
	static HashSet<Integer> throwAttackNum= new HashSet<Integer> ();
	public static void addAttackNum(int num)
	{
		attackNum.add(num);
	}
	public static void addLowAttackNum(int num)
	{
		lowAttackNum.add(num);
	}
	public static void addMediumAttackNum(int num)
	{
		mediumAttackNum.add(num);
	}
	public static void addHighAttackNum(int num)
	{
		highAttackNum.add(num);
	}
	public static void addThrowAttackNum(int num)
	{
		throwAttackNum.add(num);
	}
	public static void gettxt(String filename)
	{
		
	}
	public static void main(String[] args) throws IOException

	{
		addAttackNum(23);addAttackNum(24);
		for(int i=27;i<=55;i++)
			addAttackNum(i);
		addLowAttackNum(29);addLowAttackNum(30);addLowAttackNum(37);addLowAttackNum(38);addLowAttackNum(46);
		addMediumAttackNum(31);addMediumAttackNum(32);addMediumAttackNum(33);addMediumAttackNum(34);addMediumAttackNum(36);addMediumAttackNum(39);addMediumAttackNum(40);addMediumAttackNum(41);addMediumAttackNum(42);addMediumAttackNum(47);addMediumAttackNum(48);addMediumAttackNum(49);addMediumAttackNum(50);addMediumAttackNum(51);addMediumAttackNum(52);addMediumAttackNum(53);addMediumAttackNum(54);
		addHighAttackNum(27);addHighAttackNum(28);addHighAttackNum(35);addHighAttackNum(43);addHighAttackNum(44);addHighAttackNum(45);addHighAttackNum(55);
		addThrowAttackNum(23);addThrowAttackNum(24);
		HashSet<Integer> s=new HashSet<Integer>();
		for(int m=61;m<=78;m++)
		{
			s.clear();
			String filename="TOVOR"+Integer.toString(m)+".json";
			 File file=new File(filename);
		        String content= FileUtils.readFileToString(file,"UTF-8");
		        JSONObject jsonObject=JSONObject.parseObject(content);
		        JSONArray data=jsonObject.getJSONArray("rounds");
		        FileWriter filewriter= new FileWriter("TOVOR_STAND_GUARD_78_training.txt",true);
		        BufferedWriter fw=new BufferedWriter(filewriter);
		    	//BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("TOVOR"+Integer.toString(m)+"STAND_A.txt")),"UTF-8"));
		        for(int i=0;i<data.size();i++)
		        {
		        	fw.flush();
		        	JSONArray array=data.getJSONArray(i);
		        	for(int j=0;j<array.size();j++)
		        	{
		        		JSONObject object=array.getJSONObject(j);
		        		if(object.getJSONObject("P1").getString("action").equals("STAND_GUARD"))
		        		{
		        			int b=0;
		        	        try {
		        	        	b=Integer.valueOf(object.getString("current_frame")).intValue();
		        	        }
		        	        catch(NumberFormatException e)
		        	        {
		        	        	e.printStackTrace();
		        	        }
		        	        if(!s.contains(b))
		        	        {
		        	        	
		        	        	s.add(b);s.add(b+1);s.add(b+2);s.add(b+3);s.add(b+4);s.add(b+5);s.add(b+6);s.add(b+7);s.add(b+8);s.add(b+9);
		        	        	s.add(b-1);s.add(b-2);s.add(b-3);s.add(b-4);s.add(b-5);s.add(b-6);s.add(b-7);s.add(b-8);s.add(b-9);
		        	        	/*
		        	        	 * 31 p1x:245 p1y:537 p2x:630 p2y:537 p1speedx:4 p1speedy:0 p2speedx:-5 p2speedy:0 p1energy:0 p2energy:0 p1hp:0 p2hp:0 remainframes:3569
		        	        	 * fw.write(b+" p1x:"+p1x +" p1y:"+p1y +" p2x:"+p2x +" p2y:"+p2y +" p1speedx:"+p1speedx+" p1speedy:"+p1speedy+" p2speedx:"+p2speedx+" p2speedy:"+p2speedy+" p1energy:"+p1energy+" p2energy:"+p2energy+" p1hp:"+p1hp+" p2hp:"+p2hp+" remainframes:"+remainframes);
		        	        	 */
		        	        	int p1hp=Integer.valueOf(object.getJSONObject("P1").getString("hp")).intValue();
		        	        	int p2hp=Integer.valueOf(object.getJSONObject("P2").getString("hp")).intValue();
		        	        	int p1x=(Integer.valueOf(object.getJSONObject("P1").getString("left")).intValue()+Integer.valueOf(object.getJSONObject("P1").getString("right")).intValue())/2;
		        	        	int p2x=(Integer.valueOf(object.getJSONObject("P2").getString("left")).intValue()+Integer.valueOf(object.getJSONObject("P2").getString("right")).intValue())/2;
		        	        	int p1speedx=Integer.valueOf(object.getJSONObject("P1").getString("speed_x")).intValue();
		        	        	int p2speedx=Integer.valueOf(object.getJSONObject("P2").getString("speed_x")).intValue();
		        	        	int p1action=Integer.valueOf(object.getJSONObject("P1").getString("action_id")).intValue();
		        	        	int p2action=Integer.valueOf(object.getJSONObject("P2").getString("action_id")).intValue();
		        	        	int p2attack=ifAttack(p2action);
		        	        	int p1hitp2=canHit(p1action,p2action);
		        	        	fw.write(p1hp+" "+p2hp+" "+p1x +" "+p2x +" "+p1speedx+" "+p2speedx+" "+p2attack+" "+p1hitp2);
		        	        	fw.newLine();
		        	        }
		        		}
		        	}
		        }
		        fw.close();			
		}
       
        /*
		String content ="{\"result\":{\"code\":1,\"msg\":\"success\",\"url\":\"\",\"data\":\"0\",\"date\":\"1530007139\"},\"data\":{\"token\":\"iamtoken\",\"life\":1530005620,\"expires\":7200}}";
		JSONObject jsonObject =JSONObject.parseObject(content);
		System.out.println(jsonObject.getJSONObject("result").getString("code"));
		*/
	}
	public static int ifAttack(int num)
	{
		if(attackNum.contains(num))
			return 1;
		else
			return 0;
	}
	public static int canHit(int attack, int guard)
	{
		//not attack
		if(ifAttack(attack)==0)
			return 0;
		else
		{
			//STAND_GUARD
			if(guard==10)
			{
				if(highAttackNum.contains(attack)||mediumAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;
			}
			//CROUCH_GUARD
			if(guard==11)
			{
				if(highAttackNum.contains(attack)||lowAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;			
			}
			//AIR_GUARD
			if(guard==12)
			{
				if(highAttackNum.contains(attack)||mediumAttackNum.contains(attack)||throwAttackNum.contains(attack))
				{
					return 0;
				}
				else
					return 1;			
			}
			return 1;
		}
	}

}


