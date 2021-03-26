import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class pick {
	public static  void  main(String[] args) throws IOException {
		File file=new File("Hybrid_results.txt");//创建一个file对象，参数是你的文件路径
		BufferedReader buf=new BufferedReader(new FileReader(file));//读取文件
		
		List<double[]> list=new ArrayList<double[]>();
		List<Double> list_loss=new ArrayList<Double> ();
		List<Double> list_action0=new ArrayList<Double> ();
		List<Double> list_action1=new ArrayList<Double> ();
		/*
		List<Double> list_action2=new ArrayList<Double> ();
		List<Double> list_action3=new ArrayList<Double> ();
		*/
		double max_loss=-10000.0;
		double min_loss=10000.0;
		double max_action0=-10000.0;
		double min_action0=10000.0;
		
		double all_loss=0.0;
		double all_action0=0.0;
		double all_action1=0.0;
		
		double all_action2=0.0;
		double all_action3=0.0;
		
		while(true)
		{
			String str=buf.readLine();
			if(str==null)
				break;
			String[] strs=str.split(" ");
			double[] nums=new double[strs.length];
			for(int i=0;i<nums.length;i++)
			{
				nums[i]=Double.valueOf(strs[i]);
			}
			list.add(nums);
			list_loss.add(nums[0]);
			all_loss+=nums[0];
			
			if(nums[0]>max_loss)
				max_loss=nums[0];
			if(nums[0]<min_loss)
				min_loss=nums[0];
			
			list_action0.add(nums[2]);
			all_action0+=nums[2];
			
			if(nums[2]>max_action0)
				max_action0=nums[2];
			if(nums[2]<min_action0)
				min_action0=nums[2];
			
			list_action1.add(nums[4]);
			all_action1+=nums[4];
			/*
			list_action2.add(nums[6]);
			all_action2+=nums[6];
			
			list_action3.add(nums[8]);
			all_action3+=nums[8];
			*/
		}
		
		BufferedWriter tr=new BufferedWriter(new FileWriter("Hybrid_specials.txt"));
		
		tr.write("ranked by loss:");
		tr.newLine();
		tr.newLine();
		tr.flush();
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[0]==min_loss)
			{
				tr.write("best individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[0]==max_loss)
			{
				tr.write("worst individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		
		list_loss.sort(null);
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[0]==list_loss.get(list_loss.size()/2))
			{
				tr.write("median individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		tr.write("average result:");
		tr.newLine();
		tr.write(String.valueOf(all_loss/list_loss.size())+" ");
		tr.write(String.valueOf(all_action0/list_action0.size())+" ");
		tr.write(String.valueOf(all_action1/list_action1.size())+" ");
		/*
		tr.write(String.valueOf(all_action2/list_action2.size())+" ");
		tr.write(String.valueOf(all_action3/list_action3.size())+" ");
		*/
		tr.newLine();
		tr.newLine();
		tr.newLine();
		tr.flush();
		
		tr.write("ranked by action0_accuracy:");
		tr.newLine();
		tr.newLine();
		tr.flush();
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[2]==max_action0)
			{
				tr.write("best individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[2]==min_action0)
			{
				tr.write("worst individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		
		list_action0.sort(null);
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i)[2]==list_action0.get(list_action0.size()/2))
			{
				tr.write("median individual:");
				tr.newLine();
				for(int j=0;j<list.get(i).length;j++)
				{
					tr.write(String.valueOf(list.get(i)[j])+" ");
				}
				tr.newLine();
				tr.flush();
				break;
			}
		}
		tr.write("average result:");
		tr.newLine();
		tr.write(String.valueOf(all_loss/list_loss.size())+" ");
		tr.write(String.valueOf(all_action0/list_action0.size())+" ");
		tr.write(String.valueOf(all_action1/list_action1.size())+" ");
		/*
		tr.write(String.valueOf(all_action2/list_action2.size())+" ");
		tr.write(String.valueOf(all_action3/list_action3.size())+" ");
		*/
		tr.newLine();
		tr.newLine();
		tr.flush();
		
	}

}
