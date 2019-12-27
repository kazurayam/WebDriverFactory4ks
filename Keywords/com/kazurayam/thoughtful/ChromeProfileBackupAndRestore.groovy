package com.kazurayam.thoughtful

import java.nio.file.Files
import java.nio.file.Path
import org.apache.commons.io.FileUtils

/**
 * ChromeProfileUtils class can
 * 
 * - backup a Google Chrome Profile to a folder you specified
 * - restore a Google Chrome Profile from the backup
 * 
 * @author kazurayam
 *
 */
public class ChromeProfileBackupAndRestore {

	/**
	 * hidden constructor
	 * all methods of this class should be static
	 */
	private ChromeProfileBackupAndRestore() {}

	/**
	 * Assuming that 
	 * 1. there is a Profile named 'Katalon'
	 * 2. 'Katalon ' is associted with the profile-path 'C:\Users\kazurayam\AppData\Local\Google\Chrome\User Data\Profile 2',
	 * 
	 * then
	 * 
	 * ```
	 * Path toDir = Paths.get('./tmp') 
	 * ChromeProfileUtils.backup(toDir, 'Katalon')`
	 * ```
	 * 
	 * envoking this snippet will create
	 * 
	 * `./tmp/Katalon/Cache`
	 * 
	 * and other files/directories.
	 * 
	 * @param userName
	 * @param toDir
	 */
	public static void backup(Path toDir, String profileName) {
		Objects.requireNonNull(toDir, "toDir must not be null")
		Objects.requireNonNull(profileName, "profileName must not be null")
		// identify the Profile to backup
		if ( ! ChromeProfileFinder.hasChromeProfile(profileName) ) {
			throw new IllegalArgumentException("Chrome Profile \"${profileName}\" is not defined")
		}
		ChromeProfile profile = ChromeProfileFinder.getChromeProfile(profileName)
		Path profilePath = profile.getProfilePath()
		// identify the location to write data into
		Path backup = toDir.resolve(profileName)
		if (Files.exists(backup)) {
			backup.toFile().deleteDir()    // File#deleteDir() is a Groovy extention
		}
		Files.createDirectories(backup)
		// copy files recursively
		copyDirectory(profilePath, backup)
	}

	/**
	 * Assuming that 
	 * 1. you have backed up a Profile 'Katalon' in `./tmp/Katalon` directory
	 * 2. you hava a Chrome Profile 'Katalon'.
	 * 
	 * then
	 * 
	 * ```
	 * Path fromDir = Paths.get('./tmp')
	 * ChromeProfileUtils.restore(fromDir, 'Katalon')
	 * ```
	 * 
	 * envoking this snippet will update the files/directories in the profile-path
	 * associated with the Profile 'Katalon'
	 * 
	 * @param profileName
	 * @param fromDir
	 */
	public static void restore(Path fromDir, String profileName) {
		restore(fromDir, profileName, profileName)
	}

	/**
	 * Assuming that
	 * 1. you have backed up a Profile 'Katalon' in `./tmp/Katalon` directory
	 * 3. you have another Profile 'Katalon_alt"
	 *
	 * then
	 *
	 * ```
	 * Path fromDir = Paths.get('./tmp')
	 * ChromeProfileUtils.restore(fromDir, 'Katalon', 'Katalon_alt')
	 * ```
	 *
	 * envoking this snippet will update the files/directories in the profile-path
	 * associated with the Profile 'Katalon' into another Profile 'Katalon_alt'
	 *
	 * @param profileName
	 * @param fromDir
	 */

	public static void restore(Path fromDir, String sourceProfileName, String destProfileName) {
		Objects.requireNonNull(fromDir, "fromDir must not be null")
		Objects.requireNonNull(sourceProfileName, "sourceProfileName must not be null")
		Objects.requireNonNull(destProfileName, "destProfileName must not be null")
		
		// indentify the location to read the backup from
		Path backup = fromDir.resolve(sourceProfileName)
		if ( ! Files.exists(backup) ) {
			throw new IllegalArgumentException("Backup directory \"${backup}\" does not exist")
		}

		// locate the destination
		if ( ! ChromeProfileFinder.hasChromeProfile(destProfileName) ) {
			throw new IllegalArgumentException("Chrome Profile \"${destProfileName}\" is not defined")
		}
		ChromeProfile destProfile = ChromeProfileFinder.getChromeProfile(destProfileName)
		Path destProfilePath = destProfile.getProfilePath()
		// clean the destination directory before restoring with the backup
		FileUtils.cleanDirectory(destProfilePath.toFile())

		// copy files recursively
		copyDirectory(backup, destProfilePath)
	}

	/**
	 * copy the src directory to the dest directory recursively
	 * 
	 * E.g, provided that we have `./tmp/dir1/subdir/a.txt`,
	 * 
	 * ```
	 * Path src = Paths.get('./tmp/dir1')
	 * Path dest = Paths.get('./tmp/dir2')
	 * ChromeProfileUtils.copyDirectory(src, dest)
	 * Path a = dest.resolve('subdir/a.txt')      // a is './tmp/dir2/subdir/a.txt'
	 * assert Files.exists(a)
	 * ```
	 */
	public static void copyDirectory(Path src, Path dest) {
		try {
			Files.walk( src ).forEach { s ->
				try {
					Path d = dest.resolve( src.relativize(s) );
					if( Files.isDirectory( s ) ) {
						if( !Files.exists( d ) )
							Files.createDirectory( d );
						return;
					}
					Files.copy( s, d );// use flag to override existing
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
		} catch( Exception ex ) {
			ex.printStackTrace()
		}
	}
}
