package com.jtorres.threads.models;

import com.jtorres.threads.views.Draws;

public class Customer implements Runnable {
    private static final String NAME = "customerThread";
    private int id;
    private Table table;
    private int eatingTime;
    private Thread customerThread;
    private Draws draw;

    public Customer(int id, Table table, int eatingTime, Draws draw) {
        this.id = id;
        this.table = table;
        this.eatingTime = eatingTime;
        this.draw = draw;
        this.customerThread = new Thread(this, NAME);
    }

    @Override
    public void run() {
        while (true) {
            try {
                takeMeat();
                Thread.sleep(eatingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void takeMeat() throws InterruptedException {
        int temp = getDraw().getX();

        for (int i = getDraw().getX(); i < 600; i++) {
            draw.setX(i);
            Thread.sleep(5);
        }

        table.get();

        for (int i = 600; i > temp; i--) {
            draw.setX(i);
            Thread.sleep(5);
        }
    }

    public static String getNAME() {
        return NAME;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getEatingTime() {
        return eatingTime;
    }

    public void setEatingTime(int eatingTime) {
        this.eatingTime = eatingTime;
    }

    public Thread getCustomerThread() {
        return customerThread;
    }

    public void setCustomerThread(Thread customerThread) {
        this.customerThread = customerThread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Draws getDraw() {
        return draw;
    }

    public void setDraw(Draws draw) {
        this.draw = draw;
    }
}
