import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class crossvalidation {
	
	public static void main(String[] args) throws IOException{
		String filename="TOVOR_STAND_GUARD_78";
		File file=new File(filename+"_training.txt");
		BufferedWriter tr1=new BufferedWriter(new FileWriter(filename+"_training_1.txt"));
		BufferedWriter tr2=new BufferedWriter(new FileWriter(filename+"_training_2.txt"));
		BufferedWriter tr3=new BufferedWriter(new FileWriter(filename+"_training_3.txt"));
		BufferedWriter tr4=new BufferedWriter(new FileWriter(filename+"_training_4.txt"));
		BufferedWriter tr5=new BufferedWriter(new FileWriter(filename+"_training_5.txt"));
		BufferedWriter te1=new BufferedWriter(new FileWriter(filename+"_testing_1.txt"));
		BufferedWriter te2=new BufferedWriter(new FileWriter(filename+"_testing_2.txt"));
		BufferedWriter te3=new BufferedWriter(new FileWriter(filename+"_testing_3.txt"));
		BufferedWriter te4=new BufferedWriter(new FileWriter(filename+"_testing_4.txt"));
		BufferedWriter te5=new BufferedWriter(new FileWriter(filename+"_testing_5.txt"));
	    
		BufferedReader buf=new BufferedReader(new FileReader(file));//读取文件
		int count=0;
		while(true)
	{
		String str=buf.readLine();//一行一行读取数据
		if(str==null)
			break;
		if(count==0)
		{
			te1.write(str);
			te1.newLine();
			te1.flush();
			tr2.write(str);
			tr2.newLine();
			tr2.flush();
			tr3.write(str);
			tr3.newLine();
			tr3.flush();
			tr4.write(str);
			tr4.newLine();
			tr4.flush();
			tr5.write(str);
			tr5.newLine();	
			tr5.flush();
		}
		else if(count==1)
		{
			te2.write(str);
			te2.newLine();
			te2.flush();
			tr1.write(str);
			tr1.newLine();
			tr1.flush();
			tr3.write(str);
			tr3.newLine();
			tr3.flush();
			tr4.write(str);
			tr4.newLine();
			tr4.flush();
			tr5.write(str);
			tr5.newLine();	
			tr5.flush();
		}
		else if(count==2)
		{
			te3.write(str);
			te3.newLine();
			te3.flush();
			tr1.write(str);
			tr1.newLine();
			tr1.flush();
			tr2.write(str);
			tr2.newLine();
			tr2.flush();
			tr4.write(str);
			tr4.newLine();
			tr4.flush();
			tr5.write(str);
			tr5.newLine();	
			tr5.flush();
		}
		else if(count==3)
		{
			te4.write(str);
			te4.newLine();
			te4.flush();
			tr1.write(str);
			tr1.newLine();
			tr1.flush();
			tr2.write(str);
			tr2.newLine();
			tr2.flush();
			tr3.write(str);
			tr3.newLine();
			tr3.flush();
			tr5.write(str);
			tr5.newLine();		
			tr5.flush();
		}
		else 
		{
			te5.write(str);
			te5.newLine();
			te5.flush();
			tr1.write(str);
			tr1.newLine();
			tr1.flush();
			tr2.write(str);
			tr2.newLine();
			tr2.flush();
			tr3.write(str);
			tr3.newLine();
			tr3.flush();
			tr4.write(str);
			tr4.newLine();		
			tr4.flush();
		}
		count++;
		count=count%5;
	}
		buf.close();
		tr1.close();
		tr2.close();
		tr3.close();
		tr4.close();
		tr5.close();
		te1.close();
		te2.close();
		te3.close();
		te4.close();
		te5.close();
	}

}
