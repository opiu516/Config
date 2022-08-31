import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import com.Configuration.Configuration;
import com.Configuration.ConfigurationFactoryFactory;

public class TestCLIconfigurationFactory {

	@Test
	public void create_ReturnsValidConfiguration() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		TestConfigurationFactory.create_ReturnsValidConfiguration(
				ConfigurationFactoryFactory.create(new String[] {"--log-path=C:\\Temp","--log-level=warn"}),
				new Configuration("C:\\Temp","warn"));
	}
	

}
