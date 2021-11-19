package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
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
public class SecurityOrderIntTest {
  @Autowired
  private SecurityOrderDao securityOrderDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private QuoteDao quoteDao;

  private Trader trader1;
  private Trader trader2;
  private Account savedAccount1;
  private Account savedAccount2;

  private SecurityOrder so1;
  private SecurityOrder so2;
  @Before
  public void setUp() throws Exception {

    Quote q1= new Quote();
    q1.setAsk_Price(10d);
    q1.setAsk_Size(10);
    q1.setBid_Price(10.2d);
    q1.setBid_Size(10);
    q1.setTicker("AAPL");
    q1.setLast_Price(10.1d);
    quoteDao.save(q1);

    Quote q2= new Quote();
    q1.setAsk_Price(20d);
    q1.setAsk_Size(20);
    q1.setBid_Price(20.2d);
    q1.setBid_Size(20);
    q1.setTicker("AMZN");
    q1.setLast_Price(20.1d);
    quoteDao.save(q1);
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

    so1 = new SecurityOrder();
    so2 = new SecurityOrder();
    so1.setAccountId(1);
    so1.setPrice(13F);
    so1.setNotes("number");
    so1.setSize(7);
    so1.setTicker("AAPL");
    so1.setStatus("Unfilled");
    securityOrderDao.save(so1);

    so2.setAccountId(2);
    so2.setPrice(14F);
    so2.setNotes("number");
    so2.setSize(20);
    so2.setTicker("AMZN");
    so2.setStatus("Unfilled");
    securityOrderDao.save(so2);
  }

  @After
  public void clean() {
    //accountDao.deleteAll();
    //traderDao.deleteAll();
    securityOrderDao.deleteAll();

  }

  @Test
  public void findAllById()
  {
    List<SecurityOrder> accounts= (List<SecurityOrder>) securityOrderDao.findAllById(Arrays.asList(1,2));
    assertEquals(2,accounts.size());
    assertEquals(7,accounts.get(0).getSize(),0);
    assertEquals(2,accounts.get(1).getAccountId(),0);
  }

  @Test
  public void findAll()
  {
    List<SecurityOrder> accounts= securityOrderDao.findAll();
    assertEquals(2,accounts.size());
    assertEquals(20,accounts.get(1).getSize(),0);
    assertEquals("AMZN",accounts.get(1).getTicker());
  }

  @Test
  public void findById() {
    Optional<SecurityOrder> optional = securityOrderDao.findById(2);
    if (optional.isPresent()) {
      SecurityOrder security= optional.get();
      assertEquals(20, security.getSize(),0);

    } else {
      fail();
    }
    optional = securityOrderDao.findById(6);
    if (optional.isPresent()) {
      fail();
    } else {
      assertTrue(true);

    }
  }
  @Test
  public void existsById()
  {
    assertTrue(securityOrderDao.existsById(1));
    assertTrue(securityOrderDao.existsById(2));
    assertFalse(securityOrderDao.existsById(6));
  }
  @Test
  public void deleteById()
  {
    securityOrderDao.deletebyAccountId(2);
    List<SecurityOrder> accounts=securityOrderDao.findAll();
    assertEquals(1,accounts.size());
    assertFalse(securityOrderDao.existsById(2));
    assertEquals(1,accounts.get(0).getAccountId(),0);
  }
}
