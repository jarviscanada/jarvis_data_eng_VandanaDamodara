package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.List;
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
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  private Quote quote;


  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("AAPL");
    assertEquals(iexQuote.getSymbol(), "AAPL");
    assertEquals(iexQuote.getCompanyName(), "Apple Inc");
  }



  @Test
  public void updateMarketData() {
    quote = new Quote();
    quote.setAsk_Price(1000.0);
    quote.setAsk_Size(10);
    quote.setBid_Price(10.0);
    quote.setBid_Size(11);
    quote.setId("AAPL");
    quote.setLast_Price(20.0);

    quoteService.saveQuote(quote);
    quoteService.updateMarketData();
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(quotes.get(0).getTicker(), "AAPL");
  }

  @Test
  public void buildQuotefromIexQuote() {

  }

  @Test
  public void saveQuotes() {
    List<String> tickers = Arrays.asList("AAPL", "MSFT", "AMZN", "GOOG");
    List<Quote> quotes = quoteService.saveQuotes(tickers);
    assertEquals(quotes.size(), 4);
  }

  @Test
  public void saveQuote() {
    Quote quote = quoteService.saveQuote("AAPL");
    assertEquals(quote.getTicker(), "AAPL");
  }



  @Test
  public void findAllQuotes() {
    quote = new Quote();
    quote.setAsk_Price(1000.0);
    quote.setAsk_Size(10);
    quote.setBid_Price(10.0);
    quote.setBid_Size(11);
    quote.setId("AAPL");
    quote.setLast_Price(20.0);

    quoteService.saveQuote(quote);
    quoteService.updateMarketData();
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(quotes.get(0).getTicker(), "AAPL");
  }
}
