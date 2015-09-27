Project name:  WalmartFrontent


Question: First question (Frontend)
Automate an end-to-end user e-commerce transaction flow using any open source tool for www.walmart.com with an existing customer on Chrome or Safari browser.


Scenario to automate:
1. Login using existing account
2. Perform a search on home page from a pool of key words given below
3. Identify an item from the result set that you can add to cart
4. Add the item to cart
5. Validate that item added is present in the cart and is the only item in the cart


Test Data:
• Account details: 
	username: wishhappyforever@gmail.com
	password: happy1234
• Search term: tv


Testing tools and Programming language to be utilized: 
Java as main language, selenium as web driver tool, chromedriver as driver


Project Configure Instructions:
1. Install Java JDK 
2. Install Eclipse IDE
3. Download Selenium WebDriver Jar Files, choose the Java 2.47.1 version: http://docs.seleniumhq.org/download/ 
4. Download Chromedriver https://sites.google.com/a/chromium.org/chromedriver/downloads
5. Start Eclipse -> import the project
6. Add selenium web driver’s jar file which you just downloaded into java build path of the project:
Right click on project 'WalmartFrontent' > Select Properties > Select Java build path > Navigate to Libraries tab
Click on add external JARs button > select both .jar files from \selenium-2.47.1
Click on add external JARs button > select all .jar files from \selenium-2.47.1\libs
7. Change the chrome driver path to the local path in your computer:
In java class Frontend.java, change String CHROMEDRIVERPATH value to the local path address where the Chromedriver you stored in. 


How to run the test case:
Previous step: Manually log into the account (wishhappyforever@gmail.com, happy1234), empty the cart first, ensure there is no item saved.
Run test: right click Frontend.java class -> Run test -> Click JUnit test


In the code:
1. Setting private static Strings as instance variables for any driver, elements and Strings, in order to create clean code, avoid hardcode and easy to change.
2. @Before: set property for chrome driver to point to the right location. Create webdriver and webdriverwait before the test case run
3. @After: Quit and clean the driver
4. @Test: Go through all the scenarios in the question with enough comments. Create wrapper methods (open url, sign in, search item..) for re-usability to extends to more test cases, as well as leave a clean code flow.


Problem/Solution with trade-offs:
1. Why choose the tools? Java easy to control Selenium and have JUnit package for assertion. Selenium contains great web driver and browser automation APIs, accessed by many platforms and OS. 
2. In the test case, there is one kind of scenario that clicking a button and jumping from 1st to 2nd page and validate/click next elements. So it needs to wait for the 2nd page loaded finishing before next steps. I implement this by WebDriverWait driverWait = new WebDriverWait(driver, 30); . In this way, it has 30 sec for page loading which is long enough. However, if page jumping failed, it will not fail at once, but wait fully 30 sec, which will affect performance especially when running multiple such test cases.
3. In the test case, it needs to identify an item title and add to cart as well as validate in cart. The search term I am using is TV, which has long name like “SCEPTRE X322BV-MQC 32" LED Class 720P HDTV with ultra slim metal brush bezel, 60Hz”. I just give a short title here like “SCEPTRE X322BV-MQC 32" LED”. This is quick for checking and easy for coding. But probably not exactly correct when searching item. Can change to use the xpath or link here.


Problems may happen:
1. Test case fails at validating number of items: Should manually empty cart first before testing
2. Test case fails at adding item to cart. Due to web server very slow, or session issue. Try again later.


What else I want to add if there is more time:
1. Create Empty Cart function added in @After part. So it will automatically empty the cart after test cases executed, and don’t need to do it manually and easy to run multiple times.
2. Add more steps to resolve any page failing issues. In the process of automating I have noticed that sometimes when adding item into cart, the operation will take long time or even fail. I think can handle this by e.x. implementing try/catch sections to deal with any possible issues that encountered, and print out exception trace for better investigating the defect point.
