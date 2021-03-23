import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class calAccuracy {
	
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
    
    public static List<String> getStrList(String inputString, int length,
            int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
    
    public static double calscore(String action, double hp_difference, double distance, double relative_speed, double x_coordinate_player1, double is_attacking, double can_hit ,double x_coordinate_player2)
    {
    	char actions[]=action.toCharArray();
    	double max=-10000.0,min=10000.0,score=0.0,temp=0.0;
    	List<Double> scores = new ArrayList<Double> ();
    	if(actions[0]=='1')
    	{
    		temp=-1*hp_difference;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[1]=='1')
    	{
    		temp=hp_difference;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[2]=='1')
    	{
    		temp=-1*Math.pow(2.7,hp_difference)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[3]=='1')
    	{
    		temp=Math.pow(2.7,hp_difference)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[4]=='1')
    	{
    		temp=-1*Math.log(hp_difference+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[5]=='1')
    	{
    		temp=Math.log(hp_difference+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[6]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(hp_difference-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[7]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(hp_difference-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[8]=='1')
    	{
    		temp=-1*distance;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[9]=='1')
    	{
    		temp=distance;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[10]=='1')
    	{
    		temp=-1*Math.pow(2.7,distance)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[11]=='1')
    	{
    		temp=Math.pow(2.7,distance)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[12]=='1')
    	{
    		temp=-1*Math.log(distance+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[13]=='1')
    	{
    		temp=Math.log(distance+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[14]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(distance-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[15]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(distance-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[16]=='1')
    	{
    		temp=-1*relative_speed;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[17]=='1')
    	{
    		temp=relative_speed;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[18]=='1')
    	{
    		temp=-1*Math.pow(2.7,relative_speed)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[19]=='1')
    	{
    		temp=Math.pow(2.7,relative_speed)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[20]=='1')
    	{
    		temp=-1*Math.log(relative_speed+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[21]=='1')
    	{
    		temp=Math.log(relative_speed+1);
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[22]=='1')
    	{
    		temp=(-1/(1+Math.pow(2.7,-1*(relative_speed-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[23]=='1')
    	{
    		temp=(1/(1+Math.pow(2.7,-1*(relative_speed-0.5))));
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[24]=='1')
    	{
    		temp=(2*is_attacking-1)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[25]=='1')
    	{
    		temp=(1-2*is_attacking)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[26]=='1')
    	{
    		
    		if(Math.abs(x_coordinate_player1-x_coordinate_player2)<=245.0)
    			temp=0.5;
    		else
    			temp=-0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    			/*
    		temp=(2*can_hit-1)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    			*/
    			
    	}
    	if(actions[27]=='1')
    	{
    		
    		if(Math.abs(x_coordinate_player1-x_coordinate_player2)<=245.0)
    			temp=-0.5;
    		else
    			temp=0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    		/*
    		temp=(1-2*can_hit)/2;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    		*/
    	}
    	if(actions[28]=='1')
    	{
    		if(x_coordinate_player1>930.0 || x_coordinate_player1<30.0)
    			temp=0.5;
    		else
    			temp=-0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[29]=='1')
    	{
    		if(x_coordinate_player1>930.0 || x_coordinate_player1<30.0)
    			temp=-0.5;
    		else
    			temp=0.5;
    		scores.add(temp);
    		if(temp>max)
    			max=temp;
    		if(temp<min)
    			min=temp;
    	}
    	if(actions[30]=='1')
    	{
    		for(int i=0;i<scores.size();i++)
    		{
    			score+=scores.get(i);
    		}
    	}
    	if(actions[31]=='1')
    	{
    		for(int i=0;i<scores.size();i++)
    		{
    			score+=scores.get(i);
    		}
    		score/=scores.size();
    	}
    	if(actions[32]=='1')
    	{
    		score=max;
    	}
    	if(actions[33]=='1')
    	{
    		score=min;
    	}
    	if(actions[34]=='1')
    	{
    		score=score*0.25;
    	}
    	if(actions[35]=='1')
    	{
    		score=score*0.5;
    	}
    	if(actions[36]=='1')
    	{
    		score=score*1;
    	}
    	if(actions[37]=='1')
    	{
    		score=score*2.0;
    	}
    	if(actions[38]=='1')
    	{
    		score=score*4.0;
    	}
    	return score;
    }
	public static  void  main(String[] args) throws IOException {
		
		File file=new File("TOVOR_100_testing_1.txt");//创建一个file对象，参数是你的文件路径
		BufferedReader buf=new BufferedReader(new FileReader(file));//读取文件
		
		String str=buf.readLine();
		String[] strs=str.split(" ");
		int[] nums=new int[strs.length];
		for(int i=0;i<nums.length;i++)
		{
			nums[i]=Integer.valueOf(strs[i]);
		}
		
		int[] action_index= new int[ nums[0]];
		for(int i=1;i<nums.length;i++)
		{
			action_index[i-1]=Integer.valueOf(strs[i]);
		}
		
		int num_action=nums[0];

		
		int[] loss_count=new int[num_action];
		int[] score_count=new int[num_action];
		for(int i=0;i<num_action;i++)
		{
			loss_count[i]=0;
			score_count[i]=0;
		}
		int[][] loss_array=new int[num_action][num_action];
		int[][] error_count=new int[num_action][num_action];
		for(int i=0;i<num_action;i++)
		{
			for(int j=0;j<num_action;j++)
			{
				loss_array[i][j]=0;
				error_count[i][j]=0;
			}
		}
		
	/*
		File file1=new File("TOVOR_100_training_1_nocanhit.txt");//创建一个file对象，参数是你的文件路径
		BufferedReader buf1=new BufferedReader(new FileReader(file1));//读取文件
		
		BufferedWriter tr1=new BufferedWriter(new FileWriter("TOVOR_100_training_1_percents_nocanhit.txt"));
	*/	
		int count=0;
		int loss=0;
		/*
		int[] loss_count=new int[num_action];
		int[][] error_count=new int[num_action][num_action];
		*/
		
	/*	
		while(true)
		{
			String str1=buf1.readLine();
			if(str1==null)
				break;
			String[] strs1=str1.split(" ");
			String temp=strs1[3];
			List<String> actions= getStrList(temp, 39,nums[0]);
			count=0;
			for(int i=0;i<num_action;i++)
			{
				loss_count[i]=0;
				loss=0;
			}
			
			buf=new BufferedReader(new FileReader(file));
			buf.readLine();
	*/		
			
		String temp="001110011011010011000100101001100010000001110001101000111011111111001010000010001110010100100100110000101110010000001110011100000000110101101111111001001000";
		List<String> actions= getStrList(temp, 39,nums[0]);
		count=0;
		for(int i=0;i<num_action;i++)
		{
			loss_count[i]=0;
			loss=0;
		}	
		for(int i=0;i<num_action;i++)
		{
			for(int j=0;j<num_action;j++)
			{
				error_count[i][j]=0;
			}
		}
		buf=new BufferedReader(new FileReader(file));
		buf.readLine();
	
			while(true)
		{
			String str2=buf.readLine();//一行一行读取数据
			if(str2==null)
				break;
			//下面是对每行数据进行拆分
			String[] strs2=str2.split(" ");
			double[] nums2=new double[strs2.length];
			for(int i=0;i<nums2.length;i++)
			{
				nums2[i]=Integer.valueOf(strs2[i]);
			}
			double hp_difference=((nums2[0]-nums2[1])/400.0+1)/2;
			hp_difference=((hp_difference>1)?1.0:hp_difference);
			hp_difference=((hp_difference<0)?0.0:hp_difference);
			double distance=Math.abs(nums2[2]-nums2[3])/920.0;
			distance=((distance>1)?1.0:distance);
			distance=((distance<0)?0.0:distance);
			double relative_speed=((nums2[4]-nums2[5])/25.0+1)/2;
			relative_speed=((relative_speed>1)?1.0:relative_speed);
			relative_speed=((relative_speed<0)?0.0:relative_speed);
			double x_coordinate_player1=nums2[2];
			double is_attacking=nums2[6];
			double can_attack=nums2[7];
			double score[]=new double[num_action];
			
			for(int i=0;i<nums[0];i++)
			{
				score[i]=calscore(actions.get(i),hp_difference,distance,relative_speed,x_coordinate_player1,is_attacking,can_attack,nums2[3]);
			}
			
			int index=0;
			
			double max_score=-100000.0;
			int max_index=-1;
			
			for(int j=0;j<num_action;j++)
			{
				if(count>action_index[j])
					index++;
				else
					break;
			}
			
			int loss_temp=0;
			for(int j=0;j<num_action;j++)
			{
				if(j!=index && score[j]>score[index])
					loss_temp+=1;
				
				if(score[j]>max_score)
				{
					max_score=score[j];
					max_index=j;
				}
				
			}
			loss_count[loss_temp]++;
			loss+=loss_temp;
			count+=1;
			
			if(loss_temp>0)
			{
				error_count[index][max_index]++;
			}

			loss_array[index][loss_temp]+=1;
			//loss_count[loss_temp]++;
			score_count[max_index]++;
			


		}
		/*
			tr1.write(String.valueOf(loss));
			tr1.write(" ");
		
			for(int i=0;i<num_action;i++)
			{
				tr1.write(String.valueOf(loss_count[i]));
				tr1.write(" ");
				tr1.write(String.valueOf((double)loss_count[i]/count));
				tr1.write(" ");
			}
			tr1.newLine();
			tr1.flush();
		*/
		
			System.out.println(String.valueOf(loss));
			for(int i=0;i<num_action;i++)
			{
				System.out.println(String.valueOf(loss_count[i]));
				System.out.println(String.valueOf((double)loss_count[i]/count));
			}
			for(int i=0;i<num_action;i++)
			{
				System.out.print("action");
				System.out.print(i);
				System.out.println(" error:");			
				for(int j=0;j<num_action;j++)
				{
					System.out.print("error");
					System.out.print(j);
					System.out.print("=");
					System.out.print(error_count[i][j]);
					System.out.println();
				}
			}
			
		//}
		
		

		
		for(int i=0;i<num_action;i++)
		{
			System.out.print("action");
			System.out.print(i);
			System.out.println(":");
			for(int j=0;j<num_action;j++)
			{
				System.out.print("loss");
				System.out.print(j);
				System.out.print("=");
				double percent;
				if(i==0)
					percent=(double)loss_array[i][j]/action_index[i];
				else
					percent=(double)loss_array[i][j]/(action_index[i]-action_index[i-1]);
				System.out.print(loss_array[i][j]);
				System.out.print(",percent=");
				System.out.print(percent);
				System.out.println();
			}
		}
		for(int i=0;i<num_action;i++)
		{
			System.out.println("loss"+i+":"+loss_count[i]);
		}
		for(int i=0;i<num_action;i++)
		{
			System.out.println("action"+i+":"+score_count[i]);
		}
		for(int i=0;i<num_action;i++)
		{
			System.out.print("action");
			System.out.print(i);
			System.out.println(" error:");			
			for(int j=0;j<num_action;j++)
			{
				System.out.print("error");
				System.out.print(j);
				System.out.print("=");
				System.out.print(error_count[i][j]);
				System.out.println();
			}
		}
		
		return ;
		
	}
}
