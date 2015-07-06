package com.ibs.svn.run;

import java.io.IOException;
import java.io.OutputStream;

public class MultiCndRun {

	public static void main(String[] args) throws Exception {

		Runtime rt = Runtime.getRuntime();
		String cmd = "cmd /k D: && cd D:\\IMSI-MSISDN && dir /b";//这一句真的对吗？
		Process pr = rt.exec(cmd);
		// OutputStream out = pr.getOutputStream();
		
	}

}
