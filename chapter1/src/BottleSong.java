public class BottleSong {
    public static void main(String[] args) {
        int bottleName = 10;
        String word = "bottles"; //복수형

        while(bottleName > 0) {
            System.out.println(bottleName + " green " + word + ", hanging on the wall");
            System.out.println(bottleName + " green " + word + ", hanging on the wall");
            System.out.println("And if one green bottle should accidentally fail,");

            bottleName -= 1;
            if(bottleName == 1)
                word = "bottle"; //단수형

            if(bottleName == 0)
                System.out.println("There'll be no green bottles, hanging on the wall");
            else
                System.out.println("There'll be " + bottleName + " green " + word + ", hanging on the wall");
        }
    }
}
