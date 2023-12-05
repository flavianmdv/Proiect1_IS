package controller.adminController;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validator.Notification;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationServiceMySQL;
import view.admin.AddEmployeeView;

import java.sql.Connection;
import java.util.List;

public class AddEmployeeController {

    private final RightsRolesRepositoryMySQL rightsRolesRepository;
    private AddEmployeeView addEmployeeView;
    private UserRepository userRepository;
    private AuthenticationServiceMySQL authenticationService;


    public AddEmployeeController(AddEmployeeView addEmployeeView) {
        this.addEmployeeView = addEmployeeView;
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);

        addEmployeeView.addButtonListener(this::handleAddButton);
    }

    private void handleAddButton(ActionEvent event) {
        String username = addEmployeeView.getUsernameTextFieldText();
        String password = addEmployeeView.getPasswordFieldText();
        List<Role> roles = addEmployeeView.getRolesTextFieldText();
        User user = new UserBuilder().setUsername(username).setPassword(password).setRoles(roles).build();
        Notification<Boolean> registerNotification = authenticationService.registerEmployee(username, password);

//        this.userRepository.save(user);
        addEmployeeView.close();
    }
}



