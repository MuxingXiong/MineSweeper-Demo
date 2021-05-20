package components;

import entity.GridStatus;
import minesweeper.MainFrame;
import minesweeper.GamePanel;

import java.awt.*;

public class GridComponent extends BasicComponent {
    public static int gridSize = 30;


    private int row;
    private int col;
    private GridStatus status = GridStatus.CoverNo;
    private int content = 0;
    private static GamePanel parent;

    public static void setParent(GamePanel parent) {
        GridComponent.parent = parent;
    }

    public GridComponent(int x, int y) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
    }

    @Override
    public void onMouseLeftClicked() {
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
        if (this.status == GridStatus.CoverNo) {
            this.status = GridStatus.OpenNo;
            repaint();
            parent.autoOpenGrid(row,col);
            MainFrame.controller.nextTurn();
        }
        if (this.status == GridStatus.CoverYes) {
            this.status = GridStatus.OpenYes;
            repaint();
            MainFrame.controller.nextTurn();
        }
        //TODO: 在左键点击一个格子的时候，还需要做什么？
    }


    @Override
    public void onMouseRightClicked() {
        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status == GridStatus.CoverYes) {
            this.status = GridStatus.Flag;
            repaint();
            MainFrame.controller.nextTurn();
        }

        //TODO: 在右键点击一个格子的时候，还需要做什么？
    }

    public void draw(Graphics g) {

        if (this.status == GridStatus.CoverNo||this.status == GridStatus.CoverYes) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        if (this.status == GridStatus.OpenNo) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
        if (this.status == GridStatus.OpenYes) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.BLACK);
            g.drawString("B", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
        if (this.status == GridStatus.Flag) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            g.drawString("F", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getContent() {
        return content;
    }

    public void openGrid() {
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
        if (this.status == GridStatus.CoverNo) {
            this.status = GridStatus.OpenNo;
            repaint();
            MainFrame.controller.nextTurn();
        }
        if (this.status == GridStatus.CoverYes) {
            this.status = GridStatus.OpenYes;
            repaint();
            MainFrame.controller.nextTurn();
        }
        //TODO: 在左键点击一个格子的时候，还需要做什么？
    }

    public GridStatus getStatus() {
        return status;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }


    public void setStatus(int i) {
        if(i!=-1)
            this.status = GridStatus.CoverNo;
        else
            this.status = GridStatus.CoverYes;
    }
}
