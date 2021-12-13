package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(MarketDataDao marketDataDao,QuoteDao quoteDao)
  {
    this.quoteDao=quoteDao;
    this.marketDataDao=marketDataDao;

  }

  public IexQuote findIexQuoteByTicker(String ticker)
  {
    return marketDataDao.findById(ticker).orElseThrow(()->new IllegalArgumentException(ticker + "is invalid"));
  }
  public void updateMarketData()
  {
    List<Quote> quotes = findAllQuotes();
    for (int i = 0; i < quotes.size() - 1; i++) {
      if (marketDataDao.findById(quotes.get(i).getTicker()).isPresent()) {
        IexQuote iexQuote = marketDataDao.findById(quotes.get(i).getTicker()).get();
        quotes.set(i, buildQuotefromIexQuote(iexQuote));
      } else {
        throw new IllegalArgumentException("Ticker is not found in IEX");
      }
    }
    quoteDao.saveAll(quotes);

  }
  public static Quote buildQuotefromIexQuote(IexQuote iexQuote)
  {
    Quote quote = new Quote();
    quote.setId(iexQuote.getSymbol());
    quote.setAsk_Price((double)iexQuote.getIexAskPrice());
    quote.setAsk_Size((int)iexQuote.getIexAskSize());
    quote.setBid_Price((double)iexQuote.getIexBidPrice());
    quote.setBid_Size((int)iexQuote.getIexBidPrice());
    quote.setLast_Price((double)iexQuote.getIexLastUpdated());
    return quote;
  }
  public List<Quote> saveQuotes(List<String> tickers){
    List<Quote> quotes = new ArrayList<>();
    for (String ticker : tickers){
      quotes.add(saveQuote(ticker));
    }
    return quotes;
  }
  public Quote saveQuote(String ticker){
    Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
    Quote quote = buildQuotefromIexQuote(iexQuote.orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid")));
    return quote;
  }
  public Quote saveQuote(Quote quote){
    return quoteDao.save(quote);
  }
  public List<Quote> findAllQuotes(){
    return (List<Quote>) quoteDao.findAll();
  }

}
