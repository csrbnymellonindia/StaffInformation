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

import com.hackathon.StaffInformation.exception.AuthException;
import com.hackathon.StaffInformation.exception.StaffNotFoundException;
import com.hackathon.StaffInformation.model.Staff;
import com.hackathon.StaffInformation.service.StaffService;
import static com.hackathon.StaffInformation.controller.AuthController.currentRoleId;

@RestController("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StaffController {
    private StaffService staffService;

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * It returns the list of Staff Entries from the database according to the filters, offset and limit provided.
     * @param offset The row number from which we require the list of entries.
     * @param limit The number of staff entries we require from the offset.
     * @param staffName Filter on the basis of Staff Name
     * @param emailIdentifier Filter on the basis of Email Id.
     * @return List<Staff> which is the list of staff entries we queried for.
     * @throws AuthException
     */
    @GetMapping("/staff")
    public ResponseEntity<Object> getStaffEntries(@RequestParam(required = false) Long offset, @RequestParam(required = false) Long limit, @RequestParam(required = false) String staffName, @RequestParam(required = false) String emailIdentifier) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        if(staffName != null && emailIdentifier != null) return new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByBoth(offset, limit, staffName, emailIdentifier), HttpStatus.OK);
        else if(staffName != null || emailIdentifier != null) return staffName != null ? new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByOne(offset, limit, staffName), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getFilteredStaffEntriesByOne(offset, limit, emailIdentifier), HttpStatus.OK);
        else return offset != null && limit != null ? new ResponseEntity<Object>(staffService.getStaffEntriesByIndex(offset, limit), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getStaffEntries(), HttpStatus.OK);
    }

    /**
     * It gets the count of list of staff entries from the database.
     * @param staffName Filter on the basis of Staff Name
     * @param emailIdentifier Filter on the basis of Email Id.
     * @return A ResponseEntity<Integer> which is the JSON object of the count of list of staff entries obtained after applying given filters. If no filter is applies, count of all staff is returned.
     * @throws AuthException
     */
    @GetMapping("/staff/count")
    public ResponseEntity<Object> getStaffEntriesCount(@RequestParam(required = false) String staffName, @RequestParam(required = false) String emailIdentifier) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        if(staffName != null && emailIdentifier != null)
        {
            return new ResponseEntity<Object>(staffService.getCountFilteredStaffEntriesByBoth(staffName, emailIdentifier), HttpStatus.OK);
        }
        else if(staffName != null || emailIdentifier != null) return staffName != null ? new ResponseEntity<Object>(staffService.getCountFilteredEntriesByOne(staffName), HttpStatus.OK) : new ResponseEntity<Object>(staffService.getCountFilteredEntriesByOne(emailIdentifier), HttpStatus.OK);
        else return new ResponseEntity<Object>(staffService.getCountOfStaffEntries(), HttpStatus.OK);
    }
    /**
     * It find the staff entry from the database corresponding to the given staff id.
     * @param staffId The number by which a stafff entry is uniquely identified.
     * @return A ResponseEntity<> which is the JSON object of the staff entry corresponding to the given Staff Id.
     * @throws AuthException
     */
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<Staff> getStaffEntryById(@PathVariable("staffId") Long staffId) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        if(!staffService.findStaffEntry(staffId).isPresent())
        {
            throw new StaffNotFoundException(HttpStatus.NOT_FOUND, "Staff with this Id does not exist!");
        }
        return new ResponseEntity<Staff>(staffService.findStaffEntry(staffId).get(), HttpStatus.OK);
    } 

    /**
     * It takes the new staff entry as the body, saves it into the database and returns the new entry.
     * @param newStaffEntry Staff Object which contains the information about the new staff entry which is to be added to the database.
     * @return Staff Object of the new staff entry after adding it to the database.
     * @throws AuthException
     */
    @PostMapping("/staff")
    public ResponseEntity<Staff> addStaffEntry(@RequestBody Staff newStaffEntry) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        return new ResponseEntity<Staff>(staffService.createStaffEntry(newStaffEntry), HttpStatus.OK);
    }

    /**
     * It takes the Staff Object of the updated staff entry, and the corresponding staffId, and updates the entry in the database.
     * @param updatedStaffEntry Staff Object of the updated the staff entry.
     * @param staffId Staff Id corresponding to the updated staff entry.
     * @throws AuthException
     */
    @PutMapping("/staff/{staffId}")
    public void updateStaffEntry(@RequestBody Staff updatedStaffEntry, @PathVariable("staffId") Long staffId) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        staffService.updateStaffEntry(updatedStaffEntry, staffId);
    }

    /**
     * It takes the staff Id of the staff entry which needs to be deleted, deletes it from the database.
     * @param staffId Staff Id corresponding to the staff entry which needs to be deleted.
     * @throws AuthException
     */
    @DeleteMapping("/staff/{staffId}")
    public void deleteStaffEntry(@PathVariable("staffId") Long staffId) throws AuthException
    {
        if(currentRoleId == null) throw new AuthException("Please login to perform this operation!");
        staffService.deleteStaffEntry(staffId);
    }

}
