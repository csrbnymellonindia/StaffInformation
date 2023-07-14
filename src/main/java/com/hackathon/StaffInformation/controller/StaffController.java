package com.hackathon.StaffInformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hackathon.StaffInformation.exception.StaffNotFoundException;
import com.hackathon.StaffInformation.model.Staff;
import com.hackathon.StaffInformation.service.StaffService;

@RestController("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StaffController {
    private StaffService staffService;

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff")
    public ResponseEntity<Object> getStaffEntries(@RequestParam(required = false) Long offset, @RequestParam(required = false) Long limit, @RequestParam(required = false) String staffName, @RequestParam(required = false) String emailIdentifier)
    {
        if(staffName != null && emailIdentifier != null) return new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByBoth(offset, limit, staffName, emailIdentifier), HttpStatus.OK);
        else if(staffName != null || emailIdentifier != null) return staffName != null ? new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByOne(offset, limit, staffName), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByOne(offset, limit, emailIdentifier), HttpStatus.OK);
        else return offset != null && limit != null ? new ResponseEntity<Object>(staffService.getStaffEntriesByIndex(offset, limit), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getStaffEntries(), HttpStatus.OK);
    }
    @GetMapping("/staff/count")
    public ResponseEntity<Object> getStaffEntriesCount(@RequestParam(required = false) String staffName, @RequestParam(required = false) String emailIdentifier)
    {
        if(staffName != null && emailIdentifier != null)
        {
            return new ResponseEntity<Object>(staffService.getCountFilteredStaffEntriesByBoth(staffName, emailIdentifier), HttpStatus.OK);
        }
        else if(staffName != null || emailIdentifier != null) return staffName != null ? new ResponseEntity<Object>(staffService.getCountFilteredEntriesByOne(staffName), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getCountFilteredEntriesByOne(emailIdentifier), HttpStatus.OK);
        else return new ResponseEntity<Object>(staffService.getCountOfStaffEntries(), HttpStatus.OK);
    }
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<Staff> getStaffEntryById(@PathVariable("staffId") Long staffId)
    {
        if(!staffService.findStaffEntry(staffId).isPresent())
        {
            throw new StaffNotFoundException(HttpStatus.NOT_FOUND, "Staff with this Id does not exist!");
        }
        return new ResponseEntity<Staff>(staffService.findStaffEntry(staffId).get(), HttpStatus.OK);
    } 

    @PostMapping("/staff")
    public ResponseEntity<Staff> addStaffEntry(@RequestBody Staff newStaffEntry)
    {
        return new ResponseEntity<Staff>(staffService.createStaffEntry(newStaffEntry), HttpStatus.OK);
    }

    @PutMapping("/staff/{staffId}")
    public void updateStaffEntry(@RequestBody Staff updatedStaffEntry, @PathVariable("staffId") Long staffId)
    {
        staffService.updateStaffEntry(updatedStaffEntry, staffId);
    }

    @DeleteMapping("/staff/{staffId}")
    public void deleteStaffEntry(@PathVariable("staffId") Long staffId)
    {
        staffService.deleteStaffEntry(staffId);
    }

}
