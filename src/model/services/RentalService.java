package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

// Classe responsável por realizar o serviço de aluguel do carro.
public class RentalService {

    private Double pricePerHour;
    private Double pricePerDay;

    private TaxService taxService; // taxService = servicoFiscal.

    public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {
        // A variável minutes recebe a diferença de tempo entre getStart() e getFinish() convertida em minutos.
        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        // A variável hours recebe o valor de minutes / 60, irá retornar a hora em fração.
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            // basicPayment recebe o valor de hours arredondado para cima vezes (*) o pricePerHour.
            basicPayment = pricePerHour * Math.ceil(hours);
        }
        else {
            // basicPayment recebe o valor de hours dividido por 24, esse resultado arredondado para cima, vezes (*) o pricePerDay.
            basicPayment = pricePerDay * Math.ceil(hours / 24.0);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}