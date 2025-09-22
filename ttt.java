import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ttt {
    String playerX = "X";
    String playerO = "O"; 
    String currentPlayer = playerX; //default player is X
    JButton[][] board = new JButton[3][3]; //3x3 tiles
    boolean gameOver = false;  //checks winners
    JLabel textLabel; 
    int turns = 0; //tie

    public ttt() {
        int boardWidth = 600;
        int boardHeight = 650; //50px for display

        JFrame frame = new JFrame("Let's Play a Game!"); //window
        textLabel = new JLabel(); //text
        JPanel boardPanel = new JPanel(); //game board
        

        frame.setSize(boardWidth, boardHeight); //window size 
        frame.setLocationRelativeTo(null); //centers the window on the screen when it opens
        frame.setResizable(false); //prevents user from resizing the window (useful for fixed layouts)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window when clicked to close
        frame.setLayout(new BorderLayout()); /*sets the layout manager for the frame's content pane;
        BorderLayout allows you to place components in five regions: NORTH, SOUTH, EAST, WEST, and CENTER */
        

        textLabel.setBackground(new Color(255, 240, 245)); //background color ; RBG color
        textLabel.setForeground(Color.PINK); //font color
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 50)); //font type, bold, font size
        textLabel.setHorizontalAlignment(JLabel.CENTER); //location of text
        textLabel.setVerticalAlignment(JLabel.CENTER); //location of text
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true); //shows background color

        frame.add(textLabel); //adds Label contents to window
        frame.add(textLabel, BorderLayout.NORTH); //sets text at the top of the window

        boardPanel.setLayout(new GridLayout(3, 3)); //3x3 game board
        boardPanel.setBackground(new Color(255, 240, 245)); //game board color
        frame.add(boardPanel); 

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton(); //creates tiles for x's and o's
                board[r][c] = tile; //r = row ; c = column
                boardPanel.add(tile);

                tile.setBackground(Color.PINK); //tile, x and o color and font size
                tile.setForeground(Color.PINK);
                tile.setFont(new Font("Times New Roman", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setOpaque(true); 

                tile.addActionListener(new ActionListener() {  
                    //runs when button is pushed (tiles)
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return; //game ignored if game is over
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText().equals("")){ //only place a mark if the tile is empty -> " "
                            tile.setText(currentPlayer); //place current player's symbol 
                            turns++; //increment turn count
                            checkWinner(); //check if move wins the game
                            //if no winnder yet, switch to other player:
                            if(!gameOver) {
                               currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                               textLabel.setText(currentPlayer + "'s turn!"); 
                            }
                        }

                        
                    
                    }
                });
            }
        }
        frame.setVisible(true); //displays everything on the window
    }
    //displays x or o winner:
    void checkWinner(){
        //horizontal winners:
        for(int r=0; r<3; r++){ //loop through each row
            if(board[r][0].getText().equals ("")) continue; //skip if first tile is empty (no move yet)
            //check if all three tiles in the same row have an X or O:
            if(board[r][0].getText() == board[r][1].getText() &&
               board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0; i < 3; i++) { //highlight all three winning tiles
                    setWinner(board[r][i]);
                }
                gameOver = true; //prevents further moves if game is over
                return;
            }
        }
        //vertical winners:
        for(int c = 0; c < 3; c++) { //loop through each column (c = 0, 1, 2)
            if(board[0][c].getText().equals("")) continue; //skip column if top cell is empty 
            //check if all three tiles in the column have the same symbol
            if(board[0][c].getText() == board[1][c].getText() && 
               board[1][c].getText() == board[2][c].getText()) {
                //highlight the winning tiles in that column
                for(int i = 0; i < 3; i++){
                    setWinner(board[i][c]);
                }
                gameOver = true; 
                return; 
               }
        }
        //diagonal
        if(board[0][0].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][2].getText()&&
           board[0][0].getText() != "") {
            //highlight the winning tiles in the diagonal
            for(int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
        }
        gameOver = true;
        return;
      }
      //anti-diagonally
      if(board[0][2].getText() == board[1][1].getText() &&
         board[1][1].getText() == board[2][0].getText() && 
         board[0][2].getText() != "") {
            //highlight the winning anti-diagonal
         setWinner(board[0][2]);
         setWinner(board[1][1]);
         setWinner(board[2][0]);
         gameOver = true; 
         return; 
      }
      //tie check: if all 9 tiles have been used and there's no winner, it's a tie
      if(turns == 9) {
        //color all cells as part of the tie
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                setTie(board[r][c]);
            }
        }
        gameOver = true;
      }
    }
    //method to declare winner:
    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN); //set color of winning symbols to green 
        tile.setBackground(Color.PINK);
        tile.setOpaque(true);
        textLabel.setText(currentPlayer + " is the winner!"); //display who the winner is 

    }
    //method for a tie:
    void setTie(JButton tile) {
        tile.setForeground(Color.ORANGE); //set color of every symbol on the board to orange 
        tile.setBackground(Color.PINK);
        textLabel.setText("Tie!"); //display a tie message
    }
    //method to start the game:
    public static void main(String[] args){
        new ttt();
    }
}

    




