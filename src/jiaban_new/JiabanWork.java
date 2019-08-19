package jiaban_new;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * @author wanglei03
 * 2019年4月15日
 * 本代码适应目前的OA，如果加班流程不在你的快捷入口(就是你OA主页有个新建流程点开就是你的常用)里，手动把它弄到那里就OK了，
 * 按照原来的操作，也可以从旧版进入，太繁琐了，有需求得可以来找我，我再改
 * 如果报错的话，一般都是网页没在规定时间内加载完成，
 * 如果提示chromedrive有问题，那就是你的浏览器更新了，插件也随之改变，去下载和你chrome版本对应的即可
 */

public class JiabanWork {
	private static Selenium selenium;
	int x = 0;
	static WebDriver driver =new ChromeDriver();
    // frame是所有日期选择的iframe；          frameH是所有时间选择的iframe
    WebElement frame=driver.findElement(By.xpath("//*[@id=\"_my97DP\"]/iframe"));
    WebElement frameH=driver.findElement(By.xpath("//*[@id=\"meizzDateLayer2\"]"));
    
    
    public void jiabantime(String starthour,String startmin,String endhour,String endmin) {
		String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
		String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
		String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
		String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
		String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
		selenium.click("id=$addbutton0$");			// 添加按钮
		// 归属日期
	    selenium.click("id="+str0);
	    driver.switchTo().frame(frame);
		selenium.click("id=dpTodayInput");			// 今天
		driver.switchTo().parentFrame();
		// 19/5/10 新增要选择是转当月调休还是年度调休
		selenium.click("id=field46113_"+x);
		selenium.select("id=field46113_"+x, "label=转当月调休");
		
		// 预计开始日期
		selenium.click("id="+str1);
	    driver.switchTo().frame(frame);
		selenium.click("id=dpTodayInput");			// 今天
		driver.switchTo().parentFrame();
		// 预计开始时间
		selenium.click("id="+str2);
		driver.switchTo().frame(frameH);
		selenium.click("id=hour");
		selenium.select("id=hour", "label="+starthour);		// 选择开始时间小时
		selenium.click("id=hour");
		selenium.click("//td[@onclick='getTime(minute"+startmin+")']");	// 选择开始时间分钟
		driver.switchTo().parentFrame();
		// 预计结束日期
		selenium.click("id="+str3);
	    driver.switchTo().frame(frame);
		selenium.click("id=dpTodayInput");			// 今天
		driver.switchTo().parentFrame();
		// 预计结束时间
		selenium.click("id="+str4);
		driver.switchTo().frame(frameH);
		selenium.click("id=hour");
		selenium.select("id=hour", "label="+endhour);		// 选择结束时间小时
		selenium.click("id=hour");
		selenium.click("//td[@onclick='getTime(minute"+endmin+")']"); 	// 选择结束时间分钟
		driver.switchTo().parentFrame();
		x++;
	}
    
	
	public static void main(String[] args) throws InterruptedException {

		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		driver.manage().window().maximize();			// 浏览器最大化
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 		// 设置等待时间 8S， 如果8s内没有找到相应的元素会报错
		selenium.open("http://10.1.32.21:8082/login/Login.jsp?logintype=1");	// 进入OA
		selenium.type("id=loginid", "改成自己的");			// 输入用户名
		selenium.type("id=userpassword", "改成自己的!");		// 输入密码
		selenium.submit("id=login");						// 点击登录按钮
		
		//************************************* 下面进入OA主页了  ****************************************************************//
		
		selenium.click("id=tz");
		selenium.click("//*[@id=\"wfcentercontent\"]/ul/li[1]/a");  // 用xpath定位，只要HTML结构不变化，文字修改不影响

		//************************************* 下面进入加班界面了  *************************************//
		Set<String> winHandels = driver.getWindowHandles();     // 得到当前窗口的set集合
	    List<String> it = new ArrayList<String>(winHandels);    // 将set集合存入list对象
	    driver.switchTo().window(it.get(1));                    // 切换到弹出的新窗口
	    Thread.sleep(7000);       								// !!!!!!!!!!!!!!!!!!! 这里一定要等待时间，具体等多久看你的网速和电脑
	    selenium.selectFrame("index=2");

	    // 加班事由，自行修改
		selenium.type("id=field46107", "查看资料");
		
	    //************************************* 下面开始提加班了  *************************************//
		// 提加班一条一条提，分别对应每个时段都设置循环次数，用户要修改的是对应时段的加班人数

		int x78 = 0;	// 7:30 - 8:30
		int x7 = 0;		// 18:00 - 19:00
		int x75 = 1;	// 18:00 - 19:30
		int x8 = 0;		// 18:00 - 20:00
		int x85 = 0;	// 18:00 - 20:30
		int x9 = 0;		// 18:00 - 21:00
		int x95 = 0;	// 18:00 - 21:30
		int x10 = 0;	// 18:00 - 22:00

		//************************************* 以下是各个时间段的循环  *************************************//
		
	    JiabanWork jiabanWork = new JiabanWork();
		
		// 7:30 - 8:30
		for(int i=0;i<x78;i++) {
			jiabanWork.jiabantime("07", "30", "08", "30");
		}
		// 18:00 - 19:00
		for(int i=0;i<x7;i++) {
			jiabanWork.jiabantime("18", "0", "19", "0");
		}
		// 18:00 - 19:30
		for(int i=0;i<x75;i++) {
			jiabanWork.jiabantime("18", "0", "19", "30");
		}
		// 18:00 - 20:00
		for(int i=0;i<x8;i++) {
			jiabanWork.jiabantime("18", "0", "20", "0");
		}
		// 18:00 - 20:30
		for(int i=0;i<x85;i++) {
			jiabanWork.jiabantime("18", "0", "20", "30");
		}
		// 18:00 - 21:00
		for(int i=0;i<x9;i++) {
			jiabanWork.jiabantime("18", "0", "21", "0");
		}
		// 18:00 - 21:30
		for(int i=0;i<x95;i++) {
			jiabanWork.jiabantime("18", "0", "21", "30");
		}
		// 18:00 - 22:00
		for(int i=0;i<x10;i++) {
			jiabanWork.jiabantime("18", "0", "22", "0");
		}		
	}
}
