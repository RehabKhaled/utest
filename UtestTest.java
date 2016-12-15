
package tasksw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)

public class UtestTest {
    DAOImpl newDAOobj=new DAOImpl();
     Product newpro;
    @Mock
    Connection conn;
    
    @Mock
  
     PreparedStatement psmt;
    @InjectMocks
    DAOImpl newDAO=new DAOImpl();
    @Test
    public void testproductconestructor(){
   
    Product p= new Product(5);
    Assert.assertEquals(5 ,p.getId());
   
    }
@Test
public void testsetType(){
    Product p=new Product(1);
    p.setType("kinder");
    Assert.assertEquals("kinder", p.getType());
    
}
 @Test
public void testsetManufacturer(){
    Product p=new Product(1);
    p.setManufacturer("twarza");
    Assert.assertEquals("twarza", p.getManufacturer());
    
}
 
    
   @Test
public void testsetProductionDate(){
    Product p=new Product(1);
    p.setProductionDate("2/2/2016");
    Assert.assertEquals("2/2/2016", p.getProductionDate());
    
}
   
    
   @Test
public void testsettExpiryDate(){
    Product p=new Product(1);
    p.setExpiryDate("2/2/2017");
    Assert.assertEquals("2/2/2017", p.getExpiryDate());
    
}  
  
    
  

@Test
public void HappyTest() throws SQLException, DAOException{
	when(conn.prepareStatement(anyString())).thenReturn(psmt);
        
	ArgumentCaptor<Integer> intcaptor = ArgumentCaptor.forClass(int.class);
	ArgumentCaptor<String> stringcaptor = ArgumentCaptor.forClass(String.class);

        Product p = new Product(9);
	newDAO.insertProduct(p);
       verify(psmt, times(1)).setInt(anyInt(), intcaptor.capture());

        verify(psmt, times(4)).setString(anyInt(), stringcaptor.capture());
        Assert.assertTrue(intcaptor.getAllValues().get(0).equals(9));
}


@Test (expected = DAOException.class)
 
public void ExceptionCase() throws SQLException, DAOException{
	when(conn.prepareStatement(anyString())).thenReturn(psmt);
	when(psmt.executeUpdate()).thenThrow(new SQLException());
	Product p = new Product(5);
	newDAO.insertProduct(p); 
}

@Test

public void integrationTestdelete() throws SQLException, DAOException{
  newpro= new Product(2);
  newpro.setType("kinder");
  newpro.setManufacturer("twarza");
  newpro.setProductionDate("2/2/2016");
  newpro.setExpiryDate("2/2/2017");
  newDAOobj.insertProduct(newpro);
  Assert.assertNotNull( newDAOobj.getProduct(2));
  newDAOobj.deleteProduct(2);
  Assert.assertNull( newDAOobj.getProduct(2));
}


}


 