package yangLeGeYang.test;

import yangLeGeYang.model.Card;
import yangLeGeYang.model.Cell;
import yangLeGeYang.model.Layer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 11:45
 */
public class Test1 {


    //private String[] cardArr;

    static ArrayList<String> cardArr = new ArrayList<>();


    private static void initCardNameArr() throws IOException {

        //将所有的卡片的文件名添加到列表
        File res = new File("res");
        File[] ress = res.listFiles();//得到源文件的子目录
        for (File file : ress) {
            String[] fileName = file.getName().split("\\.");


            if (fileName[1].equals("png")) {
                if (fileName[0].length() == 2) {
                    cardArr.add(fileName[0]);
                }

            }


        }

    }

    private static String getRandomName() {
        Random random = new Random();
        return cardArr.get(random.nextInt(25));
    }

    public static void main(String[] args) throws IOException {
        initCardNameArr();

        Layer layer = new Layer(6, 6);

        Card[] cards = new Card[layer.getCapacity()];

        Cell[][] cells = layer.getCells();


        //生成图层的一维数组
        //一次生产三张卡片，但是注意图层要是3的倍数！！！！！！
        for (int i = 0; i < 36; i = i + 3) {

            String randomName = getRandomName();
            Card card1 = new Card(randomName);
            Card card2 = new Card(randomName);
            Card card3 = new Card(randomName);

            cards[i] = card1;
            cards[i + 1] = card2;
            cards[i + 2] = card3;
        }
        for (Card card : cards) {
            System.out.println(card.getName());
        }

        //打乱数组
        Random random = new Random();


        //再用个循环遍历数组把里面元素打乱
        for (int i = 0; i < cards.length; i++) {
            //暂存变量
            Card temp = cards[i];
            //i位置与随机索引的值交换
            int r = random.nextInt(cards.length);
            cards[i] = cards[r];
            cards[r] = temp;
        }


        System.out.println("---------打乱后----------");




        int index=0;
            for (int row = 0; row < cells.length; row++) {
                for (int col = 0; col < cells[row].length; col++) {

                    Cell cell = new Cell();

                    cell.setState(true);
                    cell.setCard(cards[index++]);

                    cells[row][col]=cell;
                }
            }






    }
}
