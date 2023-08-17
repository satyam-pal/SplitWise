package com.satyam.splitwise.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import com.satyam.splitwise.expenses.ExpenseModel;
import com.satyam.splitwise.split.SplitModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Set;

@Getter
@Setter
@Table(name = "users")
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @NonNull
    Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NonNull
    String username;

    @Column(name = "password", nullable = false)
    @NonNull
    String password;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    String email;

    @Column(name = "name", nullable = false)
    @NonNull
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_expense_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name  = "expense_group_id"))
    Set<ExpenseGroupModel> expenseGroups;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
    Set<ExpenseModel> createdExpenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paidBy")
    Set<ExpenseModel> paidExpenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    Set<SplitModel> sharedExpenses;

}
