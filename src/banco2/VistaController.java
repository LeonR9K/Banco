/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco2;


import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Modelo;

/**
 * FXML Controller class
 *
 * @author MASTERL
 */
public class VistaController implements Initializable {
    
      @FXML
    private TextField txtCodigoBanco;
    @FXML
    private TextField txtCodigoSucursal;
    @FXML
    private TextField txtNumeroCuenta;
    @FXML
    private TextArea txtAreaMuestra;
    @FXML
    private Button btnAceptar;

    @FXML
    private MenuButton mbtnMovimientos;
    @FXML
    private MenuItem miTalonario;
    @FXML
    private MenuItem miMovimientos;
    @FXML
    private TextField txtClavePersonal;
    Modelo mod = new Modelo();
     private boolean tal, mov; //Usados para la validacion del MenuButton
    private boolean cb, cs, nc, cp; //Usados para validacion de numeros en cajas de texto
    private boolean entrada; //Usado para validar el envio 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicktxtCodigoSucursal(MouseEvent event) {
    }

    @FXML
    private void clicktxtNumeroCuenta(MouseEvent event) {
    }

    @FXML
    private void clicktxtClavePersonal(MouseEvent event) {
    }

    @FXML
    private void TalonarioIsSelected(ActionEvent event) {
        mbtnMovimientos.setText("Talonario");
        tal = true;
        mov = false;
    }

    @FXML
    private void MovimientosIsSelected(ActionEvent event) {
        mbtnMovimientos.setText("Movimientos");
        mov = true;
        tal = false;
    }

    @FXML
    private void Aceptar(ActionEvent event) {
        
        LocalDateTime locaDate = LocalDateTime.now();
        int hours = locaDate.getHour();
        int minutes = locaDate.getMinute();
        int seconds = locaDate.getSecond();
       // System.out.println("Hora actual : " + hours + ":" + minutes + ":" + seconds);
        
        String codBan = txtCodigoBanco.getText();
        String codSuc = txtCodigoSucursal.getText();
        String numCuenta = txtNumeroCuenta.getText();
        String clPers = txtClavePersonal.getText();
        ////////////////////////////////////////////////////////////////////////// CODIGO DE BANCO
        String firstChar;
        int fc = 0;
        cb = false;
        if (codBan.isEmpty()) {
            System.out.println("Estoy vacio por dentro");
            cb = true;

        } else {
            cb = false;
        }
        if (cb == false && mod.isNumeric(codBan)) {

            firstChar = String.valueOf(codBan.charAt(0));
            fc = Integer.parseInt(firstChar);

            if (fc > 1 && cb == false) {
                cb = true;
                System.out.println("Mi primer digito es mayor a 1");
            } else {
                cb = false;
            }

            if (codBan.length() == 3 && cb == true) {
                System.out.println("Menor de 4");
                cb = true;
            } else {
                cb = false;
            }
        }

        if (cb == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en Codigo de Banco");
            alert.setContentText("Inserta un valor numerico menor de 3 digitos o deja el espacio en blanco");
            alert.showAndWait();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////CODIGO SUCURSAL

        String firstChar2 = String.valueOf(codSuc.charAt(0));
        int fc2 = Integer.parseInt(firstChar2);
        if (mod.isNumeric(codSuc) && fc2 > 0 && codSuc.length() == 4) {
            cs = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en Codigo de Sucursal");
            alert.setContentText("Inserta un valor numerico  de 4 digitos");
            alert.showAndWait();
            cs = false;
        }

        ////////////////////////////////////////////////////////////////////////////////////////NUMERO DE CUENTA
        if (mod.isNumeric(numCuenta) && numCuenta.length() == 5) {
            nc = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en Numero de Cuenta");
            alert.setContentText("Inserta un valor numerico  de 5 digitos");
            alert.showAndWait();
            nc = false;
        }

        ///////////////////////////////////////////////////////////////////////////////////////CLAVE PERSONAL
        if (clPers.length() == 5 && clPers.length() > 0) {
            cp = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en Clave Personal");
            alert.setContentText("Inserta un valor alfanumerico de 5 pocisiones");
            alert.showAndWait();
            cp = false;
        }
        if (tal && cp && cs && nc && cb) {
            txtAreaMuestra.appendText("--ENVIO DE TALONARIOS--Hora:  "+ hours + ":" + minutes + ":" + seconds);
            txtAreaMuestra.appendText(System.getProperty("line.separator"));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("ENVIO DE TALONARIOS");
            alert.showAndWait();

        }
        if (mov && cp && cs && nc && cb) {
            txtAreaMuestra.appendText("--ENVIO DE MOVIMIENTOS--Hora:  "+ hours + ":" + minutes + ":" + seconds);
            txtAreaMuestra.appendText(System.getProperty("line.separator"));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("ENVIO DE MOVIMIENTOS");
            alert.showAndWait();

        }
        if (mov == false && tal == false && cp && cs && nc && cb) {
            txtAreaMuestra.appendText("--TALONARIO y MOVIMIENTO--Hora:  "+ hours + ":" + minutes + ":" + seconds);
            txtAreaMuestra.appendText(System.getProperty("line.separator"));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("ENVIO DE TALONARIOS Y MOVIMIENTOS");
            alert.showAndWait();
        }
    }
    
}
