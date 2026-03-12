public class PermissionService {
    
    public boolean canPerformAction(User user, Action action) {
        if (user == null || action == null) {
            return false;
        }
        
        Role role = user.getRole();
        
        switch (role) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action != Action.DELETE_USER;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}
