package com.jtorres.threads.views;

import com.jtorres.threads.models.Table;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class View extends Canvas {

    private List<Draws> drawsCustomerList;
    private List<Draws> drawsCookList;
    private List<Draws> drawsTableList;
    private Table table;
    public View(List<Draws> drawsCookList, List<Draws> drawsCustomerList, List<Draws> drawsTableList, Table table) throws IOException {
        this.drawsCookList = drawsCookList;
        this.drawsCustomerList = drawsCustomerList;
        this.drawsTableList = drawsTableList;
        this.table = table;


        setSize(getSize().width, getSize().height);
    }

    public void paint() throws IOException {
        createBufferStrategy(2);
        BufferStrategy bs;

        do {
            bs = getBufferStrategy();
            Graphics g = bs.getDrawGraphics();
            g.clearRect(0,0,getWidth(),getHeight());

            BufferedImage fondo = ImageIO.read(new File("fondo.png"));
            g.drawImage(fondo, 0, 0, this);

            for (int i = 0; i < drawsCookList.size(); i++) {
                g.drawImage(drawsCookList.get(i).getBufferedImage(), drawsCookList.get(i).getX(), drawsCookList.get(i).getY(), this);
            }

            for (int i = 0; i < drawsCustomerList.size(); i++) {
                g.drawImage(drawsCustomerList.get(i).getBufferedImage(), drawsCustomerList.get(i).getX(), drawsCustomerList.get(i).getY(), this);
            }

            for (int i = 0; i < drawsTableList.size(); i++) {
                g.drawImage(drawsTableList.get(i).getBufferedImage(), drawsTableList.get(i).getX(), drawsTableList.get(i).getY(), this);
            }

            int tempX = drawsTableList.get(0).getX() + 100;
            int tempY = drawsTableList.get(0).getY() + 25;

            for (int i = 0; i < table.getAmount(); i++) {
                BufferedImage hamburguesa = ImageIO.read(new File("hamburguesa.png"));
                g.drawImage(hamburguesa, tempX, tempY, this);
                tempY-=25;
            }

            g.dispose();
            bs.show();

        } while (true);

    }
}