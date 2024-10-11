package tproject;

import java.util.Scanner;

// 게임의 진행을 담당하는 Main 클래스
public class Fivewoods {
    static int turn = 1;

    public static void main(String[] args) {
        System.out.println("=============오목 게임==============");
        System.out.println("플레이어 1부터 시작하며 흑돌은 플레이어1, 백돌은 플레이어2 입니다\n"
        					+"바둑판은 13x13이며 판의 꼭짓점과 모서리에는 돌을 둘 수 없습니다\n"
        					+"범위는 1행 1열부터 13행 13열까지이며, 입력시 \"x y\" 의 형태로 입력하십시오\n"
        					+"예) 3행 11열 -> \"3 11\" (쌍따옴표는 입력할 필요가 없습니다)\n"
        					+"프로그램 종료 : \"-1 -1\" 입력\n"
        					+"플레이어1부터 시작합니다\n"
        					+"===================================");

        Board board = Board.getInstance();    	// 싱글톤 패턴의 Board 클래스의 유일한 인스턴스 호출
        Find find = new Find();                 // Find 클래스의 인스턴스 생성
        Scanner scanner = new Scanner(System.in);
        
        // 게임이 끝날때까지 지속적으로 진행시키는 무한루프 생성
        while (true) {            				
            int player = (turn % 2 == 1) ? 1 : 2;
            System.out.printf("플레이어%d======================\n", player);
            System.out.print("몇번째 행과 열에 두시겠습니까?: ");

            // 에러방지(하나의 숫자만 입력할 경우)
            int x = 0, y = 0;
            try {
                String[] input = scanner.nextLine().split(" ");
                x = Integer.parseInt(input[0]);
                y = Integer.parseInt(input[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("행과 열, 둘 모두를 입력하십시오");
                continue;
            }

            // 프로그램 종료
            if (x == -1 && y == -1) {
                System.out.println("========프로그램을 종료합니다========");
                break;
            }
            // 기타 에러방지
            else if (x > 13 || x < 1 || y > 13 || y < 1) {        	// 바둑판 범위 초과
                System.out.println("바둑판의 범위를 벗어났습니다. 돌을 다시 두십시오");
                continue;
            } else if (board.getBoard(x, y) != 0) {        			// 이미 돌이 위치함
                System.out.println("이미 둔 곳은 다시 둘 수 없습니다. 돌을 다시 두십시오");
                continue;
            }

            // 돌 놓기 및 승리 여부 판단
            placeStone(board, find, x, y, player);
            // 바둑판 현황 보여주기
            board.showBoard();    
            System.out.println("=============================");
        }
        scanner.close();
    }

    private static void placeStone(Board board, Find find, int x, int y, int player) {
        if (player == 1) {
            board.setBoard_black(x, y);		// 흑돌 놓기
        } else {
            board.setBoard_white(x, y);		// 백돌 놓기
        }

        if (find.checkWin(x, y)) {			// 승리 여부 판단
            board.showBoard();
            System.out.println("Game over");
            System.out.printf("플레이어%d(이)가 이겼습니다!", player);
            System.exit(0);  				// 프로그램 종료
        }

        turn++;    // 다음턴
    }
}
