import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
    private static final String ALPHABET = "abcdefg"; // 7 * 7을 맞추기 위한 알파벳 7개 선언
    private static final int GRID_LENGTH = 7; // 가로 7칸
    private static final int GRID_SIZE = 49; // 총 49칸
    private static final int MAX_ATTEMPTS = 200; // 최대 시도 횟수
    private static final int HORIZONTAL_INCREMENT = 1; // 가로 증가 값
    private static final int VERTICAL_INCREMENT = GRID_LENGTH; // 세로 증가 값

    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");

        Scanner sc = new Scanner(System.in);
        return sc.nextLine().toLowerCase();
    }

    public ArrayList<String> placeStartup(int count) {
        int[] startupCoords = new int[count];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = getIncrement(); // 수직 수평 방향 선택

        while (!success && attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE); // 1~49 중 랜덤 숫자

            for(int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;

                // 7 * 7 칸이기 때문에
                // 1이 증가하면 옆으로, 7이 증가했다면 윗칸으로 좌표 이동
                location += increment;
            }

            if(startupFits(startupCoords, increment)) { // 좌표가 7 * 7을 벗어났는지 확인
                success = coordsAvailable(startupCoords); // 해당 좌표에 이미 생성된 값이 있는지 확인
            }
        }

        savePositionToGrid(startupCoords); // 해당 위치에 값 생성
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<>();
        for(int index : startupCoords) {
            String alphaCoords = getAlphaCoordsFormIndex(index); // 해당 위치의 좌표를 확인하여 알파벳 + 숫자로 좌표 변경
            alphaCells.add(alphaCoords); // 변경된 좌표(ex. A6, B3)를 arrayList에 추가
        }

        return alphaCells;
    }

    private String getAlphaCoordsFormIndex(int index) {
        int row = calcRowFromIndex(index); // 열 위치 확인
        int column = index % GRID_LENGTH; // 행 위치 확인
        String letter = ALPHABET.substring(column, column + 1); // 해당 행에 해당하는 알파벳 가져오기
        return letter + row; // 알파벳과 열을 합쳐 문자 만들어 반환
    }

    private void savePositionToGrid(int[] startupCoords) {
        for(int index : startupCoords) {
            grid[index] = 1;
        }
    }

    private boolean coordsAvailable(int[] startupCoords) {
        for(int coord : startupCoords) {
            if(grid[coord] != 0) // 해당 위치에 이미 값이 생성 되었다면
                return false; // false 반환
        }
        return true; // 해당 지점에 생성된 값이 없으면 true 반환
    }

    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if(increment == HORIZONTAL_INCREMENT) // 수평 방향이라면
            // 시작 지점과 마지막 지점이 같은 열에 있는지 확인
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        else
            return finalLocation < GRID_SIZE; // 마지막 지점이 49칸을 넘지는 않았는지 확인
    }

    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH; // 현재 인덱스가 어느 열에 있는지 확인
    }

    private int getIncrement() {
        if(startupCount % 2 == 0) // 홀 짝을 확인하여
            return HORIZONTAL_INCREMENT; // 짝수면 수평
        else
            return VERTICAL_INCREMENT; // 홀수면 수직
    }
}
