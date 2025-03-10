package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.Address;
import edu.iff.project.sustainableconnection.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {

        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {

        return addressRepository.findById(id);
    }

    public Address save(String street, String city, String state, String zipCode) {

        Address address = new Address(street, city, state, zipCode);
        return addressRepository.save(address);
    }

    public Optional<Address> update(Long id, String street, String city, String state, String zipCode) {

        Optional<Address> existingAddress = addressRepository.findById(id);

        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setZipCode(zipCode);
            return Optional.of(addressRepository.save(address));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {

        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        
        return false;
    }
}
