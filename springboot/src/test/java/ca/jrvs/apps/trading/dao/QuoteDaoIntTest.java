package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;
  private Quote savedQuote;

  @Before
  public void setUp() throws Exception {
    quoteDao.deleteAll();
    savedQuote = new Quote();
    savedQuote.setAsk_Price(1000.0);
    savedQuote.setAsk_Size(10);
    savedQuote.setBid_Price(10.0);
    savedQuote.setBid_Size(11);
    savedQuote.setId("aapl");
    savedQuote.setLast_Price(20.0);
    quoteDao.save(savedQuote);
  }

  @Test
  public void save() {
    assertEquals(savedQuote.getId(), "aapl");
    assertEquals((int) savedQuote.getAsk_Size(), 10);
    assertEquals((int) savedQuote.getBid_Size(), 11);
  }


  @Test
  public void addOne() {
    Quote newQuote = new Quote();
    newQuote.setId("aapl2");
    newQuote.setAsk_Size(1000);
    newQuote.setAsk_Price(50.5);
    newQuote.setBid_Price(50.6);
    newQuote.setBid_Size(12);
    newQuote.setLast_Price(20.2);
    quoteDao.addOne(newQuote);

    assertEquals(newQuote.getId(), "aapl2");
    assertEquals((int) newQuote.getAsk_Size(), 1000);
  }

  @Test
  public void updateOne() {
    savedQuote.setAsk_Size(1500);
    quoteDao.updateOne(savedQuote);
    assertEquals((int) savedQuote.getAsk_Size(), 1500);
  }

  @Test
  public void saveAll() {
    List<Quote> quotes = new ArrayList<>();

    Quote newQuote = new Quote();
    newQuote.setId("aapl2");
    newQuote.setAsk_Size(1000);
    newQuote.setAsk_Price(50.5);
    newQuote.setBid_Price(50.6);
    newQuote.setBid_Size(12);
    newQuote.setLast_Price(20.2);

    Quote newQuote2 = new Quote();
    newQuote2.setId("aapl3");
    newQuote2.setAsk_Size(1000);
    newQuote2.setAsk_Price(50.5);
    newQuote2.setBid_Price(50.6);
    newQuote2.setBid_Size(12);
    newQuote2.setLast_Price(20.2);

    quotes.add(newQuote);
    quotes.add(newQuote2);

    quoteDao.saveAll(quotes);
    assertNotNull(newQuote2);
    assertNotNull(newQuote);
  }

  @Test
  public void findById() {
    Optional<Quote> findQuote = quoteDao.findById("aapl");
    assertEquals(findQuote.get().getId(), "aapl");
    assertEquals((int) findQuote.get().getAsk_Size(), 10);
  }

  @Test
  public void existsById() {
    assertFalse(quoteDao.existsById("aaaapl"));
    assertTrue(quoteDao.existsById("aapl"));
  }

  @Test
  public void findAll() {
    List<Quote> quotes = (List<Quote>) quoteDao.findAll();
    assertEquals(quotes.size(), 1);
    assertEquals(quotes.get(0).getId(), "aapl");
  }

  @Test
  public void count() {
    assertEquals(quoteDao.count(),  1);
  }

  @Test
  public void deleteById() {
    assertTrue(quoteDao.existsById("aapl"));
    quoteDao.deleteById("aapl");
    assertFalse(quoteDao.existsById("aapl"));
  }
}

