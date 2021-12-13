package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.sql.Date;
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
public class TraderAccountServiceTest {

  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  private TraderAccountView traderAccountView;
  private Trader trader1;
  private Trader trader2;
  private Trader trader3;
  Trader trader = new Trader();

  @Before
  public void setUp() throws Exception {
    traderDao.deleteAll();
   accountDao.deleteAll();

    trader = new Trader();
    trader.setId(1);
    trader.setFirst_name("Dam");
    trader.setLast_name("van");
    trader.setEmail("vand@email.com");
    trader.setDob(new Date(2021, 5, 19));
    trader.setCountry("Canada");
    trader1 = new Trader();
    trader1.setId(2);
    trader1.setFirst_name("James");
    trader1.setLast_name("kim");
    trader1.setDob(Date.valueOf("1994-12-06"));
    trader1.setCountry("Canada");
    trader1.setEmail("kim.james@gmail.com");
    trader3 = new Trader();
    trader3.setId(4);
    trader3.setFirst_name("Norah");
    trader3.setLast_name("Jones");
    trader3.setDob(Date.valueOf("1979-03-30"));
    trader3.setCountry("United States");
    trader3.setEmail("norah_jones@gmail.com");
  }

  @Test
  public void createTraderAndAccount() {
    traderAccountService.createTraderAndAccount(trader);
    traderAccountService.createTraderAndAccount(trader1);
    assertTrue(traderDao.existsById(trader.getId()));
    assertEquals("James",traderDao.findById(2).get().getFirst_name());
  }
  @Test
  public void deposit() {
    traderAccountService.createTraderAndAccount(trader);
    traderAccountService.createTraderAndAccount(trader1);
    Account account = traderAccountService.deposit(trader.getId(),1500F);
    Account accounts = traderAccountService.deposit(trader1.getId(),3000F);

    traderAccountService.deposit(trader.getId(), 2000F);
    traderAccountService.deposit(trader1.getId(), 3000F);

    assertEquals(3500F, accountDao.findByTraderId(trader.getId()).get().getAmount(), 0);
    assertEquals(6000F, accountDao.findByTraderId(trader1.getId()).get().getAmount(), 0);

  }
  @Test
  public void withdraw() {

    traderAccountService.createTraderAndAccount(trader);
    traderAccountService.createTraderAndAccount(trader1);
    traderAccountService.createTraderAndAccount(trader3);

    traderAccountService.deposit(trader.getId(), 2000F);
    traderAccountService.deposit(trader1.getId(), 4000F);

    traderAccountService.withdraw(trader.getId(), 1000F);
    traderAccountService.withdraw(trader1.getId(), 3000F);


    assertEquals(1000d, accountDao.findByTraderId(trader1.getId()).get().getAmount(), 0);
  }
}
