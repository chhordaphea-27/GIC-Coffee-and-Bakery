package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;

public class Table extends Model {
    private int tableNumber;
    private String tableStatus;

    public Table() {}

    public Table(int tableNumber, String tableStatus) {
        this.tableNumber = tableNumber;
        this.tableStatus = tableStatus;
    }

    public Table(int tableId, int tableNumber, String tableStatus) {
        super(tableId);
        this.tableNumber = tableNumber;
        this.tableStatus = tableStatus;
    }

    // Getters and setters

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

   public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
