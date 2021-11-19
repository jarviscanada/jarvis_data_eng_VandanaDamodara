package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account>{
  public static final String TABLE_NAME = "account";
  public static final String ID_COL_Name="id";

  private final Logger logger = LoggerFactory.getLogger(AccountDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public AccountDao(DataSource dataSource)
  {
    this.jdbcTemplate= new JdbcTemplate(dataSource);
    this.simpleJdbcInsert=new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COL_Name);

  }

  @Override
  public Class<Account> getEntityClass() {
    return Account.class;
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COL_Name;
  }

  @Override
  public int updateOne(Account account) {
    String updateSql = "UPDATE account SET amount=? WHERE " + ID_COL_Name + "=?";
    //String updateSql = "UPDATE " + getTableName() + " SET amount=? WHERE " + getIdColumnName() + "=?";
    return jdbcTemplate.update(updateSql, account.getAmount(), account.getId());
  }
  public Optional<Account> findByTraderId(Integer traderId) {
    Optional<Account> account = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE trader_id = ?";

    try {
      account = Optional.ofNullable(getJdbcTemplate()
          .queryForObject(selectSql,
              BeanPropertyRowMapper.newInstance(getEntityClass()), traderId));
    } catch(IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find trader id: " + traderId, e);
    }
    return account;
  }
  public void updateAmountById(Integer accountId, Float amount) {
    String sql = "UPDATE " + getTableName() + " SET amount = ? WHERE id = ?";
    getJdbcTemplate().update(sql, amount, accountId);
  }
}
