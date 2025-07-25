import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int sum = 0; // 합계변수
		int num = 0; // 입력받을 값 변수
		int count = 0; // 카운팅

		while (true) {
			System.out.print("숫자 입력 : ");
			num = sc.nextInt();
			if(num == -1) break;
			sum+= num;
			++count;
		}
		System.out.println(count);
		System.out.println("합계 : "+sum);
		System.out.println("평균 : "+sum/count);

	}

}
