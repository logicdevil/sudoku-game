import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by daymond on 3/15/17.
 */
public class SudokuGame {
     ArrayList<Integer> array;
     boolean end, rightResult;
    ArrayList<JTextField> textFieldArray;
    void start() {
        array = new ArrayList<>();
        JFrame frame = new JFrame("Sudoku");
        JPanel panel = new JPanel(new GridLayout(9, 9), true);
        textFieldArray = new ArrayList<>();


        for(int i = 0; i < 81; i++) {
            JTextField textField = new JTextField();
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
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

        /*for(int i = 0; i < 9; i++) {
            if(i%3 == 0)
                System.out.println("----------------------------------");
            for(int j = 0; j < 9; j++) {
                if(j%3 == 0)
                    System.out.print("| ");
                System.out.print(arrayList.get(i*9 + j) + "  ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("----------------------------------");*/

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
        while (true) {
            MyTree root = new MyTree(this, 0);
            root.start();
            if(rightResult)
                break;
        }
        printArray(array, textFieldArray);
    }
}
