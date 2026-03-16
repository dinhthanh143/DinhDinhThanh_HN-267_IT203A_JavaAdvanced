import java.util.*;

interface DiscountStrategyB2 {
    double applyDiscount(double totalAmount);
}

class PercentageDiscountB2 implements DiscountStrategyB2 {
    private double percentage;
    public PercentageDiscountB2(double percentage) { this.percentage = percentage; }
    @Override
    public double applyDiscount(double totalAmount) { return totalAmount * (1 - percentage / 100); }
}

class FixedDiscountB2 implements DiscountStrategyB2 {
    private double fixedAmount;
    public FixedDiscountB2(double fixedAmount) { this.fixedAmount = fixedAmount; }
    @Override
    public double applyDiscount(double totalAmount) { return Math.max(0, totalAmount - fixedAmount); }
}

class NoDiscountB2 implements DiscountStrategyB2 {
    @Override
    public double applyDiscount(double totalAmount) { return totalAmount; }
}

class HolidayDiscountB2 implements DiscountStrategyB2 {
    private double percentage;
    public HolidayDiscountB2(double percentage) { this.percentage = percentage; }
    @Override
    public double applyDiscount(double totalAmount) { return totalAmount * (1 - percentage / 100); }
}

class OrderCalculatorB2 {
    private DiscountStrategyB2 discountStrategy;

    public OrderCalculatorB2(DiscountStrategyB2 strategy) {
        this.discountStrategy = strategy;
    }

    public void setDiscountStrategy(DiscountStrategyB2 strategy) {
        this.discountStrategy = strategy;
    }

    public double calculateFinalAmount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}

public class b2 {
    public static void main(String[] args) {
        double total = 1000000;

        OrderCalculatorB2 calculator = new OrderCalculatorB2(new PercentageDiscountB2(10));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(total));

        calculator.setDiscountStrategy(new FixedDiscountB2(50000));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(total));

        calculator.setDiscountStrategy(new NoDiscountB2());
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(total));

        calculator.setDiscountStrategy(new HolidayDiscountB2(15));
        System.out.println("Thêm HolidayDiscount 15% (không sửa code cũ)");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(total));
    }
}