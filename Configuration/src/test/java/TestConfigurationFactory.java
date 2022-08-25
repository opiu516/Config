import static org.junit.Assert.assertEquals;

import com.Configuration.Configuration;
import com.Configuration.ConfigurationFactory;

public class TestConfigurationFactory {

	public static void create_ReturnsValidConfiguration(ConfigurationFactory operator,Configuration wantedConfiguration) {
		assertEquals(operator.create().getLogFilePath(),wantedConfiguration.getLogFilePath());
		assertEquals(operator.create().getLogLevel(),wantedConfiguration.getLogLevel());
	}
	
	public static void create_ReturnsNullIfInvalidFile(ConfigurationFactory operator) {
		assertEquals(null,operator.create());
	}
	

}
