package com.ky0dai.tutorials.dbunit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kyodai on 5/8/2016.
 */
public class OrderDal {

    private Connection connection;

    public OrderDal(Connection connectionIn) {
        connection = connectionIn;
    }

    public void persistOrder(String symbol, float price, int quantity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into OrderTable (Symbol, Price, Quantity) values (?,?,?)");
        statement.setString(1, symbol);
        statement.setFloat(2, price);
        statement.setInt(3, quantity);
        statement.execute();
    }
}
