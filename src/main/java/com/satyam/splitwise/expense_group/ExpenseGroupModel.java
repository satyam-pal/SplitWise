package com.satyam.splitwise.expense_group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.satyam.splitwise.expenses.ExpenseModel;
import com.satyam.splitwise.user.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Table(name = "expense_groups")
@Entity
public class ExpenseGroupModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date createdAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "expenseGroups")
    Set<UserModel> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expenseGroup")
    Set<ExpenseModel> expenseModels;

}
