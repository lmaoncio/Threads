package com.jtorres.threads.models;

import com.jtorres.threads.views.Draws;

import java.io.IOException;

public class Cook implements Runnable {
    private static final String NAME = "cookThread";
    private int id;
    private Table table;
    private int cookingTime;
    private Thread cookThread;
    private Draws draw;

    public Cook(int id, Table table, int cookingTime, Draws draw) {
        this.id = id;
        this.table = table;
        this.cookingTime = cookingTime;
        this.draw = draw;
        this.cookThread = new Thread(this, NAME);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(cookingTime);
                putMeat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void putMeat() throws InterruptedException {
        int temp = getDraw().getX();

        for (int i = getDraw().getX(); i > 850; i--) {
            draw.setX(i);
            Thread.sleep(5);
        }

        table.put();

        for (int i = 850; i < temp; i++) {
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

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Thread getCookThread() {
        return cookThread;
    }

    public void setCookThread(Thread cookThread) {
        this.cookThread = cookThread;
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
