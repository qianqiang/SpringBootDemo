package test.com.dika.rest; 

import com.dika.rest.DemoHandler;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* DemoHandler Tester. 
* 
* @author qianqiang 
* @since <pre>01/28/2019</pre> 
* @version 1.0 
*/ 
public class DemoHandlerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: test123(@ApiParam(name="PARAM", value="hello xx", required=true)@RequestParam(value = "PARAM", required = false) String param) 
* 
*/ 
@Test
public void testTest123() throws Exception {
    String str = "hello world";
    DemoHandler demo = new DemoHandler();
    demo.test123(str);
} 

/** 
* 
* Method: testdemo() 
* 
*/ 
@Test
public void testTestdemo() throws Exception { 
//TODO: Test goes here... 
} 


} 
