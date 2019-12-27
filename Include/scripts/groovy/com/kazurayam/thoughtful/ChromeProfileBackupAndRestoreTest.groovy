package com.kazurayam.thoughtful

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class ChromeProfileBackupAndRestoreTest {

	static Path classOutputDir_
	@BeforeClass
	public static void setup() {
		classOutputDir_ = Paths.get('build/tmp/testOutput/ChromeProfileBackupAndRestoreTest')
	}

	@Test
	void test_copyDirectory() {
		Path caseOutputDir = classOutputDir_.resolve('test_copyDirectory')
		Files.createDirectories(caseOutputDir)
		//
		Path srcDir = caseOutputDir.resolve('dir1')
		Path a = srcDir.resolve('subdir/a.txt')
		Files.createDirectories(a.getParent())
		a.toFile().text = 'Hello, world'
		//
		Path destDir =  caseOutputDir.resolve('dir2')
		//
		ChromeProfileBackupAndRestore.copyDirectory(srcDir, destDir)
		//
		Path a2 = destDir.resolve('subdir/a.txt')
		//
		assert a2.toFile().exists()
		assert a2.toFile().text == 'Hello, world'
	}
	
	@Test
	void test_backup() {
		Path caseOutputDir = classOutputDir_.resolve('test_backup')
		Files.createDirectories(caseOutputDir)
		//
		Path backupDir = caseOutputDir.resolve('profiles')
		//
		ChromeProfileBackupAndRestore.backup(backupDir, 'Katalon')
		//
		Path cookie = backupDir.resolve('Katalon/Cookies')
		assert Files.exists(cookie)
	}
	
	@Test
	void test_restore_replicate() {
		Path caseOutputDir = classOutputDir_.resolve('test_restore')
		Files.createDirectories(caseOutputDir)
		Path backupDir = caseOutputDir.resolve('profiles')
		ChromeProfileBackupAndRestore.backup(backupDir, 'Katalon')
		//
		ChromeProfileBackupAndRestore.restore(backupDir, 'Katalon', 'Katalon_alt')
	}
}
