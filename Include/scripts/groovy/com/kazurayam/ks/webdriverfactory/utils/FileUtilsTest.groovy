package com.kazurayam.ks.webdriverfactory.utils

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.channels.FileChannel
import java.nio.charset.StandardCharsets

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class FileUtilsTest {

	/**
	 * Try reading a file in READ-ONLY mode using java.nio.channels.FileChannel and
	 * java.io.RandomAccessFile
	 * 
	 * The source is quoted from 
	 * https://www.baeldung.com/java-filechannel
	 */
	@Test
	public void test_readFromRandomAccessFile_READONLY() {
		String inputPath = "Include/fixture/utils/FileUtilsTest/test_read.in"
		// open the input file in READ-ONLY mode
		new RandomAccessFile(inputPath, "r").withCloseable() { raf ->
			FileChannel channel = raf.getChannel()
			new ByteArrayOutputStream().withCloseable { baos ->

				int bufferSize = 4096;
				if (bufferSize > channel.size()) {
					bufferSize = (int) channel.size()
				}
				ByteBuffer buff = ByteBuffer.allocate(bufferSize)

				while (channel.read(buff) > 0) {
					baos.write(buff.array(), 0, buff.position())
					buff.clear()
				}

				String fileContent = new String(baos.toByteArray(), StandardCharsets.UTF_8)
				assertEquals("Hello world", fileContent);
			}
		}
	}

	/**
	 * Try writing a file in READ+WRITE mode using java.io.channels.FileChannel and
	 * java.io.RandomAccessFile
	 * 
	 * The source is quoted from 
	 * https://www.baeldung.com/java-filechannel
	 */
	@Test
	public void test_writingIntoRandomAccessFile() {
		File file = new File("tmp/testOutput/utils/FileUtilsTest/test_write_using_filechannel.txt")
		ensureDirs(file)
		new RandomAccessFile(file, "rw").withCloseable { raf ->
			FileChannel channel = raf.getChannel()
			ByteBuffer buff = ByteBuffer.wrap("Hello world".getBytes(StandardCharsets.UTF_8))
			channel.write(buff)
		}
		// verify
		assertEquals("Hello world", file.text)
	}

	@Test
	public void test_copyReadOnlyFile() {
		File input = new File("Include/fixture/utils/FileUtilsTest/copyThisFile.txt")
		File output = new File("tmp/testOutput/utils/FileUtilsTest/copiedFile.txt")
		ensureDirs(output)
		FileUtils.copyReadOnlyFile(input, output)
		assertTrue(output.exists())
		assertEquals("copy this file, please.", output.text)
	}
	
	@Test
	public void test_copyReadOnlyDirectory() {
		Path input = Paths.get("Include/fixture/utils/FileUtilsTest/User Data")
		Path output = Paths.get("tmp/testOutput/utils/FileUtilsTest/User Data")
		ensureDirs(output.toFile())
		FileUtils.copyReadOnlyDirectory(input, output)
		assertTrue(Files.exists(output))
		assertTrue(Files.exists(output.resolve("Profile 2/Cache/data_0")))
	}
	
	private void ensureDirs(File file) {
		File parent = file.getParentFile()
		if (!parent.exists()) {
			boolean b = parent.mkdirs()
			if (!b) {
				throw new IOException("failed to create a directory ${parent}")
			}
		}
		
	}
}