package com.hackathon.StaffInformation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hackathon.StaffInformation.exception.StaffNotFoundException;
import com.hackathon.StaffInformation.model.Staff;
import com.hackathon.StaffInformation.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getStaffEntries() {
        return (List<Staff>) staffRepository.findAll();
    }

    @Override 
    public Long getCountOfStaffEntries()
    {
        return staffRepository.listStaffCount();
    }

    @Override
    public Iterable<Staff> getStaffEntriesByIndex(Long offset, Long limit)
    {
        return staffRepository.listStaffByIndices(offset, offset);
    }
    @Override
    public Iterable<Staff> getFilteredStaffEntriesByBoth(Long offset, Long limit, String staffName, String emailIdentifier)
    {
        return staffRepository.listStaffByBothFilters(offset, limit, staffName, emailIdentifier);
    }

    @Override
    public Long getCountFilteredStaffEntriesByBoth(String staffName, String emailIdentifier)
    {
        return staffRepository.listStaffCountFilteredBoth(staffName, emailIdentifier);
    }

    @Override
    public Iterable<Staff> getFilteredStaffEntriesByOne(Long offset, Long limit, String filter)
    {
        return staffRepository.listStaffByOneFilter(offset, limit, filter);
    }

    @Override
    public Long getCountFilteredEntriesByOne(String filter)
    {
        return staffRepository.listStaffCountFilteredOne(filter);
     }

     @Override
     public Optional<Staff> findStaffEntry(Long staffId)
     {
        return staffRepository.findById(staffId);
     }

     @Override
     public Staff createStaffEntry(Staff newStaffEntry)
     {
        return staffRepository.save(newStaffEntry);
     }

     @Override
     public void updateStaffEntry(Staff updatedStaffEntry, Long staffId)
     {
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if(!optionalStaff.isPresent())
        {
            throw new StaffNotFoundException(HttpStatus.NOT_FOUND, "Staff with given id is not present in the database!");
        }
        staffRepository.save(updatedStaffEntry);
     }

    @Override
    public void deleteStaffEntry(Long staffId)
    {
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if(!optionalStaff.isPresent())
        {
            throw new StaffNotFoundException(HttpStatus.NOT_FOUND, "Staff with given id is not present in the database!");
        }
        staffRepository.delete(optionalStaff.get());
    }
}
