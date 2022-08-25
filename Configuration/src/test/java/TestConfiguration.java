import static org.junit.Assert.*;

import org.junit.Test;

import com.Configuration.Configuration;

public class TestConfiguration {

	@Test
	public void getLogFilePath_ReturnsCorrectValue() {
		assertEquals(new Configuration("albala","kap").getLogFilePath(),"albala");
	}
	
	@Test
	public void getLogLevel_ReturnsCorrectValue() {
		assertEquals(new Configuration("albala","kap").getLogLevel(),"kap");
	}

}
