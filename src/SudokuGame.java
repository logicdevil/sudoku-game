import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by daymond on 3/15/17.
 */
public class SudokuGame {
    private ArrayList<Integer> array;
    boolean end, rightResult;
    private ArrayList<JTextField> textFieldArray;
    void start() {
        JFrame frame = new JFrame("Sudoku");
        JPanel panel = new JPanel(new GridLayout(9, 9), true);
        array = new ArrayList<>();
        textFieldArray = new ArrayList<>();
        for(int i = 0; i < 81; i++) {
            JTextField textField = new JTextField();
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                        doCalculating();
                    }
                    else {
                        if (e.getKeyChar() > 48 && e.getKeyChar() < 58) { // 49 == 1, 57 == 9 ASCII
                            textField.setText("" + e.getKeyChar());
                        } else textField.setText("");
                        e.consume();
                    }
                }
            });
            textField.setHorizontalAlignment(JTextField.CENTER);
            textFieldArray.add(textField);
            panel.add(textField);
        }
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);



    }
    void printArray(ArrayList<Integer> arrayList, ArrayList<JTextField> textFieldArray) {
        for(int i = 0; i < arrayList.size(); i++) {
            textFieldArray.get(i).setText("" + arrayList.get(i));
        }
    }
    void doCalculating() {
        end  = false;
        rightResult = false;
        array.clear();
        for(JTextField text : textFieldArray) {
            if(text.getText().equals(""))
                array.add(0);
            else array.add(Integer.parseInt(text.getText()));
        }
        if(!validate(array))
            JOptionPane.showMessageDialog(null, "Incorrect!");
        else {
            MyTree root = new MyTree(this, 0);
            root.start();
            printArray(array, textFieldArray);
        }
    }
    public ArrayList<Integer> getArray() {
        return array;
    }

    public void setArray(ArrayList<Integer> array) {
        this.array = array;
    }
    boolean validate(ArrayList<Integer> array) {
        for(int i = 0; i < 9; i++) {
            String arrayIntVer = "123456789";
            String arrayIntHor = "123456789";
            for(int j = 0; j < 9; j++) {
                if(arrayIntVer.length() == arrayIntVer.replaceAll(array.get(i*9+j).toString(), "").length() && array.get(i*9+j) != 0)
                    return false;
                else arrayIntVer = arrayIntVer.replaceAll(array.get(i*9+j).toString(), "");
                if(arrayIntHor.length() == arrayIntHor.replaceAll(array.get(i+j*9).toString(), "").length() && array.get(i+j*9) != 0)
                    return false;
                else arrayIntHor = arrayIntHor.replaceAll(array.get(i+j*9).toString(), "");
            }
        }
        return true;
    }
}
