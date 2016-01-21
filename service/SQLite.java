/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package foggitops.service;

import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SQLite
{
   private static String mDBName = "database.db";

   public static Boolean createTable()
   {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS Receipts " +
               " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
               ", Vendor         TEXT " + 
               ", Payment_Method   TEXT" + 
               ", Date    TEXT" + 
               ", Total          TEXT)";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
      return true;
   }

   public static int numRows()
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      int id = 0;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "SELECT seq from sqlite_sequence where name='Receipts';";
         ResultSet rs = stmt.executeQuery(sql);
         while ( rs.next() ) {
            id = rs.getInt("seq");
         }
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return -1;
      }
      return id;
   }

   public static Boolean insertRow(Receipt input)
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      int id = 0;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         PreparedStatement sql = c.prepareStatement("INSERT INTO RECEIPTS (Vendor, Payment_Method, Total, Date) " +
               "VALUES (?, ?, ?, ?);");
         sql.setString(1, input.getVendor()); 
         sql.setString(2, input.getPaymentMethod());
         sql.setString(3, input.getAmount());
         sql.setString(4, input.getDate());
         sql.execute();

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid();");
         while ( rs.next() ) {
            id = rs.getInt(1);
         }
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
      input.setID(id);
      return true;
   }

   public static Boolean updateRow(Receipt input)
   {
      createTable();
      Connection c = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         PreparedStatement sql = c.prepareStatement("UPDATE Receipts SET Vendor = ?, Payment_Method = ?, Total = ?,"
               + " Date = ? WHERE id = ?;");
         sql.setString(1, input.getVendor()); 
         sql.setString(2, input.getPaymentMethod());
         sql.setString(3, input.getAmount());
         sql.setString(4, input.getDate());
         sql.setString(5, String.valueOf(input.getID()));
         sql.execute();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
      return true;
   }

   public static Receipt getRow(int id)
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      Receipt out = new Receipt();
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "SELECT * FROM Receipts WHERE ID = " + id + ";";
         ResultSet rs = stmt.executeQuery(sql);
         while ( rs.next() ) {
            out.setAmount(rs.getString("Total"));
            out.setVendor(rs.getString("Vendor"));
            out.setPaymentMethod(rs.getString("Payment_Method"));
            out.setDate(rs.getString("Date"));
         }
         out.setID(id);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return out;
      }
      return out;
   }

   public static ArrayList<Receipt> getReceiptList()
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      ArrayList<Receipt> list = new ArrayList<Receipt>();
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "SELECT * FROM 'Receipts';";
         ResultSet rs = stmt.executeQuery(sql);
         while ( rs.next() ) {
            Receipt out = new Receipt();
            out.setAmount(rs.getString("Total"));
            out.setVendor(rs.getString("Vendor"));
            out.setPaymentMethod(rs.getString("Payment_Method"));
            out.setDate(rs.getString("Date"));
            out.setID(rs.getInt("ID"));
            list.add(out);
         }
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return list;
      }
      return list;
   }

   public static Boolean removeRow(int id)
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "DELETE FROM Receipts WHERE ID = " + id + ";";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
      return true;
   }

   public static void removeRow(Receipt input)
   {
      removeRow(input.getID());
   }

   public static Boolean exportCSV(String fileName)
   {
      createTable();
      Connection c = null;
      Statement stmt = null;
      String outText = "";
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:" + mDBName);

         stmt = c.createStatement();
         String sql = "SELECT * FROM Receipts;";
         ResultSet rs = stmt.executeQuery(sql);
         while ( rs.next() ) {
            outText += "\"" + rs.getString("Date") + "\",";
            outText += "\"" + rs.getString("Vendor") + "\",";
            outText += "\"" + rs.getString("Payment_Method") + "\",";
            outText += rs.getString("Total") + "\n";
         }
         stmt.close();
         c.close();
         File outFile = new File(fileName);
         FileWriter fileWriter = new FileWriter(outFile);
         BufferedWriter out = new BufferedWriter(fileWriter);
         out.write(outText);
         out.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
      return true;
   }
}