package tproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baseballgame {

	public static void main(String[] args) {
        System.out.println("=============야구 게임==============");
        System.out.println("무작위의 4자리 자연수가 정답으로 주어집니다\n"
        		+ "이 수는 각 자리의 수가 서로 중복되지 않으며 플레이어에게 보이지 않습니다\n"
        		+ "플레이어가 4자리 숫자를 입력하면 정답과의 일치율이 출력됩니다\n"
        		+ "일치율 : 숫자와 자릿수 모두 일치=strike, 숫자만 일치=ball\n"
        		+ "정답을 입력하면 승리합니다\n"
        		+ "예) 정답:1234, 입력:1356 > 1 strike 1 ball\n"
        		+ "프로그램 종료 : -1 입력\n"
        		+"===================================");

		Scanner scanner = new Scanner(System.in);

		// 랜덤한 4자리수의 정답 생성 과정
		ArrayList<Integer> random_numbers = new ArrayList<>();	// 1. Arraylist 생성
        for(int i=1;i<10;i++) {									// 2. 1부터 9까지의 숫자를 추가
        	random_numbers.add(i);
        }
        
        Collections.shuffle(random_numbers);					// 3. 랜덤하게 섞기
        
        											
		int random_num = random_numbers.get(0)*1000 			// 4. list에서 0~3 인덱스의 숫자 고르기
						+ random_numbers.get(1)*100 	
						+ random_numbers.get(2)*10 
						+ random_numbers.get(3);				// 생성된 4자리 랜덤숫자
		
		// 시도 횟수
		int trial_num = 0;
		
        while(true) {
        	System.out.print("숫자를 입력하시오 : ");
    		int input_num=0;
    		
    		// 플레이어의 숫자 입력
    		while(true) {
    			input_num = scanner.nextInt();
    			// 프로그램 종료
    			if(input_num == -1) {
    				System.out.println("정답은 "+random_num+" 였습니다");
    				System.out.println("프로그램을 종료합니다");
    				return;
    			}
    			// 범위 외의 숫자 입력 방지
    			else if(input_num>=1000 && input_num<=9999) {
    				break;
    			}
    			System.out.println("1000 ~ 9999 사이의 숫자를 입력해야합니다");
    			System.out.print("숫자를 입력하시오 : ");
    		}
    		
    		// 플레이어의 숫자를 각 자리수로 나누기
    		// 예) 2483 -> 2,4,8,3
    		ArrayList<Integer> user_num = new ArrayList<>();
            for (int i = 3; i >= 0; i--) {
            	user_num.add(input_num / (int) Math.pow(10, i));
                input_num %= (int) Math.pow(10, i);
            }
            
            // 스트라이크 및 볼 판단
    		int ball = 0;
    		int strike = 0;
    		for(int i=0;i<4;i++) {
    			for(int j=0;j<4;j++) {
    				// 숫자의 자리가 일치할 경우 -> 스트라이크
    				if(user_num.get(i)==random_numbers.get(j)) {
    					if(i==j) {
    						strike +=1;
    					}
    					// 숫자만 일치할 경우 -> 볼
    					else {
    						ball +=1;						
    					}
    				}
    			}
    		}
    		
    		// 스트라이크 및 볼 출력
    		System.out.println(strike+" strike and "+ball+" ball.");
    		trial_num +=1;
    		if(strike == 4) {
    			break;
    		}
        }
        // 게임 종료
        System.out.println("정답은 "+random_num+" 입니다. 총 "+trial_num+"번 시도하셨습니다");
        System.out.println("게임종료");
		
		scanner.close();
	}
}
