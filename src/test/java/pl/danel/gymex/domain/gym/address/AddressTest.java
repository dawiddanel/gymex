package pl.danel.gymex.domain.gym.address;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.command.CreateAddress;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressTest {

    @Test
    public void testCreateAddress() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .build();

        //when
        Address address = Address.create(command);

        //then
        assertEquals(address.getCountry().getValue(), command.getCountry());
        assertEquals(address.getCity().getValue(), command.getCity());
        assertEquals(address.getStreet().getValue(), command.getStreet());
        assertEquals(address.getPostalCode().getValue(), command.getPostalCode());
        assertEquals(address.getBuildingNumber().getValue(), command.getBuildingNumber());
    }

    @Test
    public void testCreateAddressPostalCodeNull() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .postalCode(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("Postal code is null", exception.getMessage());
    }

    @Test
    public void testCreateAddressPostalCodeError() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .postalCode("123")
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("Postal code is malmorfed", exception.getMessage());
    }

    @Test
    public void testCreateAddressCountryNull() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .country(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("Country is null", exception.getMessage());
    }

    @Test
    public void testCreateAddressCityNull() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .city(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("City is null", exception.getMessage());
    }

    @Test
    public void testCreateAddressStreetNull() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .street(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("Street is null", exception.getMessage());
    }

    @Test
    public void testCreateAddressBuildingNumberNull() {
        //given
        CreateAddress command = Fixtures.createAddress()
                .buildingNumber(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Address.create(command);
        });

        //then
        assertEquals("Building number is null", exception.getMessage());
    }

}
