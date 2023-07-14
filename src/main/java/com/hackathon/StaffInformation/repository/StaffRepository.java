package com.hackathon.StaffInformation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.hackathon.StaffInformation.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    @Query(value = "SELECT * FROM STAFF s LIMIT :limit OFFSET :offset", nativeQuery = true)
    Iterable<Staff> listStaffByIndices(Long offset, Long limit);
    
    @Query(value = "SELECT * from STAFF s where staff_name LIKE :staffName and email_identifier LIKE :emailIdentifier LIMIT :limit OFFSET :offset", nativeQuery = true)
    Iterable<Staff> listStaffByBothFilters(Long offset, Long limit, String staffName, String emailIdentifier);

    @Query(value = "Select * from STAFF s where staff_name LIKE :filter or email_identifier LIKE :filter LIMIT :limit OFFSET :offset", nativeQuery = true)
    Iterable<Staff> listStaffByOneFilter(Long offset, Long limit, String filter); 

    @Query(value = "Select count(*) from STAFF s", nativeQuery = true)
    Long listStaffCount();

    @Query(value = "Select count(*) from STAFF s where staff_name LIKE :staffName and email_identifier LIKE :emailIdentifier LIMIT :limit OFFSET :offset", nativeQuery = true)
    Long listStaffCountFilteredBoth(String staffName, String emailIdentifier);

    @Query(value = "Select count(*) from STAFF s where staff_name LIKE :filter or email_identifier LIKE :filter LIMIT :limit OFFSET :offset", nativeQuery = true)
    Long listStaffCountFilteredOne(String filter); 


}