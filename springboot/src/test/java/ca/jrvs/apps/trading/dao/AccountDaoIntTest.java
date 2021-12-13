package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.After;
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
public class AccountDaoIntTest {
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  private Trader trader1;
  private Trader trader2;
  private Account savedAccount1;
  private Account savedAccount2;
  @Before
  public void setUp() throws Exception {
    trader1 = new Trader();
    trader1.setId(5);
    trader1.setFirst_name("Varun");
    trader1.setLast_name("man");
    trader1.setEmail("vamgmail.com");
    trader1.setDob(new Date(2021, 6, 19));
    trader1.setCountry("Canada");
    traderDao.save(trader1);

    trader2 = new Trader();
    trader2.setId(4);
    trader2.setFirst_name("Vihan");
    trader2.setLast_name("ema");
    trader2.setEmail("lamgmail.com");
    trader2.setDob(new Date(2020, 8, 19));
    trader2.setCountry("Canada");
    traderDao.save(trader2);

    savedAccount1= new Account();
    savedAccount1.setTraderId(1);
    savedAccount1.setAmount(1000F);
    accountDao.save(savedAccount1);

    savedAccount2=new Account();
    savedAccount2.setTraderId(2);
    savedAccount2.setAmount(2000F);
    accountDao.save(savedAccount2);
  }

  @After
  public void clean() {
    accountDao.deleteAll();
    traderDao.deleteAll();

  }

  @Test
  public void findAllById()
  {
    List<Account> accounts= (List<Account>) accountDao.findAllById(Arrays.asList(1,2));
    assertEquals(2,accounts.size());
    assertEquals(1000F,accounts.get(0).getAmount(),0);
    assertEquals(2,accounts.get(1).getTraderId(),0);
  }

  @Test
  public void findAll()
  {
    List<Account> accounts= accountDao.findAll();
    assertEquals(2,accounts.size());
    assertEquals(1000F,accounts.get(0).getAmount(),0);
    assertEquals(2,accounts.get(1).getTraderId(),0);
  }

  @Test
public void findById() {
    Optional<Account> optional = accountDao.findById(2);
    if (optional.isPresent()) {
      Account account = optional.get();
      assertEquals(2000F, account.getAmount(),0);

    } else {
      fail();
    }
    optional = accountDao.findById(6);
    if (optional.isPresent()) {
      fail();
    } else {
      assertTrue(true);

    }
  }
  @Test
  public void existsById()
  {
    assertTrue(accountDao.existsById(1));
    assertTrue(accountDao.existsById(2));
    assertFalse(accountDao.existsById(6));
  }
  @Test
  public void deleteById()
  {
    accountDao.deleteById(2);
    List<Account> accounts=accountDao.findAll();
    assertEquals(1,accounts.size());
    assertFalse(accountDao.existsById(2));
    assertEquals(1,accounts.get(0).getTraderId(),0);
  }
  }