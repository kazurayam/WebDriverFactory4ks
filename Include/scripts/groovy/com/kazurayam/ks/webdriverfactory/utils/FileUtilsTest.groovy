package com.kazurayam.ks.webdriverfactory.utils

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.ByteBuffer
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
	public void givenFile_whenReadWithFileChannelUsingRandomAccessFile_thenCorrect() {
		String inputPath = "Include/fixture/test_read.in"
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
	public void whenWriteWithFileChannelUsingRandomAccessFile_thenCorrect()
	  throws IOException {
		File file = new File("tmp/test/test_write_using_filechannel.txt")
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs()
		}
		new RandomAccessFile(file, "rw").withCloseable { raf ->
			FileChannel channel = raf.getChannel()
			ByteBuffer buff = ByteBuffer.wrap("Hello world".getBytes(StandardCharsets.UTF_8))
			channel.write(buff)
		}
		// verify
		assertEquals("Hello world", file.text)
	}
}