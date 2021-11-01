import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/JDBC";
            final String USER = "root";
            final String PASS = "Admin@2012";

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("1.Find the total amount to be paid at the time of checkout for a particular CART. As shown in above table. e.g. Query should return a single integer as total amount.");
            Statement stmt1 = con.createStatement();
            ResultSet r1 = stmt1.executeQuery("select sum(amount) as total from CART");
            while (r1.next())
                System.out.println("Total = " + r1.getString(1));

            System.out.println("\n\n");
            System.out.println("2. Find the PRODUCT name which as sold the most.");
            Statement stmt2 = con.createStatement();
            ResultSet r2 = stmt2.executeQuery("select PNAME from PRODUCT where PID=(select PID from CART where QUANTITY=(select Max(QUANTITY) from CART));");
            while (r2.next())
                System.out.println("Name with max QUANTITY = " + r2.getString(1));

            System.out.println("\n\n");
            System.out.println("3.Find all the PRODUCTs which are not yet sold.");
            Statement stmt3 = con.createStatement();
            ResultSet r3 = stmt3.executeQuery("select PNAME from PRODUCT where PID NOT IN(Select PID from CART)");
            while (r3.next())
                System.out.println(r3.getString(1));

            System.out.println("\n\n");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

