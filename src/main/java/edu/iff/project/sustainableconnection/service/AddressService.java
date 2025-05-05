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

    public Address save(String street, String city, String state, String zipCode, Double latitude, Double longitude) {

        Address address = new Address(street, city, state, zipCode, latitude, longitude);
        return addressRepository.save(address);
    }

    public Address update(Long id, String street, String city, String state, String zipCode, Double latitude,
                      Double longitude) 
                      {

                        Address address = addressRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Endereço não encontrado."));

                        address.setStreet(street);
                        address.setCity(city);
                        address.setState(state);
                        address.setZipCode(zipCode);
                        address.setLatitude(latitude);
                        address.setLongitude(longitude);

        return addressRepository.save(address);
    }

    public boolean delete(Long id) {

        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        
        return false;
    }
}
