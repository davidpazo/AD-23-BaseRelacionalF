package BaseRelacional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author oracle
 */
public class BaseRelacionalF {

   Connection conexion;
   ResultSet rs;
   CallableStatement cst;

    public static void main(String[] args) {
        BaseRelacionalF brf = new BaseRelacionalF().conectar();
        brf.procedimiento(213478, 235478);
    }

    public BaseRelacionalF conectar() {
        try {
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:orcl";
            conexion = DriverManager.getConnection(BaseDeDatos, "hr", "hr");
            if (conexion != null) {
                System.out.println("Conexion exitosa!");
            } else {
                System.out.println("Conexion fallida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    public void procedimiento(int a, int b) {
        try {
            cst = conexion.prepareCall("call pjavaprocoracle(?,?)");
            cst.setInt(1, a);
            cst.setInt(2, b);
            cst.registerOutParameter(2, Types.INTEGER);
            cst.execute();
            System.out.println("A= " + a);
            System.out.println("B= " + b);
            System.out.println("Resultado: " + cst.getInt(2));
            cst.close();

        } catch (SQLException ex) {
            System.out.println("SQLExcpetion " + ex);
        }
    }
}
