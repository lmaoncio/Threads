package com.jtorres.threads.models;

import com.jtorres.threads.views.Draws;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Table {
    public static final int MAX_AMOUNT = 10;
    public static final int MIN_AMOUNT = 0;
    private int amount = 0;

    public synchronized void get() throws InterruptedException {
        while (amount <= MIN_AMOUNT) {
            wait();
        }
        amount--;
        notifyAll();
    }

    public synchronized void put() throws InterruptedException {
        while (amount >= MAX_AMOUNT) {
            wait();
        }
        amount++;
        notifyAll();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
