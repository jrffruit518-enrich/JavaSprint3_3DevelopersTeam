package org.s3team;

import org.s3team.DataBaseConnection.Data_Base_Connection;
import org.s3team.DataBaseConnection.MySQL_Data_Base_Connection;
import org.s3team.Menu.AppFactory;
import org.s3team.Menu.MainMenuController;
import org.s3team.clue.dao.ClueDao;
import org.s3team.clue.dao.ClueDaoImpl;
import org.s3team.clue.service.ClueService;
import org.s3team.decoration.dao.DecorationDao;
import org.s3team.decoration.dao.DecorationDaoImpl;
import org.s3team.decoration.service.DecorationService;
import org.s3team.inventoryMenu.*;
import org.s3team.inventoryService.InventoryManagementService;
import org.s3team.inventoryService.InventoryQueryService;
import org.s3team.room.DAO.RoomDAO;
import org.s3team.room.DAO.RoomDAOImp;
import org.s3team.room.Service.RoomService;
import org.s3team.theme.dao.ThemeDao;
import org.s3team.theme.dao.ThemeDaoImpl;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- S3.3 Escape Room Application Starting ---");
        Scanner scanner = new Scanner(System.in);
        MySQL_Data_Base_Connection dbInstance = null;

        // 1. Intentar obtener la instancia Singleton y abrir la conexión
        try {
            // El método getInstance() intentará conectar a MySQL.
            // Si falla, lanzará una RuntimeException y el programa terminará.
            dbInstance = MySQL_Data_Base_Connection.getInstance();

            // 2. Si llegamos aquí, la conexión fue exitosa.
            Connection conn = dbInstance.getConnection();

            System.out.println("Database Connection Status: SUCCESS!");
            System.out.println("JDBC Connection Object: " + conn);

            // Aquí iría el resto de la lógica de tu aplicación


        } catch (RuntimeException e) {
            System.err.println("FATAL ERROR: Application failed to initialize due to connection failure.");
            System.err.println("Check DB container status, network, and credentials.");
            e.printStackTrace();
            // Salida con código de error para que Docker sepa que falló el inicio
            System.exit(1);
        }

        AppFactory appFactory = new AppFactory(dbInstance);
        InventoryMenu inventoryMenu = appFactory.inventoryMenuGenerate(scanner);
        MainMenuController startApp = new MainMenuController(inventoryMenu);
        startApp.startApplication();
        scanner.close();
    }
}