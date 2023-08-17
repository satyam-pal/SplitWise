package com.satyam.splitwise.split;

import com.satyam.splitwise.expenses.ExpenseModel;
import com.satyam.splitwise.expenses.SplitType;
import com.satyam.splitwise.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "splits")
@Getter
@Setter
public class SplitModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount")
    Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    ExpenseModel expense;

}
