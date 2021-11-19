package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService{

  private TraderDao traderDao;
  private AccountDao accountDao;
  private PositionDao positionDao;
  private SecurityOrderDao securityOrderDao;

  @Autowired
  public TraderAccountService(TraderDao traderDao,AccountDao accountDao,PositionDao positionDao,
      SecurityOrderDao securityOrderdao)
  {
    this.traderDao=traderDao;
    this.accountDao=accountDao;
  this.positionDao=positionDao;
  this.securityOrderDao=securityOrderdao;
  }

  /**
   * Create a new trader and initialize a new account with amount 0
   * @param mytrader
   * @return
   */
  public TraderAccountView createTraderAndAccount(Trader mytrader){
    if(mytrader.getId()==null)
    {
      throw  new DataRetrievalFailureException("Invalid trader");
    }

    Trader trader=traderDao.save(mytrader);
    Account account = new Account();
    account.setId(trader.getId());
    account.setTraderId(trader.getId());
    //account.setId(trader.getId());
    account.setAmount((0F));
    accountDao.save(account);

    return new TraderAccountView(trader,account);
  }

public void deleteTraderbyId(Integer traderId){
    if(traderId==null)
    {
      throw new IllegalArgumentException("Null Trader Id");

    }
    Account account= accountDao.findById(traderId).get();
if(account.getAmount()!=0)
{
throw  new IllegalArgumentException("Non zero balance ,Cannot delete the account");

}
    if(positionDao.findById(account.getId()).isPresent())
    {
      throw  new IllegalArgumentException("Account has open position");
    }
    securityOrderDao.deletebyAccountId(account.getId());
    accountDao.deleteById(traderId);
    traderDao.deleteById(traderId);
}
public Account deposit(Integer traderId,Float fund)
{
  if(fund<0||traderId==null)
  {
    throw  new IllegalArgumentException("Invalid Input");
  }
  Account account= accountDao.findById(traderId).get();
  account.setAmount(account.getAmount()+fund);
  //accountDao.save(account);
 accountDao.updateOne(account);
  //accountDao.updateAmountById(account.getId(),(Float)account.getAmount()+fund);
  return account;
}
  public Account withdraw(Integer traderId,Float fund)
  {
    if(fund<0||traderId==null)
    {
      throw  new IllegalArgumentException("Invalid Input");
    }
    Account account= accountDao.findByTraderId(traderId).get();
    if(fund>account.getAmount())
    {
      throw new IllegalArgumentException("Not enough money in account");
    }
    account.setAmount(account.getAmount()-fund);
    accountDao.updateOne(account);
    return account;
  }
}
