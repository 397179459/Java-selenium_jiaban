package jiaban;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Jiaban {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver =new ChromeDriver();
		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void testJiaban() throws Exception {
		selenium.open("http://10.1.32.21:8082/login/Login.jsp?logintype=1");
		selenium.type("id=loginid", "wanglei03");
		selenium.type("id=userpassword", "WANGlei!");
		selenium.submit("id=form1");
		Thread.sleep(3000);
		selenium.click("id=tz");
		Thread.sleep(3000);
		selenium.click("link=（新）HR-012-A-加班申请流程-间接员工-K-VXG");
		Thread.sleep(6000);
//		selenium.selectWindow("win_ser_2");
//		Thread.sleep(3000);
		selenium.selectFrame("index=2");
//		Thread.sleep(3000);
		
		
		
		selenium.click("id=$addbutton0$");
		selenium.click("id=$addbutton0$");
		selenium.click("id=$addbutton0$");
//		selenium.click("id=field46112_0browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46112_1browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46112_2browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46125_0browser");
//		selenium.selectFrame("index=9");
//		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='六'])[1]/following::td[15]");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46125_1browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46125_2browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46126_0browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46126_1browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46126_2browser");
//		selenium.selectFrame("index=9");
//		selenium.click("id=dpTodayInput");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46127_0browser");
//		selenium.selectFrame("index=7");
//		selenium.click("id=hour");
//		selenium.select("id=hour", "label=18");
//		selenium.click("id=hour");
//		selenium.click("//td[@onclick='getTime(minute0)']");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46127_1browser");
//		selenium.selectFrame("index=7");
//		selenium.click("//td[@onclick='getTime(minute0)']");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46127_2browser");
//		selenium.selectFrame("index=7");
//		selenium.click("//td[@onclick='getTime(minute0)']");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46128_0browser");
//		selenium.selectFrame("index=7");
//		selenium.click("id=hour");
//		selenium.select("id=hour", "label=20");
//		selenium.click("id=hour");
//		selenium.click("//td[@onclick='getTime(minute0)']");
//		selenium.selectFrame("relative=parent");
//		selenium.click("id=field46128_1browser");
//		selenium.selectFrame("index=7");
//		selenium.click("//td[@onclick='getTime(minute0)']");
//		selenium.selectFrame("relative=parent");
//		selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='周末加班转支付'])[3]/following::td[4]");
//		selenium.click("id=field46128_2browser");
//		selenium.selectFrame("index=7");
//		selenium.click("id=hour");
//		selenium.select("id=hour", "label=21");
//		selenium.click("id=hour");
//		selenium.click("//td[@onclick='getTime(minute0)']");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
