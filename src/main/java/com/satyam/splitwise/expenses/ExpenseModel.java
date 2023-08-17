package com.satyam.splitwise.expenses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import com.satyam.splitwise.split.SplitModel;
import com.satyam.splitwise.user.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class ExpenseModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount", nullable = false)
    Double amount;

    @Column(name = "description", nullable = false)
    String description;

    @ManyToOne
    @JoinColumn(name = "expense_group", referencedColumnName = "id", nullable = false)
    ExpenseGroupModel expenseGroup;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    UserModel createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paid_by", referencedColumnName = "id", nullable = false)
    UserModel paidBy;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    List<SplitModel> splits;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;
}
