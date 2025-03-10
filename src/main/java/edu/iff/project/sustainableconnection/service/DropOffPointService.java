package edu.iff.project.sustainableconnection.service;

import edu.iff.project.sustainableconnection.model.Address;
import edu.iff.project.sustainableconnection.model.DropOffPoint;
import edu.iff.project.sustainableconnection.repository.AddressRepository;
import edu.iff.project.sustainableconnection.repository.DropOffPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DropOffPointService {

    @Autowired
    private DropOffPointRepository dropOffPointRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<DropOffPoint> findAll() {

        return dropOffPointRepository.findAll();
    }

    public Optional<DropOffPoint> findById(Long id) {

        return dropOffPointRepository.findById(id);
    }

    public DropOffPoint save(String name, String description, Long addressId) {

        Optional<Address> addressOpt = addressRepository.findById(addressId);

        if (addressOpt.isEmpty()) {

            throw new RuntimeException("Address not found with id: " + addressId);
        }

        DropOffPoint dropOffPoint = new DropOffPoint(name, description, addressOpt.get());
        return dropOffPointRepository.save(dropOffPoint);
    }

    public DropOffPoint update(Long id, String name, String description, Long addressId) {

        if (!dropOffPointRepository.existsById(id)) {
            return null;
        }

        Optional<Address> addressOpt = addressRepository.findById(addressId);
        if (addressOpt.isEmpty()) {
            throw new RuntimeException("Address not found with id: " + addressId);
        }

        DropOffPoint dropOffPoint = new DropOffPoint(name, description, addressOpt.get());
        dropOffPoint.setId(id);
        return dropOffPointRepository.save(dropOffPoint);
    }

    public boolean delete(Long id) {

        if (dropOffPointRepository.existsById(id)) {
            dropOffPointRepository.deleteById(id);
            return true;
        }
        
        return false;
    }
}
