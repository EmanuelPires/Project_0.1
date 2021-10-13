import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class CustomerServiceTest {
	
	@InjectMocks
	CustomerService cusServ;

	@Mock
	CustomerDao cusDao = new CustomerDao();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	};
	
	@Test
	public void testValidLogin() {
		Customer cus = new Customer();
	}
}
