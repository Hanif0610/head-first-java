public class SimpleStartupTestDrive {
    public static void main(String[] args) {
        SimpleStartup startup = new SimpleStartup();
        int[] arr = { 2, 3, 4 };

        startup.setLocationCells(arr);

        int guess = 6;
        String result = startup.checkYourself(guess);

        String testMessage = "failed";
        if(result.equals("hit")) {
            testMessage = "passed";
        }

        System.out.println(testMessage);
    }
}
