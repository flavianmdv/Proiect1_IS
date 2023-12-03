package controller.adminController;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.User;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import view.admin.AddEmployeeView;
import view.admin.AdminView;
import view.admin.DeleteEmployeeView;
import view.admin.UpdateEmployeeView;

import java.sql.Connection;
import java.util.List;

public class AdminController {

    private final RightsRolesRepositoryMySQL rightsRolesRepository;
    private AdminView adminView;
    private UserRepository userRepository;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);


        adminView.addViewAllButtonListener(this::handleViewAllButton);
        adminView.addAddButtonListener(this::handleAddButton);
        adminView.addUpdateButtonListener(this::handleUpdateButton);
        adminView.addDeleteButtonListener(this::handleDeleteButton);
        adminView.addCloseButtonListener(this::handleCloseButton);
    }

    private void handleViewAllButton(ActionEvent event) {
        List<User> employees = this.userRepository.findAll();
        adminView.setEmployeesInTable(employees);
    }

    private void handleAddButton(ActionEvent event) {
        AddEmployeeView addEmployeeView = new AddEmployeeView();
        AddEmployeeController addEmployeeController = new AddEmployeeController(addEmployeeView);
    }

    private void handleUpdateButton(ActionEvent event) {
        UpdateEmployeeView updateEmployeeView = new UpdateEmployeeView();
        UpdateEmployeeController updateEmployeeController = new UpdateEmployeeController(updateEmployeeView);
    }

    private void handleDeleteButton(ActionEvent event) {
        DeleteEmployeeView deleteEmployeeView = new DeleteEmployeeView();
        DeleteEmployeeController deleteEmployeeController = new DeleteEmployeeController(deleteEmployeeView);

    }

    private void handleCloseButton(ActionEvent event) {
        adminView.getPrimaryStageAdmin().close();
    }

}
