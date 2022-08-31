import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import com.Configuration.Configuration;
import com.Configuration.ConfigurationFactoryFactory;

public class TestIniConfigurationFactory {

	@Test
	public void create_ReturnsValidConfiguration() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		TestConfigurationFactory.create_ReturnsValidConfiguration(
				ConfigurationFactoryFactory.create(new String[] {"--configuration=" + System.getProperty("user.dir") + "/resources/testing.ini"}),
				new Configuration("C:\\Temp","WARN"));
	}
	
	@Test
	public void create_ReturnsNullIfInvalidFile() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		TestConfigurationFactory.create_ReturnsNullIfInvalidFile(ConfigurationFactoryFactory.create(new String[] {"--configuration=neshtosi.ini"}));
	}


}
