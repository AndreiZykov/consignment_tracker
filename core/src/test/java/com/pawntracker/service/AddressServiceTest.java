package com.pawntracker.service;

import com.pawntracker.entity.Address;
import com.pawntracker.repository.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.sun.tools.doclint.Entity.times;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @MockBean
    AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    Address address = new Address();

    @Before
    public void setUp() {
        address.setId(1L);
    }


    @Test
    public void saveOrUpdate() {
        when(addressRepository.save(address)).thenReturn(address);
        Address address1 = addressService.saveOrUpdate(address);
        assertEquals(address, address1);

    }

    @Test
    public void getAddressById() {
        when(addressRepository.getAddressById(address.getId())).thenReturn(address);
        Address address1 = addressService.getAddressById(address.getId());
        assertEquals(address, address1);
    }

    @Test
    public void deleteAddress() {
        when(addressRepository.getAddressById(1L)).thenReturn(address);

        addressService.deleteAddress(address);

        verify(addressRepository, times(1)).delete(address);
    }
}