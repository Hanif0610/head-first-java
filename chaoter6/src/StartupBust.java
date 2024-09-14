import java.util.ArrayList;

public class StartupBust {
    private ArrayList<Startup> stList = new ArrayList<>();
    private GameHelper helper = new GameHelper();
    private int numOfGuess = 0;

    public static void main(String[] args) {
        StartupBust bust = new StartupBust();
        bust.setUpGame();
        bust.startPlaying();
    }

    private void setUpGame() {
        Startup st1 = new Startup();
        Startup st2 = new Startup();
        Startup st3 = new Startup();

        st1.setName("cabista");
        st2.setName("poniez");
        st3.setName("hacqi");

        stList.add(st1);
        stList.add(st2);
        stList.add(st3);

        System.out.println("Your goal is to sink three Startups");
        System.out.println("cabista, poniez, hacqi");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for(Startup st : stList) {
            st.setLocationCells(helper.placeStartup(3));
        }
    }

    private void startPlaying() {
        while (!stList.isEmpty()) {
            String point = helper.getUserInput("enter a guess");
            checkUserGuess(point);
        }
        finishGame();
    }

    private void checkUserGuess(String point) {
        numOfGuess++;
        String result = "miss";
        for(Startup st : stList) {
            result = st.checkYourself(point);
            if(result != "miss") {

                if(result == "kill"){
                    stList.remove(st);
                }

                break;
            }
        }

        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All Startups are dead! Your stock is now worthless");
        if(numOfGuess <= 18) {
            System.out.println("It only took you " + numOfGuess + " guesses.");
            System.out.println("You got out before your options sank.");
        }
        else {
            System.out.println("Took you long enough. " + numOfGuess + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }
}
