package com.now.cloudphoto.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;
import android.util.Log;

public class FileUtil {
	public static void writeTextToFile(String data){
		try{
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
			file.createNewFile();
			if(file.exists())
			{
				OutputStream fo = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fo);
				osw.write(data);
				osw.flush();
				osw.close();
			}     
		}
		catch(Exception e){
			Log.e(Constants.LOG_TAG,"Error write file: "+ e);
		}
	}
}
