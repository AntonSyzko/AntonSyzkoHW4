package main.java.ProgKievFirst;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by лу on 05.02.2016.
 */
public class ApartmentsJDBC {

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
                initDB();

                while (true) {
                    System.out.println("1:addApartment");
                    System.out.println("2: changeApartmentPrice");
                    System.out.println("3: deleteAprtment");
                    System.out.println("4: changeApartmentAddress");
                    System.out.println("5: changeApartmentsArea");
                    System.out.println("6: viewApartments");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addApartment(sc);
                            break;
                        case "2":
                            changeApartmentPrice(sc);
                            break;
                        case "3":
                            deleteAprtment(sc);
                            break;
                        case "4":
                            changeApartmentAddress(sc);
                            break;
                        case "5":
                            changeApartmentsArea(sc);
                            break;
                        case "6":
                            viewApartments();
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



    private static void initDB() throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS Apartments");
            st.execute("CREATE TABLE Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " district VARCHAR(20) NOT NULL, address VARCHAR(100),area  INT, rooms INT, price INT)");
        } finally {
            st.close();
        }
    }

    private static void addApartment(Scanner sc) throws SQLException {
        System.out.print("Enter district : ");
        String distr = sc.nextLine();

        System.out.print("Enter address : ");
        String addr = sc.nextLine();

        System.out.print(" Enter area: ");
        String areaStr = sc.nextLine();
        int area = Integer.parseInt(areaStr);

        System.out.print(" Enter rooms: ");
        String roomsStr= sc.nextLine();
        int rooms = Integer.parseInt(roomsStr);

        System.out.print(" Enter price: ");
        String priceStr = sc.nextLine();
        int price = Integer.parseInt(priceStr);



        PreparedStatement ps = conn.prepareStatement("INSERT INTO Apartments (district,address,area, rooms,price) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, distr);
            ps.setString(2, addr);
            ps.setInt(3,area);
            ps.setInt(4,rooms);
            ps.setInt(5,price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }//add



    private static void deleteAprtment(Scanner sc) throws SQLException {
        System.out.print("Enter apartment address: ");
        String address = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Apartments WHERE address = ?");
        try {
            ps.setString(1, address);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }//del



    private static void changeApartmentPrice(Scanner sc) throws SQLException {


        System.out.print("Enter  apps address: ");
        String addr = sc.nextLine();

        System.out.print("Enter apps district : ");
        String distr = sc.nextLine();

        System.out.println("Enter new  price ");
        String priceStr = sc.nextLine();
        int price = Integer.parseInt(priceStr);



        PreparedStatement ps = conn.prepareStatement("UPDATE Apartments SET price = ? WHERE address = ? AND district = ?");
        try {
            ps.setInt(1, price);
            ps.setString(2, addr);
            ps.setString(3,distr);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }//change


    private static void changeApartmentAddress(Scanner sc) throws SQLException {
        System.out.print("Enter new  apartments district: ");
        String distr = sc.nextLine();

        System.out.print("Enter new apps address: ");
        String addr = sc.nextLine();


        PreparedStatement ps = conn.prepareStatement("UPDATE Apartments SET address = ? WHERE district = ?");
        try {
            ps.setString(1, addr);
            ps.setString(2, distr);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }//change



    private static void changeApartmentsArea(Scanner sc) throws SQLException {

        System.out.println(" Enter the  address of the apartment ");
        String addr = sc.nextLine();




        System.out.print("Enter new apps area: ");
        String areaStr = sc.nextLine();
        int area = Integer.parseInt(areaStr);

        PreparedStatement ps = conn.prepareStatement("UPDATE Apartments SET area = ? WHERE address = ?");
        try {
            ps.setInt(1, area);
            ps.setString(2, addr);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }//change


    private static void viewApartments() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments");
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
