package com.kazurayam.ks.webdriverfactory.utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.webdriverfactory.utils.Assert as MyAssert

import com.kms.katalon.core.exception.StepFailedException
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.ByteBuffer

@RunWith(JUnit4.class)
public class IOUtilsTest {
	
	@Test
	public void givenFile_whenReadWithFileChannelUsingRandomAccessFile_thenCorrect() throws IOException {
		new RandomAccessFile("Include/fixture/test_read.in", "r").withReader { reader ->
			FileChannel channel = reader.getChannel();
			new ByteArrayOutputStream().withWriter { out ->
	 
			int bufferSize = 1024;
			if (bufferSize > channel.size()) {
			   bufferSize = (int) channel.size();
			}
			ByteBuffer buff = ByteBuffer.allocate(bufferSize);
	 
			while (channel.read(buff) > 0) {
				out.write(buff.array(), 0, buff.position());
				buff.clear();
			}
			 
		 String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
	  
		 assertEquals("Hello world", fileContent);
		}
	}
}
