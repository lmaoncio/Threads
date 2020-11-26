package com.jtorres.threads.controllers;

import com.jtorres.threads.models.Cook;
import com.jtorres.threads.models.Customer;
import com.jtorres.threads.models.Table;
import com.jtorres.threads.views.Draws;
import com.jtorres.threads.views.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends JFrame {
    private final static int NUM_COOKS = 3;
    private final static int NUM_CUSTOMERS = 12;

    private Table table = new Table();
    private List<Customer> customerList = new ArrayList<>();
    private List<Cook> cookList = new ArrayList<>();
    private List<Draws> drawsCookList = new ArrayList<>();
    private List<Draws> drawsCustomerList = new ArrayList<>();
    private List<Draws> drawsTableList = new ArrayList<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private View view = new View(drawsCookList, drawsCustomerList, drawsTableList, table);

    public Restaurant() throws IOException {
        setWindowLayout();
        setViewLayout();
        createCooks();
        createCustomers();
        createTables();
        setVisible(true);
        view.paint();
    }

    public void createCooks() throws IOException {
        int y = 0;
        for (int i = 0; i < NUM_COOKS; i++) {
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File("chef.png"));
            Draws draw = new Draws(bufferedImage, getWidth() - bufferedImage.getWidth(), y);
            drawsCookList.add(draw);
            cookList.add(new Cook(i, table, (int) (Math.random() * (500 - 100)) + 100, draw));
            cookList.get(i).getCookThread().start();
            y += bufferedImage.getHeight();
        }
    }

    public void createCustomers() throws IOException {
        int y = 0;
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(new File("customer.png"));
            Draws draw = new Draws(bufferedImage, 0, y);
            drawsCustomerList.add(draw);
            customerList.add(new Customer(i, table, (int) (Math.random() * (25000 - 15000)) + 15000, draw));
            customerList.get(i).getCustomerThread().start();
            y += bufferedImage.getHeight();
        }
    }

    public void createTables() throws IOException {
        BufferedImage mesa = ImageIO.read(new File("table.png"));
        drawsTableList.add(new Draws(mesa, getWidth() / 2 - mesa.getWidth() / 2, getHeight() / 4));
    }

    public void setViewLayout() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(view, gbc);
    }

    public void setWindowLayout() {
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLayout(new GridBagLayout());
        setBackground(Color.BLUE);
        setResizable(true);
    }

}

