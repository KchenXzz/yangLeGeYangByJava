package yangLeGeYang.test;

import yangLeGeYang.util.LayerUtil;
import yangLeGeYang.model.Card;
import yangLeGeYang.model.Cell;
import yangLeGeYang.model.Layer;

import javax.swing.*;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 10:04
 */
public class Start1 extends JFrame {


    private Layer layer= LayerUtil.buildLayer(6,6);
    public Start1() {

        //初始化面板
        initFrame();
        //渲染图层
        renderLayer();

    }

    private void renderLayer() {
        //把图层上的一个个单元格添加到界面
        //先获取单元格数组
        Cell[][] cells = layer.getCells();

        // layer.showCells();

        //循环遍历这个二维数组添加
//        // 要修改card类的坐标
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                int x=row*59;
                int y=col*66;
                Card card1 = cells[row][col].getCard();
                card1.setBounds(x,y,59,66);
                this.getContentPane().add(card1);
            }
        }
    }

    /**
     * 自动刷新线程
     */
    private void autoFlushed() {

        Start1 start = this;
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
        this.setTitle("羊了个羊！");

        this.setSize(492, 822);
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);


        //显示出来
        setVisible(true);

        //启动自动刷新
        autoFlushed();


    }

    public static void main(String[] args) {



        new Start1();
    }


}
