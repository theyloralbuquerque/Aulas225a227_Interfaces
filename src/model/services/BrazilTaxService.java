package model.services;

// Classe responsável por calcular a porcentagem do imposto cobrado.
public class BrazilTaxService implements TaxService{

    public double tax(double amount) { // tax = imposto.
        if (amount <= 100.0) {
            return amount * 0.2;
        }
        else {
            return amount * 0.15;
        }
    }
}