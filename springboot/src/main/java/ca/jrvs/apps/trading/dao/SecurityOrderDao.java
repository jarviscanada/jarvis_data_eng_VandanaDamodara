package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder>{
  public static final String TABLE_NAME = "security_order";
  public static final String ID_COL_Name="id";
  private final String ACCOUNT_ID_COLUMN = "account_id";

  private final Logger logger = LoggerFactory.getLogger(SecurityOrder.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource)
  {
    this.jdbcTemplate= new JdbcTemplate(dataSource);
    this.simpleJdbcInsert=new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COL_Name);

  }

  @Override
  public Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
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
  public String getAccIdColumnName() {
    return ACCOUNT_ID_COLUMN;
  }
  @Override
  public int updateOne(SecurityOrder entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public void deletebyAccountId(Integer accountId)
  {
    String delete_by_id = "DELETE FROM " + getTableName() + " WHERE " + getAccIdColumnName() + "=?";
    getJdbcTemplate().update(delete_by_id, accountId);

  }
}
