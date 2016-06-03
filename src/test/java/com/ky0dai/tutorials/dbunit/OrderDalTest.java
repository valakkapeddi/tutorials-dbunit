package com.ky0dai.tutorials.dbunit;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * OrderDal Tester.
 *
 * @author ky0dai
 * @since <pre>05/08/2016</pre>
 * @version 1.0
 */


public class OrderDalTest {

    private IDatabaseTester hsqlTester = null;
    private Connection hsqlDbConnection = null;

    @Before
    public void setUp() throws Exception {
        hsqlTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem");
        hsqlDbConnection = hsqlTester.getConnection().getConnection();

        hsqlDbConnection.createStatement().execute("drop table if exists OrderTable");
        hsqlDbConnection.createStatement().execute("create table OrderTable (Symbol VARCHAR(8) NOT NULL, Price FLOAT NOT NULL, Quantity INT NOT NULL)");
    }


    // Insert
    @Test
    public void testOrderDalInsertOrder() throws Exception {
        OrderDal dab = new OrderDal(hsqlDbConnection);

        XmlDataSet xmlDataSet = new XmlDataSet(getClass().getResourceAsStream("/single_order_inserted.xml"));
        ITable expectedTable =  xmlDataSet.getTable("OrderTable");

        dab.persistOrder("EUR/USD", 1.5f, 100);

        ITable actualTable = hsqlTester.getConnection().createDataSet().getTable("OrderTable");

        Assertion.assertEquals(expectedTable, actualTable);

    }
}
