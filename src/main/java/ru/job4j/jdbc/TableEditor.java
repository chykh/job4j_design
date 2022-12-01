package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void action(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        action(String.format("create table if not exists %s();", tableName));
    }

    public void dropTable(String tableName) throws Exception {
        action(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        action(String.format("alter table %s add column %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        action(String.format("alter table %s drop column %s cascade;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        action(String.format("alter table %s rename column %s to %s ;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "Название", "Тип");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("Займы");
            tableEditor.addColumn("Займы", "id", "serial primary key");
            tableEditor.addColumn("Займы", "Имя", "varchar(50)");
            System.out.println("Таблица, Состояние 1 \n" + tableEditor.getTableScheme("Займы"));

            tableEditor.addColumn("Займы", "Сумма", "integer");
            System.out.println("Таблица, Состояние 2 \n" + tableEditor.getTableScheme("Займы"));

            tableEditor.renameColumn("Займы", "Сумма", "Количество");
            System.out.println("Таблица, Состояние 3 \n" + tableEditor.getTableScheme("Займы"));

            tableEditor.dropColumn("Займы", "Количество");
            System.out.println("Таблица, Состояние 4 \n" + tableEditor.getTableScheme("Займы"));

            tableEditor.dropTable("Займы");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}