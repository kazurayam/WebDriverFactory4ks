package com.kazurayam.ks.webdriverfactory.utils

import java.nio.ByteBuffer
import java.nio.channels.FileChannel

public class FileUtils {

	private static int BUFFER_SIZE = 4096
	
	/**
	 * IOUtils is not be be instantiated. 
	 */
	private FileUtils() {}

	/**
	 * open the source file in READ-ONLY mode and copy it into the destination file.
	 * 
	 * @param source
	 * @param dest
	 * @return
	 * @throws IOException
	 */
	static void copyFile(File source, File dest) throws IOException {
		Objects.requireNonNull(source, "source must not be null")
		Objects.requireNonNull(dest, "dest must not be null")
		if (!source.exists()) {
			throw new IOException("${source} does not exist")
		}
		if (!source.canRead()) {
			throw new IOException("${source} is not readable")
		}
		File parent = dest.getParentFile()
		if (!parent.exists()) {
			parent.mkdirs()
		}
		new RandomAccessFile(source, "r").withCloseable() { raf ->
			FileChannel fc = raf.getChannel()
			new ByteArrayOutputStream().withCloseable { baos ->
				//if (BUFFER_SIZE > channel.size())
			}
		}
	}
	
}
