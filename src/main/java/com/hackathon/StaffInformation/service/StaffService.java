package com.hackathon.StaffInformation.service;

import java.util.List;
import java.util.Optional;

import com.hackathon.StaffInformation.model.Staff;

public interface StaffService {
  List<Staff> getStaffEntries();
  Long getCountOfStaffEntries();
  Iterable<Staff> getFilteredStaffEntriesByBoth(Long offset, Long limit, String staffName, String emailIdentifier);
  Long getCountFilteredStaffEntriesByBoth(String staffName, String emailIdentifier);
  Iterable<Staff> getFilteredStaffEntriesByOne(Long offset, Long limit, String filter);
  Long getCountFilteredEntriesByOne(String filter);
  Iterable<Staff> getStaffEntriesByIndex(Long offset, Long limit);
  Optional<Staff> findStaffEntry(Long staffId);
  Staff createStaffEntry(Staff newStaffEntry);
  void updateStaffEntry(Staff updatedStaffEntry, Long staffId);
  void deleteStaffEntry(Long staffEntry);
}
