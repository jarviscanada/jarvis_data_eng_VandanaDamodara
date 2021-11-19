package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote,String> {
  public static final java.lang.String TABLE_NAME = "Quote";
  public static final java.lang.String ID_COL_Name="ticker";

  private final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource)
  {
    jdbcTemplate=new JdbcTemplate(dataSource);
    simpleJdbcInsert =  new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);

  }
  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
    for (Quote quote : iterable){
      save(quote);
    }
    return iterable;
  }



  @Override
  public <S extends Quote> S save (S quote) {
   if ((existsById(quote.getTicker())))
    {
      int updateRowNo = updateOne(quote);
      if (updateRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to Retrieve quote");

      }
    }
      else
      {
        addOne(quote);
      }
    return quote;

  }
  /**
   * helper method that saves one quote
   */
  public void addOne(Quote quote)
  {
    SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if(row != 1)
    {
      throw new IncorrectResultSizeDataAccessException("Failed to Insert",1,row);
    }
  }
  /**
   * helper method that updates one quote
   */
  public int updateOne(Quote quote)
  {
    String update_sql = "UPDATE quote SET last_price=?,bid_price=?,"
        +"bid_size=?,ask_price=?,ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql,makeUpdateValues(quote));
  }

  @Override
  public boolean existsById(String string) {
    return findById(string).isPresent();
  }

  @Override
  public void deleteAll() {
    String delete_sql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(delete_sql);
  }

  /**
   * helper method that makes SQL updates value objects
   * * @param  quote to be added
   * @return UPDATE_SQL values
   *
   * */
  public Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLast_Price(), quote.getBid_Price(),
        quote.getBid_Size(), quote.getAsk_Price(), quote.getAsk_Size(), quote.getId()};

  }

  @Override
  public Iterable findAll() {
    List<Quote> quotes;
    java.lang.String selectSql = "SELECT * FROM " + TABLE_NAME;
    try {
      quotes = jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));

    } catch (EmptyResultDataAccessException e) {
      return null;
    }
    return quotes;
  }

  @Override
  public Iterable findAllById (Iterable iterable){
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count () {
    String sql = "SELECT COUNT(*) FROM " + TABLE_NAME;
    Long count = jdbcTemplate.queryForObject(sql, Long.class);
    if (count == null) {
      throw new IllegalStateException("Encountered an issue where fetching the count");
          }
    return count;
  }

  @Override
  public void delete (Quote quote){
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll (Iterable < ? extends Quote > iterable){
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById (String string){
    String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COL_Name + " IN (SELECT " + ID_COL_Name
                  + " FROM " + TABLE_NAME + ")";
    jdbcTemplate.execute(sql);
  }



  @Override
  public Optional<Quote> findById (String string){
    String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL_Name + " =?";
    try {
      Quote quote = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Quote.class),string);
      if (quote != null){
        return Optional.of(quote);
      }
    } catch(Exception e) {
      return Optional.empty();
    }
    return Optional.empty();

     }
  }

