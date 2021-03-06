package com.hrms.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection conn;
    private static Statement st;
    private static ResultSet rset;

    //this function connects to any database u want database
    public static Connection getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection(
                    ConfigsReader.getPropertyValue("dbUrl"),
                    ConfigsReader.getPropertyValue("dbUsername"),
                    ConfigsReader.getPropertyValue("dbPassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    //statement
    public static ResultSet getResultSet(String query) throws SQLException {
        try {
            st = getConnection().createStatement();
            rset = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rset;

    }

    //retrieving data using List of Maps
    public static List<Map<String, String>> getDBDataIntoMap(String query) {
        List<Map<String, String>> dbList = new ArrayList<>();
        Map<String, String> map;
        try {
            ResultSetMetaData rsetMData = getResultSet(query).getMetaData();

            //will loop through all rows
            while (rset.next()) {
                map = new LinkedHashMap<>();

                //loops through columns of the row
                for (int c = 1; c <= rsetMData.getColumnCount(); c++) {

                    map.put(rsetMData.getColumnName(c), rset.getString(c));
                }
                dbList.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dbList;

    }
    /**
     * This method will execute the given sql query, return the data from DB as a List of String and close connection
     *
     * @param query
     * @return
     */
    public static List<String> getDBDataIntoList(String query) {

        List<String> dbList = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = getResultSet(query).getMetaData();//info about ResultSet table
            while (rset.next()) {//loops through all rows
                for (int row = 1; row <= resultSetMetaData.getColumnCount(); row++) {
                    String data = rset.getString(row);
                    dbList.add(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dbList;
    }




    public static void closeConnection() {
        try {
            if (rset != null) {
                rset.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {

        }
    }




}
