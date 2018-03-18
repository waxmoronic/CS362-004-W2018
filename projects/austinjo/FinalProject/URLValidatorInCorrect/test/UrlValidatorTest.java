

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
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   System.out.println("Manual testing: Schema");
	   System.out.println("---Should be true---");	   
	   System.out.println("http:// --> " + (urlVal.isValid("http://www.apache.com")));
	   // bug 1 crashes anything but http, commented out lines affected
	   // System.out.println("ftp:// --> " + (urlVal.isValid("ftp://www.amazon.com")));
	   
	   System.out.println("\n---Should be false---");
	   System.out.println("(blank) --> " + (urlVal.isValid("www.apache.com")));
	// bug 1 crashes anything but http, commented out lines affected
	   // System.out.println("htp:// --> " + (urlVal.isValid("htp://www.apache.com")));
	   
	   System.out.println("\nManual testing: Authority");
	   System.out.println("---Should be true---");	   
	   System.out.println("http://www.apache.com --> " + (urlVal.isValid("http://www.apache.com")));
	   System.out.println("http://apache.com --> " + (urlVal.isValid("http://apache.com")));
	   System.out.println("http://0.0.0.0 --> " + (urlVal.isValid("http://0.0.0.0")));
	   System.out.println("http://255.255.255.255 --> " + (urlVal.isValid("http://255.255.255.255")));
	   System.out.println("http://255.com --> " + (urlVal.isValid("http://255.com")));
	   
	   System.out.println("\n---Should be false---");
	   System.out.println("http://www.apache.com. --> " + (urlVal.isValid("http://www.apache.com.")));
	   System.out.println("http://http://www.apache.com --> " + (urlVal.isValid("http://http://www.apache.com")));
	   
	   // bug here, out of range, UrlValidator.java line 318 
	   System.out.println("http://.1.2.3.4 --> " + (urlVal.isValid("http://.1.2.3.4")));
	   System.out.println("http://255.255.255.256 --> " + (urlVal.isValid("http://255.255.255.256")));
	   //
	   
	   System.out.println("http://255.255.255.255.255 --> " + (urlVal.isValid("http://255.255.255.255.255")));
	   System.out.println("apache --> " + (urlVal.isValid("apache")));
	   
	   System.out.println("\nManual testing: Port");
	   System.out.println("---Should be true---");	   
	   System.out.println("http://www.apache.com:80 --> " + (urlVal.isValid("http://www.apache.com:80")));
	   System.out.println("http://www.apache.com:65535 --> " + (urlVal.isValid("http://www.apache.com:65535")));
	   
	   
	   System.out.println("\n---Should be false---");
	   System.out.println("http://www.apache.com:65536 --> " + (urlVal.isValid("http://www.apache.com:65536")));
	   System.out.println("http://www.apache.com:-1 --> " + (urlVal.isValid("http://www.apache.com:-1")));
	   System.out.println("http://www.apache.com:80c --> " + (urlVal.isValid("http://www.apache.com:80c")));
	   System.out.println("http://www.apache.com: --> " + (urlVal.isValid("http://www.apache.com:")));
	   
	   System.out.println("\nManual testing: Path");
	   System.out.println("---Should be true---");
	   System.out.println("http://www.apache.com/path --> " + (urlVal.isValid("http://www.apache.com/path")));
	   System.out.println("http://www.apache.com/path/ --> " + (urlVal.isValid("http://www.apache.com/path/")));
	   System.out.println("http://www.apache.com/path/path2 --> " + (urlVal.isValid("http://www.apache.com/path/path2")));
	   System.out.println("http://www.apache.com/$path --> " + (urlVal.isValid("http://www.apache.com/$path")));
	   
	   System.out.println("\n---Should be false---");
	   System.out.println("http://www.apache.com/.. --> " + (urlVal.isValid("http://www.apache.com/..")));
	   System.out.println("http://www.apache.com//path --> " + (urlVal.isValid("http://www.apache.com//path")));
	   
	   System.out.println("\nManual testing: Query");
	   System.out.println("---Should be true---");
	   System.out.println("http://www.apache.com? --> " + (urlVal.isValid("http://www.apache.com?")));
	   System.out.println("http://www.apache.com?true --> " + (urlVal.isValid("http://www.apache.com?true")));
	   System.out.println("http://www.apache.com?true=0 --> " + (urlVal.isValid("http://www.apache.com?true=0")));
	   System.out.println("http://www.apache.com?true=0&false=1 --> " + (urlVal.isValid("http://www.apache.com?true=0&false=1")));
	   
	   System.out.println("\n---Should be false---");
	   System.out.println("http://www.apache.com?(space)  --> " + (urlVal.isValid("http://www.apache.com? ")));
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println("Testing First Partition - Url Scheme");
	   
	   System.out.println("Testing Scheme http:// - appended to www.google.com");
	   if(urlVal.isValid("http://www.google.com")) {
		   System.out.println("Valid Scheme.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   System.out.println("Testing Scheme http/ - appended to www.google.com");
	   if(!urlVal.isValid("http/www.google.com")) {
		   System.out.println("Invalid Scheme");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   System.out.println("Testing Scheme :// - appended to www.google.com");
	   if(!urlVal.isValid("://www.google.com")) {
		   System.out.println("Invald Scheme");
	   }
	   else {
		   System.out.println("Bug found.");
	   }

	   // bug 1 crashes anything but http, commented out lines affected
	   /*	   System.out.println("Testing Scheme ftp:// - appended to www.google.com");
	   if(urlVal.isValid("ftp://www.google.com")) {
		   System.out.println("Valid Scheme");
	   }
	   else {
		   System.out.println("Bug found.");
	   }*/
	   
	   System.out.println("Testing Scheme 3ht:// - appended to www.google.com");
	   if(!urlVal.isValid("3ht://www.google.com")) {
		   System.out.println("Invalid Scheme.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   System.out.println("Testing Scheme http: - appended to www.google.com");
	   if(!urlVal.isValid("http:www.google.com")){
		   	System.out.println("Invalid Scheme");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	 
	   System.out.println("Testing Second Partition - Url Authority");
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   if(urlVal.isValid("www.google.com")) {
		   System.out.println("www.google.com - Valid Authority.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(urlVal.isValid("go.com")) {
		   System.out.println("go.com - Valid Authority.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid(".1.2.3.4.")) {
		   System.out.println(".1.2.3.4 - Invalid Authority.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("go.1aa")) {
		   System.out.println("go.1aa - Invalid Authority.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("aaa")) {
		   System.out.println("aaa - Invalid Authority.");
	   }
   }
   //You need to create more test cases for your Partitions if you need to 
   public void testYourThirdPartition() {
	   System.out.println("Testing Third Partition - Url Port");
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   if(urlVal.isValid("www.google.com:80")) {
		   System.out.println("Port :80 - Valid Port.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(urlVal.isValid("www.google.com:65535")) {
		   System.out.println("Port :65535 - Valid Port.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(urlVal.isValid("www.google.com:0")) {
		   System.out.println("Port :0 - Valid Port.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("www.google.com:-1")) {
		   System.out.println("Port :-1 Invalid Port.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("www.google.com:65a")) {
		   System.out.println("Port :65a - Invalid Port.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   
   }
   
   public void testYourFourthPartition() {
	   System.out.println("Testing Fourth Partition - Url Path");
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   if(urlVal.isValid("www.google.com/test1")) {
		   System.out.println("Path /test1 - Valid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(urlVal.isValid("www.google.com/$23")) {
		   System.out.println("Path /$23 - Valid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("www.google.com/..")) {
		   System.out.println("Path /.. - Invalid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("www.google.com/../")){
		   System.out.println("Path /../ - Invalid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(urlVal.isValid("www.google.com/test/file")) {
		   System.out.println("Path /test/file - Valid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
	   if(!urlVal.isValid("www.google.com/..//file")) {
		   System.out.println("Path /..//file - Invalid Path.");
	   }
	   else {
		   System.out.println("Bug found.");
	   }
   } 
   
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
