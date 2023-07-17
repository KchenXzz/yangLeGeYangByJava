package yangLeGeYang.model;

import jaco.mp3.player.MP3Player;

import java.io.File;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 22:32
 */
public class Music {

    public void music(){

        File file = new File("model\\背景音乐.mp3");
        MP3Player player = new MP3Player(file);

        player.play();
        player.setRepeat(true);


    }


}
