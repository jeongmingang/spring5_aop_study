package spring5_aop_study;

import spring5_aop_study.aop.Calculator;
import spring5_aop_study.aop.ImpeCalculator;
import spring5_aop_study.aop.RecCalculator;

public class Main {

	public static void main(String[] args) {
		int num = 5;
		
		test01(num);

	}

	private static void test01(int num) {
		long res = -1;
		long start = 0;
		long end = 0;
		Calculator impCal = new ImpeCalculator();
//		start = System.currentTimeMillis();
		res = impCal.factorial(num);
//		end = System.currentTimeMillis();
		
		System.out.printf("ImpeCalculator.factorial(%d) 실행시간 = %d%n", num, (end - start));
		System.out.printf("%d! = %d%n", num, res);
		
		Calculator recCal = new RecCalculator();
//		start = System.currentTimeMillis();
		res = recCal.factorial(num);
//		end = System.currentTimeMillis();
		
		System.out.printf("RecCalculator.factorial(%d) 실행시간 = %d%n", num, (end - start));
		System.out.printf("%d! = %d%n", num, res);
	}

}
