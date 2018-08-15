package codes.matthewp.desertedpvp.rankup;

public class Rank {

    private int order;
    private String group;
    private int cost;

    public Rank(int order, String group, int cost) {
        this.order = order;
        this.group = group;
        this.cost = cost;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
