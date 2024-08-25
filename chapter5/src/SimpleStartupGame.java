public class SimpleStartupGame {
    public static void main(String[] args) {
        int numOfGuesses = 0;
        boolean isAlive = true;

        SimpleStartup startup = new SimpleStartup();
        GameHelper helper = new GameHelper();

        //Math.random()은 0에서 1미만의 수를 랜덤으로 돌림
        //그래서 5를 곱해서 0부터 4 사이의 난수 생성
        int randomNum = (int)(Math.random() * 5);
        int[] locations = { randomNum, randomNum + 1, randomNum + 2 };
        startup.setLocationCells(locations);

        while (isAlive) {
            int guess = helper.getUserInput("enter a number");
            String result = startup.checkYourself(guess);
            numOfGuesses++;

            if(result.equals("kill")) {
                isAlive = false;
                System.out.println("You took " + numOfGuesses + " guesses");
            }
        }
    }
}
