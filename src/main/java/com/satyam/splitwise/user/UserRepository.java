package com.satyam.splitwise.user;

import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByUsername(String username);

    @Query("Select u from UserModel u where u.email in ?1")
    Set<UserModel> findByUsersByEmails(List<String> emails);

    @Query("SELECT u.expenseGroups FROM UserModel u WHERE u.id = ?1")
    Set<ExpenseGroupModel> findWithGroupsByUserId(Long id);


}
