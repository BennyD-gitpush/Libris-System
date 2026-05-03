package LIBRISZ;

public class LibrisDemo {
    public static void main(String[] args) {

        User user = new User();
        user.username = "Earl";

        System.out.println("PAYMENT FOR SUBSCRIPTION--------------------------");

        Payment payment = new Payment("GCash", 199.00);
        payment.processPayment();

        PaymentDAO.savePayment(user.username, payment);

        System.out.println("User: " + user.username);
        System.out.println("Payment Method: " + payment.paymentMethod);
        System.out.println("Amount: PHP " + payment.amount);
        System.out.println("Paid: " + payment.paid);

        if (payment.paid) {
            System.out.println("Payment successful.");

            System.out.println("\nSUBSCRIPTION MANAGEMENT--------------------------");

            Subscription subscription = new Subscription("Premium Monthly", 199.00);
            subscription.activate();

            user.isPremium = true;
            user.subscription = subscription;

            SubscriptionDAO.saveSubscription(user.username, subscription);

            System.out.println("Subscription Plan: " + subscription.planName);
            System.out.println("Subscription Active: " + subscription.active);
            System.out.println("User Premium Status: " + user.isPremium);

            System.out.println("\nREADING PROGRESS TRACKING--------------------------");

            ReadingProgress progress = new ReadingProgress("Java Programming", 300);
            progress.updateProgress(45);

            ReadingProgressDAO.saveProgress(user.username, progress);

            System.out.println("Book: " + progress.bookTitle);
            System.out.println("Current Page: " + progress.currentPage);
            System.out.println("Total Pages: " + progress.totalPages);
            System.out.println("Progress: " + progress.getPercentage() + "%");

        } else {
            System.out.println("Payment failed.");
            System.out.println("Subscription not activated.");
            System.out.println("Reading progress not saved.");
        }

        System.out.println("\nFAILED PAYMENT TEST------------------------------");

        Payment failedPayment = new Payment("GCash", 0.00);
        failedPayment.processPayment();

        PaymentDAO.savePayment(user.username, failedPayment);

        System.out.println("User: " + user.username);
        System.out.println("Payment Method: " + failedPayment.paymentMethod);
        System.out.println("Amount: PHP " + failedPayment.amount);
        System.out.println("Paid: " + failedPayment.paid);

        if (failedPayment.paid) {
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }

        System.out.println("\nSUBSCRIPTION NOT ACTIVATED TEST-------------------");

        User failedUser = new User();
        failedUser.username = "FailedUser";

        Subscription failedSubscription = new Subscription("Premium Monthly", 199.00);

        if (failedPayment.paid) {
            failedSubscription.activate();
            failedUser.isPremium = true;

            SubscriptionDAO.saveSubscription(failedUser.username, failedSubscription);

            System.out.println("Subscription activated.");
        } else {
            System.out.println("Payment failed.");
            System.out.println("Subscription not activated.");
            System.out.println("Subscription Active: " + failedSubscription.active);
            System.out.println("User Premium Status: " + failedUser.isPremium);
        }

        System.out.println("\nINVALID READING PAGE TEST-------------------------");

        ReadingProgress invalidProgress = new ReadingProgress("Java Programming", 300);
        invalidProgress.updateProgress(400);

        System.out.println("Book: " + invalidProgress.bookTitle);
        System.out.println("Attempted Page: 400");
        System.out.println("Total Pages: " + invalidProgress.totalPages);

        if (invalidProgress.currentPage == 0) {
            System.out.println("Invalid page. Reading progress not updated.");
        } else {
            ReadingProgressDAO.saveProgress(user.username, invalidProgress);
            System.out.println("Reading progress saved successfully.");
        }

        System.out.println("Current Page: " + invalidProgress.currentPage);
        System.out.println("Progress: " + invalidProgress.getPercentage() + "%");

        System.out.println("\nVIEW SAVED USER ACTIVITY--------------------------");

        ReportDAO.viewUserActivity(user.username);
    }
}