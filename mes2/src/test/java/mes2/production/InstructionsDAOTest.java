package mes2.production;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.persistence.InstructionsDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class InstructionsDAOTest {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(InstructionsDAOTest.class);
	
	@Inject
	private InstructionsDAO instructionDAO;
	@Test
	public void insertTest() {
		InstructionsDTO instructsDto = new InstructionsDTO();
		instructsDto.setCode("20231219A0001");
		instructsDto.setLine(1);
		instructsDto.setMdpCode("A1111");
		instructsDto.setType("F");
		instructsDto.setSoiCode("S1111");
		instructsDto.setState("S");
		
		instructionDAO.insert(instructsDto);
		
	}
}
