package yangLeGeYang.test;

import yangLeGeYang.util.LayerUtil;
import yangLeGeYang.model.Card;
import yangLeGeYang.model.Cell;
import yangLeGeYang.model.Layer;

import javax.swing.*;
import java.awt.*;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 14:39
 */
public class TestRenderLayer extends JFrame {


    //创建一个图层
    private Layer layer= LayerUtil.buildLayer(6,6);

    public TestRenderLayer() throws HeadlessException {
        //初始化
        initFrame();

        //添加图层
        //把图层上的一个个单元格添加到界面

        //先获取单元格数组
        Cell[][] cells = layer.getCells();

       // layer.showCells();

        //循环遍历这个二维数组添加
//        // 要修改card类的坐标
//        for (int row = 0; row < cells.length; row++) {
//            for (int col = 0; col < cells[row].length; col++) {
//                int x=row*59;
//                int y=col*66;
//                Card card1 = cells[row][col].getCard();
//                card1.setBounds(x,y,59,66);
//                this.getContentPane().add(card1);
//            }
//        }

        Card card = new Card("帽子");
        card.setBounds(100,100,59,66);
        this.getContentPane().add(card);


        //启动自动刷新
        autoFlushed();

    }

    private void autoFlushed() {

        TestRenderLayer start = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    start.repaint();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


    }




    private void initFrame() {

        //设置界面的标题
        this.setTitle("羊了个喜羊羊！");

        this.setSize(492, 822);

        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置界面居中
        this.setLocationRelativeTo(null);


        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);

        this.setBounds(0,0,492,822);

        this.setVisible(true);
//        //添加背景图片
//        JLabel background = new JLabel(new ImageIcon("res\\背景.jpg"));
//        background.setSize(480, 800);
//
//        //把背景图片添加到界面当中
//        this.getContentPane().add(background);

    }


    public static void main(String[] args) {


        new TestRenderLayer();
    }


}

