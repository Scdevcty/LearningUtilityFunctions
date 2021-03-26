import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class pickmost {
	public static  void  main(String[] args) throws IOException {
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		String most="";
		String loss="";
		int maxnum=0;
		
		File file=new File("TOVOR_100_training_nocanhit.txt");//创建一个file对象，参数是你的文件路径
		BufferedReader buf=new BufferedReader(new FileReader(file));//读取文件
		
		while(true)
		{
			String str=buf.readLine();
			if(str==null)
				break;
			String[] strs=str.split(" ");
			
			if(map.containsKey(strs[0]))
			{
				map.put(strs[0], map.get(strs[0])+1);
				if(map.get(strs[0])>maxnum)
				{
					maxnum=map.get(strs[0]);
					most=strs[3];
					loss=strs[0];
				}
			}
			else
			{
				map.put(strs[0],1);
				if(map.get(strs[0])>maxnum)
				{
					maxnum=map.get(strs[0]);
					most=strs[3];
					loss=strs[0];
				}
			}
		}
		System.out.println(maxnum);
		System.out.println(most);
		System.out.println(loss);
	}

}
