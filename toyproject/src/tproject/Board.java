package tproject;

// 바둑판의 변화와 상태파악을 담당하는 클래스
public class Board {
	// 싱글톤
	private static Board instance;	// 프라이빗 정적 인스턴스 변수
    private int[][] board;			
    private Board() {				// 프라이빗 생성자
        board = new int[15][15];
    }
    public static Board getInstance() { // 정적 메서드
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }
	
    // 흑돌 놓기
	void setBoard_black(int x, int y) {
		board[x][y] = 1;
	}
	// 백돌 놓기
	void setBoard_white(int x, int y) {
		board[x][y] = 2;
	}
	// 바둑판에서 x행 y열의 값 리턴
	int getBoard(int x, int y) {
		return board[x][y];
	}
	// 바둑판 상태 보여주기
	void showBoard() {
		for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if (x == 0 && y == 0) {			// 왼쪽 위 모서리막기
                    System.out.print(" ┌ ");
                }
                else if (x == 14 && y == 14){	// 오른쪽 아래 모서리막기
                    System.out.print(" ┘ ");
                }
                else if (x == 14 && y == 0) {	// 왼쪽 아래 모서리막기
                	System.out.print(" └ ");
                }
                else if (x == 0 && y == 14) {	// 오른쪽 위 모서리막기
                	System.out.print(" ┐ ");
                }
                else if (x == 0) {				// 위에 막기
                	System.out.print(" ┬ ");
                }
                else if (x == 14) {				// 아래 막기
                	System.out.print(" ┴ ");
                }
                else if (y == 0) {				// 왼쪽 막기
                	System.out.print(" ├ ");
                }
                else if (y == 14) {				// 오른쪽 막기
                	System.out.print(" ┤ ");
                }
                else if (board[x][y]==1) {				// 오른쪽 막기
                	System.out.print(" ● ");
                }
                else if (board[x][y]==2) {				// 오른쪽 막기
                	System.out.print(" ○ ");
                }
                else {
                	System.out.print(" ┼ ");
                }
            }
            System.out.println();
        }
		
	}
	
}
