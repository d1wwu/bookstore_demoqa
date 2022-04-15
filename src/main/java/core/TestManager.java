package core;

public class TestManager {

    private static TestManager inst = null;

    private String username;
    private String password;

    private TestManager() {
        fetchTestParams();
    }

    private void fetchTestParams() {
        username = System.getProperty("username");
        password = System.getProperty("password");
    }

    public static TestManager getInstance() {
        if (inst == null) {
            inst = new TestManager();
        }
        return inst;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
