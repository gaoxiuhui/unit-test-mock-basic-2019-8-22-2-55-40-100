package cashregister;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashRegisterTest {
    private static MockPrinter  mockPrinter;
    private static  CashRegister cashRegister;
    
    @BeforeAll
    private  static void setup() {
    	 mockPrinter=new MockPrinter();  
    	 cashRegister=new CashRegister(mockPrinter);
    }
    @BeforeEach
    private void cleanPrint() {
    	mockPrinter.setTempText("");
    }
    @Test
    public void should_print_the_real_purchase_when_call_process() {
        //given
    	Item[] items= {new Item("egg", 1)};
    	Purchase purchase=new Purchase(items);   	
        //when
    	cashRegister.process(purchase);
        //then
    	assertEquals("egg\t1.0\n",mockPrinter.getTempText());
    	
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        //given
    	StubPurchase stubPurchase=new StubPurchase();
        //when
    	cashRegister.process(stubPurchase);
        //then
    	assertEquals("egg\t1.0\n",mockPrinter.getTempText());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        //given
    	Printer printer=Mockito.mock(Printer.class);
    	Purchase  purchase =Mockito.mock(Purchase.class);
    	Mockito.when(purchase.asString()).thenReturn("egg\t1.0\n");
        //when
    	CashRegister cashRegister=new CashRegister(printer);
    	cashRegister.process(purchase);
        //then
    	Mockito.verify(printer).print("egg\t1.0\n");
    	
    }

}
