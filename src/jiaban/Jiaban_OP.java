package jiaban;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * @author wanglei03
 * 2019年4月15日
 * 本代码适应目前的OA，如果加班流程不在你的快捷入口(就是你OA主页有个新建流程点开就是你的常用)里，手动把它弄到那里就OK了，
 * 按照原来的操作，也可以从旧版进入，太繁琐了，有需求得可以来找我，我再改
 */

public class Jiaban_OP {
	private static Selenium selenium;
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		driver.manage().window().maximize();			// 浏览器最大化
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 		// 设置等待时间 8S， 如果8s内没有找到相应的元素会报错
		selenium.open("http://10.1.32.21:8082/login/Login.jsp?logintype=1");	// 进入OA
		selenium.type("id=loginid", "wanglei03");			// 输入用户名
		selenium.type("id=userpassword", "WANGlei!");		// 输入密码
		selenium.submit("id=form1");						// 点击登录按钮
		
		//************************************* 下面进入OA主页了  ****************************************************************//
		selenium.click("id=tz");
		selenium.click("link=（新）HR-012-A-加班申请流程-间接员工-K-VXG");
		
		//************************************* 下面进入加班界面了  *************************************//
		Set<String> winHandels = driver.getWindowHandles();     // 得到当前窗口的set集合
	    List<String> it = new ArrayList<String>(winHandels);    // 将set集合存入list对象
	    driver.switchTo().window(it.get(1));                    // 切换到弹出的新窗口
	    Thread.sleep(7000);       								// !!!!!!!!!!!!!!!!!!! 这里一定要等待时间，具体等多久看你的网速和电脑
	    selenium.selectFrame("index=2");
	    // frame是所有日期选择的iframe；          frameH是所有时间选择的iframe
	    WebElement frame=driver.findElement(By.xpath("//*[@id=\"_my97DP\"]/iframe"));
	    WebElement frameH=driver.findElement(By.xpath("//*[@id=\"meizzDateLayer2\"]"));
	    // 加班事由，自行修改
		selenium.type("id=field46107", "值班对应异常，INDEX PM，沉降测试");
		
	    //************************************* 下面开始提加班了  *************************************//
		// 提加班一条一条提，分别对应每个时段都设置循环次数，用户要修改的是对应时段的加班人数
		int x = 0;		// 这是我设置的循环计数，不用修改
		int x78 = 0;	// 7:30 - 8:30
		int x7 = 0;		// 18:00 - 19:00
		int x75 = 0;	// 18:00 - 19:30
		int x8 = 0;		// 18:00 - 20:00
		int x85 = 0;	// 18:00 - 20:30
		int x9 = 8;		// 18:00 - 21:00
		int x95 = 0;	// 18:00 - 21:30
		int x10 = 0;	// 18:00 - 22:00
		
		// ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
		int date = 18;  
		// 这里的日期是另一种需求，针对第二天来提前一天加班的人， 17 代表 16号，18代表17号，日期就是数字减一(错的)
		// 19/5/10 上面的计算是错的，我觉得应该是系统把每个月按照30天写的代码，所以每个月都不一样，要自己试一次才能知道是差几天

		// 如果不是提当天的，要把日期那天都换成这个语句，td[15]就是14号，实验得知好像是数字减一天，就是你要选择的日期
		// selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
		
		//************************************* 以下是各个时间段的循环  *************************************//
		
		//我是才开始学Java，按照模块化的思路，下面的循环体应该是可以构造一个func，然后每个时段只需要调用方法传参即可，等我学差不多了再回来修改
	    
		// 7:30 - 8:30
		for(int i=0;i<x78;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=07");		// 选择 07 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']");	// 选择 07 ：30
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=08");		// 选择 08 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 	// 选择 08 ：30
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 19:00
		for(int i=0;i<x7;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=19");		// 选择 19 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	// 选择 19 ：00
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 19:30
		for(int i=0;i<x75;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=19");		// 选择 19 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 	// 选择 19 ：30
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 20:00
		for(int i=0;i<x8;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=20");		// 选择 20 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	// 选择 20 ：00
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 20:30
		for(int i=0;i<x85;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=20");		// 选择 20 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 	// 选择 20 ：30
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 21:00
		for(int i=0;i<x9;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=21");		// 选择 21 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	// 选择 21 ：00
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 21:30
		for(int i=0;i<x95;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=21");		// 选择 21 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 	// 选择 21 ：30
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 22:00
		for(int i=0;i<x10;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// 归属日期
			String str1 = String.format("field46125_%sbrowser", x);		// 预计开始日期
			String str2 = String.format("field46127_%sbrowser", x);		// 预计开始时间
			String str3 = String.format("field46126_%sbrowser", x);		// 预计结束日期
			String str4 = String.format("field46128_%sbrowser", x);		// 预计结束时间
			selenium.click("id=$addbutton0$");			// 添加按钮
			// 归属日期
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始日期
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计开始时间
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// 选择 18 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// 选择 18 ：00
			driver.switchTo().parentFrame();
			// 预计结束日期
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
//			selenium.click("id=dpTodayInput");			// 今天
		    selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[" + date + "]");
			driver.switchTo().parentFrame();
			// 预计结束时间
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=22");		// 选择 22 点
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	// 选择 22 ：00
			driver.switchTo().parentFrame();
			x++;
		}		
	}
}