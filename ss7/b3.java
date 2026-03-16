import java.util.*;

interface PaymentMethodB3 {
    void processPayment(double amount);
}

interface CODPayableB3 {
    void processCOD(double amount);
}

interface CardPayableB3 {
    void processCreditCard(double amount);
}

interface EWalletPayableB3 {
    void processMomo(double amount);
}

class CODPaymentB3 implements PaymentMethodB3, CODPayableB3 {
    @Override
    public void processPayment(double amount) {
        processCOD(amount);
    }

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (long)amount + " - Thành công");
    }
}

class CreditCardPaymentB3 implements PaymentMethodB3, CardPayableB3 {
    @Override
    public void processPayment(double amount) {
        processCreditCard(amount);
    }

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (long)amount + " - Thành công");
    }
}

class MomoPaymentB3 implements PaymentMethodB3, EWalletPayableB3 {
    @Override
    public void processPayment(double amount) {
        processMomo(amount);
    }

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (long)amount + " - Thành công");
    }
}

class PaymentProcessorB3 {
    public void execute(PaymentMethodB3 method, double amount) {
        method.processPayment(amount);
    }
}

public class b3 {
    public static void main(String[] args) {
        PaymentProcessorB3 processor = new PaymentProcessorB3();

        PaymentMethodB3 cod = new CODPaymentB3();
        processor.execute(cod, 500000);

        PaymentMethodB3 card = new CreditCardPaymentB3();
        processor.execute(card, 1000000);

        PaymentMethodB3 momo = new MomoPaymentB3();
        processor.execute(momo, 750000);

        System.out.println("\nKiểm tra LSP:");
        PaymentMethodB3 testLSP = new CreditCardPaymentB3();
        processor.execute(testLSP, 200000);

        testLSP = new MomoPaymentB3();
        processor.execute(testLSP, 200000);
    }
}