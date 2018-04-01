package in.til.cms.propfile;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;



public class TestInvocation implements IInvokedMethodListener {

	
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        ITestNGMethod testNgMethod = result.getMethod();
        ConstructorOrMethod contructorOrMethod = testNgMethod.getConstructorOrMethod();
        Method method = contructorOrMethod.getMethod();
        System.out.print("*****MethodInvoking: " + method.getName() + "*****" + "\n");
        }

    
    public void afterInvocation(IInvokedMethod iim, ITestResult itr) {
        //Do Nothing
    }

}
