package dto;

//@author SE173593
public class Account {

    private String accountId;
    private String accountName;
    private String password;
    private int money;

    public Account(String accountId, String accountName, String password) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
    }

    public Account(String accountId, String accountName, String password, int money) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
        this.money = money;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
