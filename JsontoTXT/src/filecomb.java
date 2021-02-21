import java.io.*;

public class filecomb {
    public static  void  main(String[] args) throws IOException {
       //定义输出目录
        String FileOut="TOVOR_MIX_78.txt";
        BufferedWriter bw=new BufferedWriter(new FileWriter(FileOut));

      //读取目录下的每个文件或者文件夹，并读取文件的内容写到目标文字中去
        String filename[]= {"TOVOR_FORWARD_WALK_78","TOVOR_STAND_A_78","TOVOR_BACK_STEP_78","TOVOR_STAND_GUARD_78"};
       
        int fileCount = 4;
        for(int i=0;i<fileCount;i++)
        {

             
                BufferedReader br = new BufferedReader(new FileReader(filename[i]));
                String line;
                while((line=br.readLine())!=null) {
                    bw.write(line);
                    bw.newLine();
                }
                br.close();

        }
        bw.close();

    }

}