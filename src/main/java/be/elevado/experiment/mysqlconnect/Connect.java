package be.elevado.experiment.mysqlconnect;

import be.elevado.experiment.mysqlconnect.dto.ConnectParams;
import be.elevado.experiment.mysqlconnect.dto.ConnectResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/my/secret/api")
public class Connect {
    @GetMapping
    public ConnectResult sayHello() {
        ConnectResult result = new ConnectResult();
        result.setConnected(false);
        result.setMessage("Hi there!");
        return result;
    }

    @PostMapping
    public ConnectResult tryToConnect(
            @RequestBody ConnectParams connectParams
    ) {
        String responseMessage = "";
        boolean isConnected = false;
        try{
//            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    String.format("jdbc:mysql://%s:%d/%s", connectParams.getServiceName(), connectParams.getPort(), connectParams.getDatabaseName()),
                    connectParams.getUsername(), connectParams.getPassword());

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("show tables");
            while(rs.next())
                responseMessage += (rs.getString(1) + "\n");
            con.close();
            isConnected = true;
        }
        catch(Exception e){ responseMessage = "ERROR: " + e.getMessage();}

        ConnectResult result = new ConnectResult();
        result.setConnected(isConnected);
        result.setMessage(responseMessage);
        return result;
    }
}
