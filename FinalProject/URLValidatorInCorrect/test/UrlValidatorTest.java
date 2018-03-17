

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
  

   /* Name : testIsValid() 
    * Descript : Break down each url fragment, combine with other valid fragements and 
    * call testIsValid(url)
    * Note : Bug is found in scheme test that prevent other tests from running. 
    * Comment out scheme segement to run other tests. 
    */
   public void testIsValid()
   {
	  
	   //You can use this function for programming based testing
	   System.out.println("---Starting Programming Based Testing---");
	   
	   // Create new url validator 
	   UrlValidator urlVal = new UrlValidator(null,null, UrlValidator.ALLOW_ALL_SCHEMES); 
	   
	   // Init Variables
	   int testPassed = 0;
	   
	   // Start Scheme Test 
	    System.out.println("Starting Scheme...");
	    
	   // Create Result Pairs for scheme
	   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
               new ResultPair("ftp://", true),
               new ResultPair("h3t://", true),
               new ResultPair("3ht://", false),
               new ResultPair("http:/", false),
               new ResultPair("http:", false),
               new ResultPair("http/", false),
               new ResultPair("://", false),
               new ResultPair("!//", true)};
	   
	   // Create url fragment
	   String urlFrag = "www.google.com"; 
	   boolean result; 
	   
/*	  
	   // Check each scheme
	   for (int a = 0; a < 10; a++) {
		  String url = testUrlScheme[a].item + urlFrag;
		  
		  result = urlVal.isValid(url);
		  if(result) {
			  System.out.print("Passed URL --> ");
			  System.out.println(url); 
			  testPassed++; 
		  }else {
			  System.out.print("Failed URL --> ");
			  System.out.println(url);
		  }
		  
	  }
	   
	   // Print Test results
	   System.out.print("Scheme Test Passed: ");
	   System.out.print(testPassed);
*/


	   // Reset testPassed
	   testPassed = 0;

	   
	   // Start Authority Test
	   System.out.println("Starting Authority...");
	   
	   // Create Authority
	   ResultPair[] testUrlAuthority = {new ResultPair("www.google.com", true),
               new ResultPair("go.com", true),
               new ResultPair("go.au", true),
               new ResultPair("0.0.0.0", true),
               new ResultPair("255.255.255.255", true),
               new ResultPair("256.256.256.256", false),
               new ResultPair("255.com", true),
               new ResultPair("1.2.3.4.5", false),
               new ResultPair("1.2.3.4.", false),
               new ResultPair("1.2.3", false)};
	   
	   // CHeck each authority
	   for (int a = 0; a < 10; a++) {
		   String url = "http://" + testUrlAuthority[a].item; 
		   
		   result = urlVal.isValid(url);
		
		   if(result) {
				  System.out.println(url); 
				  testPassed++; 
			  }else {
				  System.out.print("Failed URL --> ");
				  System.out.println(url);
			  }
	   }

	   
	   // Print Test results
	   System.out.print("Authority Test Passed: ");
	   System.out.println(testPassed);
	   // Reset testPassed
	   testPassed = 0;
	   
	   
	   // Start Port Test
	   System.out.println("Starting Port...");
	   
	   // Create Port
	   ResultPair[] testUrlPort = {new ResultPair(":80", true),
               new ResultPair(":65535", true),
               new ResultPair(":0", true),
               new ResultPair("", true),
               new ResultPair(":-1", false),
              new ResultPair(":65636",false),
               new ResultPair(":65a", false),
               new ResultPair(":sda", false),
               new ResultPair(":6@a", false),
               new ResultPair(":$#", false)};
	   
	   for (int a = 0; a < 10; a++) {
		   String url = "http://google.com" + testUrlPort[a].item; 
		   
		   result = urlVal.isValid(url);
		
		   if(result) {
				  System.out.println(url); 
				  testPassed++; 
			  }else {
				  System.out.print("Failed URL --> ");
				  System.out.println(url);
			  }
	   }

	   // Print Test results
	   System.out.print("Port Test Passed: ");
	   System.out.println(testPassed);
	   // Reset testPassed
	   testPassed = 0;
	   
	   // Start Path Test
	   System.out.println("Starting Path...");
	   // Create Path 
	   ResultPair[] testPath = {new ResultPair("/test1", true),
               new ResultPair("/t123", true),
               new ResultPair("/$23", true),
               new ResultPair("/..", false),
               new ResultPair("/../", false),
               new ResultPair("/test1/", true),
               new ResultPair("", true),
               new ResultPair("/test1/file", true),
               new ResultPair("/..//file", false),
               new ResultPair("/test1//file", false)};
	   
	   for (int a = 0; a < 10; a++) {
		   String url = "http://google.com" + testPath[a].item; 
		   
		   result = urlVal.isValid(url);
		
		   if(result) {
				  System.out.println(url); 
				  testPassed++; 
			  }else {
				  System.out.print("Failed URL --> ");
				  System.out.println(url);
			  }
	   }

	   // Print Test results
	   System.out.print("Path Test Passed: ");
	   System.out.println(testPassed);
	   // Reset testPassed
	   testPassed = 0;
	  
	   
	   // Start Query Test
	   System.out.println("Starting Query...");

	   ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
               new ResultPair("?action=edit&mode=up", true),
               new ResultPair("", true)};
	   
	   for (int a = 0; a < 3; a++) {
		   String url = "http://google.com/test" + testUrlQuery[a].item; 
		   
		   result = urlVal.isValid(url);
		
		   if(result) {
				  System.out.println(url); 
				  testPassed++; 
			  }else {
				  System.out.print("Failed URL --> ");
				  System.out.println(url);
			  }
	   }
	   
	   // Print Test results
	   System.out.print("Query Test Passed: ");
	   System.out.println(testPassed);
	   // Reset testPassed
	   testPassed = 0;

   }
   


}
