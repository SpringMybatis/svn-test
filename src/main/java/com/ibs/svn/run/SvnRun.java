package com.ibs.svn.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SvnRun {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		File csv = null;
		String csvPath = "F:/svnTestDir";
		try {
			
			File csvDirectory = new File(csvPath); // CSV数据文件
			if(!csvDirectory.exists()){
				csvDirectory.mkdirs();
			}
			csv = new File(csvPath+"/make.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // 附加
			// 添加新的数据行
			bw.write("\"李四\"" + "," + "\"1988\"" + "," + "\"1992\"");
			bw.newLine();
			bw.close();
		} catch (FileNotFoundException e) {
			// File对象的创建过程中的异常捕获
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedWriter在关闭对象捕捉异常
			e.printStackTrace();
		}
		// 提交目录到SVN
		if(csv!=null){
			String path = csv.getPath();
			String makdirCsv = "svn mkdir https://192.168.1.212:8443/svn/GB/branches/csv -m \"创建CSV\"";
			// String commitCsv = "svn commit -m \"CSV COMMIT\" " +path;
			String importCsv = "svn import -m \"CSV IMPORT\" "+csvPath+" https://192.168.1.212:8443/svn/GB/branches/csv";
			// String exportCsv = "svn export https://192.168.1.212:8443/svn/GB/branches/csv F:/exportCsv";
			String checkoutCsv = "svn checkout https://192.168.1.212:8443/svn/GB/branches/csv F:/exportCsv";
			
			System.out.println(makdirCsv);
			System.out.println(importCsv);
			System.out.println(checkoutCsv);
			
			String cmd = makdirCsv+"&&"+importCsv+"&&"+checkoutCsv;
			System.out.println(cmd);
			
			// String cmd = "cmd /k "+makdirCsv+"&&"+importCsv+"&&"+checkoutCsv;
			Runtime.getRuntime().exec(cmd);
			// Runtime.getRuntime().exec(makdirCsv);
			// Runtime.getRuntime().exec(importCsv);
			// Runtime.getRuntime().exec(checkoutCsv);
			
		}
		try {  
            BufferedReader reader = new BufferedReader(new FileReader("F:/svnTestDir/make.csv"));//换成你的文件名 
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;  
            while((line=reader.readLine())!=null){  
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                String last = item[item.length-1];//这就是你要的数据了 
                System.out.println(last);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
	}

}
