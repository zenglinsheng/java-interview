package coding.test0602;

import java.util.concurrent.atomic.AtomicReference;

public class DBConnection {

    private DBConnection(){}

    private static volatile DBConnection dbConnection = null;

    private static AtomicReference<DBConnection> reference = new AtomicReference<>();

    public static DBConnection getDBConnection() {
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                if (dbConnection == null) {
                    System.out.println("init DBConnection...");
                    dbConnection = new DBConnection();
                }
            }
        }
        return dbConnection;
    }

    public static DBConnection getDBConnection2() {
        DBConnection dbConnection = reference.getAcquire();
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                dbConnection = reference.getAcquire();
                if (dbConnection == null) {
                    dbConnection = new DBConnection();
                    reference.setRelease(dbConnection);
                }
            }
        }
        return dbConnection;
    }

    public static void main(String[] args) {
        Runnable runnable = () -> DBConnection.getDBConnection2();

        for (int i = 0;i < 1000;i ++) {
            new Thread(runnable).start();
        }
    }

}
