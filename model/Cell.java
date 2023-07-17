package yangLeGeYang.model;

/**
 * //TODO
 *
 * 单元格对象，两种状态，有牌，无牌
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 11:11
 */
public class Cell {

    //有牌true 无牌 false
    private boolean state=false;
    private Card card;

    /**
     * 有牌true 无牌 false
     * @return 有牌true 无牌 false
     */
    public boolean isState() {
        return state;
    }
    /**
     * 有牌true 无牌 false
     *
     */
    public void setState(boolean state) {
        this.state = state;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
