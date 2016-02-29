package edu.wm.translationengine.espresso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wm.translationengine.classes.*;
import edu.wm.translationengine.main.*;

/**
 * This is the EspressoTranslator class, it takes Action objects and creates Espresso
 * commands that correspond to those Action objects.
 * 
 * @author Nathan Chen
 *
 */
public class EspressoTranslator extends GenericTranslator {
	private EspressoFunctions ef;
	/*
	public static File fout;
	private FileOutputStream fos;
	private BufferedWriter bw;
	public static ArrayList<String> toWrite;
	*/
	
	/**
	 * Constructor for the EspressoTranslator class.
	 * @throws IOException 
	 */
	public EspressoTranslator() throws IOException{
		super();
		/*
		fout = new File("TestFile.java");
		fos = new FileOutputStream(fout);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		toWrite = new ArrayList<String>();
		setupFile();
		*/
		ef = new EspressoFunctions();
	}
	
	
	public void setupFile() throws IOException{
		toWrite.add("import static android.support.test.espresso.Espresso.onView;\n");
		toWrite.add("import static android.support.test.espresso.matcher.ViewMatchers.withId;\n");
		toWrite.add("import static android.support.test.espresso.matcher.ViewMatchers.withText;\n");
		toWrite.add("import org.junit.runner.RunWith;\n");
		toWrite.add("import android.app.Activity;\n");
		toWrite.add("import android.support.test.espresso.action.ViewActions;\n");
		toWrite.add("import android.support.test.espresso.matcher.ViewMatchers;\n");
		toWrite.add("import android.support.test.rule.ActivityTestRule;\n");
		toWrite.add("import android.support.test.runner.AndroidJUnit4;\n");
		toWrite.add("import android.test.ActivityInstrumentationTestCase2;\n");
		toWrite.add("import android.test.suitebuilder.annotation.LargeTest;\n");
		toWrite.add("import android.support.test.runner.AndroidJUnitRunner;\n");
		toWrite.add("import junit.framework.TestSuite;\n");
	}
	
	/**
	 * Writes the argument String s to the file fout
	 * @param s String s to be written into file
	 * @throws IOException
	 */
	public void writeToFile(ArrayList<String> al) throws IOException{
		for(int i = 0; i < al.size(); i++){
			bw.write(al.get(i));
		}
	}
	
	/**
	 * Edit by Mark: Make a default, works w/out anything passed in.
	 * 
	 * Writes the argument String s to the file fout
	 * @param s String s to be written into file
	 * @throws IOException
	 */
	public void writeToFile() throws IOException{
		ArrayList<String> al = toWrite;
		for(int i = 0; i < al.size(); i++){
			bw.write(al.get(i));
		}
	}	
	
	/**
	 * Iterates through all the StepTestCase objects in the stepTestCases List.
	 * @param testCase The current TestCase object being looked at
	 * @throws IOException 
	 */
	public void steps_iterator(TestCase testCase) throws IOException{
		String appName = testCase.getAppName();
		String packageName = testCase.getPackageName();
		String mainActivity = testCase.getMainActivity();
		List<StepTestCase> stepTestCases = testCase.getSteps();
		
		toWrite.add("import " + packageName + "." + mainActivity + ";\n");
		toWrite.add("import " + packageName + ".R;\n");
		toWrite.add("\n\n\n@RunWith(AndroidJUnit4.class)\n");
		toWrite.add("@LargeTest\n");
		toWrite.add("public class TestFile extends TestSuite{\n");
		toWrite.add("\t public ActivityTestRule<" + mainActivity + "> "
				+ "mActivityRule = new ActivityTestRule<" + mainActivity +
				">(" + mainActivity + ".class);\n");
		toWrite.add("\t\t@Test\n");
		toWrite.add("\t\tpublic void test(){\n");
		
		for(int i = 0; i < stepTestCases.size(); i++){
			StepTestCase cur = stepTestCases.get(i);
			ef.espresso_switcher(cur.getAction(), cur);
		}
		toWrite.add("\t }\n }");
		
		/*Deleted since they are now called in main()
		 * 
		 * 		writeToFile(toWrite);
		 * 		closeFile();
		 * 
		 * 
		 */

	}


}
