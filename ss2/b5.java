public class b5 {
    static void run() {
        SuperAdmin superadmin = new SuperAdmin();

        System.out.println("=== Testing Diamond Problem Solution ===");
        superadmin.logActivity("System checking...");

        System.out.println("\n=== Calling specific interface logic via wrappers ===");
        superadmin.logAsUser("Logging as user context");
        superadmin.logAsAdmin("Logging as admin context");
    }

    public static void main(String[] args) {
        run();
    }
}

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("UserActions: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("AdminActions: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        System.out.println("SuperAdmin processing: " + activity);
        UserActions.super.logActivity(activity);
    }

    public void logAsUser(String activity) {
        UserActions.super.logActivity(activity);
    }

    public void logAsAdmin(String activity) {
        AdminActions.super.logActivity(activity);
    }
}