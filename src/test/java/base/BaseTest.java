package base;

import helpers.AccountHelper;
import helpers.BookStoreHelper;
import org.testng.annotations.BeforeTest;

import static assertions.Matcher.assertEquals;
import static helpers.AccountHelper.Status.SUCCESS;

public class BaseTest {

    protected AccountHelper accountHelper;
    protected BookStoreHelper bookStoreHelper;
    protected String userId;

    @BeforeTest(alwaysRun = true)
    public void baseBeforeTest() {
        accountHelper = new AccountHelper();
        bookStoreHelper = new BookStoreHelper();
    }

    protected void login() {
        assertEquals(() -> accountHelper.generateToken().getStatus(), SUCCESS.toString(),
                "User authorization succeeded");
        userId = accountHelper.login().getUserId();
    }
}
