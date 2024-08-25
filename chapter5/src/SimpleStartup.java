public class SimpleStartup {
    private int[] locationCells = new int[7];
    private int numOfHits = 0;

    public String checkYourself(int guess) {
        String result = "miss";
        for(int cell : locationCells) {
            if(cell == guess) {
                numOfHits++;
                result = "hit";
                break;
            }
        }

        if(numOfHits == 3)
            result = "kill";

        System.out.println(result);
        return result;
    }

    public void setLocationCells(int[] cellLocations) {
        for (int num : cellLocations) {
            locationCells[num] = num;
        }
    }
}
