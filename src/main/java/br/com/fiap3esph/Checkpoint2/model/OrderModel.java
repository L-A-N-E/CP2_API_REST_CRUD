package br.com.fiap3esph.Checkpoint2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "Pedidos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome do Cliente Obrigatório!")
    private String clientName;

    private LocalDate orderDate;

    @DecimalMin(value = "0.0", message = "Valor total não pode ser negativo!")
    @Positive
    private BigDecimal totalValue;

    @PrePersist
    public void prePersist() {
        if (this.orderDate == null) {
            this.orderDate = LocalDate.now();
        }
    }
}
