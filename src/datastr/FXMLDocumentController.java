package datastr;

import Code_97.Polynomial;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXButton;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Hodaifa A Quraini
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea lbAreal_ResultEquation;
    @FXML
    private AnchorPane mainPanel;
    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_sub;

    @FXML
    private JFXButton btn_mult;
    @FXML
    private TextField txt_toSolve;
    @FXML
    private JFXToolbar tog_bar;

    @FXML
    private TextField txt_FirstEquation;

    @FXML
    private TextField txt_SecondEquation;

    @FXML
    private Label lbl_solve;
    @FXML
    private JFXRadioButton rd_Infix;

    @FXML
    private JFXRadioButton rd_Postfix;

    @FXML
    private JFXRadioButton rd_Prefix;
    @FXML
    private TextArea lblArea_LastEquation;
    @FXML
    public void Add(ActionEvent event) {
        String equation1 = txt_FirstEquation.getText().trim();
        String equation2 = txt_SecondEquation.getText().trim();
        if (equation1.isEmpty() || equation2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter two equation !");
        } else {
            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();
            Polynomial.preparePoly(equation1, p1);
            Polynomial.preparePoly(equation2, p2);
            Polynomial Add = Polynomial.PolynomialAdd(p1, p2);
            lbAreal_ResultEquation.setText(Polynomial.printEquation(Add));
        }
    }

    @FXML
    public void multiply(ActionEvent event) {
        String equation1 = txt_FirstEquation.getText().trim();
        String equation2 = txt_SecondEquation.getText().trim();
        if (equation1.isEmpty() || equation2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter two equation !");
        } else {
            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();
            Polynomial.preparePoly(equation1, p1);
            Polynomial.preparePoly(equation2, p2);

            Polynomial Mult = Polynomial.PolynomialMultyplay(p1, p2);
            lbAreal_ResultEquation.setText(Polynomial.printEquation(Mult));
        }
    }

    @FXML
    public void subtraction(ActionEvent event) {
        String equation1 = txt_FirstEquation.getText().trim();
        String equation2 = txt_SecondEquation.getText().trim();
        if (equation1.isEmpty() || equation2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter two equation !");
        } else {
            Polynomial p1 = new Polynomial();
            Polynomial p2 = new Polynomial();
            Polynomial.preparePoly(equation1, p1);
            Polynomial.preparePoly(equation2, p2);
            Polynomial subtraction = Polynomial.PolynomialSubtrc(p1, p2);
            lbAreal_ResultEquation.setText(Polynomial.printEquation(subtraction));
        }
    }

    @FXML
    void infix(ActionEvent event) {
        lblArea_LastEquation.setText("");
        String equation = lbAreal_ResultEquation.getText().trim();
        if (equation.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Check the equation that you want ro convert it !");
        } else {
            lblArea_LastEquation.setText(equation);
        }
    }

    @FXML
    void postfix(ActionEvent event) {
        lblArea_LastEquation.setText("");
        String equation = lbAreal_ResultEquation.getText().trim();
        if (equation.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Check the equation that you want ro convert it !");
        } else {
            lblArea_LastEquation.setText(Code_97.InfixToPostfix.Prepare(equation));
        }
    }

    @FXML
    void prefix(ActionEvent event) {
        lblArea_LastEquation.setText("");
        String equation = lbAreal_ResultEquation.getText().trim();
        if (equation.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Check the equation that you want ro convert it !");
        } else {
            lblArea_LastEquation.setText(Code_97.prefix.Prepare(equation));
        }
    }

    @FXML
    void Solve(ActionEvent event) {

        if (txt_toSolve.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter the value pleas !");
        } else if (lbAreal_ResultEquation.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No equation to solve !");
        } else {
            double value = Double.parseDouble(txt_toSolve.getText().trim());
            Code_97.Polynomial p = new Polynomial();
            Polynomial.preparePoly(lbAreal_ResultEquation.getText().trim(), p);
            double result=Polynomial.SolveEquation(value, p) ;
            NumberFormat formatter = new DecimalFormat("#0.0000"); 
            
            lbl_solve.setText(formatter.format(result));
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        txt_FirstEquation.setText("");
        txt_SecondEquation.setText("");
        txt_toSolve.setText("");
        lblArea_LastEquation.setText("");
        lbAreal_ResultEquation.setText("");
        lbl_solve.setText("");
        rd_Infix.setSelected(false);
        rd_Postfix.setSelected(false);
        rd_Prefix.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        rd_Infix.setToggleGroup(group);
        rd_Postfix.setToggleGroup(group);
        rd_Prefix.setToggleGroup(group);
    }

}
