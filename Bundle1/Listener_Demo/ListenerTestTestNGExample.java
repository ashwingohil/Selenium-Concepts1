package Listener_Demo;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//When you write public class implement. Right click on the line and click Generate and select override methods.
//It will list all

public class  ListenerTestTestNGExample implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("The test case started is: "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("The name of the testcase success is: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("The name of the testcase failed is: "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("The name of the test case skipped is: "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
