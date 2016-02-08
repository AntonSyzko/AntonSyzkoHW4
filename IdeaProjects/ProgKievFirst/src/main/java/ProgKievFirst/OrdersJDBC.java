package main.java.ProgKievFirst;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Ыг on 05.02.2016.
 * Создать проект «База данных заказов». Создать
 таблицы «Товары» , «Клиенты» и «Заказы».
 Написать код для добавления новых клиентов,
 товаров и оформления заказов.
 */
public class OrdersJDBC {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/sakila";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "olgagrunina60660";

    static Connection conn;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initClientsDB();
                initProductsDB();
                initOrdersDB();


                while (true) {
                    System.out.println("1:addClient");
                    System.out.println("2: addProduct");
                    System.out.println("3: addOrder");
                    System.out.println("4: display Order");


                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addClient(sc);
                            break;
                        case "2":
                           addProduct(sc);
                            break;
                        case "3":
                           addOrder(sc);
                            break;
                        case "4":
                            displayOrders();
                            break;

                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }


    private static void initProductsDB() throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS Products");
            st.execute("CREATE TABLE Products (product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " product_name VARCHAR(20) NOT NULL,  price INT)");
        } finally {
            st.close();
        }
    }//




    private static void initClientsDB() throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("CREATE TABLE Clients (client_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " FirstName VARCHAR(20) NOT NULL, LastName VARCHAR(20) NOT NULL, age INT)");
        } finally {
            st.close();
        }
    }



    private static void initOrdersDB() throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS Orders");
            st.execute("CREATE TABLE Orders (order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " product_id INT , product_name VARCHAR (50),client_id INT, client_name VARCHAR (50))");
        } finally {
            st.close();
        }
    }


    private static void addClient(Scanner sc) throws SQLException {
        System.out.print("Enter client  first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter client  last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter client age: ");
        String sAge = sc.nextLine();
        int age = Integer.parseInt(sAge);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Clients (FirstName,LastName, age) VALUES(?, ?, ?)");
        try {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }



    private static void addProduct(Scanner sc) throws SQLException {
        System.out.print("Enter product's name: ");
        String prodName = sc.nextLine();

        System.out.print("Enter product's price: ");
        String sprice = sc.nextLine();
        int price = Integer.parseInt(sprice);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Products (product_name, price) VALUES(?, ?)");
        try {
            ps.setString(1, prodName);
            ps.setInt(2, price);

            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    /*
    INSERT INTO Customers (CustomerName, Country)
SELECT SupplierName, Country FROM Suppliers
WHERE Country='Germany';
     */

    private static void addOrder(Scanner sc) throws SQLException {

        System.out.print("Enter product's id : ");
        String sprodId = sc.nextLine();
        int prod_id = Integer.parseInt(sprodId);

        System.out.println(" Enter product name :");
        String prod_name = sc.nextLine();


        System.out.print("Enter client's id : ");
        String sclient_id = sc.nextLine();
        int clients_d = Integer.parseInt(sclient_id);

        System.out.println(" entere clients name ");
        String clName = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders (product_id, product_name, client_id, client_name)" +
                " VALUES ((SELECT product_id FROM Products WHERE product_id = ?)," +
                "(SELECT product_name FROM Products WHERE product_name = ?)," +
                "(SELECT client_id FROM Clients WHERE client_id = ?)," +
                "(SELECT LastName FROM Clients WHERE LastName = ? ) )");
        try {
            ps.setInt(1,prod_id);
            ps.setString(2,prod_name);
            ps.setInt(3,clients_d);
            ps.setString(4,clName);

            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

     private static void displayOrders() throws SQLException{

         PreparedStatement ps = conn.prepareStatement("SELECT * FROM Orders");
         try {
             // table of data representing a database result set,
             ResultSet rs = ps.executeQuery();
             try {
                 // can be used to get information about the types and properties of the columns in a ResultSet object
                 ResultSetMetaData md = rs.getMetaData();

                 for (int i = 1; i <= md.getColumnCount(); i++)
                     System.out.print(md.getColumnName(i) + "\t\t");
                 System.out.println();

                 while (rs.next()) {
                     for (int i = 1; i <= md.getColumnCount(); i++) {
                         System.out.print(rs.getString(i) + "\t\t");
                     }
                     System.out.println();
                 }
             } finally {
                 rs.close(); // rs can't be null according to the docs
             }
         } finally {
             ps.close();
         }
     }


}
