import static org.junit.Assert.assertEquals;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import com.Configuration.ConfigurationFactoryFactory;

public class TestConfigurationFactoryFactory {

	@Test
	public void getFileFormat_returnsCorrectFileFormats() {
		assertEquals(ConfigurationFactoryFactory.getFileFormat("test.xml"),"Xml");
	}
	
	@Test
	public void createConfigurationFactoryFromString_returnsNeedeFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		assertEquals(
				ConfigurationFactoryFactory.createConfigurationFactoryFromString("test.xml").getClass().getName(),
				"com.Configuration.XmlConfigurationFactory");
	}
	
	@Test
	public void createConfigurationFactoryFromString_throwsExceptionForIncorrectFileFormat() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
				try {
					ConfigurationFactoryFactory.createConfigurationFactoryFromString("test.txt");
					Assert.fail("No exception was thrown");
				}catch(Exception e) {
					
				}
	}
	
	@Test
	public void createConfigurationFactoryFromString_DoesNotAllowCreationOfCLIConfigurationFacotry() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			ConfigurationFactoryFactory.createConfigurationFactoryFromString("test.cli");
			Assert.fail("No exception was thrown");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void create_CreatesFactoryForReadingFromFile() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String[] args = new String[] {"--configuration=config.ini"};
		assertEquals(
				ConfigurationFactoryFactory.create(args).getClass().getName(),
				"com.Configuration.IniConfigurationFactory");
		args = new String[] {"--configuration=my_config.xml"};
		assertEquals(
				ConfigurationFactoryFactory.create(args).getClass().getName(),
				"com.Configuration.XmlConfigurationFactory");
	}
	
	@Test
	public void create_CreatesFactoryForReadingCommandLineArgs() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String[] args = new String[] {"--log-path=C:\\Temp","--log-level=warn"};
		assertEquals(
				ConfigurationFactoryFactory.create(args).getClass().getName(),
				"com.Configuration.CLIConfigurationFactory");
	}
	
	@Test
	public void create_PreffersFileToParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String[] args = new String[] {"--log-path=C:\\Temp","--log-level=warn","--configuration=my_config.xml"};
		assertEquals(
				ConfigurationFactoryFactory.create(args).getClass().getName(),
				"com.Configuration.XmlConfigurationFactory");
	}
	
	@Test
	public void create_ReturnsNullForNoParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String[] args = new String[] {};
		assertEquals(ConfigurationFactoryFactory.create(args),null);
	}
	
	@Test
	public void create_ReturnsNullForNotEnoughParameters() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
		String[] args = new String[] {"--log-level=warn"};
		assertEquals(ConfigurationFactoryFactory.create(args),null);
		args = new String[] {"--log-path=C:\\Temp"};
		assertEquals(ConfigurationFactoryFactory.create(args),null);
	}
	

}
