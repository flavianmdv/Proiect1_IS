package controller.adminController;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import view.admin.DeleteEmployeeView;

import java.sql.Connection;

public class DeleteEmployeeController {

    private final DeleteEmployeeView deleteEmployeeView;
    private final RightsRolesRepositoryMySQL rightsRolesRepository;
    private UserRepository userRepository;

    public DeleteEmployeeController(DeleteEmployeeView deleteEmployeeView) {
        this.deleteEmployeeView = deleteEmployeeView;
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        deleteEmployeeView.addDeleteButtonListener(this::handleDeleteButton);
    }

    private void handleDeleteButton(ActionEvent event) {
        Long employeeId = deleteEmployeeView.getIdTextFieldValue();
        this.userRepository.deleteById(employeeId);
        this.deleteEmployeeView.close();

    }
}
