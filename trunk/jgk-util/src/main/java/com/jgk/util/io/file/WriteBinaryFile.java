package com.jgk.util.io.file;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class WriteBinaryFile {
	public void writeRandomInts(File file, Integer num) {
        Random r = new Random(System.currentTimeMillis());
        try {
        	DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        	for (int i = 0 ; i < num ; i++) {
    			os.writeInt(r.nextInt());
			}
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void writeRandomLongs(File file, Integer num) {
        Random r = new Random(System.currentTimeMillis());
        try {
        	DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
        	for (int i = 0 ; i < num ; i++) {
    			os.writeLong(r.nextLong());
			}
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    public static void main(String[] argv) throws IOException {
        System.out.println(System.getProperty("user.home"));
//        if(true)return;
        int i = 42;
        double d = Math.PI;
        String FILENAME = "binary.dat";
        DataOutputStream os = new DataOutputStream(new FileOutputStream(new File(System.getProperty("user.home"),
            FILENAME)));
        Random r = new Random(System.currentTimeMillis());
//        int num = 2000000;
//        os.writeInt(i);
//        os.writeInt(i);
//        os.writeDouble(d);
//        for(int c = 0 ; c < num ; c++ ) {
//            os.writeInt(r.nextInt());
//        }
//        os.writeInt(1);
        os.writeLong(1l);
        os.flush();
        os.close();
//        System.out.println("Wrote " + i + ", " + d + " to file " + FILENAME);
      }

}
