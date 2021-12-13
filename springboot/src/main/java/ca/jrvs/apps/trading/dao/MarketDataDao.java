package ca.jrvs.apps.trading.dao;

import static java.util.Collections.copy;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote,String> {
  public static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  public final String IEX_BATCH_URL;

  private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  //private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao (HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig)
  {
    this.httpClientConnectionManager=httpClientConnectionManager;
    IEX_BATCH_URL=marketDataConfig.getHost()+IEX_BATCH_PATH+marketDataConfig.getToken();

  }

  @Override
  public <S extends IexQuote> S save(S s) {
    return null;
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));
     if(quotes.size()==0) {
       return Optional.empty();
     }
    else if(quotes.size()==1)
    {
      iexQuote=Optional.of(quotes.get(0));

    }
    else
     {
       throw new DataRetrievalFailureException("Unexpected number of Quotes");
     }
    return iexQuote;
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public Iterable<IexQuote> findAll() {
    return null;
  }

  @Override



  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> quotes = new ArrayList<>();

    for (String ticker : tickers){
      String url = String.format(IEX_BATCH_URL, ticker);
      String response = null;
      response = executeHttpGet(url).orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
      //copy(null,null);
      JSONObject IexQuotesJson = new JSONObject(response);
      System.out.print(IexQuotesJson);

      if (IexQuotesJson.length() == 0) {
        throw new IllegalArgumentException("Invalid ticker");
      }

      JSONObject json = IexQuotesJson.getJSONObject(ticker);
      System.out.print("output" + IexQuotesJson);
      ObjectMapper mapper = new ObjectMapper();
      try {
        IexQuote quote = mapper.readValue(json.get("quote").toString(), IexQuote.class);
        quotes.add(quote);
      } catch (IOException e) {
        throw new IllegalArgumentException("Invalid ticker");
      }
    }

    return quotes;
  }

  private Optional<String> executeHttpGet(String url) {
    System.out.println("URL IS "+url);
    CloseableHttpClient httpClient = getHttpClient();
    HttpUriRequest request = new HttpGet(url);
    try {
      HttpResponse response = httpClient.execute(request);
      String entity = EntityUtils.toString(response.getEntity());
      System.out.println("gethttp"+entity);
      return Optional.of(entity);
    } catch (IOException e) {
      throw new DataRetrievalFailureException("HTTP request failed/encounted bad status code", e);
    }
  }
  /**
   * Borrow an HTTP client from the httpClientConnectionManager
   * @return an httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
        .setConnectionManagerShared(true).build();
  }
  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public void delete(IexQuote iexQuote) {

  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {

  }

  @Override
  public void deleteAll() {

  }
}



