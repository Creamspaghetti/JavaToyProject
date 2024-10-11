package tproject;

// 오목게임의 승리 여부를 판단하는 클래스
public class Find {
    private Board board;
    private static final int SIZE = 13;
    private static final int WIN_CONDITION = 5;

    public Find() {
        this.board = Board.getInstance();	// 싱글톤 패턴의 클래스인 Board 불러오기
    }

    public boolean checkWin(int x, int y) {	// x행 y열에 돌을 놓았을 때 승리여부 판단 
        int color = board.getBoard(x, y);	// x행 y열에 놓인 돌의 색깔
        if (color == 0) { 					// 돌이 놓이지 않았을 경우
        	return false; 
        } 
        
        // 8방향 모두 탐색하여 오목에 하나라도 해당할 경우 -> true 리턴
        return (checkDirection(x, y, color, 1, 0) ||  // 가로 검사
                checkDirection(x, y, color, 0, 1) ||  // 세로 검사
                checkDirection(x, y, color, 1, 1) ||  // 대각선 검사 (/)
                checkDirection(x, y, color, 1, -1));  // 대각선 검사 (\)
    }
    
    // 주어진 방향(dx, dy)으로 연속된 돌의 개수를 세고, 반대 방향도 함께 검사
    private boolean checkDirection(int x, int y, int color, int dx, int dy) {
        int count = 1;

        // 한 방향으로 검사
        count += countConsecutive(x, y, color, dx, dy);
        // 반대 방향으로 검사
        count += countConsecutive(x, y, color, -dx, -dy);
        // 두 방향이 직선상에 위치하므로 두 방향 검사의 리턴값(count)의 합을 구하여 WIN_CONDITION과 비교
        return count >= WIN_CONDITION;
    }

    // 특정 방향으로 연속된 돌의 개수 검사
    private int countConsecutive(int x, int y, int color, int dx, int dy) {
        int count = 0;
        for (int step = 1; step < WIN_CONDITION; step++) {
            int nx = x + step * dx;
            int ny = y + step * dy;
            // 탐색위치가 바둑판의 범위를 벗어나거나, 빈 칸 혹은 다른 색의 돌이 있을 경우 -> 탐색 종료
            if (nx < 1 || nx > SIZE || ny < 1 || ny > SIZE || board.getBoard(nx, ny) != color) {
                break;
            }
            count++;	// 탐색위치에 같은 돌이 있을 경우 -> count가 1씩 증가
        }
        return count;	// 오목일 경우 4가 리턴되고 아니면 0~3 리턴
    }
}
