import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import com.Configuration.ConfigurationFactoryFactoryRefractored;

public class TestConfigurationFactoryRefractored {

	@Test
	public void create_CreatesFactoryForReadingFromFile() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] args = new String[] {"--configuration=config.ini"};
		assertEquals(
				ConfigurationFactoryFactoryRefractored.create(args).getClass().getName(),
				"com.Configuration.IniConfigurationFactory");
		args = new String[] {"--configuration=my_config.xml"};
		assertEquals(
				ConfigurationFactoryFactoryRefractored.create(args).getClass().getName(),
				"com.Configuration.XmlConfigurationFactory");
	}
	
	@Test
	public void create_CreatesFactoryForReadingCommandLineArgs() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] args = new String[] {"--log-path=C:\\Temp","--log-level=warn"};
		assertEquals(
				ConfigurationFactoryFactoryRefractored.create(args).getClass().getName(),
				"com.Configuration.CLIConfigurationFactory");
	}
	
	@Test
	public void create_PreffersFileToParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] args = new String[] {"--log-path=C:\\Temp","--log-level=warn","--configuration=my_config.xml"};
		assertEquals(
				ConfigurationFactoryFactoryRefractored.create(args).getClass().getName(),
				"com.Configuration.XmlConfigurationFactory");
	}
	
	@Test
	public void create_ReturnsNullForNoParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] args = new String[] {};
		try {
			ConfigurationFactoryFactoryRefractored.create(args);
			Assert.fail();
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void create_ReturnsNullForNotEnoughParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String[] args = new String[] {"--log-level=warn"};
		try {
			assertEquals(ConfigurationFactoryFactoryRefractored.create(args),null);
			Assert.fail();
		}
		catch(Exception e) {
			
		}
		args = new String[] {"--log-path=C:\\Temp"};
		try {
			assertEquals(ConfigurationFactoryFactoryRefractored.create(args),null);
			Assert.fail();
		}
		catch(Exception e) {
			
		}
	}

}
