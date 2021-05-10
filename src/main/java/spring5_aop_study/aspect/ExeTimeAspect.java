package spring5_aop_study.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/** @Pointcut은 공통기능을 적용할 대상을 설정
 * @Aspect 애노테이션을 적용할 클래스는 Advice와 Pointcut을 함계 제공
 */
@Aspect												// Axpect로 사용할 클래스
@Order(1)
public class ExeTimeAspect {
	/**
	* @Pointcut은 공통 기능을 적용할 대상을 설정
	* - spring5_aop_study 패키지와 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정
	*/
	@Pointcut("execution(public * spring5_aop_study.aop..*(..))")	// 공통기능을 적용할 pointcut // public은 생략 가능
	private void publicTarget() {
	}
	/** @Around 애노테이션은 Around Advice를 설정
	 * 	publicTarget()메서드에 정의한 Pointcut에 공통 기능을 적용으로 spring5_aop_study패키지나 하위패키지에 속한
	 * 		빈 객체의 public 메서드에 @around가 붙은 measure() 메서드를 적용
	 * measure() 메서드의 ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드를 호출할 때 사용하며 preceed()메서드를
	 * 	사용해서 실제 대상 객체의
	 * 	메서드를 호출한다.
	 */
	@Around("publicTarget()")	// 공통기능 메서드
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {		// finally 먼저 수행 후 return함
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns%n", joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start));
		}
	}
}
