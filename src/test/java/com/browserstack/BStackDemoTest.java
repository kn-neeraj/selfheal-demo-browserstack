package com.browserstack;

import com.browserstack.SeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class BStackDemoTest extends SeleniumTest {

    @Test
    public void testDemoScenarios() throws Exception {
        // Navigate to Browserstack Selfheal Demo Website
        driver.get("https://browserstack.github.io/selfheal-demo-app");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("browserstack-selfheal-demo"));

        // Click on "Try Demo Scenarios" button
        driver.findElement(By.id("cta-button")).click();
        Thread.sleep(2000);

        // Id Attribute Scenario
        driver.findElement(By.id("static-id-field"))
                .sendKeys("This is a test for static id field");

        // Xpath Scenario
        driver.findElement(By.xpath("//div[@id='xpath-form']/input"))
                .sendKeys("This is a test for xpath field");

        // Content Description Change Scenario Button
        driver.findElement(By.xpath("//button[@title='Submit']")).click();
        Thread.sleep(1000);

        // Text Change Scenario Button
        driver.findElement(By.xpath("//button[text()='Proceed']")).click();
        Thread.sleep(1000);

        // Class Name Change Scenario for Toggle Button
        driver.findElement(By.className("feature-toggle")).click();

        // Id change Scenario for progress bar
        Assert.assertEquals(driver.findElement(By.id("progress-status-id")).getText(), "Status: In Progress");
        driver.findElement(By.id("progress-btn-100")).click();
        Assert.assertEquals(driver.findElement(By.id("progress-status-id")).getText(), "Status: Complete");
        Thread.sleep(2000);
        
        driver.quit();
    }

    @Test
    public void testUserFlow() throws Exception {
        // Navigate to Browserstack Selfheal Demo Website
        driver.get("https://browserstack.github.io/selfheal-demo-app");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("browserstack-selfheal-demo"));

        // Click on "Profile" button
        driver.findElement(By.id("profile-btn")).click();

        // Login Form: Select any user from the dropdown
        driver.findElement(By.xpath("//*[@id='user-select']/option[2]")).click();

        // Click on Sign In button
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(2000);

        // Select few products
        WebElement card1 = driver.findElement(By.id("product-card-1"));
        // Relative xpath to find the "Add to Cart" button inside the card1
        WebElement addToCart1Button = card1.findElement(By.xpath(".//button[@title='Add to Cart']"));
        addToCart1Button.click();
        Thread.sleep(1000);

        WebElement card2 = driver.findElement(By.id("product-card-2"));
        WebElement addToCart2Button = card2.findElement(By.cssSelector("#add-to-cart-2"));
        addToCart2Button.click();
        Thread.sleep(1000);

        driver.findElement(By.id("add-to-cart-3")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#add-to-cart-9")).click();
        Thread.sleep(1000);

        // Navigate to Cart
        driver.findElement(By.id("shopping-cart-btn")).click();

        // Check number of items in cart before checkout
        List<WebElement> cartItems = driver.findElements(By.id("cart-item"));
        Assert.assertTrue(cartItems.size() == 4, "Cart should 4 items before checkout");

        // Checkout flow
        driver.findElement(By.id("checkout-btn")).click();
        driver.findElement(By.id("place-order-btn")).click();
        Thread.sleep(2000);

        // Order History: Find the first order (most recent)
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        WebElement firstOrder = driver.findElement(By.cssSelector("li.order-list-item"));
        String orderId = firstOrder.getAttribute("data-order-id");

        // Click the correct "View Invoice" button
        WebElement viewInvoiceBtn = driver.findElement(By.id("view-invoice-btn-" + orderId));
        Thread.sleep(500);
        viewInvoiceBtn.click();
        Thread.sleep(2000);

        driver.quit();
    }
}
