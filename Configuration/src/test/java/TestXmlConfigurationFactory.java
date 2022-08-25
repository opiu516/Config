import org.apache.commons.cli.ParseException;
import org.junit.Test;

import com.Configuration.Configuration;
import com.Configuration.ConfigurationFactoryFactory;

public class TestXmlConfigurationFactory {

	@Test
	public void create_ReturnsValidConfiguration() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		TestConfigurationFactory.create_ReturnsValidConfiguration(
				ConfigurationFactoryFactory.create(new String[] {"--configuration=" + System.getProperty("user.dir") + "/resources/test.xml"}),
				new Configuration("C:\\Temp","WARN"));
	}
	
	@Test
	public void create_ReturnsNullIfInvalidFile() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		TestConfigurationFactory.create_ReturnsNullIfInvalidFile(ConfigurationFactoryFactory.create(new String[] {"--configuration=neshtosi.xml"}));
	}

}
