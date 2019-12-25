package jiaban;


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
 * 2019��4��15��
 * ��������ӦĿǰ��OA������Ӱ����̲�����Ŀ�����(������OA��ҳ�и��½����̵㿪������ĳ���)��ֶ�����Ū�������OK�ˣ�
 * ����ԭ���Ĳ�����Ҳ���ԴӾɰ���룬̫�����ˣ�������ÿ��������ң����ٸ�
 */

public class Jiaban_zhoumo {
	private static Selenium selenium;
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		driver.manage().window().maximize();			// ��������
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 		// ���õȴ�ʱ�� 8S�� ���8s��û���ҵ���Ӧ��Ԫ�ػᱨ��
		selenium.open("http://10.1.32.21:8082/login/Login.jsp?logintype=1");	// ����OA
		selenium.type("id=loginid", "wanglei03");			// �����û���
		selenium.type("id=userpassword", "WANGlei!");		// ��������
		selenium.submit("id=login");						// �����¼��ť
		
		//************************************* �������OA��ҳ��   *************************************//
		selenium.click("id=tz");
		selenium.click("link=HR-012-A-�Ӱ���������-���Ա��-K-VXG");
		
		//************************************* �������Ӱ������  *************************************//
		Set<String> winHandels = driver.getWindowHandles();     // �õ���ǰ���ڵ�set����
	    List<String> it = new ArrayList<String>(winHandels);    // ��set���ϴ���list����
	    driver.switchTo().window(it.get(1));                    // �л����������´���
	    Thread.sleep(7000);       								// !!!!!!!!!!!!!!!!!!! ����һ��Ҫ�ȴ�ʱ�䣬����ȶ�ÿ�������ٺ͵���
	    selenium.selectFrame("index=2");
	    // frame����������ѡ���iframe��          frameH������ʱ��ѡ���iframe
	    WebElement frame=driver.findElement(By.xpath("//*[@id=\"_my97DP\"]/iframe"));
	    WebElement frameH=driver.findElement(By.xpath("//*[@id=\"meizzDateLayer2\"]"));
	    // �Ӱ����ɣ������޸�
		selenium.type("id=field46107", "ֵ���Ӧ�쳣��INDEX PM����������");
		
	    //************************************* ���濪ʼ��Ӱ���  *************************************//
		// ��Ӱ�һ��һ���ᣬ�ֱ��Ӧÿ��ʱ�ζ�����ѭ���������û�Ҫ�޸ĵ��Ƕ�Ӧʱ�εļӰ�����
		int x = 0;
		int x817 = 0;	// 8:00 - 17:30
		int x85 = 0;	// 8:30 - 17:30
		int x821 = 5;	// 8:30 - 21:00
		int x18 = 0;	// 18:00 - 21:00  ��ĩ�װ����

		// ��������ᵱ��ģ�Ҫ���������춼���������䣬td[15]����14�ţ�ʵ���֪���������ּ�һ�죬������Ҫѡ�������
		// selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='��'])[1]/following::td[15]");
		
		//************************************* �����Ǹ���ʱ��ε�ѭ��  *************************************//
		// 8:00 - 17:30
		for(int i=0;i<x817;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// ��������
			String str1 = String.format("field46125_%sbrowser", x);		// Ԥ�ƿ�ʼ����
			String str2 = String.format("field46127_%sbrowser", x);		// Ԥ�ƿ�ʼʱ��
			String str3 = String.format("field46126_%sbrowser", x);		// Ԥ�ƽ�������
			String str4 = String.format("field46128_%sbrowser", x);		// Ԥ�ƽ���ʱ��
			selenium.click("id=$addbutton0$");			// ��Ӱ�ť
			// ��������
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// 19/5/10 ����Ҫѡ����ת���µ��ݻ�����ȵ���
			selenium.click("id=field46113_"+x);
			selenium.select("id=field46113_"+x, "label=ת���µ���");
			// Ԥ�ƿ�ʼ����
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƿ�ʼʱ��
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=08");		// ѡ�� 08 ��
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// ѡ�� 8.00
			driver.switchTo().parentFrame();
			// Ԥ�ƽ�������
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƽ���ʱ��
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=17");		// ѡ�� 17 ��
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 	// ѡ�� 17 ��30
			driver.switchTo().parentFrame();
			x++;
		}
		// 8:30 - 17:30
		for(int i=0;i<x85;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// ��������
			String str1 = String.format("field46125_%sbrowser", x);		// Ԥ�ƿ�ʼ����
			String str2 = String.format("field46127_%sbrowser", x);		// Ԥ�ƿ�ʼʱ��
			String str3 = String.format("field46126_%sbrowser", x);		// Ԥ�ƽ�������
			String str4 = String.format("field46128_%sbrowser", x);		// Ԥ�ƽ���ʱ��
			selenium.click("id=$addbutton0$");			// ��Ӱ�ť
			// ��������
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// 19/5/10 ����Ҫѡ����ת���µ��ݻ�����ȵ���
			selenium.click("id=field46113_"+x);
			selenium.select("id=field46113_"+x, "label=ת���µ���");
			// Ԥ�ƿ�ʼ����
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƿ�ʼʱ��
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=08");	
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']");
			driver.switchTo().parentFrame();
			// Ԥ�ƽ�������
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƽ���ʱ��
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=17");	
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']"); 
			driver.switchTo().parentFrame();
			x++;
		}
		// 8:30 - 21:00
		for(int i=0;i<x821;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// ��������
			String str1 = String.format("field46125_%sbrowser", x);		// Ԥ�ƿ�ʼ����
			String str2 = String.format("field46127_%sbrowser", x);		// Ԥ�ƿ�ʼʱ��
			String str3 = String.format("field46126_%sbrowser", x);		// Ԥ�ƽ�������
			String str4 = String.format("field46128_%sbrowser", x);		// Ԥ�ƽ���ʱ��
			selenium.click("id=$addbutton0$");			// ��Ӱ�ť
			// ��������
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// 19/5/10 ����Ҫѡ����ת���µ��ݻ�����ȵ���
			selenium.click("id=field46113_"+x);
			selenium.select("id=field46113_"+x, "label=ת���µ���");
			// Ԥ�ƿ�ʼ����
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƿ�ʼʱ��
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=08");		
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute30)']");	
			driver.switchTo().parentFrame();
			// Ԥ�ƽ�������
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƽ���ʱ��
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=21");		
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	
			driver.switchTo().parentFrame();
			x++;
		}
		// 18:00 - 21:00
		for(int i=0;i<x18;i++) {
			String str0 = String.format("field46112_%sbrowser", x);		// ��������
			String str1 = String.format("field46125_%sbrowser", x);		// Ԥ�ƿ�ʼ����
			String str2 = String.format("field46127_%sbrowser", x);		// Ԥ�ƿ�ʼʱ��
			String str3 = String.format("field46126_%sbrowser", x);		// Ԥ�ƽ�������
			String str4 = String.format("field46128_%sbrowser", x);		// Ԥ�ƽ���ʱ��
			selenium.click("id=$addbutton0$");			// ��Ӱ�ť
			// ��������
		    selenium.click("id="+str0);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// 19/5/10 ����Ҫѡ����ת���µ��ݻ�����ȵ���
			selenium.click("id=field46113_"+x);
			selenium.select("id=field46113_"+x, "label=ת���µ���");
			// Ԥ�ƿ�ʼ����
			selenium.click("id="+str1);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƿ�ʼʱ��
			selenium.click("id="+str2);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=18");		// ѡ�� 18 ��
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']");	// ѡ�� 18 ��00
			driver.switchTo().parentFrame();
			// Ԥ�ƽ�������
			selenium.click("id="+str3);
		    driver.switchTo().frame(frame);
			selenium.click("id=dpTodayInput");			// ����
			driver.switchTo().parentFrame();
			// Ԥ�ƽ���ʱ��
			selenium.click("id="+str4);
			driver.switchTo().frame(frameH);
			selenium.click("id=hour");
			selenium.select("id=hour", "label=21");		// ѡ�� 21 ��
			selenium.click("id=hour");
			selenium.click("//td[@onclick='getTime(minute0)']"); 	// ѡ�� 21 ��00
			driver.switchTo().parentFrame();
			x++;
		}			
	}
}
