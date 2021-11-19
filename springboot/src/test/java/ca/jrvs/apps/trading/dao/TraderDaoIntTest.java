package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.catalina.LifecycleState;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

  private Trader trader;
  private Trader trader2;

  @Before
  public void setUp() throws Exception {
    //traderDao.deleteAll();
    trader = new Trader();
    trader.setId(2);
    trader.setFirst_name("Vandana");
    trader.setLast_name("dam");
    trader.setEmail("damgmail.com");
    trader.setDob(new Date(2021, 5, 19));
    trader.setCountry("Canada");
    traderDao.save(trader);
     trader2= new Trader();
     trader2.setId(2);
   trader2.setFirst_name("Bob");
    trader2.setLast_name("Ross");
    trader2.setDob(Date.valueOf("1942-10-29"));
    trader2.setCountry("United States");
    trader2.setEmail("bob_ross@gmail.com");
    traderDao.save(trader2);

  }

  @Test
  public void findAllById(){
    List<Trader> traders = Lists.newArrayList(traderDao.findAllById(Arrays.asList(trader.getId())));
    assertEquals(traders.size(), 1);
    assertEquals(trader.getCountry(), traders.get(0).getCountry());
    assertEquals(1, traders.get(0).getId().intValue());
    assertEquals("Vandana", traders.get(0).getFirst_name());
    assertEquals("dam", traders.get(0).getLast_name());
  }
  @Test
  public void findAll() {
    List<Trader> traders = traderDao.findAll();
    assertEquals(2, traders.size());
    assertEquals(2, traders.get(1).getId().intValue());
    assertEquals("Bob", traders.get(1).getFirst_name());
    assertEquals(Date.valueOf("1942-10-29"), traders.get(1).getDob());
  }
  @Test
  public void existsById() {
    assertTrue(traderDao.existsById(1));
    assertTrue(traderDao.existsById(2));
    assertFalse(traderDao.existsById(4));
  }

  @Test
  public void deleteById() {
    traderDao.deleteById(1);
    List<Trader> traders = traderDao.findAll();
    assertEquals(1, traders.size());
    assertFalse(traderDao.existsById(1));
  }

}