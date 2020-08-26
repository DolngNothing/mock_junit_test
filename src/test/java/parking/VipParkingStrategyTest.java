package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {
    @Mock
    Car car;
	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        Car car = mock(Car.class);

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Mockito.doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);
        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);

        verify(vipParkingStrategy).createReceipt(parkingLot,car);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        Car car =new Car("name");

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);

        verify(vipParkingStrategy).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(car.getName()).thenReturn("AVIP");

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);
        verify(vipParkingStrategy).isAllowOverPark(car);
        assertTrue(vipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(car.getName()).thenReturn("VIP");

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);
        verify(vipParkingStrategy).isAllowOverPark(car);
        assertFalse(vipParkingStrategy.isAllowOverPark(car));

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(car.getName()).thenReturn("Acccc");

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);
        verify(vipParkingStrategy).isAllowOverPark(car);
        assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(car.getName()).thenReturn("ccc");

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());

        ParkingLot parkingLot=spy(new ParkingLot("666",666));
        Mockito.doReturn(true).when(parkingLot).isFull();
        vipParkingStrategy.park(Collections.singletonList(parkingLot),car);
        verify(vipParkingStrategy).isAllowOverPark(car);
        assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
