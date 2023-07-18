package yangLeGeYang;


/**
 * //TODO
 * 启动类
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 10:07
 */
public class Start {
    public static void main(String[] args) {

        //地图层数  可以实现关卡了

//        for (int i = 2; i <= 3; ) {
//
//            YangLeGeYang yang = new YangLeGeYang(i);
//            if (yang.isWin()){
//                i++;
//            }
//
//        }


        /*
        准备实现关卡，在主界面里实现，通过iswin()来判断，赢了，就直接关闭当前JFrame然后新创建一个JFrame
        只有通过了指定关卡，才显示游戏胜利
        那么，iswin也要改的
         */

        new YangLeGeYang(2);



    }
}
