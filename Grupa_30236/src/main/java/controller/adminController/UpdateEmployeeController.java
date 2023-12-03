package controller.adminController;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationServiceMySQL;
import view.admin.UpdateEmployeeView;

import java.sql.Connection;

public class UpdateEmployeeController {

    private final RightsRolesRepositoryMySQL rightsRolesRepository;
    private UpdateEmployeeView updateEmployeeView;
    private UserRepository userRepository;

    private AuthenticationServiceMySQL authenticationService;




    public UpdateEmployeeController(UpdateEmployeeView updateEmployeeView) {
        this.updateEmployeeView = updateEmployeeView;
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);


        updateEmployeeView.setUpdateButtonHandler(this::handleUpdateButton);
    }

    private void handleUpdateButton(ActionEvent event) {
        Long idEmployee = Long.valueOf(updateEmployeeView.getIdTextFieldText());
        String username = updateEmployeeView.getUsernameTextFieldText();
        String password = updateEmployeeView.getPasswordFieldText();
        String role    = updateEmployeeView.getRolesTextFieldText();
        Long roleID = 0L;
        if(role.equals("administrator"))
            roleID = 1L;
        else{
            if(role.equals("employee"))
                roleID = 2L;
            else
                roleID = 3L;
        }
        this.userRepository.updateEmployee(idEmployee,username,roleID);
        this.updateEmployeeView.close();

    }



}
