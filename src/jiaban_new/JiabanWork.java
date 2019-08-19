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
 * 2019��4��15��
 * ��������ӦĿǰ��OA������Ӱ����̲�����Ŀ�����(������OA��ҳ�и��½����̵㿪������ĳ���)��ֶ�����Ū�������OK�ˣ�
 * ����ԭ���Ĳ�����Ҳ���ԴӾɰ���룬̫�����ˣ�������ÿ��������ң����ٸ�
 * �������Ļ���һ�㶼����ҳû�ڹ涨ʱ���ڼ�����ɣ�
 * �����ʾchromedrive�����⣬�Ǿ����������������ˣ����Ҳ��֮�ı䣬ȥ���غ���chrome�汾��Ӧ�ļ���
 */

public class JiabanWork {
	private static Selenium selenium;
	int x = 0;
	static WebDriver driver =new ChromeDriver();
    // frame����������ѡ���iframe��          frameH������ʱ��ѡ���iframe
    WebElement frame=driver.findElement(By.xpath("//*[@id=\"_my97DP\"]/iframe"));
    WebElement frameH=driver.findElement(By.xpath("//*[@id=\"meizzDateLayer2\"]"));
    
    
    public void jiabantime(String starthour,String startmin,String endhour,String endmin) {
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
		selenium.select("id=hour", "label="+starthour);		// ѡ��ʼʱ��Сʱ
		selenium.click("id=hour");
		selenium.click("//td[@onclick='getTime(minute"+startmin+")']");	// ѡ��ʼʱ�����
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
		selenium.select("id=hour", "label="+endhour);		// ѡ�����ʱ��Сʱ
		selenium.click("id=hour");
		selenium.click("//td[@onclick='getTime(minute"+endmin+")']"); 	// ѡ�����ʱ�����
		driver.switchTo().parentFrame();
		x++;
	}
    
	
	public static void main(String[] args) throws InterruptedException {

		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		driver.manage().window().maximize();			// ��������
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 		// ���õȴ�ʱ�� 8S�� ���8s��û���ҵ���Ӧ��Ԫ�ػᱨ��
		selenium.open("http://10.1.32.21:8082/login/Login.jsp?logintype=1");	// ����OA
		selenium.type("id=loginid", "�ĳ��Լ���");			// �����û���
		selenium.type("id=userpassword", "�ĳ��Լ���!");		// ��������
		selenium.submit("id=login");						// �����¼��ť
		
		//************************************* �������OA��ҳ��  ****************************************************************//
		
		selenium.click("id=tz");
		selenium.click("//*[@id=\"wfcentercontent\"]/ul/li[1]/a");  // ��xpath��λ��ֻҪHTML�ṹ���仯�������޸Ĳ�Ӱ��

		//************************************* �������Ӱ������  *************************************//
		Set<String> winHandels = driver.getWindowHandles();     // �õ���ǰ���ڵ�set����
	    List<String> it = new ArrayList<String>(winHandels);    // ��set���ϴ���list����
	    driver.switchTo().window(it.get(1));                    // �л����������´���
	    Thread.sleep(7000);       								// !!!!!!!!!!!!!!!!!!! ����һ��Ҫ�ȴ�ʱ�䣬����ȶ�ÿ�������ٺ͵���
	    selenium.selectFrame("index=2");

	    // �Ӱ����ɣ������޸�
		selenium.type("id=field46107", "�鿴����");
		
	    //************************************* ���濪ʼ��Ӱ���  *************************************//
		// ��Ӱ�һ��һ���ᣬ�ֱ��Ӧÿ��ʱ�ζ�����ѭ���������û�Ҫ�޸ĵ��Ƕ�Ӧʱ�εļӰ�����

		int x78 = 0;	// 7:30 - 8:30
		int x7 = 0;		// 18:00 - 19:00
		int x75 = 1;	// 18:00 - 19:30
		int x8 = 0;		// 18:00 - 20:00
		int x85 = 0;	// 18:00 - 20:30
		int x9 = 0;		// 18:00 - 21:00
		int x95 = 0;	// 18:00 - 21:30
		int x10 = 0;	// 18:00 - 22:00

		//************************************* �����Ǹ���ʱ��ε�ѭ��  *************************************//
		
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
