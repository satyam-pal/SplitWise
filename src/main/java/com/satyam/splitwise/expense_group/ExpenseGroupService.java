package com.satyam.splitwise.expense_group;


import com.satyam.splitwise.expense_group.dtos.CreateGroupDto;
import com.satyam.splitwise.expense_group.dtos.GroupDto;
import com.satyam.splitwise.expense_group.exceptions.ExpenseGroupNotFoundException;
import com.satyam.splitwise.user.UserModel;
import com.satyam.splitwise.user.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseGroupService {

    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public ExpenseGroupService(ExpenseGroupRepository expenseGroupRepository, UserService userService, ModelMapper modelMapper) {
        this.expenseGroupRepository = expenseGroupRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<GroupDto> getAllGroupsByUserId(Long userId) {
        UserModel userModel = userService.findUserById(userId);
        List<ExpenseGroupModel> groupList = userService.findGroupsByUser(userModel.getId()).stream().toList();

        List<GroupDto> groupDtos = groupList.stream()
                .map(entity -> modelMapper.map(entity, GroupDto.class))
                .collect(Collectors.toList());
        return groupDtos;
    }

    public ExpenseGroupModel findById(Long id){
        Optional<ExpenseGroupModel> expenseGroup = expenseGroupRepository.findById(id);
        if(expenseGroup.isEmpty()){
            throw new ExpenseGroupNotFoundException("expense group with id: "+id+" not found");
        }
        return expenseGroup.get();
    }


    public ExpenseGroupModel addGroup(ExpenseGroupModel expenseGroupModel) {
        return expenseGroupRepository.save(expenseGroupModel);
    }

    @Transactional
    public GroupDto createNewGroup(Long id, CreateGroupDto group) {
        UserModel user = userService.findUserById(id);
        Set<UserModel> groupUsersList = userService.getUserListfromEmailList(group.getUserEmails());
        ExpenseGroupModel expenseGroupModel = new ExpenseGroupModel();
        expenseGroupModel.setName(group.getName());
        expenseGroupModel.setUsers(groupUsersList);
        ExpenseGroupModel createdGroup = expenseGroupRepository.save(expenseGroupModel);
        user.getExpenseGroups().add(expenseGroupModel);
        userService.updateUser(user);
        return modelMapper.map(createdGroup, GroupDto.class);
    }

    public ExpenseGroupModel getGroupById(Long expenseGroup) {
        Optional<ExpenseGroupModel> group = expenseGroupRepository.findById(expenseGroup);

        if (group.isEmpty()){
            throw new ExpenseGroupNotFoundException("Expense group with id: "+expenseGroup+" not found.");
        }
        return group.get();
    }
}
