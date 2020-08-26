package parking;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("parking lot");
        Receipt receipt = new InOrderParkingStrategy().createReceipt(parkingLot, car);
        assertEquals(car.getName(), receipt.getCarName());
        assertEquals(parkingLot.getName(), receipt.getParkingLotName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");
        Receipt receipt = new InOrderParkingStrategy().createNoSpaceReceipt(car);
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        inOrderParkingStrategy.park(Arrays.asList(), car);
        verify(inOrderParkingStrategy).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");
        ParkingLot parkingLot = spy(new ParkingLot("666", 666));
        Mockito.doReturn(false).when(parkingLot).isFull();

        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        inOrderParkingStrategy.park(parkingLotList, car);
        verify(inOrderParkingStrategy).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = Collections.singletonList(parkingLot);
        when(parkingLot.isFull()).thenReturn(true);
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        inOrderParkingStrategy.park(parkingLotList, car);
        verify(inOrderParkingStrategy).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("james");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot,parkingLot1);

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        inOrderParkingStrategy.park(parkingLotList, car);
        verify(inOrderParkingStrategy).createReceipt(parkingLot1,car);
    }


}
