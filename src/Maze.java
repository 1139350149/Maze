import builder.*;
import mapEntity.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Maze {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                MyFrame frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    public static class MyFrame extends JFrame {
        public static final String TITLE = "Maze Game";
        public int width = 400;
        public int height = 450;

        private Map map;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            Scanner scanner = new Scanner(System.in);
            int capacity = scanner.nextInt();
            int roomCount = scanner.nextInt();
            Director director = new Director();
            if (capacity != 0 && roomCount != 0) {
                director.setCapacity(capacity);
                director.setRoomCount(roomCount);
                this.width = capacity * 10 + 100;
                this.height = capacity * 10 + 150;
            }
            Builder mapBuilder = new MapWithItemBuilder();
            map = director.Build(mapBuilder);
            map.findWay();
            setTitle(TITLE);
            setSize(width, height);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            // 设置窗口的内容面板
            setContentPane(map);
        }

//        @Override
//        public void paint(Graphics g) {
//            map.paintMap(g);
//        }
    }
}

