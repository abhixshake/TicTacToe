import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth=700;
    int boardHeight=750;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel(); //For reset

    JButton[][] board = new JButton[3][3];
    JButton resetButton = new JButton("Reset"); // Reset button
    String playerX="X";
    String playerO="O";
    String currentPlayer=playerX;

    boolean gameOver = false;
    int turns=0;

    //Counters
    int xWins=0;
    int oWins=0;

    TicTacToe()
    {
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.lightGray);
        frame.add(boardPanel);

        //Board Panel
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        if(gameOver)
                            return;
                        JButton tile =(JButton) e.getSource();
                        if(tile.getText()=="")
                        {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                                updateLabel();

                            }
                        }

                    }
                    
                });
            }
        }

        //Reset Panel
        buttonPanel.setLayout(new FlowLayout());
        resetButton.setFont(new Font("Arial",Font.BOLD,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        buttonPanel.add(resetButton);
        frame.add(buttonPanel,BorderLayout.SOUTH);

   }
   void checkWinner()
   {
        //horizontal
        for(int r=0;r<3;r++)
        {
            if(board[r][0].getText()=="")
            {
                continue;
            }
            if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver=true;
                updateScore();
                return;
            }
        }

        //vertical
        for(int c=0;c<3;c++)
        {
            if(board[0][c].getText()=="")
            {
                continue;
            }
            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver=true;
                updateScore();
                return;
            }
        }
        //diagonal
        if(board[0][0].getText()==board[1][1].getText() && board[1][1].getText()==board[2][2].getText() && board[0][0].getText() !="")
        {
            for(int i=0;i<3;i++)
            {
                setWinner(board[i][i]);
            }
            gameOver = true;
            updateScore();
            return;
        }
        //antidiagonal
        if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText() && board[0][2].getText() !="")
        {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            updateScore();
            gameOver = true;
            return;
        }
        if(turns==9)
        {
            for(int r=0;r<3;r++){
                for(int c=0;c<3;c++)
                {
                    setTie(board[r][c]);
                }
                textLabel.setText("It's a Tie! X:"+xWins+" | O:"+oWins);
                gameOver=true;
            }
        }
   }
   void setWinner(JButton tile)
   {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
   }
   void setTie(JButton tile)
   {
       tile.setForeground(Color.orange);
       tile.setBackground(Color.gray);
   }
   void updateScore()
   {
        if(currentPlayer.equals(playerX))
            xWins++;
        else
            oWins++;
            textLabel.setText(currentPlayer+" is the winner! X: "+xWins+" | O: "+oWins); 
   }
   void updateLabel()
   {
    textLabel.setText(currentPlayer+"'s turn. X: "+xWins+" | O: "+oWins);

   }

   //Reset state
   void resetGame()
   {
     //Clear board
     for(int r=0;r<3;r++)
     {
        for(int c=0;c<3;c++)
        {
            board[r][c].setText("");
            board[r][c].setForeground(Color.white);
            board[r][c].setBackground(Color.darkGray);
        }
     }
     //Resetting
     currentPlayer=playerX;
     gameOver=false;
     turns=0;
     textLabel.setText("Tic-Taac-Toe");
     updateLabel();
   }
}
