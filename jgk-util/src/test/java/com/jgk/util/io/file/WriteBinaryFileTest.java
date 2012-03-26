package com.jgk.util.io.file;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;


public class WriteBinaryFileTest {

	@Test public void testWriteLongs() {
		WriteBinaryFile wbf = new WriteBinaryFile();
		File targetDir = new File("target");
		if (!targetDir.exists()) {
			throw new RuntimeException("Missing target directory: "+targetDir.getAbsolutePath());
		}
		File file = new File(targetDir,"testBinaryLong.dat");
		wbf.writeRandomLongs(file, 1);
		Assert.assertEquals(8, file.length());
		file.delete();
		Assert.assertFalse(file.exists());
	}
	@Test public void testWriteInts() {
		WriteBinaryFile wbf = new WriteBinaryFile();
		File targetDir = new File("target");
		if (!targetDir.exists()) {
			throw new RuntimeException("Missing target directory: "+targetDir.getAbsolutePath());
		}
		File file = new File(targetDir,"testBinaryInt.dat");
		wbf.writeRandomInts(file, 1);
		Assert.assertEquals(4, file.length());
		file.delete();
		Assert.assertFalse(file.exists());
	}
}
