package com.kazurayam.ks.webdriverfactory.utils

import static java.nio.file.FileVisitResult.*
import static java.nio.file.StandardCopyOption.*

import java.nio.channels.FileChannel
import java.nio.file.FileAlreadyExistsException
import java.nio.file.FileSystemLoopException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes


public class FileUtils {

	private static int BUFFER_SIZE = 4096

	/**
	 * IOUtils is not be be instantiated. 
	 */
	private FileUtils() {}

	/**
	 * Copy a file from the source to the dest.
	 * Will open the source file in READ-ONLY mode.
	 * 
	 * @param source
	 * @param dest
	 * @return
	 * @throws IOException
	 */
	static void copyReadOnlyFile(File source, File dest) throws IOException {
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
		new RandomAccessFile(source, "r").withCloseable { sourceRaf ->
			new RandomAccessFile(dest, "rw").withCloseable { destRaf ->
				@SuppressWarnings("resource")
				FileChannel inCh = sourceRaf.getChannel()
				@SuppressWarnings("resource")
				FileChannel outCh = destRaf.getChannel()
				// copy super quickly all bytes from the source to the dest
				inCh.transferTo(0, inCh.size(), outCh)
			}
		}
	}
	
	/**
	 * 
	 */
	static int copyReadOnlyDirectory(File source, File target, boolean skipIfIdentical = true) {
		return copyReadOnlyDirectory(source.toPath(), target.toPath(), skipIfIdentical)	
	}
	
	/**
	 * Copies descendent files and directories recursively
	 * from the source directory into the target directory.
	 * Will use copyFile() method of this class that opens the input files in READ-ONLY mode.
	 * 
	 *
	 * @param source a directory from which files and directories are copied
	 * @param target a directory into which files and directories are copied
	 * @param skipExisting default to true
	 * @return
	 */
	static int copyReadOnlyDirectory(Path source, Path target, boolean skipIfIdentical = true) {
		Objects.requireNonNull(source, "source must not be null")
		Objects.requireNonNull(target, "target must not be null")
		if (!Files.exists(source)) {
			throw new IllegalArgumentException("${source.normalize().toAbsolutePath()} does not exist")
		}
		if (!Files.isDirectory(source)) {
			throw new IllegalArgumentException("${source.normalize().toAbsolutePath()} is not a directory")
		}
		if (!Files.isReadable(source)) {
			throw new IllegalArgumentException("${source.normalize().toAbsolutePath()} is not readable")
		}
		
		// if target directory is not there, create it
		Files.createDirectories(target)
		
		// number of files copied
		int count = 0
		
		Files.walkFileTree(source,
			new HashSet<>(),
			Integer.MAX_VALUE,
			new SimpleFileVisitor<Path>() {
				@Override
				FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
					Path targetdir = target.resolve(source.relativize(dir))
					try {
						Files.copy(dir, targetdir)
					} catch (FileAlreadyExistsException e) {
						if (!Files.isDirectory(targetdir))
							throw e
					}
					return CONTINUE
				}
				@Override
				FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
					Path targetFile = target.resolve(source.relativize(file))
					File sourceF = file.toFile()
					File targetF = targetFile.toFile()
					if (skipIfIdentical &&
						Files.exists(targetFile) &&
						sourceF.length() == targetF.length() &&
						sourceF.lastModified() == targetF.lastModified()) {
						; // skip copying if sourceF and targetF are identical
					} else {
						// use special method to copy file while opening the input in READ-ONLY mode
						FileUtils.copyReadOnlyFile(file.toFile(), targetFile.toFile())
						count += 1
					}
					return CONTINUE
				}
				@Override
				FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					if (exc instanceof FileSystemLoopException) {
						//logger_.warn("circular link was detected: " + file)
						System.err.println("[FileUtils#copyReadOnlyDirectory] circular link was detected: "
							+ file + ", which will be ignored")
					} else {
						//logger_.warn("unable to process file: " + file)
						System.err.println("[FileUtils#copyReadOnlyDirectory] unable to process file: "
							+ file + ", which will be ignored")
					}
					return CONTINUE
				}
			}
		)
		
		return count
	}
	
}
