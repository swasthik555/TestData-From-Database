package dpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException {

		// Connect to MySQL Database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");

		// Get the data from employees table of demo database (3rd record)
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from logincredentials;");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

		while (resultSet.next()) {

			driver.findElement(By.id("input-email")).sendKeys(resultSet.getString("username"));
			driver.findElement(By.id("input-password")).sendKeys(resultSet.getString("passowrd"));

		}

		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

	}

}
