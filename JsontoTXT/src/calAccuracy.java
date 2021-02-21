import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class calAccuracy {
	public static  void  main(String[] args) throws IOException {
		/*
		int[] action_index={1934,3151,4059,5320,6154,7760,10211};
		*/
		/*
		int[] action_index={1934,3151,4059,5320,6926,9377};
		*/
		/*
		int[] action_index={1934,3540,4801,7252};
		*/
		/*
		int[] action_index= {205,371,474,595,733,1009};
		*/
		
		int[] action_index= {2302,5559,6315,7665};
		
		int num_action=4;
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
		File file=new File("TOVOR_MIX_78.txt");//创建一个file对象，参数是你的文件路径
		BufferedReader buf=new BufferedReader(new FileReader(file));//读取文件
		buf.readLine();
		int count=0;
		while(true)
	{
		String str=buf.readLine();//一行一行读取数据
		if(str==null)
			break;
		//下面是对每行数据进行拆分
		String[] strs=str.split(" ");
		double[] nums=new double[strs.length];
		for(int i=0;i<nums.length;i++)
		{
			nums[i]=Integer.valueOf(strs[i]);
		}
		double hp_difference=((nums[0]-nums[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		double distance=Math.abs(nums[2]-nums[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		double relative_speed=((nums[4]-nums[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		double score[]=new double[num_action];
		/*
		 * relative_speed
		 * (Math.pow(2.7, relative_speed))/2
		 * Math.log(distance+1)
		 * (1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))
		 */
		/*
		score[0]=8*(Math.log(distance+1)+(-1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[6])/2+(1-2*nums[7])/2+(2*nums[8]-1)/2);
		score[1]=0.125*Math.min(-hp_difference,Math.min(-relative_speed,Math.min(-(Math.pow(2.7, relative_speed))/2,Math.min((-1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))),Math.min((2*nums[6]-1)/2,Math.min((1-2*nums[7])/2,(2*nums[8]-1)/2))))));
		score[2]=2*(-hp_difference-(Math.pow(2.7, hp_difference))/2-Math.log(hp_difference+1)+(-1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))-distance+relative_speed+(Math.pow(2.7, relative_speed))/2+(1-2*nums[6])/2+(2*nums[7]-1)/2);
		score[3]=0.125*Math.min((Math.pow(2.7, hp_difference))/2, Math.min((1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))), Math.min(relative_speed, Math.min((Math.pow(2.7, relative_speed))/2, Math.min((1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.min((2*nums[6]-1)/2, (2*nums[8]-1)/2))))));
		score[4]=8*Math.min(Math.log(hp_difference+1),Math.min(distance, Math.min((Math.pow(2.7, distance))/2, Math.min(Math.log(distance+1),Math.min(-relative_speed, Math.min(-(Math.pow(2.7, relative_speed))/2, Math.min(-Math.log(relative_speed+1), Math.min((-1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.min((2*nums[6]-1)/2, Math.min((1-2*nums[6])/2, Math.min((1-2*nums[7])/2, (2*nums[8]-1)/2)))))))))));
		score[5]=4*Math.min(hp_difference,Math.min(Math.log(hp_difference+1), Math.min((1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))), Math.min(distance, Math.min((Math.pow(2.7, distance))/2, Math.min(Math.log(distance+1), Math.min((1/(1+Math.pow(2.7, -1*(distance-0.5)))), Math.min((Math.pow(2.7, relative_speed))/2, Math.min((1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.min((1-2*nums[6])/2, (2*nums[7]-1)/2))))))))));
		score[6]=0.5*(-(Math.pow(2.7, hp_difference))/2+distance+(Math.pow(2.7, distance))/2+(1/(1+Math.pow(2.7, -1*(distance-0.5))))+Math.log(relative_speed+1)+(2*nums[6]-1)/2+(1-2*nums[6])/2+(1-2*nums[7])/2+(2*nums[8]-1)/2+(1-2*nums[8])/2);
		*/
		/*
		score[0]=8*((Math.pow(2.7, hp_difference))/2+ (-Math.pow(2.7, distance))/2 +(1-2*nums[6])/2+(1-2*nums[7])/2+(2*nums[8]-1)/2);
		score[1]=0.125*Math.max(-hp_difference,Math.max((-Math.pow(2.7, hp_difference))/2, Math.max(-Math.log(hp_difference+1), Math.max(distance, Math.max(Math.log(distance+1), Math.max(-relative_speed, Math.max(-Math.log(relative_speed+1), Math.max(-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.max((2*nums[6]-1)/2, Math.max((2*nums[7]-1)/2, Math.max((1-2*nums[7])/2, (2*nums[8]-1)/2)))))))))));
		score[2]=0.125*((Math.pow(2.7, hp_difference))/2+Math.log(distance+1)+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-(Math.pow(2.7, relative_speed))/2-Math.log(relative_speed+1)-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[7]-1)/2+(2*nums[8]-1)/2);
		score[3]=8*(hp_difference+(Math.pow(2.7, hp_difference))/2-distance-(Math.pow(2.7, distance))/2-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+relative_speed+(2*nums[7]-1)/2+(2*nums[8]-1)/2);
		score[4]=1*(hp_difference+Math.log(hp_difference+1)+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-distance-(Math.pow(2.7, distance))/2-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-(Math.pow(2.7, relative_speed))/2+(1-2*nums[6])/2+(1-2*nums[8])/2);
		score[5]=2*(Math.log(hp_difference+1)+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-distance-relative_speed-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[7])/2);
		*/
		
		/*
		//10191 loss
		score[0]=8*Math.min(Math.log(relative_speed+1), (1-2*nums[7])/2);
		score[1]=0.25*(-hp_difference+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-distance-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+(1-2*nums[7])/2+(1-2*nums[8])/2);
		score[2]=8*(1.0/15.0)*(-hp_difference-(Math.pow(2.7, hp_difference))/2-relative_speed+(Math.pow(2.7, relative_speed))/2+(1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))));
		score[3]=0.125*(-(Math.pow(2.7, hp_difference))/2-distance-Math.log(distance+1)-Math.log(relative_speed+1)+(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[7]-1)/2);
		score[4]=8*(hp_difference-Math.log(hp_difference+1)-distance-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+(Math.pow(2.7, relative_speed))/2+(2*nums[7]-1)/2+(1-2*nums[8])/2);
		score[5]=8*(hp_difference-(Math.pow(2.7, hp_difference))/2+Math.log(hp_difference+1)+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))+distance-Math.log(distance+1)+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed+Math.log(relative_speed+1)+(2*nums[6]-1)/2+(1-2*nums[7])/2+(2*nums[8]-1)/2);
		*/
		/*
		//9426 loss
		score[0]=2*(1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))+(Math.pow(2.7, distance))/2-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+relative_speed-(Math.pow(2.7, relative_speed))/2+(1-2*nums[6])/2+(1-2*nums[7])/2+(2*nums[8]-1)/2);
		score[1]=0.25*(-Math.log(hp_difference+1)-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-Math.log(distance+1)+relative_speed+(1-2*nums[6])/2+(1-2*nums[7])/2+(2*nums[8]-1)/2);
		score[2]=0.25*Math.min(hp_difference, Math.min(Math.log(hp_difference+1), Math.min(-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))), Math.min(-distance, Math.min(-Math.log(distance+1), Math.min((1/(1+Math.pow(2.7, -1*(distance-0.5)))), Math.min((Math.pow(2.7, relative_speed))/2, Math.min((1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.min((2*nums[7]-1)/2, Math.min((-Math.pow(2.7, distance))/2,Math.min((Math.pow(2.7, distance))/2, Math.min(relative_speed, -relative_speed)) ))))))))));
		score[3]=0.25*Math.max(-hp_difference,Math.max(hp_difference, Math.max(Math.log(hp_difference+1), Math.max((1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))), Math.max(-distance, Math.max(Math.log(distance+1), Math.max(-(Math.pow(2.7, relative_speed))/2, Math.max(-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), Math.max((1-2*nums[6])/2, Math.max((1-2*nums[7])/2, (2*nums[8]-1)/2))))))))));
		score[4]=2*(-distance+Math.log(distance+1)+(2*nums[7]-1)/2+(1-2*nums[8])/2);
		score[5]=2*( (Math.pow(2.7, hp_difference))/2+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed-(Math.pow(2.7, relative_speed))/2+(1-2*nums[8])/2);
		*/		
		
		
		//8680 loss
		/*
		score[0]=4*(Math.log(hp_difference+1)+Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+relative_speed-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[7])/2);
		score[1]=Math.max(hp_difference, Math.max(-(Math.pow(2.7, hp_difference))/2, Math.max(- Math.log(hp_difference+1), Math.max((Math.pow(2.7, distance))/2, Math.max(Math.log(distance+1), Math.max((1/(1+Math.pow(2.7, -1*(distance-0.5)))), Math.max(relative_speed,Math.max((Math.pow(2.7, relative_speed))/2, Math.max((2*nums[6]-1)/2, (2*nums[7]-1)/2)))))))));
		score[2]=4*1.0/11.0*(-distance+(Math.pow(2.7, distance))/2-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed-(Math.pow(2.7, relative_speed))/2+(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[6]-1)/2+(2*nums[7]-1)/2);
		score[3]=0.5*(hp_difference+(Math.pow(2.7, hp_difference))/2+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-Math.log(relative_speed+1)+(2*nums[7]-1)/2);
		score[4]=2*(-(Math.pow(2.7, hp_difference))/2+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))+(Math.pow(2.7, distance))/2+Math.log(distance+1)+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed-(Math.pow(2.7, relative_speed))/2+Math.log(relative_speed+1)+(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[7]-1)/2);
		score[5]=4*(hp_difference-(Math.pow(2.7, hp_difference))/2+Math.log(hp_difference+1)+(Math.pow(2.7, distance))/2-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[7])/2);
		*/
		
		/*
		 * relative_speed
		 * (Math.pow(2.7, relative_speed))/2
		 * Math.log(distance+1)
		 * (1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))
		 */
		
		//3802 loss 
		/*
		score[0]=2*(1.0/14.0)*(-hp_difference-(Math.pow(2.7, hp_difference))/2+Math.log(hp_difference+1)-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-(Math.pow(2.7, distance))/2+Math.log(distance+1)-Math.log(relative_speed+1)+(1-2*nums[7])/2);
		score[1]=2*(-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-distance+(Math.pow(2.7, distance))/2+(1/(1+Math.pow(2.7, -1*(distance-0.5))))-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[7]-1)/2);
		score[2]=2*(1.0/12.0)*(-hp_difference-(Math.pow(2.7, hp_difference))/2-distance-Math.log(distance+1)+relative_speed-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[6]-1)/2+(2*nums[7]-1)/2);
		score[3]=0.5*Math.min(-Math.log(hp_difference+1), Math.min(-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5)))), Math.min(-Math.log(distance+1), Math.min(-Math.log(relative_speed+1), Math.min(-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5)))), (2*nums[6]-1)/2)))));
		*/
		
		//86 loss
		/*
		score[0]=(1-2*nums[7])/2;
		score[1]=-hp_difference+(Math.pow(2.7, hp_difference))/2-distance+(Math.pow(2.7, distance))/2+Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed+Math.log(relative_speed+1)+(2*nums[7]-1)/2;
		*/
		
		//1444 loss
		/*
		score[0]=2*Math.max((Math.pow(2.7, hp_difference))/2,(Math.pow(2.7, relative_speed))/2);
		score[1]=4*((Math.pow(2.7, distance))/2-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))+ Math.log(relative_speed+1)-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[7])/2);
		*/
		// 2418 loss
		score[0]=0.5*(hp_difference+Math.log(hp_difference+1)-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))+distance-(Math.pow(2.7, distance))/2-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(1-2*nums[6])/2);
		score[1]=4*(-hp_difference-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-relative_speed+(Math.pow(2.7, relative_speed))/2+Math.log(relative_speed+1)+(2*nums[7]-1)/2);
		score[2]=0.5*(-(Math.pow(2.7, hp_difference))/2-Math.log(hp_difference+1)-(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-(Math.pow(2.7, distance))/2+Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-relative_speed+(Math.pow(2.7, relative_speed))/2-(1/(1+Math.pow(2.7, -1*(relative_speed-0.5))))+(2*nums[6]-1)/2+(1-2*nums[7])/2);
		score[3]=0.5*(hp_difference+Math.log(hp_difference+1)+(1/(1+Math.pow(2.7, -1*(hp_difference-0.5))))-distance-(Math.pow(2.7, distance))/2-Math.log(distance+1)-(1/(1+Math.pow(2.7, -1*(distance-0.5))))-(Math.pow(2.7, relative_speed))/2+(1-2*nums[6])/2+(2*nums[7]-1)/2);
		
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
		int loss=0;
		for(int j=0;j<num_action;j++)
		{
			if(j!=index && score[j]>score[index])
				loss+=1;
			if(score[j]>max_score)
			{
				max_score=score[j];
				max_index=j;
			}
		}
		if(loss>0)
		{
			error_count[index][max_index]++;
		}
		loss_array[index][loss]+=1;
		loss_count[loss]++;
		score_count[max_index]++;
		count+=1;

	}
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
	}
}
