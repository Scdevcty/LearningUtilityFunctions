import java.io.*;

public class filecomb {
    public static  void  main(String[] args) throws IOException {
       //定义输出目录
        String FileOut="TOVOR_20_training_percents_nocanhit.txt";
        BufferedWriter bw=new BufferedWriter(new FileWriter(FileOut));

      //读取目录下的每个文件或者文件夹，并读取文件的内容写到目标文字中去
        /*
         * ,"TOVOR_STAND_GUARD_100_testing_1.txt","TOVOR_BACK_STEP_100_testing_1.txt"
         */
        String filename[]= {"TOVOR_20_training_1_percents_nocanhit.txt","TOVOR_20_training_2_percents_nocanhit.txt","TOVOR_20_training_3_percents_nocanhit.txt","TOVOR_20_training_4_percents_nocanhit.txt","TOVOR_20_training_5_percents_nocanhit.txt"};
       
        int fileCount = 5;
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