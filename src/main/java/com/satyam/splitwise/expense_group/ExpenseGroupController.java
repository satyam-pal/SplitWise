package com.satyam.splitwise.expense_group;

import com.satyam.splitwise.expense_group.dtos.CreateGroupDto;
import com.satyam.splitwise.expense_group.dtos.GroupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class ExpenseGroupController {

    ExpenseGroupService expenseGroupService;

    public ExpenseGroupController(ExpenseGroupService expenseGroupService) {
        this.expenseGroupService = expenseGroupService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupDto>> getAllGroupsByUserId(@PathVariable("userId") Long userId){
        List<GroupDto> groupList = expenseGroupService.getAllGroupsByUserId(userId);
        return ResponseEntity.ok(groupList);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity createGroup(@PathVariable("userId") Long id, @RequestBody CreateGroupDto group){
        GroupDto createdGroup = expenseGroupService.createNewGroup(id, group);
        return ResponseEntity.ok(createdGroup);
    }

}
