package edu.wm.translationengine.espresso;

import java.io.IOException;
import java.util.ArrayList;

import edu.wm.translationengine.trans.FileModifierInterface;
/**
 * This is the EspressoFileModifier class, it is the centralized hub for formatting the output
 * test file. It sets up the immediately necessary imports and sets of the test method
 * header. It can also close the test method.
 * @author Nathan Chen
 *
 */
public class EspressoFileModifier implements FileModifierInterface{
	/**
	 * Constructor for the EspressoFileModifier class.
	 */
	public EspressoFileModifier(){		
	}
	
	public void setupFileImports() throws IOException {
		EspressoTranslator.toWrite.add("import static android.support.test.espresso.Espresso.onView;\n");
		EspressoTranslator.toWrite.add("import static android.support.test.espresso.Espresso.onData;\n");
		EspressoTranslator.toWrite.add("import static android.support.test.espresso.matcher.ViewMatchers.withId;\n");
		EspressoTranslator.toWrite.add("import static android.support.test.espresso.matcher.ViewMatchers.withText;\n");
		EspressoTranslator.toWrite.add("import android.support.test.espresso.action.ViewActions;\n");
		EspressoTranslator.toWrite.add("import android.support.test.espresso.matcher.ViewMatchers;\n");
		
		EspressoTranslator.toWrite.add("import static org.hamcrest.Matchers.allOf;\n");
		EspressoTranslator.toWrite.add("import static org.hamcrest.Matchers.is;\n");
		
		EspressoTranslator.toWrite.add("import android.app.Activity;\n");
		EspressoTranslator.toWrite.add("import android.support.test.rule.ActivityTestRule;\n");
		EspressoTranslator.toWrite.add("import android.support.test.runner.AndroidJUnit4;\n");
		EspressoTranslator.toWrite.add("import android.test.ActivityInstrumentationTestCase2;\n");
		EspressoTranslator.toWrite.add("import android.test.suitebuilder.annotation.LargeTest;\n");
		EspressoTranslator.toWrite.add("import android.support.test.runner.AndroidJUnitRunner;\n");
		
		EspressoTranslator.toWrite.add("import org.junit.Rule;\n");
		EspressoTranslator.toWrite.add("import org.junit.Test;\n");
		EspressoTranslator.toWrite.add("import org.junit.runner.RunWith;\n");
		EspressoTranslator.toWrite.add("import junit.framework.TestSuite;\n");
		
	}
	/**
	 * Sets up the test method header and the imports for the main activity and R file.
	 */
	public void setupTestMethodHeader(String packageName, String mainActivity) {
		EspressoTranslator.toWrite.add("import " + packageName + "." + mainActivity + ";\n");
		EspressoTranslator.toWrite.add("import " + packageName + ".R;\n");
		EspressoTranslator.toWrite.add("\n\n\n@RunWith(AndroidJUnit4.class)\n");
		EspressoTranslator.toWrite.add("@LargeTest\n");
		EspressoTranslator.toWrite.add("public class TestFile extends TestSuite{\n");
		EspressoTranslator.toWrite.add("\t@Rule\n");
		EspressoTranslator.toWrite.add("\tpublic ActivityTestRule<" + mainActivity + "> "
				+ "mActivityRule = new ActivityTestRule<" + mainActivity +
				">(" + mainActivity + ".class);\n");
		EspressoTranslator.toWrite.add("\t@Test\n");
		EspressoTranslator.toWrite.add("\tpublic void test(){\n");
	}
	/**
	 * Closes the test method and adds the Test package name at the beginning of the file, 
	 * the actual package name must be added manually after since it is not provided in 
	 * the JSON file.
	 */
	public void closeTestMethod() {
		EspressoTranslator.toWrite.add("\t }\n }");
		EspressoTranslator.toWrite.add(0, "package [Test_package_name_here]\n");
	}
}
