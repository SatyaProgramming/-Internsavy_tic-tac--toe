import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tic_tac_toe extends JPanel {
    private char player = 'x';
    private final int rows = 3;
    private final int cols = 3;
    private final JButton[][] buttons = new JButton[rows][cols];

    public Tic_tac_toe() {
        GridLayout gridLayout = new GridLayout(rows, cols);
        setLayout(gridLayout);
        createButtons();
    }

    private void createButtons() {
        Font font = new Font("Arial", Font.BOLD, 24);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton button = new JButton();
                button.setFont(font);
                button.addActionListener(e -> {
                    button.setText(String.valueOf(player));
                    button.setEnabled(false);

                    if (checkWinner()) {
                        showWinner();
                    } else if (checkDraw()) {
                        showDraw();
                    } else {
                        player = player == 'x' ? 'o' : 'x';
                    }
                });

                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private boolean checkDraw() {
        for (JButton[] buttonRow : buttons) {
            for (JButton button : buttonRow) {
                if (button.isEnabled()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < rows; row++) {
            if (checkRowCol(buttons[row][0], buttons[row][1], buttons[row][2])) {
                return true;
            }
        }

        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < cols; col++) {
            if (checkRowCol(buttons[0][col], buttons[1][col], buttons[2][col])) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonals() {
        return checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2])
            || checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkRowCol(JButton b1, JButton b2, JButton b3) {
        return !b1.isEnabled() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void showWinner() {
        JOptionPane.showMessageDialog(this, "Player " + player + " wins!");
        System.exit(0);
    }

    private void showDraw() {
        JOptionPane.showMessageDialog(this, "Draw!");
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Tic_tac_toe());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
