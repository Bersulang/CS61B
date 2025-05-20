public class Dessert {
    int price;
    int flavor;
    static int numDesserts;

    public Dessert(int flavor, int price) {
        this.price = price;
        this.flavor = flavor;
        numDesserts +=1;
    }

    public void printDessert() {
        System.out.println(this.flavor + " " + this.price + " " + numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
