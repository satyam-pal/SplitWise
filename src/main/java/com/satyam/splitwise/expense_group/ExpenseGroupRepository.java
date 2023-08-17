package com.satyam.splitwise.expense_group;

import com.satyam.splitwise.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroupModel,Long> {



}
