package tproject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("플레이할 게임을 선택하십시오\n"
					+ "1. 오목        2. 야구게임");
			int input = scanner.nextInt();
			if(input == 1) {
				Fivewoods.main(args);
				break;
			}else if(input == 2) {
				Baseballgame.main(args);
				break;
			}else {
				System.out.println("잘못된 입력입니다. 다시 입력해주십시오");
			}
			
		}
		scanner.close();
	}

}
