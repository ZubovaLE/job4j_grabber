package ru.job4j.lsp.parking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.job4j.lsp.parking.car.Car;
import ru.job4j.lsp.parking.car.PassengerCar;
import ru.job4j.lsp.parking.car.Truck;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class CarAndTruckParkingTest {
    private final static PassengerCar passengerCar = new PassengerCar(1);
    private final static Truck truck = new Truck(2, 2);

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(1, 1, 2, Set.of(passengerCar, truck), 1, Set.of(passengerCar), 1, Set.of(truck)),
                Arguments.of(0, 1, 1, Set.of(truck), 0, Set.of(), 1, Set.of(truck)),
                Arguments.of(1, 0, 1, Set.of(passengerCar), 1, Set.of(passengerCar), 0, Set.of()),
                Arguments.of(3, 0, 2, Set.of(passengerCar, truck), 2, Set.of(passengerCar, truck), 0, Set.of()),
                Arguments.of(0, 0, 0, Set.of(), 0, Set.of(), 0, Set.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    @DisplayName("Test sendCarToParking with different conditions when add one passenger car and one truck")
    void sendCarToParking(int totalCarParkingSpaces, int totalTruckParkingSpaces,
                          int expectedParkingAmountOfCars, Set<Car> expectedCarsOnParking,
                          int expectedCarParkingAmountOfCars, Set<Car> expectedCarsOnCarParking,
                          int expectedTruckParkingAmountOfCars, Set<Car> ExpectedCarsOnTruckParking) {
        Parking carParking = new CarParking(totalCarParkingSpaces);
        Parking truckParking = new TruckParking(totalTruckParkingSpaces);
        CarAndTruckParking parking = new CarAndTruckParking(carParking, truckParking);
        parking.park(passengerCar);
        parking.park(truck);
        assertThat(parking.getCarsOnParking()).hasSize(expectedParkingAmountOfCars).containsAll(expectedCarsOnParking);
        assertThat(carParking.getCarsOnParking()).hasSize(expectedCarParkingAmountOfCars).containsAll(expectedCarsOnCarParking);
        assertThat(truckParking.getCarsOnParking()).hasSize(expectedTruckParkingAmountOfCars).containsAll(ExpectedCarsOnTruckParking);
    }
}